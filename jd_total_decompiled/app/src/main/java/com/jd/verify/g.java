package com.jd.verify;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.jd.dynamic.DYConstants;
import verify.jd.com.myverify.R;

/* loaded from: classes18.dex */
public class g extends WebView {
    private WebChromeClient a;
    private WebViewClient b;

    /* loaded from: classes18.dex */
    class a extends WebChromeClient {
        a(g gVar) {
        }

        @Override // android.webkit.WebChromeClient
        public void onCloseWindow(WebView webView) {
            super.onCloseWindow(webView);
        }
    }

    public g(Context context) {
        this(context, null);
    }

    private void a() {
        setVisibility(8);
        WebSettings settings = getSettings();
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
                removeJavascriptInterface("searchBoxJavaBridge_");
                removeJavascriptInterface("accessibility");
                removeJavascriptInterface("accessibilityTraversal");
            } catch (Throwable unused) {
            }
        }
        setOverScrollMode(2);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setWebChromeClient(this.a);
        setWebViewClient(this.b);
    }

    public void b() {
        try {
            stopLoading();
            removeAllViews();
            ViewParent parent = getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(this);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            destroy();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        this.a = null;
        this.b = null;
    }

    /* loaded from: classes18.dex */
    class b extends WebViewClient {

        /* loaded from: classes18.dex */
        class a implements View.OnClickListener {
            final /* synthetic */ SslErrorHandler a;
            final /* synthetic */ com.jd.verify.View.c b;

            a(b bVar, SslErrorHandler sslErrorHandler, com.jd.verify.View.c cVar) {
                this.a = sslErrorHandler;
                this.b = cVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                this.a.cancel();
                this.b.dismiss();
            }
        }

        /* renamed from: com.jd.verify.g$b$b  reason: collision with other inner class name */
        /* loaded from: classes18.dex */
        class ViewOnClickListenerC0223b implements View.OnClickListener {
            final /* synthetic */ SslErrorHandler a;
            final /* synthetic */ com.jd.verify.View.c b;

            ViewOnClickListenerC0223b(b bVar, SslErrorHandler sslErrorHandler, com.jd.verify.View.c cVar) {
                this.a = sslErrorHandler;
                this.b = cVar;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                this.a.proceed();
                this.b.dismiss();
            }
        }

        b(g gVar) {
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            com.jd.verify.j.d.a("JDVerify.PreLoadWebView", "onPageFinished " + str);
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            com.jd.verify.j.d.a("JDVerify.PreLoadWebView", "onPageStarted " + str);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i2, String str, String str2) {
            com.jd.verify.j.d.b("JDVerify.PreLoadWebView", "onReceivedError:" + str);
            super.onReceivedError(webView, i2, str, str2);
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(21)
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            com.jd.verify.j.d.b("JDVerify.PreLoadWebView", "onReceivedHttpError:" + webResourceRequest.getUrl() + "   " + webResourceResponse.getStatusCode());
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            com.jd.verify.j.d.b("JDVerify.PreLoadWebView", "onReceivedSslError");
            try {
                com.jd.verify.View.c cVar = new com.jd.verify.View.c(webView.getContext());
                cVar.b(webView.getContext().getString(R.string.verify_ssl_tip));
                cVar.a(webView.getContext().getString(R.string.verify_no));
                cVar.c(webView.getContext().getString(R.string.verify_yes));
                cVar.a(new a(this, sslErrorHandler, cVar));
                cVar.b(new ViewOnClickListenerC0223b(this, sslErrorHandler, cVar));
                cVar.show();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.webkit.WebViewClient
        @TargetApi(23)
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            com.jd.verify.j.d.b("JDVerify.PreLoadWebView", "onReceivedError M:" + webResourceRequest.getUrl() + DYConstants.DY_REGEX_COMMA + ((Object) webResourceError.getDescription()) + DYConstants.DY_REGEX_COMMA + webResourceError.toString());
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }
    }

    public g(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public g(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.a = new a(this);
        this.b = new b(this);
        a();
    }

    public void a(String str) {
        try {
            loadUrl(str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
