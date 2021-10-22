package com.example.baganturnamen.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baganturnamen.Peserta;
import com.example.baganturnamen.R;
import com.example.baganturnamen.edit_peserta;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;
//import com.google.firebase.database.core.view.View;

import java.util.ArrayList;

public class AdapterDaftarPeserta extends RecyclerView.Adapter<AdapterDaftarPeserta.DaftarPesertaViewHolder>{
    Context context;
    ArrayList<Peserta> ALPeserta;
    ArrayList<Integer> ALINo = new ArrayList<Integer>();
    ArrayList<String> ALKey = new ArrayList<String>();
    FirebaseUser firebaseUser;
    String UID;

    public AdapterDaftarPeserta(Context context, ArrayList<Peserta> ALPeserta, ArrayList<String> ALKey){
        this.context = context;
        this.ALPeserta = ALPeserta;
        this.ALKey = ALKey;
    }

    public static class DaftarPesertaViewHolder extends RecyclerView.ViewHolder{
        TextView TVNo, TVNama, TVUmur, TVClub, TVUnggulan;
        ImageView IVHapus, IVEdit;

        public DaftarPesertaViewHolder(@NonNull View itemView){
            super(itemView);
            TVNo = itemView.findViewById(R.id.idTVNo);
            TVNama = itemView.findViewById(R.id.idTVNama);
            TVUmur = itemView.findViewById(R.id.idTVUmur);
            TVClub = itemView.findViewById(R.id.idTVClub);
            TVUnggulan = itemView.findViewById(R.id.idTVUnggulan);
            IVHapus = itemView.findViewById(R.id.idIVHapus);
            IVEdit = itemView.findViewById(R.id.idIVEdit);
        }
    }

    @NonNull
    @Override
    public DaftarPesertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View VItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_rv_daftar_peserta, parent, false);
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
        holder.TVClub.setText(peserta.getClub());
        holder.TVUnggulan.setText(peserta.getUnggulan());
//        holder.TVUnggulan.setText(ALPeserta.get(position).getUnggulan());
//        Log.d("TAG", "ALINo1: "+ALINo);

        ALINo.clear();
        for(Integer no = 1; no<=ALPeserta.size(); no++){
            ALINo.add(no);
        }
        holder.TVNo.setText(Integer.toString(ALINo.get(position)));
//        Log.d("TAG", "ALINo: "+ALINo);

        holder.IVHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if(firebaseUser!=null) {

                    UID = firebaseUser.getUid();
                }


                final DatabaseReference DBRef = FirebaseDatabase.getInstance().getReference("Admin/"+UID+"/Peserta");
                final String SNama = peserta.getNama();
//                Log.d("TAG", "klik hapus"+SNama+ALPeserta);
                DBRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ALPeserta.clear();
//                        Log.d("TAG", "hapus: "+SNama+ALPeserta);
                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                            String nama = dataSnapshot.getValue(Peserta.class).getNama();

                            if(nama.equals(SNama)){
                                dataSnapshot.getRef().removeValue();
                            }
                        }
//                        Log.d("TAG", "sudah hapus: "+SNama+ALPeserta);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        holder.IVEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "klik edit");
                Intent intent = new Intent(view.getContext(), edit_peserta.class);
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if(firebaseUser!=null) {

                    UID = firebaseUser.getUid();
                }


                final DatabaseReference DBRef = FirebaseDatabase.getInstance().getReference("Admin/"+UID+"/Peserta");
                final String SKey = ALKey.get(position);
                final String SNama = peserta.getNama();
                final String SClub = peserta.getClub();
                final Integer IUmur = peserta.getUmur();
                final String SUnggulan = peserta.getUnggulan();

                intent.putExtra("key", SKey);
                intent.putExtra("nama", SNama);
                intent.putExtra("club", SClub);
                intent.putExtra("umur", IUmur);
                intent.putExtra("unggulan", SUnggulan);

                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ALPeserta.size();
    }
}
