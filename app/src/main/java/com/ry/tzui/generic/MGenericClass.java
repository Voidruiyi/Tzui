package com.ry.tzui.generic;

/**
 * Created by ruiyi on 2019/12/2.
 */

public class MGenericClass<T, K> {

    public T t;
    public K k;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }
}

class Test_1{
    public void test(){
        MGenericClass<Integer, String> m = new MGenericClass();

        String k = m.getK();
        Integer t = m.getT();
    }

}
