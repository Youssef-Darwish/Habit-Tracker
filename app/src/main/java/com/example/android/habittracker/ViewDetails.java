package com.example.android.habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

public class ViewDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView titleTextView;
    private TextView descriptionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar_edit);
        setSupportActionBar(toolbar);
        titleTextView = (TextView) findViewById(R.id.title_show_details);
        descriptionTextView =(TextView) findViewById(R.id.description_show_details);

        Bundle extras = getIntent().getExtras();
        titleTextView.setText(extras.getString("Title"));
        descriptionTextView.setText(extras.getString("Description"));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_details,menu);
        return true;
    }
}


