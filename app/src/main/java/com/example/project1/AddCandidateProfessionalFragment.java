package com.example.project1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCandidateProfessionalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCandidateProfessionalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String fetchSource = "https://demotic-recruit.000webhostapp.com/source_fetch.php";
    String fetchOwnership = "https://demotic-recruit.000webhostapp.com/ownership_fetch.php";



    Spinner ownership, source;
    ArrayList<String> ownershipList;
    ArrayList<String> sourceList;

    ArrayAdapter ownershipAdapter;
    ArrayAdapter sourceAdapter;
    EditText CurrentTitle ;
    EditText CompanyName ;
    EditText CurrentSalary ;
    EditText HourlyRateLow ;
    EditText DesiredSalary;
    EditText HourlyRateHigh;
    String id, firstName, lastName, status, statusId, email, phoneNumber, address, city, zipcode, type, preference, sourceId, ownerId, currentSalary, desiredSalary, stateId, countryId, title, companyName, hourlyRateLow, hourlyRateHigh, talent, skill, degree, comments, availabilityDate, job, accessibility, createdDate, image_data, flag;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCandidateProfessionalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCandidateProfessionalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCandidateProfessionalFragment newInstance(String param1, String param2) {
        AddCandidateProfessionalFragment fragment = new AddCandidateProfessionalFragment();
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
        View view = inflater.inflate(R.layout.candidate_profession_info, container, false);
        Button toSkill = view.findViewById(R.id.button11);
        source=view.findViewById(R.id.spinner24);
        ownership=view.findViewById(R.id.spinner25);
        sourceList = new ArrayList<>();
        ownershipList = new ArrayList<>();

         CurrentTitle = view.findViewById(R.id.editTextTextPersonName36);
        CompanyName = view.findViewById(R.id.editTextTextPersonName37);
        CurrentSalary = view.findViewById(R.id.editTextTextPersonName38);
        HourlyRateLow = view.findViewById(R.id.editTextTextPersonName39);
        DesiredSalary = view.findViewById(R.id.editTextTextPersonName40);
        HourlyRateHigh = view.findViewById(R.id.editTextTextPersonName41);
        Spinner cand=view.findViewById(R.id.spinner22);
        Spinner emp=view.findViewById(R.id.spinner23);

        Bundle bundle = getArguments();
        id = bundle.getString("id");
            firstName = bundle.getString("firstName");
            lastName = bundle.getString("lastName");
            statusId = bundle.getString("statusId");
            email = bundle.getString("mainMail");
            phoneNumber = bundle.getString("contactNumber");
            address = bundle.getString("address");
            city = bundle.getString("city");
            zipcode = bundle.getString("zipcode");
            stateId = bundle.getString("stateId");
            countryId = bundle.getString("countryId");
            status = bundle.getString("status");
            title = bundle.getString("current_title");
            companyName = bundle.getString("company_name");
            type = bundle.getString("type");
            preference = bundle.getString("preference");
            ownerId = bundle.getString("contact_type_id");
            sourceId = bundle.getString("source_id");
            currentSalary = bundle.getString("current_salary");
            desiredSalary = bundle.getString("desired_salary");
            hourlyRateHigh = bundle.getString("hourly_rate_high");
            hourlyRateLow = bundle.getString("hourly_rate_low");
            talent = bundle.getString("talent");
            skill = bundle.getString("skill");
            degree = bundle.getString("degree");
            comments = bundle.getString("comments");
            availabilityDate = bundle.getString("availability_date");
            job = bundle.getString("job");
            accessibility = bundle.getString("accessibility");
            createdDate = bundle.getString("created_date");
        image_data = bundle.getString("image_data");
        flag = bundle.getString("flag");

        if(id!=null) {

            CurrentTitle.setText(title);
            CompanyName.setText(companyName);
            CurrentSalary.setText(currentSalary);
            HourlyRateLow.setText(hourlyRateLow);
            DesiredSalary.setText(desiredSalary);
            HourlyRateHigh.setText(hourlyRateHigh);

            if(ownerId!=null){
                ownership.setSelection(Integer.parseInt(ownerId));
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Ownership",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item", Integer.parseInt(ownerId));
                prefEditor2.commit();
            }

            if(sourceId!=null){
                source.setSelection(Integer.parseInt(sourceId));
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Source",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item3", Integer.parseInt(sourceId));
                prefEditor2.commit();
            }
        }





            ArrayList<String> can_List=new ArrayList<>();
        can_List.add("CandidateType1");
        can_List.add("CandidateTpye2");
        can_List.add("CandidateType3");
        can_List.add("All");
        ArrayAdapter CanAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,can_List);
        CanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cand.setAdapter(CanAdapter);

        ArrayList<String> emp_List=new ArrayList<>();
        emp_List.add("Employment Pref Type1");
        emp_List.add("Employment Pref Type2");
        emp_List.add("Employment Pref Type3");
        emp_List.add("All");
        ArrayAdapter EmpAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,emp_List);
        EmpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emp.setAdapter(EmpAdapter);




        loadOwnership();
        loadSource();



        ownership.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ownerId = String.valueOf(ownership.getSelectedItemPosition());
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Ownership",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item", Integer.parseInt(ownerId));
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        source.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sourceId = String.valueOf(source.getSelectedItemPosition());
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Source",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item3", Integer.parseInt(sourceId));
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        toSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNext();


            }
        });
        return view;
    }

    private void userNext() {
        String currentTitle = CurrentTitle.getText().toString().trim();
        String companyName = CompanyName.getText().toString().trim();
        String currentlysalary = CurrentSalary.getText().toString().trim();
        String hourlyratelow = HourlyRateLow.getText().toString().trim();
        String desiredsalary = DesiredSalary.getText().toString().trim();
        String hourlyratehigh = HourlyRateHigh.getText().toString().trim();
        if (currentTitle.isEmpty()) {
            Toast.makeText(getContext(), "Please Enter Current Title", Toast.LENGTH_LONG).show();
            CurrentTitle.setError("Please Enter Current Title");
            CurrentTitle.requestFocus();
            return;
        }
        if (companyName.isEmpty()) {
            Toast.makeText(getContext(), "Please Enter Company Name", Toast.LENGTH_LONG).show();
            CompanyName.setError("Please Enter Company Name");
            CompanyName.requestFocus();
            return;
        }
        if (currentlysalary.isEmpty()) {
            Toast.makeText(getContext(), "Please Enter Currently Salary", Toast.LENGTH_LONG).show();
            CurrentSalary.setError("Please Enter Currently Salary");
            CurrentSalary.requestFocus();
            return;
        }
        if (hourlyratelow.isEmpty()) {
            Toast.makeText(getContext(), "Please Enter Hourly Rate Low", Toast.LENGTH_LONG).show();
            HourlyRateLow.setError("Please Enter Hourly Rate Low");
            HourlyRateLow.requestFocus();
            return;
        }
        if (desiredsalary.isEmpty()) {
            Toast.makeText(getContext(), "Please Enter Desired Salary", Toast.LENGTH_LONG).show();
            DesiredSalary.setError("Please Enter Desired Salary");
            DesiredSalary.requestFocus();
            return;
        }
        if (hourlyratehigh.isEmpty()) {
            Toast.makeText(getContext(), "Please Hourly Rate High", Toast.LENGTH_LONG).show();
            HourlyRateHigh.setError("Please Hourly Rate High");
            HourlyRateHigh.requestFocus();
            return;
        }
        Bundle bundle1 = new Bundle();
        bundle1.putString("id", id);
        bundle1.putString("firstName",firstName);
        bundle1.putString("lastName", lastName);
        bundle1.putString("status", status);
        bundle1.putString("status_id", statusId);
        bundle1.putString("mainMail" , email);
        bundle1.putString("contactNumber", phoneNumber);
        bundle1.putString("address" , address);
        bundle1.putString("city" , city);
        bundle1.putString("zipcode", zipcode);
        bundle1.putString("stateId" , stateId);
        bundle1.putString("countryId", countryId);
        bundle1.putString("title" , CurrentTitle.getText().toString().trim());
        bundle1.putString("companyName" , CompanyName.getText().toString().trim());
        bundle1.putString("type", String.valueOf(0));
        bundle1.putString("preference" , String.valueOf(0));
        bundle1.putString("sourceId" , sourceId);
        bundle1.putString("ownerId" , ownerId);
        bundle1.putString("currentSalary" , CurrentSalary.getText().toString().trim());
        bundle1.putString("hourlyRatel" , HourlyRateLow.getText().toString().trim());
        bundle1.putString("desiredSalary" , DesiredSalary.getText().toString().trim());
        bundle1.putString("hourlyRateh" , HourlyRateHigh.getText().toString().trim());
        bundle1.putString("talent", talent);
        bundle1.putString("skill", skill);
        bundle1.putString("degree", degree);
        bundle1.putString("comments", comments);
        bundle1.putString("availability_date", availabilityDate);
        bundle1.putString("job", job);
        bundle1.putString("accessibility", accessibility);
        bundle1.putString("created_date", createdDate);
        bundle1.putString("image_data", image_data);
        bundle1.putString("flag",flag);

        ((AddCandidateActivity)getActivity()).addFragmentOnTop(new AddCandidateSkillFragment(), bundle1);
        ((AddCandidateActivity)getActivity()).changeViewForSkill();
    }

    private void loadSource() {
        StringRequest sourceRequest = new StringRequest(Request.Method.GET, fetchSource, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String divisionName = countryObject.getString("name");
                        sourceList.add(divisionName);
                    }

                    sourceAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,sourceList);
                    sourceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    source.setAdapter(sourceAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Source", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item3",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        source.setSelection(spinnerValue,true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getContext()).add(sourceRequest);
    }

    private void loadOwnership() {
        StringRequest ownershipRequest = new StringRequest(Request.Method.GET, fetchOwnership, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("name");
                        ownershipList.add(countryName);
                    }

                    ownershipAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,ownershipList);
                    ownershipAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ownership.setAdapter(ownershipAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Ownership", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        ownership.setSelection(spinnerValue,true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getContext()).add(ownershipRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("Ownership",0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("spinner_item",0);
        prefEditor.commit();

        SharedPreferences sharedPref3 = getActivity().getSharedPreferences("Source",0);
        SharedPreferences.Editor prefEditor3 = sharedPref3.edit();
        prefEditor3.putInt("spinner_item3",0);
        prefEditor3.commit();
    }
}