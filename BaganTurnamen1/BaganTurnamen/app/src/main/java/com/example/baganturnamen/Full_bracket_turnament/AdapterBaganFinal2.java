package com.example.baganturnamen.Full_bracket_turnament;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baganturnamen.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterBaganFinal2 extends RecyclerView.Adapter<AdapterBaganFinal2.BaganFinal2ViewHolder>{
    Context context;
    ArrayList<String> ALNama;
    ArrayList<String> ALNamaPemenangB1Peserta1;
    ArrayList<String> ALNamaPemenangB1Peserta2;
    ArrayList<String> ALNamaPemenangB2Peserta1;
    ArrayList<String> ALNamaPemenangB2Peserta2;
    ArrayList<String> ALKeyPemenangB2Peserta1;
    ArrayList<String> ALKeyPemenangB2Peserta2;
    ArrayList<Integer> ALSkorPemenangB2Peserta1;
    ArrayList<Integer> ALSkorPemenangB2Peserta2;
    ArrayList<Integer> ALSkorPemenangB3Peserta1;
    ArrayList<Integer> ALSkorPemenangB3Peserta2;

    ArrayList<Integer> ALSemuaSkorB3;
    ArrayList<Integer> ALSemuaSkorB2;

    int n=0;

    DatabaseReference DBRef;

//    String SkorFinal;

    public AdapterBaganFinal2(Context context, ArrayList<String> ALNama,
                              ArrayList<String> ALNamaPemenangB1Peserta1, ArrayList<String> ALNamaPemenangB1Peserta2,
                              ArrayList<String> ALNamaPemenangB2Peserta1, ArrayList<String> ALNamaPemenangB2Peserta2,
                              ArrayList<String> ALKeyPemenangB2Peserta1, ArrayList<String> ALKeyPemenangB2Peserta2,
                              ArrayList<Integer> ALSkorPemenangB2Peserta1, ArrayList<Integer> ALSkorPemenangB2Peserta2,
                              ArrayList<Integer> ALSkorPemenangB3Peserta1, ArrayList<Integer> ALSkorPemenangB3Peserta2,
                              ArrayList<Integer> ALSemuaSkorB3, ArrayList<Integer> ALSemuaSkorB2){
        this.context = context;
        this.ALNama = ALNama;
        this.ALNamaPemenangB1Peserta1 = ALNamaPemenangB1Peserta1;
        this.ALNamaPemenangB1Peserta2 = ALNamaPemenangB1Peserta2;
        this.ALNamaPemenangB2Peserta1 = ALNamaPemenangB2Peserta1;
        this.ALNamaPemenangB2Peserta2 = ALNamaPemenangB2Peserta2;
//        this.ALKeyPemenangB1Peserta1 = ALKeyPemenangB1Peserta1;
//        this.ALKeyPemenangB1Peserta2 = ALKeyPemenangB1Peserta2;
        this.ALKeyPemenangB2Peserta1 = ALKeyPemenangB2Peserta1;
        this.ALKeyPemenangB2Peserta2 = ALKeyPemenangB2Peserta2;
        this.ALSkorPemenangB2Peserta1 = ALSkorPemenangB2Peserta1;
        this.ALSkorPemenangB2Peserta2 = ALSkorPemenangB2Peserta2;
        this.ALSkorPemenangB3Peserta1 = ALSkorPemenangB3Peserta1;
        this.ALSkorPemenangB3Peserta2 = ALSkorPemenangB3Peserta2;
        this.ALSemuaSkorB3 = ALSemuaSkorB3;
        this.ALSemuaSkorB2 = ALSemuaSkorB2;
    }

    public static class BaganFinal2ViewHolder extends RecyclerView.ViewHolder{
        CardView CVPesertaFinal;
        TextView TVNamaPesertaFinal;
        EditText ETSkorPesertaFinal;

        public BaganFinal2ViewHolder(@NonNull View itemView){
            super(itemView);
            CVPesertaFinal = itemView.findViewById(R.id.idCVPesertaFinal);
            TVNamaPesertaFinal = itemView.findViewById(R.id.idTVNamaPesertaFinal);
            ETSkorPesertaFinal = itemView.findViewById(R.id.idETSkorPesertaFinal);
        }
    }

    @NonNull
    @Override
    public AdapterBaganFinal2.BaganFinal2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View VItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_baganfinal, parent, false);
        return new AdapterBaganFinal2.BaganFinal2ViewHolder(VItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBaganFinal2.BaganFinal2ViewHolder holder, int position) {
//        DBRef = FirebaseDatabase.getInstance().getReference("Peserta");  //////////////////////////////////////////////
        DBRef = FirebaseDatabase.getInstance().getReference("Peserta2");
        if(ALSkorPemenangB2Peserta1.size()!=0&&ALSkorPemenangB2Peserta2.size()!=0&&
                ALNamaPemenangB1Peserta1.size()!=0&&ALNamaPemenangB1Peserta2.size()!=0){
//                ALNamaPemenangB2Peserta1.size()!=0&&ALNamaPemenangB2Peserta2.size()!=0){
            if(ALSkorPemenangB2Peserta1.get(position)==ALSkorPemenangB2Peserta2.get(position)){
                holder.TVNamaPesertaFinal.setText("null");
                holder.ETSkorPesertaFinal.setText("null");
            }
            else if(ALSkorPemenangB2Peserta1.get(position)>ALSkorPemenangB2Peserta2.get(position)){
//                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB2Peserta2.get(position)).child("History").child("babak4").setValue(null);
                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB2Peserta2.get(position)).child("History").child("babak3").setValue(null);
                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB2Peserta2.get(position)).child("History").child("pemenangB2").setValue(null);

                HashMap hashMap = new HashMap();
//                if(ALSkorPemenangB3Peserta1.size()<ALKeyPemenangB2Peserta1.size()){
//                if(ALSkorPemenangB3Peserta1.size()<getItemCount()){
//                if(ALSemuaSkorB3.size()<getItemCount()){
//                if(ALSkorPemenangB3Peserta1.get(position)==null){
//                if(ALSkorPemenangB3Peserta1.get(position)==0){
                    hashMap.put("babak3", ALSemuaSkorB3.get(position));
//                    hashMap.put("babak4", 0);
//                    hashMap.put("babak3", 0);
//                }

//                                holder.TVNamaPesertaFinal.setText(ALNamaPemenangB1Peserta1.get(position));
//                hashMap.put("pemenangB2", ALNamaPemenangB2Peserta1.get(position));
//                if(ALNamaPemenangB2Peserta1.get(position)==null) {
                    hashMap.put("pemenangB2", ALNamaPemenangB1Peserta1.get(position));
//                }
//                if(ALSkorPemenangB3Peserta1.get(position)==null){
//                    hashMap.put("babak3", 0);
//                }
                DBRef.child(ALKeyPemenangB2Peserta1.get(position)).child("History").updateChildren(hashMap);
//                DBRef.child(ALKeyPemenangB2Peserta2.get(position)).child("History").updateChildren(hashMap);

//                holder.TVNamaPesertaFinal.setText(ALNamaPemenangB2Peserta1.get(position));
                holder.TVNamaPesertaFinal.setText(ALNamaPemenangB1Peserta1.get(position));
//                holder.ETSkorPesertaFinal.setText(ALSkorPemenangB3Peserta1.get(position).toString());
                holder.ETSkorPesertaFinal.setText(ALSemuaSkorB3.get(position).toString());

            }
            else if(ALSkorPemenangB2Peserta1.get(position)<ALSkorPemenangB2Peserta2.get(position)){
//                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB2Peserta1.get(position)).child("History").child("babak4").setValue(null);
                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB2Peserta1.get(position)).child("History").child("babak3").setValue(null);
                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB2Peserta1.get(position)).child("History").child("pemenangB2").setValue(null);

