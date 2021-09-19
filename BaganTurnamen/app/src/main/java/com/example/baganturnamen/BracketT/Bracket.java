package com.example.baganturnamen.BracketT;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.baganturnamen.Peserta;
import com.example.baganturnamen.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ventura.bracketslib.BracketsView;
import com.ventura.bracketslib.fragment.BracketsFragment;
import com.ventura.bracketslib.model.ColomnData;
import com.ventura.bracketslib.model.CompetitorData;
import com.ventura.bracketslib.model.MatchData;

import java.util.ArrayList;
import java.util.Arrays;

//import android.os.Bundle;
//import android.util.DisplayMetrics;
//
//import com.example.baganturnamen.BracketT.Fragment.BracketsFragment;
//import com.example.baganturnamen.BracketT.application.BracketsApplication;
//import com.example.baganturnamen.R;

public class Bracket extends AppCompatActivity {

    private BracketsFragment bracketsFragment;
    BracketsView bracketsView;
    DatabaseReference databaseReference;
    ArrayList<String> ALNama;
    ArrayList<String> ALNama2;
    ArrayList<Peserta> ALPeserta;
    Peserta peserta;
    CompetitorData mahes, kosong, isi, isi2, isi3, isi4, isi5, isi6, isi7, isi8;
    MatchData MDkosong, MDisi, MD1, MD2, MD3, MD4, MD5;
    ColomnData CLkosong, CL1, CL2, CLfinal;

//    @Override
//    protected void onStart(){
//        super.onStart();
//        readData(new FirebaseCallback() {
//            @Override
//            public void onCallback(ArrayList<String> ALNama) {
//                peserta.setALNama(ALNama);
//                Log.d("TAG", "onCallback: "+peserta.getALNama()+ALNama);
////                Log.d("TAG", "onCallback: "+ALNama);
//                mahes = new CompetitorData("mahes","0");
////                bagan();
////                peserta.getALNama();
//
//            }
//        });
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ALNama = new ArrayList<String>();
////                    ALNama.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Peserta peserta = dataSnapshot.getValue(Peserta.class);
//                    String nama = dataSnapshot.child("nama").getValue(String.class);
//
//                    ALNama.add(nama);
//                    ALPeserta.add(peserta);
//                }
//                getDS();
////                    treeView.setAdapter(adapter);
////                Toast.makeText(Bagans.this, "Toast"+ALNama, Toast.LENGTH_SHORT).show();
//                Log.d("TAG", "ALNama2: " + ALNama+ALPeserta);
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bracket);
        bracketsView = findViewById(R.id.bracket_view);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Peserta");

//        ALNama = new ArrayList<String>();
        peserta = new Peserta();
        ALPeserta = new ArrayList<Peserta>();

//        Log.d("TAG", "ALNama1: "+ALNama);



//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ALNama = new ArrayList<String>();
////                    ALNama.clear();
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    Peserta peserta = dataSnapshot.getValue(Peserta.class);
//                    String nama = dataSnapshot.child("nama").getValue(String.class);
//
//                    ALNama.add(nama);
//                    ALPeserta.add(peserta);
//                }
////                    treeView.setAdapter(adapter);
//
////                Toast.makeText(Bagans.this, "Toast"+ALNama, Toast.LENGTH_SHORT).show();
//
//                Log.d("TAG", "ALNama2: " + ALNama+ALPeserta);
//                alnama(ALNama);
//
//
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        Toast.makeText(this, ""+ALNama, Toast.LENGTH_SHORT).show();

//        readData(new FirebaseCallback() {
//            @Override
//            public void onCallback(ArrayList<String> ALNama) {
//                Log.d("TAG", "onCallback: "+ALNama);
////                bagan();
//            }
//        });

        readData(new FirebaseCallback() {
            @Override
            public void onCallback(ArrayList<String> ALNama) {
                peserta.setALNama(ALNama);
                Log.d("TAG", "onCallback: "+peserta.getALNama()+ALNama);
//                Log.d("TAG", "onCallback: "+ALNama);
//                mahes = new CompetitorData("mahes","0");
//                bagan();
//                peserta.getALNama();

            }
        });
        Log.d("TAG", "ALNama3: "+peserta.getALNama());

            bagan();

