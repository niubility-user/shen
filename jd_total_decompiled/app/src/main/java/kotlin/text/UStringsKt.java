package kotlin.text;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\u001a\u001e\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a\u001e\u0010\u0006\u001a\u00020\u0003*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\b\u0010\t\u001a\u001e\u0010\u0006\u001a\u00020\u0003*\u00020\n2\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\f\u001a\u001e\u0010\u0006\u001a\u00020\u0003*\u00020\r2\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a\u0016\u0010\u0010\u001a\u00020\u0000*\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0011\u001a\u001e\u0010\u0010\u001a\u00020\u0000*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0012\u001a\u0016\u0010\u0013\u001a\u00020\u0007*\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0014\u001a\u001e\u0010\u0013\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0013\u0010\u0015\u001a\u0016\u0010\u0016\u001a\u00020\n*\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0016\u0010\u0017\u001a\u001e\u0010\u0016\u001a\u00020\n*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0016\u0010\u0018\u001a\u0016\u0010\u0019\u001a\u00020\r*\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001a\u001a\u001e\u0010\u0019\u001a\u00020\r*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001b\u001a\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u0000*\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001c\u0010\u001d\u001a \u0010\u001c\u001a\u0004\u0018\u00010\u0000*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001c\u0010\u001e\u001a\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u0007*\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001f\u0010 \u001a \u0010\u001f\u001a\u0004\u0018\u00010\u0007*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001f\u0010!\u001a\u0018\u0010\"\u001a\u0004\u0018\u00010\n*\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\"\u0010#\u001a \u0010\"\u001a\u0004\u0018\u00010\n*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\"\u0010$\u001a\u0018\u0010%\u001a\u0004\u0018\u00010\r*\u00020\u0003H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010&\u001a \u0010%\u001a\u0004\u0018\u00010\r*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010'\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006("}, d2 = {"Lkotlin/UByte;", "", "radix", "", "toString-LxnNnR4", "(BI)Ljava/lang/String;", "toString", "Lkotlin/UShort;", "toString-olVBNx4", "(SI)Ljava/lang/String;", "Lkotlin/UInt;", "toString-V7xB4Y4", "(II)Ljava/lang/String;", "Lkotlin/ULong;", "toString-JSWoG40", "(JI)Ljava/lang/String;", "toUByte", "(Ljava/lang/String;)B", "(Ljava/lang/String;I)B", "toUShort", "(Ljava/lang/String;)S", "(Ljava/lang/String;I)S", "toUInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "toULong", "(Ljava/lang/String;)J", "(Ljava/lang/String;I)J", "toUByteOrNull", "(Ljava/lang/String;)Lkotlin/UByte;", "(Ljava/lang/String;I)Lkotlin/UByte;", "toUShortOrNull", "(Ljava/lang/String;)Lkotlin/UShort;", "(Ljava/lang/String;I)Lkotlin/UShort;", "toUIntOrNull", "(Ljava/lang/String;)Lkotlin/UInt;", "(Ljava/lang/String;I)Lkotlin/UInt;", "toULongOrNull", "(Ljava/lang/String;)Lkotlin/ULong;", "(Ljava/lang/String;I)Lkotlin/ULong;", "kotlin-stdlib"}, k = 2, mv = {1, 4, 0})
@JvmName(name = "UStringsKt")
/* loaded from: classes11.dex */
public final class UStringsKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toString-JSWoG40  reason: not valid java name */
    public static final String m1158toStringJSWoG40(long j2, int i2) {
        int checkRadix;
        checkRadix = CharsKt__CharJVMKt.checkRadix(i2);
        return UnsignedKt.ulongToString(j2, checkRadix);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toString-LxnNnR4  reason: not valid java name */
    public static final String m1159toStringLxnNnR4(byte b, int i2) {
        int checkRadix;
        checkRadix = CharsKt__CharJVMKt.checkRadix(i2);
        String num = Integer.toString(b & 255, checkRadix);
        Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
        return num;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toString-V7xB4Y4  reason: not valid java name */
    public static final String m1160toStringV7xB4Y4(int i2, int i3) {
        int checkRadix;
        checkRadix = CharsKt__CharJVMKt.checkRadix(i3);
        String l2 = Long.toString(i2 & 4294967295L, checkRadix);
        Intrinsics.checkExpressionValueIsNotNull(l2, "java.lang.Long.toString(this, checkRadix(radix))");
        return l2;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* renamed from: toString-olVBNx4  reason: not valid java name */
    public static final String m1161toStringolVBNx4(short s, int i2) {
        int checkRadix;
        checkRadix = CharsKt__CharJVMKt.checkRadix(i2);
        String num = Integer.toString(s & 65535, checkRadix);
        Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
        return num;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final byte toUByte(@NotNull String str) {
        UByte uByteOrNull = toUByteOrNull(str);
        if (uByteOrNull != null) {
            return uByteOrNull.getData();
        }
        StringsKt__StringNumberConversionsKt.numberFormatError(str);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UByte toUByteOrNull(@NotNull String str) {
        return toUByteOrNull(str, 10);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final int toUInt(@NotNull String str) {
        UInt uIntOrNull = toUIntOrNull(str);
        if (uIntOrNull != null) {
            return uIntOrNull.getData();
        }
        StringsKt__StringNumberConversionsKt.numberFormatError(str);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UInt toUIntOrNull(@NotNull String str) {
        return toUIntOrNull(str, 10);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final long toULong(@NotNull String str) {
        ULong uLongOrNull = toULongOrNull(str);
        if (uLongOrNull != null) {
            return uLongOrNull.getData();
        }
        StringsKt__StringNumberConversionsKt.numberFormatError(str);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final ULong toULongOrNull(@NotNull String str) {
        return toULongOrNull(str, 10);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final short toUShort(@NotNull String str) {
        UShort uShortOrNull = toUShortOrNull(str);
        if (uShortOrNull != null) {
            return uShortOrNull.getData();
        }
        StringsKt__StringNumberConversionsKt.numberFormatError(str);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UShort toUShortOrNull(@NotNull String str) {
        return toUShortOrNull(str, 10);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final byte toUByte(@NotNull String str, int i2) {
        UByte uByteOrNull = toUByteOrNull(str, i2);
        if (uByteOrNull != null) {
            return uByteOrNull.getData();
        }
        StringsKt__StringNumberConversionsKt.numberFormatError(str);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UByte toUByteOrNull(@NotNull String str, int i2) {
        UInt uIntOrNull = toUIntOrNull(str, i2);
        if (uIntOrNull != null) {
            int data = uIntOrNull.getData();
            if (UnsignedKt.uintCompare(data, UInt.m285constructorimpl(255)) > 0) {
                return null;
            }
            return UByte.m212boximpl(UByte.m218constructorimpl((byte) data));
        }
        return null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final int toUInt(@NotNull String str, int i2) {
        UInt uIntOrNull = toUIntOrNull(str, i2);
        if (uIntOrNull != null) {
            return uIntOrNull.getData();
        }
        StringsKt__StringNumberConversionsKt.numberFormatError(str);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UInt toUIntOrNull(@NotNull String str, int i2) {
        CharsKt__CharJVMKt.checkRadix(i2);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        int i3 = 0;
        char charAt = str.charAt(0);
        int i4 = 1;
        if (charAt >= '0') {
            i4 = 0;
        } else if (length == 1 || charAt != '+') {
            return null;
        }
        int m285constructorimpl = UInt.m285constructorimpl(i2);
        int i5 = 119304647;
        while (i4 < length) {
            int digitOf = CharsKt__CharJVMKt.digitOf(str.charAt(i4), i2);
            if (digitOf < 0) {
                return null;
            }
            if (UnsignedKt.uintCompare(i3, i5) > 0) {
                if (i5 == 119304647) {
                    i5 = UnsignedKt.m511uintDivideJ1ME1BU(-1, m285constructorimpl);
                    if (UnsignedKt.uintCompare(i3, i5) > 0) {
                    }
                }
                return null;
            }
            int m285constructorimpl2 = UInt.m285constructorimpl(i3 * m285constructorimpl);
            int m285constructorimpl3 = UInt.m285constructorimpl(UInt.m285constructorimpl(digitOf) + m285constructorimpl2);
            if (UnsignedKt.uintCompare(m285constructorimpl3, m285constructorimpl2) < 0) {
                return null;
            }
            i4++;
            i3 = m285constructorimpl3;
        }
        return UInt.m279boximpl(i3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final long toULong(@NotNull String str, int i2) {
        ULong uLongOrNull = toULongOrNull(str, i2);
        if (uLongOrNull != null) {
            return uLongOrNull.getData();
        }
        StringsKt__StringNumberConversionsKt.numberFormatError(str);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final ULong toULongOrNull(@NotNull String str, int i2) {
        CharsKt__CharJVMKt.checkRadix(i2);
        int length = str.length();
        if (length == 0) {
            return null;
        }
        long j2 = -1;
        int i3 = 0;
        char charAt = str.charAt(0);
        if (charAt < '0') {
            if (length == 1 || charAt != '+') {
                return null;
            }
            i3 = 1;
        }
        long m354constructorimpl = ULong.m354constructorimpl(i2);
        long j3 = 0;
        long j4 = 512409557603043100L;
        while (i3 < length) {
            if (CharsKt__CharJVMKt.digitOf(str.charAt(i3), i2) < 0) {
                return null;
            }
            if (UnsignedKt.ulongCompare(j3, j4) > 0) {
                if (j4 == 512409557603043100L) {
                    j4 = UnsignedKt.m513ulongDivideeb3DHEI(j2, m354constructorimpl);
                    if (UnsignedKt.ulongCompare(j3, j4) > 0) {
                    }
                }
                return null;
            }
            long m354constructorimpl2 = ULong.m354constructorimpl(j3 * m354constructorimpl);
            long m354constructorimpl3 = ULong.m354constructorimpl(ULong.m354constructorimpl(UInt.m285constructorimpl(r15) & 4294967295L) + m354constructorimpl2);
            if (UnsignedKt.ulongCompare(m354constructorimpl3, m354constructorimpl2) < 0) {
                return null;
            }
            i3++;
            j3 = m354constructorimpl3;
            j2 = -1;
        }
        return ULong.m348boximpl(j3);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final short toUShort(@NotNull String str, int i2) {
        UShort uShortOrNull = toUShortOrNull(str, i2);
        if (uShortOrNull != null) {
            return uShortOrNull.getData();
        }
        StringsKt__StringNumberConversionsKt.numberFormatError(str);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UShort toUShortOrNull(@NotNull String str, int i2) {
        UInt uIntOrNull = toUIntOrNull(str, i2);
        if (uIntOrNull != null) {
            int data = uIntOrNull.getData();
            if (UnsignedKt.uintCompare(data, UInt.m285constructorimpl(65535)) > 0) {
                return null;
            }
            return UShort.m445boximpl(UShort.m451constructorimpl((short) data));
        }
        return null;
    }
}
