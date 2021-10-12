package com.example.baganturnamen.Full_bracket_turnament;

import android.media.session.MediaSession;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.TextKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baganturnamen.History;
import com.example.baganturnamen.MainActivity;
import com.example.baganturnamen.Pertandingan;
import com.example.baganturnamen.Peserta;
import com.example.baganturnamen.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class AdapterBagan extends RecyclerView.Adapter<AdapterBagan.BaganViewHolder>{
    Context context;
    ArrayList<String> ALNama;
    ArrayList<String> ALNama2;
    ArrayList<String> ALSKey1;
    ArrayList<String> ALSKey2;

    ArrayList<Integer> ALSkorBabak1Peserta1;
    ArrayList<Integer> ALSkorBabak1Peserta2;
    ArrayList<Integer> ALSkorBabak2Peserta1;
    ArrayList<Integer> ALSkorBabak2Peserta2;

    ArrayList<Integer> ALSemuaSkorB2;

    DatabaseReference DBRef;

    String skorbaru, skorbaru2, skorfinal, skey1, skey2, skeymenang1, skeymenang2;

    int skora, skorb;
    int iskorbaru, iskorbaru2;


//    public AdapterBagan(Context context, ArrayList<String> ALNama, ArrayList<String> ALNama2) {
//    public AdapterBagan(Context context, ArrayList<String> ALNama, ArrayList<String> ALNama2,
//                        ArrayList<String> ALSKey1 , ArrayList<String> ALSKey2){
    public AdapterBagan(Context context, ArrayList<String> ALNama, ArrayList<String> ALNama2,
                        ArrayList<String> ALSKey1 , ArrayList<String> ALSKey2,
                        ArrayList<Integer> ALSkorBabak1Peserta1, ArrayList<Integer> ALSkorBabak1Peserta2,
                        ArrayList<Integer> ALSkorBabak2Peserta1, ArrayList<Integer> ALSkorBabak2Peserta2,
                        ArrayList<Integer> ALSemuaSkorB2){
        this.context = context;
        this.ALNama = ALNama;
        this.ALNama2 = ALNama2;
        this.ALSKey1 = ALSKey1;
        this.ALSKey2 = ALSKey2;
        this.ALSkorBabak1Peserta1 = ALSkorBabak1Peserta1;
        this.ALSkorBabak1Peserta2 = ALSkorBabak1Peserta2;
        this.ALSkorBabak2Peserta1 = ALSkorBabak2Peserta1;
        this.ALSkorBabak2Peserta2 = ALSkorBabak2Peserta2;
        this.ALSemuaSkorB2 = ALSemuaSkorB2;
    }

    public static class BaganViewHolder extends RecyclerView.ViewHolder{
        CardView CVPeserta1, CVPeserta2, CVPemenangB1;
        TextView TVNamaPeserta1, TVNamaPeserta2, TVNamaPemenangB1;
        EditText ETSkorPeserta1, ETSkorPeserta2, ETSkorPemenangB1;

        public BaganViewHolder(@NonNull View itemView){
            super(itemView);
            CVPeserta1 = itemView.findViewById(R.id.idCVPeserta1);
            CVPeserta2 = itemView.findViewById(R.id.idCVPeserta2);
            CVPemenangB1 = itemView.findViewById(R.id.idCVPemenangB1);
            TVNamaPeserta1 = itemView.findViewById(R.id.idTVNamaPeserta1);
            TVNamaPeserta2 = itemView.findViewById(R.id.idTVNamaPeserta2);
            TVNamaPemenangB1 = itemView.findViewById(R.id.idTVNamaPemenangB1);
            ETSkorPeserta1 = itemView.findViewById(R.id.idETSkorPeserta1);
            ETSkorPeserta2 = itemView.findViewById(R.id.idETSkorPeserta2);
            ETSkorPemenangB1 = itemView.findViewById(R.id.idETSkorPemenangB1);
        }
    }

    @NonNull
    @Override
    public AdapterBagan.BaganViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View VItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_bagan, parent, false);
        return new AdapterBagan.BaganViewHolder(VItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBagan.BaganViewHolder holder, int position) {
        DBRef = FirebaseDatabase.getInstance().getReference("Peserta");

        if(ALSKey2.size()%2!=0&&position==ALSKey2.size()){
            ALSKey2.add(null);
            ALNama2.add(null);
            ALSkorBabak1Peserta2.add(0);
            holder.CVPeserta2.setVisibility(View.GONE);
            holder.TVNamaPemenangB1.setText(ALNama.get(position));
        }

        holder.TVNamaPeserta1.setText(ALNama.get(position));
        holder.TVNamaPeserta2.setText(ALNama2.get(position));

//        if(ALSkorBabak1Peserta1.get(position).equals(0)) {
        if(ALSkorBabak1Peserta1.get(position)==null) {
            holder.ETSkorPeserta1.setText("");
//            if(ALSkorBabak1Peserta2.get(position).equals(0)) {
            if(ALSkorBabak1Peserta2.get(position)==null) {
                holder.ETSkorPeserta2.setText("");
            }
            else{
                holder.ETSkorPeserta2.setText(ALSkorBabak1Peserta2.get(position).toString());
            }
        }
//        else if(ALSkorBabak1Peserta2.get(position).equals(0)) {
        else if(ALSkorBabak1Peserta2.get(position)==null) {
            holder.ETSkorPeserta2.setText("");
//            if(ALSkorBabak1Peserta1.get(position).equals(0)) {
            if(ALSkorBabak1Peserta1.get(position)==null) {
                holder.ETSkorPeserta1.setText("");
            }
            else{
                holder.ETSkorPeserta1.setText(ALSkorBabak1Peserta1.get(position).toString());
            }
        }
        else {

            holder.ETSkorPeserta1.setText(ALSkorBabak1Peserta1.get(position).toString());
            holder.ETSkorPeserta2.setText(ALSkorBabak1Peserta2.get(position).toString());

            skeymenang1 = ALSKey1.get(position);
            skeymenang2 = ALSKey2.get(position);

            skora = Integer.parseInt(holder.ETSkorPeserta1.getText().toString());
            skorb = Integer.parseInt(holder.ETSkorPeserta2.getText().toString());

            if (skora > skorb) {
//            if (ALSkorBabak1Peserta1.get(position) > ALSkorBabak1Peserta2.get(position)) {
                holder.TVNamaPemenangB1.setText(ALNama.get(position));
                if (ALSKey1.get(position) != null) {
//                    if(ALSkorBabak2Peserta1.get(position).toString().equals("0")){
                    if(ALSkorBabak2Peserta1.get(position)==null){
                        holder.ETSkorPemenangB1.setText("");
                    }
                    else{
                        holder.ETSkorPemenangB1.setText(ALSkorBabak2Peserta1.get(position).toString());
                        HashMap hashMap = new HashMap();
                        hashMap.put("pemenangB1", ALNama.get(position));
                        DBRef.child(ALSKey1.get(position)).child("History").updateChildren(hashMap);
//                            ALSKeyPemenang.add(ALSKey1.get(position));
                    }
                }

            } else if (skora < skorb) {
//            } else if (ALSkorBabak1Peserta1.get(position) < ALSkorBabak1Peserta2.get(position)) {
                holder.TVNamaPemenangB1.setText(ALNama2.get(position));
                if (ALSKey2.get(position) != null) {
//                    if(ALSkorBabak2Peserta2.get(position).toString().equals("0")){
                    if(ALSkorBabak2Peserta2.get(position)==null){
                        holder.ETSkorPemenangB1.setText("");
                    }
                    else{
                        holder.ETSkorPemenangB1.setText(ALSkorBabak2Peserta2.get(position).toString());
                        HashMap hashMap = new HashMap();
                        hashMap.put("pemenangB1", ALNama2.get(position));
                        DBRef.child(ALSKey2.get(position)).child("History").updateChildren(hashMap);
                    }
                }
            } else {
                holder.ETSkorPemenangB1.setText("");
            }
        }

        if (ALSkorBabak1Peserta1.get(position)== null && ALSkorBabak1Peserta2.get(position) ==null)
        {

        }
        else if (ALSkorBabak1Peserta1.get(position)!= null && ALSkorBabak1Peserta2.get(position) ==null)
        {

        }
        else {
            if (holder.ETSkorPemenangB1.getText().toString().equals("")) {
                holder.ETSkorPemenangB1.setText(Integer.toString(0));


                if (ALSkorBabak1Peserta1.get(position) > ALSkorBabak1Peserta2.get(position)) {
                    DBRef.child(ALSKey1.get(position)).child("History").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Integer sas = snapshot.child("babak2").getValue(Integer.class);
                            Log.d("TAG", "onDataChange: " + sas);
                            if (sas == null) {
                                HashMap hashMap = new HashMap();
                                hashMap.put("babak2", 0);
                                DBRef.child(ALSKey1.get(position)).child("History").updateChildren(hashMap);

                            } else
                                holder.ETSkorPemenangB1.setText(Integer.toString(sas));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


//                DBRef.child(ALKeyPemenangB2Peserta1.get(position)).child("History").updateChildren(hashMap);
                } else if (ALSkorBabak1Peserta1.get(position) < ALSkorBabak1Peserta2.get(position)) {
                    //                            DBRef.child(ALKeyPemenangB1Peserta2.get(position)).child("History").updateChildren(hashMap);
                    DBRef.child(ALSKey2.get(position)).child("History").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            Integer sas = snapshot.child("babak2").getValue(Integer.class);
                            Log.d("TAG", "onDataChange: " + sas);
                            if (sas == null) {
                                HashMap hashMap = new HashMap();
                                hashMap.put("babak2", 0);
                                DBRef.child(ALSKey2.get(position)).child("History").updateChildren(hashMap);

                            } else
                                holder.ETSkorPemenangB1.setText(Integer.toString(sas));
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

//            DBRef.child(ALKeyPemenangB2Peserta1.get(position)).child("History").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    Integer sas=snapshot.child("babak3").getValue(Integer.class);
//                    holder.ETSkorPesertaFinal.setText(Integer.toString(sas));
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });


            }
        }
        holder.ETSkorPeserta1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    if(skorbaru!=null) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("babak1", ALSkorBabak1Peserta1.get(position));
