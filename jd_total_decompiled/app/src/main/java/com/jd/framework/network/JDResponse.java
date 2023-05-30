package com.jd.framework.network;

import java.util.Map;

/* loaded from: classes13.dex */
public class JDResponse<T> {
    private T data;
    private Map<String, String> headers;
    private boolean isCache;
    private int statusCode;

    public JDResponse() {
    }

    public T getData() {
        return this.data;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public boolean isCache() {
        return this.isCache;
    }

    public void setCache(boolean z) {
        this.isCache = z;
    }

    public void setData(T t) {
        this.data = t;
    }

    public void setHeaders(Map<String, String> map) {
        this.headers = map;
    }

    public void setStatusCode(int i2) {
        this.statusCode = i2;
    }

    public JDResponse(int i2, boolean z, T t, Map<String, String> map) {
        this.isCache = z;
        this.data = t;
        this.headers = map;
        this.statusCode = i2;
    }
}
