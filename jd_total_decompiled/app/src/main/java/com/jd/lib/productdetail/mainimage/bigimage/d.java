package com.jd.lib.productdetail.mainimage.bigimage;

import com.jd.lib.productdetail.core.utils.PDCalorieImageUtil;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;
import com.jd.lib.productdetail.mainimage.old.k;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;

/* loaded from: classes15.dex */
public class d implements k.n {
    public final /* synthetic */ PdBigImageActivity.ImageFragment a;

    public d(PdBigImageActivity.ImageFragment imageFragment) {
        this.a = imageFragment;
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.n
    public void a() {
        PdMainImagePresenter pdMainImagePresenter = PdBigImageActivity.Q;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.mtaClick("Productdetail_PhotoVideo", "", "1");
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.n
    public void a(boolean z) {
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.n
    public void b() {
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.n
    public void b(boolean z) {
        if (z) {
            PdBigImageActivity.ImageFragment imageFragment = this.a;
            imageFragment.u.o = false;
            if (PdBigImageActivity.R) {
                imageFragment.v.setImageResource(R.drawable.lib_pd_mainimage_elder_bigimg_stop_btn);
            } else {
                imageFragment.v.setImageResource(R.drawable.lib_pd_mainimage_bigimg_stop_btn_v10);
            }
            this.a.v.setVisibility(8);
            return;
        }
        PdMainImagePresenter pdMainImagePresenter = PdBigImageActivity.Q;
        if (pdMainImagePresenter != null) {
            pdMainImagePresenter.mtaClick("Productdetail_PhotoVideo", "", "2");
        }
        PdBigImageActivity.ImageFragment imageFragment2 = this.a;
        imageFragment2.u.o = true;
        imageFragment2.v.setVisibility(0);
        if (PdBigImageActivity.R) {
            PDCalorieImageUtil.get().display("2707", this.a.v);
        } else {
            this.a.v.setImageResource(R.drawable.lib_pd_mainimage_bigimg_play_btn_v10);
        }
    }
}
