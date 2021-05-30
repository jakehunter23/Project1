package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskSelRec3Adapter extends RecyclerView.Adapter<TaskSelRec3Adapter.TaskSel3ViewHolder> {
    @NonNull
    @Override
    public TaskSel3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_sel_rec2_item,parent,false);
        return new TaskSel3ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskSel3ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class TaskSel3ViewHolder extends RecyclerView.ViewHolder {

        public TaskSel3ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
