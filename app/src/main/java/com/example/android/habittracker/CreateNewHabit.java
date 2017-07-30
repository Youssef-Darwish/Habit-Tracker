package com.example.android.habittracker;

import android.content.Intent;
import android.database.Cursor;
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

import java.util.ArrayList;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_habit);
        toolbar  = (Toolbar) findViewById(R.id.toolbar_create);
        setSupportActionBar(toolbar);
        spinner = (Spinner) findViewById(R.id.categoriesSpinner);
        titleEditText = (EditText) findViewById(R.id.habitNameEditText);
        descriptionEditText = (EditText) findViewById(R.id.habitDescriptionEditText);
        timePicker= (TimePicker) findViewById(R.id.time_picker);
        radioGroup = (RadioGroup) findViewById(R.id.radio_set_notification);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    if (checkedId == R.id.radio_with_notification){
                        timePicker.setVisibility(View.VISIBLE);
                    }
                    else
                        timePicker.setVisibility(View.GONE);
            }
        });

    }


    public void addHabit(View view)throws JSONException{

        Habit h = new Habit(titleEditText.getText().toString(),
                descriptionEditText.getText().toString()
                ,spinner.getSelectedItem().toString(),0,0);
        MainActivity.habitsList.add(h);
        MainActivity.dbAdapter.addHabit(h);

        ArrayList<Habit> hList = MainActivity.dbAdapter.getAllData();
        Log.d("list size",String.valueOf(hList.size()));
        Log.d("list data",hList.get(5).getTitle());
        MainActivity.mAdapter.notifyDataSetChanged();
        Toast toast = new Toast(this);
        toast.makeText(this,"Habit added",Toast.LENGTH_LONG).show();
        finish();
    }
}
