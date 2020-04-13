package com.example.cs449_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Spinner scaleSpinner;
    Spinner keySpinner;
    Spinner optionsSpinner;
    Button submitButton;
    Button homeButton;
    TextView theScale;
    String scale;
    String key;
    String[] finishedScale;
    boolean scaleSelected = false;
    boolean keySelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.submitButton);
        submitButton.setVisibility(View.INVISIBLE);
        homeButton = findViewById(R.id.homeButton);
        theScale = findViewById(R.id.theProduct);
        theScale.setVisibility(View.INVISIBLE);
        optionsSpinner = findViewById(R.id.optionsSpinner);
        scaleSpinner = findViewById(R.id.scaleSelection);
        keySpinner = findViewById(R.id.keySelection);

        //
        //SCALE SELECTOR SPINNER
        //initializes the scale selector spinner with that available scale options
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.scales_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scaleSpinner.setAdapter(adapter);

        //the first option is "..."
        //in order for the submit button to appear, an option other than "..." needs to be chosen
        scaleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    scaleSelected = true;
                    scale = parent.getItemAtPosition(position).toString();

                    if(keySelected)
                        submitButton.setVisibility(View.VISIBLE);
                    else
                        submitButton.setVisibility(View.INVISIBLE);
                }
                else {
                    scaleSelected = false;
                    submitButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //
        //KEY SELECTOR SPINNER
        //initializes the key selector spinner with the available key options
        ArrayAdapter<CharSequence> adapter1;
        adapter1 = ArrayAdapter.createFromResource(this, R.array.keys_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        keySpinner.setAdapter(adapter1);

        //the first option is "..."
        //in order for the submit button to appear, an option other than "..." needs to be chosen
        keySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    keySelected = true;
                    key = parent.getItemAtPosition(position).toString();

                    if(scaleSelected)
                        submitButton.setVisibility(View.VISIBLE);
                    else
                        submitButton.setVisibility(View.INVISIBLE);
                }
                else {
                    keySelected = false;
                    submitButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //
        //SUBMIT BUTTON
        //need to have chosen both a scale and a key for the button to appear
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleCreationAlgorithm theCreation;
                theCreation = new ScaleCreationAlgorithm();
                finishedScale = theCreation.scaleCreator(scale, key);

                StringBuilder temp = new StringBuilder();
                int size = finishedScale.length;
                boolean appended = false;

                for(int i = 0; i < size; ++i){
                    if(appended && (i < 7)) {
                        temp.append(' ');
                        temp.append(' ');
                    }
                    appended = true;
                    temp.append(finishedScale[i]);
                }

                //theScale.setText(temp.toString());
                theScale.setText(String.valueOf(temp));

                theScale.setVisibility(View.VISIBLE);
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

        //
        //OPTIONS SPINNER
        //lets you go to the home page of the info page
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


    }
}
