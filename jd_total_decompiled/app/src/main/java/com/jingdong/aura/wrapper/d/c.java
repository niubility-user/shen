package com.jingdong.aura.wrapper.d;

import android.app.Application;
import com.jingdong.aura.a.b.h;
import com.jingdong.aura.wrapper.AuraConfig;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: c  reason: collision with root package name */
    private static final com.jingdong.aura.core.util.l.b f12265c = com.jingdong.aura.core.util.l.c.a("OptDexProcess");
    private static c d;
    private Application a;
    private boolean b;

    c() {
    }

    public static synchronized c b() {
        synchronized (c.class) {
            synchronized (c.class) {
                if (d == null) {
                    d = new c();
                }
            }
            return d;
        }
        return d;
    }

    private void c() {
        for (String str : AuraConfig.AUTO) {
            l.b.a.d a = com.jingdong.aura.a.b.a.b().a(str);
            if (a != null) {
                try {
                    ((h) a).m();
                } catch (Throwable unused) {
                }
            }
        }
    }

    private void d() {
        for (String str : AuraConfig.DELAY) {
            l.b.a.d a = com.jingdong.aura.a.b.a.b().a(str);
            if (a != null) {
                try {
                    ((h) a).m();
                } catch (Throwable unused) {
                }
            }
        }
    }

    private void e() {
        for (l.b.a.d dVar : com.jingdong.aura.a.b.a.b().a()) {
            if (dVar != null && !a(AuraConfig.DELAY, dVar.b())) {
                try {
                    ((h) dVar).m();
                } catch (Throwable unused) {
                }
            }
        }
    }

    public void a(Application application) {
        this.a = application;
        this.b = true;
    }

    public synchronized void a(boolean z, boolean z2) {
        if (!this.b) {
            f12265c.d("Bundle Installer not initialized yet, process abort!");
            return;
        }
        if (z) {
            long currentTimeMillis = System.currentTimeMillis();
            c();
            if (z2) {
                a();
            }
            f12265c.b("dexopt auto start bundles cost time = " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        } else {
            long currentTimeMillis2 = System.currentTimeMillis();
            e();
            com.jingdong.aura.core.util.l.b bVar = f12265c;
            bVar.b("dexopt bundles not delayed cost time = " + (System.currentTimeMillis() - currentTimeMillis2) + " ms");
            if (z2) {
                a();
            }
            long currentTimeMillis3 = System.currentTimeMillis();
            b().d();
            bVar.b("dexopt delayed bundles cost time = " + (System.currentTimeMillis() - currentTimeMillis3) + " ms");
        }
    }

    private void a() {
        com.jingdong.aura.wrapper.c.e(this.a);
        com.jingdong.aura.wrapper.c.d(this.a);
    }

    private boolean a(String[] strArr, String str) {
        if (strArr != null && str != null) {
            for (String str2 : strArr) {
                if (str2 != null && str2.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