//        bagan();
//        CompetitorData mahes = new CompetitorData("mahes","0");
//        CompetitorData arthao = new CompetitorData("arthao", "3");
//
//        CompetitorData ace = new CompetitorData("ace","0");
//        CompetitorData luffy = new CompetitorData("luffy", "3");
//
//        CompetitorData sabo = new CompetitorData("sabo","0");
//        CompetitorData teach = new CompetitorData("teach", "3");
//
//        CompetitorData edward = new CompetitorData("edward","0");
//        CompetitorData roger = new CompetitorData("roger", "3");
//
//        CompetitorData kosong = new CompetitorData("","");
////        CompetitorData kosongf = new CompetitorData("", "");//agar animasi naik turun
//
//        CompetitorData ssk = new CompetitorData("", "");
//
////        ssk.setName("asass");
////        ssk.setScore("2");
//
//        MatchData MD0 = new MatchData(kosong, kosong);
//        MatchData MD1 = new MatchData(mahes, arthao);
//        MatchData MD2 = new MatchData(ace, luffy);
//        MatchData MD3 = new MatchData(sabo, teach);
//        MatchData MD4 = new MatchData(edward, roger);
//        MatchData MD5 = new MatchData(kosong, kosong);
//
//        ColomnData CDBabak1 = new ColomnData(Arrays.asList(MD1, MD2, MD3, MD4));
//        ColomnData CDBabak2 = new ColomnData(Arrays.asList(MD0, MD0));
//        ColomnData CDFinal = new ColomnData(Arrays.asList(MD5));
//
//        bracketsView.setBracketsData(Arrays.asList(CDBabak1, CDBabak2, CDFinal));

