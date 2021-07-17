package com.example.project1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Maindata> datalist;
    private Activity context;
    private RoomDB database;

    public MainAdapter(Activity context, List<Maindata> datalist){
        this.context=context;
        this.datalist=datalist;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_local_stored_data,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Maindata data=datalist.get(position);

            database=RoomDB.getInstance(context);

            holder.textView.setText(data.getText());


            holder.btdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Maindata d=datalist.get(holder.getAdapterPosition());
                    database.mainDao().delete(d);
                    int position=holder.getAdapterPosition();
                    datalist.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,datalist.size());
                }
            });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView btdelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_view_role);
            btdelete=itemView.findViewById(R.id.delete_id);
        }
    }
}
