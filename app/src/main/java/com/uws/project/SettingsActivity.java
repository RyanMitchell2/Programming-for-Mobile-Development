package com.uws.project;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    Spinner styleSpinner, colourSpinner, sizeSpinner, speedSpinner, backgroundSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Settings");

        addItemsOnSpinners();
    }

    // add items into spinner dynamically
    public void addItemsOnSpinners() {

        styleSpinner = findViewById(R.id.styleSpinner);
        colourSpinner = findViewById(R.id.colourSpinner);
        sizeSpinner = findViewById(R.id.sizeSpinner);
        speedSpinner = findViewById(R.id.speedSpinner);
        backgroundSpinner = findViewById(R.id.backgroundSpinner);

        List<String> styleList = new ArrayList<>();
        styleList.add("Light");
        styleList.add("Regular");
        styleList.add("Semi-bold");
        styleList.add("Bold");
        styleList.add("Black");
        ArrayAdapter<String> styleAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, styleList);
        styleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        styleSpinner.setAdapter(styleAdapter);

        List<String> colourList = new ArrayList<>();
        colourList.add("White");
        colourList.add("Red");
        colourList.add("Blue");
        colourList.add("Green");
        colourList.add("Yellow");
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
        backgroundList.add("Blue");
        backgroundList.add("Red");
        backgroundList.add("Yellow");
        ArrayAdapter<String> backgroundAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, backgroundList);
        backgroundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backgroundSpinner.setAdapter(backgroundAdapter);


    }

}
