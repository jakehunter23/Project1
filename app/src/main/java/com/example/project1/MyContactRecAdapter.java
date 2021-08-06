package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

public class MyContactRecAdapter extends RecyclerView.Adapter<MyContactRecAdapter.MyConViewHolder> {

    Context context;
    List<ContactModel> contactModels;
    List<ClientModel> clientModels;


    public  MyContactRecAdapter(Context context, List<ContactModel> contactModels, List<ClientModel> clientModels){
        this.context=context;
        this.contactModels=contactModels;
        this.clientModels = clientModels;
    }
    @NonNull
    @Override
    public MyConViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.my_contact_rec_item,parent,false);
        return new MyConViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyConViewHolder holder, int position) {
        holder.hiddenView.setVisibility(View.GONE);
        ContactModel contact_item = contactModels.get(position);
        int company_id = contact_item.getCompany_id();
        ClientModel client_item = clientModels.get(company_id);
        String companyName = client_item.getName();
        String firstName = contact_item.getFirstName().toString();
        String lastName = contact_item.getLastName().toString();
        String middleName = contact_item.getMiddleName().toString();

        holder.name.setText(firstName + " " + middleName + " " + lastName);
        holder.companyName.setText(companyName);
        holder.title.setText(contact_item.getTitle());
        holder.phoneNumber.setText(contact_item.getContactNumber());
        holder.email.setText(contact_item.getEmail());
        holder.address.setText(contact_item.getAddress());
        holder.date.setText(contact_item.getLastContactDate());

    }

    @Override
    public int getItemCount() {
        return contactModels.size();
    }

    class MyConViewHolder extends RecyclerView.ViewHolder {
        ImageView arrow;
        LinearLayout hiddenView;
        CardView cardView;
        CardView ClickBait;
        TextView name;
        TextView companyName;
        TextView title;
        TextView phoneNumber;
        TextView email;
        TextView address;
        TextView date;
        Button view;


        public MyConViewHolder(@NonNull View itemView) {
            super(itemView);

            arrow = itemView.findViewById(R.id.imageView59);
            hiddenView = itemView.findViewById(R.id.ghost_layout);
            cardView = itemView.findViewById(R.id.con_card_lay);
            ClickBait =itemView.findViewById(R.id.click_expand_mycon);
            name=itemView.findViewById(R.id.textView405);
            companyName = itemView.findViewById(R.id.textView406);
            title = itemView.findViewById(R.id.textView407);
            phoneNumber = itemView.findViewById(R.id.textView409);
            email = itemView.findViewById(R.id.textView412);
            address =itemView.findViewById(R.id.textView413);
            date =itemView.findViewById(R.id.textView415);
            view= itemView.findViewById(R.id.button38);




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
                    Intent intent = new Intent(context, ContactActivity.class);
                    ContactModel spec_contact = contactModels.get(getAdapterPosition());
                    String firstName = spec_contact.getFirstName();
                    String lastName = spec_contact.getLastName();
                    String middleName = spec_contact.getMiddleName();
                    String phone = spec_contact.getContactNumber();
                    String email = spec_contact.getEmail();
                    String address = spec_contact.getAddress();
                    String date = spec_contact.getLastContactDate();
                    Bundle bundle = new Bundle();
                    bundle.putString("first_name", firstName);
                    bundle.putString("last_name", lastName);
                    bundle.putString("middle_name", middleName);
                    bundle.putString("contact_number", phone);
                    bundle.putString("email", email);
                    bundle.putString("address", address);
                    bundle.putString("last_contact_date", date);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
        }
    }
}
