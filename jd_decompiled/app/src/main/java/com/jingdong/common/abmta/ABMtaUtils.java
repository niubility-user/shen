package com.jingdong.common.abmta;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class ABMtaUtils {
    private static String touchstoneKey = "touchstone_expids";

    public static void joinABTest(JDJSONObject jDJSONObject, HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            return;
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            jDJSONObject.put(entry.getKey(), (Object) entry.getValue());
        }
    }

    public static String joinJsonParamsWithAbTest(String str, HashMap<String, String> hashMap) {
        if (TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDSearch", "abMtaTest", "abMtaTest", "0"), "0") || hashMap == null || hashMap.size() == 0) {
            return str;
        }
        if (TextUtils.isEmpty(str)) {
            return new JSONObject(hashMap).toString();
        }
        Object parse = JDJSON.parse(str);
        if (parse instanceof JDJSONArray) {
            JDJSONArray jDJSONArray = (JDJSONArray) parse;
            Iterator<Object> it = jDJSONArray.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof JDJSONObject) {
                    joinABTest((JDJSONObject) next, hashMap);
                }
            }
            return jDJSONArray.toJSONString();
        } else if (parse instanceof JDJSONObject) {
            JDJSONObject jDJSONObject = (JDJSONObject) parse;
            joinABTest(jDJSONObject, hashMap);
            return jDJSONObject.toJSONString();
        } else {
            return str;
        }
    }

    public static void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        JDMtaUtils.sendClickDataWithExt(context, str, str2, str3, str4, str5, str6, str7, joinJsonParamsWithAbTest(str8, hashMap2), hashMap);
    }

    public static void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        JDMtaUtils.sendExposureDataWithExt(context, str, str2, str3, str4, str5, joinJsonParamsWithAbTest(str6, hashMap2), hashMap);
    }

    public static void sendClickDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        JDMtaUtils.sendClickDataWithExt(context, str, str2, str3, str4, str5, str6, str7, joinJsonParamsWithAbTest(str8, hashMap2), str9, str10, str11, str12, hashMap);
    }

    public static void sendExposureDataWithExt(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        JDMtaUtils.sendExposureDataWithExt(context, str, str2, str3, str4, str5, joinJsonParamsWithAbTest(str6, hashMap2), str7, str8, str9, hashMap);
    }

    public static void joinABTest(JSONObject jSONObject, HashMap<String, String> hashMap) {
        if (jSONObject == null || hashMap == null || hashMap.size() == 0) {
            return;
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            try {
                jSONObject.put(entry.getKey(), entry.getValue());
            } catch (Exception unused) {
            }
        }
    }

    public static void joinABTest(JDJSONObject jDJSONObject, Object obj) {
        if (obj == null) {
            return;
        }
        jDJSONObject.put(touchstoneKey, obj);
    }

    public static void joinABTest(JSONObject jSONObject, Object obj) {
        if (jSONObject == null || obj == null) {
            return;
        }
        try {
            jSONObject.put(touchstoneKey, obj);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
