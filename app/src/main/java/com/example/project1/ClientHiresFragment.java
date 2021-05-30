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
 * Use the {@link ClientHiresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientHiresFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int [] dot_img = {R.drawable.dot_red,R.drawable.dot_yellow,R.drawable.dot_yellow};
    String [] date = {"05-05-2021","05-05-2021","05-05-2021"};
    String [] typejob = {"Full-time","Part-time","Full-time"};
    String [] con_name = {"Philip Martin","Philip Martin","Philip Martin"};
    String [] con_mail = {"sandrovicenta@gmail.com","sandrovicenta@gmail.com","sandrovicenta@gmail.com"};
    String [] rec = {"Sandro Vicenta","Sandro Vicenta","Sandro Vicenta"};
    String [] rec_mail = {"sandrovicenta@gmail.com","sandrovicenta@gmail.com","sandrovicenta@gmail.com"};

    public ClientHiresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientHiresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientHiresFragment newInstance(String param1, String param2) {
        ClientHiresFragment fragment = new ClientHiresFragment();
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
        View view = inflater.inflate(R.layout.fragment_client_hires, container, false);
        RecyclerView hiresRecycler = view.findViewById(R.id.hireCard);
        hiresRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        hiresRecycler.setAdapter(new ClientHiresRecAdapter(dot_img,date,typejob,con_name,con_mail,rec,rec_mail));

        return view;
    }
}