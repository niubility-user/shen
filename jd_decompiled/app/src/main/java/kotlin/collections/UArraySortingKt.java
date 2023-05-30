package kotlin.collections;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a*\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000e\u001a*\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a*\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a*\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00162\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00162\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001a\u001a\u001a\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u0000H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a\u001a\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\fH\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001e\u0010\u001f\u001a\u001a\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u0011H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010!\u001a\u001a\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u0016H\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\"\u0010#\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006$"}, d2 = {"Lkotlin/UByteArray;", "array", "", "left", "right", "partition-4UcCI2c", "([BII)I", "partition", "", "quickSort-4UcCI2c", "([BII)V", "quickSort", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort-Aa5vz7o", "([SII)V", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "quickSort-oBK06Vg", "([III)V", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "quickSort--nroSd4", "([JII)V", "sortArray-GBYM_sE", "([B)V", "sortArray", "sortArray-rL5Bavg", "([S)V", "sortArray--ajY-9A", "([I)V", "sortArray-QwZRm1k", "([J)V", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class UArraySortingKt {
    @ExperimentalUnsignedTypes
    /* renamed from: partition--nroSd4  reason: not valid java name */
    private static final int m519partitionnroSd4(long[] jArr, int i2, int i3) {
        long m405getimpl = ULongArray.m405getimpl(jArr, (i2 + i3) / 2);
        while (i2 <= i3) {
            while (UnsignedKt.ulongCompare(ULongArray.m405getimpl(jArr, i2), m405getimpl) < 0) {
                i2++;
            }
            while (UnsignedKt.ulongCompare(ULongArray.m405getimpl(jArr, i3), m405getimpl) > 0) {
                i3--;
            }
            if (i2 <= i3) {
                long m405getimpl2 = ULongArray.m405getimpl(jArr, i2);
                ULongArray.m410setk8EXiF4(jArr, i2, ULongArray.m405getimpl(jArr, i3));
                ULongArray.m410setk8EXiF4(jArr, i3, m405getimpl2);
                i2++;
                i3--;
            }
        }
        return i2;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: partition-4UcCI2c  reason: not valid java name */
    private static final int m520partition4UcCI2c(byte[] bArr, int i2, int i3) {
        int i4;
        byte m267getimpl = UByteArray.m267getimpl(bArr, (i2 + i3) / 2);
        while (i2 <= i3) {
            while (true) {
                i4 = m267getimpl & 255;
                if (Intrinsics.compare(UByteArray.m267getimpl(bArr, i2) & 255, i4) >= 0) {
                    break;
                }
                i2++;
            }
            while (Intrinsics.compare(UByteArray.m267getimpl(bArr, i3) & 255, i4) > 0) {
                i3--;
            }
            if (i2 <= i3) {
                byte m267getimpl2 = UByteArray.m267getimpl(bArr, i2);
                UByteArray.m272setVurrAj0(bArr, i2, UByteArray.m267getimpl(bArr, i3));
                UByteArray.m272setVurrAj0(bArr, i3, m267getimpl2);
                i2++;
                i3--;
            }
        }
        return i2;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: partition-Aa5vz7o  reason: not valid java name */
    private static final int m521partitionAa5vz7o(short[] sArr, int i2, int i3) {
        int i4;
        short m500getimpl = UShortArray.m500getimpl(sArr, (i2 + i3) / 2);
        while (i2 <= i3) {
            while (true) {
                i4 = m500getimpl & 65535;
                if (Intrinsics.compare(UShortArray.m500getimpl(sArr, i2) & 65535, i4) >= 0) {
                    break;
                }
                i2++;
            }
            while (Intrinsics.compare(UShortArray.m500getimpl(sArr, i3) & 65535, i4) > 0) {
                i3--;
            }
            if (i2 <= i3) {
                short m500getimpl2 = UShortArray.m500getimpl(sArr, i2);
                UShortArray.m505set01HTLdE(sArr, i2, UShortArray.m500getimpl(sArr, i3));
                UShortArray.m505set01HTLdE(sArr, i3, m500getimpl2);
                i2++;
                i3--;
            }
        }
        return i2;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: partition-oBK06Vg  reason: not valid java name */
    private static final int m522partitionoBK06Vg(int[] iArr, int i2, int i3) {
        int m336getimpl = UIntArray.m336getimpl(iArr, (i2 + i3) / 2);
        while (i2 <= i3) {
            while (UnsignedKt.uintCompare(UIntArray.m336getimpl(iArr, i2), m336getimpl) < 0) {
                i2++;
            }
            while (UnsignedKt.uintCompare(UIntArray.m336getimpl(iArr, i3), m336getimpl) > 0) {
                i3--;
            }
            if (i2 <= i3) {
                int m336getimpl2 = UIntArray.m336getimpl(iArr, i2);
                UIntArray.m341setVXSXFK8(iArr, i2, UIntArray.m336getimpl(iArr, i3));
                UIntArray.m341setVXSXFK8(iArr, i3, m336getimpl2);
                i2++;
                i3--;
            }
        }
        return i2;
    }

    @ExperimentalUnsignedTypes
    /* renamed from: quickSort--nroSd4  reason: not valid java name */
    private static final void m523quickSortnroSd4(long[] jArr, int i2, int i3) {
        int m519partitionnroSd4 = m519partitionnroSd4(jArr, i2, i3);
        int i4 = m519partitionnroSd4 - 1;
        if (i2 < i4) {
            m523quickSortnroSd4(jArr, i2, i4);
        }
        if (m519partitionnroSd4 < i3) {
            m523quickSortnroSd4(jArr, m519partitionnroSd4, i3);
        }
    }

    @ExperimentalUnsignedTypes
    /* renamed from: quickSort-4UcCI2c  reason: not valid java name */
    private static final void m524quickSort4UcCI2c(byte[] bArr, int i2, int i3) {
        int m520partition4UcCI2c = m520partition4UcCI2c(bArr, i2, i3);
        int i4 = m520partition4UcCI2c - 1;
        if (i2 < i4) {
            m524quickSort4UcCI2c(bArr, i2, i4);
        }
        if (m520partition4UcCI2c < i3) {
            m524quickSort4UcCI2c(bArr, m520partition4UcCI2c, i3);
        }
    }

    @ExperimentalUnsignedTypes
    /* renamed from: quickSort-Aa5vz7o  reason: not valid java name */
    private static final void m525quickSortAa5vz7o(short[] sArr, int i2, int i3) {
        int m521partitionAa5vz7o = m521partitionAa5vz7o(sArr, i2, i3);
        int i4 = m521partitionAa5vz7o - 1;
        if (i2 < i4) {
            m525quickSortAa5vz7o(sArr, i2, i4);
        }
        if (m521partitionAa5vz7o < i3) {
            m525quickSortAa5vz7o(sArr, m521partitionAa5vz7o, i3);
        }
    }

    @ExperimentalUnsignedTypes
    /* renamed from: quickSort-oBK06Vg  reason: not valid java name */
    private static final void m526quickSortoBK06Vg(int[] iArr, int i2, int i3) {
        int m522partitionoBK06Vg = m522partitionoBK06Vg(iArr, i2, i3);
        int i4 = m522partitionoBK06Vg - 1;
        if (i2 < i4) {
            m526quickSortoBK06Vg(iArr, i2, i4);
        }
        if (m522partitionoBK06Vg < i3) {
            m526quickSortoBK06Vg(iArr, m522partitionoBK06Vg, i3);
        }
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray--ajY-9A  reason: not valid java name */
    public static final void m527sortArrayajY9A(@NotNull int[] iArr) {
        m526quickSortoBK06Vg(iArr, 0, UIntArray.m337getSizeimpl(iArr) - 1);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray-GBYM_sE  reason: not valid java name */
    public static final void m528sortArrayGBYM_sE(@NotNull byte[] bArr) {
        m524quickSort4UcCI2c(bArr, 0, UByteArray.m268getSizeimpl(bArr) - 1);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray-QwZRm1k  reason: not valid java name */
    public static final void m529sortArrayQwZRm1k(@NotNull long[] jArr) {
        m523quickSortnroSd4(jArr, 0, ULongArray.m406getSizeimpl(jArr) - 1);
    }

    @ExperimentalUnsignedTypes
    /* renamed from: sortArray-rL5Bavg  reason: not valid java name */
    public static final void m530sortArrayrL5Bavg(@NotNull short[] sArr) {
        m525quickSortAa5vz7o(sArr, 0, UShortArray.m501getSizeimpl(sArr) - 1);
    }
}
