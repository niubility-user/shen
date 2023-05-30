package com.jingdong.aura.sdk.update;

import com.jingdong.aura.sdk.update.updater.IUpdateListener;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class AuraBundleResult implements Serializable, Comparable<AuraBundleResult> {
    public static final int BUNDLE_TYPE_PROVIDED = 1;
    public static final int BUNDLE_TYPE_UPDATE = 2;
    public static final int DOWNLOAD_TYPE_FORCE_UPDATE = 2;
    public static final int DOWNLOAD_TYPE_NOTAUTO_UPDATE = 0;
    public static final int DOWNLOAD_TYPE_WIFI_UPDATE = 1;
    private static final String TAG = "AuraBundleResult";
    private static final long serialVersionUID = 1720758801969137994L;
    public int bundleType;
    public int bundleVersionCode;
    public int downLoadFrom;
    public int downloadOrder;
    public int downloadType;
    public String downloadUrl;
    public String downloadedFileName;
    public String downloadedFilePath;
    public long downloadedSize;
    public boolean isFromProvidedPage;
    public boolean isValid;
    public String md5;
    public long size;
    public String updateId;
    public transient IUpdateListener updateListener;

    public AuraBundleResult() {
        this.downloadType = 1;
        this.downloadOrder = 0;
        this.bundleType = 2;
        this.isFromProvidedPage = false;
        this.isValid = true;
        this.downLoadFrom = 0;
    }

    public AuraBundleResult(AuraBundleResult auraBundleResult) {
        this.downloadType = 1;
        this.downloadOrder = 0;
        this.bundleType = 2;
        this.isFromProvidedPage = false;
        this.isValid = true;
        this.downLoadFrom = 0;
        this.updateId = auraBundleResult.updateId;
        this.bundleVersionCode = auraBundleResult.bundleVersionCode;
        this.size = auraBundleResult.size;
        this.downloadedSize = auraBundleResult.downloadedSize;
        this.md5 = auraBundleResult.md5;
        this.downloadedFilePath = auraBundleResult.downloadedFilePath;
        this.downloadedFileName = auraBundleResult.downloadedFileName;
        this.downloadUrl = auraBundleResult.downloadUrl;
        this.downloadType = auraBundleResult.downloadType;
        this.downloadOrder = auraBundleResult.downloadOrder;
        this.bundleType = auraBundleResult.bundleType;
        this.updateListener = auraBundleResult.updateListener;
        this.isValid = auraBundleResult.isValid;
        this.isFromProvidedPage = auraBundleResult.isFromProvidedPage;
        this.downLoadFrom = auraBundleResult.downLoadFrom;
    }

    @Override // java.lang.Comparable
    public int compareTo(AuraBundleResult auraBundleResult) {
        int i2 = this.downloadType;
        int i3 = auraBundleResult.downloadType;
        if (i2 < i3) {
            return 1;
        }
        if (i2 > i3) {
            return -1;
        }
        return this.downloadOrder - auraBundleResult.downloadOrder;
    }

    public boolean equals(AuraBundleResult auraBundleResult) {
        try {
            if (this.updateId.equals(auraBundleResult.updateId) && this.bundleVersionCode == auraBundleResult.bundleVersionCode && this.size == auraBundleResult.size && this.md5.equals(auraBundleResult.md5)) {
                return this.downloadUrl.equals(auraBundleResult.downloadUrl);
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public String getDownloadTypeStr() {
        int i2 = this.downloadType;
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? "unknown" : "\u5f3a\u5236\u4e0b\u8f7d\uff0cwifi\u4e0e\u6d41\u91cf\u73af\u5883\u91cc\u90fd\u4e0b\u8f7d" : "\u9759\u9ed8\u4e0b\u8f7d\uff0c\u4ec5\u4ec5\u5728wifi\u60c5\u51b5\u4e0b\u4e0b\u8f7d" : "wifi\u548c\u6d41\u91cf\u60c5\u51b5\u4e0b\u90fd\u4e0d\u4e0b\u8f7d";
    }

    public String toString() {
        return "updateid:" + this.updateId + ",md5:" + this.md5 + ",bundleVersionCode:" + this.bundleVersionCode + ",downloadUrl:" + this.downloadUrl + ",updateListener:" + this.updateListener;
    }
}
