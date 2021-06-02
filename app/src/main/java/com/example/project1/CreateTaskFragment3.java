package com.example.project1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateTaskFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateTaskFragment3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String insertTask = "https://demotic-recruit.000webhostapp.com/task_insert.php";

    int jobTypeId, jobId;
    String  restrictionNumber, emails, name, description, skillRequirements, accuracy, startDate, endDate, deadlineW, deadlineD, deadlineH, acceptedExtensions;
    int companyId;

    EditText Emails;
    EditText Name;
    EditText Description;
    EditText SkillRequirements;
    EditText Accuracy;
    EditText StartDate;
    EditText EndDate;
    EditText DeadlineW;
    EditText DeadlineD;
    EditText DeadlineH;
    EditText AcceptedExtension;

    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = dateFormat.format(date);

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateTaskFragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateTaskFragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateTaskFragment3 newInstance(String param1, String param2) {
        CreateTaskFragment3 fragment = new CreateTaskFragment3();
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
        View view =  inflater.inflate(R.layout.create_task_2, container, false);
        Button save = view.findViewById(R.id.button30);
         Emails = view.findViewById(R.id.editTextTextEmailAddress);
         Name = view.findViewById(R.id.editTextTextPersonName59);
         Description = view.findViewById(R.id.editTextTextPersonName60);
         SkillRequirements = view.findViewById(R.id.editTextTextPersonName61);
         Accuracy = view.findViewById(R.id.editTextTextPersonName62);
         StartDate = view.findViewById(R.id.editTextTextPersonName63);
         EndDate = view.findViewById(R.id.editTextTextPersonName64);
         DeadlineW = view.findViewById(R.id.editTextTextPersonName65);
         DeadlineD = view.findViewById(R.id.editTextTextPersonName66);
         DeadlineH = view.findViewById(R.id.editTextTextPersonName67);
         AcceptedExtension = view.findViewById(R.id.editTextTextPersonName68);

        Bundle bundle2 = this.getArguments();
        jobTypeId = bundle2.getInt("jobTypeId");
        jobId = bundle2.getInt("jobId");
        companyId = bundle2.getInt("companyId");
        restrictionNumber = bundle2.getString("restrictionNumber");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
        return view;
    }

    private void insertData() {

       emails = Emails.getText().toString().trim();
       name = Name.getText().toString().trim();
       description = Description.getText().toString().trim();
       skillRequirements = SkillRequirements.getText().toString().trim();
       accuracy = Accuracy.getText().toString().trim();
       startDate = StartDate.getText().toString().trim();
       endDate = EndDate.getText().toString().trim();
       deadlineW = DeadlineW.getText().toString().trim();
       deadlineD = DeadlineD.getText().toString().trim();
       deadlineH = DeadlineH.getText().toString().trim();
       acceptedExtensions = AcceptedExtension.getText().toString().trim();

        StringRequest insertRequest = new StringRequest(Request.Method.POST, insertTask, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent intent = new Intent(getContext(),TaskCreationSuccess.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> param = new HashMap<String, String>();
                param.put("job_id", String.valueOf(jobId));
                param.put("company_id", String.valueOf(companyId));
                param.put("name", name);
                param.put("job_type_id", String.valueOf(jobTypeId));
                param.put("restriction_numbers", restrictionNumber);
                param.put("emails", emails);
                param.put("description",description);
                param.put("skill_requirement",skillRequirements);
                param.put("accuracy", accuracy);
                param.put("start_date", startDate);
                param.put("end_date",endDate);
                param.put("deadline_weeks",deadlineW);
                param.put("deadline_days",deadlineD);
                param.put("deadline_hours",deadlineH);
                param.put("accepted_extensions",acceptedExtensions);
                param.put("created_date",strDate);

                return param;
            }
        };




        Volley.newRequestQueue(getContext()).add(insertRequest);

    }
}