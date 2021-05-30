package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyMailAdapter extends RecyclerView.Adapter<MailViewHolder> {
    private String name;
    private String sub;
    private String context;

    public MyMailAdapter(String name, String sub, String context){
        this.name=name;
        this.sub=sub;
        this.context=context;
    }


    @Override
    public MailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater MailInflater=LayoutInflater.from(parent.getContext());
        View Mail_View = MailInflater.inflate(R.layout.my_mail_view,parent,false);
        return new MailViewHolder(Mail_View);
    }

    @Override
    public void onBindViewHolder(@NonNull MailViewHolder holder, int position) {
       for(int i=0;i<position;++i) {
           holder.name.setText(name);
           holder.subject.setText(sub);
           holder.Mail.setText(context);
       }

    }

    @Override
    public int getItemCount() {

        return 20;
    }
}

class MailViewHolder extends RecyclerView.ViewHolder{

    TextView name;
    TextView subject;
    TextView Mail;

    public MailViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.email_name);
        subject=itemView.findViewById(R.id.email_subject);
        Mail=itemView.findViewById(R.id.email_content);
    }
}
