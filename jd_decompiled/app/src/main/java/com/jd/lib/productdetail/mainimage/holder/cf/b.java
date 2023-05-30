package com.jd.lib.productdetail.mainimage.holder.cf;

import android.text.TextUtils;
import android.view.animation.LinearInterpolator;
import androidx.lifecycle.Observer;
import com.jd.lib.productdetail.core.entitys.SkinRecommendInfoEntity;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.protocol.SkinRecommendInfoProtocol;
import com.jd.lib.productdetail.core.utils.PDCalorieImageUtil;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.holder.cf.d;
import java.util.List;

/* loaded from: classes15.dex */
public class b implements Observer<PdBaseProtocolLiveData.Result<SkinRecommendInfoEntity>> {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ d.a f4705g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ SkinRecommendInfoProtocol f4706h;

    public b(d dVar, d.a aVar, SkinRecommendInfoProtocol skinRecommendInfoProtocol) {
        this.f4705g = aVar;
        this.f4706h = skinRecommendInfoProtocol;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(PdBaseProtocolLiveData.Result<SkinRecommendInfoEntity> result) {
        d.a aVar;
        SkinRecommendInfoEntity.Data data;
        List<SkinRecommendInfoEntity.CompleteSkus> list;
        PdMCfRecommendItemView pdMCfRecommendItemView;
        List<SkinRecommendInfoEntity.Functions> list2;
        PdMCfRecommendItemView pdMCfRecommendItemView2;
        List<SkinRecommendInfoEntity.Functions> list3;
        PdMCfRecommendItemView pdMCfRecommendItemView3;
        List<SkinRecommendInfoEntity.Functions> list4;
        PdBaseProtocolLiveData.Result<SkinRecommendInfoEntity> result2 = result;
        if (result2 != null) {
            PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
            PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = PdBaseProtocolLiveData.Result.DataStatus.SUCCESS;
            if (dataStatus == dataStatus2) {
                SkinRecommendInfoEntity skinRecommendInfoEntity = result2.mData;
                if (skinRecommendInfoEntity.success && (aVar = this.f4705g) != null) {
                    PdMCfViewHolder pdMCfViewHolder = PdMCfViewHolder.this;
                    if (!pdMCfViewHolder.f4651k) {
                        pdMCfViewHolder.f4654n.pdHolderDataContainer.skinRecommendInfoEntity = skinRecommendInfoEntity;
                        if (skinRecommendInfoEntity != null && (data = skinRecommendInfoEntity.data) != null) {
                            boolean z = pdMCfViewHolder.R;
                            if (data.isMatch && !TextUtils.isEmpty(data.percentage)) {
                                pdMCfViewHolder.R = false;
                                pdMCfViewHolder.M.setVisibility(0);
                                pdMCfViewHolder.K.setText(data.percentage);
                                PDCalorieImageUtil.get().display("2724", pdMCfViewHolder.L);
                                if (z) {
                                    pdMCfViewHolder.S.setDuration(1000L);
                                    pdMCfViewHolder.S.setInterpolator(new LinearInterpolator());
                                    pdMCfViewHolder.M.startAnimation(pdMCfViewHolder.S);
                                }
                            } else {
                                pdMCfViewHolder.M.setVisibility(8);
                            }
                            if (!data.isMatch && (list = data.completeSkus) != null && list.size() >= 3) {
                                pdMCfViewHolder.J.setVisibility(8);
                                pdMCfViewHolder.Q.setVisibility(0);
                                PdMCfRecommendView pdMCfRecommendView = pdMCfViewHolder.Q;
                                List<SkinRecommendInfoEntity.CompleteSkus> list5 = data.completeSkus;
                                pdMCfRecommendView.getClass();
                                if (list5 != null && list5.size() >= 3) {
                                    pdMCfRecommendView.f4698g.setText(pdMCfRecommendView.getContext().getString(R.string.lib_pd_image_topimage_cf_recommend_title));
                                    pdMCfRecommendView.f4699h.setText(pdMCfRecommendView.getContext().getString(R.string.lib_pd_image_topimage_cf_recommend_title_right));
                                    SkinRecommendInfoEntity.CompleteSkus completeSkus = list5.get(0);
                                    if (completeSkus != null && (pdMCfRecommendItemView3 = pdMCfRecommendView.f4700i) != null && (list4 = completeSkus.functions) != null) {
                                        pdMCfRecommendItemView3.a(completeSkus.image, list4.get(0).function, completeSkus.price);
                                    }
                                    SkinRecommendInfoEntity.CompleteSkus completeSkus2 = list5.get(1);
                                    if (completeSkus != null && (pdMCfRecommendItemView2 = pdMCfRecommendView.f4701j) != null && (list3 = completeSkus2.functions) != null) {
                                        pdMCfRecommendItemView2.a(completeSkus2.image, list3.get(0).function, completeSkus2.price);
                                    }
                                    SkinRecommendInfoEntity.CompleteSkus completeSkus3 = list5.get(2);
                                    if (completeSkus != null && (pdMCfRecommendItemView = pdMCfRecommendView.f4702k) != null && (list2 = completeSkus3.functions) != null) {
                                        pdMCfRecommendItemView.a(completeSkus3.image, list2.get(0).function, completeSkus3.price);
                                    }
                                } else {
                                    pdMCfRecommendView.setVisibility(8);
                                }
                                pdMCfViewHolder.f4654n.mtaExposure("Productdetail_ScanSkinToastExpo");
                            } else {
                                pdMCfViewHolder.Q.setVisibility(8);
                                pdMCfViewHolder.J.setVisibility(0);
                            }
                        }
                    }
                }
            }
            PdBaseProtocolLiveData.Result.DataStatus dataStatus3 = result2.mStatus;
            if (dataStatus3 == dataStatus2 || dataStatus3 == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                this.f4706h.mResult.removeObserver(this);
                return;
            }
            return;
        }
        this.f4706h.mResult.removeObserver(this);
    }
}
