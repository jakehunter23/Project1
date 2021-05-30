package com.example.project1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddRecruitRolesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddRecruitRolesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String jobTypeItem;
    String statusItem;
    String priorityItem;
    int jobPreferenceNumber;
    String designation;
    String openings;
    String startDate;
    String endDate,rolesResponsibility,growthOppurtunity,learningOppurtunity,employeeEndorsements,employeeBenefits,companyReputation;
    String clientMargin, skills, qualification, eligibilityCriteria, experienceRequirement, relevantExperience, irrelevantExperience;
    int positionId, industryId, companyId, recruiterId, countryId, stateId, cityId, contactId;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddRecruitRolesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddRecruitRolesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddRecruitRolesFragment newInstance(String param1, String param2) {
        AddRecruitRolesFragment fragment = new AddRecruitRolesFragment();
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
        View view = inflater.inflate(R.layout.create_recruitment_roles, container, false);
        EditText RolesResponsibility = view.findViewById(R.id.editTextTextPersonName14);
        EditText GrowthOportunity = view.findViewById(R.id.editTextTextPersonName15);
        EditText LearningOpportunity = view.findViewById(R.id.editTextTextPersonName16);
        EditText EmployeeEndorsement = view.findViewById(R.id.editTextTextPersonName18);
        EditText EmployeeBenefits = view.findViewById(R.id.editTextTextPersonName23);
        EditText CompanyReputation = view.findViewById(R.id.editTextTextPersonName24);
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
        Button next = view.findViewById(R.id.button20);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle2 = new Bundle();
                bundle2.putInt("positionId", positionId);
                bundle2.putInt("industryId", industryId);
                bundle2.putString("jobType", jobTypeItem);
                bundle2.putInt("companyId", companyId);
                bundle2.putInt("contactId", contactId);
                bundle2.putString("status", statusItem);
                bundle2.putString("priority", priorityItem);
                bundle2.putInt("jobPreferenceNumber", jobPreferenceNumber);
                bundle2.putString("designation", designation);
                bundle2.putInt("recruiterId", recruiterId);
                bundle2.putString("openings", openings);
                bundle2.putString("startDate", startDate);
                bundle2.putString("endDate", endDate);
                bundle2.putInt("countryId", countryId);
                bundle2.putInt("stateId", stateId);
                bundle2.putInt("cityId", cityId);
                bundle2.putString("skills", skills);
                bundle2.putString("qualification", qualification);
                bundle2.putString("eligibilityCriteria", eligibilityCriteria);
                bundle2.putString("experienceRequirement", experienceRequirement);
                bundle2.putString("relevantExperience", relevantExperience);
                bundle2.putString("irrelevantExperience", irrelevantExperience);
                bundle2.putString("rolesResponsibility", RolesResponsibility.getText().toString().trim());
                bundle2.putString("growthOppurtunity", GrowthOportunity.getText().toString().trim());
                bundle2.putString("learningOppurtunity", LearningOpportunity.getText().toString().trim());
                bundle2.putString("employeeEndorsements", EmployeeEndorsement.getText().toString().trim());
                bundle2.putString("employeeBenefits", EmployeeBenefits.getText().toString().trim());
                bundle2.putString("companyReputation", CompanyReputation.getText().toString().trim());
                ((AddRecruitmentActivity)getActivity()).addFragmentOnTop(new AddRecruitPaymentFragment(),bundle2);
                ((AddRecruitmentActivity)getActivity()).changeViewForPayment();
            }
        });
        return view;
    }
}