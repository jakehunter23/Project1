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



    Spinner Status, ownership, industry, source, active, parent;
    ArrayList<String> statusList;
    ArrayList<String> ownershipList;
    ArrayList<String> industryList;
    ArrayList<String> sourceList;

    ArrayAdapter statusAdapter;
    ArrayAdapter ownershipAdapter;
    ArrayAdapter industryAdapter;
    ArrayAdapter sourceAdapter;
    ArrayAdapter contactAdapter;
    ArrayAdapter parentAdapter;

    EditText companyText;
    EditText companyUrlText;
    EditText companyDescriptionText;

    String id, name, activeId,  phoneNumber, address,  email, createdDate, parentId, creatorId, activeContactId, sourceId, ownershipId, industryId, status, url, description, stateId, countryId, city, zipcode, bankName;
    private String bankId, bankAccountNumber, IBAN, VAT;

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

        companyText = view.findViewById(R.id.editTextTextPersonName43);
        companyUrlText = view.findViewById(R.id.editTextTextPersonName44);
        companyDescriptionText = view.findViewById(R.id.editTextTextPersonName45);
        parent=view.findViewById(R.id.parent_cmny);
        active=view.findViewById(R.id.active_cntact);



        ArrayList<String> con_List=new ArrayList<>();
        con_List.add("Active contact1");
        con_List.add("Active contact2");
        con_List.add("ActiveContact3");
        con_List.add("All");
        contactAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,con_List);
        contactAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        active.setAdapter(contactAdapter);

        ArrayList<String> parent_list=new ArrayList<>();
        parent_list.add("Facebook");
        parent_list.add("Google");
        parent_list.add("Microsoft");
        parent_list.add("Amazon");
        parent_list.add("Analysed");
        parentAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,parent_list);
        parentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parent.setAdapter(parentAdapter);

        Button next = view.findViewById(R.id.client_to_contact_button);
        Status =view.findViewById(R.id.spinner45);
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

        Bundle bundle = getArguments();
        id = bundle.getString("id");
        if(id!=null) {
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

            companyText.setText(name);
            companyUrlText.setText(url);
            companyDescriptionText.setText(description);

            if(status!=null){
                Status.setSelection(Integer.parseInt(status));
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Status",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item5", Integer.parseInt(status));
                prefEditor2.commit();
            }

            if(ownershipId!=null){
                ownership.setSelection(Integer.parseInt(ownershipId));
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Ownership",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item", Integer.parseInt(ownershipId));
                prefEditor2.commit();
            }

            if(industryId!=null){
                industry.setSelection(Integer.parseInt(industryId));
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Industry",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item2", Integer.parseInt(industryId));
                prefEditor2.commit();
            }

            if(sourceId!=null){
                source.setSelection(Integer.parseInt(sourceId));
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Source",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item3", Integer.parseInt(sourceId));
                prefEditor2.commit();
            }

            if(activeContactId!=null){
                active.setSelection(Integer.parseInt(activeContactId));
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Active",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item4", Integer.parseInt(activeContactId));
                prefEditor2.commit();
            }

            if(parentId!=null){
                parent.setSelection(Integer.parseInt(parentId));
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Parent",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item6", Integer.parseInt(parentId));
                prefEditor2.commit();
            }
        }

        Status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                status = String.valueOf(Status.getSelectedItemPosition());
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Status",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item5", Integer.parseInt(status));
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ownership.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ownershipId = String.valueOf(ownership.getSelectedItemPosition());
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Ownership",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item", Integer.parseInt(ownershipId));
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        industry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                industryId = String.valueOf(industry.getSelectedItemPosition());
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Industry",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item2", Integer.parseInt(industryId));
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

        active.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                activeContactId = String.valueOf(active.getSelectedItemPosition());
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Active",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item4", Integer.parseInt(activeContactId));
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        parent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                parentId = String.valueOf(parent.getSelectedItemPosition());
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Parent",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item6", Integer.parseInt(parentId));
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();

            }
        });


        return view;
    }
    private void userLogin() {
        String companyName = companyText.getText().toString().trim();
        String companyURL = companyUrlText.getText().toString().trim();
        String companyDes = companyDescriptionText.getText().toString().trim();
        if(companyName.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Company Name",Toast.LENGTH_LONG).show();
            companyText.setError("Please Enter Company Name");
            companyText.requestFocus();
            return;
        }
        if(companyURL.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Company URL",Toast.LENGTH_LONG).show();
            companyUrlText.setError("Please Enter Company URL");
            companyUrlText.requestFocus();
            return;
        }
        if(companyDes.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Description",Toast.LENGTH_LONG).show();
            companyDescriptionText.setError("Please Enter Description");
            companyDescriptionText.requestFocus();
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("name",companyText.getText().toString().trim());
        bundle.putString("url",companyUrlText.getText().toString().trim());
        bundle.putString("active_contact_id", activeContactId);
        bundle.putString("parent_id",parentId);
        bundle.putString("status",status);
        bundle.putString("ownership_id", ownershipId);
        bundle.putString("industry_id", industryId);
        bundle.putString("source_id", sourceId);
        bundle.putString("description", companyDescriptionText.getText().toString().trim());
        bundle.putString("creator_id", creatorId);
        bundle.putString("phone_number", phoneNumber);
        bundle.putString("created_date", createdDate);
        bundle.putString("state_id", stateId);
        bundle.putString("country_id", countryId);
        bundle.putString("city", city);
        bundle.putString("zipcode", zipcode);
        bundle.putString("bank_name", bankName);
        bundle.putString("bank_id", bankId);
        bundle.putString("bank_account_number", bankAccountNumber);
        bundle.putString("IBAN", IBAN);
        bundle.putString("VAT", VAT);
        bundle.putString("email", email);
        bundle.putString("address", address);
        ((Add_client_activity)getActivity()).addFragmentOnTop(new AddClientContactFragment(),bundle);
        ((Add_client_activity)getActivity()).changeViewForContact();



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
                    Status.setAdapter(statusAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Status", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item5",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        Status.setSelection(spinnerValue,true);
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

        SharedPreferences sharedPref4 = getActivity().getSharedPreferences("Active",0);
        SharedPreferences.Editor prefEditor4 = sharedPref4.edit();
        prefEditor4.putInt("spinner_item4",0);
        prefEditor4.commit();

        SharedPreferences sharedPref6 = getActivity().getSharedPreferences("Parent",0);
        SharedPreferences.Editor prefEditor6 = sharedPref6.edit();
        prefEditor6.putInt("spinner_item6",0);
        prefEditor6.commit();

    }
}