package com.example.project1;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateTaskFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateTaskFragment2 extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button next;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateTaskFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateTaskFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateTaskFragment2 newInstance(String param1, String param2) {
        CreateTaskFragment2 fragment = new CreateTaskFragment2();
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
        View view = inflater.inflate(R.layout.create_task_1, container, false);
        CardView item1 =view.findViewById(R.id.task2_card_item);
        CardView item2 = view.findViewById(R.id.task2_card_item2);
        item2.setElevation(0);
        CardView item3 = view.findViewById(R.id.task2_card_item4);
        item3.setElevation(0);
        CardView item4 =view.findViewById(R.id.task2_card_item3);
        item4.setElevation(0);
        Button next = view.findViewById(R.id.button29);
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item1.getCardElevation()==0){
                    item1.setElevation(25);
                    next.setVisibility(View.VISIBLE);
                }
                else{
                    item1.setElevation(0);
                    next.setVisibility(View.INVISIBLE);
                }
            }
        });

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item2.getCardElevation()==0){
                    item2.setElevation(25);
                }
                else{
                    item2.setElevation(0);
                }
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item3.getCardElevation()==0){
                    item3.setElevation(25);
                }
                else{
                    item3.setElevation(0);
                }
            }
        });

        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item4.getCardElevation()==0){
                    item4.setElevation(25);
                }
                else{
                    item4.setElevation(0);
                }
            }
        });

        if(item1.getCardElevation()==0 && item2.getCardElevation()==0 && item3.getCardElevation()==0 && item4.getCardElevation()==0){
            next.setVisibility(View.INVISIBLE);
        }
        else{
            next.setVisibility(View.VISIBLE);
        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((CreateTask)getActivity()).addFragmentOnTop(new CreateTaskFragment3());
            }
        });
        return view;
    }

    
}