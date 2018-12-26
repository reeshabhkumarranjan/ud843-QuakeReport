package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Quake {
    private float magnitude;
    private String location;
    private Date date;

    public Quake(float magnitude, String location, Long time) {
        this.magnitude = magnitude;
        this.location = location;
        this.date=new Date();
        date.setTime(time);
    }

    public float getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public String getSimpleDate(){
        //Reference: https://www.javatpoint.com/java-simpledateformat
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String simpleDate=simpleDateFormat.format(this.date);
        return simpleDate;
    }
}
