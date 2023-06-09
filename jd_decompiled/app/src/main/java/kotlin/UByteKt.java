package kotlin;

import kotlin.internal.InlineOnly;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a\u0017\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u0017\u0010\u0002\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0005\u001a\u0017\u0010\u0002\u001a\u00020\u0001*\u00020\u0006H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0007\u001a\u0017\u0010\u0002\u001a\u00020\u0001*\u00020\bH\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\n"}, d2 = {"", "Lkotlin/UByte;", "toUByte", "(B)B", "", "(S)B", "", "(I)B", "", "(J)B", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class UByteKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte toUByte(byte b) {
        return UByte.m218constructorimpl(b);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte toUByte(short s) {
        return UByte.m218constructorimpl((byte) s);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte toUByte(int i2) {
        return UByte.m218constructorimpl((byte) i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    private static final byte toUByte(long j2) {
        return UByte.m218constructorimpl((byte) j2);
    }
}
