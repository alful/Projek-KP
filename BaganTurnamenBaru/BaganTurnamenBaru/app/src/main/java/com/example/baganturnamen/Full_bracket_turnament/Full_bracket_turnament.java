package com.example.baganturnamen.Full_bracket_turnament;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.baganturnamen.History;
import com.example.baganturnamen.Pertandingan;
import com.example.baganturnamen.Peserta;
import com.example.baganturnamen.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;

public class Full_bracket_turnament extends AppCompatActivity {
    private RecyclerView RVBagan, RVBaganFinal2, RVBaganFinal3;
    AdapterBagan adapterBagan;
    AdapterBaganFinal2 adapterBaganFinal2;
    AdapterBaganFinal3 adapterBaganFinal3;
    private DatabaseReference DBRef;
    ArrayList<Peserta> ALPeserta;
    ArrayList<Peserta> ALPeserta2;
    ArrayList<String> ALNama;
    ArrayList<String> ALNama2;
    ArrayList<String> ALSKey1;
    ArrayList<String> ALSKey2;

    ArrayList<Integer> ALSkorBabak1Peserta1;
    ArrayList<Integer> ALSkorBabak1Peserta2;
    ArrayList<Integer> ALSkorBabak2Peserta1;
    ArrayList<Integer> ALSkorBabak2Peserta2;
    ArrayList<Integer> ALSkorBabak3Peserta1;
    ArrayList<Integer> ALSkorBabak3Peserta2;
    ArrayList<Integer> ALSkorPemenangB1;
    Context context;
    Peserta peserta, peserta2;
    Pertandingan pertandingan;
//    History history, history2;
    String nama, nama2, SKey1, SKey2, SKeySemua;
    Integer SkorBabak1Peserta1, SkorBabak1Peserta2,
            SkorBabak2Peserta1, SkorBabak2Peserta2,
            SkorBabak3Peserta1, SkorBabak3Peserta2,
            SkorPemenangB1;

    ArrayList<Integer> ALSemuaSkorB1;
    Integer semuaskorB1;
    ArrayList<Integer> ALSkorB1Peserta1;
    ArrayList<Integer> ALSkorB1Peserta2;


//    String semuapemenangB1;
    String semuanamapemenangB1;
    ArrayList<String> ALSemuaNamaPemenangB1;
    ArrayList<String> ALNamaPemenangB1Peserta1;
    ArrayList<String> ALNamaPemenangB1Peserta2;

//    ArrayList<Integer> ALSemuaSkorPemenangB2;
    ArrayList<Integer> ALSemuaSkorB2;
//    Integer semuaskorpemenangB2, semuaskorpemenangB3;
    Integer semuaskorB2;
    ArrayList<Integer> ALSkorPemenangB2Peserta1;
    ArrayList<Integer> ALSkorPemenangB2Peserta2;

    ArrayList<String> ALKeySemuaPemenangB2;

    ArrayList<String> ALKeySemua;
    ArrayList<String> ALNamaPemenangB2Peserta1;
    ArrayList<String> ALNamaPemenangB2Peserta2;
    ArrayList<String> ALKeyPemenangB2Peserta1;
    ArrayList<String> ALKeyPemenangB2Peserta2;

    ArrayList<String> ALSemuaNamaPemenangB2;
    String semuanamapemenangB2;

//    ArrayList<Integer> ALSemuaSkorPemenangB3;
    Integer semuaskorB3;
    ArrayList<Integer> ALSemuaSkorB3;
    ArrayList<Integer> ALSkorPemenangB3Peserta1;
    ArrayList<Integer> ALSkorPemenangB3Peserta2;

