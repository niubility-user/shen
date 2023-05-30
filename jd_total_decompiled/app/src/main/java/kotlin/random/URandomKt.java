package kotlin.random;

import ..;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\u001a\u0016\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a&\u0010\u0002\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\b\u0010\t\u001a\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0002\u0010\f\u001a\u0016\u0010\u000e\u001a\u00020\r*\u00020\u0000H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a\u001e\u0010\u000e\u001a\u00020\r*\u00020\u00002\u0006\u0010\u0004\u001a\u00020\rH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0011\u001a&\u0010\u000e\u001a\u00020\r*\u00020\u00002\u0006\u0010\u0007\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\rH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a\u001e\u0010\u000e\u001a\u00020\r*\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0014H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u0015\u001a\u001e\u0010\u001a\u001a\u00020\u0016*\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0016H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0018\u0010\u0019\u001a\u001e\u0010\u001a\u001a\u00020\u0016*\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u001d\u001a2\u0010\u001a\u001a\u00020\u0016*\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u001e\u001a\u00020\u001b2\b\b\u0002\u0010\u001f\u001a\u00020\u001bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010!\u001a\"\u0010%\u001a\u00020\"2\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010$\u001a\"\u0010(\u001a\u00020\"2\u0006\u0010\u0007\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\rH\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b&\u0010'\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006)"}, d2 = {"Lkotlin/random/Random;", "Lkotlin/UInt;", "nextUInt", "(Lkotlin/random/Random;)I", "until", "nextUInt-qCasIEU", "(Lkotlin/random/Random;I)I", "from", "nextUInt-a8DCA5k", "(Lkotlin/random/Random;II)I", "Lkotlin/ranges/UIntRange;", "range", "(Lkotlin/random/Random;Lkotlin/ranges/UIntRange;)I", "Lkotlin/ULong;", "nextULong", "(Lkotlin/random/Random;)J", "nextULong-V1Xi4fY", "(Lkotlin/random/Random;J)J", "nextULong-jmpaW-c", "(Lkotlin/random/Random;JJ)J", "Lkotlin/ranges/ULongRange;", "(Lkotlin/random/Random;Lkotlin/ranges/ULongRange;)J", "Lkotlin/UByteArray;", "array", "nextUBytes-EVgfTAA", "(Lkotlin/random/Random;[B)[B", "nextUBytes", "", ApkDownloadTable.FIELD_SIZE, "(Lkotlin/random/Random;I)[B", "fromIndex", "toIndex", "nextUBytes-Wvrt4B4", "(Lkotlin/random/Random;[BII)[B", "", "checkUIntRangeBounds-J1ME1BU", "(II)V", "checkUIntRangeBounds", "checkULongRangeBounds-eb3DHEI", "(JJ)V", "checkULongRangeBounds", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class URandomKt {
    @ExperimentalUnsignedTypes
    /* renamed from: checkUIntRangeBounds-J1ME1BU */
    public static final void m1111checkUIntRangeBoundsJ1ME1BU(int i2, int i3) {
        if (!(UnsignedKt.uintCompare(i3, i2) > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(UInt.m279boximpl(i2), UInt.m279boximpl(i3)).toString());
        }
    }

    @ExperimentalUnsignedTypes
    /* renamed from: checkULongRangeBounds-eb3DHEI */
    public static final void m1112checkULongRangeBoundseb3DHEI(long j2, long j3) {
        if (!(UnsignedKt.ulongCompare(j3, j2) > 0)) {
            throw new IllegalArgumentException(RandomKt.boundsErrorMessage(ULong.m348boximpl(j2), ULong.m348boximpl(j3)).toString());
        }
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    public static final byte[] nextUBytes(@NotNull Random random, int i2) {
        return UByteArray.m262constructorimpl(random.nextBytes(i2));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: nextUBytes-EVgfTAA */
    public static final byte[] m1113nextUBytesEVgfTAA(@NotNull Random random, @NotNull byte[] bArr) {
        random.nextBytes(bArr);
        return bArr;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: nextUBytes-Wvrt4B4 */
    public static final byte[] m1114nextUBytesWvrt4B4(@NotNull Random random, @NotNull byte[] bArr, int i2, int i3) {
        random.nextBytes(bArr, i2, i3);
        return bArr;
    }

    /* renamed from: nextUBytes-Wvrt4B4$default */
    public static /* synthetic */ byte[] m1115nextUBytesWvrt4B4$default(Random random, byte[] bArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = UByteArray.m268getSizeimpl(bArr);
        }
        return m1114nextUBytesWvrt4B4(random, bArr, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final int nextUInt(@NotNull Random random) {
        return UInt.m285constructorimpl(random.nextInt());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: nextUInt-a8DCA5k */
    public static final int m1116nextUInta8DCA5k(@NotNull Random random, int i2, int i3) {
        m1111checkUIntRangeBoundsJ1ME1BU(i2, i3);
        return UInt.m285constructorimpl(random.nextInt(i2 ^ Integer.MIN_VALUE, i3 ^ Integer.MIN_VALUE) ^ Integer.MIN_VALUE);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: nextUInt-qCasIEU */
    public static final int m1117nextUIntqCasIEU(@NotNull Random random, int i2) {
        return m1116nextUInta8DCA5k(random, 0, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final long nextULong(@NotNull Random random) {
        return ULong.m354constructorimpl(random.nextLong());
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: nextULong-V1Xi4fY */
    public static final long m1118nextULongV1Xi4fY(@NotNull Random random, long j2) {
        return m1119nextULongjmpaWc(random, 0L, j2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: nextULong-jmpaW-c */
    public static final long m1119nextULongjmpaWc(@NotNull Random random, long j2, long j3) {
        m1112checkULongRangeBoundseb3DHEI(j2, j3);
        return ULong.m354constructorimpl(random.nextLong(j2 ^ Long.MIN_VALUE, j3 ^ Long.MIN_VALUE) ^ Long.MIN_VALUE);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final int nextUInt(@NotNull Random random, @NotNull  Var) {
        if (!Var.isEmpty()) {
            return UnsignedKt.uintCompare(Var.getLast(), -1) < 0 ? m1116nextUInta8DCA5k(random, Var.getFirst(), UInt.m285constructorimpl(Var.getLast() + 1)) : UnsignedKt.uintCompare(Var.getFirst(), 0) > 0 ? UInt.m285constructorimpl(m1116nextUInta8DCA5k(random, UInt.m285constructorimpl(Var.getFirst() - 1), Var.getLast()) + 1) : nextUInt(random);
        }
        throw new IllegalArgumentException("Cannot get random in empty range: " + Var);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final long nextULong(@NotNull Random random, @NotNull .. Var) {
        if (!Var.isEmpty()) {
            if (UnsignedKt.ulongCompare(Var.getLast(), -1L) < 0) {
                return m1119nextULongjmpaWc(random, Var.getFirst(), ULong.m354constructorimpl(Var.getLast() + ULong.m354constructorimpl(4294967295L & 1)));
            }
            if (UnsignedKt.ulongCompare(Var.getFirst(), 0L) > 0) {
                long j2 = 4294967295L & 1;
                return ULong.m354constructorimpl(m1119nextULongjmpaWc(random, ULong.m354constructorimpl(Var.getFirst() - ULong.m354constructorimpl(j2)), Var.getLast()) + ULong.m354constructorimpl(j2));
            }
            return nextULong(random);
        }
        throw new IllegalArgumentException("Cannot get random in empty range: " + Var);
    }
}
