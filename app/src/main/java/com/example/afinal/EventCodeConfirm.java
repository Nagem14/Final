package com.example.afinal;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EventCodeConfirm extends Activity implements View.OnClickListener {

    public static final int requestCode_235 = 235;
    public static final int requestCode_240 = 240;

    private String event_id_value;
    private String event_name_value;
    private String event_location_value;
    private String event_date_value;
    private String event_start_time_value;
    private String event_end_time_value;
    private String event_description_value;
    private String action;

    private TextView code_confirm_instruction;
    private EditText code_input_box;
    private Button confirm;

    private SQLiteDatabase db;
    private ContentValues values;
    private Cursor cursor;
    private SQLHelper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_code);

        helper = new SQLHelper(this);

        //create database
        try {
            db = helper.getWritableDatabase();
        } catch (SQLException e) {
            Log.d("SQLite", "Create database failed");
        }

        //inflate UI elements
        code_confirm_instruction = (TextView) findViewById(R.id.code_confirm_instruction);
        code_input_box = (EditText) findViewById(R.id.code_input_box);
        confirm = (Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(this);

        Intent intent = getIntent();
        event_id_value = intent.getStringExtra("eventID");
        event_name_value = intent.getStringExtra("eventName");
        event_location_value = intent.getStringExtra("eventLocation");
        event_date_value = intent.getStringExtra("eventDate");
        event_start_time_value = intent.getStringExtra("eventStartTime");
        event_end_time_value = intent.getStringExtra("eventEndTime");
        event_description_value = intent.getStringExtra("eventDescription");
        action = intent.getStringExtra("action");
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.confirm:
                if (code_input_box.getText().toString().equals(event_id_value)) {
                    if (action.equals("edit")) {
                        Intent i9 = new Intent(this, EditEvent.class);
                        i9.putExtra("eventID", event_id_value);
                        i9.putExtra("eventName", event_name_value);
                        i9.putExtra("eventLocation", event_location_value);
                        i9.putExtra("eventDate", event_date_value);
                        i9.putExtra("eventStartTime", event_start_time_value);
                        i9.putExtra("eventEndTime", event_end_time_value);
                        i9.putExtra("eventDescription", event_description_value);
                        startActivityForResult(i9,requestCode_235);
                    }else if (action.equals("delete")) {
                        helper.deleteEvent(new Event(event_name_value, event_location_value, event_date_value, event_start_time_value, event_end_time_value, event_description_value, event_id_value));
                        Intent i10 = new Intent(this, DeleteConfirmation.class);
                        startActivityForResult(i10,requestCode_235);
                    }
                } else {
                    Toast.makeText(this, "Wrong code, please try again", Toast.LENGTH_LONG).show();
                }
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
