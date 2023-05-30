package com.jingdong.union.a;

import android.os.Bundle;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class c {
    public static String a(Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            try {
                jSONObject.put(str, bundle.get(str));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return jSONObject.toString();
    }
}
