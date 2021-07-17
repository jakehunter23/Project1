package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReferralAdapter_JS extends RecyclerView.Adapter<ReferralViewHolder_JS> {

    String [] name;
    public ReferralAdapter_JS(String[] name){
        this.name=name;
    }

    @Override
    public ReferralViewHolder_JS onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater ref_lay=LayoutInflater.from(parent.getContext());
        View refView=ref_lay.inflate(R.layout.referral_list_item_js,parent,false);
        return new ReferralViewHolder_JS(refView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReferralViewHolder_JS holder, int position) {
        String temp=name[position];
        holder.Name.setText(temp);
        holder.email.setText("example");
        holder.status.setText("pending");
        holder.sent.setText("idk");
        holder.sentThorugh.setText("my boss");

    }



    @Override
    public int getItemCount() {
        return name.length;
    }
}

class ReferralViewHolder_JS extends RecyclerView.ViewHolder {
        TextView Name;
        TextView email;
        TextView status;
        TextView sent;
        TextView sentThorugh;

    public ReferralViewHolder_JS(View itemView) {
        super(itemView);
        Name=itemView.findViewById(R.id.RefName);
        email=itemView.findViewById(R.id.RefEmail);
        status=itemView.findViewById(R.id.RefStatus);
        sent=itemView.findViewById(R.id.RefSent);
        sentThorugh=itemView.findViewById(R.id.RefSentThrough);
    }
}
