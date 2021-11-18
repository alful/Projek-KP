package com.example.baganturnamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.baganturnamen.Full_bracket_turnament.Full_bracket_turnament;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    EditText ETNama, ETUmur, ETClub;
    CheckBox CBUnggulan;
    Button BSimpan, BHapus;

    Integer INo;
    String SNama, SCBUnggulan, SKey, SClub;
    String UID;
    Integer IUmur;

    FirebaseUser firebaseUser;

    Peserta peserta;
    History history;


    ArrayList<Integer> ALNo = new ArrayList<Integer>();
    ArrayList<String> ALKeyAd = new ArrayList<String>();
    ArrayList<String> ALKey = new ArrayList<String>();
    ArrayList<String> ALNama = new ArrayList<String>();
    ArrayList<Integer> ALUmur = new ArrayList<Integer>();
    ArrayList<String> ALClub = new ArrayList<String>();
    ArrayList<String> ALUnggulan = new ArrayList<String>();
    ArrayList<String> ALUnggulanYa = new ArrayList<String>();
    DatabaseReference DRef;
    ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETNama = findViewById(R.id.idETNama);
        ETUmur = findViewById(R.id.idETUmur);
        ETClub = findViewById(R.id.idETClub);
        CBUnggulan = findViewById(R.id.idCBUnggulan);
        BSimpan = findViewById(R.id.idBSimpan);
        BHapus = findViewById(R.id.idBHapus);
        androidx.appcompat.widget.Toolbar toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.tolbar);
        toolbar.setTitle("Tambah Peserta");
        setSupportActionBar(toolbar);
        peserta = new Peserta();
        history = new History();

//        Intent intent=getIntent();
//        UID=intent.getStringExtra("idkeys");
        Log.d("TAG", "onCreate: "+UID+ALKeyAd);
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        UID=firebaseUser.getUid();
//        UID="d9X4OZ5j4YW489bHEonzXJsDyoj2";
        Intent intent=getIntent();
        UID=intent.getStringExtra("UID");
//        ALKeyAd.add(UID);

        Log.d("TAG", "onCreate2: "+UID+ALKeyAd);
