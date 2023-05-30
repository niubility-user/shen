package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import android.view.ViewGroup;
import com.jd.dynamic.R;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.views.CornerSimpleDraweeView;

/* loaded from: classes13.dex */
public class g extends p<CornerSimpleDraweeView> {

    /* renamed from: h */
    private ViewGroup.LayoutParams f2451h;

    /* renamed from: g */
    private com.jd.dynamic.lib.viewparse.c.f<CornerSimpleDraweeView> f2450g = new com.jd.dynamic.lib.viewparse.c.k();

    /* renamed from: i */
    private boolean f2452i = false;

    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: i */
    public CornerSimpleDraweeView d(Context context) {
        return new CornerSimpleDraweeView(context);
    }

    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: j */
    public CornerSimpleDraweeView a(ViewNode viewNode, Context context) {
        CornerSimpleDraweeView cornerSimpleDraweeView = (CornerSimpleDraweeView) super.a(viewNode, context);
        if (this.f2452i) {
            com.jd.dynamic.lib.utils.m.r(cornerSimpleDraweeView, 8);
            return cornerSimpleDraweeView;
        }
        cornerSimpleDraweeView.setTag(R.id.dynamic_layout_params, this.f2451h);
        if (this.d || viewNode.getAttributes() == null) {
            return cornerSimpleDraweeView;
        }
        this.f2450g.b(this.f2460c);
        return this.f2450g.a(com.jd.dynamic.lib.utils.m.Q(viewNode.getAttributes()), cornerSimpleDraweeView);
    }

    public void k() {
        this.f2452i = true;
    }

    public void l(ViewGroup.LayoutParams layoutParams) {
        this.f2451h = layoutParams;
    }
}
