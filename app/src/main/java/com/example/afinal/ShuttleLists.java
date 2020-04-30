package com.example.afinal;

import java.util.ArrayList;
import java.util.Arrays;

public class ShuttleLists {

    public static ArrayList<String> scheduleBentley = new ArrayList<String>(
            Arrays.asList("Monday, 8:00am", "Monday, 9:00am", "Monday, 10:00am", "Monday, 11:00am",
                    "Monday, 2:00pm", "Monday, 3:00pm", "Monday, 4:00pm", "Monday, 5:00pm", "Monday, 6:00pm",
                    "Tuesday, 8:00am", "Tuesday, 9:00am", "Tuesday, 10:00am", "Tuesday, 11:00am",
                    "Tuesday, 2:00pm", "Tuesday, 3:00pm", "Tuesday, 4:00pm", "Tuesday, 5:00pm", "Tuesday, 6:00pm",
                    "Wednesday, 8:00am", "Wednesday, 9:00am", "Wednesday, 10:00am", "Wednesday, 11:00am",
                    "Wednesday, 2:00pm", "Wednesday, 3:00pm", "Wednesday, 4:00pm", "Wednesday, 5:00pm", "Wednesday, 6:00pm",
                    "Thursday, 8:00am", "Thursday, 9:00am", "Thursday, 10:00am", "Thursday, 11:00am",
                    "Thursday, 2:00pm", "Thursday, 3:00pm", "Thursday, 4:00pm", "Thursday, 5:00pm", "Thursday, 6:00pm",
                    "Friday, 8:00am", "Friday, 9:00am", "Friday, 10:00am", "Friday, 11:00am",
                    "Friday, 2:00pm", "Friday, 3:00pm", "Friday, 4:00pm", "Friday, 5:00pm", "Friday, 6:00pm"
            ));

    public static ArrayList<String> scheduleWaverley = new ArrayList<String>(
            Arrays.asList("Monday, 8:30am", "Monday, 10:30am", "Monday, 2:30pm", "Monday, 4:30pm", "Monday, 7:30pm", "Monday, 10:30pm",
                    "Tuesday, 8:30am", "Tuesday, 10:30am", "Tuesday, 2:30pm", "Tuesday, 4:30pm", "Tuesday, 7:30pm", "Tuesday, 10:30pm",
                    "Wednesday, 8:30am", "Wednesday, 10:30am", "Wednesday, 2:30pm", "Wednesday, 4:30pm", "Wednesday, 7:30pm", "Wednesday, 10:30pm",
                    "Thursday, 8:30am", "Thursday, 10:30am", "Thursday, 2:30pm", "Thursday, 4:30pm", "Thursday, 7:30pm", "Thursday, 10:30pm",
                    "Friday, 8:30am", "Friday, 10:30am", "Friday, 2:30pm", "Friday, 4:30pm", "Friday, 7:30pm", "Friday, 10:30pm"
            ));

    public static final String DATABASE_NAME = "shuttle_schedule.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "shuttle_schedule";
    public static final String KEY_DAY = "day";
    public static final String KEY_TIME = "time";
    public static final String KEY_ID = "id integer primary key autoincrement";
    public static final String CREATE_TABLE = "CREATE TABLE shuttle_schedule ("
            + ShuttleLists.KEY_ID + "," + ShuttleLists.KEY_DAY + " text,"
            + ShuttleLists.KEY_TIME + " text);";

}
