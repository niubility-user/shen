package com.jingdong.jdma.f;

import com.jingdong.jdma.common.utils.m;
import com.jingdong.jdma.common.utils.n;
import com.tencent.tencentmap.mapsdk.maps.TencentMapServiceProtocol;

/* loaded from: classes12.dex */
public class d {
    private static int[] a;

    /* loaded from: classes12.dex */
    public static class a {
        public static a d = new a(1, TencentMapServiceProtocol.SERVICE_NAME_STATISTIC, 0);

        /* renamed from: e  reason: collision with root package name */
        public static a f12729e = new a(2, "quick_log", 0);

        /* renamed from: f  reason: collision with root package name */
        public static a f12730f = new a(3, "dau_log", 0);

        /* renamed from: g  reason: collision with root package name */
        public static a f12731g = new a(4, "m_log", 0);
        private int a;
        private String b;

        /* renamed from: c  reason: collision with root package name */
        private int f12732c;

        a(int i2, String str, int i3) {
            this.a = i2;
            this.b = str;
            this.f12732c = i3;
        }

        public int a() {
            return this.a;
        }

        public int b() {
            return this.f12732c;
        }

        public String c() {
            return this.b;
        }

        public String toString() {
            return "LogChannel= {channelCode=" + this.a + ",tableName=" + m.a(this.b) + ",reportLogType=" + this.f12732c + "}";
        }
    }

    static {
        int[] iArr = new int[4];
        a = iArr;
        iArr[0] = a.f12730f.a();
        a[1] = a.f12729e.a();
        a[2] = a.d.a();
        a[3] = a.f12731g.a();
    }

    public static a a(int i2) {
        if (i2 == a.d.a()) {
            return a.d;
        }
        if (i2 == a.f12729e.a()) {
            return a.f12729e;
        }
        if (i2 == a.f12731g.a()) {
            return a.f12731g;
        }
        return a.f12730f;
    }

    public static int b(int i2, String str) {
        com.jingdong.jdma.bean.e.b c2;
        if (i2 == a.f12729e.a()) {
            com.jingdong.jdma.bean.e.d f2 = com.jingdong.jdma.h.d.e().f();
            if (f2 != null) {
                return f2.b().a(str);
            }
        } else if (i2 == a.d.a()) {
            return com.jingdong.jdma.h.d.e().a(str);
        } else {
            if (i2 == a.f12730f.a()) {
                if (n.a().c()) {
                    return com.jingdong.jdma.h.d.e().a(str);
                }
                return 10;
            } else if (i2 == a.f12731g.a() && (c2 = com.jingdong.jdma.h.d.e().c()) != null) {
                return c2.a(str);
            }
        }
        return 15;
    }

    public static int c(int i2, String str) {
        return com.jingdong.jdma.h.d.e().b(str);
    }

    public static int a(int i2, String str) {
        com.jingdong.jdma.bean.e.b c2;
        if (i2 == a.f12729e.a()) {
            com.jingdong.jdma.bean.e.d f2 = com.jingdong.jdma.h.d.e().f();
            if (f2 != null) {
                return f2.b().b(str);
            }
        } else if (i2 == a.d.a()) {
            return com.jingdong.jdma.h.d.e().c(str);
        } else {
            if (i2 == a.f12730f.a()) {
                if (n.a().c()) {
                    return com.jingdong.jdma.h.d.e().c(str);
                }
                return 1;
            } else if (i2 == a.f12731g.a() && (c2 = com.jingdong.jdma.h.d.e().c()) != null) {
                return c2.b(str);
            }
        }
        return 10;
    }

    public static String b(int i2) {
        if (i2 == a.f12731g.a()) {
            return com.jingdong.jdma.h.d.e().d();
        }
        return com.jingdong.jdma.h.d.e().h();
    }

    public static int a(String str) {
        if (str.equals("st")) {
            return a.f12730f.a();
        }
        if (str.equals("h5")) {
            return a.f12731g.a();
        }
        if (com.jingdong.jdma.h.d.e().d(str)) {
            return a.f12729e.a();
        }
        return a.d.a();
    }

    public static int[] a() {
        return a;
    }

    public static void a(int i2, int i3) {
        if (i2 == a.d.a()) {
            com.jingdong.jdma.common.utils.c.f12674c = i3;
        }
        if (i2 == a.f12729e.a()) {
            com.jingdong.jdma.common.utils.c.d = i3;
        }
        if (i2 == a.f12730f.a()) {
            com.jingdong.jdma.common.utils.c.f12675e = i3;
        }
        if (i2 == a.f12731g.a()) {
            com.jingdong.jdma.common.utils.c.f12676f = i3;
        }
    }
}
