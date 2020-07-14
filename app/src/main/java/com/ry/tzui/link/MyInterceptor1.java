package com.ry.tzui.link;

import android.util.Log;


public class MyInterceptor1 implements Interceptor {

    @Override
    public String intercept(Chain chain) {
        String request = chain.request();
        request = request+"1;";
        Log.e("eee","MyInterceptor1 intercept="+request);
        String reponse =  chain.proceed(request)+"e1;";
        return reponse;
    }
}
