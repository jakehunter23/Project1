package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

public class ActurialApplicantRecAdapter extends RecyclerView.Adapter<ActurialApplicantRecAdapter.ActAptView> {
    @NonNull
    @Override
    public ActAptView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.acturial_applicant_rec_item,parent,false);
        return new ActAptView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActAptView holder, int position) {
        holder.information.setText("Show More");
        holder.hiddenView.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ActAptView extends RecyclerView.ViewHolder {

        ImageView arrow;
        LinearLayout hiddenView;
        RelativeLayout ClickBait;
        CardView cardView;
        TextView information;

        public ActAptView(@NonNull View itemView) {
            super(itemView);
            arrow = itemView.findViewById(R.id.imageView19);
            hiddenView = itemView.findViewById(R.id.ghost_layout);
            ClickBait = itemView.findViewById(R.id.click_me);
            cardView =itemView.findViewById(R.id.card_for_actapt);
            information = itemView.findViewById(R.id.textView211);

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
