package com.example.project1;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import androidx.appcompat.app.AppCompatActivity;

public class CandidateCertificates extends AppCompatActivity {
    ImageView back_btn_cert;
    Button btn_next, button_resume, button_certificate, button_attach;
    String firstname, lastname, email, phone, city, address, country, zipcode, company_id;

    private int PICK_PDF_REQUEST = 1;
    private int PICK_PDF_CERTIFICATE = 2;
    private int PICK_PDF_ATTACH = 3;
    String encodedResume, encodedCertificate, encodedAttach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_certificates);

        firstname = getIntent().getExtras().getString("first_name");
        lastname = getIntent().getExtras().getString("last_name");
        email = getIntent().getExtras().getString("email");
        phone = getIntent().getExtras().getString("phone_number");
        address = getIntent().getExtras().getString("address");
        city = getIntent().getExtras().getString("city");
        country = getIntent().getExtras().getString("country");
        zipcode = getIntent().getExtras().getString("zipcode");
        company_id = getIntent().getExtras().getString("company_id");

        back_btn_cert = findViewById(R.id.back_arrow_certificate);
        btn_next = findViewById(R.id.cert_next);
        button_resume = findViewById(R.id.button_resume);
        button_certificate = findViewById(R.id.button_certificate);

        button_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFile();
            }
        });

        button_certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFile2();
            }
        });




        // setting listener for next
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CandidateEducation.class);
                Bundle bundle = new Bundle();
                bundle.putString("first_name", firstname);
                bundle.putString("last_name", lastname);
                bundle.putString("email",email);
                bundle.putString("phone_number", phone);
                bundle.putString("address", address);
                bundle.putString("city", city);
                bundle.putString("country", country);
                bundle.putString("zipcode", zipcode);
                bundle.putString("company_id", company_id);
                bundle.putString("resume", encodedResume);
                bundle.putString("certificates", encodedCertificate);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //setting listener for back button
        ImageView back_btn_review= findViewById(R.id.back_arrow_review);
        back_btn_cert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(getApplicationContext(), CandidateGenInfo.class);
               // startActivity(intent);
                finish();
            }
        });
    }

    private void selectFile() {
        Dexter.withActivity(CandidateCertificates.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        //providing implict intent
                        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                        intent.setType("*/*");
                        startActivityForResult(Intent.createChooser(intent,"select a file"),PICK_PDF_REQUEST);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void selectFile2() {
        Dexter.withActivity(CandidateCertificates.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        //providing implict intent
                        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                        intent.setType("*/*");
                        startActivityForResult(Intent.createChooser(intent,"select a file"),PICK_PDF_CERTIFICATE);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK)

        {
            //code for setting uri in toast
            String filepath = data.getData().getPath();
            Toast.makeText(this, ""+filepath,
                    Toast.LENGTH_LONG).show();

            //code for setting filename in textview
            Uri uri= data.getData();
            File file= new File(uri.getPath());
            String filename=  file.getName();

            try {
                InputStream stream = this.getContentResolver()
                        .openInputStream(uri);
                byte [] fileByte = new byte[stream.available()];
                stream.read(fileByte);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    encodedResume = Base64.getEncoder().encodeToString(fileByte);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        else if(requestCode == PICK_PDF_CERTIFICATE && resultCode == RESULT_OK)

        {
            //code for setting uri in toast
            String filepath = data.getData().getPath();
            Toast.makeText(this, ""+filepath,
                    Toast.LENGTH_LONG).show();

            //code for setting filename in textview
            Uri uri= data.getData();
            File file= new File(uri.getPath());
            String filename=  file.getName();

            try {
                InputStream stream = this.getContentResolver()
                        .openInputStream(uri);
                byte [] fileByte = new byte[stream.available()];
                stream.read(fileByte);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    encodedCertificate = Base64.getEncoder().encodeToString(fileByte);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
}