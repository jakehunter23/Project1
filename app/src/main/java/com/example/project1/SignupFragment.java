package com.example.project1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseAuth mAuth;

    private DatabaseReference databaseReference;
    private String eemail, eeusername;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
      View view = inflater.inflate(R.layout.fragment_signup, container, false);
      EditText username = view.findViewById(R.id.signup_edit_user);
      String user = username.getText().toString().trim();
      EditText email =view.findViewById(R.id.signup_edit_email);
      String mail = email.getText().toString().trim();
      EditText pass = view.findViewById(R.id.signup_edit_pass);
      String password = pass.getText().toString().trim();
        CheckBox terms = view.findViewById(R.id.checkBox2);
        Button signup = view.findViewById(R.id.signup_button);


        //Firebase
        mAuth=FirebaseAuth.getInstance();




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Enter first name", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Enter email", Toast.LENGTH_SHORT).show();
                } else if (pass.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Enter password", Toast.LENGTH_SHORT).show();
                }else if(!terms.isChecked()) {
                    Toast.makeText(getContext(),"Agree to Terms First",Toast.LENGTH_SHORT).show();
                    /* else if (!emailValidator(inputemail.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                } */
                } else {

                    HashMap<String, String> params = new HashMap<>();
                    params.put("username", username.getText().toString());
                    params.put("email", email.getText().toString());
                    params.put("password", pass.getText().toString());

                    eemail=email.getText().toString();
                    eeusername= username.getText().toString();


                    registerUsingFirebase(email.getText().toString(),pass.getText().toString());
                    register(params);

                }


            }
        });
      return view;
    }

    private void registerUsingFirebase(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String userId=FirebaseAuth.getInstance().getUid();
                                databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                                HashMap<String,String> hashMap=new HashMap<>();
                                hashMap.put("id",userId);
                                hashMap.put("email",eemail);
                                hashMap.put("username",eeusername);
                                databaseReference.setValue(hashMap);
                                Intent i= new Intent(getContext(),LoginActivity.class);
                                startActivity(i);
                               Log.e("Sucess","1");
                            }
                            else{
                                Log.e("eroor",task.getException().toString());
                                Toast.makeText(
                                        getContext(),
                                        "Registration failed!!"
                                                + " FIREBASE",
                                        Toast.LENGTH_LONG)
                                        .show();

                            }
                    }
                });
    }


    private void register(HashMap<String, String> params) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<RegistrationResponseModel> registerCall = networkService.register(params);
        registerCall.enqueue(new Callback<RegistrationResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<RegistrationResponseModel> call, @NonNull Response<RegistrationResponseModel> response) {
                RegistrationResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        Toast.makeText(getContext(), responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                       Intent i= new Intent(getContext(),LoginActivity.class);
                       startActivity(i);
                    } else {
                        Toast.makeText(getContext(), responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<RegistrationResponseModel> call, @NonNull Throwable t) {
                progressDialog.dismiss();
            }
        });
    }




}