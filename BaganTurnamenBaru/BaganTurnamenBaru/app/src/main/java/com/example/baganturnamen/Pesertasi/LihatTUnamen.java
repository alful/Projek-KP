package com.example.baganturnamen.Pesertasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.baganturnamen.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class LihatTUnamen extends AppCompatActivity {
    String Skey,Snama,Semail,Spass;
    DatabaseReference databaseReference;
    ArrayList<String> keyadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_tunamen);
        Intent intent=getIntent();
        Skey=intent.getStringExtra("adminkey");
        keyadmin = new ArrayList<String>();

        databaseReference=FirebaseDatabase.getInstance().getReference().child("Admin").child(Skey);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                Log.d("TAG", "onDataChange: "+map);
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    String Skeyadm=entry.getKey();
                    keyadmin.add(Skeyadm);
                }
                Log.d("TAG", "onDataChange: "+keyadmin);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}