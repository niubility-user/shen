package com.jingdong.jdpush_new.h;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.messagecenter.MIPushMsg;
import com.jingdong.jdpush_new.d.Brands;
import com.jingdong.jdpush_new.g.c.d;
import com.jingdong.jdpush_new.j.c;
import com.jingdong.jdpush_new.j.l;
import com.jingdong.sdk.baseinfo.BaseInfo;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class b {
    public static com.jingdong.jdpush_new.g.b a(Context context, short s, com.jingdong.jdpush_new.g.c.b bVar) {
        String string;
        com.jingdong.jdpush_new.g.b bVar2 = new com.jingdong.jdpush_new.g.b();
        bVar2.setCommand(s);
        try {
            String a = bVar.a();
            String b = bVar.b();
            JSONObject jSONObject = new JSONObject(bVar.e());
            String string2 = jSONObject.getString("CLIENTID");
            if (jSONObject.getInt("DEVMODLE") == 0) {
                string = com.jingdong.jdpush_new.f.a.k(context).h(a).c();
            } else {
                string = jSONObject.getString("DEVTOKEN");
            }
            if (TextUtils.isEmpty(a) || TextUtils.isEmpty(string) || TextUtils.isEmpty(b)) {
                return null;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(MIPushMsg.APP_ID, Integer.valueOf(a));
            jSONObject2.put("APP_SECRET", b);
            jSONObject2.put("CLIENTID", string2.trim());
            jSONObject2.put("DEVTOKEN", string);
            bVar2.setMsgBody(jSONObject2.toString());
            return bVar2;
        } catch (Exception unused) {
            return null;
        }
    }

    public static com.jingdong.jdpush_new.g.b b(Context context, short s, d dVar) {
        com.jingdong.jdpush_new.g.b bVar = new com.jingdong.jdpush_new.g.b();
        bVar.setCommand(s);
        try {
            String d = c.d(context);
            JSONObject jSONObject = new JSONObject(dVar.d());
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

    public static com.jingdong.jdpush_new.g.b c(Context context, short s, com.jingdong.jdpush_new.g.c.b bVar) {
        com.jingdong.jdpush_new.g.b bVar2 = new com.jingdong.jdpush_new.g.b();
        bVar2.setCommand(s);
        try {
            String a = bVar.a();
            String b = bVar.b();
            JSONObject jSONObject = new JSONObject(bVar.e());
            String string = jSONObject.getString("DEVTOKEN");
            int i2 = jSONObject.getInt("DEVMODLE");
            int optInt = jSONObject.optInt("OPEN_PUSH");
            String c2 = com.jd.lib.push.c.b.b().a().c();
            if (TextUtils.isEmpty(a) || TextUtils.isEmpty(string)) {
                return null;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(MIPushMsg.APP_ID, Integer.valueOf(a));
            jSONObject2.put("APP_SECRET", b);
            jSONObject2.put("DEVTOKEN", string);
            jSONObject2.put("OS_VER", c.c());
            jSONObject2.put("APP_VER", c.o(context));
            jSONObject2.put(MIPushMsg.DEVTYPE, 2);
            jSONObject2.put("PKG_NAME", c.l(context));
            jSONObject2.put("UUID", com.jingdong.jdpush_new.a.b());
            jSONObject2.put("DEV_CATEGORY", l.a());
            jSONObject2.put("DEV_SRC", i2);
            jSONObject2.put("SDK_VER", c.m());
            jSONObject2.put("BRAND", BaseInfo.getDeviceModel());
            jSONObject2.put("OPEN_PUSH", optInt);
            jSONObject2.put("ROM", c.g());
            jSONObject2.put("REAL_BRAND", Brands.a(BaseInfo.getDeviceBrand()));
            jSONObject2.put("ENGINE_VERSION", c2);
            bVar2.setMsgBody(jSONObject2.toString());
            return bVar2;
        } catch (Exception unused) {
            return null;
        }
    }

    public static com.jingdong.jdpush_new.g.b d(Context context, short s, com.jingdong.jdpush_new.g.c.b bVar) {
        String string;
        com.jingdong.jdpush_new.g.b bVar2 = new com.jingdong.jdpush_new.g.b();
        bVar2.setCommand(s);
        try {
            JSONObject jSONObject = new JSONObject(bVar.e());
            String string2 = jSONObject.getString("CLIENTID");
            int i2 = jSONObject.getInt("DEVMODLE");
            String a = bVar.a();
            String b = bVar.b();
            if (i2 == 0) {
                string = com.jingdong.jdpush_new.f.a.k(context).h(a).c();
            } else {
                string = jSONObject.getString("DEVTOKEN");
            }
            if (TextUtils.isEmpty(a) || TextUtils.isEmpty(string) || TextUtils.isEmpty(b)) {
                return null;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(MIPushMsg.APP_ID, Integer.valueOf(a));
            jSONObject2.put("APP_SECRET", b);
            jSONObject2.put("CLIENTID", string2.trim());
            jSONObject2.put("DEVTOKEN", string);
            bVar2.setMsgBody(jSONObject2.toString());
            return bVar2;
        } catch (Exception unused) {
            return null;
        }
    }
}
