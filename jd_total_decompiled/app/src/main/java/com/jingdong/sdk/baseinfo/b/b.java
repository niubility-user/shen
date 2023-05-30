package com.jingdong.sdk.baseinfo.b;

import android.text.TextUtils;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.android.sdk.coreinfo.util.Logger;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.baseinfo.c.c;
import com.jingdong.sdk.baseinfo.c.d;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class b {
    private static final HashMap<String, a> a = new HashMap<>();
    private static JSONArray b;

    public static void a() {
        String string = d.a().getString("call_records", "");
        if (TextUtils.isEmpty(string)) {
            Logger.e("BaseInfo.record", "no saved data");
            return;
        }
        Logger.d("BaseInfo.record", "read encrypted:".concat(String.valueOf(string)));
        String b2 = c.b(string);
        Logger.d("BaseInfo.record", "after decrypted:".concat(String.valueOf(b2)));
        try {
            b = new JSONObject(b2).optJSONArray("records");
        } catch (JSONException e2) {
            Logger.e("BaseInfo.record", "parse error ", e2);
        }
    }

    public static void a(String str, String str2, String str3, String str4, long j2) {
        a aVar;
        String str5 = str + "-" + str4;
        HashMap<String, a> hashMap = a;
        synchronized (hashMap) {
            aVar = hashMap.get(str5);
            if (aVar == null) {
                aVar = new a(str, str2);
                hashMap.put(str5, aVar);
            }
            aVar.f14684c = str3;
            aVar.d++;
            aVar.f14685e = str4;
            aVar.f14686f = j2;
        }
        Logger.d("BaseInfo.record", "addPrivacyInfo: " + str + ", " + str2 + ", count=" + aVar.d);
    }

    public static JSONArray b() {
        return b;
    }

    public static JSONArray c() {
        HashMap<String, a> hashMap = a;
        synchronized (hashMap) {
            b = e();
            hashMap.clear();
        }
        return b;
    }

    public static void d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", BaseInfo.getAppVersionName());
            jSONObject.put("records", e());
        } catch (JSONException unused) {
        }
        String jSONObject2 = jSONObject.toString();
        d.a().edit().putString("call_records", c.a(jSONObject2)).apply();
        Logger.d("BaseInfo.record", "save to disk:".concat(String.valueOf(jSONObject2)));
    }

    private static JSONArray e() {
        JSONArray jSONArray = new JSONArray();
        try {
            for (a aVar : a.values()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("key", aVar.a);
                jSONObject.put("name", aVar.b);
                jSONObject.put("value", aVar.f14684c);
                jSONObject.put("count", aVar.d);
                jSONObject.put("pin", aVar.f14685e);
                jSONObject.put(VerifyTracker.KEY_TIMESTAMP, aVar.f14686f);
                jSONArray.put(jSONObject);
            }
        } catch (JSONException unused) {
        }
        return jSONArray;
    }
}
