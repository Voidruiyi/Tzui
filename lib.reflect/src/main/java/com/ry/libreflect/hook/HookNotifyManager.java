package com.ry.libreflect.hook;

import android.app.NotificationManager;
import android.content.Context;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by ruiyi on 2019/11/8.
 */

public class HookNotifyManager {

    public void hookNotifyManager(Context context){
        try {
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            //--------- 为了sService 实例化
            Method getService = NotificationManager.class.getDeclaredMethod("getService");
            getService.setAccessible(true);
            final Object sService = getService.invoke(notificationManager);
            //---------

            Class iNotiMngClz = Class.forName("android.app.INotificationManager");
            Object proxyNotiMng = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{iNotiMngClz}, new InvocationHandler() {

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return null;
                }
            });
            Field sServiceField = NotificationManager.class.getDeclaredField("sService");
            sServiceField.setAccessible(true);
            sServiceField.set(notificationManager, proxyNotiMng);
        }catch (Exception e){}
    }
}
