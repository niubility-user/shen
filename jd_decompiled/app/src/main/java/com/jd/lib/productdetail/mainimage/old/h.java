package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.text.TextUtils;
import androidx.lifecycle.Observer;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo;
import com.jd.lib.productdetail.core.entitys.PdDpgRecalculateInfo;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.PdMDpgLayerAdapter;
import java.util.List;

/* loaded from: classes15.dex */
public class h implements Observer<PdBaseProtocolLiveData.Result<PdDpgRecalculateInfo>> {

    /* renamed from: g */
    public final /* synthetic */ PdMDpgLayerView f5154g;

    public h(PdMDpgLayerView pdMDpgLayerView) {
        this.f5154g = pdMDpgLayerView;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(PdBaseProtocolLiveData.Result<PdDpgRecalculateInfo> result) {
        PdDpgRecalculateInfo pdDpgRecalculateInfo;
        Context context;
        PdDpgRecalculateInfo.DetailBean.RequestParam requestParam;
        List<PdDpgListLayerInfo.DetailBean.ItemsBean> list;
        PdDpgListLayerInfo.DetailBean.InfoBean infoBean;
        PdBaseProtocolLiveData.Result<PdDpgRecalculateInfo> result2 = result;
        if (result2 == null || (pdDpgRecalculateInfo = result2.mData) == null) {
            return;
        }
        PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
        if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
            if (pdDpgRecalculateInfo instanceof PdDpgRecalculateInfo) {
                PdDpgRecalculateInfo pdDpgRecalculateInfo2 = pdDpgRecalculateInfo;
                if (pdDpgRecalculateInfo2.code == 0) {
                    PdDpgRecalculateInfo.DetailBean detailBean = pdDpgRecalculateInfo2.data;
                    if (detailBean != null && (requestParam = detailBean.param) != null) {
                        List<PdDpgRecalculateInfo.DetailBean.SkuInfoParam> list2 = requestParam.skuInfoParamList;
                        PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem = this.f5154g.f5021n;
                        if (itemInfoAndItem != null && (list = itemInfoAndItem.items) != null && list.size() > 0) {
                            List<PdDpgListLayerInfo.DetailBean.ItemsBean> list3 = this.f5154g.f5021n.items;
                            for (int i2 = 0; i2 < list3.size(); i2++) {
                                PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean = list3.get(i2);
                                if (itemsBean != null && list2 != null) {
                                    for (int i3 = 0; i3 < list2.size(); i3++) {
                                        PdDpgRecalculateInfo.DetailBean.SkuInfoParam skuInfoParam = list2.get(i3);
                                        if (skuInfoParam != null && TextUtils.equals(skuInfoParam.skuId, itemsBean.skuId)) {
                                            itemsBean.isSelectedProductCheckBoxNative = true;
                                            itemsBean.isSelectedTextNative = true;
                                        }
                                    }
                                }
                            }
                            PdMDpgLayerView pdMDpgLayerView = this.f5154g;
                            if (!pdMDpgLayerView.p) {
                                pdMDpgLayerView.o.isSelectedProductCheckBoxNative = false;
                            }
                            if (pdMDpgLayerView.f5021n.info != null) {
                                if (!TextUtils.isEmpty(detailBean.matchPrice)) {
                                    this.f5154g.f5021n.info.nativeTotalPriceStr = detailBean.matchPrice;
                                } else {
                                    this.f5154g.f5021n.info.nativeTotalPriceStr = "";
                                }
                                if (list2 != null && list2.size() > 0) {
                                    this.f5154g.f5021n.info.nativeTotalNumber = list2.size();
                                    if (!TextUtils.isEmpty(detailBean.discount)) {
                                        PdMDpgLayerView pdMDpgLayerView2 = this.f5154g;
                                        pdMDpgLayerView2.f5021n.info.discount = pdMDpgLayerView2.L.getString(R.string.lib_pd_image_dpg_discount_price, detailBean.discount);
                                    } else {
                                        this.f5154g.f5021n.info.discount = "";
                                    }
                                } else {
                                    PdDpgListLayerInfo.DetailBean.InfoBean infoBean2 = this.f5154g.f5021n.info;
                                    infoBean2.nativeTotalNumber = 0;
                                    infoBean2.discount = "";
                                }
                                if (!TextUtils.isEmpty(this.f5154g.f5021n.info.discount)) {
                                    PdMDpgLayerView pdMDpgLayerView3 = this.f5154g;
                                    if (!TextUtils.isEmpty(pdMDpgLayerView3.f5021n.info.discount)) {
                                        JDJSONObject jDJSONObject = new JDJSONObject();
                                        PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem2 = pdMDpgLayerView3.f5021n;
                                        if (itemInfoAndItem2 != null && (infoBean = itemInfoAndItem2.info) != null) {
                                            jDJSONObject.put("matchid", (Object) infoBean.matchId);
                                            int i4 = pdMDpgLayerView3.f5021n.type;
                                            if (i4 > 0) {
                                                jDJSONObject.put("type", (Object) Integer.valueOf(i4));
                                            } else {
                                                jDJSONObject.put("type", (Object) "-100");
                                            }
                                            pdMDpgLayerView3.F.mtaExposure("Productdetail_DapeiBuyPromotionExpo", jDJSONObject.toJSONString());
                                        }
                                    }
                                }
                            }
                        }
                    }
                    PdMDpgLayerView pdMDpgLayerView4 = this.f5154g;
                    PdMDpgLayerAdapter.PdDpgLayerHolder pdDpgLayerHolder = pdMDpgLayerView4.q;
                    if (pdDpgLayerHolder != null) {
                        pdDpgLayerHolder.c(pdMDpgLayerView4.f5021n, pdMDpgLayerView4.s, pdDpgLayerHolder, true, pdMDpgLayerView4.r);
                        return;
                    }
                    return;
                }
            }
            Context context2 = this.f5154g.L;
            if (context2 != null) {
                PDUtils.showToastShortNormal(context2, context2.getResources().getString(R.string.lib_pd_image_data_load_error));
            }
        } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL && (context = this.f5154g.L) != null) {
            PDUtils.showToastShortNormal(context, context.getResources().getString(R.string.lib_pd_image_data_load_error));
        }
    }
}
