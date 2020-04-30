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

public class EditEvent extends Activity implements View.OnClickListener {

    public static final int requestCode_235 = 235;
    public static final int requestCode_240 = 240;

    private String event_name_value;
    private String event_location_value;
    private String event_date_value;
    private String event_start_time_value;
    private String event_end_time_value;
    private String event_description_value;
    private String event_id_value;

    private String new_event_name_value;
    private String new_event_location_value;
    private String new_event_date_value;
    private String new_event_start_time_value;
    private String new_event_end_time_value;
    private String new_event_description_value;

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
    private Button save;
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
        setContentView(R.layout.edit_event);

        helper = new SQLHelper(this);

        //create database
        try {
            db = helper.getWritableDatabase();
        } catch (SQLException e) {
            Log.d("SQLite", "Create database failed");
        }

        Intent intent = getIntent();
        event_id_value = intent.getStringExtra("eventID");
        event_name_value = intent.getStringExtra("eventName");
        event_location_value = intent.getStringExtra("eventLocation");
        event_date_value = intent.getStringExtra("eventDate");
        event_start_time_value = intent.getStringExtra("eventStartTime");
        event_end_time_value = intent.getStringExtra("eventEndTime");
        event_description_value = intent.getStringExtra("eventDescription");

        //inflate UI elements
        event_name = (TextView) findViewById(R.id.event_name);
        event_name_box = (EditText) findViewById(R.id.event_name_box);
        event_name_box.setText(event_name_value);

        event_location = (TextView) findViewById(R.id.event_location);
        event_location_box = (EditText) findViewById(R.id.event_location_box);
        event_location_box.setText(event_location_value);

        event_date = (TextView) findViewById(R.id.event_date);
        event_datepicker = (Button) findViewById(R.id.event_datepicker);
        event_datepicker_box = (TextView) findViewById(R.id.event_date_box);
        event_datepicker_box.setText(event_date_value);
        //setting up datepicker
        event_datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(EditEvent.this, android.R.style.Theme, mDateSetListener, year, month, day);
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
        event_start_time_box.setText(event_start_time_value);
        event_start_time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                boolean twentyFour = TRUE;

                TimePickerDialog dialog = new TimePickerDialog(EditEvent.this, android.R.style.Theme, sTimeSetListener, hour, minute, twentyFour);
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
        event_end_time_box.setText(event_end_time_value);
        event_end_time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int hour = cal.get(Calendar.HOUR_OF_DAY);
                int minute = cal.get(Calendar.MINUTE);
                boolean twentyFour = TRUE;

                TimePickerDialog dialog = new TimePickerDialog(EditEvent.this, android.R.style.Theme, eTimeSetListener, hour, minute, twentyFour);
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
        event_description_box.setText(event_description_value);

        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);
    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.save:
                new_event_name_value = event_name_box.getText().toString();
                new_event_location_value = event_location_box.getText().toString();
                new_event_date_value = event_datepicker_box.getText().toString();
                new_event_start_time_value = event_start_time_box.getText().toString();
                new_event_end_time_value = event_end_time_box.getText().toString();
                new_event_description_value = event_description_box.getText().toString();
                event_id_value = event_id_value;
                helper.updateAll(new Event(event_name_value, event_location_value, event_date_value, event_start_time_value, event_end_time_value, event_description_value, event_id_value),new Event(new_event_name_value, new_event_location_value, new_event_date_value, new_event_start_time_value, new_event_end_time_value, new_event_description_value, event_id_value));
                Intent i11 = new Intent(this, EditConfirmation.class);
                startActivityForResult(i11, requestCode_235);
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
