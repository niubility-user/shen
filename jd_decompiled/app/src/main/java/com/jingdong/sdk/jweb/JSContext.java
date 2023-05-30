package com.jingdong.sdk.jweb;

import android.webkit.ValueCallback;
import java.net.URL;
import java.nio.ByteBuffer;

/* loaded from: classes7.dex */
public interface JSContext {
    void addJavascriptInterface(Object obj, String str);

    boolean canPauseAndResume();

    boolean canSetTitle();

    boolean canUseNativeBuffer();

    void cleanup();

    void evaluateJavascript(String str, ValueCallback<String> valueCallback);

    void evaluateJavascript(String str, ValueCallback<String> valueCallback, URL url);

    ByteBuffer getNativeBuffer(int i2);

    int getNativeBufferId();

    void init();

    void pause();

    void resume();

    void setJsExceptionHandler(JSExceptionHandler jSExceptionHandler);

    void setNativeBuffer(int i2, ByteBuffer byteBuffer);

    void setTitle(String str);
}
