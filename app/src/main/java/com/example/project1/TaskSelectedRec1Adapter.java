package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskSelectedRec1Adapter extends RecyclerView.Adapter<TaskSelectedRec1Adapter.TaskSel1ViewHolder> {

    int [] icons;
    String [] title;

    public TaskSelectedRec1Adapter(int [] icons, String [] title){
        this.icons=icons;
        this.title=title;

    }
    @NonNull
    @Override
    public TaskSel1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_sel_constraint_item,parent,false);
        return new TaskSel1ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TaskSel1ViewHolder holder, int position) {
                holder.icon.setImageResource(icons[position]);
                holder.title.setText(title[position]);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    class TaskSel1ViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;

        public TaskSel1ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.imageView53);
            title = itemView.findViewById(R.id.textView350);
        }
    }
}
