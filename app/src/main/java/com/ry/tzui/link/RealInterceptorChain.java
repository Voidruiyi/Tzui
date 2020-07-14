package com.ry.tzui.link;

import android.util.Log;
import java.util.List;

public class RealInterceptorChain implements Interceptor.Chain {
    private final List<Interceptor> interceptors;
    private final int index;
    private String request;


    public RealInterceptorChain(List<Interceptor> interceptors, int index, String request) {
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
    }

    @Override
    public String proceed(String request){
        if (index >= interceptors.size()){
            Log.e("eee", "oversize");
            return "ruiyi=";
        }

//        Log.e("eee", "RealInterceptorChain request="+request);

        RealInterceptorChain next = new RealInterceptorChain(interceptors, index + 1, request);
        Interceptor interceptor = interceptors.get(index);
        String response = interceptor.intercept(next);

        return response;
    }

    @Override
    public String request() {
        return request;
    }
}
