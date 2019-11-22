package com.ry.libreflect.hook;

import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by ruiyi on 2019/11/8.
 */

public class HookOnClick {

    public void hookClick(View v) {
        try {
            Class<?> viewClass = Class.forName("android.view.View");
            Method getListenerInfo = viewClass.getDeclaredMethod("getListenerInfo");
            getListenerInfo.setAccessible(true);
            Object listInfo = getListenerInfo.invoke(v);

            Class<?> listenerInfoClass = Class.forName("android.view.View$ListenerInfo");
            Field mOnClickListener = listenerInfoClass.getDeclaredField("mOnClickListener");
            mOnClickListener.setAccessible(true);
            View.OnClickListener onClickListener = (View.OnClickListener) mOnClickListener.get(listInfo);
            ProxyClick proxyClick = new ProxyClick(onClickListener);

            mOnClickListener.set(listInfo, proxyClick);
        } catch (Exception e) {

        }
    }

    class ProxyClick implements View.OnClickListener {

        private View.OnClickListener origin;

        ProxyClick(View.OnClickListener origin) {
            this.origin = origin;
        }

        @Override
        public void onClick(View v) {
            Log.e("eee", "e");
            origin.onClick(v);
        }
    }
}
