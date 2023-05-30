package com.jd.lib.productdetail.mainimage.old;

import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import com.jd.lib.productdetail.core.entitys.PdPreferentialRecommendProductListInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.protocol.PreferentialRecommendNewProtocol;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.lbs.LocManager;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes15.dex */
public class g0 {
    public MutableLiveData<PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo>> a = new MutableLiveData<>();

    public void a(BaseActivity baseActivity, String str, String str2, String str3, PdMainImagePresenter pdMainImagePresenter) {
        MutableLiveData<WareBusinessUnitMainImageEntity> mutableLiveData;
        PreferentialRecommendNewProtocol preferentialRecommendNewProtocol = new PreferentialRecommendNewProtocol();
        PreferentialRecommendNewProtocol.RecommendProductListRequestParams recommendProductListRequestParams = new PreferentialRecommendNewProtocol.RecommendProductListRequestParams();
        recommendProductListRequestParams.area = LocManager.getCommonLbsParameter();
        recommendProductListRequestParams.mSkuId = str;
        recommendProductListRequestParams.pageId = PDHelper.getPageId("com.jd.lib.productdetail.ProductDetailActivity");
        if (pdMainImagePresenter != null && (mutableLiveData = pdMainImagePresenter.mainImageData) != null && mutableLiveData.getValue() != null && pdMainImagePresenter.mainImageData.getValue().extMap != null && !TextUtils.isEmpty(pdMainImagePresenter.mainImageData.getValue().extMap.recommendPid)) {
            recommendProductListRequestParams.pid = pdMainImagePresenter.mainImageData.getValue().extMap.recommendPid;
        } else {
            recommendProductListRequestParams.pid = "12201";
        }
        recommendProductListRequestParams.skuSize = 20;
        recommendProductListRequestParams.fieldSet = new HashSet();
        if (TextUtils.equals(str3, "3")) {
            for (int i2 = 1; i2 < 5; i2++) {
                recommendProductListRequestParams.fieldSet.add(Integer.valueOf(i2));
            }
            recommendProductListRequestParams.fieldSet.add(201);
            recommendProductListRequestParams.fieldSet.add(301);
        } else {
            recommendProductListRequestParams.fieldSet.add(1);
            recommendProductListRequestParams.fieldSet.add(3);
            recommendProductListRequestParams.fieldSet.add(4);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("cateFlag", Boolean.TRUE);
        hashMap.put("asyncLoadingCate3Id", str2);
        hashMap.put("cateSkuMinSize", 3);
        hashMap.put("cateTabMaxSize", 8);
        hashMap.put("cateTabBottomSize", 2);
        hashMap.put("cateBottomSkuSize", 3);
        recommendProductListRequestParams.cateParam = hashMap;
        preferentialRecommendNewProtocol.mRequestParams = recommendProductListRequestParams;
        preferentialRecommendNewProtocol.request(baseActivity);
        preferentialRecommendNewProtocol.mResult.observe(baseActivity, new r(this, str3, str2, preferentialRecommendNewProtocol));
    }
}
