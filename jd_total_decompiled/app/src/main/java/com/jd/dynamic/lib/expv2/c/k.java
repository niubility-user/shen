package com.jd.dynamic.lib.expv2.c;

import android.view.View;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.lib.expv2.c.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes13.dex */
public abstract class k extends i {

    /* renamed from: i  reason: collision with root package name */
    private final Object f2239i;

    /* renamed from: j  reason: collision with root package name */
    private List<i> f2240j;

    public k(@NotNull String str) {
        super(str);
        this.f2239i = new Object();
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    @Nullable
    public Object i(@Nullable Object obj, @Nullable DynamicTemplateEngine dynamicTemplateEngine, @Nullable View view) {
        int size;
        List<Object> t;
        String r = r();
        if (r == null || r.length() == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(p());
            List<i> list = this.f2240j;
            if (list != null) {
                for (i iVar : list) {
                    Object b = iVar.b(obj, dynamicTemplateEngine, view);
                    if (b == null || (b instanceof String)) {
                        List<Object> t2 = t();
                        if (t2 == null || t2.isEmpty()) {
                            e(new ArrayList());
                            size = 0;
                        } else {
                            List<Object> t3 = t();
                            if (t3 == null) {
                                Intrinsics.throwNpe();
                            }
                            size = t3.size();
                        }
                        if (Intrinsics.areEqual("P_H", b)) {
                            Object s = iVar.s();
                            if (s != null) {
                                sb.append(i.f2234h.a(size));
                                List<Object> t4 = t();
                                if (t4 != null) {
                                    t4.add(s);
                                }
                            }
                        } else if (iVar.n()) {
                            if (b == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                            }
                            String str = (String) b;
                            List<Object> t5 = iVar.t();
                            if (t5 != null) {
                                Iterator<T> it = t5.iterator();
                                String str2 = str;
                                int i2 = 0;
                                while (it.hasNext()) {
                                    it.next();
                                    int i3 = i2 + 1;
                                    if (i2 < 0) {
                                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                                    }
                                    i.a aVar = i.f2234h;
                                    str2 = StringsKt__StringsJVMKt.replace$default(str2, aVar.a(i2), aVar.a(i2 + size), false, 4, (Object) null);
                                    i2 = i3;
                                }
                                str = str2;
                            }
                            if (t5 != null && (t = t()) != null) {
                                t.addAll(t5);
                            }
                            sb.append(str);
                        }
                    }
                    sb.append(b);
                }
            }
            sb.append(q());
            m(sb.toString());
            return r();
        }
        return r();
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    @Nullable
    public String l() {
        int size;
        List<Object> t;
        String r = r();
        if (r == null || r.length() == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(p());
            List<i> list = this.f2240j;
            if (list != null) {
                for (i iVar : list) {
                    Object o = iVar.o();
                    if (o == null || (o instanceof String)) {
                        List<Object> t2 = t();
                        if (t2 == null || t2.isEmpty()) {
                            e(new ArrayList());
                            size = 0;
                        } else {
                            List<Object> t3 = t();
                            if (t3 == null) {
                                Intrinsics.throwNpe();
                            }
                            size = t3.size();
                        }
                        if (Intrinsics.areEqual("P_H", o)) {
                            Object s = iVar.s();
                            if (s != null) {
                                sb.append(i.f2234h.a(size));
                                List<Object> t4 = t();
                                if (t4 != null) {
                                    t4.add(s);
                                }
                            }
                        } else if (iVar.n()) {
                            if (o == null) {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                            }
                            String str = (String) o;
                            List<Object> t5 = iVar.t();
                            if (t5 != null) {
                                Iterator<T> it = t5.iterator();
                                String str2 = str;
                                int i2 = 0;
                                while (it.hasNext()) {
                                    it.next();
                                    int i3 = i2 + 1;
                                    if (i2 < 0) {
                                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                                    }
                                    i.a aVar = i.f2234h;
                                    str2 = StringsKt__StringsJVMKt.replace$default(str2, aVar.a(i2), aVar.a(i2 + size), false, 4, (Object) null);
                                    i2 = i3;
                                }
                                str = str2;
                            }
                            if (t5 != null && (t = t()) != null) {
                                t.addAll(t5);
                            }
                            sb.append(str);
                        }
                    }
                    sb.append(o);
                }
            }
            sb.append(q());
            m(sb.toString());
            return r();
        }
        return r();
    }

    @Override // com.jd.dynamic.lib.expv2.c.i
    public void v() {
        List<i> list = this.f2240j;
        if (list != null) {
            Iterator<i> it = list.iterator();
            while (it.hasNext()) {
                it.next().v();
            }
        }
        super.v();
    }

    public final void w(@NotNull i iVar) {
        synchronized (this.f2239i) {
            if (this.f2240j == null) {
                this.f2240j = new ArrayList();
            }
            List<i> list = this.f2240j;
            if (list != null) {
                list.add(iVar);
            }
        }
    }

    public boolean x() {
        return true;
    }

    public final void y(@Nullable List<? extends i> list) {
        if (list == null) {
            return;
        }
        this.f2240j = new ArrayList(list);
    }

    @Nullable
    public final List<i> z() {
        if (this.f2240j == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        List<i> list = this.f2240j;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((i) it.next()).h());
            }
        }
        return arrayList;
    }
}
