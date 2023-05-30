package com.jd.libs.hybrid;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.annotation.Keep;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleOwner;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.HybridWebView;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.HybridWebClient;
import com.jd.libs.hybrid.offlineload.IPreDownloadParamGetter;
import com.jd.libs.hybrid.offlineload.OfflineCallback;
import com.jd.libs.hybrid.offlineload.OfflineLoadController;
import com.jd.libs.hybrid.offlineload.OfflineWebClient;
import com.jd.libs.hybrid.offlineload.entity.CommonFile;
import com.jd.libs.hybrid.offlineload.entity.OfflineFiles;
import com.jd.libs.hybrid.offlineload.jdcache.CacheLoader;
import com.jd.libs.hybrid.offlineload.loader.HtmlLoader;
import com.jd.libs.hybrid.offlineload.temp.OfflineSwitchSetting;
import com.jd.libs.hybrid.offlineload.utils.GraySwitch;
import com.jd.libs.hybrid.offlineload.utils.JDCacheSwitch;
import com.jd.libs.xdog.b;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Keep
/* loaded from: classes16.dex */
public class HybridOfflineLoader {
    private static final String TAG = "HybridOfflineLoader";
    private final AtomicBoolean bizFileChecked;
    public final boolean clientNew;
    private final boolean grayOff;
    private IPreDownloadParamGetter.PreDownloadParamGetter htmlDownloadParamGetter;
    private CacheLoader mCacheLoader;
    private HybridWebClient mHybridWebClient;
    private final AtomicBoolean sharedFileChecked;
    private final AtomicBoolean triedPreloadHtml;

    private HybridOfflineLoader(String str) {
        boolean z = OfflineSwitchSetting.TYPE_4_OFF;
        this.grayOff = z;
        boolean booleanValue = JDCacheSwitch.useJdCache.booleanValue();
        this.clientNew = booleanValue;
        this.bizFileChecked = new AtomicBoolean(false);
        this.sharedFileChecked = new AtomicBoolean(false);
        this.triedPreloadHtml = new AtomicBoolean(false);
        if (z) {
            return;
        }
        if (booleanValue) {
            CacheLoader cacheLoader = new CacheLoader(str);
            this.mCacheLoader = cacheLoader;
            cacheLoader.init();
            if (Log.isDebug()) {
                Log.xLogDForDev(TAG, "\u6b63\u5728\u4f7f\u7528JDCache");
                return;
            }
            return;
        }
        this.mHybridWebClient = new HybridWebClient(str);
    }

    public static HybridOfflineLoader getLoader(String str) {
        return getLoader(str, null);
    }

