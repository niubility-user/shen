package com.jd.lib.productdetail.mainimage.bigimage;

import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;
import com.jd.lib.productdetail.mainimage.old.k;

/* loaded from: classes15.dex */
public class f implements k.o {
    public final /* synthetic */ PdBigImageActivity.ImageFragment a;

    public f(PdBigImageActivity.ImageFragment imageFragment) {
        this.a = imageFragment;
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.o
    public void a() {
        this.a.v.setVisibility(8);
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.o
    public void a(int i2) {
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.o
    public void onVideoFinish() {
        com.jd.lib.productdetail.mainimage.old.k kVar;
        PdBigImageActivity pdBigImageActivity = this.a.q;
        if (pdBigImageActivity == null || pdBigImageActivity.isFinishing() || (kVar = this.a.u) == null) {
            return;
        }
        kVar.c();
        this.a.d(true, false);
    }
}
