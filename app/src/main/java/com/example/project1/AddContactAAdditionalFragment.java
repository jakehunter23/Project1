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

    Spinner visibilty, validity;
    ArrayList<String> visibilityList;
    ArrayList<String> validityList;
    ArrayAdapter visibilityAdapter;
    ArrayAdapter validityAdapter;
    EditText LinkedIn;

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
        visibilty=view.findViewById(R.id.spinner67);
        validity=view.findViewById(R.id.spinner68);
        LinkedIn=view.findViewById(R.id.editTextTextPersonName83);
        visibilityList = new ArrayList<>();
        validityList = new ArrayList<>();

        loadVisibility();
        loadValidity();


        visibilty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int SelectedVisibilityPosition = visibilty.getSelectedItemPosition();
                SharedPreferences sharedPref = getActivity().getSharedPreferences("Visibility",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_item",SelectedVisibilityPosition);
                prefEditor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        validity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int SelectedValidityPosition = validity.getSelectedItemPosition();
                SharedPreferences sharedPref = getActivity().getSharedPreferences("Validity",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_item2",SelectedValidityPosition);
                prefEditor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = LinkedIn.getText().toString().trim();
                if (link.isEmpty()){
                    Toast.makeText(getContext(),"Please Enter LinkedIn Profile URL",Toast.LENGTH_LONG).show();
                    LinkedIn.setError("Please Enter LinkedIn Profile URL");
                    LinkedIn.requestFocus();
                    return;
                }
                Intent intent = new Intent(getContext(),ContactSplashScreenActivity.class);
                startActivity(intent);
            }
        });
        return view;
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
                    validity.setAdapter(validityAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Validity", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item2",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        visibilty.setSelection(spinnerValue,true);
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
                    visibilty.setAdapter(visibilityAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Visibility", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        visibilty.setSelection(spinnerValue,true);
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