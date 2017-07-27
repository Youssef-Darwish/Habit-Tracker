package com.example.android.habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.android.habittracker.HabitsAdapter.HabitViewHolder;
import com.example.android.habittracker.data.Habit;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Habit> habitsList;
    private RecyclerView mrecyclerview;
    private HabitsAdapter mAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar  =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        habitsList = new ArrayList<Habit>();
        mrecyclerview = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter  =  new HabitsAdapter(habitsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mrecyclerview.setLayoutManager(mLayoutManager);
        mrecyclerview.setAdapter(mAdapter);

        makeFakeData();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.info_black_id:
                Toast toast = new Toast(this);
                toast.makeText(this,"info item was selected",Toast.LENGTH_LONG).show();
                return true;

            default:return super.onOptionsItemSelected(item);
        }



    }

    private void makeFakeData(){
        Habit h = new Habit("title1","description1","",0,0);
        habitsList.add(h);

        for (int i = 0; i<50; i++){
            h = new Habit("title" + String.valueOf(i),"description" + String.valueOf(i),"",0,0);
            habitsList.add(h);
        }
        mAdapter.notifyDataSetChanged();
    }

    public void tryActivity(View view){

        HabitViewHolder holder = (HabitViewHolder) view.getTag();

        System.out.println(holder.description.getText().toString());

        Intent intent  = new Intent(this,ViewDetails.class);
        intent.putExtra("Title",holder.title.getText().toString());
        intent.putExtra("Description",holder.description.getText().toString());
        startActivity(intent);

    }
}
