package com.jd.lib.productdetail.mainimage.old;

import android.text.TextUtils;
import androidx.lifecycle.Observer;
import com.jd.lib.productdetail.core.entitys.PdPreferentialRecommendProductExtInfo;
import com.jd.lib.productdetail.core.entitys.PdPreferentialRecommendProductListInfo;
import com.jd.lib.productdetail.core.entitys.PreferentialRecommendTabItemEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PreferentialRecommendItemEntity;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import java.util.List;

/* loaded from: classes15.dex */
public class o implements Observer<PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo>> {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdMPartsRecommendView f5188g;

    public o(PdMPartsRecommendView pdMPartsRecommendView) {
        this.f5188g = pdMPartsRecommendView;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo> result) {
        PdPreferentialRecommendProductListInfo pdPreferentialRecommendProductListInfo;
        List<PreferentialRecommendItemEntity> list;
        PdPreferentialRecommendProductExtInfo pdPreferentialRecommendProductExtInfo;
        PdPreferentialRecommendProductListInfo pdPreferentialRecommendProductListInfo2;
        List<PreferentialRecommendTabItemEntity> list2;
        int i2;
        PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo> result2 = result;
        if (result2 == null || (pdPreferentialRecommendProductListInfo = result2.mData) == null || !TextUtils.equals(pdPreferentialRecommendProductListInfo.from, "3")) {
            return;
        }
        PdPreferentialRecommendProductListInfo pdPreferentialRecommendProductListInfo3 = result2.mData;
        List<PreferentialRecommendItemEntity> list3 = null;
        if (pdPreferentialRecommendProductListInfo3 == null || !TextUtils.equals(pdPreferentialRecommendProductListInfo3.currentTabId, this.f5188g.z)) {
            list = null;
            pdPreferentialRecommendProductExtInfo = null;
        } else {
            if (result2.mStatus != PdBaseProtocolLiveData.Result.DataStatus.SUCCESS || (pdPreferentialRecommendProductListInfo2 = result2.mData) == null) {
                list = null;
                pdPreferentialRecommendProductExtInfo = null;
            } else {
                List<PreferentialRecommendTabItemEntity> list4 = pdPreferentialRecommendProductListInfo2.tabItemEntities;
                if (list4 != null) {
                    PdMPartsRecommendView pdMPartsRecommendView = this.f5188g;
                    String str = pdMPartsRecommendView.z;
                    if (list4 != null && !TextUtils.isEmpty(str)) {
                        i2 = 0;
                        while (i2 < list4.size()) {
                            if (list4.get(i2) != null && TextUtils.equals(str, list4.get(i2).tabId)) {
                                break;
                            }
                            i2++;
                        }
                    }
                    i2 = -1;
                    pdMPartsRecommendView.y = i2;
                    list2 = result2.mData.tabItemEntities;
                } else {
                    list2 = null;
                }
                PdMPartsRecommendView pdMPartsRecommendView2 = this.f5188g;
                if (pdMPartsRecommendView2.y == -1) {
                    pdMPartsRecommendView2.y = 0;
                    if (list2 != null && !list2.isEmpty() && list2.get(0) != null) {
                        this.f5188g.z = list2.get(0).tabId;
                    }
                }
                PdPreferentialRecommendProductListInfo pdPreferentialRecommendProductListInfo4 = result2.mData;
                if (pdPreferentialRecommendProductListInfo4.itemEntitiesMap != null) {
                    PdMPartsRecommendView pdMPartsRecommendView3 = this.f5188g;
                    List<PreferentialRecommendTabItemEntity> list5 = pdPreferentialRecommendProductListInfo4.tabItemEntities;
                    int i3 = pdMPartsRecommendView3.y;
                    String str2 = pdMPartsRecommendView3.z;
                    if (TextUtils.isEmpty(str2)) {
                        str2 = (list5 == null || list5.size() <= 0 || i3 >= list5.size()) ? null : list5.get(i3).tabId;
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        list3 = result2.mData.itemEntitiesMap.get(str2);
                    }
                }
                List<PreferentialRecommendTabItemEntity> list6 = list2;
                pdPreferentialRecommendProductExtInfo = result2.mData.extInfo;
                list = list3;
                list3 = list6;
            }
            this.f5188g.j();
        }
        PdMPartsRecommendView pdMPartsRecommendView4 = this.f5188g;
        int i4 = PdMPartsRecommendView.E;
        pdMPartsRecommendView4.e(list3);
        this.f5188g.f(list, pdPreferentialRecommendProductExtInfo);
    }
}
