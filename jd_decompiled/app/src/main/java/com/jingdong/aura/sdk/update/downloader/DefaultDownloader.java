package com.jingdong.aura.sdk.update.downloader;

import android.content.Context;
import com.jingdong.aura.sdk.network.http.AuraHttp;
import com.jingdong.aura.sdk.network.http.InitializationConfig;
import com.jingdong.aura.sdk.network.http.dowmload.DownloadListener;
import com.jingdong.aura.sdk.network.http.dowmload.DownloadQueue;
import com.jingdong.aura.sdk.network.http.dowmload.DownloadRequest;
import com.jingdong.aura.sdk.network.http.rest.Headers;
import com.jingdong.aura.sdk.network.http.rest.RequestMethod;
import com.jingdong.aura.sdk.network.http.rest.URLConnectionNetworkExecutor;
import com.jingdong.aura.sdk.network.utils.Logger;
import com.jingdong.aura.sdk.update.AuraBundleResult;
import com.jingdong.aura.sdk.update.b.c;
import java.util.List;

/* loaded from: classes4.dex */
public class DefaultDownloader implements IDownloader {
    private static String TAG = "DefaultDownloader";
    private DownloadQueue downloadQueue;

    public DefaultDownloader(Context context) {
        this(context, false);
    }

    public DefaultDownloader(Context context, boolean z) {
        AuraHttp.initialize(InitializationConfig.newBuilder(context).networkExecutor(new URLConnectionNetworkExecutor()).build());
        Logger.setDebug(z);
        this.downloadQueue = AuraHttp.newDownloadQueue();
    }

    @Override // com.jingdong.aura.sdk.update.downloader.IDownloader
    public boolean cancelAll(List<AuraBundleResult> list) {
        this.downloadQueue.cancelAll();
        return true;
    }

    @Override // com.jingdong.aura.sdk.update.downloader.IDownloader
    public boolean cancelBySign(AuraBundleResult auraBundleResult) {
        this.downloadQueue.cancelAll();
        return true;
    }

    @Override // com.jingdong.aura.sdk.update.downloader.IDownloader
    public void download(AuraBundleResult auraBundleResult, final IDownloadCallback iDownloadCallback) {
        if (auraBundleResult != null) {
            this.downloadQueue.add(0, new DownloadRequest(auraBundleResult.downloadUrl, RequestMethod.GET, com.jingdong.aura.sdk.update.a.a().f12241k.getAbsolutePath(), auraBundleResult.downloadedFileName, true, true), new DownloadListener() { // from class: com.jingdong.aura.sdk.update.downloader.DefaultDownloader.1
                public final void onCancel(int i2) {
                    iDownloadCallback.onCanceled();
                }

                public final void onDownloadError(int i2, Exception exc) {
                    iDownloadCallback.onError(exc);
                }

                public final void onFinish(int i2, String str) {
                    iDownloadCallback.onFinish();
                }

                public final void onProgress(int i2, int i3, long j2, long j3) {
                    iDownloadCallback.onProgress(j2);
                }

                public final void onStart(int i2, boolean z, long j2, Headers headers, long j3) {
                    iDownloadCallback.onStart();
                }
            });
            return;
        }
        c.b(TAG, "bundleResult is null");
        if (iDownloadCallback != null) {
            iDownloadCallback.onError(new IllegalArgumentException("bundleResult is null"));
        }
    }
}
