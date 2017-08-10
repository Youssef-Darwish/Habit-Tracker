package com.example.android.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import static android.R.id.edit;

public class ViewDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView categoryTextView;
    private int position;
    private String category;

    private String url = "http://lorempixel.com/400/200/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar_edit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Habit Details");
        titleTextView = (TextView) findViewById(R.id.title_show_details);
        descriptionTextView = (TextView) findViewById(R.id.description_show_details);
        categoryTextView = (TextView) findViewById(R.id.category_show_details);
        Bundle extras = getIntent().getExtras();
        titleTextView.setText(extras.getString("Title"));
        descriptionTextView.setText(extras.getString("Description"));
        categoryTextView.setText(extras.getString("category"));

/*
        Glide
                .with(this)
                .load(url)
                .into(imageView);
*/
        position = extras.getInt("position");
        category = extras.getString("category");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.edit_item) {

            Intent intentEdit = new Intent(this, EditActivity.class);
            System.out.println(titleTextView.getText().toString());
            intentEdit.putExtra("title", titleTextView.getText().toString());
            intentEdit.putExtra("description", descriptionTextView.getText().toString());
            intentEdit.putExtra("category", category);
            intentEdit.putExtra("position", position);
            startActivity(intentEdit);
        } else if (item.getItemId() == R.id.delete_item) {

            MainActivity.dbAdapter.deleteHabit(MainActivity.habitsList.get(position));
            MainActivity.habitsList.remove(position);
            MainActivity.mAdapter.notifyDataSetChanged();

            finish();


        }

        return true;
    }
/*
    @Override
    protected void onPause() {
        super.onPause();
        Glide.clear(imageView);
    }
    */

}


