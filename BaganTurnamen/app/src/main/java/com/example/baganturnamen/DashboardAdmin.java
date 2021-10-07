package com.example.baganturnamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.baganturnamen.Bagan.Bagans;
import com.example.baganturnamen.BracketT.Bracket;
import com.example.baganturnamen.BracketT.Bracket2;
import com.example.baganturnamen.LogSign.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardAdmin extends AppCompatActivity {
    ImageView IVInputPeserta, IVDaftarPeserta, IVBaganTurnamen, IVLogOff;
    DatabaseReference databaseReference;
    ArrayList<String> ALNama = new ArrayList<String>();
    String nama;
    FirebaseUser firebaseUser;
    String UID;
    private static int SPLASH_SCREEN = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        IVInputPeserta = findViewById(R.id.idIVInputPeserta);
        IVDaftarPeserta = findViewById(R.id.idIVDaftarPeserta);
        IVBaganTurnamen = findViewById(R.id.idIVBaganTurnamen);
        IVLogOff = findViewById(R.id.idIVLoggOff);
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        UID=firebaseUser.getUid();

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Admin").child(UID).child("Peserta");
        androidx.appcompat.widget.Toolbar toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.tolbar);
        toolbar.setTitle("Dashboard");
        setSupportActionBar(toolbar);

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

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(DashboardAdmin.this, Bracket2.class);
                        intent.putStringArrayListExtra("namas",ALNama);
                        startActivity(intent);

                        Log.d("TAG", "onClicsask: "+ALNama);
                        finish();
                    }
                },SPLASH_SCREEN);
//                Intent intent = new Intent(DashboardAdmin.this, Bracket2.class);
//                intent.putStringArrayListExtra("namas",ALNama);
//                startActivity(intent);
//
//                Log.d("TAG", "onClicsask: "+ALNama);
//                finish();
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