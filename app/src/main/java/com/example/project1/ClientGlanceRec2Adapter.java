package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

public class ClientGlanceRec2Adapter extends RecyclerView.Adapter<ClientGlanceRec2Adapter.ItemHHold> {

    int [] dot_img;
    String [] date;
    String [] typejob;
    String [] con_name;
    String [] con_mail;
    String [] rec;
    String [] rec_mail;

    public ClientGlanceRec2Adapter(int [] dot_img, String [] date, String [] typejob, String [] con_name, String [] con_mail, String [] rec, String [] rec_mail){
        this.dot_img = dot_img;
        this.date = date;
        this.typejob = typejob;
        this.con_name=con_name;
        this.con_mail=con_mail;
        this.rec = rec;
        this.rec_mail=rec_mail;
    }
    @NonNull
    @Override
    public ItemHHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater rec2inflater = LayoutInflater.from(parent.getContext());
        View indiView = rec2inflater.inflate(R.layout.client_glance_rec2_item,parent,false);
        return new ItemHHold(indiView);
    }

    @Override
    public void onBindViewHolder(ItemHHold holder, int position) {
        holder.dot.setImageResource(dot_img[position]);
        holder.date.setText(date[position]);
        holder.type_job.setText(typejob[position]);
        holder.contact_name.setText(con_name[position]);
        holder.contact_mail.setText(con_mail[position]);
        holder.recruiter.setText(rec[position]);
        holder.rec_mail.setText(rec_mail[position]);
        holder.information.setText("Show More");
        holder.hiddenView.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return con_name.length;
    }

    class ItemHHold extends RecyclerView.ViewHolder {
        ImageView dot;
        TextView date;
        TextView type_job;
        TextView contact_name;
        TextView contact_mail;
        TextView recruiter;
        TextView rec_mail;
        ImageView arrow;
        RelativeLayout hiddenView;
        RelativeLayout ClickBait;
        CardView cardView;
        TextView information;


        public ItemHHold(@NonNull View itemView) {
            super(itemView);

            dot = itemView.findViewById(R.id.imageView21);
            date =itemView.findViewById(R.id.cliglan_rec2_addate);
            type_job = itemView.findViewById(R.id.cliglan_rec2_full);
            contact_name = itemView.findViewById(R.id.cliglan_rec2_com_contact);
            contact_mail = itemView.findViewById(R.id.cliglan_rec2_com_conmail);
            recruiter = itemView.findViewById(R.id.cliglan_rec2_recname);
            rec_mail = itemView.findViewById(R.id.cliglan_rec2_recmail);
            arrow = itemView.findViewById(R.id.imageView19);
            hiddenView = itemView.findViewById(R.id.hidden_info);
            ClickBait = itemView.findViewById(R.id.card_option_click);
            cardView = itemView.findViewById(R.id.item_expand_card);
            information = itemView.findViewById(R.id.show_more);

            ClickBait.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (hiddenView.getVisibility() == View.VISIBLE) {


                        TransitionManager.beginDelayedTransition(cardView,
                                new AutoTransition());
                        hiddenView.setVisibility(View.GONE);
                        arrow.setImageResource(R.drawable.expand_more);
                        information.setText(R.string.show_more);

                    }  else {
                        TransitionManager.beginDelayedTransition(cardView,
                                new AutoTransition());
                        hiddenView.setVisibility(View.VISIBLE);
                        arrow.setImageResource(R.drawable.expand_less);
                        information.setText(R.string.show_less);
                    }


                }

            });
        }
    }
}
