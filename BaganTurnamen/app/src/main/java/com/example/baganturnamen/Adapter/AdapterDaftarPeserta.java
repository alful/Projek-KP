package com.example.baganturnamen.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baganturnamen.Peserta;
import com.example.baganturnamen.R;
import com.google.firebase.database.core.Context;
//import com.google.firebase.database.core.view.View;

import java.util.ArrayList;

public class AdapterDaftarPeserta extends RecyclerView.Adapter<AdapterDaftarPeserta.DaftarPesertaViewHolder>{
    Context context;
    ArrayList<Peserta> ALPeserta;
    ArrayList<String> ALSNama;
    ArrayList<Integer> ALIUmur;

//    Integer no = 1;
    ArrayList<Integer> ALINo = new ArrayList<Integer>();

    public AdapterDaftarPeserta(Context context, ArrayList<Peserta> ALPeserta){
        this.context = context;
        this.ALPeserta = ALPeserta;
    }

    public static class DaftarPesertaViewHolder extends RecyclerView.ViewHolder{
        TextView TVNo, TVNama, TVUmur, TVUnggulan;

        public DaftarPesertaViewHolder(@NonNull View itemView){
            super(itemView);
            TVNo = itemView.findViewById(R.id.idTVNo);
            TVNama = itemView.findViewById(R.id.idTVNama);
            TVUmur = itemView.findViewById(R.id.idTVUmur);
            TVUnggulan = itemView.findViewById(R.id.idTVUnggulan);
        }
    }

    @NonNull
    @Override
    public DaftarPesertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View VItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_rv_daftar_peserta, parent, false);
//        View VItem = LayoutInflater.from(context).inflate(R.layout.list_item_rv_daftar_peserta, parent, false);
        return new DaftarPesertaViewHolder(VItem);
    }
    
    @Override
    public void onBindViewHolder(@NonNull DaftarPesertaViewHolder holder, int position){
        Peserta peserta = ALPeserta.get(position);
//        holder.TVNo.setText(Integer.toString(no));
        holder.TVNama.setText(peserta.getNama());
//        holder.TVNama.setText(ALPeserta.get(position).getNama());
        holder.TVUmur.setText(Integer.toString(peserta.getUmur()));
//        holder.TVUmur.setText(Integer.toString(ALPeserta.get(position).getUmur()));
        holder.TVUnggulan.setText(peserta.getUnggulan());
//        holder.TVUnggulan.setText(ALPeserta.get(position).getUnggulan());
        Log.d("TAG", "ALINo1: "+ALINo);

        ALINo.clear();
        for(Integer no = 1; no<=ALPeserta.size(); no++){
            ALINo.add(no);
        }
        holder.TVNo.setText(Integer.toString(ALINo.get(position)));
        Log.d("TAG", "ALINo: "+ALINo);
    }

    @Override
    public int getItemCount() {
        return ALPeserta.size();
    }
}
