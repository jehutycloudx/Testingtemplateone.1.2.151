package com.templateonetwo.testingtemplateonetwo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_activity_main_provider);

        Log.d(TAG, "onCreate: started.");

        BottomNavigationView bottomNavigationView2 = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar2);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView2);
    }
/*Need to create 'FragmentStatePagerAdapter' for the app, go to developer site if need be */

}
