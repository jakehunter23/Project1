package com.example.project1;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActurialActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem tab1, tab2, tab3, tab4, tab5, tab6;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    Fragment fragment =null;
    FragmentTransaction fragmentTransaction;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acturial_glance);

        tabLayout=findViewById(R.id.act_glance_tabView);
        tab1 = findViewById(R.id.act_glance);
        tab2 = findViewById(R.id.act_appt);
        tab3 =findViewById(R.id.act2);
        tab4 = findViewById(R.id.act_file);
        tab5 = findViewById(R.id.act_note);
        tab6 = findViewById(R.id.act_publish);
        frameLayout = findViewById(R.id.act_host_fragment);
        fragment = new ActurialGlanceFragment();
        fragmentManager =getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.act_host_fragment,fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new ActurialGlanceFragment();
                        break;
                    case 1:
                        fragment = new ActurialApplicantsFragment();
                        break;
                    case 2:
                        fragment = new ActurialActivityFragment();
                        break;
                    case 3:
                        fragment = new ActurialFilesFragment();
                        break;
                    case 4:
                        fragment = new ActurialNotesFragment();
                        break;
                    case 5:
                        fragment = new ActurialPublishFragment();
                        break;

                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.act_host_fragment, fragment);
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
}
