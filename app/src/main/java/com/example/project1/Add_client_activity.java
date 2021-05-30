package com.example.project1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Add_client_activity extends AppCompatActivity {

    private static final String BACK_STACK_ROOT_TAG = "root_fragment";
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    Fragment fragment = null;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_client_activity);

        frameLayout = findViewById(R.id.add_client_fragment);
        fragment = new AddClientGeneralFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.add_client_fragment, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }


    public void addFragmentOnTop(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.add_client_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void changeViewForContact(){
        ImageView cardOuter = findViewById(R.id.imageView_2);
        cardOuter.setImageResource(R.drawable.circle_done);
        CardView dot4 = findViewById(R.id.card_dot_4);
        dot4.setCardBackgroundColor(Color.parseColor("#04C652"));
        CardView dot5 = findViewById(R.id.card_dot_5);
        dot5.setCardBackgroundColor(Color.parseColor("#FF04C652"));
        CardView dot6 = findViewById(R.id.card_dot_6);
        dot6.setCardBackgroundColor(Color.parseColor("#FF04C652"));
        CardView dot7 = findViewById(R.id.card_dot_7);
        dot7.setCardBackgroundColor(Color.parseColor("#FF04C652"));
        ImageView cardPresent = findViewById(R.id.imageView2_forAC);
        cardPresent.setImageResource(R.drawable.circle_new);

    }

    public void changeViewForBilling(){
        ImageView cardOuter = findViewById(R.id.imageView2_forAC);
        cardOuter.setImageResource(R.drawable.circle_done);
        CardView dot8 = findViewById(R.id.card_dot_8);
        dot8.setCardBackgroundColor(Color.parseColor("#04C652"));
        CardView dot9 = findViewById(R.id.card_dot_9);
        dot9.setCardBackgroundColor(Color.parseColor("#FF04C652"));
        CardView dot10 = findViewById(R.id.card_dot_10);
        dot10.setCardBackgroundColor(Color.parseColor("#FF04C652"));
        CardView dot11 = findViewById(R.id.card_dot_11);
        dot11.setCardBackgroundColor(Color.parseColor("#FF04C652"));
        ImageView cardPresent = findViewById(R.id.imageView3);
        cardPresent.setImageResource(R.drawable.circle_new);
    }

}
