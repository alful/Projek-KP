package com.example.baganturnamen.Full_bracket_turnament;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
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
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterBaganFinal3 extends RecyclerView.Adapter<AdapterBaganFinal3.BaganFinal3ViewHolder> {
    Context context;
    ArrayList<String> ALSemuaNama;
    ArrayList<Integer> ALSemuaSkorB5;
    ArrayList<String> ALKeySemuaPemenangB4;
    ArrayList<Integer> ALSkorPemenangB4Peserta1;
    ArrayList<Integer> ALSkorPemenangB4Peserta2;
    ArrayList<String> ALKeyPemenangB4Peserta1;
    ArrayList<String> ALKeyPemenangB4Peserta2;
    ArrayList<String> ALNamaPemenangB3Peserta1;
    ArrayList<String> ALNamaPemenangB3Peserta2;

    DatabaseReference DBRef;

    public AdapterBaganFinal3(Context context, ArrayList<String> ALSemuaNama,
                              ArrayList<Integer> ALSemuaSkorB5,
                              ArrayList<String> ALKeySemuaPemenangB4,
                              ArrayList<Integer> ALSkorPemenangB4Peserta1, ArrayList<Integer> ALSkorPemenangB4Peserta2,
                              ArrayList<String> ALKeyPemenangB4Peserta1, ArrayList<String> ALKeyPemenangB4Peserta2,
                              ArrayList<String> ALNamaPemenangB3Peserta1, ArrayList<String> ALNamaPemenangB3Peserta2) {
        this.context = context;
        this.ALSemuaNama = ALSemuaNama;
        this.ALSemuaSkorB5 = ALSemuaSkorB5;
        this.ALKeySemuaPemenangB4 = ALKeySemuaPemenangB4;
        this.ALSkorPemenangB4Peserta1 = ALSkorPemenangB4Peserta1;
        this.ALSkorPemenangB4Peserta2 = ALSkorPemenangB4Peserta2;
        this.ALKeyPemenangB4Peserta1 = ALKeyPemenangB4Peserta1;
        this.ALKeyPemenangB4Peserta2 = ALKeyPemenangB4Peserta2;
        this.ALNamaPemenangB3Peserta1 = ALNamaPemenangB3Peserta1;
        this.ALNamaPemenangB3Peserta2 = ALNamaPemenangB3Peserta2;
    }

    public static class BaganFinal3ViewHolder extends RecyclerView.ViewHolder{
        CardView CVPesertaFinal3;
        TextView TVNamaPesertaFinal3;
        EditText ETSkorPesertaFinal3;

        public BaganFinal3ViewHolder(@NonNull View itemView){
            super(itemView);
            CVPesertaFinal3 = itemView.findViewById(R.id.idCVPesertaFinal3);
            TVNamaPesertaFinal3 = itemView.findViewById(R.id.idTVNamaPesertaFinal3);
            ETSkorPesertaFinal3 = itemView.findViewById(R.id.idETSkorPesertaFinal3);
        }
    }

    @NonNull
    @Override
    public BaganFinal3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_baganfinal3, parent, false);
        return new AdapterBaganFinal3.BaganFinal3ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaganFinal3ViewHolder holder, int position) {
        //DBRef = FirebaseDatabase.getInstance().getReference("Peserta2");//
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String UID=firebaseUser.getUid();

        DBRef = FirebaseDatabase.getInstance().getReference("Admin/"+UID+"/Peserta");

        if(getItemCount()==1){
            holder.ETSkorPesertaFinal3.setVisibility(View.GONE);
        }

        if(ALSkorPemenangB4Peserta1.size()==0&&ALSkorPemenangB4Peserta2.size()==0){
            holder.TVNamaPesertaFinal3.setText("");
            holder.ETSkorPesertaFinal3.setText("");
        }

        else if(ALSkorPemenangB4Peserta1.size()==getItemCount()&&ALSkorPemenangB4Peserta2.size()==getItemCount()&&
                ALSkorPemenangB4Peserta1.get(position)==ALSkorPemenangB4Peserta2.get(position)){
            holder.TVNamaPesertaFinal3.setText("");
            holder.ETSkorPesertaFinal3.setText("");
        }
        else if(ALSkorPemenangB4Peserta1.size()==getItemCount()&&ALSkorPemenangB4Peserta2.size()==getItemCount()&&
                ALSkorPemenangB4Peserta1.get(position)>ALSkorPemenangB4Peserta2.get(position)){
            holder.TVNamaPesertaFinal3.setText("");
            holder.ETSkorPesertaFinal3.setText("");
            if(ALSkorPemenangB4Peserta2.get(position)!=0&&ALKeyPemenangB4Peserta1.size()==getItemCount()){
                holder.TVNamaPesertaFinal3.setText(ALNamaPemenangB3Peserta1.get(position));
                HashMap hashMap = new HashMap();
                hashMap.put("pemenangB4", ALNamaPemenangB3Peserta1.get(position));
                DBRef.child(ALKeyPemenangB4Peserta1.get(position)).child("History").updateChildren(hashMap);

                if(ALSemuaSkorB5.size()==0||ALSemuaSkorB5.size()>0&&ALSemuaSkorB5.size()<getItemCount()){
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak5", 0);
                    DBRef.child(ALKeyPemenangB4Peserta1.get(position)).child("History").updateChildren(hashMap);
                }
                else if(ALSemuaSkorB5.size()!=0&&ALSemuaSkorB5.get(position)==0){
                    holder.ETSkorPesertaFinal3.setText("");
                }
                else if(ALSemuaSkorB5.size()!=0&&ALSemuaSkorB5.get(position)!=0){
                    holder.ETSkorPesertaFinal3.setText(ALSemuaSkorB5.get(position).toString());
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak5", ALSemuaSkorB5.get(position));
                    DBRef.child(ALKeyPemenangB4Peserta1.get(position)).child("History").updateChildren(hashMap);
                }
            }
        }
        else if(ALSkorPemenangB4Peserta1.size()==getItemCount()&&ALSkorPemenangB4Peserta2.size()==getItemCount()&&
                ALSkorPemenangB4Peserta1.get(position)<ALSkorPemenangB4Peserta2.get(position)){
            holder.TVNamaPesertaFinal3.setText("");
            holder.ETSkorPesertaFinal3.setText("");
            if(ALSkorPemenangB4Peserta1.get(position)!=0&&ALKeyPemenangB4Peserta2.size()==getItemCount()){
                holder.TVNamaPesertaFinal3.setText(ALNamaPemenangB3Peserta2.get(position));
                HashMap hashMap = new HashMap();
                hashMap.put("pemenangB4", ALNamaPemenangB3Peserta2.get(position));
                DBRef.child(ALKeyPemenangB4Peserta2.get(position)).child("History").updateChildren(hashMap);

                if(ALSemuaSkorB5.size()==0||ALSemuaSkorB5.size()>0&&ALSemuaSkorB5.size()<getItemCount()){
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak5", 0);
                    DBRef.child(ALKeyPemenangB4Peserta2.get(position)).child("History").updateChildren(hashMap);
                }
                else if(ALSemuaSkorB5.size()!=0&&ALSemuaSkorB5.get(position)==0){
                    holder.ETSkorPesertaFinal3.setText("");
                }
                else if(ALSemuaSkorB5.size()!=0&&ALSemuaSkorB5.get(position)!=0){
                    holder.ETSkorPesertaFinal3.setText(ALSemuaSkorB5.get(position).toString());
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak5", ALSemuaSkorB5.get(position));
                    DBRef.child(ALKeyPemenangB4Peserta2.get(position)).child("History").updateChildren(hashMap);
                }
            }
        }

        holder.ETSkorPesertaFinal3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    String SkorFinals = holder.ETSkorPesertaFinal3.getText().toString();
                    if(!SkorFinals.equals("")) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("babak5", ALSemuaSkorB5.get(position));
                        if(ALSkorPemenangB4Peserta1.get(position)>ALSkorPemenangB4Peserta2.get(position)){
                            DBRef.child(ALKeyPemenangB4Peserta1.get(position)).child("History").updateChildren(hashMap);
                        }
                        else if(ALSkorPemenangB4Peserta1.get(position)<ALSkorPemenangB4Peserta2.get(position)){
                            DBRef.child(ALKeyPemenangB4Peserta2.get(position)).child("History").updateChildren(hashMap);
                        }
                    }

                }
            }
        });

        holder.ETSkorPesertaFinal3.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s){
                if(holder.ETSkorPesertaFinal3.isFocused()){
                    String SkorFinal = holder.ETSkorPesertaFinal3.getText().toString();
                    if(!SkorFinal.equals("")) {
                        int inti = Integer.parseInt(SkorFinal);
                        if(ALSkorPemenangB4Peserta1.get(position)>ALSkorPemenangB4Peserta2.get(position)){
                            ALSemuaSkorB5.set(position, inti);
                        }
                        else if(ALSkorPemenangB4Peserta1.get(position)<ALSkorPemenangB4Peserta2.get(position)){
                            ALSemuaSkorB5.set(position, inti);
                        }
                    }
                    else if(!SkorFinal.equals("")&&ALSemuaSkorB5.size()==ALSkorPemenangB4Peserta1.size()){
                        int inti = Integer.parseInt(SkorFinal);
                        ALSemuaSkorB5.set(position, inti);
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
        int n = ALSemuaNama.size();
        if(n%2==0){
            n = n/16;
        }
        else if(n<16){
            n = 0;
        }
        else{
            n = n%2;
        }
        return n;
    }
}
