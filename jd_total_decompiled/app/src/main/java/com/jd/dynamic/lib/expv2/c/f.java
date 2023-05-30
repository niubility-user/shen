package com.jd.dynamic.lib.expv2.c;

import android.view.View;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.CachePool;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.entity.AttrMethod;
import com.jd.dynamic.lib.expv2.a.c;
import com.jd.dynamic.lib.expv2.a.g;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public final class f extends k {
    public f(@NotNull String str) {
        super(str);
    }

    private final Object A(Object obj, String str, DynamicTemplateEngine dynamicTemplateEngine, View view) {
        List split$default;
        if (com.jd.dynamic.lib.expv2.g.a(str)) {
            ArrayList arrayList = new ArrayList();
            split$default = StringsKt__StringsKt.split$default((CharSequence) str, new char[]{OrderISVUtil.MONEY_DECIMAL_CHAR}, false, 0, 6, (Object) null);
            Iterator it = split$default.iterator();
            while (it.hasNext()) {
                arrayList.add((String) it.next());
            }
            return obj instanceof JSONArray ? com.jd.dynamic.lib.expv2.a.g.d.c(obj, str, this) : B(obj, arrayList, this, dynamicTemplateEngine, view);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:625:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:631:0x0052 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:648:0x01c9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:673:0x0018 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v76 */
    /* JADX WARN: Type inference failed for: r0v77, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v78, types: [org.json.JSONArray] */
    /* JADX WARN: Type inference failed for: r0v79, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Object B(Object obj, List<? extends Object> list, i iVar, DynamicTemplateEngine dynamicTemplateEngine, View view) {
        Object obj2;
        boolean contains$default;
        boolean equals;
        CachePool cachePool;
        CachePool cachePool2;
        boolean equals2;
        boolean equals3;
        long nanoTime = System.nanoTime();
        Object obj3 = !(obj instanceof View) ? obj : null;
        Iterator<? extends Object> it = list.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (!(next instanceof String) || !com.jd.dynamic.lib.expv2.g.b((String) next) || (next = iVar.a(next)) != null) {
                if ((obj3 instanceof String) && 1 == com.jd.dynamic.lib.expv2.g.c((String) obj3)) {
                    try {
                        obj2 = new JSONObject((String) obj3);
                    } catch (Exception unused) {
                    }
                    if (!(next instanceof c.b)) {
                        obj3 = ((c.b) next).a();
                    } else if ((next instanceof JSONObject) || (next instanceof JSONArray)) {
                        obj3 = next;
                    } else {
                        if (next instanceof String) {
                            String str = (String) next;
                            if (1 == com.jd.dynamic.lib.expv2.g.c(str)) {
                                try {
                                    obj3 = new JSONObject((String) next);
                                } catch (Exception unused2) {
                                }
                            }
                            contains$default = StringsKt__StringsKt.contains$default((CharSequence) next, '[', false, 2, (Object) null);
                            if (contains$default) {
                                g.a aVar = com.jd.dynamic.lib.expv2.a.g.d;
                                Pair<String, String> l2 = aVar.l(str);
                                if (!(l2.getFirst().length() == 0)) {
                                    if ((l2.getSecond().length() == 0) || !(obj2 instanceof JSONObject)) {
                                        return obj2;
                                    }
                                    Object opt = ((JSONObject) obj2).opt(l2.getFirst());
                                    obj3 = opt instanceof JSONArray ? aVar.f((JSONArray) opt, l2.getSecond(), iVar) : opt;
                                    if (obj3 == null) {
                                        return obj3;
                                    }
                                }
                                return obj2;
                            }
                            if (com.jd.dynamic.lib.expv2.a.g.d.h(str)) {
                                if (obj2 instanceof String) {
                                    return Integer.valueOf(((String) obj2).length());
                                }
                                if (obj2 instanceof JSONObject) {
                                    try {
                                        obj3 = ((JSONObject) obj2).opt((String) next);
                                        if (obj3 != null) {
                                            continue;
                                        }
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                            String D = D(str);
                            if (D.length() > 0) {
                                if (Intrinsics.areEqual("count", D) && (obj2 instanceof JSONObject)) {
                                    try {
                                        if (((JSONObject) obj2).opt((String) next) == null) {
                                            return Integer.valueOf(((JSONObject) obj2).length());
                                        }
                                        obj3 = ((JSONObject) obj2).opt((String) next);
                                    } catch (Exception e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                return (Intrinsics.areEqual("$count", D) && (obj2 instanceof JSONObject)) ? Integer.valueOf(((JSONObject) obj2).length()) : C(obj2);
                            } else if (Intrinsics.areEqual("scopedCacheKey", next)) {
                                obj3 = com.jd.dynamic.lib.expv2.a.g.d.e(it, iVar);
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
                                                if (dynamicTemplateEngine != null && (cachePool2 = dynamicTemplateEngine.getCachePool()) != null) {
                                                    obj3 = cachePool2.getDataObj((String) next2);
                                                    if (obj3 != null) {
                                                        return obj3;
                                                    }
                                                }
                                                obj3 = null;
                                                if (obj3 != null) {
                                                }
                                            } else {
                                                if (dynamicTemplateEngine != null && (cachePool = dynamicTemplateEngine.getCachePool()) != null) {
                                                    obj3 = cachePool.getData((String) next2);
                                                    if (obj3 != null) {
                                                    }
                                                }
                                                obj3 = null;
                                                if (obj3 != null) {
                                                }
                                            }
                                        }
                                    }
                                    obj3 = obj2;
                                } else {
                                    equals2 = StringsKt__StringsJVMKt.equals("targetView", str, true);
                                    if (equals2) {
                                        obj3 = view;
                                    } else if (Intrinsics.areEqual("container", next)) {
                                        obj3 = com.jd.dynamic.lib.expv2.a.g.d.d(it, dynamicTemplateEngine, iVar);
                                    } else {
                                        equals3 = StringsKt__StringsJVMKt.equals("object", str, true);
                                        if (equals3) {
                                            obj3 = obj;
                                        } else if (obj2 instanceof JSONObject) {
                                            obj3 = ((JSONObject) obj2).opt(str);
                                        } else if (obj2 instanceof JSONArray) {
                                            obj3 = new JSONArray();
                                            JSONArray jSONArray = (JSONArray) obj2;
                                            int length = jSONArray.length();
                                            for (int i2 = 0; i2 < length; i2++) {
                                                Object obj4 = jSONArray.get(i2);
                                                if (obj4 instanceof JSONObject) {
                                                    obj3.put(((JSONObject) obj4).opt(str));
                                                }
                                            }
                                        } else {
                                            AttrMethod method = CachePool.getMethod(str);
                                            if (method != null) {
                                                obj3 = c.C0076c.a(obj2, method, dynamicTemplateEngine);
                                                DYConstants.DYLog("result is : " + ((Object) obj3));
                                            } else {
                                                obj3 = 0;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            obj3 = obj2;
                        }
                        if (obj3 == 0) {
                            return obj3;
                        }
                    }
                }
                obj2 = obj3;
                if (!(next instanceof c.b)) {
                }
            }
        }
        DYConstants.DYLog("take value use time is : " + (System.nanoTime() - nanoTime));
        return obj3;
    }

    private final Object C(Object obj) {
        int length;
        if (obj instanceof JSONArray) {
            length = ((JSONArray) obj).length();
        } else if (obj instanceof JSONObject) {
            length = ((JSONObject) obj).length();
        } else if (!(obj instanceof String)) {
            return obj;
        } else {
            length = ((String) obj).length();
        }
        return Integer.valueOf(length);
    }

    private final String D(String str) {
        boolean equals;
        boolean equals2;
        equals = StringsKt__StringsJVMKt.equals("count", str, true);
        if (equals) {
            return "count";
        }
        equals2 = StringsKt__StringsJVMKt.equals("$count", str, true);
        return equals2 ? "$count" : "";
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    public boolean f(@NotNull String str) {
        k(str);
        return Intrinsics.areEqual(String.valueOf('}'), str);
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    @NotNull
    public i h() {
        f fVar = new f(p());
        fVar.k(q());
        fVar.y(z());
        return fVar;
    }

    @Override // com.jd.dynamic.lib.expv2.c.k, com.jd.dynamic.lib.expv2.c.i
    @Nullable
    public Object i(@Nullable Object obj, @Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable View view) {
        System.nanoTime();
        Object i2 = super.i(obj, dynamicTemplateEngine, view);
        if (i2 != null && (i2 instanceof String)) {
            String str = (String) i2;
            if (str.length() > 3) {
                return A(obj, com.jd.dynamic.lib.expv2.a.c.d.c(str), dynamicTemplateEngine, view);
            }
        }
        return i2;
    }
}
