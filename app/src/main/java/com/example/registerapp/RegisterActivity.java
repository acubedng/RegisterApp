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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView signinText;
    EditText regFullname,regUsername, regEmail, regPassword;
    Button regBtn;
    SharedPreferences sharedPref;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signinText = findViewById(R.id.signinText);
        regBtn = findViewById(R.id.regBtn);
        regFullname = findViewById(R.id.regFullname);
        regUsername = findViewById(R.id.regUsername);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);

        sharedPref = getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(RegisterActivity.this, MainActivity.class);

        signinText.setOnClickListener(this);
        regBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signinText:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.regBtn:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String fullname = regFullname.getText().toString().trim();
        String username = regUsername.getText().toString().trim();
        String email = regEmail.getText().toString().trim();
        String password = regPassword.getText().toString().trim();

        if (fullname.isEmpty()) {
            regFullname.setError("Email is required");
            regFullname.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            regEmail.setError("Email is required");
            regEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            regEmail.setError("Please enter a valid email");
            regEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            regPassword.setError("Email is required");
            regPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            regPassword.setError("Minimum length of password should be 6");
            regPassword.requestFocus();
            return;
        }

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(email + password + "data", "Welcome, " + username + "\n" +
                "Your Details are as follows: " + "\n" + "Fullname: " + fullname + "\n" + "Username: " +
                 username + "\n" + "Email: " + email);
        editor.commit();
        Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }

}