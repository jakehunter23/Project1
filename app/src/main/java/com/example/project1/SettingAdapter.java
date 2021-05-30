package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class SettingAdapter extends RecyclerView.Adapter<SettingViewHolder> {

    private final String[] Head;
    private String describe;

    public SettingAdapter(String[] Head,String describe) {
        this.Head=Head;
        this.describe=describe;
    }

    @Override
    public SettingViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater sett_inflater=LayoutInflater.from(parent.getContext());
        View sett_view=sett_inflater.inflate(R.layout.setting_view,parent,false);

        return new SettingViewHolder(sett_view);
    }

    @Override
    public void onBindViewHolder( SettingViewHolder holder, int position) {
        String heading=Head[position];
        holder.Heading.setText(heading);
        holder.Details.setText(describe);
    }

    @Override
    public int getItemCount() {
        return Head.length;
    }
}

class SettingViewHolder extends RecyclerView.ViewHolder{
    TextView Heading;
    TextView Details;

    public SettingViewHolder( View itemView) {
        super(itemView);
        Heading=itemView.findViewById(R.id.sett_heading);
        Details=itemView.findViewById(R.id.sett_desc);
    }
}
