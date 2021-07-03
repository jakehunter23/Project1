package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SpecificJobAdoptor extends RecyclerView.Adapter<SpecificJobAdoptor.ViewHolder> {
    private List<SpecificJob> jobList;
    public SpecificJobAdoptor(List<SpecificJob>jobList)
    {
        this.jobList=jobList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(parent.getContext()).inflate(R.layout.specific_job_list,parent,false);
        return new ViewHolder(views);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resource = jobList.get(position).getImageviewjob();
        String nameactive= jobList.get(position).getActive();
        String jobsname= jobList.get(position).getJobs();
        String locationname= jobList.get(position).getLocation();
        holder.setData(resource,nameactive,jobsname,locationname);




    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageviewjob;
        private TextView active;
        private TextView location;
        private TextView jobs;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageviewjob=itemView.findViewById(R.id.iamgeviewjobs);
            active=itemView.findViewById(R.id.active);
            jobs=itemView.findViewById(R.id.jobs);
            location=itemView.findViewById(R.id.location);

        }

        public void setData(int resource, String nameactive, String jobsname, String locationname) {
            imageviewjob.setImageResource(resource);
            active.setText(nameactive);
            jobs.setText(jobsname);
            location.setText(locationname);
        }
    }
}
