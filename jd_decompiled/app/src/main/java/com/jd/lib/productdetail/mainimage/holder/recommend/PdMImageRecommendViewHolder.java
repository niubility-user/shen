package com.jd.lib.productdetail.mainimage.holder.recommend;

import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import com.jd.lib.productdetail.core.entitys.PdPreferentialRecommendProductListInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageRecommendEntity;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.common.BaseActivity;

/* loaded from: classes15.dex */
public class PdMImageRecommendViewHolder extends PdMainImageBaseHolder implements com.jd.lib.productdetail.mainimage.dialog.a {
    public PdMImageRecommendView B;
    public Observer<PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo>> C;

    /* loaded from: classes15.dex */
    public class a implements Observer<PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo>> {
        public a() {
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo> result) {
            PdPreferentialRecommendProductListInfo pdPreferentialRecommendProductListInfo;
            WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
            WareBuinessUnitMainImageBizDataEntity.PdTjBizData pdTjBizData;
            WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity2;
            WareBuinessUnitMainImageBizDataEntity.PdTjBizData pdTjBizData2;
            PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo> result2 = result;
            if (result2 == null || (pdPreferentialRecommendProductListInfo = result2.mData) == null || !TextUtils.equals(pdPreferentialRecommendProductListInfo.from, "1")) {
                return;
            }
            PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
            if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
                PdPreferentialRecommendProductListInfo pdPreferentialRecommendProductListInfo2 = result2.mData;
                if (pdPreferentialRecommendProductListInfo2 instanceof PdPreferentialRecommendProductListInfo) {
                    PdPreferentialRecommendProductListInfo pdPreferentialRecommendProductListInfo3 = pdPreferentialRecommendProductListInfo2;
                    PdMImageRecommendViewHolder pdMImageRecommendViewHolder = PdMImageRecommendViewHolder.this;
                    WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = pdMImageRecommendViewHolder.r;
                    if (wareBusinessMagicHeadPicInfoEntity != null && (wareBuinessUnitMainImageBizDataEntity2 = wareBusinessMagicHeadPicInfoEntity.bizData) != null && (pdTjBizData2 = wareBuinessUnitMainImageBizDataEntity2.tjBizData) != null) {
                        pdMImageRecommendViewHolder.G(pdPreferentialRecommendProductListInfo3, pdTjBizData2.tjInfo);
                    }
                }
                PdMImageRecommendViewHolder.this.f4654n.mRecommendContainer.a.removeObserver(this);
            } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                PdMImageRecommendViewHolder pdMImageRecommendViewHolder2 = PdMImageRecommendViewHolder.this;
                WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity2 = pdMImageRecommendViewHolder2.r;
                if (wareBusinessMagicHeadPicInfoEntity2 != null && (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity2.bizData) != null && (pdTjBizData = wareBuinessUnitMainImageBizDataEntity.tjBizData) != null) {
                    pdMImageRecommendViewHolder2.G(null, pdTjBizData.tjInfo);
                }
                PdMImageRecommendViewHolder.this.f4654n.mRecommendContainer.a.removeObserver(this);
            }
        }
    }

    public PdMImageRecommendViewHolder(@NonNull View view, View view2) {
        super(view, view2);
    }

    public void G(PdPreferentialRecommendProductListInfo pdPreferentialRecommendProductListInfo, WareImageRecommendEntity wareImageRecommendEntity) {
        PdMImageRecommendView pdMImageRecommendView = this.B;
        if (pdMImageRecommendView != null) {
            pdMImageRecommendView.e(this.r);
            PdMImageRecommendView pdMImageRecommendView2 = this.B;
            int i2 = PdMImageRecommendItemView.s;
            pdMImageRecommendView2.g(2);
            this.B.b(pdPreferentialRecommendProductListInfo, wareImageRecommendEntity, this.v);
            this.B.h(this);
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.dialog.a
    public void a() {
    }

    @Override // com.jd.lib.productdetail.mainimage.dialog.a
    public void a(Object obj) {
        if (obj instanceof View) {
            o((View) obj);
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void d(View view) {
        this.B = (PdMImageRecommendView) view;
        this.C = new a();
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void j(PdMainImagePresenter pdMainImagePresenter) {
        super.j(pdMainImagePresenter);
        PdMImageRecommendView pdMImageRecommendView = this.B;
        if (pdMImageRecommendView != null) {
            pdMImageRecommendView.f(pdMainImagePresenter);
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void n() {
        this.f4654n.mRecommendContainer.a.observe((BaseActivity) this.f4649i, this.C);
    }
}
