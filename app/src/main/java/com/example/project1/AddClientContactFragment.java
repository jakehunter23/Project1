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

    String id, name,  phoneNumber, address,  email, createdDate, parentId, creatorId, activeContactId, sourceId, ownershipId, industryId, status, url, description, stateId, countryId, city, zipcode, bankName, image_data, flag;
    private String bankId, bankAccountNumber, IBAN, VAT;

    Spinner state, country;
    ArrayList<String> stateList;
    ArrayList<String> countryList;

    ArrayAdapter stateAdapter;
    ArrayAdapter countryAdapter;
    EditText Email;
    EditText PhoneNumber;
    EditText Address;
    EditText City ;
    EditText ZipCode;

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
        Email=view.findViewById(R.id.editTextTextEmailAddress4);
        PhoneNumber =view.findViewById(R.id.editTextPhone3);
        Address = view.findViewById(R.id.editTextTextPersonName48);
        City = view.findViewById(R.id.editTextTextPersonName49);
        ZipCode = view.findViewById(R.id.editTextNumber);
        state =view.findViewById(R.id.spinner51);
        country=view.findViewById(R.id.spinner52);
        stateList=new ArrayList<>();
        countryList=new ArrayList<>();

        Bundle bundle = this.getArguments();
        id = bundle.getString("id");

            parentId = bundle.getString("parent_id");
            creatorId = bundle.getString("creator_id");
            activeContactId = bundle.getString("active_contact_id");
            sourceId = bundle.getString("source_id");
            ownershipId = bundle.getString("ownership_id");
            industryId = bundle.getString("industry_id");
            status = bundle.getString("status");
            phoneNumber = bundle.getString("phone_number");
            createdDate = bundle.getString("created_date");
            url = bundle.getString("url");
            description = bundle.getString("description");
            stateId = bundle.getString("state_id");
            countryId = bundle.getString("country_id");
            city = bundle.getString("city");
            zipcode = bundle.getString("zipcode");
            bankName = bundle.getString("bank_name");
            bankId = bundle.getString("bank_id");
            bankAccountNumber = bundle.getString("bank_account_number");
            IBAN = bundle.getString("IBAN");
            VAT = bundle.getString("VAT");
            name = bundle.getString("name");
            email = bundle.getString("email");
            address = bundle.getString("address");
        image_data = bundle.getString("image_data");
        flag = bundle.getString("flag");
        if(id!=null) {

            Email.setText(email);
            PhoneNumber.setText(phoneNumber);
            Address.setText(address);
            City.setText(city);
            ZipCode.setText(zipcode);

            if(stateId!=null){
                state.setSelection(Integer.parseInt(stateId));
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("State",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item9", Integer.parseInt(stateId));
                prefEditor2.commit();
            }

            if(countryId!=null){
                country.setSelection(Integer.parseInt(countryId));
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Country",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item10", Integer.parseInt(countryId));
                prefEditor2.commit();
            }
        }


        loadState();
        loadCountry();

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stateId= String.valueOf(state.getSelectedItemPosition());
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("State",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item9", Integer.parseInt(stateId));
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                countryId = String.valueOf(country.getSelectedItemPosition());
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Country",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item10", Integer.parseInt(countryId));
                prefEditor2.commit();
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
        String emails = Email.getText().toString().trim();
        String phoneNumbers = PhoneNumber.getText().toString().trim();
        String addresss = Address.getText().toString().trim();
        String citys = City.getText().toString().trim();
        String zipcodes = ZipCode.getText().toString().trim();
        if(emails.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Email",Toast.LENGTH_LONG).show();
            Email.setError("Please Enter Email");
            Email.requestFocus();
            return;
        }
        if(phoneNumbers.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Phone Number",Toast.LENGTH_LONG).show();
            PhoneNumber.setError("Please Enter PhoneNumber");
            PhoneNumber.requestFocus();
            return;
        }
        if(phoneNumbers.length() < 10) {
            Toast.makeText(getContext(),"Phone Number Length must be 10",Toast.LENGTH_LONG).show();
            PhoneNumber.setError("Phone Number Length must be 10");
            PhoneNumber.requestFocus();
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
            ZipCode.setError("Please Enter Zipcode");
            ZipCode.requestFocus();
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("id", id);
        bundle2.putString("name",name);
        bundle2.putString("url",url);
        bundle2.putString("creator_id", creatorId);
        bundle2.putString("active_contact_id", activeContactId);
        bundle2.putString("parent_id", parentId);
        bundle2.putString("status",status);
        bundle2.putString("ownership_id", ownershipId);
        bundle2.putString("industry_id", industryId);
        bundle2.putString("source_id", sourceId);
        bundle2.putString("description", description);
        bundle2.putString("email",Email.getText().toString().trim());
        bundle2.putString("phone_number",PhoneNumber.getText().toString().trim());
        bundle2.putString("address",Address.getText().toString().trim());
        bundle2.putString("city",City.getText().toString().trim());
        bundle2.putString("zipcode",ZipCode.getText().toString().trim());
        bundle2.putString("state_id", stateId);
        bundle2.putString("country_id", countryId);
        bundle2.putString("bank_name", bankName);
        bundle2.putString("bank_id", bankId);
        bundle2.putString("bank_account_number", bankAccountNumber);
        bundle2.putString("IBAN", IBAN);
        bundle2.putString("VAT", VAT);
        bundle2.putString("created_date", createdDate);
        bundle2.putString("image_data", image_data);
        bundle2.putString("flag",flag);
        ((Add_client_activity)getActivity()).addFragmentOnTop(new AddClientBillingFragment(), bundle2);
        ((Add_client_activity)getActivity()).changeViewForBilling();
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