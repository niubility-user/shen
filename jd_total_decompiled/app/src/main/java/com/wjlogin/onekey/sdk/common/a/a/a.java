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
    */
    public static String a(String str) {
        JSONObject jSONObject;
        String a2;
        String b2;
        String valueOf = String.valueOf(System.currentTimeMillis());
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("timeStamp", valueOf);
            jSONObject2.put("bussinessType", str);
            a2 = a(com.wjlogin.onekey.sdk.common.a.b.e.a(jSONObject2.toString(), false, ""), b);
            b2 = b(b);
            jSONObject = new JSONObject();
        } catch (JSONException e2) {
            e = e2;
            jSONObject = null;
        }
        try {
            jSONObject.put("paramKey", b2);
            jSONObject.put("paramStr", a2);
            jSONObject.put("clientId", com.wjlogin.onekey.sdk.common.a.b.d.b);
            jSONObject.put("clientType", f18323e);
            jSONObject.put("format", f18324f);
            jSONObject.put("version", f18325g);
            if (LogUtil.enableLog) {
                StringBuilder sb = new StringBuilder();
                sb.append("paramKey: ");
                sb.append(b2);
                sb.append("----paramStr: ");
                sb.append(a2);
                LogUtil.LogI(a, sb.toString());
            }
        } catch (JSONException e3) {
            e = e3;
            e.printStackTrace();
            a(jSONObject);
            String a3 = com.wjlogin.onekey.sdk.common.a.b.e.a(jSONObject.toString(), false, "");
            if (LogUtil.enableLog) {
            }
            return a3;
        }
        a(jSONObject);
        String a32 = com.wjlogin.onekey.sdk.common.a.b.e.a(jSONObject.toString(), false, "");
        if (LogUtil.enableLog) {
            LogUtil.LogI(a, "params: " + a32);
        }
        return a32;
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
