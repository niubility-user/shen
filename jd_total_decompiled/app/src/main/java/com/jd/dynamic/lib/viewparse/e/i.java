package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.views.RichTextViewContainer;
import java.util.ArrayList;

/* loaded from: classes13.dex */
public class i extends o<RichTextViewContainer> {

    /* renamed from: h  reason: collision with root package name */
    private final com.jd.dynamic.lib.viewparse.c.f<RichTextViewContainer> f2454h;

    public i() {
        new ArrayList();
        this.f2454h = new com.jd.dynamic.lib.viewparse.c.m();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public RichTextViewContainer d(Context context) {
        return new RichTextViewContainer(context);
    }

    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public RichTextViewContainer a(ViewNode viewNode, Context context) {
        RichTextViewContainer richTextViewContainer = (RichTextViewContainer) super.j(viewNode, context);
        richTextViewContainer.attachEngine(this.f2460c);
        if (this.d || viewNode.getAttributes() == null) {
            return richTextViewContainer;
        }
        this.f2454h.b(this.f2460c);
        return this.f2454h.a(com.jd.dynamic.lib.utils.m.Q(viewNode.getAttributes()), richTextViewContainer);
    }
}
