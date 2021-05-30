package com.example.project1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CreateTaskFrag1Adapter extends RecyclerView.Adapter<CreateTaskFrag1Adapter.CreateTask1ViewHolder> {

    private Context context;

    public CreateTaskFrag1Adapter(Context context){
        this.context=context;

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
                        ((CreateTask)context).addFragmentOnTop(new CreateTaskFragment2());
                    }
                }
            });
        }
    }
}
