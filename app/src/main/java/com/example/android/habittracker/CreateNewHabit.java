package com.example.android.habittracker;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.android.habittracker.data.Habit;
import com.example.android.habittracker.R;
import com.example.android.habittracker.MainActivity;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.R.string.no;

public class CreateNewHabit extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText categoryEditText;
    private Spinner spinner;
    private RadioGroup radioGroup;
    private RadioButton yesRadioButton;
    private TimePicker timePicker;
    private EditText numberOfDaysEditText;
    private static String TAG = CreateNewHabit.class.toString();
    private int numberOfDays;
    private static int DEFAULT_NUMBER_OF_DAYS = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_habit);
        toolbar = (Toolbar) findViewById(R.id.toolbar_create);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add New Habit");
        spinner = (Spinner) findViewById(R.id.categoriesSpinner);
        numberOfDaysEditText = (EditText) findViewById(R.id.habitDaysEditText);
        titleEditText = (EditText) findViewById(R.id.habitNameEditText);
        descriptionEditText = (EditText) findViewById(R.id.habitDescriptionEditText);
        timePicker = (TimePicker) findViewById(R.id.time_picker);
        radioGroup = (RadioGroup) findViewById(R.id.radio_set_notification);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.radio_with_notification) {
                    timePicker.setVisibility(View.VISIBLE);
                } else
                    timePicker.setVisibility(View.GONE);
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.M)
    public void addHabit(View view) throws JSONException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date todaysDate = new Date();
        String dateString = dateFormat.format(todaysDate);

        if (validateUserInput()) {
            Habit h;

            if (timePicker.getVisibility() == View.VISIBLE) {
                //Log.d(TAG, dateString);
                //System.out.println(Integer.parseInt(numberOfDaysEditText.getText().toString()));
                h = new Habit(titleEditText.getText().toString(),
                        descriptionEditText.getText().toString()
                        , spinner.getSelectedItem().toString(), numberOfDays
                        , 0, timePicker.getHour(), dateString);

            } else {
                //         Log.d(TAG,String.valueOf(Integer.parseInt(numberOfDaysEditText.getText().toString())));
                h = new Habit(titleEditText.getText().toString(),
                        descriptionEditText.getText().toString()
                        , spinner.getSelectedItem().toString(), numberOfDays
                        , 0, dateString);

            }
            MainActivity.habitsList.add(h);
            MainActivity.dbAdapter.addHabit(h);
            MainActivity.mAdapter.notifyDataSetChanged();
            finish();
        } else {
            Toast toast = new Toast(this);
            toast.makeText(this, "Invalid Input", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validateUserInput() {
        if (titleEditText.getText().toString().matches("")) {
            return false;
        }
        if (numberOfDaysEditText.getText().toString().matches("")) {
            numberOfDays = DEFAULT_NUMBER_OF_DAYS;
        } else {
            numberOfDays = Integer.parseInt(numberOfDaysEditText.getText().toString());
        }

        return true;
    }
}