//        DRef= FirebaseDatabase.getInstance().getReference().child("Admin");
        DRef= FirebaseDatabase.getInstance().getReference();
        Log.d("TAG", "onCreate3: "+UID+ALKeyAd);

        BHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ETNama.setText("");
                ETUmur.setText("");
                ETClub.setText("");
                CBUnggulan.setChecked(false);
            }
        });

        BSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(ETNama.getText().toString())){
                    ETNama.setError("Masukkan Nama");
                }
                if(TextUtils.isEmpty(ETUmur.getText().toString())){
                    ETUmur.setError("Masukkan Umur");
                }
                if(TextUtils.isEmpty(ETClub.getText().toString())){
                    ETClub.setError("Masukkan Club");
                }
                else {
//                    INo++;
                    SKey = DRef.push().getKey();
                    SNama = ETNama.getText().toString();
                    IUmur = Integer.parseInt(ETUmur.getText().toString());
                    SClub = ETClub.getText().toString();
                    CekUnggulan();
//                    String ids=UID;


//                DRef.child("Peserta").addValueEventListener(new ValueEventListener() {
//                    Log.d("TAG", "dbref: "+UID+ALNama);
//                    Log.d("TAG", "dbref: "+ALKey);
//                    Log.d("TAG", "dbref: "+ALUnggulan);
                    DRef.child("Admin").child(UID).child("Peserta").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ALNo.clear();
                            ALKey.clear();
                            ALNama.clear();
                            ALUmur.clear();
                            ALUnggulan.clear();
                            ALUnggulanYa.clear();
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Integer no = dataSnapshot.child("no").getValue(Integer.class);
                                String Key = dataSnapshot.getKey();
                                String Nama = dataSnapshot.child("nama").getValue(String.class);
                                Integer Umur = dataSnapshot.child("umur").getValue(Integer.class);
                                String Club = dataSnapshot.child("club").getValue(String.class);
                                String Unggulan = dataSnapshot.child("unggulan").getValue(String.class);
//                                Log.d("TAG", "onDataChange: " + Nama + " NMA " + SNama);
//                                if (SNama.equals(Nama)) {
                                    ALNo.add(no);
                                    ALKey.add(Key);
                                    ALNama.add(Nama);
                                    ALUmur.add(Umur);
                                    ALClub.add(Club);
                                    ALUnggulan.add(Unggulan);
                                    if(Unggulan.equals("Unggulan")){
                                        ALUnggulanYa.add(Unggulan);
                                    }
//                                }
                            }
                            if (ALNama.contains(SNama)) {
                                Toast.makeText(MainActivity.this, "Nama Sudah Ada", Toast.LENGTH_SHORT).show();
                            } else {
//                                if(ALNo.size()==0){
//                                    INo = 0;
//                                }
//                                else{
//                                    INo = Collections.max(ALNo);
//                                }
//                                INo++;
//                                peserta.setNo(INo);
//                                peserta.setNama(SNama);
//                                peserta.setUmur(IUmur);
//                                peserta.setClub(SClub);
                                Log.d("TAG", "unggulans: "+SCBUnggulan+ALUnggulanYa);
//                                if(ALUnggulan.size()<=2) {

                                if(SCBUnggulan.equals("Unggulan")) {
                                    if(ALUnggulanYa.size()<2){
                                        Log.d("TAG", "unggulan1: "+SCBUnggulan+ALUnggulanYa);
                                        peserta.setUnggulan(SCBUnggulan);
                                        if(ALNo.size()==0){
                                            INo = 0;
                                        }
                                        else{
                                            INo = Collections.max(ALNo);
                                        }
                                        INo++;
                                        peserta.setNo(INo);
                                        peserta.setNama(SNama);
                                        peserta.setUmur(IUmur);
                                        peserta.setClub(SClub);
                                        DRef.child("Admin").child(UID).child("Peserta").child(SKey).setValue(peserta);
                                        Toast.makeText(MainActivity.this,  SNama+" Berhasil Disimpan", Toast.LENGTH_SHORT).show();
    //                                    CBUnggulan.setChecked(false);
                                    }
                                    else if(ALUnggulanYa.size()>=2){
//                                else if(ALUnggulan.size()>2){
                                        Toast.makeText(MainActivity.this, "Unggulan Max 2", Toast.LENGTH_SHORT).show();
                                        Log.d("TAG", "unggulan2: "+SCBUnggulan+ALUnggulanYa);
                                        CBUnggulan.setChecked(false);
                                        CekUnggulan();
                                        peserta.setUnggulan(SCBUnggulan);
                                        if(ALNo.size()==0){
                                            INo = 0;
                                        }
                                        else{
                                            INo = Collections.max(ALNo);
                                        }
                                        INo++;
                                        peserta.setNo(INo);
                                        peserta.setNama(SNama);
                                        peserta.setUmur(IUmur);
                                        peserta.setClub(SClub);
                                        DRef.child("Admin").child(UID).child("Peserta").child(SKey).setValue(peserta);
                                        Toast.makeText(MainActivity.this, SNama+" Berhasil Disimpan;"+"Unggulan:"+SCBUnggulan, Toast.LENGTH_SHORT).show();

                                    }
                                }

                                else if(SCBUnggulan.equals("Tidak")){
                                    peserta.setUnggulan(SCBUnggulan);
                                    if(ALNo.size()==0){
                                        INo = 0;
                                    }
                                    else{
                                        INo = Collections.max(ALNo);
                                    }
                                    INo++;
                                    peserta.setNo(INo);
                                    peserta.setNama(SNama);
                                    peserta.setUmur(IUmur);
                                    peserta.setClub(SClub);
                                    DRef.child("Admin").child(UID).child("Peserta").child(SKey).setValue(peserta);
                                    Toast.makeText(MainActivity.this, SNama+" Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                                }

//                                DRef.child("Admin").child(UID).child("Peserta").child(SKey).setValue(peserta);
////                                DRef.child("Peserta").child(SKey).child("History").setValue(history);
////                                Log.d("TAG", "onDataChange: "+DRef.child("Peserta").child(SKey));
//                                Toast.makeText(MainActivity.this, "Berhasil Disimpan" + ALNama, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                }


        });

    }

    public void CekUnggulan(){
        if(CBUnggulan.isChecked()){
            SCBUnggulan = "Unggulan";
        }
        else{
            SCBUnggulan = "Tidak";
        }
    }

    public void CekText(){

    }

    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(MainActivity.this, DashboardAdmin.class);
        startActivity(intent);
    }
}