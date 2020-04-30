package com.example.afinal;

import java.util.Random;

public class Event {

    private String eventName;
    private String eventLocation;
    private String eventDate;
    private String eventStartTime;
    private String eventEndTime;
    private String eventDescription;
    private String eventID;

    public String getEventID(){return eventID;}
    public void setEventID(String eventID) {this.eventID = eventID;}

    public String getEventName(){return eventName;}
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation(){return eventLocation;}
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDate(){return eventDate;}
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventStartTime(){return eventStartTime;}
    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEventEndTime(){return eventEndTime;}
    public void setEventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String getEventDescription(){return eventDescription;}
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Event(String eventName, String eventLocation, String eventDate, String eventStartTime, String eventEndTime, String eventDescription, String eventID) {
        super();
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventDate = eventDate;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventDescription = eventDescription;
        this.eventID = eventID;
    }

}
