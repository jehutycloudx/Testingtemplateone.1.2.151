package com.templateonetwo.testingtemplateonetwo.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.templateonetwo.testingtemplateonetwo.R;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "Register Activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer);
        Log.d(TAG, "onCreate: Started.");

    }
}
