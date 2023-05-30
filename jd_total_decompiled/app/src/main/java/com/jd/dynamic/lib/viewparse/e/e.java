package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import android.widget.HorizontalScrollView;
import com.jd.dynamic.entity.ViewNode;

/* loaded from: classes13.dex */
public class e extends o<HorizontalScrollView> {

    /* renamed from: h  reason: collision with root package name */
    private com.jd.dynamic.lib.viewparse.c.j<HorizontalScrollView> f2449h = new com.jd.dynamic.lib.viewparse.c.i();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public HorizontalScrollView d(Context context) {
        return new HorizontalScrollView(context);
    }

    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public HorizontalScrollView a(ViewNode viewNode, Context context) {
        HorizontalScrollView horizontalScrollView = (HorizontalScrollView) super.j(viewNode, context);
        if (this.d || viewNode.getAttributes() == null) {
            return horizontalScrollView;
        }
        this.f2449h.a(com.jd.dynamic.lib.utils.m.Q(viewNode.getAttributes()), horizontalScrollView);
        return horizontalScrollView;
    }
}
