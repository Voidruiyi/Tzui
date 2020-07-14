package com.ry.tzui.util;

import android.text.TextUtils;
import android.util.Log;

public class LogUtil {

    private static final int OVER_SIZE=150;
    private static final String TAG="Tzui";

    public static void logI(String tag,String msg){
        if (TextUtils.isEmpty(msg)){
            Log.i(TAG,"NULL");
        }else {
            int startIndex=0;
            int endIndex=msg.length()>OVER_SIZE?OVER_SIZE:msg.length();
            while (endIndex<msg.length()+OVER_SIZE){
                Log.i(tag,msg.substring(startIndex,Math.min(endIndex,msg.length())));
                startIndex=endIndex;
                endIndex+=OVER_SIZE;
            }
        }
    }
}
