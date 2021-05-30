package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardRec2Adapter extends RecyclerView.Adapter<DashboardRec2Adapter.DashRec2ViewHolder> {

    String [] names;
    String [] designation;
    int [] dp;

    public DashboardRec2Adapter(String [] names, String [] designation, int [] dp){
        this.names=names;
        this.designation=designation;
        this.dp =dp;

    }
    @NonNull
    @Override
    public DashRec2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater dash2Inflater = LayoutInflater.from(parent.getContext());
        View indiView = dash2Inflater.inflate(R.layout.dashboard_rec2_item, parent, false);
        return new DashboardRec2Adapter.DashRec2ViewHolder(indiView);
    }

    @Override
    public void onBindViewHolder(@NonNull DashRec2ViewHolder holder, int position) {
        holder.name.setText(names[position]);
        holder.desg.setText(designation[position]);
        holder.dp.setImageResource(dp[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    class DashRec2ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView desg;
        ImageView dp;

        public DashRec2ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.textView332);
            desg=itemView.findViewById(R.id.textView333);
            dp=itemView.findViewById(R.id.imageView30);
        }
    }
}
