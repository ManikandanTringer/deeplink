package com.example.pratice;

import java.util.HashMap;

public class HttpCall {

    public static final int GET=1;
    public static final int POST=2;

    private String url;
    private int methodType;
    private HashMap<String,String> params;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getMethodType() {
        return methodType;
    }

    public void setMethodType(int methodType) {
        this.methodType = methodType;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

//    public HttpCall(String url, int methodType, HashMap<String, String> params) {
//        this.url = url;
//        this.methodType = methodType;
//        this.params = params;
//    }
}
