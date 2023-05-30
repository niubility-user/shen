package com.jd.jdsec.a.l;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class c {
    public static void a(JSONObject jSONObject, JSONObject... jSONObjectArr) {
        if (jSONObjectArr == null || jSONObject == null) {
            return;
        }
        try {
            for (JSONObject jSONObject2 : jSONObjectArr) {
                if (jSONObject2 != null) {
                    Iterator<String> keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String obj = keys.next().toString();
                        try {
                            jSONObject.put(obj, jSONObject2.opt(obj));
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }
}
