package com.example.baganturnamen.Full_bracket_turnament;

import android.text.Editable;
import android.text.TextWatcher;
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

public class AdapterBaganFinal2 extends RecyclerView.Adapter<AdapterBaganFinal2.BaganFinal2ViewHolder>{
    Context context;
    ArrayList<String> ALSemuaNama;
    ArrayList<String> ALKeySemuaPemenangB3;
    ArrayList<Integer> ALSkorPemenangB3Peserta1;
    ArrayList<Integer> ALSkorPemenangB3Peserta2;
    ArrayList<String> ALKeyPemenangB3Peserta1;
    ArrayList<String> ALKeyPemenangB3Peserta2;
    ArrayList<String> ALKeyPemenangB2Peserta1;
    ArrayList<String> ALKeyPemenangB2Peserta2;
    ArrayList<String> ALNamaPemenangB2Peserta1;
    ArrayList<String> ALNamaPemenangB2Peserta2;
    ArrayList<Integer> ALSemuaSkorB4;
    ArrayList<String> ALNamaPemenangB3Peserta1;
    ArrayList<String> ALNamaPemenangB3Peserta2;
    ArrayList<Integer> ALSemuaSkorB5;
    ArrayList<Integer> ALSkorPemenangB4Peserta1;
    ArrayList<Integer> ALSkorPemenangB4Peserta2;

    DatabaseReference DBRef;

    public AdapterBaganFinal2(Context context,
                              ArrayList<String> ALSemuaNama,
                              ArrayList<String> ALKeySemuaPemenangB3,
                              ArrayList<String> ALKeyPemenangB2Peserta1, ArrayList<String> ALKeyPemenangB2Peserta2,
                              ArrayList<String> ALKeyPemenangB3Peserta1, ArrayList<String> ALKeyPemenangB3Peserta2,
                              ArrayList<String> ALNamaPemenangB2Peserta1, ArrayList<String> ALNamaPemenangB2Peserta2,
                              ArrayList<Integer> ALSkorPemenangB3Peserta1, ArrayList<Integer> ALSkorPemenangB3Peserta2,
                              ArrayList<Integer> ALSemuaSkorB4,
                              ArrayList<String> ALNamaPemenangB3Peserta1, ArrayList<String> ALNamaPemenangB3Peserta2,
                              ArrayList<Integer> ALSemuaSkorB5,
                              ArrayList<Integer> ALSkorPemenangB4Peserta1, ArrayList<Integer> ALSkorPemenangB4Peserta2){
        this.context = context;
        this.ALSemuaNama = ALSemuaNama;
        this.ALKeySemuaPemenangB3 = ALKeySemuaPemenangB3;
        this.ALKeyPemenangB2Peserta1 = ALKeyPemenangB2Peserta1;
        this.ALKeyPemenangB2Peserta2 = ALKeyPemenangB2Peserta2;
        this.ALKeyPemenangB3Peserta1 = ALKeyPemenangB3Peserta1;
        this.ALKeyPemenangB3Peserta2 = ALKeyPemenangB3Peserta2;
        this.ALNamaPemenangB2Peserta1 = ALNamaPemenangB2Peserta1;
        this.ALNamaPemenangB2Peserta2 = ALNamaPemenangB2Peserta2;
        this.ALSkorPemenangB3Peserta1 = ALSkorPemenangB3Peserta1;
        this.ALSkorPemenangB3Peserta2 = ALSkorPemenangB3Peserta2;
        this.ALSemuaSkorB4 = ALSemuaSkorB4;
        this.ALNamaPemenangB3Peserta1 = ALNamaPemenangB3Peserta1;
        this.ALNamaPemenangB3Peserta2 = ALNamaPemenangB3Peserta2;
        this.ALSemuaSkorB5 = ALSemuaSkorB5;
        this.ALSkorPemenangB4Peserta1 = ALSkorPemenangB4Peserta1;
        this.ALSkorPemenangB4Peserta2 = ALSkorPemenangB4Peserta2;
    }

    public static class BaganFinal2ViewHolder extends RecyclerView.ViewHolder{
        CardView CVPesertaFinal2;
        TextView TVNamaPesertaFinal2;
        EditText ETSkorPesertaFinal2;
        View VPanah2Final3;

        public BaganFinal2ViewHolder(@NonNull View itemview){
            super(itemview);
            CVPesertaFinal2 = itemView.findViewById(R.id.idCVPesertaFinal2);
            TVNamaPesertaFinal2 = itemView.findViewById(R.id.idTVNamaPesertaFinal2);
            ETSkorPesertaFinal2 = itemView.findViewById(R.id.idETSkorPesertaFinal2);
            VPanah2Final3 = itemView.findViewById(R.id.idVPanah2Final3);
        }
    }

