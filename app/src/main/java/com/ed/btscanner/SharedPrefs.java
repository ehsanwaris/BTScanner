package com.ed.btscanner;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPrefs {
    SharedPreferences sp;


    Editor e;

    String FileName = "btscanner";
    String testName, deviceName, distance, orientation, macAddress;


    SharedPrefs(Context context) {
        sp = context.getSharedPreferences(FileName, 0);
    }

    public String getTestName() {
        this.testName = sp.getString("testName", "");
        return this.testName;
    }

    public void setTestName(String testName) {
        e = sp.edit();
        e.putString("testName", testName);
        e.commit();
        this.testName = testName;
    }

    public String getDeviceName() {
        return sp.getString("deviceName", "");

    }

    public void setDeviceName(String deviceName) {
        e = sp.edit();
        e.putString("deviceName", deviceName);
        e.commit();
        this.deviceName = deviceName;
    }

	public String getDistance() {
		return sp.getString("distance", "");
	}

	public void setDistance(String distance) {
		e = sp.edit();
		e.putString("distance", distance);
		e.commit();
		this.distance = distance;
	}

	public String getOrientation() {
		return sp.getString("orientation", "");
	}

	public void setOrientation(String orientation) {
		e = sp.edit();
		e.putString("orientation", orientation);
		e.commit();
		this.orientation = orientation;
	}

	public String getMacAddress() {
		return sp.getString("macAddress", "");
	}

	public void setMacAddress(String macAddress) {
		e = sp.edit();
		e.putString("macAddress", macAddress);
		e.commit();
		this.macAddress = macAddress;
	}
}