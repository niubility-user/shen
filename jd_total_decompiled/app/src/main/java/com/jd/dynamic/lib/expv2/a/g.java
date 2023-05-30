package com.jd.dynamic.lib.expv2.a;

import android.view.View;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.CachePool;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.entity.AttrMethod;
import com.jd.dynamic.lib.expv2.a.c;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public final class g extends com.jd.dynamic.lib.expv2.a.a.a {
    public static final a d = new a(null);

    /* loaded from: classes13.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final Integer a(Object obj, Object obj2, DynamicTemplateEngine dynamicTemplateEngine) {
            int i2;
            if (Intrinsics.areEqual("estimatedSize", obj)) {
                if (Intrinsics.areEqual("height", obj2)) {
                    if (dynamicTemplateEngine == null) {
                        return null;
                    }
                    i2 = dynamicTemplateEngine.containerHeight;
                } else if (Intrinsics.areEqual("width", obj2)) {
                    if (dynamicTemplateEngine == null) {
                        return null;
                    }
                    i2 = dynamicTemplateEngine.containerWidth;
                }
                return Integer.valueOf(i2);
            }
            return 0;
        }

        private final Object b(Object obj, com.jd.dynamic.lib.expv2.c.i iVar) {
            return ((obj instanceof String) && com.jd.dynamic.lib.expv2.g.b((String) obj)) ? iVar.a(obj) : obj;
        }

        @Nullable
        public final Object c(@Nullable Object obj, @NotNull Object obj2, @NotNull com.jd.dynamic.lib.expv2.c.i iVar) {
            boolean contains$default;
            if (obj2 instanceof String) {
                contains$default = StringsKt__StringsKt.contains$default((CharSequence) obj2, '[', false, 2, (Object) null);
                if (contains$default) {
                    String str = (String) obj2;
                    if (i(str)) {
                        Pair<String, Pair<Integer, Integer>> j2 = j(str);
                        if (Intrinsics.areEqual("", j2.getFirst()) && (obj instanceof JSONArray)) {
                            return g((JSONArray) obj, j2.getSecond());
                        }
                        if (obj instanceof JSONObject) {
                            obj = ((JSONObject) obj).get(j2.getFirst());
                        }
                        return obj instanceof JSONArray ? g((JSONArray) obj, j2.getSecond()) : obj;
                    }
                    Pair<String, String> l2 = l(str);
                    if (l2.getSecond().length() == 0) {
                        return obj;
                    }
                    if ((l2.getFirst().length() == 0) && (obj instanceof JSONArray)) {
                        return f((JSONArray) obj, l2.getSecond(), iVar);
                    }
                    if (obj instanceof JSONObject) {
                        obj = ((JSONObject) obj).get(l2.getFirst());
                    }
                    return obj instanceof JSONArray ? f((JSONArray) obj, l2.getSecond(), iVar) : obj;
                }
                return obj;
            }
            return obj;
        }

        @Nullable
        public final Object d(@NotNull Iterator<? extends Object> it, @Nullable DynamicTemplateEngine dynamicTemplateEngine, @NotNull com.jd.dynamic.lib.expv2.c.i iVar) {
            Object obj;
            Object obj2;
            if (it.hasNext()) {
                obj2 = b(it.next(), iVar);
                obj = it.hasNext() ? b(it.next(), iVar) : null;
            } else {
                obj = null;
                obj2 = null;
            }
            if (Intrinsics.areEqual("estimatedSize", obj2)) {
                return a(obj2, obj, dynamicTemplateEngine);
            }
            return null;
        }

        @Nullable
        public final Object e(@NotNull Iterator<? extends Object> it, @NotNull com.jd.dynamic.lib.expv2.c.i iVar) {
            Object obj;
            Object obj2;
            if (it.hasNext()) {
                obj2 = b(it.next(), iVar);
                obj = it.hasNext() ? b(it.next(), iVar) : null;
            } else {
                obj = null;
                obj2 = null;
            }
            if ((obj2 instanceof String) && (obj instanceof String)) {
                return com.jd.dynamic.b.m.d.a.b.a((String) obj2, (String) obj);
            }
            return null;
        }

        @Nullable
        public final Object f(@NotNull JSONArray jSONArray, @Nullable Object obj, @NotNull com.jd.dynamic.lib.expv2.c.i iVar) {
            if (obj != null && (obj instanceof String)) {
                if (com.jd.dynamic.lib.expv2.g.b((String) obj)) {
                    obj = iVar.a(obj);
                }
                if (!(obj instanceof String)) {
                    obj = null;
                }
                int k2 = k((String) obj);
                if (Integer.MIN_VALUE != k2 && k2 >= 0 && k2 < jSONArray.length()) {
                    return jSONArray.get(k2);
                }
            }
            return null;
        }

        @Nullable
        public final Object g(@NotNull JSONArray jSONArray, @NotNull Pair<Integer, Integer> pair) {
            int i2;
            int length = jSONArray.length();
            int intValue = pair.getFirst().intValue();
            int intValue2 = pair.getSecond().intValue();
            if (intValue2 <= 0) {
                return null;
            }
            if (Integer.MAX_VALUE == intValue2) {
                intValue2 = intValue < 0 ? -intValue : length - intValue;
            }
            if (intValue2 > jSONArray.length()) {
                return null;
            }
            if ((intValue >= 0 || (intValue = intValue + length) >= 0) && (i2 = intValue2 + intValue) <= length) {
                JSONArray jSONArray2 = new JSONArray();
                while (intValue < i2) {
                    jSONArray2.put(jSONArray.get(intValue));
                    intValue++;
                }
                return jSONArray2;
            }
            return null;
        }

        public final boolean h(@NotNull String str) {
            boolean equals;
            equals = StringsKt__StringsJVMKt.equals(CartConstant.KEY_LENGTH, str, true);
            return equals;
        }

        public final boolean i(@NotNull String str) {
            int indexOf$default;
            int indexOf$default2;
            int indexOf$default3;
            indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) str, '[', 0, false, 6, (Object) null);
            indexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) str, ":", 0, false, 6, (Object) null);
            indexOf$default3 = StringsKt__StringsKt.indexOf$default((CharSequence) str, ']', 0, false, 6, (Object) null);
            return indexOf$default >= 0 && indexOf$default2 > indexOf$default && indexOf$default3 > indexOf$default2;
        }

        @NotNull
        public final Pair<String, Pair<Integer, Integer>> j(@NotNull String str) {
            int indexOf$default;
            int indexOf$default2;
            int indexOf$default3;
            int k2;
            indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) str, '[', 0, false, 6, (Object) null);
            indexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) str, ":", 0, false, 6, (Object) null);
            indexOf$default3 = StringsKt__StringsKt.indexOf$default((CharSequence) str, ']', 0, false, 6, (Object) null);
            int i2 = 0;
            String substring = str.substring(0, indexOf$default);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
            if (indexOf$default2 - indexOf$default != 1) {
                String substring2 = str.substring(indexOf$default + 1, indexOf$default2);
                Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                i2 = k(substring2);
            }
            if (indexOf$default3 - indexOf$default2 == 1) {
                k2 = Integer.MAX_VALUE;
            } else {
                String substring3 = str.substring(indexOf$default2 + 1, indexOf$default3);
                Intrinsics.checkExpressionValueIsNotNull(substring3, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                k2 = k(substring3);
            }
            return k2 <= i2 ? new Pair<>("", new Pair(-1, -1)) : new Pair<>(substring, new Pair(Integer.valueOf(i2), Integer.valueOf(k2)));
        }

        public final int k(@Nullable String str) {
            boolean contains$default;
            if (str == null) {
                return Integer.MIN_VALUE;
            }
            try {
                contains$default = StringsKt__StringsKt.contains$default((CharSequence) str, '[', false, 2, (Object) null);
                if (contains$default) {
                    str = str.substring(0, str.length() - 1);
                    Intrinsics.checkExpressionValueIsNotNull(str, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                }
                return (int) Float.parseFloat(str);
            } catch (Exception unused) {
                return Integer.MIN_VALUE;
            }
        }

        @NotNull
        public final Pair<String, String> l(@NotNull String str) {
            int indexOf$default;
            int indexOf$default2;
            int indexOf$default3;
            try {
                indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) str, '[', 0, false, 6, (Object) null);
                String substring = str.substring(0, indexOf$default);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                indexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) str, '[', 0, false, 6, (Object) null);
                indexOf$default3 = StringsKt__StringsKt.indexOf$default((CharSequence) str, ']', 0, false, 6, (Object) null);
                String substring2 = str.substring(indexOf$default2 + 1, indexOf$default3);
                Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                return new Pair<>(substring, substring2);
            } catch (Exception unused) {
                return new Pair<>("", "");
            }
        }
    }

    public g(@Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable Object obj, @Nullable View view) {
        super(dynamicTemplateEngine, obj, view);
    }

    private final Object e(Object obj, String str, com.jd.dynamic.lib.expv2.c.i iVar) {
        List<? extends Object> split$default;
        if (com.jd.dynamic.lib.expv2.g.a(str)) {
            split$default = StringsKt__StringsKt.split$default((CharSequence) str, new char[]{OrderISVUtil.MONEY_DECIMAL_CHAR}, false, 0, 6, (Object) null);
            return obj instanceof JSONArray ? d.c(obj, str, iVar) : f(obj, split$default, iVar);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:672:0x0228 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:692:0x0014 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Object f(Object obj, List<? extends Object> list, com.jd.dynamic.lib.expv2.c.i iVar) {
        Object jSONObject;
        boolean contains$default;
        int length;
        boolean equals;
        CachePool cachePool;
        CachePool cachePool2;
        boolean equals2;
        boolean equals3;
        Object obj2 = !(obj instanceof View) ? obj : null;
        Iterator<? extends Object> it = list.iterator();
        long nanoTime = System.nanoTime();
        while (it.hasNext()) {
            long nanoTime2 = System.nanoTime();
            Object next = it.next();
            if (!(next instanceof String) || !com.jd.dynamic.lib.expv2.g.b((String) next) || (next = iVar.a(next)) != null) {
                if ((obj2 instanceof String) && 1 == com.jd.dynamic.lib.expv2.g.c((String) obj2)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject((String) obj2);
                        try {
                            DYConstants.DYLog("str 2 json : " + (System.nanoTime() - nanoTime2));
                        } catch (Exception unused) {
                        }
                        obj2 = jSONObject2;
                    } catch (Exception unused2) {
                    }
                }
                if (next instanceof c.b) {
                    obj2 = ((c.b) next).a();
                } else if ((next instanceof JSONObject) || (next instanceof JSONArray)) {
                    obj2 = next;
                } else {
                    if (next instanceof String) {
                        String str = (String) next;
                        if (1 == com.jd.dynamic.lib.expv2.g.c(str)) {
                            try {
                                jSONObject = new JSONObject((String) next);
                                try {
                                    DYConstants.DYLog(" key is json : " + (System.nanoTime() - nanoTime2));
                                } catch (Exception unused3) {
                                    obj2 = jSONObject;
                                }
                            } catch (Exception unused4) {
                            }
                            obj2 = jSONObject;
                        }
                        jSONObject = obj2;
                        contains$default = StringsKt__StringsKt.contains$default((CharSequence) next, '[', false, 2, (Object) null);
                        if (contains$default) {
                            a aVar = d;
                            Pair<String, String> l2 = aVar.l(str);
                            if (!(l2.getFirst().length() == 0)) {
                                if ((l2.getSecond().length() == 0) || !(jSONObject instanceof JSONObject)) {
                                    return jSONObject;
                                }
                                Object opt = ((JSONObject) jSONObject).opt(l2.getFirst());
                                obj2 = opt instanceof JSONArray ? aVar.f((JSONArray) opt, l2.getSecond(), iVar) : opt;
                                if (obj2 == null) {
                                    return obj2;
                                }
                            }
                            return jSONObject;
                        }
                        if (d.h(str)) {
                            if (jSONObject instanceof String) {
                                return Integer.valueOf(((String) jSONObject).length());
                            }
                            if (jSONObject instanceof JSONObject) {
                                try {
                                    obj2 = ((JSONObject) jSONObject).opt((String) next);
                                    if (obj2 != null) {
                                        continue;
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                        String g2 = g(str);
                        if (g2.length() > 0) {
                            if (Intrinsics.areEqual("count", g2) && (jSONObject instanceof JSONObject)) {
                                try {
                                    if (((JSONObject) jSONObject).opt((String) next) == null) {
                                        return Integer.valueOf(((JSONObject) jSONObject).length());
                                    }
                                    obj2 = ((JSONObject) jSONObject).opt((String) next);
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                            if (Intrinsics.areEqual("$count", g2) && (jSONObject instanceof JSONObject)) {
                                return Integer.valueOf(((JSONObject) jSONObject).length());
                            }
                            if (jSONObject instanceof JSONArray) {
                                length = ((JSONArray) jSONObject).length();
                            } else if (jSONObject instanceof JSONObject) {
                                length = ((JSONObject) jSONObject).length();
                            } else if (!(jSONObject instanceof String)) {
                                return jSONObject;
                            } else {
                                length = ((String) jSONObject).length();
                            }
                            return Integer.valueOf(length);
                        } else if (Intrinsics.areEqual("scopedCacheKey", next)) {
                            obj2 = d.e(it, iVar);
                        } else {
                            equals = StringsKt__StringsJVMKt.equals("cacheKey", str, true);
                            if (equals) {
                                if (it.hasNext()) {
                                    Object next2 = it.next();
                                    if ((next2 instanceof String) && com.jd.dynamic.lib.expv2.g.b((String) next2) && (next2 = iVar.a(next2)) == null) {
                                        return null;
                                    }
                                    if (next2 instanceof String) {
                                        com.jd.dynamic.b.a.b o = com.jd.dynamic.b.a.b.o();
                                        Intrinsics.checkExpressionValueIsNotNull(o, "DYABConfigUtil.getInstance()");
                                        if (o.e()) {
                                            DynamicTemplateEngine b = b();
                                            if (b != null && (cachePool2 = b.getCachePool()) != null) {
                                                obj2 = cachePool2.getDataObj((String) next2);
                                                if (obj2 != null) {
                                                    return obj2;
                                                }
                                            }
                                            obj2 = null;
                                            if (obj2 != null) {
                                            }
                                        } else {
                                            DynamicTemplateEngine b2 = b();
                                            if (b2 != null && (cachePool = b2.getCachePool()) != null) {
                                                obj2 = cachePool.getData((String) next2);
                                                if (obj2 != null) {
                                                }
                                            }
                                            obj2 = null;
                                            if (obj2 != null) {
                                            }
                                        }
                                    }
                                }
                                obj2 = jSONObject;
                            } else {
                                equals2 = StringsKt__StringsJVMKt.equals("targetView", str, true);
                                if (equals2) {
                                    obj2 = d();
                                } else if (Intrinsics.areEqual("container", next)) {
                                    obj2 = d.d(it, b(), iVar);
                                } else {
                                    equals3 = StringsKt__StringsJVMKt.equals("object", str, true);
                                    if (equals3) {
                                        obj2 = obj;
                                    } else {
                                        if (jSONObject instanceof JSONObject) {
                                            obj2 = ((JSONObject) jSONObject).opt(str);
                                        } else {
                                            AttrMethod method = CachePool.getMethod(str);
                                            if (method != null) {
                                                obj2 = c.C0076c.a(jSONObject, method, b());
                                                DYConstants.DYLog("result is : " + obj2);
                                            } else {
                                                obj2 = null;
                                            }
                                        }
                                        DYConstants.DYLog("after opt key : " + (System.nanoTime() - nanoTime2) + " key is : " + next);
                                    }
                                }
                            }
                        }
                    }
                    if (obj2 == null) {
                        return obj2;
                    }
                }
            }
        }
        DYConstants.DYLog("return time " + (System.nanoTime() - nanoTime) + " obj inner: " + obj2);
        return obj2;
    }

    private final String g(String str) {
        boolean equals;
        boolean equals2;
        equals = StringsKt__StringsJVMKt.equals("count", str, true);
        if (equals) {
            return "count";
        }
        equals2 = StringsKt__StringsJVMKt.equals("$count", str, true);
        return equals2 ? "$count" : "";
    }

    @Override // com.jd.dynamic.lib.expv2.a.i
    @Nullable
    public Object a(@NotNull com.jd.dynamic.lib.expv2.c.i iVar) {
        String l2 = iVar.l();
        if (l2 == null || l2.length() <= 3 || !(iVar instanceof com.jd.dynamic.lib.expv2.c.f)) {
            return l2;
        }
        return e(c(), c.d.c(l2), iVar);
    }
}
