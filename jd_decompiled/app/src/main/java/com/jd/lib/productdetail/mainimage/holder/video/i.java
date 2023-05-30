package com.jd.lib.productdetail.mainimage.holder.video;

import android.view.View;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.k;

/* loaded from: classes15.dex */
public class i implements k.InterfaceC0159k {
    public final /* synthetic */ PdMVideoViewHolder a;

    public i(PdMVideoViewHolder pdMVideoViewHolder) {
        this.a = pdMVideoViewHolder;
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.InterfaceC0159k
    public void a(View view) {
        PdMVideoViewHolder pdMVideoViewHolder = this.a;
        if (pdMVideoViewHolder.I != null) {
            if (pdMVideoViewHolder.f4654n.getMainImageParams().isElder) {
                this.a.I.setImageResource(R.drawable.lib_pd_mainimage_elder_bigimg_stop_btn);
            } else {
                this.a.I.setBackgroundResource(R.drawable.lib_pd_mainimage_bigimg_stop_btn_v10);
            }
            this.a.I.setVisibility(8);
        }
    }
}
