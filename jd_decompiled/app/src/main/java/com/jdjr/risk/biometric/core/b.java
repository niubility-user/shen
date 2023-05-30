package com.jdjr.risk.biometric.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jdjr.risk.device.b.f;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class b {
    private static b a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private int f7303c;
    private volatile int d = 0;

    /* renamed from: e  reason: collision with root package name */
    private ReadWriteLock f7304e;

    /* renamed from: f  reason: collision with root package name */
    private ReentrantReadWriteLock f7305f;

    private b(Context context) {
        this.b = "";
        this.f7303c = 0;
        this.f7304e = null;
        this.f7305f = null;
        SharedPreferences a2 = com.jdjr.risk.util.a.d.a(context);
        this.b = a2.getString("RT_POLICY", "");
        this.f7303c = a2.getInt("RT_CODE", 0);
        this.f7304e = new ReentrantReadWriteLock();
        this.f7305f = new ReentrantReadWriteLock();
    }

    public static b a(Context context) {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b(context);
                }
            }
        }
        return a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0028, code lost:
        if (r5.getInt("rt_crash_" + r6, 0) == 0) goto L7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean c(android.content.Context r5, int r6) {
        /*
            r4 = this;
            android.content.SharedPreferences r5 = com.jdjr.risk.util.a.d.a(r5)
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r4.f7305f
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r0.readLock()
            r0.lock()
            r0 = 0
            int r1 = r4.d     // Catch: java.lang.Throwable -> L2b
            r2 = 1
            if (r1 != 0) goto L2a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2b
            r1.<init>()     // Catch: java.lang.Throwable -> L2b
            java.lang.String r3 = "rt_crash_"
            r1.append(r3)     // Catch: java.lang.Throwable -> L2b
            r1.append(r6)     // Catch: java.lang.Throwable -> L2b
            java.lang.String r6 = r1.toString()     // Catch: java.lang.Throwable -> L2b
            int r5 = r5.getInt(r6, r0)     // Catch: java.lang.Throwable -> L2b
            if (r5 != 0) goto L2b
        L2a:
            r0 = 1
        L2b:
            java.util.concurrent.locks.ReentrantReadWriteLock r5 = r4.f7305f
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r5 = r5.readLock()
            r5.unlock()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.biometric.core.b.c(android.content.Context, int):boolean");
    }

    public JSONObject a(com.jdjr.risk.biometric.a.a aVar, String str) {
        JSONObject x;
        if (aVar != null) {
            try {
                if (TextUtils.equals(str, "device_basic_info")) {
                    if (aVar.c() != 1) {
                        return null;
                    }
                    x = aVar.d();
                } else if (TextUtils.equals(str, f.f7325c)) {
                    if (aVar.e() != 1) {
                        return null;
                    }
                    x = aVar.f();
                } else if (TextUtils.equals(str, "device_network_info")) {
                    if (aVar.g() != 1) {
                        return null;
                    }
                    x = aVar.h();
                } else if (TextUtils.equals(str, "device_status_info")) {
                    if (aVar.i() != 1) {
                        return null;
                    }
                    x = aVar.j();
                } else if (TextUtils.equals(str, "device_safe_info")) {
                    if (aVar.k() != 1) {
                        return null;
                    }
                    x = aVar.l();
                } else if (TextUtils.equals(str, "device_hardware_info")) {
                    if (aVar.m() != 1) {
                        return null;
                    }
                    x = aVar.n();
                } else if (TextUtils.equals(str, "device_cpu_info")) {
                    if (aVar.o() != 1) {
                        return null;
                    }
                    x = aVar.p();
                } else if (TextUtils.equals(str, "device_app_info")) {
                    if (aVar.q() != 1) {
                        return null;
                    }
                    x = aVar.r();
                } else if (TextUtils.equals(str, "device_sdk_info")) {
                    if (aVar.s() != 1) {
                        return null;
                    }
                    x = aVar.t();
                } else if (TextUtils.equals(str, "device_system_info")) {
                    if (aVar.u() != 1) {
                        return null;
                    }
                    x = aVar.v();
                } else if (TextUtils.equals(str, "device_other_info")) {
                    if (aVar.y() != 1) {
                        return null;
                    }
                    x = aVar.z();
                } else if (!TextUtils.equals(str, "device_permission_info") || aVar.w() != 1) {
                    return null;
                } else {
                    x = aVar.x();
                }
                return x;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    public void a(Context context, int i2) {
        SharedPreferences a2 = com.jdjr.risk.util.a.d.a(context);
        this.f7305f.writeLock().lock();
        try {
            int i3 = a2.getInt("rt_crash_" + i2, 0);
            a2.edit().putInt("rt_crash_" + i2, i3 + 1).apply();
            this.d = 1;
        } catch (Throwable unused) {
        }
        this.f7305f.writeLock().unlock();
    }

    public void a(Context context, String str) {
        try {
            this.f7304e.readLock().lock();
            if (TextUtils.equals(str, this.b)) {
                this.f7304e.readLock().unlock();
                return;
            }
            this.f7304e.readLock().unlock();
            this.f7304e.writeLock().lock();
            if (!TextUtils.equals(str, this.b)) {
                this.b = str;
                this.f7303c++;
                SharedPreferences.Editor edit = com.jdjr.risk.util.a.d.a(context).edit();
                edit.putString("RT_POLICY", str);
                edit.putInt("RT_CODE", this.f7303c);
                edit.apply();
            }
            this.f7304e.writeLock().unlock();
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:1|2|3|4|(2:5|6)|(2:8|(4:10|11|(1:16)|(1:28)(5:19|20|21|22|23)))|31|32|11|(2:14|16)|(1:28)(1:29)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
        r4.f7304e.readLock().unlock();
        r2 = -1;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0049 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Map<java.lang.String, java.lang.Object> b(android.content.Context r5, java.lang.String r6) {
        /*
            r4 = this;
            r0 = 0
            java.util.concurrent.locks.ReadWriteLock r1 = r4.f7304e     // Catch: java.lang.Throwable -> L5f
            java.util.concurrent.locks.Lock r1 = r1.readLock()     // Catch: java.lang.Throwable -> L5f
            r1.lock()     // Catch: java.lang.Throwable -> L5f
            r1 = -1
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L30
            java.lang.String r3 = r4.b     // Catch: java.lang.Throwable -> L30
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L30
            org.json.JSONObject r6 = r2.optJSONObject(r6)     // Catch: java.lang.Throwable -> L30
            if (r6 == 0) goto L1b
        L18:
            int r2 = r4.f7303c     // Catch: java.lang.Throwable -> L31
            goto L26
        L1b:
            java.lang.String r6 = "ALL"
            org.json.JSONObject r6 = r2.optJSONObject(r6)     // Catch: java.lang.Throwable -> L30
            if (r6 == 0) goto L24
            goto L18
        L24:
            r6 = r0
            r2 = -1
        L26:
            java.util.concurrent.locks.ReadWriteLock r3 = r4.f7304e     // Catch: java.lang.Throwable -> L5f
            java.util.concurrent.locks.Lock r3 = r3.readLock()     // Catch: java.lang.Throwable -> L5f
            r3.unlock()     // Catch: java.lang.Throwable -> L5f
            goto L3b
        L30:
            r6 = r0
        L31:
            java.util.concurrent.locks.ReadWriteLock r2 = r4.f7304e     // Catch: java.lang.Throwable -> L5f
            java.util.concurrent.locks.Lock r2 = r2.readLock()     // Catch: java.lang.Throwable -> L5f
            r2.unlock()     // Catch: java.lang.Throwable -> L5f
            r2 = -1
        L3b:
            if (r6 == 0) goto L47
            if (r2 == r1) goto L47
            boolean r5 = r4.c(r5, r2)     // Catch: java.lang.Throwable -> L5f
            if (r5 != 0) goto L47
            r6 = r0
            r2 = -1
        L47:
            if (r6 == 0) goto L5f
            if (r2 == r1) goto L5f
            java.util.HashMap r5 = new java.util.HashMap     // Catch: java.lang.Throwable -> L5f
            r5.<init>()     // Catch: java.lang.Throwable -> L5f
            java.lang.String r0 = "policy"
            r5.put(r0, r6)     // Catch: java.lang.Throwable -> L5e
            java.lang.String r6 = "version"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L5e
            r5.put(r6, r0)     // Catch: java.lang.Throwable -> L5e
        L5e:
            r0 = r5
        L5f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jdjr.risk.biometric.core.b.b(android.content.Context, java.lang.String):java.util.Map");
    }

    public void b(Context context, int i2) {
        SharedPreferences a2 = com.jdjr.risk.util.a.d.a(context);
        this.f7305f.writeLock().lock();
        try {
            int i3 = a2.getInt("rt_crash_" + i2, 0);
            int i4 = i3 - 1;
            a2.edit().putInt("rt_crash_" + i2, i4).apply();
            if (i4 == 0) {
                this.d = 0;
            }
        } catch (Throwable unused) {
        }
        this.f7305f.writeLock().unlock();
    }
}
