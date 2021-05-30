package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyTaskRecAdapter extends RecyclerView.Adapter<MyTaskRecAdapter.MyTaskViewHolder> {
    Context context;

    public MyTaskRecAdapter(Context context){
        this.context=context;
    }
    @NonNull
    @Override
    public MyTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.my_task_rec_item,parent,false);
        return new MyTaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTaskViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MyTaskViewHolder extends RecyclerView.ViewHolder {

        CardView itemClick;

        public MyTaskViewHolder(@NonNull View itemView) {
            super(itemView);

            itemClick=itemView.findViewById(R.id.task_indi_item);
            itemClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(context,TaskSelectedActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
}
