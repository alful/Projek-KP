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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    ArrayList<String> ALSemuaKey;
    ArrayList<String> ALKeyB1Peserta1;
    ArrayList<String> ALKeyB1Peserta2;
    ArrayList<String> ALSemuaNama;
    ArrayList<String> ALNamaB1Peserta1;
    ArrayList<String> ALNamaB1Peserta2;
    ArrayList<Integer> ALSemuaSkorB1;
    ArrayList<Integer> ALSkorB1Peserta1;
    ArrayList<Integer> ALSkorB1Peserta2;
    //    ArrayList<String> ALSKey1;
//    ArrayList<String> ALSKey2;
    ArrayList<Integer> ALSemuaSkorB2;
    ArrayList<Integer> ALSkorBabak2Peserta1;
    ArrayList<Integer> ALSkorBabak2Peserta2;

    DatabaseReference DBRef;
//    FirebaseDatabase FBDB;

    String skorbaru, skorbaru2, skorfinal;
//    int iskorbaru, iskorbaru2;


    //    public AdapterBagan(Context context, ArrayList<String> ALNama, ArrayList<String> ALNama2) {
//    public AdapterBagan(Context context, ArrayList<String> ALNama, ArrayList<String> ALNama2,
//                        ArrayList<String> ALSKey1 , ArrayList<String> ALSKey2){
    public AdapterBagan(Context context,
                        ArrayList<String> ALSemuaKey,
                        ArrayList<String> ALKeyB1Peserta1 , ArrayList<String> ALKeyB1Peserta2,
                        ArrayList<String> ALSemuaNama,
                        ArrayList<String> ALNamaB1Peserta1, ArrayList<String> ALNamaB1Peserta2,
                        ArrayList<Integer> ALSemuaSkorB1,
                        ArrayList<Integer> ALSkorB1Peserta1, ArrayList<Integer> ALSkorB1Peserta2,
                        ArrayList<Integer> ALSkorBabak2Peserta1, ArrayList<Integer> ALSkorBabak2Peserta2,
                        ArrayList<Integer> ALSemuaSkorB2){
        this.context = context;
        this.ALSemuaKey = ALSemuaKey;
        this.ALKeyB1Peserta1 = ALKeyB1Peserta1;
        this.ALKeyB1Peserta2 = ALKeyB1Peserta2;
        this.ALSemuaNama = ALSemuaNama;
        this.ALNamaB1Peserta1 = ALNamaB1Peserta1;
        this.ALNamaB1Peserta2 = ALNamaB1Peserta2;
        this.ALSemuaSkorB1 = ALSemuaSkorB1;
        this.ALSkorB1Peserta1 = ALSkorB1Peserta1;
        this.ALSkorB1Peserta2 = ALSkorB1Peserta2;
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
    public void onBindViewHolder(@NonNull AdapterBagan.BaganViewHolder holder, int position){
//        DBRef = FirebaseDatabase.getInstance().getReference("Peserta");
        //DBRef = FirebaseDatabase.getInstance().getReference("Peserta2"); ///////////////////////////////////////
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String UID=firebaseUser.getUid();

        DBRef = FirebaseDatabase.getInstance().getReference("Admin/"+UID+"/Peserta");
//        if(ALSKey2.size()%2!=0&&position==ALSKey2.size()){
//            ALSKey2.add(null);
//            ALNama2.add(null);
//            ALSkorBabak1Peserta2.add(0);
//            holder.CVPeserta2.setVisibility(View.GONE);
//            holder.TVNamaPemenangB1.setText(ALNama.get(position));
//        }

//        holder.TVNamaPeserta1.setText(ALNama.get(position));
//        holder.TVNamaPeserta2.setText(ALNama2.get(position));


        if(ALSemuaSkorB1.size()==0){
            HashMap hashMap = new HashMap();
            hashMap.put("babak1", 0);
            DBRef.child(ALKeyB1Peserta1.get(position)).child("History").updateChildren(hashMap);
            DBRef.child(ALKeyB1Peserta2.get(position)).child("History").updateChildren(hashMap);
            holder.TVNamaPeserta1.setText(ALNamaB1Peserta1.get(position));
            holder.TVNamaPeserta2.setText(ALNamaB1Peserta2.get(position));
            holder.ETSkorPeserta1.setText("");
            holder.ETSkorPeserta2.setText("");
            holder.TVNamaPemenangB1.setText("");
        }
        else if(ALSkorB1Peserta1.get(position)==ALSkorB1Peserta2.get(position)){
            holder.TVNamaPeserta1.setText(ALNamaB1Peserta1.get(position));
            holder.TVNamaPeserta2.setText(ALNamaB1Peserta2.get(position));
            holder.ETSkorPeserta1.setText("");
            holder.ETSkorPeserta2.setText("");
            holder.TVNamaPemenangB1.setText("");
            holder.ETSkorPemenangB1.setText("");
        }
        else if(ALSkorB1Peserta1.get(position)>ALSkorB1Peserta2.get(position)){
            holder.TVNamaPeserta1.setText(ALNamaB1Peserta1.get(position));
            holder.TVNamaPeserta2.setText(ALNamaB1Peserta2.get(position));
            holder.ETSkorPeserta1.setText(ALSkorB1Peserta1.get(position).toString());
            holder.ETSkorPeserta2.setText("");
            holder.TVNamaPemenangB1.setText("");
            holder.ETSkorPemenangB1.setText("");
            if(ALSkorB1Peserta1.get(position)!=0&&ALSkorB1Peserta2.get(position)!=0){
                holder.ETSkorPeserta2.setText(ALSkorB1Peserta2.get(position).toString());
                holder.TVNamaPemenangB1.setText(ALNamaB1Peserta1.get(position));
                if(ALSemuaSkorB2.size()==0||ALSemuaSkorB2.size()>0&&ALSemuaSkorB2.size()<getItemCount()){
                    HashMap hashMap = new HashMap();
                    hashMap.put("babak2", 0);
                    DBRef.child(ALKeyB1Peserta1.get(position)).child("History").updateChildren(hashMap);
                }
                else if(ALSemuaSkorB2.size()!=0&&ALSemuaSkorB2.get(position)==0){
                    holder.ETSkorPemenangB1.setText("");
                }
                else if(ALSemuaSkorB2.size()!=0&&ALSemuaSkorB2.get(position)!=0){
                    holder.ETSkorPemenangB1.setText(ALSemuaSkorB2.get(position).toString());
                    HashMap hashMap = new HashMap();
                    hashMap.put("babak2", ALSemuaSkorB2.get(position));
                    DBRef.child(ALKeyB1Peserta1.get(position)).child("History").updateChildren(hashMap);
                }
            }

        }
        else if(ALSkorB1Peserta1.get(position)!=0&&ALSkorB1Peserta2.get(position)!=0&&
                ALSkorB1Peserta1.get(position)<ALSkorB1Peserta2.get(position)){
            holder.TVNamaPeserta1.setText(ALNamaB1Peserta1.get(position));
            holder.TVNamaPeserta2.setText(ALNamaB1Peserta2.get(position));
            holder.ETSkorPeserta1.setText("");
            holder.ETSkorPeserta2.setText(ALSkorB1Peserta2.get(position).toString());
            holder.TVNamaPemenangB1.setText(ALNamaB1Peserta2.get(position));
            holder.ETSkorPemenangB1.setText("");
            if(ALSkorB1Peserta1.get(position)!=0&&ALSkorB1Peserta2.get(position)!=0){
                holder.ETSkorPeserta1.setText(ALSkorB1Peserta1.get(position).toString());
                holder.TVNamaPemenangB1.setText(ALNamaB1Peserta2.get(position));
                if(ALSemuaSkorB2.size()==0||ALSemuaSkorB2.size()>0&&ALSemuaSkorB2.size()<getItemCount()){
                    HashMap hashMap = new HashMap();
                    hashMap.put("babak2", 0);
                    DBRef.child(ALKeyB1Peserta2.get(position)).child("History").updateChildren(hashMap);
                }
                else if(ALSemuaSkorB2.size()!=0&&ALSemuaSkorB2.get(position)==0){
                    holder.ETSkorPemenangB1.setText("");
                }
                else if(ALSemuaSkorB2.size()!=0&&ALSemuaSkorB2.get(position)!=0){
                    holder.ETSkorPemenangB1.setText(ALSemuaSkorB2.get(position).toString());
                    HashMap hashMap = new HashMap();
                    hashMap.put("babak2", ALSemuaSkorB2.get(position));
                    DBRef.child(ALKeyB1Peserta2.get(position)).child("History").updateChildren(hashMap);
                }
            }
        }

        holder.ETSkorPeserta1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    if(skorbaru!=null) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("babak1", ALSkorB1Peserta1.get(position));
                        DBRef.child(ALKeyB1Peserta1.get(position)).child("History").updateChildren(hashMap);
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
                    int iskorbaru = Integer.parseInt(skorbaru);
                    ALSkorB1Peserta1.set(position, iskorbaru);
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
                        ALSkorB1Peserta2.set(position, iskorbaru2);
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
                        hashMap.put("babak1", ALSkorB1Peserta2.get(position));
                        DBRef.child(ALKeyB1Peserta2.get(position)).child("History").updateChildren(hashMap);
                    }
                }
            }
        });


        holder.ETSkorPemenangB1.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s){
                if(holder.ETSkorPemenangB1.isFocused()){
                    skorfinal = holder.ETSkorPemenangB1.getText().toString();
                    if(!skorfinal.equals("")&&ALSemuaSkorB2.size()<getItemCount()) {
                        int ifinal = Integer.parseInt(skorfinal);
//                        ALSemuaSkorB2.set(position, ifinal);
                        ALSemuaSkorB2.add(position, ifinal);
                    }
                    else if(!skorfinal.equals("")&&ALSemuaSkorB2.size()==getItemCount()){
                        int ifinal = Integer.parseInt(skorfinal);
                        ALSemuaSkorB2.set(position, ifinal);
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

        holder.ETSkorPemenangB1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean bfokus) {
                if(!bfokus){
                    if(skorfinal!=null) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("babak2", ALSemuaSkorB2.get(position));
                        if(ALSkorB1Peserta1.get(position)>ALSkorB1Peserta2.get(position)) {
                            hashMap.put("pemenangB1", ALNamaB1Peserta1.get(position));
                            DBRef.child(ALKeyB1Peserta1.get(position)).child("History").updateChildren(hashMap);
                        }
                        else if(ALSkorB1Peserta1.get(position)<ALSkorB1Peserta2.get(position)) {
                            hashMap.put("pemenangB1", ALNamaB1Peserta2.get(position));
                            DBRef.child(ALKeyB1Peserta2.get(position)).child("History").updateChildren(hashMap);
                        }
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
//        return ALNama.size();
        int n = ALSemuaNama.size();
        if(n%2==0){
            n = n/2;
        }
        else{
            n = n%2;
        }
        return n;
    }

}
