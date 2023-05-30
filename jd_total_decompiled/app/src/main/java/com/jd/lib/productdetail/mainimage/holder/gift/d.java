package com.jd.lib.productdetail.mainimage.holder.gift;

import android.view.View;
import com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerTitleAdapter;

/* loaded from: classes15.dex */
public class d implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ PdMImageGiftLayerTitleAdapter.GiftsTitleHolder f4853g;

    /* renamed from: h */
    public final /* synthetic */ PdMImageGiftLayerTitleAdapter f4854h;

    public d(PdMImageGiftLayerTitleAdapter pdMImageGiftLayerTitleAdapter, PdMImageGiftLayerTitleAdapter.GiftsTitleHolder giftsTitleHolder) {
        this.f4854h = pdMImageGiftLayerTitleAdapter;
        this.f4853g = giftsTitleHolder;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        PdMImageGiftLayerTitleAdapter.a aVar = this.f4854h.f4831f;
        if (aVar != null) {
            PdMImageGiftLayerTitleAdapter.GiftsTitleHolder giftsTitleHolder = this.f4853g;
            aVar.onClick(giftsTitleHolder.itemView, giftsTitleHolder.getAdapterPosition());
        }
        this.f4854h.h(this.f4853g.getAdapterPosition());
    }
}
