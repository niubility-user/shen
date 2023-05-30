package com.jd.lib.productdetail.mainimage.bigimage;

import com.jd.lib.productdetail.core.utils.PDCalorieImageUtil;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;
import com.jd.lib.productdetail.mainimage.old.k;

/* loaded from: classes15.dex */
public class e implements k.p {
    public final /* synthetic */ PdBigImageActivity.ImageFragment a;

    public e(PdBigImageActivity.ImageFragment imageFragment) {
        this.a = imageFragment;
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.p
    public void a() {
        if (this.a.u.C()) {
            this.a.v.setVisibility(8);
            if (PdBigImageActivity.R) {
                this.a.v.setImageResource(R.drawable.lib_pd_mainimage_elder_bigimg_stop_btn);
                return;
            } else {
                this.a.v.setImageResource(R.drawable.lib_pd_mainimage_bigimg_stop_btn_v10);
                return;
            }
        }
        if (PdBigImageActivity.R) {
            PDCalorieImageUtil.get().display("2707", this.a.v);
        } else {
            this.a.v.setImageResource(R.drawable.lib_pd_mainimage_bigimg_play_btn_v10);
        }
        if (this.a.u.s() == -1) {
            this.a.v.setVisibility(0);
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.p
    public void b() {
        this.a.u.J();
        if (this.a.u.C()) {
            if (PdBigImageActivity.R) {
                this.a.v.setImageResource(R.drawable.lib_pd_mainimage_elder_bigimg_stop_btn);
            } else {
                this.a.v.setImageResource(R.drawable.lib_pd_mainimage_bigimg_stop_btn_v10);
            }
        } else if (PdBigImageActivity.R) {
            PDCalorieImageUtil.get().display("2707", this.a.v);
        } else {
            this.a.v.setImageResource(R.drawable.lib_pd_mainimage_bigimg_play_btn_v10);
        }
        this.a.v.setVisibility(0);
    }
}
