package com.jd.libs.hybrid.requestpreload.jsbridge;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import androidx.annotation.Keep;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.HybridWebView;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.base.util.PerformanceUtils;
import com.jd.libs.hybrid.base.util.StringUtils;
import com.jd.libs.hybrid.requestpreload.RequestPreloadSDK;
import com.jd.libs.hybrid.requestpreload.entity.CacheItem;
import com.jd.libs.hybrid.requestpreload.network.RequestCacheManager;
import com.jd.libs.hybrid.requestpreload.network.RequestHelper;
import com.jd.libs.hybrid.requestpreload.perf.PerfMonitor;
import com.jd.libs.hybrid.requestpreload.utils.CommonUtil;
import com.jd.libs.xdog.b;
import com.jingdong.sdk.jdhttpdns.config.HttpDnsConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u0000 &2\u00020\u0001:\u0002&'B\u001b\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0002\u00a2\u0006\u0004\b$\u0010%J=\u0010\f\u001a\u00020\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0002\u00a2\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0007H\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\u0013\u0010\u0012J-\u0010\u0017\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00022\b\u0010\u0015\u001a\u0004\u0018\u00010\u00022\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002H\u0007\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u000b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u00a2\u0006\u0004\b\u001b\u0010\u001cR\u0016\u0010\u001d\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001f\u0010\u001eR\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010 R\u0018\u0010!\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010#\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b#\u0010\u001e\u00a8\u0006("}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/jsbridge/PreloadJSBridge;", "", "", "appId", "Lcom/jd/libs/hybrid/base/HybridWebView;", "webView", "jsCallBack", "", "code", "Lcom/jd/libs/hybrid/requestpreload/entity/CacheItem;", "cacheItem", "", "sendJSONStr2M", "(Ljava/lang/String;Lcom/jd/libs/hybrid/base/HybridWebView;Ljava/lang/String;ILcom/jd/libs/hybrid/requestpreload/entity/CacheItem;)V", "status", "callPreloadCallback", "(I)V", "perfOnStart", "()V", "perfOnEnd", "appIdOrUrl", "requestIdOrUrl", "callback", "fetchPreloadData", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Lcom/jd/libs/hybrid/requestpreload/jsbridge/PreloadJSBridge$PreloadDataCallback;", "preloadCallback", "setPreloadCallback", "(Lcom/jd/libs/hybrid/requestpreload/jsbridge/PreloadJSBridge$PreloadDataCallback;)V", "gprlstart", "Ljava/lang/String;", VerifyTracker.KEY_TIMESTAMP, "Lcom/jd/libs/hybrid/requestpreload/jsbridge/PreloadJSBridge$PreloadDataCallback;", "webview", "Lcom/jd/libs/hybrid/base/HybridWebView;", "gprlend", "<init>", "(Lcom/jd/libs/hybrid/base/HybridWebView;Ljava/lang/String;)V", "Companion", "PreloadDataCallback", "request-preload_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes16.dex */
public final class PreloadJSBridge {
    @NotNull
    public static final String CHANNEL = "RequestPreload";
    @NotNull
    public static final String JS_OBJ_NAME = "JDHybridPreload";
    @NotNull
    public static final String TAG = "RequestPreloadBridge";
    private String gprlend;
    private String gprlstart;
    private PreloadDataCallback preloadCallback;
    private final String timestamp;
    private final HybridWebView webview;

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/jsbridge/PreloadJSBridge$PreloadDataCallback;", "", "", "jsCallbackStatus", "", "onWebFetchData", "(I)V", "request-preload_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes16.dex */
    public interface PreloadDataCallback {
        void onWebFetchData(int jsCallbackStatus);
    }

