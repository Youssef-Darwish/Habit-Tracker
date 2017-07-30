package com.example.android.habittracker;

import android.app.Activity;
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
import com.example.android.habittracker.data.DBAdapter;
import com.example.android.habittracker.data.Habit;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {


    public static ArrayList<Habit> habitsList;

    private RecyclerView mrecyclerview;
    public static HabitsAdapter mAdapter;
    private Toolbar toolbar;
    public static DBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar  =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Habits");
        habitsList = new ArrayList<Habit>();
        mrecyclerview = (RecyclerView) findViewById(R.id.recycler_view);
        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        habitsList = dbAdapter.getAllData();
        mAdapter  =  new HabitsAdapter(habitsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mrecyclerview.setLayoutManager(mLayoutManager);
        mrecyclerview.setAdapter(mAdapter);
              mAdapter.notifyDataSetChanged();
        for (int i =0; i<habitsList.size();i++){
            System.out.println(habitsList.get(i).getTitle());
            System.out.println(habitsList.get(i).getDescription());
            System.out.println(habitsList.get(i).getCategory());
        }

        //makeFakeData();



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
            case R.id.add_habit_item:
                Intent intent = new Intent(this,CreateNewHabit.class);
                startActivity(intent);



            default:return super.onOptionsItemSelected(item);
        }



    }

    private void makeFakeData(){
        Habit h = new Habit("title1","description1","",0,0);
        habitsList.add(h);

        for (int i = 0; i<20; i++){
            h = new Habit("title" + String.valueOf(i),"description" + String.valueOf(i),
                    "category " + String.valueOf(i),0,0);
            habitsList.add(h);
        }

        mAdapter.notifyDataSetChanged();
    }

    public void viewDetails(View view){

        HabitViewHolder holder = (HabitViewHolder) view.getTag();

        System.out.println(holder.description.getText().toString());

        Intent intent  = new Intent(this,ViewDetails.class);
        intent.putExtra("Title",holder.title.getText().toString());
        intent.putExtra("Description",holder.description.getText().toString());
        intent.putExtra("position",holder.getAdapterPosition());

        startActivityForResult(intent,1);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1){
            if(resultCode == Activity.RESULT_OK){
                int position = data.getIntExtra("position",5);
                dbAdapter.deleteHabit(habitsList.get(position));
                habitsList.remove(position);
                mAdapter.notifyDataSetChanged();
            }
            if(resultCode == Activity.RESULT_CANCELED){
                Toast t = new Toast(this);
                t.makeText(this,"no deletion",Toast.LENGTH_LONG).show();
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
