package com.example.android.habittracker.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.android.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by youssef on 22/07/17.
 *
 */

public class DBAdapter {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    private String [] allColumns = {HabitEntry._ID,HabitEntry.COLUMN_HABIT_TITLE,HabitEntry.COLUMN_HABIT_DESCRIPTION,
                    HabitEntry.COLUMN_HABIT_CATEGORY, HabitEntry.COLUMN_HABIT_DAYS, HabitEntry.COLUMN_HABIT_DAYS};

    public DBAdapter(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException{

        database = dbHelper.getWritableDatabase();
    }

    public void close ()throws SQLException{
        database.close();
    }



}
