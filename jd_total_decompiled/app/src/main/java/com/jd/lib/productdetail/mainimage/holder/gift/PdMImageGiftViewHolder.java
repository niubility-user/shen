package com.jd.lib.productdetail.mainimage.holder.gift;

import android.view.View;
import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;

/* loaded from: classes15.dex */
public class PdMImageGiftViewHolder extends PdMainImageBaseHolder {
    public PdMImageGiftView B;

    /* loaded from: classes15.dex */
    public class a implements com.jd.lib.productdetail.mainimage.dialog.a {
        public a() {
        }

        @Override // com.jd.lib.productdetail.mainimage.dialog.a
        public void a() {
        }

        @Override // com.jd.lib.productdetail.mainimage.dialog.a
        public void a(Object obj) {
            WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = PdMImageGiftViewHolder.this.r;
            if (wareBusinessMagicHeadPicInfoEntity == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) == null || wareBuinessUnitMainImageBizDataEntity.headPicGiftInfo == null) {
                return;
            }
            PdMImageGiftViewHolder pdMImageGiftViewHolder = PdMImageGiftViewHolder.this;
            PdMImageGiftLayer pdMImageGiftLayer = new PdMImageGiftLayer(pdMImageGiftViewHolder.f4649i, true, pdMImageGiftViewHolder.f4654n);
            pdMImageGiftLayer.buildData2View(wareBusinessMagicHeadPicInfoEntity.bizData.headPicGiftInfo.giftLayerInfos);
            pdMImageGiftLayer.layerIsShow(true);
            PdMImageGiftViewHolder pdMImageGiftViewHolder2 = PdMImageGiftViewHolder.this;
            pdMImageGiftViewHolder2.e(pdMImageGiftLayer, pdMImageGiftViewHolder2.f4649i.getString(R.string.lib_pd_image_ok), true, true);
        }
    }

    public PdMImageGiftViewHolder(@NonNull View view, PdMImageGiftView pdMImageGiftView) {
        super(view, pdMImageGiftView);
        this.B = pdMImageGiftView;
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void n() {
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity;
        PdMImageGiftView pdMImageGiftView = this.B;
        if (pdMImageGiftView == null || (wareBusinessMagicHeadPicInfoEntity = this.r) == null || wareBusinessMagicHeadPicInfoEntity.bizData == null) {
            return;
        }
        pdMImageGiftView.e(false);
        this.B.d(this.r.bizData.headPicGiftInfo, this.f4654n);
        this.B.f(new a());
    }
}
