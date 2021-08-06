package com.example.project1;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NavAdapter_JS extends RecyclerView.Adapter<NavAdapter_JS.NavViewHolder> {
    private String[] navList;
    private int[] drawablesResId;
    public NavAdapter_JS(String[] navList, int[] drawablesResId){
        this.navList=navList;
        this.drawablesResId=drawablesResId;

    }

    @Override
    public NavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.nav_view_js,parent,false);
        return new NavViewHolder(view);
    }


    @Override
    public void onBindViewHolder(NavViewHolder holder, int position) {
        String title=navList[position];
        int Id=drawablesResId[position];
        holder.description.setText(title);
        holder.icon.setImageResource(Id);


    }

    @Override
    public int getItemCount() {
        return navList.length;
    }
    class NavViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView description;
        RelativeLayout IndNav;
        private final Context context;
        public NavViewHolder( View itemView) {
            super(itemView);
            icon=(ImageView)itemView.findViewById(R.id.iconView);
            description=(TextView)itemView.findViewById(R.id.Description);
            IndNav=(RelativeLayout)itemView.findViewById(R.id.entire_indi_nav);
            context=itemView.getContext();
            IndNav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Intent intent;
                    Intent intent1 = null;
                    switch (getAdapterPosition()){
                        case 1:
                            intent1 = new Intent(context,mytask.class);
                            break;
                        case 2:
                            intent1 = new Intent(context,dashboard.class);
                            break;
                        case 3:
                            intent1 = new Intent(context,mytask.class);
                            break;
                        case 4:
                            intent1 = new Intent(context,mySchedule.class);
                            break;
                        case 5:
                            intent1 = new Intent(context,myfiles.class);
                            break;
                        case 6:
                            intent1 = new Intent(context,dashboard.class);
                            break;
                        case 7:
                            intent1 = new Intent(context,Referal.class);
                            break;
                        case 8:
                            intent1 = new Intent(context,faq.class);
                            break;
                        case 9:
                            intent1 = new Intent(context,dashboard.class);
                            break;
                        default:
                            intent1 = new Intent(context,dashboard.class);
                            break;
                    }
                    intent = intent1;
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                }
            });

        }
}


}
