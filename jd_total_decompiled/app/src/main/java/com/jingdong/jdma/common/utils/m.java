package com.jingdong.jdma.common.utils;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class m {
    public static String a(HashMap<String, String> hashMap) {
        try {
            if (LogUtil.isDebug()) {
                LogUtil.w("StringUtil", "recordMap->" + hashMap);
            }
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                String key = entry.getKey();
                String valueOf = entry.getValue() != null ? String.valueOf(entry.getValue()) : "";
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(valueOf)) {
                    jSONObject.put(key, valueOf);
                }
            }
            return jSONObject.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static long b(String str) {
        if (TextUtils.isEmpty(str) || !TextUtils.isDigitsOnly(str)) {
            return 0L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }
}
