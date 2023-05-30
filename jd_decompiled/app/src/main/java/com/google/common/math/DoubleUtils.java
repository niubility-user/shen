package com.google.common.math;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.math.BigInteger;

@GwtIncompatible
/* loaded from: classes12.dex */
final class DoubleUtils {
    static final int EXPONENT_BIAS = 1023;
    static final long EXPONENT_MASK = 9218868437227405312L;
    static final long IMPLICIT_BIT = 4503599627370496L;
    @VisibleForTesting
    static final long ONE_BITS = 4607182418800017408L;
    static final int SIGNIFICAND_BITS = 52;
    static final long SIGNIFICAND_MASK = 4503599627370495L;
    static final long SIGN_MASK = Long.MIN_VALUE;

    private DoubleUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double bigToDouble(BigInteger bigInteger) {
        BigInteger abs = bigInteger.abs();
        boolean z = true;
        int bitLength = abs.bitLength() - 1;
        if (bitLength < 63) {
            return bigInteger.longValue();
        }
        if (bitLength > 1023) {
            double signum = bigInteger.signum();
            Double.isNaN(signum);
            return signum * Double.POSITIVE_INFINITY;
        }
        int i2 = (bitLength - 52) - 1;
        long longValue = abs.shiftRight(i2).longValue();
        long j2 = (longValue >> 1) & SIGNIFICAND_MASK;
        if ((longValue & 1) == 0 || ((j2 & 1) == 0 && abs.getLowestSetBit() >= i2)) {
            z = false;
        }
        if (z) {
            j2++;
        }
        return Double.longBitsToDouble((((bitLength + 1023) << 52) + j2) | (bigInteger.signum() & Long.MIN_VALUE));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double ensureNonNegative(double d) {
        Preconditions.checkArgument(!Double.isNaN(d));
        if (d > 0.0d) {
            return d;
        }
        return 0.0d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getSignificand(double d) {
        Preconditions.checkArgument(isFinite(d), "not a normal value");
        int exponent = Math.getExponent(d);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d) & SIGNIFICAND_MASK;
        return exponent == -1023 ? doubleToRawLongBits << 1 : doubleToRawLongBits | IMPLICIT_BIT;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isFinite(double d) {
        return Math.getExponent(d) <= 1023;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isNormal(double d) {
        return Math.getExponent(d) >= -1022;
    }

    static double nextDown(double d) {
        return -Math.nextUp(-d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double scaleNormalize(double d) {
        return Double.longBitsToDouble((Double.doubleToRawLongBits(d) & SIGNIFICAND_MASK) | ONE_BITS);
    }
}
