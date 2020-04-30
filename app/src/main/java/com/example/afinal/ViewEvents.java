package com.example.afinal;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewEvents extends Activity implements AdapterView.OnItemClickListener {

    private String event_name_value;
    private String event_location_value;
    private String event_date_value;
    private String event_start_time_value;
    private String event_end_time_value;
    private String event_description_value;
    private String event_id_value;

    private SQLHelper helper;
    ArrayList<Event> eventList;
    private ListView listview;
    Event event;

    public static final int requestCode_235 = 235;
    public static final int requestCode_240 = 240;

    //variable to hold listView item position
    int itemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_events);

        helper = new SQLHelper(this);

        eventList = helper.getEventList();

        SixColumn_ListAdapter adapter = new SixColumn_ListAdapter(this, R.layout.view_events_adapter,eventList);

        listview = (ListView) findViewById(R.id.view_events_list);

        //attach listener to listView
        listview.setOnItemClickListener(this);

        listview.setAdapter(adapter);
    }

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        itemPosition = position;
        Event itemValue = eventList.get(position);
        event_name_value = itemValue.getEventName();
        event_location_value = itemValue.getEventLocation();
        event_date_value = itemValue.getEventDate();
        event_start_time_value = itemValue.getEventStartTime();
        event_end_time_value = itemValue.getEventEndTime();
        event_description_value = itemValue.getEventDescription();
        event_id_value = itemValue.getEventID();
        Intent i6 = new Intent(this, ViewIndividualEvent.class);
        i6.putExtra("eventName", event_name_value);
        i6.putExtra("eventLocation", event_location_value);
        i6.putExtra("eventDate", event_date_value);
        i6.putExtra("eventStartTime", event_start_time_value);
        i6.putExtra("eventEndTime", event_end_time_value);
        i6.putExtra("eventDescription", event_description_value);
        i6.putExtra("eventID", event_id_value);
        startActivityForResult(i6,requestCode_235);
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
