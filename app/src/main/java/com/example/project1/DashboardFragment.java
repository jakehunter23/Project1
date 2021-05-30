package com.example.project1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView Dash1RecyclerView;
    private RecyclerView Dash2Recyclerview;
    private RecyclerView Dash3Recyclerview;
    private ImageButton taskButton;
    private ImageButton mailButton;
    private ImageButton fileButton;
    private ImageView Menu;
    Button CalInt;

    String [] names={"Job created","Applied candidate","Interviews Done","Hired candidate","Task Assigned","Other Statistics"};
    int [] numbers = {140,250,310,140,437,437};
    String [] name = {"Jean Gray","Cyclops","Thor","Tony Stark"};
    String [] desig = {"Android Developer","MERN stack developer","Android Studio developer","Data scientist"};
    String [] date = {"12th May 2021","12th May 2021","12th May 2021","12th May 2021"};
    int[] dp = {R.drawable.dp_1,R.drawable.small_dp_1,R.drawable.ellipse_1,R.drawable.ellipse_234};
    int [] bg = {R.color.yellow,R.color.red,R.color.light_green, R.color.red,R.color.light_green,R.color.yellow};

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Dash1RecyclerView=view.findViewById(R.id.dash_rec1);
        Dash1RecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        Dash1RecyclerView.setAdapter(new DashboardRec1Adapter(names,numbers,bg));
        Dash2Recyclerview=view.findViewById(R.id.dash_rec2);
        Dash2Recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        Dash2Recyclerview.setAdapter(new DashboardRec2Adapter(name,desig,dp));
        Dash3Recyclerview=view.findViewById(R.id.dash_rec3);
        Dash3Recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        Dash3Recyclerview.setAdapter(new DashboardRec3Adapter(name,date,getContext(),dp));


        Menu=view.findViewById(R.id.menu_icon);
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),NavActivity.class);
                startActivity(i);
            }
        });

        CalInt = view.findViewById(R.id.button26);
        CalInt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);

                startActivity(intent);
            }


        });


        taskButton = view.findViewById(R.id.tasksButton);
        taskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MyTaskActivity.class);
                startActivity(intent);
            }
        });

        fileButton = view.findViewById(R.id.filesButton);
        fileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MyFilesActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}