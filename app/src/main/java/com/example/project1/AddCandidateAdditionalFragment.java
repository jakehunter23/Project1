package com.example.project1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCandidateAdditionalFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class AddCandidateAdditionalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String insertCandidate = "https://demotic-recruit.000webhostapp.com/candidate_insert.php";
    String updateCandidate = "https://demotic-recruit.000webhostapp.com/candidate_update.php";

    String id, firstName, lastName, statusId, status, email, phoneNumber, address, city, zipcode, currentSalary, hourlyRateLow, desiredSalary, hourlyRateHigh, title, companyName, skill, talent, degree;
    String stateId, countryId, pdf, candidateType, preference, sourceId, ownerId, type, comments, availabilityDate, job, createdDate, accessibility;

    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = dateFormat.format(date);
    EditText Comments, Job;
    EditText AvailabilityDate;

    Spinner Accessibility;

    String date_selected_availablity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCandidateAdditionalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCandidateAdditionalFragment newInstance(String param1, String param2) {
        AddCandidateAdditionalFragment fragment = new AddCandidateAdditionalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AddCandidateAdditionalFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.candidate_additional_info, container, false);
        Button toCandAct = view.findViewById(R.id.button12);
        Comments = view.findViewById(R.id.editTextTextPersonName5);
        Job=view.findViewById(R.id.ejobEditText);
        Accessibility=view.findViewById(R.id.accessSpinner);
        AvailabilityDate = view.findViewById(R.id.availability_date);



        ArrayList<String> access_List=new ArrayList<>();
        access_List.add("Private");
        access_List.add("Public");
        access_List.add("Dummy1");
        access_List.add("Dummy2");

        ArrayAdapter accessAdapter= new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, access_List);
        accessAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Accessibility.setAdapter(accessAdapter);

        view.findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdate_picker_dialog(AvailabilityDate);
                // AvailabilityDate.setText(date_selected_availablity);
            }
        });


        Bundle bundle = this.getArguments();
        id = bundle.getString("id");

                firstName = bundle.getString("firstName");
                lastName = bundle.getString("lastName");
                status = bundle.getString("status");
                statusId = bundle.getString("status_id");
                email = bundle.getString("mainMail");
                phoneNumber = bundle.getString("contactNumber");
                address = bundle.getString("address");
                city = bundle.getString("city");
                zipcode = bundle.getString("zipcode");
                stateId = bundle.getString("stateId");
                countryId = bundle.getString("countryId");
                title = bundle.getString("title");
                companyName = bundle.getString("companyName");
                type = bundle.getString("type");
                preference = bundle.getString("preference");
                sourceId = bundle.getString("sourceId");
                ownerId = bundle.getString("ownerId");
                currentSalary = bundle.getString("currentSalary");
                hourlyRateLow = bundle.getString("hourlyRatel");
                desiredSalary = bundle.getString("desiredSalary");
                hourlyRateHigh = bundle.getString("hourlyRateh");
                comments = bundle.getString("comments");
                availabilityDate = bundle.getString("availability_date");
                job = bundle.getString("job");
                accessibility = bundle.getString("accessibility");
                createdDate = bundle.getString("created_date");
                talent = bundle.getString("talent");
                skill = bundle.getString("skill");
                degree = bundle.getString("degree");

        if(id!=null) {

                Comments.setText(comments);
                Job.setText(job);
                AvailabilityDate.setText(availabilityDate);
            }

        toCandAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id!=null){
                    updateData();
                }
                else {
                    insertData();
                }

            }
        });
        return view;
    }

    private void updateData() {
        String comm = Comments.getText().toString().trim();
        job =Job.getText().toString().trim();
        availabilityDate= AvailabilityDate.getText().toString().trim();
        if (comm.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Comments",Toast.LENGTH_LONG).show();
            Comments.setError("Please Enter Comments");
            Comments.requestFocus();
            return;
        }

        StringRequest insertRequest = new StringRequest(Request.Method.POST, updateCandidate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent intent = new Intent(getContext(),CandidateSplashScreen.class);
                Bundle bundle =  new Bundle();
                bundle.putString("first_name", firstName);
                bundle.putString("last_name", lastName);
                bundle.putString("email", email);
                bundle.putString("created_date", createdDate);
                intent.putExtras(bundle);
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
                param.put("id", id);
                param.put("first_name", firstName);
                param.put("last_name", lastName);
                param.put("status", status);
                param.put("status_id", statusId);
                param.put("email", email);
                param.put("phone_number", phoneNumber);
                param.put("address", address);
                param.put("city", city);
                param.put("zipcode", zipcode);
                param.put("type", String.valueOf(type));
                param.put("preference", String.valueOf(preference));
                param.put("source_id", String.valueOf(sourceId));
                param.put("owner_id", String.valueOf(ownerId));
                param.put("current_salary", currentSalary);
                param.put("desired_salary", desiredSalary);
                param.put("state_id", String.valueOf(stateId));
                param.put("country_id", String.valueOf(countryId));
                param.put("title",title);
                param.put("company_name", companyName);
                param.put("hourly_rate_low", hourlyRateLow);
                param.put("hourly_rate_high", hourlyRateHigh);
                param.put("talent", talent);
                param.put("skill", skill);
                param.put("degree", degree);
                param.put("comments",comm);
                param.put("availability_date", availabilityDate);
                param.put("created_date", createdDate);
                param.put("job", job);
                param.put("accessibility", String.valueOf(Accessibility.getSelectedItemPosition()));



                return param;
            }
        };




        Volley.newRequestQueue(getContext()).add(insertRequest);
    }

    private void insertData() {
        comments = Comments.getText().toString().trim();
        job = Job.getText().toString().trim();
        availabilityDate= AvailabilityDate.getText().toString().trim();
        if (comments.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Comments",Toast.LENGTH_LONG).show();
            Comments.setError("Please Enter Comments");
            Comments.requestFocus();
            return;
        }

        StringRequest insertRequest = new StringRequest(Request.Method.POST, insertCandidate, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent intent = new Intent(getContext(),CandidateSplashScreen.class);
                Bundle bundle =  new Bundle();
                bundle.putString("first_name", firstName);
                bundle.putString("last_name", lastName);
                bundle.putString("email", email);
                bundle.putString("created_date", strDate);
                intent.putExtras(bundle);
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
                param.put("first_name", firstName);
                param.put("last_name", lastName);
                param.put("status", status);
                param.put("status_id", statusId);
                param.put("email", email);
                param.put("phone_number", phoneNumber);
                param.put("address", address);
                param.put("city", city);
                param.put("zipcode", zipcode);
                param.put("type", String.valueOf(type));
                param.put("preference", String.valueOf(preference));
                param.put("source_id", String.valueOf(sourceId));
                param.put("owner_id", String.valueOf(ownerId));
                param.put("current_salary", currentSalary);
                param.put("desired_salary", desiredSalary);
                param.put("state_id", String.valueOf(stateId));
                param.put("country_id", String.valueOf(countryId));
                param.put("title",title);
                param.put("company_name", companyName);
                param.put("hourly_rate_low", hourlyRateLow);
                param.put("hourly_rate_high", hourlyRateHigh);
                param.put("talent", talent);
                param.put("skill", skill);
                param.put("degree", degree);
                param.put("comments",comments);
                param.put("availability_date", availabilityDate);
                param.put("created_date", strDate);
                param.put("job", job);
                param.put("accessibility", String.valueOf(Accessibility.getSelectedItemPosition()));




                return param;
            }
        };




        Volley.newRequestQueue(getContext()).add(insertRequest);

    }

    private void showdate_picker_dialog(EditText mdate_available){

        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int date=calendar.get(Calendar.DATE);


        DatePickerDialog datePickerDialog=new DatePickerDialog(
                getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date_selected_availablity=dayOfMonth+"-"+(month+1)+"-"+year;
                        mdate_available.setText(date_selected_availablity);
                        Log.e("Date selected by user: ",date_selected_availablity);
                    }
                },
                year,month,date);
        datePickerDialog.show();
    }
}