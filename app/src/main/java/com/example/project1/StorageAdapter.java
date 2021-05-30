package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

public class StorageAdapter extends RecyclerView.Adapter<StorageViewHolder> {
    String [] Type_list;
    String size="Some KB";
    String number="Some No.";

    public StorageAdapter(String [] Type_list){
        this.Type_list=Type_list;

    }

    @Override
    public StorageViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater storageInflater = LayoutInflater.from(parent.getContext());
        View storage_view=storageInflater.inflate(R.layout.storage_list,parent,false);
        return new StorageViewHolder(storage_view);
    }

    @Override
    public void onBindViewHolder(StorageViewHolder holder, int position) {
        String Type_list_name = Type_list[position];
        holder.field_name.setText(Type_list_name);
        holder.field_size.setText(size);
        holder.field_number.setText(number);



    }

    @Override
    public int getItemCount() {
        return Type_list.length;
    }
}

class StorageViewHolder extends RecyclerView.ViewHolder {
    TextView field_name;
    TextView field_size;
    TextView field_number;

    public StorageViewHolder( View itemView) {
        super(itemView);
        field_name=itemView.findViewById(R.id.TypeName);
        field_size=itemView.findViewById(R.id.TypeSize);
        field_number=itemView.findViewById(R.id.TypeNumber);
    }
}
