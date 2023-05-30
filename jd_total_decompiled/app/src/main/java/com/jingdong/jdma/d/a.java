package com.jingdong.jdma.d;

import com.jingdong.jdma.minterface.HttpDns;

/* loaded from: classes12.dex */
public class a {
    private static a d;
    private HttpDns a;
    private volatile int b = 0;

    /* renamed from: c  reason: collision with root package name */
    private boolean f12710c = false;

    public static a d() {
        if (d == null) {
            synchronized (a.class) {
                if (d == null) {
                    d = new a();
                }
            }
        }
        return d;
    }

    public void a(HttpDns httpDns) {
        this.a = httpDns;
        this.f12710c = httpDns != null && httpDns.isHttpDnsEnabled();
    }

    public boolean b() {
        return this.f12710c;
    }

    public void c() {
        this.b = 0;
    }

    public boolean e() {
        return this.b > 4;
    }

    public String a(String str) {
        HttpDns httpDns = this.a;
        if (httpDns != null) {
            return httpDns.getIpByHost(str);
        }
        return null;
    }

    public void a() {
        this.b++;
    }

    public void a(boolean z) {
        this.f12710c = z;
    }
}
