package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DrugsRecAdapter extends RecyclerView.Adapter<DrugsRecAdapter.DrugsViewHolder> {
    List<DrugModel> drugsList;
    Context context;

    public void setFilteredList( List<DrugModel> drugsList){
        this.drugsList = drugsList;
        notifyDataSetChanged();
    }


    public DrugsRecAdapter(Context context, List<DrugModel> drugsList){
        this.drugsList = drugsList;
        this.context = context;

    }
    @NonNull
    @Override
    public DrugsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.drug_list_item,parent,false);
        return new DrugsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrugsViewHolder holder, int position) {
        DrugModel drug = drugsList.get(position);
        holder.name.setText(drug.drug_name.toString());
        holder.mfr.setText(drug.manufacturer.toString());
    }


    @Override
    public int getItemCount() {
        return drugsList.size();
    }

    public class DrugsViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView mfr;
        RelativeLayout clickBait;
        public DrugsViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.textView334);
            mfr = view.findViewById(R.id.textView335);
            clickBait = view.findViewById(R.id.drugrel);

            clickBait.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle b1 = new Bundle();
                    b1.putString("drug_name", drugsList.get(getAdapterPosition()).getDrug_name());
                    b1.putString("manufacturer", drugsList.get(getAdapterPosition()).manufacturer);
                    b1.putString("composition", drugsList.get(getAdapterPosition()).composition);

                    Intent intent = new Intent(context, DrugDetails.class);
                    intent.putExtras(b1);
                    context.startActivity(intent);

                }

            });
        }
    }
}
