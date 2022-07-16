package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ActurialFileRecAdapter extends RecyclerView.Adapter<ActurialFileRecAdapter.ActFileView> {
    @NonNull
    @Override
    public ActFileView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.acturial_files_rec_item,parent,false);
        return new ActFileView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActFileView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ActFileView extends RecyclerView.ViewHolder {

        public ActFileView(@NonNull View itemView) {
            super(itemView);
        }
    }
}