//        Toast.makeText(this, ""+bracketsFragment.getBracketsFragment(0), Toast.LENGTH_SHORT).show();
        




    }

    public void bagan(){
        ALNama = new ArrayList<String>();
        ALNama2 = new ArrayList<String>();
        peserta = new Peserta();

        Log.d("TAG", "ALbagan: "+ALNama+peserta.getALNama());
//        bracketsView = findViewById(R.id.bracket_view);

        ALNama2.add("mahes");
        ALNama2.add("arthao");
        ALNama2.add("ace");
        ALNama2.add("luffy");
        ALNama2.add("sabo");
        ALNama2.add("teach");
        ALNama2.add("edward");
        ALNama2.add("roger");
//        ALNama2.add("");
        Log.d("TAG", "bagan: "+ALNama2);
//        for(int i=0; i<=6; i++){
//            if(i==0) {
//                kosong = new CompetitorData("", "0");
//                MD5 = new MatchData(kosong, kosong);
//                MDkosong = new MatchData(kosong, kosong);
//                CL2 = new ColomnData(Arrays.asList(MD5, MDkosong));
//                Log.d("TAG", "i=0 "+kosong+MD5+MDkosong+CL2);
//            }
//            if(i==1){
//                isi = new CompetitorData(ALNama2.get(i-1), "0");
//                isi2 = new CompetitorData(ALNama2.get(i-2), "0");
//                MD1 = new MatchData(isi, isi2);
//                Log.d("TAG", "i=1 "+isi+isi2+MD1);
//            }
//            if(i==2){
//                isi3 = new CompetitorData(ALNama2.get(i), "0");
//                isi4 = new CompetitorData(ALNama2.get(i+1), "0");
//                MD2 = new MatchData(isi3, isi4);
//                Log.d("TAG", "i=2 "+isi3+isi4+MD2);
//            }
//            if(i==3){
//                isi5 = new CompetitorData(ALNama2.get(i+1), "0");
//                isi6 = new CompetitorData(ALNama2.get(i+2), "0");
//                MD3 = new MatchData(isi5, isi6);
//                Log.d("TAG", "i=3 "+isi5+isi6+MD3);
//            }
//            if(i==4) {
//                isi7 = new CompetitorData(ALNama2.get(i+2), "0");
//                isi8 = new CompetitorData(ALNama2.get(i+3), "0");
//                MD4 = new MatchData(isi7, isi8);
//                CL1 = new ColomnData(Arrays.asList(MD1, MD2, MD3, MD4));
//                Log.d("TAG", "i=4 "+isi7+isi8+MD4);
//            }
//            if(i==5) {
//                MD5 = new MatchData(kosong, kosong);
//                CLfinal = new ColomnData(Arrays.asList(MDkosong));
//                Log.d("TAG", "i=5 "+MD5);
//            }
//            if(i==6){
//                bracketsView.setBracketsData(Arrays.asList(CL1, CL2, CLfinal));
//                Log.d("TAG", "i=6 "+CL1+CL2+CLfinal);
//            }
//        }

        mahes = new CompetitorData(ALNama2.get(0),"0");
        CompetitorData arthao = new CompetitorData(ALNama2.get(1), "3");

        CompetitorData ace = new CompetitorData(ALNama2.get(2),"0");
        CompetitorData luffy = new CompetitorData(ALNama2.get(3), "3");

        CompetitorData sabo = new CompetitorData(ALNama2.get(4),"0");
        CompetitorData teach = new CompetitorData(ALNama2.get(5), "3");

        CompetitorData edward = new CompetitorData(ALNama2.get(6),"0");
        CompetitorData roger = new CompetitorData(ALNama2.get(7), "3");

        CompetitorData kosong = new CompetitorData("","");
//        CompetitorData kosongf = new CompetitorData("", "");//agar animasi naik turun

//        CompetitorData ssk = new CompetitorData("", "");
//
//        ssk.setName("asass");
//        ssk.setScore("2");

        MatchData MD0 = new MatchData(kosong, kosong);
        MatchData MD1 = new MatchData(mahes, arthao);
        MatchData MD2 = new MatchData(ace, luffy);
        MatchData MD3 = new MatchData(sabo, teach);
        MatchData MD4 = new MatchData(edward, roger);
        MatchData MD5 = new MatchData(kosong, kosong);

//        MDkosong.setCompetitorOne();

        ColomnData CDBabak1 = new ColomnData(Arrays.asList(MD1, MD2, MD3, MD4));
        ColomnData CDBabak2 = new ColomnData(Arrays.asList(MD0, MD0));
        ColomnData CDFinal = new ColomnData(Arrays.asList(MD5));

        bracketsView.setBracketsData(Arrays.asList(CDBabak1, CDBabak2, CDFinal));
        bracketsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "bracket klik"+ALNama);
            }
        });
        Log.d("TAG", "bracket "+MD1.getCompetitorOne());


    }
    private void readData(FirebaseCallback firebaseCallback){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ALNama = new ArrayList<String>();
//                    ALNama.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Peserta peserta = dataSnapshot.getValue(Peserta.class);
                    String nama = dataSnapshot.child("nama").getValue(String.class);

                    ALNama.add(nama);
                    ALPeserta.add(peserta);
                }
                Log.d("TAG", "onDataChange: "+ALNama);
                firebaseCallback.onCallback(ALNama);

//                getDS();
//                    treeView.setAdapter(adapter);
//                Toast.makeText(Bagans.this, "Toast"+ALNama, Toast.LENGTH_SHORT).show();
                Log.d("TAG", "ALNama2: " + ALNama+ALPeserta);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private interface FirebaseCallback{
        void onCallback(ArrayList<String> ALNama);
    }

}