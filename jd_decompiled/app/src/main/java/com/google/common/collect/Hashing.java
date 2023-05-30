package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* loaded from: classes12.dex */
final class Hashing {
    private static final long C1 = -862048943;
    private static final long C2 = 461845907;
    private static final int MAX_TABLE_SIZE = 1073741824;

    private Hashing() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int closedTableSize(int i2, double d) {
        int max = Math.max(i2, 2);
        int highestOneBit = Integer.highestOneBit(max);
        double d2 = highestOneBit;
        Double.isNaN(d2);
        if (max > ((int) (d * d2))) {
            int i3 = highestOneBit << 1;
            if (i3 > 0) {
                return i3;
            }
            return 1073741824;
        }
        return highestOneBit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean needsResizing(int i2, int i3, double d) {
        double d2 = i3;
        Double.isNaN(d2);
        return ((double) i2) > d * d2 && i3 < 1073741824;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int smear(int i2) {
        return (int) (Integer.rotateLeft((int) (i2 * C1), 15) * C2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int smearedHash(@NullableDecl Object obj) {
        return smear(obj == null ? 0 : obj.hashCode());
    }
}
