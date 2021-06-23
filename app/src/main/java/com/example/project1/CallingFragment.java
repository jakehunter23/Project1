package com.example.project1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CallingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CallingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView textView;
    List<ChatList_Users> list_users;
    RecyclerView recyclerView;
    Chat_List_Adapter chat_list_adapter;


    public CallingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CallingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CallingFragment newInstance(String param1, String param2) {
        CallingFragment fragment = new CallingFragment();
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
        View view=inflater.inflate(R.layout.fragment_calling, container, false);
        textView=view.findViewById(R.id.demo_id);
        recyclerView=view.findViewById(R.id.recycler_users_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list_users=new ArrayList<>();
        
        readUsers();

        return view;

    }

    private void readUsers() {

        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                list_users.clear();
                for (DataSnapshot data:snapshot.getChildren()){
                    ChatList_Users user=data.getValue(ChatList_Users.class);
                    if(!user.getid().equals(firebaseUser.getUid())){
                        list_users.add(user);
                    }


                }
                chat_list_adapter=new Chat_List_Adapter(getContext(),list_users);
                // chat_list_adapter.notifyDataSetChanged();
                recyclerView.setAdapter(chat_list_adapter);


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}