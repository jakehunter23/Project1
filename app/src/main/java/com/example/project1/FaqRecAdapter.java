package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FaqRecAdapter extends RecyclerView.Adapter<FaqRecAdapter.FaqRecViewHolder> {
    @NonNull
    @Override
    public FaqRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.faq_recent_rec_item,parent,false);
        return new FaqRecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaqRecViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class FaqRecViewHolder extends RecyclerView.ViewHolder {

        public FaqRecViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
