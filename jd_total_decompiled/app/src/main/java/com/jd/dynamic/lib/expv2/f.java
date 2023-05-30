package com.jd.dynamic.lib.expv2;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.lib.expv2.c.e;
import com.jd.dynamic.lib.expv2.c.i;
import com.jd.dynamic.lib.expv2.c.j;
import com.jd.dynamic.lib.expv2.c.k;
import java.util.Stack;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public abstract class f implements d {

    /* renamed from: c  reason: collision with root package name */
    private static final char[] f2242c = {'{', '('};
    private static final char[] d = {'{', '(', '}', ')'};
    private final Stack<i> a = new Stack<>();
    private i b;

    private final void c(e eVar, String str, String str2) {
        CharSequence trim;
        String str3;
        long nanoTime;
        long nanoTime2;
        CharSequence trim2;
        long nanoTime3 = System.nanoTime();
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        trim = StringsKt__StringsKt.trim((CharSequence) str);
        String obj = trim.toString();
        i iVar = null;
        if (str2 == null) {
            str3 = null;
        } else if (str2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        } else {
            trim2 = StringsKt__StringsKt.trim((CharSequence) str2);
            str3 = trim2.toString();
        }
        if (!TextUtils.isEmpty(str3)) {
            if (str3 == null) {
                Intrinsics.throwNpe();
            }
            iVar = eVar.a(str3);
        }
        i a = eVar.a(obj);
        long nanoTime4 = System.nanoTime();
        long j2 = 0;
        if (this.a.isEmpty()) {
            if ((a instanceof k) && ((k) a).x()) {
                this.a.push(a);
            }
            nanoTime2 = 0;
            j2 = System.nanoTime();
            nanoTime = 0;
        } else {
            i peek = this.a.peek();
            if (peek instanceof k) {
                if (iVar != null) {
                    ((k) peek).w(iVar);
                }
                ((k) peek).w(a);
            }
            nanoTime = System.nanoTime();
            if (a.g()) {
                this.a.push(a);
            }
            nanoTime2 = System.nanoTime();
        }
        DYConstants.DYLog("time create:" + (nanoTime4 - nanoTime3) + " stackNull : " + (j2 - nanoTime4) + " + endAdd: " + (nanoTime - nanoTime4) + " + endPush :" + (nanoTime2 - nanoTime));
    }

    private final void d(e eVar, String str, String str2) {
        CharSequence trim;
        String str3;
        String str4;
        CharSequence trim2;
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        trim = StringsKt__StringsKt.trim((CharSequence) str);
        String obj = trim.toString();
        if (str2 == null) {
            str3 = null;
        } else if (str2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        } else {
            trim2 = StringsKt__StringsKt.trim((CharSequence) str2);
            str3 = trim2.toString();
        }
        if (this.a.isEmpty()) {
            str4 = "stack is null ";
        } else {
            i peek = this.a.peek();
            if (peek == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.jd.dynamic.lib.expv2.nodes.XPRootNode");
            }
            k kVar = (k) peek;
            if (kVar.f(obj)) {
                if (!(str3 == null || str3.length() == 0)) {
                    kVar.w(eVar.a(str3));
                }
                this.a.pop();
                if (this.a.isEmpty()) {
                    this.b = kVar;
                    return;
                }
                return;
            }
            str4 = "not match";
        }
        DYConstants.DYLog(str4);
    }

    @Override // com.jd.dynamic.lib.expv2.d
    @Nullable
    public Object a(@NotNull String str) {
        CharSequence trim;
        boolean startsWith$default;
        String str2;
        boolean contains$default;
        String substring;
        boolean contains$default2;
        CharSequence trim2;
        long nanoTime = System.nanoTime();
        e b = b();
        trim = StringsKt__StringsKt.trim((CharSequence) str);
        String obj = trim.toString();
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(obj, "fun{", false, 2, null);
        if (startsWith$default) {
            return obj;
        }
        com.jd.dynamic.lib.expv2.e.b bVar = new com.jd.dynamic.lib.expv2.e.b(obj);
        while (bVar.c()) {
            String b2 = bVar.b(d);
            if (b2 == null) {
                str2 = null;
            } else if (b2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            } else {
                trim2 = StringsKt__StringsKt.trim((CharSequence) b2);
                str2 = trim2.toString();
            }
            if (str2 != null) {
                char[] cArr = f2242c;
                contains$default = StringsKt__StringsKt.contains$default((CharSequence) str2, cArr[0], false, 2, (Object) null);
                if (!contains$default) {
                    contains$default2 = StringsKt__StringsKt.contains$default((CharSequence) str2, cArr[1], false, 2, (Object) null);
                    if (!contains$default2) {
                        if (1 == str2.length()) {
                            d(b, str2, null);
                        } else {
                            int length = str2.length() - 1;
                            if (str2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring2 = str2.substring(length);
                            Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
                            int length2 = str2.length() - 1;
                            if (str2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring3 = str2.substring(0, length2);
                            Intrinsics.checkExpressionValueIsNotNull(substring3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                            d(b, substring2, substring3);
                        }
                    }
                }
                if (1 != str2.length()) {
                    j.a aVar = j.b;
                    int c2 = aVar.c(str2);
                    if (c2 < 0) {
                        c2 = aVar.b(str2);
                    }
                    if (c2 < 0) {
                        c2 = aVar.a(str2);
                    }
                    if (c2 != 0) {
                        if (c2 > 0) {
                            if (str2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            substring = str2.substring(c2);
                            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                            if (str2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                        } else if (aVar.e(str2)) {
                            int a = bVar.a(str2, Typography.dollar);
                            if (str2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring4 = str2.substring(a);
                            Intrinsics.checkExpressionValueIsNotNull(substring4, "(this as java.lang.String).substring(startIndex)");
                            if (str2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            String substring5 = str2.substring(0, a);
                            Intrinsics.checkExpressionValueIsNotNull(substring5, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                            c(b, substring4, substring5);
                        } else {
                            int length3 = str2.length() - 1;
                            if (str2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            substring = str2.substring(length3);
                            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                            c2 = str2.length() - 1;
                            if (str2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                        }
                        String substring6 = str2.substring(0, c2);
                        Intrinsics.checkExpressionValueIsNotNull(substring6, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                        c(b, substring, substring6);
                    }
                }
                c(b, str2, null);
            }
        }
        if (this.a.size() > 0) {
            return "";
        }
        DYConstants.DYLog("+++---+++ parse time is : " + (System.nanoTime() - nanoTime));
        return this.b;
    }

    @NotNull
    public abstract e b();
}
