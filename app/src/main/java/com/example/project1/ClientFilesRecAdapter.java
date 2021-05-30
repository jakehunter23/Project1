package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClientFilesRecAdapter extends RecyclerView.Adapter<ClientFilesRecAdapter.FileItemHolder> {

    String [] filename;
    String [] fileSize;
    String  verify;
    String [] person_name;
    String [] date;

    public ClientFilesRecAdapter(String [] filename, String [] fileSize, String verify, String [] person_name, String [] date){
        this.filename=filename;
        this.fileSize=fileSize;
        this.verify=verify;
        this.person_name=person_name;
        this.date=date;
    }

    @NonNull
    @Override
    public FileItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater fileInflator = LayoutInflater.from(parent.getContext());
        View indiView = fileInflator.inflate(R.layout.client_files_rec_item,parent,false);
        return new ClientFilesRecAdapter.FileItemHolder(indiView);
    }

    @Override
    public void onBindViewHolder(@NonNull FileItemHolder holder, int position) {
        holder.name.setText(filename[position]);
        holder.size.setText(fileSize[position]);
        holder.verify.setText(verify);
        holder.s_name.setText(person_name[position]);
        holder.date.setText(date[position]);

    }

    @Override
    public int getItemCount() {
        return filename.length;
    }

    class FileItemHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView size;
        TextView verify;
        TextView s_name;
        TextView date;

        public FileItemHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.textView212);
            size=itemView.findViewById(R.id.textView213);
            verify=itemView.findViewById(R.id.textView214);
            s_name=itemView.findViewById(R.id.textView216);
            date=itemView.findViewById(R.id.textView217);

        }
    }
}
