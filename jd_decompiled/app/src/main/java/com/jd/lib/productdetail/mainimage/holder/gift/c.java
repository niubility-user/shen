package com.jd.lib.productdetail.mainimage.holder.gift;

import android.text.TextUtils;
import android.view.View;
import com.jd.lib.productdetail.core.entitys.warebusiness.HeadPicGiftInfoEntity;
import com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerGiftsAdapter;
import com.jd.lib.productdetail.mainimage.old.j0;

/* loaded from: classes15.dex */
public class c implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ HeadPicGiftInfoEntity.GiftLayerInfosEntity f4850g;

    /* renamed from: h */
    public final /* synthetic */ int f4851h;

    /* renamed from: i */
    public final /* synthetic */ PdMImageGiftLayerGiftsAdapter.GiftsItemHolder f4852i;

    public c(PdMImageGiftLayerGiftsAdapter.GiftsItemHolder giftsItemHolder, HeadPicGiftInfoEntity.GiftLayerInfosEntity giftLayerInfosEntity, int i2) {
        this.f4852i = giftsItemHolder;
        this.f4850g = giftLayerInfosEntity;
        this.f4851h = i2;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.f4850g.gifts.get(this.f4851h) == null || TextUtils.isEmpty(this.f4850g.gifts.get(this.f4851h).sku)) {
            return;
        }
        j0.b(PdMImageGiftLayerGiftsAdapter.this.a, Long.valueOf(Long.parseLong(this.f4850g.gifts.get(this.f4851h).sku)), null, null);
    }
}
