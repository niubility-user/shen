package com.xiaomi.push;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.huawei.hms.push.constant.RemoteMessageConst;

/* loaded from: classes11.dex */
public class v2 {

    /* renamed from: c  reason: collision with root package name */
    private static volatile v2 f19278c;
    private Context a;
    private a b;

    /* loaded from: classes11.dex */
    public interface a {
        void a();
    }

    private v2(Context context) {
        this.a = context;
    }

    public static int a(int i2) {
        return Math.max(60, i2);
    }

    public static v2 b(Context context) {
        if (f19278c == null) {
            synchronized (v2.class) {
                if (f19278c == null) {
                    f19278c = new v2(context);
                }
            }
        }
        return f19278c;
    }

    private void e(com.xiaomi.push.service.b0 b0Var, i iVar, boolean z) {
        if (b0Var.m(h7.UploadSwitch.a(), true)) {
            z2 z2Var = new z2(this.a);
            if (z) {
                iVar.j(z2Var, a(b0Var.a(h7.UploadFrequency.a(), RemoteMessageConst.DEFAULT_TTL)));
            } else {
                iVar.i(z2Var);
            }
        }
    }

    private boolean f() {
        try {
            Context context = this.a;
            if (!(context instanceof Application)) {
                context = context.getApplicationContext();
            }
            ((Application) context).registerActivityLifecycleCallbacks(new p2(this.a, String.valueOf(System.currentTimeMillis() / 1000)));
            return true;
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        a aVar;
        i b = i.b(this.a);
        com.xiaomi.push.service.b0 d = com.xiaomi.push.service.b0.d(this.a);
        SharedPreferences sharedPreferences = this.a.getSharedPreferences("mipush_extra", 0);
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = sharedPreferences.getLong("first_try_ts", currentTimeMillis);
        if (j2 == currentTimeMillis) {
            sharedPreferences.edit().putLong("first_try_ts", currentTimeMillis).commit();
        }
        if (Math.abs(currentTimeMillis - j2) < 172800000) {
            return;
        }
        e(d, b, false);
        if (d.m(h7.StorageCollectionSwitch.a(), true)) {
            int a2 = a(d.a(h7.StorageCollectionFrequency.a(), RemoteMessageConst.DEFAULT_TTL));
            b.k(new y2(this.a, a2), a2, 0);
        }
        if (a8.j(this.a) && (aVar = this.b) != null) {
            aVar.a();
        }
        if (d.m(h7.ActivityTSSwitch.a(), false)) {
            f();
        }
        e(d, b, true);
    }

    public void c() {
        i.b(this.a).g(new w2(this));
    }
}