    ArrayList<Integer> ALSemuaSkorPemenangB4;
    ArrayList<Integer> ALSkorPemenangB4Peserta1;
    ArrayList<Integer> ALSkorPemenangB4Peserta2;
    Integer semuaskorpemenangB4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_bracket_turnament);

        DBRef = FirebaseDatabase.getInstance().getReference("Peserta");
        RVBagan = findViewById(R.id.idRVBagan);
        RVBaganFinal2 = findViewById(R.id.idRVBaganFinal2);
        RVBaganFinal3 = findViewById(R.id.idRVBaganFinal3);
        ALPeserta = new ArrayList<Peserta>();
        ALPeserta2 = new ArrayList<Peserta>();
        ALNama = new ArrayList<String>();
        ALNama2 = new ArrayList<String>();
        ALSKey1 = new ArrayList<String>();
        ALSKey2 = new ArrayList<String>();
        ALKeySemua = new ArrayList<String>();

        ALSkorBabak1Peserta1 = new ArrayList<Integer>();
        ALSkorBabak1Peserta2 = new ArrayList<Integer>();
        ALSkorBabak2Peserta1 = new ArrayList<Integer>();
        ALSkorBabak2Peserta2 = new ArrayList<Integer>();
        ALSkorBabak3Peserta1 = new ArrayList<Integer>();
        ALSkorBabak3Peserta2 = new ArrayList<Integer>();

        ALSemuaSkorB1 = new ArrayList<Integer>();
        ALSkorB1Peserta1 = new ArrayList<Integer>();
        ALSkorB1Peserta2 = new ArrayList<Integer>();
        ALSkorPemenangB1 = new ArrayList<Integer>();
        ALSemuaNamaPemenangB1 = new ArrayList<String>();
        ALNamaPemenangB1Peserta1 = new ArrayList<String>();
        ALNamaPemenangB1Peserta2 = new ArrayList<String>();

//        ALSemuaSkorPemenangB2 = new ArrayList<Integer>();
        ALSemuaSkorB2 = new ArrayList<Integer>();
        ALSemuaNamaPemenangB2 = new ArrayList<String>();
        ALNamaPemenangB2Peserta1 = new ArrayList<String>();
        ALNamaPemenangB2Peserta2 = new ArrayList<String>();
        ALSkorPemenangB2Peserta1 = new ArrayList<Integer>();
        ALSkorPemenangB2Peserta2 = new ArrayList<Integer>();
        ALKeySemuaPemenangB2 = new ArrayList<String>();
        ALKeyPemenangB2Peserta1 = new ArrayList<String>();
        ALKeyPemenangB2Peserta2 = new ArrayList<String>();

//        ALSemuaSkorPemenangB3 = new ArrayList<Integer>();
        ALSemuaSkorB3 = new ArrayList<Integer>();
        ALSkorPemenangB3Peserta1 = new ArrayList<Integer>();
        ALSkorPemenangB3Peserta2 = new ArrayList<Integer>();

        ALSemuaSkorPemenangB4 = new ArrayList<Integer>();
        ALSkorPemenangB4Peserta1 = new ArrayList<Integer>();
        ALSkorPemenangB4Peserta2 = new ArrayList<Integer>();
//        RVBagan.setHasFixedSize(true);
//        RVBagan.setLayoutManager(new LinearLayoutManager(this));

        DBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i=0;
                ALPeserta.clear();
                ALPeserta2.clear();
                ALNama.clear();
                ALNama2.clear();
                ALSKey1.clear();
                ALSKey2.clear();
                ALKeySemua.clear();
                ALSkorBabak1Peserta1.clear();
                ALSkorBabak1Peserta2.clear();
                ALSkorBabak2Peserta1.clear();
                ALSkorBabak2Peserta2.clear();
                ALSkorBabak3Peserta1.clear();
                ALSkorBabak3Peserta2.clear();
                ALSemuaNamaPemenangB1.clear();
                ALSemuaNamaPemenangB2.clear();
                ALNamaPemenangB1Peserta1.clear();
                ALNamaPemenangB1Peserta2.clear();
                ALNamaPemenangB2Peserta1.clear();
                ALNamaPemenangB2Peserta2.clear();
                ALSemuaSkorB1.clear();
                ALSkorB1Peserta1.clear();
                ALSkorB1Peserta2.clear();
