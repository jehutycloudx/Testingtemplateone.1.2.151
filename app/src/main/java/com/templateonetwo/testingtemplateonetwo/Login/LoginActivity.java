package com.templateonetwo.testingtemplateonetwo.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.templateonetwo.testingtemplateonetwo.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Login Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_customer);
        Log.d(TAG, "onCreate: Started.");

    }
}
