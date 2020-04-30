package com.example.afinal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DeleteConfirmation extends Activity {

    private TextView delete_confirmation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_confirmation);

        //inflate UI elements
        delete_confirmation = (TextView) findViewById(R.id.delete_confirmation);
    }

}
