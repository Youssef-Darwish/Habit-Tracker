package com.example.android.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.test.suitebuilder.TestMethod;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    private EditText titleEditView;
    private EditText descriptionEditView;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        titleEditView = (EditText)findViewById(R.id.titleEditText);
        descriptionEditView = (EditText) findViewById(R.id.descriptionEditText);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        titleEditView.setText(extras.getString("title"));
        descriptionEditView.setText(extras.getString("description"));
        Intent returnIntent = new Intent();
        Toast toast = new Toast(this);
        toast.makeText(this,"entered",Toast.LENGTH_LONG).show();
        setResult(Activity.RESULT_CANCELED,returnIntent);
        finish();

    }
}
