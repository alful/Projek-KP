package com.example.baganturnamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.baganturnamen.Full_bracket_turnament.Full_bracket_turnament;
import com.example.baganturnamen.LogSign.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardAdmin extends AppCompatActivity {
    ImageView IVInputPeserta, IVDaftarPeserta, IVBaganTurnamen, IVLogOff, aboutus;
    DatabaseReference databaseReference;
    ArrayList<String> ALNama = new ArrayList<String>();
    String nama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        IVInputPeserta = findViewById(R.id.idIVInputPeserta);
        IVDaftarPeserta = findViewById(R.id.idIVDaftarPeserta);
        IVBaganTurnamen = findViewById(R.id.idIVBaganTurnamen);
        aboutus=findViewById(R.id.aboutus);
        IVLogOff = findViewById(R.id.idIVLoggOff);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Peserta");
//        androidx.appcompat.widget.Toolbar toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.tolbar);
//        toolbar.setTitle("Dashboard");
//        setSupportActionBar(toolbar);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ALPeserta.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    nama = dataSnapshot.child("nama").getValue(String.class);

                    ALNama.add(nama);
//                Log.d("TAG", "onDataChange: "+nama);

                }
                Log.d("TAG", "onDataChange: "+ALNama);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        IVInputPeserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardAdmin.this, MainActivity.class);
                startActivity(intent);
            }
        });

        IVDaftarPeserta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(DashboardAdmin.this, DaftarPeserta.class);
                startActivity(intent);
            }
        });

        IVBaganTurnamen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(DashboardAdmin.this, Bracket.class);
//                Intent intent = new Intent(DashboardAdmin.this, BracketTurnamen.class);
                Intent intent = new Intent(DashboardAdmin.this, Full_bracket_turnament.class);
                intent.putStringArrayListExtra("namas",ALNama);
                startActivity(intent);

                Log.d("TAG", "onClicsask: "+ALNama);
                finish();
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardAdmin.this,About_us.class);
                startActivity(intent);
                finish();
            }
        });

        IVLogOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
    }
}