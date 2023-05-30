package com.wjlogin.onekey.sdk.common;

import android.text.TextUtils;
import com.cmic.sso.sdk.auth.TokenListener;
import com.wjlogin.onekey.sdk.common.listener.OnResponseCallback;
import com.wjlogin.onekey.sdk.util.Constans;
import com.wjlogin.onekey.sdk.util.LogUtil;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class c implements TokenListener {
    final /* synthetic */ OnResponseCallback a;
    final /* synthetic */ OneKeyLoginHelper b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(OneKeyLoginHelper oneKeyLoginHelper, OnResponseCallback onResponseCallback) {
        this.b = oneKeyLoginHelper;
        this.a = onResponseCallback;
    }

    @Override // com.cmic.sso.sdk.auth.TokenListener
    public void onGetTokenComplete(JSONObject jSONObject) {
        if (LogUtil.enableLog) {
            LogUtil.LogE("WJLogin.OneKey.OneKeyLoginHelper", "getAccessCodeCM  success");
        }
        if (jSONObject == null) {
            this.b.a(this.a, com.wjlogin.onekey.sdk.util.a.a("-2", Constans.responseError, "CM"));
            return;
        }
        try {
            String optString = jSONObject.optString("resultCode");
            if (LogUtil.enableLog) {
                StringBuilder sb = new StringBuilder();
                sb.append("getAccessCodeCM onGetTokenComplete jsonObject == ");
                sb.append(jSONObject.toString());
                LogUtil.LogE("WJLogin.OneKey.OneKeyLoginHelper", sb.toString());
            }
            String optString2 = jSONObject.optString("authTypeDes");
            if (!TextUtils.equals(optString, "103000")) {
                this.b.a(this.a, com.wjlogin.onekey.sdk.util.a.a(optString, optString2, "CM"));
                return;
            }
            String optString3 = jSONObject.optString("securityphone");
            this.b.b(this.a, com.wjlogin.onekey.sdk.util.a.b("0", jSONObject.optString("token"), optString3, "CM"));
        } catch (Exception unused) {
            this.b.a(this.a, com.wjlogin.onekey.sdk.util.a.a("-2", Constans.responseError, "CM"));
        }
    }
}
