package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CandidateNoteRecAdapter extends RecyclerView.Adapter<CandidateNoteRecAdapter.CandNoteViewHolder> {
    @NonNull
    @Override
    public CandNoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.candidate_notes_rec_item,parent,false);
        return new CandNoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CandNoteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class CandNoteViewHolder extends RecyclerView.ViewHolder {

        public CandNoteViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
