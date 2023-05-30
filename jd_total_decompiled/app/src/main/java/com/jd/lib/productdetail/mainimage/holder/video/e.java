package com.jd.lib.productdetail.mainimage.holder.video;

/* loaded from: classes15.dex */
public class e implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdMVideoViewHolder f4971g;

    public e(PdMVideoViewHolder pdMVideoViewHolder) {
        this.f4971g = pdMVideoViewHolder;
    }

    @Override // java.lang.Runnable
    public void run() {
        PdMVideoViewHolder pdMVideoViewHolder = this.f4971g;
        if (pdMVideoViewHolder.f4651k) {
            return;
        }
        pdMVideoViewHolder.G = true;
        PdMVideoViewHolder pdMVideoViewHolder2 = this.f4971g;
        pdMVideoViewHolder2.b(pdMVideoViewHolder2.G);
    }
}
