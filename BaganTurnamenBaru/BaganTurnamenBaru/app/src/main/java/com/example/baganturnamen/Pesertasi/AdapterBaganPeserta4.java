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

import com.example.baganturnamen.Full_bracket_turnament.AdapterBaganFinal4;
import com.example.baganturnamen.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterBaganPeserta4 extends RecyclerView.Adapter<AdapterBaganPeserta4.BaganPeserta4ViewHolder>{
    Context context;
    ArrayList<Integer> ALSemuaSkorB5;
    ArrayList<String> ALKeySemuaPemenangB4;
    ArrayList<Integer> ALSkorPemenangB4Peserta1;
    ArrayList<Integer> ALSkorPemenangB4Peserta2;
    ArrayList<String> ALKeyPemenangB4Peserta1;
    ArrayList<String> ALKeyPemenangB4Peserta2;
    ArrayList<String> ALNamaPemenangB3Peserta1;
    ArrayList<String> ALNamaPemenangB3Peserta2;

    ArrayList<String> ALNama;
    int n=0;

    DatabaseReference DBRef;
    String ids;
    public AdapterBaganPeserta4(Context context, ArrayList<Integer> ALSemuaSkorB5,
                              ArrayList<String> ALKeySemuaPemenangB4,
                              ArrayList<Integer> ALSkorPemenangB4Peserta1, ArrayList<Integer> ALSkorPemenangB4Peserta2,
                              ArrayList<String> ALKeyPemenangB4Peserta1, ArrayList<String> ALKeyPemenangB4Peserta2,
                              ArrayList<String> ALNamaPemenangB3Peserta1, ArrayList<String> ALNamaPemenangB3Peserta2, String ids,ArrayList<String> ALNama) {
        this.context = context;
        this.ALSemuaSkorB5 = ALSemuaSkorB5;
        this.ALKeySemuaPemenangB4 = ALKeySemuaPemenangB4;
        this.ALSkorPemenangB4Peserta1 = ALSkorPemenangB4Peserta1;
        this.ALSkorPemenangB4Peserta2 = ALSkorPemenangB4Peserta2;
        this.ALKeyPemenangB4Peserta1 = ALKeyPemenangB4Peserta1;
        this.ALKeyPemenangB4Peserta2 = ALKeyPemenangB4Peserta2;
        this.ALNamaPemenangB3Peserta1 = ALNamaPemenangB3Peserta1;
        this.ALNamaPemenangB3Peserta2 = ALNamaPemenangB3Peserta2;
        this.ids=ids;
        this.ALNama=ALNama;
    }

    public static class BaganPeserta4ViewHolder extends RecyclerView.ViewHolder {
        CardView CVPesertaFinal3;
        TextView TVNamaPesertaFinal3;
        EditText ETSkorPesertaFinal3;

        public BaganPeserta4ViewHolder(@NonNull View itemView) {
            super(itemView);
            CVPesertaFinal3 = itemView.findViewById(R.id.idCVPesertaFinal3);
            TVNamaPesertaFinal3 = itemView.findViewById(R.id.idTVNamaPesertaFinal3);
            ETSkorPesertaFinal3 = itemView.findViewById(R.id.idETSkorPesertaFinal3);

        }
    }
    @NonNull
    @Override
    public AdapterBaganPeserta4.BaganPeserta4ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_baganfinal3, parent, false);
        return new AdapterBaganPeserta4.BaganPeserta4ViewHolder(view);
    }


        @Override
    public void onBindViewHolder(@NonNull AdapterBaganPeserta4.BaganPeserta4ViewHolder holder, int position) {
            DBRef = FirebaseDatabase.getInstance().getReference("Admin/"+ids+"/Peserta");

            holder.ETSkorPesertaFinal3.setInputType(InputType.TYPE_NULL);
            holder.ETSkorPesertaFinal3.setFocusable(false);



            if(ALSemuaSkorB5.size()==0&&ALKeyPemenangB4Peserta1.size()!=0&&ALKeyPemenangB4Peserta2.size()!=0) {
                HashMap hashMap = new HashMap();
                hashMap.put("babak5", 0);
                for(int x=0; x<ALKeySemuaPemenangB4.size(); x++){
                    DBRef.child(ALKeySemuaPemenangB4.get(x)).child("History").updateChildren(hashMap);
                }
            }
            else if(ALSkorPemenangB4Peserta1.size()!=0&&ALSkorPemenangB4Peserta2.size()!=0){
//        if(ALSkorPemenangB4Peserta1.size()!=0&&ALSkorPemenangB4Peserta2.size()!=0){
//            holder.TVNamaPesertaFinal2.setText("null1");
//            holder.ETSkorPesertaFinal2.setText("null1");
                if(ALSkorPemenangB4Peserta1.get(position)==ALSkorPemenangB4Peserta2.get(position)){
                    holder.TVNamaPesertaFinal3.setText("");
                    holder.ETSkorPesertaFinal3.setText("");
                }
                else if(ALSkorPemenangB4Peserta1.get(position)>ALSkorPemenangB4Peserta2.get(position)){
//                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB4Peserta2.get(position)).child("History").child("babak5").setValue(null);
//                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB4Peserta2.get(position)).child("History").child("pemenangB4").setValue(null);
//                HashMap hashMap = new HashMap();
//                hashMap.put("babak5", ALSemuaSkorB5.get(position));
//                hashMap.put("pemenangB4", ALNamaPemenangB3Peserta1.get(position));
//                DBRef.child(ALKeyPemenangB4Peserta1.get(position)).child("History").updateChildren(hashMap);
//                DBRef.child(ALKeyPemenangB2Peserta1.get(position)).child("History").updateChildren(hashMap);
                    holder.TVNamaPesertaFinal3.setText(ALNamaPemenangB3Peserta1.get(position));
//                holder.ETSkorPesertaFinal2.setText("1");
                    holder.ETSkorPesertaFinal3.setText(ALSemuaSkorB5.get(position).toString());
                }
                else if(ALSkorPemenangB4Peserta1.get(position)<ALSkorPemenangB4Peserta2.get(position)){
//                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB4Peserta1.get(position)).child("History").child("babak5").setValue(null);
//                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB4Peserta1.get(position)).child("History").child("pemenangB4").setValue(null);
//                HashMap hashMap = new HashMap();
//                hashMap.put("babak5", ALSemuaSkorB5.get(position));
//                hashMap.put("pemenangB4", ALNamaPemenangB3Peserta2.get(position));
//                DBRef.child(ALKeyPemenangB4Peserta2.get(position)).child("History").updateChildren(hashMap);
//                DBRef.child(ALKeyPemenangB2Peserta2.get(position)).child("History").updateChildren(hashMap);
                    holder.TVNamaPesertaFinal3.setText(ALNamaPemenangB3Peserta2.get(position));
//                holder.ETSkorPesertaFinal2.setText("2");
                    holder.ETSkorPesertaFinal3.setText(ALSemuaSkorB5.get(position).toString());
                }
            }

            holder.ETSkorPesertaFinal3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b){
                        String SkorFinals = holder.ETSkorPesertaFinal3.getText().toString();
                        if(!SkorFinals.equals("")) {
                            HashMap hashMap = new HashMap();
                            hashMap.put("babak5", Integer.parseInt(SkorFinals));
                            if(ALKeyPemenangB4Peserta1.size()!=0&&ALSkorPemenangB4Peserta1.get(position)>ALSkorPemenangB4Peserta2.get(position)){
                                DBRef.child(ALKeyPemenangB4Peserta1.get(position)).child("History").updateChildren(hashMap);
                            }
                            else if(ALKeyPemenangB4Peserta2.size()!=0&&ALSkorPemenangB4Peserta1.get(position)<ALSkorPemenangB4Peserta2.get(position)){
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
    public int getItemCount(){
        n = ALNama.size();
        if(n%2==0){
            n = n/8;
        }
        else{
            n = (n/8)+1;
        }
        return n;
    }
}
