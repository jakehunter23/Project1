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

    String firstName, lastName, statusItem, mainMail, contactNumber, address, city, zipcode, currentSalary, hourlyRatel, desiredSalary, hourlyRateh, title, companyName;
    int stateId, countryId,  sourceId, ownerId;

    Spinner ownership, source;
    ArrayList<String> ownershipList;
    ArrayList<String> sourceList;

    ArrayAdapter ownershipAdapter;
    ArrayAdapter sourceAdapter;

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

        EditText CurrentTitle = view.findViewById(R.id.editTextTextPersonName36);
        EditText CompanyName = view.findViewById(R.id.editTextTextPersonName37);
        EditText CurrentSalary = view.findViewById(R.id.editTextTextPersonName38);
        EditText HourlyRateLow = view.findViewById(R.id.editTextTextPersonName39);
        EditText DesiredSalary = view.findViewById(R.id.editTextTextPersonName40);
        EditText HourlyRateHigh = view.findViewById(R.id.editTextTextPersonName41);
        Spinner cand=view.findViewById(R.id.spinner22);
        Spinner emp=view.findViewById(R.id.spinner23);

        Bundle bundle = this.getArguments();
        firstName = bundle.getString("firstName");
        lastName = bundle.getString("lastName");
        statusItem = bundle.getString("status");
        mainMail = bundle.getString("mainMail");
        contactNumber = bundle.getString("contactNumber");
        address = bundle.getString("address");
        city = bundle.getString("city");
        zipcode = bundle.getString("zipcode");
        stateId = bundle.getInt("stateId");
        countryId = bundle.getInt("countryId");



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
                ownerId = ownership.getSelectedItemPosition();
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Ownership",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item",ownerId);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        source.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sourceId = source.getSelectedItemPosition();
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Source",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item3",sourceId);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        toSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle1 = new Bundle();
                bundle1.putString("firstName",firstName);
                bundle1.putString("lastName", lastName);
                bundle1.putString("status", statusItem);
                bundle1.putString("mainMail" , mainMail);
                bundle1.putString("contactNumber", contactNumber);
                bundle1.putString("address" , address);
                bundle1.putString("city" , city);
                bundle1.putString("zipcode", zipcode);
                bundle1.putInt("stateId" , stateId);
                bundle1.putInt("countryId", countryId);
                bundle1.putString("title" , CurrentTitle.getText().toString().trim());
                bundle1.putString("companyName" , CompanyName.getText().toString().trim());
                bundle1.putInt("type", 0);
                bundle1.putInt("preference" , 0);
                bundle1.putInt("sourceId" , sourceId);
                bundle1.putInt("ownerId" , ownerId);
                bundle1.putString("currentSalary" , CurrentSalary.getText().toString().trim());
                bundle1.putString("hourlyRatel" , HourlyRateLow.getText().toString().trim());
                bundle1.putString("desiredSalary" , DesiredSalary.getText().toString().trim());
                bundle1.putString("hourlyRateh" , HourlyRateHigh.getText().toString().trim());

                ((AddCandidateActivity)getActivity()).addFragmentOnTop(new AddCandidateSkillFragment(), bundle1);
                ((AddCandidateActivity)getActivity()).changeViewForSkill();
            }
        });
        return view;
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