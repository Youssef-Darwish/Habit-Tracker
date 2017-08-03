package com.example.android.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.test.suitebuilder.TestMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.habittracker.MainActivity;

public class EditActivity extends AppCompatActivity {

    private EditText titleEditView;
    private EditText descriptionEditView;
    private EditText categoryEditText;
    private Toolbar toolbar;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        titleEditView = (EditText)findViewById(R.id.titleEditText);
        descriptionEditView = (EditText) findViewById(R.id.descriptionEditText);
        categoryEditText  =(EditText) findViewById(R.id.categoryEditText);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        titleEditView.setText(extras.getString("title"));
        descriptionEditView.setText(extras.getString("description"));
        categoryEditText.setText(extras.getString("category"));
        position = extras.getInt("position");
       // Intent returnIntent = new Intent();
        Toast toast = new Toast(this);
        toast.makeText(this,"entered",Toast.LENGTH_LONG).show();
       // setResult(Activity.RESULT_CANCELED,returnIntent);
        //finish();

    }

    public void saveChanges(View view) {


        MainActivity.habitsList.get(position).setTitle(titleEditView.getText().toString());
        MainActivity.habitsList.get(position).setDescription(descriptionEditView.getText().toString());
        MainActivity.habitsList.get(position).setCategory(categoryEditText.getText().toString());
        // we will encounter a problem while updating
        // MainActivity.dbAdapter.updateHabit();
        MainActivity.mAdapter.notifyDataSetChanged();
        finish();
    }
}
