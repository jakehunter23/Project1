package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactFileRecAdapter extends RecyclerView.Adapter<ContactFileRecAdapter.ConFileView> {
    @NonNull
    @Override
    public ConFileView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_files_rec_item,parent,false);
        return new ConFileView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConFileView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ConFileView extends RecyclerView.ViewHolder {

        public ConFileView(@NonNull View itemView) {
            super(itemView);
        }
    }
}
