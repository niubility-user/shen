package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import android.widget.ScrollView;
import com.jd.dynamic.entity.ViewNode;

/* loaded from: classes13.dex */
public class j extends o<ScrollView> {

    /* renamed from: h  reason: collision with root package name */
    private com.jd.dynamic.lib.viewparse.c.j<ScrollView> f2455h = new com.jd.dynamic.lib.viewparse.c.n();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public ScrollView d(Context context) {
        return new ScrollView(context);
    }

    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public ScrollView a(ViewNode viewNode, Context context) {
        ScrollView scrollView = (ScrollView) super.j(viewNode, context);
        if (this.d || viewNode.getAttributes() == null) {
            return scrollView;
        }
        this.f2455h.a(com.jd.dynamic.lib.utils.m.Q(viewNode.getAttributes()), scrollView);
        return scrollView;
    }
}
