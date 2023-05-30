package com.jingdong.sdk.jdupgrade.a.j.m.a;

/* loaded from: classes7.dex */
public final class b<A, B> {
    private final A a;
    private final B b;

    private b(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public static <A, B> b<A, B> a(A a, B b) {
        return new b<>(a, b);
    }

    public A a() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && b.class == obj.getClass()) {
            b bVar = (b) obj;
            A a = this.a;
            if (a == null) {
                if (bVar.a != null) {
                    return false;
                }
            } else if (!a.equals(bVar.a)) {
                return false;
            }
            B b = this.b;
            B b2 = bVar.b;
            if (b == null) {
                if (b2 != null) {
                    return false;
                }
            } else if (!b.equals(b2)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        A a = this.a;
        int hashCode = ((a == null ? 0 : a.hashCode()) + 31) * 31;
        B b = this.b;
        return hashCode + (b != null ? b.hashCode() : 0);
    }
}
