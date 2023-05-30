package com.jingdong.manto.jsengine;

import android.webkit.ValueCallback;

/* loaded from: classes15.dex */
public interface IMantoWebViewJS extends IMantoBaseInterface {
    void addJavascriptInterface(Object obj, String str);

    void destroy();

    void evaluateJavascript(String str, ValueCallback<String> valueCallback);

    <T extends IMantoBaseInterface> T getInterface(Class<T> cls);

    String getName();
}
