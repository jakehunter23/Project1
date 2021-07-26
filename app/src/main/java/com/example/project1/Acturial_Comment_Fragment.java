package com.example.project1;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Acturial_Comment_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Acturial_Comment_Fragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String url = "https://demotic-recruit.000webhostapp.com/applicant_comment_insert.php";
    String title;
    EditText Title;
    String modifiDate;
    EditText ModifidDate;
    String notes;
    EditText Notes;
    private Acturial_CommentRecAdapter acturial_commentRecAdapter;
    RecyclerView ActComRec;
    private List<Acturial_ComUser> userList;
    private String urls = "https://demotic-recruit.000webhostapp.com/applicant_comment_fetch.php";

    public Acturial_Comment_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Acturial_Comment_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Acturial_Comment_Fragment newInstance(String param1, String param2) {
        Acturial_Comment_Fragment fragment = new Acturial_Comment_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View views = inflater.inflate(R.layout.fragment_acturial__comment, container, false);
        ActComRec =views.findViewById(R.id.act_comment_card);
        ActComRec.setLayoutManager(new LinearLayoutManager(getContext()));
        userList = new ArrayList<>();

        LoadAllUsers();
        AppCompatButton btn_add_comment= views.findViewById(R.id.act_add_comment);
        btn_add_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                LayoutInflater factory = LayoutInflater.from(view.getContext());
                final View views = factory.inflate(R.layout.act_cmt_dialogue, null);
                alertDialog.setView(views);
                Title = views.findViewById(R.id.addTitle);
                ModifidDate = views.findViewById(R.id.modify_dates);
                Notes = views.findViewById(R.id.addnotes);
                AppCompatButton save = views.findViewById(R.id.note_button_save);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        title = Title.getText().toString().trim();
                        modifiDate = ModifidDate.getText().toString().trim();
                        notes = Notes.getText().toString().trim();
                        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                Title.setText("");
                                ModifidDate.setText("");
                                Notes.setText("");
                                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {


                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> param = new HashMap<String, String>();
                                param.put("title", title);
                                param.put("created_date", modifiDate);
                                param.put("content", notes);

                                return param;
                            }
                        };
                        RequestQueue queue = Volley.newRequestQueue(getContext());
                        queue.add(request);


                    }
                });
                alertDialog.show();


            }

    });
        return views;
    }
    private void LoadAllUsers(){
        JsonArrayRequest request = new JsonArrayRequest(urls, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String id = jsonObject.getString("id").trim();
                        String titles = jsonObject.getString("title").trim();
                        String edit_date = jsonObject.getString("created_date").trim();
                        String context = jsonObject.getString("content").trim();
                        Acturial_ComUser users = new Acturial_ComUser();
                        users.setId(id);
                        users.setTitle(titles);
                        users.setEdited(edit_date);
                        users.setContent(context);
                        userList.add(users);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    acturial_commentRecAdapter=new Acturial_CommentRecAdapter(getContext(),userList);
                    ActComRec.setAdapter(acturial_commentRecAdapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);

    }


}