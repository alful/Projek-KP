package com.ventura.emilp.tournamentbrackets.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ventura.emilp.tournamentbrackets.Fragment.BracketsColomnFragment;
import com.ventura.emilp.tournamentbrackets.Fragment.BracketsFragment;
import com.ventura.emilp.tournamentbrackets.Peserta;
import com.ventura.emilp.tournamentbrackets.R;
import com.ventura.emilp.tournamentbrackets.model.MatchData;
import com.ventura.emilp.tournamentbrackets.viewholder.BracketsCellViewHolder;

import java.util.ArrayList;

/**
 * Created by Emil on 21/10/17.
 */

public class BracketsCellAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private BracketsColomnFragment fragment;
    private Context context;
    private ArrayList<MatchData> list;
    private boolean handler;

    public BracketsCellAdapter(BracketsColomnFragment bracketsColomnFragment, Context context, ArrayList<MatchData> list) {

        this.fragment = bracketsColomnFragment;
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_cell_brackets, parent, false);
        return new BracketsCellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BracketsCellViewHolder viewHolder = null;
        if (holder instanceof BracketsCellViewHolder){
            viewHolder = (BracketsCellViewHolder) holder;
            setFields(viewHolder, position);
        }
    }

    private void setFields(final BracketsCellViewHolder viewHolder, final int position) {
        handler = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewHolder.setAnimation(list.get(position).getHeight());
            }
        }, 100);

        viewHolder.getTeamOneName().setText(list.get(position).getCompetitorOne().getName());
        viewHolder.getTeamTwoName().setText(list.get(position).getCompetitorTwo().getName());
        viewHolder.getTeamOneScore().setText(list.get(position).getCompetitorOne().getScore());
        viewHolder.getTeamTwoScore().setText(list.get(position).getCompetitorTwo().getScore());

        viewHolder.getTeamOneName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Peserta peserta = new Peserta();
                Toast.makeText(context, ""+list.get(position).getCompetitorOne().getName(), Toast.LENGTH_SHORT).show();
                peserta.setSNama(list.get(position).getCompetitorOne().getName());
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                Log.d("TAG", "setData1: "+peserta.getSNama());

            }
        });

        viewHolder.getTeamTwoName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+list.get(position).getCompetitorTwo().getName(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.getTeamOneScore().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+list.get(position).getCompetitorOne().getScore(), Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.getTeamTwoScore().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+list.get(position).getCompetitorTwo().getScore(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public void setList(ArrayList<MatchData> colomnList) {
        this.list = colomnList;
        notifyDataSetChanged();
    }
}
