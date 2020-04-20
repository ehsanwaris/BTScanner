package com.ed.btscanner.database.model;

public class RSSIEntryModel {


    public  Integer id;
    public  String test_name;
    public  long time_stamp;
    public  Integer rssi_value;
    public  Double distance;
    public  String orientation ;
    public  String device_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public long getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(long time_stamp) {
        this.time_stamp = time_stamp;
    }

    public Integer getRssi_value() {
        return rssi_value;
    }

    public void setRssi_value(Integer rssi_value) {
        this.rssi_value = rssi_value;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }
}
