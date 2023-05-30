package com.jd.dynamic.lib.viewparse.b.carouselView;

import android.content.Context;
import com.jd.dynamic.entity.ViewNode;
import com.jd.dynamic.lib.utils.m;
import com.jd.dynamic.lib.viewparse.c.f;
import com.jd.dynamic.lib.viewparse.e.o;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class j extends o<f> {

    /* renamed from: h  reason: collision with root package name */
    private final f<f> f2356h = new n();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: k  reason: merged with bridge method [inline-methods] */
    public f d(Context context) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: l  reason: merged with bridge method [inline-methods] */
    public f e(Context context, ViewNode viewNode) {
        f fVar = new f(context, viewNode);
        fVar.f(this.f2460c);
        return fVar;
    }

    @Override // com.jd.dynamic.lib.viewparse.e.p
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public f a(ViewNode viewNode, Context context) {
        HashMap<String, String> Q;
        f fVar = (f) super.j(viewNode, context);
        if (this.d || (Q = m.Q(viewNode.getAttributes())) == null) {
            return fVar;
        }
        this.f2356h.b(this.f2460c);
        return this.f2356h.a(Q, fVar);
    }
}
