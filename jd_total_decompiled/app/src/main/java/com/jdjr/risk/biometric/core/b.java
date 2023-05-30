package com.jdjr.risk.biometric.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jdjr.risk.device.b.f;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.commons.codec.language.bm.Rule;
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
    */
    private boolean c(Context context, int i2) {
        SharedPreferences a2 = com.jdjr.risk.util.a.d.a(context);
        this.f7305f.readLock().lock();
        boolean z = false;
        try {
            if (this.d == 0) {
            }
            z = true;
        } catch (Throwable unused) {
        }
        this.f7305f.readLock().unlock();
        return z;
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
    */
    public Map<String, Object> b(Context context, String str) {
        JSONObject jSONObject;
        int i2;
        JSONObject jSONObject2;
        try {
            this.f7304e.readLock().lock();
            try {
                jSONObject2 = new JSONObject(this.b);
                jSONObject = jSONObject2.optJSONObject(str);
            } catch (Throwable unused) {
                jSONObject = null;
            }
            if (jSONObject == null) {
                jSONObject = jSONObject2.optJSONObject(Rule.ALL);
                if (jSONObject == null) {
                    jSONObject = null;
                    i2 = -1;
                    this.f7304e.readLock().unlock();
                    if (jSONObject != null && i2 != -1 && !c(context, i2)) {
                        jSONObject = null;
                        i2 = -1;
                    }
                    if (jSONObject != null && i2 != -1) {
                        HashMap hashMap = new HashMap();
                        try {
                            hashMap.put("policy", jSONObject);
                            hashMap.put("version", Integer.valueOf(i2));
                        } catch (Throwable unused2) {
                        }
                        return hashMap;
                    }
                }
            }
            i2 = this.f7303c;
            this.f7304e.readLock().unlock();
            if (jSONObject != null) {
                jSONObject = null;
                i2 = -1;
            }
            return jSONObject != null ? null : null;
        } catch (Throwable unused3) {
            return null;
        }
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