//                ALSemuaSkorPemenangB2.clear();
                ALSemuaSkorB2.clear();
                ALSemuaSkorB3.clear();
//                ALSemuaSkorPemenangB3.clear();
                ALSkorPemenangB3Peserta1.clear();
                ALSkorPemenangB3Peserta2.clear();
                ALSemuaSkorPemenangB4.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    semuapemenangB1 = dataSnapshot.child("History").child("pemenangB1").getValue(String.class);
                    semuanamapemenangB1 = dataSnapshot.child("History").child("pemenangB1").getValue(String.class);
                    semuanamapemenangB2 = dataSnapshot.child("History").child("pemenangB2").getValue(String.class);
                    semuaskorB1 = dataSnapshot.child("History").child("babak1").getValue(Integer.class);
//                    semuaskorpemenangB2 = dataSnapshot.child("History").child("babak2").getValue(Integer.class);
                    semuaskorB2 = dataSnapshot.child("History").child("babak2").getValue(Integer.class);
                    semuaskorB3 = dataSnapshot.child("History").child("babak3").getValue(Integer.class);
                    SKeySemua = dataSnapshot.getKey();
//                    semuaskorpemenangB3 = dataSnapshot.child("History").child("babak3").getValue(Integer.class);
                    semuaskorpemenangB4 = dataSnapshot.child("History").child("babak4").getValue(Integer.class);

                    if(semuaskorB1!=null){
                        ALSemuaSkorB1.add(semuaskorB1);
                    }
                    if(semuaskorB2!=null){
                        ALSemuaSkorB2.add(semuaskorB2);
                        ALKeySemuaPemenangB2.add(SKeySemua);
                        if(semuanamapemenangB1!=null){
                            ALSemuaNamaPemenangB1.add(semuanamapemenangB1);
                        }
//                        if(semuaskorB3==null){
//                            ALSemuaSkorB3.add(0);
//                        }
                        if(semuaskorB3!=null){
                            ALSemuaSkorB3.add(semuaskorB3);
                            if(semuanamapemenangB2!=null) {
                                ALSemuaNamaPemenangB2.add(semuanamapemenangB2);
                            }
                        }
                    }




//                    if(semuapemenangB1!=null) {
//                        ALSemuaNamaPemenangB1.add(semuapemenangB1);
//                        ALKeySemuaPemenangB2.add(SKeySemua);
////                        if(semuaskorpemenangB3!=0){
//                        ALSemuaSkorPemenangB3.add(semuaskorpemenangB3);
//
//                        if(semuaskorpemenangB4!=null) {
//                            ALSemuaSkorPemenangB4.add(semuaskorpemenangB4);
//                        }
////                        }
//                    }
//                    if(semuaskorpemenangB2!=0){
//                        ALSemuaSkorPemenangB2.add(semuaskorpemenangB2);
//                    }
//                    if(semuaskorpemenangB3!=0){
//                        ALSemuaSkorPemenangB3.add(semuaskorpemenangB3);
//                    }





                    if(i%2==0){
                        peserta = dataSnapshot.getValue(Peserta.class);
                        nama = dataSnapshot.child("nama").getValue(String.class);
                        SkorBabak1Peserta1 = dataSnapshot.child("History").child("babak1").getValue(Integer.class);
                        SkorBabak2Peserta1 = dataSnapshot.child("History").child("babak2").getValue(Integer.class);
                        SkorBabak3Peserta1 = dataSnapshot.child("History").child("babak3").getValue(Integer.class);
                        SKey1 = dataSnapshot.getKey();

                        ALPeserta.add(peserta);
                        ALSkorBabak1Peserta1.add(SkorBabak1Peserta1);
                        ALSkorBabak2Peserta1.add(SkorBabak2Peserta1);
                        ALSkorBabak3Peserta1.add(SkorBabak3Peserta1);
                        ALNama.add(nama);
                        ALSKey1.add(SKey1);
                    }
                    if(i%2!=0){
                        peserta2 = dataSnapshot.getValue(Peserta.class);
                        nama2 = dataSnapshot.child("nama").getValue(String.class);
                        SkorBabak1Peserta2 = dataSnapshot.child("History").child("babak1").getValue(Integer.class);
                        SkorBabak2Peserta2 = dataSnapshot.child("History").child("babak2").getValue(Integer.class);
                        SkorBabak3Peserta1 = dataSnapshot.child("History").child("babak3").getValue(Integer.class);
                        SKey2 = dataSnapshot.getKey();

                        ALPeserta2.add(peserta2);
                        ALSkorBabak1Peserta2.add(SkorBabak1Peserta2);
                        ALSkorBabak2Peserta2.add(SkorBabak2Peserta2);
                        ALSkorBabak3Peserta2.add(SkorBabak3Peserta2);
                        ALNama2.add(nama2);
                        ALSKey2.add(SKey2);
                    }
                    i++;
                }

                for(int x=0; x<ALSemuaSkorB1.size(); x++) {
                    if (x % 2 != 0) {
                        ALSkorB1Peserta2.add(ALSemuaSkorB1.get(x));
                    }
                    else if(x%2==0){
                        ALSkorB1Peserta1.add(ALSemuaSkorB1.get(x));
                    }
                }
