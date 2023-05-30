package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import g.j.b.a.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes11.dex */
public class c4 {
    private static a a;
    private static Map<String, m7> b;

    /* loaded from: classes11.dex */
    public interface a {
        void a(Context context, g7 g7Var);
    }

    public static int a(int i2) {
        if (i2 > 0) {
            return i2 + 1000;
        }
        return -1;
    }

    public static int b(Enum r1) {
        if (r1 != null) {
            if (r1 instanceof c7) {
                return r1.ordinal() + 1001;
            }
            if (r1 instanceof m7) {
                return r1.ordinal() + 2001;
            }
            if (r1 instanceof l4) {
                return r1.ordinal() + 3001;
            }
        }
        return -1;
    }

    public static g.j.b.a.a c(Context context) {
        boolean m2 = com.xiaomi.push.service.b0.d(context).m(h7.PerfUploadSwitch.a(), false);
        boolean m3 = com.xiaomi.push.service.b0.d(context).m(h7.EventUploadNewSwitch.a(), false);
        int a2 = com.xiaomi.push.service.b0.d(context).a(h7.PerfUploadFrequency.a(), RemoteMessageConst.DEFAULT_TTL);
        int a3 = com.xiaomi.push.service.b0.d(context).a(h7.EventUploadFrequency.a(), RemoteMessageConst.DEFAULT_TTL);
        a.C0842a b2 = g.j.b.a.a.b();
        b2.l(m3);
        b2.k(a3);
        b2.o(m2);
        b2.n(a2);
        return b2.h(context);
    }

    public static g.j.b.a.b d(Context context, String str, String str2, int i2, long j2, String str3) {
        g.j.b.a.b e2 = e(str);
        e2.f19670h = str2;
        e2.f19671i = i2;
        e2.f19672j = j2;
        e2.f19673k = str3;
        return e2;
    }

    public static g.j.b.a.b e(String str) {
        g.j.b.a.b bVar = new g.j.b.a.b();
        bVar.a = 1000;
        bVar.f19677c = 1001;
        bVar.b = str;
        return bVar;
    }

    public static g.j.b.a.c f() {
        g.j.b.a.c cVar = new g.j.b.a.c();
        cVar.a = 1000;
        cVar.f19677c = 1000;
        cVar.b = "P100000";
        return cVar;
    }

    public static g.j.b.a.c g(Context context, int i2, long j2, long j3) {
        g.j.b.a.c f2 = f();
        f2.f19674h = i2;
        f2.f19675i = j2;
        f2.f19676j = j3;
        return f2;
    }

    public static g7 h(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        g7 g7Var = new g7();
        g7Var.d("category_client_report_data");
        g7Var.a("push_sdk_channel");
        g7Var.a(1L);
        g7Var.b(str);
        g7Var.a(true);
        g7Var.b(System.currentTimeMillis());
        g7Var.g(context.getPackageName());
        g7Var.e("com.xiaomi.xmsf");
        g7Var.f(com.xiaomi.push.service.e1.b());
        g7Var.c("quality_support");
        return g7Var;
    }

    public static m7 i(String str) {
        if (b == null) {
            synchronized (m7.class) {
                if (b == null) {
                    b = new HashMap();
                    for (m7 m7Var : m7.values()) {
                        b.put(m7Var.f179a.toLowerCase(), m7Var);
                    }
                }
            }
        }
        m7 m7Var2 = b.get(str.toLowerCase());
        return m7Var2 != null ? m7Var2 : m7.Invalid;
    }

    public static String j(int i2) {
        return i2 == 1000 ? "E100000" : i2 == 3000 ? "E100002" : i2 == 2000 ? "E100001" : i2 == 6000 ? "E100003" : "";
    }

    public static void k(Context context) {
        g.j.b.b.a.d(context, c(context));
    }

    public static void l(Context context, g.j.b.a.a aVar) {
        g.j.b.b.a.a(context, aVar, new a4(context), new b4(context));
    }

    private static void m(Context context, g7 g7Var) {
        if (p(context.getApplicationContext())) {
            com.xiaomi.push.service.f1.a(context.getApplicationContext(), g7Var);
            return;
        }
        a aVar = a;
        if (aVar != null) {
            aVar.a(context, g7Var);
        }
    }

    public static void n(Context context, List<String> list) {
        if (list == null) {
            return;
        }
        try {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                g7 h2 = h(context, it.next());
                if (!com.xiaomi.push.service.e1.e(h2, false)) {
                    m(context, h2);
                }
            }
        } catch (Throwable th) {
            g.j.a.a.a.c.D(th.getMessage());
        }
    }

    public static void o(a aVar) {
        a = aVar;
    }

    public static boolean p(Context context) {
        return (context == null || TextUtils.isEmpty(context.getPackageName()) || !"com.xiaomi.xmsf".equals(context.getPackageName())) ? false : true;
    }
}
