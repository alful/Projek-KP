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

public class AdapterBaganFinal extends RecyclerView.Adapter<AdapterBaganFinal.BaganFinalViewHolder>{
    Context context;
    ArrayList<String> ALSemuaNama;
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
    ArrayList<Integer> ALSemuaSkorB4;
    ArrayList<Integer> ALSemuaSkorB5;

    int n=0;

    DatabaseReference DBRef;

//    String SkorFinal;

    public AdapterBaganFinal(Context context, ArrayList<String> ALSemuaNama,
                             ArrayList<String> ALNamaPemenangB1Peserta1, ArrayList<String> ALNamaPemenangB1Peserta2,
                             ArrayList<String> ALNamaPemenangB2Peserta1, ArrayList<String> ALNamaPemenangB2Peserta2,
                             ArrayList<String> ALKeyPemenangB2Peserta1, ArrayList<String> ALKeyPemenangB2Peserta2,
                             ArrayList<Integer> ALSkorPemenangB2Peserta1, ArrayList<Integer> ALSkorPemenangB2Peserta2,
                             ArrayList<Integer> ALSkorPemenangB3Peserta1, ArrayList<Integer> ALSkorPemenangB3Peserta2,
                             ArrayList<Integer> ALSemuaSkorB3, ArrayList<Integer> ALSemuaSkorB2,
                             ArrayList<Integer> ALSemuaSkorB4, ArrayList<Integer> ALSemuaSkorB5){
        this.context = context;
        this.ALSemuaNama = ALSemuaNama;
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
        this.ALSemuaSkorB4 = ALSemuaSkorB4;
        this.ALSemuaSkorB5 = ALSemuaSkorB5;
    }

    public static class BaganFinalViewHolder extends RecyclerView.ViewHolder{
        CardView CVPesertaFinal;
        TextView TVNamaPesertaFinal;
        EditText ETSkorPesertaFinal;

        public BaganFinalViewHolder(@NonNull View itemView){
            super(itemView);
            CVPesertaFinal = itemView.findViewById(R.id.idCVPesertaFinal);
            TVNamaPesertaFinal = itemView.findViewById(R.id.idTVNamaPesertaFinal);
            ETSkorPesertaFinal = itemView.findViewById(R.id.idETSkorPesertaFinal);
        }
    }

