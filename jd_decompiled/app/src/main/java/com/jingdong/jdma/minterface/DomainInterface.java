package com.jingdong.jdma.minterface;

/* loaded from: classes12.dex */
public class DomainInterface {
    private String mH5Url;
    private String mReportDomain;
    private String mStrategyDomain;

    public DomainInterface(String str, String str2) {
        this.mStrategyDomain = str;
        this.mReportDomain = str2;
    }

    public String getH5Url() {
        return this.mH5Url;
    }

    public String getReportDomain() {
        return this.mReportDomain;
    }

    public String getStrategyDomain() {
        return this.mStrategyDomain;
    }

    public DomainInterface(String str, String str2, String str3) {
        this.mStrategyDomain = str;
        this.mReportDomain = str2;
        this.mH5Url = str3;
    }
}
