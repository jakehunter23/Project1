package com.example.project1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import retrofit2.http.Url;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddContactPersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class  AddContactPersonalFragment extends Fragment {

   

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String fetchUrl = "https://demotic-recruit.000webhostapp.com/spinner.php";
    String fetchState = "https://demotic-recruit.000webhostapp.com/state_spinner.php";
    String fetchStatus= "https://demotic-recruit.000webhostapp.com/status_spinner.php";
    Spinner spinnerCountry, spinnerState, spinnerStatus;
    ArrayList<String>stateList;
    ArrayList<String>statusList;
    List<String> countryList;
    ArrayAdapter countryAdapter;
    ArrayAdapter stateAdapter;
    ArrayAdapter statusAdapter;
    EditText FirstName;
    EditText LastName;
    EditText MiddleName;
    EditText Title;
    EditText MainEmail;
    EditText ContactNumber;
    EditText Address;
    EditText City;
    EditText Zipcode;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddContactPersonalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddContactPersonalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddContactPersonalFragment newInstance(String param1, String param2) {
        AddContactPersonalFragment fragment = new AddContactPersonalFragment();
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
        View view = inflater.inflate(R.layout.add_contact_personal, container, false);
        Button next = view.findViewById(R.id.button44);
        spinnerCountry = view.findViewById(R.id.spinner58);
        spinnerState =view.findViewById(R.id.spinner61);
        spinnerStatus=view.findViewById(R.id.spinner57);
        FirstName=view.findViewById(R.id.editTextTextPersonName71);
        LastName=view.findViewById(R.id.editTextTextPersonName72);
        MiddleName=view.findViewById(R.id.editTextTextPersonName73);
        Title=view.findViewById(R.id.editTextTextPersonName74);
        MainEmail=view.findViewById(R.id.editTextTextEmailAddress5);
        ContactNumber=view.findViewById(R.id.editTextPhone4);
        Address=view.findViewById(R.id.editTextTextPersonName75);
        City=view.findViewById(R.id.editTextTextPersonName76);
        Zipcode=view.findViewById(R.id.editTextTextPersonName77);


        countryList = new ArrayList<>();
        stateList = new ArrayList<>();
        statusList = new ArrayList<>();

        loadProducts();
        loadState();
        loadStatus();



        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int SelectedCountryPosition = spinnerCountry.getSelectedItemPosition();
                SharedPreferences sharedPref = getActivity().getSharedPreferences("Position",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_item2",SelectedCountryPosition);
                prefEditor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int SelectedStatePosition = spinnerState.getSelectedItemPosition();
                SharedPreferences sharedPref = getActivity().getSharedPreferences("State",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_state",SelectedStatePosition);
                prefEditor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int SelectedStatusPosition = spinnerStatus.getSelectedItemPosition();
                SharedPreferences sharedPref = getActivity().getSharedPreferences("Status",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_item3",SelectedStatusPosition);
                prefEditor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNext();


            }
        });




        return view;

    }

    private void userNext() {
        String firstname = FirstName.getText().toString().trim();
        String lastname = LastName.getText().toString().trim();
        String middlename = MiddleName.getText().toString().trim();
        String title = Title.getText().toString().trim();
        String emails = MainEmail.getText().toString().trim();
        String phoneNumbers = ContactNumber.getText().toString().trim();
        String addresss = Address.getText().toString().trim();
        String citys = City.getText().toString().trim();
        String zipcodes = Zipcode.getText().toString().trim();
        if(firstname.isEmpty()){
            Toast.makeText(getContext(),"Please Enter First Name",Toast.LENGTH_LONG).show();
            FirstName.setError("Please Enter First Name");
            FirstName.requestFocus();
            return;
        }
        if(lastname.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Last Name",Toast.LENGTH_LONG).show();
            LastName.setError("Please Enter Last Name");
            LastName.requestFocus();
            return;
        }
        if(middlename.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Middle Name",Toast.LENGTH_LONG).show();
            MiddleName.setError("Please Enter Middle Name");
            MiddleName.requestFocus();
            return;
        }
        if(title.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Title",Toast.LENGTH_LONG).show();
            Title.setError("Please Enter Title");
            Title.requestFocus();
            return;
        }
        if(emails.isEmpty()){
            Toast.makeText(getContext(),"Please Enter MainEmail",Toast.LENGTH_LONG).show();
            MainEmail.setError("Please Enter Main Email");
            MainEmail.requestFocus();
            return;
        }
        if(phoneNumbers.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Phone Number",Toast.LENGTH_LONG).show();
            ContactNumber.setError("Please Enter PhoneNumber");
            ContactNumber.requestFocus();
            return;
        }
        if(phoneNumbers.length() < 10) {
            Toast.makeText(getContext(),"Phone Number Length must be 10",Toast.LENGTH_LONG).show();
            ContactNumber.setError("Phone Number Length must be 10");
            ContactNumber.requestFocus();
            return;
        }
        if(addresss.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Address",Toast.LENGTH_LONG).show();
            Address.setError("Please Enter Address");
            Address.requestFocus();
            return;
        }
        if(citys.isEmpty()){
            Toast.makeText(getContext(),"Please Enter City",Toast.LENGTH_LONG).show();
            City.setError("Please Enter City");
            City.requestFocus();
            return;
        }
        if(zipcodes.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Zipcode",Toast.LENGTH_LONG).show();
            Zipcode.setError("Please Enter Zipcode");
            Zipcode.requestFocus();
            return;
        }
        ((AddContactActivity)getActivity()).addFragmentOnTop(new AddContactProfessionalFragment());
        ((AddContactActivity)getActivity()).changeViewForProfessional();
    }

    private void loadStatus() {
        StringRequest statusRequest = new StringRequest(Request.Method.GET, fetchStatus, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray country = new JSONArray(response);

                    for(int i=0;i<country.length();i++){
                        JSONObject countryObject = country.getJSONObject(i);

                        String countryName = countryObject.getString("name");
                        statusList.add(countryName);
                    }

                    statusAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,statusList);
                    statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerStatus.setAdapter(statusAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Status", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item3",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        spinnerStatus.setSelection(spinnerValue,true);
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

    private void loadProducts() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, fetchUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray country = new JSONArray(response);

                    for(int i=0;i<country.length();i++){
                        JSONObject countryObject = country.getJSONObject(i);

                        String countryName = countryObject.getString("name");
                        countryList.add(countryName);
                    }

                    countryAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,countryList);
                    countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCountry.setAdapter(countryAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Position", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item2",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        spinnerCountry.setSelection(spinnerValue,true);
                    }

                    if(countryList==null){
                        Toast.makeText(getContext(),"NULL hai",Toast.LENGTH_SHORT).show();
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

        Volley.newRequestQueue(getContext()).add(stringRequest);
    }

    private void loadState(){
        StringRequest stateRequest = new StringRequest(Request.Method.GET, fetchState, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray country = new JSONArray(response);

                    for(int i=0;i<country.length();i++){
                        JSONObject countryObject = country.getJSONObject(i);

                        String countryName = countryObject.getString("name");
                        stateList.add(countryName);
                    }

                    stateAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,stateList);
                    stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerState.setAdapter(stateAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("State", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_state",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        spinnerState.setSelection(spinnerValue,true);
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
        Volley.newRequestQueue(getContext()).add(stateRequest);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("Position",0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("spinner_item2",0);
        prefEditor.commit();

        SharedPreferences sharedPref2 = getActivity().getSharedPreferences("State",0);
        SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
        prefEditor2.putInt("spinner_state",0);
        prefEditor2.commit();

        SharedPreferences sharedPref3 = getActivity().getSharedPreferences("Status",0);
        SharedPreferences.Editor prefEditor3 = sharedPref3.edit();
        prefEditor3.putInt("spinner_item3",0);
        prefEditor3.commit();

    }
}