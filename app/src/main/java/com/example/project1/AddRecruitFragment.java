package com.example.project1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddRecruitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddRecruitFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String fetchPosition = "https://demotic-recruit.000webhostapp.com/position_fetch.php";
    String fetchIndustry = "https://demotic-recruit.000webhostapp.com/industry_fetch.php";
    String fetchJobType = "https://demotic-recruit.000webhostapp.com/job_type_fetch.php";
    String fetchClientCompany = "https://demotic-recruit.000webhostapp.com/client_company_fetch.php";
    String fetchStatus = "https://demotic-recruit.000webhostapp.com/status_spinner.php";
    String fetchPriority = "https://demotic-recruit.000webhostapp.com/priority_fetch.php";
    String fetchRecruiter = "https://demotic-recruit.000webhostapp.com/user_fetch.php";
    String fetchCity = "https://demotic-recruit.000webhostapp.com/cities_fetch.php";
    String fetchState = "https://demotic-recruit.000webhostapp.com/state_spinner.php";
    String fetchCountry = "https://demotic-recruit.000webhostapp.com/spinner.php";

    Spinner position, industry, jobType, clientCompany, status, priority, recruiter, city, state, country;
    ArrayList<String> positionList;
    ArrayList<String> industryList;
    ArrayList<String> jobTypeList;
    ArrayList<String> clientCompanyList;
    ArrayList<String> statusList;
    ArrayList<String> priorityList;
    ArrayList<String> recruiterList;
    ArrayList<String> cityList;
    ArrayList<String> stateList;
    ArrayList<String> countryList;

    ArrayAdapter positionAdapter;
    ArrayAdapter industryAdapter;
    ArrayAdapter jobTypeAdapter;
    ArrayAdapter clientCompanyAdapter;
    ArrayAdapter statusAdapter;
    ArrayAdapter priorityAdapter;
    ArrayAdapter recruiterAdapter;
    ArrayAdapter cityAdapter;
    ArrayAdapter stateAdapter;
    ArrayAdapter countryAdapter;

    String jobTypeItem, statusItem, priorityItem;
    int positionId, industryId, companyId, recruiterId, countryId, stateId, cityId;


    EditText StartDate ;
    EditText EndDate;
    String start_date_selected;
    String end_date_selected;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddRecruitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddRecruitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddRecruitFragment newInstance(String param1, String param2) {
        AddRecruitFragment fragment = new AddRecruitFragment();
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
        View view = inflater.inflate(R.layout.create_recruitment, container, false);
        Button next = view.findViewById(R.id.button15);
        EditText Designation = view.findViewById(R.id.editTextTextPersonName17);
        EditText Openings = view.findViewById(R.id.editTextTextPersonName19);
        //date picker id's
        StartDate = view.findViewById(R.id.editTextDate2);
         EndDate = view.findViewById(R.id.editTextDate3);

        position = view.findViewById(R.id.spinner3);
        industry = view.findViewById(R.id.spinner4);
        jobType = view.findViewById(R.id.spinner5);
        clientCompany = view.findViewById(R.id.spinner6);
        status = view.findViewById(R.id.spinner9);
        priority = view.findViewById(R.id.spinner10);
        recruiter = view.findViewById(R.id.spinner12);
        city = view.findViewById(R.id.spinner15);
        state = view.findViewById(R.id.spinner14);
        country = view.findViewById(R.id.spinner13);
        positionList = new ArrayList<>();
        industryList = new ArrayList<>();
        jobTypeList = new ArrayList<>();
        clientCompanyList = new ArrayList<>();
        statusList = new ArrayList<>();
        priorityList = new ArrayList<>();
        recruiterList = new ArrayList<>();
        cityList = new ArrayList<>();
        stateList = new ArrayList<>();
        countryList =new ArrayList<>();




        loadPosition();
        loadIndustry();
        loadJobType();
        loadClientCompany();
        loadStatus();
        loadPriority();
        loadRecruiter();
        loadCity();
        loadState();
        loadCountry();

        view.findViewById(R.id.show_dialog_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdate_picker_dialog_start();
            }
        });

        view.findViewById(R.id.show_dialog_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdate_picker_dialog_end();
            }
        });
        position.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                positionId = position.getSelectedItemPosition();
                SharedPreferences sharedPref = getActivity().getSharedPreferences("Position",0);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putInt("spinner_item1",positionId);
                prefEditor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        industry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                industryId = industry.getSelectedItemPosition();
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Industry",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item2",industryId);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        jobType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int SelectedIndustryPosition = jobType.getSelectedItemPosition();
                jobTypeItem = jobTypeList.get(SelectedIndustryPosition);
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("JobType",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item3",SelectedIndustryPosition);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        clientCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                companyId = clientCompany.getSelectedItemPosition();
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("ClientCompany",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item4",companyId);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

        priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int SelectedIndustryPosition = priority.getSelectedItemPosition();
                priorityItem = priorityList.get(SelectedIndustryPosition);
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Priority",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item6",SelectedIndustryPosition);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        recruiter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                recruiterId = recruiter.getSelectedItemPosition();
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Recruiter",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item7",recruiterId);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cityId = city.getSelectedItemPosition();
                SharedPreferences sharedPref2 = getActivity().getSharedPreferences("City",0);
                SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
                prefEditor2.putInt("spinner_item8",cityId);
                prefEditor2.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                stateId = state.getSelectedItemPosition();
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
                Bundle bundle = new Bundle();
                bundle.putInt("positionId", positionId+1);
                bundle.putInt("industryId", industryId+1);
                bundle.putString("jobType", jobTypeItem);
                bundle.putInt("companyId", companyId+1);
                bundle.putInt("contactId", 0);
                bundle.putString("status", statusItem);
                bundle.putString("priority", priorityItem);
                bundle.putInt("jobPreferenceNumber", 0);
                bundle.putString("designation", Designation.getText().toString().trim());
                bundle.putInt("recruiterId", recruiterId);
                bundle.putString("openings", Openings.getText().toString().trim());
                bundle.putString("startDate", StartDate.getText().toString().trim());
                bundle.putString("endDate", EndDate.getText().toString().trim());
                bundle.putInt("countryId", countryId);
                bundle.putInt("stateId", stateId);
                bundle.putInt("cityId", cityId);
                

                ((AddRecruitmentActivity)getActivity()).addFragmentOnTop(new AddRecruitSkillFragment(),bundle);
                ((AddRecruitmentActivity)getActivity()).changeViewForSkills();
            }
        });
        return view;
    }

    private void showdate_picker_dialog_end() {
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int date=calendar.get(Calendar.DATE);


        DatePickerDialog datePickerDialog=new DatePickerDialog(
                getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        end_date_selected=dayOfMonth+"-"+(month+1)+"-"+year;
                        EndDate.setText(end_date_selected);
                        Log.e("Date selected by user: ",end_date_selected);
                    }
                },
                year,month,date);
        datePickerDialog.show();
    }

    private void showdate_picker_dialog_start() {
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int date=calendar.get(Calendar.DATE);


        DatePickerDialog datePickerDialog=new DatePickerDialog(
                getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       start_date_selected=dayOfMonth+"-"+(month+1)+"-"+year;
                        StartDate.setText(start_date_selected);
                        Log.e("Date selected by user: ",start_date_selected);
                    }
                },
                year,month,date);
        datePickerDialog.show();
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

    private void loadCity() {
        StringRequest cityRequest = new StringRequest(Request.Method.GET, fetchCity, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("city");
                        cityList.add(countryName);
                    }

                    cityAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,cityList);
                    cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    city.setAdapter(cityAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("City", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item8",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        city.setSelection(spinnerValue,true);
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
        Volley.newRequestQueue(getContext()).add(cityRequest);
    }

    private void loadRecruiter() {
        StringRequest recruiterRequest = new StringRequest(Request.Method.GET, fetchRecruiter, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String divisionName = countryObject.getString("first_name");
                        String lastName = countryObject.getString("last_name");
                        recruiterId = countryObject.getInt("id");
                        recruiterList.add(divisionName+" "+lastName);
                    }

                    recruiterAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,recruiterList);
                    recruiterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    recruiter.setAdapter(recruiterAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Recruiter", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item7",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        recruiter.setSelection(spinnerValue,true);
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
        Volley.newRequestQueue(getContext()).add(recruiterRequest);
    }

    private void loadPriority() {
        StringRequest priorityRequest = new StringRequest(Request.Method.GET, fetchPriority, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("name");
                        priorityList.add(countryName);
                    }

                    priorityAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,priorityList);
                    priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    priority.setAdapter(priorityAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Priority", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item6",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        priority.setSelection(spinnerValue,true);
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
        Volley.newRequestQueue(getContext()).add(priorityRequest);
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

    private void loadClientCompany() {
        StringRequest clientCompanyRequest = new StringRequest(Request.Method.GET, fetchClientCompany, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("name");
                        clientCompanyList.add(countryName);
                    }

                    clientCompanyAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,clientCompanyList);
                    clientCompanyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    clientCompany.setAdapter(clientCompanyAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("ClientCompany", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item4",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        clientCompany.setSelection(spinnerValue,true);
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
        Volley.newRequestQueue(getContext()).add(clientCompanyRequest);
    }

    private void loadJobType() {
        StringRequest jobTypeRequest = new StringRequest(Request.Method.GET, fetchJobType, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("type");
                        jobTypeList.add(countryName);
                    }

                    jobTypeAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,jobTypeList);
                    jobTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    jobType.setAdapter(jobTypeAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("JobType", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item3",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        jobType.setSelection(spinnerValue,true);
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
        Volley.newRequestQueue(getContext()).add(jobTypeRequest);
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

    private void loadPosition() {
        StringRequest positionRequest = new StringRequest(Request.Method.GET, fetchPosition, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);

                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        String countryName = countryObject.getString("name");
                        positionList.add(countryName);
                    }

                    positionAdapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,positionList);
                    positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    position.setAdapter(positionAdapter);

                    SharedPreferences sharedPref = getActivity().getSharedPreferences("Position", Context.MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("spinner_item1",-1);
                    if(spinnerValue != -1) {
                        // set the value of the spinner
                        position.setSelection(spinnerValue,true);
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
        Volley.newRequestQueue(getContext()).add(positionRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("Position",0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("spinner_item1",0);
        prefEditor.commit();

        SharedPreferences sharedPref2 = getActivity().getSharedPreferences("Industry",0);
        SharedPreferences.Editor prefEditor2 = sharedPref2.edit();
        prefEditor2.putInt("spinner_item2",0);
        prefEditor2.commit();

        SharedPreferences sharedPref3 = getActivity().getSharedPreferences("JobType",0);
        SharedPreferences.Editor prefEditor3 = sharedPref3.edit();
        prefEditor3.putInt("spinner_item3",0);
        prefEditor3.commit();

        SharedPreferences sharedPref4 = getActivity().getSharedPreferences("ClientCompany",0);
        SharedPreferences.Editor prefEditor4 = sharedPref4.edit();
        prefEditor4.putInt("spinner_item4",0);
        prefEditor4.commit();

        SharedPreferences sharedPref5 = getActivity().getSharedPreferences("Status",0);
        SharedPreferences.Editor prefEditor5 = sharedPref5.edit();
        prefEditor5.putInt("spinner_item5",0);
        prefEditor5.commit();

        SharedPreferences sharedPref6 = getActivity().getSharedPreferences("Priority",0);
        SharedPreferences.Editor prefEditor6 = sharedPref6.edit();
        prefEditor6.putInt("spinner_item6",0);
        prefEditor6.commit();

        SharedPreferences sharedPref7 = getActivity().getSharedPreferences("Recruiter",0);
        SharedPreferences.Editor prefEditor7 = sharedPref7.edit();
        prefEditor7.putInt("spinner_item7",0);
        prefEditor7.commit();

        SharedPreferences sharedPref8 = getActivity().getSharedPreferences("City",0);
        SharedPreferences.Editor prefEditor8 = sharedPref8.edit();
        prefEditor8.putInt("spinner_item8",0);
        prefEditor8.commit();

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