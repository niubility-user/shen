package com.jd.lib.productdetail.mainimage.holder.video;

import com.jd.lib.productdetail.core.utils.PDCalorieImageUtil;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.k;

/* loaded from: classes15.dex */
public class c implements k.n {
    public final /* synthetic */ PdMVideoViewHolder a;

    public c(PdMVideoViewHolder pdMVideoViewHolder) {
        this.a = pdMVideoViewHolder;
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.n
    public void a() {
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.n
    public void a(boolean z) {
        this.a.G = z;
        this.a.f4654n.mtaClick("Productdetail_MainPicVideoSound", "", z ? "0" : "1");
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.n
    public void b() {
        this.a.f4654n.mtaExposure("Productdetail_MainPicVideoTrailerPlay");
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.n
    public void b(boolean z) {
        if (!z) {
            if (this.a.f4654n.getMainImageParams().isElder) {
                PDCalorieImageUtil.get().display("2707", this.a.I);
            } else {
                this.a.I.setBackgroundResource(R.drawable.lib_pd_mainimage_bigimg_play_btn_v10);
            }
        } else if (this.a.f4654n.getMainImageParams().isElder) {
            this.a.I.setBackgroundResource(R.drawable.lib_pd_mainimage_elder_bigimg_stop_btn);
        } else {
            this.a.I.setBackgroundResource(R.drawable.lib_pd_mainimage_bigimg_stop_btn_v10);
        }
        com.jd.lib.productdetail.mainimage.old.k kVar = this.a.E;
        if (kVar != null) {
            kVar.o = !z;
        }
    }
}
