package com.example.project1;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardRec3Adapter extends RecyclerView.Adapter<DashboardRec3Adapter.DashRec3ViewHolder>{

    String [] names;
    String [] date;
    Context context;
    int [] dp;

    public DashboardRec3Adapter(String [] names, String [] date, Context context, int [] dp){
        this.names=names;
        this.date=date;
        this.context=context;
        this.dp=dp;

    }
    @NonNull
    @Override
    public DashRec3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater dash3Inflater = LayoutInflater.from(parent.getContext());
        View indiView = dash3Inflater.inflate(R.layout.dash_rec3_item, parent, false);
        return new DashboardRec3Adapter.DashRec3ViewHolder(indiView);
    }

    @Override
    public void onBindViewHolder(@NonNull DashRec3ViewHolder holder, int position) {
        holder.name.setText(names[position]);
        holder.date.setText(date[position]);
        holder.dp.setImageResource(dp[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    class DashRec3ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView date;
        ImageView dp;

        public DashRec3ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.textView334);
            date=itemView.findViewById(R.id.textView335);
            dp=itemView.findViewById(R.id.imageView31);

        }
    }
}
