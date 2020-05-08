package com.example.currentplacedetailsonmap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This Activity is a screen that lists all locations visited.
 */
public class ProfileActivity extends Activity {

    /**
     * Tag for Log
     */
    private static final String TAG = "ProfileActivity";

    // Import Globals
    Globals g;

    RadioButton gender_male;
    RadioButton gender_female;

    RadioButton age_0_17, age_18_44, age_45_64, age_65_74, age_75;
    CheckBox cb_cardio, cb_diabetes, cb_resp, cb_hypertension, cb_cancer;

    String msg_gender, msg_age, msg_conditions;
    int gender_score, age_score, conditions_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve globals
        g = Globals.getInstance();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        // Setup the window
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.profile);

        // Set result CANCELED in case the user backs out
        setResult(Activity.RESULT_CANCELED);

        // Create objects for checkboxes
        gender_male = findViewById(R.id.gender_male);
        gender_female = findViewById(R.id.gender_female);

        age_0_17 = findViewById(R.id.age_0_17);
        age_18_44 = findViewById(R.id.age_18_44);
        age_45_64 = findViewById(R.id.age_45_64);
        age_65_74 = findViewById(R.id.age_65_74);
        age_75 = findViewById(R.id.age_75);

        cb_cardio = findViewById(R.id.cb_cardio);
        cb_diabetes = findViewById(R.id.cb_diabetes);
        cb_resp = findViewById(R.id.cb_resp);
        cb_hypertension = findViewById(R.id.cb_hypertension);
        cb_cancer = findViewById(R.id.cb_cancer);

        msg_conditions = "";
        msg_age = "";
        msg_gender = "";

        // Initialize buttons
        Button explButton = (Button) findViewById(R.id.button_explanation);

    }

        // This function is invoked when the button is pressed.
        public void Check(View v)
        {
            gender_score = 0;
            age_score = 0;
            conditions_score = 0;

            // Concatenation of the checked options in if

            // isChecked() is used to check whether
            // the CheckBox is in true state or not.

            if(gender_male.isChecked()){
                gender_score = 20;
                msg_gender = "Males account for 61.8% of Coronavirus deaths - 20 points\n\n";
            }
            if(gender_female.isChecked()){
                gender_score = 10;
                msg_gender = "Females account for 38.2% of Coronavirus deaths - 10 points\n\n";
            }


            if(age_0_17.isChecked()) {
                age_score = 0;
                msg_age = "0-17 year olds account for 0.04% of Coronavirus deaths - 0 points\n\n";
            }
            if(age_18_44.isChecked()){
                age_score = 0;
                msg_age = "18-44 year olds account for 4.5% of Coronavirus deaths - 5 points\n\n";
            }
            if(age_45_64.isChecked()){
                age_score = 0;
                msg_age = "45-64 year olds account for 23.1% of Coronavirus deaths - 20 points\n\n";
            }
            if(age_65_74.isChecked()){
                age_score = 0;
                msg_age = "65-74 year olds account for 24.6% of Coronavirus deaths - 25 points\n\n";
            }
            if(age_75.isChecked()){
                age_score = 0;
                msg_age = "75+ year olds account for 47.7% of Coronavirus deaths - 50 points\n\n";
            }

            msg_conditions = "";

            if(cb_cardio.isChecked()){
                conditions_score += 50;
                msg_conditions += "People with cardiovascular disease are at a 11.7x higher rate of death - 50 pts\n\n";
            }
            if(cb_diabetes.isChecked()){
                conditions_score += 40;
                msg_conditions += "People with diabetes are at a 8.1x higher rate of death - 40 pts\n\n";
            }
            if(cb_resp.isChecked()){
                conditions_score += 35;
                msg_conditions += "People with chronic respiratory disease are at a 7x higher rate of death - 35 pts\n\n";
            }
            if(cb_hypertension.isChecked()){
                conditions_score += 30;
                msg_conditions += "People with Hypertension are at a 6.6x higher rate of death - 30 pts\n\n";
            }
            if(cb_cancer.isChecked()){
                conditions_score += 30;
                msg_conditions += "People with cancer are at a 6.2x higher rate of death - 30 pts\n\n";
            }

            g.setCoronavirusScore(gender_score + age_score + conditions_score);

            // Toast.makeText(getApplicationContext(), "Score: " + (gender_score+age_score+conditions_score) + "\n\n" + msg_gender + msg_age + msg_gender, Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder1 = new AlertDialog.Builder(ProfileActivity.this);
            builder1.setTitle("Score: " + (gender_score + age_score + conditions_score));
            builder1.setMessage(msg_gender + msg_age + msg_conditions);
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            Intent intent=new Intent();
                            intent.putExtra("SCORE", (gender_score + age_score + conditions_score));
                            setResult(RESULT_OK,intent);
                            finish(); //finish activity
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

        }








}