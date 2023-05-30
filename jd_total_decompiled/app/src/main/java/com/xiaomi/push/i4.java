package com.xiaomi.push;

import android.content.Context;
import android.content.pm.ServiceInfo;
import android.os.Build;
import com.xiaomi.push.service.XMJobService;

/* loaded from: classes11.dex */
public final class i4 {
    private static a a;
    private static final String b = XMJobService.class.getCanonicalName();

    /* renamed from: c */
    private static int f18739c = 0;

    /* loaded from: classes11.dex */
    public interface a {
        void a();

        void a(boolean z);

        /* renamed from: a */
        boolean mo81a();
    }

    public static synchronized void a() {
        synchronized (i4.class) {
            if (a == null) {
                return;
            }
            g.j.a.a.a.c.o("[Alarm] stop alarm.");
            a.a();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:124:0x005a, code lost:
        if (r7.equals(com.xiaomi.push.r9.c(r9, r6.name).getSuperclass().getCanonicalName()) != false) goto L122;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void b(Context context) {
        j4 j4Var;
        Context applicationContext = context.getApplicationContext();
        if ("com.xiaomi.xmsf".equals(applicationContext.getPackageName())) {
            j4Var = new j4(applicationContext);
        } else {
            int i2 = 0;
            try {
                ServiceInfo[] serviceInfoArr = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 4).services;
                if (serviceInfoArr != null) {
                    int length = serviceInfoArr.length;
                    int i3 = 0;
                    while (i2 < length) {
                        try {
                            ServiceInfo serviceInfo = serviceInfoArr[i2];
                            if ("android.permission.BIND_JOB_SERVICE".equals(serviceInfo.permission)) {
                                String str = b;
                                if (!str.equals(serviceInfo.name)) {
                                }
                                i3 = 1;
                                if (i3 == 1) {
                                    break;
                                }
                            }
                            if (b.equals(serviceInfo.name) && "android.permission.BIND_JOB_SERVICE".equals(serviceInfo.permission)) {
                                i2 = 1;
                                break;
                            }
                            i2++;
                        } catch (Exception e2) {
                            e = e2;
                            i2 = i3;
                            g.j.a.a.a.c.o("check service err : " + e.getMessage());
                            if (i2 != 0) {
                            }
                            int i4 = Build.VERSION.SDK_INT;
                            j4Var = new j4(applicationContext);
                            a = j4Var;
                        }
                    }
                    i2 = i3;
                }
            } catch (Exception e3) {
                e = e3;
            }
            if (i2 != 0 && r9.g(applicationContext)) {
                throw new RuntimeException("Should export service: " + b + " with permission android.permission.BIND_JOB_SERVICE in AndroidManifest.xml file");
            }
            int i42 = Build.VERSION.SDK_INT;
            j4Var = new j4(applicationContext);
        }
        a = j4Var;
    }

    public static synchronized void c(Context context, int i2) {
        synchronized (i4.class) {
            int i3 = f18739c;
            if (!"com.xiaomi.xmsf".equals(context.getPackageName())) {
                if (i2 == 2) {
                    f18739c = 2;
                } else {
                    f18739c = 0;
                }
            }
            int i4 = f18739c;
            if (i3 != i4 && i4 == 2) {
                a();
                a = new k4(context);
            }
        }
    }

    public static synchronized void d(boolean z) {
        synchronized (i4.class) {
            if (a == null) {
                g.j.a.a.a.c.o("timer is not initialized");
                return;
            }
            g.j.a.a.a.c.o("[Alarm] register alarm. (" + z + ")");
            a.a(z);
        }
    }

    public static synchronized boolean e() {
        synchronized (i4.class) {
            a aVar = a;
            if (aVar == null) {
                return false;
            }
            return aVar.mo81a();
        }
    }
}
