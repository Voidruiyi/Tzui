package com.ry.tzui;

import java.text.DecimalFormat;

/**
 * @author ruiyi
 */
public class TestUtils {

    /**
     * tod
     * @return
     */
    public static String[] showTodTime(long tEnd) {
        String[] str = new String[2];

        long now = System.currentTimeMillis() / 1000L;
        long limit = tEnd - now;
        str[0] = limit + "";

        long all = tEnd - MyApplication.getInTime();
        long thrw = now - MyApplication.getInTime();

        double rate =(thrw * 100f) / all;
        double rate1 =100 - rate;

        DecimalFormat format = new DecimalFormat("0.000");

        str[1] = thrw + "\n-------- " + format.format(rate1)+"%\n-------- "+format.format(rate)+"%";

        return str;
    }

    public static void updateStart(){
        MyApplication.setInTime();
    }
}