    @NonNull
    @Override
    public AdapterBaganFinal2.BaganFinal2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_baganfinal2, parent, false);
        return new AdapterBaganFinal2.BaganFinal2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaganFinal2ViewHolder holder, int position) {
        //DBRef = FirebaseDatabase.getInstance().getReference("Peserta2");//
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String UID=firebaseUser.getUid();

        DBRef = FirebaseDatabase.getInstance().getReference("Admin/"+UID+"/Peserta");

        if(getItemCount()==1){
            holder.ETSkorPesertaFinal2.setVisibility(View.GONE);
            holder.VPanah2Final3.setVisibility(View.GONE);
        }

        if(ALSkorPemenangB3Peserta1.size()==0&&ALSkorPemenangB3Peserta2.size()==0){
            holder.TVNamaPesertaFinal2.setText("");
            holder.ETSkorPesertaFinal2.setText("");
        }
        else if(ALSkorPemenangB3Peserta1.size()==getItemCount()&&ALSkorPemenangB3Peserta2.size()==getItemCount()&&
                ALSkorPemenangB3Peserta1.get(position)==ALSkorPemenangB3Peserta2.get(position)){
            holder.TVNamaPesertaFinal2.setText("");
            holder.ETSkorPesertaFinal2.setText("");
        }
        else if(ALSkorPemenangB3Peserta1.size()==getItemCount()&&ALSkorPemenangB3Peserta2.size()==getItemCount()&&
                ALSkorPemenangB3Peserta1.get(position)>ALSkorPemenangB3Peserta2.get(position)){
            holder.TVNamaPesertaFinal2.setText("");
            holder.ETSkorPesertaFinal2.setText("");
            if(ALSkorPemenangB3Peserta2.get(position)!=0){
                holder.TVNamaPesertaFinal2.setText(ALNamaPemenangB2Peserta1.get(position));
                HashMap hashMap = new HashMap();
                hashMap.put("pemenangB3", ALNamaPemenangB2Peserta1.get(position));
                DBRef.child(ALKeyPemenangB3Peserta1.get(position)).child("History").updateChildren(hashMap);

                if(ALSemuaSkorB4.size()==0||ALSemuaSkorB4.size()>0&&ALSemuaSkorB4.size()<getItemCount()){
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak4", 0);
                    DBRef.child(ALKeyPemenangB3Peserta1.get(position)).child("History").updateChildren(hashMap);
                }
                else if(ALSemuaSkorB4.size()!=0&&ALSemuaSkorB4.get(position)==0){
                    holder.ETSkorPesertaFinal2.setText("");
                }
                else if(ALSemuaSkorB4.size()!=0&&ALSemuaSkorB4.get(position)!=0){
                    holder.ETSkorPesertaFinal2.setText(ALSemuaSkorB4.get(position).toString());
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak4", ALSemuaSkorB4.get(position));
                    DBRef.child(ALKeyPemenangB3Peserta1.get(position)).child("History").updateChildren(hashMap);
                }
            }
        }
        else if(ALSkorPemenangB3Peserta1.size()==getItemCount()&&ALSkorPemenangB3Peserta2.size()==getItemCount()&&
                ALSkorPemenangB3Peserta1.get(position)<ALSkorPemenangB3Peserta2.get(position)){
            holder.TVNamaPesertaFinal2.setText("");
            holder.ETSkorPesertaFinal2.setText("");
            if(ALSkorPemenangB3Peserta1.get(position)!=0){
                holder.TVNamaPesertaFinal2.setText(ALNamaPemenangB2Peserta2.get(position));
                HashMap hashMap = new HashMap();
                hashMap.put("pemenangB3", ALNamaPemenangB2Peserta2.get(position));
                DBRef.child(ALKeyPemenangB3Peserta2.get(position)).child("History").updateChildren(hashMap);

                if(ALSemuaSkorB4.size()==0||ALSemuaSkorB4.size()>0&&ALSemuaSkorB4.size()<getItemCount()){
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak4", 0);
                    DBRef.child(ALKeyPemenangB3Peserta2.get(position)).child("History").updateChildren(hashMap);
                }
                else if(ALSemuaSkorB4.size()!=0&&ALSemuaSkorB4.get(position)==0){
                    holder.ETSkorPesertaFinal2.setText("");
                }
                else if(ALSemuaSkorB4.size()!=0&&ALSemuaSkorB4.get(position)!=0){
                    holder.ETSkorPesertaFinal2.setText(ALSemuaSkorB4.get(position).toString());
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak4", ALSemuaSkorB4.get(position));
                    DBRef.child(ALKeyPemenangB3Peserta2.get(position)).child("History").updateChildren(hashMap);
                }
            }
        }

        holder.ETSkorPesertaFinal2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    String SkorFinals = holder.ETSkorPesertaFinal2.getText().toString();
                    if(!SkorFinals.equals("")) {
                        HashMap hashMap = new HashMap();
//                        hashMap.put("babak4", Integer.parseInt(SkorFinals));
                        hashMap.put("babak4", ALSemuaSkorB4.get(position));
                        if(ALSkorPemenangB3Peserta1.get(position)>ALSkorPemenangB3Peserta2.get(position)){
                            DBRef.child(ALKeyPemenangB3Peserta1.get(position)).child("History").updateChildren(hashMap);
                        }
                        else if(ALSkorPemenangB3Peserta1.get(position)<ALSkorPemenangB3Peserta2.get(position)){
                            DBRef.child(ALKeyPemenangB3Peserta2.get(position)).child("History").updateChildren(hashMap);
                        }
                    }

                }
            }
        });

        holder.ETSkorPesertaFinal2.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s){
                if(holder.ETSkorPesertaFinal2.isFocused()){
                    String SkorFinal = holder.ETSkorPesertaFinal2.getText().toString();
                    if(!SkorFinal.equals("")) {
                        int inti = Integer.parseInt(SkorFinal);
                        if(ALSkorPemenangB3Peserta1.get(position)>ALSkorPemenangB3Peserta2.get(position)){
                            ALSemuaSkorB4.set(position, inti);
                        }
                        else if(ALSkorPemenangB3Peserta1.get(position)<ALSkorPemenangB3Peserta2.get(position)){
                            ALSemuaSkorB4.set(position, inti);
                        }
                    }
                    else if(!SkorFinal.equals("")&&ALSemuaSkorB4.size()==ALSkorPemenangB3Peserta1.size()){
                        int inti = Integer.parseInt(SkorFinal);
                        ALSemuaSkorB4.set(position, inti);
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
//        return 2;
        int n = ALSemuaNama.size();
        if(n%2==0){
            n = n/8;
        }
        else if(n<8){
            n = 0;
        }
        else{
            n = n%2;
        }
        return n;
    }
}
