package com.jd.dynamic.lib.expv2.c;

import android.view.View;
import com.jd.dynamic.base.DynamicTemplateEngine;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public abstract class i extends h {

    /* renamed from: h  reason: collision with root package name */
    public static final a f2234h = new a(null);
    private com.jd.dynamic.lib.expv2.a.f a;
    @NotNull
    private String b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    private String f2235c;
    @Nullable
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private Object f2236e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    private List<Object> f2237f;

    /* renamed from: g  reason: collision with root package name */
    private Object f2238g;

    /* loaded from: classes13.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String a(int i2) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf('#') + String.valueOf(i2));
            sb.append('`');
            return sb.toString();
        }
    }

    public i(@NotNull String str) {
        this.b = str;
    }

    private final void j(Object obj) {
        this.f2236e = obj;
    }

    @Nullable
    public final Object a(@Nullable Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            List<Object> list = this.f2237f;
            if (!(list == null || list.isEmpty())) {
                String str = (String) obj;
                com.jd.dynamic.lib.expv2.e.a a2 = com.jd.dynamic.lib.expv2.e.c.a(str, '#');
                if (-1 == a2.a()) {
                    return obj;
                }
                com.jd.dynamic.lib.expv2.e.a a3 = com.jd.dynamic.lib.expv2.e.c.a(str, '`');
                if (-1 != a3.a() && a2.a() < a3.a()) {
                    if (a2.a() != 0 || a3.a() != str.length() - 1) {
                        List<Object> list2 = this.f2237f;
                        if (list2 != null) {
                            int size = list2.size();
                            String str2 = str;
                            for (int i2 = 0; i2 < size; i2++) {
                                str2 = StringsKt__StringsJVMKt.replace$default(str2, f2234h.a(i2), list2.get(i2).toString(), false, 4, (Object) null);
                            }
                            return str2;
                        }
                        return str;
                    }
                    String substring = str.substring(1, a3.a());
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin\u2026ing(startIndex, endIndex)");
                    int parseInt = Integer.parseInt(substring);
                    if (parseInt > -1) {
                        List<Object> list3 = this.f2237f;
                        if (list3 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (parseInt > list3.size()) {
                            return null;
                        }
                        List<Object> list4 = this.f2237f;
                        if (list4 == null) {
                            Intrinsics.throwNpe();
                        }
                        return list4.get(parseInt);
                    }
                    return null;
                }
            }
        }
        return obj;
    }

    @Nullable
    public Object b(@Nullable Object obj, @Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable View view) {
        Object obj2 = this.f2238g;
        if (obj2 != null) {
            return obj2;
        }
        Object i2 = i(obj, dynamicTemplateEngine, view);
        if (i2 == null || (i2 instanceof Number)) {
            this.f2238g = i2;
            return i2;
        }
        if (i2 instanceof String) {
            if (((CharSequence) i2).length() == 0) {
                this.f2238g = i2;
                return i2;
            }
        }
        j(i2);
        this.f2238g = "P_H";
        return "P_H";
    }

    @Nullable
    public final Object c(@NotNull String str, @Nullable Object obj, @Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable View view) {
        boolean startsWith$default;
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str, "fun{", false, 2, null);
        if (startsWith$default) {
            return str;
        }
        Object b = b(obj, dynamicTemplateEngine, view);
        Object obj2 = this.f2236e;
        if (obj2 != null) {
            b = obj2;
        }
        return b instanceof Number ? b.toString() : !com.jd.dynamic.lib.expv2.g.a(b) ? "" : a(b);
    }

    public final void d(@NotNull com.jd.dynamic.lib.expv2.a.f fVar) {
        this.a = fVar;
    }

    public final void e(@Nullable List<Object> list) {
        this.f2237f = list;
    }

    public abstract boolean f(@NotNull String str);

    public boolean g() {
        return true;
    }

    @NotNull
    public abstract i h();

    @Nullable
    public abstract Object i(@Nullable Object obj, @Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable View view);

    /* JADX INFO: Access modifiers changed from: protected */
    public final void k(@Nullable String str) {
        this.f2235c = str;
    }

    @Nullable
    public abstract String l();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void m(@Nullable String str) {
        this.d = str;
    }

    public boolean n() {
        return false;
    }

    @Nullable
    public Object o() {
        Object obj = this.f2238g;
        if (obj != null) {
            return obj;
        }
        com.jd.dynamic.lib.expv2.a.f fVar = this.a;
        com.jd.dynamic.lib.expv2.a.i a2 = fVar != null ? fVar.a(this) : null;
        Object a3 = a2 != null ? a2.a(this) : null;
        if (a3 == null || (a3 instanceof Number)) {
            this.f2238g = a3;
            return a3;
        }
        if (a3 instanceof String) {
            if (((CharSequence) a3).length() == 0) {
                this.f2238g = a3;
                return a3;
            }
        }
        j(a3);
        this.f2238g = "P_H";
        return "P_H";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final String p() {
        return this.b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final String q() {
        return this.f2235c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public final String r() {
        return this.d;
    }

    @Nullable
    public final Object s() {
        return this.f2236e;
    }

    @Nullable
    public final List<Object> t() {
        return this.f2237f;
    }

    @Nullable
    public final Object u() {
        Object o = o();
        Object obj = this.f2236e;
        if (obj != null) {
            o = obj;
        }
        return o instanceof Number ? o.toString() : a(o);
    }

    public void v() {
        this.f2238g = null;
        this.f2236e = null;
        this.f2237f = null;
        this.d = null;
    }
}
