package com.jingdong.app.mall.im.business;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.jingdong.app.mall.im.business.b0;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.web.MKeyNames;
import com.jingdong.common.web.ui.CommonMFragment;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.ui.X5WebView;
import com.jingdong.common.web.uilistener.WebViewClientListener;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.service.callback.WebPageFinishedListener;
import com.jingdong.service.callback.WebPageListener;
import com.jingdong.service.impl.IMXview;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes4.dex */
public class b0 extends IMXview {
    private static final String d = "b0";
    private CommonMFragment a;
    private JDWebView b;

    /* renamed from: c  reason: collision with root package name */
    private X5WebView f11128c;

    /* loaded from: classes4.dex */
    class a implements WebViewClientListener {
        final /* synthetic */ WebPageFinishedListener a;

        a(b0 b0Var, WebPageFinishedListener webPageFinishedListener) {
            this.a = webPageFinishedListener;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void a(WebPageFinishedListener webPageFinishedListener, WebView webView) {
            if (webPageFinishedListener == null || webView == null) {
                return;
            }
            try {
                webPageFinishedListener.onPageFinished(webView.getTitle());
            } catch (Exception e2) {
                OKLog.e(b0.d, e2.getMessage());
            }
        }

        @Override // com.jingdong.common.web.uilistener.WebViewClientListener
        public void onPageFinished(final WebView webView, String str) {
            if (webView == null || str == null) {
                return;
            }
            final WebPageFinishedListener webPageFinishedListener = this.a;
            webView.post(new Runnable() { // from class: com.jingdong.app.mall.im.business.a
                @Override // java.lang.Runnable
                public final void run() {
                    b0.a.a(WebPageFinishedListener.this, webView);
                }
            });
        }

        @Override // com.jingdong.common.web.uilistener.WebViewClientListener
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        }

        @Override // com.jingdong.common.web.uilistener.WebViewClientListener
        public void onReceivedError(WebView webView, int i2, String str, String str2) {
        }

        @Override // com.jingdong.common.web.uilistener.WebViewClientListener
        public void shouldOverrideUrlLoading(WebView webView, String str) {
        }
    }

    /* loaded from: classes4.dex */
    class b implements WebViewClientListener {
        final /* synthetic */ WebPageListener a;

