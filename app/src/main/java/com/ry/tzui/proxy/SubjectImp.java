package com.ry.tzui.proxy;

import android.util.Log;

/**
 * Created by ruiyi on 2019/12/12.
 */

public class SubjectImp implements ISubject {
    @Override
    public void doTest() {
        Log.e("eee", "impl:doTest()");
    }
}
