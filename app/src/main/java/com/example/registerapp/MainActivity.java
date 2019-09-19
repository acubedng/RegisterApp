package com.example.registerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText signinEmail, signinPassword;
    TextView regText;
    SharedPreferences sharedPreferences;
    Intent intent;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signinEmail = findViewById(R.id.signinEmail);
        signinPassword = findViewById(R.id.signinPassword);
        signIn = findViewById(R.id.signInBtn);
        regText = findViewById(R.id.regText);

        regText.setOnClickListener(this);
        signIn.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(MainActivity.this, DashboardActivity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regText:
                startActivity(new Intent(this, RegisterActivity.class));
                break;

            case R.id.signInBtn:
                userSignin();
        }


    }

    private void userSignin() {
        sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        String email = signinEmail.getText().toString().trim();
        String password = signinPassword.getText().toString().trim();

        if (email.isEmpty()) {
            signinEmail.setError("Email is required");
            signinEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signinEmail.setError("Please enter a valid email");
            signinEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            signinPassword.setError("Email is required");
            signinPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            signinPassword.setError("Minimum length of password should be 6");
            signinPassword.requestFocus();
            return;
        }

        String userDetails = sharedPreferences.getString(email + password + "data", "Email or Password is incorrect");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userDetails", userDetails);
        editor.commit();


        if (sharedPreferences.contains(email + password + "data")) {
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Credentials are not valid", Toast.LENGTH_SHORT).show();
        }
    }

}