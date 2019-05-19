package com.example.wmc;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    // BUTTON
    private Button btnDaftar;
    // EDIT TEXT
    private EditText inputUserkode, inputEmail;
    // INPUT NAME DI PHP
    String user_kode        = "user_kode";
    String email            = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        btnDaftar = findViewById(R.id.btnDaftar);
        inputUserkode = findViewById(R.id.inputUserkode);
        inputEmail = findViewById(R.id.inputEmail);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRelawan();
            }
        });

        if (ContextCompat.checkSelfPermission(Register.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 5);
            }
        }
    }

    private void addRelawan(){
        final String GetKode    = inputUserkode.getText().toString().trim();
        final String GetEmail   = inputEmail.getText().toString().trim();

        class AddRelawan extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Register.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(user_kode, GetKode);
                params.put(email, GetEmail);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.ADD_RELAWAN, params);
                return res;
            }
        }

        AddRelawan tambahRelawan = new AddRelawan();
        tambahRelawan.execute();
    }
}
