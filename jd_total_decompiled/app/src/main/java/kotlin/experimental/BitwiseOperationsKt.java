package kotlin.experimental;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0010\u0005\n\u0002\b\u0007\n\u0002\u0010\n\n\u0002\b\u0003\u001a\u001c\u0010\u0002\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\f\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u001c\u0010\u0004\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\f\u00a2\u0006\u0004\b\u0004\u0010\u0003\u001a\u001c\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0087\f\u00a2\u0006\u0004\b\u0005\u0010\u0003\u001a\u0014\u0010\u0006\u001a\u00020\u0000*\u00020\u0000H\u0087\b\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a\u001c\u0010\u0002\u001a\u00020\b*\u00020\b2\u0006\u0010\u0001\u001a\u00020\bH\u0087\f\u00a2\u0006\u0004\b\u0002\u0010\t\u001a\u001c\u0010\u0004\u001a\u00020\b*\u00020\b2\u0006\u0010\u0001\u001a\u00020\bH\u0087\f\u00a2\u0006\u0004\b\u0004\u0010\t\u001a\u001c\u0010\u0005\u001a\u00020\b*\u00020\b2\u0006\u0010\u0001\u001a\u00020\bH\u0087\f\u00a2\u0006\u0004\b\u0005\u0010\t\u001a\u0014\u0010\u0006\u001a\u00020\b*\u00020\bH\u0087\b\u00a2\u0006\u0004\b\u0006\u0010\n\u00a8\u0006\u000b"}, d2 = {"", "other", "and", "(BB)B", "or", "xor", "inv", "(B)B", "", "(SS)S", "(S)S", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class BitwiseOperationsKt {
    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte and(byte b, byte b2) {
        return (byte) (b & b2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short and(short s, short s2) {
        return (short) (s & s2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte inv(byte b) {
        return (byte) (b ^ (-1));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short inv(short s) {
        return (short) (s ^ (-1));
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte or(byte b, byte b2) {
        return (byte) (b | b2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short or(short s, short s2) {
        return (short) (s | s2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte xor(byte b, byte b2) {
        return (byte) (b ^ b2);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short xor(short s, short s2) {
        return (short) (s ^ s2);
    }
}
