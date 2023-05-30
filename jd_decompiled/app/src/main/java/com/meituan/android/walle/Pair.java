package com.meituan.android.walle;

/* loaded from: classes.dex */
final class Pair<A, B> {
    private final A mFirst;
    private final B mSecond;

    private Pair(A a, B b) {
        this.mFirst = a;
        this.mSecond = b;
    }

    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && Pair.class == obj.getClass()) {
            Pair pair = (Pair) obj;
            A a = this.mFirst;
            if (a == null) {
                if (pair.mFirst != null) {
                    return false;
                }
            } else if (!a.equals(pair.mFirst)) {
                return false;
            }
            B b = this.mSecond;
            if (b == null) {
                if (pair.mSecond != null) {
                    return false;
                }
            } else if (!b.equals(pair.mSecond)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public A getFirst() {
        return this.mFirst;
    }

    public B getSecond() {
        return this.mSecond;
    }

    public int hashCode() {
        A a = this.mFirst;
        int hashCode = ((a == null ? 0 : a.hashCode()) + 31) * 31;
        B b = this.mSecond;
        return hashCode + (b != null ? b.hashCode() : 0);
    }
}
