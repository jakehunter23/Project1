package com.example.project1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

public class Acturial_CommentRecAdapter extends RecyclerView.Adapter<Acturial_CommentRecAdapter.ActPubView>{
    Context context;
    List<Acturial_ComUser> userList;
    private String urlDelete = "https://demotic-recruit.000webhostapp.com/applicant_comment_delete.php";

    public Acturial_CommentRecAdapter(Context context, List<Acturial_ComUser> userList) {
        this.context = context;
        this.userList = userList;
    }
    @NonNull
    @Override
    public ActPubView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.acturial_com_rec_item,parent,false);
        return new ActPubView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActPubView holder, int position) {
        Acturial_ComUser user = userList.get(position);
        holder.Title.setText(user.getTitle());
        holder.Modified_Date.setText(user.getEdited());
        holder.Context.setText(user.getContent());
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

    class ActPubView extends RecyclerView.ViewHolder {
        TextView Title,Modified_Date,Context;
        CardView Delete;

        public ActPubView(@NonNull View itemView) {
            super(itemView);
            Title=itemView.findViewById(R.id.title_note);
            Modified_Date=itemView.findViewById(R.id.edited_date);
            Context=itemView.findViewById(R.id.context);
            Delete=itemView.findViewById(R.id.con_del_note_card);
        }
    }
    public void Delete(int item){
        userList.remove(item);
        notifyItemRemoved(item);
    }

}
