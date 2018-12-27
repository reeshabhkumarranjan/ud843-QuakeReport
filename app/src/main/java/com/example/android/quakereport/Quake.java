package com.example.android.quakereport;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Quake {
    private double magnitude;
    private String location;
    private String baseLocation;
    private String locationOffset;
    private Date date;
    private String url;

    public Quake(double magnitude, String location, Long time, String url) {
        this.magnitude = magnitude;
        this.location = location;
        this.date=new Date();
        date.setTime(time);
        this.url=url;

        if(location.contains("km")){
            int separatorPosition=location.indexOf(" of ");
            separatorPosition=separatorPosition+4;
            locationOffset=location.substring(0,separatorPosition);
            baseLocation=location.substring(separatorPosition);
        }

        else{
            locationOffset="Near";
            baseLocation=location;
        }
    }

    public double getMagnitude() {
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

    public String getSimpleTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("h:mm a");
        return simpleDateFormat.format(date);
    }

    public String getBaseLocation() {
        return baseLocation;
    }

    public String getLocationOffset() {
        return locationOffset;
    }

    public String getSimpleMagnitude() {
        DecimalFormat decimalFormat=new DecimalFormat("0.0");
        return decimalFormat.format(magnitude);
    }

    public String getUrl() {
        return url;
    }
}
