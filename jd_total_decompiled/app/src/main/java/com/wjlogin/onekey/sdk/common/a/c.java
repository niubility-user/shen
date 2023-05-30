package com.wjlogin.onekey.sdk.common.a;

import android.content.Context;
import com.wjlogin.onekey.sdk.common.a.a.g;
import com.wjlogin.onekey.sdk.common.a.a.h;
import com.wjlogin.onekey.sdk.common.a.b.d;
import com.wjlogin.onekey.sdk.common.listener.OnResponseCallback;
import com.wjlogin.onekey.sdk.util.Constans;
import com.wjlogin.onekey.sdk.util.LogUtil;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class c {
    private static c a = null;
    private static int b = 3000;

    /* renamed from: c */
    private static final String f18360c = "WJLogin.OneKey.TelecomLoginHelper";

    public static c a(String str, String str2) {
        d.b = str;
        d.f18347c = str2;
        if (a == null) {
            synchronized (c.class) {
                a = new c();
            }
        }
        return a;
    }

    public void b(Context context, OnResponseCallback onResponseCallback) {
        if (LogUtil.enableLog) {
            LogUtil.LogI(f18360c, " preGetMobileCT ");
        }
        JSONObject jSONObject = Constans.CT_PREGETMOBILE;
        if (jSONObject != null) {
            onResponseCallback.onSuccess(jSONObject);
            return;
        }
        g gVar = new g(context, d.d, com.wjlogin.onekey.sdk.common.a.a.a.a(com.wjlogin.onekey.sdk.common.a.a.a.f18322c));
        gVar.a(b);
        gVar.a(new a(this, onResponseCallback));
        h.a().a(gVar);
    }

    public void a(Context context, OnResponseCallback onResponseCallback) {
        if (LogUtil.enableLog) {
            LogUtil.LogI(f18360c, " getAccessCodeCT ");
        }
        g gVar = new g(context, d.d, com.wjlogin.onekey.sdk.common.a.a.a.a(com.wjlogin.onekey.sdk.common.a.a.a.d));
        gVar.a(b);
        gVar.a(new b(this, onResponseCallback));
        h.a().a(gVar);
    }

    public void a(int i2) {
        b = i2;
    }
}
