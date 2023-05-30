package com.jd.fireeye.b;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class e {
    public static JSONObject a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appCount", "");
            jSONObject.put("appList", "");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
