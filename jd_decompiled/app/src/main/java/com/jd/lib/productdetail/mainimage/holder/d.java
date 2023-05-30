package com.jd.lib.productdetail.mainimage.holder;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;

/* loaded from: classes15.dex */
public class d implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdMainImageBaseHolder.c f4795g;

    public d(PdMainImageBaseHolder.c cVar) {
        this.f4795g = cVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        PdMainImageBaseHolder pdMainImageBaseHolder = PdMainImageBaseHolder.this;
        SimpleDraweeView simpleDraweeView = pdMainImageBaseHolder.f4652l;
        if (simpleDraweeView == null) {
            return;
        }
        simpleDraweeView.setOnClickListener(pdMainImageBaseHolder);
    }
}
