package com.example.android.habittracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.habittracker.data.Habit;

import java.util.ArrayList;

/**
 * Created by youssef on 23/07/17.
 *
 */

public class HabitsAdapter extends RecyclerView.Adapter<HabitsAdapter.HabitViewHolder>{

    private ArrayList<Habit> habitsList;

    public class HabitViewHolder extends RecyclerView.ViewHolder{

        public TextView title,description;

        public HabitViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.item_title);
            description = (TextView) itemView.findViewById(R.id.item_description);

        }
    }

    public HabitsAdapter (ArrayList<Habit> habits) {
            this.habitsList = habits;
    }

    @Override
    public HabitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item,parent,false);
        return new HabitViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HabitViewHolder holder, int position) {
            Habit habit = habitsList.get(position);
            holder.title.setText(habit.getTitle());
            holder.description.setText(habit.getDescription());
    }

    @Override
    public int getItemCount() {
       return habitsList.size();
    }



}
