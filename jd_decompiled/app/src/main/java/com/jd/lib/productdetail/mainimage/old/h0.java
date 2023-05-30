package com.jd.lib.productdetail.mainimage.old;

import androidx.lifecycle.MutableLiveData;
import com.jd.lib.productdetail.core.entitys.topimagerecommend.PdTopRecommendEntity;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.protocol.PdTopImageRecommendProtocol;
import com.jingdong.common.BaseActivity;

/* loaded from: classes15.dex */
public class h0 {
    public MutableLiveData<PdBaseProtocolLiveData.Result<PdTopRecommendEntity>> a = new MutableLiveData<>();

    public void a(BaseActivity baseActivity) {
        this.a.removeObservers(baseActivity);
        this.a = new MutableLiveData<>(new PdBaseProtocolLiveData.Result(PdBaseProtocolLiveData.Result.DataStatus.NONE, null, null));
    }

    public void b(BaseActivity baseActivity, String str, String str2, String str3, String str4, String str5) {
        PdTopImageRecommendProtocol pdTopImageRecommendProtocol = new PdTopImageRecommendProtocol();
        PdTopImageRecommendProtocol.RecommendProductListRequestParams recommendProductListRequestParams = new PdTopImageRecommendProtocol.RecommendProductListRequestParams();
        recommendProductListRequestParams.cid1 = str;
        recommendProductListRequestParams.cid2 = str2;
        recommendProductListRequestParams.cid3 = str3;
        recommendProductListRequestParams.storeId = str4;
        recommendProductListRequestParams.skuId = str5;
        pdTopImageRecommendProtocol.mRequestParams = recommendProductListRequestParams;
        pdTopImageRecommendProtocol.request(baseActivity);
        pdTopImageRecommendProtocol.mResult.observe(baseActivity, new u(this, pdTopImageRecommendProtocol));
    }
}
