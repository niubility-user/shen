package com.xiaomi.mipush.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.xiaomi.push.a8;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.d4;
import com.xiaomi.push.d7;
import com.xiaomi.push.d8;
import com.xiaomi.push.g7;
import com.xiaomi.push.h7;
import com.xiaomi.push.j8;
import com.xiaomi.push.m7;
import com.xiaomi.push.m8;
import com.xiaomi.push.n8;
import com.xiaomi.push.p7;
import com.xiaomi.push.service.g2;
import com.xiaomi.push.w1;
import com.xiaomi.push.y7;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public class f0 {

    /* renamed from: l */
    private static f0 f18372l;

    /* renamed from: m */
    private static boolean f18373m;

    /* renamed from: n */
    private static final ArrayList<a> f18374n = new ArrayList<>();
    private boolean a;
    private Context b;
    private Messenger d;

    /* renamed from: e */
    private Handler f18376e;

    /* renamed from: h */
    private long f18379h;

    /* renamed from: f */
    private List<Message> f18377f = new ArrayList();

    /* renamed from: g */
    private boolean f18378g = false;

    /* renamed from: i */
    private String f18380i = null;

    /* renamed from: j */
    private Intent f18381j = null;

    /* renamed from: k */
    private Integer f18382k = null;

    /* renamed from: c */
    private String f18375c = null;

    /* loaded from: classes11.dex */
    public static class a<T extends n8<T, ?>> {
        T a;
        c7 b;

        /* renamed from: c */
        boolean f18383c;

        a() {
        }
    }

    private f0(Context context) {
        this.a = false;
        this.f18376e = null;
        this.b = context.getApplicationContext();
        this.a = U();
        f18373m = Y();
        this.f18376e = new g0(this, Looper.getMainLooper());
        if (a8.j(context)) {
            g2.a(new h0(this));
        }
        Intent M = M();
        if (M != null) {
            O(M);
        }
    }

    public void F(String str, l0 l0Var, boolean z, HashMap<String, String> hashMap) {
        c8 c8Var;
        String str2;
        String str3 = str;
        if (o0.c(this.b).p() && com.xiaomi.push.j0.p(this.b)) {
            c8 c8Var2 = new c8();
            c8Var2.a(true);
            Intent d = d();
            if (TextUtils.isEmpty(str)) {
                str3 = com.xiaomi.push.service.f0.a();
                c8Var2.a(str3);
                c8Var = z ? new c8(str3, true) : null;
                synchronized (x.class) {
                    x.b(this.b).e(str3);
                }
            } else {
                c8Var2.a(str3);
                c8Var = z ? new c8(str3, true) : null;
            }
            switch (k0.a[l0Var.ordinal()]) {
                case 1:
                    m7 m7Var = m7.DisablePushMessage;
                    c8Var2.c(m7Var.f179a);
                    c8Var.c(m7Var.f179a);
                    if (hashMap != null) {
                        c8Var2.a(hashMap);
                        c8Var.a(hashMap);
                    }
                    str2 = "com.xiaomi.mipush.DISABLE_PUSH_MESSAGE";
                    d.setAction(str2);
                    break;
                case 2:
                    m7 m7Var2 = m7.EnablePushMessage;
                    c8Var2.c(m7Var2.f179a);
                    c8Var.c(m7Var2.f179a);
                    if (hashMap != null) {
                        c8Var2.a(hashMap);
                        c8Var.a(hashMap);
                    }
                    str2 = "com.xiaomi.mipush.ENABLE_PUSH_MESSAGE";
                    d.setAction(str2);
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                    c8Var2.c(m7.ThirdPartyRegUpdate.f179a);
                    if (hashMap != null) {
                        c8Var2.a(hashMap);
                        break;
                    }
                    break;
            }
            g.j.a.a.a.c.E("type:" + l0Var + ", " + str3);
            c8Var2.b(o0.c(this.b).d());
            c8Var2.d(this.b.getPackageName());
            c7 c7Var = c7.Notification;
            y(c8Var2, c7Var, false, null);
            if (z) {
                c8Var.b(o0.c(this.b).d());
                c8Var.d(this.b.getPackageName());
                Context context = this.b;
                byte[] f2 = m8.f(z.b(context, c8Var, c7Var, false, context.getPackageName(), o0.c(this.b).d()));
                if (f2 != null) {
                    w1.f(this.b.getPackageName(), this.b, c8Var, c7Var, f2.length);
                    d.putExtra("mipush_payload", f2);
                    d.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
                    d.putExtra("mipush_app_id", o0.c(this.b).d());
                    d.putExtra("mipush_app_token", o0.c(this.b).m());
                    T(d);
                }
            }
            Message obtain = Message.obtain();
            obtain.what = 19;
            int ordinal = l0Var.ordinal();
            obtain.obj = str3;
            obtain.arg1 = ordinal;
            if (hashMap != null && hashMap.get("third_sync_reason") != null) {
                Bundle bundle = new Bundle();
                bundle.putString("third_sync_reason", hashMap.get("third_sync_reason"));
                obtain.setData(bundle);
            }
            this.f18376e.sendMessageDelayed(obtain, Final.FIVE_SECOND);
        }
    }

    private Intent M() {
        if ("com.xiaomi.xmsf".equals(this.b.getPackageName())) {
            g.j.a.a.a.c.B("pushChannel xmsf create own channel");
            return Z();
        }
        return Q();
    }

    private void O(Intent intent) {
        try {
            if (a8.i() || Build.VERSION.SDK_INT < 26) {
                this.b.startService(intent);
            } else {
                X(intent);
            }
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
        }
    }

    private Intent Q() {
        if (J()) {
            g.j.a.a.a.c.B("pushChannel app start miui china channel");
            return V();
        }
        g.j.a.a.a.c.B("pushChannel app start  own channel");
        return Z();
    }

    private synchronized void S(int i2) {
        this.b.getSharedPreferences("mipush_extra", 0).edit().putInt("service_boot_mode", i2).commit();
    }

    private void T(Intent intent) {
        com.xiaomi.push.service.b0 d = com.xiaomi.push.service.b0.d(this.b);
        int a2 = h7.ServiceBootMode.a();
        d7 d7Var = d7.START;
        int a3 = d.a(a2, d7Var.a());
        int a4 = a();
        d7 d7Var2 = d7.BIND;
        boolean z = a3 == d7Var2.a() && f18373m;
        int a5 = z ? d7Var2.a() : d7Var.a();
        if (a5 != a4) {
            K(a5);
        }
        if (z) {
            X(intent);
        } else {
            O(intent);
        }
    }

    private boolean U() {
        try {
            PackageInfo packageInfo = this.b.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
            if (packageInfo == null) {
                return false;
            }
            return packageInfo.versionCode >= 105;
        } catch (Throwable unused) {
            return false;
        }
    }

    private Intent V() {
        Intent intent = new Intent();
        String packageName = this.b.getPackageName();
        intent.setPackage("com.xiaomi.xmsf");
        intent.setClassName("com.xiaomi.xmsf", k());
        intent.putExtra("mipush_app_package", packageName);
        d0();
        return intent;
    }

    private synchronized void X(Intent intent) {
        if (this.f18378g) {
            Message e2 = e(intent);
            if (this.f18377f.size() >= 50) {
                this.f18377f.remove(0);
            }
            this.f18377f.add(e2);
            return;
        }
        if (this.d == null) {
            this.b.bindService(intent, new j0(this), 1);
            this.f18378g = true;
            this.f18377f.clear();
            this.f18377f.add(e(intent));
        } else {
            try {
                this.d.send(e(intent));
            } catch (RemoteException unused) {
                this.d = null;
                this.f18378g = false;
            }
        }
    }

    private boolean Y() {
        if (J()) {
            try {
                return this.b.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= 108;
            } catch (Exception unused) {
            }
        }
        return true;
    }

    private Intent Z() {
        Intent intent = new Intent();
        String packageName = this.b.getPackageName();
        e0();
        intent.setComponent(new ComponentName(this.b, "com.xiaomi.push.service.XMPushService"));
        intent.putExtra("mipush_app_package", packageName);
        return intent;
    }

    private synchronized int a() {
        return this.b.getSharedPreferences("mipush_extra", 0).getInt("service_boot_mode", -1);
    }

    private boolean a0() {
        String packageName = this.b.getPackageName();
        return packageName.contains("miui") || packageName.contains("xiaomi") || (this.b.getApplicationInfo().flags & 1) != 0;
    }

    private void c0() {
        this.f18379h = SystemClock.elapsedRealtime();
    }

    private Intent d() {
        return (!J() || "com.xiaomi.xmsf".equals(this.b.getPackageName())) ? Z() : V();
    }

    private void d0() {
        try {
            PackageManager packageManager = this.b.getPackageManager();
            ComponentName componentName = new ComponentName(this.b, "com.xiaomi.push.service.XMPushService");
            if (packageManager.getComponentEnabledSetting(componentName) == 2) {
                return;
            }
            packageManager.setComponentEnabledSetting(componentName, 2, 1);
        } catch (Throwable unused) {
        }
    }

    private Message e(Intent intent) {
        Message obtain = Message.obtain();
        obtain.what = 17;
        obtain.obj = intent;
        return obtain;
    }

    private void e0() {
        try {
            PackageManager packageManager = this.b.getPackageManager();
            ComponentName componentName = new ComponentName(this.b, "com.xiaomi.push.service.XMPushService");
            if (packageManager.getComponentEnabledSetting(componentName) == 1) {
                return;
            }
            packageManager.setComponentEnabledSetting(componentName, 1, 1);
        } catch (Throwable unused) {
        }
    }

    public static synchronized f0 h(Context context) {
        f0 f0Var;
        synchronized (f0.class) {
            if (f18372l == null) {
                f18372l = new f0(context);
            }
            f0Var = f18372l;
        }
        return f0Var;
    }

    private String k() {
        String str = this.f18380i;
        if (str != null) {
            return str;
        }
        try {
            if (this.b.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= 106) {
                this.f18380i = "com.xiaomi.push.service.XMPushService";
                return "com.xiaomi.push.service.XMPushService";
            }
        } catch (Exception unused) {
        }
        this.f18380i = "com.xiaomi.xmsf.push.service.XMPushService";
        return "com.xiaomi.xmsf.push.service.XMPushService";
    }

    public final <T extends n8<T, ?>> void A(T t, c7 c7Var, boolean z, boolean z2, p7 p7Var, boolean z3) {
        B(t, c7Var, z, z2, p7Var, z3, this.b.getPackageName(), o0.c(this.b).d());
    }

    public final <T extends n8<T, ?>> void B(T t, c7 c7Var, boolean z, boolean z2, p7 p7Var, boolean z3, String str, String str2) {
        C(t, c7Var, z, z2, p7Var, z3, str, str2, true);
    }

    public final <T extends n8<T, ?>> void C(T t, c7 c7Var, boolean z, boolean z2, p7 p7Var, boolean z3, String str, String str2, boolean z4) {
        D(t, c7Var, z, z2, p7Var, z3, str, str2, z4, true);
    }

    public final <T extends n8<T, ?>> void D(T t, c7 c7Var, boolean z, boolean z2, p7 p7Var, boolean z3, String str, String str2, boolean z4, boolean z5) {
        if (z5 && !o0.c(this.b).s()) {
            if (z2) {
                x(t, c7Var, z);
                return;
            } else {
                g.j.a.a.a.c.o("drop the message before initialization.");
                return;
            }
        }
        y7 b = z4 ? z.b(this.b, t, c7Var, z, str, str2) : z.f(this.b, t, c7Var, z, str, str2);
        if (p7Var != null) {
            b.a(p7Var);
        }
        byte[] f2 = m8.f(b);
        if (f2 == null) {
            g.j.a.a.a.c.o("send message fail, because msgBytes is null.");
            return;
        }
        w1.f(this.b.getPackageName(), this.b, t, c7Var, f2.length);
        Intent d = d();
        d.setAction("com.xiaomi.mipush.SEND_MESSAGE");
        d.putExtra("mipush_payload", f2);
        d.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", z3);
        T(d);
    }

    public final void E(String str, l0 l0Var, r0 r0Var, String str2) {
        x.b(this.b).d(l0Var, "syncing");
        HashMap<String, String> e2 = v0.e(this.b, r0Var);
        e2.put("third_sync_reason", str2);
        F(str, l0Var, false, e2);
    }

    public void G(String str, String str2) {
        Intent d = d();
        d.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        d.putExtra(com.xiaomi.push.service.m0.B, this.b.getPackageName());
        d.putExtra(com.xiaomi.push.service.m0.H, str);
        d.putExtra(com.xiaomi.push.service.m0.I, str2);
        T(d);
    }

    public final void H(boolean z) {
        I(z, null);
    }

    public final void I(boolean z, String str) {
        l0 l0Var;
        x b;
        l0 l0Var2;
        if (z) {
            x b2 = x.b(this.b);
            l0Var = l0.DISABLE_PUSH;
            b2.d(l0Var, "syncing");
            b = x.b(this.b);
            l0Var2 = l0.ENABLE_PUSH;
        } else {
            x b3 = x.b(this.b);
            l0Var = l0.ENABLE_PUSH;
            b3.d(l0Var, "syncing");
            b = x.b(this.b);
            l0Var2 = l0.DISABLE_PUSH;
        }
        b.d(l0Var2, "");
        F(str, l0Var, true, null);
    }

    public boolean J() {
        return this.a && 1 == o0.c(this.b).a();
    }

    public boolean K(int i2) {
        if (o0.c(this.b).p()) {
            S(i2);
            c8 c8Var = new c8();
            c8Var.a(com.xiaomi.push.service.f0.a());
            c8Var.b(o0.c(this.b).d());
            c8Var.d(this.b.getPackageName());
            c8Var.c(m7.ClientABTest.f179a);
            HashMap hashMap = new HashMap();
            c8Var.f113a = hashMap;
            hashMap.put("boot_mode", i2 + "");
            h(this.b).y(c8Var, c7.Notification, false, null);
            return true;
        }
        return false;
    }

    public final void N() {
        Intent d = d();
        d.setAction("com.xiaomi.mipush.DISABLE_PUSH");
        T(d);
    }

    public boolean P() {
        if (J() && a0()) {
            if (this.f18382k == null) {
                Integer valueOf = Integer.valueOf(com.xiaomi.push.service.p0.c(this.b).a());
                this.f18382k = valueOf;
                if (valueOf.intValue() == 0) {
                    this.b.getContentResolver().registerContentObserver(com.xiaomi.push.service.p0.c(this.b).b(), false, new i0(this, new Handler(Looper.getMainLooper())));
                }
            }
            return this.f18382k.intValue() != 0;
        }
        return true;
    }

    public void R() {
        if (this.f18381j != null) {
            c0();
            T(this.f18381j);
            this.f18381j = null;
        }
    }

    public void W() {
        ArrayList<a> arrayList = f18374n;
        synchronized (arrayList) {
            boolean z = Thread.currentThread() == Looper.getMainLooper().getThread();
            Iterator<a> it = arrayList.iterator();
            while (it.hasNext()) {
                a next = it.next();
                A(next.a, next.b, next.f18383c, false, null, true);
                if (!z) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
            f18374n.clear();
        }
    }

    public long b() {
        return this.f18379h;
    }

    public void b0() {
        Intent d = d();
        d.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        d.putExtra(com.xiaomi.push.service.m0.B, this.b.getPackageName());
        d.putExtra(com.xiaomi.push.service.m0.G, com.xiaomi.push.o0.d(this.b.getPackageName()));
        T(d);
    }

    public void m() {
        O(d());
    }

    public void n(int i2) {
        o(i2, 0);
    }

    public void o(int i2, int i3) {
        Intent d = d();
        d.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        d.putExtra(com.xiaomi.push.service.m0.B, this.b.getPackageName());
        d.putExtra(com.xiaomi.push.service.m0.C, i2);
        d.putExtra(com.xiaomi.push.service.m0.D, i3);
        T(d);
    }

    public void p(int i2, String str) {
        Intent d = d();
        d.setAction("com.xiaomi.mipush.thirdparty");
        d.putExtra("com.xiaomi.mipush.thirdparty_LEVEL", i2);
        d.putExtra("com.xiaomi.mipush.thirdparty_DESC", str);
        O(d);
    }

    public void q(Context context) {
        if (a8.i()) {
            return;
        }
        y a2 = a1.a(context);
        if (y.HUAWEI.equals(a2)) {
            E(null, l0.UPLOAD_HUAWEI_TOKEN, r0.ASSEMBLE_PUSH_HUAWEI, "update");
        }
        if (y.OPPO.equals(a2)) {
            E(null, l0.UPLOAD_COS_TOKEN, r0.ASSEMBLE_PUSH_COS, "update");
        }
        if (y.VIVO.equals(a2)) {
            E(null, l0.UPLOAD_FTOS_TOKEN, r0.ASSEMBLE_PUSH_FTOS, "update");
        }
    }

    public void r(Intent intent) {
        intent.fillIn(d(), 24);
        T(intent);
    }

    public final void t(g7 g7Var) {
        Intent d = d();
        byte[] f2 = m8.f(g7Var);
        if (f2 == null) {
            g.j.a.a.a.c.o("send TinyData failed, because tinyDataBytes is null.");
            return;
        }
        d.setAction("com.xiaomi.mipush.SEND_TINYDATA");
        d.putExtra("mipush_payload", f2);
        O(d);
    }

    public final void u(d8 d8Var, boolean z) {
        d4.a(this.b.getApplicationContext()).g(this.b.getPackageName(), "E100003", d8Var.a(), 6001, null);
        this.f18381j = null;
        o0.c(this.b).d = d8Var.a();
        Intent d = d();
        byte[] f2 = m8.f(z.a(this.b, d8Var, c7.Registration));
        if (f2 == null) {
            g.j.a.a.a.c.o("register fail, because msgBytes is null.");
            return;
        }
        d.setAction("com.xiaomi.mipush.REGISTER_APP");
        d.putExtra("mipush_app_id", o0.c(this.b).d());
        d.putExtra("mipush_payload", f2);
        d.putExtra("mipush_session", this.f18375c);
        d.putExtra("mipush_env_chanage", z);
        d.putExtra("mipush_env_type", o0.c(this.b).a());
        if (!com.xiaomi.push.j0.p(this.b) || !P()) {
            this.f18381j = d;
            return;
        }
        c0();
        T(d);
    }

    public final void v(j8 j8Var) {
        byte[] f2 = m8.f(z.a(this.b, j8Var, c7.UnRegistration));
        if (f2 == null) {
            g.j.a.a.a.c.o("unregister fail, because msgBytes is null.");
            return;
        }
        Intent d = d();
        d.setAction("com.xiaomi.mipush.UNREGISTER_APP");
        d.putExtra("mipush_app_id", o0.c(this.b).d());
        d.putExtra("mipush_payload", f2);
        T(d);
    }

    public final <T extends n8<T, ?>> void w(T t, c7 c7Var, p7 p7Var) {
        y(t, c7Var, !c7Var.equals(c7.Registration), p7Var);
    }

    public <T extends n8<T, ?>> void x(T t, c7 c7Var, boolean z) {
        a aVar = new a();
        aVar.a = t;
        aVar.b = c7Var;
        aVar.f18383c = z;
        ArrayList<a> arrayList = f18374n;
        synchronized (arrayList) {
            arrayList.add(aVar);
            if (arrayList.size() > 10) {
                arrayList.remove(0);
            }
        }
    }

    public final <T extends n8<T, ?>> void y(T t, c7 c7Var, boolean z, p7 p7Var) {
        A(t, c7Var, z, true, p7Var, true);
    }

    public final <T extends n8<T, ?>> void z(T t, c7 c7Var, boolean z, p7 p7Var, boolean z2) {
        A(t, c7Var, z, true, p7Var, z2);
    }
}
