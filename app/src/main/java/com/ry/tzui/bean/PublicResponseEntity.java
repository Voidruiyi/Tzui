package com.ry.tzui.bean;

import java.io.Serializable;

public class PublicResponseEntity<T> implements Serializable {

    //errorCode = 0 代表执行成功，不建议依赖任何非0的 errorCode.
    //errorCode = -1001 代表登录失效，需要重新登录。
    private int errorCode;
    private T   data;
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "PublicResponseEntity{" +
                "errorCode=" + errorCode +
                ", data=" + data +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
