package com.tencent.mapsdk.internal;

import android.graphics.Rect;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.tencent.mapsdk.core.components.protocol.jce.rtt.RttRequest;
import com.tencent.mapsdk.core.components.protocol.jce.user.user_login_t;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class bh extends Thread {

    /* renamed from: g  reason: collision with root package name */
    private static final String f16337g = "rttserverex";

    /* renamed from: h  reason: collision with root package name */
    private static final String f16338h = "getRtt";

    /* renamed from: i  reason: collision with root package name */
    private static final String f16339i = "info";

    /* renamed from: j  reason: collision with root package name */
    private static final String f16340j = "req";

    /* renamed from: k  reason: collision with root package name */
    public static final String f16341k = "UTF-8";

    /* renamed from: l  reason: collision with root package name */
    private static final int f16342l = 30000;
    private qc a;
    private hb b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f16343c = true;
    private boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    private boolean f16344e = false;

    /* renamed from: f  reason: collision with root package name */
    private List<zg> f16345f;

    /* loaded from: classes9.dex */
    public class b {
        public int a;
        public int b;

        private b() {
        }
    }

    public bh(qc qcVar, hb hbVar) {
        this.a = null;
        this.b = null;
        setName("tms-traffic-refresh");
        this.a = qcVar;
        this.b = hbVar;
        this.f16345f = new ArrayList();
    }

    private RttRequest a() {
        qc qcVar = this.a;
        RttRequest rttRequest = null;
        if (qcVar == null) {
            return null;
        }
        ah[] k2 = qcVar.S().k();
        if (k2 != null && k2.length != 0) {
            rttRequest = new RttRequest();
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (ah ahVar : k2) {
                arrayList.add(Integer.valueOf(ahVar.d));
                arrayList.add(Integer.valueOf(ahVar.f16249c));
                arrayList.add(Integer.valueOf(ahVar.f16251f));
                arrayList.add(Integer.valueOf(ahVar.f16250e));
                arrayList.add(Integer.valueOf(ahVar.f16252g));
            }
            ArrayList<Long> arrayList2 = new ArrayList<>();
            for (ah ahVar2 : k2) {
                arrayList2.add(Long.valueOf(ahVar2.f16254i));
            }
            rttRequest.crcs = arrayList2;
            rttRequest.type = k2[0].f16253h;
            rttRequest.bounds = arrayList;
            rttRequest.zip = (short) 1;
            rttRequest.zoom = (short) k2[0].a;
        }
        return rttRequest;
    }

    private b a(double d, double d2) {
        double sin = Math.sin((d2 * 3.1415926d) / 180.0d);
        b bVar = new b();
        bVar.a = (int) ((((d + 180.0d) / 360.0d) * 2.68435456E8d) + 0.5d);
        bVar.b = (int) ((((180.0d - ((Math.log((sin + 1.0d) / (1.0d - sin)) * 180.0d) / 6.2831852d)) / 360.0d) * 2.68435456E8d) + 0.5d);
        return bVar;
    }

    private byte[] a(RttRequest rttRequest) {
        user_login_t user_login_tVar = new user_login_t();
        user_login_tVar.pf = "android_sdk";
        user_login_tVar.is_login = false;
        user_login_tVar.channel = b7.N();
        user_login_tVar.imei = b7.A();
        f fVar = new f();
        fVar.g(f16337g);
        fVar.f(f16338h);
        fVar.a("info", (String) user_login_tVar);
        fVar.a("req", (String) rttRequest);
        return fVar.b();
    }

    private void b() {
        int r = this.a.h().r();
        Rect i2 = this.a.h().i();
        b a2 = a(i2.left / 1000000.0f, i2.bottom / 1000000.0f);
        b a3 = a(i2.right / 1000000.0f, i2.top / 1000000.0f);
        this.a.S().a(r, Math.min(a2.a, a3.a), Math.min(a2.b, a3.b), Math.max(a3.a, a2.a), Math.max(a3.b, a2.b));
    }

    private byte[] d() {
        RttRequest a2;
        if (this.a == null || (a2 = a()) == null) {
            return null;
        }
        return this.b.a(a(a2));
    }

    private void f() {
        qc qcVar = this.a;
        if (qcVar == null) {
            return;
        }
        try {
            synchronized (qcVar.S().r()) {
                b();
            }
            byte[] d = d();
            if (d == null || d.length <= 0) {
                return;
            }
            this.a.S().a(d, d.length, true, false);
        } catch (Throwable th) {
            ma.b("refreshTrafficData error", th);
        }
    }

    public void a(zg zgVar) {
        List<zg> list = this.f16345f;
        if (list == null || zgVar == null) {
            return;
        }
        list.add(zgVar);
    }

    public void b(zg zgVar) {
        List<zg> list = this.f16345f;
        if (list == null || zgVar == null) {
            return;
        }
        list.remove(zgVar);
    }

    public void c() {
        this.d = false;
        synchronized (this) {
            notifyAll();
        }
    }

    public void e() {
        this.d = true;
        synchronized (this) {
            notifyAll();
        }
    }

    public void g() {
        this.f16344e = true;
        synchronized (this) {
            notifyAll();
        }
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        while (!this.f16344e) {
            if (!this.d) {
                if (this.a == null) {
                    return;
                }
                f();
                this.a.w0();
            }
            try {
                synchronized (this) {
                    if (this.f16343c) {
                        wait(500L);
                        this.f16343c = false;
                    } else {
                        wait(Final.HALF_MINUTE);
                    }
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
