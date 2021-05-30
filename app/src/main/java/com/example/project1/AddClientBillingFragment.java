package com.example.project1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddClientBillingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddClientBillingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
     String insertClient = "https://demotic-recruit.000webhostapp.com/client_insert.php";

    String statusItem;
    int ownershipId, indutryId, sourceId, activeContactId, parentCompanyId, stateId, countryId;
    float vat;
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = dateFormat.format(date);
    String companyName, companyUrl, description, email, phoneNumber, address, city, zipcode, bankName, bankId, bankAccountNumber, iban;

    EditText BankName;
    EditText BankId;
    EditText BankAccNo;
    EditText IBAN;
    EditText VAT;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddClientBillingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddClientBillingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddClientBillingFragment newInstance(String param1, String param2) {
        AddClientBillingFragment fragment = new AddClientBillingFragment();
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
        View view = inflater.inflate(R.layout.add_client_billing, container, false);


        Bundle bundle = this.getArguments();
        companyName = bundle.getString("companyName");
        companyUrl = bundle.getString("companyUrl");
        activeContactId=bundle.getInt("activeContactId");
        parentCompanyId=bundle.getInt("parentId");
        statusItem=bundle.getString("status");
        ownershipId=bundle.getInt("ownershipId");
        sourceId=bundle.getInt("sourceId");
        indutryId=bundle.getInt("industryId");
        description=bundle.getString("companyDescription");
        email=bundle.getString("email");
        phoneNumber=bundle.getString("contactNumber");
        address=bundle.getString("address");
        city=bundle.getString("city");
        zipcode=bundle.getString("zipcode");
        stateId=bundle.getInt("stateId");
        countryId=bundle.getInt("countryId");

        BankName = view.findViewById(R.id.editTextTextPersonName50);
        BankId = view.findViewById(R.id.editTextTextPersonName51);
        BankAccNo = view.findViewById(R.id.editTextTextPersonName52);
        IBAN=view.findViewById(R.id.editTextTextPersonName53);
        VAT = view.findViewById(R.id.editTextTextPersonName54);

        Button save = view.findViewById(R.id.ac_billing_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertData();

            }
        });

        return view;
    }
    float ParseFloat(String string) {
        if (string != null && string.length() > 0) {
            try {
                return Float.parseFloat(string);
            } catch(Exception e) {
                return 0;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        }
        else return 0;
    }

    private void insertData() {

        bankName = BankName.getText().toString().trim();
        bankId = BankId.getText().toString().trim();
        bankAccountNumber = BankAccNo.getText().toString().trim();
        iban = IBAN.getText().toString().trim();
        String vatString = VAT.getText().toString().trim();
        vat = ParseFloat(vatString);

        StringRequest insertRequest = new StringRequest(Request.Method.POST, insertClient, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Intent toClientGlance = new Intent(getContext(),SplashScreenActivity.class);
                startActivity(toClientGlance);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> param = new HashMap<String, String>();
                param.put("parent_id", String.valueOf(parentCompanyId));
                param.put("creator_id", String.valueOf(1));
                param.put("active_contact_id", String.valueOf(activeContactId));
                param.put("source_id", String.valueOf(sourceId));
                param.put("ownership_id", String.valueOf(ownershipId));
                param.put("industry_id", String.valueOf(indutryId));
                param.put("name", companyName);
                param.put("status",statusItem);
                param.put("email",email);
                param.put("phone_number",phoneNumber);
                param.put("created_date", strDate);
                param.put("url",companyUrl);
                param.put("description",description);
                param.put("address",address);
                param.put("state_id", String.valueOf(stateId));
                param.put("country_id", String.valueOf(countryId));
                param.put("city",city);
                param.put("zipcode",zipcode);
                param.put("bank_name",bankName);
                param.put("bank_id",bankId);
                param.put("bank_account_number",bankAccountNumber);
                param.put("IBAN",iban);
                param.put("VAT", String.valueOf(vat));

                return param;
            }
        };




        Volley.newRequestQueue(getContext()).add(insertRequest);

    }


}