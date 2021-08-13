package com.example.project1;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class  DashBoardActivity extends AppCompatActivity {

    private MeowBottomNavigation bottomNavigationView;
    public static String username;
    String fetchString = "https://demotic-recruit.000webhostapp.com/fetch_spec_signup.php";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        bottomNavigationView = findViewById(R.id.Dash_nav);
        bottomNavigationView.add(new MeowBottomNavigation.Model(1, R.drawable.home_icon));
        bottomNavigationView.add(new MeowBottomNavigation.Model(2, R.drawable.call));
        bottomNavigationView.add(new MeowBottomNavigation.Model(3, R.drawable.add_icon));
        bottomNavigationView.add(new MeowBottomNavigation.Model(4, R.drawable.person_outline));

        bottomNavigationView.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch (item.getId()) {
                    case 1:
                        fragment = new DashboardFragment();
                        break;
                    case 2:
                        fragment = new CallingFragment();
                        break;
                    case 3:
                        fragment = new AddingFragment();
                        break;
                    case 4:
                        fragment = new MessageFragment();
                }
                loadFragment(fragment);

            }
        });
        bottomNavigationView.show(1, true);
        bottomNavigationView.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        bottomNavigationView.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });





    }



    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.Main_frame,fragment)
                .commit();
    }

}
