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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

 /**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddRecruitPaymentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddRecruitPaymentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String fetchCurrency = "https://demotic-recruit.000webhostapp.com/currency_fetch.php";
    String fetchPackage = "https://demotic-recruit.000webhostapp.com/package_type_fetch.php";
    String jobInsert = "https://demotic-recruit.000webhostapp.com/job_insert.php";

    String jobTypeItem;
    String statusItem;
    String priorityItem;
    int jobPreferenceNumber;
    String designation;
    String openings;
    String startDate, packageCurrency, packageType, packageItem, billRate, markupPercentage, clientMargin, daysOn, daysOff, shiftPattern, createdDate;
    String endDate,rolesResponsibility,growthOppurtunity,learningOppurtunity,employeeEndorsements,employeeBenefits,companyReputation;
    String skills, qualification, eligibilityCriteria, experienceRequirement, relevantExperience, irrelevantExperience;
    int positionId, industryId, companyId, recruiterId, countryId, stateId, cityId, contactId;

    EditText Package;
    EditText BillRate;
    EditText MarkupPercentage;
    EditText ClientMargin;
    EditText DaysOn;
    EditText DaysOff;
    EditText ShiftPattern;

    Spinner currency, packages;
    ArrayList<String> currencyList;
    ArrayList<String> packageList;

    ArrayAdapter currencyAdapter;
    ArrayAdapter packageAdapter;

     Date date = Calendar.getInstance().getTime();
     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     String strDate = dateFormat.format(date);

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddRecruitPaymentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddRecruitPaymentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddRecruitPaymentFragment newInstance(String param1, String param2) {
        AddRecruitPaymentFragment fragment = new AddRecruitPaymentFragment();
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
        View view = inflater.inflate(R.layout.create_recruitment_payment, container, false);
        Button save = view.findViewById(R.id.button22);
        currency = view.findViewById(R.id.spinner17);
        packages =view.findViewById(R.id.spinner18);
        Package = view.findViewById(R.id.editTextTextPersonName26);
        BillRate = view.findViewById(R.id.editTextTextPersonName27);
        MarkupPercentage = view.findViewById(R.id.editTextTextPersonName28);
        ClientMargin = view.findViewById(R.id.editTextTextPersonName29);
        DaysOn = view.findViewById(R.id.editTextTime);
        DaysOff = view.findViewById(R.id.editTextTime2);
        ShiftPattern = view.findViewById(R.id.editTextTextPersonName30);
        currencyList = new ArrayList<>();
        packageList = new ArrayList<>();

        loadCurrency();
        loadPackage();

        Bundle bundle = this.getArguments();
        positionId = bundle.getInt("positionId");
        industryId = bundle.getInt("industryId");
        jobTypeItem = bundle.getString("jobType");
        companyId = bundle.getInt("companyId");
        contactId = bundle.getInt("contactId");
        statusItem = bundle.getString("status");
        priorityItem = bundle.getString("priority");
        jobPreferenceNumber = bundle.getInt("jobPreferenceNumber");
        designation = bundle.getString("designation");
        recruiterId = bundle.getInt("recruiterId");
        openings = bundle.getString("openings");
        startDate = bundle.getString("startDate");
        endDate = bundle.getString("endDate");
        countryId = bundle.getInt("countryId");
        stateId = bundle.getInt("stateId");
        cityId = bundle.getInt("cityId");
        skills = bundle.getString("skills");
        qualification = bundle.getString("qualification");
        eligibilityCriteria = bundle.getString("eligibilityCriteria");
        experienceRequirement = bundle.getString("experienceRequirement");
        relevantExperience = bundle.getString("relevantExperience");
        irrelevantExperience = bundle.getString("irrelevantExperience");
        rolesResponsibility = bundle.getString("rolesResponsibility");
        growthOppurtunity = bundle.getString("growthOppurtunity");
        learningOppurtunity = bundle.getString("learningOppurtunity");
        employeeEndorsements = bundle.getString("employeeEndorsements");
        employeeBenefits = bundle.getString("employeeBenefits");
        companyReputation = bundle.getString("companyReputation");

        currency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int SelectedIndustryPosition = currency.getSelectedItemPosition();
                packageCurrency = currencyList.get(SelectedIndustryPosition);
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Currency",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item1",SelectedIndustryPosition);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        packages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int SelectedIndustryPosition = packages.getSelectedItemPosition();
                packageType = packageList.get(SelectedIndustryPosition);
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Package",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item2",SelectedIndustryPosition);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();

            }
        });
        return view;
    }

    private void insertData() {

        packageItem = Package.getText().toString().trim();
        billRate = BillRate.getText().toString().trim();
        markupPercentage = MarkupPercentage.getText().toString().trim();
        clientMargin = ClientMargin.getText().toString().trim();
        daysOn = DaysOn.getText().toString().trim();
        daysOff = DaysOff.getText().toString().trim();
        shiftPattern = ShiftPattern.getText().toString().trim();

        StringRequest insertRequest = new StringRequest(Request.Method.POST, jobInsert, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent intent = new Intent(getContext(),RecruitmentSplashScreenActivity.class);
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
               param.put("position_id", String.valueOf(positionId));
                param.put("industry_id", String.valueOf(industryId));
                param.put("job_type", jobTypeItem);
                param.put("company_id", String.valueOf(companyId));
                param.put("contact_id", String.valueOf(contactId));
                param.put("status",statusItem);
                param.put("priority", priorityItem);
                param.put("reference_number", String.valueOf(jobPreferenceNumber));
                param.put("designation", designation);
                param.put("recruiter_id", String.valueOf(recruiterId));
                param.put("openings", openings);
                param.put("start_date", startDate);
                param.put("end_date", endDate);
                param.put("country_id", String.valueOf(countryId));
                param.put("state_id", String.valueOf(stateId));
                param.put("city_id", String.valueOf(cityId));
                param.put("skills", skills);
                param.put("qualifications", qualification);
                param.put("eligibility_criteria", eligibilityCriteria);
                param.put("experience_requirement", experienceRequirement);
                param.put("relevant_experience", relevantExperience);
                param.put("irrelevant_experience", irrelevantExperience);
                param.put("roles_responsibilities", rolesResponsibility);
                param.put("growth_opportunities", growthOppurtunity);
                param.put("learning_opportunities", learningOppurtunity);
                param.put("employee_endorsements", employeeEndorsements);
                param.put("employee_benefits", employeeBenefits);
                param.put("company_reputation", companyReputation);
                param.put("package_currency", packageCurrency);
                param.put("package_type", packageType);
                param.put("package", packageItem);
                param.put("bill_rate", billRate);
                param.put("markup_percentage", markupPercentage);
                param.put("client_margin", clientMargin);
                param.put("days_on", daysOn);
                param.put("days_off", daysOff);
                param.put("shift_pattern", shiftPattern);
                param.put("created_date", strDate);

                return param;
            }
        };




        Volley.newRequestQueue(getContext()).add(insertRequest);

    }

    private void loadPackage() {
        StringRequest packageRequest = new StringRequest(Request.Method.GET, fetchPackage, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("name");
                        packageList.add(countryName);
                    }

                    packageAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,packageList);
                    packageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    packages.setAdapter(packageAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Package", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item2",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        packages.setSelection(spinnerValue,true);
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
        Volley.newRequestQueue(getContext()).add(packageRequest);
    }

    private void loadCurrency() {
        StringRequest currencyRequest = new StringRequest(Request.Method.GET, fetchCurrency, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("code");
                        currencyList.add(countryName);
                    }

                    currencyAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,currencyList);
                    currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    currency.setAdapter(currencyAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Currency", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item1",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        currency.setSelection(spinnerValue,true);
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
        Volley.newRequestQueue(getContext()).add(currencyRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("Currency",0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("spinner_item1",0);
        prefEditor.commit();

        SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Package",0);
        SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
        prefEditor2.putInt("spinner_item2",0);
        prefEditor2.commit();
    }
}