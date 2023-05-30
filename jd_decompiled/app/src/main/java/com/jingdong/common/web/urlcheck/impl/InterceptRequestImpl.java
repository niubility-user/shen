package com.jingdong.common.web.urlcheck.impl;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.jd.libs.hybrid.HybridOfflineLoader;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.util.CommonUtils;
import com.jd.libs.hybrid.offlineload.OfflineCallback;
import com.jd.libs.hybrid.offlineload.OfflineWebClient;
import com.jd.libs.hybrid.offlineload.entity.LocalFileType;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.xdog.b;
import com.jingdong.common.web.WebOfflineLoaderManager;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.managers.WebPerformanceHolder;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.ui.WebHybridLogView;
import com.jingdong.common.web.urlcheck.ICheckUrl;
import com.jingdong.common.web.urlcheck.IInterceptRequest;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes12.dex */
public class InterceptRequestImpl implements IInterceptRequest {
    private JDWebView jdWebView;
    private HybridOfflineLoader offlineLoader;
    private final AtomicBoolean offlineFileHit = new AtomicBoolean(false);
    private final AtomicBoolean sharedFileHit = new AtomicBoolean(false);
    public OnStartForInterceptRequest checkOnStart = new OnStartForInterceptRequest();
    public OnFinishForInterceptRequest checkOnFinish = new OnFinishForInterceptRequest();

    /* loaded from: classes12.dex */
    public class OnFinishForInterceptRequest implements ICheckUrl {
        public OnFinishForInterceptRequest() {
        }

        @Override // com.jingdong.common.web.urlcheck.ICheckUrl
        public boolean checkReturn() {
            return false;
        }

        @Override // com.jingdong.common.web.urlcheck.ICheckUrl
        public boolean checkUrl(WebView webView, String str) {
            if (InterceptRequestImpl.this.offlineLoader == null || TextUtils.isEmpty(str)) {
                return false;
            }
            InterceptRequestImpl.this.offlineLoader.onPageFinished(InterceptRequestImpl.this.jdWebView.getWebView(), str);
            return false;
        }

        @Override // com.jingdong.common.web.urlcheck.ICheckUrl
        public String getKey() {
            return "OnFinishForInterceptRequest";
        }
    }

    /* loaded from: classes12.dex */
    public class OnStartForInterceptRequest implements ICheckUrl {
        public OnStartForInterceptRequest() {
        }

        @Override // com.jingdong.common.web.urlcheck.ICheckUrl
        public boolean checkReturn() {
            return false;
        }

        @Override // com.jingdong.common.web.urlcheck.ICheckUrl
        public boolean checkUrl(WebView webView, String str) {
            if (InterceptRequestImpl.this.offlineLoader == null || TextUtils.isEmpty(str)) {
                return false;
            }
            InterceptRequestImpl.this.offlineLoader.onPageStarted(InterceptRequestImpl.this.jdWebView.getWebView(), str, null);
            return false;
        }

        @Override // com.jingdong.common.web.urlcheck.ICheckUrl
        public String getKey() {
            return "OnStartForInterceptRequest";
        }
    }

