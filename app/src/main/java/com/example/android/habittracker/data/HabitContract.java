package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by youssef on 22/07/17.
 */

public class HabitContract {

    public static final class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habitsTable";

        public static final String COLUMN_HABIT_TITLE = "title";

        public static final String COLUMN_HABIT_DESCRIPTION = "description";

        public static final String COLUMN_HABIT_CATEGORY = "category";

        public static final String COLUMN_HABIT_DAYS = "days";

        public static final String COLUMN_HABIT_NOTIFICATION = "notification";

        public static final String COLUMN_HABIT_NOTIFICATION_TIME = "time";

        public static final String COLUMN_hABIT_START_DATE = "startDate";

    }
}
