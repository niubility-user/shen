package com.tencent.smtt.export.external.interfaces;

/* loaded from: classes9.dex */
public interface DownloadListener {
    void onDownloadStart(String str, String str2, String str3, String str4, long j2);

    void onDownloadStart(String str, String str2, byte[] bArr, String str3, String str4, String str5, long j2, String str6, String str7);

    void onDownloadVideo(String str, long j2, int i2);
}
