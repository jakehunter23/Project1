package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Chat_List_Adapter extends RecyclerView.Adapter<Chat_List_Adapter.ViewHolder> {

    List<ChatList_Users> items;
    Context context;

    public Chat_List_Adapter(Context context,List<ChatList_Users>items){
        this.context=context;
        this.items=items;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        ChatList_Users chatList_users=items.get(position);
        holder.ename.setText(chatList_users.getUsername());
        holder.email.setText(chatList_users.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MessageAcitvity.class);
                intent.putExtra("userid",chatList_users.getid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView ename,email;
        View itemView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ename=itemView.findViewById(R.id.name_of_user_id);
            email=itemView.findViewById(R.id.email_id_user);
            this.itemView=itemView;
        }
    }
}
