package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CandidateFilesRecAdapter extends RecyclerView.Adapter<CandidateFilesRecAdapter.CandFileViewHolder> {
    @NonNull
    @Override
    public CandFileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.candidate_files_rec_item,parent,false);
        return new CandFileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CandFileViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class CandFileViewHolder extends RecyclerView.ViewHolder {

        public CandFileViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
