package com.wjlogin.onekey.sdk.common.a;

import com.wjlogin.onekey.sdk.common.listener.OnResponseCallback;
import com.wjlogin.onekey.sdk.util.LogUtil;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class b implements com.wjlogin.onekey.sdk.common.a.a.b {
    final /* synthetic */ OnResponseCallback a;
    final /* synthetic */ c b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(c cVar, OnResponseCallback onResponseCallback) {
        this.b = cVar;
        this.a = onResponseCallback;
    }

    @Override // com.wjlogin.onekey.sdk.common.a.a.b
    public void a(String str) {
        if (LogUtil.enableLog) {
            LogUtil.LogI("WJLogin.OneKey.TelecomLoginHelper", "  getAccessCodeCT  onSuccess result = " + str);
        }
        this.a.onSuccess(com.wjlogin.onekey.sdk.common.a.a.a.c(str));
    }

    @Override // com.wjlogin.onekey.sdk.common.a.a.b
    public void a(JSONObject jSONObject) {
        if (LogUtil.enableLog) {
            LogUtil.LogI("WJLogin.OneKey.TelecomLoginHelper", "  getAccessCodeCT  onError result = " + jSONObject);
        }
        this.a.onFail(jSONObject);
    }
}
