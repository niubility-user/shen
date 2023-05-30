package kotlin.sequences;

import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001c\u0010\u0004\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u001c\u0010\u0004\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00050\u0000H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007\u001a\u001c\u0010\u0004\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\b0\u0000H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\u0003\u001a\u001c\u0010\u0004\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\n0\u0000H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\u0003\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lkotlin/sequences/Sequence;", "Lkotlin/UInt;", "sumOfUInt", "(Lkotlin/sequences/Sequence;)I", "sum", "Lkotlin/ULong;", "sumOfULong", "(Lkotlin/sequences/Sequence;)J", "Lkotlin/UByte;", "sumOfUByte", "Lkotlin/UShort;", "sumOfUShort", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/sequences/USequencesKt")
/* loaded from: classes11.dex */
class USequencesKt___USequencesKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfUByte")
    public static final int sumOfUByte(@NotNull Sequence<UByte> sequence) {
        Iterator<UByte> it = sequence.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 = UInt.m285constructorimpl(i2 + UInt.m285constructorimpl(it.next().getData() & 255));
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfUInt")
    public static final int sumOfUInt(@NotNull Sequence<UInt> sequence) {
        Iterator<UInt> it = sequence.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 = UInt.m285constructorimpl(i2 + it.next().getData());
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfULong")
    public static final long sumOfULong(@NotNull Sequence<ULong> sequence) {
        Iterator<ULong> it = sequence.iterator();
        long j2 = 0;
        while (it.hasNext()) {
            j2 = ULong.m354constructorimpl(j2 + it.next().getData());
        }
        return j2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfUShort")
    public static final int sumOfUShort(@NotNull Sequence<UShort> sequence) {
        Iterator<UShort> it = sequence.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 = UInt.m285constructorimpl(i2 + UInt.m285constructorimpl(it.next().getData() & 65535));
        }
        return i2;
    }
}
