package com.example.project1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCandidatePersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCandidatePersonalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ScrollView scroll;

    String fetchStatus = "https://demotic-recruit.000webhostapp.com/status_spinner.php";
    String fetchState = "https://demotic-recruit.000webhostapp.com/state_spinner.php";
    String fetchCountry = "https://demotic-recruit.000webhostapp.com/spinner.php";

    String statusItem, image_data;
    String id, firstName, lastName, status, statusId, email, phoneNumber, address, city, zipcode, type, preference, sourceId, ownerId, currentSalary, desiredSalary, stateId, countryId, title, companyName, hourlyRateLow, hourlyRateHigh, talent, skill, degree, comments, availabilityDate, job, accessibility, createdDate;


    Spinner Status, state, country;
    ArrayList<String> statusList;
    ArrayList<String> stateList;
    ArrayList<String> countryList;

    ArrayAdapter statusAdapter;
    ArrayAdapter stateAdapter;
    ArrayAdapter countryAdapter;

    EditText FirstName;
    EditText LastName;
    EditText MainMail;
    EditText ContactNumber;
    EditText Address;
    EditText City;
    EditText Zipcode;
    Bitmap bitmap;
    ImageView image;
    String encodedImage;
    int flag=0;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCandidatePersonalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCandidatePersonalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCandidatePersonalFragment newInstance(String param1, String param2) {
        AddCandidatePersonalFragment fragment = new AddCandidatePersonalFragment();
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
        View view = inflater.inflate(R.layout.candidate_personalinfo, container, false);
        Button toPro = view.findViewById(R.id.button11);
        scroll = view.findViewById(R.id.con_per_scroll);
        Status=view.findViewById(R.id.spinner19);
        state=view.findViewById(R.id.spinner20);
        country=view.findViewById(R.id.spinner21);
        statusList = new ArrayList<>();
        stateList = new ArrayList<>();
        countryList = new ArrayList<>();

        FirstName = view.findViewById(R.id.editTextTextPersonName31);
        LastName = view.findViewById(R.id.editTextTextPersonName32);
        MainMail = view.findViewById(R.id.editTextTextEmailAddress3);
        ContactNumber = view.findViewById(R.id.editTextPhone2);
        Address = view.findViewById(R.id.editTextTextPersonName33);
        City = view.findViewById(R.id.editTextTextPersonName34);
        Zipcode = view.findViewById(R.id.editTextTextPersonName35);
        Button selectImage = view.findViewById(R.id.button10);
        image = view.findViewById(R.id.imageView18);

        loadStatus();
        loadState();
        loadCountry();

        Bundle bundle1 = getArguments();
        id = bundle1.getString("id");
        if(id!=null) {
            firstName = bundle1.getString("first_name");
            lastName = bundle1.getString("last_name");
            statusId = bundle1.getString("status_id");
            status = bundle1.getString("status");
            email = bundle1.getString("email");
            phoneNumber = bundle1.getString("contact_number");
            address = bundle1.getString("address");
            city = bundle1.getString("city");
            zipcode = bundle1.getString("zipcode");
            stateId = bundle1.getString("state_id");
            countryId = bundle1.getString("country_id");
            title = bundle1.getString("current_title");
            companyName = bundle1.getString("company_name");
            type = bundle1.getString("type");
            preference = bundle1.getString("preference");
            ownerId = bundle1.getString("owner_id");
            sourceId = bundle1.getString("source_id");
            currentSalary = bundle1.getString("current_salary");
            desiredSalary = bundle1.getString("desired_salary");
            hourlyRateHigh = bundle1.getString("hourly_rate_high");
            hourlyRateLow = bundle1.getString("hourly_rate_low");
            talent = bundle1.getString("talent");
            skill = bundle1.getString("skill");
            degree = bundle1.getString("degree");
            comments = bundle1.getString("comments");
            availabilityDate = bundle1.getString("availability_date");
            job = bundle1.getString("job");
            accessibility = bundle1.getString("accessibility");
            createdDate = bundle1.getString("created_date");
            image_data = bundle1.getString("image_data");

            FirstName.setText(firstName);
            LastName.setText(lastName);
            MainMail.setText(email);
            ContactNumber.setText(phoneNumber);
            Address.setText(address);
            City.setText(city);
            Zipcode.setText(zipcode);

            if(statusId!=null){
                Status.setSelection(Integer.parseInt(statusId));
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Status",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item5", Integer.parseInt(statusId));
                prefEditor2.commit();
            }

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

        Status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                statusId = String.valueOf(Status.getSelectedItemPosition());
                status = statusList.get(Integer.parseInt(statusId));
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Status",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item5", Integer.parseInt(statusId));
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               stateId = String.valueOf(state.getSelectedItemPosition());
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

        toPro.setOnClickListener(new View.OnClickListener() {
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
        String mainmail = MainMail.getText().toString().trim();
        String contactnumber = ContactNumber.getText().toString().trim();
        String address = Address.getText().toString().trim();
        String city = City.getText().toString().trim();
        String zipcode = Zipcode.getText().toString().trim();
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
        if(mainmail.isEmpty()) {
            Toast.makeText(getContext(),"Please Enter Main Mail",Toast.LENGTH_LONG).show();
            MainMail.setError("Please Enter Main Mail");
            MainMail.requestFocus();
            return;
        }
        if(contactnumber.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Contact Number",Toast.LENGTH_LONG).show();
            ContactNumber.setError("Please Enter Contact Number");
            ContactNumber.requestFocus();
            return;
        }
        if(address.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Address",Toast.LENGTH_LONG).show();
            Address.setError("Please Enter Address");
            Address.requestFocus();
            return;
        }
        if(city.isEmpty()){
            Toast.makeText(getContext(),"Please Enter City",Toast.LENGTH_LONG).show();
            City.setError("Please Enter City");
            City.requestFocus();
            return;
        }
        if(zipcode.isEmpty()) {
            Toast.makeText(getContext(), "Please Enter  Zipcode", Toast.LENGTH_LONG).show();
            Zipcode.setError("Please Enter  Zipcode");
            Zipcode.requestFocus();
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("firstName",FirstName.getText().toString().trim());
        bundle.putString("lastName", LastName.getText().toString().trim());
        bundle.putString("statusId", statusId);
        bundle.putString("mainMail" , MainMail.getText().toString().trim());
        bundle.putString("contactNumber", ContactNumber.getText().toString().trim());
        bundle.putString("address" , Address.getText().toString().trim());
        bundle.putString("city" , City.getText().toString().trim());
        bundle.putString("zipcode", Zipcode.getText().toString().trim());
        bundle.putString("stateId" , stateId);
        bundle.putString("countryId", countryId);
        bundle.putString("status", status);
        bundle.putString("current_title", title);
        bundle.putString("company_name", companyName);
        bundle.putString("type", type);
        bundle.putString("preference", preference);
        bundle.putString("contact_type_id", ownerId);
        bundle.putString("source_id", sourceId);
        bundle.putString("current_salary", currentSalary);
        bundle.putString("desired_salary", desiredSalary);
        bundle.putString("hourly_rate_high", hourlyRateHigh);
        bundle.putString("hourly_rate_low", hourlyRateLow);
        bundle.putString("talent", talent);
        bundle.putString("skill", skill);
        bundle.putString("degree", degree);
        bundle.putString("comments", comments);
        bundle.putString("availability_date", availabilityDate);
        bundle.putString("job", job);
        bundle.putString("accessibility", accessibility);
        bundle.putString("created_date", createdDate);
        bundle.putString("image_data", encodedImage);
        bundle.putString("flag", String.valueOf(flag));

        ((AddCandidateActivity)getActivity()).addFragmentOnTop(new AddCandidateProfessionalFragment(),bundle);
        ((AddCandidateActivity)getActivity()).changeViewForProfessional();

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
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if(requestCode==1 && resultCode == RESULT_OK && data!=null){
            Uri filePath = data.getData();
            flag=1;

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
        SharedPreferences sharedPref5 = getActivity().getSharedPreferences("Status",0);
        SharedPreferences.Editor prefEditor5 = sharedPref5.edit();
        prefEditor5.putInt("spinner_item5",0);
        prefEditor5.commit();

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