//                adapterBagan = new AdapterBagan(context, ALNama, ALNama2, ALSKey1, ALSKey2);
//                adapterBagan = new AdapterBagan(context, ALNama, ALNama2, ALSKey1, ALSKey2,
//                                                ALSkorBabak1Peserta1, ALSkorBabak1Peserta2,
//                                                ALSkorBabak2Peserta1, ALSkorBabak2Peserta2);
                adapterBagan = new AdapterBagan(context, ALNama, ALNama2, ALSKey1, ALSKey2,
                                                ALSkorBabak1Peserta1, ALSkorBabak1Peserta2,
                                                ALSkorBabak2Peserta1, ALSkorBabak2Peserta2,
                                                ALSemuaSkorB2);
                RVBagan.setAdapter(adapterBagan);
                adapterBagan.notifyDataSetChanged();
//                RVBagan.setHasFixedSize(true);
                //keyB2
                for(int x=0; x<ALKeySemuaPemenangB2.size(); x++) {
                    if (x % 2 != 0) {
                        ALKeyPemenangB2Peserta2.add(ALKeySemuaPemenangB2.get(x));
                    }
                    else if(x%2==0){
                        ALKeyPemenangB2Peserta1.add(ALKeySemuaPemenangB2.get(x));
                    }
                }
//                Log.d("TAG", "onDataChangeKeyS: "+ALKeySemuaPemenangB2);
//                Log.d("TAG", "onDataChangekey: "+ALKeyPemenangB2Peserta1);
//                Log.d("TAG", "onDataChangekey: "+ALKeyPemenangB2Peserta2);


                //namaB2

//                Log.d("TAG", "onDataChangeAL: "+ALNamaPemenangB1Peserta1);
//                Log.d("TAG", "onDataChangeAL: "+ALNamaPemenangB1Peserta2);
                for(int x=0; x<ALSemuaNamaPemenangB1.size(); x++) {
                    if (x % 2 != 0) {
                        ALNamaPemenangB1Peserta2.add(ALSemuaNamaPemenangB1.get(x));
                    }
                    else if(x%2==0){
                        ALNamaPemenangB1Peserta1.add(ALSemuaNamaPemenangB1.get(x));
                    }
                }



//                //namaB2
                for(int x=0; x<ALSemuaNamaPemenangB2.size(); x++) {
                    if (x % 2 != 0) {
                        ALNamaPemenangB2Peserta2.add(ALSemuaNamaPemenangB2.get(x));
                    }
                    else if(x%2==0){
                        ALNamaPemenangB2Peserta1.add(ALSemuaNamaPemenangB2.get(x));
                    }
                }
