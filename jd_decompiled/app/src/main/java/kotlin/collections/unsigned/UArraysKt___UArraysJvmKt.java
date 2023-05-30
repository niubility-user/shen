package kotlin.collections.unsigned;

import java.util.List;
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
import kotlin.UnsignedKt;
import kotlin.collections.AbstractList;
import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0016\u001a\u001f\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a\u001f\u0010\u0006\u001a\u00020\b*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u001a\u001f\u0010\u0006\u001a\u00020\f*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\u000e\u001a\u001f\u0010\u0006\u001a\u00020\u0010*\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0011\u0010\u0012\u001a\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013*\u00020\u0000H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0013*\u00020\u0007H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u0013*\u00020\u000bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001a\u001a\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\u0013*\u00020\u000fH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a2\u0010\"\u001a\u00020\u0001*\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00032\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010!\u001a2\u0010\"\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u001d\u001a\u00020\b2\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010$\u001a2\u0010\"\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\f2\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010&\u001a2\u0010\"\u001a\u00020\u0001*\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u00102\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010(\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006)"}, d2 = {"Lkotlin/UIntArray;", "", "index", "Lkotlin/UInt;", "elementAt-qFRl0hI", "([II)I", "elementAt", "Lkotlin/ULongArray;", "Lkotlin/ULong;", "elementAt-r7IrZao", "([JI)J", "Lkotlin/UByteArray;", "Lkotlin/UByte;", "elementAt-PpDY95g", "([BI)B", "Lkotlin/UShortArray;", "Lkotlin/UShort;", "elementAt-nggk6HY", "([SI)S", "", "asList--ajY-9A", "([I)Ljava/util/List;", "asList", "asList-QwZRm1k", "([J)Ljava/util/List;", "asList-GBYM_sE", "([B)Ljava/util/List;", "asList-rL5Bavg", "([S)Ljava/util/List;", "element", "fromIndex", "toIndex", "binarySearch-2fe2U9s", "([IIII)I", "binarySearch", "binarySearch-K6DWlUc", "([JJII)I", "binarySearch-WpHrYlw", "([BBII)I", "binarySearch-EtDCXyQ", "([SSII)I", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, pn = "kotlin.collections", xs = "kotlin/collections/unsigned/UArraysKt")
/* loaded from: classes11.dex */
public class UArraysKt___UArraysJvmKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: asList--ajY-9A */
    public static final List<UInt> m559asListajY9A(@NotNull int[] iArr) {
        return new UArraysKt___UArraysJvmKt$asList$1(iArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: asList-GBYM_sE */
    public static final List<UByte> m560asListGBYM_sE(@NotNull byte[] bArr) {
        return new UArraysKt___UArraysJvmKt$asList$3(bArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: asList-QwZRm1k */
    public static final List<ULong> m561asListQwZRm1k(@NotNull long[] jArr) {
        return new UArraysKt___UArraysJvmKt$asList$2(jArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: asList-rL5Bavg */
    public static final List<UShort> m562asListrL5Bavg(@NotNull short[] sArr) {
        return new UArraysKt___UArraysJvmKt$asList$4(sArr);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: binarySearch-2fe2U9s */
    public static final int m563binarySearch2fe2U9s(@NotNull int[] iArr, int i2, int i3, int i4) {
        AbstractList.INSTANCE.checkRangeIndexes$kotlin_stdlib(i3, i4, UIntArray.m337getSizeimpl(iArr));
        int i5 = i4 - 1;
        while (i3 <= i5) {
            int i6 = (i3 + i5) >>> 1;
            int uintCompare = UnsignedKt.uintCompare(iArr[i6], i2);
            if (uintCompare < 0) {
                i3 = i6 + 1;
            } else if (uintCompare <= 0) {
                return i6;
            } else {
                i5 = i6 - 1;
            }
        }
        return -(i3 + 1);
    }

    /* renamed from: binarySearch-2fe2U9s$default */
    public static /* synthetic */ int m564binarySearch2fe2U9s$default(int[] iArr, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            i3 = 0;
        }
        if ((i5 & 4) != 0) {
            i4 = UIntArray.m337getSizeimpl(iArr);
        }
        return m563binarySearch2fe2U9s(iArr, i2, i3, i4);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: binarySearch-EtDCXyQ */
    public static final int m565binarySearchEtDCXyQ(@NotNull short[] sArr, short s, int i2, int i3) {
        AbstractList.INSTANCE.checkRangeIndexes$kotlin_stdlib(i2, i3, UShortArray.m501getSizeimpl(sArr));
        int i4 = s & 65535;
        int i5 = i3 - 1;
        while (i2 <= i5) {
            int i6 = (i2 + i5) >>> 1;
            int uintCompare = UnsignedKt.uintCompare(sArr[i6], i4);
            if (uintCompare < 0) {
                i2 = i6 + 1;
            } else if (uintCompare <= 0) {
                return i6;
            } else {
                i5 = i6 - 1;
            }
        }
        return -(i2 + 1);
    }

    /* renamed from: binarySearch-EtDCXyQ$default */
    public static /* synthetic */ int m566binarySearchEtDCXyQ$default(short[] sArr, short s, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = UShortArray.m501getSizeimpl(sArr);
        }
        return m565binarySearchEtDCXyQ(sArr, s, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: binarySearch-K6DWlUc */
    public static final int m567binarySearchK6DWlUc(@NotNull long[] jArr, long j2, int i2, int i3) {
        AbstractList.INSTANCE.checkRangeIndexes$kotlin_stdlib(i2, i3, ULongArray.m406getSizeimpl(jArr));
        int i4 = i3 - 1;
        while (i2 <= i4) {
            int i5 = (i2 + i4) >>> 1;
            int ulongCompare = UnsignedKt.ulongCompare(jArr[i5], j2);
            if (ulongCompare < 0) {
                i2 = i5 + 1;
            } else if (ulongCompare <= 0) {
                return i5;
            } else {
                i4 = i5 - 1;
            }
        }
        return -(i2 + 1);
    }

    /* renamed from: binarySearch-K6DWlUc$default */
    public static /* synthetic */ int m568binarySearchK6DWlUc$default(long[] jArr, long j2, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = ULongArray.m406getSizeimpl(jArr);
        }
        return m567binarySearchK6DWlUc(jArr, j2, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    /* renamed from: binarySearch-WpHrYlw */
    public static final int m569binarySearchWpHrYlw(@NotNull byte[] bArr, byte b, int i2, int i3) {
        AbstractList.INSTANCE.checkRangeIndexes$kotlin_stdlib(i2, i3, UByteArray.m268getSizeimpl(bArr));
        int i4 = b & 255;
        int i5 = i3 - 1;
        while (i2 <= i5) {
            int i6 = (i2 + i5) >>> 1;
            int uintCompare = UnsignedKt.uintCompare(bArr[i6], i4);
            if (uintCompare < 0) {
                i2 = i6 + 1;
            } else if (uintCompare <= 0) {
                return i6;
            } else {
                i5 = i6 - 1;
            }
        }
        return -(i2 + 1);
    }

    /* renamed from: binarySearch-WpHrYlw$default */
    public static /* synthetic */ int m570binarySearchWpHrYlw$default(byte[] bArr, byte b, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = 0;
        }
        if ((i4 & 4) != 0) {
            i3 = UByteArray.m268getSizeimpl(bArr);
        }
        return m569binarySearchWpHrYlw(bArr, b, i2, i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAt-PpDY95g */
    private static final byte m571elementAtPpDY95g(@NotNull byte[] bArr, int i2) {
        return UByteArray.m267getimpl(bArr, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAt-nggk6HY */
    private static final short m572elementAtnggk6HY(@NotNull short[] sArr, int i2) {
        return UShortArray.m500getimpl(sArr, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAt-qFRl0hI */
    private static final int m573elementAtqFRl0hI(@NotNull int[] iArr, int i2) {
        return UIntArray.m336getimpl(iArr, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @InlineOnly
    /* renamed from: elementAt-r7IrZao */
    private static final long m574elementAtr7IrZao(@NotNull long[] jArr, int i2) {
        return ULongArray.m405getimpl(jArr, i2);
    }
}
