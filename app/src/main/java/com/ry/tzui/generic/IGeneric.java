package com.ry.tzui.generic;

/**
 * Created by ruiyi on 2019/12/2.
 */

public interface IGeneric<T,K> {
    T getT();
    void setK(K k);
}

class ImpGeneric<T, K> implements IGeneric<T, K>{

    @Override
    public T getT() {
        return null;
    }

    @Override
    public void setK(K o) {

    }
}

class ImpGeneric2 implements IGeneric<Integer, String>{

    @Override
    public Integer getT() {
        return null;
    }

    @Override
    public void setK(String s) {

    }
}

class Test{
    public void test(){
        ImpGeneric<String, Integer> imp = new ImpGeneric();
        String t = imp.getT();
        imp.setK(2);

        ImpGeneric2 imp2 = new ImpGeneric2();
        imp2.setK("s");
        Integer t1 = imp2.getT();

    }

}
