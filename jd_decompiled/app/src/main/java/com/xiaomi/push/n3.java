package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;

/* loaded from: classes11.dex */
public class n3 {
    public static void a(Context context, String str, int i2, String str2) {
        i.b(context).g(new o3(context, str, i2, str2));
    }

    private static void b(Context context, HashMap<String, String> hashMap) {
        v3 c2 = r3.b(context).c();
        if (c2 != null) {
            c2.a(context, hashMap);
        }
    }

    private static void d(Context context, HashMap<String, String> hashMap) {
        v3 c2 = r3.b(context).c();
        if (c2 != null) {
            c2.b(context, hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(Context context, String str, int i2, String str2) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("awake_info", str);
            hashMap.put("event_type", String.valueOf(i2));
            hashMap.put("description", str2);
            int a = r3.b(context).a();
            if (a != 1) {
                if (a != 2) {
                    if (a == 3) {
                        b(context, hashMap);
                    }
                }
                f(context, hashMap);
            } else {
                b(context, hashMap);
            }
            d(context, hashMap);
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
        }
    }

    private static void f(Context context, HashMap<String, String> hashMap) {
        v3 c2 = r3.b(context).c();
        if (c2 != null) {
            c2.c(context, hashMap);
        }
    }
}
