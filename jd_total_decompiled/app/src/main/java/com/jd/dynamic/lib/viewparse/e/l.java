package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.views.TagView;

/* loaded from: classes13.dex */
public class l extends o<TagView> {

    /* renamed from: h  reason: collision with root package name */
    private com.jd.dynamic.lib.viewparse.c.f<TagView> f2457h = new com.jd.dynamic.lib.viewparse.c.q();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public TagView d(Context context) {
        return new TagView(context);
    }

    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public TagView a(ViewNode viewNode, Context context) {
        TagView tagView = (TagView) super.j(viewNode, context);
        if (this.d || viewNode.getAttributes() == null) {
            return tagView;
        }
        this.f2457h.b(this.f2460c);
        tagView.attachEngine(this.f2460c);
        return this.f2457h.a(com.jd.dynamic.lib.utils.m.Q(viewNode.getAttributes()), tagView);
    }
}
