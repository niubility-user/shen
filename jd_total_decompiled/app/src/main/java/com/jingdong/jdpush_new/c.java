package com.jingdong.jdpush_new;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.jd.lib.push.utils.d;
import com.jingdong.jdpush_new.j.g;

/* loaded from: classes12.dex */
public class c {
    public static final b a = new b();

    public static void a(Context context) {
        try {
            g.a("rebind service on BackToForeground");
            d(context);
        } catch (Throwable th) {
            g.f("error when bind service after start", th);
        }
    }

    public static b b() {
        return a;
    }

    public static void c(Context context) {
        try {
            g.a("begin start service.");
            com.jingdong.jdpush_new.mta.b.b().l(100360);
            d(context);
        } catch (Throwable th) {
            g.g(th);
        }
    }

    public static void d(Context context) {
        try {
            Intent intent = new Intent(context, JDSPushService.class);
            if (Build.VERSION.SDK_INT < 26 && !d.k()) {
                context.startService(intent);
                g.h("startService");
            }
            g.h("bindService");
            context.bindService(intent, a, 1);
        } catch (Throwable th) {
            g.g(th);
        }
    }

    public static void e(Context context) {
        try {
            if (a.a()) {
                g.a("service connection is OK");
            } else {
                g.c("service connection is lost, try reBind service");
                d(context);
            }
        } catch (Throwable th) {
            g.f("error when bind service after start", th);
        }
    }
}
