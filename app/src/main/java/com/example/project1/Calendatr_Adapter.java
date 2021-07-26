package com.example.project1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Calendatr_Adapter extends RecyclerView.Adapter<Calendatr_Adapter.ViewHolder>  {

    Context context;
    ArrayList<Schedule_Class> items;
    Activity activity;
    public Calendatr_Adapter(Context context, ArrayList<Schedule_Class> items,Activity activity){
        this.context=context;
        this.items=items;
        this.activity=activity;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.calendar_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Schedule_Class schedule_class=items.get(position);
        holder.time.setText(schedule_class.getTime());
        holder.title.setText(schedule_class.getTitle());
        holder.location.setText(schedule_class.getLocation());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView time,title,location;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            time=itemView.findViewById(R.id.time_id_item);
            title=itemView.findViewById(R.id.title_id_item);
            location=itemView.findViewById(R.id.location_id_item);
        }
    }

}
