package com.example.project1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddRecruitSkillFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddRecruitSkillFragment extends Fragment {

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
    String endDate;
    String clientMargin, skills, qualification, eligibilityCriteria, experienceRequirement, relevantExperience, irrelevantExperience;
    int positionId, industryId, companyId, recruiterId, countryId, stateId, cityId, contactId;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddRecruitSkillFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddRecruitSkillFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddRecruitSkillFragment newInstance(String param1, String param2) {
        AddRecruitSkillFragment fragment = new AddRecruitSkillFragment();
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
        View view = inflater.inflate(R.layout.create_recruitment_skill, container, false);
        Button next = view.findViewById(R.id.button17);
        EditText Skills = view.findViewById(R.id.editTextTextPersonName8);
        EditText Qualification = view.findViewById(R.id.editTextTextPersonName9);
        EditText EligibilityCriteria = view.findViewById(R.id.editTextTextPersonName10);
        EditText ExperienceRequirement = view.findViewById(R.id.editTextTextPersonName11);
        EditText RelevantExperience = view.findViewById(R.id.editTextTextPersonName12);
        EditText IrrelevantExperience =  view.findViewById(R.id.editTextTextPersonName13);

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


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String skilss = Skills.getText().toString().trim();
                String qua = Qualification.getText().toString().trim();
                String eligib = EligibilityCriteria.getText().toString().trim();
                String experience = ExperienceRequirement.getText().toString().trim();
                String reExp = RelevantExperience.getText().toString().trim();
                String irreExp = IrrelevantExperience.getText().toString().trim();
                if (skilss.isEmpty()){
                    Toast.makeText(getContext(),"Please Enter skills ",Toast.LENGTH_LONG).show();
                    Skills.setError("Please Enter Skills");
                    Skills.requestFocus();
                    return;
                }
                if (qua.isEmpty()){
                    Toast.makeText(getContext(),"Please Enter Qualification",Toast.LENGTH_LONG).show();
                    Qualification.setError("Please Enter Qualification");
                    Qualification.requestFocus();
                    return;
                }
                if (eligib.isEmpty()){
                    Toast.makeText(getContext(),"Please Enter EligibilityCriteria",Toast.LENGTH_LONG).show();
                    EligibilityCriteria.setError("Please Enter EligibilityCriteria");
                    EligibilityCriteria.requestFocus();
                    return;
                }
                if (experience.isEmpty()){
                    Toast.makeText(getContext(),"Please Enter ExperienceRequirement",Toast.LENGTH_LONG).show();
                    ExperienceRequirement.setError("Please Enter ExperienceRequirement");
                    ExperienceRequirement.requestFocus();
                    return;
                }
                if (reExp.isEmpty()){
                    Toast.makeText(getContext(),"Please Enter RelevantExperience",Toast.LENGTH_LONG).show();
                    RelevantExperience.setError("Please Enter RelevantExperience");
                    RelevantExperience.requestFocus();
                    return;
                }
                if (irreExp.isEmpty()){
                    Toast.makeText(getContext(),"Please Enter IrrelevantExperience",Toast.LENGTH_LONG).show();
                    IrrelevantExperience.setError("Please Enter IrrelevantExperience");
                    IrrelevantExperience.requestFocus();
                    return;
                }
                Bundle bundle1 = new Bundle();
                bundle1.putInt("positionId", positionId);
                bundle1.putInt("industryId", industryId);
                bundle1.putString("jobType", jobTypeItem);
                bundle1.putInt("companyId", companyId);
                bundle1.putInt("contactId", contactId);
                bundle1.putString("status", statusItem);
                bundle1.putString("priority", priorityItem);
                bundle1.putInt("jobPreferenceNumber", jobPreferenceNumber);
                bundle1.putString("designation", designation);
                bundle1.putInt("recruiterId", recruiterId);
                bundle1.putString("openings", openings);
                bundle1.putString("startDate", startDate);
                bundle1.putString("endDate", endDate);
                bundle1.putInt("countryId", countryId);
                bundle1.putInt("stateId", stateId);
                bundle1.putInt("cityId", cityId);
                bundle1.putString("skills", Skills.getText().toString().trim());
                bundle1.putString("qualification", Qualification.getText().toString().trim());
                bundle1.putString("eligibilityCriteria", EligibilityCriteria.getText().toString().trim());
                bundle1.putString("experienceRequirement", ExperienceRequirement.getText().toString().trim());
                bundle1.putString("relevantExperience", RelevantExperience.getText().toString().trim());
                bundle1.putString("irrelevantExperience", IrrelevantExperience.getText().toString().trim());
                ((AddRecruitmentActivity)getActivity()).addFragmentOnTop(new AddRecruitRolesFragment(),bundle1);
                ((AddRecruitmentActivity)getActivity()).changeViewForRoles();
            }
        });
        return view;
    }
}