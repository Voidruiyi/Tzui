package com.ry.libreflect.hook;

import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by ruiyi on 2019/11/7.
 */

public class IRuiManagerProxy implements InvocationHandler {
    private Object mRuiManager;

    public IRuiManagerProxy(Object ruiManager) {
        this.mRuiManager = ruiManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (TextUtils.equals(method.getName(), "logInfo")) {
            Object result = method.invoke(mRuiManager, args);
            if (result instanceof RuiManager) {
                //在这里hook LocationManager
                HookHelper.hookRuiManager((RuiManager) result);
                Log.e("eee", "ee");
            }
            return result;
        }

        return method.invoke(mRuiManager, args);
    }
}
