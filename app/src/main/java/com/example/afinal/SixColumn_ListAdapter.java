package com.example.afinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SixColumn_ListAdapter extends ArrayAdapter<Event> {

    private LayoutInflater mInflater;
    private ArrayList<Event> events;
    private int mViewResourceId;

    public SixColumn_ListAdapter(Context context, int textViewResourceId,ArrayList<Event> events) {
        super(context,textViewResourceId,events);
        this.events = events;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parents) {
        convertView = mInflater.inflate(mViewResourceId,null);

        Event event = events.get(position);

        if (event != null) {
            TextView eventName = (TextView) convertView.findViewById(R.id.textEventName);
            TextView eventLocation = (TextView) convertView.findViewById(R.id.textEventLocation);
            TextView eventDate = (TextView) convertView.findViewById(R.id.textEventDate);
            TextView eventStartTime = (TextView) convertView.findViewById(R.id.textEventStartTime);
            TextView eventEndTime = (TextView) convertView.findViewById(R.id.textEventEndTime);
            TextView eventDescription = (TextView) convertView.findViewById(R.id.textEventDescription);

            if (eventName != null) {
                eventName.setText((event.getEventName()));
            }

            if (eventLocation != null) {
                eventLocation.setText((event.getEventLocation()));
            }

            if (eventDate != null) {
                eventDate.setText((event.getEventDate()));
            }

            if (eventStartTime != null) {
                eventStartTime.setText((event.getEventStartTime()));
            }

            if (eventEndTime != null) {
                eventEndTime.setText((event.getEventEndTime()));
            }

            if (eventDescription != null) {
                eventDescription.setText((event.getEventDescription()));
            }
        }
        return convertView;
    }
}
