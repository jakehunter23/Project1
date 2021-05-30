package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyFilesRecAdapter extends RecyclerView.Adapter<MyFilesRecAdapter.MyFilesViewHolder> {
    @NonNull
    @Override
    public MyFilesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.my_files_list,parent,false);
        return new MyFilesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFilesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }


    class MyFilesViewHolder extends RecyclerView.ViewHolder {


        public MyFilesViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
