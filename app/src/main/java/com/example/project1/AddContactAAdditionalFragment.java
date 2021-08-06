package com.example.project1;

import android.content.Context;
import android.content.Intent;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddContactAAdditionalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddContactAAdditionalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String fetchVisibility = "https://demotic-recruit.000webhostapp.com/visibility_fetch.php";
    String fetchValidity = "https://demotic-recruit.000webhostapp.com/validity_fetch.php";

    Spinner Visibilty, Validity;
    ArrayList<String> visibilityList;
    ArrayList<String> validityList;
    ArrayAdapter visibilityAdapter;
    ArrayAdapter validityAdapter;

    String insertContact = "https://demotic-recruit.000webhostapp.com/contact_insert.php";
    String updatecon = "https://demotic-recruit.000webhostapp.com/contact_update.php";

    EditText LastContactDate;
    EditText LastVisitDate;

    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = dateFormat.format(date);

    String id, firstName, lastName, middleName, title, email, phoneNumber, address, city, zipcode, division, reportsToId, visibility, validity;
    String photo, contactTypeId, industryId, sourceId, statusId, lastContactDate, companyId, stateId, countryId, createdDate, lastVisitDate;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddContactAAdditionalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddContactAAdditionalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddContactAAdditionalFragment newInstance(String param1, String param2) {
        AddContactAAdditionalFragment fragment = new AddContactAAdditionalFragment();
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
        View view = inflater.inflate(R.layout.add_contact_additional, container, false);
        Button save = view.findViewById(R.id.button43);
        Visibilty=view.findViewById(R.id.spinner67);
        Validity=view.findViewById(R.id.spinner68);
        visibilityList = new ArrayList<>();
        validityList = new ArrayList<>();

        loadVisibility();
        loadValidity();

        LastContactDate = view.findViewById(R.id.editTextTextPersonName81);
        LastVisitDate = view.findViewById(R.id.editTextTextPersonName82);

        Bundle bundle = this.getArguments();
        id = bundle.getString("id");

            firstName = bundle.getString("first_name");
            lastName = bundle.getString("last_name");
            middleName = bundle.getString("middle_name");
            statusId = bundle.getString("status");
            email = bundle.getString("email");
            phoneNumber = bundle.getString("contact_number");
            address = bundle.getString("address");
            city = bundle.getString("city");
            zipcode = bundle.getString("zipcode");
            sourceId = bundle.getString("source_id");
            stateId = bundle.getString("state_id");
            countryId = bundle.getString("country_id");
            title = bundle.getString("current_title");
            companyId = bundle.getString("company_name");
            contactTypeId = bundle.getString("contact_type_id");
            division = bundle.getString("division");
            reportsToId = bundle.getString("report_to_id");
            industryId = bundle.getString("industry_id");
            lastContactDate = bundle.getString("last_contact_date");
            lastVisitDate = bundle.getString("last_visit_date");
            visibility = bundle.getString("visibility");
            validity = bundle.getString("validity");
            createdDate = bundle.getString("created_date");
            photo = bundle.getString("image_data");

        if(id!=null) {
            LastContactDate.setText(bundle.getString("last_contact_date"));
            LastVisitDate.setText(bundle.getString("last_visit_date"));


            if (visibility != null) {
                Visibilty.setSelection(Integer.parseInt(visibility));
                SharedPreferences sharedPref = getActivity().getSharedPreferences("Visibility", 0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_item", Integer.parseInt(visibility));
                prefEditor.commit();
            }

            if (validity != null) {
                Validity.setSelection(Integer.parseInt(validity));
                SharedPreferences sharedPref = getActivity().getSharedPreferences("Validity", 0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_item2", Integer.parseInt(validity));
                prefEditor.commit();
            }
        }



        Visibilty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                visibility = String.valueOf(Visibilty.getSelectedItemPosition());
                SharedPreferences sharedPref = getActivity().getSharedPreferences("Visibility",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_item", Integer.parseInt(visibility));
                prefEditor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Validity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                validity = String.valueOf(Validity.getSelectedItemPosition());
                SharedPreferences sharedPref = getActivity().getSharedPreferences("Validity",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_item2", Integer.parseInt(validity));
                prefEditor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id!=null){
                    updateContact();
                }
                else {
                    loadContact();
                }
            }
        });
        return view;
    }

    private void updateContact() {
        lastContactDate = LastContactDate.getText().toString().trim();
        lastVisitDate= LastVisitDate.getText().toString().trim();

        StringRequest insertRequest = new StringRequest(Request.Method.POST, updatecon, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent intent = new Intent(getContext(),ContactSplashScreenActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("first_name", firstName);
                bundle.putString("last_name", lastName);
                bundle.putString("middle_name", middleName);
                bundle.putString("email", email);
                bundle.putString("contact_number", phoneNumber);
                bundle.putString("last_contact_date", lastContactDate);
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
                param.put("middle_name", middleName);
                param.put("status", statusId);
                param.put("email", email);
                param.put("contact_number", phoneNumber);
                param.put("address", address);
                param.put("city", city);
                param.put("zipcode", zipcode);
                param.put("source_id", sourceId);
                param.put("state_id", stateId);
                param.put("country_id", countryId);
                param.put("current_title", title);
                param.put("company_name", companyId);
                param.put("contact_type_id", contactTypeId);
                param.put("division", division);
                param.put("report_to_id", reportsToId);
                param.put("industry_id", industryId);
                param.put("last_contact_date", lastContactDate);
                param.put("last_visit_date", lastVisitDate);
                param.put("visibility", visibility);
                param.put("validity", validity);
                param.put("created_date", createdDate);
                param.put("photo", photo);

                return param;
            }
        };




        Volley.newRequestQueue(getContext()).add(insertRequest);

    }

    private void loadContact() {
        lastContactDate = LastContactDate.getText().toString().trim();
        lastVisitDate= LastVisitDate.getText().toString().trim();

        StringRequest insertRequest = new StringRequest(Request.Method.POST, insertContact, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent intent = new Intent(getContext(),ContactSplashScreenActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("first_name", firstName);
                bundle.putString("last_name", lastName);
                bundle.putString("middle_name", middleName);
                bundle.putString("email", email);
                bundle.putString("contact_number", phoneNumber);
                bundle.putString("last_contact_date", lastContactDate);
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
                param.put("middle_name", middleName);
                param.put("status", statusId);
                param.put("email", email);
                param.put("contact_number", phoneNumber);
                param.put("address", address);
                param.put("city", city);
                param.put("zipcode", zipcode);
                param.put("source_id", sourceId);
                param.put("state_id", stateId);
                param.put("country_id", countryId);
                param.put("current_title", title);
                param.put("company_id", companyId);
                param.put("contact_type_id", contactTypeId);
                param.put("division", division);
                param.put("report_to_id", reportsToId);
                param.put("industry_id", industryId);
                param.put("last_contact_date", lastContactDate);
                param.put("last_visit_date", lastVisitDate);
                param.put("visibility", visibility);
                param.put("validity", validity);
                param.put("created_date", strDate);



                return param;
            }
        };




        Volley.newRequestQueue(getContext()).add(insertRequest);
    }

    private void loadValidity() {
        StringRequest validityRequest = new StringRequest(Request.Method.GET, fetchValidity, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray country = new JSONArray(response);

                    for(int i=0;i<country.length();i++){
                        JSONObject countryObject = country.getJSONObject(i);

                        String countryName = countryObject.getString("name");
                        validityList.add(countryName);
                    }

                    validityAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,validityList);
                    validityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Validity.setAdapter(validityAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Validity", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item2",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        Validity.setSelection(spinnerValue,true);
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

        Volley.newRequestQueue(getContext()).add(validityRequest);
    }

    private void loadVisibility() {
        StringRequest visibilityRequest = new StringRequest(Request.Method.GET, fetchVisibility, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray country = new JSONArray(response);

                    for(int i=0;i<country.length();i++){
                        JSONObject countryObject = country.getJSONObject(i);

                        String countryName = countryObject.getString("name");
                        visibilityList.add(countryName);
                    }

                    visibilityAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,visibilityList);
                    visibilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Visibilty.setAdapter(visibilityAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Visibility", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        Visibilty.setSelection(spinnerValue,true);
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

        Volley.newRequestQueue(getContext()).add(visibilityRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("Visibility",0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("spinner_item",0);
        prefEditor.commit();

        SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Validity",0);
        SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
        prefEditor2.putInt("spinner_item2",0);
        prefEditor2.commit();
    }
}