package com.xiaomi.push;

import java.net.InetSocketAddress;

/* loaded from: classes11.dex */
public final class d1 {
    private String a;
    private int b;

    public d1(String str, int i2) {
        this.a = str;
        this.b = i2;
    }

    public static d1 b(String str, int i2) {
        int lastIndexOf = str.lastIndexOf(":");
        if (lastIndexOf != -1) {
            String substring = str.substring(0, lastIndexOf);
            try {
                int parseInt = Integer.parseInt(str.substring(lastIndexOf + 1));
                if (parseInt > 0) {
                    i2 = parseInt;
                }
            } catch (NumberFormatException unused) {
            }
            str = substring;
        }
        return new d1(str, i2);
    }

    public static InetSocketAddress d(String str, int i2) {
        d1 b = b(str, i2);
        return new InetSocketAddress(b.c(), b.a());
    }

    public int a() {
        return this.b;
    }

    public String c() {
        return this.a;
    }

    public String toString() {
        if (this.b > 0) {
            return this.a + ":" + this.b;
        }
        return this.a;
    }
}
