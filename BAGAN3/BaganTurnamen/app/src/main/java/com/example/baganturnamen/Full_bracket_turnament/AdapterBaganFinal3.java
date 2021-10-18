package com.example.baganturnamen.Full_bracket_turnament;

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
import com.google.firebase.database.core.Context;

import java.util.ArrayList;

public class AdapterBaganFinal3 extends RecyclerView.Adapter<AdapterBaganFinal3.BaganFinal3ViewHolder>{
    Context context;
    ArrayList<Integer> ALSkorPemenangB3Peserta1;
    ArrayList<Integer> ALSkorPemenangB3Peserta2;

    public AdapterBaganFinal3(Context context,
                              ArrayList<Integer> ALSkorPemenangB3Peserta1, ArrayList<Integer> ALSkorPemenangB3Peserta2){
        this.context = context;
        this.ALSkorPemenangB3Peserta1 = ALSkorPemenangB3Peserta1;
        this.ALSkorPemenangB3Peserta2 = ALSkorPemenangB3Peserta2;
    }

    public static class BaganFinal3ViewHolder extends RecyclerView.ViewHolder{
        CardView CVPesertaFinal2;
        TextView TVNamaPesertaFinal2;
        EditText ETSkorPesertaFinal2;

        public BaganFinal3ViewHolder(@NonNull View itemview){
            super(itemview);
            CVPesertaFinal2 = itemView.findViewById(R.id.idCVPesertaFinal2);
            TVNamaPesertaFinal2 = itemView.findViewById(R.id.idTVNamaPesertaFinal2);
            ETSkorPesertaFinal2 = itemView.findViewById(R.id.idETSkorPesertaFinal2);
        }
    }

    @NonNull
    @Override
    public AdapterBaganFinal3.BaganFinal3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_baganfinal2, parent, false);
        return new AdapterBaganFinal3.BaganFinal3ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaganFinal3ViewHolder holder, int position) {
//        Log.d("TAG", "onBindViewHolder: "+ALSkorPemenangB3Peserta1);
//        Log.d("TAG", "onBindViewHolder: "+ALSkorPemenangB3Peserta2);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
