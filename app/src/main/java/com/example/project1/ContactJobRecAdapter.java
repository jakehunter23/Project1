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

public class ContactJobRecAdapter extends RecyclerView.Adapter<ContactJobRecAdapter.ContactJobView> {
    @NonNull
    @Override
    public ContactJobView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater rec2inflater = LayoutInflater.from(parent.getContext());
        View indiView = rec2inflater.inflate(R.layout.contact_jobs_rec_item,parent,false);
        return new ContactJobView(indiView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactJobView holder, int position) {
        holder.information.setText("Show More");
        holder.hiddenView.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ContactJobView extends RecyclerView.ViewHolder {
        ImageView arrow;
        RelativeLayout hiddenView;
        RelativeLayout ClickBait;
        CardView cardView;
        TextView information;

        public ContactJobView(@NonNull View itemView) {
            super(itemView);

            arrow = itemView.findViewById(R.id.con_imageView19);
            hiddenView = itemView.findViewById(R.id.conjob_hidden_info);
            ClickBait = itemView.findViewById(R.id.con_job_clickbait);
            cardView = itemView.findViewById(R.id.con_job_card);
            information = itemView.findViewById(R.id.con_textView211);

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
