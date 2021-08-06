package com.example.project1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

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

    String id, firstName, lastName, statusId, status, email, phoneNumber, address, city, zipcode, currentSalary, hourlyRateLow, desiredSalary, hourlyRateHigh, title, companyName, skill, talent, degree;
    String stateId, countryId, candidateType, preference, sourceId, ownerId, type, comments, availabilityDate, job, createdDate, accessibility;

    EditText skillLine;
    ChipGroup chipGroup;
    EditText specialiation;
    EditText Talent;
    Spinner Degree;
    Button resume;

    String encodedPdf;


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
        Talent = view.findViewById(R.id.editTextTextPersonName69);
       Degree = view.findViewById(R.id.degreeSpinner);

        ArrayList<String> deg_List=new ArrayList<>();
        deg_List.add("Bachelor (graduate)");
        deg_List.add("Master (post graduate)");
        deg_List.add("Doctorate");
        deg_List.add("Diploma");
        deg_List.add("ITI");
        deg_List.add("Higher secondary");
        deg_List.add("Other");

        ArrayAdapter DegAdapter= new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, deg_List);
        DegAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Degree.setAdapter(DegAdapter);

        Bundle bundle = this.getArguments();
        id = bundle.getString("id");

            firstName = bundle.getString("firstName");
            lastName = bundle.getString("lastName");
            statusId = bundle.getString("status_id");
            email = bundle.getString("mainMail");
            phoneNumber = bundle.getString("contactNumber");
            address = bundle.getString("address");
            city = bundle.getString("city");
            zipcode = bundle.getString("zipcode");
            stateId = bundle.getString("stateId");
            countryId = bundle.getString("countryId");
            status = bundle.getString("status");
            title = bundle.getString("title");
            companyName = bundle.getString("companyName");
            type = bundle.getString("type");
            preference = bundle.getString("preference");
            ownerId = bundle.getString("ownerId");
            sourceId = bundle.getString("sourceId");
            currentSalary = bundle.getString("currentSalary");
            desiredSalary = bundle.getString("desiredSalary");
            hourlyRateHigh = bundle.getString("hourlyRateh");
            hourlyRateLow = bundle.getString("hourlyRatel");
            talent = bundle.getString("talent");
            skill = bundle.getString("skill");
            degree = bundle.getString("degree");
            comments = bundle.getString("comments");
            availabilityDate = bundle.getString("availability_date");
            job = bundle.getString("job");
            accessibility = bundle.getString("accessibility");
            createdDate = bundle.getString("created_date");
        if(id!=null) {

            String [] tags = skill.split(" ");
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
            Talent.setText(talent);
        }

        resume = view.findViewById(R.id.button31);
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("application/pdf");

                startActivityForResult(Intent.createChooser(chooseFile, "select a file"), 101);
            }
        });

        toAdditional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNext();


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

    @Override
    public void onActivityResult(int requestCode, int resultCode,  Intent data) {
        if(requestCode == 101 && requestCode == RESULT_OK && data!=null){
            Uri path = data.getData();
            try {
                InputStream inputStream = getContext().getContentResolver().openInputStream(path);
                byte[] fileByte = new byte[inputStream.available()];
                inputStream.read(fileByte);
                encodedPdf = android.util.Base64.encodeToString(fileByte, Base64.DEFAULT);

                Toast.makeText(getContext(), encodedPdf,Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void userNext() {
        String talent = Talent.getText().toString().trim();
        String skillinesss = skill;

        if(talent.isEmpty()){
            Toast.makeText(getContext(),"Please Enter Talent",Toast.LENGTH_LONG).show();
            Talent.setError("Please Enter Talent");
            Talent.requestFocus();
            return;
        }

        if(skillinesss.isEmpty()){
            Toast.makeText(getContext(),"Please Add Skills",Toast.LENGTH_LONG).show();
            skillLine.setError("Please Add Skills");
            skillLine.requestFocus();
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("id", id);
        bundle2.putString("firstName",firstName);
        bundle2.putString("lastName", lastName);
        bundle2.putString("status", status);
        bundle2.putString("status_id", statusId);
        bundle2.putString("mainMail" , email);
        bundle2.putString("contactNumber", phoneNumber);
        bundle2.putString("address" , address);
        bundle2.putString("city" , city);
        bundle2.putString("zipcode", zipcode);
        bundle2.putString("stateId" , stateId);
        bundle2.putString("countryId", countryId);
        bundle2.putString("title" , title);
        bundle2.putString("companyName" , companyName);
        bundle2.putString("type", String.valueOf(0));
        bundle2.putString("preference" , String.valueOf(0));
        bundle2.putString("sourceId" , sourceId);
        bundle2.putString("ownerId" , ownerId);
        bundle2.putString("currentSalary" , currentSalary);
        bundle2.putString("hourlyRatel" , hourlyRateLow);
        bundle2.putString("desiredSalary" , desiredSalary);
        bundle2.putString("hourlyRateh" , hourlyRateHigh);
        bundle2.putString("comments", comments);
        bundle2.putString("availability_date", availabilityDate);
        bundle2.putString("job", job);
        bundle2.putString("accessibility", accessibility);
        bundle2.putString("created_date", createdDate);
        bundle2.putString("talent", Talent.getText().toString().trim());
        bundle2.putString("skill", skill);
        bundle2.putString("degree", Degree.getSelectedItem().toString().trim());



        ((AddCandidateActivity)getActivity()).addFragmentOnTop(new AddCandidateAdditionalFragment(), bundle2);
        ((AddCandidateActivity)getActivity()).changeViewForAdditional();

    }

}