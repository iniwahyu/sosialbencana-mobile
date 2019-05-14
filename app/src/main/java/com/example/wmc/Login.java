package com.example.wmc;

import android.Manifest;
import android.app.AlertDialog;
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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wmc.fragment.HomeFragment;

public class Login extends AppCompatActivity implements LocationListener {

    private EditText txtUserkode, txtPassword;
    private Button btnLogin, btnDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        if (mLocationManager == null)
        {
            mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        }

        if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            showGPSDiabledDialog();
        }
        
        txtUserkode = (EditText)findViewById(R.id.txtUserkode);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        btnLogin    = (Button)findViewById(R.id.btnLogin);
        btnDaftar   = (Button)findViewById(R.id.btnDaftar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userkode = txtUserkode.getText().toString();
                String password = txtPassword.getText().toString();

                if(userkode.trim().length()==0 || password.trim().length()==0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage("Kode Relawan atau Password Tidak Boleh Kosong!")
                            .setNegativeButton("Retry", null).create().show();
                }
                else{
                    if(userkode.equals("admin") && password.equals("123")){
                        Toast.makeText(getApplicationContext(), "Login Berhasil", Toast.LENGTH_LONG).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("Login", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("User", userkode);
                        editor.commit();
                        editor.apply();
                        Intent intent = new Intent(Login.this, BottomMenu.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                        builder.setMessage("Kode Relawan atau Password Salah!")
                                .setNegativeButton("Retry", null).create().show();
                    }
                }
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

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        if (provider.equals(LocationManager.GPS_PROVIDER))
        {
            showGPSDiabledDialog();
        }
    }

    public void showGPSDiabledDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("GPS Disabled");
        builder.setMessage("Gps is disabled, in order to use the application properly you need to enable GPS of your device");
        builder.setPositiveButton("Enable GPS", new DialogInterface.OnClickListener() {
            private int GPS_ENABLE_REQUEST;

            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), GPS_ENABLE_REQUEST);
            }
        }).setNegativeButton("No, Just Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog mGPSDialog = builder.create();
        mGPSDialog.show();
    }
}
