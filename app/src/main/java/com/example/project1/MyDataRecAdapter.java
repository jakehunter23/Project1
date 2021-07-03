package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;

import java.util.List;
import java.util.concurrent.TimeoutException;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

public class MyDataRecAdapter extends RecyclerView.Adapter<MyDataRecAdapter.MyDataViewHolder> {

    Context context;
    List<CandidateModel> candidateModels;

    public MyDataRecAdapter(Context context, List<CandidateModel> candidateModels){
        this.context=context;
        this.candidateModels=candidateModels;
    }
    @NonNull
    @Override
    public MyDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.my_database_rec_item,parent,false);
        return new MyDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDataViewHolder holder, int position) {
        holder.information.setText("Show More");
        holder.hiddenView.setVisibility(View.GONE);
        CandidateModel cand_item = candidateModels.get(position);
        String firstName = cand_item.getFirst_name();
        String lastName = cand_item.getLast_name();
        holder.name.setText(firstName + " " + lastName);
        holder.email.setText(cand_item.getEmail());
        holder.companyName.setText(cand_item.getCompany_name());
        holder.title.setText(cand_item.getTitle());
        holder.status.setText(cand_item.getStatus());
        holder.createdDate.setText(cand_item.getCreated_date());
        holder.degree.setText("(" + cand_item.getDegree() + ")");
    }

    @Override
    public int getItemCount() {
        return candidateModels.size();
    }

    class MyDataViewHolder extends RecyclerView.ViewHolder {
        ImageView arrow;
        LinearLayout hiddenView;
        RelativeLayout ClickBait;
        CardView cardView;
        TextView information;

        TextView name;
        TextView email;
        TextView companyName;
        TextView title;
        TextView status;
        TextView createdDate;
        TextView degree;


        public MyDataViewHolder(@NonNull View itemView) {
            super(itemView);

            arrow = itemView.findViewById(R.id.imageView19);
            hiddenView = itemView.findViewById(R.id.ghost_layout);
            ClickBait = itemView.findViewById(R.id.click_me);
            cardView =itemView.findViewById(R.id.card_for_database);
            information = itemView.findViewById(R.id.textView211);
            name = itemView.findViewById(R.id.textView405);
            companyName=itemView.findViewById(R.id.textView408);
            email=itemView.findViewById(R.id.textView406);
            degree=itemView.findViewById(R.id.textView418);
            status=itemView.findViewById(R.id.textView419);
            title=itemView.findViewById(R.id.textView409);
            createdDate=itemView.findViewById(R.id.textView415);


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
