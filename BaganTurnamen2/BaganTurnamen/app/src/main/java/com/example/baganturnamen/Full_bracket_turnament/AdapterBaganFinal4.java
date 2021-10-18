package com.example.baganturnamen.Full_bracket_turnament;

import android.util.Log;
import android.view.LayoutInflater;
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
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterBaganFinal4 extends RecyclerView.Adapter<AdapterBaganFinal4.BaganFinal4ViewHolder> {
    Context context;
    ArrayList<Integer> ALSemuaSkorB5;
    ArrayList<String> ALKeySemuaPemenangB4;
    ArrayList<Integer> ALSkorPemenangB4Peserta1;
    ArrayList<Integer> ALSkorPemenangB4Peserta2;
    ArrayList<String> ALKeyPemenangB4Peserta1;
    ArrayList<String> ALKeyPemenangB4Peserta2;
    ArrayList<String> ALNamaPemenangB3Peserta1;
    ArrayList<String> ALNamaPemenangB3Peserta2;

    DatabaseReference DBRef;

    public AdapterBaganFinal4(Context context, ArrayList<Integer> ALSemuaSkorB5,
                              ArrayList<String> ALKeySemuaPemenangB4,
                              ArrayList<Integer> ALSkorPemenangB4Peserta1, ArrayList<Integer> ALSkorPemenangB4Peserta2,
                              ArrayList<String> ALKeyPemenangB4Peserta1, ArrayList<String> ALKeyPemenangB4Peserta2,
                              ArrayList<String> ALNamaPemenangB3Peserta1, ArrayList<String> ALNamaPemenangB3Peserta2) {
        this.context = context;
        this.ALSemuaSkorB5 = ALSemuaSkorB5;
        this.ALKeySemuaPemenangB4 = ALKeySemuaPemenangB4;
        this.ALSkorPemenangB4Peserta1 = ALSkorPemenangB4Peserta1;
        this.ALSkorPemenangB4Peserta2 = ALSkorPemenangB4Peserta2;
        this.ALKeyPemenangB4Peserta1 = ALKeyPemenangB4Peserta1;
        this.ALKeyPemenangB4Peserta2 = ALKeyPemenangB4Peserta2;
        this.ALNamaPemenangB3Peserta1 = ALNamaPemenangB3Peserta1;
        this.ALNamaPemenangB3Peserta2 = ALNamaPemenangB3Peserta2;
    }

    public static class BaganFinal4ViewHolder extends RecyclerView.ViewHolder{
        CardView CVPesertaFinal3;
        TextView TVNamaPesertaFinal3;
        EditText ETSkorPesertaFinal3;

        public BaganFinal4ViewHolder(@NonNull View itemView){
            super(itemView);
            CVPesertaFinal3 = itemView.findViewById(R.id.idCVPesertaFinal3);
            TVNamaPesertaFinal3 = itemView.findViewById(R.id.idTVNamaPesertaFinal3);
            ETSkorPesertaFinal3 = itemView.findViewById(R.id.idETSkorPesertaFinal3);
        }
    }

    @NonNull
    @Override
    public BaganFinal4ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_baganfinal3, parent, false);
        return new AdapterBaganFinal4.BaganFinal4ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaganFinal4ViewHolder holder, int position) {
        DBRef = FirebaseDatabase.getInstance().getReference("Peserta2");

        if(ALSemuaSkorB5.size()==0&&ALKeyPemenangB4Peserta1.size()!=0&&ALKeyPemenangB4Peserta2.size()!=0) {
            HashMap hashMap = new HashMap();
            hashMap.put("babak5", 0);
            for(int x=0; x<ALKeySemuaPemenangB4.size(); x++){
                DBRef.child(ALKeySemuaPemenangB4.get(x)).child("History").updateChildren(hashMap);
            }
        }
        else if(ALSkorPemenangB4Peserta1.size()!=0&&ALSkorPemenangB4Peserta2.size()!=0){
//            holder.TVNamaPesertaFinal2.setText("null1");
//            holder.ETSkorPesertaFinal2.setText("null1");
            if(ALSkorPemenangB4Peserta1.get(position)==ALSkorPemenangB4Peserta2.get(position)){
                holder.TVNamaPesertaFinal3.setText("null");
                holder.ETSkorPesertaFinal3.setText("null");
            }
            else if(ALSkorPemenangB4Peserta1.get(position)>ALSkorPemenangB4Peserta2.get(position)){
                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB4Peserta2.get(position)).child("History").child("babak5").setValue(null);
                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB4Peserta2.get(position)).child("History").child("pemenangB4").setValue(null);
                HashMap hashMap = new HashMap();
                hashMap.put("babak5", ALSemuaSkorB5.get(position));
                hashMap.put("pemenangB4", ALNamaPemenangB3Peserta1.get(position));
                DBRef.child(ALKeyPemenangB4Peserta1.get(position)).child("History").updateChildren(hashMap);
//                DBRef.child(ALKeyPemenangB2Peserta1.get(position)).child("History").updateChildren(hashMap);
                holder.TVNamaPesertaFinal3.setText(ALNamaPemenangB3Peserta1.get(position));
//                holder.ETSkorPesertaFinal2.setText("1");
                holder.ETSkorPesertaFinal3.setText(ALSemuaSkorB5.get(position).toString());
            }
            else if(ALSkorPemenangB4Peserta1.get(position)<ALSkorPemenangB4Peserta2.get(position)){
                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB4Peserta1.get(position)).child("History").child("babak5").setValue(null);
                FirebaseDatabase.getInstance().getReference("Peserta2").child(ALKeyPemenangB4Peserta1.get(position)).child("History").child("pemenangB4").setValue(null);
                HashMap hashMap = new HashMap();
                hashMap.put("babak5", ALSemuaSkorB5.get(position));
                hashMap.put("pemenangB3", ALNamaPemenangB3Peserta2.get(position));
                DBRef.child(ALKeyPemenangB4Peserta2.get(position)).child("History").updateChildren(hashMap);
//                DBRef.child(ALKeyPemenangB2Peserta2.get(position)).child("History").updateChildren(hashMap);
                holder.TVNamaPesertaFinal3.setText(ALNamaPemenangB3Peserta2.get(position));
//                holder.ETSkorPesertaFinal2.setText("2");
                holder.ETSkorPesertaFinal3.setText(ALSemuaSkorB5.get(position).toString());
            }
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
