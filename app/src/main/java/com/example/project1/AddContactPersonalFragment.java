package com.example.project1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.http.Url;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

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
    EditText MainEmail;
    EditText ContactNumber;
    EditText Address;
    EditText City;
    EditText Zipcode;
    Bitmap bitmap;
    ImageView image;
    String encodedImage;

    String id, first_name, last_name, middle_name, status, email, contact_number, address, city, zipcode, stateId, countryId;
    String title, companyName, contactTypeId, division, sourceId, reportToId, industryId, lastContactDate, lastVisitDate, visibility, validity, created_date, image_data;



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
        MainEmail=view.findViewById(R.id.editTextTextEmailAddress5);
        ContactNumber=view.findViewById(R.id.editTextPhone4);
        Address=view.findViewById(R.id.editTextTextPersonName75);
        City=view.findViewById(R.id.editTextTextPersonName76);
        Zipcode=view.findViewById(R.id.editTextTextPersonName77);
        Button selectImage = view.findViewById(R.id.button41);
        image = view.findViewById(R.id.imageView62);



        countryList = new ArrayList<>();
        stateList = new ArrayList<>();
        statusList = new ArrayList<>();

        loadProducts();
        loadState();
        loadStatus();

        Bundle bundle = getArguments();
        id = bundle.getString("id");
        if(id!=null){
            first_name = bundle.getString("first_name");
            last_name = bundle.getString("last_name");
            middle_name = bundle.getString("middle_name");
            status = bundle.getString("status");
            email = bundle.getString("email");
            contact_number = bundle.getString("contact_number");
            address = bundle.getString("address");
            city = bundle.getString("city");
            zipcode = bundle.getString("zipcode");
            stateId = bundle.getString("state_id");
            countryId = bundle.getString("country_id");
            title = bundle.getString("current_title");
            companyName = bundle.getString("company_name");
            contactTypeId = bundle.getString("contact_type_id");
            division = bundle.getString("division");
            sourceId = bundle.getString("source_id");
            reportToId = bundle.getString("report_to_id");
            industryId = bundle.getString("industry_id");
            lastContactDate = bundle.getString("last_contact_date");
            lastVisitDate = bundle.getString("last_visit_date");
            visibility = bundle.getString("visibility");
            validity = bundle.getString("validity");
            created_date = bundle.getString("created_date");
            image_data = bundle.getString("image_data");

            String url = "https://demotic-recruit.000webhostapp.com/Images/"+image_data;


            Glide.with(this).load(url).override(100, 200).fitCenter().into(image);


            FirstName.setText(first_name);
            LastName.setText(last_name);
            MiddleName.setText(middle_name);
            MainEmail.setText(email);
            ContactNumber.setText(contact_number);
            Address.setText(address);
            City.setText(city);
            Zipcode.setText(zipcode);

            if(countryId!=null){
                spinnerCountry.setSelection(Integer.parseInt(countryId));
                SharedPreferences sharedPref = getActivity().getSharedPreferences("Position",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_item2", Integer.parseInt(countryId));
                prefEditor.commit();
            }

            if(stateId!=null){
                spinnerState.setSelection(Integer.parseInt(stateId));
                SharedPreferences sharedPref = getActivity().getSharedPreferences("State",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_state", Integer.parseInt(stateId));
                prefEditor.commit();
            }

            if(status!=null){
                spinnerStatus.setSelection(Integer.parseInt(status));
                SharedPreferences sharedPref = getActivity().getSharedPreferences("Status",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_item3", Integer.parseInt(status));
                prefEditor.commit();
            }

            if(image_data!=null){
                encodedImage=image_data;
            }
        }



        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                countryId = String.valueOf(spinnerCountry.getSelectedItemPosition());
                SharedPreferences sharedPref = getActivity().getSharedPreferences("Position",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_item2", Integer.parseInt(countryId));
                prefEditor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stateId = String.valueOf(spinnerState.getSelectedItemPosition());
                SharedPreferences sharedPref = getActivity().getSharedPreferences("State",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_state", Integer.parseInt(stateId));
                prefEditor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                status = String.valueOf(spinnerStatus.getSelectedItemPosition());
                SharedPreferences sharedPref = getActivity().getSharedPreferences("Status",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_item3", Integer.parseInt(status));
                prefEditor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withContext(getActivity())
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "select Image"),1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
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
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("first_name", firstname);
        bundle.putString("last_name", lastname);
        bundle.putString("middle_name", middlename);
        bundle.putString("status", status);
        bundle.putString("email", emails);
        bundle.putString("contact_number", phoneNumbers);
        bundle.putString("address", addresss);
        bundle.putString("city", citys);
        bundle.putString("zipcode", zipcodes);
        bundle.putString("state_id", stateId);
        bundle.putString("country_id", countryId);
        bundle.putString("current_title", title);
        bundle.putString("company_name", companyName);
        bundle.putString("contact_type_id", contactTypeId);
        bundle.putString("division", division);
        bundle.putString("source_id", sourceId);
        bundle.putString("report_to_id", reportToId);
        bundle.putString("industry_id", industryId);
        bundle.putString("last_contact_date", lastContactDate);
        bundle.putString("last_visit_date", lastVisitDate);
        bundle.putString("visibility", visibility);
        bundle.putString("validity", validity);
        bundle.putString("created_date", created_date);
        bundle.putString("image_data", encodedImage);

        ((AddContactActivity)getActivity()).addFragmentOnTop(new AddContactProfessionalFragment(), bundle);
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if(requestCode==1 && resultCode == RESULT_OK && data!=null){
            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContext().getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(bitmap);
                
                imageStore(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void imageStore(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,10,stream);

        byte[] imageByte = stream.toByteArray();
        encodedImage = android.util.Base64.encodeToString(imageByte, Base64.DEFAULT);

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