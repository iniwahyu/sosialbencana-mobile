package com.example.wmc;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    GridLayout gridLayout;
    CardView cardBencana, cardAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        gridLayout = findViewById(R.id.mainGrid);
        cardBencana = findViewById(R.id.cardBencana);
        cardBencana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bencana = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(bencana);
            }
        });
        cardAccount = findViewById(R.id.cardAccount);
        cardAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent account = new Intent(getApplicationContext(), Login.class);
                startActivity(account);
            }
        });
    }

    // MENAHAN KEMBALI
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan Dua Kali Untuk Keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
