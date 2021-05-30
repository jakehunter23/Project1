package com.example.project1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactGlanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactGlanceFragment extends Fragment {

    String [] Company={"Fox Hunt","Atica"};
    String [] per={"30%","60%"};
    String [] taskName={"Task Name","Task Name"};
    String [] taskType={"Type of the Task","Type of the Task"};
    String [] da={"01-05-2020","01-05-2020"};
    String [] dd={"08-05-2020","08-05-2020"};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactGlanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactGlanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactGlanceFragment newInstance(String param1, String param2) {
        ContactGlanceFragment fragment = new ContactGlanceFragment();
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
        View view = inflater.inflate(R.layout.fragment_contact_glance, container, false);
        RecyclerView ConTaskRec = view.findViewById(R.id.con_task_recycler);
        ConTaskRec.setLayoutManager(new LinearLayoutManager(getContext()));
        ConTaskRec.setAdapter(new ContactTaskRecAdapter(Company,per,taskName,taskType,da,dd));
        RecyclerView ConJobRec =view.findViewById(R.id.con_job_recycler);
        ConJobRec.setLayoutManager(new LinearLayoutManager(getContext()));
        ConJobRec.setAdapter(new ConGlaJobAdapter());
        return view;
    }
}