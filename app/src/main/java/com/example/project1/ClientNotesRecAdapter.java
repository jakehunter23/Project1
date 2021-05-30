package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClientNotesRecAdapter extends RecyclerView.Adapter<ClientNotesRecAdapter.NotesViewHolder> {

    String [] Name;
    String [] Type;
    String [] Time;
    String [] last;
    String [] timestamp;
    String Note;

    public ClientNotesRecAdapter( String [] Name, String [] Type, String [] Time, String [] last, String [] timestamp, String Note){
        this.Name = Name;
        this.Type = Type;
        this.Time = Time;
        this.last = last;
        this.timestamp = timestamp;
        this.Note = Note;

    }
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater notesInflater = LayoutInflater.from(parent.getContext());
        View indiView = notesInflater.inflate(R.layout.client_notes_rec_item,parent,false);
        return new ClientNotesRecAdapter.NotesViewHolder(indiView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.name.setText(Name[position]);
        holder.type.setText(Type[position]);
        holder.time.setText(Time[position]);
        holder.latest.setText(last[position]);
        holder.date.setText(timestamp[position]);
        holder.text.setText(Note);
    }

    @Override
    public int getItemCount() {
        return Name.length;
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView type;
        TextView time;
        TextView latest;
        TextView date;
        TextView text;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.client_name);
            type = itemView.findViewById(R.id.textView220);
            time = itemView.findViewById(R.id.textView222);
            latest = itemView.findViewById(R.id.textView224);
            date = itemView.findViewById(R.id.textView226);
            text = itemView.findViewById(R.id.textView227);
        }
    }
}
