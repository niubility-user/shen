package com.jd.fireeye.a;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.jd.android.sdk.coreinfo.util.Logger;
import com.jd.fireeye.a.c.d;
import com.jd.fireeye.a.c.e;
import com.jd.fireeye.a.c.f;
import com.jd.fireeye.a.d.c;
import com.jd.fireeye.a.d.g;
import com.jd.fireeye.a.d.i;
import com.jd.fireeye.a.d.j;
import java.util.List;

/* loaded from: classes13.dex */
public class a {
    private static Context a;
    private static boolean b;

    public static synchronized void a(b bVar) {
        synchronized (a.class) {
            if (b) {
                return;
            }
            if (bVar == null) {
                return;
            }
            Context b2 = bVar.b();
            if (b2 == null) {
                return;
            }
            boolean c2 = bVar.c();
            Logger.enableLogger(c2);
            String str = "init AntiSDK :context=" + bVar.b() + ", debugFlag=" + c2;
            a = b2;
            b = true;
        }
    }

    public static int b() {
        try {
            if (a != null) {
                return a.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("health", -1);
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static int c() {
        try {
            if (a != null) {
                return a.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("voltage", -1);
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static String d() {
        return String.valueOf(e.a(a));
    }

    public static String e() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = a.getPackageManager().resolveActivity(intent, 0);
        return resolveActivity == null ? "" : f.b(resolveActivity.activityInfo.packageName);
    }

    public static String f() {
        return d.a();
    }

    public static String g() {
        try {
            return j.a(a);
        } catch (Exception unused) {
            return "0";
        }
    }

    public static String h() {
        try {
            return new com.jd.fireeye.a.d.e(a).b();
        } catch (Exception unused) {
            return "0";
        }
    }

    public static String i() {
        return com.jd.fireeye.a.d.a.a(a);
    }

    public static String j() {
        return com.jd.fireeye.a.d.b.b(a);
    }

    public static int k() {
        return (int) c.a(a).longValue();
    }

    public static String l() {
        return com.jd.fireeye.a.d.d.b(a);
    }

    public static String m() {
        return String.valueOf(com.jd.fireeye.a.d.f.b(a));
    }

    public static String n() {
        return String.valueOf(g.b(a));
    }

    public static String o() {
        return i.b(a);
    }

    public static String a() {
        List<String> a2 = com.jd.fireeye.a.c.b.a(a);
        return a2 == null ? "" : TextUtils.join(", ", a2);
    }
}
