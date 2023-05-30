package com.jd.lib.productdetail.mainimage.holder.video;

import com.jd.lib.productdetail.mainimage.old.k;

/* loaded from: classes15.dex */
public class b implements k.p {
    public final /* synthetic */ PdMVideoViewHolder a;

    public b(PdMVideoViewHolder pdMVideoViewHolder) {
        this.a = pdMVideoViewHolder;
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.p
    public void a() {
        com.jd.lib.productdetail.mainimage.old.k kVar;
        PdMVideoViewHolder pdMVideoViewHolder = this.a;
        if (pdMVideoViewHolder.f4651k || (kVar = pdMVideoViewHolder.E) == null || kVar.f5168l != 3) {
            return;
        }
        kVar.E(false);
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.p
    public void b() {
        com.jd.lib.productdetail.mainimage.old.k kVar;
        PdMVideoViewHolder pdMVideoViewHolder = this.a;
        if (pdMVideoViewHolder.f4651k || (kVar = pdMVideoViewHolder.E) == null || kVar.f5168l != 3) {
            return;
        }
        kVar.E(true);
    }
}
