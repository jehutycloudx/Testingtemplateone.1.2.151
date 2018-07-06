package com.templateonetwo.testingtemplateonetwo.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.templateonetwo.testingtemplateonetwo.R;

public class UserTypeActivity extends AppCompatActivity implements View.OnClickListener{

    public Button mCustomerType;
    public Button mServiceProviderType;
    String userSelection="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_login_user_path_selection);

        mCustomerType = (Button) findViewById(R.id.button_to_customer_login);
        mServiceProviderType = (Button) findViewById(R.id.button_to_provider_login);
        mCustomerType.setOnClickListener(this);
        mServiceProviderType.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button_to_customer_login:
                userSelection="customer";
                break;
            case R.id.button_to_provider_login:
                userSelection="provider";
                break;
        }
        Intent intent=new Intent(this,RegisterActivity.class);
        intent.putExtra("userType",userSelection);
        startActivity(intent);
    }
}
