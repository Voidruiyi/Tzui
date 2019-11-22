package com.ry.libreflect.hook;

import java.lang.reflect.Proxy;

/**
 * Created by ruiyi on 2019/11/7.
 */

public class HookHelper {

    public static void hookRuiManager(RuiManager manager) {
        try {
            Object testHook = null;

            Class<?> ruiManagerClass = Class.forName("com.ry.libt1.RuiManager");
//            ruiManagerClass.getm
//            testHook = getF

            Object proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    new Class<?>[]{ruiManagerClass}, new IRuiManagerProxy(testHook));


        } catch (Exception e) {

        }

    }
}
