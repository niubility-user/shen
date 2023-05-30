package com.jd.dynamic.lib.expv2.a;

import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.entity.AttrMethod;
import com.jd.dynamic.lib.utils.m;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public final class c extends com.jd.dynamic.lib.expv2.a.a.a {
    public static final a d = new a(null);

    /* loaded from: classes13.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final Object a(String str, String str2, String str3, com.jd.dynamic.lib.expv2.c.i iVar) {
            List<String> f2 = f(str);
            return f2.isEmpty() ? "" : f2.size() >= 2 ? Intrinsics.areEqual(Boolean.TRUE, b(f2, iVar)) ? com.jd.dynamic.lib.expv2.g.b(str2) ? iVar.a(str2) : str2 : com.jd.dynamic.lib.expv2.g.b(str3) ? iVar.a(str3) : str3 : e(f2.get(0), iVar) ? com.jd.dynamic.lib.expv2.g.b(str2) ? iVar.a(str2) : str2 : com.jd.dynamic.lib.expv2.g.b(str3) ? iVar.a(str3) : str3;
        }

        private final Pair<String, String> d(String str, String str2, com.jd.dynamic.lib.expv2.c.i iVar) {
            boolean b = com.jd.dynamic.lib.expv2.g.b(str);
            Object obj = str;
            if (b) {
                obj = iVar.a(str);
            }
            boolean b2 = com.jd.dynamic.lib.expv2.g.b(str2);
            Object obj2 = str2;
            if (b2) {
                obj2 = iVar.a(str2);
            }
            boolean a = com.jd.dynamic.lib.expv2.g.a(obj);
            Object obj3 = obj;
            if (!a) {
                obj3 = "";
            }
            boolean a2 = com.jd.dynamic.lib.expv2.g.a(obj2);
            Object obj4 = obj2;
            if (!a2) {
                obj4 = "";
            }
            return new Pair<>(obj3 != null ? obj3.toString() : null, obj4 != null ? obj4.toString() : null);
        }

        private final boolean g(String str, com.jd.dynamic.lib.expv2.c.i iVar) {
            boolean equals;
            boolean b = com.jd.dynamic.lib.expv2.g.b(str);
            String str2 = str;
            if (b) {
                str2 = iVar.a(str);
            }
            if (str2 instanceof String) {
                equals = StringsKt__StringsJVMKt.equals(DYConstants.DY_TRUE, str2, true);
                return equals;
            } else if (str2 instanceof Boolean) {
                return ((Boolean) str2).booleanValue();
            } else {
                return false;
            }
        }

        private final List<String> h(String str) {
            boolean contains$default;
            List split$default;
            boolean contains$default2;
            boolean contains$default3;
            boolean contains$default4;
            boolean contains$default5;
            boolean contains$default6;
            ArrayList arrayList = new ArrayList();
            String str2 = "==";
            contains$default = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "==", false, 2, (Object) null);
            if (!contains$default) {
                contains$default2 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "!=", false, 2, (Object) null);
                if (contains$default2) {
                    str2 = "!=";
                } else {
                    contains$default3 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) ">=", false, 2, (Object) null);
                    if (contains$default3) {
                        str2 = ">=";
                    } else {
                        contains$default4 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) ">", false, 2, (Object) null);
                        if (contains$default4) {
                            str2 = ">";
                        } else {
                            contains$default5 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "<=", false, 2, (Object) null);
                            if (contains$default5) {
                                str2 = "<=";
                            } else {
                                contains$default6 = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "<", false, 2, (Object) null);
                                str2 = contains$default6 ? "<" : "";
                            }
                        }
                    }
                }
            }
            if ((!Intrinsics.areEqual("", str2)) == true) {
                arrayList.add(str2);
                split$default = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{str2}, false, 0, 6, (Object) null);
                arrayList.addAll(split$default);
            } else {
                arrayList.add("");
                arrayList.add(str);
            }
            return arrayList;
        }

        @Nullable
        public final Object b(@NotNull List<String> list, @NotNull com.jd.dynamic.lib.expv2.c.i iVar) {
            String str = list.get(0);
            if (Intrinsics.areEqual("||", str)) {
                for (String str2 : list) {
                    if (!Intrinsics.areEqual("||", str2) && !Intrinsics.areEqual("&&", str2) && e(str2, iVar)) {
                        return Boolean.TRUE;
                    }
                }
                return Boolean.FALSE;
            } else if (!Intrinsics.areEqual("&&", str)) {
                return 3 == list.size() ? a(list.get(0), list.get(1), list.get(2), iVar) : "";
            } else {
                for (String str3 : list) {
                    if (!Intrinsics.areEqual("||", str3) && !Intrinsics.areEqual("&&", str3) && !e(str3, iVar)) {
                        return Boolean.FALSE;
                    }
                }
                return Boolean.TRUE;
            }
        }

        @NotNull
        public final String c(@NotNull String str) {
            String substring = str.substring(2, str.length() - 1);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            return substring;
        }

        public final boolean e(@NotNull String str, @NotNull com.jd.dynamic.lib.expv2.c.i iVar) {
            Boolean bool;
            boolean equals;
            boolean equals2;
            List<String> h2 = h(str);
            String str2 = h2.get(0);
            int hashCode = str2.hashCode();
            if (hashCode == 0) {
                if (str2.equals("")) {
                    return g(h2.get(1), iVar);
                }
                return false;
            } else if (hashCode == 60) {
                if (str2.equals("<")) {
                    Pair<String, String> d = d(h2.get(1), h2.get(2), iVar);
                    if ((d.getFirst() instanceof String) && (d.getSecond() instanceof String)) {
                        String first = d.getFirst();
                        if (first != null) {
                            BigDecimal d2 = com.jd.dynamic.lib.expv2.g.d(first);
                            String second = d.getSecond();
                            if (second != null) {
                                return d2.compareTo(com.jd.dynamic.lib.expv2.g.d(second)) < 0;
                            }
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                        }
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                    }
                    return false;
                }
                return false;
            } else if (hashCode == 62) {
                if (str2.equals(">")) {
                    Pair<String, String> d3 = d(h2.get(1), h2.get(2), iVar);
                    if ((d3.getFirst() instanceof String) && (d3.getSecond() instanceof String)) {
                        String first2 = d3.getFirst();
                        if (first2 != null) {
                            BigDecimal d4 = com.jd.dynamic.lib.expv2.g.d(first2);
                            String second2 = d3.getSecond();
                            if (second2 != null) {
                                return d4.compareTo(com.jd.dynamic.lib.expv2.g.d(second2)) > 0;
                            }
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                        }
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                    }
                    return false;
                }
                return false;
            } else {
                Boolean bool2 = null;
                if (hashCode != 1084) {
                    if (hashCode == 1921) {
                        if (str2.equals("<=")) {
                            Pair<String, String> d5 = d(h2.get(1), h2.get(2), iVar);
                            if ((d5.getFirst() instanceof String) && (d5.getSecond() instanceof String)) {
                                String first3 = d5.getFirst();
                                if (first3 != null) {
                                    BigDecimal d6 = com.jd.dynamic.lib.expv2.g.d(first3);
                                    String second3 = d5.getSecond();
                                    if (second3 != null) {
                                        return d6.compareTo(com.jd.dynamic.lib.expv2.g.d(second3)) <= 0;
                                    }
                                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                                }
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                            }
                            return false;
                        }
                        return false;
                    } else if (hashCode != 1952) {
                        if (hashCode == 1983 && str2.equals(">=")) {
                            Pair<String, String> d7 = d(h2.get(1), h2.get(2), iVar);
                            if ((d7.getFirst() instanceof String) && (d7.getSecond() instanceof String)) {
                                String first4 = d7.getFirst();
                                if (first4 != null) {
                                    BigDecimal d8 = com.jd.dynamic.lib.expv2.g.d(first4);
                                    String second4 = d7.getSecond();
                                    if (second4 != null) {
                                        return d8.compareTo(com.jd.dynamic.lib.expv2.g.d(second4)) >= 0;
                                    }
                                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                                }
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                            }
                            return false;
                        }
                        return false;
                    } else if (!str2.equals("==")) {
                        return false;
                    } else {
                        Pair<String, String> d9 = d(h2.get(1), h2.get(2), iVar);
                        String first5 = d9.getFirst();
                        if (first5 != null) {
                            equals2 = StringsKt__StringsJVMKt.equals(first5, d9.getSecond(), true);
                            bool2 = Boolean.valueOf(equals2);
                        }
                        bool = Boolean.TRUE;
                    }
                } else if (!str2.equals("!=")) {
                    return false;
                } else {
                    Pair<String, String> d10 = d(h2.get(1), h2.get(2), iVar);
                    String first6 = d10.getFirst();
                    if (first6 != null) {
                        equals = StringsKt__StringsJVMKt.equals(first6, d10.getSecond(), true);
                        bool2 = Boolean.valueOf(equals);
                    }
                    bool = Boolean.FALSE;
                }
                return Intrinsics.areEqual(bool, bool2);
            }
        }

        @NotNull
        public final List<String> f(@NotNull String str) {
            List split$default;
            List split$default2;
            List split$default3;
            List split$default4;
            ArrayList arrayList = new ArrayList();
            split$default = StringsKt__StringsKt.split$default((CharSequence) str, new char[]{'?'}, false, 0, 6, (Object) null);
            if (1 == split$default.size()) {
                split$default3 = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{"&&"}, false, 0, 6, (Object) null);
                if (2 <= split$default3.size()) {
                    arrayList.add("&&");
                    arrayList.addAll(split$default3);
                    return arrayList;
                }
                split$default4 = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{"||"}, false, 0, 6, (Object) null);
                if (split$default4.size() >= 2) {
                    arrayList.add("||");
                    arrayList.addAll(split$default4);
                    return arrayList;
                }
                arrayList.add(str);
            } else if (2 == split$default.size()) {
                split$default2 = StringsKt__StringsKt.split$default((CharSequence) split$default.get(1), new char[]{':'}, false, 0, 6, (Object) null);
                if (2 == split$default2.size()) {
                    arrayList.add(split$default.get(0));
                    arrayList.addAll(split$default2);
                }
            }
            return arrayList;
        }
    }

    /* loaded from: classes13.dex */
    public final class b {
        @Nullable
        private final Object a;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public b() {
            this(r0, 1, r0);
            DefaultConstructorMarker defaultConstructorMarker = null;
        }

        public b(@Nullable Object obj) {
            this.a = obj;
        }

        public /* synthetic */ b(Object obj, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? null : obj);
        }

        @Nullable
        public final Object a() {
            return this.a;
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof b) && Intrinsics.areEqual(this.a, ((b) obj).a);
            }
            return true;
        }

        public int hashCode() {
            Object obj = this.a;
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }

        @NotNull
        public String toString() {
            return "ResultContainer(value=" + this.a + ")";
        }
    }

    /* renamed from: com.jd.dynamic.lib.expv2.a.c$c  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public class C0076c {
        public static Object a(Object obj, AttrMethod attrMethod, DynamicTemplateEngine dynamicTemplateEngine) {
            try {
                Method method = obj.getClass().getMethod(attrMethod.methodName, attrMethod.parameterTypes);
                if (method != null) {
                    return method.invoke(obj, attrMethod.args);
                }
                return null;
            } catch (Exception e2) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "TakeValueParser invoke " + attrMethod.methodName + " error", m.j(dynamicTemplateEngine), m.O(dynamicTemplateEngine), e2);
                return null;
            }
        }
    }

    public c(@Nullable DynamicTemplateEngine dynamicTemplateEngine) {
        super(dynamicTemplateEngine, null, null, 6, null);
    }

    @Override // com.jd.dynamic.lib.expv2.a.i
    @Nullable
    public Object a(@NotNull com.jd.dynamic.lib.expv2.c.i iVar) {
        String l2 = iVar.l();
        if (l2 == null || l2.length() <= 3) {
            return l2;
        }
        a aVar = d;
        String c2 = aVar.c(l2);
        List<String> f2 = aVar.f(c2);
        if (f2.isEmpty()) {
            return "";
        }
        if (f2.size() >= 2) {
            return aVar.b(f2, iVar);
        }
        try {
            return Boolean.valueOf(aVar.e(c2, iVar));
        } catch (Exception unused) {
            return "";
        }
    }
}
