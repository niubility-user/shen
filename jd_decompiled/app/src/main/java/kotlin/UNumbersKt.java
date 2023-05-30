package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\u0017\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u0017\u0010\u0006\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0003\u001a\u0017\u0010\b\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0007\u0010\u0003\u001a\u0017\u0010\n\u001a\u00020\u0000*\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\u0003\u001a\u0017\u0010\f\u001a\u00020\u0000*\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\u0003\u001a\u001f\u0010\u0010\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\r\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a\u001f\u0010\u0012\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\r\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u000f\u001a\u0017\u0010\u0004\u001a\u00020\u0001*\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a\u0017\u0010\u0006\u001a\u00020\u0001*\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0016\u0010\u0015\u001a\u0017\u0010\b\u001a\u00020\u0001*\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0015\u001a\u0017\u0010\n\u001a\u00020\u0013*\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019\u001a\u0017\u0010\f\u001a\u00020\u0013*\u00020\u0013H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u0019\u001a\u001f\u0010\u0010\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\r\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a\u001f\u0010\u0012\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\r\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001c\u001a\u0017\u0010\u0004\u001a\u00020\u0001*\u00020\u001eH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001f\u0010 \u001a\u0017\u0010\u0006\u001a\u00020\u0001*\u00020\u001eH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b!\u0010 \u001a\u0017\u0010\b\u001a\u00020\u0001*\u00020\u001eH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\"\u0010 \u001a\u0017\u0010\n\u001a\u00020\u001e*\u00020\u001eH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010$\u001a\u0017\u0010\f\u001a\u00020\u001e*\u00020\u001eH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010$\u001a\u001f\u0010\u0010\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010\r\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b&\u0010'\u001a\u001f\u0010\u0012\u001a\u00020\u001e*\u00020\u001e2\u0006\u0010\r\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b(\u0010'\u001a\u0017\u0010\u0004\u001a\u00020\u0001*\u00020)H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b*\u0010+\u001a\u0017\u0010\u0006\u001a\u00020\u0001*\u00020)H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b,\u0010+\u001a\u0017\u0010\b\u001a\u00020\u0001*\u00020)H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b-\u0010+\u001a\u0017\u0010\n\u001a\u00020)*\u00020)H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b.\u0010/\u001a\u0017\u0010\f\u001a\u00020)*\u00020)H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b0\u0010/\u001a\u001f\u0010\u0010\u001a\u00020)*\u00020)2\u0006\u0010\r\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b1\u00102\u001a\u001f\u0010\u0012\u001a\u00020)*\u00020)2\u0006\u0010\r\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b3\u00102\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00064"}, d2 = {"Lkotlin/UInt;", "", "countOneBits-WZ4Q5Ns", "(I)I", "countOneBits", "countLeadingZeroBits-WZ4Q5Ns", "countLeadingZeroBits", "countTrailingZeroBits-WZ4Q5Ns", "countTrailingZeroBits", "takeHighestOneBit-WZ4Q5Ns", "takeHighestOneBit", "takeLowestOneBit-WZ4Q5Ns", "takeLowestOneBit", "bitCount", "rotateLeft-V7xB4Y4", "(II)I", "rotateLeft", "rotateRight-V7xB4Y4", "rotateRight", "Lkotlin/ULong;", "countOneBits-VKZWuLQ", "(J)I", "countLeadingZeroBits-VKZWuLQ", "countTrailingZeroBits-VKZWuLQ", "takeHighestOneBit-VKZWuLQ", "(J)J", "takeLowestOneBit-VKZWuLQ", "rotateLeft-JSWoG40", "(JI)J", "rotateRight-JSWoG40", "Lkotlin/UByte;", "countOneBits-7apg3OU", "(B)I", "countLeadingZeroBits-7apg3OU", "countTrailingZeroBits-7apg3OU", "takeHighestOneBit-7apg3OU", "(B)B", "takeLowestOneBit-7apg3OU", "rotateLeft-LxnNnR4", "(BI)B", "rotateRight-LxnNnR4", "Lkotlin/UShort;", "countOneBits-xj2QHRw", "(S)I", "countLeadingZeroBits-xj2QHRw", "countTrailingZeroBits-xj2QHRw", "takeHighestOneBit-xj2QHRw", "(S)S", "takeLowestOneBit-xj2QHRw", "rotateLeft-olVBNx4", "(SI)S", "rotateRight-olVBNx4", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
@JvmName(name = "UNumbersKt")
/* loaded from: classes11.dex */
public final class UNumbersKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: countLeadingZeroBits-7apg3OU  reason: not valid java name */
    private static final int m416countLeadingZeroBits7apg3OU(byte b) {
        return Integer.numberOfLeadingZeros(b & 255) - 24;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: countLeadingZeroBits-VKZWuLQ  reason: not valid java name */
    private static final int m417countLeadingZeroBitsVKZWuLQ(long j2) {
        return Long.numberOfLeadingZeros(j2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: countLeadingZeroBits-WZ4Q5Ns  reason: not valid java name */
    private static final int m418countLeadingZeroBitsWZ4Q5Ns(int i2) {
        return Integer.numberOfLeadingZeros(i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: countLeadingZeroBits-xj2QHRw  reason: not valid java name */
    private static final int m419countLeadingZeroBitsxj2QHRw(short s) {
        return Integer.numberOfLeadingZeros(s & 65535) - 16;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: countOneBits-7apg3OU  reason: not valid java name */
    private static final int m420countOneBits7apg3OU(byte b) {
        return Integer.bitCount(UInt.m285constructorimpl(b & 255));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: countOneBits-VKZWuLQ  reason: not valid java name */
    private static final int m421countOneBitsVKZWuLQ(long j2) {
        return Long.bitCount(j2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: countOneBits-WZ4Q5Ns  reason: not valid java name */
    private static final int m422countOneBitsWZ4Q5Ns(int i2) {
        return Integer.bitCount(i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: countOneBits-xj2QHRw  reason: not valid java name */
    private static final int m423countOneBitsxj2QHRw(short s) {
        return Integer.bitCount(UInt.m285constructorimpl(s & 65535));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: countTrailingZeroBits-7apg3OU  reason: not valid java name */
    private static final int m424countTrailingZeroBits7apg3OU(byte b) {
        return Integer.numberOfTrailingZeros(b | 256);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: countTrailingZeroBits-VKZWuLQ  reason: not valid java name */
    private static final int m425countTrailingZeroBitsVKZWuLQ(long j2) {
        return Long.numberOfTrailingZeros(j2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: countTrailingZeroBits-WZ4Q5Ns  reason: not valid java name */
    private static final int m426countTrailingZeroBitsWZ4Q5Ns(int i2) {
        return Integer.numberOfTrailingZeros(i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: countTrailingZeroBits-xj2QHRw  reason: not valid java name */
    private static final int m427countTrailingZeroBitsxj2QHRw(short s) {
        return Integer.numberOfTrailingZeros(s | 65536);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: rotateLeft-JSWoG40  reason: not valid java name */
    private static final long m428rotateLeftJSWoG40(long j2, int i2) {
        return ULong.m354constructorimpl(Long.rotateLeft(j2, i2));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: rotateLeft-LxnNnR4  reason: not valid java name */
    private static final byte m429rotateLeftLxnNnR4(byte b, int i2) {
        return UByte.m218constructorimpl(NumbersKt__NumbersKt.rotateLeft(b, i2));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: rotateLeft-V7xB4Y4  reason: not valid java name */
    private static final int m430rotateLeftV7xB4Y4(int i2, int i3) {
        return UInt.m285constructorimpl(Integer.rotateLeft(i2, i3));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: rotateLeft-olVBNx4  reason: not valid java name */
    private static final short m431rotateLeftolVBNx4(short s, int i2) {
        return UShort.m451constructorimpl(NumbersKt__NumbersKt.rotateLeft(s, i2));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: rotateRight-JSWoG40  reason: not valid java name */
    private static final long m432rotateRightJSWoG40(long j2, int i2) {
        return ULong.m354constructorimpl(Long.rotateRight(j2, i2));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: rotateRight-LxnNnR4  reason: not valid java name */
    private static final byte m433rotateRightLxnNnR4(byte b, int i2) {
        return UByte.m218constructorimpl(NumbersKt__NumbersKt.rotateRight(b, i2));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: rotateRight-V7xB4Y4  reason: not valid java name */
    private static final int m434rotateRightV7xB4Y4(int i2, int i3) {
        return UInt.m285constructorimpl(Integer.rotateRight(i2, i3));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: rotateRight-olVBNx4  reason: not valid java name */
    private static final short m435rotateRightolVBNx4(short s, int i2) {
        return UShort.m451constructorimpl(NumbersKt__NumbersKt.rotateRight(s, i2));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeHighestOneBit-7apg3OU  reason: not valid java name */
    private static final byte m436takeHighestOneBit7apg3OU(byte b) {
        return UByte.m218constructorimpl((byte) Integer.highestOneBit(b & 255));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeHighestOneBit-VKZWuLQ  reason: not valid java name */
    private static final long m437takeHighestOneBitVKZWuLQ(long j2) {
        return ULong.m354constructorimpl(Long.highestOneBit(j2));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeHighestOneBit-WZ4Q5Ns  reason: not valid java name */
    private static final int m438takeHighestOneBitWZ4Q5Ns(int i2) {
        return UInt.m285constructorimpl(Integer.highestOneBit(i2));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeHighestOneBit-xj2QHRw  reason: not valid java name */
    private static final short m439takeHighestOneBitxj2QHRw(short s) {
        return UShort.m451constructorimpl((short) Integer.highestOneBit(s & 65535));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeLowestOneBit-7apg3OU  reason: not valid java name */
    private static final byte m440takeLowestOneBit7apg3OU(byte b) {
        return UByte.m218constructorimpl((byte) Integer.lowestOneBit(b & 255));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeLowestOneBit-VKZWuLQ  reason: not valid java name */
    private static final long m441takeLowestOneBitVKZWuLQ(long j2) {
        return ULong.m354constructorimpl(Long.lowestOneBit(j2));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeLowestOneBit-WZ4Q5Ns  reason: not valid java name */
    private static final int m442takeLowestOneBitWZ4Q5Ns(int i2) {
        return UInt.m285constructorimpl(Integer.lowestOneBit(i2));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: takeLowestOneBit-xj2QHRw  reason: not valid java name */
    private static final short m443takeLowestOneBitxj2QHRw(short s) {
        return UShort.m451constructorimpl((short) Integer.lowestOneBit(s & 65535));
    }
}
