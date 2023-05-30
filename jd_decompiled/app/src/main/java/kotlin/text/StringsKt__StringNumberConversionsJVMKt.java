package kotlin.text;

import com.jd.dynamic.base.interfaces.IExceptionHandler;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0010\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001c\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a\u001c\u0010\u0004\u001a\u00020\u0003*\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00a2\u0006\u0004\b\u0004\u0010\u0007\u001a\u001c\u0010\u0004\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00a2\u0006\u0004\b\u0004\u0010\b\u001a\u001c\u0010\u0004\u001a\u00020\u0003*\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00a2\u0006\u0004\b\u0004\u0010\n\u001a\u0014\u0010\f\u001a\u00020\u000b*\u00020\u0003H\u0087\b\u00a2\u0006\u0004\b\f\u0010\r\u001a\u0014\u0010\u000e\u001a\u00020\u0000*\u00020\u0003H\u0087\b\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a\u001c\u0010\u000e\u001a\u00020\u0000*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00a2\u0006\u0004\b\u000e\u0010\u0010\u001a\u0014\u0010\u0011\u001a\u00020\u0006*\u00020\u0003H\u0087\b\u00a2\u0006\u0004\b\u0011\u0010\u0012\u001a\u001c\u0010\u0011\u001a\u00020\u0006*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00a2\u0006\u0004\b\u0011\u0010\u0013\u001a\u0014\u0010\u0014\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u00a2\u0006\u0004\b\u0014\u0010\u0015\u001a\u001c\u0010\u0014\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00a2\u0006\u0004\b\u0014\u0010\u0016\u001a\u0014\u0010\u0017\u001a\u00020\t*\u00020\u0003H\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a\u001c\u0010\u0017\u001a\u00020\t*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\u0019\u001a\u0014\u0010\u001b\u001a\u00020\u001a*\u00020\u0003H\u0087\b\u00a2\u0006\u0004\b\u001b\u0010\u001c\u001a\u0014\u0010\u001e\u001a\u00020\u001d*\u00020\u0003H\u0087\b\u00a2\u0006\u0004\b\u001e\u0010\u001f\u001a\u0015\u0010 \u001a\u0004\u0018\u00010\u001a*\u00020\u0003H\u0007\u00a2\u0006\u0004\b \u0010!\u001a\u0015\u0010\"\u001a\u0004\u0018\u00010\u001d*\u00020\u0003H\u0007\u00a2\u0006\u0004\b\"\u0010#\u001a\u0014\u0010%\u001a\u00020$*\u00020\u0003H\u0087\b\u00a2\u0006\u0004\b%\u0010&\u001a\u001c\u0010%\u001a\u00020$*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\b\u00a2\u0006\u0004\b%\u0010'\u001a\u0015\u0010(\u001a\u0004\u0018\u00010$*\u00020\u0003H\u0007\u00a2\u0006\u0004\b(\u0010&\u001a\u001d\u0010(\u001a\u0004\u0018\u00010$*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0001H\u0007\u00a2\u0006\u0004\b(\u0010'\u001a\u0014\u0010*\u001a\u00020)*\u00020\u0003H\u0087\b\u00a2\u0006\u0004\b*\u0010+\u001a\u001c\u0010*\u001a\u00020)*\u00020\u00032\u0006\u0010-\u001a\u00020,H\u0087\b\u00a2\u0006\u0004\b*\u0010.\u001a\u0015\u0010/\u001a\u0004\u0018\u00010)*\u00020\u0003H\u0007\u00a2\u0006\u0004\b/\u0010+\u001a\u001d\u0010/\u001a\u0004\u0018\u00010)*\u00020\u00032\u0006\u0010-\u001a\u00020,H\u0007\u00a2\u0006\u0004\b/\u0010.\u001a4\u00106\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u001002\u0006\u00101\u001a\u00020\u00032\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00028\u000002H\u0082\b\u00a2\u0006\u0004\b4\u00105\u00a8\u00067"}, d2 = {"", "", "radix", "", "toString", "(BI)Ljava/lang/String;", "", "(SI)Ljava/lang/String;", "(II)Ljava/lang/String;", "", "(JI)Ljava/lang/String;", "", "toBoolean", "(Ljava/lang/String;)Z", "toByte", "(Ljava/lang/String;)B", "(Ljava/lang/String;I)B", "toShort", "(Ljava/lang/String;)S", "(Ljava/lang/String;I)S", "toInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "toLong", "(Ljava/lang/String;)J", "(Ljava/lang/String;I)J", "", "toFloat", "(Ljava/lang/String;)F", "", "toDouble", "(Ljava/lang/String;)D", "toFloatOrNull", "(Ljava/lang/String;)Ljava/lang/Float;", "toDoubleOrNull", "(Ljava/lang/String;)Ljava/lang/Double;", "Ljava/math/BigInteger;", "toBigInteger", "(Ljava/lang/String;)Ljava/math/BigInteger;", "(Ljava/lang/String;I)Ljava/math/BigInteger;", "toBigIntegerOrNull", "Ljava/math/BigDecimal;", "toBigDecimal", "(Ljava/lang/String;)Ljava/math/BigDecimal;", "Ljava/math/MathContext;", "mathContext", "(Ljava/lang/String;Ljava/math/MathContext;)Ljava/math/BigDecimal;", "toBigDecimalOrNull", "T", "str", "Lkotlin/Function1;", IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "screenFloatValue$StringsKt__StringNumberConversionsJVMKt", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "screenFloatValue", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/text/StringsKt")
/* loaded from: classes.dex */
public class StringsKt__StringNumberConversionsJVMKt extends StringsKt__StringBuilderKt {
    private static final <T> T screenFloatValue$StringsKt__StringNumberConversionsJVMKt(String str, Function1<? super String, ? extends T> function1) {
        try {
            if (ScreenFloatValueRegEx.value.matches(str)) {
                return function1.invoke(str);
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(@NotNull String str) {
        return new BigDecimal(str);
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static BigDecimal toBigDecimalOrNull(@NotNull String str) {
        try {
            if (ScreenFloatValueRegEx.value.matches(str)) {
                return new BigDecimal(str);
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(@NotNull String str) {
        return new BigInteger(str);
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigInteger toBigIntegerOrNull(@NotNull String str) {
        return toBigIntegerOrNull(str, 10);
    }

    @InlineOnly
    private static final boolean toBoolean(@NotNull String str) {
        return Boolean.parseBoolean(str);
    }

    @InlineOnly
    private static final byte toByte(@NotNull String str) {
        return Byte.parseByte(str);
    }

    @InlineOnly
    private static final double toDouble(@NotNull String str) {
        return Double.parseDouble(str);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Double toDoubleOrNull(@NotNull String str) {
        try {
            if (ScreenFloatValueRegEx.value.matches(str)) {
                return Double.valueOf(Double.parseDouble(str));
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @InlineOnly
    private static final float toFloat(@NotNull String str) {
        return Float.parseFloat(str);
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final Float toFloatOrNull(@NotNull String str) {
        try {
            if (ScreenFloatValueRegEx.value.matches(str)) {
                return Float.valueOf(Float.parseFloat(str));
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @InlineOnly
    private static final int toInt(@NotNull String str) {
        return Integer.parseInt(str);
    }

    @InlineOnly
    private static final long toLong(@NotNull String str) {
        return Long.parseLong(str);
    }

    @InlineOnly
    private static final short toShort(@NotNull String str) {
        return Short.parseShort(str);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(byte b, int i2) {
        int checkRadix;
        int checkRadix2;
        checkRadix = CharsKt__CharJVMKt.checkRadix(i2);
        checkRadix2 = CharsKt__CharJVMKt.checkRadix(checkRadix);
        String num = Integer.toString(b, checkRadix2);
        Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
        return num;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(@NotNull String str, MathContext mathContext) {
        return new BigDecimal(str, mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(@NotNull String str, int i2) {
        int checkRadix;
        checkRadix = CharsKt__CharJVMKt.checkRadix(i2);
        return new BigInteger(str, checkRadix);
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigInteger toBigIntegerOrNull(@NotNull String str, int i2) {
        int checkRadix;
        CharsKt__CharJVMKt.checkRadix(i2);
        int length = str.length();
        if (length != 0) {
            if (length != 1) {
                for (int i3 = str.charAt(0) == '-' ? 1 : 0; i3 < length; i3++) {
                    if (CharsKt__CharJVMKt.digitOf(str.charAt(i3), i2) < 0) {
                        return null;
                    }
                }
            } else if (CharsKt__CharJVMKt.digitOf(str.charAt(0), i2) < 0) {
                return null;
            }
            checkRadix = CharsKt__CharJVMKt.checkRadix(i2);
            return new BigInteger(str, checkRadix);
        }
        return null;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final byte toByte(@NotNull String str, int i2) {
        int checkRadix;
        checkRadix = CharsKt__CharJVMKt.checkRadix(i2);
        return Byte.parseByte(str, checkRadix);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final int toInt(@NotNull String str, int i2) {
        int checkRadix;
        checkRadix = CharsKt__CharJVMKt.checkRadix(i2);
        return Integer.parseInt(str, checkRadix);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final long toLong(@NotNull String str, int i2) {
        int checkRadix;
        checkRadix = CharsKt__CharJVMKt.checkRadix(i2);
        return Long.parseLong(str, checkRadix);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final short toShort(@NotNull String str, int i2) {
        int checkRadix;
        checkRadix = CharsKt__CharJVMKt.checkRadix(i2);
        return Short.parseShort(str, checkRadix);
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(short s, int i2) {
        int checkRadix;
        int checkRadix2;
        checkRadix = CharsKt__CharJVMKt.checkRadix(i2);
        checkRadix2 = CharsKt__CharJVMKt.checkRadix(checkRadix);
        String num = Integer.toString(s, checkRadix2);
        Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
        return num;
    }

    @SinceKotlin(version = "1.2")
    @Nullable
    public static final BigDecimal toBigDecimalOrNull(@NotNull String str, @NotNull MathContext mathContext) {
        try {
            if (ScreenFloatValueRegEx.value.matches(str)) {
                return new BigDecimal(str, mathContext);
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(int i2, int i3) {
        int checkRadix;
        checkRadix = CharsKt__CharJVMKt.checkRadix(i3);
        String num = Integer.toString(i2, checkRadix);
        Intrinsics.checkExpressionValueIsNotNull(num, "java.lang.Integer.toStri\u2026(this, checkRadix(radix))");
        return num;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String toString(long j2, int i2) {
        int checkRadix;
        checkRadix = CharsKt__CharJVMKt.checkRadix(i2);
        String l2 = Long.toString(j2, checkRadix);
        Intrinsics.checkExpressionValueIsNotNull(l2, "java.lang.Long.toString(this, checkRadix(radix))");
        return l2;
    }
}
