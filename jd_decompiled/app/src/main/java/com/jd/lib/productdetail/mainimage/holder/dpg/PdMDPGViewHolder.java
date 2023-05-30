package com.jd.lib.productdetail.mainimage.holder.dpg;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PDTopReocommendEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMainPictureDpgPops;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.entitys.wozhe.MatchItemsBean;
import com.jd.lib.productdetail.core.entitys.wozhe.MatchSkusBean;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bean.PdImageEventCode;
import com.jd.lib.productdetail.mainimage.bean.PdMImageEventEntity;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jd.lib.productdetail.mainimage.old.PdMImagePartsDpgIntegrateView;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMDPGViewHolder extends PdMainImageBaseHolder implements com.jd.lib.productdetail.mainimage.dialog.a {
    public PdMImageAnchorLayout B;
    public PdMImagePartsDpgIntegrateView C;

    /* loaded from: classes15.dex */
    public class a implements PdMImagePartsDpgIntegrateView.a {
        public a() {
        }

        @Override // com.jd.lib.productdetail.mainimage.old.PdMImagePartsDpgIntegrateView.a
        public void a() {
            PdMDPGViewHolder.this.s();
        }
    }

    public PdMDPGViewHolder(@NonNull View view, View view2) {
        super(view, view2);
    }

    @Override // com.jd.lib.productdetail.mainimage.dialog.a
    public void a() {
    }

    @Override // com.jd.lib.productdetail.mainimage.dialog.a
    public void a(Object obj) {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdVrDpgBizData pdVrDpgBizData;
        ArrayList<WareBusinessMainPictureDpgPops> arrayList = null;
        if (this.C == null) {
            PdMImagePartsDpgIntegrateView pdMImagePartsDpgIntegrateView = (PdMImagePartsDpgIntegrateView) LayoutInflater.from(this.f4649i).inflate(R.layout.lib_pd_mainimage_parts_dpg_dialog_layout, (ViewGroup) null);
            this.C = pdMImagePartsDpgIntegrateView;
            PdMainImagePresenter pdMainImagePresenter = this.f4654n;
            if (pdMainImagePresenter != null) {
                pdMImagePartsDpgIntegrateView.c(pdMainImagePresenter);
            }
        }
        if (obj instanceof PDTopReocommendEntity) {
            PDTopReocommendEntity pDTopReocommendEntity = (PDTopReocommendEntity) obj;
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
            if (wareBusinessMagicHeadPicInfoEntity != null && (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) != null && (pdVrDpgBizData = wareBuinessUnitMainImageBizDataEntity.vrDpgBizData) != null) {
                arrayList = pdVrDpgBizData.mainPictureDpgPops;
                pDTopReocommendEntity.dpgIntegration = pdVrDpgBizData.dpgIntegration;
            }
            this.C.a(this.v, pDTopReocommendEntity, arrayList);
            this.C.d(new a());
            o(this.C);
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void d(View view) {
        this.B = (PdMImageAnchorLayout) view.findViewById(R.id.pd_topimage_anchorlayout);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void h(PdMImageEventEntity pdMImageEventEntity) {
        if (pdMImageEventEntity.pdImageEventCodeCode != PdImageEventCode.EVENT_CHANGE_SCREEN || this.f4651k) {
            return;
        }
        n();
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void j(PdMainImagePresenter pdMainImagePresenter) {
        super.j(pdMainImagePresenter);
        PdMImageAnchorLayout pdMImageAnchorLayout = this.B;
        if (pdMImageAnchorLayout != null) {
            pdMImageAnchorLayout.g(pdMainImagePresenter);
        }
        PdMImagePartsDpgIntegrateView pdMImagePartsDpgIntegrateView = this.C;
        if (pdMImagePartsDpgIntegrateView != null) {
            pdMImagePartsDpgIntegrateView.c(pdMainImagePresenter);
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void n() {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        PdMImageAnchorLayout pdMImageAnchorLayout;
        MatchSkusBean matchSkusBean;
        PdMImageAnchorLayout pdMImageAnchorLayout2;
        PdMainImagePresenter pdMainImagePresenter = this.f4654n;
        if (pdMainImagePresenter != null && (pdMImageAnchorLayout2 = this.B) != null) {
            pdMImageAnchorLayout2.g(pdMainImagePresenter);
        }
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.v;
        if (wareBusinessUnitMainImageEntity == null || wareBusinessUnitMainImageEntity.magicHeadPicInfo == null || (wareBuinessUnitMainImageBizDataEntity = this.r.bizData) == null || (pdMImageAnchorLayout = this.B) == null) {
            return;
        }
        MatchItemsBean matchItemsBean = wareBuinessUnitMainImageBizDataEntity.dpgHeadPicInfo;
        if (matchItemsBean.matchSkus != null) {
            pdMImageAnchorLayout.e(wareBusinessUnitMainImageEntity);
            this.B.j(matchItemsBean.source);
            this.B.f(this.r);
            List<MatchSkusBean> list = matchItemsBean.matchSkus;
            if (list != null && !list.isEmpty()) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (list.get(i2) != null) {
                        list.get(i2).changeX = this.f4654n.appImageWidth * (list.get(i2).x / 710.0f);
                        list.get(i2).changeY = this.f4654n.appImageHeight * (list.get(i2).y / 710.0f);
                    }
                }
            }
            this.B.i(matchItemsBean.matchSkus);
        }
        PdMImageAnchorLayout pdMImageAnchorLayout3 = this.B;
        int i3 = this.f4654n.appImageWidth;
        pdMImageAnchorLayout3.f4797h.removeAllViews();
        pdMImageAnchorLayout3.f4799j = new ArrayList<>();
        List<MatchSkusBean> list2 = pdMImageAnchorLayout3.f4796g;
        if (list2 != null && !list2.isEmpty()) {
            for (int i4 = 0; i4 < pdMImageAnchorLayout3.f4796g.size(); i4++) {
                if (pdMImageAnchorLayout3.f4796g.get(i4) != null && !TextUtils.isEmpty(pdMImageAnchorLayout3.f4796g.get(i4).name) && (matchSkusBean = pdMImageAnchorLayout3.f4796g.get(i4)) != null) {
                    View inflate = LayoutInflater.from(pdMImageAnchorLayout3.f4798i).inflate(TextUtils.isEmpty(matchSkusBean.subTitle) ? R.layout.lib_pd_mainimage_wozhe_img_point_topimage_small : R.layout.lib_pd_mainimage_wozhe_img_point_topimage_big, (ViewGroup) pdMImageAnchorLayout3, false);
                    if (inflate instanceof ConstraintLayout) {
                        ConstraintLayout constraintLayout = (ConstraintLayout) inflate;
                        MatchSkusBean matchSkusBean2 = pdMImageAnchorLayout3.f4796g.get(i4);
                        if (matchSkusBean2 != null) {
                            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) constraintLayout.findViewById(R.id.imgPoint);
                            ConstraintLayout constraintLayout2 = (ConstraintLayout) constraintLayout.findViewById(R.id.lib_pd_wozhe_img_point_title_ll);
                            TextView textView = (TextView) constraintLayout.findViewById(R.id.lib_pd_wozhe_img_point_title);
                            textView.setTypeface(FontsUtil.getTypeFace(pdMImageAnchorLayout3.f4798i, 4099));
                            textView.setText(matchSkusBean2.name);
                            ImageView imageView = (ImageView) constraintLayout.findViewById(R.id.lib_pd_wozhe_top_image_arrow);
                            TextView textView2 = (TextView) constraintLayout.findViewById(R.id.lib_pd_wozhe_img_point_title_price);
                            simpleDraweeView.setTag(Boolean.valueOf(textView2 != null));
                            if (textView2 != null) {
                                textView2.setTypeface(FontsUtil.getTypeFace(pdMImageAnchorLayout3.f4798i, 4099));
                                String str = matchSkusBean2.subTitle;
                                if (TextUtils.isEmpty(str)) {
                                    textView2.setVisibility(8);
                                } else {
                                    textView2.setText(str);
                                }
                            }
                            if (imageView != null) {
                                imageView.setVisibility(matchSkusBean2.current ? 8 : 0);
                            }
                            constraintLayout.post(new b(pdMImageAnchorLayout3, constraintLayout2, constraintLayout, simpleDraweeView, i3, matchSkusBean2));
                        }
                        if (!pdMImageAnchorLayout3.f4796g.get(i4).current) {
                            constraintLayout.setTag(pdMImageAnchorLayout3.f4796g.get(i4));
                        }
                        if (constraintLayout.getLayoutParams() instanceof FrameLayout.LayoutParams) {
                            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) constraintLayout.getLayoutParams();
                            layoutParams.leftMargin = (int) (pdMImageAnchorLayout3.f4796g.get(i4).changeX + 0.5f);
                            layoutParams.topMargin = (int) (pdMImageAnchorLayout3.f4796g.get(i4).changeY - 0.5f);
                            constraintLayout.setOnClickListener(pdMImageAnchorLayout3);
                            pdMImageAnchorLayout3.f4797h.addView(constraintLayout, layoutParams);
                        }
                    }
                }
            }
        }
        if (pdMImageAnchorLayout3.t != null) {
            pdMImageAnchorLayout3.f4801l.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(pdMImageAnchorLayout3.getContext(), R.color.lib_pd_image_color_FF232326, pdMImageAnchorLayout3.t.getMainImageParams().isDark));
            pdMImageAnchorLayout3.q.setBackgroundResource(pdMImageAnchorLayout3.t.getMainImageParams().isDark ? R.drawable.lib_pd_mainimage_wozhe_imgview_bg_dark : R.drawable.lib_pd_mainimage_wozhe_imgview_bg);
            pdMImageAnchorLayout3.r.setImageResource(com.jd.lib.productdetail.mainimage.utils.a.b(pdMImageAnchorLayout3.t.getMainImageParams().isDark));
            pdMImageAnchorLayout3.f4802m.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(pdMImageAnchorLayout3.getContext(), R.color.lib_pd_image_color_FA2C19, pdMImageAnchorLayout3.t.getMainImageParams().isDark));
        }
        this.B.h(this);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void a(boolean z) {
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        MatchItemsBean matchItemsBean;
        this.f4650j = z;
        if (!z || (wareBusinessMagicHeadPicInfoEntity = this.r) == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) == null || (matchItemsBean = wareBuinessUnitMainImageBizDataEntity.dpgHeadPicInfo) == null) {
            return;
        }
        JDJSONArray jDJSONArray = new JDJSONArray();
        List<MatchSkusBean> list = matchItemsBean.matchSkus;
        if (list != null && list.size() > 0) {
            for (int i2 = 0; i2 < matchItemsBean.matchSkus.size(); i2++) {
                MatchSkusBean matchSkusBean = matchItemsBean.matchSkus.get(i2);
                if (matchSkusBean != null) {
                    JDJSONObject jDJSONObject = new JDJSONObject();
                    jDJSONObject.put("matchsku", (Object) Long.valueOf(matchSkusBean.skuId));
                    if (!TextUtils.isEmpty(matchItemsBean.source)) {
                        jDJSONObject.put("source", (Object) matchItemsBean.source);
                    }
                    jDJSONObject.put("matchid", (Object) Integer.valueOf(matchItemsBean.matchId));
                    jDJSONArray.add(jDJSONObject);
                }
            }
        }
        this.f4654n.mtaExposure("Productdetail_MainPhotoMatchExpo", jDJSONArray.toJSONString());
        JDJSONObject jDJSONObject2 = new JDJSONObject();
        if (!TextUtils.isEmpty(matchItemsBean.source)) {
            jDJSONObject2.put("source", (Object) matchItemsBean.source);
        }
        jDJSONObject2.put("matchid", (Object) Integer.valueOf(matchItemsBean.matchId));
        jDJSONObject2.put("touchstone_expids", (Object) matchItemsBean.expIds);
        this.f4654n.mtaExposure("Productdetail_PhotoMatchEntranceExpo", jDJSONObject2.toJSONString());
        if (this.r.bizData.dpgHeadPicInfo.subText != null) {
            this.f4654n.mtaExposure("Productdetail_PhotoMatchDiscount");
        }
    }
}
