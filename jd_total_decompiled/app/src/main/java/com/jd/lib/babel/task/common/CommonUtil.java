package com.jd.lib.babel.task.common;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class CommonUtil {
    public static Map<String, String> jsonToMap(@Nullable String str) {
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str)) {
            return hashMap;
        }
        try {
            JSONObject jSONObject = new JSONObject(str.trim());
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String optString = jSONObject.optString(next);
                if (!TextUtils.isEmpty(optString)) {
                    hashMap.put(next, optString.trim());
                }
            }
            return hashMap;
        } catch (JSONException unused) {
            return new HashMap();
        }
    }

    public static Map<String, Object> jsonToMapObject(@Nullable String str) {
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str)) {
            return hashMap;
        }
        try {
            JSONObject jSONObject = new JSONObject(str.trim());
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject.get(next);
                if (obj != null) {
                    hashMap.put(next, obj);
                }
            }
        } catch (JSONException unused) {
            hashMap.clear();
        }
        return hashMap;
    }
}
