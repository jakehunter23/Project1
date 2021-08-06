package com.example.project1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AddContactActivity extends AppCompatActivity {

    private static final String BACK_STACK_ROOT_TAG = "root_fragment";
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    Fragment fragment = null;
    FragmentTransaction fragmentTransaction;
    Bundle bundle = new Bundle();
    String id, first_name, last_name, middle_name, status, email, contact_number, address, city, zipcode, stateId, countryId;
    String title, companyName, contactTypeId, division, sourceId, reportToId, industryId, lastContactDate, lastVisitDate, visibility, validity, created_date, image_data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_activity);

        Intent intent = getIntent();
        if(intent.hasExtra("id")) {
            id = getIntent().getExtras().getString("id");
            first_name = getIntent().getExtras().getString("first_name");
            last_name = getIntent().getExtras().getString("last_name");
            middle_name = getIntent().getExtras().getString("middle_name");
            status = getIntent().getExtras().getString("status");
            email = getIntent().getExtras().getString("email");
            contact_number = getIntent().getExtras().getString("contact_number");
            address = getIntent().getExtras().getString("address");
            city = getIntent().getExtras().getString("city");
            zipcode = getIntent().getExtras().getString("zipcode");
            stateId = getIntent().getExtras().getString("state_id");
            countryId = getIntent().getExtras().getString("country_id");
            title = getIntent().getExtras().getString("current_title");
            companyName = getIntent().getExtras().getString("company_name");
            contactTypeId = getIntent().getExtras().getString("contact_type_id");
            division = getIntent().getExtras().getString("division");
            sourceId = getIntent().getExtras().getString("source_id");
            reportToId = getIntent().getExtras().getString("report_to_id");
            industryId = getIntent().getExtras().getString("industry_id");
            lastContactDate = getIntent().getExtras().getString("last_contact_date");
            lastVisitDate = getIntent().getExtras().getString("last_visit_date");
            visibility = getIntent().getExtras().getString("visibility");
            validity = getIntent().getExtras().getString("validity");
            created_date= getIntent().getExtras().getString("created_date");
            image_data= getIntent().getExtras().getString("image_data");

        }

        bundle.putString("id", id);
        bundle.putString("first_name", first_name);
        bundle.putString("last_name", last_name);
        bundle.putString("middle_name", middle_name);
        bundle.putString("status", status);
        bundle.putString("email", email);
        bundle.putString("contact_number", contact_number);
        bundle.putString("address", address);
        bundle.putString("city", city);
        bundle.putString("zipcode", zipcode);
        bundle.putString("state_id", stateId);
        bundle.putString("country_id", countryId);
        bundle.putString("current_title", title);
        bundle.putString("company_name", companyName);
        bundle.putString("contact_type_id", contactTypeId);
        bundle.putString("division", division);
        bundle.putString("source_id", sourceId);
        bundle.putString("report_to_id", reportToId);
        bundle.putString("industry_id", industryId);
        bundle.putString("last_contact_date", lastContactDate);
        bundle.putString("last_visit_date", lastVisitDate);
        bundle.putString("visibility", visibility);
        bundle.putString("validity", validity);
        bundle.putString("created_date", created_date);
        bundle.putString("image_data", image_data);

        frameLayout = findViewById(R.id.add_contact_fragment);
        fragment = new AddContactPersonalFragment();
        fragment.setArguments(bundle);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.add_contact_fragment, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    public void addFragmentOnTop(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.add_contact_fragment, fragment)
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
