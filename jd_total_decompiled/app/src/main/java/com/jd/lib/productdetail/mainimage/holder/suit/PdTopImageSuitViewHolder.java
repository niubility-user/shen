package com.jd.lib.productdetail.mainimage.holder.suit;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PDTopReocommendEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdDpgSmallInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdMainSku;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMainPictureDpgPops;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jd.lib.productdetail.mainimage.old.PdMImagePartsDpgIntegrateView;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class PdTopImageSuitViewHolder extends PdMainImageBaseHolder implements com.jd.lib.productdetail.mainimage.dialog.a {
    public PdMImagePartsDpgIntegrateView B;
    public final PdTopImageSuitView C;

    /* loaded from: classes15.dex */
    public class a implements PdMImagePartsDpgIntegrateView.a {
        public a() {
        }

        @Override // com.jd.lib.productdetail.mainimage.old.PdMImagePartsDpgIntegrateView.a
        public void a() {
            PdTopImageSuitViewHolder.this.s();
        }
    }

    public PdTopImageSuitViewHolder(@NonNull View view, PdTopImageSuitView pdTopImageSuitView) {
        super(view, pdTopImageSuitView);
        this.C = pdTopImageSuitView;
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public boolean A() {
        return true;
    }

    @Override // com.jd.lib.productdetail.mainimage.dialog.a
    public void a() {
    }

    @Override // com.jd.lib.productdetail.mainimage.dialog.a
    public void a(Object obj) {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdDpgSuitBizData pdDpgSuitBizData;
        ArrayList<WareBusinessMainPictureDpgPops> arrayList = null;
        if (this.B == null) {
            PdMImagePartsDpgIntegrateView pdMImagePartsDpgIntegrateView = (PdMImagePartsDpgIntegrateView) LayoutInflater.from(this.f4649i).inflate(R.layout.lib_pd_mainimage_parts_dpg_dialog_layout, (ViewGroup) null);
            this.B = pdMImagePartsDpgIntegrateView;
            PdMainImagePresenter pdMainImagePresenter = this.f4654n;
            if (pdMainImagePresenter != null) {
                pdMImagePartsDpgIntegrateView.c(pdMainImagePresenter);
            }
        }
        if (obj instanceof PDTopReocommendEntity) {
            PDTopReocommendEntity pDTopReocommendEntity = (PDTopReocommendEntity) obj;
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
            if (wareBusinessMagicHeadPicInfoEntity != null && (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) != null && (pdDpgSuitBizData = wareBuinessUnitMainImageBizDataEntity.dpgSuitBizData) != null) {
                arrayList = pdDpgSuitBizData.mainPictureDpgPops;
            }
            this.B.a(this.v, pDTopReocommendEntity, arrayList);
            this.B.d(new a());
            o(this.B);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0023, code lost:
        if (r0.getSuitDetails() != null) goto L19;
     */
    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void n() {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        if (this.C != null) {
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
            boolean z = true;
            if ((wareBusinessMagicHeadPicInfoEntity == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) == null || wareBuinessUnitMainImageBizDataEntity.dpgSuitBizData == null) ? false : true) {
                WareBuinessUnitMainImageBizDataEntity.PdDpgSuitBizData pdDpgSuitBizData = wareBusinessMagicHeadPicInfoEntity.bizData.dpgSuitBizData;
                if (pdDpgSuitBizData.mainProductShortDpgSuit != null) {
                }
            }
            z = false;
            if (z) {
                PdTopImageSuitView pdTopImageSuitView = this.C;
                pdTopImageSuitView.f4939h = this.f4654n;
                WareBuinessUnitMainImageBizDataEntity.PdDpgSuitBizData pdDpgSuitBizData2 = this.r.bizData.dpgSuitBizData;
                PdMainSku pdMainSku = pdDpgSuitBizData2.mainProductShortDpgSuit;
                ArrayList<PdDpgSmallInfo> suitDetails = pdDpgSuitBizData2.getSuitDetails();
                WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity2 = this.r;
                String str = wareBusinessMagicHeadPicInfoEntity2.anchorType;
                WareBuinessUnitMainImageBizDataEntity.PdDpgSuitBizData pdDpgSuitBizData3 = wareBusinessMagicHeadPicInfoEntity2.bizData.dpgSuitBizData;
                pdTopImageSuitView.b(pdMainSku, suitDetails, "", str, pdDpgSuitBizData3.recommendRankShowMap, pdDpgSuitBizData3.dpgIntegration);
            } else {
                PdTopImageSuitView pdTopImageSuitView2 = this.C;
                PdTopImageSuitViewFloor pdTopImageSuitViewFloor = pdTopImageSuitView2.f4940i;
                if (pdTopImageSuitViewFloor != null) {
                    pdTopImageSuitViewFloor.setVisibility(8);
                }
                PdTopImageSuitViewFloor pdTopImageSuitViewFloor2 = pdTopImageSuitView2.f4941j;
                if (pdTopImageSuitViewFloor2 != null) {
                    pdTopImageSuitViewFloor2.setVisibility(8);
                }
            }
            this.C.d(this);
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void a(boolean z) {
        ArrayList<PdDpgSmallInfo> arrayList;
        this.f4650j = z;
        PdMainImagePresenter pdMainImagePresenter = this.f4654n;
        if (pdMainImagePresenter == null || !z) {
            return;
        }
        pdMainImagePresenter.mtaExposure("Productdetail_MainPhotoDPExpo");
        PdTopImageSuitView pdTopImageSuitView = this.C;
        if (pdTopImageSuitView != null) {
            if (!((pdTopImageSuitView.f4942k == null || (arrayList = pdTopImageSuitView.f4943l) == null || arrayList.size() <= 0) ? false : true) || pdTopImageSuitView.f4939h == null) {
                return;
            }
            JDJSONArray jDJSONArray = new JDJSONArray();
            for (int i2 = 0; i2 < pdTopImageSuitView.f4943l.size(); i2++) {
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("matchid", (Object) "-100");
                if (pdTopImageSuitView.f4943l.get(i2) != null && pdTopImageSuitView.f4943l.get(i2).info != null && !TextUtils.isEmpty(pdTopImageSuitView.f4943l.get(i2).info.matchId)) {
                    jDJSONObject.put("matchid", (Object) pdTopImageSuitView.f4943l.get(i2).info.matchId);
                }
                jDJSONObject.put("type", (Object) pdTopImageSuitView.f4944m);
                jDJSONArray.add(jDJSONObject);
            }
            pdTopImageSuitView.f4939h.mtaExposure("Productdetail_MainPhotoProductExpo", jDJSONArray.toJSONString());
            JDJSONArray jDJSONArray2 = new JDJSONArray();
            for (int i3 = 0; i3 < pdTopImageSuitView.f4943l.size(); i3++) {
                JDJSONObject jDJSONObject2 = new JDJSONObject();
                jDJSONObject2.put("matchid", (Object) "-100");
                jDJSONObject2.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) "-100");
                if (pdTopImageSuitView.f4943l.get(i3) != null) {
                    if (pdTopImageSuitView.f4943l.get(i3).info != null && !TextUtils.isEmpty(pdTopImageSuitView.f4943l.get(i3).info.matchId)) {
                        jDJSONObject2.put("matchid", (Object) pdTopImageSuitView.f4943l.get(i3).info.matchId);
                    }
                    if (pdTopImageSuitView.f4943l.get(i3).items != null) {
                        StringBuilder sb = new StringBuilder();
                        for (int i4 = 0; i4 < pdTopImageSuitView.f4943l.get(i3).items.size(); i4++) {
                            if (pdTopImageSuitView.f4943l.get(i3).items.get(i4) != null) {
                                sb.append(pdTopImageSuitView.f4943l.get(i3).items.get(i4).skuId);
                                sb.append(CartConstant.KEY_YB_INFO_LINK);
                            }
                        }
                        if (sb.length() > 0) {
                            jDJSONObject2.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) sb.substring(0, sb.length() - 1));
                        }
                    }
                }
                jDJSONObject2.put("type", (Object) pdTopImageSuitView.f4944m);
                jDJSONArray2.add(jDJSONObject2);
            }
            pdTopImageSuitView.f4939h.mtaExposure("Productdetail_MainPhotoMatchProuctExpo", jDJSONArray2.toJSONString());
        }
    }
}
