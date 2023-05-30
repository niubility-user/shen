package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0010\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\u0002\u0010\u0005\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0006\u0010\u0003\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\u0006\u0010\u0005\u001a\u0014\u0010\u0007\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0007\u0010\u0003\u001a\u0014\u0010\u0007\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\u0007\u0010\u0005\u001a\u0014\u0010\t\u001a\u00020\b*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\t\u0010\n\u001a\u0014\u0010\u000b\u001a\u00020\b*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u000b\u0010\n\u001a\u001c\u0010\u000e\u001a\u00020\u0000*\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0087\b\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a\u0014\u0010\t\u001a\u00020\u0010*\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\t\u0010\u0011\u001a\u0014\u0010\u000b\u001a\u00020\u0010*\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\u000b\u0010\u0011\u001a\u001c\u0010\u000e\u001a\u00020\u0004*\u00020\u00122\u0006\u0010\r\u001a\u00020\u0010H\u0087\b\u00a2\u0006\u0004\b\u000e\u0010\u0013\u001a\u0014\u0010\u0014\u001a\u00020\u0010*\u00020\u0010H\u0087\b\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a\u0014\u0010\u0016\u001a\u00020\u0010*\u00020\u0010H\u0087\b\u00a2\u0006\u0004\b\u0016\u0010\u0015\u001a\u0014\u0010\u0017\u001a\u00020\u0010*\u00020\u0010H\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\u0015\u001a\u0014\u0010\u0018\u001a\u00020\u0010*\u00020\u0010H\u0087\b\u00a2\u0006\u0004\b\u0018\u0010\u0015\u001a\u0014\u0010\u0019\u001a\u00020\u0010*\u00020\u0010H\u0087\b\u00a2\u0006\u0004\b\u0019\u0010\u0015\u001a\u001c\u0010\u001b\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0087\b\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a\u001c\u0010\u001d\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u0010H\u0087\b\u00a2\u0006\u0004\b\u001d\u0010\u001c\u001a\u0014\u0010\u0014\u001a\u00020\u0010*\u00020\bH\u0087\b\u00a2\u0006\u0004\b\u0014\u0010\u001e\u001a\u0014\u0010\u0016\u001a\u00020\u0010*\u00020\bH\u0087\b\u00a2\u0006\u0004\b\u0016\u0010\u001e\u001a\u0014\u0010\u0017\u001a\u00020\u0010*\u00020\bH\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\u001e\u001a\u0014\u0010\u0018\u001a\u00020\b*\u00020\bH\u0087\b\u00a2\u0006\u0004\b\u0018\u0010\u001f\u001a\u0014\u0010\u0019\u001a\u00020\b*\u00020\bH\u0087\b\u00a2\u0006\u0004\b\u0019\u0010\u001f\u001a\u001c\u0010\u001b\u001a\u00020\b*\u00020\b2\u0006\u0010\u001a\u001a\u00020\u0010H\u0087\b\u00a2\u0006\u0004\b\u001b\u0010 \u001a\u001c\u0010\u001d\u001a\u00020\b*\u00020\b2\u0006\u0010\u001a\u001a\u00020\u0010H\u0087\b\u00a2\u0006\u0004\b\u001d\u0010 \u00a8\u0006!"}, d2 = {"", "", "isNaN", "(D)Z", "", "(F)Z", "isInfinite", "isFinite", "", "toBits", "(D)J", "toRawBits", "Lkotlin/Double$Companion;", "bits", "fromBits", "(Lkotlin/jvm/internal/DoubleCompanionObject;J)D", "", "(F)I", "Lkotlin/Float$Companion;", "(Lkotlin/jvm/internal/FloatCompanionObject;I)F", "countOneBits", "(I)I", "countLeadingZeroBits", "countTrailingZeroBits", "takeHighestOneBit", "takeLowestOneBit", "bitCount", "rotateLeft", "(II)I", "rotateRight", "(J)I", "(J)J", "(JI)J", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/NumbersKt")
/* loaded from: classes11.dex */
public class NumbersKt__NumbersJVMKt extends NumbersKt__BigIntegersKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int countLeadingZeroBits(int i2) {
        return Integer.numberOfLeadingZeros(i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int countOneBits(int i2) {
        return Integer.bitCount(i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int countTrailingZeroBits(int i2) {
        return Integer.numberOfTrailingZeros(i2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final double fromBits(@NotNull DoubleCompanionObject doubleCompanionObject, long j2) {
        return Double.longBitsToDouble(j2);
    }

    @InlineOnly
    private static final boolean isFinite(double d) {
        return (Double.isInfinite(d) || Double.isNaN(d)) ? false : true;
    }

    @InlineOnly
    private static final boolean isInfinite(double d) {
        return Double.isInfinite(d);
    }

    @InlineOnly
    private static final boolean isNaN(double d) {
        return Double.isNaN(d);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int rotateLeft(int i2, int i3) {
        return Integer.rotateLeft(i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int rotateRight(int i2, int i3) {
        return Integer.rotateRight(i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int takeHighestOneBit(int i2) {
        return Integer.highestOneBit(i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int takeLowestOneBit(int i2) {
        return Integer.lowestOneBit(i2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final long toBits(double d) {
        return Double.doubleToLongBits(d);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final long toRawBits(double d) {
        return Double.doubleToRawLongBits(d);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int countLeadingZeroBits(long j2) {
        return Long.numberOfLeadingZeros(j2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int countOneBits(long j2) {
        return Long.bitCount(j2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int countTrailingZeroBits(long j2) {
        return Long.numberOfTrailingZeros(j2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final float fromBits(@NotNull FloatCompanionObject floatCompanionObject, int i2) {
        return Float.intBitsToFloat(i2);
    }

    @InlineOnly
    private static final boolean isFinite(float f2) {
        return (Float.isInfinite(f2) || Float.isNaN(f2)) ? false : true;
    }

    @InlineOnly
    private static final boolean isInfinite(float f2) {
        return Float.isInfinite(f2);
    }

    @InlineOnly
    private static final boolean isNaN(float f2) {
        return Float.isNaN(f2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final long rotateLeft(long j2, int i2) {
        return Long.rotateLeft(j2, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final long rotateRight(long j2, int i2) {
        return Long.rotateRight(j2, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final long takeHighestOneBit(long j2) {
        return Long.highestOneBit(j2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final long takeLowestOneBit(long j2) {
        return Long.lowestOneBit(j2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final int toBits(float f2) {
        return Float.floatToIntBits(f2);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final int toRawBits(float f2) {
        return Float.floatToRawIntBits(f2);
    }
}
