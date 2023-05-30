package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.views.SpanView;

/* loaded from: classes13.dex */
public class k extends p<SpanView> {

    /* renamed from: g  reason: collision with root package name */
    private com.jd.dynamic.lib.viewparse.c.f<SpanView> f2456g = new com.jd.dynamic.lib.viewparse.c.p();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public SpanView d(Context context) {
        return new SpanView(context);
    }

    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: j  reason: merged with bridge method [inline-methods] */
    public SpanView a(ViewNode viewNode, Context context) {
        SpanView spanView = (SpanView) super.a(viewNode, context);
        if (this.d || viewNode.getAttributes() == null) {
            return spanView;
        }
        this.f2456g.b(this.f2460c);
        return this.f2456g.a(viewNode.getAttributes(), spanView);
    }
}
