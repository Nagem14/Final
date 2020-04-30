package com.example.afinal;

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

public class Operations extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
    public List<Map> data = new ArrayList<Map>();
    //Creating Map of all operations information
    Map<String, String> police = new HashMap<String, String>() {{
        put("Name", "University Police");
        put("link", "https://www.bentley.edu/offices/university-police");
        put("Building", "University Police");
        put("Geo", "42.385835, -71.220964");
        put("Sunday", "ALWAYS OPEN");
        put("Monday", "ALWAYS OPEN");
        put("Tuesday", "ALWAYS OPEN");
        put("Wednesday", "ALWAYS OPEN");
        put("Thursday", "ALWAYS OPEN");
        put("Friday", "ALWAYS OPEN");
        put("Saturday", "ALWAYS OPEN");
    }};
    Map<String, String> lib = new HashMap<String, String>() {{
        put("Name", "Library");
        put("link", "https://library.bentley.edu/about/hours.asp");
        put("Building", "Library");
        put("Geo", "42.385835, -71.220964");
        put("Sunday", "10AM-2AM");
        put("Monday", "7:30AM-2AM");
        put("Tuesday", "7:30AM-2AM");
        put("Wednesday", "7:30AM-2AM");
        put("Thursday", "7:30AM-2AM");
        put("Friday", "7:30AM-6PM");
        put("Saturday", "10AM-6PM");

    }};Map<String, String> food = new HashMap<String, String>() {{
        put("Name", "Food Services");
        put("link", "https://bentley.sodexomyway.com/dining-near-me/hours");
        put("Building", "Click for dinning locations");
        put("Geo", "42.385835, -71.220964");
        put("Sunday", "Click for dinning hours");
        put("Monday", "Click for dinning hours");
        put("Tuesday", "Click for dinning hours");
        put("Wednesday", "Click for dinning hours");
        put("Thursday", "Click for dinning hours");
        put("Friday", "Click for dinning hours");
        put("Saturday", "Click for dinning hours");
    }};
    Map<String, String> gym = new HashMap<String, String>() {{
        put("Name", "Fitness Center");
        put("link", "https://www.bentleyfalcons.com/facilities/hours");
        put("Building", "Dana Center");
        put("Geo", "42.388518, -71.221221");
        put("Sunday", "9AM-11PM");
        put("Monday", "7AM-11PM");
        put("Tuesday", "7AM-11PM");
        put("Wednesday", "7AM-11PM");
        put("Thursday", "7AM-11PM");
        put("Friday", "7AM-7PM");
        put("Saturday", "9AM-6PM");
    }};
    Map<String, String> academ = new HashMap<String, String>() {{
        put("Name", "Academic Services");
        put("link", "https://www.bentley.edu/about/bentley-education");
        put("Building", "Rauch Administration Center");
        put("Geo", "42.388518, -71.221221");
        put("Sunday", "CLOSED");
        put("Monday", "8:30AM-4:30AM");
        put("Tuesday", "8:30AM-4:30AM");
        put("Wednesday", "8:30AM-4:30AM");
        put("Thursday", "8:30AM-4:30AM");
        put("Friday", "8:30AM-4:30AM");
        put("Saturday", "CLOSED");
    }};
    Map<String, String> tech = new HashMap<String, String>() {{
        put("Name", "Help Desk");
        put("link", "https://www.bentley.edu/offices/it/client-services");
        put("Building", "Library");
        put("Geo", "42.388041, -71.219875");
        put("Sunday", "CLOSED");
        put("Monday", "7:30AM-8PM");
        put("Tuesday", "7:30AM-8PM");
        put("Wednesday", "7:30AM-8PM");
        put("Thursday", "7:30AM-8PM");
        put("Friday", "7:30AM-5PM");
        put("Saturday", "CLOSED");
    }};
    Map<String, String> dunks = new HashMap<String, String>() {{
        put("Name", "Dunkin Dounuts");
        put("link", "https://bentley.sodexomyway.com/dining-near-me/dunkindonuts");
        put("Building", "Collins");
        put("Geo", "42.388041, -71.219875");
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
        setContentView(R.layout.activity_operations);
        Date now = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        String day = simpleDateformat.format(now).toString();
        //Add all operation arrays into one array
        data.add(police);
        data.add(lib);
        data.add(gym);
        data.add(academ);
        data.add(tech);
        data.add(dunks);
        data.add(food);
        //Populate Table header
        values.add("Name");
        values.add("Building");
        values.add(day + " Hours");

        for (int i = 0; i < data.size(); i++) {
            //load all values from data array to values array to display
            values.add(data.get(i).get("Name").toString());
            values.add(data.get(i).get("Building").toString());
            values.add(data.get(i).get(day).toString());
        }

        GridView myGrid = (GridView) findViewById(R.id.grid);
        mContext = getApplicationContext();
        //create array adapter with layout from cell.xml populated with values array
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                mContext,
                R.layout.cell,values
        ){
            @Override
            //Create formatted Gridview
            public View getView(int position, View convertView, ViewGroup parent){
                TextView tv_cell = (TextView) super.getView(position,convertView,parent);
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = size.y;
                int header = (height / 15);
                if(position <3){
                    tv_cell.setBackground(ContextCompat.getDrawable(mContext,R.drawable.gridview_item_header));
                    tv_cell.setGravity(Gravity.CENTER);
                    tv_cell.getLayoutParams().height = header;//Header row height
                }else{
                    tv_cell.setBackground(ContextCompat.getDrawable(mContext,R.drawable.gridview_item_background));
                    tv_cell.getLayoutParams().height = (height - header) / 7;//data row height
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
    }
    public void onClick(View v) {    }
    public void act(int position, String content) {
        if (position == 21 || position == 22 || position == 23) {
            Intent intent = new Intent(this, Dining.class);
            startActivity(intent);
        } else if (position % 3 == 0) { //Name
            loadName(content);
        } else if (position % 3 == 1) { //Map
            loadMap(content);
        }
    }
    public void loadMap(String building){
        for (int i = 0; i < data.size(); i++) {
            for(int x = 0; x < data.get(i).size(); x++) {
                if (data.get(i).containsValue(building)) {
                    String label = "Here!";
                    String uriBegin = "geo:" + data.get(i).get("Geo").toString();
                    String query = data.get(i).get("Geo").toString()+ "(" + label + ")";
                    String encodedQuery = Uri.encode(query);
                    String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                    Uri uri4 = Uri.parse(uriString);
                    Intent i4 = new Intent(Intent.ACTION_VIEW, uri4);
                    startActivity(i4);
                }
            }
        }
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
