package com.example.baganturnamen.Pesertasi;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baganturnamen.Full_bracket_turnament.AdapterBaganFinal3;
import com.example.baganturnamen.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterBaganPeserta3 extends RecyclerView.Adapter<AdapterBaganPeserta3.BaganPeserta3ViewHolder>{
    Context context;
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
    ArrayList<String> ALNama;


    DatabaseReference DBRef;
    int n=0;

    String ids;

    public AdapterBaganPeserta3(Context context,
                              ArrayList<String> ALKeySemuaPemenangB3,
                              ArrayList<String> ALKeyPemenangB2Peserta1, ArrayList<String> ALKeyPemenangB2Peserta2,
                              ArrayList<String> ALKeyPemenangB3Peserta1, ArrayList<String> ALKeyPemenangB3Peserta2,
                              ArrayList<String> ALNamaPemenangB2Peserta1, ArrayList<String> ALNamaPemenangB2Peserta2,
                              ArrayList<Integer> ALSkorPemenangB3Peserta1, ArrayList<Integer> ALSkorPemenangB3Peserta2,
                              ArrayList<Integer> ALSemuaSkorB4, ArrayList<String> ALNamaPemenangB3Peserta1, ArrayList<String> ALNamaPemenangB3Peserta2,String ids,ArrayList<String> ALNama){
        this.context = context;
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
        this.ids=ids;
        this.ALNama=ALNama;
    }
    public static class BaganPeserta3ViewHolder extends RecyclerView.ViewHolder {
        CardView CVPesertaFinal2;
        TextView TVNamaPesertaFinal2;
        EditText ETSkorPesertaFinal2;

        public BaganPeserta3ViewHolder(@NonNull View itemView) {
            super(itemView);
            CVPesertaFinal2 = itemView.findViewById(R.id.idCVPesertaFinal2);
            TVNamaPesertaFinal2 = itemView.findViewById(R.id.idTVNamaPesertaFinal2);
            ETSkorPesertaFinal2 = itemView.findViewById(R.id.idETSkorPesertaFinal2);

        }
    }
    @NonNull
    @Override
    public BaganPeserta3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_baganfinal2, parent, false);
        return new AdapterBaganPeserta3.BaganPeserta3ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaganPeserta3ViewHolder holder, int position) {
        DBRef = FirebaseDatabase.getInstance().getReference("Admin/"+ids+"/Peserta");

//        Log.d("TAG", "onBindViewHolder: "+ALSemuaSkorB3);
        holder.ETSkorPesertaFinal2.setInputType(InputType.TYPE_NULL);
        holder.ETSkorPesertaFinal2.setFocusable(false);


//        Log.d("TAG", "onBindViewHolder: "+ALSemuaSkorB3);

        if(ALSemuaSkorB4.size()==0&&ALKeyPemenangB3Peserta1.size()!=0&&ALKeyPemenangB3Peserta2.size()!=0) {
//        if(ALSemuaSkorB4.size()==0&&ALNamaPemenangB2Peserta1.size()!=0&&ALNamaPemenangB2Peserta2.size()!=0) {
//        if(ALSemuaSkorB4.size()==0&&ALNamaPemenangB3Peserta1.size()!=0&&ALNamaPemenangB3Peserta2.size()!=0) {
            HashMap hashMap = new HashMap();
            hashMap.put("babak4", 0);
            hashMap.put("babak5", 0);
            for(int x=0; x<ALKeySemuaPemenangB3.size(); x++){
                DBRef.child(ALKeySemuaPemenangB3.get(x)).child("History").updateChildren(hashMap);
            }
//            if(ALSkorPemenangB3Peserta1.get(position)>ALSkorPemenangB3Peserta2.get(position)){
//                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB3Peserta2.get(position)).child("History").child("babak4").setValue(null);
////                hashMap.put("babak4", 0);
////                DBRef.child(ALKeyPemenangB3Peserta1.get(position)).child("History").updateChildren(hashMap);
//                DBRef.child(ALKeyPemenangB3Peserta2.get(position)).child("History").updateChildren(hashMap);
//            }
//            else if(ALSkorPemenangB3Peserta1.get(position)<ALSkorPemenangB3Peserta2.get(position)) {
//                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB3Peserta1.get(position)).child("History").child("babak4").setValue(null);
////                hashMap.put("babak4", 0);
////                DBRef.child(ALKeyPemenangB3Peserta2.get(position)).child("History").updateChildren(hashMap);
//                DBRef.child(ALKeyPemenangB3Peserta1.get(position)).child("History").updateChildren(hashMap);
//            }
        }
        else if(ALSkorPemenangB3Peserta1.size()!=0&&ALSkorPemenangB3Peserta2.size()!=0){
//            holder.TVNamaPesertaFinal2.setText("null1");
//            holder.ETSkorPesertaFinal2.setText("null1");
            if(ALSkorPemenangB3Peserta1.get(position)==ALSkorPemenangB3Peserta2.get(position)){
                holder.TVNamaPesertaFinal2.setText("");
                holder.ETSkorPesertaFinal2.setText("");
            }
            else if(ALSkorPemenangB3Peserta1.get(position)>ALSkorPemenangB3Peserta2.get(position)){
                FirebaseDatabase.getInstance().getReference("Admin/"+ids+"/Peserta").child(ALKeyPemenangB3Peserta2.get(position)).child("History").child("babak5").setValue(null);
                FirebaseDatabase.getInstance().getReference("Admin/"+ids+"/Peserta").child(ALKeyPemenangB3Peserta2.get(position)).child("History").child("babak4").setValue(null);
                FirebaseDatabase.getInstance().getReference("Admin/"+ids+"/Peserta").child(ALKeyPemenangB3Peserta2.get(position)).child("History").child("pemenangB3").setValue(null);
                HashMap hashMap = new HashMap();
                hashMap.put("babak4", ALSemuaSkorB4.get(position));
                hashMap.put("pemenangB3", ALNamaPemenangB2Peserta1.get(position));
                DBRef.child(ALKeyPemenangB3Peserta1.get(position)).child("History").updateChildren(hashMap);
//                DBRef.child(ALKeyPemenangB2Peserta1.get(position)).child("History").updateChildren(hashMap);
                holder.TVNamaPesertaFinal2.setText(ALNamaPemenangB2Peserta1.get(position));
//                holder.ETSkorPesertaFinal2.setText("1");
                holder.ETSkorPesertaFinal2.setText(ALSemuaSkorB4.get(position).toString());
            }
            else if(ALSkorPemenangB3Peserta1.get(position)<ALSkorPemenangB3Peserta2.get(position)){
                FirebaseDatabase.getInstance().getReference("Admin/"+ids+"/Peserta").child(ALKeyPemenangB3Peserta1.get(position)).child("History").child("babak5").setValue(null);
                FirebaseDatabase.getInstance().getReference("Admin/"+ids+"/Peserta").child(ALKeyPemenangB3Peserta1.get(position)).child("History").child("babak4").setValue(null);
                FirebaseDatabase.getInstance().getReference("Admin/"+ids+"/Peserta").child(ALKeyPemenangB3Peserta1.get(position)).child("History").child("pemenangB3").setValue(null);
                HashMap hashMap = new HashMap();
                hashMap.put("babak4", ALSemuaSkorB4.get(position));
                hashMap.put("pemenangB3", ALNamaPemenangB2Peserta2.get(position));
                DBRef.child(ALKeyPemenangB3Peserta2.get(position)).child("History").updateChildren(hashMap);
//                DBRef.child(ALKeyPemenangB2Peserta2.get(position)).child("History").updateChildren(hashMap);
                holder.TVNamaPesertaFinal2.setText(ALNamaPemenangB2Peserta2.get(position));
//                holder.ETSkorPesertaFinal2.setText("2");
                holder.ETSkorPesertaFinal2.setText(ALSemuaSkorB4.get(position).toString());
            }
        }

        holder.ETSkorPesertaFinal2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    String SkorFinals = holder.ETSkorPesertaFinal2.getText().toString();
                    if(!SkorFinals.equals("")) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("babak4", Integer.parseInt(SkorFinals));
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
        n = ALNama.size();
        if(n%2==0){
            n = n/4;
        }
        else{
            n = (n/4)+1;
        }
        return n;
    }
}
