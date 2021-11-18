package com.example.baganturnamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.baganturnamen.Adapter.AdapterDaftarPeserta;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    DatabaseReference DBRef;
    ArrayList<Peserta> ALPeserta;
    ArrayList<String> ALKey = new ArrayList<String>();
    Context context;
    FirebaseUser firebaseUser;
    String UID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_peserta);
//        kembali = findViewById(R.id.kembali);

        androidx.appcompat.widget.Toolbar toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.tolbar);
        toolbar.setTitle("Daftar Peserta");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RVDaftarPeserta = findViewById(R.id.idRVDaftarPeserta);

        ALPeserta = new ArrayList<Peserta>();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null) {
            UID = firebaseUser.getUid();
        }
        DBRef = FirebaseDatabase.getInstance().getReference("Admin/"+UID+"/Peserta");

        RVDaftarPeserta.setHasFixedSize(true);

        DBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ALPeserta.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Peserta peserta = dataSnapshot.getValue(Peserta.class);
                    String key = dataSnapshot.getKey();

                    ALPeserta.add(peserta);
                    ALKey.add(key);
                }

                adapterDaftarPeserta = new AdapterDaftarPeserta(context, ALPeserta, ALKey);
                RVDaftarPeserta.setAdapter(adapterDaftarPeserta);

                adapterDaftarPeserta.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}