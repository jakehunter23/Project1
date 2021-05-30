package com.example.project1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientFilesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientFilesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String [] filename = {"Filename.pdf","Filename.pdf","Filename.pdf","Filename.pdf","Filename.pdf"};
    String [] fileSize = {"300Kb","300Kb","300Kb","300Kb","300Kb"};
    String  verify = "Internal System verified";
    String [] person_name = {"Sasuke Uchiha","Sasuke Uchiha","Sasuke Uchiha","Sasuke Uchiha","Sasuke Uchiha"};
    String [] date = {"05-05-2021","05-05-2021","05-05-2021","05-05-2021","05-05-2021"};

    public ClientFilesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientFilesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientFilesFragment newInstance(String param1, String param2) {
        ClientFilesFragment fragment = new ClientFilesFragment();
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
        View view = inflater.inflate(R.layout.fragment_client_files,container,false);
        RecyclerView FilesRecycler = view.findViewById(R.id.filecard);
        FilesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        FilesRecycler.setAdapter(new ClientFilesRecAdapter(filename,fileSize,verify,person_name,date));
        return view;
    }
}