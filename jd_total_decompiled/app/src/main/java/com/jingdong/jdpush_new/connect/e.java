package com.jingdong.jdpush_new.connect;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.common.messagecenter.MIPushMsg;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class e {
    public static com.jingdong.jdpush_new.g.b a(Context context) throws JSONException, b {
        String i2 = com.jingdong.jdpush_new.j.c.i(context);
        com.jingdong.jdpush_new.mta.b.b().c().longConnDT = i2;
        if (!TextUtils.isEmpty(i2)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(com.jingdong.jdpush_new.j.c.h(context)));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("DEVTOKEN", i2);
            jSONObject.put("SDK_VER", com.jingdong.jdpush_new.j.c.m());
            jSONObject.put("APP_LIST", new JSONArray((Collection) arrayList));
            return new com.jingdong.jdpush_new.g.b((short) 2102, jSONObject.toString());
        }
        throw new b("\u81ea\u5efa\u901a\u9053DT\u4e3a\u7a7a");
    }

    public static com.jingdong.jdpush_new.g.b b(Context context, com.jingdong.jdpush_new.g.c.c cVar) {
        com.jingdong.jdpush_new.g.b bVar = new com.jingdong.jdpush_new.g.b();
        bVar.setCommand((short) 2115);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(MIPushMsg.APP_ID, Integer.valueOf(cVar.a()));
            jSONObject.put(MIPushMsg.MSG_SEQ, cVar.f());
            int i2 = 0;
            jSONObject.put("STATUS", 0);
            jSONObject.put(MIPushMsg.ECHO, cVar.d());
            if (!TextUtils.isEmpty(com.jingdong.jdpush_new.j.c.d(context))) {
                i2 = Integer.valueOf(com.jingdong.jdpush_new.j.c.d(context)).intValue();
            }
            jSONObject.put("TRANSFER_APPID", i2);
            jSONObject.put("callbackParam", cVar.b());
            bVar.setMsgBody(jSONObject.toString());
            return bVar;
        } catch (Exception unused) {
            return null;
        }
    }
}