//                        DBRef.child(skey1).child("History").updateChildren(hashMap);
                        DBRef.child(ALSKey1.get(position)).child("History").updateChildren(hashMap);
                    }
                }
            }
        });


        holder.ETSkorPeserta1.addTextChangedListener(new TextWatcher(){


            @Override
            public void afterTextChanged(Editable s){
//                            if(holder.ETSkorPeserta1.isFocused()){
                skorbaru = holder.ETSkorPeserta1.getText().toString();
                if(!skorbaru.equals("")){
                    iskorbaru = Integer.parseInt(skorbaru);
                }
                if(!skorbaru.equals("")) {
                    ALSkorBabak1Peserta1.set(position, iskorbaru);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){

            }


        });


        holder.ETSkorPeserta2.addTextChangedListener(new TextWatcher(){

            @Override
            public void afterTextChanged(Editable s){
                if(holder.ETSkorPeserta2.isFocused()){
                    skorbaru2 = holder.ETSkorPeserta2.getText().toString();
                    if(!skorbaru2.equals("")) {
                        int iskorbaru2 = Integer.parseInt(skorbaru2);
                        ALSkorBabak1Peserta2.set(position, iskorbaru2);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){

            }
        });

        holder.ETSkorPeserta2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean bfokus) {
                if(!bfokus){
                    if(skorbaru2!=null) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("babak1", ALSkorBabak1Peserta2.get(position));
//                        DBRef.child(skey2).child("History").updateChildren(hashMap);
                        DBRef.child(ALSKey2.get(position)).child("History").updateChildren(hashMap);
                    }
                }
            }
        });


        holder.ETSkorPemenangB1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
