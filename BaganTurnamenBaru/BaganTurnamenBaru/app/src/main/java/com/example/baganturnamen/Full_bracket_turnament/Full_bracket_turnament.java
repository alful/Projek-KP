package com.example.baganturnamen.Full_bracket_turnament;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.baganturnamen.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Full_bracket_turnament extends AppCompatActivity {
    private RecyclerView RVBagan, RVBaganFinal, RVBaganFinal2, RVBaganFinal3, RVBaganFinal4;
    AdapterBagan adapterBagan;
    AdapterBaganFinal adapterBaganFinal;
    AdapterBaganFinal2 adapterBaganFinal2;
    AdapterBaganFinal3 adapterBaganFinal3;
    AdapterBaganFinal4 adapterBaganFinal4;
    private DatabaseReference DBRef;
    ArrayList<Integer> ALSemuaNo;
    ArrayList<Integer> ALNos;
    ArrayList<Integer> ALNos2;
    Integer semuano;
    ArrayList<String> ALSemuaKey;
    ArrayList<String> ALKeys;
    ArrayList<String> ALKeys2;
    String semuakey;
    ArrayList<String> ALKeyB1Peserta1;
    ArrayList<String> ALKeyB1Peserta2;
    ArrayList<String> ALSemuaNama;
    String semuanama;
    ArrayList<String> ALNamaB1Peserta1;
    ArrayList<String> ALNamaB1Peserta2;
    ArrayList<Integer> ALSemuaSkorB1;
    Integer semuaskorB1;
    ArrayList<Integer> ALSkorB1Peserta1;
    ArrayList<Integer> ALSkorB1Peserta2;
    ArrayList<String> ALSemuaUnggulan;
    ArrayList<String> ALUnggulan1;
    ArrayList<String> ALUnggulan2;
    String semuaunggulan;
    int nu;

    ArrayList<Integer> ALSkorBabak2Peserta1;
    ArrayList<Integer> ALSkorBabak2Peserta2;
    ArrayList<Integer> ALSkorBabak3Peserta1;
    ArrayList<Integer> ALSkorBabak3Peserta2;
    ArrayList<Integer> ALSkorPemenangB1;
    Context context;

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
    ArrayList<String> ALKeySemuaPemenangB3;
    ArrayList<String> ALSemuaNamaPemenangB3;
    ArrayList<String> ALNamaPemenangB3Peserta1;
    ArrayList<String> ALNamaPemenangB3Peserta2;
    String semuanamapemenangB3;
    ArrayList<String> ALKeyPemenangB3Peserta1;
    ArrayList<String> ALKeyPemenangB3Peserta2;

    ArrayList<Integer> ALSemuaSkorB4;
    Integer semuaskorB4;
    ArrayList<Integer> ALSemuaSkorPemenangB4;
    ArrayList<Integer> ALSkorPemenangB4Peserta1;
    ArrayList<Integer> ALSkorPemenangB4Peserta2;
//    Integer semuaskorpemenangB4;

    ArrayList<String> ALKeySemuaPemenangB4;
    ArrayList<String> ALKeyPemenangB4Peserta1;
    ArrayList<String> ALKeyPemenangB4Peserta2;
    ArrayList<Integer> ALSemuaSkorB5;
    Integer semuaskorB5;
    ArrayList<String> ALSemuaNamaPemenangB4;

    ArrayList<String> ALKeySemuaPemenangB5;
    ArrayList<String> ALKeyPemenangB5Peserta1;
    ArrayList<String> ALKeyPemenangB5Peserta2;
    ArrayList<Integer> ALSkorPemenangB5Peserta1;
    ArrayList<Integer> ALSkorPemenangB5Peserta2;
    ArrayList<String> ALNamaPemenangB4Peserta1;
    ArrayList<String> ALNamaPemenangB4Peserta2;
    String semuanamapemenangB4;
    ArrayList<Integer> ALSemuaSkorB6;
    Integer semuaskorB6;

    String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_bracket_turnament);

