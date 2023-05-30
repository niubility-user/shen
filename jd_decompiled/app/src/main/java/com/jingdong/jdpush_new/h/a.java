package com.jingdong.jdpush_new.h;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.messagecenter.MIPushMsg;
import com.jingdong.jdpush_new.d.Brands;
import com.jingdong.jdpush_new.j.c;
import com.jingdong.jdpush_new.j.g;
import com.jingdong.jdpush_new.j.l;
import com.jingdong.sdk.baseinfo.BaseInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class a {
    public static com.jingdong.jdpush_new.g.b a(Context context, String str) {
        com.jingdong.jdpush_new.g.b bVar = new com.jingdong.jdpush_new.g.b();
        bVar.setCommand((short) 2108);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("CLIENTID");
            String c2 = jSONObject.getInt("DEVMODLE") == 0 ? com.jingdong.jdpush_new.f.a.k(context).h(c.d(context)).c() : jSONObject.getString("DEVTOKEN");
            if (TextUtils.isEmpty(c2)) {
                return null;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(MIPushMsg.APP_ID, c.h(context));
            jSONObject2.put("APP_SECRET", c.b(context));
            jSONObject2.put("CLIENTID", string.trim());
            jSONObject2.put("DEVTOKEN", c2);
            bVar.setMsgBody(jSONObject2.toString());
            return bVar;
        } catch (Exception unused) {
            return null;
        }
    }

    @Deprecated
    public static com.jingdong.jdpush_new.g.b b(Context context, com.jingdong.jdpush_new.g.c.c cVar) {
        com.jingdong.jdpush_new.g.b bVar = new com.jingdong.jdpush_new.g.b();
        bVar.setCommand((short) 2118);
        try {
            String a = cVar.a();
            String h2 = cVar.h();
            String q = c.q(context);
            if (TextUtils.isEmpty(a) || TextUtils.isEmpty(h2) || TextUtils.isEmpty(q)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(MIPushMsg.APP_ID, Integer.valueOf(a));
            jSONObject.put("PKG_NAME", h2);
            jSONObject.put("DEVTOKEN", q);
            bVar.setMsgBody(jSONObject.toString());
            return bVar;
        } catch (JSONException e2) {
            g.g(e2);
            return null;
        }
    }

    public static com.jingdong.jdpush_new.g.b c(Context context) {
        com.jingdong.jdpush_new.g.b bVar = new com.jingdong.jdpush_new.g.b();
        bVar.setCommand((short) 2100);
        return bVar;
    }

    public static com.jingdong.jdpush_new.g.b d(Context context, short s, String str) {
        com.jingdong.jdpush_new.g.b bVar = new com.jingdong.jdpush_new.g.b();
        bVar.setCommand(s);
        try {
            String d = c.d(context);
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(MIPushMsg.MSG_SEQ);
            String string2 = jSONObject.getString(MIPushMsg.ECHO);
            if (d == null || string == null || string2 == null) {
                return null;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(MIPushMsg.APP_ID, Integer.valueOf(d));
            jSONObject2.put(MIPushMsg.MSG_SEQ, string);
            jSONObject2.put(MIPushMsg.ECHO, string2);
            bVar.setMsgBody(jSONObject2.toString());
            return bVar;
        } catch (Exception unused) {
            return null;
        }
    }

    public static com.jingdong.jdpush_new.g.b e(Context context, String str) {
        com.jingdong.jdpush_new.g.b bVar = new com.jingdong.jdpush_new.g.b();
        bVar.setCommand((short) 2104);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("DEVTOKEN");
            int i2 = jSONObject.getInt("DEVMODLE");
            int i3 = jSONObject.getInt("OPEN_PUSH");
            String c2 = com.jd.lib.push.c.b.b().a().c();
            g.a("\u63a8\u9001\u5f15\u64ce\u7248\u672c:" + c2);
            int a = Brands.a(BaseInfo.getDeviceBrand());
            g.a("realBrand:" + a);
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(MIPushMsg.APP_ID, c.d(context));
            jSONObject2.put("APP_SECRET", c.b(context));
            jSONObject2.put("DEVTOKEN", string);
            jSONObject2.put("OS_VER", c.c());
            jSONObject2.put("APP_VER", c.o(context));
            jSONObject2.put(MIPushMsg.DEVTYPE, c.p() ? 5 : 2);
            jSONObject2.put("PKG_NAME", c.l(context));
            jSONObject2.put("UUID", com.jingdong.jdpush_new.a.b());
            jSONObject2.put("DEV_CATEGORY", l.a());
            jSONObject2.put("DEV_SRC", i2);
            jSONObject2.put("SDK_VER", c.m());
            jSONObject2.put("BRAND", BaseInfo.getDeviceModel());
            jSONObject2.put("OPEN_PUSH", i3);
            jSONObject2.put("ROM", c.g());
            jSONObject2.put("REAL_BRAND", a);
            jSONObject2.put("ENGINE_VERSION", c2);
            if (a == 10) {
                jSONObject2.put("SUPPORT_HONOR_PUSH", l.n() ? 1 : 0);
            }
            g.c("\u901a\u77e5\u5f00\u5173\u72b6\u6001" + i3);
            bVar.setMsgBody(jSONObject2.toString());
            return bVar;
        } catch (Exception unused) {
            return null;
        }
    }

    public static com.jingdong.jdpush_new.g.b f(Context context, String str) {
        com.jingdong.jdpush_new.g.b bVar = new com.jingdong.jdpush_new.g.b();
        bVar.setCommand((short) 2110);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("CLIENTID");
            int i2 = jSONObject.getInt("DEVMODLE");
            String d = c.d(context);
            String b = c.b(context);
            String c2 = i2 == 0 ? com.jingdong.jdpush_new.f.a.k(context).h(d).c() : jSONObject.getString("DEVTOKEN");
            if (TextUtils.isEmpty(d) || TextUtils.isEmpty(c2) || TextUtils.isEmpty(b)) {
                return null;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(MIPushMsg.APP_ID, Integer.valueOf(d));
            jSONObject2.put("APP_SECRET", b);
            jSONObject2.put("CLIENTID", string.trim());
            jSONObject2.put("DEVTOKEN", c2);
            bVar.setMsgBody(jSONObject2.toString());
            return bVar;
        } catch (Exception unused) {
            return null;
        }
    }

    public static com.jingdong.jdpush_new.g.b g(String str) {
        com.jingdong.jdpush_new.g.b bVar = new com.jingdong.jdpush_new.g.b();
        bVar.setCommand((short) 2124);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString(MIPushMsg.APP_ID);
            String optString2 = jSONObject.optString("DEVTOKEN");
            String optString3 = jSONObject.optString("APP_SECRET");
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2) || TextUtils.isEmpty(optString3)) {
                return null;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(MIPushMsg.APP_ID, Integer.valueOf(optString));
            jSONObject2.put("DEVTOKEN", optString2);
            jSONObject2.put("APP_SECRET", optString3);
            bVar.setMsgBody(jSONObject2.toString());
            return bVar;
        } catch (Exception unused) {
            return null;
        }
    }
}
