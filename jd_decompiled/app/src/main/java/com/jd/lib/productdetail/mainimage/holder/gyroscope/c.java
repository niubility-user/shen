package com.jd.lib.productdetail.mainimage.holder.gyroscope;

/* loaded from: classes15.dex */
public class c implements Runnable {

    /* renamed from: g */
    public final /* synthetic */ e f4874g;

    public c(e eVar) {
        this.f4874g = eVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        PdMImageGyroscopeViewHolder pdMImageGyroscopeViewHolder = this.f4874g.a;
        if (pdMImageGyroscopeViewHolder.f4651k) {
            return;
        }
        pdMImageGyroscopeViewHolder.D.setVisibility(8);
        this.f4874g.a.f4652l.setVisibility(0);
        this.f4874g.a.z();
    }
}
