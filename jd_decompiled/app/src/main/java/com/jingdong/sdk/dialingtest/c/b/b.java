package com.jingdong.sdk.dialingtest.c.b;

import android.os.SystemClock;
import android.text.TextUtils;
import java.net.Inet4Address;
import java.net.Inet6Address;

/* loaded from: classes7.dex */
public class b implements Runnable {

    /* renamed from: h  reason: collision with root package name */
    private String f14719h;

    /* renamed from: m  reason: collision with root package name */
    private a f14724m;

    /* renamed from: g  reason: collision with root package name */
    private final Object f14718g = new Object();

    /* renamed from: i  reason: collision with root package name */
    private int f14720i = 0;

    /* renamed from: j  reason: collision with root package name */
    private int f14721j = 3000;

    /* renamed from: k  reason: collision with root package name */
    private int f14722k = 1000;

    /* renamed from: l  reason: collision with root package name */
    private int f14723l = 0;

    /* loaded from: classes7.dex */
    public interface a {
        void a(com.jingdong.sdk.dialingtest.c.b.a aVar);
    }

    public void a() {
        com.jingdong.sdk.dialingtest.c.d.a.e().b(this);
    }

    public void b(int i2) {
        this.f14720i = i2;
    }

    public void c(a aVar) {
        this.f14724m = aVar;
    }

    public void d(String str) {
        this.f14719h = str;
    }

    public void e(int i2) {
        this.f14721j = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (TextUtils.isEmpty(this.f14719h)) {
            return;
        }
        long uptimeMillis = SystemClock.uptimeMillis();
        if (this.f14720i > 1) {
            synchronized (this.f14718g) {
                try {
                    this.f14718g.wait((this.f14720i - 1) * this.f14722k);
                } catch (Exception e2) {
                    com.jingdong.sdk.dialingtest.c.e.a.a("PingEmulatorSetting", e2.toString());
                }
            }
        }
        com.jingdong.sdk.dialingtest.c.e.a.c("PingEmulatorSetting", "index " + this.f14720i + " wait " + (SystemClock.uptimeMillis() - uptimeMillis) + " milliSec");
        com.jingdong.sdk.dialingtest.c.b.a aVar = new com.jingdong.sdk.dialingtest.c.b.a();
        aVar.a = this.f14720i;
        boolean z = false;
        long uptimeMillis2 = SystemClock.uptimeMillis();
        try {
            if (this.f14719h.contains(":")) {
                aVar.d = "ICMPv6TypeEchoReply";
                z = Inet6Address.getByName(this.f14719h).isReachable(null, this.f14723l, this.f14721j);
            } else {
                aVar.d = "ICMPv4TypeEchoReply";
                z = Inet4Address.getByName(this.f14719h).isReachable(null, this.f14723l, this.f14721j);
            }
            if (!z) {
                int i2 = this.f14723l;
                aVar.f14716e = i2 == 0 ? "-98" : "-1";
                aVar.f14717f = i2 == 0 ? "packet timeout" : "maybe time out,maybe reach the maximum ttl";
            }
        } catch (Exception e3) {
            com.jingdong.sdk.dialingtest.c.e.a.a("PingEmulatorSetting", e3.toString());
            aVar.f14717f = e3.toString();
            aVar.f14716e = e3.toString().contains("timeout") ? "-98" : "-1";
        } catch (Throwable th) {
            aVar.f14717f = th.toString();
            aVar.f14716e = th.toString().contains("timeout") ? "-98" : "-1";
        }
        int uptimeMillis3 = (int) (SystemClock.uptimeMillis() - uptimeMillis2);
        aVar.b = z;
        if (z) {
            aVar.f14715c = uptimeMillis3;
        } else {
            aVar.f14715c = -1;
        }
        a aVar2 = this.f14724m;
        if (aVar2 != null) {
            aVar2.a(aVar);
        }
    }
}
