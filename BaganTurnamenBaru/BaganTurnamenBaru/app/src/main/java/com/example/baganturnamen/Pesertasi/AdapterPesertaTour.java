package com.example.baganturnamen.Pesertasi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baganturnamen.Adapter.AdapterDaftarPeserta;
import com.example.baganturnamen.LogSign.admin;
import com.example.baganturnamen.Peserta;
import com.example.baganturnamen.R;

import java.util.ArrayList;


public class AdapterPesertaTour extends RecyclerView.Adapter<AdapterPesertaTour.DaftarPesertaTourViewHolder>{
    private Context context;
    ArrayList<admin> admins;
    ArrayList<Integer> ALINo = new ArrayList<Integer>();
    ArrayList<String> ALKey = new ArrayList<String>();

    public AdapterPesertaTour(Context context, ArrayList<admin> admins, ArrayList<String> ALKey) {
        this.context = context;
        this.admins = admins;
        this.ALKey = ALKey;
    }


    public static class DaftarPesertaTourViewHolder extends RecyclerView.ViewHolder{
        TextView no,admin;
        CardView adm;

        public DaftarPesertaTourViewHolder(@NonNull View itemView) {
            super(itemView);
            no = itemView.findViewById(R.id.nosa);
            admin = itemView.findViewById(R.id.Admn);
            adm=itemView.findViewById(R.id.tournam);

        }
    }

    @NonNull
    @Override
    public DaftarPesertaTourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View VItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.daftar_tours, parent, false);
        return new AdapterPesertaTour.DaftarPesertaTourViewHolder(VItem);
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarPesertaTourViewHolder holder, int position) {
        admin Admin = admins.get(position);
        holder.admin.setText(Admin.getNama());

        ALINo.clear();
        for(Integer no = 1; no<=admins.size(); no++){
            ALINo.add(no);
        }
        holder.no.setText(Integer.toString(ALINo.get(position)));

        holder.adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, BracketPeserta.class);
                String key=ALKey.get(position);
                intent.putExtra("adminkey",key);
                context.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return admins.size();
    }


}