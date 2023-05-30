package com.jd.lib.productdetail.mainimage.holder.gift;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerGiftsAdapter;
import com.jd.mobile.image.ImageRequestListener;

/* loaded from: classes15.dex */
public class a implements ImageRequestListener<ImageInfo> {
    public final /* synthetic */ PdMImageGiftLayerGiftsAdapter.GiftsItemHolder a;

    public a(PdMImageGiftLayerGiftsAdapter.GiftsItemHolder giftsItemHolder) {
        this.a = giftsItemHolder;
    }

    @Override // com.jd.mobile.image.ImageRequestListener
    public void onCancel() {
    }

    @Override // com.jd.mobile.image.ImageRequestListener
    public void onFailure(Throwable th) {
        SimpleDraweeView simpleDraweeView = this.a.d;
        if (simpleDraweeView != null) {
            simpleDraweeView.setVisibility(4);
        }
    }

    @Override // com.jd.mobile.image.ImageRequestListener
    public void onSuccess(ImageInfo imageInfo) {
    }
}
