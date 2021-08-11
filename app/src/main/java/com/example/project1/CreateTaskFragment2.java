package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
 * Use the {@link CreateTaskFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateTaskFragment2 extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String fetchJob = "https://demotic-recruit.000webhostapp.com/job_fetch.php";
    Button next;
    int jobTypeId, jobId, res1=1, res2, res3, res4;
    String  restrictionNumber, designation, createdDate;
    String companyId;

    List<JobModel> jobList = new ArrayList<>();

    CardView item1;
    CardView item2;
    CardView item3;
    CardView item4;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateTaskFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateTaskFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateTaskFragment2 newInstance(String param1, String param2) {
        CreateTaskFragment2 fragment = new CreateTaskFragment2();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.create_task_1, container, false);
        item1 =view.findViewById(R.id.task2_card_item);
        item2 = view.findViewById(R.id.task2_card_item2);
        item2.setElevation(0);
        item3 = view.findViewById(R.id.task2_card_item4);
        item3.setElevation(0);
        item4 =view.findViewById(R.id.task2_card_item3);
        item4.setElevation(0);
        next = view.findViewById(R.id.button29);

        Bundle bundle1 = this.getArguments();
        jobTypeId = bundle1.getInt("jobTypeId");
        designation = bundle1.getString("designation");
        createdDate = bundle1.getString("createdDate");



        fetchJobInfo();
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item1.getCardElevation()==0){
                    item1.setElevation(25);
                    res1 = 1;
                    checkForNext();
                }
                else{
                    item1.setElevation(0);
                    res1 = 0;
                    checkForNext();
                }
            }
        });


        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item2.getCardElevation()==0){
                    item2.setElevation(25);
                    res2 = 1;
                    checkForNext();
                }
                else{
                    item2.setElevation(0);
                    res2=0;
                    checkForNext();
                }
            }
        });


        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item3.getCardElevation()==0){
                    item3.setElevation(25);
                    res3 = 1;
                    checkForNext();
                }
                else{
                    item3.setElevation(0);
                    res3 = 0;
                    checkForNext();
                }
            }
        });


        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item4.getCardElevation()==0){
                    item4.setElevation(25);
                    res4=1;
                    checkForNext();
                }
                else{
                    item4.setElevation(0);
                    res4 = 0;
                    checkForNext();
                }
            }
        });





        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRestriction();
                Bundle bundle = new Bundle();
                bundle.putInt("jobTypeId", jobTypeId);
                bundle.putInt("jobId", jobId );
                bundle.putString("companyId", companyId);
                bundle.putString("restrictionNumber", restrictionNumber);
                ((CreateTask)getActivity()).addFragmentOnTop(new CreateTaskFragment3(), bundle);
            }
        });
        return view;
    }

    private void fetchJobInfo() {

        StringRequest statusRequest = new StringRequest(Request.Method.GET, fetchJob, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject jobObject = contactArray.getJSONObject(i);

                        jobId = jobObject.getInt("id");
                        companyId = jobObject.getString("company_id");
                        JobModel job = new JobModel();
                        job.setId(jobId);
                        job.setCompany_id(companyId);
                        jobList.add(job);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("designation", designation);
                param.put("created_date", createdDate);
                return param;
            }
        };
        Volley.newRequestQueue(getContext()).add(statusRequest);
    }

    public void checkForNext(){
        if (item1.getCardElevation() == 0 && item2.getCardElevation() == 0 && item3.getCardElevation() == 0 && item4.getCardElevation() == 0) {
            next.setVisibility(View.INVISIBLE);
        } else {
            next.setVisibility(View.VISIBLE);
        }
    }

    public void setRestriction(){
        String binaryString = String.valueOf(res1)+String.valueOf(res2)+String.valueOf(res3)+String.valueOf(res4);
        int decimal=Integer.parseInt(binaryString,2);

        switch(decimal){
            case 0:
                restrictionNumber = "";
                break;
            case 1:
                restrictionNumber = "4";
                break;
            case 2:
                restrictionNumber = "3";
                break;
            case 3:
                restrictionNumber = "3 4";
                break;
            case 4:
                restrictionNumber = "2";
                break;
            case 5:
                restrictionNumber = "2 4";
                break;
            case 6:
                restrictionNumber = "2 3";
                break;
            case 7:
                restrictionNumber = "2 3 4";
                break;
            case 8:
                restrictionNumber = "1";
                break;
            case 9:
                restrictionNumber = "1 4";
                break;
            case 10:
                restrictionNumber = "1 3";
                break;
            case 11:
                restrictionNumber = "1 3 4";
                break;
            case 12:
                restrictionNumber = "1 2";
                break;
            case 13:
                restrictionNumber = "1 2 4";
                break;
            case 14:
                restrictionNumber = "1 2 3";
                break;
            case 15:
                restrictionNumber = "1 2 3 4";
                break;
        }
    }


}