package kotlin.collections;

import java.util.Arrays;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.jvm.JvmStatic;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;

@Deprecated(level = DeprecationLevel.HIDDEN, message = "Provided for binary compatibility")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\b\f\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b=\u0010>J\u001e\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\u0004\u001a\u00020\t*\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000bJ\u001e\u0010\u0004\u001a\u00020\r*\u00020\f2\u0006\u0010\u0004\u001a\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u001e\u0010\u0004\u001a\u00020\u0011*\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0018\u001a\u00020\u0015*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0002H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u0018\u001a\u00020\u0015*\u00020\b2\u0006\u0010\u0014\u001a\u00020\bH\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u001f\u0010\u0018\u001a\u00020\u0015*\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u001f\u0010\u0018\u001a\u00020\u0015*\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0010H\u0087\u0004\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0016\u0010\"\u001a\u00020\u001f*\u00020\u0002H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010!J\u0016\u0010\"\u001a\u00020\u001f*\u00020\bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b#\u0010$J\u0016\u0010\"\u001a\u00020\u001f*\u00020\fH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010&J\u0016\u0010\"\u001a\u00020\u001f*\u00020\u0010H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b'\u0010(J\u0016\u0010,\u001a\u00020)*\u00020\u0002H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b*\u0010+J\u0016\u0010,\u001a\u00020)*\u00020\bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b-\u0010.J\u0016\u0010,\u001a\u00020)*\u00020\fH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b/\u00100J\u0016\u0010,\u001a\u00020)*\u00020\u0010H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b1\u00102J\u001c\u00106\u001a\b\u0012\u0004\u0012\u00020\u000503*\u00020\u0002H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b4\u00105J\u001c\u00106\u001a\b\u0012\u0004\u0012\u00020\t03*\u00020\bH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b7\u00108J\u001c\u00106\u001a\b\u0012\u0004\u0012\u00020\r03*\u00020\fH\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b9\u0010:J\u001c\u00106\u001a\b\u0012\u0004\u0012\u00020\u001103*\u00020\u0010H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b;\u0010<\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006?"}, d2 = {"Lkotlin/collections/UArraysKt;", "", "Lkotlin/UIntArray;", "Lkotlin/random/Random;", "random", "Lkotlin/UInt;", "random-2D5oskM", "([ILkotlin/random/Random;)I", "Lkotlin/ULongArray;", "Lkotlin/ULong;", "random-JzugnMA", "([JLkotlin/random/Random;)J", "Lkotlin/UByteArray;", "Lkotlin/UByte;", "random-oSF2wD8", "([BLkotlin/random/Random;)B", "Lkotlin/UShortArray;", "Lkotlin/UShort;", "random-s5X_as8", "([SLkotlin/random/Random;)S", "other", "", "contentEquals-ctEhBpI", "([I[I)Z", "contentEquals", "contentEquals-us8wMrg", "([J[J)Z", "contentEquals-kdPth3s", "([B[B)Z", "contentEquals-mazbYpA", "([S[S)Z", "", "contentHashCode--ajY-9A", "([I)I", "contentHashCode", "contentHashCode-QwZRm1k", "([J)I", "contentHashCode-GBYM_sE", "([B)I", "contentHashCode-rL5Bavg", "([S)I", "", "contentToString--ajY-9A", "([I)Ljava/lang/String;", "contentToString", "contentToString-QwZRm1k", "([J)Ljava/lang/String;", "contentToString-GBYM_sE", "([B)Ljava/lang/String;", "contentToString-rL5Bavg", "([S)Ljava/lang/String;", "", "toTypedArray--ajY-9A", "([I)[Lkotlin/UInt;", "toTypedArray", "toTypedArray-QwZRm1k", "([J)[Lkotlin/ULong;", "toTypedArray-GBYM_sE", "([B)[Lkotlin/UByte;", "toTypedArray-rL5Bavg", "([S)[Lkotlin/UShort;", "<init>", "()V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class UArraysKt {
    public static final UArraysKt INSTANCE = new UArraysKt();

    private UArraysKt() {
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-ctEhBpI  reason: not valid java name */
    public static final boolean m531contentEqualsctEhBpI(@NotNull int[] iArr, @NotNull int[] iArr2) {
        return Arrays.equals(iArr, iArr2);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-kdPth3s  reason: not valid java name */
    public static final boolean m532contentEqualskdPth3s(@NotNull byte[] bArr, @NotNull byte[] bArr2) {
        return Arrays.equals(bArr, bArr2);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-mazbYpA  reason: not valid java name */
    public static final boolean m533contentEqualsmazbYpA(@NotNull short[] sArr, @NotNull short[] sArr2) {
        return Arrays.equals(sArr, sArr2);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentEquals-us8wMrg  reason: not valid java name */
    public static final boolean m534contentEqualsus8wMrg(@NotNull long[] jArr, @NotNull long[] jArr2) {
        return Arrays.equals(jArr, jArr2);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode--ajY-9A  reason: not valid java name */
    public static final int m535contentHashCodeajY9A(@NotNull int[] iArr) {
        return Arrays.hashCode(iArr);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode-GBYM_sE  reason: not valid java name */
    public static final int m536contentHashCodeGBYM_sE(@NotNull byte[] bArr) {
        return Arrays.hashCode(bArr);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode-QwZRm1k  reason: not valid java name */
    public static final int m537contentHashCodeQwZRm1k(@NotNull long[] jArr) {
        return Arrays.hashCode(jArr);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: contentHashCode-rL5Bavg  reason: not valid java name */
    public static final int m538contentHashCoderL5Bavg(@NotNull short[] sArr) {
        return Arrays.hashCode(sArr);
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString--ajY-9A  reason: not valid java name */
    public static final String m539contentToStringajY9A(@NotNull int[] iArr) {
        String joinToString$default;
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(UIntArray.m329boximpl(iArr), ", ", "[", "]", 0, null, null, 56, null);
        return joinToString$default;
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString-GBYM_sE  reason: not valid java name */
    public static final String m540contentToStringGBYM_sE(@NotNull byte[] bArr) {
        String joinToString$default;
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(UByteArray.m260boximpl(bArr), ", ", "[", "]", 0, null, null, 56, null);
        return joinToString$default;
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString-QwZRm1k  reason: not valid java name */
    public static final String m541contentToStringQwZRm1k(@NotNull long[] jArr) {
        String joinToString$default;
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(ULongArray.m398boximpl(jArr), ", ", "[", "]", 0, null, null, 56, null);
        return joinToString$default;
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: contentToString-rL5Bavg  reason: not valid java name */
    public static final String m542contentToStringrL5Bavg(@NotNull short[] sArr) {
        String joinToString$default;
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(UShortArray.m493boximpl(sArr), ", ", "[", "]", 0, null, null, 56, null);
        return joinToString$default;
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: random-2D5oskM  reason: not valid java name */
    public static final int m543random2D5oskM(@NotNull int[] iArr, @NotNull Random random) {
        if (!UIntArray.m339isEmptyimpl(iArr)) {
            return UIntArray.m336getimpl(iArr, random.nextInt(UIntArray.m337getSizeimpl(iArr)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: random-JzugnMA  reason: not valid java name */
    public static final long m544randomJzugnMA(@NotNull long[] jArr, @NotNull Random random) {
        if (!ULongArray.m408isEmptyimpl(jArr)) {
            return ULongArray.m405getimpl(jArr, random.nextInt(ULongArray.m406getSizeimpl(jArr)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: random-oSF2wD8  reason: not valid java name */
    public static final byte m545randomoSF2wD8(@NotNull byte[] bArr, @NotNull Random random) {
        if (!UByteArray.m270isEmptyimpl(bArr)) {
            return UByteArray.m267getimpl(bArr, random.nextInt(UByteArray.m268getSizeimpl(bArr)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    /* renamed from: random-s5X_as8  reason: not valid java name */
    public static final short m546randoms5X_as8(@NotNull short[] sArr, @NotNull Random random) {
        if (!UShortArray.m503isEmptyimpl(sArr)) {
            return UShortArray.m500getimpl(sArr, random.nextInt(UShortArray.m501getSizeimpl(sArr)));
        }
        throw new NoSuchElementException("Array is empty.");
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray--ajY-9A  reason: not valid java name */
    public static final UInt[] m547toTypedArrayajY9A(@NotNull int[] iArr) {
        int m337getSizeimpl = UIntArray.m337getSizeimpl(iArr);
        UInt[] uIntArr = new UInt[m337getSizeimpl];
        for (int i2 = 0; i2 < m337getSizeimpl; i2++) {
            uIntArr[i2] = UInt.m279boximpl(UIntArray.m336getimpl(iArr, i2));
        }
        return uIntArr;
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray-GBYM_sE  reason: not valid java name */
    public static final UByte[] m548toTypedArrayGBYM_sE(@NotNull byte[] bArr) {
        int m268getSizeimpl = UByteArray.m268getSizeimpl(bArr);
        UByte[] uByteArr = new UByte[m268getSizeimpl];
        for (int i2 = 0; i2 < m268getSizeimpl; i2++) {
            uByteArr[i2] = UByte.m212boximpl(UByteArray.m267getimpl(bArr, i2));
        }
        return uByteArr;
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray-QwZRm1k  reason: not valid java name */
    public static final ULong[] m549toTypedArrayQwZRm1k(@NotNull long[] jArr) {
        int m406getSizeimpl = ULongArray.m406getSizeimpl(jArr);
        ULong[] uLongArr = new ULong[m406getSizeimpl];
        for (int i2 = 0; i2 < m406getSizeimpl; i2++) {
            uLongArr[i2] = ULong.m348boximpl(ULongArray.m405getimpl(jArr, i2));
        }
        return uLongArr;
    }

    @JvmStatic
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toTypedArray-rL5Bavg  reason: not valid java name */
    public static final UShort[] m550toTypedArrayrL5Bavg(@NotNull short[] sArr) {
        int m501getSizeimpl = UShortArray.m501getSizeimpl(sArr);
        UShort[] uShortArr = new UShort[m501getSizeimpl];
        for (int i2 = 0; i2 < m501getSizeimpl; i2++) {
            uShortArr[i2] = UShort.m445boximpl(UShortArray.m500getimpl(sArr, i2));
        }
        return uShortArr;
    }
}
