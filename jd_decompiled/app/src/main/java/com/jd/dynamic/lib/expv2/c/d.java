package com.jd.dynamic.lib.expv2.c;

import android.text.TextUtils;
import android.view.View;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.CachePool;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.base.interfaces.IUniConfig;
import com.jd.dynamic.base.interfaces.IUniConfigWithAdapter;
import com.jd.dynamic.base.timer.TimerRequest;
import com.jd.dynamic.lib.expv2.MathCalc;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.utils.s;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public final class d extends k {
    public d(@NotNull String str) {
        super(str);
    }

    private final Object A(DynamicTemplateEngine dynamicTemplateEngine, String str) {
        return com.jd.dynamic.a.a.a.a.b.a(dynamicTemplateEngine, str);
    }

    private final Object B(String str, i iVar) {
        if (com.jd.dynamic.lib.expv2.g.b(str)) {
            Object a = iVar.a(str);
            if (a instanceof String) {
                if (!(((CharSequence) a).length() == 0)) {
                    return a;
                }
            }
            return "";
        }
        return str;
    }

    private final Object C(String str, String str2, i iVar, Object obj, DynamicTemplateEngine dynamicTemplateEngine, View view) {
        Object B = B(str2, iVar);
        if (B instanceof String) {
            if (((CharSequence) B).length() == 0) {
                return "";
            }
            switch (str.hashCode()) {
                case -483445399:
                    return str.equals("$evalFun(") ? F((String) B, obj, dynamicTemplateEngine, view) : "";
                case 532606710:
                    return str.equals("$appContext(") ? A(dynamicTemplateEngine, (String) B) : "";
                case 1125074639:
                    return str.equals("$calc(") ? N((String) B) : "";
                case 1762622314:
                    return str.equals("$unescape(") ? Q((String) B) : "";
                default:
                    return "";
            }
        }
        return "";
    }

    private final Object D(List<? extends Object> list, DynamicTemplateEngine dynamicTemplateEngine) {
        String optString;
        CachePool cachePool;
        if (list.size() == 2) {
            Object obj = list.get(0);
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
            String timerId = TimerRequest.getTimerId((String) obj, dynamicTemplateEngine);
            Object obj2 = list.get(1);
            if (obj2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
            String str = (String) obj2;
            Object data = (dynamicTemplateEngine == null || (cachePool = dynamicTemplateEngine.getCachePool()) == null) ? null : cachePool.getData("timerData");
            String str2 = data instanceof String ? data : null;
            if (TextUtils.isEmpty(str2)) {
                if (Intrinsics.areEqual(str, "value")) {
                    return "";
                }
                return 0;
            }
            try {
                if (str2 == null) {
                    Intrinsics.throwNpe();
                }
                JSONObject optJSONObject = new JSONObject(str2).optJSONObject(timerId);
                if (Intrinsics.areEqual(str, "value")) {
                    return (optJSONObject == null || (optString = optJSONObject.optString("currentValue")) == null) ? "" : optString;
                } else if (optJSONObject != null) {
                    return Integer.valueOf(optJSONObject.optInt(XView2Constants.STATE, 0));
                } else {
                    return 0;
                }
            } catch (Exception unused) {
            }
        }
        return "";
    }

    private final void E(String str, List<Float> list, DynamicTemplateEngine dynamicTemplateEngine) {
        CharSequence trim;
        try {
            if (str == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            trim = StringsKt__StringsKt.trim((CharSequence) str);
            list.add(Float.valueOf(Float.parseFloat(trim.toString())));
        } catch (NumberFormatException e2) {
            list.add(Float.valueOf(-1));
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "FunctionEvaluator parseAttribute sizeAdapter error", m.j(dynamicTemplateEngine), m.O(dynamicTemplateEngine), R2.attr.loadStartAngle, e2, m.q(dynamicTemplateEngine != null ? dynamicTemplateEngine.getZipVersion() : null, null));
            e2.printStackTrace();
        }
    }

    private final Object F(String str, Object obj, DynamicTemplateEngine dynamicTemplateEngine, View view) {
        return s.b(str, obj, dynamicTemplateEngine, view);
    }

    private final Object G(String str, String str2, i iVar, Object obj, DynamicTemplateEngine dynamicTemplateEngine, View view) {
        if (M(str)) {
            return C(str, str2, iVar, obj, dynamicTemplateEngine, view);
        }
        List<Object> I = I(str2, iVar);
        if (I.isEmpty()) {
            return "";
        }
        switch (str.hashCode()) {
            case -1324784642:
                if (str.equals("$sizeAdapter(")) {
                    return K(I, dynamicTemplateEngine);
                }
                break;
            case 32242592:
                if (str.equals("$modeAdapter(")) {
                    return H(I, dynamicTemplateEngine);
                }
                break;
            case 36329870:
                if (str.equals("$dic(")) {
                    return L(I);
                }
                break;
            case 730832386:
                if (str.equals("$joint(")) {
                    return O(I);
                }
                break;
            case 1011693223:
                if (str.equals("$timer(")) {
                    return D(I, dynamicTemplateEngine);
                }
                break;
            case 1126004174:
                if (str.equals("$dark(")) {
                    return J(I);
                }
                break;
        }
        DYConstants.DYLog("this type not support " + str);
        return "";
    }

    private final Object H(List<? extends Object> list, DynamicTemplateEngine dynamicTemplateEngine) {
        DynamicSdk.Engine engine = DynamicSdk.getEngine();
        Intrinsics.checkExpressionValueIsNotNull(engine, "DynamicSdk.getEngine()");
        IUniConfig uniConfig = engine.getUniConfig();
        if (uniConfig == null || !(uniConfig instanceof IUniConfigWithAdapter)) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "FunctionEvaluator parseAttribute modeAdapter error", m.j(dynamicTemplateEngine), m.O(dynamicTemplateEngine), R2.attr.loadStartAngle, new IllegalStateException("IUniConfigWithFontAdapter must not null."), m.q(dynamicTemplateEngine != null ? dynamicTemplateEngine.getZipVersion() : null, null));
            return "";
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj != null) {
                arrayList.add(obj.toString());
            }
        }
        return ((IUniConfigWithAdapter) uniConfig).getModeInfo(arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r15v0, types: [com.jd.dynamic.lib.expv2.c.i] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Object] */
    private final List<Object> I(String str, i iVar) {
        boolean contains$default;
        ArrayList arrayListOf;
        ArrayList arrayListOf2;
        int i2 = 0;
        contains$default = StringsKt__StringsKt.contains$default((CharSequence) str, ',', false, 2, (Object) null);
        if (!contains$default) {
            boolean b = com.jd.dynamic.lib.expv2.g.b(str);
            String str2 = str;
            if (b) {
                str2 = iVar.a(str);
            }
            arrayListOf2 = CollectionsKt__CollectionsKt.arrayListOf(str2);
            return arrayListOf2;
        } else if (str != null) {
            char[] charArray = str.toCharArray();
            Intrinsics.checkExpressionValueIsNotNull(charArray, "(this as java.lang.String).toCharArray()");
            ArrayList arrayList = new ArrayList();
            int length = charArray.length;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (i3 < length) {
                char c2 = charArray[i3];
                int i6 = i4 + 1;
                if ('(' == c2) {
                    i5++;
                }
                if (')' == c2) {
                    i5--;
                }
                if (i5 == 0 && ',' == c2) {
                    arrayList.add(Integer.valueOf(i4));
                }
                i3++;
                i4 = i6;
            }
            if (arrayList.size() == 0) {
                boolean b2 = com.jd.dynamic.lib.expv2.g.b(str);
                String str3 = str;
                if (b2) {
                    str3 = iVar.a(str);
                }
                arrayListOf = CollectionsKt__CollectionsKt.arrayListOf(str3);
                return arrayListOf;
            }
            arrayList.add(Integer.valueOf(str.length()));
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Integer end = (Integer) it.next();
                Intrinsics.checkExpressionValueIsNotNull(end, "end");
                int intValue = end.intValue();
                if (str == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                ?? substring = str.substring(i2, intValue);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                if (com.jd.dynamic.lib.expv2.g.b(substring)) {
                    substring = iVar.a(substring);
                }
                arrayList2.add(substring);
                i2 = end.intValue() + 1;
            }
            return arrayList2;
        } else {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0033 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.Object J(java.util.List<? extends java.lang.Object> r4) {
        /*
            r3 = this;
            int r0 = r4.size()
            r1 = 0
            r2 = 2
            if (r0 >= r2) goto Ld
        L8:
            java.lang.Object r4 = r4.get(r1)
            goto L2b
        Ld:
            com.jd.dynamic.base.DynamicSdk$Engine r0 = com.jd.dynamic.base.DynamicSdk.getEngine()
            java.lang.String r2 = "DynamicSdk.getEngine()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            com.jd.dynamic.base.interfaces.IDynamicDark r0 = r0.getDynamicDark()
            java.lang.String r2 = "DynamicSdk.getEngine().dynamicDark"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r2)
            boolean r0 = r0.isDarkMode()
            if (r0 != 0) goto L26
            goto L8
        L26:
            r0 = 1
            java.lang.Object r4 = r4.get(r0)
        L2b:
            java.lang.String r0 = "null"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r4)
            if (r0 == 0) goto L35
            java.lang.String r4 = ""
        L35:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.expv2.c.d.J(java.util.List):java.lang.Object");
    }

    private final Object K(List<? extends Object> list, DynamicTemplateEngine dynamicTemplateEngine) {
        DynamicSdk.Engine engine = DynamicSdk.getEngine();
        Intrinsics.checkExpressionValueIsNotNull(engine, "DynamicSdk.getEngine()");
        IUniConfig uniConfig = engine.getUniConfig();
        if (uniConfig == null || !(uniConfig instanceof IUniConfigWithAdapter)) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_PARSE, "FunctionEvaluator parseAttribute sizeAdapter error", m.j(dynamicTemplateEngine), m.O(dynamicTemplateEngine), R2.attr.loadStartAngle, new IllegalStateException("IUniConfigWithFontAdapter must not null."), m.q(dynamicTemplateEngine != null ? dynamicTemplateEngine.getZipVersion() : null, null));
            return -1;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof String) {
                E((String) obj, arrayList, dynamicTemplateEngine);
            }
        }
        return Float.valueOf(((IUniConfigWithAdapter) uniConfig).getTextSize(arrayList));
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0081 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x007e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0075 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.Object L(java.util.List<? extends java.lang.Object> r12) {
        /*
            r11 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.util.Iterator r12 = r12.iterator()
        L9:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L94
            java.lang.Object r1 = r12.next()
            if (r1 == 0) goto L9
            boolean r2 = r1 instanceof java.lang.String
            if (r2 == 0) goto L9
            r3 = r1
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r2 = 2
            java.lang.String r4 = ":"
            r9 = 0
            r10 = 0
            boolean r2 = kotlin.text.StringsKt.contains$default(r3, r4, r9, r2, r10)
            if (r2 == 0) goto L9
            r5 = 0
            r6 = 0
            r7 = 6
            r8 = 0
            java.lang.String r4 = ":"
            int r2 = kotlin.text.StringsKt.indexOf$default(r3, r4, r5, r6, r7, r8)
            if (r2 <= 0) goto L72
            java.lang.String r1 = (java.lang.String) r1
            int r3 = r1.length()
            if (r2 >= r3) goto L72
            java.lang.String r3 = r1.substring(r9, r2)
            java.lang.String r4 = "(this as java.lang.Strin\u2026ing(startIndex, endIndex)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            java.lang.String r4 = "null cannot be cast to non-null type kotlin.CharSequence"
            if (r3 == 0) goto L6c
            java.lang.CharSequence r3 = kotlin.text.StringsKt.trim(r3)
            java.lang.String r10 = r3.toString()
            int r2 = r2 + 1
            java.lang.String r1 = r1.substring(r2)
            java.lang.String r2 = "(this as java.lang.String).substring(startIndex)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            if (r1 == 0) goto L66
            java.lang.CharSequence r1 = kotlin.text.StringsKt.trim(r1)
            java.lang.String r1 = r1.toString()
            goto L73
        L66:
            kotlin.TypeCastException r12 = new kotlin.TypeCastException
            r12.<init>(r4)
            throw r12
        L6c:
            kotlin.TypeCastException r12 = new kotlin.TypeCastException
            r12.<init>(r4)
            throw r12
        L72:
            r1 = r10
        L73:
            if (r10 == 0) goto L7e
            int r2 = r10.length()     // Catch: org.json.JSONException -> L7c
            if (r2 != 0) goto L7f
            goto L7e
        L7c:
            r1 = move-exception
            goto L8f
        L7e:
            r9 = 1
        L7f:
            if (r9 != 0) goto L9
            boolean r2 = com.jd.dynamic.lib.expv2.g.a(r1)     // Catch: org.json.JSONException -> L7c
            if (r2 == 0) goto L8c
        L87:
            r0.put(r10, r1)     // Catch: org.json.JSONException -> L7c
            goto L9
        L8c:
            java.lang.String r1 = ""
            goto L87
        L8f:
            r1.printStackTrace()
            goto L9
        L94:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.expv2.c.d.L(java.util.List):java.lang.Object");
    }

    private final boolean M(String str) {
        boolean equals;
        boolean equals2;
        equals = StringsKt__StringsJVMKt.equals("$calc(", str, true);
        if (equals) {
            return true;
        }
        equals2 = StringsKt__StringsJVMKt.equals("$appContext(", str, true);
        return equals2 || Intrinsics.areEqual("$unescape(", str) || Intrinsics.areEqual("$evalFun(", str);
    }

    private final Object N(String str) {
        double calcExpr = new MathCalc().calcExpr(str);
        int i2 = (int) calcExpr;
        double d = i2;
        Double.isNaN(d);
        return calcExpr - d == 0.0d ? Integer.valueOf(i2) : Double.valueOf(calcExpr);
    }

    private final Object O(List<? extends Object> list) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : list) {
            if (obj != null && com.jd.dynamic.lib.expv2.g.a(obj)) {
                sb.append(obj);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "sb.toString()");
        return sb2;
    }

    private final Object Q(String str) {
        boolean equals;
        equals = StringsKt__StringsJVMKt.equals("space", str, true);
        return equals ? LangUtils.SINGLE_SPACE : Intrinsics.areEqual("comma", str) ? DYConstants.DY_REGEX_COMMA : "";
    }

    @NotNull
    public final String P() {
        return p();
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    public boolean f(@NotNull String str) {
        k(str);
        return Intrinsics.areEqual(str, String.valueOf(')'));
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    @NotNull
    public i h() {
        d dVar = new d(p());
        dVar.k(q());
        dVar.y(z());
        return dVar;
    }

    @Override // com.jd.dynamic.lib.expv2.c.k, com.jd.dynamic.lib.expv2.c.i
    @Nullable
    public Object i(@Nullable Object obj, @Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable View view) {
        String P = P();
        Object i2 = super.i(obj, dynamicTemplateEngine, view);
        if (i2 != null && (i2 instanceof String)) {
            String str = (String) i2;
            if (str.length() >= P.length() + 1) {
                String substring = str.substring(P.length(), str.length() - 1);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                return G(P, substring, this, obj, dynamicTemplateEngine, view);
            }
        }
        return i2;
    }
}
