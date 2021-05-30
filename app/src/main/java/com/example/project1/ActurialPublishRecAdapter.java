package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ActurialPublishRecAdapter extends RecyclerView.Adapter<ActurialPublishRecAdapter.ActPubView> {
    @NonNull
    @Override
    public ActPubView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.acturial_publish_rec_item,parent,false);
        return new ActPubView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActPubView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class ActPubView extends RecyclerView.ViewHolder {

        public ActPubView(@NonNull View itemView) {
            super(itemView);
        }
    }
}
