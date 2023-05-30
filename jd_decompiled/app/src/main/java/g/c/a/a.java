package g.c.a;

import java.util.BitSet;

/* loaded from: classes12.dex */
class a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static BitSet a(long j2, long j3) {
        BitSet bitSet = new BitSet();
        b(j2, 0, bitSet);
        b(j3, 64, bitSet);
        return bitSet;
    }

    static void b(long j2, int i2, BitSet bitSet) {
        int i3 = 0;
        while (j2 != 0) {
            if (j2 % 2 != 0) {
                bitSet.set(i2 + i3);
            }
            i3++;
            j2 >>>= 1;
        }
    }
}
