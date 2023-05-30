package com.jdpaysdk.freechargesdk.entity;

import java.io.Serializable;

/* loaded from: classes18.dex */
public class Request implements Serializable {
    private String model;
    private String name;
    private String request;

    public String getModel() {
        return this.model;
    }

    public String getName() {
        return this.name;
    }

    public String getRequest() {
        return this.request;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setRequest(String str) {
        this.request = str;
    }
}
