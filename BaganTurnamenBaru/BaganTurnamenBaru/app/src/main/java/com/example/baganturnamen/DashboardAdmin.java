package com.example.baganturnamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.baganturnamen.Edit.edit_Admin;
import com.example.baganturnamen.Full_bracket_turnament.Full_bracket_turnament;
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
    ImageView IVInputPeserta, IVDaftarPeserta, IVBaganTurnamen, IVLogOff, aboutus;
    DatabaseReference databaseReference,databaseReference2;
    ArrayList<String> ALNama = new ArrayList<String>();
    ArrayList<String> ALKeyAd = new ArrayList<String>();
    String nama;
    String Snama,Skey,Spass,Semail;
    FirebaseUser firebaseUser;
    String UID;
    String ssd;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_admin);
        IVInputPeserta = findViewById(R.id.idIVInputPeserta);
        IVDaftarPeserta = findViewById(R.id.idIVDaftarPeserta);
        IVBaganTurnamen = findViewById(R.id.idIVBaganTurnamen);
        aboutus=findViewById(R.id.aboutus);
        IVLogOff = findViewById(R.id.idIVLoggOff);
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        UID=firebaseUser.getUid();
        Intent intent=getIntent();
        UID=intent.getStringExtra("UID");
        Log.d("TAG", "onCreateDB: "+UID);
        if(UID==null){
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            UID=firebaseUser.getUid();
            Log.d("TAG", "onCreateDB2: "+UID);
        }
//
//        databaseReference= FirebaseDatabase.getInstance().getReference().child("Admin").child(UID).child("Peserta");
////        androidx.appcompat.widget.Toolbar toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.tolbar);
////        toolbar.setTitle("Dashboard");
////        setSupportActionBar(toolbar);
//
//
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                ALPeserta.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    nama = dataSnapshot.child("nama").getValue(String.class);
//
//                    ALNama.add(nama);
////                Log.d("TAG", "onDataChange: "+nama);
//
//                }
//                Log.d("TAG", "onDataChange: "+ALNama);
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        IVInputPeserta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardAdmin.this, MainActivity.class);
                intent.putExtra("UID", UID);
                startActivity(intent);
//                String uid;
//                uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
//                databaseReference2=FirebaseDatabase.getInstance().getReference().child("Admin").child(uid);
//                databaseReference2.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Log.d("TAG", "onDataChange: ");
//                        Semail=snapshot.child("email").getValue(String.class);
//                        Spass=snapshot.child("password").getValue(String.class);
//                        Snama=snapshot.child("nama").getValue(String.class);
//                        intent.putExtra("idkeys",uid);
//                        startActivity(intent);
//                        finish();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
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
                intent.putExtra("UID",UID);
                startActivity(intent);
//                String uid;
//                uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
//                databaseReference2=FirebaseDatabase.getInstance().getReference().child("Admin").child(UID);
//                databaseReference2.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Semail=snapshot.child("email").getValue(String.class);
//                        Spass=snapshot.child("password").getValue(String.class);
//                        Snama=snapshot.child("nama").getValue(String.class);
//                        String keys=snapshot.child("uid").getValue(String.class);
//                        intent.putExtra("idkeys",keys);
//                        startActivity(intent);
//                        finish();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
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

    public void EditAdmin(View view) {
        Intent intent=new Intent(DashboardAdmin.this, edit_Admin.class);
        intent.putExtra("UID",UID);
//        intent.putExtra("idemails",Semail);
//        intent.putExtra("idpasss",Spass);
//        intent.putExtra("idnamas",Snama);
        startActivity(intent);
//        String uid;
//        uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
//        databaseReference2=FirebaseDatabase.getInstance().getReference().child("Admin").child(UID);
//        databaseReference2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Semail=snapshot.child("email").getValue(String.class);
//                Spass=snapshot.child("password").getValue(String.class);
//                Snama=snapshot.child("nama").getValue(String.class);
////                intent.putExtra("idkeys",uid);
//                intent.putExtra("UID",UID);
//                intent.putExtra("idemails",Semail);
//                intent.putExtra("idpasss",Spass);
//                intent.putExtra("idnamas",Snama);
////                startActivity(intent);
////                finish();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        intent.putExtra("idkeys",Skey);
//        intent.putExtra("idemails",Semail);
//        intent.putExtra("idpasss",Spass);
//        intent.putExtra("idnamas",Snama);
//        Log.d("TAG", "onClicssasak: "+Semail);
//        Log.d("TAG", "onClicasaask: "+Skey);

//        startActivity(intent);
    }
}