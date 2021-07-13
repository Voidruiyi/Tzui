package com.ry.tzui;

import android.app.Application;

/**
 * author:ruiyi
 * creatTime:2021/2/3
 * Describe:
 */
public class MyApplication extends Application {

    public static long[] time = new long[2];

    @Override
    public void onCreate() {
        super.onCreate();
        time[0] = System.currentTimeMillis() / 1000L;
    }

    public static long getInTime(){
        return time[0];
    }

    public static void setInTime(){
        time[0] = System.currentTimeMillis() / 1000L;
    }
}
