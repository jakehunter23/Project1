package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CandidateEducationRecAdapter extends RecyclerView.Adapter<CandidateEducationRecAdapter.educationViewHolder> {

    String [] degree;
    String [] course;
    String [] institute;
    String [] da;
    String [] dd;

    public CandidateEducationRecAdapter(String [] degree, String [] course, String [] institute, String [] da, String [] dd){
        this.degree=degree;
        this.course=course;
        this.institute=institute;
        this.da=da;
        this.dd=dd;

    }
    @NonNull
    @Override
    public educationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater eduInflater = LayoutInflater.from(parent.getContext());
        View indiView = eduInflater.inflate(R.layout.candidate_education_rec_item,parent,false);
        return new CandidateEducationRecAdapter.educationViewHolder(indiView);
    }

    @Override
    public void onBindViewHolder(@NonNull educationViewHolder holder, int position) {
        holder.degree.setText(degree[position]);
        holder.course.setText(course[position]);
        holder.institute.setText(institute[position]);
        holder.da.setText(da[position]);
        holder.dd.setText(dd[position]);
        if(position==degree.length-1){
            holder.line.setVisibility(View.GONE);
        }
        else{
            holder.line.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return degree.length;
    }

    class educationViewHolder extends RecyclerView.ViewHolder {

        TextView degree;
        TextView course;
        TextView institute;
        TextView da;
        TextView dd;
        View line;

        public educationViewHolder(@NonNull View itemView) {
            super(itemView);

            degree = itemView.findViewById(R.id.cand_textView259);
            course=itemView.findViewById(R.id.cand_textView260);
            institute=itemView.findViewById(R.id.cand_textView261);
            da=itemView.findViewById(R.id.cand_textView262);
            dd=itemView.findViewById(R.id.cand_textView263);
            line=itemView.findViewById(R.id.edu_line);

        }
    }
}
