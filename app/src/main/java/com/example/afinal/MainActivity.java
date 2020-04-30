package com.example.afinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    private Button shuttle_schedule;
    private Button search_main_button;
    private Button bulletin_main_button;
    private Button operation;
    private Button emergency;

    public static final int requestCode_235 = 235;
    public static final int requestCode_240 = 240;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_main_button = (Button) findViewById(R.id.search_main_button);
        search_main_button.setOnClickListener(this);

        bulletin_main_button = (Button) findViewById(R.id.bulletin_main_button);
        bulletin_main_button.setOnClickListener(this);

        operation = (Button) findViewById(R.id.operation);
        operation.setOnClickListener(this);

        emergency = (Button) findViewById(R.id.emergency);
        emergency.setOnClickListener(this);

        shuttle_schedule = (Button) findViewById(R.id.shuttle_schedule);
        shuttle_schedule.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.search_main_button:
                Intent i1 = new Intent(this, Search.class);
                startActivityForResult(i1, requestCode_235);
                break;

            case R.id.bulletin_main_button:
                Intent i2 = new Intent(this, Bulletin.class);
                startActivityForResult(i2, requestCode_235);
                break;

            case R.id.operation:
                Intent i3 = new Intent(this, Operations.class);
                startActivityForResult(i3, requestCode_235);
                break;

            case R.id.emergency:
                Intent i4 = new Intent(this, Emergency.class);
                startActivityForResult(i4, requestCode_235);
                break;

            case R.id.shuttle_schedule:
                Intent i5 = new Intent(this, ActivityShuttleSchedule.class);
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
