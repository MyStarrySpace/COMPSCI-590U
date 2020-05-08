package com.example.currentplacedetailsonmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * This Activity is a screen that lists all locations visited.
 */
public class MyInteractionsActivity extends Activity {

    /**
     * Tag for Log
     */
    private static final String TAG = "MyInteractionsActivity";

    // Import Globals
    Globals myInteractions;

    ArrayAdapter<String> pairedDevicesArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve globals
        myInteractions = Globals.getInstance();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        // Setup the window
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.my_interactions);

        // Set result CANCELED in case the user backs out
        setResult(Activity.RESULT_CANCELED);

        // Test set
//        Pair<String, String> p = Pair.create("Subway", "Fri May 1, 2020");
//        Pair<String, String> q = Pair.create("Dunkin' Donuts", "Thu Apr 30, 2020");
//        final ArrayList<Pair<String, String>> locationSets = new ArrayList<Pair<String, String>>();
//        locationSets.add(p);
//        locationSets.add(q);

        // Import location sets based on globals
        ArrayList<Pair<String, String>> interaction_sets = myInteractions.getMyInteractions();

        // Initialize array adapters. One for already paired devices and
        // one for newly discovered devices
        pairedDevicesArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_name);

        // Initialize buttons
        Button resetButton = (Button) findViewById(R.id.button_explanation);
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myInteractions.clearInteractions();
                pairedDevicesArrayAdapter.clear();
                pairedDevicesArrayAdapter.add("No locations visited");
            }
        });


        // Find and set up the ListView for locations
        ListView locationsListView = (ListView) findViewById(R.id.interactions);
        locationsListView.setAdapter(pairedDevicesArrayAdapter);


        // If there are any interactions, add each one to the ArrayAdapter
        if (interaction_sets != null && interaction_sets.size() > 0) {
            findViewById(R.id.title_interaction).setVisibility(View.VISIBLE);
            for (Pair<String, String> intSet : interaction_sets) {
                pairedDevicesArrayAdapter.add("Interacted with: " + intSet.first + "\n" + "At: " + intSet.second);
            }
        } else {
            pairedDevicesArrayAdapter.add("No interactions");
        }

    }








}