//        DBRef = FirebaseDatabase.getInstance().getReference("Peserta"); //////////////////////////////////////
        //DBRef = FirebaseDatabase.getInstance().getReference("Peserta2");//
        Intent intent=getIntent();
        UID=intent.getStringExtra("UID");


        DBRef = FirebaseDatabase.getInstance().getReference("Admin/"+UID+"/Peserta");
        RVBagan = findViewById(R.id.idRVBagan);
        RVBaganFinal = findViewById(R.id.idRVBaganFinal);
        RVBaganFinal2 = findViewById(R.id.idRVBaganFinal2);
        RVBaganFinal3 = findViewById(R.id.idRVBaganFinal3);
        RVBaganFinal4 = findViewById(R.id.idRVBaganFinal4);

        ALSemuaNo = new ArrayList<Integer>();
        ALNos = new ArrayList<Integer>();
        ALNos2 = new ArrayList<Integer>();
        ALSemuaKey = new ArrayList<String>();
        ALKeys = new ArrayList<String>();
        ALKeys2 = new ArrayList<String>();
        ALKeyB1Peserta1 = new ArrayList<String>();
        ALKeyB1Peserta2 = new ArrayList<String>();
        ALSemuaNama = new ArrayList<String>();
        ALNamaB1Peserta1 = new ArrayList<String>();
        ALNamaB1Peserta2 = new ArrayList<String>();
        ALSemuaSkorB1 = new ArrayList<Integer>();
        ALSkorB1Peserta1 = new ArrayList<Integer>();
        ALSkorB1Peserta2 = new ArrayList<Integer>();

        ALSemuaUnggulan = new ArrayList<String>();
        ALUnggulan1 = new ArrayList<String>();
        ALUnggulan2 = new ArrayList<String>();

        ALSkorBabak2Peserta1 = new ArrayList<Integer>();
        ALSkorBabak2Peserta2 = new ArrayList<Integer>();
        ALSkorBabak3Peserta1 = new ArrayList<Integer>();
        ALSkorBabak3Peserta2 = new ArrayList<Integer>();

        ALSkorPemenangB1 = new ArrayList<Integer>();
        ALSemuaNamaPemenangB1 = new ArrayList<String>();
        ALNamaPemenangB1Peserta1 = new ArrayList<String>();
        ALNamaPemenangB1Peserta2 = new ArrayList<String>();

        ALSemuaSkorB2 = new ArrayList<Integer>();
        ALSemuaNamaPemenangB2 = new ArrayList<String>();
        ALNamaPemenangB2Peserta1 = new ArrayList<String>();
        ALNamaPemenangB2Peserta2 = new ArrayList<String>();
        ALSkorPemenangB2Peserta1 = new ArrayList<Integer>();
        ALSkorPemenangB2Peserta2 = new ArrayList<Integer>();
        ALKeySemuaPemenangB2 = new ArrayList<String>();
        ALKeyPemenangB2Peserta1 = new ArrayList<String>();
        ALKeyPemenangB2Peserta2 = new ArrayList<String>();

        ALKeySemuaPemenangB3 = new ArrayList<String>();
        ALSemuaNamaPemenangB3 = new ArrayList<String>();
        ALNamaPemenangB3Peserta1 = new ArrayList<String>();
        ALNamaPemenangB3Peserta2 = new ArrayList<String>();
        ALKeyPemenangB3Peserta1 = new ArrayList<String>();
        ALKeyPemenangB3Peserta2 = new ArrayList<String>();
        ALSemuaSkorB3 = new ArrayList<Integer>();
        ALSkorPemenangB3Peserta1 = new ArrayList<Integer>();
        ALSkorPemenangB3Peserta2 = new ArrayList<Integer>();

        ALSemuaSkorB4 = new ArrayList<Integer>();
        ALSemuaSkorPemenangB4 = new ArrayList<Integer>();
        ALSkorPemenangB4Peserta1 = new ArrayList<Integer>();
        ALSkorPemenangB4Peserta2 = new ArrayList<Integer>();

        ALKeySemuaPemenangB4 = new ArrayList<String>();
        ALKeyPemenangB4Peserta1 = new ArrayList<String>();
        ALKeyPemenangB4Peserta2 = new ArrayList<String>();
        ALSemuaNamaPemenangB4 = new ArrayList<String>();
        ALSemuaSkorB5 = new ArrayList<Integer>();

        ALKeySemuaPemenangB5 = new ArrayList<String>();
        ALKeyPemenangB5Peserta1 = new ArrayList<String>();
        ALKeyPemenangB5Peserta2 = new ArrayList<String>();
        ALSkorPemenangB5Peserta1 = new ArrayList<Integer>();
        ALSkorPemenangB5Peserta2 = new ArrayList<Integer>();
        ALKeyPemenangB5Peserta1 = new ArrayList<String>();
        ALKeyPemenangB5Peserta2 = new ArrayList<String>();
        ALNamaPemenangB4Peserta1 = new ArrayList<String>();
        ALNamaPemenangB4Peserta2 = new ArrayList<String>();
        ALSemuaSkorB6 = new ArrayList<Integer>();

