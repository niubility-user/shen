package com.xiaomi.push.service;

import android.content.Context;
import android.util.Log;
import com.jd.dynamic.DYConstants;
import com.xiaomi.push.c8;
import com.xiaomi.push.m7;
import com.xiaomi.push.s9;
import com.xiaomi.push.service.XMPushService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes11.dex */
public class l1 implements XMPushService.n {
    private static final boolean a = Log.isLoggable("UNDatas", 3);
    private static final Map<Integer, Map<String, List<String>>> b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private static Context f19125c;

    public l1(Context context) {
        f19125c = context;
    }

    private static c8 c(String str, String str2, String str3, String str4) {
        c8 c8Var = new c8();
        if (str3 != null) {
            c8Var.c(str3);
        }
        if (str != null) {
            c8Var.b(str);
        }
        if (str2 != null) {
            c8Var.a(str2);
        }
        if (str4 != null) {
            c8Var.d(str4);
        }
        c8Var.a(false);
        return c8Var;
    }

    private static void d(Context context, c8 c8Var) {
        if (a) {
            g.j.a.a.a.c.y("UNDatas upload message notification:" + c8Var);
        }
        com.xiaomi.push.i.b(context).g(new m1(c8Var));
    }

    private static void e() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(b);
        if (hashMap.size() > 0) {
            for (Integer num : hashMap.keySet()) {
                Map map = (Map) hashMap.get(num);
                if (map != null && map.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (String str : map.keySet()) {
                        sb.append(str);
                        sb.append(":");
                        List list = (List) map.get(str);
                        if (!s9.d(list)) {
                            for (int i2 = 0; i2 < list.size(); i2++) {
                                if (i2 != 0) {
                                    sb.append(DYConstants.DY_REGEX_COMMA);
                                }
                                sb.append((String) list.get(i2));
                            }
                        }
                        sb.append(";");
                    }
                    c8 c2 = c(null, f0.a(), m7.NotificationRemoved.f179a, null);
                    c2.a("removed_reason", String.valueOf(num));
                    c2.a("all_delete_msgId_appId", sb.toString());
                    g.j.a.a.a.c.y("UNDatas upload all removed messages reason: " + num + " allIds: " + sb.toString());
                    d(f19125c, c2);
                }
                b.remove(num);
            }
        }
    }

    @Override // com.xiaomi.push.service.XMPushService.n
    public void a() {
        Map<Integer, Map<String, List<String>>> map = b;
        if (map.size() > 0) {
            synchronized (map) {
                e();
            }
        }
    }
}