//                holder.TVNamaPesertaFinal.setText(ALNamaPemenangB1Peserta2.get(position));

//                hashMap.put("pemenangB2", ALNamaPemenangB1Peserta2.get(position));

                HashMap hashMap = new HashMap();
//                if(ALSkorPemenangB3Peserta2.get(position)==null) {
//                if(ALSkorPemenangB3Peserta2.size()<ALKeyPemenangB2Peserta2.size()){
//                if(ALSkorPemenangB3Peserta2.size()<getItemCount()){
//                if(ALSemuaSkorB3.size()<getItemCount()){
//                if(ALSkorPemenangB3Peserta2.get(position)==null) {
//                    hashMap.put("babak3", 0);
                    hashMap.put("babak3", ALSemuaSkorB3.get(position));
//                    hashMap.put("babak4", 0);
//                }
                    hashMap.put("pemenangB2", ALNamaPemenangB1Peserta2.get(position));
//                }
//                if(ALSkorPemenangB3Peserta2.get(position)==null){
//                    hashMap.put("babak3", 0);
//                }
                DBRef.child(ALKeyPemenangB2Peserta2.get(position)).child("History").updateChildren(hashMap);
//                DBRef.child(ALKeyPemenangB2Peserta1.get(position)).child("History").updateChildren(hashMap);


                holder.TVNamaPesertaFinal.setText(ALNamaPemenangB1Peserta2.get(position));
