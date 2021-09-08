package com.example.baganturnamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.baganturnamen.Adapter.AdapterDaftarPeserta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;

public class DaftarPeserta extends AppCompatActivity {
    RecyclerView RVDaftarPeserta;
    AdapterDaftarPeserta adapterDaftarPeserta;
    DatabaseReference DBRef = FirebaseDatabase.getInstance().getReference();
    ArrayList<Peserta> ALPeserta = new ArrayList<Peserta>();
    ArrayList<String> ALNama = new ArrayList<String>();
    ArrayList<Integer> ALUmur = new ArrayList<Integer>();
    ArrayList<String> ALUnggulan = new ArrayList<String>();
    ImageView IVKembali;
    Context context;
    Peserta peserta = new Peserta();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_peserta);

        RVDaftarPeserta = findViewById(R.id.idRVDaftarPeserta);
        IVKembali = findViewById(R.id.idIVKembali);

        IVKembali.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
//               Intent intent = new Intent(DaftarPeserta.this, DashboardAdmin.class);
//               startActivity(intent);
               finish();
           }
        });

        DBRef.child("Peserta").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Peserta peserta = dataSnapshot.getValue(Peserta.class);
                    String nama = dataSnapshot.child("nama").getValue(String.class);
                    Integer umur = dataSnapshot.child("umur").getValue(Integer.class);
                    String unggulan = dataSnapshot.child("unggulan").getValue(String.class);

                    ALPeserta.add(peserta);
                    ALNama.add(nama);;
                    ALUmur.add(umur);
                    ALUnggulan.add(unggulan);
                }

                adapterDaftarPeserta = new AdapterDaftarPeserta(context, ALPeserta);
                RVDaftarPeserta.setAdapter(adapterDaftarPeserta);

                adapterDaftarPeserta.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}