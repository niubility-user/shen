package com.jd.verify.View;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.jd.verify.j.g;
import com.jd.verify.j.h;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.net.URL;
import verify.jd.com.myverify.R;

/* loaded from: classes18.dex */
public class b {
    private Context a;
    private e b;
    private WebViewClient d;

    /* renamed from: e */
    private WebChromeClient f7118e;

    /* renamed from: f */
    private com.jd.verify.common.a f7119f;

    /* renamed from: g */
    private com.jd.verify.View.a f7120g;

    /* renamed from: h */
    private com.jd.verify.View.c f7121h;

    /* renamed from: l */
    private WebView f7125l;

    /* renamed from: c */
    private int f7117c = 10000;

    /* renamed from: i */
    private boolean f7122i = false;

    /* renamed from: j */
    private boolean f7123j = false;

    /* renamed from: k */
    private CountDownTimer f7124k = null;

    /* renamed from: m */
    private boolean f7126m = false;

    /* loaded from: classes18.dex */
    public class a extends CountDownTimer {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(long j2, long j3) {
            super(j2, j3);
            b.this = r1;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            com.jd.verify.j.d.a("timer\u8d85\u65f6\u4e86");
            com.jd.verify.j.d.a("timer\u8d85\u65f6\u4e86 isLoadFinish=" + b.this.f7123j);
            if (b.this.f7125l != null) {
                com.jd.verify.j.d.a("timer\u8d85\u65f6\u4e86  mWebview.getProgress()=" + b.this.f7125l.getProgress());
            }
            if (b.this.f7123j || b.this.f7125l == null || b.this.f7125l.getProgress() >= 100) {
                return;
            }
            if (b.this.f7120g != null) {
                b.this.f7120g.cancel();
            }
            b.this.f7125l.stopLoading();
            if (1 == g.a()) {
                b.this.f7119f.b(13);
            } else {
                if (b.this.f7119f != null) {
                    b.this.f7119f.a(13);
                }
                if (b.this.a != null) {
                    h.a(b.this.a.getResources().getString(R.string.verify_fail));
                }
            }
            b.this.f7125l = null;
            b.this.f7122i = true;
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j2) {
            com.jd.verify.j.d.a("startTimer onTick millisUntilFinished=" + j2);
        }
    }

    /* renamed from: com.jd.verify.View.b$b */
    /* loaded from: classes18.dex */
    public class C0221b extends WebChromeClient {
        private C0221b() {
            b.this = r1;
        }

        @Override // android.webkit.WebChromeClient
        public void onCloseWindow(WebView webView) {
            if (b.this.b != null) {
                b.this.b.cancel();
            }
            if (b.this.f7120g != null) {
                b.this.f7120g.cancel();
            }
            if (b.this.f7119f != null) {
                com.jd.verify.j.d.b("onCloseWindow notifyOver");
                if (1 == g.a()) {
                    b.this.f7119f.b(11);
                } else {
                    b.this.f7119f.a(11);
                }
            }
            super.onCloseWindow(webView);
        }

        /* synthetic */ C0221b(b bVar, a aVar) {
            this();
        }
    }

    /* loaded from: classes18.dex */
    public class c extends WebViewClient {

        /* loaded from: classes18.dex */
        class a implements View.OnClickListener {
            final /* synthetic */ SslErrorHandler a;

            a(SslErrorHandler sslErrorHandler) {
                c.this = r1;
                this.a = sslErrorHandler;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                this.a.cancel();
                if (b.this.f7120g != null) {
                    b.this.f7120g.cancel();
                }
                if (b.this.f7119f != null) {
                    b.this.f7119f.a();
                }
                c cVar = c.this;
                if (cVar.a(b.this.f7121h)) {
                    b.this.f7121h.dismiss();
                    b.this.f7121h = null;
                }
                com.jd.verify.j.d.b("onReceivedSslError cancel");
            }
        }

        /* renamed from: com.jd.verify.View.b$c$b */
        /* loaded from: classes18.dex */
        class ViewOnClickListenerC0222b implements View.OnClickListener {
            final /* synthetic */ SslErrorHandler a;

            ViewOnClickListenerC0222b(SslErrorHandler sslErrorHandler) {
                c.this = r1;
                this.a = sslErrorHandler;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                this.a.proceed();
                b.this.f();
                c cVar = c.this;
                if (cVar.a(b.this.f7121h)) {
                    b.this.f7121h.dismiss();
                    b.this.f7121h = null;
                }
                com.jd.verify.j.d.b("onReceivedSslError proceed");
            }
        }

