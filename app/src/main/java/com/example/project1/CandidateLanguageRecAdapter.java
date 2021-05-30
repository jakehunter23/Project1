package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CandidateLanguageRecAdapter extends RecyclerView.Adapter<CandidateLanguageRecAdapter.langViewHolder> {


    String [] language;
    String [] fluency;

    public CandidateLanguageRecAdapter( String [] language, String [] fluency){
        this.language=language;
        this.fluency=fluency;

    }
    @NonNull
    @Override
    public langViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater langInflater = LayoutInflater.from(parent.getContext());
        View indiView = langInflater.inflate(R.layout.candidate_language_rec_item,parent,false);
        return new CandidateLanguageRecAdapter.langViewHolder(indiView);
    }

    @Override
    public void onBindViewHolder(@NonNull langViewHolder holder, int position) {
        holder.language.setText(language[position]);
        holder.fluency.setText(fluency[position]);

    }

    @Override
    public int getItemCount() {
        return language.length;
    }

    class langViewHolder extends RecyclerView.ViewHolder {

        TextView language;
        TextView fluency;

        public langViewHolder(@NonNull View itemView) {
            super(itemView);

            language=itemView.findViewById(R.id.cand_textView257);
            fluency=itemView.findViewById(R.id.cand_textView258);
        }
    }
}
