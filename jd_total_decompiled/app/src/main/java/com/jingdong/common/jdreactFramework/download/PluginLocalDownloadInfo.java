package com.jingdong.common.jdreactFramework.download;

/* loaded from: classes5.dex */
public class PluginLocalDownloadInfo {
    public String commonVersion;
    public boolean full;
    public String status;

    public PluginLocalDownloadInfo(String str, boolean z, String str2) {
        this.status = str;
        this.full = z;
        this.commonVersion = str2;
    }

    public String toString() {
        return "PluginLocalDownloadInfo{status='" + this.status + "', full=" + this.full + ", commonVersion='" + this.commonVersion + "'}";
    }

    public PluginLocalDownloadInfo(String str) {
        this.status = str;
    }
}
