package com.example.baganturnamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class edit_peserta extends AppCompatActivity {
    EditText ETNama, ETUmur, ETClub;
    CheckBox CBUnggulan;
    DatabaseReference DBRef;
    Button BSimpan;

    ArrayList<String> ALUnggulan = new ArrayList<String>();
    ArrayList<String> ALUnggulanYa = new ArrayList<String>();

    String SKey, SNama, SUnggulan, SClub;
    Integer IUmur;

    FirebaseUser firebaseUser;
    String UID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_peserta);

        ETNama = findViewById(R.id.idETNama);
        ETUmur = findViewById(R.id.idETUmur);
        ETClub = findViewById(R.id.idETClub);
        CBUnggulan = findViewById(R.id.idCBUnggulan);
        BSimpan = findViewById(R.id.idBSimpan);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null) {

            UID = firebaseUser.getUid();
        }

        DBRef = FirebaseDatabase.getInstance().getReference("Admin/"+UID+"/Peserta");

        SKey = getIntent().getExtras().getString("key");
        SNama = getIntent().getExtras().getString("nama");
        SClub = getIntent().getExtras().getString("club");
        SUnggulan = getIntent().getExtras().getString("unggulan");
        IUmur = getIntent().getExtras().getInt("umur");

        ETNama.setText(SNama);
        ETUmur.setText(IUmur.toString());
        ETClub.setText(SClub);
        if(SUnggulan.equals("Unggulan")){
            CBUnggulan.setChecked(true);
        }
        else{
            CBUnggulan.setChecked(false);
        }


        BSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            String unggulan = dataSnapshot.getValue(Peserta.class).getUnggulan();

                            ALUnggulan.add(unggulan);
                            if(unggulan.equals("Unggulan")){
                                ALUnggulanYa.add(unggulan);
                            }
                        }
                        if(SUnggulan.equals("Unggulan")) {
                            if(ALUnggulanYa.size()<2){
                                HashMap hashMap = new HashMap();
                                hashMap.put("nama", ETNama.getText().toString());
                                hashMap.put("umur", Integer.parseInt(ETUmur.getText().toString()));
                                hashMap.put("club", ETClub.getText().toString());
                                if(CBUnggulan.isChecked()){
                                    SUnggulan = "Unggulan";
                                }
                                else{
                                    SUnggulan = "Tidak";
                                }
                                hashMap.put("unggulan", SUnggulan);
                                DBRef.child(SKey).updateChildren(hashMap);
                                Toast.makeText(edit_peserta.this, "Berhasil Diubah1", Toast.LENGTH_SHORT).show();
                            }
                            else if(ALUnggulanYa.size()>=2){
                                HashMap hashMap = new HashMap();
                                hashMap.put("nama", ETNama.getText().toString());
                                hashMap.put("umur", Integer.parseInt(ETUmur.getText().toString()));
                                hashMap.put("club", ETClub.getText().toString());
                                if(CBUnggulan.isChecked()){
                                    SUnggulan = "Unggulan";
                                }
                                else{
                                    SUnggulan = "Tidak";
                                }
                                hashMap.put("unggulan", SUnggulan);
                                DBRef.child(SKey).updateChildren(hashMap);
                                Toast.makeText(edit_peserta.this, "Berhasil Diubah", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if(SUnggulan.equals("Tidak")){
                            HashMap hashMap = new HashMap();
                            hashMap.put("nama", ETNama.getText().toString());
                            hashMap.put("umur", Integer.parseInt(ETUmur.getText().toString()));
                            hashMap.put("club", ETClub.getText().toString());
                            hashMap.put("unggulan", SUnggulan);
                            CBUnggulan.setChecked(false);
                            DBRef.child(SKey).updateChildren(hashMap);
                            Toast.makeText(edit_peserta.this, "Berhasil Diubah", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}