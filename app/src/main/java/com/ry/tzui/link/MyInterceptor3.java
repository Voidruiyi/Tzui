package com.ry.tzui.link;

import android.util.Log;


public class MyInterceptor3 implements Interceptor {

    @Override
    public String intercept(Chain chain)  {
        String request = chain.request();
        request = request+"3;";
        Log.e("eee","MyInterceptor3 intercept="+request);
        String reponse =  chain.proceed(request)+"e3";
        return reponse;
    }
}
