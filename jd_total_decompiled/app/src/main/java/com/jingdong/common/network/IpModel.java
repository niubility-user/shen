package com.jingdong.common.network;

/* loaded from: classes5.dex */
public final class IpModel {
    public static final float TTL_RATIO = 0.75f;
    private String host;
    private String master;
    public String ttl;
    public String updateTime;
    private String[] v4Backup;
    private String[] v6Backup;

    public IpModel(String str, String str2, String[] strArr, String[] strArr2) {
        this.host = str;
        this.master = str2;
        this.v4Backup = strArr;
        this.v6Backup = strArr2;
    }

    public String getHost() {
        return this.host;
    }

    public String getMaster() {
        return this.master;
    }

    public String[] getV4Backup() {
        return this.v4Backup;
    }

    public String[] getV6Backup() {
        return this.v6Backup;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setMaster(String str) {
        this.master = str;
    }

    public void setV4Backup(String[] strArr) {
        this.v4Backup = strArr;
    }

    public void setV6Backup(String[] strArr) {
        this.v6Backup = strArr;
    }

    public IpModel(String str, String str2, String[] strArr, String[] strArr2, String str3) {
        this.host = str;
        this.master = str2;
        this.v4Backup = strArr;
        this.v6Backup = strArr2;
        this.updateTime = str3;
    }

    public IpModel(String str, String str2, String[] strArr, String[] strArr2, String str3, String str4) {
        this.host = str;
        this.master = str2;
        this.v4Backup = strArr;
        this.v6Backup = strArr2;
        this.updateTime = str3;
        this.ttl = str4;
    }
}
