package com.example.midtronicsfinal;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Activity to be triggered upon intent from ZeromskiProfileActivity.
 * Displays a list of countries.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String title = "Andre Zeromski - Midtronics Project";
        setTitle(title);

        // Create activity elements and set up listeners
        Button profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(this);

        ListView countryList;
        final String[] countries = getResources().getStringArray(R.array.countries_array);
        countryList = findViewById(R.id.list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
        countryList.setAdapter(arrayAdapter);
        countryList.setOnItemClickListener(this);

    }
    /**
     * Method for onItemClick events, puts item name to intent for next activity's GET request
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent;
        String name = adapterView.getItemAtPosition(i).toString();
        intent = new Intent(MainActivity.this, CountryActivity.class);
        intent.putExtra("Country", name);
        startActivity(intent);
    }

    /**
     * Method for onClick event, finishes current activity and takes user to back to Andre
     * Zeromski's profile.
     */
    @Override
    public void onClick (View view){
        switch (view.getId()) {
            case R.id.profileButton:
                finish();
                break;
        }
    }
}

