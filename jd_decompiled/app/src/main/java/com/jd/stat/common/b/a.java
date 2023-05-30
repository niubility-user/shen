package com.jd.stat.common.b;

import android.content.Context;
import com.jd.stat.common.EncryptUtil;
import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class a {
    public static JSONObject a(Context context, JSONObject jSONObject) {
        return a(context, jSONObject.toString());
    }

    public static JSONObject a(Context context, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appsign", "android_" + com.jd.stat.common.c.c(context) + CartConstant.KEY_YB_INFO_LINK + g.a(context.getPackageName()));
            if (EncryptUtil.a()) {
                HashMap<String, String> encrypt = EncryptUtil.encrypt(context, str, "2.5.8", true);
                jSONObject.put(DataCompassUtils.MODULE_TYPE_HEAD, encrypt.get(DataCompassUtils.MODULE_TYPE_HEAD));
                jSONObject.put("info", encrypt.get("info"));
                return jSONObject;
            }
            return jSONObject;
        } catch (Throwable unused) {
            return new JSONObject();
        }
    }
}
