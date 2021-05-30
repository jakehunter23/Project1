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
 * Use the {@link AddClientContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddClientContactFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String fetchState = "https://demotic-recruit.000webhostapp.com/state_spinner.php";
    String fetchCountry = "https://demotic-recruit.000webhostapp.com/spinner.php";

    String statusItem;
    int ownershipId, indutryId, sourceId, activeContactId, parentCompanyId, stateId, countryId;
    String companyName, companyUrl, description;

    Spinner state, country;
    ArrayList<String> stateList;
    ArrayList<String> countryList;

    ArrayAdapter stateAdapter;
    ArrayAdapter countryAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddClientContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddClientContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddClientContactFragment newInstance(String param1, String param2) {
        AddClientContactFragment fragment = new AddClientContactFragment();
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
        View view = inflater.inflate(R.layout.add_client_contact, container, false);
        Button next = view.findViewById(R.id.ac_contact_next);
        EditText email=view.findViewById(R.id.editTextTextEmailAddress4);
        EditText phoneNumber =view.findViewById(R.id.editTextPhone3);
        EditText address = view.findViewById(R.id.editTextTextPersonName48);
        EditText city = view.findViewById(R.id.editTextTextPersonName49);
        EditText zipCode = view.findViewById(R.id.editTextNumber);
        state =view.findViewById(R.id.spinner51);
        country=view.findViewById(R.id.spinner52);
        stateList=new ArrayList<>();
        countryList=new ArrayList<>();

        Bundle bundle = this.getArguments();
        companyName = bundle.getString("companyName");
        companyUrl = bundle.getString("companyUrl");
        activeContactId=bundle.getInt("activeContactId");
        parentCompanyId=bundle.getInt("parentId");
        statusItem=bundle.getString("status");
        ownershipId=bundle.getInt("ownershipId");
        sourceId=bundle.getInt("sourceId");
        indutryId=bundle.getInt("industryId");
        description=bundle.getString("companyDescription");


        loadState();
        loadCountry();

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stateId= state.getSelectedItemPosition();
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("State",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item9",stateId);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                countryId = country.getSelectedItemPosition();
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Country",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item10",countryId);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("companyName",companyName);
                bundle2.putString("companyUrl",companyUrl);
                bundle2.putInt("activeContactId",-1);
                bundle2.putInt("parentId", -1);
                bundle2.putString("status",statusItem);
                bundle2.putInt("ownershipId", ownershipId);
                bundle2.putInt("industryId",indutryId);
                bundle2.putInt("sourceId",sourceId);
                bundle2.putString("companyDescription", description);
                bundle2.putString("email",email.getText().toString().trim());
                bundle2.putString("contactNumber",phoneNumber.getText().toString().trim());
                bundle2.putString("address",address.getText().toString().trim());
                bundle2.putString("city",city.getText().toString().trim());
                bundle2.putString("zipcode",zipCode.getText().toString().trim());
                bundle2.putInt("stateId",stateId+1);
                bundle2.putInt("countryId",countryId+1);
                ((Add_client_activity)getActivity()).addFragmentOnTop(new AddClientBillingFragment(), bundle2);
                ((Add_client_activity)getActivity()).changeViewForBilling();
            }
        });


        return view;
    }

    private void loadCountry() {
        StringRequest countryRequest = new StringRequest(Request.Method.GET, fetchCountry, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("name");
                        countryList.add(countryName);
                    }

                    countryAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,countryList);
                    countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    country.setAdapter(countryAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Country", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item10",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        country.setSelection(spinnerValue,true);
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
        Volley.newRequestQueue(getContext()).add(countryRequest);
    }

    private void loadState() {
        StringRequest stateRequest = new StringRequest(Request.Method.GET, fetchState, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("name");
                        stateList.add(countryName);
                    }

                    stateAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,stateList);
                    stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    state.setAdapter(stateAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("State", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item9",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        state.setSelection(spinnerValue,true);
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
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPref9 = getActivity().getSharedPreferences("State",0);
        SharedPreferences.Editor prefEditor9 = sharedPref9.edit();
        prefEditor9.putInt("spinner_item9",0);
        prefEditor9.commit();

        SharedPreferences sharedPref10 = getActivity().getSharedPreferences("Country",0);
        SharedPreferences.Editor prefEditor10 = sharedPref10.edit();
        prefEditor10.putInt("spinner_item10",0);
        prefEditor10.commit();
    }
}