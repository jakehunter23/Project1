package com.example.project1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
        RelativeLayout toCandDetails;
        CardView delete;


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
            String deleteCandidate = "https://demotic-recruit.000webhostapp.com/candidate_delete.php";


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

            delete = itemView.findViewById(R.id.delete_card);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CandidateModel specific_cand_item = candidateModels.get(getAdapterPosition());
                    String firstName = specific_cand_item.getFirst_name();
                    String lastName = specific_cand_item.getLast_name();
                    String email = specific_cand_item.getEmail();
                    String createdDate = specific_cand_item.getCreated_date();
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Do you really want to delete?");
                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            StringRequest deleteRequest = new StringRequest(Request.Method.POST, deleteCandidate, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Intent intent = new Intent(context, MyDatabase.class);
                                    context.startActivity(intent);


                                 }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }){
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    HashMap<String, String> param = new HashMap<>();
                                    param.put("first_name",firstName);
                                    param.put("last_name", lastName);
                                    param.put("email",email);
                                    param.put("created_date",createdDate);

                                    return param;
                                }
                            };

                            Volley.newRequestQueue(context).add(deleteRequest);

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });

            toCandDetails = itemView.findViewById(R.id.toCandDetails);
            toCandDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CandidateActivity.class);
                    CandidateModel specific_cand_item = candidateModels.get(getAdapterPosition());
                    String firstName = specific_cand_item.getFirst_name();
                    String lastName = specific_cand_item.getLast_name();
                    String email = specific_cand_item.getEmail();
                    String createdDate = specific_cand_item.getCreated_date();
                    Bundle bundle = new Bundle();
                    bundle.putString("first_name", firstName);
                    bundle.putString("last_name", lastName);
                    bundle.putString("email", email);
                    bundle.putString("created_date", createdDate);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }

    }
}
