package g.c.a;

import java.io.Serializable;
import java.util.BitSet;

/* loaded from: classes12.dex */
public final class g implements Serializable {
    private final int prefixLength;

    public g(int i2) {
        if (i2 >= 0 && i2 <= 128) {
            this.prefixLength = i2;
            return;
        }
        throw new IllegalArgumentException("prefix length should be in interval [0, 128]");
    }

    private static void a(b bVar) {
        BitSet a = a.a(bVar.getLowBits(), bVar.getHighBits());
        boolean z = false;
        for (int i2 = 127; i2 >= 0 && !z; i2--) {
            if (!a.get(i2)) {
                for (int i3 = i2 - 1; i3 >= 0; i3--) {
                    if (a.get(i3)) {
                        throw new IllegalArgumentException(bVar + " is not a valid network mask");
                    }
                }
                z = true;
            }
        }
    }

    public static g fromAddress(b bVar) {
        a(bVar);
        return new g(bVar.numberOfLeadingOnes());
    }

    public static g fromPrefixLength(int i2) {
        return new g(i2);
    }

    public b asAddress() {
        int i2 = this.prefixLength;
        if (i2 == 128) {
            return new b(-1L, -1L);
        }
        if (i2 == 64) {
            return new b(-1L, 0L);
        }
        if (i2 > 64) {
            return new b(-1L, (-1) << (64 - (i2 - 64)));
        }
        if (i2 == 0) {
            return new b(0L, 0L);
        }
        return new b((-1) << (64 - this.prefixLength), 0L);
    }

    public int asPrefixLength() {
        return this.prefixLength;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && g.class == obj.getClass() && this.prefixLength == ((g) obj).prefixLength;
    }

    public int hashCode() {
        return this.prefixLength;
    }

    public String toString() {
        return "" + this.prefixLength;
    }
}
