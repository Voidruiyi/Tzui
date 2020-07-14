package com.ry.tzui.link;

import android.util.Log;


public class MyInterceptor2 implements Interceptor {

    @Override
    public String intercept(Chain chain) {
        String request = chain.request();
        request = request+"2;";
        Log.e("eee","MyInterceptor2 intercept="+request);
        String reponse =  chain.proceed(request)+"e2";
        return reponse;
    }
}
