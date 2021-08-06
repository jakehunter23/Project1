package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

public class MyClientRecAdapter extends RecyclerView.Adapter<MyClientRecAdapter.MyClientViewHolder> {

    Context context;
    List<ClientModel> clientList;
    List<CreatorModel> creator;

    public MyClientRecAdapter(Context context, List<ClientModel> clientList, List<CreatorModel> creator){
        this.context=context;
        this.clientList=clientList;
        this.creator=creator;

    }
    @NonNull
    @Override
    public MyClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.my_client_rec_item,parent,false);
        return new MyClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClientViewHolder holder, int position) {
        ClientModel client_item = clientList.get(position);

        int creator_id = client_item.getCreator_id();
        CreatorModel creator_item = creator.get(creator_id);
        String creator_name = creator_item.getFirst_name().toString();
        String creator_last = creator_item.getLast_name().toString();
        String name = client_item.getName().toString();
        holder.hiddenView.setVisibility(View.GONE);
        holder.phoneNumber.setText(client_item.getPhone_number().toString());
        holder.companyName.setText(name);
        holder.email.setText(client_item.getEmail().toString());
        holder.address.setText(client_item.getAddress().toString());
        holder.date.setText(client_item.getDateCreated().toString());


    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    class MyClientViewHolder extends RecyclerView.ViewHolder {


        ImageView arrow;
        LinearLayout hiddenView;
        CardView cardView;
        CardView ClickBait;
        TextView companyName;
        TextView phoneNumber;
        TextView email;
        TextView address;
        TextView date;
        Button view;

        public MyClientViewHolder(@NonNull View itemView) {
            super(itemView);

            arrow = itemView.findViewById(R.id.imageView59);
            hiddenView = itemView.findViewById(R.id.ghost_layout);
            cardView = itemView.findViewById(R.id.cli_card_lay);
            ClickBait =itemView.findViewById(R.id.clickMe_card);
            companyName=itemView.findViewById(R.id.textView405);
            phoneNumber = itemView.findViewById(R.id.textView409);
            email =itemView.findViewById(R.id.textView412);
            address =itemView.findViewById(R.id.textView413);
            date = itemView.findViewById(R.id.textView415);
            view = itemView.findViewById(R.id.button38);

            ClickBait.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (hiddenView.getVisibility() == View.VISIBLE) {


                        TransitionManager.beginDelayedTransition(cardView,
                                new AutoTransition());
                        hiddenView.setVisibility(View.GONE);
                        arrow.setImageResource(R.drawable.keyboard_arrow_down);


                    }  else {
                        TransitionManager.beginDelayedTransition(cardView,
                                new AutoTransition());
                        hiddenView.setVisibility(View.VISIBLE);
                        arrow.setImageResource(R.drawable.arrow_up_icon);

                    }


                }

            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ClientActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name",clientList.get(getAdapterPosition()).getName());
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
        }

    }

}
