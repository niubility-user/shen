package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Looper;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsError;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsVirtualMachine;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes9.dex */
public final class JsVirtualMachine {
    private final Context a;
    private final IX5JsVirtualMachine b;

    /* renamed from: c */
    private final HashSet<WeakReference<a>> f17721c;

    /* loaded from: classes9.dex */
    public static class a implements IX5JsContext {
        private WebView a;

        public a(Context context) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                WebView webView = new WebView(context);
                this.a = webView;
                webView.getSettings().setJavaScriptEnabled(true);
                return;
            }
            String str = "in system core, JsContext can just in mainLooper, current looper is " + Looper.myLooper();
        }

        public void a() {
            WebView webView = this.a;
            if (webView == null) {
                return;
            }
            webView.onPause();
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void addJavascriptInterface(Object obj, String str) {
            WebView webView = this.a;
            if (webView == null) {
                return;
            }
            webView.addJavascriptInterface(obj, str);
            this.a.loadUrl("about:blank");
        }

        public void b() {
            WebView webView = this.a;
            if (webView == null) {
                return;
            }
            webView.onResume();
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void destroy() {
            WebView webView = this.a;
            if (webView == null) {
                return;
            }
            webView.clearHistory();
            this.a.clearCache(true);
            this.a.loadUrl("about:blank");
            this.a.freeMemory();
            this.a.pauseTimers();
            this.a.destroy();
            this.a = null;
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void evaluateJavascript(String str, final android.webkit.ValueCallback<String> valueCallback, URL url) {
            WebView webView = this.a;
            if (webView == null) {
                return;
            }
            webView.evaluateJavascript(str, valueCallback == null ? null : new ValueCallback<String>() { // from class: com.tencent.smtt.sdk.JsVirtualMachine.a.1
                {
                    a.this = this;
                }

                @Override // com.tencent.smtt.sdk.ValueCallback, android.webkit.ValueCallback
                /* renamed from: a */
                public void onReceiveValue(String str2) {
                    valueCallback.onReceiveValue(str2);
                }
            });
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public IX5JsValue evaluateScript(String str, URL url) {
            WebView webView = this.a;
            if (webView == null) {
                return null;
            }
            webView.evaluateJavascript(str, null);
            return null;
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void evaluateScriptAsync(String str, final android.webkit.ValueCallback<IX5JsValue> valueCallback, URL url) {
            WebView webView = this.a;
            if (webView == null) {
                return;
            }
            webView.evaluateJavascript(str, valueCallback == null ? null : new ValueCallback<String>() { // from class: com.tencent.smtt.sdk.JsVirtualMachine.a.2
                {
                    a.this = this;
                }

                @Override // com.tencent.smtt.sdk.ValueCallback, android.webkit.ValueCallback
                /* renamed from: a */
                public void onReceiveValue(String str2) {
                    valueCallback.onReceiveValue(null);
                }
            });
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public byte[] getNativeBuffer(int i2) {
            return null;
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public int getNativeBufferId() {
            return -1;
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void removeJavascriptInterface(String str) {
            WebView webView = this.a;
            if (webView == null) {
                return;
            }
            webView.removeJavascriptInterface(str);
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void setExceptionHandler(android.webkit.ValueCallback<IX5JsError> valueCallback) {
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void setName(String str) {
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public int setNativeBuffer(int i2, byte[] bArr) {
            return -1;
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void setPerContextData(Object obj) {
        }

        @Override // com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext
        public void stealValueFromOtherCtx(String str, IX5JsContext iX5JsContext, String str2) {
        }
    }

    public JsVirtualMachine(Context context) {
        this(context, null);
    }

    public JsVirtualMachine(Context context, Looper looper) {
        this.f17721c = new HashSet<>();
        this.a = context;
        this.b = X5JsCore.a(context, looper);
    }

    public IX5JsContext a() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.b;
        if (iX5JsVirtualMachine == null) {
            a aVar = new a(this.a);
            this.f17721c.add(new WeakReference<>(aVar));
            return aVar;
        }
        return iX5JsVirtualMachine.createJsContext();
    }

    public void destroy() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.b;
        if (iX5JsVirtualMachine != null) {
            iX5JsVirtualMachine.destroy();
            return;
        }
        Iterator<WeakReference<a>> it = this.f17721c.iterator();
        while (it.hasNext()) {
            WeakReference<a> next = it.next();
            if (next.get() != null) {
                next.get().destroy();
            }
        }
    }

    public Looper getLooper() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.b;
        return iX5JsVirtualMachine != null ? iX5JsVirtualMachine.getLooper() : Looper.myLooper();
    }

    public boolean isFallback() {
        return this.b == null;
    }

    public void onPause() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.b;
        if (iX5JsVirtualMachine != null) {
            iX5JsVirtualMachine.onPause();
            return;
        }
        Iterator<WeakReference<a>> it = this.f17721c.iterator();
        while (it.hasNext()) {
            WeakReference<a> next = it.next();
            if (next.get() != null) {
                next.get().a();
            }
        }
    }

    public void onResume() {
        IX5JsVirtualMachine iX5JsVirtualMachine = this.b;
        if (iX5JsVirtualMachine != null) {
            iX5JsVirtualMachine.onResume();
            return;
        }
        Iterator<WeakReference<a>> it = this.f17721c.iterator();
        while (it.hasNext()) {
            WeakReference<a> next = it.next();
            if (next.get() != null) {
                next.get().b();
            }
        }
    }
}
