package com.example.project1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CreateTaskFrag1Adapter extends RecyclerView.Adapter<CreateTaskFrag1Adapter.CreateTask1ViewHolder> {

    private Context context;
    String designation;
    String createdDate;

    public CreateTaskFrag1Adapter(Context context, String designation, String createdDate){
        this.context=context;
        this.createdDate=createdDate;
        this.designation=designation;

    }
    @NonNull
    @Override
    public CreateTask1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.create_task_rec_item,parent,false);
        return new CreateTask1ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreateTask1ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class CreateTask1ViewHolder extends RecyclerView.ViewHolder {

        CardView click;
        public CreateTask1ViewHolder(@NonNull View itemView) {
            super(itemView);

            click = itemView.findViewById(R.id.task_one_item);

            click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(context instanceof CreateTask){
                        Bundle bundle = new Bundle();
                        bundle.putInt("jobTypeId", getAdapterPosition()+1);
                        bundle.putString("designation", designation);
                        bundle.putString("createdDate", createdDate);
                        ((CreateTask)context).addFragmentOnTop(new CreateTaskFragment2(),bundle);
                    }
                }
            });
        }
    }
}
