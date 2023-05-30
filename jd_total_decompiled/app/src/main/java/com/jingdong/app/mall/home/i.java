package com.jingdong.app.mall.home;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes4.dex */
public class i {
    private static final AtomicBoolean a = new AtomicBoolean(false);
    private static final AtomicBoolean b = new AtomicBoolean(false);

    /* renamed from: c  reason: collision with root package name */
    private static final AtomicBoolean f10300c = new AtomicBoolean(false);
    private static final AtomicInteger d = new AtomicInteger(0);

    /* renamed from: e  reason: collision with root package name */
    private static final AtomicInteger f10301e = new AtomicInteger(0);

    /* renamed from: f  reason: collision with root package name */
    private static final AtomicInteger f10302f = new AtomicInteger(0);

    /* renamed from: g  reason: collision with root package name */
    private static final AtomicInteger f10303g = new AtomicInteger(0);

    /* renamed from: h  reason: collision with root package name */
    private static final AtomicInteger f10304h = new AtomicInteger(0);

    /* renamed from: i  reason: collision with root package name */
    private static final AtomicInteger f10305i = new AtomicInteger(0);

    /* renamed from: j  reason: collision with root package name */
    private static final AtomicLong f10306j = new AtomicLong(0);

    public static void a() {
        f10303g.incrementAndGet();
    }

    public static void b() {
        d.incrementAndGet();
    }

    public static void c() {
        f10302f.incrementAndGet();
    }

    public static void d() {
        f10301e.incrementAndGet();
    }

    public static int e() {
        return f10303g.get();
    }

    public static int f() {
        return d.get();
    }

    public static int g() {
        return f10302f.get();
    }

    public static int h() {
        return f10304h.get();
    }

    public static boolean i() {
        return a.get();
    }

    public static boolean j() {
        return b.get();
    }

    public static boolean k() {
        return f10300c.get();
    }

    public static void l(boolean z) {
        b.set(z);
    }

    public static void m(long j2) {
        f10306j.set(j2);
    }

    public static void n(int i2) {
        f10304h.set(i2);
    }

    public static void o(boolean z) {
        f10300c.set(z);
    }

    public static void p(int i2) {
        f10305i.set(i2);
    }

    public static void q(boolean z) {
        a.set(z);
    }
}
