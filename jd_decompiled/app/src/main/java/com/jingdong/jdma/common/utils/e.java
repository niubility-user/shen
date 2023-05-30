package com.jingdong.jdma.common.utils;

import com.jingdong.jdma.f.d;
import java.util.Random;

/* loaded from: classes12.dex */
public class e {
    private static long a = -1;
    private static int b = 0;

    /* renamed from: c  reason: collision with root package name */
    private static int f12688c = -1;
    private static int d = 0;

    /* renamed from: e  reason: collision with root package name */
    private static int f12689e = -1;

    /* renamed from: f  reason: collision with root package name */
    private static long f12690f = 0;

    /* renamed from: g  reason: collision with root package name */
    private static int f12691g = -1;

    /* renamed from: h  reason: collision with root package name */
    private static int f12692h = 0;

    /* renamed from: i  reason: collision with root package name */
    private static int f12693i = -1;

    /* renamed from: j  reason: collision with root package name */
    private static int f12694j = 0;

    /* renamed from: k  reason: collision with root package name */
    private static int f12695k = -1;

    /* renamed from: l  reason: collision with root package name */
    private static int f12696l;

    public static long a() {
        if (a == -1 || b != c.B) {
            a = n.a().c() ? new Random().nextInt(c.B + 1) : 0L;
            b = c.B;
        }
        return a;
    }

    public static int b(int i2) {
        if (i2 == d.a.f12731g.a()) {
            if (f12693i == -1 || f12694j != c.A) {
                f12693i = n.a().c() ? new Random().nextInt(c.A + 1) : 0;
                f12694j = c.A;
            }
            return f12693i;
        }
        if (f12691g == -1 || f12692h != c.z) {
            f12691g = n.a().c() ? new Random().nextInt(c.z + 1) : 0;
            f12692h = c.z;
        }
        return f12691g;
    }

    public static int c(int i2) {
        if (n.a().c()) {
            return i2 * 1000;
        }
        return 0;
    }

    public static int a(int i2) {
        if (i2 == d.a.f12731g.a()) {
            if (f12689e == -1 || f12690f != c.y) {
                f12689e = n.a().c() ? new Random().nextInt(c.y + 1) : 0;
                f12690f = c.y;
            }
            return f12689e;
        }
        if (f12688c == -1 || d != c.x) {
            f12688c = n.a().c() ? new Random().nextInt(c.x + 1) : 0;
            d = c.x;
        }
        return f12688c;
    }

    public static int b() {
        if (f12695k == -1 || f12696l != c.C) {
            f12695k = n.a().c() ? new Random().nextInt(c.C + 1) * 1000 : 0;
            f12696l = c.C;
        }
        return f12695k;
    }
}
