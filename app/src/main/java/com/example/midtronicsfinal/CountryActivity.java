package com.example.midtronicsfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Activity to be triggered upon app start up.
 * Displays Andre Zeromski's profile.
 */
public class CountryActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        final String title = "Andre Zeromski - Midtronics Project";
        setTitle(title);

        // Create activity elements and set up listeners
        Button buttonCountryList = findViewById(R.id.buttonCountryList);
        buttonCountryList.setOnClickListener(this);

        String countryName;
        countryName = getIntent().getStringExtra("Country");
        TextView name = findViewById(R.id.name);
        name.setText(countryName);

        // Variables for HTTP GET request
        String countryRequestUrl = "https://restcountries.com/v3.1/name/" + countryName +
                "?fields=name,capital,population,area,region,subregion";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, countryRequestUrl,
                new Response.Listener<String>() {
                    String capital = "N/A";
                    String population = "N/A";
                    String area = "N/A";
                    String region = "N/A";
                    String subregion = "N/A";
            /**
             * onResponse event
             * Put relevant API information into TextView upon a successful response from the
             * string request
             */
            @Override
            public void onResponse(String response) {
                Log.d("response", response);

                try {
                    // Get JSON response
                    JSONArray jsonResponse = new JSONArray(response);

                    // JSON response at idx 0 not always correct; possible solution to sort based on
                    // population

                    JSONObject json = jsonResponse.getJSONObject(0);

                    // Fetch relevant information from JSON
                    if(json.has("capital")) {
                        capital = json.getString("capital");
                        capital = capital.replace("[", "").replace("\"",
                                "").replace("]", "");
                    }
                    if(json.has("population")) {
                        population = json.getString("population");
                    }
                    if(json.has("area")) {
                        area = json.getString("area");
                    }
                    if(json.has("capital")) {
                        region = json.getString("region");
                    }
                    if(json.has("subregion")) {
                        subregion = json.getString("subregion");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            // Concatenate and store the fetched information
            String finalValues = capital + "\n" + population + "\n" + area + "\n" + region + "\n"
                    + subregion;
            TextView result = findViewById(R.id.values);
            result.setText(finalValues);
            }

        }, new Response.ErrorListener(){

            /**
             * onErrorResponse event
             * Output a toast message describing the error upon an unsuccessful response from the
             * string request
             */
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Add stringRequest to request queue for Volley to execute
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    /**
     * Method for onClick event, finishes current activity and takes user to back to country list.
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonCountryList:
                finish();
                break;
        }
    }
}

