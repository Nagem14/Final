package com.example.afinal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Emergency extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
    public List<Map> data = new ArrayList<Map>();
    //Creating Map of all emergency contacts information
    Map<String, String> police = new HashMap<String, String>() {{
        put("Name", "Campus Police");
        put("link", "https://www.bentley.edu/offices/university-police");
        put("Building", "University Police");
        put("Geo", "42.385835,-71.220964");
        put("Phone", "(781) 891-2201");
    }};
    Map<String, String> card = new HashMap<String, String>() {{
        put("Name", "Card Office");
        put("link", "https://www.bentley.edu/offices/university-police/card-office");
        put("Building", "University Police");
        put("Geo", "42.385835,-71.220964");
        put("Phone", "(781) 891-2201");

    }};
    Map<String, String> disserv = new HashMap<String, String>() {{
        put("Name", "Counceling");
        put("link", "https://www.bentley.edu/offices/disability-services");
        put("Building", "Counceling Center");
        put("Geo", "42.385835,-71.220964");
        put("Phone", "(781) 891-2004");
    }};
    Map<String, String> office = new HashMap<String, String>() {{
        put("Name", "Front Office");
        put("link", "https://www.bentley.edu/about/bentley-education");
        put("Building", "Rauch Administration Center");
        put("Geo", "42.388518,-71.221221");
        put("Phone", "(781) 891-2000");
    }};
    Map<String, String> tech = new HashMap<String, String>() {{
        put("Name", "Help Desk");
        put("link", "https://www.bentley.edu/offices/it/client-services");
        put("Building", "Library");
        put("Geo", "42.388041,-71.219875");
        put("Phone", "(781) 891-2854");
    }};
    Map<String, String> health = new HashMap<String, String>() {{
        put("Name", "Health Center");
        put("link", "https://www.bentley.edu/university-life/student-health/health-center");
        put("Building", "Rhodes Hall");
        put("Geo", "42.386431,-71.222685");
        put("Phone", "(781) 891-3131");
    }};
    public List<String> values = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        //Add all emergency contacts arrays into one array
        data.add(police);
        data.add(card);
        data.add(disserv);
        data.add(office);
        data.add(tech);
        data.add(health);
        //Populate Table header
        values.add("Name");
        values.add("Building");
        values.add("Phone");
        for (int i = 0; i < data.size(); i++) {
            //load all values from data array to values array to display
            values.add(data.get(i).get("Name").toString());
            values.add(data.get(i).get("Building").toString());
            values.add(data.get(i).get("Phone").toString());
        }
        GridView myGrid = findViewById(R.id.grid);
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
                if (position < 3) { // Header Formatting
                    tv_cell.setBackground(ContextCompat.getDrawable(mContext, R.drawable.gridview_item_header));
                    tv_cell.setGravity(Gravity.CENTER);
                    tv_cell.getLayoutParams().height = header; //Header row height
                } else {//data formatting
                    tv_cell.setBackground(ContextCompat.getDrawable(mContext, R.drawable.gridview_item_background));
                    tv_cell.getLayoutParams().height = (height - header) / 7; //Data row height
                }
                return tv_cell;
            }
        };
        myGrid.setAdapter(adapter);
        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                System.out.println(position);
                String content = values.get(position);
                act(position, content);
            }
        });
        TextView covid = findViewById(R.id.covid);
        covid.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.covid:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bentley.edu/coronavirus"));
                startActivity(browserIntent);
        }
    }

    public void act(int position, String content) {
        if (position == 0 || position == 1 || position == 2) {
            return;
        } else if (position % 3 == 0) { //Name
            loadName(content);
        } else if (position % 3 == 1) { //Map
            System.out.println(content);
            loadMap(content);
        } else if (position % 3 == 2) { //Phone
            makeCall(content);
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
                    String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                    Uri uri4 = Uri.parse(uriString);
                    Intent i4 = new Intent(Intent.ACTION_VIEW, uri4);
                    startActivity(i4);
                }
            }
        }
    }

    public void makeCall(String phone) {
        for (int i = 0; i < data.size(); i++) {
            for (int x = 0; x < data.get(i).size(); x++) {
                if (data.get(i).containsValue(phone)) {
                    String s = data.get(i).get("Phone").toString();
                    s = s.replace("-", "").replace(" ", "").replace("(", "").replace(")", "");
                    Uri uri3 = Uri.parse("tel:" + s);
                    Intent i3 = new Intent(Intent.ACTION_CALL, uri3);
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(i3);
                }
            }}
    }
    public void loadName(String name){
        for (int i = 0; i < data.size(); i++) {
            for(int x = 0; x < data.get(i).size(); x++){
                if(data.get(i).containsValue(name)){
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