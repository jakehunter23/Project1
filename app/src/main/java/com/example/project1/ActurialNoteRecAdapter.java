package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ActurialNoteRecAdapter extends RecyclerView.Adapter<ActurialNoteRecAdapter.ActNoteView> {
    @NonNull
    @Override
    public ActNoteView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.acturial_notes_rec_item,parent,false);
        return new ActNoteView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActNoteView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ActNoteView extends RecyclerView.ViewHolder {

        public ActNoteView(@NonNull View itemView) {
            super(itemView);
        }
    }
}
