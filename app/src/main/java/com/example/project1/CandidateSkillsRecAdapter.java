package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CandidateSkillsRecAdapter extends RecyclerView.Adapter<CandidateSkillsRecAdapter.skillViewHolder> {

    String [] SkillName;
    float [] ratings;

    public CandidateSkillsRecAdapter(String [] SkillName, float [] ratings){
        this.SkillName = SkillName;
        this.ratings =ratings;

    }
    @NonNull
    @Override
    public skillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater skillInflater = LayoutInflater.from(parent.getContext());
        View indiView = skillInflater.inflate(R.layout.candidate_skill_rec_item,parent,false);
        return new CandidateSkillsRecAdapter.skillViewHolder(indiView);
    }

    @Override
    public void onBindViewHolder(@NonNull skillViewHolder holder, int position) {
        holder.skillName.setText(SkillName[position]);
        holder.ratingBar.setRating(ratings[position]);


    }

    @Override
    public int getItemCount() {
        return SkillName.length;
    }

    class skillViewHolder extends RecyclerView.ViewHolder {

        TextView skillName;
        RatingBar ratingBar;

        public skillViewHolder(@NonNull View itemView) {
            super(itemView);

            skillName =itemView.findViewById(R.id.cand_skill_name);
            ratingBar = itemView.findViewById(R.id.ratingBar);

        }
    }
}
