package com.example.baganturnamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText ETNama, ETUmur, ETClub;
    CheckBox CBUnggulan;
    Button BSimpan, BHapus;
    ImageView IVKembali;

    String SNama, SCBUnggulan, SKey, SClub;
    Integer IUmur;

    DatabaseReference DRef;

    Peserta peserta;
    History history;

    ArrayList<String> ALKey = new ArrayList<String>();
    ArrayList<String> ALNama = new ArrayList<String>();
    ArrayList<Integer> ALUmur = new ArrayList<Integer>();
    ArrayList<String> ALClub = new ArrayList<String>();
    ArrayList<String> ALUnggulan = new ArrayList<String>();

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
        IVKembali = findViewById(R.id.idIVKembali);
        androidx.appcompat.widget.Toolbar toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.tolbar);
        toolbar.setTitle("Tambah Peserta");
        setSupportActionBar(toolbar);
        peserta = new Peserta();
        history = new History();

        DRef = FirebaseDatabase.getInstance().getReference();

        IVKembali.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, DashboardAdmin.class);
                startActivity(intent);
            }
        });

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
                    ETUmur.setError("Masukkan Club");
                }
                else {
                    SKey = DRef.push().getKey();
                    SNama = ETNama.getText().toString();
                    IUmur = Integer.parseInt(ETUmur.getText().toString());
                    SClub = ETClub.getText().toString();
                    CekUnggulan();

//                DRef.child("Peserta").addValueEventListener(new ValueEventListener() {
                    DRef.child("Peserta").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ALKey.clear();
                            ALNama.clear();
                            ALUmur.clear();
                            ALUnggulan.clear();
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String Key = dataSnapshot.getKey();
                                String Nama = dataSnapshot.child("nama").getValue(String.class);
                                Integer Umur = dataSnapshot.child("umur").getValue(Integer.class);
                                String Club = dataSnapshot.child("club").getValue(String.class);
                                String Unggulan = dataSnapshot.child("unggulan").getValue(String.class);
//                                Log.d("TAG", "onDataChange: " + Nama + " NMA " + SNama);
//                                if (SNama.equals(Nama)) {
                                    ALKey.add(Key);
                                    ALNama.add(Nama);
                                    ALUmur.add(Umur);
                                    ALClub.add(Club);
                                    ALUnggulan.add(Unggulan);
//                                }
                            }
                            if (ALNama.contains(SNama)) {
                                Toast.makeText(MainActivity.this, "Nama Sudah Ada", Toast.LENGTH_SHORT).show();
                            } else {
                                peserta.setNama(SNama);
                                peserta.setUmur(IUmur);
                                peserta.setClub(SClub);
                                peserta.setUnggulan(SCBUnggulan);
                                history.setBabak1(1);
                                history.setBabak2(0);
                                history.setBabak3(0);

                                DRef.child("Peserta").child(SKey).setValue(peserta);
                                DRef.child("Peserta").child(SKey).child("History").setValue(history);

                                Toast.makeText(MainActivity.this, "berhasil" + ALNama, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    Toast.makeText(MainActivity.this, "Berhasil Simpan:" + SNama + ";" +SClub+";"+IUmur+ ";"+SCBUnggulan, Toast.LENGTH_SHORT).show();
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
}