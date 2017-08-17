package com.example.android.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.habittracker.data.HabitContract.HabitEntry;
/**
 * Created by youssef on 22/07/17.
 *
 */

public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "habits.db";

    private static final int DATABASE_VERSION = 3;

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME +
                " (" +
                HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HabitEntry.COLUMN_HABIT_TITLE + " TEXT NOT NULL, " +
                HabitEntry.COLUMN_HABIT_DESCRIPTION + " TEXT NOT NULL, " +
                HabitEntry.COLUMN_HABIT_CATEGORY + " TEXT NOT NULL, " +
                HabitEntry.COLUMN_HABIT_DAYS + " TEXT NOT NULL, " +
                HabitEntry.COLUMN_HABIT_NOTIFICATION + " INTEGER, " +
                HabitEntry.COLUMN_HABIT_NOTIFICATION_TIME+ " INTEGER, " +
                HabitEntry.COLUMN_hABIT_START_DATE+ " TEXT " +
                ");";

        db.execSQL(CREATE_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME);

        onCreate(db);

    }
}
