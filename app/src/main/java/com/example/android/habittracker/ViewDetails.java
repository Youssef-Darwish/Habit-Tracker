package com.example.android.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.edit;

public class ViewDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private int position;
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
        position = extras.getInt("position");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_show_details,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.edit_item:
                Toast editToast = new Toast(this);
                editToast.makeText(this,"new edit activity",Toast.LENGTH_LONG).show();
                Intent intentEdit = new Intent(this,EditActivity.class);
                System.out.println(titleTextView.getText().toString());
                intentEdit.putExtra("title",titleTextView.getText().toString());
                intentEdit.putExtra("description",descriptionTextView.getText().toString());

                startActivity(intentEdit);

            case R.id.delete_item:

                Intent returnIntent = new Intent();
                returnIntent.putExtra("position",position);
                Toast toast = new Toast(this);
                toast.makeText(this,"Habit Deleted!",Toast.LENGTH_LONG).show();
                setResult(Activity.RESULT_OK,returnIntent);
                finish();

            default: return true;

        }


    }
}


