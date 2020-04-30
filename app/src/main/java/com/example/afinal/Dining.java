package com.example.afinal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dining extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
    public List<Map> data = new ArrayList<Map>();
    Map<String, String> currito = new HashMap<String, String>() {{
        put("Name", "Currito");
        put("link", "https://bentley.sodexomyway.com/dining-near-me/currito");
        put("Building", "Dana Center");
        put("Geo", "42.383663,-71.224518");
        put("Sunday", "12PM-11PM");
        put("Monday", "12PM-11PM");
        put("Tuesday", "12PM-11PM");
        put("Wednesday", "12PM-11PM");
        put("Thursday", "12PM-11PM");
        put("Friday", "12PM-7PM");
        put("Saturday", "12PM-6PM");
    }};
    Map<String, String> nest = new HashMap<String, String>() {{
        put("Name", "The Nest");
        put("link", "https://bentley.sodexomyway.com/dining-near-me/the-nest");
        put("Building", "Dana Center");
        put("Geo", "42.383663,-71.224518");
        put("Sunday", "7AM-2PM");
        put("Monday", "8AM-2PM");
        put("Tuesday", "8AM-2PM");
        put("Wednesday", "8AM-2PM");
        put("Thursday", "8AM-2PM");
        put("Friday", "8AM-2PM");
        put("Saturday", "7AM-2PM");

    }};
    Map<String, String> EBB = new HashMap<String, String>() {{
        put("Name", "Einstein Bros. Bagels");
        put("link", "https://bentley.sodexomyway.com/dining-near-me/Einstein-bros-bagels");
        put("Building", "Library");
        put("Geo", "42.387982,-71.220076");
        put("Sunday", "12PM-4PM");
        put("Monday", "7:30AM-9:30PM");
        put("Tuesday", "7:30AM-9:30PM");
        put("Wednesday", "7:30AM-9:30PM");
        put("Thursday", "7:30AM-9:30PM");
        put("Friday", "7:30AM-2:30PM");
        put("Saturday", "CLOSED");
    }};
    Map<String, String> lacava = new HashMap<String, String>() {{
        put("Name", "LaCava Cafe");
        put("link", "https://bentley.sodexomyway.com/dining-near-me/express");
        put("Building", "LaCava");
        put("Geo", "42.388973,-71.219754");
        put("Sunday", "CLOSED");
        put("Monday", "7:30AM-7PM");
        put("Tuesday", "7:30AM-7PM");
        put("Wednesday", "7:30AM-7PM");
        put("Thursday", "7:30AM-7PM");
        put("Friday", "7:30AM-3PM");
        put("Saturday", "CLOSED");
    }};
    Map<String, String> seasons = new HashMap<String, String>() {{
        put("Name", "The 921");
        put("link", "https://bentley.sodexomyway.com/dining-near-me/the-921-dining");
        put("Building", "Student Center");
        put("Geo", "42.386128,-71.222855");
        put("Sunday", "9AM-8PM");
        put("Monday", "7AM-8PM");
        put("Tuesday", "7AM-8PM");
        put("Wednesday", "7AM-8PM");
        put("Thursday", "7AM-8PM");
        put("Friday", "7AM-8PM");
        put("Saturday", "9AM-8PM");
    }};
    Map<String, String> harrys = new HashMap<String, String>() {{
        put("Name", "Harry's");
        put("link", "https://bentley.sodexomyway.com/dining-near-me/Harrys");
        put("Building", "Student Center");
        put("Geo", "42.386128,-71.222855");
        put("Sunday", "5PM-12:45AM");
        put("Monday", "5PM-12:45AM");
        put("Tuesday", "5PM-12:45AM");
        put("Wednesday", "5PM-12:45AM");
        put("Thursday", "5PM-12:45AM");
        put("Friday", "5PM-1:45AM");
        put("Saturday", "5PM-1:45AM");
    }};
    Map<String, String> dunks = new HashMap<String, String>() {{
        put("Name", "Dunkin Dounuts");
        put("link", "https://bentley.sodexomyway.com/dining-near-me/dunkindonuts");
        put("Building", "Collins");
        put("Geo", "42.386831,-71.222326");
        put("Sunday", "8AM-6PM");
        put("Monday", "7AM-7PM");
        put("Tuesday", "7AM-7PM");
        put("Wednesday", "7AM-7PM");
        put("Thursday", "7AM-7PM");
        put("Friday", "7AM-7PM");
        put("Saturday", "8AM-6PM");
    }};
    public List<String> values = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinning);
        Date now = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        String day = simpleDateformat.format(now).toString();
        //Add all dinning options arrays into one array
        data.add(seasons);
        data.add(EBB);
        data.add(currito);
        data.add(nest);
        data.add(harrys);
        data.add(lacava);
        data.add(dunks);
        //populate Table header
        values.add("Name");
        values.add("Building");
        values.add(day + " Hours");
        //load all values from data array to values array to display
        for (int i = 0; i < data.size(); i++) {
            values.add(data.get(i).get("Name").toString());
            values.add(data.get(i).get("Building").toString());
            values.add(data.get(i).get(day).toString());
        }
        GridView myGrid = (GridView) findViewById(R.id.grid);
        mContext = getApplicationContext();
        //create array adapter with layout from cell.xml populated with values array
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                mContext,
                R.layout.cell, values
        ) {
            @Override
            //Create formatted Gridview
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv_cell = (TextView) super.getView(position, convertView, parent);
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;
                int header = (height / 15);
                if (position < 3) {
                    tv_cell.setBackground(ContextCompat.getDrawable(mContext, R.drawable.gridview_item_header));
                    tv_cell.setGravity(Gravity.CENTER);
                    tv_cell.getLayoutParams().height = header; //Header row height
                } else {
                    tv_cell.setBackground(ContextCompat.getDrawable(mContext, R.drawable.gridview_item_background));
                    tv_cell.getLayoutParams().height = (height - header) / 7; //data row height
                }
                return tv_cell;
            }
        };
        myGrid.setAdapter(adapter);
        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String content = values.get(position);
                act(position, content);
            }
        });
    }
    public void onClick(View v) {    }
    public void act(int position, String content) {
        if (position % 3 == 0) { //Name
            loadName(content);
        } else if (position % 3 == 1) { //Map
            System.out.println(content);
            loadMap(content);
        }
    }

    public void loadMap(String building) {
        for (int i = 0; i < data.size(); i++) {
            for (int x = 0; x < data.get(i).size(); x++) {
                if (data.get(i).containsValue(building)) {
                    String label = "Here!";
                    String uriBegin = "geo:" + data.get(i).get("Geo").toString();
                    String query = data.get(i).get("Geo").toString() + "(" + label + ")";
                    String encodedQuery = Uri.encode(query);
                    String uriString = uriBegin + "?q=" + encodedQuery + "&z=21";
                    Uri uri4 = Uri.parse(uriString);
                    Intent i4 = new Intent(Intent.ACTION_VIEW, uri4);
                    startActivity(i4);
                }
            }
        }
    }

    public void loadName(String name) {
        for (int i = 0; i < data.size(); i++) {
            for (int x = 0; x < data.get(i).size(); x++) {
                if (data.get(i).containsValue(name)) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.get(i).get("link").toString()));
                    startActivity(browserIntent);
                }
            }
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}