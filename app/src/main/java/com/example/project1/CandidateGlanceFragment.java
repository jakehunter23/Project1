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
 * Use the {@link CandidateGlanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CandidateGlanceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String [] SkillName={"PHP","MySQL","Node.js"};
    float [] ratings={(float) 2.5, (float) 2.5, (float) 2.5};

    String [] jobRole={"MERN stack developer","MERN stack developer"};
    String [] companyName={"Company name","Company name"};
    String [] location={"City","City"};
    String  jobDes="Job description Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy";
    String [] datef={"November 2017","November 2017"};
    String [] dateto={"December 2020","December 2020"};

    String [] Company={"Fox Hunt","Atica"};
    String [] per={"30%","60%"};
    String [] taskName={"Task Name","Task Name"};
    String [] taskType={"Type of the Task","Type of the Task"};
    String [] da={"01-05-2020","01-05-2020"};
    String [] dd={"08-05-2020","08-05-2020"};

    String [] language={"English","Hindi","Spanish"};
    String [] fluency={"Fluent","Conversational","Basic"};

    String [] degree = {"Bachelor of Technology (B.Tech.)","Bachelor of Technology (B.Tech.)"};
    String [] course={"Computer Science","Computer Science"};
    String [] institute={"Indian Institute of Information Technology (IIT), Sri City, Chitoor District","Indian Institute of Information Technology (IIT), Sri City, Chitoor District"};
    String [] dateIn={"2018","2018"};
    String [] dateOut={"2021(Expected)","2021(Expected)"};

    String [] certCourse={"Typography-01","Typography-01"};
    String [] certIn={"The Futur","The Futur"};
    String [] certDate={"2020","2020"};

    String [] title={"Head of the cybersecurity club in college","Freelaced as a web developer for one year"};
    String [] tdi={"2017","2018"};
    String [] tdo={"2019","2021(Expected)"};

    public CandidateGlanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CandidateGlanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CandidateGlanceFragment newInstance(String param1, String param2) {
        CandidateGlanceFragment fragment = new CandidateGlanceFragment();
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
        View view = inflater.inflate(R.layout.fragment_candidate_glance, container, false);
        RecyclerView recyclerView1 = view.findViewById(R.id.cand_skill_recycler);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView1.setAdapter(new CandidateSkillsRecAdapter(SkillName,ratings));
        RecyclerView recyclerView2 =view.findViewById(R.id.cand_employ_recycler);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setAdapter(new CandidateEmploymentRecAdapter(jobRole,companyName,location,jobDes,datef,dateto));
        RecyclerView recyclerView3 = view.findViewById(R.id.cand_task_recycler);
        recyclerView3.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView3.setAdapter(new CandidateTaskRecAdapter(companyName,per,taskName,taskType,da,dd));
        RecyclerView recyclerView4 = view.findViewById(R.id.cand_lang_recycler);
        recyclerView4.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView4.setAdapter(new CandidateLanguageRecAdapter(language,fluency));
        RecyclerView recyclerView5=view.findViewById(R.id.cand_education_recycler);
        recyclerView5.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView5.setAdapter(new CandidateEducationRecAdapter(degree,course,institute,dateIn,dateOut));
        RecyclerView recyclerView6=view.findViewById(R.id.cand_certificate_recycler);
        recyclerView6.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView6.setAdapter(new CandidateCertRedAdapter(certCourse,certIn,certDate));
        RecyclerView recyclerView7 = view.findViewById(R.id.cand_achieve_recycler);
        recyclerView7.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView7.setAdapter(new CandidateAchieveRecAdapter(title,tdi,tdo));

        return view;
    }
}