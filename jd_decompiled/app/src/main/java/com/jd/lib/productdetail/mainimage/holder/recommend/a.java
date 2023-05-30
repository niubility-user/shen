package com.jd.lib.productdetail.mainimage.holder.recommend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.MutableLiveData;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PDTopReocommendEntity;
import com.jd.lib.productdetail.core.entitys.topimagerecommend.PdRankEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMainPictureDpgPops;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageRecommendRankEntity;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.PdMImagePartsDpgIntegrateView;
import com.jd.lib.productdetail.mainimage.old.PdMPartsDpgViewAdapter;
import com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendView;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class a implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ PdMImageRecommendNewView f4930g;

    public a(PdMImageRecommendNewView pdMImageRecommendNewView) {
        this.f4930g = pdMImageRecommendNewView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        MutableLiveData<WareBusinessUnitMainImageEntity> mutableLiveData;
        ArrayList<WareBusinessMainPictureDpgPops> arrayList;
        MutableLiveData<WareBusinessUnitMainImageEntity> mutableLiveData2;
        PdMPartsRecommendView pdMPartsRecommendView;
        MutableLiveData<WareBusinessUnitMainImageEntity> mutableLiveData3;
        PdMImageRecommendNewView pdMImageRecommendNewView = this.f4930g;
        if (pdMImageRecommendNewView.r == null) {
            return;
        }
        pdMImageRecommendNewView.getClass();
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("type", (Object) WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_COMMENT_BANG_DAN);
        PdMainImagePresenter pdMainImagePresenter = pdMImageRecommendNewView.p;
        if (pdMainImagePresenter != null && (mutableLiveData3 = pdMainImagePresenter.mainImageData) != null && mutableLiveData3.getValue() != null) {
            jDJSONObject.put("QuesNum", (Object) "");
            jDJSONObject.put("isPhoto", (Object) "0");
            WareImageRecommendRankEntity wareImageRecommendRankEntity = pdMImageRecommendNewView.q;
            if (wareImageRecommendRankEntity != null) {
                jDJSONObject.put("rankid", (Object) wareImageRecommendRankEntity.rankId);
                jDJSONObject.put("touchstone_expids", (Object) pdMImageRecommendNewView.q.bangDanExperiment);
            } else {
                jDJSONObject.put("rankid", (Object) "");
                jDJSONObject.put("touchstone_expids", (Object) "");
            }
            jDJSONObject.put("frame_info", (Object) "-100");
            jDJSONObject.put("LableNum", (Object) "");
            jDJSONObject.put("click_pos", (Object) "-100");
            pdMImageRecommendNewView.p.mtaClick("Productdetail_FunctionEntrance", jDJSONObject.toJSONString());
        }
        PDTopReocommendEntity pDTopReocommendEntity = new PDTopReocommendEntity();
        PdMImageRecommendNewView pdMImageRecommendNewView2 = this.f4930g;
        PdRankEntity pdRankEntity = pdMImageRecommendNewView2.r;
        pDTopReocommendEntity.rankId = pdRankEntity.rankId;
        pDTopReocommendEntity.typeId = pdRankEntity.rankType;
        if (pdMImageRecommendNewView2.p != null) {
            WareImageRecommendRankEntity wareImageRecommendRankEntity2 = pdMImageRecommendNewView2.q;
            if (wareImageRecommendRankEntity2 != null && (arrayList = wareImageRecommendRankEntity2.mainPictureDpgPops) != null && arrayList.size() > 0) {
                PdMImageRecommendNewView pdMImageRecommendNewView3 = this.f4930g;
                if (pdMImageRecommendNewView3.t == null) {
                    PdMImagePartsDpgIntegrateView pdMImagePartsDpgIntegrateView = (PdMImagePartsDpgIntegrateView) LayoutInflater.from(pdMImageRecommendNewView3.f4908h).inflate(R.layout.lib_pd_mainimage_parts_dpg_dialog_layout, (ViewGroup) null);
                    pdMImageRecommendNewView3.t = pdMImagePartsDpgIntegrateView;
                    PdMainImagePresenter pdMainImagePresenter2 = pdMImageRecommendNewView3.p;
                    if (pdMainImagePresenter2 != null) {
                        pdMImagePartsDpgIntegrateView.c(pdMainImagePresenter2);
                    }
                }
                PdMainImagePresenter pdMainImagePresenter3 = pdMImageRecommendNewView3.p;
                if (pdMainImagePresenter3 == null || (mutableLiveData2 = pdMainImagePresenter3.mainImageData) == null || mutableLiveData2.getValue() == null || pdMImageRecommendNewView3.q == null) {
                    return;
                }
                PdMPartsDpgViewAdapter pdMPartsDpgViewAdapter = pdMImageRecommendNewView3.t.f5048n;
                if (pdMPartsDpgViewAdapter != null && (pdMPartsRecommendView = pdMPartsDpgViewAdapter.f5071c) != null) {
                    pdMPartsRecommendView.a();
                }
                pdMImageRecommendNewView3.t.a(pdMImageRecommendNewView3.p.mainImageData.getValue(), pDTopReocommendEntity, pdMImageRecommendNewView3.q.mainPictureDpgPops);
                pdMImageRecommendNewView3.t.d(new c(pdMImageRecommendNewView3));
                com.jd.lib.productdetail.mainimage.dialog.a aVar = pdMImageRecommendNewView3.u;
                if (aVar != null) {
                    aVar.a(pdMImageRecommendNewView3.t);
                    return;
                }
                return;
            }
            PdMImageRecommendNewView pdMImageRecommendNewView4 = this.f4930g;
            if (pdMImageRecommendNewView4.s == null) {
                PdMPartsRecommendView pdMPartsRecommendView2 = (PdMPartsRecommendView) LayoutInflater.from(pdMImageRecommendNewView4.f4908h).inflate(R.layout.lib_pd_mainimage_parts_recommend_dialog_layout, (ViewGroup) null);
                pdMImageRecommendNewView4.s = pdMPartsRecommendView2;
                PdMainImagePresenter pdMainImagePresenter4 = pdMImageRecommendNewView4.p;
                if (pdMainImagePresenter4 != null) {
                    pdMPartsRecommendView2.l(pdMainImagePresenter4);
                }
            }
            PdMainImagePresenter pdMainImagePresenter5 = pdMImageRecommendNewView4.p;
            if (pdMainImagePresenter5 == null || (mutableLiveData = pdMainImagePresenter5.mainImageData) == null || mutableLiveData.getValue() == null) {
                return;
            }
            pdMImageRecommendNewView4.s.a();
            pdMImageRecommendNewView4.s.d(pdMImageRecommendNewView4.p.mainImageData.getValue(), pDTopReocommendEntity.pid, pDTopReocommendEntity.tabId, pDTopReocommendEntity.layerTitle, false);
            pdMImageRecommendNewView4.s.n(true);
            pdMImageRecommendNewView4.s.m(new d(pdMImageRecommendNewView4));
            com.jd.lib.productdetail.mainimage.dialog.a aVar2 = pdMImageRecommendNewView4.u;
            if (aVar2 != null) {
                aVar2.a(pdMImageRecommendNewView4.s);
            }
        }
    }
}
