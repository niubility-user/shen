package com.jingdong.manto.jsengine;

import android.content.Context;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.core.app.NotificationCompat;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.utils.z;
import com.jingdong.sdk.jweb.sys.Wrapper;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes15.dex */
public class c extends WebView implements IMantoWebViewJS, com.jingdong.manto.jsengine.b {
    boolean a;
    private Handler b;

    /* renamed from: c  reason: collision with root package name */
    private final LinkedList<Pair<String, ValueCallback<String>>> f13222c;
    private boolean d;

    /* renamed from: e  reason: collision with root package name */
    private volatile boolean f13223e;

    /* loaded from: classes15.dex */
    class a extends WebViewClient {
        a() {
        }

        private WebResourceResponse a(String str) {
            if (str.equals(c.this.getEngineUrl())) {
                WebResourceResponse convert = Wrapper.convert(com.jingdong.manto.pkg.b.f.b("NAPageFrame.html"));
                return convert == null ? Build.VERSION.SDK_INT >= 21 ? new WebResourceResponse("image/*", "utf-8", 404, "Not Found", new HashMap(), new ByteArrayInputStream(new byte[0])) : new WebResourceResponse("image/*", "utf-8", new ByteArrayInputStream(new byte[0])) : convert;
            }
            return null;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            synchronized (c.this.f13222c) {
                c.this.d = true;
                Iterator it = c.this.f13222c.iterator();
                while (it.hasNext()) {
                    Pair pair = (Pair) it.next();
                    c.this.a((String) pair.first, (ValueCallback) pair.second);
                }
                c.this.f13222c.clear();
            }
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            sslErrorHandler.cancel();
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (MantoStringUtils.isEmpty(str)) {
                return null;
            }
            return a(str);
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            c cVar = c.this;
            cVar.loadUrl(cVar.getEngineUrl());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.manto.jsengine.c$c  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public class RunnableC0534c implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ ValueCallback b;

        RunnableC0534c(String str, ValueCallback valueCallback) {
            this.a = str;
            this.b = valueCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str;
            if (Build.VERSION.SDK_INT >= 19) {
                c.super.evaluateJavascript(this.a, this.b);
                return;
            }
            if (this.a.startsWith("javascript:")) {
                str = this.a;
            } else {
                str = "javascript:" + this.a;
            }
            c.super.loadUrl(str);
        }
    }

    public c(Context context) {
        super(context);
        this.a = false;
        this.f13222c = new LinkedList<>();
        this.d = false;
        this.f13223e = false;
        this.b = new Handler(Looper.getMainLooper());
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setSavePassword(false);
        settings.setUserAgentString(z.a(context, settings.getUserAgentString()));
        setWebViewClient(new a());
        setWillNotDraw(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, ValueCallback<String> valueCallback) {
        if (this.f13223e) {
            return;
        }
        RunnableC0534c runnableC0534c = new RunnableC0534c(str, valueCallback);
        if (MantoThreadUtils.isMainThread()) {
            runnableC0534c.run();
        } else {
            this.b.post(runnableC0534c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getEngineUrl() {
        return "https://service.vapp.jd.com/js-engine";
    }

    @Override // android.webkit.WebView, com.jingdong.manto.jsengine.IMantoWebViewJS
    public final void addJavascriptInterface(Object obj, String str) {
        if (obj == null || MantoStringUtils.isEmpty(str)) {
            return;
        }
        super.addJavascriptInterface(obj, str);
    }

    @Override // android.webkit.WebView, com.jingdong.manto.jsengine.IMantoWebViewJS
    public void destroy() {
        this.f13223e = true;
        super.destroy();
    }

    @Override // android.webkit.WebView, com.jingdong.manto.jsengine.IMantoWebViewJS
    public final void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (!this.a) {
            this.a = true;
            this.b.post(new b());
        }
        synchronized (this.f13222c) {
            if (this.d) {
                a(str, valueCallback);
            } else {
                this.f13222c.add(new Pair<>(str, valueCallback));
            }
        }
    }

    @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
    public IMantoBaseInterface getInterface(Class cls) {
        if (cls.isInstance(this)) {
            return this;
        }
        return null;
    }

    @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
    public String getName() {
        return NotificationCompat.CATEGORY_SYSTEM;
    }

    @Override // com.jingdong.manto.jsengine.b
    public void setTitle(String str) {
        evaluateJavascript("document.title=\"" + str + "\"", null);
    }
}
