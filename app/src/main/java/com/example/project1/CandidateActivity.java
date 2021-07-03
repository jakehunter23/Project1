package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

public class CandidateActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem tab1, tab2, tab3, tab4, tab5;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    Fragment fragment =null;
    FragmentTransaction fragmentTransaction;
    RelativeLayout contact,info;
    ImageView arrow;
    TextView moreOrLess;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candidate_glance);
        contact=findViewById(R.id.contactGlance);
        contact.setVisibility(View.GONE);
        info=findViewById(R.id.item_description_layout);
        arrow=findViewById(R.id.item_description_img);
        moreOrLess=findViewById(R.id.item_description_title);

        tabLayout=findViewById(R.id.cand_glance_tabView);
        tab1 = findViewById(R.id.cand_glance);
        tab2 = findViewById(R.id.cand_status);
        tab3 =findViewById(R.id.cand_act);
        tab4 = findViewById(R.id.cand_files);
        tab5 = findViewById(R.id.cand_notes);
        frameLayout = findViewById(R.id.cand_fragment_host);
        fragment = new CandidateGlanceFragment();
        fragmentManager =getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.cand_fragment_host,fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();



        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contact.getVisibility() == View.VISIBLE) {


                    TransitionManager.beginDelayedTransition(contact,
                            new AutoTransition());
                   contact.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.expand_more);
                    moreOrLess.setText("More information");

                }  else {
                    TransitionManager.beginDelayedTransition(contact,
                            new AutoTransition());
                    contact.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.expand_less);
                    moreOrLess.setText("Less information");
                }


            }

        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new CandidateGlanceFragment();
                        break;
                    case 1:
                        fragment = new CandidateStatusFragment();
                        break;
                    case 2:
                        fragment = new CandidateActivityFragment();
                        break;
                    case 3:
                        fragment = new CandidateFilesFragment();
                        break;
                    case 4:
                        fragment = new CandidateNotesFragment();
                        break;

                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.cand_fragment_host, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CandidateActivity.this,
                MyDatabase.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }
}
