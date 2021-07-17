package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptor extends RecyclerView.Adapter<Adaptor.ViewHolder> {
    private List<mytaskAdoptar> userList;
    public Adaptor (List<mytaskAdoptar>userList){this.userList=userList;}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mytask_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resource = userList.get(position).getImageview();
        String name= userList.get(position).getTextview1();
        String msg= userList.get(position).getTextview2();
        holder.setData(resource,name,msg);


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageview;
        private TextView textView1;
        private TextView textView2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageview=itemView.findViewById(R.id.imageprofile);
            textView1=itemView.findViewById(R.id.textname);
            textView2=itemView.findViewById(R.id.textjob);
        }

        public void setData(int resource, String name, String msg) {
            imageview.setImageResource(resource);
            textView1.setText(name);
            textView2.setText(msg);
        }
    }
}
