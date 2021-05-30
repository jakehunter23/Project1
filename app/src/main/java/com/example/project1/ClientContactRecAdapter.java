package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClientContactRecAdapter extends RecyclerView.Adapter<ClientContactRecAdapter.ContactHolder>{

    String [] Name;
    String [] last_date;
    String [] number;
    String [] email;
    String [] added_date;

    ClientContactRecAdapter(String [] Name, String [] last_date, String [] number, String [] email, String [] added_date){
        this.Name=Name;
        this.last_date=last_date;
        this.number=number;
        this.email=email;
        this.added_date=added_date;
    }
    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater contactInflater = LayoutInflater.from(parent.getContext());
        View indiView = contactInflater.inflate(R.layout.client_contact_rec_item,parent,false);
        return new ClientContactRecAdapter.ContactHolder(indiView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {

        holder.name.setText(Name[position]);
        holder.date.setText(last_date[position]);
        holder.number.setText(number[position]);
        holder.email.setText(email[position]);
        holder.added.setText(added_date[position]);
    }

    @Override
    public int getItemCount() {
        return Name.length;
    }

    class ContactHolder extends RecyclerView.ViewHolder {
        com.wajahatkarim3.easyflipview.EasyFlipView flipCon;
        TextView name;
        TextView date;
        TextView number;
        TextView email;
        TextView added;
        public ContactHolder(@NonNull View itemView) {
            super(itemView);

            flipCon = itemView.findViewById(R.id.con_card);
            name = flipCon.findViewById(R.id.clicon_name);
            date = flipCon.findViewById(R.id.clicon_up_date);
            number = flipCon.findViewById(R.id.clicon_number);
            email=flipCon.findViewById(R.id.clicon_email);
            added = flipCon.findViewById(R.id.clicon_timestamp);
        }
    }
}
