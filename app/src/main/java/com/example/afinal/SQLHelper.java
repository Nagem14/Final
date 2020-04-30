package com.example.afinal;

//This class is not an Activity. It is a helper class
// used to execute the SQL statements on SQLite.

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.Random;

/** Helper to the database, manages versions and creation */
public class SQLHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "event.db";
    public static final int DATABASE_VERSION = 4;
    public static final String TABLE_NAME = "STUDENT_EVENTS";
    public static final String KEY_EVENT_NAME = "EVENT_NAME";
    public static final String KEY_EVENT_LOCATION = "EVENT_LOCATION";
    public static final String KEY_EVENT_DATE = "EVENT_DATE";
    public static final String KEY_EVENT_START_TIME = "EVENT_START_TIME";
    public static final String KEY_EVENT_END_TIME = "EVENT_END_TIME";
    public static final String KEY_EVENT_DESCRIPTION = "EVENT_DESCRIPTION";
    public static final String KEY_EVENT_ID = "EVENT_ID";
    public static final String CREATE_TABLE = "CREATE TABLE STUDENT_EVENTS ("
            + KEY_EVENT_NAME + " text," + KEY_EVENT_LOCATION + " text,"
            + KEY_EVENT_DATE + " text," + KEY_EVENT_START_TIME + " text,"
            + KEY_EVENT_END_TIME + " text," + KEY_EVENT_DESCRIPTION + " text,"
            + KEY_EVENT_ID + " text);";

    private ContentValues values;
    private ArrayList<Event> eventList;
    private Cursor cursor;

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //called to create table
    //NB: this is not a lifecycle method because this class is not an Activity
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = CREATE_TABLE;
        db.execSQL(sql);
    }

    //called when database version mismatch
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion >= newVersion) return;
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);   //not calling a lifecycle method
    }

    //add event to database
    public void addEvent(Event item) {
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(KEY_EVENT_NAME, item.getEventName());
        values.put(KEY_EVENT_LOCATION, item.getEventLocation());
        values.put(KEY_EVENT_DATE, item.getEventDate());
        values.put(KEY_EVENT_START_TIME, item.getEventStartTime());
        values.put(KEY_EVENT_END_TIME, item.getEventEndTime());
        values.put(KEY_EVENT_DESCRIPTION, item.getEventDescription());
        values.put(KEY_EVENT_ID, item.getEventID());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //update all attributes of an Event in database
    public  void updateAll(Event item, Event newItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(KEY_EVENT_NAME, newItem.getEventName());
        values.put(KEY_EVENT_LOCATION, newItem.getEventLocation());
        values.put(KEY_EVENT_DATE, newItem.getEventDate());
        values.put(KEY_EVENT_START_TIME, newItem.getEventStartTime());
        values.put(KEY_EVENT_END_TIME, newItem.getEventEndTime());
        values.put(KEY_EVENT_DESCRIPTION, newItem.getEventDescription());
        values.put(KEY_EVENT_ID, newItem.getEventID());
        db.update(TABLE_NAME, values, KEY_EVENT_ID + "=?", new String[] {item.getEventID()});
        db.close();
    }

    //update Event name in database
    public void updateEventName(Event item, Event newItem){
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(KEY_EVENT_NAME, newItem.getEventName());
        db.update(TABLE_NAME, values, KEY_EVENT_NAME + "=?", new String[] {item.getEventName()});
        db.close();
    }

    //update Event location in database
    public void updateEventLocation(Event item, Event newItem){
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(KEY_EVENT_LOCATION, newItem.getEventLocation());
        db.update(TABLE_NAME, values, KEY_EVENT_LOCATION + "=?", new String[] {item.getEventLocation()});
        db.close();
    }

    //update Event date in database
    public void updateEventDate(Event item, Event newItem){
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(KEY_EVENT_DATE, newItem.getEventDate());
        db.update(TABLE_NAME, values, KEY_EVENT_DATE + "=?", new String[] {item.getEventDate()});
        db.close();
    }

    //update Event start time in database
    public void updateEventStartTime(Event item, Event newItem){
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(KEY_EVENT_START_TIME, newItem.getEventStartTime());
        db.update(TABLE_NAME, values, KEY_EVENT_START_TIME + "=?", new String[] {item.getEventStartTime()});
        db.close();
    }

    //update Event end time in database
    public void updateEventEndTime(Event item, Event newItem){
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(KEY_EVENT_END_TIME, newItem.getEventEndTime());
        db.update(TABLE_NAME, values, KEY_EVENT_END_TIME + "=?", new String[] {item.getEventEndTime()});
        db.close();
    }

    //update Event description in database
    public void updateEventDescription(Event item, Event newItem){
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(KEY_EVENT_DESCRIPTION, newItem.getEventDescription());
        db.update(TABLE_NAME, values, KEY_EVENT_DESCRIPTION + "=?", new String[] {item.getEventDescription()});
        db.close();
    }

    //delete event from database
    public void deleteEvent(Event item){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_EVENT_ID + "=?", new String[] {item.getEventID()});
        db.close();
    }

    //query database and return ArrayList of all events
    public ArrayList<Event> getEventList () {

        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.query(TABLE_NAME,
                new String[] {KEY_EVENT_NAME, KEY_EVENT_LOCATION, KEY_EVENT_DATE, KEY_EVENT_START_TIME, KEY_EVENT_END_TIME, KEY_EVENT_DESCRIPTION, KEY_EVENT_ID},
                null, null, null, null, KEY_EVENT_NAME);

        //write contents of Cursor to list
        eventList = new ArrayList<Event>();
        while (cursor.moveToNext()) {
            String str1 = cursor.getString(cursor.getColumnIndex(KEY_EVENT_NAME));
            String str2 = cursor.getString(cursor.getColumnIndex(KEY_EVENT_LOCATION));
            String str3 = cursor.getString(cursor.getColumnIndex(KEY_EVENT_DATE));
            String str4 = cursor.getString(cursor.getColumnIndex(KEY_EVENT_START_TIME));
            String str5 = cursor.getString(cursor.getColumnIndex(KEY_EVENT_END_TIME));
            String str6 = cursor.getString(cursor.getColumnIndex(KEY_EVENT_DESCRIPTION));
            String str7 = cursor.getString(cursor.getColumnIndex(KEY_EVENT_ID));
            eventList.add(new Event(str1,str2,str3,str4,str5,str6,str7));
        };
        db.close();
        return eventList;
    }
}

