package com.xiaomi.push;

/* loaded from: classes11.dex */
public class b {
    public static final String a;
    public static boolean b;

    /* renamed from: c  reason: collision with root package name */
    private static int f18461c;

    static {
        int i2;
        String str = e.a ? "ONEBOX" : "@SHIP.TO.2A2FE0D7@";
        a = str;
        b = false;
        f18461c = 1;
        if (str.equalsIgnoreCase("SANDBOX")) {
            i2 = 2;
        } else if (!str.equalsIgnoreCase("ONEBOX")) {
            f18461c = 1;
            return;
        } else {
            i2 = 3;
        }
        f18461c = i2;
    }

    public static int a() {
        return f18461c;
    }

    public static void b(int i2) {
        f18461c = i2;
    }

    public static boolean c() {
        return f18461c == 2;
    }

    public static boolean d() {
        return f18461c == 3;
    }
}
