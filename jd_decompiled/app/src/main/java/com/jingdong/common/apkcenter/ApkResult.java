package com.jingdong.common.apkcenter;

import com.jingdong.common.apkcenter.ApkCenter;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class ApkResult {
    static final int BUNDLE_TYPE_PROVIDED = 2;
    static final int BUNDLE_TYPE_WITHLOADED = 1;
    public static final int STATUS_COMPLETED = 1;
    public static final int STATUS_DOWNLOADING = 2;
    public static final int STATUS_NO_DOWNLOAD = 0;
    private static final String TAG = "ApkResult";
    public int bundleVersionCode;
    public int currentSize;
    public String downloadUrl;
    public String fileName;
    public String hostVersionCode;
    public String hostVersionName;
    public String id;
    public String localPath;
    public String md5;
    public int size;
    public int status;
    private ApkCenter.AuraBundleUpdateListener updateListener;
    public int downloadType = 1;
    public int downloadOrder = 0;
    public int bundleType = 1;

    public boolean equals(ApkResult apkResult) {
        try {
            if (this.id.equals(apkResult.id) && this.bundleVersionCode == apkResult.bundleVersionCode && this.size == apkResult.size && this.md5.equals(apkResult.md5)) {
                return this.downloadUrl.equals(apkResult.downloadUrl);
            }
            return false;
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return false;
        }
    }

    public ApkCenter.AuraBundleUpdateListener getUpdateListener() {
        return this.updateListener;
    }

    public void setUpdateListener(ApkCenter.AuraBundleUpdateListener auraBundleUpdateListener) {
        this.updateListener = auraBundleUpdateListener;
    }
}
