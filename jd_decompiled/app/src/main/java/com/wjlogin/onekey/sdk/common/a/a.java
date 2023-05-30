package com.wjlogin.onekey.sdk.common.a;

import com.wjlogin.onekey.sdk.common.listener.OnResponseCallback;
import com.wjlogin.onekey.sdk.util.Constans;
import com.wjlogin.onekey.sdk.util.LogUtil;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class a implements com.wjlogin.onekey.sdk.common.a.a.b {
    final /* synthetic */ OnResponseCallback a;
    final /* synthetic */ c b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(c cVar, OnResponseCallback onResponseCallback) {
        this.b = cVar;
        this.a = onResponseCallback;
    }

    @Override // com.wjlogin.onekey.sdk.common.a.a.b
    public void a(String str) {
        if (LogUtil.enableLog) {
            LogUtil.LogI("WJLogin.OneKey.TelecomLoginHelper", " preGetMobileCT  onSuccess result = " + str);
        }
        JSONObject c2 = com.wjlogin.onekey.sdk.common.a.a.a.c(str);
        Constans.CT_PREGETMOBILE = c2;
        this.a.onSuccess(c2);
    }

    @Override // com.wjlogin.onekey.sdk.common.a.a.b
    public void a(JSONObject jSONObject) {
        if (LogUtil.enableLog) {
            LogUtil.LogI("WJLogin.OneKey.TelecomLoginHelper", "  preGetMobileCT  onError result = " + jSONObject.toString());
        }
        this.a.onFail(jSONObject);
    }
}
