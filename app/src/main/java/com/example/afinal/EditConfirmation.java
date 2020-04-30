package com.example.afinal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EditConfirmation extends Activity {

    private TextView edit_confirmation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_confirmation);

        //inflate UI elements
        edit_confirmation = (TextView) findViewById(R.id.edit_confirmation);
    }

}