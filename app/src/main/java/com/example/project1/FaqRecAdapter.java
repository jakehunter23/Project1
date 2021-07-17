package com.example.project1;

import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class FaqRecAdapter extends RecyclerView.Adapter<FaqRecAdapter.FaqRecViewHolder> {
    @NonNull
    @Override
    public FaqRecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.faq_recent_rec_item,parent,false);
        return new FaqRecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaqRecViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class FaqRecViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout readMore;
        TextView descrption;
        boolean expandable;
        ImageView desc_image;
        TextView dec_title;

        public FaqRecViewHolder(@NonNull View itemView) {
            super(itemView);
            readMore=itemView.findViewById(R.id.item_description_layout);
            descrption=itemView.findViewById(R.id.cand_note_name);
            desc_image=itemView.findViewById(R.id.item_description_img);
            dec_title=itemView.findViewById(R.id.item_description_title);





            readMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!expandable)
                    {
                        expandable=true;
                        ObjectAnimator animator=ObjectAnimator.ofInt(descrption,"maxLines",40);
                        animator.setDuration(100).start();
                        desc_image.setImageResource(R.drawable.expand_less);
                        dec_title.setText("Read less");

                    }
                    else
                    {
                        expandable=false;
                        ObjectAnimator animator=ObjectAnimator.ofInt(descrption,"maxLines",4);
                        animator.setDuration(100).start();
                        desc_image.setImageResource(R.drawable.expand_more);
                        dec_title.setText("Read more");

                    }
                }
            });


        }
    }
}
