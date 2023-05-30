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
    @GwtIncompatible
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int log10(BigInteger bigInteger, RoundingMode roundingMode) {
        int i2;
        MathPreconditions.checkPositive(JshopConst.JSHOP_PROMOTIO_X, bigInteger);
        if (fitsInLong(bigInteger)) {
            return LongMath.log10(bigInteger.longValue(), roundingMode);
        }
        double log2 = log2(bigInteger, RoundingMode.FLOOR);
        double d = LN_2;
        Double.isNaN(log2);
        int i3 = (int) ((log2 * d) / LN_10);
        BigInteger pow = BigInteger.TEN.pow(i3);
        int compareTo = pow.compareTo(bigInteger);
        if (compareTo <= 0) {
            BigInteger multiply = BigInteger.TEN.multiply(pow);
            int i4 = compareTo;
            int compareTo2 = multiply.compareTo(bigInteger);
            while (compareTo2 <= 0) {
                i3++;
                BigInteger multiply2 = BigInteger.TEN.multiply(multiply);
                int compareTo3 = multiply2.compareTo(bigInteger);
                BigInteger bigInteger2 = multiply;
                multiply = multiply2;
                pow = bigInteger2;
                i4 = compareTo2;
                compareTo2 = compareTo3;
            }
            i2 = i4;
            switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
                case 1:
                    MathPreconditions.checkRoundingUnnecessary(i2 == 0);
                    break;
                case 2:
                case 3:
                    break;
                case 4:
                case 5:
                    return pow.equals(bigInteger) ? i3 : i3 + 1;
                case 6:
                case 7:
                case 8:
                    return bigInteger.pow(2).compareTo(pow.pow(2).multiply(BigInteger.TEN)) <= 0 ? i3 : i3 + 1;
                default:
                    throw new AssertionError();
            }
            return i3;
        }
        do {
            i3--;
            pow = pow.divide(BigInteger.TEN);
            i2 = pow.compareTo(bigInteger);
        } while (i2 > 0);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
        }
        return i3;
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