        private c() {
            b.this = r1;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            com.jd.verify.j.d.a("onPageFinished: " + str);
            b.this.f7123j = true;
            b.this.g();
            com.jd.verify.j.d.a("onPageFinished isAutoHideWebview: " + b.this.f7126m);
            if (b.this.f7126m && b.this.f7125l != null) {
                com.jd.verify.j.d.a("onPageFinished mWebview.setVisibility(View.INVISIBLE ");
                b.this.f7125l.setVisibility(0);
            }
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            com.jd.verify.j.d.a("onPageStarted: " + str);
            super.onPageStarted(webView, str, bitmap);
            b.this.f7123j = false;
            b.this.f();
            if (!b.this.f7126m || b.this.f7125l == null) {
                return;
            }
            b.this.f7125l.setVisibility(4);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            try {
                com.jd.verify.j.d.b("onReceivedError11: " + str2 + ", " + str + ", " + i2);
            } catch (Exception e2) {
                e2.printStackTrace();
                if (b.this.f7119f != null) {
                    if (1 == g.a()) {
                        com.jd.verify.j.d.b("onReceivedError11 notifyLocalError:");
                        b.this.f7119f.b(4);
                    } else {
                        com.jd.verify.j.d.b("onReceivedError11 notifyOver:");
                        h.a(a(R.string.verify_fail));
                        b.this.f7119f.a(4);
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= 23) {
                com.jd.verify.j.d.b("onReceivedError11 return");
                if (b.this.f7119f != null) {
                    if (1 != g.a()) {
                        b.this.f7119f.a(4);
                        return;
                    }
                    com.jd.verify.j.d.b("onReceivedError11 notifyLocalError:");
                    b.this.f7119f.b(4);
                    return;
                }
                return;
            }
            if (URLUtil.isHttpsUrl(str2) || URLUtil.isHttpUrl(str2)) {
                URL url = new URL(str2);
                if ("h5.360buyimg.com".equalsIgnoreCase(url.getHost()) || "jcap.m.jd.com".equalsIgnoreCase(url.getHost()) || "jcap.jd.co.th".equalsIgnoreCase(url.getHost()) || "jcap.jd.id".equalsIgnoreCase(url.getHost()) || "jcap.ochama.com.nl".equalsIgnoreCase(url.getHost()) || "lightcap.m.jd.com".equalsIgnoreCase(url.getHost()) || "beta-lightcap.m.jd.com".equalsIgnoreCase(url.getHost()) || "beta-jcap.m.jd.com".equalsIgnoreCase(url.getHost())) {
                    com.jd.verify.j.d.b("onReceivedError11  isForMainFrame:");
                    if (!str2.endsWith("/favicon.ico")) {
                        com.jd.verify.j.d.b("onReceivedError11 fail");
                        if (b.this.f7119f != null) {
                            if (1 == g.a()) {
                                com.jd.verify.j.d.b("onReceivedError11 notifyLocalError:");
                                b.this.f7119f.b(4);
                            } else {
                                com.jd.verify.j.d.b("onReceivedError11 notifyOver:");
                                b.this.f7119f.a(4);
                            }
                        }
                        if (b.this.f7120g != null) {
                            b.this.f7120g.cancel();
                        }
                    }
                }
                if (!url.getHost().contains(FontsUtil.KEY_MULTI_LIGHT) && !url.getPath().endsWith("/favicon.ico") && 1 != g.a()) {
                    h.a(a(R.string.verify_fail));
                }
            }
            super.onReceivedError(webView, i2, str, str2);
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(21)
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            try {
                boolean isForMainFrame = webResourceRequest.isForMainFrame();
                com.jd.verify.j.d.b("onReceivedError33: " + webResourceRequest.getUrl() + ", " + webResourceResponse.getStatusCode() + ", " + isForMainFrame);
                if (isForMainFrame || "jcap.m.jd.com".equalsIgnoreCase(webResourceRequest.getUrl().getHost()) || "jcap.ochama.com.nl".equalsIgnoreCase(webResourceRequest.getUrl().getHost()) || "jcap.jd.co.th".equalsIgnoreCase(webResourceRequest.getUrl().getHost()) || "jcap.jd.id".equalsIgnoreCase(webResourceRequest.getUrl().getHost()) || "lightcap.m.jd.com".equalsIgnoreCase(webResourceRequest.getUrl().getHost()) || "beta-lightcap.m.jd.com".equalsIgnoreCase(webResourceRequest.getUrl().getHost()) || "beta-jcap.m.jd.com".equalsIgnoreCase(webResourceRequest.getUrl().getHost())) {
                    com.jd.verify.j.d.b("onReceivedError33 fail");
                    if (!webResourceRequest.getUrl().getPath().endsWith("/favicon.ico")) {
                        if (b.this.f7119f != null) {
                            if (1 == g.a()) {
                                com.jd.verify.j.d.b("onReceivedError33  notifyLocalError:");
                                b.this.f7119f.b(4);
                            } else {
                                com.jd.verify.j.d.b("onReceivedError33  notifyOver:");
                                b.this.f7119f.a(4);
                            }
                        }
                        if (b.this.f7120g != null) {
                            b.this.f7120g.cancel();
                        }
                    }
                }
                if (!webResourceRequest.getUrl().getHost().contains(FontsUtil.KEY_MULTI_LIGHT) && !webResourceRequest.getUrl().getPath().endsWith("/favicon.ico") && 1 != g.a()) {
                    h.a(a(R.string.verify_fail));
                }
            } catch (Exception unused) {
                com.jd.verify.j.d.b("onReceivedError33 Exception");
                if (b.this.f7119f != null) {
                    if (1 == g.a()) {
                        com.jd.verify.j.d.b("onReceivedError33  notifyLocalError:");
                        b.this.f7119f.b(4);
                    } else {
                        com.jd.verify.j.d.b("onReceivedError33  notifyOver:");
                        h.a(a(R.string.verify_fail));
                        b.this.f7119f.a(4);
                    }
                }
            }
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            com.jd.verify.j.d.b("onReceivedSslError");
            b.this.g();
            if (com.jd.verify.a.a()) {
                if (b.this.f7121h == null) {
                    if (b.this.a == null) {
                        b.this.a = webView.getContext();
                    }
                    if (b.this.a == null) {
                        return;
                    }
                    Resources resources = b.this.a.getResources();
                    b.this.f7121h = new com.jd.verify.View.c(b.this.a);
                    b.this.f7121h.b(resources.getString(R.string.verify_ssl_tip));
                    b.this.f7121h.a(resources.getString(R.string.verify_no));
                    b.this.f7121h.c(resources.getString(R.string.verify_yes));
                    b.this.f7121h.a(new a(sslErrorHandler));
                    b.this.f7121h.b(new ViewOnClickListenerC0222b(sslErrorHandler));
                }
                b.this.f7121h.show();
                return;
            }
            com.jd.verify.j.d.b("onReceivedSslError IsShowToast = " + com.jd.verify.a.a());
            sslErrorHandler.cancel();
            if (b.this.f7120g != null) {
                b.this.f7120g.cancel();
            }
            if (b.this.f7119f != null) {
                b.this.f7119f.a();
            }
            com.jd.verify.j.d.b("onReceivedSslError cancel");
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            com.jd.verify.j.d.a("shouldOverrideUrlLoading");
            return super.shouldOverrideUrlLoading(webView, str);
        }

        /* synthetic */ c(b bVar, a aVar) {
            this();
        }

        public boolean a(Dialog dialog) {
            return dialog != null && dialog.isShowing();
        }

        private String a(int i2) {
            Context a2 = com.jd.verify.d.a();
            if (a2 == null) {
                a2 = b.this.a;
            }
            return a2 != null ? a2.getResources().getString(i2) : "";
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            try {
                boolean isForMainFrame = webResourceRequest.isForMainFrame();
                com.jd.verify.j.d.b("onReceivedError22: " + webResourceRequest.getUrl() + ", " + ((Object) webResourceError.getDescription()) + ", " + webResourceError.toString() + ", " + isForMainFrame);
                if (isForMainFrame || "jcap.m.jd.com".equalsIgnoreCase(webResourceRequest.getUrl().getHost()) || "jcap.ochama.com.nl".equalsIgnoreCase(webResourceRequest.getUrl().getHost()) || "jcap.jd.co.th".equalsIgnoreCase(webResourceRequest.getUrl().getHost()) || "jcap.jd.id".equalsIgnoreCase(webResourceRequest.getUrl().getHost()) || "lightcap.m.jd.com".equalsIgnoreCase(webResourceRequest.getUrl().getHost()) || "beta-lightcap.m.jd.com".equalsIgnoreCase(webResourceRequest.getUrl().getHost()) || "beta-jcap.m.jd.com".equalsIgnoreCase(webResourceRequest.getUrl().getHost())) {
                    com.jd.verify.j.d.b("onReceivedError22 fail");
                    if (!webResourceRequest.getUrl().getPath().endsWith("/favicon.ico")) {
                        if (b.this.f7119f != null) {
                            if (1 == g.a()) {
                                com.jd.verify.j.d.b("onReceivedError22  notifyLocalError:");
                                b.this.f7119f.b(4);
                            } else {
                                com.jd.verify.j.d.b("onReceivedError22  notifyOver:");
                                b.this.f7119f.a(4);
                            }
                        }
                        if (b.this.f7120g != null) {
                            b.this.f7120g.cancel();
                        }
                    }
                }
                if (!webResourceRequest.getUrl().getHost().contains(FontsUtil.KEY_MULTI_LIGHT) && !webResourceRequest.getUrl().getPath().endsWith("/favicon.ico") && 1 != g.a()) {
                    h.a(a(R.string.verify_fail));
                }
            } catch (Exception unused) {
                com.jd.verify.j.d.b("onReceivedError22 Exception");
                if (b.this.f7119f != null) {
                    if (1 == g.a()) {
                        com.jd.verify.j.d.b("onReceivedError11 notifyLocalError:");
                        b.this.f7119f.b(4);
                    } else {
                        com.jd.verify.j.d.b("onReceivedError11 notifyOver:");
                        h.a(a(R.string.verify_fail));
                        b.this.f7119f.a(4);
                    }
                }
            }
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }
    }

    public b(Context context, WebView webView) {
        a aVar = null;
        this.d = null;
        this.f7118e = null;
        this.a = context;
        this.f7125l = webView;
        this.d = new c(this, aVar);
        this.f7118e = new C0221b(this, aVar);
    }

    public void f() {
        try {
            com.jd.verify.j.d.a("startTimer Timer\u8ba1\u65f6\u5f00\u59cb");
            a aVar = new a(this.f7117c, 1000L);
            this.f7124k = aVar;
            aVar.start();
        } catch (Exception e2) {
            com.jd.verify.j.d.a("Exp", e2);
        }
    }

    public void g() {
        com.jd.verify.j.d.a("Timer\u8ba1\u65f6\u7ed3\u675f");
        try {
            CountDownTimer countDownTimer = this.f7124k;
            if (countDownTimer != null) {
                countDownTimer.cancel();
                this.f7124k = null;
            }
        } catch (Exception e2) {
            com.jd.verify.j.d.a("Exp", e2);
        }
    }

    public WebView c() {
        return this.f7125l;
    }

    public boolean d() {
        return this.f7122i;
    }

    public void e() {
        WebView webView = this.f7125l;
        if (webView == null) {
            return;
        }
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setAllowFileAccess(false);
        settings.setSavePassword(false);
        settings.setCacheMode(-1);
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                this.f7125l.removeJavascriptInterface("searchBoxJavaBridge_");
                this.f7125l.removeJavascriptInterface("accessibility");
                this.f7125l.removeJavascriptInterface("accessibilityTraversal");
            } catch (Throwable unused) {
            }
        }
        this.f7125l.setOverScrollMode(2);
        this.f7125l.setHorizontalScrollBarEnabled(false);
        this.f7125l.setVerticalScrollBarEnabled(false);
        this.f7125l.setWebViewClient(this.d);
        this.f7125l.setWebChromeClient(this.f7118e);
        this.f7125l.onResume();
    }

    public void b(boolean z) {
        this.f7123j = z;
    }

    public com.jd.verify.common.a b() {
        return this.f7119f;
    }

    public void a(com.jd.verify.common.a aVar) {
        this.f7119f = aVar;
    }

    public void a(com.jd.verify.View.a aVar) {
        if (aVar != null) {
            this.f7120g = aVar;
        }
    }

    public void a(e eVar) {
        this.b = eVar;
    }

    public Context a() {
        return this.a;
    }

    public void a(boolean z) {
        this.f7126m = z;
    }
}
