package com.ry.tzui.link;

public interface Interceptor {

    String intercept(Chain chain) ;


    interface Chain{
        String proceed(String request);

        String request();
    }
}
