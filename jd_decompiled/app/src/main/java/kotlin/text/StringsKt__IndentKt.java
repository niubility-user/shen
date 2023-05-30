package kotlin.text;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final java.lang.String reindent$StringsKt__IndentKt(@org.jetbrains.annotations.NotNull java.util.List<java.lang.String> r11, int r12, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> r13, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> r14) {
        /*
            int r0 = kotlin.collections.CollectionsKt.getLastIndex(r11)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r11 = r11.iterator()
            r2 = 0
            r3 = 0
        Lf:
            boolean r4 = r11.hasNext()
            if (r4 == 0) goto L57
            java.lang.Object r4 = r11.next()
            int r5 = r3 + 1
            if (r3 >= 0) goto L31
            r6 = 3
            r7 = 1
            boolean r6 = kotlin.internal.PlatformImplementationsKt.apiVersionIsAtLeast(r7, r6, r2)
            if (r6 == 0) goto L29
            kotlin.collections.CollectionsKt.throwIndexOverflow()
            goto L31
        L29:
            java.lang.ArithmeticException r11 = new java.lang.ArithmeticException
            java.lang.String r12 = "Index overflow has happened."
            r11.<init>(r12)
            throw r11
        L31:
            java.lang.String r4 = (java.lang.String) r4
            if (r3 == 0) goto L37
            if (r3 != r0) goto L3f
        L37:
            boolean r3 = kotlin.text.StringsKt.isBlank(r4)
            if (r3 == 0) goto L3f
            r4 = 0
            goto L50
        L3f:
            java.lang.Object r3 = r14.invoke(r4)
            java.lang.String r3 = (java.lang.String) r3
            if (r3 == 0) goto L50
            java.lang.Object r3 = r13.invoke(r3)
            java.lang.String r3 = (java.lang.String) r3
            if (r3 == 0) goto L50
            r4 = r3
        L50:
            if (r4 == 0) goto L55
            r1.add(r4)
        L55:
            r3 = r5
            goto Lf
        L57:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r12)
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 124(0x7c, float:1.74E-43)
            r10 = 0
            java.lang.String r3 = "\n"
            java.lang.Appendable r11 = kotlin.collections.CollectionsKt.joinTo$default(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            java.lang.StringBuilder r11 = (java.lang.StringBuilder) r11
            java.lang.String r11 = r11.toString()
            java.lang.String r12 = "mapIndexedNotNull { inde\u2026\"\\n\")\n        .toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r12)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__IndentKt.reindent$StringsKt__IndentKt(java.util.List, int, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00af A[SYNTHETIC] */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.String replaceIndent(@org.jetbrains.annotations.NotNull java.lang.String r14, @org.jetbrains.annotations.NotNull java.lang.String r15) {
        /*
            java.util.List r0 = kotlin.text.StringsKt__StringsKt.lines(r14)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r2 = r0.iterator()
        Ld:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L26
            java.lang.Object r3 = r2.next()
            r4 = r3
            java.lang.String r4 = (java.lang.String) r4
            boolean r4 = kotlin.text.StringsKt.isBlank(r4)
            r4 = r4 ^ 1
            if (r4 == 0) goto Ld
            r1.add(r3)
            goto Ld
        L26:
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 10
            int r3 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r1, r3)
            r2.<init>(r3)
            java.util.Iterator r1 = r1.iterator()
        L35:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L4d
            java.lang.Object r3 = r1.next()
            java.lang.String r3 = (java.lang.String) r3
            int r3 = indentWidth$StringsKt__IndentKt(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2.add(r3)
            goto L35
        L4d:
            java.lang.Comparable r1 = kotlin.collections.CollectionsKt.min(r2)
            java.lang.Integer r1 = (java.lang.Integer) r1
            r2 = 0
            if (r1 == 0) goto L5b
            int r1 = r1.intValue()
            goto L5c
        L5b:
            r1 = 0
        L5c:
            int r14 = r14.length()
            int r3 = r15.length()
            int r4 = r0.size()
            int r3 = r3 * r4
            int r14 = r14 + r3
            kotlin.jvm.functions.Function1 r15 = getIndentFunction$StringsKt__IndentKt(r15)
            int r3 = kotlin.collections.CollectionsKt.getLastIndex(r0)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r0 = r0.iterator()
        L7c:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto Lb1
            java.lang.Object r5 = r0.next()
            int r6 = r2 + 1
            if (r2 >= 0) goto L8d
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L8d:
            java.lang.String r5 = (java.lang.String) r5
            if (r2 == 0) goto L93
            if (r2 != r3) goto L9b
        L93:
            boolean r2 = kotlin.text.StringsKt.isBlank(r5)
            if (r2 == 0) goto L9b
            r5 = 0
            goto Laa
        L9b:
            java.lang.String r2 = kotlin.text.StringsKt___StringsKt.drop(r5, r1)
            if (r2 == 0) goto Laa
            java.lang.Object r2 = r15.invoke(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto Laa
            r5 = r2
        Laa:
            if (r5 == 0) goto Laf
            r4.add(r5)
        Laf:
            r2 = r6
            goto L7c
        Lb1:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r14)
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 124(0x7c, float:1.74E-43)
            r13 = 0
            java.lang.String r6 = "\n"
            java.lang.Appendable r14 = kotlin.collections.CollectionsKt.joinTo$default(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            java.lang.StringBuilder r14 = (java.lang.StringBuilder) r14
            java.lang.String r14 = r14.toString()
            java.lang.String r15 = "mapIndexedNotNull { inde\u2026\"\\n\")\n        .toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r14, r15)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__IndentKt.replaceIndent(java.lang.String, java.lang.String):java.lang.String");
    }

    public static /* synthetic */ String replaceIndent$default(String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str2 = "";
        }
        return replaceIndent(str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00a1 A[SYNTHETIC] */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.String replaceIndentByMargin(@org.jetbrains.annotations.NotNull java.lang.String r16, @org.jetbrains.annotations.NotNull java.lang.String r17, @org.jetbrains.annotations.NotNull java.lang.String r18) {
        /*
            boolean r0 = kotlin.text.StringsKt.isBlank(r18)
            r0 = r0 ^ 1
            if (r0 == 0) goto Lc2
            java.util.List r0 = kotlin.text.StringsKt__StringsKt.lines(r16)
            int r1 = r16.length()
            int r2 = r17.length()
            int r3 = r0.size()
            int r2 = r2 * r3
            int r1 = r1 + r2
            kotlin.jvm.functions.Function1 r2 = getIndentFunction$StringsKt__IndentKt(r17)
            int r3 = kotlin.collections.CollectionsKt.getLastIndex(r0)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r0 = r0.iterator()
            r5 = 0
            r6 = 0
        L2e:
            boolean r7 = r0.hasNext()
            if (r7 == 0) goto La3
            java.lang.Object r7 = r0.next()
            int r8 = r6 + 1
            if (r6 >= 0) goto L3f
            kotlin.collections.CollectionsKt.throwIndexOverflow()
        L3f:
            java.lang.String r7 = (java.lang.String) r7
            r15 = 0
            if (r6 == 0) goto L46
            if (r6 != r3) goto L4e
        L46:
            boolean r6 = kotlin.text.StringsKt.isBlank(r7)
            if (r6 == 0) goto L4e
            r7 = r15
            goto L9c
        L4e:
            int r6 = r7.length()
            r9 = 0
        L53:
            r10 = -1
            if (r9 >= r6) goto L67
            char r11 = r7.charAt(r9)
            boolean r11 = kotlin.text.CharsKt__CharJVMKt.isWhitespace(r11)
            r11 = r11 ^ 1
            if (r11 == 0) goto L64
            r6 = r9
            goto L68
        L64:
            int r9 = r9 + 1
            goto L53
        L67:
            r6 = -1
        L68:
            if (r6 != r10) goto L6b
            goto L91
        L6b:
            r12 = 0
            r13 = 4
            r14 = 0
            r9 = r7
            r10 = r18
            r11 = r6
            boolean r9 = kotlin.text.StringsKt__StringsJVMKt.startsWith$default(r9, r10, r11, r12, r13, r14)
            if (r9 == 0) goto L91
            int r9 = r18.length()
            int r6 = r6 + r9
            if (r7 == 0) goto L89
            java.lang.String r15 = r7.substring(r6)
            java.lang.String r6 = "(this as java.lang.String).substring(startIndex)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r15, r6)
            goto L91
        L89:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type java.lang.String"
            r0.<init>(r1)
            throw r0
        L91:
            if (r15 == 0) goto L9c
            java.lang.Object r6 = r2.invoke(r15)
            java.lang.String r6 = (java.lang.String) r6
            if (r6 == 0) goto L9c
            r7 = r6
        L9c:
            if (r7 == 0) goto La1
            r4.add(r7)
        La1:
            r6 = r8
            goto L2e
        La3:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r1)
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 124(0x7c, float:1.74E-43)
            r13 = 0
            java.lang.String r6 = "\n"
            java.lang.Appendable r0 = kotlin.collections.CollectionsKt.joinTo$default(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            java.lang.StringBuilder r0 = (java.lang.StringBuilder) r0
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "mapIndexedNotNull { inde\u2026\"\\n\")\n        .toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            return r0
        Lc2:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "marginPrefix must be non-blank string."
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            goto Lcf
        Lce:
            throw r0
        Lcf:
            goto Lce
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__IndentKt.replaceIndentByMargin(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
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
