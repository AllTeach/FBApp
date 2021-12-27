package com.example.fbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseComm firebaseComm;
    private EditText etEmail,etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        // check if user is signed in -
        // show a toast in such a case
        if(FirebaseComm.isUserSignedIn()) {
            String mail = FirebaseComm.authUserEmail();
            Toast.makeText(this, "Signed in as: " + mail,Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        firebaseComm = new FirebaseComm();
        etEmail = findViewById(R.id.usernameLog);
        etPassword = findViewById(R.id.passwordLog);
    }

    public void login(View view)
    {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        firebaseComm.loginUser(email,password);
    }

    public void register(View view)
    {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        firebaseComm.createUser(email,password);
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = com.google.firebase.auth.FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null)
        {
            String uid =firebaseUser.getUid();
            Toast.makeText(this," id = " + uid,Toast.LENGTH_SHORT).show();
        }
    }

    public void gotoPosts(View view)
    {

        if(!FirebaseComm.isUserSignedIn())
        {
            Toast.makeText(this," login first ",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this,PostActivity.class);
        startActivity(intent);

    }
}