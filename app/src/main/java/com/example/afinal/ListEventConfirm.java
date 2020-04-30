package com.example.afinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;


public class ListEventConfirm extends Activity {

    private TextView list_event_confirm_message;
    private TextView list_event_confirm_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_event_confirm);

        list_event_confirm_message = (TextView) findViewById(R.id.list_event_confirm_message);
        list_event_confirm_id = (TextView) findViewById(R.id.list_event_confirm_ID);

        Intent intent = getIntent();
        final String ID = intent.getStringExtra("ID");

        list_event_confirm_id.setText(ID);
    }
    //Built in back button to go return to home
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN)) {
            Intent iReturnHome = new Intent(this, MainActivity.class);
            startActivity(iReturnHome);
            return true;
        }
        return false;
    }


}
