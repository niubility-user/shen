package kotlin.text;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0019\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\u001a\u001b\u0010\u0002\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u0001H\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a(\u0010\t\u001a\u00020\b*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\n\u00a2\u0006\u0004\b\t\u0010\n\u001a4\u0010\u000e\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\rH\u0087\b\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a$\u0010\u0010\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0005\u001a\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\u0010\u0010\u0011\u001a,\u0010\u0012\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a>\u0010\u0017\u001a\u00020\b*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\u0017\u0010\u0018\u001a4\u0010\u0019\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0007\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\u0019\u0010\u001a\u001a6\u0010\u0019\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\u0019\u0010\u001c\u001a<\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\u001d\u0010\u001e\u001a>\u0010\u001d\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\u001d\u0010\u001f\u001a\u0019\u0010\"\u001a\u00060 j\u0002`!*\u00060 j\u0002`!\u00a2\u0006\u0004\b\"\u0010#\u001a&\u0010\"\u001a\u00060 j\u0002`!*\u00060 j\u0002`!2\b\u0010\u0007\u001a\u0004\u0018\u00010\u001bH\u0087\b\u00a2\u0006\u0004\b\"\u0010$\u001a$\u0010\"\u001a\u00060 j\u0002`!*\u00060 j\u0002`!2\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u00a2\u0006\u0004\b\"\u0010%\u001a\u0019\u0010\"\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u0001\u00a2\u0006\u0004\b\"\u0010\u0003\u001a&\u0010\"\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010&H\u0087\b\u00a2\u0006\u0004\b\"\u0010'\u001a&\u0010\"\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\u001bH\u0087\b\u00a2\u0006\u0004\b\"\u0010(\u001a&\u0010\"\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010\rH\u0087\b\u00a2\u0006\u0004\b\"\u0010)\u001a&\u0010\"\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\b\u0010\u0007\u001a\u0004\u0018\u00010*H\u0087\b\u00a2\u0006\u0004\b\"\u0010+\u001a,\u0010\"\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u000e\u0010\u0007\u001a\n\u0018\u00010\u0000j\u0004\u0018\u0001`\u0001H\u0087\b\u00a2\u0006\u0004\b\"\u0010,\u001a$\u0010\"\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0007\u001a\u00020\u0014H\u0087\b\u00a2\u0006\u0004\b\"\u0010-\u001a$\u0010\"\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0007\u001a\u00020\u0006H\u0087\b\u00a2\u0006\u0004\b\"\u0010.\u001a$\u0010\"\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0007\u001a\u00020/H\u0087\b\u00a2\u0006\u0004\b\"\u00100\u001a$\u0010\"\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0007\u001a\u00020\u0004H\u0087\b\u00a2\u0006\u0004\b\"\u0010\u0011\u001a$\u0010\"\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0007\u001a\u000201H\u0087\b\u00a2\u0006\u0004\b\"\u00102\u001a$\u0010\"\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0007\u001a\u000203H\u0087\b\u00a2\u0006\u0004\b\"\u00104\u001a$\u0010\"\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0007\u001a\u000205H\u0087\b\u00a2\u0006\u0004\b\"\u00106\u001a$\u0010\"\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0007\u001a\u000207H\u0087\b\u00a2\u0006\u0004\b\"\u00108\u001a$\u0010\"\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0007\u001a\u000209H\u0087\b\u00a2\u0006\u0004\b\"\u0010:\u00a8\u0006;"}, d2 = {"Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "clear", "(Ljava/lang/StringBuilder;)Ljava/lang/StringBuilder;", "", "index", "", "value", "", "set", "(Ljava/lang/StringBuilder;IC)V", "startIndex", "endIndex", "", "setRange", "(Ljava/lang/StringBuilder;IILjava/lang/String;)Ljava/lang/StringBuilder;", "deleteAt", "(Ljava/lang/StringBuilder;I)Ljava/lang/StringBuilder;", "deleteRange", "(Ljava/lang/StringBuilder;II)Ljava/lang/StringBuilder;", "", "destination", "destinationOffset", "toCharArray", "(Ljava/lang/StringBuilder;[CIII)V", "appendRange", "(Ljava/lang/StringBuilder;[CII)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;Ljava/lang/CharSequence;II)Ljava/lang/StringBuilder;", "insertRange", "(Ljava/lang/StringBuilder;I[CII)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;ILjava/lang/CharSequence;II)Ljava/lang/StringBuilder;", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "appendln", "(Ljava/lang/Appendable;)Ljava/lang/Appendable;", "(Ljava/lang/Appendable;Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "(Ljava/lang/Appendable;C)Ljava/lang/Appendable;", "Ljava/lang/StringBuffer;", "(Ljava/lang/StringBuilder;Ljava/lang/StringBuffer;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;Ljava/lang/CharSequence;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;Ljava/lang/String;)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;Ljava/lang/Object;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;Ljava/lang/StringBuilder;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;[C)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;C)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;Z)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;S)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;B)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;J)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;F)Ljava/lang/StringBuilder;", "", "(Ljava/lang/StringBuilder;D)Ljava/lang/StringBuilder;", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/text/StringsKt")
/* loaded from: classes.dex */
public class StringsKt__StringBuilderJVMKt extends StringsKt__RegexExtensionsKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final StringBuilder appendRange(@NotNull StringBuilder sb, char[] cArr, int i2, int i3) {
        sb.append(cArr, i2, i3 - i2);
        Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, start\u2026x, endIndex - startIndex)");
        return sb;
    }

    @NotNull
    public static final Appendable appendln(@NotNull Appendable appendable) {
        Appendable append = appendable.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkExpressionValueIsNotNull(append, "append(SystemProperties.LINE_SEPARATOR)");
        return append;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final StringBuilder clear(@NotNull StringBuilder sb) {
        sb.setLength(0);
        return sb;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final StringBuilder deleteAt(@NotNull StringBuilder sb, int i2) {
        StringBuilder deleteCharAt = sb.deleteCharAt(i2);
        Intrinsics.checkExpressionValueIsNotNull(deleteCharAt, "this.deleteCharAt(index)");
        return deleteCharAt;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final StringBuilder deleteRange(@NotNull StringBuilder sb, int i2, int i3) {
        StringBuilder delete = sb.delete(i2, i3);
        Intrinsics.checkExpressionValueIsNotNull(delete, "this.delete(startIndex, endIndex)");
        return delete;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final StringBuilder insertRange(@NotNull StringBuilder sb, int i2, char[] cArr, int i3, int i4) {
        StringBuilder insert = sb.insert(i2, cArr, i3, i4 - i3);
        Intrinsics.checkExpressionValueIsNotNull(insert, "this.insert(index, value\u2026x, endIndex - startIndex)");
        return insert;
    }

    @InlineOnly
    private static final void set(@NotNull StringBuilder sb, int i2, char c2) {
        sb.setCharAt(i2, c2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final StringBuilder setRange(@NotNull StringBuilder sb, int i2, int i3, String str) {
        StringBuilder replace = sb.replace(i2, i3, str);
        Intrinsics.checkExpressionValueIsNotNull(replace, "this.replace(startIndex, endIndex, value)");
        return replace;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final void toCharArray(@NotNull StringBuilder sb, char[] cArr, int i2, int i3, int i4) {
        sb.getChars(i3, i4, cArr, i2);
    }

    static /* synthetic */ void toCharArray$default(StringBuilder sb, char[] cArr, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 2) != 0) {
            i2 = 0;
        }
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = sb.length();
        }
        sb.getChars(i3, i4, cArr, i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final StringBuilder appendRange(@NotNull StringBuilder sb, CharSequence charSequence, int i2, int i3) {
        sb.append(charSequence, i2, i3);
        Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(value, startIndex, endIndex)");
        return sb;
    }

    @InlineOnly
    private static final Appendable appendln(@NotNull Appendable appendable, CharSequence charSequence) {
        Appendable append = appendable.append(charSequence);
        Intrinsics.checkExpressionValueIsNotNull(append, "append(value)");
        return appendln(append);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @InlineOnly
    private static final StringBuilder insertRange(@NotNull StringBuilder sb, int i2, CharSequence charSequence, int i3, int i4) {
        StringBuilder insert = sb.insert(i2, charSequence, i3, i4);
        Intrinsics.checkExpressionValueIsNotNull(insert, "this.insert(index, value, startIndex, endIndex)");
        return insert;
    }

    @InlineOnly
    private static final Appendable appendln(@NotNull Appendable appendable, char c2) {
        Appendable append = appendable.append(c2);
        Intrinsics.checkExpressionValueIsNotNull(append, "append(value)");
        return appendln(append);
    }

    @NotNull
    public static final StringBuilder appendln(@NotNull StringBuilder sb) {
        sb.append(SystemProperties.LINE_SEPARATOR);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(SystemProperties.LINE_SEPARATOR)");
        return sb;
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, StringBuffer stringBuffer) {
        sb.append(stringBuffer);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, CharSequence charSequence) {
        sb.append(charSequence);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, String str) {
        sb.append(str);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, Object obj) {
        sb.append(obj);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, StringBuilder sb2) {
        sb.append((CharSequence) sb2);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, char[] cArr) {
        sb.append(cArr);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, char c2) {
        sb.append(c2);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, boolean z) {
        sb.append(z);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, int i2) {
        sb.append(i2);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, short s) {
        sb.append((int) s);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value.toInt())");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, byte b) {
        sb.append((int) b);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value.toInt())");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, long j2) {
        sb.append(j2);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, float f2) {
        sb.append(f2);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }

    @InlineOnly
    private static final StringBuilder appendln(@NotNull StringBuilder sb, double d) {
        sb.append(d);
        Intrinsics.checkExpressionValueIsNotNull(sb, "append(value)");
        return appendln(sb);
    }
}
