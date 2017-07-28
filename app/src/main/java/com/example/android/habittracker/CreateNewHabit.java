package com.example.android.habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.habittracker.data.Habit;
import com.example.android.habittracker.R;
import com.example.android.habittracker.MainActivity;
public class CreateNewHabit extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText categoryEditText;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_habit);
        toolbar  = (Toolbar) findViewById(R.id.toolbar_create);
        setSupportActionBar(toolbar);
        spinner = (Spinner) findViewById(R.id.categoriesSpinner);
        titleEditText = (EditText) findViewById(R.id.habitNameEditText);
        descriptionEditText = (EditText) findViewById(R.id.habitDescriptionEditText);

    }


    public void addHabit(View view){

        Habit h = new Habit(titleEditText.getText().toString(),
                descriptionEditText.getText().toString()
                ,spinner.getSelectedItem().toString(),0,0);
        MainActivity.habitsList.add(h);
        MainActivity.mAdapter.notifyDataSetChanged();
        Toast toast = new Toast(this);
        toast.makeText(this,"Habit added",Toast.LENGTH_LONG).show();
        finish();
    }
}
