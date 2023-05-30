package com.wjlogin.onekey.sdk.common.a.a;

import android.text.TextUtils;
import com.wjlogin.onekey.sdk.util.Constans;
import com.wjlogin.onekey.sdk.util.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class a {
    private static final String a = "WJLogin.OneKey.DataUtil";
    private static String b = "e1c3d0de067d4666";

    /* renamed from: c  reason: collision with root package name */
    public static final String f18322c = "qhx";
    public static final String d = "qh";

    /* renamed from: e  reason: collision with root package name */
    private static final String f18323e = "30100";

    /* renamed from: f  reason: collision with root package name */
    private static final String f18324f = "json";

    /* renamed from: g  reason: collision with root package name */
    private static final String f18325g = "v1.5";

    /* JADX WARN: Removed duplicated region for block: B:34:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.lang.String r7) {
        /*
            java.lang.String r0 = "WJLogin.OneKey.DataUtil"
            java.lang.String r1 = ""
            long r2 = java.lang.System.currentTimeMillis()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r3 = 0
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: org.json.JSONException -> L7e
            r4.<init>()     // Catch: org.json.JSONException -> L7e
            java.lang.String r5 = "timeStamp"
            r4.put(r5, r2)     // Catch: org.json.JSONException -> L7e
            java.lang.String r2 = "bussinessType"
            r4.put(r2, r7)     // Catch: org.json.JSONException -> L7e
            java.lang.String r7 = r4.toString()     // Catch: org.json.JSONException -> L7e
            java.lang.String r7 = com.wjlogin.onekey.sdk.common.a.b.e.a(r7, r3, r1)     // Catch: org.json.JSONException -> L7e
            java.lang.String r2 = com.wjlogin.onekey.sdk.common.a.a.a.b     // Catch: org.json.JSONException -> L7e
            java.lang.String r7 = a(r7, r2)     // Catch: org.json.JSONException -> L7e
            java.lang.String r2 = com.wjlogin.onekey.sdk.common.a.a.a.b     // Catch: org.json.JSONException -> L7e
            java.lang.String r2 = b(r2)     // Catch: org.json.JSONException -> L7e
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: org.json.JSONException -> L7e
            r4.<init>()     // Catch: org.json.JSONException -> L7e
            java.lang.String r5 = "paramKey"
            r4.put(r5, r2)     // Catch: org.json.JSONException -> L7c
            java.lang.String r5 = "paramStr"
            r4.put(r5, r7)     // Catch: org.json.JSONException -> L7c
            java.lang.String r5 = "clientId"
            java.lang.String r6 = com.wjlogin.onekey.sdk.common.a.b.d.b     // Catch: org.json.JSONException -> L7c
            r4.put(r5, r6)     // Catch: org.json.JSONException -> L7c
            java.lang.String r5 = "clientType"
            java.lang.String r6 = "30100"
            r4.put(r5, r6)     // Catch: org.json.JSONException -> L7c
            java.lang.String r5 = "format"
            java.lang.String r6 = "json"
            r4.put(r5, r6)     // Catch: org.json.JSONException -> L7c
            java.lang.String r5 = "version"
            java.lang.String r6 = "v1.5"
            r4.put(r5, r6)     // Catch: org.json.JSONException -> L7c
            boolean r5 = com.wjlogin.onekey.sdk.util.LogUtil.enableLog     // Catch: org.json.JSONException -> L7c
            if (r5 == 0) goto L83
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> L7c
            r5.<init>()     // Catch: org.json.JSONException -> L7c
            java.lang.String r6 = "paramKey: "
            r5.append(r6)     // Catch: org.json.JSONException -> L7c
            r5.append(r2)     // Catch: org.json.JSONException -> L7c
            java.lang.String r2 = "----paramStr: "
            r5.append(r2)     // Catch: org.json.JSONException -> L7c
            r5.append(r7)     // Catch: org.json.JSONException -> L7c
            java.lang.String r7 = r5.toString()     // Catch: org.json.JSONException -> L7c
            com.wjlogin.onekey.sdk.util.LogUtil.LogI(r0, r7)     // Catch: org.json.JSONException -> L7c
            goto L83
        L7c:
            r7 = move-exception
            goto L80
        L7e:
            r7 = move-exception
            r4 = 0
        L80:
            r7.printStackTrace()
        L83:
            a(r4)
            java.lang.String r7 = r4.toString()
            java.lang.String r7 = com.wjlogin.onekey.sdk.common.a.b.e.a(r7, r3, r1)
            boolean r1 = com.wjlogin.onekey.sdk.util.LogUtil.enableLog
            if (r1 == 0) goto La6
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "params: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            com.wjlogin.onekey.sdk.util.LogUtil.LogI(r0, r1)
        La6:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wjlogin.onekey.sdk.common.a.a.a.a(java.lang.String):java.lang.String");
    }

    public static String b(String str) {
        try {
            return com.wjlogin.onekey.sdk.common.a.b.g.a(com.wjlogin.onekey.sdk.common.a.b.g.c(), str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static JSONObject c(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (LogUtil.enableLog) {
                StringBuilder sb = new StringBuilder();
                sb.append("CT getResponse = ");
                sb.append(jSONObject.toString());
                LogUtil.LogI(a, sb.toString());
            }
            String optString = jSONObject.optString("result");
            String optString2 = jSONObject.optString("msg");
            if (TextUtils.equals(optString, "0")) {
                String a2 = com.wjlogin.onekey.sdk.common.a.b.a.a(jSONObject.optString("data"), b);
                JSONObject jSONObject2 = new JSONObject(a2);
                if (LogUtil.enableLog) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("data = ");
                    sb2.append(a2);
                    LogUtil.LogI(a, sb2.toString());
                }
                return com.wjlogin.onekey.sdk.util.a.b(optString, jSONObject2.optString("accessCode"), jSONObject2.optString("number"), "CT");
            }
            return com.wjlogin.onekey.sdk.util.a.a(optString, optString2, "CT");
        } catch (Exception unused) {
            return com.wjlogin.onekey.sdk.util.a.a("-2", Constans.responseError, "CT");
        }
    }

    public static String a(String str, String str2) {
        String str3;
        try {
            str3 = com.wjlogin.onekey.sdk.common.a.b.a.b(str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
            str3 = "";
        }
        return com.wjlogin.onekey.sdk.common.a.b.h.m(str3) ? "" : str3;
    }

    public static void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        jSONObject.remove("sign");
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                arrayList.add(keys.next());
            }
            Collections.sort(arrayList);
            StringBuffer stringBuffer = new StringBuffer();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                stringBuffer.append(jSONObject.get((String) arrayList.get(i2)));
            }
            jSONObject.put("sign", com.wjlogin.onekey.sdk.common.a.b.f.b(stringBuffer.toString(), com.wjlogin.onekey.sdk.common.a.b.d.f18347c, ""));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
