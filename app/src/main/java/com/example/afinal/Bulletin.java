package com.example.afinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Bulletin extends Activity implements View.OnClickListener {

    private Button list_new_event;
    private Button view_events;

    public static final int requestCode_235 = 235;
    public static final int requestCode_240 = 240;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulletin_main);

        list_new_event = (Button)findViewById(R.id.list_event_button);
        list_new_event.setOnClickListener(this);

        view_events = (Button)findViewById(R.id.view_events_button);
        view_events.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.list_event_button:
                Intent i3 = new Intent(this, ListEvent.class);
                startActivityForResult(i3,requestCode_235);
                break;

            case R.id.view_events_button:
                Intent i4 = new Intent(this, ViewEvents.class);
                startActivityForResult(i4,requestCode_235);
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
