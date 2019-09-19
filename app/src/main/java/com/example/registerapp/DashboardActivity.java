package com.example.registerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    SharedPreferences preferences;
    Intent intent;
    TextView textViewDetails;
    Button signOutBtn;
    String details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        textViewDetails = findViewById(R.id.textViewDetails);
        signOutBtn = findViewById(R.id.signOut);
        preferences = getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(DashboardActivity.this, MainActivity.class);
        details = preferences.getString("userDetails", "");

        textViewDetails.setText(details);

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}