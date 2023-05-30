package com.jd.lib.productdetail.mainimage.bigimage;

import android.view.View;
import android.widget.ImageView;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;
import com.jd.lib.productdetail.mainimage.old.k;

/* loaded from: classes15.dex */
public class c implements k.InterfaceC0159k {
    public final /* synthetic */ PdBigImageActivity.ImageFragment a;

    public c(PdBigImageActivity.ImageFragment imageFragment) {
        this.a = imageFragment;
    }

    @Override // com.jd.lib.productdetail.mainimage.old.k.InterfaceC0159k
    public void a(View view) {
        ImageView imageView = this.a.v;
        if (imageView != null) {
            if (PdBigImageActivity.R) {
                imageView.setImageResource(R.drawable.lib_pd_mainimage_elder_bigimg_stop_btn);
            } else {
                imageView.setImageResource(R.drawable.lib_pd_mainimage_bigimg_stop_btn_v10);
            }
            this.a.v.setVisibility(8);
        }
    }
}