    @NonNull
    @Override
    public AdapterBaganFinal.BaganFinalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View VItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_baganfinal, parent, false);
        return new AdapterBaganFinal.BaganFinalViewHolder(VItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBaganFinal.BaganFinalViewHolder holder, int position) {
//        DBRef = FirebaseDatabase.getInstance().getReference("Peserta");  //////////////////////////////////////////////
        //DBRef = FirebaseDatabase.getInstance().getReference("Peserta2");//
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String UID=firebaseUser.getUid();

        DBRef = FirebaseDatabase.getInstance().getReference("Admin/"+UID+"/Peserta");


//        if(ALSemuaSkorB1.size()==0&&ALSkorPemenangB2Peserta1.size()==0&&ALSkorPemenangB2Peserta2.size()==0){
        if(ALSkorPemenangB2Peserta1.size()==0&&ALSkorPemenangB2Peserta2.size()==0){
            holder.TVNamaPesertaFinal.setText("");
            holder.ETSkorPesertaFinal.setText("");
        }
        else if(ALSkorPemenangB2Peserta1.size()>0&&ALSkorPemenangB2Peserta2.size()>0&&
                ALSkorPemenangB2Peserta1.size()==getItemCount()&&ALSkorPemenangB2Peserta2.size()==getItemCount()&&
                ALSkorPemenangB2Peserta1.get(position)==ALSkorPemenangB2Peserta2.get(position)){
            holder.TVNamaPesertaFinal.setText("");
            holder.ETSkorPesertaFinal.setText("");
        }
        else if(ALSkorPemenangB2Peserta1.size()>0&&ALSkorPemenangB2Peserta2.size()>0&&
                ALSkorPemenangB2Peserta1.size()==getItemCount()&&ALSkorPemenangB2Peserta2.size()==getItemCount()&&
                ALSkorPemenangB2Peserta1.get(position)>ALSkorPemenangB2Peserta2.get(position)){
            holder.TVNamaPesertaFinal.setText("");
            holder.ETSkorPesertaFinal.setText("");
            if(ALSkorPemenangB2Peserta2.get(position)!=0){
                holder.TVNamaPesertaFinal.setText(ALNamaPemenangB1Peserta1.get(position));
                HashMap hashMap = new HashMap();
                hashMap.put("pemenangB2", ALNamaPemenangB1Peserta1.get(position));
                DBRef.child(ALKeyPemenangB2Peserta1.get(position)).child("History").updateChildren(hashMap);

                if(ALSemuaSkorB3.size()==0||ALSemuaSkorB3.size()>0&&ALSemuaSkorB3.size()<getItemCount()){
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak3", 0);
                    DBRef.child(ALKeyPemenangB2Peserta1.get(position)).child("History").updateChildren(hashMap);
                }
                else if(ALSemuaSkorB3.size()!=0&&ALSemuaSkorB3.get(position)==0){
                    holder.ETSkorPesertaFinal.setText("");
                }
                else if(ALSemuaSkorB3.size()!=0&&ALSemuaSkorB3.get(position)!=0){
                    holder.ETSkorPesertaFinal.setText(ALSemuaSkorB3.get(position).toString());
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak3", ALSemuaSkorB3.get(position));
                    DBRef.child(ALKeyPemenangB2Peserta1.get(position)).child("History").updateChildren(hashMap);
                }
            }
        }
        else if(ALSkorPemenangB2Peserta1.size()>0&&ALSkorPemenangB2Peserta2.size()>0&&
                ALSkorPemenangB2Peserta1.size()==getItemCount()&&ALSkorPemenangB2Peserta2.size()==getItemCount()&&
                ALSkorPemenangB2Peserta1.get(position)<ALSkorPemenangB2Peserta2.get(position)){
            holder.TVNamaPesertaFinal.setText("");
            holder.ETSkorPesertaFinal.setText("");
            if(ALSkorPemenangB2Peserta1.get(position)!=0){
                holder.TVNamaPesertaFinal.setText(ALNamaPemenangB1Peserta2.get(position));
                HashMap hashMap = new HashMap();
                hashMap.put("pemenangB2", ALNamaPemenangB1Peserta2.get(position));
                DBRef.child(ALKeyPemenangB2Peserta2.get(position)).child("History").updateChildren(hashMap);

                if(ALSemuaSkorB3.size()==0||ALSemuaSkorB3.size()>0&&ALSemuaSkorB3.size()<getItemCount()){
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak3", 0);
                    DBRef.child(ALKeyPemenangB2Peserta2.get(position)).child("History").updateChildren(hashMap);
                }
                else if(ALSemuaSkorB3.size()!=0&&ALSemuaSkorB3.get(position)==0){
                    holder.ETSkorPesertaFinal.setText("");
                }
                else if(ALSemuaSkorB3.size()!=0&&ALSemuaSkorB3.get(position)!=0){
                    holder.ETSkorPesertaFinal.setText(ALSemuaSkorB3.get(position).toString());
//                    HashMap hashMap = new HashMap();
                    hashMap.put("babak3", ALSemuaSkorB3.get(position));
                    DBRef.child(ALKeyPemenangB2Peserta2.get(position)).child("History").updateChildren(hashMap);
                }
            }
        }
        else{
            holder.TVNamaPesertaFinal.setText("");
            holder.ETSkorPesertaFinal.setText("");
        }


        holder.ETSkorPesertaFinal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    String SkorFinals = holder.ETSkorPesertaFinal.getText().toString();
                    if(!SkorFinals.equals("")) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("babak3", Integer.parseInt(SkorFinals));
                        if(ALSkorPemenangB2Peserta1.get(position)>ALSkorPemenangB2Peserta2.get(position)){
                            DBRef.child(ALKeyPemenangB2Peserta1.get(position)).child("History").updateChildren(hashMap);
                        }
                        else if(ALSkorPemenangB2Peserta1.get(position)<ALSkorPemenangB2Peserta2.get(position)){
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
                    if(!SkorFinal.equals("")) {
                        int inti = Integer.parseInt(SkorFinal);
                        if(ALSkorPemenangB2Peserta1.get(position)>ALSkorPemenangB2Peserta2.get(position)){
                            ALSemuaSkorB3.set(position, inti);
                        }
                        else if(ALSkorPemenangB2Peserta1.get(position)<ALSkorPemenangB2Peserta2.get(position)){
                            ALSemuaSkorB3.set(position, inti);
                        }
                    }
                    else if(!SkorFinal.equals("")&&ALSemuaSkorB3.size()==ALSkorPemenangB2Peserta1.size()){
                        int inti = Integer.parseInt(SkorFinal);
                        ALSemuaSkorB3.set(position, inti);}
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
        n = ALSemuaNama.size();
        if(n%2==0){
            n = n/4;
        }
//        else if(n<4){
//            n = 0;
//        }
        else{
            n = n%2;
        }
        return n;
    }

}
