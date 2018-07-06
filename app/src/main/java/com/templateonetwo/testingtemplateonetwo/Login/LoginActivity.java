package com.templateonetwo.testingtemplateonetwo.Login;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.templateonetwo.testingtemplateonetwo.MainActivity;
import com.templateonetwo.testingtemplateonetwo.Models.UserModel;
import com.templateonetwo.testingtemplateonetwo.R;
import com.templateonetwo.testingtemplateonetwo.Utils.SessionManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    protected FirebaseStorage storage;
    //change the url according to your firebase app
    protected StorageReference storageRef;
    // ...creating refreence to firebase database
    protected DatabaseReference mDatabase;
    protected FirebaseUser firebaseUser;
    public FirebaseAuth firebaseAuth;
    SessionManager mSessionManager;
    private static final String TAG = "Login Activity";
    public EditText mEmailLogin;
    public EditText mPassworkLogin;
    public Button mLoginButton;
    public TextView mRegsiterRedirect;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_customer);
        Log.d(TAG, "onCreate: Started.");
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        mEmailLogin = (EditText) findViewById(R.id.input_email_login);
        mPassworkLogin = (EditText) findViewById(R.id.input_password_login);
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mRegsiterRedirect = (TextView) findViewById(R.id.link_signup_to_register);
        mLoginButton.setOnClickListener(this);
        mRegsiterRedirect.setOnClickListener(this);




        //Databse Intialization...
        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mSessionManager=SessionManager.NewInstance(this);
        if(mSessionManager.isLogin() && firebaseUser!=null)
        {
            UserModel userModel=mSessionManager.getProfileData(this);
            if(userModel.getUserType().equalsIgnoreCase("customer")) {
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




    }

    private void signIn(String email, String pass) {
        firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in firebaseUser's information
                    Log.d("TAG", "signInWithEmail:success");
                    firebaseUser = firebaseAuth.getCurrentUser();

                    if (firebaseUser != null) {
                        if (firebaseUser.isEmailVerified()) {
                            mDatabase.child("Users").orderByChild("email").equalTo(firebaseUser.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot dataChild:dataSnapshot.getChildren()) {
                                        UserModel userModel = dataChild.getValue(UserModel.class);
                                                /*if (user.getApnsId()==null || user.getApnsId().equals("")) {

                                                }*/
                                       mSessionManager.saveProfileData(LoginActivity.this,userModel);
                                        mSessionManager.setLogin(true);
                                        Toast.makeText(LoginActivity.this, "Welcome " + userModel.getUserName(), Toast.LENGTH_SHORT).show();
                                        Intent mIntent = new Intent(LoginActivity.this, MainActivity.class);
                                        mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(mIntent);
                                        finish();
                                    }


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        } else {

                            dialogToNotVerified();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // If sign in fails, display a message to the firebaseUser.
                    Log.w("TAG", "signInWithEmail:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_login:
                if(mEmailLogin.getText().toString().isEmpty() || mPassworkLogin.getText().toString().isEmpty())
                {
                    Toast.makeText(this, "Please fill in the mandatory fields to login", Toast.LENGTH_SHORT).show();
                    return;
                }
                signIn(mEmailLogin.getText().toString(),mPassworkLogin.getText().toString());
                break;
            case R.id.link_signup_to_register:
                Intent intent=new Intent(this,UserTypeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void dialogToNotVerified() {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_verify_email, null);
        builder.setView(dialogView);

        final Button btn_cancel = dialogView.findViewById(R.id.btn_cancel);
        final Button btn_send_link = dialogView.findViewById(R.id.btn_send_link);

        builder.setCancelable(false);
        final android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_send_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        alertDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Verification Email is sent to your email", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Fails to send verification Email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        if (alertDialog != null && !alertDialog.isShowing()) {
            alertDialog.show();
        }
    }


}
