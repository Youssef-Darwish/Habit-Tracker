package com.example.android.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.google.gson.Gson;


import org.json.JSONException;


import java.util.ArrayList;

/**
 * Created by Youssef on 22/07/17.
 *
 */

public class DBAdapter {

    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private static String TAG = DBAdapter.class.toString();
    private String[] allColumns = {HabitEntry._ID, HabitEntry.COLUMN_HABIT_TITLE, HabitEntry.COLUMN_HABIT_DESCRIPTION,
            HabitEntry.COLUMN_HABIT_CATEGORY, HabitEntry.COLUMN_HABIT_DAYS, HabitEntry.COLUMN_HABIT_NOTIFICATION
            ,HabitEntry.COLUMN_HABIT_NOTIFICATION_TIME,HabitEntry.COLUMN_hABIT_START_DATE};

    public DBAdapter(Context context) {
        dbHelper = new DBHelper(context);
    }



    private String ArrayListToString(ArrayList<Double> days) throws JSONException {

        Gson gson = new Gson();
        return gson.toJson(days);
    }

    private ArrayList<Double> stringToArrayList(String string) throws JSONException{
        Gson gson = new Gson();
        ArrayList<Double> arrayList = (ArrayList<Double>) gson.fromJson(string, ArrayList.class);
        return arrayList;
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
        values.put(HabitEntry.COLUMN_hABIT_START_DATE,habit.getStartDate());
        //Log.d(TAG,String.valueOf(habit.getDays().size()));
        String days = ArrayListToString(habit.getDays());
        values.put(HabitEntry.COLUMN_HABIT_DAYS,days);
        //Log.d(TAG,habit.getTitle());
        //Log.d(TAG,habit.getDescription());
        database.insert(HabitEntry.TABLE_NAME,null,values);

    }

    public void updateHabit(String oldHabitTitle, Habit newHabit) throws JSONException {

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_TITLE,newHabit.getTitle());
        values.put(HabitEntry.COLUMN_HABIT_DESCRIPTION,newHabit.getDescription());
        values.put(HabitEntry.COLUMN_HABIT_CATEGORY,newHabit.getCategory());
      //  values.put(HabitEntry.COLUMN_HABIT_NOTIFICATION,newHabit.getReminder());
        String days = ArrayListToString(newHabit.getDays());
        values.put(HabitEntry.COLUMN_HABIT_DAYS,days);

        Log.d(TAG,newHabit.getTitle());
        Log.d(TAG,newHabit.getDescription());
        Log.d(TAG,newHabit.getCategory());
        Log.d(TAG,oldHabitTitle);
        database.update(HabitEntry.TABLE_NAME,values,
                HabitEntry.COLUMN_HABIT_TITLE +  "='"+oldHabitTitle+"'",null);

    }
    public void deleteHabit (Habit habit){
        String title = habit.getTitle();
        database.delete(HabitEntry.TABLE_NAME,HabitEntry.COLUMN_HABIT_TITLE + "='"+title+"'",null);

    }

    public ArrayList<Habit> getAllData() throws JSONException{

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
    private Habit cursorToHabit(Cursor cursor) throws JSONException{

        Habit h = new Habit("","","",0,0,"");
        h.setTitle(cursor.getString(1));
        h.setDescription(cursor.getString(2));
        h.setCategory(cursor.getString(3));
        h.setNotificationTime(cursor.getInt(6));
        h.setStartDate(cursor.getString(7));
        h.setDays(stringToArrayList(cursor.getString(4)));

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