//        RVBagan.setHasFixedSize(true);
//        RVBagan.setLayoutManager(new LinearLayoutManager(this));

        DBRef.orderByChild("no").addValueEventListener(new ValueEventListener() {
            //        DBRef.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ALSemuaNo.clear();
                ALSemuaKey.clear();
                ALKeyB1Peserta1.clear();
                ALKeyB1Peserta2.clear();
                ALSemuaNama.clear();
                ALNamaB1Peserta1.clear();
                ALNamaB1Peserta2.clear();
                ALSemuaSkorB1.clear();
                ALSkorB1Peserta1.clear();
                ALSkorB1Peserta2.clear();
                ALSemuaUnggulan.clear();
                ALUnggulan1.clear();
                ALUnggulan2.clear();

                ALSkorBabak2Peserta1.clear();
                ALSkorBabak2Peserta2.clear();
                ALKeySemuaPemenangB2.clear();
                ALKeyPemenangB2Peserta1.clear();
                ALKeyPemenangB2Peserta2.clear();
                ALSkorBabak3Peserta1.clear();
                ALSkorBabak3Peserta2.clear();
                ALSemuaNamaPemenangB1.clear();
                ALSemuaNamaPemenangB2.clear();
                ALNamaPemenangB1Peserta1.clear();
                ALNamaPemenangB1Peserta2.clear();
                ALNamaPemenangB2Peserta1.clear();
                ALNamaPemenangB2Peserta2.clear();
                ALSkorPemenangB2Peserta1.clear();
                ALSkorPemenangB2Peserta2.clear();
                ALSemuaSkorB1.clear();
                ALSkorB1Peserta1.clear();
                ALSkorB1Peserta2.clear();
//                ALSemuaSkorPemenangB2.clear();
                ALSemuaSkorB2.clear();
                ALSemuaSkorB3.clear();
//                ALSemuaSkorPemenangB3.clear();
                ALKeySemuaPemenangB3.clear();
                ALKeyPemenangB3Peserta1.clear();
                ALKeyPemenangB3Peserta2.clear();
                ALSkorPemenangB3Peserta1.clear();
                ALSkorPemenangB3Peserta2.clear();
                ALSemuaNamaPemenangB3.clear();
                ALNamaPemenangB3Peserta1.clear();
                ALNamaPemenangB3Peserta2.clear();
                ALSemuaSkorB4.clear();
                ALSkorPemenangB4Peserta1.clear();
                ALSkorPemenangB4Peserta2.clear();
                ALSemuaSkorPemenangB4.clear();
                ALKeySemuaPemenangB4.clear();
                ALKeyPemenangB4Peserta1.clear();
                ALKeyPemenangB4Peserta2.clear();
                ALSemuaSkorB5.clear();
                ALSemuaNamaPemenangB4.clear();
                ALSkorPemenangB5Peserta1.clear();
                ALSkorPemenangB5Peserta2.clear();
                ALKeySemuaPemenangB5.clear();
                ALKeyPemenangB5Peserta1.clear();
                ALKeyPemenangB5Peserta2.clear();
                ALNamaPemenangB4Peserta1.clear();
                ALNamaPemenangB4Peserta2.clear();
                ALSemuaSkorB6.clear();

                ALKeys.clear();
                ALKeys2.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    semuano = dataSnapshot.child("no").getValue(Integer.class);
                    semuakey = dataSnapshot.getKey();
                    semuanama = dataSnapshot.child("nama").getValue(String.class);
                    semuaunggulan = dataSnapshot.child("unggulan").getValue(String.class);
                    semuanamapemenangB1 = dataSnapshot.child("History").child("pemenangB1").getValue(String.class);
                    semuanamapemenangB2 = dataSnapshot.child("History").child("pemenangB2").getValue(String.class);
                    semuanamapemenangB3 = dataSnapshot.child("History").child("pemenangB3").getValue(String.class);
                    semuanamapemenangB4 = dataSnapshot.child("History").child("pemenangB4").getValue(String.class);
                    semuaskorB1 = dataSnapshot.child("History").child("babak1").getValue(Integer.class);
                    semuaskorB2 = dataSnapshot.child("History").child("babak2").getValue(Integer.class);
                    semuaskorB3 = dataSnapshot.child("History").child("babak3").getValue(Integer.class);
                    semuaskorB4 = dataSnapshot.child("History").child("babak4").getValue(Integer.class);
                    semuaskorB5 = dataSnapshot.child("History").child("babak5").getValue(Integer.class);
                    semuaskorB6 = dataSnapshot.child("History").child("babak5").getValue(Integer.class);
//                    semuaskorpemenangB4 = dataSnapshot.child("History").child("babak4").getValue(Integer.class);

                    if(semuano!=null){
                        ALSemuaNo.add(semuano);
                    }
                    if(semuakey!=null){
                        ALSemuaKey.add(semuakey);
                    }
                    if(semuanama!=null){
                        ALSemuaNama.add(semuanama);
                    }
                    if(semuaunggulan!=null){
                        ALSemuaUnggulan.add(semuaunggulan);
                    }
                    if(semuaskorB1!=null){
                        ALSemuaSkorB1.add(semuaskorB1);
                    }

                    if(semuaskorB2!=null){
                        ALSemuaSkorB2.add(semuaskorB2);
//                        ALKeySemuaPemenangB2.add(SKeySemua);
                        ALKeySemuaPemenangB2.add(semuakey);
                        if(semuanamapemenangB1!=null){
                            ALSemuaNamaPemenangB1.add(semuanamapemenangB1);
                        }
//                        if(semuaskorB3==null){
//                            ALSemuaSkorB3.add(0);
//                        }
                        if(semuaskorB3!=null){
//                            ALKeySemuaPemenangB3.add(SKeySemua);
                            ALKeySemuaPemenangB3.add(semuakey);
                            ALSemuaSkorB3.add(semuaskorB3);
                            if(semuanamapemenangB2!=null) {
                                ALSemuaNamaPemenangB2.add(semuanamapemenangB2);
                            }
//                            if(semuaskorB3!=0){

//                            }
                            if(semuanamapemenangB3!=null) {
                                ALSemuaNamaPemenangB3.add(semuanamapemenangB3);
                            }
//                            ALKeySemuaPemenangB3.add(SKeySemua);
                            if(semuaskorB4!=null){
                                ALSemuaSkorB4.add(semuaskorB4);
                                ALKeySemuaPemenangB4.add(semuakey);
                                if(semuaskorB5!=null){
//                                    ALKeySemuaPemenangB4.add(SKeySemua);
                                    ALKeySemuaPemenangB5.add(semuakey);
                                    ALSemuaNamaPemenangB4.add(semuanamapemenangB4);
                                    ALSemuaSkorB5.add(semuaskorB5);
                                }
                            }
                        }
                    }

                }
                if(ALNos.size()==0){
//                    ALKeys.clear();
//                    ALKeys2.clear();
                    ALKeys.add(ALSemuaKey.get(0));
                    ALKeys2.add(ALSemuaKey.get(ALSemuaUnggulan.size()-1));
                    if(!ALSemuaUnggulan.get(0).equals("Unggulan")&&!ALSemuaUnggulan.get(ALSemuaUnggulan.size()-1).equals("Unggulan")) {
                        long seed = System.nanoTime();
                        Collections.shuffle(ALSemuaKey, new Random(seed));
                        Collections.shuffle(ALSemuaNama, new Random(seed));
                        Collections.shuffle(ALSemuaUnggulan, new Random(seed));
                        Collections.shuffle(ALSemuaNo, new Random(seed));
                        for(int x=0; x<ALSemuaUnggulan.size(); x++) {
                            if(ALSemuaUnggulan.get(x).equals("Unggulan")){
                                if(ALSemuaUnggulan.get(0).equals("Tidak")) {
                                    ALNos.clear();
                                    Collections.swap(ALSemuaKey, x, 0);
                                    Collections.swap(ALSemuaNama, x, 0);
                                    Collections.swap(ALSemuaUnggulan, x, 0);
                                    Collections.swap(ALSemuaNo, x, 0);
                                    ALNos.add(ALSemuaNo.get(0));
                                    HashMap hashMap = new HashMap();
//                                      hashMap.put("no", ALSemuaNo.get(x));
                                    hashMap.put("no", Collections.min(ALSemuaNo));
                                    DBRef.child(ALSemuaKey.get(0)).updateChildren(hashMap);
                                    hashMap.put("no", ALNos.get(0));
                                    //                                DBRef.child(ALSemuaKey.get(x)).updateChildren(hashMap);
                                    DBRef.child(ALKeys.get(0)).updateChildren(hashMap);
                                }
                                else if(ALSemuaUnggulan.get(0).equals("Unggulan")&&
                                        ALSemuaUnggulan.get(ALSemuaUnggulan.size()-1).equals("Tidak")&&
                                        ALNos2.size()==0){
                                    ALNos2.clear();
                                    Collections.swap(ALSemuaKey, x, ALSemuaUnggulan.size()-1);
                                    Collections.swap(ALSemuaNama, x, ALSemuaUnggulan.size()-1);
                                    Collections.swap(ALSemuaUnggulan, x, ALSemuaUnggulan.size()-1);
                                    Collections.swap(ALSemuaNo, x, ALSemuaUnggulan.size()-1);
                                    ALNos2.add(ALSemuaNo.get(ALSemuaUnggulan.size()-1));

                                    HashMap hashMap = new HashMap();
                                    //                                hashMap.put("no", ALSemuaNo.get(x));
                                    //                                hashMap.put("no", ALSemuaNo.get(ALSemuaUnggulan.size()-1));
                                    hashMap.put("no", Collections.max(ALSemuaNo));
                                    DBRef.child(ALSemuaKey.get(ALSemuaUnggulan.size()-1)).updateChildren(hashMap);
                                    hashMap.put("no", ALNos2.get(0));
                                    //                                DBRef.child(ALSemuaKey.get(x)).updateChildren(hashMap);
                                    DBRef.child(ALKeys2.get(0)).updateChildren(hashMap);
                                }
                                else if(ALSemuaUnggulan.get(0).equals("Unggulan")&&
                                        ALSemuaUnggulan.get(ALSemuaUnggulan.size()-1).equals("Unggulan")&&
                                        ALNos2.size()==0){
                                    ALNos2.clear();
                                    Collections.swap(ALSemuaKey, x, ALSemuaUnggulan.size()-1);
                                    Collections.swap(ALSemuaNama, x, ALSemuaUnggulan.size()-1);
                                    Collections.swap(ALSemuaUnggulan, x, ALSemuaUnggulan.size()-1);
                                    Collections.swap(ALSemuaNo, x, ALSemuaUnggulan.size()-1);
                                    ALNos2.add(ALSemuaNo.get(ALSemuaUnggulan.size()-1));
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("no", Collections.max(ALSemuaNo));
                                    DBRef.child(ALSemuaKey.get(ALSemuaUnggulan.size()-1)).updateChildren(hashMap);
                                    hashMap.put("no", ALNos2.get(0));
                                    DBRef.child(ALKeys2.get(0)).updateChildren(hashMap);
                                }
                            }
                        }
                    }
                }

                for(int x=0; x<ALSemuaKey.size(); x++) {
                    if (x % 2 != 0) {
                        ALKeyB1Peserta2.add(ALSemuaKey.get(x));
                    }
                    else if(x%2==0){
                        ALKeyB1Peserta1.add(ALSemuaKey.get(x));
                    }
                }
//                Log.d("TAG", "ALKey1: "+ALKeyB1Peserta1);
//                Log.d("TAG", "ALKey2: "+ALKeyB1Peserta2);


                int xu = 0;
                for(int x=0; x<ALSemuaNama.size(); x++) {
                    if (x % 2 != 0) {
                        ALNamaB1Peserta2.add(ALSemuaNama.get(x));
                    }
                    else if(x%2==0){
                        ALNamaB1Peserta1.add(ALSemuaNama.get(x));
                    }
                }
//                Log.d("TAG", "ALNama1: "+ALNamaB1Peserta1+ALNamaB1Peserta1.size());
//                Log.d("TAG", "ALNama2: "+ALNamaB1Peserta2+ALNamaB1Peserta2.size());

                for(int x=0; x<ALSemuaSkorB1.size(); x++) {
                    if (x % 2 != 0) {
                        ALSkorB1Peserta2.add(ALSemuaSkorB1.get(x));
                    }
                    else if(x%2==0){
                        ALSkorB1Peserta1.add(ALSemuaSkorB1.get(x));
                    }
                }

                adapterBagan = new AdapterBagan(context,
                        ALSemuaKey, ALKeyB1Peserta1, ALKeyB1Peserta2,
                        ALSemuaNama, ALNamaB1Peserta1, ALNamaB1Peserta2,
                        ALSemuaSkorB1, ALSkorB1Peserta1, ALSkorB1Peserta2,
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
//                Log.d("TAG", "onDataChangeAL: "+ALSemuaSkorB3);
//                Log.d("TAG", "onDataChangeALs: "+ALSemuaSkorB2.size());
//                Log.d("TAG", "onDataChange1: "+ALSkorPemenangB3Peserta1);
//                Log.d("TAG", "onDataChange2: "+ALSkorPemenangB3Peserta2);

                //skorB4
                for(int x=0; x<ALSemuaSkorPemenangB4.size(); x++) {
                    if (x % 2 != 0) {
                        ALSkorPemenangB4Peserta2.add(ALSemuaSkorPemenangB4.get(x));
                    }
                    else if(x%2==0){
                        ALSkorPemenangB4Peserta1.add(ALSemuaSkorPemenangB4.get(x));
                    }
                }

                for(int x=0; x<ALSemuaSkorB4.size(); x++) {
                    if (x % 2 != 0) {
//                        ALSkorPemenangB2Peserta2.add(ALSemuaSkorPemenangB2.get(x));
                        ALSkorPemenangB4Peserta2.add(ALSemuaSkorB4.get(x));
                    }
                    else if(x%2==0){
//                        ALSkorPemenangB2Peserta1.add(ALSemuaSkorPemenangB2.get(x));
                        ALSkorPemenangB4Peserta1.add(ALSemuaSkorB4.get(x));
                    }
                }



                adapterBaganFinal = new AdapterBaganFinal(context, ALSemuaNama,
                        ALNamaPemenangB1Peserta1, ALNamaPemenangB1Peserta2,
                        ALNamaPemenangB2Peserta1, ALNamaPemenangB2Peserta2,
                        ALKeyPemenangB2Peserta1, ALKeyPemenangB2Peserta2,
                        ALSkorPemenangB2Peserta1, ALSkorPemenangB2Peserta2,
                        ALSkorPemenangB3Peserta1, ALSkorPemenangB3Peserta2,
                        ALSemuaSkorB3, ALSemuaSkorB2,
                        ALSemuaSkorB4, ALSemuaSkorB5);
                RVBaganFinal.setAdapter(adapterBaganFinal);
//                RVBagan.setHasFixedSize(true);

                adapterBaganFinal.notifyDataSetChanged();



                for(int x=0; x<ALKeySemuaPemenangB3.size(); x++) {
                    if (x % 2 != 0) {
//                        ALSkorPemenangB2Peserta2.add(ALSemuaSkorPemenangB2.get(x));
                        ALKeyPemenangB3Peserta2.add(ALKeySemuaPemenangB3.get(x));
                    }
                    else if(x%2==0){
//                        ALSkorPemenangB2Peserta1.add(ALSemuaSkorPemenangB2.get(x));
                        ALKeyPemenangB3Peserta1.add(ALKeySemuaPemenangB3.get(x));
                    }
                }

                for(int x=0; x<ALSemuaNamaPemenangB3.size(); x++) {
                    if (x % 2 != 0) {
//                        ALSkorPemenangB2Peserta2.add(ALSemuaSkorPemenangB2.get(x));
                        ALNamaPemenangB3Peserta2.add(ALSemuaNamaPemenangB3.get(x));
                    }
                    else if(x%2==0){
//                        ALSkorPemenangB2Peserta1.add(ALSemuaSkorPemenangB2.get(x));
                        ALNamaPemenangB3Peserta1.add(ALSemuaNamaPemenangB3.get(x));
                    }
                }



                adapterBaganFinal2 = new AdapterBaganFinal2(context, ALSemuaNama,
                        ALKeySemuaPemenangB3,
                        ALKeyPemenangB2Peserta1, ALKeyPemenangB2Peserta2,
                        ALKeyPemenangB3Peserta1, ALKeyPemenangB3Peserta2,
                        ALNamaPemenangB2Peserta1, ALNamaPemenangB2Peserta2,
                        ALSkorPemenangB3Peserta1, ALSkorPemenangB3Peserta2,
                        ALSemuaSkorB4,
                        ALNamaPemenangB3Peserta1, ALNamaPemenangB3Peserta2,
                        ALSemuaSkorB5,
                        ALSkorPemenangB4Peserta1, ALSkorPemenangB4Peserta2);
                RVBaganFinal2.setAdapter(adapterBaganFinal2);
                adapterBaganFinal2.notifyDataSetChanged();


                for(int x=0; x<ALKeySemuaPemenangB4.size(); x++) {
                    if (x % 2 != 0) {
//                        ALSkorPemenangB2Peserta2.add(ALSemuaSkorPemenangB2.get(x));
                        ALKeyPemenangB4Peserta2.add(ALKeySemuaPemenangB4.get(x));
                    }
                    else if(x%2==0){
//                        ALSkorPemenangB2Peserta1.add(ALSemuaSkorPemenangB2.get(x));
                        ALKeyPemenangB4Peserta1.add(ALKeySemuaPemenangB4.get(x));
                    }
                }
//                Log.d("TAG", "onDataChange: "+ALKeyPemenangB4Peserta1);
//                Log.d("TAG", "onDataChange: "+ALKeyPemenangB4Peserta2);

                adapterBaganFinal3 = new AdapterBaganFinal3(context, ALSemuaNama, ALSemuaSkorB5,
                        ALKeySemuaPemenangB4,
                        ALSkorPemenangB4Peserta1, ALSkorPemenangB4Peserta2,
                        ALKeyPemenangB4Peserta1, ALKeyPemenangB4Peserta2,
                        ALNamaPemenangB3Peserta1, ALNamaPemenangB3Peserta2);
                RVBaganFinal3.setAdapter(adapterBaganFinal3);
                adapterBaganFinal3.notifyDataSetChanged();


                for(int x=0; x<ALKeySemuaPemenangB5.size(); x++) {
                    if (x % 2 != 0) {
                        ALKeyPemenangB5Peserta2.add(ALKeySemuaPemenangB5.get(x));
                    }
                    else if(x%2==0){
                        ALKeyPemenangB5Peserta1.add(ALKeySemuaPemenangB5.get(x));
                    }
                }

                for(int x=0; x<ALSemuaNamaPemenangB4.size(); x++) {
                    if (x % 2 != 0) {
                        ALNamaPemenangB4Peserta2.add(ALSemuaNamaPemenangB4.get(x));
                    }
                    else if(x%2==0){
                        ALNamaPemenangB4Peserta1.add(ALSemuaNamaPemenangB4.get(x));
                    }
                }

                for(int x=0; x<ALSemuaSkorB5.size(); x++) {
                    if (x % 2 != 0) {
                        ALSkorPemenangB5Peserta2.add(ALSemuaSkorB5.get(x));
                    }
                    else if(x%2==0){
                        ALSkorPemenangB5Peserta1.add(ALSemuaSkorB5.get(x));
                    }
                }

//                Log.d("TAG", "onDataChange: "+ALKeySemuaPemenangB5);
//                Log.d("TAG", "onDataChange: "+ALSemuaNamaPemenangB4);
//                Log.d("TAG", "onDataChange: "+ALSemuaSkorB5);

                adapterBaganFinal4 = new AdapterBaganFinal4(context, ALSemuaNama,
                        ALSkorPemenangB5Peserta1, ALSkorPemenangB5Peserta2,
                        ALKeyPemenangB5Peserta1, ALKeyPemenangB5Peserta2,
                        ALNamaPemenangB4Peserta1, ALNamaPemenangB4Peserta2,
                        ALSemuaSkorB6);
                RVBaganFinal4.setAdapter(adapterBaganFinal4);
                adapterBaganFinal4.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
//        Intent intent = new Intent(Full_bracket_turnament.this, DashboardAdmin.class);
//        startActivity(intent);
    }
}