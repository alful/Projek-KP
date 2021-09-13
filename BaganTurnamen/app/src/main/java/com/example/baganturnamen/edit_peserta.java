package com.example.baganturnamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class edit_peserta extends AppCompatActivity {
    EditText ETNama, ETUmur, ETClub;
    CheckBox CBUnggulan;
    DatabaseReference DBRef;
    Button BSimpan;

    ArrayList<Peserta> ALPeserta= new ArrayList<Peserta>();
    ArrayList<String> ALKey= new ArrayList<String>();
    ArrayList<String> ALNama= new ArrayList<String>();
    ArrayList<String> ALClub= new ArrayList<String>();
    ArrayList<Integer> ALUmur = new ArrayList<Integer>();
    ArrayList<String> ALUnggulan = new ArrayList<String>();

    Peserta peserta;

    String SKey, SNama, SUnggulan, SClub;
    Integer IUmur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_peserta);

        ETNama = findViewById(R.id.idETNama);
        ETUmur = findViewById(R.id.idETUmur);
        ETClub = findViewById(R.id.idETClub);
        CBUnggulan = findViewById(R.id.idCBUnggulan);
        BSimpan = findViewById(R.id.idBSimpan);

        peserta = new Peserta();

        DBRef = FirebaseDatabase.getInstance().getReference("Peserta");

        SKey = getIntent().getExtras().getString("key");
        SNama = getIntent().getExtras().getString("nama");
        SClub = getIntent().getExtras().getString("club");
        SUnggulan = getIntent().getExtras().getString("unggulan");
        IUmur = getIntent().getExtras().getInt("umur");

        Log.d("TAG", "onCreate: "+SKey+SNama+SClub+SUnggulan+IUmur);

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

                Toast.makeText(edit_peserta.this, "ubah jadi"+SNama+IUmur+SClub+SUnggulan, Toast.LENGTH_SHORT).show();
            }
        });


//        DBRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
////                    peserta = dataSnapshot.getValue(Peserta.class);
//                    String nama = dataSnapshot.getValue(Peserta.class).getNama();
//                    Integer umur = dataSnapshot.getValue(Peserta.class).getUmur();
//                    String unggulan = dataSnapshot.getValue(Peserta.class).getUnggulan();
//
//                    ALNama.add(nama);
//                    ALUmur.add(umur);
//                    ALUnggulan.add(unggulan);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}