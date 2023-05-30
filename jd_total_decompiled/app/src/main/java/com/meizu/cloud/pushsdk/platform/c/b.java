package com.meizu.cloud.pushsdk.platform.c;

import android.content.Context;
import com.meizu.cloud.pushsdk.e.b.c;
import com.meizu.cloud.pushsdk.platform.d.d;
import com.meizu.cloud.pushsdk.platform.d.e;
import com.meizu.cloud.pushsdk.platform.d.f;
import com.meizu.cloud.pushsdk.platform.d.g;
import java.io.File;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes14.dex */
public class b {

    /* renamed from: j  reason: collision with root package name */
    private static b f16009j;
    private ScheduledExecutorService a;
    private final Context b;

    /* renamed from: c  reason: collision with root package name */
    private final a f16010c;
    private final com.meizu.cloud.pushsdk.platform.d.b d;

    /* renamed from: e  reason: collision with root package name */
    private final g f16011e;

    /* renamed from: f  reason: collision with root package name */
    private final f f16012f;

    /* renamed from: g  reason: collision with root package name */
    private final e f16013g;

    /* renamed from: h  reason: collision with root package name */
    private final d f16014h;

    /* renamed from: i  reason: collision with root package name */
    private final boolean f16015i;

    public b(Context context, boolean z) {
        this(context, z, true);
    }

    public b(Context context, boolean z, boolean z2) {
        Context applicationContext = context.getApplicationContext();
        this.b = applicationContext;
        a aVar = new a(applicationContext);
        this.f16010c = aVar;
        if (z) {
            this.a = (ScheduledExecutorService) com.meizu.cloud.pushsdk.f.c.h.b.a();
        }
        this.f16015i = z2;
        ScheduledExecutorService scheduledExecutorService = this.a;
        this.d = new com.meizu.cloud.pushsdk.platform.d.b(applicationContext, aVar, scheduledExecutorService, z2);
        this.f16011e = new g(applicationContext, aVar, scheduledExecutorService, z2);
        this.f16012f = new f(applicationContext, aVar, scheduledExecutorService, z2);
        ScheduledExecutorService scheduledExecutorService2 = this.a;
        this.f16013g = new e(applicationContext, aVar, scheduledExecutorService2, z2);
        this.f16014h = new d(applicationContext, aVar, scheduledExecutorService2, z2);
    }

    public static b b(Context context) {
        if (f16009j == null) {
            synchronized (b.class) {
                if (f16009j == null) {
                    f16009j = new b(context, true);
                }
            }
        }
        return f16009j;
    }

    public c<String> a(String str, String str2, String str3, String str4, File file) {
        return this.f16010c.d(str, str2, str3, str4, file);
    }

    public void c(boolean z) {
        this.d.e(z);
        this.f16011e.e(z);
        this.f16012f.e(z);
        this.f16014h.e(z);
        this.f16013g.e(z);
    }

    public boolean d(String str) {
        com.meizu.cloud.pushsdk.platform.d.a aVar = new com.meizu.cloud.pushsdk.platform.d.a(this.b, this.a, this.f16015i);
        aVar.w(0);
        aVar.l(str);
        return aVar.o();
    }

    public boolean e(String str, String str2) {
        com.meizu.cloud.pushsdk.platform.d.a aVar = new com.meizu.cloud.pushsdk.platform.d.a(this.b, this.a, this.f16015i);
        aVar.w(2);
        aVar.x(str2);
        aVar.l(str);
        return aVar.o();
    }

    public boolean f(String str, String str2, String str3) {
        this.d.d(str);
        this.d.i(str2);
        this.d.l(str3);
        return this.d.o();
    }

    public boolean g(String str, String str2, String str3, String str4) {
        this.f16012f.d(str);
        this.f16012f.i(str2);
        this.f16012f.l(str3);
        this.f16012f.A(str4);
        this.f16012f.w(2);
        return this.f16012f.o();
    }

    public boolean h(String str, String str2, String str3, String str4, int i2, boolean z) {
        this.f16012f.d(str);
        this.f16012f.i(str2);
        this.f16012f.l(str3);
        this.f16012f.A(str4);
        this.f16012f.w(i2);
        this.f16012f.z(z);
        return this.f16012f.o();
    }

    public boolean i(String str, String str2, String str3, String str4, String str5) {
        this.f16014h.d(str);
        this.f16014h.i(str2);
        this.f16014h.l(str3);
        this.f16014h.z(str4);
        this.f16014h.w(0);
        this.f16014h.y(str5);
        return this.f16014h.o();
    }

    public boolean j(String str, String str2, String str3, String str4, boolean z) {
        this.f16012f.d(str);
        this.f16012f.i(str2);
        this.f16012f.l(str3);
        this.f16012f.A(str4);
        this.f16012f.w(3);
        this.f16012f.z(z);
        return this.f16012f.o();
    }

    public boolean k(String str, int... iArr) {
        com.meizu.cloud.pushsdk.platform.d.a aVar = new com.meizu.cloud.pushsdk.platform.d.a(this.b, this.a, this.f16015i);
        aVar.v(iArr);
        aVar.l(str);
        aVar.w(1);
        return aVar.o();
    }

    public boolean l(String str, String str2, String str3) {
        this.f16011e.d(str);
        this.f16011e.i(str2);
        this.f16011e.l(str3);
        return this.f16011e.o();
    }

    public boolean m(String str, String str2, String str3, String str4) {
        this.f16014h.d(str);
        this.f16014h.i(str2);
        this.f16014h.l(str3);
        this.f16014h.z(str4);
        this.f16014h.w(2);
        return this.f16014h.o();
    }

    public boolean n(String str, String str2, String str3, String str4, String str5) {
        this.f16013g.d(str);
        this.f16013g.i(str2);
        this.f16013g.l(str3);
        this.f16013g.x(str4);
        this.f16013g.w(0);
        this.f16013g.y(str5);
        return this.f16013g.o();
    }

    public boolean o(String str, String str2, String str3, String str4) {
        this.f16013g.d(str);
        this.f16013g.i(str2);
        this.f16013g.l(str3);
        this.f16013g.x(str4);
        this.f16013g.w(3);
        return this.f16013g.o();
    }

    public boolean p(String str, String str2, String str3, String str4, String str5) {
        this.f16014h.d(str);
        this.f16014h.i(str2);
        this.f16014h.l(str3);
        this.f16014h.z(str4);
        this.f16014h.w(1);
        this.f16014h.y(str5);
        return this.f16014h.o();
    }

    public boolean q(String str, String str2, String str3, String str4) {
        this.f16013g.d(str);
        this.f16013g.i(str2);
        this.f16013g.l(str3);
        this.f16013g.x(str4);
        this.f16013g.w(2);
        return this.f16013g.o();
    }

    public boolean r(String str, String str2, String str3, String str4, String str5) {
        this.f16013g.d(str);
        this.f16013g.i(str2);
        this.f16013g.l(str3);
        this.f16013g.x(str4);
        this.f16013g.w(1);
        this.f16013g.y(str5);
        return this.f16013g.o();
    }
}
