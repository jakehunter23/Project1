package com.example.project1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactJobsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactJobsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Spinner hiresSpinner;

    public ContactJobsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactJobsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactJobsFragment newInstance(String param1, String param2) {
        ContactJobsFragment fragment = new ContactJobsFragment();
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
        View view = inflater.inflate(R.layout.fragment_contact_jobs, container, false);
        RecyclerView CJRec = view.findViewById(R.id.con_job_rec);
        CJRec.setLayoutManager(new LinearLayoutManager(getContext()));
        CJRec.setAdapter(new ContactJobRecAdapter());


        hiresSpinner=view.findViewById(R.id.spinner53);
        ArrayList<String> list=new ArrayList<>();
        list.add("High");
        list.add("Medium");
        list.add("Low");
        ArrayAdapter Adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,list);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hiresSpinner.setAdapter(Adapter);


        return view;
    }
}