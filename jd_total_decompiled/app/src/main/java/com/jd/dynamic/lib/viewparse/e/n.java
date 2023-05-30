package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.viewparse.c.s;
import com.jd.dynamic.lib.views.UnIconView;

/* loaded from: classes13.dex */
public class n extends o<UnIconView> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public UnIconView d(Context context) {
        return new UnIconView(context);
    }

    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public UnIconView a(ViewNode viewNode, Context context) {
        UnIconView unIconView = (UnIconView) super.j(viewNode, context);
        if (this.d || viewNode.getAttributes() == null) {
            return unIconView;
        }
        new s().b(com.jd.dynamic.lib.utils.m.Q(viewNode.getAttributes()), unIconView);
        return unIconView;
    }
}
