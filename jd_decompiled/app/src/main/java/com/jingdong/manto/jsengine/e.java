package com.jingdong.manto.jsengine;

import android.os.Handler;
import android.os.Looper;
import android.webkit.ValueCallback;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.sdk.jweb.JSContext;
import java.nio.ByteBuffer;

/* loaded from: classes15.dex */
public class e implements IMantoWebViewJS, com.jingdong.manto.jsengine.a, IMantoBaseInterface {
    private final JSContext a;
    private final Handler b;

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f13225c = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ ValueCallback b;

        a(String str, ValueCallback valueCallback) {
            this.a = str;
            this.b = valueCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.a.evaluateJavascript(this.a, this.b);
        }
    }

    public e() {
        com.jingdong.manto.u.d dVar = new com.jingdong.manto.u.d();
        this.a = dVar;
        this.b = new Handler(Looper.getMainLooper());
        dVar.init();
    }

    private void a(String str, ValueCallback<String> valueCallback) {
        if (this.f13225c) {
            return;
        }
        if (MantoThreadUtils.isMainThread()) {
            this.a.evaluateJavascript(str, valueCallback);
            return;
        }
        this.b.post(new a(str, valueCallback));
    }

    @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
    public void addJavascriptInterface(Object obj, String str) {
        this.a.addJavascriptInterface(obj, str);
    }

    @Override // com.jingdong.manto.jsengine.a
    public boolean canUseNativeBuffer() {
        return this.a.canUseNativeBuffer();
    }

    @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
    public void destroy() {
        if (!this.f13225c) {
            this.a.cleanup();
        }
        this.f13225c = true;
    }

    @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        a(str, valueCallback);
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
        return "v8";
    }

    @Override // com.jingdong.manto.jsengine.a
    public ByteBuffer getNativeBuffer(int i2) {
        return this.a.getNativeBuffer(i2);
    }

    @Override // com.jingdong.manto.jsengine.a
    public int getNativeBufferId() {
        return this.a.getNativeBufferId();
    }

    @Override // com.jingdong.manto.jsengine.a
    public void setNativeBuffer(int i2, ByteBuffer byteBuffer) {
        this.a.setNativeBuffer(i2, byteBuffer);
    }
}
