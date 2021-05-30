package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




import androidx.recyclerview.widget.RecyclerView;

public class ReferralAdapter extends RecyclerView.Adapter<ReferralViewHolder> {

    String [] name;
    public ReferralAdapter(String[] name){
        this.name=name;
    }

    @Override
    public ReferralViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater ref_lay=LayoutInflater.from(parent.getContext());
        View refView=ref_lay.inflate(R.layout.referral_list_item,parent,false);
        return new ReferralViewHolder(refView);
    }

    @Override
    public void onBindViewHolder(ReferralViewHolder holder, int position) {
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

class ReferralViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView email;
        TextView status;
        TextView sent;
        TextView sentThorugh;

    public ReferralViewHolder(View itemView) {
        super(itemView);
        Name=itemView.findViewById(R.id.RefName);
        email=itemView.findViewById(R.id.RefEmail);
        status=itemView.findViewById(R.id.RefStatus);
        sent=itemView.findViewById(R.id.RefSent);
        sentThorugh=itemView.findViewById(R.id.RefSentThrough);
    }
}
