package com.example.project1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CandidateStatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CandidateStatusFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Spinner status;

    public CandidateStatusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CandidateStatusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CandidateStatusFragment newInstance(String param1, String param2) {
        CandidateStatusFragment fragment = new CandidateStatusFragment();
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
        View view= inflater.inflate(R.layout.fragment_candidate_status, container, false);
        status=view.findViewById(R.id.statusSpinner);

        ArrayList<String> status_List=new ArrayList<>();
        status_List.add("Inactive");
        status_List.add("Active");
        status_List.add("Placed by client");
        status_List.add("Seeking");
        status_List.add("Blacklist");
        ArrayAdapter StatusAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,status_List);
        StatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       status.setAdapter(StatusAdapter);




        return  view;
    }
}