package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CandidateEmploymentRecAdapter extends RecyclerView.Adapter<CandidateEmploymentRecAdapter.employmentViewHolder> {

    String [] jobRole;
    String [] companyName;
    String [] location;
    String  jobDes;
    String [] datef;
    String [] dateto;

    public CandidateEmploymentRecAdapter(String [] jobRole, String [] companyName, String [] location, String jobDes, String [] datef, String [] dateto){
        this.jobRole=jobRole;
        this.companyName=companyName;
        this.location=location;
        this.jobDes=jobDes;
        this.datef=datef;
        this.dateto=dateto;

    }
    @NonNull
    @Override
    public employmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater employInflater = LayoutInflater.from(parent.getContext());
        View indiView = employInflater.inflate(R.layout.candidate_employment_rec_item,parent,false);
        return new CandidateEmploymentRecAdapter.employmentViewHolder(indiView);
    }

    @Override
    public void onBindViewHolder(@NonNull employmentViewHolder holder, int position) {
        holder.jobRole.setText(jobRole[position]);
        holder.company_name.setText(companyName[position]);
        holder.location.setText(location[position]);
        holder.jobDes.setText(jobDes);
        holder.dateFrom.setText(datef[position]);
        holder.dateTo.setText(dateto[position]);
        if(position==jobRole.length-1){
            holder.line.setVisibility(View.GONE);
        }
        else{
            holder.line.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return jobRole.length;
    }

    class employmentViewHolder extends RecyclerView.ViewHolder {

        TextView jobRole;
        TextView company_name;
        TextView location;
        TextView jobDes;
        TextView dateFrom;
        TextView dateTo;
        View line;

        public employmentViewHolder(@NonNull View itemView) {
            super(itemView);

            jobRole=itemView.findViewById(R.id.cand_textView237);
            company_name=itemView.findViewById(R.id.cand_textView238);
            location=itemView.findViewById(R.id.cand_textView239);
            jobDes=itemView.findViewById(R.id.cand_textView240);
            dateFrom=itemView.findViewById(R.id.cand_textView242);
            dateTo=itemView.findViewById(R.id.cand_textView248);
            line = itemView.findViewById(R.id.endline1);
        }
    }
}
