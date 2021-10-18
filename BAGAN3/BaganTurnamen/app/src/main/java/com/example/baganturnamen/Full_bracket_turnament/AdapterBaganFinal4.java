package com.example.baganturnamen.Full_bracket_turnament;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baganturnamen.R;
import com.google.firebase.database.core.Context;
import android.view.View;

public class AdapterBaganFinal4 extends RecyclerView.Adapter<AdapterBaganFinal4.BaganFinal4ViewHolder> {
    Context context;

    public AdapterBaganFinal4(Context context){
        this.context = context;

    }

    public static class BaganFinal4ViewHolder extends RecyclerView.ViewHolder{
        CardView CVPesertaFinal3;
        TextView TVNamaPesertaFinal3;

        public BaganFinal4ViewHolder(@NonNull View itemView){
            super(itemView);
            CVPesertaFinal3 = itemView.findViewById(R.id.idCVPesertaFinal3);
            TVNamaPesertaFinal3 = itemView.findViewById(R.id.idTVNamaPesertaFinal3);
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

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
