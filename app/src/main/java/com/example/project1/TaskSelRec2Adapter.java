package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskSelRec2Adapter extends RecyclerView.Adapter<TaskSelRec2Adapter.TaskSel2ViewHolder> {

    int [] dp;

    public TaskSelRec2Adapter(int [] dp){
        this.dp=dp;

    }
    @NonNull
    @Override
    public TaskSel2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.task_sel_rec1_item,parent,false);
        return new TaskSel2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskSel2ViewHolder holder, int position) {
            holder.dp.setImageResource(dp[position]);
    }

    @Override
    public int getItemCount() {
        return dp.length;
    }

    class TaskSel2ViewHolder extends RecyclerView.ViewHolder {

        ImageView dp;

        public TaskSel2ViewHolder(@NonNull View itemView) {
            super(itemView);

            dp=itemView.findViewById(R.id.imageView51);
        }
    }
}
