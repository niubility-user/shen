package com.jdcloud.media.common.bean;

import java.io.Serializable;
import java.util.Map;

/* loaded from: classes18.dex */
public class LogData implements Serializable {
    private String track;
    private Map xdata;
    private String p = "OUT";
    private String v = "1.0.0";
    private long time = System.currentTimeMillis();

    public String getP() {
        return this.p;
    }

    public long getTime() {
        return this.time;
    }

    public String getTrack() {
        return this.track;
    }

    public String getV() {
        return this.v;
    }

    public Map getXdata() {
        return this.xdata;
    }

    public void setP(String str) {
        this.p = str;
    }

    public void setTime(long j2) {
        this.time = j2;
    }

    public void setTrack(String str) {
        this.track = str;
    }

    public void setV(String str) {
        this.v = str;
    }

    public void setXdata(Map map) {
        this.xdata = map;
    }
}
