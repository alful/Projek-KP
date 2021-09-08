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
    EditText ETNama, ETUmur;
    CheckBox CBUnggulan;
    Button BSimpan, BHapus;
    ImageView IVKembali;

    String SNama, SCBUnggulan, SKey;
    Integer IUmur;

    FirebaseDatabase FDB;
    DatabaseReference DRef;

    Peserta peserta;

    ArrayList<String> ALKey = new ArrayList<String>();
    ArrayList<String> ALNama = new ArrayList<String>();
    ArrayList<Integer> ALUmur = new ArrayList<Integer>();
    ArrayList<String> ALUnggulan = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ETNama = findViewById(R.id.idETNama);
        ETUmur = findViewById(R.id.idETUmur);
        CBUnggulan = findViewById(R.id.idCBUnggulan);
        BSimpan = findViewById(R.id.idBSimpan);
        BHapus = findViewById(R.id.idBHapus);
        IVKembali = findViewById(R.id.idIVKembali);

        peserta = new Peserta();

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
                else {
                    SKey = DRef.push().getKey();
                    SNama = ETNama.getText().toString();
                    IUmur = Integer.parseInt(ETUmur.getText().toString());
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
                                String Unggulan = dataSnapshot.child("unggulan").getValue(String.class);
                                Log.d("TAG", "onDataChange: " + Nama + " NMA " + SNama);
//                                if (SNama.equals(Nama)) {
                                    ALKey.add(Key);
                                    ALNama.add(Nama);
                                    ALUmur.add(Umur);
                                    ALUnggulan.add(Unggulan);
//                                }
                            }
                            if (ALNama.contains(SNama)) {
                                Toast.makeText(MainActivity.this, "Nama Sudah Ada", Toast.LENGTH_SHORT).show();
                            } else {
                                peserta.setNama(SNama);
                                peserta.setUmur(IUmur);
                                peserta.setUnggulan(SCBUnggulan);
                                DRef.child("Peserta").child(SKey).setValue(peserta);
                                Toast.makeText(MainActivity.this, "berhasil" + ALNama, Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    Toast.makeText(MainActivity.this, "Berhasil Simpan:" + SNama + ";" + IUmur + ";" + SCBUnggulan, Toast.LENGTH_SHORT).show();
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