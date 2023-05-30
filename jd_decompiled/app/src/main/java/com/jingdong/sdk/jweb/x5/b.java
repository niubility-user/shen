package com.jingdong.sdk.jweb.x5;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import com.jingdong.sdk.jweb.JSContext;
import com.jingdong.sdk.jweb.JSExceptionHandler;
import com.jingdong.sdk.jweb.x5.a;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.X5JsCore;
import java.net.URL;
import java.nio.ByteBuffer;

/* loaded from: classes7.dex */
public class b implements JSContext {
    private Context a;
    private X5JsCore b;

    public b(Context context) {
        this.a = context;
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void addJavascriptInterface(Object obj, String str) {
        this.b.addJavascriptInterface(obj, str);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public boolean canPauseAndResume() {
        return X5JsCore.canUseX5JsCore(this.a) && QbSdk.getTbsVersion(this.a) >= 43600;
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public boolean canSetTitle() {
        return false;
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public boolean canUseNativeBuffer() {
        return X5JsCore.canX5JsCoreUseNativeBuffer(this.a);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void cleanup() {
        this.b.destroy();
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        evaluateJavascript(str, valueCallback, null);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void evaluateJavascript(String str, ValueCallback<String> valueCallback, URL url) {
        this.b.evaluateJavascript(str, new a.i(valueCallback));
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public ByteBuffer getNativeBuffer(int i2) {
        return this.b.getNativeBuffer(i2);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public int getNativeBufferId() {
        return this.b.getNativeBufferId();
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    @JavascriptInterface
    public void init() {
        this.b = new X5JsCore(this.a);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void pause() {
        this.b.pause();
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void resume() {
        this.b.resume();
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void setJsExceptionHandler(JSExceptionHandler jSExceptionHandler) {
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void setNativeBuffer(int i2, ByteBuffer byteBuffer) {
        this.b.setNativeBuffer(i2, byteBuffer);
    }

    @Override // com.jingdong.sdk.jweb.JSContext
    public void setTitle(String str) {
    }
}
