package com.jingdong.sdk.jweb.x5;

import android.content.Context;
import android.util.SparseArray;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import com.jingdong.sdk.jweb.JSContext;
import com.jingdong.sdk.jweb.JSExceptionHandler;
import com.tencent.smtt.sdk.JsContext;
import com.tencent.smtt.sdk.JsError;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes7.dex */
public class c implements JSContext {
    private Context a;
    private JsContext b;

    /* renamed from: c  reason: collision with root package name */
    private JSExceptionHandler f15164c;
    private C0735c d;

    /* loaded from: classes7.dex */
    class a implements ValueCallback<String> {
        a(c cVar) {
        }

        @Override // android.webkit.ValueCallback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onReceiveValue(String str) {
        }
    }

    /* loaded from: classes7.dex */
    class b implements JsContext.ExceptionHandler {
        b() {
        }

        @Override // com.tencent.smtt.sdk.JsContext.ExceptionHandler
        public void handleException(JsContext jsContext, JsError jsError) {
            if (jsError == null) {
                return;
            }
            String message = jsError.getMessage();
            String.format("handleException(%s)", message);
            if (c.this.f15164c != null) {
                c.this.f15164c.handleException(c.this, message);
            }
        }
    }

    /* renamed from: com.jingdong.sdk.jweb.x5.c$c  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    static class C0735c {
        private static final AtomicInteger b = new AtomicInteger(1);
        SparseArray<byte[]> a = new SparseArray<>();

        C0735c() {
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

    public c(Context context) {
        this.a = context;
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    @JavascriptInterface
    public void addJavascriptInterface(Object obj, String str) {
        this.b.addJavascriptInterface(obj, str);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public boolean canPauseAndResume() {
        return true;
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public boolean canSetTitle() {
        return false;
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public boolean canUseNativeBuffer() {
        return false;
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void cleanup() {
        this.b.destroy();
        this.d.a.clear();
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    @JavascriptInterface
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        this.b.evaluateJavascript(str, valueCallback);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    @JavascriptInterface
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback, URL url) {
        String.format("evaluateJavascriptWithURL(%s)", url);
        this.b.evaluateJavascript(str, valueCallback, url);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public ByteBuffer getNativeBuffer(int i2) {
        byte[] nativeBuffer = this.d.getNativeBuffer(i2);
        if (nativeBuffer == null || nativeBuffer.length <= 0) {
            return null;
        }
        return ByteBuffer.wrap(nativeBuffer);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public int getNativeBufferId() {
        return this.d.getNativeBufferId();
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    @JavascriptInterface
    public void init() {
        this.b = new JsContext(this.a);
        C0735c c0735c = new C0735c();
        this.d = c0735c;
        this.b.addJavascriptInterface(c0735c, "nativeBufferCompat");
        this.b.evaluateJavascript("function getNativeBufferId(){return nativeBufferCompat?nativeBufferCompat.getNativeBufferId():-1}function setNativeBuffer(a,b){return nativeBufferCompat?nativeBufferCompat.setNativeBuffer(a,b):void 0}function getNativeBuffer(a){return nativeBufferCompat?nativeBufferCompat.getNativeBuffer(a):void 0}", new a(this));
        this.b.setExceptionHandler(new b());
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void pause() {
        this.b.virtualMachine().onPause();
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void resume() {
        this.b.virtualMachine().onResume();
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void setJsExceptionHandler(JSExceptionHandler jSExceptionHandler) {
        this.f15164c = jSExceptionHandler;
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
        this.d.setNativeBuffer(i2, array);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void setTitle(String str) {
    }
}
