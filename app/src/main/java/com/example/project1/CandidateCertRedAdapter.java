package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CandidateCertRedAdapter extends RecyclerView.Adapter<CandidateCertRedAdapter.certViewHolder> {

    String [] certCourse;
    String [] certIn;
    String [] certDate;

    public CandidateCertRedAdapter(String [] certCourse, String [] certIn, String [] certDate){
        this.certCourse=certCourse;
        this.certIn=certIn;
        this.certDate=certDate;

    }
    @NonNull
    @Override
    public certViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater certInflater = LayoutInflater.from(parent.getContext());
        View indiView = certInflater.inflate(R.layout.candidate_certificate_rec_item,parent,false);
        return new CandidateCertRedAdapter.certViewHolder(indiView);
    }

    @Override
    public void onBindViewHolder(@NonNull certViewHolder holder, int position) {
        holder.certCourse.setText(certCourse[position]);
        holder.certInt.setText(certIn[position]);
        holder.certDate.setText(certDate[position]);
        if(position==certCourse.length-1){
            holder.line.setVisibility(View.GONE);
        }
        else{
            holder.line.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return certCourse.length;
    }

    class certViewHolder extends RecyclerView.ViewHolder {

        TextView certCourse;
        TextView certInt;
        TextView certDate;
        View line;

        public certViewHolder(@NonNull View itemView) {
            super(itemView);

            certCourse=itemView.findViewById(R.id.cand_textView265);
            certInt=itemView.findViewById(R.id.cand_textView266);
            certDate=itemView.findViewById(R.id.cand_textView267);
            line=itemView.findViewById(R.id.cert_line);
        }
    }
}