    public PreloadJSBridge(@Nullable HybridWebView hybridWebView, @NotNull String str) {
        this.webview = hybridWebView;
        this.timestamp = str;
        this.gprlstart = "";
        this.gprlend = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callPreloadCallback(int status) {
        PreloadDataCallback preloadDataCallback = this.preloadCallback;
        if (preloadDataCallback != null) {
            preloadDataCallback.onWebFetchData(status);
        }
        PerformanceUtils.onPreloadStatusChange(this.webview, String.valueOf(status));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void perfOnEnd() {
        String valueOf = String.valueOf(System.currentTimeMillis());
        this.gprlend = valueOf;
        b.k(HttpDnsConfig.PREDOWNLOAD_PARAMS, "gprlend", valueOf);
        PerfMonitor perfMonitor = RequestPreloadSDK.INSTANCE.getInstance().getPerfMonitor();
        perfMonitor.addRecord(this.timestamp, "gprlend", this.gprlend);
        perfMonitor.report(this.timestamp, this.webview, true);
    }

    private final void perfOnStart() {
        String valueOf = String.valueOf(System.currentTimeMillis());
        this.gprlstart = valueOf;
        b.k(HttpDnsConfig.PREDOWNLOAD_PARAMS, "gprlstart", valueOf);
        PerfMonitor perfMonitor = RequestPreloadSDK.INSTANCE.getInstance().getPerfMonitor();
        perfMonitor.addRecord(this.timestamp, "gprlstart", this.gprlstart);
        perfMonitor.report(this.timestamp, this.webview, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendJSONStr2M(final String appId, final HybridWebView webView, final String jsCallBack, final int code, final CacheItem cacheItem) {
        CommonUtil commonUtil = CommonUtil.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append("Preload result: status=");
        sb.append(code);
        sb.append(", data=");
        sb.append(cacheItem != null ? cacheItem.getJsonData() : null);
        commonUtil.logX(sb.toString());
        View view = webView != null ? webView.getView() : null;
        if (view == null) {
            Log.e(TAG, "webview is null in preload js callback.");
            perfOnEnd();
            return;
        }
        view.post(new Runnable() { // from class: com.jd.libs.hybrid.requestpreload.jsbridge.PreloadJSBridge$sendJSONStr2M$1
            @Override // java.lang.Runnable
            public final void run() {
                JSONObject jSONObject = new JSONObject();
                try {
                    if (Build.VERSION.SDK_INT < 23) {
                        PreloadJSBridge.this.callPreloadCallback(-2);
                        jSONObject.put("status", -2);
                        jSONObject.put("data", (Object) null);
                        jSONObject.put("msg", "\u9884\u52a0\u8f7d\uff1aAndroid\u7cfb\u7edf\u8fc7\u4f4e(< 6.0)\uff0c\u4e0d\u652f\u6301\u9884\u52a0\u8f7d\u529f\u80fd");
                        String str = "javascript:" + jsCallBack + "('" + jSONObject + "');";
                        CommonUtil.INSTANCE.logX("preload send data back to H5, run js --> " + str);
                        PreloadJSBridge.this.perfOnEnd();
                        HybridWebView hybridWebView = webView;
                        if (hybridWebView == null) {
                            Intrinsics.throwNpe();
                        }
                        hybridWebView.evaluateJavascript(str, null);
                        return;
                    }
                    jSONObject.put("status", code);
                    PreloadJSBridge.this.callPreloadCallback(code);
                    CacheItem cacheItem2 = cacheItem;
                    jSONObject.put("data", cacheItem2 != null ? cacheItem2.getJsonData() : null);
                    int i2 = code;
                    if (i2 == 200) {
                        jSONObject.put("msg", "\u9884\u52a0\u8f7d\uff1a\u63a5\u53e3\u8bf7\u6c42\u6210\u529f");
                        String str2 = "javascript:" + jsCallBack + "('" + StringUtils.string2JsStr(jSONObject.toString()) + "');";
                        PreloadJSBridge.this.perfOnEnd();
                        HybridWebView hybridWebView2 = webView;
                        if (hybridWebView2 == null) {
                            Intrinsics.throwNpe();
                        }
                        hybridWebView2.evaluateJavascript(str2, null);
                    } else {
                        if (i2 == -1) {
                            jSONObject.put("msg", "\u9884\u52a0\u8f7d\uff1a\u7f51\u7edc\u8bf7\u6c42\u5931\u8d25");
                        } else if (i2 == -3) {
                            jSONObject.put("msg", "\u9884\u52a0\u8f7d\uff1a\u53c2\u6570\u89e3\u6790\u9519\u8bef");
                        } else {
                            jSONObject.put("msg", "\u9884\u52a0\u8f7d\uff1a\u672a\u67e5\u8be2\u5230\u9884\u52a0\u8f7d\u914d\u7f6e\u4fe1\u606f");
                        }
                        String str3 = "javascript:" + jsCallBack + "('" + jSONObject + "');";
                        PreloadJSBridge.this.perfOnEnd();
                        HybridWebView hybridWebView3 = webView;
                        if (hybridWebView3 == null) {
                            Intrinsics.throwNpe();
                        }
                        hybridWebView3.evaluateJavascript(str3, null);
                    }
                    CommonUtil.INSTANCE.logX("\u6536\u5230H5\u83b7\u53d6\u63a5\u53e3\u9884\u52a0\u8f7d\u6570\u636e\u7684\u8c03\u7528\uff1a\u9879\u76eeid=" + appId + "\uff0c\u72b6\u6001=" + RequestHelper.INSTANCE.getPreloadStatusStr(code) + "\uff0c\u8fd4\u56de\u7ed9H5\u7684data\uff1a" + jSONObject);
                } catch (Exception e2) {
                    Log.e(PreloadJSBridge.TAG, e2);
                    CommonUtil.INSTANCE.logX("js\u6865\u6267\u884c\u5931\u8d25: " + e2);
                    PreloadJSBridge.this.perfOnEnd();
                }
            }
        });
    }

    @JavascriptInterface
    public final void fetchPreloadData(@Nullable String appIdOrUrl, @Nullable String requestIdOrUrl, @Nullable final String callback) {
        perfOnStart();
        RequestPreloadSDK.Companion companion = RequestPreloadSDK.INSTANCE;
        if (!companion.getInstance().getIsInit().get()) {
            CommonUtil.INSTANCE.logX("SDK\u6ca1\u6709\u6210\u529f\u521d\u59cb\u5316");
            perfOnEnd();
        } else if (appIdOrUrl != null && requestIdOrUrl != null && callback != null) {
            final String appId = companion.getInstance().getPolicyManager().getAppId(appIdOrUrl);
            String requestId = companion.getInstance().getPolicyManager().getRequestId(appIdOrUrl, requestIdOrUrl);
            if (!HybridSettings.isInited()) {
                CommonUtil.INSTANCE.logX("Hybrid SDK is not initialized!");
                sendJSONStr2M(appIdOrUrl, this.webview, callback, -2, null);
            } else if (!TextUtils.isEmpty(appId) && !TextUtils.isEmpty(requestId) && !TextUtils.isEmpty(this.timestamp)) {
                RequestCacheManager requestCacheManager = companion.getInstance().getRequestCacheManager();
                if (appId == null) {
                    Intrinsics.throwNpe();
                }
                if (requestId == null) {
                    Intrinsics.throwNpe();
                }
                requestCacheManager.jsRequestData(appId, requestId, this.timestamp, new RequestCacheManager.Callback() { // from class: com.jd.libs.hybrid.requestpreload.jsbridge.PreloadJSBridge$fetchPreloadData$1
                    @Override // com.jd.libs.hybrid.requestpreload.network.RequestCacheManager.Callback
                    public void onResult(int code, @Nullable CacheItem data) {
                        HybridWebView hybridWebView;
                        PreloadJSBridge preloadJSBridge = PreloadJSBridge.this;
                        String str = appId;
                        hybridWebView = preloadJSBridge.webview;
                        preloadJSBridge.sendJSONStr2M(str, hybridWebView, callback, code, data);
                    }
                });
            } else {
                sendJSONStr2M(appId, this.webview, callback, -2, null);
            }
        } else {
            CommonUtil.INSTANCE.logX("appId(" + appIdOrUrl + ")/requestId(" + requestIdOrUrl + ")/callback(" + callback + ")\u53c2\u6570\u90fd\u5fc5\u987b\u4e0d\u4e3a\u7a7a");
            callPreloadCallback(0);
            perfOnEnd();
        }
    }

    public final void setPreloadCallback(@Nullable PreloadDataCallback preloadCallback) {
        this.preloadCallback = preloadCallback;
    }

    public /* synthetic */ PreloadJSBridge(HybridWebView hybridWebView, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(hybridWebView, (i2 & 2) != 0 ? "" : str);
    }
}
