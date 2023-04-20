package com.example.midtronicsfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity to be triggered upon intent from MainActivity.
 * Displays Andre Zeromski's profile.
 */
public class ZeromskiProfileActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_zeromski);
        final String title = "Andre Zeromski - Midtronics Project";
        setTitle(title);

        // Create country search button and set up listener
        Button countryButton = findViewById(R.id.countryButton);
        countryButton.setOnClickListener(this);
    }

    /**
     * Method for onClick event, takes user to MainActivity
     */
    @Override
    public void onClick(View view) {
        Intent intent;
        // Switch in case of added buttons in future
        switch (view.getId()) {
            case R.id.countryButton:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}