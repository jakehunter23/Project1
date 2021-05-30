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
 * Use the {@link AddContactProfessionalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddContactProfessionalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String fetchContact ="https://demotic-recruit.000webhostapp.com/contact_type_fetch.php";
    String fetchDivision ="https://demotic-recruit.000webhostapp.com/division_fetch.php";
    String fetchSource="https://demotic-recruit.000webhostapp.com/source_fetch.php";
    String fetchReports="https://demotic-recruit.000webhostapp.com/user_fetch.php";
    String fetchIndustry="https://demotic-recruit.000webhostapp.com/industry_fetch.php";

    Spinner contactType, division, source, ReportsTo, industry;
    ArrayList<String> contactTypeList;
    ArrayList<String> divisionList;
    ArrayList<String> sourceList;
    ArrayList<String> reportsToList;
    ArrayList<String> industryList;

    ArrayAdapter contactAdapter;
    ArrayAdapter divisionAdapter;
    ArrayAdapter sourceAdapter;
    ArrayAdapter reportsAdapter;
    ArrayAdapter industryAdapter;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddContactProfessionalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddContactProfessionalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddContactProfessionalFragment newInstance(String param1, String param2) {
        AddContactProfessionalFragment fragment = new AddContactProfessionalFragment();
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
        View view = inflater.inflate(R.layout.add_contact_professional, container, false);
        Button next = view.findViewById(R.id.button42);
        contactType =view.findViewById(R.id.spinner62);
        division =view.findViewById(R.id.spinner63);
        source=view.findViewById(R.id.spinner64);
        ReportsTo=view.findViewById(R.id.spinner65);
        industry=view.findViewById(R.id.spinner66);
        contactTypeList = new ArrayList<>();
        divisionList = new ArrayList<>();
        sourceList =new ArrayList<>();
        reportsToList=new ArrayList<>();
        industryList=new ArrayList<>();

        loadContactType();
        loadDivision();
        loadSource();
        loadReportsTo();
        loadIndustry();

        contactType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int SelectedContactPosition = contactType.getSelectedItemPosition();
                SharedPreferences sharedPref = getActivity().getSharedPreferences("ContactType",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_item1",SelectedContactPosition);
                prefEditor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        division.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int SelectedDivisionPosition = division.getSelectedItemPosition();
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Division",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item2",SelectedDivisionPosition);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        source.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int SelectedSourcePosition = source.getSelectedItemPosition();
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Source",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item3",SelectedSourcePosition);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ReportsTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int SelectedReportsPosition = ReportsTo.getSelectedItemPosition();
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("ReportsTo",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item4",SelectedReportsPosition);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        industry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int SelectedIndustryPosition = industry.getSelectedItemPosition();
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Industry",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item5",SelectedIndustryPosition);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AddContactActivity)getActivity()).addFragmentOnTop(new AddContactAAdditionalFragment());
                ((AddContactActivity)getActivity()).changeViewForAdditional();
            }
        });
        return view;
    }

    private void loadIndustry() {
        StringRequest IndustryRequest = new StringRequest(Request.Method.GET, fetchIndustry, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String divisionName = countryObject.getString("name");
                        industryList.add(divisionName);
                    }

                    industryAdapter =new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,industryList);
                    industryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    industry.setAdapter(industryAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Industry", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item5",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        industry.setSelection(spinnerValue,true);
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
        Volley.newRequestQueue(getContext()).add(IndustryRequest);
    }

    private void loadReportsTo() {
        StringRequest ReportsRequest = new StringRequest(Request.Method.GET, fetchReports, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String divisionName = countryObject.getString("first_name");
                        String lastName = countryObject.getString("last_name");
                        reportsToList.add(divisionName+" "+lastName);
                    }

                    reportsAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,reportsToList);
                    reportsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    ReportsTo.setAdapter(reportsAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("ReportsTo", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item4",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        ReportsTo.setSelection(spinnerValue,true);
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
        Volley.newRequestQueue(getContext()).add(ReportsRequest);
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

    private void loadDivision() {
        StringRequest divisionRequest = new StringRequest(Request.Method.GET, fetchDivision, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String divisionName = countryObject.getString("name");
                        divisionList.add(divisionName);
                    }

                    divisionAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,divisionList);
                    divisionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    division.setAdapter(divisionAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Division", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item2",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        contactType.setSelection(spinnerValue,true);
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
        Volley.newRequestQueue(getContext()).add(divisionRequest);
    }

    private void loadContactType() {
        StringRequest contactRequest = new StringRequest(Request.Method.GET, fetchContact, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("name");
                        contactTypeList.add(countryName);
                    }

                    contactAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,contactTypeList);
                    contactAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    contactType.setAdapter(contactAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("ContactType", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item1",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        contactType.setSelection(spinnerValue,true);
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
        Volley.newRequestQueue(getContext()).add(contactRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("ContactType",0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("spinner_item1",0);
        prefEditor.commit();

        SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Division",0);
        SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
        prefEditor2.putInt("spinner_item2",0);
        prefEditor2.commit();

        SharedPreferences sharedPref3 = getActivity().getSharedPreferences("Source",0);
        SharedPreferences.Editor prefEditor3 = sharedPref3.edit();
        prefEditor3.putInt("spinner_item3",0);
        prefEditor3.commit();

        SharedPreferences sharedPref4 = getActivity().getSharedPreferences("ReportsTo",0);
        SharedPreferences.Editor prefEditor4 = sharedPref4.edit();
        prefEditor4.putInt("spinner_item4",0);
        prefEditor4.commit();

        SharedPreferences sharedPref5 = getActivity().getSharedPreferences("ReportsTo",0);
        SharedPreferences.Editor prefEditor5 = sharedPref4.edit();
        prefEditor5.putInt("spinner_item4",0);
        prefEditor5.commit();
    }
}