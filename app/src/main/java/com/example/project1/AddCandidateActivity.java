package com.example.project1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AddCandidateActivity extends AppCompatActivity {
    private static final String BACK_STACK_ROOT_TAG = "root_fragment";
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    Fragment fragment = null;
    FragmentTransaction fragmentTransaction;
    Bundle bundle = new Bundle();
    String id, firstName, lastName, status, statusId, email, phoneNumber, address, city, zipcode, type, preference, sourceId, ownerId, currentSalary, desiredSalary, stateId, countryId, title, companyName, hourlyRateLow, hourlyRateHigh, talent, skill, degree, comments, availabilityDate, job, accessibility, createdDate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_candidate_activity);

        Intent intent = getIntent();
        if(intent.hasExtra("id")) {
            id = getIntent().getExtras().getString("id");
            firstName = getIntent().getExtras().getString("first_name");
            lastName = getIntent().getExtras().getString("last_name");
            statusId = getIntent().getExtras().getString("status_id");
            status = getIntent().getExtras().getString("status");
            email = getIntent().getExtras().getString("email");
            phoneNumber = getIntent().getExtras().getString("contact_number");
            address = getIntent().getExtras().getString("address");
            city = getIntent().getExtras().getString("city");
            zipcode = getIntent().getExtras().getString("zipcode");
            stateId = getIntent().getExtras().getString("state_id");
            countryId = getIntent().getExtras().getString("country_id");
            title = getIntent().getExtras().getString("current_title");
            companyName = getIntent().getExtras().getString("company_name");
            type = getIntent().getExtras().getString("type");
            preference = getIntent().getExtras().getString("preference");
            ownerId = getIntent().getExtras().getString("owner_id");
            sourceId = getIntent().getExtras().getString("source_id");
            currentSalary = getIntent().getExtras().getString("current_salary");
            desiredSalary = getIntent().getExtras().getString("desired_salary");
            hourlyRateHigh = getIntent().getExtras().getString("hourly_rate_high");
            hourlyRateLow = getIntent().getExtras().getString("hourly_rate_low");
            talent = getIntent().getExtras().getString("talent");
            skill = getIntent().getExtras().getString("skill");
            degree = getIntent().getExtras().getString("degree");
            comments = getIntent().getExtras().getString("comments");
            availabilityDate = getIntent().getExtras().getString("availability_date");
            job = getIntent().getExtras().getString("job");
            accessibility = getIntent().getExtras().getString("accessibility");
            createdDate = getIntent().getExtras().getString("created_date");

        }

        bundle.putString("id", id);
        bundle.putString("first_name", firstName);
        bundle.putString("last_name", lastName);
        bundle.putString("status_id", statusId);
        bundle.putString("status", status);
        bundle.putString("email", email);
        bundle.putString("contact_number", phoneNumber);
        bundle.putString("address", address);
        bundle.putString("city", city);
        bundle.putString("zipcode", zipcode);
        bundle.putString("state_id", stateId);
        bundle.putString("country_id", countryId);
        bundle.putString("current_title", title);
        bundle.putString("company_name", companyName);
        bundle.putString("type", type);
        bundle.putString("preference", preference);
        bundle.putString("owner_id", ownerId);
        bundle.putString("source_id", sourceId);
        bundle.putString("current_salary", currentSalary);
        bundle.putString("desired_salary", desiredSalary);
        bundle.putString("hourly_rate_high", hourlyRateHigh);
        bundle.putString("hourly_rate_low", hourlyRateLow);
        bundle.putString("talent", talent);
        bundle.putString("skill", skill);
        bundle.putString("degree", degree);
        bundle.putString("comments", comments);
        bundle.putString("availability_date", availabilityDate);
        bundle.putString("job", job);
        bundle.putString("accessibility", accessibility);
        bundle.putString("created_date", createdDate);

        frameLayout = findViewById(R.id.add_candidate_fragment);
        fragment = new AddCandidatePersonalFragment();
        fragment.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.add_candidate_fragment, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();


    }

    public void addFragmentOnTop(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.add_candidate_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void changeViewForProfessional(){
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

    public void changeViewForSkill(){
        ImageView cardOuter = findViewById(R.id.imageView_2);
        cardOuter.setImageResource(R.drawable.circle_done);
        CardView dot1 = findViewById(R.id.dot_on_1);
        dot1.setVisibility(View.VISIBLE);
        dot1.setCardBackgroundColor(Color.parseColor("#04C652"));
        CardView dot2 = findViewById(R.id.dot_on_2);
        dot2.setVisibility(View.VISIBLE);
        dot2.setCardBackgroundColor(Color.parseColor("#04C652"));
        CardView dot3 = findViewById(R.id.dot_on_3);
        dot3.setVisibility(View.VISIBLE);
        dot3.setCardBackgroundColor(Color.parseColor("#04C652"));
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
        CardView dot12 = findViewById(R.id.card_dot_12);
        dot12.setVisibility(View.INVISIBLE);
        CardView dot13 = findViewById(R.id.card_dot_13);
        dot13.setVisibility(View.INVISIBLE);
        CardView dot14 = findViewById(R.id.card_dot_14);
        dot14.setVisibility(View.INVISIBLE);

    }

    public void changeViewForAdditional(){
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
