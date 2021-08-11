package com.example.project1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActurialApplicantsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActurialApplicantsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ActurialApplicantRecAdapter acturialApplicantRecAdapter;
    RecyclerView ActAptRec;
    List<JobRequestModel> requestList;
    String fetchJr = "https://demotic-recruit.000webhostapp.com/specific_jr_fetch.php";
    String id, candidate_id, zipcode, company_id, created_date, first_name, last_name, email, address, city, country, phone_number;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ActurialApplicantsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActurialApplicantsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActurialApplicantsFragment newInstance(String param1, String param2) {
        ActurialApplicantsFragment fragment = new ActurialApplicantsFragment();
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
        View view = inflater.inflate(R.layout.fragment_acturial_applicants, container, false);
        ActAptRec = view.findViewById(R.id.act_apt_rec);
        ActAptRec.setLayoutManager(new LinearLayoutManager(getContext()));
        requestList = new ArrayList<>();

        fetch_jr();

        return view;
    }

    private void fetch_jr() {
        StringRequest creatorRequest = new StringRequest(Request.Method.POST, fetchJr , new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                try {
                    JSONArray contactArray = new JSONArray(response);
                    int j= contactArray.length();
                    for(int i=0;i<contactArray.length();i++){
                        JSONObject countryObject = contactArray.getJSONObject(i);

                        id = countryObject.getString("id");
                        candidate_id = countryObject.getString("candidate_id");
                        company_id = countryObject.getString("company_id");
                        first_name = countryObject.getString("first_name");
                        last_name = countryObject.getString("last_name");
                        email = countryObject.getString("email");
                        phone_number = countryObject.getString("phone_number");
                        address = countryObject.getString("address");
                        city = countryObject.getString("city");
                        country = countryObject.getString("country");
                        zipcode = countryObject.getString("zipcode");
                        created_date = countryObject.getString("created_date");

                        JobRequestModel jobRequestItem = new JobRequestModel();
                        jobRequestItem.setId(id);
                        jobRequestItem.setCandidate_id(candidate_id);
                        jobRequestItem.setCompany_id(company_id);
                        jobRequestItem.setFirstName(first_name);
                        jobRequestItem.setLastName(last_name);
                        jobRequestItem.setEmail(email);
                        jobRequestItem.setPhone(phone_number);
                        jobRequestItem.setAddress(address);
                        jobRequestItem.setCity(city);
                        jobRequestItem.setCountry(country);
                        jobRequestItem.setZipcode(zipcode);
                        jobRequestItem.setCreatedDate(created_date);

                        requestList.add(jobRequestItem);


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                acturialApplicantRecAdapter = new ActurialApplicantRecAdapter(getContext(),requestList);
                ActAptRec.setAdapter(acturialApplicantRecAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("company_id",ActurialActivity.id);
                return  param;
            }
        };
        Volley.newRequestQueue(getContext()).add(creatorRequest);
    }
}