package kotlin.text;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\u001a\u001b\u0010\u0002\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0001\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a%\u0010\u0005\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00002\b\b\u0002\u0010\u0001\u001a\u00020\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a\u0011\u0010\u0007\u001a\u00020\u0000*\u00020\u0000\u00a2\u0006\u0004\b\u0007\u0010\b\u001a\u001b\u0010\t\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0000\u00a2\u0006\u0004\b\t\u0010\u0003\u001a\u001b\u0010\u000b\u001a\u00020\u0000*\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\u0000\u00a2\u0006\u0004\b\u000b\u0010\u0003\u001a\u0013\u0010\u000f\u001a\u00020\f*\u00020\u0000H\u0002\u00a2\u0006\u0004\b\r\u0010\u000e\u001a#\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u00102\u0006\u0010\n\u001a\u00020\u0000H\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012\u001aL\u0010\u001a\u001a\u00020\u0000*\b\u0012\u0004\u0012\u00020\u00000\u00142\u0006\u0010\u0015\u001a\u00020\f2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u00102\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0010H\u0082\b\u00a2\u0006\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001b"}, d2 = {"", "marginPrefix", "trimMargin", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "newIndent", "replaceIndentByMargin", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "trimIndent", "(Ljava/lang/String;)Ljava/lang/String;", "replaceIndent", "indent", "prependIndent", "", "indentWidth$StringsKt__IndentKt", "(Ljava/lang/String;)I", "indentWidth", "Lkotlin/Function1;", "getIndentFunction$StringsKt__IndentKt", "(Ljava/lang/String;)Lkotlin/jvm/functions/Function1;", "getIndentFunction", "", "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "(Ljava/util/List;ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/String;", "reindent", "kotlin-stdlib"}, k = 5, mv = {1, 4, 0}, xs = "kotlin/text/StringsKt")
/* loaded from: classes.dex */
public class StringsKt__IndentKt extends StringsKt__AppendableKt {
    private static final Function1<String, String> getIndentFunction$StringsKt__IndentKt(final String str) {
        return str.length() == 0 ? new Function1<String, String>() { // from class: kotlin.text.StringsKt__IndentKt$getIndentFunction$1
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final String invoke(@NotNull String str2) {
                return str2;
            }
        } : new Function1<String, String>() { // from class: kotlin.text.StringsKt__IndentKt$getIndentFunction$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final String invoke(@NotNull String str2) {
                return str + str2;
            }
        };
    }

    private static final int indentWidth$StringsKt__IndentKt(@NotNull String str) {
        int length = str.length();
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            } else if ((!CharsKt__CharJVMKt.isWhitespace(str.charAt(i2))) == true) {
                break;
            } else {
                i2++;
            }
        }
        return i2 == -1 ? str.length() : i2;
    }

    @NotNull
    public static final String prependIndent(@NotNull String str, @NotNull final String str2) {
        Sequence map;
        String joinToString$default;
        map = SequencesKt___SequencesKt.map(StringsKt__StringsKt.lineSequence(str), new Function1<String, String>() { // from class: kotlin.text.StringsKt__IndentKt$prependIndent$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final String invoke(@NotNull String str3) {
                boolean isBlank;
                isBlank = StringsKt__StringsJVMKt.isBlank(str3);
                if (isBlank) {
                    return str3.length() < str2.length() ? str2 : str3;
                }
                return str2 + str3;
            }
        });
        joinToString$default = SequencesKt___SequencesKt.joinToString$default(map, ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, null, null, 0, null, null, 62, null);
        return joinToString$default;
    }

    public static /* synthetic */ String prependIndent$default(String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str2 = "    ";
        }
        return prependIndent(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0055 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final String reindent$StringsKt__IndentKt(@NotNull List<String> list, int i2, Function1<? super String, String> function1, Function1<? super String, String> function12) {
        int lastIndex;
        Appendable joinTo$default;
        boolean isBlank;
        String invoke;
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(list);
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        for (Object obj : list) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                if (!PlatformImplementationsKt.apiVersionIsAtLeast(1, 3, 0)) {
                    throw new ArithmeticException("Index overflow has happened.");
                }
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            if (i3 == 0 || i3 == lastIndex) {
                isBlank = StringsKt__StringsJVMKt.isBlank(str);
                if (isBlank) {
                    str = null;
                    if (str == null) {
                        arrayList.add(str);
                    }
                    i3 = i4;
                }
            }
            String invoke2 = function12.invoke(str);
            if (invoke2 != null && (invoke = function1.invoke(invoke2)) != null) {
                str = invoke;
            }
            if (str == null) {
            }
            i3 = i4;
        }
        joinTo$default = CollectionsKt___CollectionsKt.joinTo$default(arrayList, new StringBuilder(i2), ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, null, null, 0, null, null, 124, null);
        String sb = ((StringBuilder) joinTo$default).toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "mapIndexedNotNull { inde\u2026\"\\n\")\n        .toString()");
        return sb;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00af A[SYNTHETIC] */
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final String replaceIndent(@NotNull String str, @NotNull String str2) {
        int collectionSizeOrDefault;
        int lastIndex;
        Appendable joinTo$default;
        boolean isBlank;
        String invoke;
        boolean isBlank2;
        List<String> lines = StringsKt__StringsKt.lines(str);
        ArrayList arrayList = new ArrayList();
        for (Object obj : lines) {
            isBlank2 = StringsKt__StringsJVMKt.isBlank((String) obj);
            if ((!isBlank2) != false) {
                arrayList.add(obj);
            }
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(Integer.valueOf(indentWidth$StringsKt__IndentKt((String) it.next())));
        }
        Integer num = (Integer) CollectionsKt.min((Iterable) arrayList2);
        int i2 = 0;
        int intValue = num != null ? num.intValue() : 0;
        int length = str.length() + (str2.length() * lines.size());
        Function1<String, String> indentFunction$StringsKt__IndentKt = getIndentFunction$StringsKt__IndentKt(str2);
        lastIndex = CollectionsKt__CollectionsKt.getLastIndex(lines);
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : lines) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            String str3 = (String) obj2;
            if (i2 == 0 || i2 == lastIndex) {
                isBlank = StringsKt__StringsJVMKt.isBlank(str3);
                if (isBlank) {
                    str3 = null;
                    if (str3 == null) {
                        arrayList3.add(str3);
                    }
                    i2 = i3;
                }
            }
            String drop = StringsKt___StringsKt.drop(str3, intValue);
            if (drop != null && (invoke = indentFunction$StringsKt__IndentKt.invoke(drop)) != null) {
                str3 = invoke;
            }
            if (str3 == null) {
            }
            i2 = i3;
        }
        joinTo$default = CollectionsKt___CollectionsKt.joinTo$default(arrayList3, new StringBuilder(length), ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, null, null, 0, null, null, 124, null);
        String sb = ((StringBuilder) joinTo$default).toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "mapIndexedNotNull { inde\u2026\"\\n\")\n        .toString()");
        return sb;
    }

    public static /* synthetic */ String replaceIndent$default(String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str2 = "";
        }
        return replaceIndent(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00a1 A[SYNTHETIC] */
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final String replaceIndentByMargin(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        boolean isBlank;
        int lastIndex;
        Appendable joinTo$default;
        boolean isBlank2;
        int i2;
        String invoke;
        isBlank = StringsKt__StringsJVMKt.isBlank(str3);
        if ((!isBlank) != false) {
            List<String> lines = StringsKt__StringsKt.lines(str);
            int length = str.length() + (str2.length() * lines.size());
            Function1<String, String> indentFunction$StringsKt__IndentKt = getIndentFunction$StringsKt__IndentKt(str2);
            lastIndex = CollectionsKt__CollectionsKt.getLastIndex(lines);
            ArrayList arrayList = new ArrayList();
            int i3 = 0;
            for (Object obj : lines) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                String str4 = (String) obj;
                String str5 = null;
                if (i3 == 0 || i3 == lastIndex) {
                    isBlank2 = StringsKt__StringsJVMKt.isBlank(str4);
                    if (isBlank2) {
                        str4 = null;
                        if (str4 == null) {
                            arrayList.add(str4);
                        }
                        i3 = i4;
                    }
                }
                int length2 = str4.length();
                int i5 = 0;
                while (true) {
                    if (i5 >= length2) {
                        i2 = -1;
                        break;
                    } else if ((!CharsKt__CharJVMKt.isWhitespace(str4.charAt(i5))) == true) {
                        i2 = i5;
                        break;
                    } else {
                        i5++;
                    }
                }
                if (i2 != -1 && StringsKt__StringsJVMKt.startsWith$default(str4, str3, i2, false, 4, null)) {
                    int length3 = i2 + str3.length();
                    if (str4 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                    str5 = str4.substring(length3);
                    Intrinsics.checkExpressionValueIsNotNull(str5, "(this as java.lang.String).substring(startIndex)");
                }
                if (str5 != null && (invoke = indentFunction$StringsKt__IndentKt.invoke(str5)) != null) {
                    str4 = invoke;
                }
                if (str4 == null) {
                }
                i3 = i4;
            }
            joinTo$default = CollectionsKt___CollectionsKt.joinTo$default(arrayList, new StringBuilder(length), ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, null, null, 0, null, null, 124, null);
            String sb = ((StringBuilder) joinTo$default).toString();
            Intrinsics.checkExpressionValueIsNotNull(sb, "mapIndexedNotNull { inde\u2026\"\\n\")\n        .toString()");
            return sb;
        }
        throw new IllegalArgumentException("marginPrefix must be non-blank string.".toString());
    }

    public static /* synthetic */ String replaceIndentByMargin$default(String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str2 = "";
        }
        if ((i2 & 2) != 0) {
            str3 = "|";
        }
        return replaceIndentByMargin(str, str2, str3);
    }

    @NotNull
    public static String trimIndent(@NotNull String str) {
        return replaceIndent(str, "");
    }

    @NotNull
    public static final String trimMargin(@NotNull String str, @NotNull String str2) {
        return replaceIndentByMargin(str, "", str2);
    }

    public static /* synthetic */ String trimMargin$default(String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str2 = "|";
        }
        return trimMargin(str, str2);
    }
}
