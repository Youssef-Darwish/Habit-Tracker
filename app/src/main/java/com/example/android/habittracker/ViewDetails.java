package com.example.android.habittracker;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ViewDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private TextView categoryTextView;
    private int position;
    private String category;
    private RadioGroup radioGroup;
    private RadioButton doneRadioButton, missedRadioButton;
    private Date startDate, todaysDate;
    private static String TAG = ViewDetails.class.toString();

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
        radioGroup = (RadioGroup) findViewById(R.id.radio_select_progress_type);
        doneRadioButton = (RadioButton) findViewById(R.id.radio_did_it);
        missedRadioButton = (RadioButton) findViewById(R.id.radio_missed_it);
        Bundle extras = getIntent().getExtras();
        titleTextView.setText(extras.getString("Title"));
        descriptionTextView.setText(extras.getString("Description"));
        categoryTextView.setText(extras.getString("category"));
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

    public void saveProgress(View view) {

        int numberOfDays;
        try {
            todaysDate = new SimpleDateFormat("dd/MM/yyyy").parse("21/08/2017");
            startDate = new SimpleDateFormat("dd/MM/yyyy").
                    parse(MainActivity.habitsList.get(position).getStartDate());
            Log.d(TAG, todaysDate.toString());
            Log.d(TAG, startDate.toString());
            long diff = todaysDate.getTime() - startDate.getTime();
            numberOfDays = (int) (diff / (1000 * 60 * 60 * 24));

            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == R.id.radio_missed_it) {
                MainActivity.habitsList.get(position).modifyDay(numberOfDays, 0);
            } else if (selectedId == R.id.radio_did_it) {
                int size = MainActivity.habitsList.get(position).getDays().size();
                System.out.println(size);
                String title = MainActivity.habitsList.get(position).getTitle();
                System.out.println(title);
                MainActivity.habitsList.get(position).modifyDay(numberOfDays, 1);
            }
            MainActivity.dbAdapter.updateHabit(MainActivity.habitsList.get(position).getTitle(),
                    MainActivity.habitsList.get(position));
            MainActivity.mAdapter.notifyDataSetChanged();
            Log.d("date modified", String.valueOf(numberOfDays));
            ArrayList<Integer> temp = MainActivity.habitsList.get(position).getDays();
            for (int i = 0; i < temp.size(); i++) {
                Log.d(TAG, String.valueOf(temp.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // add toast to indicate error
        }
        finish();
    }
}


