package com.uws.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SettingsActivity extends AppCompatActivity implements Serializable {

    EditText editBiography;
    Spinner styleSpinner, colourSpinner, sizeSpinner, speedSpinner, backgroundSpinner, pictureSpinner;
    ArrayList<String> settingsObject;
    Profile currentUser;

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Settings");

        styleSpinner = findViewById(R.id.styleSpinner);
        colourSpinner = findViewById(R.id.colourSpinner);
        sizeSpinner = findViewById(R.id.sizeSpinner);
        speedSpinner = findViewById(R.id.speedSpinner);
        backgroundSpinner = findViewById(R.id.backgroundSpinner);
        pictureSpinner = findViewById(R.id.pictureSpinner);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            settingsObject = extras.getStringArrayList("settings");
            currentUser = extras.getParcelable("user_details");
        }

        editBiography = findViewById(R.id.editBiography);
        if (currentUser.getBiography().equals("Go to settings to change your biography")) {
            editBiography.setText("");
        } else {
            editBiography.setText(currentUser.getBiography());
        }

        addItemsOnSpinners();

        final Button save_button = findViewById(R.id.saveButton);
        save_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveSettings();
            }
        });

        final Button default_button = findViewById(R.id.defaultButton);
        default_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                defaultSettings();
            }
        });

    }

    // add items into spinner dynamically
    public void addItemsOnSpinners() {

        List<String> styleList = new ArrayList<>();
        styleList.add("Light");
        styleList.add("Regular");
        styleList.add("Semi-bold");
        styleList.add("Bold");
        styleList.add("Black");
        styleList.add("Comic Sans");
        ArrayAdapter<String> styleAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, styleList);
        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        styleSpinner.setAdapter(styleAdapter);

        List<String> colourList = new ArrayList<>();
        colourList.add("White");
        colourList.add("Black");
        colourList.add("Grey");
        colourList.add("Red");
        colourList.add("Orange");
        colourList.add("Yellow");
        colourList.add("Green");
        colourList.add("Blue");
        colourList.add("Indigo");
        colourList.add("Violet");
        ArrayAdapter<String> colourAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, colourList);
        colourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colourSpinner.setAdapter(colourAdapter);

        List<String> sizeList = new ArrayList<>();
        sizeList.add("16sp");
        sizeList.add("18sp");
        sizeList.add("20sp");
        sizeList.add("22sp");
        sizeList.add("24sp");
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, sizeList);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(sizeAdapter);

        List<String> speedList = new ArrayList<>();
        speedList.add("0.5x");
        speedList.add("0.75x");
        speedList.add("1x");
        speedList.add("1.25x");
        speedList.add("1.5x");
        ArrayAdapter<String> speedAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, speedList);
        speedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speedSpinner.setAdapter(speedAdapter);

        List<String> backgroundList = new ArrayList<>();
        backgroundList.add("White");
        backgroundList.add("Black");
        backgroundList.add("Grey");
        backgroundList.add("Red");
        backgroundList.add("Orange");
        backgroundList.add("Yellow");
        backgroundList.add("Green");
        backgroundList.add("Blue");
        backgroundList.add("Indigo");
        backgroundList.add("Violet");
        ArrayAdapter<String> backgroundAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, backgroundList);
        backgroundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backgroundSpinner.setAdapter(backgroundAdapter);

        List<String> pictureList = new ArrayList<>();
        pictureList.add("Default");
        pictureList.add("Male 2D");
        pictureList.add("Female 2D");
        pictureList.add("Outdoors 1");
        pictureList.add("Outdoors 2");
        pictureList.add("Outdoors 3");
        ArrayAdapter<String> pictureAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, pictureList);
        pictureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pictureSpinner.setAdapter(pictureAdapter);

        setSpinnerDefaults();

    }

    // set spinner defaults
    public void setSpinnerDefaults() {
        int testPOS;

        testPOS = getSavedSetting(styleSpinner,settingsObject.get(0));
        styleSpinner.setSelection(testPOS);

        testPOS = getSavedSetting(colourSpinner,settingsObject.get(1));
        colourSpinner.setSelection(testPOS);

        testPOS = getSavedSetting(sizeSpinner,settingsObject.get(2));
        sizeSpinner.setSelection(testPOS);

        testPOS = getSavedSetting(speedSpinner,settingsObject.get(3));
        speedSpinner.setSelection(testPOS);

        testPOS = getSavedSetting(backgroundSpinner,settingsObject.get(4));
        backgroundSpinner.setSelection(testPOS);

        testPOS = getSavedSetting(pictureSpinner,settingsObject.get(5));
        pictureSpinner.setSelection(testPOS);
    }

    private int getSavedSetting(Spinner spinner, String setting){
        int index = 0;
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(setting)){
                index = i;
            }
        }
        return index;
    }

    private void saveSettings() {
        Object style_option = styleSpinner.getSelectedItem();
        Object colour_option = colourSpinner.getSelectedItem();
        Object size_option = sizeSpinner.getSelectedItem();
        Object speed_option = speedSpinner.getSelectedItem();
        Object background_option = backgroundSpinner.getSelectedItem();
        Object picture_option = pictureSpinner.getSelectedItem();

        settingsObject = new ArrayList<>();
        settingsObject.add((String) style_option);
        settingsObject.add((String) colour_option);
        settingsObject.add((String) size_option);
        settingsObject.add((String) speed_option);
        settingsObject.add((String) background_option);
        settingsObject.add((String) picture_option);

        currentUser.setBiography(editBiography.getText().toString());
        if (currentUser.getBiography().equals("")) {
            currentUser.setBiography("Go to settings to change your biography");
        }

        Intent intent = new Intent();
        intent.putExtra("settings", settingsObject);
        intent.putExtra("user_details", currentUser);
        setResult(2,intent);
        finish();
    }

    private void defaultSettings() {
        settingsObject = null;
        Intent intent = new Intent();
        intent.putExtra("settings", settingsObject);
        intent.putExtra("user_details", currentUser);
        setResult(2,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("settings", settingsObject);
        intent.putExtra("user_details", currentUser);
        setResult(2,intent);
        finish();
    }



}
