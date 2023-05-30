package com.jd.lib.babel.servicekit.util;

import android.os.Bundle;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class JumpUtil {
    public static final String DES_ADDCART = "addtocart";
    public static final String DES_ADDCARTS = "batchaddtocart";
    public static final String VALUE_DES_BABEL = "babel";
    public static final String VAULE_DES_M = "m";

    public static Bundle getBundleFromJson(JSONObject jSONObject) {
        Bundle bundle = new Bundle();
        if (jSONObject == null) {
            return bundle;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object opt = jSONObject.opt(next);
            if (opt != null) {
                if (opt instanceof String) {
                    bundle.putString(next, (String) opt);
                } else if (opt instanceof Integer) {
                    bundle.putInt(next, ((Integer) opt).intValue());
                } else if (opt instanceof Boolean) {
                    bundle.putBoolean(next, ((Boolean) opt).booleanValue());
                } else if (opt instanceof Long) {
                    bundle.putLong(next, ((Long) opt).longValue());
                } else if (opt instanceof JSONArray) {
                    bundle.putString(next, opt.toString());
                } else if (opt instanceof JSONObject) {
                    bundle.putString(next, opt.toString());
                }
            }
        }
        return bundle;
    }

    public static Bundle getBundleFromJsonString(String str) {
        try {
            return getBundleFromJson(new JSONObject(str));
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
