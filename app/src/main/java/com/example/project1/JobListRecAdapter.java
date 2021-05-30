package com.example.project1;

import android.content.Context;
import android.hardware.display.VirtualDisplay;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JobListRecAdapter extends RecyclerView.Adapter<JobListRecAdapter.JobRecViewHolder> {

    Context context;
    List<JobModel> jobList;
    List<ClientModel> clientList;
    List<CreatorModel> creatorList;

    public JobListRecAdapter(Context context, List<JobModel> jobList, List<ClientModel> clientList, List<CreatorModel> creatorList){
        this.context=context;
        this.jobList=jobList;
        this.clientList=clientList;
        this.creatorList=creatorList;

    }
    @NonNull
    @Override
    public JobRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.joblist_rec_item,parent,false);
        return new JobListRecAdapter.JobRecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobRecViewHolder holder, int position) {
       JobModel job_item = jobList.get(position);
        int company_id = job_item.getCompany_id();
        int creator_id = job_item.getRecruiter_id();
        ClientModel client_item = clientList.get(company_id);
        CreatorModel creator_item = creatorList.get(creator_id);
        String companyName = client_item.getName();
        String companyAddress = client_item.getAddress();
        int openings = job_item.getOpenings();
        String createdDate = job_item.getCreatedDate();
        String creatorName = creator_item.getFirst_name();
        String creatorLast = creator_item.getLast_name();
        String creatorMail = creator_item.getEmail();

        holder.companyName.setText(companyName);
        holder.companyAddress.setText(companyAddress);
        holder.openings.setText(openings + " " + "Openings");
        holder.addedOn.setText(createdDate);
        holder.recruiterName.setText(creatorName + " " + creatorLast);
        holder.recruiterEmail.setText(creatorMail);

    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    class JobRecViewHolder extends RecyclerView.ViewHolder {

        TextView companyName;
        TextView companyAddress;
        TextView addedOn;
        TextView openings;
        TextView recruiterName;
        TextView recruiterEmail;

        public JobRecViewHolder(@NonNull View itemView) {
            super(itemView);

            companyName = itemView.findViewById(R.id.joblist_company);
            companyAddress = itemView.findViewById(R.id.joblist_company_name);
            addedOn = itemView.findViewById(R.id.clihire_timestamp);
            openings = itemView.findViewById(R.id.clihire_contact_mail);
            recruiterName = itemView.findViewById(R.id.clihire_rec_name);
            recruiterEmail = itemView.findViewById(R.id.clihire_rec_mail);
        }

    }
}

