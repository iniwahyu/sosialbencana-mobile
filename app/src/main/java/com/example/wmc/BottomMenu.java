package com.example.wmc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.wmc.fragment.HomeFragment;
import com.example.wmc.fragment.ProfilFragment;
import com.example.wmc.fragment.UploadFragment;

public class BottomMenu extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.containerFragment, fragment, fragment.getClass().getSimpleName()).commit();
                    return true;
                case R.id.navigation_upload:
                    fragment = new UploadFragment();
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.containerFragment, fragment, fragment.getClass().getSimpleName()).commit();
                    return true;
                case R.id.navigation_dashboard:
                    fragment = new ProfilFragment();
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.containerFragment, fragment, fragment.getClass().getSimpleName()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(savedInstanceState == null){
            navigation.setSelectedItemId(R.id.navigation_home);
        }
    }

}
