package com.jd.lib.productdetail.mainimage.holder.gyroscope;

/* loaded from: classes15.dex */
public class d implements Runnable {

    /* renamed from: g */
    public final /* synthetic */ e f4875g;

    public d(e eVar) {
        this.f4875g = eVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        PdMImageGyroscopeViewHolder pdMImageGyroscopeViewHolder = this.f4875g.a;
        if (pdMImageGyroscopeViewHolder.f4651k) {
            return;
        }
        pdMImageGyroscopeViewHolder.D.setVisibility(8);
        this.f4875g.a.f4652l.setVisibility(0);
        this.f4875g.a.z();
    }
}
