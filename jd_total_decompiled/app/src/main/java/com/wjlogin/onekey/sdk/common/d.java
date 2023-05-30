package com.wjlogin.onekey.sdk.common;

import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.unicom.xiaowo.login.ResultListener;
import com.wjlogin.onekey.sdk.common.listener.OnResponseCallback;
import com.wjlogin.onekey.sdk.util.Constans;
import com.wjlogin.onekey.sdk.util.LogUtil;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class d implements ResultListener {
    final /* synthetic */ OnResponseCallback a;
    final /* synthetic */ OneKeyLoginHelper b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(OneKeyLoginHelper oneKeyLoginHelper, OnResponseCallback onResponseCallback) {
        this.b = oneKeyLoginHelper;
        this.a = onResponseCallback;
    }

    @Override // com.unicom.xiaowo.login.ResultListener
    public void onResult(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                this.b.a(this.a, com.wjlogin.onekey.sdk.util.a.a("-2", Constans.responseError, "CU"));
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            if (LogUtil.enableLog) {
                StringBuilder sb = new StringBuilder();
                sb.append("getAccessCodeCU mUnicomResult = ");
                sb.append(jSONObject.toString());
                LogUtil.LogI("WJLogin.OneKey.OneKeyLoginHelper", sb.toString());
            }
            String optString = jSONObject.optString("resultCode");
            String optString2 = jSONObject.optString(CartConstant.KEY_CART_RESULTMSG);
            if (!"0".equals(optString)) {
                this.b.a(this.a, com.wjlogin.onekey.sdk.util.a.a(optString, optString2, "CU"));
                return;
            }
            String optString3 = jSONObject.optString("resultData");
            if (TextUtils.isEmpty(optString3)) {
                return;
            }
            JSONObject jSONObject2 = new JSONObject(optString3);
            this.b.b(this.a, com.wjlogin.onekey.sdk.util.a.b(optString, jSONObject2.optString("accessCode"), jSONObject2.optString("mobile"), "CU"));
        } catch (Exception e2) {
            if (LogUtil.enableLog) {
                LogUtil.LogE("WJLogin.OneKey.OneKeyLoginHelper", "getAccessCodeCU UnicomExp: " + e2);
            }
            this.b.a(this.a, com.wjlogin.onekey.sdk.util.a.a("-2", Constans.responseError, "CU"));
        }
    }
}
