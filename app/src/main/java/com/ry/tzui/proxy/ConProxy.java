package com.ry.tzui.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by ruiyi on 2019/12/12.
 */

public class ConProxy implements InvocationHandler {

    private Object subject;

    public ConProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e("eee", "before");
        method.invoke(proxy, args);
        Log.e("eee", "after");

        return null;
    }
}
