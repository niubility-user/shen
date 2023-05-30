package com.jingdong.common.apkcenter;

import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import java.util.List;

/* loaded from: classes5.dex */
public class ApkCenter {

    /* loaded from: classes5.dex */
    public interface ApkListener {
        void onDownloadProgressChanged(int i2);

        void onFailure(String str);

        void onFinish(ApkResult apkResult);
    }

    /* loaded from: classes5.dex */
    public interface AuraBundleUpdateListener {
        void onDownloadFailure(Exception exc);

        void onDownloadFinish();

        void onDownloadProgressChanged(int i2, int i3);

        void onInstallFinish(boolean z);

        void onInstallStart();

        void onPause(boolean z);

        void onStart();
    }

    public static void deleteDownloadCache(String str) {
        ApkDownloadController.getInStance().deleteBundleCache(str);
    }

    public static void downloadAllApks() {
        ApkDownloadController.getInStance().breakPointDownload();
    }

    public static void insetProvidedBundleInfoToDB() {
        ApkDownloadController.getInStance().insetProvidedBundleInfoToDB();
    }

    public static List<ApkResult> queryApkResults() {
        return ApkDownloadTable.queryApks();
    }

    public static void registerApkListener(String str, ApkListener apkListener) {
        ApkDownloadController.getInStance().addListenerMap(str, apkListener);
    }

    public static void requestApkList(HttpGroup.OnCommonListener onCommonListener) {
        ApkDownloadController.getInStance().reqeustDownLoadList(onCommonListener);
    }
}
