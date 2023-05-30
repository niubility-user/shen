package com.jd.dynamic.lib.expv2.c;

import android.view.View;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    /* JADX WARN: Removed duplicated region for block: B:398:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:404:0x0052 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:421:0x01c9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:446:0x0018 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v76 */
    /* JADX WARN: Type inference failed for: r0v77, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v78, types: [org.json.JSONArray] */
    /* JADX WARN: Type inference failed for: r0v79, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.Object B(java.lang.Object r17, java.util.List<? extends java.lang.Object> r18, com.jd.dynamic.lib.expv2.c.i r19, com.jd.dynamic.base.DynamicTemplateEngine r20, android.view.View r21) {
        /*
            Method dump skipped, instructions count: 614
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.expv2.c.f.B(java.lang.Object, java.util.List, com.jd.dynamic.lib.expv2.c.i, com.jd.dynamic.base.DynamicTemplateEngine, android.view.View):java.lang.Object");
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
