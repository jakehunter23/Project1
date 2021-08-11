package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.hardware.display.VirtualDisplay;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

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
        String company_id = job_item.getCompany_id();
        int creator_id = job_item.getRecruiter_id();
        ClientModel client_item = clientList.get(Integer.parseInt(company_id)-1);
        CreatorModel creator_item = creatorList.get(creator_id);
        String companyName = client_item.getName();
        String companyAddress = client_item.getAddress();
        int openings = job_item.getOpenings();
        String createdDate = job_item.getCreatedDate();
        String creatorName = creator_item.getFirst_name();
        String creatorLast = creator_item.getLast_name();
        String creatorMail = creator_item.getEmail();
        holder.hidden.setVisibility(View.GONE);


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
        RelativeLayout hidden;
        RelativeLayout click;
        TextView addedOn;
        TextView openings;
        TextView recruiterName;
        TextView recruiterEmail,information;
        ImageView arrow;
        ImageView view;

        public JobRecViewHolder(@NonNull View itemView) {
            super(itemView);

            companyName = itemView.findViewById(R.id.joblist_company);
            companyAddress = itemView.findViewById(R.id.joblist_company_name);
            addedOn = itemView.findViewById(R.id.clihire_timestamp);
            openings = itemView.findViewById(R.id.clihire_contact_mail);
            recruiterName = itemView.findViewById(R.id.clihire_rec_name);
            recruiterEmail = itemView.findViewById(R.id.clihire_rec_mail);
            click=itemView.findViewById(R.id.click);
            hidden=itemView.findViewById(R.id.hidden_info);
            arrow=itemView.findViewById(R.id.downarrow);
            information=itemView.findViewById(R.id.more);
            view = itemView.findViewById(R.id.clihire_priority_dot);

            click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (hidden.getVisibility() == View.VISIBLE) {


                        TransitionManager.beginDelayedTransition(hidden,
                                new AutoTransition());
                        hidden.setVisibility(View.GONE);
                        arrow.setImageResource(R.drawable.expand_more);
                        information.setText(R.string.show_more);

                    }  else {
                        TransitionManager.beginDelayedTransition(hidden,
                                new AutoTransition());
                        hidden.setVisibility(View.VISIBLE);
                        arrow.setImageResource(R.drawable.expand_less);
                        information.setText(R.string.show_less);
                    }
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClientModel client = clientList.get(getAdapterPosition());
                    JobModel job = jobList.get(getAdapterPosition());
                    String companyId=job.getCompany_id();
                    String opening = String.valueOf(job.getOpenings());
                    String createdDate = job.getCreatedDate();

                    Intent intent = new Intent(context, ActurialActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("company_id", companyId);
                    bundle.putString("openings", opening);
                    bundle.putString("created_date", createdDate);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
        }

    }
}

