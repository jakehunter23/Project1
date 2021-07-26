package com.example.project1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CandidateNoteRecAdapter extends RecyclerView.Adapter<CandidateNoteRecAdapter.CandNoteViewHolder> {
    Context context;
    List<CandidateNotesUser> userList;
    private String urlEdit = "https://demotic-recruit.000webhostapp.com/candidate_note_edit.php";
    private String urlDelete = "https://demotic-recruit.000webhostapp.com/candidate_note_delete.php";

    public CandidateNoteRecAdapter(Context context, List<CandidateNotesUser> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public CandNoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.candidate_notes_rec_item,parent,false);
        return new CandNoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CandNoteViewHolder holder, int position) {
        CandidateNotesUser user = userList.get(position);
        holder.Title.setText(user.getTitle());
        holder.Create_date.setText(user.getCreated());
        holder.Modified_Date.setText(user.getEdited());
        holder.Context.setText(user.getContent());
        holder.Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View views = LayoutInflater.from(context).inflate(R.layout.edit_dia,null);
                EditText Title = views.findViewById(R.id.addTitle);
                EditText CreatedDate = views.findViewById(R.id.created_dates);
                EditText ModifidDate = views.findViewById(R.id.modify_dates);
                EditText Notes = views.findViewById(R.id.addnotes);
                Title.setText(user.getTitle());
                CreatedDate.setText(user.getCreated());
                ModifidDate.setText(user.getEdited());
                Notes.setText(user.getContent());
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Edit "+user.getTitle());
                builder.setView(views);
                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = Title.getText().toString().trim();
                        String creteDate = CreatedDate.getText().toString().trim();
                        String modifiDate = ModifidDate.getText().toString().trim();
                        String notes = Notes.getText().toString().trim();
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlEdit,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();

                            }
                        }){

                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String,String> params = new HashMap<>();
                                params.put("title",title);
                                params.put("created_date",creteDate);
                                params.put("edited_date",modifiDate);
                                params.put("content",notes);
                                params.put("id", user.getId());
                                return params;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(context);
                        requestQueue.add(stringRequest);
                        builder.show();



                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });





            }
        });
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete User");
                builder.setMessage("Cofirm to Delete " +user.getTitle());
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDelete,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject object = new JSONObject(response);
                                            String check = object.getString("state");
                                            if (check.equals("delete")){
                                                Delete(position);
                                                Toast.makeText(context, "Delete Successfull", Toast.LENGTH_SHORT).show();
                                            }else {
                                                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String,String> deleteParams = new HashMap<>();
                                deleteParams.put("id",user.getId());
                                return deleteParams;
                            }
                        };
                        RequestQueue queue = Volley.newRequestQueue(context);
                        queue.add(stringRequest);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });



    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class CandNoteViewHolder extends RecyclerView.ViewHolder {
        TextView Title,Create_date,Modified_Date,Context;
        CardView Edit,Delete;

        public CandNoteViewHolder(@NonNull View itemView) {
            super(itemView);
            Title=itemView.findViewById(R.id.title_note);
            Create_date=itemView.findViewById(R.id.created_date);
            Modified_Date=itemView.findViewById(R.id.edited_date);
            Context=itemView.findViewById(R.id.context);
            Edit=itemView.findViewById(R.id.con_edit_note_card);
            Delete=itemView.findViewById(R.id.con_del_note_card);

        }
    }
    public void Delete(int item){
        userList.remove(item);
        notifyItemRemoved(item);
    }
}
