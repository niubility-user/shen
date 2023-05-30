package com.cmic.sso.sdk.d;

import android.content.Context;
import android.text.TextUtils;
import com.cmic.sso.sdk.c.c.d;
import com.cmic.sso.sdk.e.c;
import com.cmic.sso.sdk.e.f;
import com.cmic.sso.sdk.e.k;
import com.cmic.sso.sdk.e.m;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b {
    private com.cmic.sso.sdk.a a;

    private static void a(a aVar, com.cmic.sso.sdk.a aVar2) {
        if (aVar == null || aVar2 == null) {
            return;
        }
        aVar.b(aVar2.b("appid", ""));
        aVar.e(m.a());
        aVar.h(aVar2.b("interfaceType", ""));
        aVar.g(aVar2.b("interfaceCode", ""));
        aVar.f(aVar2.b("interfaceElasped", ""));
        aVar.k(aVar2.b("timeOut"));
        aVar.r(aVar2.b("traceId"));
        aVar.m(aVar2.b("simCardNum"));
        aVar.n(aVar2.b("operatortype"));
        aVar.o(m.b());
        aVar.p(m.c());
        aVar.w(String.valueOf(aVar2.b("networktype", 0)));
        aVar.s(aVar2.b("starttime"));
        aVar.t(aVar2.b("endtime"));
        aVar.l(String.valueOf(aVar2.b("systemEndTime", 0L) - aVar2.b("systemStartTime", 0L)));
        aVar.c(aVar2.b("imsiState"));
        aVar.x(k.b("AID", ""));
        aVar.y(aVar2.b("operatortype"));
        aVar.z(aVar2.b("scripType"));
        aVar.A(aVar2.b("networkTypeByAPI"));
        c.a("SendLog", "traceId" + aVar2.b("traceId"));
    }

    public void a(Context context, String str, com.cmic.sso.sdk.a aVar) {
        String str2 = "";
        try {
            a a = aVar.a();
            String b = f.b(context);
            a.d(str);
            a.u(aVar.b("loginMethod", ""));
            if (aVar.b("isCacheScrip", false)) {
                a.q("scrip");
            } else {
                a.q("pgw");
            }
            a.i(f.a(context));
            if (!TextUtils.isEmpty(b)) {
                str2 = b;
            }
            a.j(str2);
            a(a, aVar);
            JSONArray jSONArray = null;
            if (a.a.size() > 0) {
                jSONArray = new JSONArray();
                Iterator<Throwable> it = a.a.iterator();
                while (it.hasNext()) {
                    Throwable next = it.next();
                    StringBuffer stringBuffer = new StringBuffer();
                    JSONObject jSONObject = new JSONObject();
                    for (StackTraceElement stackTraceElement : next.getStackTrace()) {
                        stringBuffer.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                        stringBuffer.append(stackTraceElement.toString());
                    }
                    jSONObject.put("message", next.toString());
                    jSONObject.put("stack", stringBuffer.toString());
                    jSONArray.put(jSONObject);
                }
                a.a.clear();
            }
            if (jSONArray != null && jSONArray.length() > 0) {
                a.a(jSONArray);
            }
            c.a("SendLog", "\u767b\u5f55\u65e5\u5fd7");
            a(a.b(), aVar);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(JSONObject jSONObject, com.cmic.sso.sdk.a aVar) {
        this.a = aVar;
        a(jSONObject);
    }

    private void a(JSONObject jSONObject) {
        com.cmic.sso.sdk.c.c.a.a().a(jSONObject, this.a, new d() { // from class: com.cmic.sso.sdk.d.b.1
            @Override // com.cmic.sso.sdk.c.c.d
            public void a(String str, String str2, JSONObject jSONObject2) {
                com.cmic.sso.sdk.a.a b = b.this.a.b();
                HashMap hashMap = new HashMap();
                if (!str.equals("103000")) {
                    if (b.l() != 0 && b.k() != 0) {
                        int a = k.a("logFailTimes", 0) + 1;
                        if (a >= b.k()) {
                            hashMap.put("logFailTimes", 0);
                            hashMap.put("logCloseTime", Long.valueOf(System.currentTimeMillis()));
                        } else {
                            hashMap.put("logFailTimes", Integer.valueOf(a));
                        }
                    }
                } else {
                    hashMap.put("logFailTimes", 0);
                    hashMap.put("logCloseTime", 0L);
                }
                k.a(hashMap);
            }
        });
    }
}
