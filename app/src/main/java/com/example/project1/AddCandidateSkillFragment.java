package com.example.project1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCandidateSkillFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCandidateSkillFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String firstName, lastName, statusItem, mainMail, contactNumber, address, city, zipcode, currentSalary, hourlyRatel, desiredSalary, hourlyRateh, title, companyName, skill, talent, degree;
    int stateId, countryId, candidateType, preference, sourceId, ownerId;

    EditText skillLine;
    ChipGroup chipGroup;


    public AddCandidateSkillFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCandidateSkillFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCandidateSkillFragment newInstance(String param1, String param2) {
        AddCandidateSkillFragment fragment = new AddCandidateSkillFragment();
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
        View view = inflater.inflate(R.layout.candidate_skill_info, container, false);
        Button toAdditional = view.findViewById(R.id.button40);
        chipGroup =view.findViewById(R.id.chip_group_canskill);
        EditText Talent = view.findViewById(R.id.editTextTextPersonName69);
       Spinner Degree = view.findViewById(R.id.degreeSpinner);
       EditText specialiation=view.findViewById(R.id.specialization);

        ArrayList<String> deg_List=new ArrayList<>();
        deg_List.add("Bachelors (graduate)");
        deg_List.add("Masters (post graduate");
        deg_List.add("Doctorate");
        deg_List.add("Diploma");
        deg_List.add("ITI");
        deg_List.add("Higher secondary");
        deg_List.add("Other");

        ArrayAdapter DegAdapter= new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, deg_List);
        DegAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Degree.setAdapter(DegAdapter);

        Bundle bundle = this.getArguments();
        firstName = bundle.getString("firstName");
        lastName = bundle.getString("lastName");
        statusItem = bundle.getString("status");
        mainMail = bundle.getString("mainMail");
        contactNumber = bundle.getString("contactNumber");
        address = bundle.getString("address");
        city = bundle.getString("city");
        zipcode = bundle.getString("zipcode");
        stateId = bundle.getInt("stateId");
        countryId = bundle.getInt("countryId");
        title =  bundle.getString("title");
        companyName = bundle.getString("companyName");
        candidateType = bundle.getInt("type");
        preference = bundle.getInt("preference");
        sourceId = bundle.getInt("sourceId");
        ownerId = bundle.getInt("ownerId");
        currentSalary = bundle.getString("currentSalary");
        hourlyRatel = bundle.getString("hourlyRatel");
        desiredSalary = bundle.getString("desiredSalary");
        hourlyRateh = bundle.getString("hourlyRateh");

        toAdditional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle2 = new Bundle();
                bundle2.putString("firstName",firstName);
                bundle2.putString("lastName", lastName);
                bundle2.putString("status", statusItem);
                bundle2.putString("mainMail" , mainMail);
                bundle2.putString("contactNumber", contactNumber);
                bundle2.putString("address" , address);
                bundle2.putString("city" , city);
                bundle2.putString("zipcode", zipcode);
                bundle2.putInt("stateId" , stateId);
                bundle2.putInt("countryId", countryId);
                bundle2.putString("title" , title);
                bundle2.putString("companyName" , companyName);
                bundle2.putInt("type", candidateType);
                bundle2.putInt("preference" , preference);
                bundle2.putInt("sourceId" , sourceId);
                bundle2.putInt("ownerId" , ownerId);
                bundle2.putString("currentSalary" , currentSalary);
                bundle2.putString("hourlyRatel" , hourlyRatel);
                bundle2.putString("desiredSalary" , desiredSalary);
                bundle2.putString("hourlyRateh" , hourlyRateh);
                bundle2.putString("talent", Talent.getText().toString().trim());
                bundle2.putString("skill", skill);
                bundle2.putString("degree", Degree.getSelectedItem().toString().trim());
                bundle2.putString("specialiation",specialiation.getText().toString().trim());


                ((AddCandidateActivity)getActivity()).addFragmentOnTop(new AddCandidateAdditionalFragment(), bundle2);
                ((AddCandidateActivity)getActivity()).changeViewForAdditional();
            }
        });

        ImageView addSkill =view.findViewById(R.id.imageView61);
        addSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Add Skills");

                  skillLine = new EditText(getContext());
                 skillLine.setInputType(InputType.TYPE_CLASS_TEXT);


                 builder.setView(skillLine);

                 builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                          String [] tags = skillLine.getText().toString().split(" ");
                          skill = skillLine.getText().toString().trim();
                          LayoutInflater inflater1 = LayoutInflater.from(getContext());
                          for(String text : tags){
                              Chip chip = (Chip)inflater.inflate(R.layout.chip_item,null,false);
                              chip.setText(text);
                              chip.setOnCloseIconClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {
                                      chipGroup.removeView(view);
                                  }
                              });
                              chipGroup.addView(chip);
                          }
                     }
                 });

                 builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         dialogInterface.cancel();
                     }
                 });
                 builder.show();
            }
        });
        return view;
    }
}