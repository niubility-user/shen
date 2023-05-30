package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicTemplateEngine;
import com.jd.dynamic.base.DynamicUtils;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.viewparse.c.t;
import com.jd.dynamic.lib.viewparse.c.u;
import java.util.HashMap;

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
    */
    public T a(ViewNode viewNode, Context context) {
        com.jd.dynamic.lib.viewparse.c.f<T> fVar;
        DynamicTemplateEngine dynamicTemplateEngine;
        com.jd.dynamic.lib.utils.t.e("ViewImp", "after createView " + viewNode.getViewName());
        System.nanoTime();
        T d = d(context);
        if (d == null) {
            d = e(context, viewNode);
        }
        if (d != null) {
            d.setId(viewNode.viewId);
            if (viewNode.getAttributes() != null) {
                String str = viewNode.getAttributes().get("layoutId");
                if (!TextUtils.isEmpty(str)) {
                    com.jd.dynamic.lib.dynamic.parser.h.c(this.f2460c, str, d.getId());
                }
            }
            if (this.f2462f) {
                d.setTag(R.id.dynamic_tag_view_item, com.jd.dynamic.b.c.a.b);
            }
        }
        String str2 = viewNode.getAttributes().get("visibility");
        if (d != null && (DynamicUtils.isEL(str2) || DynamicUtils.isKnownSymbol(str2))) {
            d.setVisibility(8);
        }
        HashMap<String, String> Q = com.jd.dynamic.lib.utils.m.Q(viewNode.getAttributes());
        if (com.jd.dynamic.lib.utils.m.J(Q)) {
            if (!this.d) {
                if (!viewNode.unNeedInitBind || this.f2461e) {
                    this.b.b(this.f2460c);
                    fVar = this.b;
                }
                dynamicTemplateEngine = this.f2460c;
                if (dynamicTemplateEngine != null) {
                    dynamicTemplateEngine.getCachePool().putViewIntoCache(d.getId(), d);
                }
                return d;
            }
            this.a.b(this.f2460c);
            fVar = this.a;
            fVar.a(Q, d);
            dynamicTemplateEngine = this.f2460c;
            if (dynamicTemplateEngine != null) {
            }
            return d;
        }
        return d;
    }

    public void g(boolean z) {
        this.f2461e = z;
    }

    public void h(boolean z) {
        this.f2462f = z;
    }
}
