package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.constant.JshopConst;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@GwtCompatible(emulated = true)
/* loaded from: classes12.dex */
public final class BigIntegerMath {
    @VisibleForTesting
    static final int SQRT2_PRECOMPUTE_THRESHOLD = 256;
    @VisibleForTesting
    static final BigInteger SQRT2_PRECOMPUTED_BITS = new BigInteger("16a09e667f3bcc908b2fb1366ea957d3e3adec17512775099da2f590b0667322a", 16);
    private static final double LN_10 = Math.log(10.0d);
    private static final double LN_2 = Math.log(2.0d);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.math.BigIntegerMath$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode;

        static {
            int[] iArr = new int[RoundingMode.values().length];
            $SwitchMap$java$math$RoundingMode = iArr;
            try {
                iArr[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private BigIntegerMath() {
    }

    public static BigInteger binomial(int i2, int i3) {
        int i4;
        MathPreconditions.checkNonNegative(PersonalConstants.ICON_STYLE_N, i2);
        MathPreconditions.checkNonNegative("k", i3);
        int i5 = 1;
        Preconditions.checkArgument(i3 <= i2, "k (%s) > n (%s)", i3, i2);
        if (i3 > (i2 >> 1)) {
            i3 = i2 - i3;
        }
        int[] iArr = LongMath.biggestBinomials;
        if (i3 < iArr.length && i2 <= iArr[i3]) {
            return BigInteger.valueOf(LongMath.binomial(i2, i3));
        }
        BigInteger bigInteger = BigInteger.ONE;
        long j2 = i2;
        long j3 = 1;
        int log2 = LongMath.log2(j2, RoundingMode.CEILING);
        while (true) {
            int i6 = log2;
            while (i5 < i3) {
                i4 = i2 - i5;
                i5++;
                i6 += log2;
                if (i6 >= 63) {
                    break;
                }
                j2 *= i4;
                j3 *= i5;
            }
            return bigInteger.multiply(BigInteger.valueOf(j2)).divide(BigInteger.valueOf(j3));
            bigInteger = bigInteger.multiply(BigInteger.valueOf(j2)).divide(BigInteger.valueOf(j3));
            j2 = i4;
            j3 = i5;
        }
    }

    @Beta
    public static BigInteger ceilingPowerOfTwo(BigInteger bigInteger) {
        return BigInteger.ZERO.setBit(log2(bigInteger, RoundingMode.CEILING));
    }

    @GwtIncompatible
    public static BigInteger divide(BigInteger bigInteger, BigInteger bigInteger2, RoundingMode roundingMode) {
        return new BigDecimal(bigInteger).divide(new BigDecimal(bigInteger2), 0, roundingMode).toBigIntegerExact();
    }

    public static BigInteger factorial(int i2) {
        MathPreconditions.checkNonNegative(PersonalConstants.ICON_STYLE_N, i2);
        long[] jArr = LongMath.factorials;
        if (i2 < jArr.length) {
            return BigInteger.valueOf(jArr[i2]);
        }
        ArrayList arrayList = new ArrayList(IntMath.divide(IntMath.log2(i2, RoundingMode.CEILING) * i2, 64, RoundingMode.CEILING));
        int length = jArr.length;
        long j2 = jArr[length - 1];
        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j2);
        long j3 = j2 >> numberOfTrailingZeros;
        int log2 = LongMath.log2(j3, RoundingMode.FLOOR) + 1;
        long j4 = length;
        int log22 = LongMath.log2(j4, RoundingMode.FLOOR) + 1;
        int i3 = 1 << (log22 - 1);
        while (j4 <= i2) {
            if ((i3 & j4) != 0) {
                i3 <<= 1;
                log22++;
            }
            int numberOfTrailingZeros2 = Long.numberOfTrailingZeros(j4);
            long j5 = j4 >> numberOfTrailingZeros2;
            numberOfTrailingZeros += numberOfTrailingZeros2;
            if ((log22 - numberOfTrailingZeros2) + log2 >= 64) {
                arrayList.add(BigInteger.valueOf(j3));
                j3 = 1;
            }
            j3 *= j5;
            log2 = LongMath.log2(j3, RoundingMode.FLOOR) + 1;
            j4++;
        }
        if (j3 > 1) {
            arrayList.add(BigInteger.valueOf(j3));
        }
        return listProduct(arrayList).shiftLeft(numberOfTrailingZeros);
    }

    @GwtIncompatible
    static boolean fitsInLong(BigInteger bigInteger) {
        return bigInteger.bitLength() <= 63;
    }

    @Beta
    public static BigInteger floorPowerOfTwo(BigInteger bigInteger) {
        return BigInteger.ZERO.setBit(log2(bigInteger, RoundingMode.FLOOR));
    }

    public static boolean isPowerOfTwo(BigInteger bigInteger) {
        Preconditions.checkNotNull(bigInteger);
        return bigInteger.signum() > 0 && bigInteger.getLowestSetBit() == bigInteger.bitLength() - 1;
    }

    static BigInteger listProduct(List<BigInteger> list) {
        return listProduct(list, 0, list.size());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0099  */
    @com.google.common.annotations.GwtIncompatible
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int log10(java.math.BigInteger r7, java.math.RoundingMode r8) {
        /*
            java.lang.String r0 = "x"
            com.google.common.math.MathPreconditions.checkPositive(r0, r7)
            boolean r0 = fitsInLong(r7)
            if (r0 == 0) goto L15
            long r0 = r7.longValue()
            int r7 = com.google.common.math.LongMath.log10(r0, r8)
            return r7
        L15:
            java.math.RoundingMode r0 = java.math.RoundingMode.FLOOR
            int r0 = log2(r7, r0)
            double r0 = (double) r0
            double r2 = com.google.common.math.BigIntegerMath.LN_2
            java.lang.Double.isNaN(r0)
            double r0 = r0 * r2
            double r2 = com.google.common.math.BigIntegerMath.LN_10
            double r0 = r0 / r2
            int r0 = (int) r0
            java.math.BigInteger r1 = java.math.BigInteger.TEN
            java.math.BigInteger r1 = r1.pow(r0)
            int r2 = r1.compareTo(r7)
            if (r2 <= 0) goto L42
        L33:
            int r0 = r0 + (-1)
            java.math.BigInteger r2 = java.math.BigInteger.TEN
            java.math.BigInteger r1 = r1.divide(r2)
            int r2 = r1.compareTo(r7)
            if (r2 > 0) goto L33
            goto L65
        L42:
            java.math.BigInteger r3 = java.math.BigInteger.TEN
            java.math.BigInteger r3 = r3.multiply(r1)
            int r4 = r3.compareTo(r7)
            r5 = r4
            r4 = r2
            r2 = r5
        L4f:
            if (r2 > 0) goto L64
            int r0 = r0 + 1
            java.math.BigInteger r1 = java.math.BigInteger.TEN
            java.math.BigInteger r1 = r1.multiply(r3)
            int r4 = r1.compareTo(r7)
            r5 = r3
            r3 = r1
            r1 = r5
            r6 = r4
            r4 = r2
            r2 = r6
            goto L4f
        L64:
            r2 = r4
        L65:
            int[] r3 = com.google.common.math.BigIntegerMath.AnonymousClass1.$SwitchMap$java$math$RoundingMode
            int r8 = r8.ordinal()
            r8 = r3[r8]
            switch(r8) {
                case 1: goto L99;
                case 2: goto La1;
                case 3: goto La1;
                case 4: goto L8f;
                case 5: goto L8f;
                case 6: goto L76;
                case 7: goto L76;
                case 8: goto L76;
                default: goto L70;
            }
        L70:
            java.lang.AssertionError r7 = new java.lang.AssertionError
            r7.<init>()
            throw r7
        L76:
            r8 = 2
            java.math.BigInteger r7 = r7.pow(r8)
            java.math.BigInteger r8 = r1.pow(r8)
            java.math.BigInteger r1 = java.math.BigInteger.TEN
            java.math.BigInteger r8 = r8.multiply(r1)
            int r7 = r7.compareTo(r8)
            if (r7 > 0) goto L8c
            goto L8e
        L8c:
            int r0 = r0 + 1
        L8e:
            return r0
        L8f:
            boolean r7 = r1.equals(r7)
            if (r7 == 0) goto L96
            goto L98
        L96:
            int r0 = r0 + 1
        L98:
            return r0
        L99:
            if (r2 != 0) goto L9d
            r7 = 1
            goto L9e
        L9d:
            r7 = 0
        L9e:
            com.google.common.math.MathPreconditions.checkRoundingUnnecessary(r7)
        La1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.BigIntegerMath.log10(java.math.BigInteger, java.math.RoundingMode):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int log2(BigInteger bigInteger, RoundingMode roundingMode) {
        MathPreconditions.checkPositive(JshopConst.JSHOP_PROMOTIO_X, (BigInteger) Preconditions.checkNotNull(bigInteger));
        int bitLength = bigInteger.bitLength() - 1;
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(bigInteger));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return isPowerOfTwo(bigInteger) ? bitLength : bitLength + 1;
            case 6:
            case 7:
            case 8:
                return bitLength < 256 ? bigInteger.compareTo(SQRT2_PRECOMPUTED_BITS.shiftRight(256 - bitLength)) <= 0 ? bitLength : bitLength + 1 : bigInteger.pow(2).bitLength() + (-1) < (bitLength * 2) + 1 ? bitLength : bitLength + 1;
            default:
                throw new AssertionError();
        }
        return bitLength;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @GwtIncompatible
    public static BigInteger sqrt(BigInteger bigInteger, RoundingMode roundingMode) {
        MathPreconditions.checkNonNegative(JshopConst.JSHOP_PROMOTIO_X, bigInteger);
        if (fitsInLong(bigInteger)) {
            return BigInteger.valueOf(LongMath.sqrt(bigInteger.longValue(), roundingMode));
        }
        BigInteger sqrtFloor = sqrtFloor(bigInteger);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(sqrtFloor.pow(2).equals(bigInteger));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                int intValue = sqrtFloor.intValue();
                return intValue * intValue == bigInteger.intValue() && sqrtFloor.pow(2).equals(bigInteger) ? sqrtFloor : sqrtFloor.add(BigInteger.ONE);
            case 6:
            case 7:
            case 8:
                return sqrtFloor.pow(2).add(sqrtFloor).compareTo(bigInteger) >= 0 ? sqrtFloor : sqrtFloor.add(BigInteger.ONE);
            default:
                throw new AssertionError();
        }
        return sqrtFloor;
    }

    @GwtIncompatible
    private static BigInteger sqrtApproxWithDoubles(BigInteger bigInteger) {
        return DoubleMath.roundToBigInteger(Math.sqrt(DoubleUtils.bigToDouble(bigInteger)), RoundingMode.HALF_EVEN);
    }

    @GwtIncompatible
    private static BigInteger sqrtFloor(BigInteger bigInteger) {
        BigInteger shiftLeft;
        int log2 = log2(bigInteger, RoundingMode.FLOOR);
        if (log2 < 1023) {
            shiftLeft = sqrtApproxWithDoubles(bigInteger);
        } else {
            int i2 = (log2 - 52) & (-2);
            shiftLeft = sqrtApproxWithDoubles(bigInteger.shiftRight(i2)).shiftLeft(i2 >> 1);
        }
        BigInteger shiftRight = shiftLeft.add(bigInteger.divide(shiftLeft)).shiftRight(1);
        if (shiftLeft.equals(shiftRight)) {
            return shiftLeft;
        }
        while (true) {
            BigInteger shiftRight2 = shiftRight.add(bigInteger.divide(shiftRight)).shiftRight(1);
            if (shiftRight2.compareTo(shiftRight) >= 0) {
                return shiftRight;
            }
            shiftRight = shiftRight2;
        }
    }

    static BigInteger listProduct(List<BigInteger> list, int i2, int i3) {
        int i4 = i3 - i2;
        if (i4 != 0) {
            if (i4 != 1) {
                if (i4 != 2) {
                    if (i4 != 3) {
                        int i5 = (i3 + i2) >>> 1;
                        return listProduct(list, i2, i5).multiply(listProduct(list, i5, i3));
                    }
                    return list.get(i2).multiply(list.get(i2 + 1)).multiply(list.get(i2 + 2));
                }
                return list.get(i2).multiply(list.get(i2 + 1));
            }
            return list.get(i2);
        }
        return BigInteger.ONE;
    }
}
