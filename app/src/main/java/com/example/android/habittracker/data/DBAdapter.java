package com.example.android.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.android.habittracker.data.Habit;
import com.example.android.habittracker.data.HabitContract.HabitEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by youssef on 22/07/17.
 *
 */

public class DBAdapter {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    private String[] allColumns = {HabitEntry._ID, HabitEntry.COLUMN_HABIT_TITLE, HabitEntry.COLUMN_HABIT_DESCRIPTION,
            HabitEntry.COLUMN_HABIT_CATEGORY, HabitEntry.COLUMN_HABIT_DAYS, HabitEntry.COLUMN_HABIT_DAYS};

    public DBAdapter(Context context) {
        dbHelper = new DBHelper(context);
    }

    public String ArrayListToString(ArrayList<Integer> days) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uniqueArray",days);
        String stringToReturn = jsonObject.toString();
        return stringToReturn;
    }
    public void open() throws SQLException {

        database = dbHelper.getWritableDatabase();
    }

    public void close() throws SQLException {
        database.close();
    }


    public void addHabit(Habit habit) throws JSONException {

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_TITLE,habit.getTitle());
        values.put(HabitEntry.COLUMN_HABIT_DESCRIPTION,habit.getDescription());
        values.put(HabitEntry.COLUMN_HABIT_CATEGORY,habit.getCategory());
        values.put(HabitEntry.COLUMN_HABIT_NOTIFICATION,habit.getReminder());
        String days = ArrayListToString(habit.getNumberOfDays());
        values.put(HabitEntry.COLUMN_HABIT_DAYS,days);

        database.insert(HabitEntry.TABLE_NAME,null,values);

    }

    public void updateHabit(Habit oldHabit, Habit newHabit) throws JSONException {

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_TITLE,newHabit.getTitle());
        values.put(HabitEntry.COLUMN_HABIT_DESCRIPTION,newHabit.getDescription());
        values.put(HabitEntry.COLUMN_HABIT_CATEGORY,newHabit.getCategory());
        values.put(HabitEntry.COLUMN_HABIT_NOTIFICATION,newHabit.getReminder());
        String days = ArrayListToString(newHabit.getNumberOfDays());
        values.put(HabitEntry.COLUMN_HABIT_DAYS,days);

        String oldHabitTitle = oldHabit.getTitle();

        database.update(HabitEntry.TABLE_NAME,values,
                HabitEntry.COLUMN_HABIT_TITLE + " =" + oldHabitTitle,null);

    }
    public void deleteHabit (Habit habit){
        String title = habit.getTitle();
        database.delete(HabitEntry.TABLE_NAME,HabitEntry.COLUMN_HABIT_TITLE + " = " + title,null);

    }


    // methods not yet implemented :
    // getAll , select , what else ?
}
