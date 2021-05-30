package com.example.project1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

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
 * Use the {@link AddClientGeneralFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddClientGeneralFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String fetchStatus = "https://demotic-recruit.000webhostapp.com/status_spinner.php";
    String fetchOwnership = "https://demotic-recruit.000webhostapp.com/ownership_fetch.php";
    String fetchIndustry = "https://demotic-recruit.000webhostapp.com/industry_fetch.php";
    String fetchSource = "https://demotic-recruit.000webhostapp.com/source_fetch.php";

    String statusItem;
    int ownershipId, indutryId, sourceId;


    Spinner status, ownership, industry, source;
    ArrayList<String> statusList;
    ArrayList<String> ownershipList;
    ArrayList<String> industryList;
    ArrayList<String> sourceList;

    ArrayAdapter statusAdapter;
    ArrayAdapter ownershipAdapter;
    ArrayAdapter industryAdapter;
    ArrayAdapter sourceAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddClientGeneralFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddClientGeneralFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddClientGeneralFragment newInstance(String param1, String param2) {
        AddClientGeneralFragment fragment = new AddClientGeneralFragment();
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
        View view = inflater.inflate(R.layout.add_client_general, container, false);

        EditText companyText = view.findViewById(R.id.editTextTextPersonName43);
        EditText companyUrlText = view.findViewById(R.id.editTextTextPersonName44);
        EditText companyDescriptionText = view.findViewById(R.id.editTextTextPersonName45);

        Button next = view.findViewById(R.id.client_to_contact_button);
        status =view.findViewById(R.id.spinner45);
        ownership=view.findViewById(R.id.spinner46);
        industry=view.findViewById(R.id.spinner47);
        source=view.findViewById(R.id.spinner48);
        statusList = new ArrayList<>();
        ownershipList = new ArrayList<>();
        industryList = new ArrayList<>();
        sourceList = new ArrayList<>();

        loadStatus();
        loadOwnership();
        loadIndustry();
        loadSource();

        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int SelectedIndustryPosition = status.getSelectedItemPosition();
                statusItem = statusList.get(SelectedIndustryPosition);
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Status",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item5",SelectedIndustryPosition);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ownership.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ownershipId = ownership.getSelectedItemPosition();
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Ownership",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item",ownershipId);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        industry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                indutryId = industry.getSelectedItemPosition();
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Industry",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item2",indutryId);
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

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("companyName",companyText.getText().toString().trim());
                bundle.putString("companyUrl",companyUrlText.getText().toString().trim());
                bundle.putInt("activeContactId",-1);
                bundle.putInt("parentId", -1);
                bundle.putString("status",statusItem);
                bundle.putInt("ownershipId", ownershipId+1);
                bundle.putInt("industryId",indutryId+1);
                bundle.putInt("sourceId",sourceId+1);
                bundle.putString("companyDescription", companyDescriptionText.getText().toString().trim());
                ((Add_client_activity)getActivity()).addFragmentOnTop(new AddClientContactFragment(),bundle);
                ((Add_client_activity)getActivity()).changeViewForContact();
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
                    int spinnerValue = sharedPref.getInt("spinner_item2",-1);
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

    private void loadStatus() {
        StringRequest statusRequest = new StringRequest(Request.Method.GET, fetchStatus, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("name");
                        statusList.add(countryName);
                    }

                    statusAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,statusList);
                    statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    status.setAdapter(statusAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Status", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item5",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        status.setSelection(spinnerValue,true);
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
        Volley.newRequestQueue(getContext()).add(statusRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPref5 = getActivity().getSharedPreferences("Status",0);
        SharedPreferences.Editor prefEditor5 = sharedPref5.edit();
        prefEditor5.putInt("spinner_item5",0);
        prefEditor5.commit();

        SharedPreferences sharedPref = getActivity().getSharedPreferences("Ownership",0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("spinner_item",0);
        prefEditor.commit();

        SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Industry",0);
        SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
        prefEditor2.putInt("spinner_item2",0);
        prefEditor2.commit();

        SharedPreferences sharedPref3 = getActivity().getSharedPreferences("Source",0);
        SharedPreferences.Editor prefEditor3 = sharedPref3.edit();
        prefEditor3.putInt("spinner_item3",0);
        prefEditor3.commit();

    }
}