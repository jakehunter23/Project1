package com.example.project1;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardRec1Adapter extends RecyclerView.Adapter<DashboardRec1Adapter.DashRec1Viewholder> {
    String[] titles;
    int[] numbers;
    int [] bg;


    public DashboardRec1Adapter(String[] titles, int[] numbers, int [] bg) {
        this.numbers = numbers;
        this.titles = titles;
        this.bg =bg;


    }

    @NonNull
    @Override
    public DashRec1Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater dash1Inflater = LayoutInflater.from(parent.getContext());
        View indiView = dash1Inflater.inflate(R.layout.dashboard_card_rec_item, parent, false);
        return new DashboardRec1Adapter.DashRec1Viewholder(indiView);
    }

    @Override
    public void onBindViewHolder(@NonNull DashRec1Viewholder holder, int position) {
        holder.title.setText(titles[position]);
        holder.number.setText(String.valueOf(numbers[position]));
        holder.bg.setCardBackgroundColor(bg[position]);

    }


    @Override
    public int getItemCount() {
        return titles.length;
    }


    class DashRec1Viewholder extends RecyclerView.ViewHolder {
        TextView title;
        TextView number;
        CardView bg;


        public DashRec1Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView330);
            number = itemView.findViewById(R.id.textView331);
            bg = itemView.findViewById(R.id.dash_item_bg);

        }
    }
}
