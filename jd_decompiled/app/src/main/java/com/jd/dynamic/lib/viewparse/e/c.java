package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import android.view.View;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.entity.ViewNode;

/* loaded from: classes13.dex */
public class c extends p<View> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    public View d(Context context) {
        return null;
    }

    @Override // com.jd.dynamic.lib.viewparse.e.p
    protected View e(Context context, ViewNode viewNode) {
        return DynamicSdk.getEngine().getCustomViewFactory().getCustomView(context, viewNode.getViewName());
    }

    @Override // com.jd.dynamic.lib.viewparse.e.p, com.jd.dynamic.lib.viewparse.e.f
    /* renamed from: f */
    public View a(ViewNode viewNode, Context context) {
        View a = super.a(viewNode, context);
        return (this.d || viewNode.getAttributes() == null) ? a : DynamicSdk.getEngine().getCustomViewFactory().parse(viewNode.getAttributes(), a);
    }
}
