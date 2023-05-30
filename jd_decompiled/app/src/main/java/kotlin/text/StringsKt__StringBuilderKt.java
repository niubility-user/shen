package kotlin.text;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\u001a&\u0010\u0004\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0087\b\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a-\u0010\u000b\u001a\u00020\n2\u001b\u0010\t\u001a\u0017\u0012\b\u0012\u00060\u0000j\u0002`\u0001\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\b\bH\u0087\b\u00a2\u0006\u0004\b\u000b\u0010\f\u001a5\u0010\u000b\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\r2\u001b\u0010\t\u001a\u0017\u0012\b\u0012\u00060\u0000j\u0002`\u0001\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\b\bH\u0087\b\u00a2\u0006\u0004\b\u000b\u0010\u000f\u001a1\u0010\u0004\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\n0\u0010\"\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u0004\u0010\u0012\u001a1\u0010\u0004\u001a\u00060\u0000j\u0002`\u0001*\u00060\u0000j\u0002`\u00012\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00020\u0010\"\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "", "obj", "append", "(Ljava/lang/StringBuilder;Ljava/lang/Object;)Ljava/lang/StringBuilder;", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "builderAction", "", "buildString", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/String;", "", "capacity", "(ILkotlin/jvm/functions/Function1;)Ljava/lang/String;", "", "value", "(Ljava/lang/StringBuilder;[Ljava/lang/String;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;[Ljava/lang/Object;)Ljava/lang/StringBuilder;", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/text/StringsKt")
/* loaded from: classes.dex */
class StringsKt__StringBuilderKt extends StringsKt__StringBuilderJVMKt {
    @Deprecated(level = DeprecationLevel.WARNING, message = "Use append(value: Any?) instead", replaceWith = @ReplaceWith(expression = "append(value = obj)", imports = {}))
    @InlineOnly
    private static final StringBuilder append(@NotNull StringBuilder sb, Object obj) {
        sb.append(obj);
        Intrinsics.checkExpressionValueIsNotNull(sb, "this.append(obj)");
        return sb;
    }

    @InlineOnly
    private static final String buildString(Function1<? super StringBuilder, Unit> function1) {
        StringBuilder sb = new StringBuilder();
        function1.invoke(sb);
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public static final StringBuilder append(@NotNull StringBuilder sb, @NotNull String... strArr) {
        for (String str : strArr) {
            sb.append(str);
        }
        return sb;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final String buildString(int i2, Function1<? super StringBuilder, Unit> function1) {
        StringBuilder sb = new StringBuilder(i2);
        function1.invoke(sb);
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder(capacity).\u2026builderAction).toString()");
        return sb2;
    }

    @NotNull
    public static final StringBuilder append(@NotNull StringBuilder sb, @NotNull Object... objArr) {
        for (Object obj : objArr) {
            sb.append(obj);
        }
        return sb;
    }
}
