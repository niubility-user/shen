package com.tencent.mapsdk.shell.events;

import androidx.annotation.Keep;

@Keep
/* loaded from: classes9.dex */
public class NetFlowEventModel {
    public String bizType;
    public double downloadFlow;
    public long downloadTime;
    public int errorCode;
    public final String eventCode = "cm_nf";
    public double uploadFlow;
    public long uploadTime;
    public String url;

    public NetFlowEventModel() {
    }

    public NetFlowEventModel(double d, double d2, long j2, long j3, int i2, String str, String str2) {
        this.uploadFlow = d;
        this.downloadFlow = d2;
        this.uploadTime = j2;
        this.downloadTime = j3;
        this.errorCode = i2;
        this.url = str;
        this.bizType = str2;
    }
}
