package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import android.view.View;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.viewparse.c.t;
import com.jd.dynamic.lib.viewparse.c.u;

/* loaded from: classes13.dex */
public class p<T extends View> implements f<T> {

    /* renamed from: c  reason: collision with root package name */
    protected DynamicTemplateEngine f2460c;
    private com.jd.dynamic.lib.viewparse.c.f<T> a = new u();
    private com.jd.dynamic.lib.viewparse.c.f<T> b = new t();
    protected boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    protected boolean f2461e = false;

    /* renamed from: f  reason: collision with root package name */
    protected boolean f2462f = false;

    public void b(DynamicTemplateEngine dynamicTemplateEngine) {
        this.f2460c = dynamicTemplateEngine;
    }

    public void c(boolean z) {
        this.d = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T d(Context context) {
        return (T) new View(context);
    }

    protected T e(Context context, ViewNode viewNode) {
        return (T) new View(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00b8  */
    @Override // com.jd.dynamic.lib.viewparse.e.f
    /* renamed from: f  reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public T a(com.jd.dynamic.entity.ViewNode r5, android.content.Context r6) {
        /*
            r4 = this;
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r1 = 0
            java.lang.String r2 = "ViewImp"
            r0[r1] = r2
            r1 = 1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "after createView "
            r2.append(r3)
            java.lang.String r3 = r5.getViewName()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0[r1] = r2
            com.jd.dynamic.lib.utils.t.e(r0)
            java.lang.System.nanoTime()
            android.view.View r0 = r4.d(r6)
            if (r0 != 0) goto L30
            android.view.View r0 = r4.e(r6, r5)
        L30:
            if (r0 == 0) goto L63
            int r6 = r5.viewId
            r0.setId(r6)
            java.util.HashMap r6 = r5.getAttributes()
            if (r6 == 0) goto L58
            java.util.HashMap r6 = r5.getAttributes()
            java.lang.String r1 = "layoutId"
            java.lang.Object r6 = r6.get(r1)
            java.lang.String r6 = (java.lang.String) r6
            boolean r1 = android.text.TextUtils.isEmpty(r6)
            if (r1 != 0) goto L58
            int r1 = r0.getId()
            com.jd.dynamic.base.DynamicTemplateEngine r2 = r4.f2460c
            com.jd.dynamic.lib.dynamic.parser.h.c(r2, r6, r1)
        L58:
            boolean r6 = r4.f2462f
            if (r6 == 0) goto L63
            int r6 = com.jd.dynamic.R.id.dynamic_tag_view_item
            java.lang.Object r1 = com.jd.dynamic.b.c.a.b
            r0.setTag(r6, r1)
        L63:
            java.util.HashMap r6 = r5.getAttributes()
            java.lang.String r1 = "visibility"
            java.lang.Object r6 = r6.get(r1)
            java.lang.String r6 = (java.lang.String) r6
            if (r0 == 0) goto L82
            boolean r1 = com.jd.dynamic.base.DynamicUtils.isEL(r6)
            if (r1 != 0) goto L7d
            boolean r6 = com.jd.dynamic.base.DynamicUtils.isKnownSymbol(r6)
            if (r6 == 0) goto L82
        L7d:
            r6 = 8
            r0.setVisibility(r6)
        L82:
            java.util.HashMap r6 = r5.getAttributes()
            java.util.HashMap r6 = com.jd.dynamic.lib.utils.m.Q(r6)
            boolean r1 = com.jd.dynamic.lib.utils.m.J(r6)
            if (r1 != 0) goto L91
            return r0
        L91:
            boolean r1 = r4.d
            if (r1 == 0) goto La2
            com.jd.dynamic.lib.viewparse.c.f<T extends android.view.View> r5 = r4.a
            com.jd.dynamic.base.DynamicTemplateEngine r1 = r4.f2460c
            r5.b(r1)
            com.jd.dynamic.lib.viewparse.c.f<T extends android.view.View> r5 = r4.a
        L9e:
            r5.a(r6, r0)
            goto Lb4
        La2:
            boolean r5 = r5.unNeedInitBind
            if (r5 == 0) goto Laa
            boolean r5 = r4.f2461e
            if (r5 == 0) goto Lb4
        Laa:
            com.jd.dynamic.lib.viewparse.c.f<T extends android.view.View> r5 = r4.b
            com.jd.dynamic.base.DynamicTemplateEngine r1 = r4.f2460c
            r5.b(r1)
            com.jd.dynamic.lib.viewparse.c.f<T extends android.view.View> r5 = r4.b
            goto L9e
        Lb4:
            com.jd.dynamic.base.DynamicTemplateEngine r5 = r4.f2460c
            if (r5 == 0) goto Lc3
            com.jd.dynamic.base.CachePool r5 = r5.getCachePool()
            int r6 = r0.getId()
            r5.putViewIntoCache(r6, r0)
        Lc3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.lib.viewparse.e.p.a(com.jd.dynamic.entity.ViewNode, android.content.Context):android.view.View");
    }

    public void g(boolean z) {
        this.f2461e = z;
    }

    public void h(boolean z) {
        this.f2462f = z;
    }
}
