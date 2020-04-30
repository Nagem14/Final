package com.example.afinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class ActivityShuttleSchedule extends Activity implements View.OnClickListener {
    private TextView text;
    private RadioButton schedule_waverley;
    private RadioButton schedule_Bentley;
    private RadioButton display_webpage;
    private String day_time;
    private String [] day_time_array;
    private String day;
    private String time;
    private String show ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuttle);

        schedule_waverley = (RadioButton) findViewById(R.id.schedule_later);
        schedule_waverley.setOnClickListener(second_radio_listener);

        schedule_Bentley = (RadioButton) findViewById(R.id.schedule_now);
        schedule_Bentley.setOnClickListener(first_radio_listener);

        display_webpage = (RadioButton) findViewById(R.id.display_webpage);
        display_webpage.setOnClickListener(third_radio_listener);

        text = (TextView) findViewById(R.id.display_schedule);
   }

    View.OnClickListener first_radio_listener = new View.OnClickListener(){
        public void onClick(View v) {
            text.setText("");
            show = "";
            for (int i = 0; i < ShuttleLists.scheduleBentley.size(); i++) {
                day_time = ShuttleLists.scheduleBentley.get(i);
                day_time_array = day_time.split(",");
                day = day_time_array[0];
                time = day_time_array[1];
                show += String.format("\t%-30s %10s \n", day, time);


            }
            text.setText(show);
        }
        };

    View.OnClickListener second_radio_listener = new View.OnClickListener(){
        public void onClick(View v) {
            text.setText(null);
            show = "";
            for (int i = 0; i < ShuttleLists.scheduleWaverley.size(); i++ ) {
                day_time = ShuttleLists.scheduleWaverley.get(i);
                day_time_array = day_time.split(",");
                day = day_time_array[0];
                time = day_time_array[1];
                show += String.format("\t%-30s %10s \n", day, time);

        }                text.setText(show);

        }
    };

    View.OnClickListener third_radio_listener = new View.OnClickListener(){
        public void onClick(View v) {
            Intent iSchedule = new Intent(getApplicationContext(), ShuttleWebView.class);
            startActivityForResult(iSchedule, 235);
        }
    };

    private void startActivityForResult(Intent iSchedule) {

    }

    @Override
    public void onClick(View v) {

    }
}
