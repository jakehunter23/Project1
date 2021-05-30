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
 * Use the {@link ClientNotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientNotesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String [] Name = {"Philip Martin","Philip Martin","Philip Martin"};
    String [] Type = {"Resume evaluation","Resume evaluation","Resume evaluation"};
    String [] Time = {"30 min","30 min","30 min"};
    String [] last = {"05-05-2021","05-05-2021","05-05-2021"};
    String [] timestamp = {"05-05-2021","05-05-2021","05-05-2021"};
    String Note = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt t labore et dolore magna aliqua. Ut enim ad inim veniam, quis nostrud exercitation";

    public ClientNotesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientNotesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientNotesFragment newInstance(String param1, String param2) {
        ClientNotesFragment fragment = new ClientNotesFragment();
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
        View view = inflater.inflate(R.layout.fragment_client_notes,container,false);
        RecyclerView NotesRecycler = view.findViewById(R.id.notecard);
        NotesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        NotesRecycler.setAdapter(new ClientNotesRecAdapter(Name,Type,Time,last,timestamp,Note));

        return view;
    }
}