package com.ed.btscanner;

public class RssiToMeters {
    public static double ConvertRssiToMeters(int rssi){
       // double distance = 0;
        if(rssi>-45){
            return 0;
        }
        else if(-45>rssi && rssi >=-47){
            return 1;
        }
        else if(-47>rssi && rssi >=-51){
            return 2;
        }
        else if(-51>rssi && rssi >=-54){
            return 3;
        }
        else if(-54>rssi && rssi >=-58){
            return 4;
        }
        else if(-58>rssi && rssi >=-61){
            return 5;
        }
        else if(-61>rssi && rssi >=-68){
            return 6;
        }
        else if(-68>rssi && rssi >=-71){
            return 7;
        }
        else if(-71>rssi && rssi >=-76){
            return 8;
        }
        else if(-76>rssi && rssi >=-80){
            return 9;
        }
        else if(-80>rssi ){
            return 10;
        }else {
            return 0;
        }
    }
}