    private void searchConfig(final String str) {
        if (TextUtils.isEmpty(str) || this.grayOff) {
            return;
        }
        if (this.clientNew) {
            CacheLoader cacheLoader = this.mCacheLoader;
            if (cacheLoader != null) {
                cacheLoader.setPreDownloadGetter(this.htmlDownloadParamGetter);
                cacheLoader.searchConfig();
                return;
            }
            return;
        }
        OfflineLoadController offlineLoadController = OfflineLoadController.getInstance(HybridSettings.getAppContext());
        offlineLoadController.getOfflineFiles(str, new OfflineLoadController.NetConfigCallback<OfflineFiles>() { // from class: com.jd.libs.hybrid.HybridOfflineLoader.1
            @Override // com.jd.libs.hybrid.offlineload.OfflineLoadController.ConfigCallback
            public void onFilesAvailable() {
                HybridWebClient hybridWebClient = HybridOfflineLoader.this.mHybridWebClient;
                if (hybridWebClient != null) {
                    hybridWebClient.onFilesAvailable();
                    HybridOfflineLoader.this.tryPreloadHtml(str);
                }
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineLoadController.ConfigCallback
            public void onCacheCallback(OfflineFiles offlineFiles, boolean z) {
                HybridWebClient hybridWebClient = HybridOfflineLoader.this.mHybridWebClient;
                if (hybridWebClient == null) {
                    return;
                }
                if (offlineFiles == null) {
                    HybridOfflineLoader.this.bizFileChecked.set(true);
                    if (HybridOfflineLoader.this.sharedFileChecked.get()) {
                        HybridOfflineLoader.this.tryPreloadHtml(str);
                    }
                }
                hybridWebClient.onConfig(offlineFiles);
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineLoadController.NetConfigCallback
            public void onNetworkCallback(OfflineFiles offlineFiles, boolean z, boolean z2) {
                HybridWebClient hybridWebClient = HybridOfflineLoader.this.mHybridWebClient;
                if (hybridWebClient != null) {
                    hybridWebClient.onLatestConfig(offlineFiles, z, z2);
                }
            }
        });
        offlineLoadController.getSharedOfflineFiles(str, new OfflineLoadController.ConfigCallback<OfflineFiles>() { // from class: com.jd.libs.hybrid.HybridOfflineLoader.2
            @Override // com.jd.libs.hybrid.offlineload.OfflineLoadController.ConfigCallback
            public void onFilesAvailable() {
                HybridWebClient hybridWebClient = HybridOfflineLoader.this.mHybridWebClient;
                if (hybridWebClient != null) {
                    hybridWebClient.onSharedFilesAvailable();
                    HybridOfflineLoader.this.sharedFileChecked.set(true);
                    if (HybridOfflineLoader.this.bizFileChecked.get()) {
                        HybridOfflineLoader.this.tryPreloadHtml(str);
                    }
                }
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineLoadController.ConfigCallback
            public void onCacheCallback(OfflineFiles offlineFiles, boolean z) {
                HybridWebClient hybridWebClient = HybridOfflineLoader.this.mHybridWebClient;
                if (hybridWebClient != null) {
                    hybridWebClient.onSharedConfig(offlineFiles);
                }
            }
        });
        offlineLoadController.getCommonOfflineFiles(new OfflineLoadController.ConfigCallback<List<CommonFile>>() { // from class: com.jd.libs.hybrid.HybridOfflineLoader.3
            @Override // com.jd.libs.hybrid.offlineload.OfflineLoadController.ConfigCallback
            public void onFilesAvailable() {
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineLoadController.ConfigCallback
            public void onCacheCallback(List<CommonFile> list, boolean z) {
                HybridWebClient hybridWebClient = HybridOfflineLoader.this.mHybridWebClient;
                if (hybridWebClient != null) {
                    hybridWebClient.onCommonConfig(list);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryPreloadHtml(String str) {
        if (this.triedPreloadHtml.compareAndSet(false, true) && this.mHybridWebClient != null) {
            OfflineFiles offlineFiles = getOfflineFiles();
            if (offlineFiles == null) {
                offlineFiles = getSharedFiles();
            }
            if (offlineFiles != null && offlineFiles.isCanPreloadHtml() && offlineFiles.isAvailable()) {
                IPreDownloadParamGetter.PreDownloadParamGetter preDownloadParamGetter = this.htmlDownloadParamGetter;
                if (preDownloadParamGetter != null) {
                    str = preDownloadParamGetter.getDownloadUrl(offlineFiles, str);
                }
                if (TextUtils.isEmpty(str)) {
                    if (Log.isDebug()) {
                        Log.d(TAG, "pre-download html canceled by outside.");
                        Log.xLogDForDev(TAG, "\u4e1a\u52a1\u5c42\u63a7\u5236\u4e0d\u8fdb\u884c\u9884\u4e0b\u8f7dHTML");
                    }
                } else if (GraySwitch.fileForPreloadHtml) {
                    HtmlLoader.preloadHtmlFile(str, offlineFiles);
                } else {
                    HtmlLoader.preloadHtmlStream(str, offlineFiles);
                }
            }
        }
    }

    @Deprecated
    public boolean canPassGentoken() {
        OfflineFiles offlineFiles = getOfflineFiles();
        return offlineFiles != null && offlineFiles.isCanPassGentoken();
    }

    public void destroy() {
        b.a();
        HybridWebClient hybridWebClient = this.mHybridWebClient;
        if (hybridWebClient != null) {
            hybridWebClient.destroy();
            this.mHybridWebClient = null;
        }
        CacheLoader cacheLoader = this.mCacheLoader;
        if (cacheLoader != null) {
            cacheLoader.destroy();
            this.mCacheLoader = null;
        }
    }

    public String getBConfig() {
        OfflineFiles offlineFiles = getOfflineFiles();
        if (offlineFiles == null) {
            offlineFiles = getSharedFiles();
        }
        String bConfig = offlineFiles != null ? offlineFiles.getBConfig() : null;
        return !TextUtils.isEmpty(bConfig) ? bConfig : "";
    }

    public OfflineFiles getFiles() {
        OfflineFiles offlineFiles = getOfflineFiles();
        return offlineFiles == null ? getSharedFiles() : offlineFiles;
    }

    public OfflineFiles getOfflineFiles() {
        if (this.clientNew) {
            CacheLoader cacheLoader = this.mCacheLoader;
            if (cacheLoader != null) {
                return cacheLoader.getBizOfflineFiles();
            }
            return null;
        }
        HybridWebClient hybridWebClient = this.mHybridWebClient;
        if (hybridWebClient != null) {
            return hybridWebClient.getOfflineFiles();
        }
        return null;
    }

    public OfflineFiles getSharedFiles() {
        if (this.clientNew) {
            CacheLoader cacheLoader = this.mCacheLoader;
            if (cacheLoader != null) {
                return cacheLoader.getSharedOfflineFiles();
            }
            return null;
        }
        HybridWebClient hybridWebClient = this.mHybridWebClient;
        if (hybridWebClient != null) {
            return hybridWebClient.getSharedFiles();
        }
        return null;
    }

    @Deprecated
    public OfflineWebClient getWebClient() {
        return null;
    }

    public boolean hasOfflineConfig() {
        return getOfflineFiles() != null;
    }

    public boolean hasOfflineFiles() {
        OfflineFiles offlineFiles = getOfflineFiles();
        if (offlineFiles == null) {
            offlineFiles = getSharedFiles();
        }
        return offlineFiles != null && offlineFiles.isAvailable();
    }

    public void onPageFinished(HybridWebView hybridWebView, String str) {
        if (this.clientNew) {
            CacheLoader cacheLoader = this.mCacheLoader;
            if (cacheLoader != null) {
                cacheLoader.onPageFinished(str);
                return;
            }
            return;
        }
        HybridWebClient hybridWebClient = this.mHybridWebClient;
        if (hybridWebClient != null) {
            hybridWebClient.onPageFinished(hybridWebView, str);
        }
    }

    public void onPageStarted(HybridWebView hybridWebView, String str, Bitmap bitmap) {
        if (this.clientNew) {
            CacheLoader cacheLoader = this.mCacheLoader;
            if (cacheLoader != null) {
                cacheLoader.onPageStarted(str);
                return;
            }
            return;
        }
        HybridWebClient hybridWebClient = this.mHybridWebClient;
        if (hybridWebClient != null) {
            hybridWebClient.onPageStarted(hybridWebView, str, bitmap);
        }
    }

    public void setCallback(OfflineCallback offlineCallback) {
        HybridWebClient hybridWebClient = this.mHybridWebClient;
        if (hybridWebClient != null) {
            hybridWebClient.setCallback(offlineCallback);
        }
        CacheLoader cacheLoader = this.mCacheLoader;
        if (cacheLoader != null) {
            cacheLoader.setCallback(offlineCallback);
        }
    }

    @Deprecated
    public void setCallback(OfflineWebClient.Callback callback) {
    }

    public void setLifeCycleOwner(LifecycleOwner lifecycleOwner) {
        CacheLoader cacheLoader = this.mCacheLoader;
        if (cacheLoader != null) {
            cacheLoader.setLifecycleOwner(lifecycleOwner);
        }
    }

    @RequiresApi(api = 21)
    public WebResourceResponse shouldInterceptRequest(HybridWebView hybridWebView, WebResourceRequest webResourceRequest) {
        if (this.clientNew) {
            CacheLoader cacheLoader = this.mCacheLoader;
            if (cacheLoader != null) {
                cacheLoader.setView(hybridWebView);
                return cacheLoader.onRequest(webResourceRequest);
            }
            return null;
        }
        HybridWebClient hybridWebClient = this.mHybridWebClient;
        if (hybridWebClient != null) {
            return hybridWebClient.shouldInterceptRequest(hybridWebView, webResourceRequest);
        }
        return null;
    }

    @Deprecated
    public boolean useNewClient() {
        return true;
    }

    public static HybridOfflineLoader getLoader(String str, IPreDownloadParamGetter.PreDownloadParamGetter preDownloadParamGetter) {
        if (!HybridSDK.isInited()) {
            Log.e(TAG, "Hybrid SDK is not initialized!");
            return null;
        } else if (TextUtils.isEmpty(str)) {
            return null;
        } else {
            HybridOfflineLoader hybridOfflineLoader = new HybridOfflineLoader(str);
            hybridOfflineLoader.htmlDownloadParamGetter = preDownloadParamGetter;
            hybridOfflineLoader.searchConfig(str);
            return hybridOfflineLoader;
        }
    }
}
