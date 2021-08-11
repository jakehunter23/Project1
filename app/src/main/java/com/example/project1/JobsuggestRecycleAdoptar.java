package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JobsuggestRecycleAdoptar extends RecyclerView.Adapter<JobSuggestViewHolder> {

    List<JSJobModel> jobModelList;
    Context context;

    public JobsuggestRecycleAdoptar( List<JSJobModel> jobModelList, Context context){
        this.jobModelList=jobModelList;
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
        JSJobModel job_item = jobModelList.get(position);
        holder.Names.setText(job_item.getCompany_id());
        holder.status.setText(job_item.getStatus());


       //new added listener for apply now button
        holder.apply_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, JobDetail.class);
                Bundle bundle = new Bundle();
                bundle.putString("company_id", job_item.getCompany_id());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return jobModelList.size();
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
