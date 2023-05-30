package com.jingdong.manto.jsengine;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.sdk.jweb.JSContext;
import com.jingdong.sdk.jweb.JWebFactory;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes15.dex */
public class f implements IMantoWebViewJS, com.jingdong.manto.jsengine.a, IMantoBaseInterface {
    private final JSContext a;
    private final Handler b;

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f13227c = false;
    private volatile boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    private volatile CountDownLatch f13228e;

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
            f.this.a.evaluateJavascript(this.a, this.b);
        }
    }

    public f(Context context) {
        JSContext createJSContext = JWebFactory.createJSContext(context, JWebFactory.JSContextType.CT_TYPE_X5);
        this.a = createJSContext;
        createJSContext.addJavascriptInterface(this, "JDJsThreadCaller");
        this.b = new Handler(Looper.getMainLooper());
    }

    private void a(String str, ValueCallback<String> valueCallback) {
        if (this.f13227c) {
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
        if (obj == null || MantoStringUtils.isEmpty(str)) {
            return;
        }
        this.a.addJavascriptInterface(obj, str);
    }

    @JavascriptInterface
    public int callFromJsThread() {
        if (this.d) {
            this.d = false;
            this.f13228e = new CountDownLatch(1);
            try {
                this.f13228e.await();
            } catch (InterruptedException e2) {
                MantoLog.e("MantoX5JsEngine", String.format("pause await e = %s", MantoStringUtils.throwable2String(e2)));
            }
        }
        return 1;
    }

    @Override // com.jingdong.manto.jsengine.a
    public boolean canUseNativeBuffer() {
        try {
            if (this.a.canUseNativeBuffer()) {
                return TextUtils.equals(jd.wjlogin_sdk.util.f.f19954c, com.jingdong.manto.c.b());
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.jingdong.manto.jsengine.IMantoWebViewJS
    public void destroy() {
        if (!this.f13227c) {
            this.a.cleanup();
        }
        this.f13227c = true;
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
        return "x5";
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