        b(b0 b0Var, WebPageListener webPageListener) {
            this.a = webPageListener;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void a(WebPageListener webPageListener, WebView webView) {
            if (webPageListener == null || webView == null) {
                return;
            }
            try {
                webPageListener.onPageFinished(webView.getTitle());
            } catch (Exception e2) {
                OKLog.e(b0.d, e2.getMessage());
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void b(WebPageListener webPageListener, WebView webView, String str, Bitmap bitmap) {
            if (webPageListener == null || webView == null) {
                return;
            }
            try {
                webPageListener.onPageStarted(str, bitmap);
            } catch (Exception e2) {
                OKLog.e(b0.d, e2.getMessage());
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void c(WebPageListener webPageListener, WebView webView, int i2, String str, String str2) {
            if (webPageListener == null || webView == null) {
                return;
            }
            try {
                webPageListener.onReceivedError(i2, str, str2);
            } catch (Exception e2) {
                OKLog.e(b0.d, e2.getMessage());
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void d(WebPageListener webPageListener, WebView webView, String str) {
            if (webPageListener == null || webView == null) {
                return;
            }
            try {
                webPageListener.shouldOverrideUrlLoading(str);
            } catch (Exception e2) {
                OKLog.e(b0.d, e2.getMessage());
            }
        }

        @Override // com.jingdong.common.web.uilistener.WebViewClientListener
        public void onPageFinished(final WebView webView, String str) {
            if (webView == null || str == null) {
                return;
            }
            final WebPageListener webPageListener = this.a;
            webView.post(new Runnable() { // from class: com.jingdong.app.mall.im.business.c
                @Override // java.lang.Runnable
                public final void run() {
                    b0.b.a(WebPageListener.this, webView);
                }
            });
        }

        @Override // com.jingdong.common.web.uilistener.WebViewClientListener
        public void onPageStarted(final WebView webView, final String str, final Bitmap bitmap) {
            if (webView == null) {
                return;
            }
            final WebPageListener webPageListener = this.a;
            webView.post(new Runnable() { // from class: com.jingdong.app.mall.im.business.e
                @Override // java.lang.Runnable
                public final void run() {
                    b0.b.b(WebPageListener.this, webView, str, bitmap);
                }
            });
        }

        @Override // com.jingdong.common.web.uilistener.WebViewClientListener
        public void onReceivedError(final WebView webView, final int i2, final String str, final String str2) {
            if (webView == null) {
                return;
            }
            final WebPageListener webPageListener = this.a;
            webView.post(new Runnable() { // from class: com.jingdong.app.mall.im.business.b
                @Override // java.lang.Runnable
                public final void run() {
                    b0.b.c(WebPageListener.this, webView, i2, str, str2);
                }
            });
        }

        @Override // com.jingdong.common.web.uilistener.WebViewClientListener
        public void shouldOverrideUrlLoading(final WebView webView, final String str) {
            if (webView == null) {
                return;
            }
            final WebPageListener webPageListener = this.a;
            webView.post(new Runnable() { // from class: com.jingdong.app.mall.im.business.d
                @Override // java.lang.Runnable
                public final void run() {
                    b0.b.d(WebPageListener.this, webView, str);
                }
            });
        }
    }

    @Override // com.jingdong.service.impl.IMXview, com.jingdong.service.service.XVIewService
    public Fragment getWebFragment(String str) {
        OKLog.d("bundleicssdkservice", d + "--- getWebFragment url:" + str);
        try {
            this.a = new CommonMFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", str);
            bundle.putBoolean("isTopBarGone", true);
            bundle.putBoolean(MBaseKeyNames.IS_NEED_SHARE, false);
            bundle.putBoolean("canUseDarkMode", false);
            bundle.putBoolean(MKeyNames.NEED_CHECK_NATIVE, true);
            this.a.setArguments(bundle);
        } catch (Exception unused) {
        }
        return this.a;
    }

    @Override // com.jingdong.service.impl.IMXview, com.jingdong.service.service.XVIewService
    public void loadUrl(String str) {
        super.loadUrl(str);
        try {
            X5WebView x5WebView = this.f11128c;
            if (x5WebView != null) {
                x5WebView.loadUrl(str);
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.service.impl.IMXview, com.jingdong.service.service.XVIewService
    public void setJavascriptEnable(boolean z) {
        super.setJavascriptEnable(z);
        try {
            X5WebView x5WebView = this.f11128c;
            if (x5WebView != null) {
                x5WebView.getSettings().setJavaScriptEnabled(z);
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.service.impl.IMXview, com.jingdong.service.service.XVIewService
    public void setOnPageFinishedListener(WebPageFinishedListener webPageFinishedListener) {
        OKLog.d("bundleicssdkservice", d + "--- setOnPageFinishedListener");
        CommonMFragment commonMFragment = this.a;
        if (commonMFragment != null) {
            this.b = commonMFragment.getJdWebView();
        }
        JDWebView jDWebView = this.b;
        if (jDWebView != null) {
            try {
                this.f11128c = jDWebView.getWebView();
                this.b.setWebViewClientListener(new a(this, webPageFinishedListener));
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.jingdong.service.impl.IMXview, com.jingdong.service.service.XVIewService
    public void setOnWebPageListener(WebPageListener webPageListener) {
        OKLog.d("bundleicssdkservice", d + "--- setOnWebPageListener");
        CommonMFragment commonMFragment = this.a;
        if (commonMFragment != null) {
            this.b = commonMFragment.getJdWebView();
        }
        JDWebView jDWebView = this.b;
        if (jDWebView != null) {
            try {
                this.f11128c = jDWebView.getWebView();
                this.b.setWebViewClientListener(new b(this, webPageListener));
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.jingdong.service.impl.IMXview, com.jingdong.service.service.XVIewService
    public void showDialog(FragmentManager fragmentManager, Activity activity, String str, String str2, boolean z) {
        OKLog.d("bundleicssdkservice", d + "--- showDialog link:" + str + " pageName: " + str2 + " needCloseButton: " + z);
        try {
            RedPacketRainDialog.e(activity, str, str2, z).f(fragmentManager);
        } catch (Throwable unused) {
        }
    }

    @Override // com.jingdong.service.impl.IMXview, com.jingdong.service.service.XVIewService
    public boolean webViewGoBack() {
        OKLog.d("bundleicssdkservice", d + "--- webViewGoBack");
        CommonMFragment commonMFragment = this.a;
        if (commonMFragment != null) {
            this.b = commonMFragment.getJdWebView();
        }
        try {
            JDWebView jDWebView = this.b;
            if (jDWebView != null) {
                this.f11128c = jDWebView.getWebView();
                return this.b.onBack();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
