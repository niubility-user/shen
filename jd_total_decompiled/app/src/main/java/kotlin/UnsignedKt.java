package kotlin;

import com.jingdong.common.unification.uniconfig.UnIconConfigController;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt__CharJVMKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u001f\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0001\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a\u001f\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0005H\u0001\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a\"\u0010\n\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\bH\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\u0004\u001a\"\u0010\f\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\bH\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\u0004\u001a\"\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0001\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\rH\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a\"\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0001\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\rH\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u000f\u001a\u001a\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0013H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001a\u001a\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0013H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a\u0017\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0000H\u0001\u00a2\u0006\u0004\b\u0019\u0010\u001a\u001a\u0017\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0001\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a\u0017\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u0005H\u0000\u00a2\u0006\u0004\b\u001e\u0010\u001f\u001a\u001f\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0000H\u0000\u00a2\u0006\u0004\b\u001e\u0010!\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"", "v1", "v2", "uintCompare", "(II)I", "", "ulongCompare", "(JJ)I", "Lkotlin/UInt;", "uintDivide-J1ME1BU", "uintDivide", "uintRemainder-J1ME1BU", "uintRemainder", "Lkotlin/ULong;", "ulongDivide-eb3DHEI", "(JJ)J", "ulongDivide", "ulongRemainder-eb3DHEI", "ulongRemainder", "", "v", "doubleToUInt", "(D)I", "doubleToULong", "(D)J", "uintToDouble", "(I)D", "ulongToDouble", "(J)D", "", "ulongToString", "(J)Ljava/lang/String;", UnIconConfigController.A_B_SWITCH_A, "(JI)Ljava/lang/String;", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
@JvmName(name = "UnsignedKt")
/* loaded from: classes11.dex */
public final class UnsignedKt {
    @PublishedApi
    public static final int doubleToUInt(double d) {
        if (!Double.isNaN(d) && d > uintToDouble(0)) {
            if (d >= uintToDouble(-1)) {
                return -1;
            }
            double d2 = Integer.MAX_VALUE;
            if (d <= d2) {
                return UInt.m285constructorimpl((int) d);
            }
            Double.isNaN(d2);
            return UInt.m285constructorimpl(UInt.m285constructorimpl((int) (d - d2)) + UInt.m285constructorimpl(Integer.MAX_VALUE));
        }
        return 0;
    }

    @PublishedApi
    public static final long doubleToULong(double d) {
        if (!Double.isNaN(d) && d > ulongToDouble(0L)) {
            if (d >= ulongToDouble(-1L)) {
                return -1L;
            }
            if (d < Long.MAX_VALUE) {
                return ULong.m354constructorimpl((long) d);
            }
            return ULong.m354constructorimpl(ULong.m354constructorimpl((long) (d - 9.223372036854776E18d)) - Long.MIN_VALUE);
        }
        return 0L;
    }

    @PublishedApi
    public static final int uintCompare(int i2, int i3) {
        return Intrinsics.compare(i2 ^ Integer.MIN_VALUE, i3 ^ Integer.MIN_VALUE);
    }

    @PublishedApi
    /* renamed from: uintDivide-J1ME1BU  reason: not valid java name */
    public static final int m511uintDivideJ1ME1BU(int i2, int i3) {
        return UInt.m285constructorimpl((int) ((i2 & 4294967295L) / (i3 & 4294967295L)));
    }

    @PublishedApi
    /* renamed from: uintRemainder-J1ME1BU  reason: not valid java name */
    public static final int m512uintRemainderJ1ME1BU(int i2, int i3) {
        return UInt.m285constructorimpl((int) ((i2 & 4294967295L) % (i3 & 4294967295L)));
    }

    @PublishedApi
    public static final double uintToDouble(int i2) {
        double d = Integer.MAX_VALUE & i2;
        double d2 = (i2 >>> 31) << 30;
        double d3 = 2;
        Double.isNaN(d2);
        Double.isNaN(d3);
        Double.isNaN(d);
        return d + (d2 * d3);
    }

    @PublishedApi
    public static final int ulongCompare(long j2, long j3) {
        return ((j2 ^ Long.MIN_VALUE) > (j3 ^ Long.MIN_VALUE) ? 1 : ((j2 ^ Long.MIN_VALUE) == (j3 ^ Long.MIN_VALUE) ? 0 : -1));
    }

    @PublishedApi
    /* renamed from: ulongDivide-eb3DHEI  reason: not valid java name */
    public static final long m513ulongDivideeb3DHEI(long j2, long j3) {
        if (j3 < 0) {
            return ulongCompare(j2, j3) < 0 ? ULong.m354constructorimpl(0L) : ULong.m354constructorimpl(1L);
        } else if (j2 >= 0) {
            return ULong.m354constructorimpl(j2 / j3);
        } else {
            long j4 = ((j2 >>> 1) / j3) << 1;
            return ULong.m354constructorimpl(j4 + (ulongCompare(ULong.m354constructorimpl(j2 - (j4 * j3)), ULong.m354constructorimpl(j3)) < 0 ? 0 : 1));
        }
    }

    @PublishedApi
    /* renamed from: ulongRemainder-eb3DHEI  reason: not valid java name */
    public static final long m514ulongRemaindereb3DHEI(long j2, long j3) {
        if (j3 < 0) {
            return ulongCompare(j2, j3) < 0 ? j2 : ULong.m354constructorimpl(j2 - j3);
        } else if (j2 >= 0) {
            return ULong.m354constructorimpl(j2 % j3);
        } else {
            long j4 = j2 - ((((j2 >>> 1) / j3) << 1) * j3);
            if (ulongCompare(ULong.m354constructorimpl(j4), ULong.m354constructorimpl(j3)) < 0) {
                j3 = 0;
            }
            return ULong.m354constructorimpl(j4 - j3);
        }
    }

    @PublishedApi
    public static final double ulongToDouble(long j2) {
        double d = j2 >>> 11;
        double d2 = 2048;
        Double.isNaN(d);
        Double.isNaN(d2);
        double d3 = j2 & 2047;
        Double.isNaN(d3);
        return (d * d2) + d3;
    }

    @NotNull
    public static final String ulongToString(long j2) {
        return ulongToString(j2, 10);
    }

    @NotNull
    public static final String ulongToString(long j2, int i2) {
        int checkRadix;
        int checkRadix2;
        int checkRadix3;
        if (j2 >= 0) {
            checkRadix3 = CharsKt__CharJVMKt.checkRadix(i2);
            String l2 = Long.toString(j2, checkRadix3);
            Intrinsics.checkExpressionValueIsNotNull(l2, "java.lang.Long.toString(this, checkRadix(radix))");
            return l2;
        }
        long j3 = i2;
        long j4 = ((j2 >>> 1) / j3) << 1;
        long j5 = j2 - (j4 * j3);
        if (j5 >= j3) {
            j5 -= j3;
            j4++;
        }
        StringBuilder sb = new StringBuilder();
        checkRadix = CharsKt__CharJVMKt.checkRadix(i2);
        String l3 = Long.toString(j4, checkRadix);
        Intrinsics.checkExpressionValueIsNotNull(l3, "java.lang.Long.toString(this, checkRadix(radix))");
        sb.append(l3);
        checkRadix2 = CharsKt__CharJVMKt.checkRadix(i2);
        String l4 = Long.toString(j5, checkRadix2);
        Intrinsics.checkExpressionValueIsNotNull(l4, "java.lang.Long.toString(this, checkRadix(radix))");
        sb.append(l4);
        return sb.toString();
    }
}
