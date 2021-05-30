package com.example.project1;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClientGlanceRec1Adapter extends RecyclerView.Adapter<ClientGlanceRec1Adapter.ItemHolder> {
    int [] icons;
    String [] title;
    String [] number;

    public ClientGlanceRec1Adapter(int [] icons, String [] title, String [] number){
        this.icons=icons;
        this.title=title;
        this.number=number;

    }
    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.client_glance_rec1_item,parent,false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        holder.icon.setImageResource(icons[position]);
        holder.title.setText(title[position]);
        holder.number.setText(number[position]);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;
        TextView number;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.clirec1_icon);
            title=itemView.findViewById(R.id.clirec1_title);
            number=itemView.findViewById(R.id.clirec1_number);
        }
    }
}
