package com.wjlogin.onekey.sdk.common;

import com.wjlogin.onekey.sdk.common.listener.OnResponseCallback;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class e implements Runnable {
    final /* synthetic */ OnResponseCallback a;
    final /* synthetic */ JSONObject b;

    /* renamed from: c */
    final /* synthetic */ OneKeyLoginHelper f18361c;

    public e(OneKeyLoginHelper oneKeyLoginHelper, OnResponseCallback onResponseCallback, JSONObject jSONObject) {
        this.f18361c = oneKeyLoginHelper;
        this.a = onResponseCallback;
        this.b = jSONObject;
    }

    @Override // java.lang.Runnable
    public void run() {
        OnResponseCallback onResponseCallback = this.a;
        if (onResponseCallback != null) {
            onResponseCallback.onFail(this.b);
        }
    }
}
