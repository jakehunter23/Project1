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
    Bundle bundle = new Bundle();
    String id, name,  phoneNumber, address,  email, createdDate, parentId, creatorId, activeContactId, sourceId, ownershipId, industryId, status, url, description, stateId, countryId, city, zipcode, bankName;
    private String bankId, bankAccountNumber, IBAN, VAT;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_client_activity);

        Intent intent = getIntent();
        if(intent.hasExtra("id")) {
            id = getIntent().getExtras().getString("id");
            parentId = getIntent().getExtras().getString("parent_id");
            creatorId = getIntent().getExtras().getString("creator_id");
            activeContactId = getIntent().getExtras().getString("active_contact_id");
            sourceId = getIntent().getExtras().getString("source_id");
            ownershipId = getIntent().getExtras().getString("ownership_id");
            industryId = getIntent().getExtras().getString("industry_id");
            status = getIntent().getExtras().getString("status");
            phoneNumber = getIntent().getExtras().getString("phone_number");
            createdDate = getIntent().getExtras().getString("created_date");
            url = getIntent().getExtras().getString("url");
            description = getIntent().getExtras().getString("description");
            stateId = getIntent().getExtras().getString("state_id");
            countryId = getIntent().getExtras().getString("country_id");
            city = getIntent().getExtras().getString("city");
            zipcode = getIntent().getExtras().getString("zipcode");
            bankName = getIntent().getExtras().getString("bank_name");
            bankId = getIntent().getExtras().getString("bank_id");
            bankAccountNumber = getIntent().getExtras().getString("bank_account_number");
            IBAN = getIntent().getExtras().getString("IBAN");
            VAT = getIntent().getExtras().getString("VAT");
            name = getIntent().getExtras().getString("name");
            email = getIntent().getExtras().getString("email");
            address=getIntent().getExtras().getString("address");

        }

        bundle.putString("id", id);
        bundle.putString("parent_id", parentId);
        bundle.putString("creator_id", creatorId);
        bundle.putString("active_contact_id", activeContactId);
        bundle.putString("source_id", sourceId);
        bundle.putString("ownership_id", ownershipId);
        bundle.putString("industry_id", industryId);
        bundle.putString("status", status);
        bundle.putString("phone_number", phoneNumber);
        bundle.putString("created_date", createdDate);
        bundle.putString("url", url);
        bundle.putString("description", description);
        bundle.putString("state_id", stateId);
        bundle.putString("country_id", countryId);
        bundle.putString("city", city);
        bundle.putString("zipcode", zipcode);
        bundle.putString("bank_name", bankName);
        bundle.putString("bank_id", bankId);
        bundle.putString("bank_account_number", bankAccountNumber);
        bundle.putString("IBAN", IBAN);
        bundle.putString("VAT", VAT);
        bundle.putString("name", name);
        bundle.putString("email", email);
        bundle.putString("address", address);

        frameLayout = findViewById(R.id.add_client_fragment);
        fragment = new AddClientGeneralFragment();
        fragment.setArguments(bundle);
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
