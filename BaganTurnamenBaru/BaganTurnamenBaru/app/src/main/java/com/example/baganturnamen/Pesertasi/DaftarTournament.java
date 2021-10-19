package com.example.baganturnamen.Pesertasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.baganturnamen.LogSign.admin;
import com.example.baganturnamen.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class DaftarTournament extends AppCompatActivity {

    DatabaseReference databaseReference,databaseReference2;
    ArrayList<String> keyadmin;
    String Skeyadm;
    RecyclerView TampilDaftarTour;
    AdapterPesertaTour adapterPesertaTour;
    ArrayList<admin> admins = new ArrayList<admin>();
    ArrayList<String> ALKey = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_tournament);
        TampilDaftarTour = findViewById(R.id.daftartoru);


        keyadmin = new ArrayList<String>();

//        databaseReference= FirebaseDatabase.getInstance().getReference().child("Admin");
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
//                Log.d("TAG", "onDataChange: "+map);
//                for (Map.Entry<String, Object> entry : map.entrySet()) {
//                    Skeyadm=entry.getKey();
//                    keyadmin.add(Skeyadm);
//                }
//                Log.d("TAG", "onDataChange: "+keyadmin);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        databaseReference2=FirebaseDatabase.getInstance().getReference("Admin");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                admins.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    admin admink=dataSnapshot.getValue(admin.class);
                    String key = dataSnapshot.getKey();
                    ALKey.add(key);

                    admins.add(admink);
                }
                adapterPesertaTour.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        TampilDaftarTour.setLayoutManager(layoutManager);
//        konfirmasiHistoryAdapter = new KonfirmasiHistoryAdapter(this,  ALHistory, ALKey, ALGambarGame);
        adapterPesertaTour = new AdapterPesertaTour(this,  admins, ALKey);
        TampilDaftarTour.setAdapter(adapterPesertaTour);

    }
}