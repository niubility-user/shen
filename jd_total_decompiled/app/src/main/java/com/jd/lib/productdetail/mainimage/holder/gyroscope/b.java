package com.jd.lib.productdetail.mainimage.holder.gyroscope;

import android.graphics.Bitmap;

/* loaded from: classes15.dex */
public class b implements Runnable {

    /* renamed from: g */
    public final /* synthetic */ Bitmap f4872g;

    /* renamed from: h */
    public final /* synthetic */ e f4873h;

    public b(e eVar, Bitmap bitmap) {
        this.f4873h = eVar;
        this.f4872g = bitmap;
    }

    @Override // java.lang.Runnable
    public void run() {
        PdMImageGyroscopeViewHolder pdMImageGyroscopeViewHolder = this.f4873h.a;
        if (pdMImageGyroscopeViewHolder.f4651k) {
            return;
        }
        pdMImageGyroscopeViewHolder.D.setImageBitmap(this.f4872g);
    }
}