    public InterceptRequestImpl(final JDWebView jDWebView, String str) {
        this.jdWebView = jDWebView;
        HybridOfflineLoader fetchOfflineLoader = WebOfflineLoaderManager.fetchOfflineLoader(str);
        this.offlineLoader = fetchOfflineLoader;
        if (fetchOfflineLoader == null) {
            if (Log.D) {
                Log.d("JDHybrid", "InterceptRequestImpl: cannot find loader in global manager.");
                return;
            }
            return;
        }
        if (Log.D) {
            Log.d("JDHybrid", "InterceptRequestImpl: found a loader in global manager.");
        }
        this.offlineLoader.setLifeCycleOwner(jDWebView);
        this.offlineLoader.setCallback(new OfflineWebClient.Callback() { // from class: com.jingdong.common.web.urlcheck.impl.InterceptRequestImpl.1
            @Override // com.jd.libs.hybrid.offlineload.OfflineWebClient.Callback
            public void beforeReload(String str2) {
                InterceptRequestImpl.this.beforeReload(str2);
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineWebClient.Callback
            public void onFetchPreDownloadFile(int i2, long j2, long j3) {
                InterceptRequestImpl.this.onFetchPreDownloadFile(i2, j2, j3, null);
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineWebClient.Callback
            public void onFileHitMainPage(String str2, String str3, String str4) {
                InterceptRequestImpl.this.onFileHitMainPage(str2, str3, str4);
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineWebClient.Callback
            public void onFirstOfflinePageStarted(String str2, boolean z, boolean z2, String str3) {
                InterceptRequestImpl.this.onFirstOfflinePageStarted(str2, z, z2, str3);
            }
        });
        this.offlineLoader.setCallback(new OfflineCallback() { // from class: com.jingdong.common.web.urlcheck.impl.InterceptRequestImpl.2
            @Override // com.jd.libs.hybrid.offlineload.OfflineCallback
            public void beforeReload() {
                InterceptRequestImpl.this.beforeReload();
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineCallback
            public void onFetchPreDownloadFile(int i2, long j2, long j3, Object obj) {
                InterceptRequestImpl.this.onFetchPreDownloadFile(i2, j2, j3, obj);
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineCallback
            public void onOfflineFileHit(String str2, String str3, boolean z, LocalFileType localFileType) {
                LocalFileType localFileType2 = LocalFileType.FILE_TYPE_PKG_SHARED;
                if (localFileType == localFileType2 || localFileType == LocalFileType.FILE_TYPE_PKG) {
                    b.j(jDWebView.getDogId(), "text", "\u547d\u4e2d\u79bb\u7ebf\u5305", "yes");
                }
                if (localFileType == localFileType2) {
                    if (!InterceptRequestImpl.this.sharedFileHit.getAndSet(true)) {
                        InterceptRequestImpl.this.onSharedPkgFileHit(str2, str3, z);
                    }
                    if (InterceptRequestImpl.this.jdWebView != null) {
                        InterceptRequestImpl.this.jdWebView.appendPerformanceData(WebPerfManager.C_BINGO_COUNT, "1");
                    }
                } else if (localFileType == LocalFileType.FILE_TYPE_PKG) {
                    if (!InterceptRequestImpl.this.offlineFileHit.getAndSet(true)) {
                        InterceptRequestImpl.this.onPkgFileHit(str2, str3, z);
                    }
                    if (InterceptRequestImpl.this.jdWebView != null) {
                        InterceptRequestImpl.this.jdWebView.appendPerformanceData(WebPerfManager.BINGO_COUNT, "1");
                    }
                }
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineCallback
            public void onOfflinePageStarted(String str2) {
                InterceptRequestImpl.this.onOfflinePageStarted(str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    public void beforeReload(String str) {
        JDWebView jDWebView = this.jdWebView;
        if (jDWebView != null) {
            Log.d("JDHybrid", "\u57cb\u70b9-->\u79bb\u7ebf\u5305\u914d\u7f6e\u4e0e\u7ebf\u4e0a\u7248\u672c\u4e0d\u5bf9\uff0c\u91cd\u5237\u9875\u9762");
            OfflineFiles offlineFiles = getOfflineFiles();
            jDWebView.appendPerformanceData(WebPerfManager.HYBRID_ID, offlineFiles != null ? offlineFiles.getAppId() : "");
            jDWebView.appendPerformanceData("businessType", "1");
            jDWebView.appendPerformanceData(WebPerfManager.X_B_TYPE, str);
            jDWebView.appendPerformanceData(WebPerfManager.BIZ_OFFLINE_BINGO, "2");
            jDWebView.appendPerformanceData(WebPerfManager.HYBRID_CONFIG_VERSION, offlineFiles != null ? String.valueOf(offlineFiles.getModuleVersion()) : "");
            jDWebView.appendPerformanceData(WebPerfManager.HYBRID_FILE_VERSION, offlineFiles != null ? String.valueOf(offlineFiles.getFileVersion()) : "");
            jDWebView.appendWhiteScreenData("businessType", "1");
        }
    }

    private OfflineFiles getOfflineFiles() {
        HybridOfflineLoader hybridOfflineLoader = this.offlineLoader;
        if (hybridOfflineLoader != null) {
            return hybridOfflineLoader.getOfflineFiles();
        }
        return null;
    }

    private OfflineFiles getSharedFiles() {
        HybridOfflineLoader hybridOfflineLoader = this.offlineLoader;
        if (hybridOfflineLoader != null) {
            return hybridOfflineLoader.getSharedFiles();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFetchPreDownloadFile(int i2, long j2, long j3, Object obj) {
        WebPerformanceHolder performanceHolder;
        JDWebView jDWebView = this.jdWebView;
        if (jDWebView != null) {
            jDWebView.appendPerformanceData("htmlPreDownload", String.valueOf(i2));
            jDWebView.appendWhiteScreenData("htmlPreDownload", String.valueOf(i2));
            if (i2 == 200) {
                b.j(jDWebView.getDogId(), "text", "HTML\u9884\u4e0b\u8f7d", "yes");
            }
            jDWebView.appendPerformanceData(WebPerfManager.HTML_PRE_DOWNLOAD_START, String.valueOf(j2));
            if (j3 > 0) {
                jDWebView.appendPerformanceData(WebPerfManager.HTML_PRE_DOWNLOAD_END, String.valueOf(j3));
            }
            if (obj != null && (performanceHolder = jDWebView.getPerformanceHolder()) != null) {
                performanceHolder.appendExtraToCurrRecord("preHtml", obj);
            }
            if (Log.D) {
                Log.d("JDHybrid", "\u9884\u4e0b\u8f7dhtml\u72b6\u6001\uff1a" + i2 + ", start:" + j2 + ", end:" + j3 + ", elapsed=" + (j3 - j2) + ", extra:" + obj);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    public void onFileHitMainPage(String str, String str2, String str3) {
        JDWebView jDWebView = this.jdWebView;
        if (jDWebView != null) {
            OfflineFiles offlineFiles = getOfflineFiles();
            jDWebView.appendPerformanceData(WebPerfManager.HYBRID_ID, offlineFiles != null ? offlineFiles.getAppId() : "");
            jDWebView.appendPerformanceData("businessType", "1");
            jDWebView.appendPerformanceData(WebPerfManager.X_B_TYPE, str3);
            jDWebView.appendPerformanceData(WebPerfManager.BIZ_OFFLINE_BINGO, "1");
            jDWebView.appendPerformanceData(WebPerfManager.HYBRID_CONFIG_VERSION, offlineFiles != null ? String.valueOf(offlineFiles.getModuleVersion()) : "");
            jDWebView.appendPerformanceData(WebPerfManager.HYBRID_FILE_VERSION, offlineFiles != null ? String.valueOf(offlineFiles.getFileVersion()) : "");
            jDWebView.appendWhiteScreenData("businessType", "1");
            if (Log.D) {
                Log.d("JDHybrid", "\u547d\u4e2d\u4e3b\u8d44\u6e90:" + str);
            }
            if (!WebHybridLogView.toastHit || jDWebView.getContext() == null) {
                return;
            }
            ToastUtils.showToast(jDWebView.getContext(), "\u547d\u4e2d\u4e3b\u8d44\u6e90:" + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    public void onFirstOfflinePageStarted(String str, boolean z, boolean z2, String str2) {
        JDWebView jDWebView = this.jdWebView;
        if (jDWebView != null) {
            OfflineFiles offlineFiles = getOfflineFiles();
            jDWebView.appendPerformanceData(WebPerfManager.HYBRID_ID, offlineFiles != null ? offlineFiles.getAppId() : "");
            if (z2) {
                jDWebView.appendPerformanceData("businessType", "1");
                jDWebView.appendPerformanceData(WebPerfManager.BIZ_OFFLINE_BINGO, "1");
                jDWebView.appendWhiteScreenData("businessType", "1");
            } else if (z) {
                jDWebView.appendPerformanceData("businessType", "1");
                jDWebView.appendWhiteScreenData("businessType", "1");
            }
            jDWebView.appendPerformanceData(WebPerfManager.X_B_TYPE, str2);
            jDWebView.appendPerformanceData(WebPerfManager.HYBRID_CONFIG_VERSION, offlineFiles != null ? String.valueOf(offlineFiles.getModuleVersion()) : "");
            jDWebView.appendPerformanceData(WebPerfManager.HYBRID_FILE_VERSION, offlineFiles != null ? String.valueOf(offlineFiles.getFileVersion()) : "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onOfflinePageStarted(String str) {
        JDWebView jDWebView = this.jdWebView;
        if (jDWebView != null) {
            jDWebView.appendPerformanceData("businessType", "1");
            jDWebView.appendPerformanceData(WebPerfManager.X_B_TYPE, "4");
            jDWebView.appendWhiteScreenData("businessType", "1");
            saveHybridInfo();
            saveHybridSharedPkgInfo();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPkgFileHit(String str, String str2, boolean z) {
        JDWebView jDWebView = this.jdWebView;
        if (jDWebView != null) {
            jDWebView.appendPerformanceData("businessType", "1");
            jDWebView.appendPerformanceData(WebPerfManager.X_B_TYPE, "4");
            jDWebView.appendPerformanceData(WebPerfManager.BIZ_OFFLINE_BINGO, "1");
            jDWebView.appendWhiteScreenData("businessType", "1");
            saveHybridInfo();
            if (Log.D) {
                Log.d("JDHybrid", "\u547d\u4e2d\u4e1a\u52a1\u5305\u8d44\u6e90:" + str);
            }
            if (!WebHybridLogView.toastHit || jDWebView.getContext() == null) {
                return;
            }
            ToastUtils.showToast(jDWebView.getContext(), "\u547d\u4e2d\u4e1a\u52a1\u5305\u8d44\u6e90:" + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSharedPkgFileHit(String str, String str2, boolean z) {
        JDWebView jDWebView = this.jdWebView;
        if (jDWebView != null) {
            jDWebView.appendPerformanceData("businessType", "1");
            jDWebView.appendPerformanceData(WebPerfManager.X_B_TYPE, "4");
            jDWebView.appendPerformanceData(WebPerfManager.SHARED_OFFLINE_BINGO, "1");
            jDWebView.appendWhiteScreenData("businessType", "1");
            saveHybridSharedPkgInfo();
            if (Log.D) {
                Log.d("JDHybrid", "\u547d\u4e2d\u516c\u5171\u5305\u8d44\u6e90:" + str);
            }
            if (!WebHybridLogView.toastHit || jDWebView.getContext() == null) {
                return;
            }
            ToastUtils.showToast(jDWebView.getContext(), "\u547d\u4e2d\u516c\u5171\u5305\u8d44\u6e90:" + str);
        }
    }

    private void saveHybridInfo() {
        JDWebView jDWebView = this.jdWebView;
        if (jDWebView != null) {
            HybridOfflineLoader hybridOfflineLoader = this.offlineLoader;
            if (hybridOfflineLoader != null && hybridOfflineLoader.clientNew) {
                jDWebView.appendPerformanceData("useJdCache", "1");
            }
            OfflineFiles offlineFiles = getOfflineFiles();
            jDWebView.appendPerformanceData(WebPerfManager.HYBRID_ID, offlineFiles != null ? offlineFiles.getAppId() : "");
            jDWebView.appendPerformanceData(WebPerfManager.HYBRID_CONFIG_VERSION, offlineFiles != null ? String.valueOf(offlineFiles.getModuleVersion()) : "");
            jDWebView.appendPerformanceData(WebPerfManager.HYBRID_FILE_VERSION, offlineFiles != null ? String.valueOf(offlineFiles.getFileVersion()) : "");
        }
    }

    private void saveHybridSharedPkgInfo() {
        JDWebView jDWebView = this.jdWebView;
        if (jDWebView != null) {
            OfflineFiles sharedFiles = getSharedFiles();
            jDWebView.appendPerformanceData(WebPerfManager.SHARED_HYBRID_ID, sharedFiles != null ? sharedFiles.getAppId() : "");
            jDWebView.appendPerformanceData(WebPerfManager.HYBRID_SHARED_CONFIG_VERSION, sharedFiles != null ? String.valueOf(sharedFiles.getModuleVersion()) : "");
            jDWebView.appendPerformanceData(WebPerfManager.HYBRID_SHARED_FILE_VERSION, sharedFiles != null ? String.valueOf(sharedFiles.getFileVersion()) : "");
        }
    }

    @Override // com.jingdong.common.web.urlcheck.IInterceptRequest
    public WebResourceResponse interceptRequest(WebView webView, final WebResourceRequest webResourceRequest) {
        if (webResourceRequest.isForMainFrame()) {
            this.offlineFileHit.set(false);
            this.sharedFileHit.set(false);
        }
        if (this.offlineLoader == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        android.webkit.WebResourceResponse shouldInterceptRequest = this.offlineLoader.shouldInterceptRequest(this.jdWebView.getWebView(), new android.webkit.WebResourceRequest() { // from class: com.jingdong.common.web.urlcheck.impl.InterceptRequestImpl.3
            @Override // android.webkit.WebResourceRequest
            public String getMethod() {
                return webResourceRequest.getMethod();
            }

            @Override // android.webkit.WebResourceRequest
            public Map<String, String> getRequestHeaders() {
                return webResourceRequest.getRequestHeaders();
            }

            @Override // android.webkit.WebResourceRequest
            public Uri getUrl() {
                return webResourceRequest.getUrl();
            }

            @Override // android.webkit.WebResourceRequest
            public boolean hasGesture() {
                return webResourceRequest.hasGesture();
            }

            @Override // android.webkit.WebResourceRequest
            public boolean isForMainFrame() {
                return webResourceRequest.isForMainFrame();
            }

            @Override // android.webkit.WebResourceRequest
            public boolean isRedirect() {
                return webResourceRequest.isRedirect();
            }
        });
        if (shouldInterceptRequest != null) {
            InputStream data = shouldInterceptRequest.getData();
            if ("1".equals(HybridBase.getInstance().getSetting(HybridSDK.SWITCH_IO_DETAIL))) {
                data = new IoDetailInputStream(shouldInterceptRequest.getData(), CommonUtils.getCleanUrl(webResourceRequest.getUrl().toString()));
            }
            return new WebResourceResponse(shouldInterceptRequest.getMimeType(), shouldInterceptRequest.getEncoding(), shouldInterceptRequest.getStatusCode(), shouldInterceptRequest.getReasonPhrase(), shouldInterceptRequest.getResponseHeaders(), data);
        }
        return null;
    }

    @Override // com.jingdong.common.web.urlcheck.IInterceptRequest
    public WebResourceResponse interceptRequest(WebView webView, String str) {
        return null;
    }

    /* loaded from: classes12.dex */
    class IoDetailInputStream extends BufferedInputStream {
        long closeTime;
        AtomicBoolean finish;
        long finishTime;
        long startTime;
        AtomicBoolean started;
        String url;

        public IoDetailInputStream(InputStream inputStream, String str) {
            super(inputStream);
            this.started = new AtomicBoolean(false);
            this.finish = new AtomicBoolean(false);
            this.startTime = 0L;
            this.finishTime = 0L;
            this.closeTime = 0L;
            this.url = str;
        }

        private void onFinish(boolean z) {
            WebPerformanceHolder performanceHolder;
            if (z && this.finish.compareAndSet(false, true)) {
                this.finishTime = System.currentTimeMillis();
                long currentTimeMillis = System.currentTimeMillis() - this.startTime;
                if (InterceptRequestImpl.this.jdWebView == null || (performanceHolder = InterceptRequestImpl.this.jdWebView.getPerformanceHolder()) == null || performanceHolder.getCurrentRecord() == null) {
                    return;
                }
                performanceHolder.getCurrentRecord().addIoDetail(this.url, currentTimeMillis);
            }
        }

        private void onStart() {
            if (this.started.compareAndSet(false, true)) {
                this.startTime = System.currentTimeMillis();
            }
        }

        @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            this.closeTime = System.currentTimeMillis();
        }

        @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
        public synchronized int read() throws IOException {
            int read;
            onStart();
            read = super.read();
            onFinish(read == -1);
            return read;
        }

        @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
        public synchronized int read(byte[] bArr, int i2, int i3) throws IOException {
            int read;
            onStart();
            read = super.read(bArr, i2, i3);
            onFinish(read == -1);
            return read;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            onStart();
            int read = super.read(bArr);
            onFinish(read == -1);
            return read;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void beforeReload() {
        JDWebView jDWebView = this.jdWebView;
        if (jDWebView != null) {
            Log.d("JDHybrid", "\u57cb\u70b9-->\u79bb\u7ebf\u5305\u914d\u7f6e\u4e0e\u7ebf\u4e0a\u7248\u672c\u4e0d\u5bf9\uff0c\u91cd\u5237\u9875\u9762");
            jDWebView.appendPerformanceData("businessType", "1");
            jDWebView.appendPerformanceData(WebPerfManager.X_B_TYPE, "4");
            jDWebView.appendPerformanceData(WebPerfManager.BIZ_OFFLINE_BINGO, "2");
            jDWebView.appendPerformanceData(WebPerfManager.SHARED_OFFLINE_BINGO, "0");
            jDWebView.appendWhiteScreenData("businessType", "1");
            saveHybridInfo();
            saveHybridSharedPkgInfo();
        }
    }
}
