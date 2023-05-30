package com.xiaomi.push;

/* loaded from: classes11.dex */
public final class z3 {
    static {
        b(1, 3);
        b(1, 4);
        b(2, 0);
        b(3, 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i2) {
        return i2 & 7;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(int i2, int i3) {
        return (i2 << 3) | i3;
    }

    public static int c(int i2) {
        return i2 >>> 3;
    }
}
