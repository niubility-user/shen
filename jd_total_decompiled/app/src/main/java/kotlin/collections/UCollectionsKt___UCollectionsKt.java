package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\b\u001a\u001c\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a\u001c\u0010\u0007\u001a\u00020\u0006*\b\u0012\u0004\u0012\u00020\u00050\u0000H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0007\u0010\b\u001a\u001c\u0010\u000b\u001a\u00020\n*\b\u0012\u0004\u0012\u00020\t0\u0000H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\f\u001a\u001c\u0010\u000f\u001a\u00020\u000e*\b\u0012\u0004\u0012\u00020\r0\u0000H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001a\u001c\u0010\u0014\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00050\u0011H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a\u001c\u0010\u0014\u001a\u00020\t*\b\u0012\u0004\u0012\u00020\t0\u0011H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0015\u0010\u0016\u001a\u001c\u0010\u0014\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00010\u0011H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0013\u001a\u001c\u0010\u0014\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\r0\u0011H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"", "Lkotlin/UByte;", "Lkotlin/UByteArray;", "toUByteArray", "(Ljava/util/Collection;)[B", "Lkotlin/UInt;", "Lkotlin/UIntArray;", "toUIntArray", "(Ljava/util/Collection;)[I", "Lkotlin/ULong;", "Lkotlin/ULongArray;", "toULongArray", "(Ljava/util/Collection;)[J", "Lkotlin/UShort;", "Lkotlin/UShortArray;", "toUShortArray", "(Ljava/util/Collection;)[S", "", "sumOfUInt", "(Ljava/lang/Iterable;)I", "sum", "sumOfULong", "(Ljava/lang/Iterable;)J", "sumOfUByte", "sumOfUShort", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/collections/UCollectionsKt")
/* loaded from: classes11.dex */
class UCollectionsKt___UCollectionsKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfUByte")
    public static final int sumOfUByte(@NotNull Iterable<UByte> iterable) {
        Iterator<UByte> it = iterable.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 = UInt.m285constructorimpl(i2 + UInt.m285constructorimpl(it.next().getData() & 255));
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfUInt")
    public static final int sumOfUInt(@NotNull Iterable<UInt> iterable) {
        Iterator<UInt> it = iterable.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 = UInt.m285constructorimpl(i2 + it.next().getData());
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfULong")
    public static final long sumOfULong(@NotNull Iterable<ULong> iterable) {
        Iterator<ULong> it = iterable.iterator();
        long j2 = 0;
        while (it.hasNext()) {
            j2 = ULong.m354constructorimpl(j2 + it.next().getData());
        }
        return j2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @JvmName(name = "sumOfUShort")
    public static final int sumOfUShort(@NotNull Iterable<UShort> iterable) {
        Iterator<UShort> it = iterable.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 = UInt.m285constructorimpl(i2 + UInt.m285constructorimpl(it.next().getData() & 65535));
        }
        return i2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] toUByteArray(@NotNull Collection<UByte> collection) {
        byte[] m261constructorimpl = UByteArray.m261constructorimpl(collection.size());
        Iterator<UByte> it = collection.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            UByteArray.m272setVurrAj0(m261constructorimpl, i2, it.next().getData());
            i2++;
        }
        return m261constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final int[] toUIntArray(@NotNull Collection<UInt> collection) {
        int[] m330constructorimpl = UIntArray.m330constructorimpl(collection.size());
        Iterator<UInt> it = collection.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            UIntArray.m341setVXSXFK8(m330constructorimpl, i2, it.next().getData());
            i2++;
        }
        return m330constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final long[] toULongArray(@NotNull Collection<ULong> collection) {
        long[] m399constructorimpl = ULongArray.m399constructorimpl(collection.size());
        Iterator<ULong> it = collection.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            ULongArray.m410setk8EXiF4(m399constructorimpl, i2, it.next().getData());
            i2++;
        }
        return m399constructorimpl;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final short[] toUShortArray(@NotNull Collection<UShort> collection) {
        short[] m494constructorimpl = UShortArray.m494constructorimpl(collection.size());
        Iterator<UShort> it = collection.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            UShortArray.m505set01HTLdE(m494constructorimpl, i2, it.next().getData());
            i2++;
        }
        return m494constructorimpl;
    }
}
