package com.xiaomi.push;

/* loaded from: classes11.dex */
public class r2 {
    private static volatile r2 b;
    private q2 a;

    public static r2 b() {
        if (b == null) {
            synchronized (r2.class) {
                if (b == null) {
                    b = new r2();
                }
            }
        }
        return b;
    }

    public q2 a() {
        return this.a;
    }

    public void c(q2 q2Var) {
        this.a = q2Var;
    }
}
