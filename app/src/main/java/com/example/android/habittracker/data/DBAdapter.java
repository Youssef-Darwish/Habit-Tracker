package com.example.android.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.android.habittracker.data.Habit;
import com.example.android.habittracker.data.HabitContract.HabitEntry;

import org.json.JSONArray;
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
    private static String TAG = DBAdapter.class.toString();
    private String[] allColumns = {HabitEntry._ID, HabitEntry.COLUMN_HABIT_TITLE, HabitEntry.COLUMN_HABIT_DESCRIPTION,
            HabitEntry.COLUMN_HABIT_CATEGORY, HabitEntry.COLUMN_HABIT_DAYS, HabitEntry.COLUMN_HABIT_NOTIFICATION
            ,HabitEntry.COLUMN_HABIT_NOTIFICATION_TIME};

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
        Log.d(TAG,habit.getTitle());
        Log.d(TAG,habit.getDescription());
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
        database.delete(HabitEntry.TABLE_NAME,HabitEntry.COLUMN_HABIT_TITLE + "='"+title+"'",null);

    }

    public ArrayList<Habit> getAllData(){

        ArrayList<Habit> habits = new ArrayList<>();
        ContentValues values = new ContentValues();
        Cursor cursor = database.query(HabitEntry.TABLE_NAME,allColumns,null,null,null,null,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Habit temp = cursorToHabit(cursor);
            habits.add(temp);
            cursor.moveToNext();

        }


        return habits;

    }
    private Habit cursorToHabit(Cursor cursor){

        Habit h = new Habit("","","",0,0);
        h.setTitle(cursor.getString(1));
        h.setDescription(cursor.getString(2));
        h.setCategory(cursor.getString(3));
        h.setNotificationTime(cursor.getInt(6));
/*
        try {
            JSONObject object = new JSONObject(cursor.getString(3));
            JSONArray ar = object.optJSONArray("uniqueArray");

        }
        catch (JSONException e){
            Log.e("DBAdapter","cannot convert string to json");
            //do sth
        }

        h.setReminder(cursor.getInt(4));
*/
        return h;
    }

    // methods not yet implemented :
    // getAll , select , what else ?
}
