package com.jingdong.sdk.jweb.sys;

import android.content.Context;
import android.os.Build;
import android.util.SparseArray;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.jingdong.sdk.jweb.JSContext;
import com.jingdong.sdk.jweb.JSExceptionHandler;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes7.dex */
public class b implements JSContext {
    private Context a;
    private WebView b;

    /* renamed from: c  reason: collision with root package name */
    private C0733b f15163c;

    /* loaded from: classes7.dex */
    class a implements ValueCallback<String> {
        a(b bVar) {
        }

        @Override // android.webkit.ValueCallback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onReceiveValue(String str) {
        }
    }

    /* renamed from: com.jingdong.sdk.jweb.sys.b$b  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    static class C0733b {
        private static final AtomicInteger b = new AtomicInteger(1);
        SparseArray<byte[]> a = new SparseArray<>();

        C0733b() {
        }

        @JavascriptInterface
        public final byte[] getNativeBuffer(int i2) {
            byte[] bArr = this.a.get(i2);
            this.a.remove(i2);
            return bArr;
        }

        @JavascriptInterface
        public final int getNativeBufferId() {
            AtomicInteger atomicInteger;
            int i2;
            int i3;
            do {
                atomicInteger = b;
                i2 = atomicInteger.get();
                i3 = i2 + 1;
                if (i3 > 16777216) {
                    i3 = 1;
                }
            } while (!atomicInteger.compareAndSet(i2, i3));
            return i2;
        }

        @JavascriptInterface
        public final void setNativeBuffer(int i2, byte[] bArr) {
            this.a.put(i2, bArr);
        }
    }

    public b(Context context) {
        this.a = context;
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    @JavascriptInterface
    public void addJavascriptInterface(Object obj, String str) {
        try {
            this.b.addJavascriptInterface(obj, str);
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public boolean canPauseAndResume() {
        return true;
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public boolean canSetTitle() {
        return true;
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public boolean canUseNativeBuffer() {
        return false;
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    @JavascriptInterface
    public void cleanup() {
        this.b.destroy();
        this.f15163c.a.clear();
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    @JavascriptInterface
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.b.evaluateJavascript(str, valueCallback);
            return;
        }
        try {
            if (!str.startsWith("javascript:")) {
                str = "javascript:" + str;
            }
            this.b.loadUrl(str);
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback, URL url) {
        String.format("evaluateJavascriptWithURL(%s)", url);
        evaluateJavascript(str, valueCallback);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public ByteBuffer getNativeBuffer(int i2) {
        byte[] nativeBuffer = this.f15163c.getNativeBuffer(i2);
        if (nativeBuffer == null || nativeBuffer.length <= 0) {
            return null;
        }
        return ByteBuffer.wrap(nativeBuffer);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public int getNativeBufferId() {
        return this.f15163c.getNativeBufferId();
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    @JavascriptInterface
    public void init() {
        WebView webView = new WebView(this.a);
        this.b = webView;
        webView.getSettings().setAllowFileAccess(false);
        this.b.getSettings().setSavePassword(false);
        this.b.getSettings().setJavaScriptEnabled(true);
        this.b.loadUrl("about://blank");
        C0733b c0733b = new C0733b();
        this.f15163c = c0733b;
        this.b.addJavascriptInterface(c0733b, "nativeBufferCompat");
        if (Build.VERSION.SDK_INT >= 19) {
            this.b.evaluateJavascript("function getNativeBufferId(){return nativeBufferCompat?nativeBufferCompat.getNativeBufferId():-1}function setNativeBuffer(a,b){return nativeBufferCompat?nativeBufferCompat.setNativeBuffer(a,b):void 0}function getNativeBuffer(a){return nativeBufferCompat?nativeBufferCompat.getNativeBuffer(a):void 0};window.getNativeBufferId=getNativeBufferId;", new a(this));
            return;
        }
        try {
            this.b.loadUrl("javascript:function getNativeBufferId(){return nativeBufferCompat?nativeBufferCompat.getNativeBufferId():-1}function setNativeBuffer(a,b){return nativeBufferCompat?nativeBufferCompat.setNativeBuffer(a,b):void 0}function getNativeBuffer(a){return nativeBufferCompat?nativeBufferCompat.getNativeBuffer(a):void 0};window.getNativeBufferId=getNativeBufferId;");
        } catch (Exception e2) {
            String.format("evaluateJavascript failed : %s", e2.getMessage());
        }
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void pause() {
        this.b.onPause();
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void resume() {
        this.b.onResume();
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void setJsExceptionHandler(JSExceptionHandler jSExceptionHandler) {
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void setNativeBuffer(int i2, ByteBuffer byteBuffer) {
        byte[] array;
        if (byteBuffer == null) {
            array = new byte[0];
        } else if (byteBuffer.isDirect()) {
            int position = byteBuffer.position();
            byteBuffer.position(0);
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            byteBuffer.position(position);
            array = bArr;
        } else {
            array = byteBuffer.array();
        }
        this.f15163c.setNativeBuffer(i2, array);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    @JavascriptInterface
    public void setTitle(String str) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.b.evaluateJavascript("document.title =\"" + str + "\"", null);
            return;
        }
        try {
            this.b.loadUrl("javascript:document.title =\"" + str + "\"");
        } catch (Exception unused) {
        }
    }
}
