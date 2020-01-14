package com.ry.tzui.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by ruiyi on 2019/12/12.
 */

public class TestUtls {

    public static void test() {
        SubjectImp subjectImp = new SubjectImp();
        ConProxy proxy = new ConProxy(subjectImp);

        ISubject subject = (ISubject) Proxy.newProxyInstance(proxy.getClass().getClassLoader(),
                subjectImp.getClass().getInterfaces(),
                proxy);

        subject.doTest();
    }

    public static void test2() {
        SubjectImp subjectImp = new SubjectImp();
//        ConProxy proxy = new ConProxy(subjectImp);
        ISubject subject = (ISubject) Proxy.newProxyInstance(subjectImp.getClass().getClassLoader(),
                subjectImp.getClass().getInterfaces(),
                new ConProxy(subjectImp));

        subject.doTest();
    }
}
