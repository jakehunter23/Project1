package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class ClientActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem tab1, tab2, tab3, tab4, tab5, tab6, tab7;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    Fragment fragment =null;
    FragmentTransaction fragmentTransaction;
    LinearLayout expand;
    RelativeLayout more_info;
    ImageView arrow;
    TextView information;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_glance);

        tabLayout = findViewById(R.id.client_activity_tab);
        tab1 = findViewById(R.id.client_at_a_glance);
        tab2 = findViewById(R.id.client_hires);
        tab3 =findViewById(R.id.client_contacts);
        tab4 = findViewById(R.id.client_apps);
        tab5 = findViewById(R.id.client_act);
        tab6 =findViewById(R.id.client_files);
        tab7 = findViewById(R.id.client_notes);

        expand=findViewById(R.id.expand_layout);
        more_info=findViewById(R.id.item_description_layout);
        expand.setVisibility(View.GONE);
        arrow=findViewById(R.id.item_description_img);
        information=findViewById(R.id.item_description_title);

        frameLayout = findViewById(R.id.tab_host_fragment);
        fragment = new ClientGlanceFragment();
        fragmentManager =getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.tab_host_fragment,fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();

        more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expand.getVisibility() == View.VISIBLE) {


                    TransitionManager.beginDelayedTransition(expand,
                            new AutoTransition());
                    expand.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.expand_more);
                    information.setText(R.string.show_more);

                }  else {
                    TransitionManager.beginDelayedTransition(expand,
                            new AutoTransition());
                    expand.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.expand_less);
                    information.setText(R.string.show_less);
                }
            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new ClientGlanceFragment();
                        break;
                    case 1:
                        fragment = new ClientHiresFragment();
                        break;
                    case 2:
                        fragment = new ClientContactsFragment();
                        break;
                    case 3:
                        fragment = new ClientAppointmentFragment();
                        break;
                    case 4:
                        fragment = new ClientActivityFragment();
                        break;
                    case 5:
                        fragment = new ClientFilesFragment();
                        break;
                    case 6:
                        fragment = new ClientNotesFragment();
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.tab_host_fragment, fragment);
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
        Intent intent = new Intent(ClientActivity.this,
               MyClients.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);

    }
}
