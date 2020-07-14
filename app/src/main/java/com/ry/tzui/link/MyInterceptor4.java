package com.ry.tzui.link;

import android.util.Log;


public class MyInterceptor4 implements Interceptor {

    @Override
    public String intercept(Chain chain) {

        String reponse =  chain.request()+"end;";
        return reponse;
    }
}
