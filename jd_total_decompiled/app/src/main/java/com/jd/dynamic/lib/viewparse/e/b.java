package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.views.CollectionView;

/* loaded from: classes13.dex */
public class b extends o<CollectionView> {

    /* renamed from: h  reason: collision with root package name */
    private com.jd.dynamic.lib.viewparse.c.f<CollectionView> f2447h = new com.jd.dynamic.lib.viewparse.c.g();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public CollectionView d(Context context) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public CollectionView e(Context context, ViewNode viewNode) {
        return new CollectionView(context, viewNode);
    }

    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public CollectionView a(ViewNode viewNode, Context context) {
        CollectionView collectionView = (CollectionView) super.j(viewNode, context);
        if (this.d || viewNode.getAttributes() == null) {
            return collectionView;
        }
        this.f2447h.b(this.f2460c);
        return this.f2447h.a(com.jd.dynamic.lib.utils.m.Q(viewNode.getAttributes()), collectionView);
    }
}
