package com.jd.manto.center.h;

import android.content.Context;
import com.jd.framework.json.JDJSONObject;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class b extends a {
    public static void e(Context context, String str, String str2, int i2) {
        if (com.jd.manto.center.k.b.a(context)) {
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        try {
            jDJSONObject.put("id", (Object) a.a(str));
            jDJSONObject.put("url", (Object) a.a(str2));
            jDJSONObject.put("frame", (Object) a.a(String.valueOf(i2)));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a.b(context, "J_Applets_Find_Banner", "", "", "J_Applets_Find", "", "", "", jDJSONObject);
    }

    public static void f(Context context, String str, String str2, String str3) {
        if (com.jd.manto.center.k.b.a(context)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", a.a(str));
            jSONObject.put("name", a.a(str2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a.c(context, str3, "", "", "J_Applets_My", "", "", "", jSONObject);
    }

    public static void g(Context context, String str, String str2, String str3) {
        if (com.jd.manto.center.k.b.a(context)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", a.a(str));
            jSONObject.put("name", a.a(str2));
            jSONObject.put("status", a.a(str3));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a.c(context, "J_Applets_RecentlyUsed_Follow", "", "", "J_Applets_RecentlyUsed", "", "", "", jSONObject);
    }

    public static void h(Context context, String str, String str2, String str3, String str4) {
        if (com.jd.manto.center.k.b.a(context)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", a.a(str));
            jSONObject.put("name", a.a(str2));
            jSONObject.put("position", a.a(str3));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a.c(context, str4, "", "", "J_Applets_RecentlyUsed", "", "", "", jSONObject);
    }

    public static void i(Context context, String str) {
        if (com.jd.manto.center.k.b.a(context)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("keywords", a.a(str));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a.c(context, "J_Applets_Search_Find", "", "", "J_Applets_Search", "", "", "", jSONObject);
    }

    public static void j(Context context) {
        if (com.jd.manto.center.k.b.a(context)) {
            return;
        }
        a.c(context, "J_Applets_Search_Clear", "", "", "J_Applets_Search", "", "", "", new JSONObject());
    }

    public static void k(Context context, String str) {
        if (com.jd.manto.center.k.b.a(context)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("keywords", a.a(str));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a.c(context, "J_Applets_Search_History", "", "", "J_Applets_Search", "", "", "", jSONObject);
    }

    public static void l(Context context, String str) {
        if (com.jd.manto.center.k.b.a(context)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", a.a(str));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a.c(context, "J_Applets_Search_Open", "", "", "J_Applets_Search", "", "", "", jSONObject);
    }

    public static void m(Context context, String str, String str2, String str3) {
        if (com.jd.manto.center.k.b.a(context)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", a.a(str));
            jSONObject.put("name", a.a(str2));
            jSONObject.put("position", a.a(str3));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a.c(context, "J_Applets_SearchResult_List", "", "", "J_Applets_SearchResult", "", "", "", jSONObject);
    }

    public static void n(Context context, String str) {
        if (com.jd.manto.center.k.b.a(context)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("keywords", a.a(str));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a.c(context, "J_Applets_Search_Search", "", "", "J_Applets_Search", "", "", "", jSONObject);
    }

    public static void o(Context context, String str, String str2, int i2) {
        if (com.jd.manto.center.k.b.a(context)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", a.a(str));
            jSONObject.put("name", a.a(str2));
            jSONObject.put("position", a.a(String.valueOf(i2)));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a.c(context, "J_Applets_Find_Recommend", "", "", "J_Applets_Find", "", "", "", jSONObject);
    }

    public static void p(Context context, String str) {
        if (com.jd.manto.center.k.b.a(context, str)) {
            return;
        }
        a.d(context, context, null, str, "");
    }

    public static void q(Context context, String str, String str2) {
        if (com.jd.manto.center.k.b.a(context)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("pageid", a.a(str2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        a.c(context, str, "", "", "J_Applets_Top", "", "", "", jSONObject);
    }
}
