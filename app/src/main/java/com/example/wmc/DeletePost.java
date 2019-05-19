package com.example.wmc;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class DeletePost extends AppCompatActivity {

    // BUTTON
    private Button btnDelete, btnKembali;
    // EDIT TEXT
    private EditText inputSlug;
    // INPUT NAME DI PHP
    String slug        = "slug_post";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_post);

        inputSlug = findViewById(R.id.postSlug);
        btnDelete = findViewById(R.id.btnDelete);
        btnKembali = findViewById(R.id.btnKembali);

        Intent dapat = getIntent();
        final String dataSlug = dapat.getExtras().getString("Slug");
        inputSlug.setText(dataSlug);
        inputSlug.setVisibility(View.GONE);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePost();
                AlertDialog.Builder builder = new AlertDialog.Builder(DeletePost.this);
                builder.setTitle("Sukses")
                        .setMessage("Berhasil Menghapus");
                AlertDialog alert = builder.create();
                alert.show();
                Intent sukses = new Intent(DeletePost.this, BottomMenu.class);
                startActivity(sukses);
                finish();
            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void deletePost(){
        final String GetSlug    = inputSlug.getText().toString().trim();
        final String URL_DATA = Konfigurasi.DELETE_POST+GetSlug;

        class HapusPost extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DeletePost.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(DeletePost.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(slug, GetSlug);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(URL_DATA, params);
                return res;
            }
        }

        HapusPost hapusPost = new HapusPost();
        hapusPost.execute();
    }
}