//                    String et = holder.ETSkorPesertaFinal.getText().toString();
//                    if(et.equals("")){
//                        if(ALSkorPemenangB2Peserta1.get(position)>ALSkorPemenangB2Peserta2.get(position)){
//                            Log.d("TAG", "upload: ");
//                            HashMap hashMap = new HashMap();
//                            hashMap.put("babak3", 0);
//                            DBRef.child(ALKeyPemenangB2Peserta1.get(position)).child("History").updateChildren(hashMap);
//                        }
//                        else if(ALSkorPemenangB2Peserta1.get(position)<ALSkorPemenangB2Peserta2.get(position)){
//                            Log.d("TAG", "upload2: ");
//                            HashMap hashMap = new HashMap();
//                            hashMap.put("babak3", 0);
//                            DBRef.child(ALKeyPemenangB2Peserta2.get(position)).child("History").updateChildren(hashMap);
//                        }
//                    }
                    Log.d("TAG", "onFocusChange: pencet");
                }
                if (!b) {
                    Log.d("TAG", "onFocusChange: lepas");
//                    String SkorFinals = ALSemuaSkorB3.get(position).toString();
//                    Log.d("TAG", "sebe3: "+position+SkorFinals+ALSemuaSkorB3);
//                    if(!SkorFinals.equals("")&&ALSemuaSkorB3.size()/2==ALSkorPemenangB2Peserta1.size()) {
//                    if(!SkorFinals.equals("")) {
//                    if(!ALSemuaSkorB3.get(position).equals("")) {

                        if (!ALSemuaSkorB2.get(position).equals("")) {
                            Log.d("TAG", "onFocusChange: lepas " + position);

//                        Log.d("TAG", "onFocusChangeseb: "+ALSemuaSkorB3+ALSemuaSkorB3.get(position));
                            //                            Log.d("TAG", "onFocusChangeseb: "+SkorFinal);
                            //                            Log.d("TAG", "onFocusChangeseb: "+position+ALSemuaSkorB3);
//                                Log.d("TAG", "sesub: "+position+ALSemuaSkorB3+ALSemuaSkorB3.get(position));
//                                Log.d("TAG", "sesub2: "+ALSkorPemenangB2Peserta1.get(position));
//                                Log.d("TAG", "sesub3: "+ALSkorPemenangB2Peserta2.get(position));
                            //                        int inti = Integer.parseInt(SkorFinal);
//                        HashMap hashMap = new HashMap();
//                        hashMap.put("babak3", ALSemuaSkorB3.get(position));
////
//                            Log.d("TAG", "onFocusChange: "+ALSemuaSkorB3.get(position));
////
                            if (ALSkorBabak1Peserta1.get(position) > ALSkorBabak1Peserta2.get(position)) {
                                HashMap hashMap = new HashMap();
                                hashMap.put("babak2", ALSemuaSkorB2.get(position));
                                hashMap.put("pemenangB1", ALNama.get(position));

                                //                            DBRef.child(ALKeyPemenangB1Peserta1.get(position)).child("History").updateChildren(hashMap);
                                DBRef.child(ALSKey1.get(position)).child("History").updateChildren(hashMap);
                            } else if (ALSkorBabak1Peserta1.get(position) < ALSkorBabak1Peserta2.get(position)) {
                                HashMap hashMap = new HashMap();
                                hashMap.put("babak2", ALSemuaSkorB2.get(position));
                                hashMap.put("pemenangB1", ALNama.get(position));

                                //                            DBRef.child(ALKeyPemenangB1Peserta2.get(position)).child("History").updateChildren(hashMap);
                                DBRef.child(ALSKey2.get(position)).child("History").updateChildren(hashMap);
                            }
//                        else{
////                            holder.ETSkorPesertaFinal.setText("");
//                        }


//                    else if(!SkorFinal.equals("")&&ALSemuaSkorB3.size()==ALSkorPemenangB2Peserta1.size()){
//
                        }
//                    else if(!SkorFinals.equals("")&&position!=0){
//                        HashMap hashMap = new HashMap();
//                        hashMap.put("babak3", ALSemuaSkorB3.get(position-1));
//
//                        //                        Log.d("TAG", "onFocusChange: "+ALSemuaSkorB3.get(position));
//
//                        if(ALSkorPemenangB2Peserta1.get(position-1)>ALSkorPemenangB2Peserta2.get(position-1)){
//                            hashMap.put("pemenangB2", ALNamaPemenangB1Peserta1.get(position-1));
//                            //                            DBRef.child(ALKeyPemenangB1Peserta1.get(position)).child("History").updateChildren(hashMap);
//                            DBRef.child(ALKeyPemenangB2Peserta1.get(position-1)).child("History").updateChildren(hashMap);
//                        }
//                        else if(ALSkorPemenangB2Peserta1.get(position-1)<ALSkorPemenangB2Peserta2.get(position-1)){
//                            hashMap.put("pemenangB2", ALNamaPemenangB1Peserta2.get(position-1));
//                            //                            DBRef.child(ALKeyPemenangB1Peserta2.get(position)).child("History").updateChildren(hashMap);
//                            DBRef.child(ALKeyPemenangB2Peserta2.get(position-1)).child("History").updateChildren(hashMap);
//                        }
//                        else{
//                            holder.ETSkorPesertaFinal.setText("");
//                        }
//                    }



                }
            }
        });

        holder.ETSkorPemenangB1.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (holder.ETSkorPemenangB1.isFocused()) {
                    skorfinal = holder.ETSkorPemenangB1.getText().toString();
                    Log.d("TAG", "afterTsssextChanged: " + skorfinal);
//                    if(!SkorFinal.equals("")&&ALSemuaSkorB3.size()!=ALSkorPemenangB2Peserta1.size()) {
                    if (!skorfinal.equals("")) {
                        int inti = Integer.parseInt(skorfinal);
//                        ALSemuaSkorB3.add(position, inti);
//                        Log.d("TAG", "sebe: "+position+inti+ALSemuaSkorB3);
//                        ALSemuaSkorB3.set(position, inti);
//                        Log.d("TAG", "sebe2: "+position+inti+ALSemuaSkorB3);
//                        HashMap hashMap = new HashMap();
//                        hashMap.put("babak3", inti);
                        if (ALSkorBabak1Peserta1.get(position) > ALSkorBabak1Peserta2.get(position)) {
                            Log.d("TAG", "sebebes1: " + position + ALSemuaSkorB2 + inti);
//                            ALSemuaSkorB3.set(position, inti);
                            ALSemuaSkorB2.add(position, inti);
                            Log.d("TAG", "sebebes2: " + position + ALSemuaSkorB2 + inti);
//                            DBRef.child(ALKeyPemenangB1Peserta1.get(position)).child("History").updateChildren(hashMap);
                        } else if (ALSkorBabak1Peserta1.get(position) < ALSkorBabak1Peserta2.get(position)) {
//                            DBRef.child(ALKeyPemenangB1Peserta2.get(position)).child("History").updateChildren(hashMap);
                            Log.d("TAG", "sebecil1: " + position + inti + ALSemuaSkorB2);
//                            ALSemuaSkorB3.set(position, inti);
                            ALSemuaSkorB2.add(position, inti);
                            Log.d("TAG", "sebecil2: " + position + inti + ALSemuaSkorB2);
                        }
                    }
//                    else if(!SkorFinal.equals("")&&ALSemuaSkorB3.size()==ALSkorPemenangB2Peserta1.size()){
                    else if (!skorfinal.equals("") && ALSemuaSkorB2.size() == ALSkorBabak1Peserta1.size()) {
                        int inti = Integer.parseInt(skorfinal);
                        ALSemuaSkorB2.set(position, inti);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return ALNama.size();
    }
    }

