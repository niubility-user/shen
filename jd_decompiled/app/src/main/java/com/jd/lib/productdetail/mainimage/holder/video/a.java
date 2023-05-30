package com.jd.lib.productdetail.mainimage.holder.video;

import com.jd.lib.productdetail.mainimage.holder.video.PdMVideoViewHolder;

/* loaded from: classes15.dex */
public class a implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdMVideoViewHolder.a f4969g;

    public a(PdMVideoViewHolder.a aVar) {
        this.f4969g = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.jd.lib.productdetail.mainimage.old.k kVar;
        PdMVideoViewHolder pdMVideoViewHolder = PdMVideoViewHolder.this;
        if (pdMVideoViewHolder.f4651k || (kVar = pdMVideoViewHolder.E) == null) {
            return;
        }
        kVar.H(true);
    }
}
