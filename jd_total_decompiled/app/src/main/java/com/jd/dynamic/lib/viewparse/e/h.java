package com.jd.dynamic.lib.viewparse.e;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.views.MarqueeTextView;

/* loaded from: classes13.dex */
public class h extends p<MarqueeTextView> {

    /* renamed from: g  reason: collision with root package name */
    private com.jd.dynamic.lib.viewparse.c.f<AppCompatTextView> f2453g = new com.jd.dynamic.lib.viewparse.c.l();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: i  reason: merged with bridge method [inline-methods] */
    public MarqueeTextView d(Context context) {
        return new MarqueeTextView(context);
    }

    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: j  reason: merged with bridge method [inline-methods] */
    public MarqueeTextView a(ViewNode viewNode, Context context) {
        MarqueeTextView marqueeTextView = (MarqueeTextView) super.a(viewNode, context);
        if (this.d || viewNode.getAttributes() == null) {
            return marqueeTextView;
        }
        this.f2453g.b(this.f2460c);
        AppCompatTextView a = this.f2453g.a(com.jd.dynamic.lib.utils.m.Q(viewNode.getAttributes()), marqueeTextView);
        return a instanceof MarqueeTextView ? (MarqueeTextView) a : marqueeTextView;
    }
}
