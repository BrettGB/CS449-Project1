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
import android.widget.Toast;
import com.example.cs449_project.ScaleCreationAlgorithm;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static java.security.AccessController.getContext;

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
    String[] musicNotes = {"B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B",
            "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B", "C"};

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

        //
        //Scale Selector Spinner
        //
        scaleSpinner = findViewById(R.id.scaleSelection);

        //these next couple lines initialize the spinner with the data in
        //R.array.scales_array, each option in the spinner is a string/charSequence from that array
        //the spinner then gets set with the setAdapter method
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.scales_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scaleSpinner.setAdapter(adapter);

        //when selecting an item in the spinner,
        //if the first item is selected (...) then no message will appear
        //but if any other item is selected, the message: "Selected: " (your item) will appear
        //the first entry in the spinner is the default/unpickable option
        scaleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    scale = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Scale Selected: " + scale, Toast.LENGTH_LONG).show();
                    scaleSelected = true;

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
        //Key Selector Spinner
        //
        keySpinner = findViewById(R.id.keySelection);

        //these next couple lines initialize the spinner with the data in
        //R.array.scales_array, each option in the spinner is a string/charSequence from that array
        //the spinner then gets set with the setAdapter method
        ArrayAdapter<CharSequence> adapter1;
        adapter1 = ArrayAdapter.createFromResource(this, R.array.keys_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        keySpinner.setAdapter(adapter1);

        //when selecting an item in the spinner,
        //if the first item is selected (...) then no message will appear
        //but if any other item is selected, the message: "Selected: " (your item) will appear
        //the first entry in the spinner is the default/unpickable option
        keySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    key = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Key Selected: " + key, Toast.LENGTH_LONG).show();
                    keySelected = true;

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
        //Submit Button
        //
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (scaleSelected && keySelected){
                //Toast.makeText(getApplicationContext(), "Submit now Available", Toast.LENGTH_LONG).show();
                 //}

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

                theScale.setText(temp.toString());

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


    }
}
