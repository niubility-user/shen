package com.jingdong.sdk.dialingtest.e.b;

/* loaded from: classes7.dex */
public class b {
    private static volatile a a;

    public static a a() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new a();
                }
            }
        }
        return a;
    }

    public static void b(a aVar) {
        a = aVar;
    }
}
