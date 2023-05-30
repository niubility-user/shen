package com.jd.lib.productdetail.mainimage.holder.recommend;

import androidx.lifecycle.Observer;
import com.jd.lib.productdetail.core.entitys.topimagerecommend.PdRankResultEntity;
import com.jd.lib.productdetail.core.entitys.topimagerecommend.PdTopRecommendEntity;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;

/* loaded from: classes15.dex */
public class e implements Observer<PdBaseProtocolLiveData.Result<PdTopRecommendEntity>> {

    /* renamed from: g */
    public final /* synthetic */ PdMImageRecommendNewView f4932g;

    public e(PdMImageRecommendNewView pdMImageRecommendNewView) {
        this.f4932g = pdMImageRecommendNewView;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(PdBaseProtocolLiveData.Result<PdTopRecommendEntity> result) {
        PdRankResultEntity pdRankResultEntity;
        PdBaseProtocolLiveData.Result<PdTopRecommendEntity> result2 = result;
        if (result2 != null) {
            PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
            if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
                PdTopRecommendEntity pdTopRecommendEntity = result2.mData;
                if ((pdTopRecommendEntity instanceof PdTopRecommendEntity) && (pdRankResultEntity = pdTopRecommendEntity.result) != null) {
                    PdMImageRecommendNewView.a(this.f4932g, pdRankResultEntity.rank);
                } else {
                    PdMImageRecommendNewView.a(this.f4932g, null);
                }
            } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                PdMImageRecommendNewView.a(this.f4932g, null);
            }
        }
    }
}