//                holder.ETSkorPesertaFinal.setText(ALSkorPemenangB3Peserta2.get(position).toString());
                holder.ETSkorPesertaFinal.setText(ALSemuaSkorB3.get(position).toString());
            }
        }
        else{
            holder.TVNamaPesertaFinal.setText("nul");
        }


        holder.ETSkorPesertaFinal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
//                    String SkorFinals = ALSemuaSkorB3.get(position).toString();
                    String SkorFinals = holder.ETSkorPesertaFinal.getText().toString();
//                    Log.d("TAG", "sebe3: "+position+SkorFinals+ALSemuaSkorB3);
//                    if(!SkorFinals.equals("")&&ALSemuaSkorB3.size()/2==ALSkorPemenangB2Peserta1.size()) {
                    if(!SkorFinals.equals("")) {
//                    if(!ALSemuaSkorB3.get(position).equals("")) {
//                    if(!ALSemuaSkorB3.get(position).equals("")) {
//                    if(ALSemuaSkorB3.get(position)!=null) {
//                    if(ALSemuaSkorB3.get(position)!=null&&
//                            ALNamaPemenangB1Peserta1.size()<getItemCount()&&
//                            ALNamaPemenangB1Peserta2.size()<getItemCount()) {
//                    if(ALSemuaSkorB3.get(position)!=null) {
                        HashMap hashMap = new HashMap();
//                        hashMap.put("babak3", ALSemuaSkorB3.get(position));
                        hashMap.put("babak3", Integer.parseInt(SkorFinals));
                        if(ALSkorPemenangB2Peserta1.get(position)>ALSkorPemenangB2Peserta2.get(position)){
//                            FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB2Peserta2.get(position)).child("History").child("babak3").setValue(null);

//                            if(ALSkorPemenangB3Peserta1.size()<ALKeyPemenangB2Peserta1.size()){
////                                hashMap.put("babak3", ALSemuaSkorB3.get(position));
//                                hashMap.put("babak3", Integer.parseInt(SkorFinals));
//                            }

//                            hashMap.put("pemenangB2", ALNamaPemenangB1Peserta1.get(position));
//                            hashMap.put("pemenangB2", ALNamaPemenangB2Peserta1.get(position));

//                            Log.d("TAG", "p1: "+ALSkorPemenangB2Peserta1.get(position)+">"+ALSkorPemenangB2Peserta2.get(position)+";nm:"+ALNamaPemenangB2Peserta1.get(position)+";"+ALKeyPemenangB2Peserta1.get(position));
//                            Log.d("TAG", "p1  "+ALSemuaSkorB3+ALSemuaSkorB3.get(position));
//                            Log.d("TAG", "sesub3: "+ALNamaPemenangB1Peserta1.get(position));
//                            Log.d("TAG", "sesub4: "+ALKeyPemenangB2Peserta1.get(position));
    //                            DBRef.child(ALKeyPemenangB1Peserta1.get(position)).child("History").updateChildren(hashMap);
                            DBRef.child(ALKeyPemenangB2Peserta1.get(position)).child("History").updateChildren(hashMap);
                        }
                        else if(ALSkorPemenangB2Peserta1.get(position)<ALSkorPemenangB2Peserta2.get(position)){
//                            FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB2Peserta1.get(position)).child("History").child("babak3").setValue(null);

//                            HashMap hashMap = new HashMap();
//                            hashMap.put("babak3", ALSemuaSkorB3.get(position));
//                            hashMap.put("pemenangB2", ALNamaPemenangB1Peserta2.get(position));
//                            hashMap.put("pemenangB2", ALNamaPemenangB2Peserta2.get(position));

//                            if(ALSkorPemenangB3Peserta2.size()<ALKeyPemenangB2Peserta2.size()){
////                                hashMap.put("babak3", ALSemuaSkorB3.get(position));
//                                hashMap.put("babak3", Integer.parseInt(SkorFinals));
//                            }

//                            Log.d("TAG", "p1: "+ALSkorPemenangB2Peserta1.get(position)+"<"+ALSkorPemenangB2Peserta2.get(position)+";nm:"+ALNamaPemenangB2Peserta2.get(position)+";"+ALKeyPemenangB2Peserta2.get(position));
//                            Log.d("TAG", "p1  "+ALSemuaSkorB3+ALSemuaSkorB3.get(position));

//                            Log.d("TAG", "sesub2: "+ALSemuaSkorB3.get(position));
//                            Log.d("TAG", "sesub3: "+ALNamaPemenangB1Peserta2.get(position));
//                            Log.d("TAG", "sesub4: "+ALKeyPemenangB2Peserta2.get(position));
//                                DBRef.child(ALKeyPemenangB1Peserta2.get(position)).child("History").updateChildren(hashMap);
                            DBRef.child(ALKeyPemenangB2Peserta2.get(position)).child("History").updateChildren(hashMap);
                        }
                    }

                }
            }
        });

        holder.ETSkorPesertaFinal.addTextChangedListener(new TextWatcher (){
            @Override
            public void afterTextChanged(Editable s){
                if(holder.ETSkorPesertaFinal.isFocused()){
                    String SkorFinal = holder.ETSkorPesertaFinal.getText().toString();
//                    if(!SkorFinal.equals("")&&ALSemuaSkorB3.size()!=ALSkorPemenangB2Peserta1.size()) {
//                    if(!SkorFinal.equals("")&&ALSemuaSkorB3.size()<getItemCount()) {
                    if(!SkorFinal.equals("")) {
                        int inti = Integer.parseInt(SkorFinal);
//                        ALSemuaSkorB3.add(position, inti);
//                        Log.d("TAG", "sebe: "+position+inti+ALSemuaSkorB3);
//                        ALSemuaSkorB3.set(position, inti);
//                        Log.d("TAG", "sebe2: "+position+inti+ALSemuaSkorB3);
//                        HashMap hashMap = new HashMap();
//                        hashMap.put("babak3", inti);
                        if(ALSkorPemenangB2Peserta1.get(position)>ALSkorPemenangB2Peserta2.get(position)){
//                            Log.d("TAG", "sebebes1: "+position+ALSemuaSkorB3+inti);
                            ALSemuaSkorB3.set(position, inti);
//                            ALSkorPemenangB3Peserta1.set(position, inti);
//                            ALSemuaSkorB3.add(position, inti);
//                            Log.d("TAG", "sebebes2: "+position+ALSemuaSkorB3+inti);
//                            DBRef.child(ALKeyPemenangB1Peserta1.get(position)).child("History").updateChildren(hashMap);
                        }
                        else if(ALSkorPemenangB2Peserta1.get(position)<ALSkorPemenangB2Peserta2.get(position)){
//                            DBRef.child(ALKeyPemenangB1Peserta2.get(position)).child("History").updateChildren(hashMap);
//                            Log.d("TAG", "sebecil1: "+position+inti+ALSemuaSkorB3);
                            ALSemuaSkorB3.set(position, inti);
//                            ALSkorPemenangB3Peserta2.set(position, inti);
//                            ALSemuaSkorB3.add(position, inti);
//                            Log.d("TAG", "sebecil2: "+position+inti+ALSemuaSkorB3);
                        }
                    }
//                    else if(!SkorFinal.equals("")&&ALSemuaSkorB3.size()==ALSkorPemenangB2Peserta1.size()){
                    else if(!SkorFinal.equals("")&&ALSemuaSkorB3.size()==ALSkorPemenangB2Peserta1.size()){
//                        Log.d("TAG", "afterTextChanged3: "+ALSemuaSkorB3+ALSemuaSkorB3.size()+ALSkorPemenangB2Peserta1.size());
                        int inti = Integer.parseInt(SkorFinal);
                        ALSemuaSkorB3.set(position, inti);
//                        Log.d("TAG", "afterTextChanged4: "+ALSemuaSkorB3+ALSemuaSkorB3.size()+ALSkorPemenangB2Peserta1.size());
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
    }

    @Override
    public int getItemCount() {
        n = ALNama.size();
        if(n%2==0){
            n = n/2;
        }
        else{
            n = n%2;
        }
        return n;
    }

}
