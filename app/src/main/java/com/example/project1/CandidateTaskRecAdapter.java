package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CandidateTaskRecAdapter extends RecyclerView.Adapter<CandidateTaskRecAdapter.taskViewHolder> {

    String [] Company;
    String [] per;
    String [] taskName;
    String [] taskType;
    String [] da;
    String [] dd;


    public CandidateTaskRecAdapter(String [] Company, String [] per, String [] taskName, String [] taskType, String [] da, String [] dd){
        this.Company=Company;
        this.per=per;
        this.taskName=taskName;
        this.taskType=taskType;
        this.da=da;
        this.dd=dd;

    }
    @NonNull
    @Override
    public taskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater taskInflater = LayoutInflater.from(parent.getContext());
        View indiView = taskInflater.inflate(R.layout.candidate_tasks_rec_item,parent,false);
        return new CandidateTaskRecAdapter.taskViewHolder(indiView);
    }

    @Override
    public void onBindViewHolder(@NonNull taskViewHolder holder, int position) {
        holder.company.setText(Company[position]);
        holder.percentage.setText(per[position]);
        holder.taskName.setText(taskName[position]);
        holder.taskType.setText(taskType[position]);
        holder.da.setText(da[position]);
        holder.dd.setText(dd[position]);
        if(position==taskName.length-1){
            holder.line.setVisibility(View.GONE);
        }
        else{
            holder.line.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return taskName.length;
    }

    class taskViewHolder extends RecyclerView.ViewHolder {

        TextView company;
        TextView percentage;
        TextView taskName;
        TextView taskType;
        TextView da;
        TextView dd;
        View line;

        public taskViewHolder(@NonNull View itemView) {
            super(itemView);

            company=itemView.findViewById(R.id.cand_textView249);
            percentage=itemView.findViewById(R.id.cand_textView250);
            taskName=itemView.findViewById(R.id.cand_textView251);
            taskType=itemView.findViewById(R.id.cand_textView252);
            da=itemView.findViewById(R.id.cand_textView254);
            dd=itemView.findViewById(R.id.cand_textView256);
            line=itemView.findViewById(R.id.task_line);

        }
    }
}
