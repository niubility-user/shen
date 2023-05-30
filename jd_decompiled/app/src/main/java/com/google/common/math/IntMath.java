package com.google.common.math;

import com.airbnb.lottie.utils.Utils;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.huawei.hms.framework.common.ExceptionCode;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.jdsdk.a.a;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.sdk.platform.business.personal.R2;
import java.math.RoundingMode;

@GwtCompatible(emulated = true)
/* loaded from: classes12.dex */
public final class IntMath {
    @VisibleForTesting
    static final int FLOOR_SQRT_MAX_INT = 46340;
    @VisibleForTesting
    static final int MAX_POWER_OF_SQRT2_UNSIGNED = -1257966797;
    @VisibleForTesting
    static final int MAX_SIGNED_POWER_OF_TWO = 1073741824;
    @VisibleForTesting
    static final byte[] maxLog10ForLeadingZeros = {9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0};
    @VisibleForTesting
    static final int[] powersOf10 = {1, 10, 100, 1000, 10000, 100000, 1000000, ExceptionCode.CRASH_EXCEPTION, 100000000, Utils.SECOND_IN_NANOS};
    @VisibleForTesting
    static final int[] halfPowersOf10 = {3, 31, 316, R2.color.button_f_01_stroke, 31622, 316227, 3162277, 31622776, 316227766, Integer.MAX_VALUE};
    private static final int[] factorials = {1, 1, 2, 6, 24, 120, R2.attr.counterOverflowTextColor, 5040, 40320, 362880, 3628800, 39916800, 479001600};
    @VisibleForTesting
    static int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, 65536, R2.attr.yg_borderEnd, R2.attr.bottomNavigationStyle, R2.anim.slide_out_from_left, 110, 75, 58, 49, 43, 39, 37, 35, 34, 34, 33};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.math.IntMath$1  reason: invalid class name */
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

    private IntMath() {
    }

    public static int binomial(int i2, int i3) {
        MathPreconditions.checkNonNegative(PersonalConstants.ICON_STYLE_N, i2);
        MathPreconditions.checkNonNegative("k", i3);
        int i4 = 0;
        Preconditions.checkArgument(i3 <= i2, "k (%s) > n (%s)", i3, i2);
        if (i3 > (i2 >> 1)) {
            i3 = i2 - i3;
        }
        int[] iArr = biggestBinomials;
        if (i3 >= iArr.length || i2 > iArr[i3]) {
            return Integer.MAX_VALUE;
        }
        if (i3 != 0) {
            if (i3 != 1) {
                long j2 = 1;
                while (i4 < i3) {
                    i4++;
                    j2 = (j2 * (i2 - i4)) / i4;
                }
                return (int) j2;
            }
            return i2;
        }
        return 1;
    }

    @Beta
    public static int ceilingPowerOfTwo(int i2) {
        MathPreconditions.checkPositive(JshopConst.JSHOP_PROMOTIO_X, i2);
        if (i2 <= 1073741824) {
            return 1 << (-Integer.numberOfLeadingZeros(i2 - 1));
        }
        throw new ArithmeticException("ceilingPowerOfTwo(" + i2 + ") not representable as an int");
    }

    public static int checkedAdd(int i2, int i3) {
        long j2 = i2 + i3;
        int i4 = (int) j2;
        MathPreconditions.checkNoOverflow(j2 == ((long) i4));
        return i4;
    }

    public static int checkedMultiply(int i2, int i3) {
        long j2 = i2 * i3;
        int i4 = (int) j2;
        MathPreconditions.checkNoOverflow(j2 == ((long) i4));
        return i4;
    }

    public static int checkedPow(int i2, int i3) {
        MathPreconditions.checkNonNegative("exponent", i3);
        if (i2 == -2) {
            MathPreconditions.checkNoOverflow(i3 < 32);
            return (i3 & 1) == 0 ? 1 << i3 : (-1) << i3;
        } else if (i2 == -1) {
            return (i3 & 1) == 0 ? 1 : -1;
        } else if (i2 == 0) {
            return i3 == 0 ? 1 : 0;
        } else if (i2 != 1) {
            if (i2 == 2) {
                MathPreconditions.checkNoOverflow(i3 < 31);
                return 1 << i3;
            }
            int i4 = 1;
            while (i3 != 0) {
                if (i3 == 1) {
                    return checkedMultiply(i4, i2);
                }
                if ((i3 & 1) != 0) {
                    i4 = checkedMultiply(i4, i2);
                }
                i3 >>= 1;
                if (i3 > 0) {
                    MathPreconditions.checkNoOverflow((-46340 <= i2) & (i2 <= FLOOR_SQRT_MAX_INT));
                    i2 *= i2;
                }
            }
            return i4;
        } else {
            return 1;
        }
    }

    public static int checkedSubtract(int i2, int i3) {
        long j2 = i2 - i3;
        int i4 = (int) j2;
        MathPreconditions.checkNoOverflow(j2 == ((long) i4));
        return i4;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0044, code lost:
        if (((r7 == java.math.RoundingMode.HALF_EVEN) & ((r0 & 1) != 0)) != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0047, code lost:
        if (r1 > 0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004a, code lost:
        if (r5 > 0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004d, code lost:
        if (r5 < 0) goto L37;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int divide(int r5, int r6, java.math.RoundingMode r7) {
        /*
            com.google.common.base.Preconditions.checkNotNull(r7)
            if (r6 == 0) goto L5c
            int r0 = r5 / r6
            int r1 = r6 * r0
            int r1 = r5 - r1
            if (r1 != 0) goto Le
            return r0
        Le:
            r5 = r5 ^ r6
            int r5 = r5 >> 31
            r2 = 1
            r5 = r5 | r2
            int[] r3 = com.google.common.math.IntMath.AnonymousClass1.$SwitchMap$java$math$RoundingMode
            int r4 = r7.ordinal()
            r3 = r3[r4]
            r4 = 0
            switch(r3) {
                case 1: goto L50;
                case 2: goto L57;
                case 3: goto L4d;
                case 4: goto L58;
                case 5: goto L4a;
                case 6: goto L25;
                case 7: goto L25;
                case 8: goto L25;
                default: goto L1f;
            }
        L1f:
            java.lang.AssertionError r5 = new java.lang.AssertionError
            r5.<init>()
            throw r5
        L25:
            int r1 = java.lang.Math.abs(r1)
            int r6 = java.lang.Math.abs(r6)
            int r6 = r6 - r1
            int r1 = r1 - r6
            if (r1 != 0) goto L47
            java.math.RoundingMode r6 = java.math.RoundingMode.HALF_UP
            if (r7 == r6) goto L58
            java.math.RoundingMode r6 = java.math.RoundingMode.HALF_EVEN
            if (r7 != r6) goto L3b
            r6 = 1
            goto L3c
        L3b:
            r6 = 0
        L3c:
            r7 = r0 & 1
            if (r7 == 0) goto L42
            r7 = 1
            goto L43
        L42:
            r7 = 0
        L43:
            r6 = r6 & r7
            if (r6 == 0) goto L57
            goto L58
        L47:
            if (r1 <= 0) goto L57
            goto L58
        L4a:
            if (r5 <= 0) goto L57
            goto L58
        L4d:
            if (r5 >= 0) goto L57
            goto L58
        L50:
            if (r1 != 0) goto L53
            goto L54
        L53:
            r2 = 0
        L54:
            com.google.common.math.MathPreconditions.checkRoundingUnnecessary(r2)
        L57:
            r2 = 0
        L58:
            if (r2 == 0) goto L5b
            int r0 = r0 + r5
        L5b:
            return r0
        L5c:
            java.lang.ArithmeticException r5 = new java.lang.ArithmeticException
            java.lang.String r6 = "/ by zero"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.IntMath.divide(int, int, java.math.RoundingMode):int");
    }

    public static int factorial(int i2) {
        MathPreconditions.checkNonNegative(PersonalConstants.ICON_STYLE_N, i2);
        int[] iArr = factorials;
        if (i2 < iArr.length) {
            return iArr[i2];
        }
        return Integer.MAX_VALUE;
    }

    @Beta
    public static int floorPowerOfTwo(int i2) {
        MathPreconditions.checkPositive(JshopConst.JSHOP_PROMOTIO_X, i2);
        return Integer.highestOneBit(i2);
    }

    public static int gcd(int i2, int i3) {
        MathPreconditions.checkNonNegative(a.a, i2);
        MathPreconditions.checkNonNegative("b", i3);
        if (i2 == 0) {
            return i3;
        }
        if (i3 == 0) {
            return i2;
        }
        int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i2);
        int i4 = i2 >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Integer.numberOfTrailingZeros(i3);
        int i5 = i3 >> numberOfTrailingZeros2;
        while (i4 != i5) {
            int i6 = i4 - i5;
            int i7 = (i6 >> 31) & i6;
            int i8 = (i6 - i7) - i7;
            i5 += i7;
            i4 = i8 >> Integer.numberOfTrailingZeros(i8);
        }
        return i4 << Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
    }

    public static boolean isPowerOfTwo(int i2) {
        return (i2 > 0) & ((i2 & (i2 + (-1))) == 0);
    }

    @Beta
    @GwtIncompatible
    public static boolean isPrime(int i2) {
        return LongMath.isPrime(i2);
    }

    @VisibleForTesting
    static int lessThanBranchFree(int i2, int i3) {
        return (((i2 - i3) ^ (-1)) ^ (-1)) >>> 31;
    }

    @GwtIncompatible
    public static int log10(int i2, RoundingMode roundingMode) {
        int lessThanBranchFree;
        MathPreconditions.checkPositive(JshopConst.JSHOP_PROMOTIO_X, i2);
        int log10Floor = log10Floor(i2);
        int i3 = powersOf10[log10Floor];
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(i2 == i3);
                return log10Floor;
            case 2:
            case 3:
                return log10Floor;
            case 4:
            case 5:
                lessThanBranchFree = lessThanBranchFree(i3, i2);
                return log10Floor + lessThanBranchFree;
            case 6:
            case 7:
            case 8:
                lessThanBranchFree = lessThanBranchFree(halfPowersOf10[log10Floor], i2);
                return log10Floor + lessThanBranchFree;
            default:
                throw new AssertionError();
        }
    }

    private static int log10Floor(int i2) {
        byte b = maxLog10ForLeadingZeros[Integer.numberOfLeadingZeros(i2)];
        return b - lessThanBranchFree(i2, powersOf10[b]);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int log2(int i2, RoundingMode roundingMode) {
        MathPreconditions.checkPositive(JshopConst.JSHOP_PROMOTIO_X, i2);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(i2));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 32 - Integer.numberOfLeadingZeros(i2 - 1);
            case 6:
            case 7:
            case 8:
                int numberOfLeadingZeros = Integer.numberOfLeadingZeros(i2);
                return (31 - numberOfLeadingZeros) + lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> numberOfLeadingZeros, i2);
            default:
                throw new AssertionError();
        }
        return 31 - Integer.numberOfLeadingZeros(i2);
    }

    public static int mean(int i2, int i3) {
        return (i2 & i3) + ((i2 ^ i3) >> 1);
    }

    public static int mod(int i2, int i3) {
        if (i3 > 0) {
            int i4 = i2 % i3;
            return i4 >= 0 ? i4 : i4 + i3;
        }
        throw new ArithmeticException("Modulus " + i3 + " must be > 0");
    }

    @GwtIncompatible
    public static int pow(int i2, int i3) {
        MathPreconditions.checkNonNegative("exponent", i3);
        if (i2 == -2) {
            if (i3 < 32) {
                return (i3 & 1) == 0 ? 1 << i3 : -(1 << i3);
            }
            return 0;
        } else if (i2 == -1) {
            return (i3 & 1) == 0 ? 1 : -1;
        } else if (i2 == 0) {
            return i3 == 0 ? 1 : 0;
        } else if (i2 != 1) {
            if (i2 == 2) {
                if (i3 < 32) {
                    return 1 << i3;
                }
                return 0;
            }
            int i4 = 1;
            while (i3 != 0) {
                if (i3 == 1) {
                    return i2 * i4;
                }
                i4 *= (i3 & 1) == 0 ? 1 : i2;
                i2 *= i2;
                i3 >>= 1;
            }
            return i4;
        } else {
            return 1;
        }
    }

    @Beta
    public static int saturatedAdd(int i2, int i3) {
        return Ints.saturatedCast(i2 + i3);
    }

    @Beta
    public static int saturatedMultiply(int i2, int i3) {
        return Ints.saturatedCast(i2 * i3);
    }

    @Beta
    public static int saturatedPow(int i2, int i3) {
        MathPreconditions.checkNonNegative("exponent", i3);
        if (i2 == -2) {
            return i3 >= 32 ? (i3 & 1) + Integer.MAX_VALUE : (i3 & 1) == 0 ? 1 << i3 : (-1) << i3;
        } else if (i2 == -1) {
            return (i3 & 1) == 0 ? 1 : -1;
        } else if (i2 == 0) {
            return i3 == 0 ? 1 : 0;
        } else if (i2 != 1) {
            if (i2 == 2) {
                if (i3 >= 31) {
                    return Integer.MAX_VALUE;
                }
                return 1 << i3;
            }
            int i4 = ((i2 >>> 31) & i3 & 1) + Integer.MAX_VALUE;
            int i5 = 1;
            while (i3 != 0) {
                if (i3 == 1) {
                    return saturatedMultiply(i5, i2);
                }
                if ((i3 & 1) != 0) {
                    i5 = saturatedMultiply(i5, i2);
                }
                i3 >>= 1;
                if (i3 > 0) {
                    if ((-46340 > i2) || (i2 > FLOOR_SQRT_MAX_INT)) {
                        return i4;
                    }
                    i2 *= i2;
                }
            }
            return i5;
        } else {
            return 1;
        }
    }

    @Beta
    public static int saturatedSubtract(int i2, int i3) {
        return Ints.saturatedCast(i2 - i3);
    }

    @GwtIncompatible
    public static int sqrt(int i2, RoundingMode roundingMode) {
        int lessThanBranchFree;
        MathPreconditions.checkNonNegative(JshopConst.JSHOP_PROMOTIO_X, i2);
        int sqrtFloor = sqrtFloor(i2);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(sqrtFloor * sqrtFloor == i2);
                return sqrtFloor;
            case 2:
            case 3:
                return sqrtFloor;
            case 4:
            case 5:
                lessThanBranchFree = lessThanBranchFree(sqrtFloor * sqrtFloor, i2);
                return sqrtFloor + lessThanBranchFree;
            case 6:
            case 7:
            case 8:
                lessThanBranchFree = lessThanBranchFree((sqrtFloor * sqrtFloor) + sqrtFloor, i2);
                return sqrtFloor + lessThanBranchFree;
            default:
                throw new AssertionError();
        }
    }

    private static int sqrtFloor(int i2) {
        return (int) Math.sqrt(i2);
    }
}
