package com.jingdong.sdk.jweb.sys;

import android.content.Context;
import com.jingdong.sdk.jweb.JDWebView;
import com.jingdong.sdk.jweb.JSContext;
import com.jingdong.sdk.jweb.JWebFactory;
import com.jingdong.sdk.jweb.JWebView;

/* loaded from: classes7.dex */
public class c {
    private static c a;

    public static c a() {
        if (a == null) {
            a = new c();
        }
        return a;
    }

    public JSContext a(Context context) {
        return new b(context);
    }

    public JWebView a(JDWebView jDWebView) {
        return new a(jDWebView);
    }

    public void a(Context context, JWebFactory.InitCallback initCallback) {
        if (initCallback != null) {
            initCallback.onFinish(true);
        }
    }

    public boolean b() {
        return false;
    }
}
