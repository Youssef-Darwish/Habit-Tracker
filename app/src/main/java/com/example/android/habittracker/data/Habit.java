package com.example.android.habittracker.data;


import android.util.Log;

import java.util.ArrayList;

/**
 * Created by youssef on 22/07/17.
 * This class defines the basic attributes of habit that will be
 * added, edit or deleted
 */

public class Habit {

    private String title;
    private String description;
    private String category;
    private int reminder;
    private ArrayList<Double> numberOfDays;
    private int notificationTime;
    private String startDate;
    private static String TAG = Habit.class.toString();
    public Habit(String title, String desc, String catg, int days, int remind, String date) {
        this.title = title;
        this.category = catg;
        this.description = desc;
      //  Log.d(TAG,String.valueOf(days));
        numberOfDays = new ArrayList<Double>(days);

        initializeDaysValues(days);
        this.reminder = remind;
        startDate = date;

    }

    public Habit(String title, String catg, int days, int remind, String date) {
        this.title = title;
        this.category = catg;
        numberOfDays = new ArrayList<Double>(days);

        initializeDaysValues(days);
        this.reminder = remind;
        startDate = date;
    }

    public Habit(String title, String desc, String catg, int days, int remind, int notificationTime, String date) {
        this.title = title;
        this.category = catg;
        this.description = desc;
        numberOfDays = new ArrayList<>(days);
     //   Log.d(TAG,String.valueOf(days));
        initializeDaysValues(days);
        this.reminder = remind;
        this.notificationTime = notificationTime;
        startDate = date;
    }


    public void setTitle(String tit) {
        this.title = tit;
    }

    public void setDescription(String desc) {
        description = desc;

    }


    public void setCategory(String catg) {
        category = catg;
    }

    public void setNotificationTime(int time) {
        this.notificationTime = time;
    }

    public void setReminder(int remind) {
        reminder = remind;
    }

    // to mark this habit as done or not on the given day

    public void modifyDay(int day, double value) {
        numberOfDays.set(day, value);
    }

    public void setDays(ArrayList<Double> days) {
        numberOfDays = days;
    }

    public void setNumberOfDays(ArrayList<Double> arrayList) {
        numberOfDays = arrayList;
    }

    public void setStartDate(String date) {
        this.startDate = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getStartDate() {
        return startDate;
    }

    public int getNumberOfDays() {
        return numberOfDays.size();
    }

    public ArrayList<Double> getDays() {
        return numberOfDays;
    }


    public int getNotificationTime() {
        return notificationTime;
    }

    public int getReminder() {
        return this.reminder;
    }

    public void initializeDaysValues(int size){
        for (int i=0;i<size;i++){
            numberOfDays.add(0.0);
        }
    }
}
