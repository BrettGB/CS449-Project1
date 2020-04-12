package com.example.cs449_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class InfoActivity extends AppCompatActivity {


    Spinner optionsSpinner;
    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        homeButton = findViewById(R.id.homeButton);
        optionsSpinner = findViewById(R.id.optionsSpinner);

        ArrayAdapter<CharSequence> optionsAdapter;
        optionsAdapter = ArrayAdapter.createFromResource(this, R.array.options_array, android.R.layout.simple_spinner_item);
        optionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        optionsSpinner.setAdapter(optionsAdapter);

        optionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).toString().equals("Home")){
                    Intent homeScreen = new Intent(getApplicationContext(), MainActivity.class);
                    homeScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(homeScreen);
                }

                else if (parent.getItemAtPosition(position).toString().equals("Info")){
                    Intent infoScreen = new Intent(getApplicationContext(), InfoActivity.class);
                    infoScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(infoScreen);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(getApplicationContext(), MainActivity.class);
                home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(home);
            }
        });

    }
}
