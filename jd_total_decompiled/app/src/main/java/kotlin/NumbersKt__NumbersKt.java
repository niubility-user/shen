package kotlin;

import kotlin.internal.InlineOnly;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0010\u0005\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\n\n\u0002\b\u0004\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0004\u0010\u0003\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0005\u0010\u0003\u001a\u0014\u0010\u0006\u001a\u00020\u0000*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a\u0014\u0010\b\u001a\u00020\u0000*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\b\u0010\u0007\u001a\u001b\u0010\n\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\t\u001a\u00020\u0001H\u0007\u00a2\u0006\u0004\b\n\u0010\u000b\u001a\u001b\u0010\f\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\t\u001a\u00020\u0001H\u0007\u00a2\u0006\u0004\b\f\u0010\u000b\u001a\u0014\u0010\u0002\u001a\u00020\u0001*\u00020\rH\u0087\b\u00a2\u0006\u0004\b\u0002\u0010\u000e\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\rH\u0087\b\u00a2\u0006\u0004\b\u0004\u0010\u000e\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\rH\u0087\b\u00a2\u0006\u0004\b\u0005\u0010\u000e\u001a\u0014\u0010\u0006\u001a\u00020\r*\u00020\rH\u0087\b\u00a2\u0006\u0004\b\u0006\u0010\u000f\u001a\u0014\u0010\b\u001a\u00020\r*\u00020\rH\u0087\b\u00a2\u0006\u0004\b\b\u0010\u000f\u001a\u001b\u0010\n\u001a\u00020\r*\u00020\r2\u0006\u0010\t\u001a\u00020\u0001H\u0007\u00a2\u0006\u0004\b\n\u0010\u0010\u001a\u001b\u0010\f\u001a\u00020\r*\u00020\r2\u0006\u0010\t\u001a\u00020\u0001H\u0007\u00a2\u0006\u0004\b\f\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"", "", "countOneBits", "(B)I", "countLeadingZeroBits", "countTrailingZeroBits", "takeHighestOneBit", "(B)B", "takeLowestOneBit", "bitCount", "rotateLeft", "(BI)B", "rotateRight", "", "(S)I", "(S)S", "(SI)S", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/NumbersKt")
/* loaded from: classes11.dex */
class NumbersKt__NumbersKt extends NumbersKt__NumbersJVMKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int countLeadingZeroBits(byte b) {
        return Integer.numberOfLeadingZeros(b & 255) - 24;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int countOneBits(byte b) {
        return Integer.bitCount(b & 255);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int countTrailingZeroBits(byte b) {
        return Integer.numberOfTrailingZeros(b | 256);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    public static final byte rotateLeft(byte b, int i2) {
        int i3 = i2 & 7;
        return (byte) (((b & 255) >>> (8 - i3)) | (b << i3));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    public static final short rotateLeft(short s, int i2) {
        int i3 = i2 & 15;
        return (short) (((s & 65535) >>> (16 - i3)) | (s << i3));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    public static final byte rotateRight(byte b, int i2) {
        int i3 = i2 & 7;
        return (byte) (((b & 255) >>> i3) | (b << (8 - i3)));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    public static final short rotateRight(short s, int i2) {
        int i3 = i2 & 15;
        return (short) (((s & 65535) >>> i3) | (s << (16 - i3)));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final byte takeHighestOneBit(byte b) {
        return (byte) Integer.highestOneBit(b & 255);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final byte takeLowestOneBit(byte b) {
        return (byte) Integer.lowestOneBit(b);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int countLeadingZeroBits(short s) {
        return Integer.numberOfLeadingZeros(s & 65535) - 16;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int countOneBits(short s) {
        return Integer.bitCount(s & 65535);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final int countTrailingZeroBits(short s) {
        return Integer.numberOfTrailingZeros(s | 65536);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final short takeHighestOneBit(short s) {
        return (short) Integer.highestOneBit(s & 65535);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final short takeLowestOneBit(short s) {
        return (short) Integer.lowestOneBit(s);
    }
}
