package com.jd.dynamic.entity;

import java.util.Map;

/* loaded from: classes13.dex */
public class RequestEntity {
    private String body;
    private String businessType;
    private String functionId;
    private Map<String, String> headers;
    private String host;
    private String method;
    private String showLoading;
    private String url;

    public String getBody() {
        return this.body;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public String getFunctionId() {
        return this.functionId;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public String getHost() {
        return this.host;
    }

    public String getMethod() {
        return this.method;
    }

    public String getShowLoading() {
        return this.showLoading;
    }

    public String getUrl() {
        return this.url;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public void setBusinessType(String str) {
        this.businessType = str;
    }

    public void setFunctionId(String str) {
        this.functionId = str;
    }

    public void setHeaders(Map<String, String> map) {
        this.headers = map;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public void setShowLoading(String str) {
        this.showLoading = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String toString() {
        return "RequestEntity{url='" + this.url + "', functionId='" + this.functionId + "', host='" + this.host + "', headers=" + this.headers + ", method='" + this.method + "', body='" + this.body + "', businessType='" + this.businessType + "', showLoading='" + this.showLoading + "'}";
    }
}
