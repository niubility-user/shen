package com.jd.lib.productdetail.mainimage.old;

import androidx.lifecycle.Observer;
import com.jd.lib.productdetail.core.entitys.topimagerecommend.PdTopRecommendEntity;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.protocol.PdTopImageRecommendProtocol;

/* loaded from: classes15.dex */
public class u implements Observer<PdBaseProtocolLiveData.Result<PdTopRecommendEntity>> {

    /* renamed from: g */
    public final /* synthetic */ PdTopImageRecommendProtocol f5204g;

    /* renamed from: h */
    public final /* synthetic */ h0 f5205h;

    public u(h0 h0Var, PdTopImageRecommendProtocol pdTopImageRecommendProtocol) {
        this.f5205h = h0Var;
        this.f5204g = pdTopImageRecommendProtocol;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(PdBaseProtocolLiveData.Result<PdTopRecommendEntity> result) {
        PdBaseProtocolLiveData.Result<PdTopRecommendEntity> result2 = result;
        if (result2 != null) {
            PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
            PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = PdBaseProtocolLiveData.Result.DataStatus.SUCCESS;
            if (dataStatus == dataStatus2) {
                PdTopRecommendEntity pdTopRecommendEntity = new PdTopRecommendEntity();
                PdTopRecommendEntity pdTopRecommendEntity2 = result2.mData;
                if (pdTopRecommendEntity2 instanceof PdTopRecommendEntity) {
                    pdTopRecommendEntity = pdTopRecommendEntity2;
                }
                this.f5205h.a.postValue(new PdBaseProtocolLiveData.Result<>(dataStatus2, pdTopRecommendEntity, ""));
                this.f5204g.mResult.removeObserver(this);
                return;
            }
            PdBaseProtocolLiveData.Result.DataStatus dataStatus3 = PdBaseProtocolLiveData.Result.DataStatus.FAIL;
            if (dataStatus == dataStatus3) {
                this.f5205h.a.postValue(new PdBaseProtocolLiveData.Result<>(dataStatus3, null, ""));
                this.f5204g.mResult.removeObserver(this);
                return;
            }
            return;
        }
        this.f5205h.a.postValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.FAIL, null, ""));
        this.f5204g.mResult.removeObserver(this);
    }
}
