package com.example.project1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String mail;
    String pass;
    private FirebaseAuth mAuth;
    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        mAuth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        EditText loginEmail =view.findViewById(R.id.Login_email);
        mail = loginEmail.getText().toString().trim();
        EditText loginPass = view.findViewById(R.id.login_password);
        pass = loginPass.getText().toString().trim();
        Button login = view.findViewById(R.id.button36);
        CheckBox rememberMe = view.findViewById(R.id.remember_me);

        //creating shared preference object
        SharedPreferences preferences = getActivity().getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if(checkbox.equals("true"))
        {//calling login method
            login();
        }else if (checkbox.equals("false"))
        {
            Toast.makeText(getContext(), "Please Login", Toast.LENGTH_SHORT).show();
        }

        //listener for remember me
        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    //creating shared preferences object
                    SharedPreferences preferences = getActivity().getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor =preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();


                }else if(!buttonView.isChecked()){

                    SharedPreferences preferences = getActivity().getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor =preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();

                }
            }
        });

        //listener for login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginEmail.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please Enter Email", Toast.LENGTH_SHORT).show();
                } /*else if (!emailValidator(inputEmail.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please Field Valid Email", Toast.LENGTH_SHORT).show();
                }*/ else if (loginPass.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    loginusingFirebase(loginEmail.getText().toString(),loginPass.getText().toString());
                    login();
                }

            }
        });
        return view;
    }

    private void loginusingFirebase(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
//                            Toast.makeText(getContext(),
//                                    "Login successful!! FIREBASE",
//                                    Toast.LENGTH_LONG)
//                                    .show();

                        }
                        else{
                            Toast.makeText(getContext(),
                                    "Login failed!! FIREBASE",
                                    Toast.LENGTH_LONG)
                                    .show();

                        }
                    }
                });
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    private void login() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<LoginResponseModel> login = networkService.login(mail,pass);
        login.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                LoginResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {
                        SharedPreferences preferences = getActivity().getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(Constants.KEY_ISE_LOGGED_IN, true);
                        editor.putString(Constants.KEY_USERNAME, responseBody.getUserDetailObject().getUserDetails().get(0).getFirstName() + " " + responseBody.getUserDetailObject().getUserDetails().get(0).getLastName());
                        /*editor.putString(Constants.KEY_LASTNAME, responseBody.getUserDetailObject().getUserDetails().get(0).getLastName());*/
                        editor.putString(Constants.KEY_EMAIL, responseBody.getUserDetailObject().getUserDetails().get(0).getEmail());
                        editor.apply();
                        Log.e("Success","1 ");
                        Toast.makeText(getContext(), responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getContext(),DashBoardActivity.class);
                        startActivity(i);

                    } else {
                        Log.e("Success","1 ");
                        Toast.makeText(getContext(), responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponseModel> call, @NonNull Throwable t) {
                Log.e("get message",t.getMessage().toString());
                progressDialog.dismiss();
            }
        });
    }

    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
