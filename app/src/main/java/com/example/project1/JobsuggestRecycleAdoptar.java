package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JobsuggestRecycleAdoptar extends RecyclerView.Adapter<JobSuggestViewHolder> {

    String [] names;
    Context context;

    public JobsuggestRecycleAdoptar(String[] name, Context context){
        this.names=name;
        this.context = context;
    }

    @Override
    public JobSuggestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater ref_lay=LayoutInflater.from(parent.getContext());
        View refViews=ref_lay.inflate(R.layout.job_suggestion_list,parent,false);
        return new JobSuggestViewHolder(refViews);
    }


    @Override
    public void onBindViewHolder(@NonNull JobSuggestViewHolder holder, int position) {
        String temp=names[position];
        holder.Names.setText(temp);

       //new added listener for apply now button
        holder.apply_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, JobDetail.class);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return names.length;
    }
}

class JobSuggestViewHolder extends RecyclerView.ViewHolder {
    TextView Names;
    TextView email;
    TextView status;
    TextView apply_now;


    public JobSuggestViewHolder(View itemView) {
        super(itemView);
        Names=itemView.findViewById(R.id.textview1);
        email=itemView.findViewById(R.id.textview2);
        status=itemView.findViewById(R.id.textview3);

        apply_now = itemView.findViewById(R.id.apply_now);

    }
}
