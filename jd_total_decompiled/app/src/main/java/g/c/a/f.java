package g.c.a;

import java.util.BitSet;

/* loaded from: classes12.dex */
public class f {
    private static int a(BitSet bitSet, BitSet bitSet2) {
        int i2 = 0;
        for (int i3 = 127; i3 >= 0 && bitSet.get(i3) == bitSet2.get(i3); i3--) {
            i2++;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(b bVar, b bVar2) {
        return a(a.a(bVar.getLowBits(), bVar.getHighBits()), a.a(bVar2.getLowBits(), bVar2.getHighBits()));
    }
}
