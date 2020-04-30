package com.example.afinal;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

import static java.lang.Boolean.TRUE;

public class ListEvent extends Activity implements View.OnClickListener {

    public static final int requestCode_235 = 235;
    public static final int requestCode_240 = 240;

    final int rnd = new Random().nextInt(800000) + 100000;
    private String eventID = "A" + rnd;

    private String event_name_value;
    private String event_location_value;
    private String event_date_value;
    private String event_start_time_value;
    private String event_end_time_value;
    private String event_description_value;
    private String event_id_value;

    private TextView event_name;
    private EditText event_name_box;
    private TextView event_location;
    private EditText event_location_box;
    private TextView event_date;
    private Button event_datepicker;
    private TextView event_datepicker_box;
    private TextView event_start_time;
    private Button event_start_time_picker;
    private TextView event_start_time_box;
    private TextView event_end_time;
    private Button event_end_time_picker;
    private TextView event_end_time_box;
    private TextView event_description;
    private EditText event_description_box;
    private Button submit;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener sTimeSetListener;
    private TimePickerDialog.OnTimeSetListener eTimeSetListener;

    private SQLiteDatabase db;
    private ContentValues values;
    private Cursor cursor;
    private SQLHelper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_event);

        helper = new SQLHelper(this);

        //create database
        try {
            db = helper.getWritableDatabase();
        } catch (SQLException e) {
            Log.d("SQLite", "Create database failed");
        }

        //inflate UI elements
        event_name = (TextView) findViewById(R.id.event_name);
        event_name_box = (EditText) findViewById(R.id.event_name_box);

        event_location = (TextView) findViewById(R.id.event_location);
        event_location_box = (EditText) findViewById(R.id.event_location_box);

        event_date = (TextView) findViewById(R.id.event_date);
        event_datepicker = (Button) findViewById(R.id.event_datepicker);
        event_datepicker_box = (TextView) findViewById(R.id.event_date_box);
        //setting up datepicker
        event_datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(ListEvent.this, android.R.style.Theme, mDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = month + "/" + dayOfMonth + "/" + year;
                event_datepicker_box.setText(date);
            }
        };

        event_start_time = (TextView) findViewById(R.id.event_start_time);
        event_start_time_picker = (Button) findViewById(R.id.event_start_time_picker);
        event_start_time_box = (TextView) findViewById(R.id.event_start_time_box);
        event_start_time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                boolean twentyFour = TRUE;

                TimePickerDialog dialog = new TimePickerDialog(ListEvent.this, android.R.style.Theme, sTimeSetListener, hour, minute, twentyFour);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        sTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String stime = hourOfDay + ":" + minute;
                event_start_time_box.setText(stime);
            }
        };


        event_end_time = (TextView) findViewById(R.id.event_end_time);
        event_end_time_picker = (Button) findViewById(R.id.event_end_time_picker);
        event_end_time_box = (TextView) findViewById(R.id.event_end_time_box);
        event_end_time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                boolean twentyFour = TRUE;

                TimePickerDialog dialog = new TimePickerDialog(ListEvent.this, android.R.style.Theme, eTimeSetListener, hour, minute, twentyFour);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        eTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String etime = hourOfDay + ":" + minute;
                event_end_time_box.setText(etime);
            }
        };


        event_description = (TextView) findViewById(R.id.event_description);
        event_description_box = (EditText) findViewById(R.id.event_description_box);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);
    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.submit:
                event_name_value = event_name_box.getText().toString();
                event_location_value = event_location_box.getText().toString();
                event_date_value = event_datepicker_box.getText().toString();
                event_start_time_value = event_start_time_box.getText().toString();
                event_end_time_value = event_end_time_box.getText().toString();
                event_description_value = event_description_box.getText().toString();
                event_id_value = eventID;
                helper.addEvent(new Event(event_name_value, event_location_value, event_date_value, event_start_time_value, event_end_time_value, event_description_value, event_id_value));
                Intent i5 = new Intent(this, ListEventConfirm.class);
                i5.putExtra("ID", eventID);
                startActivityForResult(i5, requestCode_235);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case (requestCode_235): {

                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "Result OK for " + requestCode, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Result NOT OK for " + requestCode, Toast.LENGTH_LONG).show();
                }
                break;
            }
            default:
                Toast.makeText(this, "Not my problem", Toast.LENGTH_LONG).show();
        }//switch

    }// onActivityResult

}
