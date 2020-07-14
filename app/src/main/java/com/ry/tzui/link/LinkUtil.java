package com.ry.tzui.link;

import java.util.ArrayList;
import java.util.List;

public class LinkUtil {

    public static String test(){
         List<Interceptor> interceptors = new ArrayList<>();
         interceptors.add(new MyInterceptor1());
         interceptors.add(new MyInterceptor2());
         interceptors.add(new MyInterceptor3());
         interceptors.add(new MyInterceptor4());

        Interceptor.Chain chain = new RealInterceptorChain(interceptors,0, "test");

        return chain.proceed("start");

    }


}
