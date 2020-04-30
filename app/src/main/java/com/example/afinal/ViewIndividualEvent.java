package com.example.afinal;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewIndividualEvent extends Activity implements View.OnClickListener {

    public static final int requestCode_235 = 235;
    public static final int requestCode_240 = 240;

    private String event_name_value;
    private String event_location_value;
    private String event_date_value;
    private String event_start_time_value;
    private String event_end_time_value;
    private String event_description_value;
    private String event_id_value;

    private TextView event_name;
    private TextView event_name_box;
    private TextView event_location;
    private TextView event_location_box;
    private TextView event_date;
    private TextView event_datepicker_box;
    private TextView event_start_time;
    private TextView event_start_time_box;
    private TextView event_end_time;
    private TextView event_end_time_box;
    private TextView event_description;
    private TextView event_description_box;
    private Button edit;
    private Button delete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_individual_event);

        //inflate UI elements
        event_name = (TextView) findViewById(R.id.event_name);
        event_name_box = (TextView) findViewById(R.id.event_name_box);
        event_location = (TextView) findViewById(R.id.event_location);
        event_location_box = (TextView) findViewById(R.id.event_location_box);
        event_date = (TextView) findViewById(R.id.event_date);
        event_datepicker_box = (TextView) findViewById(R.id.event_date_box);
        event_start_time = (TextView) findViewById(R.id.event_start_time);
        event_start_time_box = (TextView) findViewById(R.id.event_start_time_box);
        event_end_time = (TextView) findViewById(R.id.event_end_time);
        event_end_time_box = (TextView) findViewById(R.id.event_end_time_box);
        event_description = (TextView) findViewById(R.id.event_description);
        event_description_box = (TextView) findViewById(R.id.event_description_box);
        edit = (Button) findViewById(R.id.edit);
        delete = (Button) findViewById(R.id.delete);

        Intent intent = getIntent();

        event_name_value = intent.getStringExtra("eventName");
        event_name_box.setText(event_name_value);

        event_location_value = intent.getStringExtra("eventLocation");
        event_location_box.setText(event_location_value);

        event_date_value = intent.getStringExtra("eventDate");
        event_datepicker_box.setText(event_date_value);

        event_start_time_value = intent.getStringExtra("eventStartTime");
        event_start_time_box.setText(event_start_time_value);

        event_end_time_value = intent.getStringExtra("eventEndTime");
        event_end_time_box.setText(event_end_time_value);

        event_description_value = intent.getStringExtra("eventDescription");
        event_description_box.setText(event_description_value);

        event_id_value = intent.getStringExtra("eventID");

        edit.setOnClickListener(this);
        delete.setOnClickListener(this);

        //Toast.makeText(this, event_id_value, Toast.LENGTH_LONG).show();
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.edit:
                Intent i7 = new Intent(this, EventCodeConfirm.class);
                i7.putExtra("eventID", event_id_value);
                i7.putExtra("action","edit");
                i7.putExtra("eventName", event_name_value);
                i7.putExtra("eventLocation", event_location_value);
                i7.putExtra("eventDate", event_date_value);
                i7.putExtra("eventStartTime", event_start_time_value);
                i7.putExtra("eventEndTime", event_end_time_value);
                i7.putExtra("eventDescription", event_description_value);
                startActivityForResult(i7,requestCode_235);
                break;

            case R.id.delete:
                Intent i8 = new Intent(this, EventCodeConfirm.class);
                i8.putExtra("eventID", event_id_value);
                i8.putExtra("action","delete");
                i8.putExtra("eventName", event_name_value);
                i8.putExtra("eventLocation", event_location_value);
                i8.putExtra("eventDate", event_date_value);
                i8.putExtra("eventStartTime", event_start_time_value);
                i8.putExtra("eventEndTime", event_end_time_value);
                i8.putExtra("eventDescription", event_description_value);
                startActivityForResult(i8,requestCode_235);
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
