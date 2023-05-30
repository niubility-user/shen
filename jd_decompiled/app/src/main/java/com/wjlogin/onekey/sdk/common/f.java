package com.wjlogin.onekey.sdk.common;

import com.wjlogin.onekey.sdk.common.listener.OnResponseCallback;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class f implements Runnable {
    final /* synthetic */ OnResponseCallback a;
    final /* synthetic */ JSONObject b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ OneKeyLoginHelper f18362c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(OneKeyLoginHelper oneKeyLoginHelper, OnResponseCallback onResponseCallback, JSONObject jSONObject) {
        this.f18362c = oneKeyLoginHelper;
        this.a = onResponseCallback;
        this.b = jSONObject;
    }

    @Override // java.lang.Runnable
    public void run() {
        OnResponseCallback onResponseCallback = this.a;
        if (onResponseCallback != null) {
            onResponseCallback.onSuccess(this.b);
        }
    }
}
