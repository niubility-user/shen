package com.unicom.xiaowo.login.b;

import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.unicom.xiaowo.login.ResultListener;
import com.unicom.xiaowo.login.d.d;
import com.unicom.xiaowo.login.d.f;
import com.unicom.xiaowo.login.d.g;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class b {
    private ResultListener a = null;

    public void a(ResultListener resultListener) {
        this.a = resultListener;
    }

    public void a(String str, String str2) {
        try {
            if (this.a == null) {
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("resultCode", 0);
            jSONObject.put(CartConstant.KEY_CART_RESULTMSG, str);
            if (!TextUtils.isEmpty(str2)) {
                JSONObject jSONObject2 = new JSONObject(str2);
                jSONObject2.put("msgId", g.a("" + System.currentTimeMillis()));
                jSONObject2.put("operatorType", (Object) null);
                jSONObject.put("resultData", jSONObject2);
            } else {
                jSONObject.put("resultData", "");
            }
            jSONObject.put("operatorType", "CU");
            this.a.onResult(jSONObject.toString());
            this.a = null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(int i2, String str, String str2) {
        try {
            if (this.a == null) {
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("resultCode", i2);
            jSONObject.put(CartConstant.KEY_CART_RESULTMSG, str);
            jSONObject.put("resultData", "");
            jSONObject.put("traceId", str2);
            jSONObject.put("operatorType", "CU");
            this.a.onResult(jSONObject.toString());
            this.a = null;
            if (i2 < 10000 || i2 > 10099) {
                return;
            }
            if (i2 == 10000) {
                str = "" + f.c();
            }
            d.a("" + i2, str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void a(int i2, String str) {
        a(i2, str, "");
    }
}
