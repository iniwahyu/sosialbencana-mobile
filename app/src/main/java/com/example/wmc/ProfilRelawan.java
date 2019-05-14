package com.example.wmc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ProfilRelawan extends AppCompatActivity {

    private EditText inputNama, inputEmail, inputTgllahir, inputPhone;
    private Button btnSimpan;
    private TextView txtUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_relawan);

        inputNama           = findViewById(R.id.inputNama);
        inputEmail          = findViewById(R.id.inputEmail);
        inputTgllahir       = findViewById(R.id.inputTgllahir);
        inputPhone          = findViewById(R.id.inputPhone);

        Spinner dropdown = findViewById(R.id.inputJeniskelamin);
        String[] items = new String[]{"Laki-Laki", "Perempuan"};
        ArrayAdapter<String> jeniskelamin = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(jeniskelamin);

        getData();

        btnSimpan           = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    public void getData(){
        RequestQueue queue = Volley.newRequestQueue(this);
        SharedPreferences profil = getSharedPreferences("Login", Context.MODE_PRIVATE);
        final String username = profil.getString("User", "");

        txtUser = findViewById(R.id.txtUser);
        txtUser.setText("Profil "+username);

        String URL_DATA     = Konfigurasi.EDIT_RELAWAN+username;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonPost = new JSONObject(response);
                    inputNama.setText(jsonPost.getString("nama").toString());
                    inputEmail.setText(jsonPost.getString("email").toString());
                    inputTgllahir.setText(jsonPost.getString("tgl_lahir").toString());
                    inputPhone.setText(jsonPost.getString("phone").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Response", error.toString());
            }
        });
        queue.add(stringRequest);
    }

    public void saveData(){
        String nama         = "nama";
        String email        = "email";
        String tgl_lahir    = "tgl_lahir";
        String phone        = "phone";

        String GetNama      = inputNama.getText().toString().trim();
        String GetEmail     = inputEmail.getText().toString().trim();

        class SaveData extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ProfilRelawan.this,"Melakukan Perubahan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ProfilRelawan.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
//                params.put(user_kode, GetKode);
//                params.put(email, GetEmail);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.ADD_RELAWAN, params);
                return res;
            }
        }
    }
}
