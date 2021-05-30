package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CandidateAchieveRecAdapter extends RecyclerView.Adapter<CandidateAchieveRecAdapter.achieveViewHolder> {

    String [] title;
    String [] tdi;
    String [] tdo;

    public CandidateAchieveRecAdapter(String [] title, String [] tdi, String [] tdo){
        this.title=title;
        this.tdi=tdi;
        this.tdo=tdo;

    }
    @NonNull
    @Override
    public achieveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater achInflater = LayoutInflater.from(parent.getContext());
        View indiView = achInflater.inflate(R.layout.candidate_achievement_rec_item,parent,false);
        return new CandidateAchieveRecAdapter.achieveViewHolder(indiView);
    }

    @Override
    public void onBindViewHolder(@NonNull achieveViewHolder holder, int position) {
        holder.title.setText(title[position]);
        holder.tdi.setText(tdi[position]);
        holder.tdo.setText(tdo[position]);

    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    class achieveViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView tdi;
        TextView tdo;

        public achieveViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.cand_textView269);
            tdi=itemView.findViewById(R.id.cand_textView270);
            tdo=itemView.findViewById(R.id.cand_textView272);
        }
    }
}
