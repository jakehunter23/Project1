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
 * Use the {@link ClientContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientContactsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String [] Name = {"Acturial Assistant","Acturial Assistant","Acturial Assistant","Acturial Assistant","Acturial Assistant"};
    String [] last_date = {"05-05-2021","05-05-2021","05-05-2021","05-05-2021","05-05-2021"};
    String [] number = {"9007861268","9007861268","9007861268","9007861268","9007861268"};
    String [] email = {"sayanbhatacharya@gmail.com","sayanbhatacharya@gmail.com","sayanbhatacharya@gmail.com","sayanbhatacharya@gmail.com","sayanbhatacharya@gmail.com"};
    String [] added_date = {"05-05-2021","05-05-2021","05-05-2021","05-05-2021","05-05-2021"};

    public ClientContactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientContactsFragment newInstance(String param1, String param2) {
        ClientContactsFragment fragment = new ClientContactsFragment();
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
        View view = inflater.inflate(R.layout.fragment_client_contacts, container, false);
        RecyclerView contactRecycler = view.findViewById(R.id.con_card_rec);
        contactRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        contactRecycler.setAdapter(new ClientContactRecAdapter(Name,last_date,number,email,added_date));

        return view;
    }
}