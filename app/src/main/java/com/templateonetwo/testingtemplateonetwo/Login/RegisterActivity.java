package com.templateonetwo.testingtemplateonetwo.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.templateonetwo.testingtemplateonetwo.MainActivity;
import com.templateonetwo.testingtemplateonetwo.Models.UserModel;
import com.templateonetwo.testingtemplateonetwo.R;
import com.templateonetwo.testingtemplateonetwo.Utils.SessionManager;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Register Activity";
    protected DatabaseReference mDatabase;
    protected FirebaseUser firebaseUser;
    public FirebaseAuth firebaseAuth;
    SessionManager mSessionManager;

    public EditText mInputEmail;
    public EditText mInputFullname;
    public EditText mInputDisplayname;
    public EditText mInputPassword;
    public AppCompatButton registerButton;
    String userType;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_customer);
        Log.d(TAG, "onCreate: Started.");
        firebaseAuth = FirebaseAuth.getInstance();
        userType=getIntent().getStringExtra("userType");




        //Databse Intialization...
        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mSessionManager = SessionManager.NewInstance(this);

        mInputEmail = (EditText) findViewById(R.id.input_email);
        mInputFullname = (EditText) findViewById(R.id.input_fullname);
        mInputDisplayname = (EditText) findViewById(R.id.input_displayname);
        mInputPassword = (EditText) findViewById(R.id.input_password);
        registerButton=(AppCompatButton) findViewById(R.id.btn_register);
        registerButton.setOnClickListener(this);
    }

    private void signUpUser(final String email, final String password)
    {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseUser = firebaseAuth.getCurrentUser();
                    Toast.makeText(RegisterActivity.this, "Authentication Success." + firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
                    firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("TAG", "Email sent.");
                                Toast.makeText(RegisterActivity.this, "Verification Email is sent to your email", Toast.LENGTH_SHORT).show();
                                storeUserData(firebaseUser.getUid());
                            } else {
                                Toast.makeText(RegisterActivity.this, "Fails to send verification Email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    // If sign in fails, display a message to the firebaseUser.
                    Log.w("TAG", "createUserWithEmail:failure", task.getException());
                    if (task.getException()!= null) {
                        Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void storeUserData(String userId) {
        HashMap<String,Object> data = new HashMap<>();
        data.put("email",firebaseUser.getEmail());
        data.put("userName",mInputDisplayname.getText().toString());
        data.put("password",mInputPassword.getText().toString());
        data.put("fullName",mInputFullname.getText().toString());
        data.put("userType",userType);
        data.put("userKey",userId);
        mDatabase.child("Users").child(userId).setValue(data);
        UserModel userModel = new UserModel();
        userModel.setEmail(firebaseUser.getEmail());
        userModel.setUserKey(firebaseUser.getUid());
        userModel.setUserName(mInputFullname.getText().toString());
        userModel.setPassword(mInputPassword.getText().toString());
        userModel.setUserType(userType);
        mSessionManager.saveProfileData(this,userModel);
        if(userType.equalsIgnoreCase("customer")) {
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
        else
        {
            // we have to change the class later for service provider to take that user to the correct activity
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_register:
                if(mInputEmail.getText().toString().isEmpty() || mInputFullname.getText().toString().isEmpty()
                        || mInputPassword.getText().toString().isEmpty() || mInputDisplayname.getText().toString().isEmpty())
                {
                    Toast.makeText(this, "Please fill in the mandatory fields to register", Toast.LENGTH_SHORT).show();
                    return;
                }

                signUpUser(mInputEmail.getText().toString(),mInputPassword.getText().toString());
        }
    }
}
