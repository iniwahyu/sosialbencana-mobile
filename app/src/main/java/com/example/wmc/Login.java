package com.example.wmc;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText txtUserkode, txtPassword;
    private Button btnLogin, btnDaftar;

    private String URL_DATA = Konfigurasi.LOGIN;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        txtUserkode = findViewById(R.id.txtUserkode);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin    = findViewById(R.id.btnLogin);
        btnDaftar   = findViewById(R.id.btnDaftar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent daftar = new Intent(Login.this, Register.class);
                startActivity(daftar);
                finish();
            }
        });
    }

    private void login(){
        final String userkode = txtUserkode.getText().toString().trim();
        final String password = txtPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("Success")) {
                    Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_LONG).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("User", userkode);
                    editor.putString("Sesi", "Login");
                    editor.commit();
                    editor.apply();
                    Intent intent = new Intent(Login.this, BottomMenu.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Kode Relawan/Password Salah", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("kode", userkode);
                params.put("password", password);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