//                Log.d("TAG", "onDataChangeAL: "+ALSemuaNamaPemenangB2);
//                Log.d("TAG", "onDataChange1: "+ALNamaPemenangB2Peserta1);
//                Log.d("TAG", "onDataChange2: "+ALNamaPemenangB2Peserta2);

                //skorB2
//                for(int x=0; x<ALSemuaSkorPemenangB2.size(); x++) {
                for(int x=0; x<ALSemuaSkorB2.size(); x++) {
                    if (x % 2 != 0) {
//                        ALSkorPemenangB2Peserta2.add(ALSemuaSkorPemenangB2.get(x));
                        ALSkorPemenangB2Peserta2.add(ALSemuaSkorB2.get(x));
                    }
                    else if(x%2==0){
//                        ALSkorPemenangB2Peserta1.add(ALSemuaSkorPemenangB2.get(x));
                        ALSkorPemenangB2Peserta1.add(ALSemuaSkorB2.get(x));
                    }
                }
//                Log.d("TAG", "onDataChangeKeyS: "+ALSemuaSkorB2);
//                Log.d("TAG", "onDataChangekey: "+ALSkorPemenangB2Peserta1);
//                Log.d("TAG", "onDataChangekey: "+ALSkorPemenangB2Peserta2);


//                Log.d("TAG", "onDataChange: "+ALSemuaSkorPemenangB3);
                //skorB3
//                for(int x=0; x<ALSemuaSkorPemenangB3.size(); x++) {

                    for (int x = 0; x < ALSemuaSkorB3.size(); x++) {
                        if (x % 2 != 0) {
//                        ALSkorPemenangB3Peserta2.add(ALSemuaSkorPemenangB3.get(x));
                            ALSkorPemenangB3Peserta2.add(ALSemuaSkorB3.get(x));
                        } else if (x % 2 == 0) {
                            ALSkorPemenangB3Peserta1.add(ALSemuaSkorB3.get(x));
                        }
                    }
                Log.d("TAG", "onDataChangeAL: "+ALSemuaSkorB3);
                Log.d("TAG", "onDataChangeALs: "+ALSemuaSkorB2.size());
                Log.d("TAG", "onDataChange1: "+ALSkorPemenangB3Peserta1);
                Log.d("TAG", "onDataChange2: "+ALSkorPemenangB3Peserta2);

                //skorB4
                for(int x=0; x<ALSemuaSkorPemenangB4.size(); x++) {
                    if (x % 2 != 0) {
                        ALSkorPemenangB4Peserta2.add(ALSemuaSkorPemenangB4.get(x));
                    }
                    else if(x%2==0){
                        ALSkorPemenangB4Peserta1.add(ALSemuaSkorPemenangB4.get(x));
                    }
                }



                adapterBaganFinal2 = new AdapterBaganFinal2(context, ALNama,
                        ALNamaPemenangB1Peserta1, ALNamaPemenangB1Peserta2,
                        ALKeyPemenangB2Peserta1, ALKeyPemenangB2Peserta2,
                        ALSkorPemenangB2Peserta1, ALSkorPemenangB2Peserta2,
                        ALSkorPemenangB3Peserta1, ALSkorPemenangB3Peserta2,
                        ALSemuaSkorB3);
                RVBaganFinal2.setAdapter(adapterBaganFinal2);
//                RVBagan.setHasFixedSize(true);

                adapterBaganFinal2.notifyDataSetChanged();



                adapterBaganFinal3 = new AdapterBaganFinal3(context,
                        ALSkorPemenangB3Peserta1, ALSkorPemenangB3Peserta2);
                RVBaganFinal3.setAdapter(adapterBaganFinal3);
                adapterBaganFinal3.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}