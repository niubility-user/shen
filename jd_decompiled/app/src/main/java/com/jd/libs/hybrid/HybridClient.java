package com.jd.libs.hybrid;

import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jd.libs.hybrid.base.HybridWebView;
import com.jd.libs.hybrid.base.XDogUtils;
import com.jd.libs.hybrid.offlineload.OfflineCallback;
import com.jd.libs.hybrid.offlineload.entity.LocalFileType;
import com.jd.libs.xdog.b;
import com.jd.libs.xdog.f;

/* loaded from: classes16.dex */
public class HybridClient extends WebViewClient {
    private HybridOfflineLoader a;
    private long b;

    /* renamed from: c  reason: collision with root package name */
    private String f5879c;
    private boolean d = false;

    public void bindHybridLoader(HybridOfflineLoader hybridOfflineLoader) {
        this.a = hybridOfflineLoader;
        this.f5879c = String.valueOf(System.identityHashCode(hybridOfflineLoader));
        this.a.setCallback(new OfflineCallback() { // from class: com.jd.libs.hybrid.HybridClient.1
            @Override // com.jd.libs.hybrid.offlineload.OfflineCallback
            public void beforeReload() {
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineCallback
            public void onFetchPreDownloadFile(int i2, long j2, long j3, Object obj) {
                if (i2 == 200) {
                    b.j(HybridClient.this.f5879c, "text", "HTML\u9884\u4e0b\u8f7d", "yes");
                }
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineCallback
            public void onOfflineFileHit(String str, String str2, boolean z, LocalFileType localFileType) {
                b.j(HybridClient.this.f5879c, "text", "\u547d\u4e2d\u79bb\u7ebf\u5305", "yes");
            }

            @Override // com.jd.libs.hybrid.offlineload.OfflineCallback
            public void onOfflinePageStarted(String str) {
            }
        });
    }

    @Override // android.webkit.WebViewClient
    @CallSuper
    public void onPageFinished(WebView webView, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if (f.v && this.d) {
            long j2 = this.b;
            int i2 = (int) (currentTimeMillis - j2);
            if (j2 > 0 && currentTimeMillis > 0) {
                b.j(this.f5879c, "data", "loadTime", String.valueOf(i2));
            }
            XDogUtils.setConfigForPaint((HybridWebView) webView, this.f5879c);
        }
        HybridOfflineLoader hybridOfflineLoader = this.a;
        if (hybridOfflineLoader != null) {
            hybridOfflineLoader.onPageFinished((HybridWebView) webView, str);
        }
        super.onPageFinished(webView, str);
    }

    @Override // android.webkit.WebViewClient
    @CallSuper
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        HybridOfflineLoader hybridOfflineLoader = this.a;
        if (hybridOfflineLoader != null) {
            hybridOfflineLoader.onPageStarted((HybridWebView) webView, str, bitmap);
        }
        this.b = System.currentTimeMillis();
        if ((f.u || f.v) && !TextUtils.isEmpty(this.f5879c) && !this.d) {
            this.d = true;
            ViewParent parent = webView.getParent();
            if ((parent instanceof RelativeLayout) || (parent instanceof FrameLayout) || (parent instanceof ConstraintLayout)) {
                b.c((ViewGroup) parent, webView.getContext(), this.f5879c);
            }
        }
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    @CallSuper
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        HybridOfflineLoader hybridOfflineLoader = this.a;
        if (hybridOfflineLoader != null && Build.VERSION.SDK_INT >= 21) {
            return hybridOfflineLoader.shouldInterceptRequest((HybridWebView) webView, webResourceRequest);
        }
        return super.shouldInterceptRequest(webView, webResourceRequest);
    }
}
