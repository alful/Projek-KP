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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterBaganFinal4 extends RecyclerView.Adapter<AdapterBaganFinal4.BaganFinal4ViewHolder> {
    Context context;
    ArrayList<String> ALSemuaNama;
    ArrayList<Integer> ALSkorPemenangB5Peserta1;
    ArrayList<Integer> ALSkorPemenangB5Peserta2;
    ArrayList<String> ALKeyPemenangB5Peserta1;
    ArrayList<String> ALKeyPemenangB5Peserta2;
    ArrayList<String> ALNamaPemenangB4Peserta1;
    ArrayList<String> ALNamaPemenangB4Peserta2;
    ArrayList<Integer> ALSemuaSkorB6;


    DatabaseReference DBRef;

    public AdapterBaganFinal4(Context context, ArrayList<String> ALSemuaNama,
                              ArrayList<Integer> ALSkorPemenangB5Peserta1, ArrayList<Integer> ALSkorPemenangB5Peserta2,
                              ArrayList<String> ALKeyPemenangB5Peserta1, ArrayList<String> ALKeyPemenangB5Peserta2,
                              ArrayList<String> ALNamaPemenangB4Peserta1, ArrayList<String> ALNamaPemenangB4Peserta2,
                              ArrayList<Integer> ALSemuaSkorB6) {
        this.context = context;
        this.ALSemuaNama = ALSemuaNama;
        this.ALSkorPemenangB5Peserta1 = ALSkorPemenangB5Peserta1;
        this.ALSkorPemenangB5Peserta2 = ALSkorPemenangB5Peserta2;
        this.ALKeyPemenangB5Peserta1 = ALKeyPemenangB5Peserta1;
        this.ALKeyPemenangB5Peserta2 = ALKeyPemenangB5Peserta2;
        this.ALNamaPemenangB4Peserta1 = ALNamaPemenangB4Peserta1;
        this.ALNamaPemenangB4Peserta2 = ALNamaPemenangB4Peserta2;
        this.ALSemuaSkorB6 = ALSemuaSkorB6;
    }

    public static class BaganFinal4ViewHolder extends RecyclerView.ViewHolder{
        CardView CVPesertaFinal4;
        TextView TVNamaPesertaFinal4;
        EditText ETSkorPesertaFinal4;

        public BaganFinal4ViewHolder(@NonNull View itemview){
            super(itemview);
            CVPesertaFinal4 = itemview.findViewById(R.id.idCVPesertaFinal4);
            TVNamaPesertaFinal4 = itemview.findViewById(R.id.idTVNamaPesertaFinal4);
            ETSkorPesertaFinal4 = itemview.findViewById(R.id.idETSkorPesertaFinal4);
        }
    }

    @NonNull
    @Override
    public BaganFinal4ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View VItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_baganfinal4, parent, false);
        return new AdapterBaganFinal4.BaganFinal4ViewHolder(VItem);
    }

    @Override
    public void onBindViewHolder(@NonNull BaganFinal4ViewHolder holder, int position) {
        //DBRef = FirebaseDatabase.getInstance().getReference("Peserta2");//

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String UID=firebaseUser.getUid();

        DBRef = FirebaseDatabase.getInstance().getReference("Admin/"+UID+"/Peserta");

        if(getItemCount()==1){
            holder.ETSkorPesertaFinal4.setVisibility(View.GONE);
        }

        if(ALSkorPemenangB5Peserta1.size()==0&&ALSkorPemenangB5Peserta2.size()==0){
            holder.TVNamaPesertaFinal4.setText("");
            holder.ETSkorPesertaFinal4.setText("");
            Log.d("TAG", "onBindViewHolder: "+ALSkorPemenangB5Peserta1);
            Log.d("TAG", "onBindViewHolder: "+ALSkorPemenangB5Peserta2);
        }

//        holder.TVNamaPesertaFinal4.setText("");
//        holder.ETSkorPesertaFinal4.setText("");

        else if(ALSkorPemenangB5Peserta1.size()==getItemCount()&&ALSkorPemenangB5Peserta2.size()==getItemCount()&&
                ALSkorPemenangB5Peserta1.get(position)==ALSkorPemenangB5Peserta2.get(position)){
            holder.TVNamaPesertaFinal4.setText("");
            holder.ETSkorPesertaFinal4.setText("");
        }

        else if(ALSkorPemenangB5Peserta1.size()==getItemCount()&&ALSkorPemenangB5Peserta2.size()==getItemCount()&&
                ALSkorPemenangB5Peserta1.get(position)>ALSkorPemenangB5Peserta2.get(position)){
            holder.TVNamaPesertaFinal4.setText("");
            holder.ETSkorPesertaFinal4.setText("");
            Log.d("TAG", "nama: "+ALNamaPemenangB4Peserta1);
            Log.d("TAG", "nama: "+ALNamaPemenangB4Peserta2);
            if(ALSkorPemenangB5Peserta2.get(position)!=0&&ALKeyPemenangB5Peserta1.size()==getItemCount()){
                holder.TVNamaPesertaFinal4.setText(ALNamaPemenangB4Peserta1.get(position));
                HashMap hashMap = new HashMap();
                hashMap.put("pemenangB5", ALNamaPemenangB4Peserta1.get(position));
                DBRef.child(ALKeyPemenangB5Peserta1.get(position)).child("History").updateChildren(hashMap);

                if(ALSemuaSkorB6.size()==0||ALSemuaSkorB6.size()>0&&ALSemuaSkorB6.size()<getItemCount()){
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak6", 0);
                    DBRef.child(ALKeyPemenangB5Peserta1.get(position)).child("History").updateChildren(hashMap);
                }
                else if(ALSemuaSkorB6.size()!=0&&ALSemuaSkorB6.get(position)==0){
                    holder.ETSkorPesertaFinal4.setText("");
                }
                else if(ALSemuaSkorB6.size()!=0&&ALSemuaSkorB6.get(position)!=0){
                    holder.ETSkorPesertaFinal4.setText(ALSemuaSkorB6.get(position).toString());
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak6", ALSemuaSkorB6.get(position));
                    DBRef.child(ALKeyPemenangB5Peserta1.get(position)).child("History").updateChildren(hashMap);
                }
            }
        }
        else if(ALSkorPemenangB5Peserta1.size()==getItemCount()&&ALSkorPemenangB5Peserta2.size()==getItemCount()&&
                ALSkorPemenangB5Peserta1.get(position)<ALSkorPemenangB5Peserta2.get(position)){
            holder.TVNamaPesertaFinal4.setText("");
            holder.ETSkorPesertaFinal4.setText("");
            Log.d("TAG", "nama: "+ALNamaPemenangB4Peserta1);
            Log.d("TAG", "nama: "+ALNamaPemenangB4Peserta2);
            if(ALSkorPemenangB5Peserta1.get(position)!=0&&ALKeyPemenangB5Peserta2.size()==getItemCount()){
                holder.TVNamaPesertaFinal4.setText(ALNamaPemenangB4Peserta2.get(position));
                HashMap hashMap = new HashMap();
                hashMap.put("pemenangB5", ALNamaPemenangB4Peserta2.get(position));
                DBRef.child(ALKeyPemenangB5Peserta2.get(position)).child("History").updateChildren(hashMap);

                if(ALSemuaSkorB6.size()==0||ALSemuaSkorB6.size()>0&&ALSemuaSkorB6.size()<getItemCount()){
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak6", 0);
                    DBRef.child(ALKeyPemenangB5Peserta2.get(position)).child("History").updateChildren(hashMap);
                }
                else if(ALSemuaSkorB6.size()!=0&&ALSemuaSkorB6.get(position)==0){
                    holder.ETSkorPesertaFinal4.setText("");
                }
                else if(ALSemuaSkorB6.size()!=0&&ALSemuaSkorB6.get(position)!=0){
                    holder.ETSkorPesertaFinal4.setText(ALSemuaSkorB6.get(position).toString());
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak6", ALSemuaSkorB6.get(position));
                    DBRef.child(ALKeyPemenangB5Peserta2.get(position)).child("History").updateChildren(hashMap);
                }
            }
        }

        holder.ETSkorPesertaFinal4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    String SkorFinals = holder.ETSkorPesertaFinal4.getText().toString();
                    if(!SkorFinals.equals("")) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("babak6", ALSemuaSkorB6.get(position));
                        if(ALSkorPemenangB5Peserta1.get(position)>ALSkorPemenangB5Peserta2.get(position)){
                            DBRef.child(ALKeyPemenangB5Peserta1.get(position)).child("History").updateChildren(hashMap);
                        }
                        else if(ALSkorPemenangB5Peserta1.get(position)<ALSkorPemenangB5Peserta2.get(position)){
                            DBRef.child(ALKeyPemenangB5Peserta2.get(position)).child("History").updateChildren(hashMap);
                        }
                    }

                }
            }
        });

        holder.ETSkorPesertaFinal4.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s){
                if(holder.ETSkorPesertaFinal4.isFocused()){
                    String SkorFinal = holder.ETSkorPesertaFinal4.getText().toString();
                    if(!SkorFinal.equals("")) {
                        int inti = Integer.parseInt(SkorFinal);
                        if(ALSkorPemenangB5Peserta1.get(position)>ALSkorPemenangB5Peserta2.get(position)){
                            ALSemuaSkorB6.set(position, inti);
                        }
                        else if(ALSkorPemenangB5Peserta1.get(position)<ALSkorPemenangB5Peserta2.get(position)){
                            ALSemuaSkorB6.set(position, inti);
                        }
                    }
                    else if(!SkorFinal.equals("")&&ALSemuaSkorB6.size()==ALSkorPemenangB5Peserta1.size()){
                        int inti = Integer.parseInt(SkorFinal);
                        ALSemuaSkorB6.set(position, inti);
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
//        return 1;
        int n = ALSemuaNama.size();
        if(n%2==0){
            n = n/32;
        }
        else if(n<32){
            n = 0;
        }
        else{
            n = n%2;
        }
        return n;
    }
}
