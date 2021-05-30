package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactNoteRecAdapter extends RecyclerView.Adapter<ContactNoteRecAdapter.ConNoteView> {
    @NonNull
    @Override
    public ConNoteView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_notes_rec_item,parent,false);
        return new ConNoteView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConNoteView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ConNoteView extends RecyclerView.ViewHolder {


        public ConNoteView(@NonNull View itemView) {
            super(itemView);
        }
    }
}
