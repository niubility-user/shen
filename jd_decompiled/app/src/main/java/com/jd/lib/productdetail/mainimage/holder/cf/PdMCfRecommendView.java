package com.jd.lib.productdetail.mainimage.holder.cf;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.jd.lib.productdetail.core.entitys.PreferentialRecommendTabItemEntity;
import com.jd.lib.productdetail.core.entitys.SkinRecommendInfoEntity;
import com.jd.lib.productdetail.core.utils.OpenAppUtils;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.holder.cf.PdMCfViewHolder;
import com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendTabRecyclerView;
import com.jd.lib.productdetail.mainimage.presenter.PdHolderDataContainer;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.view.PdImageFromType;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMCfRecommendView extends RelativeLayout implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public TextView f4698g;

    /* renamed from: h  reason: collision with root package name */
    public TextView f4699h;

    /* renamed from: i  reason: collision with root package name */
    public PdMCfRecommendItemView f4700i;

    /* renamed from: j  reason: collision with root package name */
    public PdMCfRecommendItemView f4701j;

    /* renamed from: k  reason: collision with root package name */
    public PdMCfRecommendItemView f4702k;

    /* renamed from: l  reason: collision with root package name */
    public a f4703l;

    /* loaded from: classes15.dex */
    public interface a {
    }

    public PdMCfRecommendView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(a aVar) {
        this.f4703l = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        a aVar;
        PdHolderDataContainer pdHolderDataContainer;
        SkinRecommendInfoEntity skinRecommendInfoEntity;
        SkinRecommendInfoEntity.Data data;
        PdMCFRecommendLayerView pdMCFRecommendLayerView;
        Context context;
        int i2;
        Context context2;
        int i3;
        boolean z;
        int id = view.getId();
        if (id != R.id.topimage_cf_rec_title && id != R.id.topimage_cf_rec_title_right) {
            if ((id == R.id.topimage_cf_rec_image_left || id == R.id.topimage_cf_rec_image_mid || id == R.id.topimage_cf_rec_image_right) && (aVar = this.f4703l) != null) {
                PdMCfViewHolder.b bVar = (PdMCfViewHolder.b) aVar;
                PdMCfViewHolder pdMCfViewHolder = PdMCfViewHolder.this;
                PdMainImagePresenter pdMainImagePresenter = pdMCfViewHolder.f4654n;
                if (pdMainImagePresenter != null && pdMainImagePresenter.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
                    pdMainImagePresenter.toDetailPage.setValue(Boolean.TRUE);
                    return;
                }
                if (pdMainImagePresenter != null && (pdHolderDataContainer = pdMainImagePresenter.pdHolderDataContainer) != null && (skinRecommendInfoEntity = pdHolderDataContainer.skinRecommendInfoEntity) != null && (data = skinRecommendInfoEntity.data) != null) {
                    List<SkinRecommendInfoEntity.Diagnosis> list = data.diagnosis;
                    if (list == null || list.size() <= 0) {
                        pdMCFRecommendLayerView = null;
                    } else {
                        pdMCFRecommendLayerView = new PdMCFRecommendLayerView(pdMCfViewHolder.f4649i);
                        PdMainImagePresenter pdMainImagePresenter2 = pdMCfViewHolder.f4654n;
                        pdMCFRecommendLayerView.s = pdMainImagePresenter2;
                        PdMPartsRecommendTabRecyclerView pdMPartsRecommendTabRecyclerView = pdMCFRecommendLayerView.f4690j;
                        if (pdMPartsRecommendTabRecyclerView != null) {
                            pdMPartsRecommendTabRecyclerView.h(pdMainImagePresenter2);
                        }
                        pdMCFRecommendLayerView.r = data;
                        pdMCFRecommendLayerView.q.setBackgroundResource(pdMCFRecommendLayerView.s.getMainImageParams().isDark ? R.drawable.lib_pd_mainimage_parts_recommend_dark_bg : R.drawable.lib_pd_mainimage_parts_recommend_bg);
                        pdMCFRecommendLayerView.f4688h.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(pdMCFRecommendLayerView.f4687g, R.color.lib_pd_image_color_1A1A1A, pdMCFRecommendLayerView.s.getMainImageParams().isDark));
                        ConstraintLayout constraintLayout = pdMCFRecommendLayerView.f4692l;
                        if (pdMCFRecommendLayerView.s.getMainImageParams().isDark) {
                            context = pdMCFRecommendLayerView.f4687g;
                            i2 = R.color.lib_pd_image_color_302E2E;
                        } else {
                            context = pdMCFRecommendLayerView.f4687g;
                            i2 = R.color.lib_pd_image_color_FFFFFF;
                        }
                        constraintLayout.setBackgroundColor(ContextCompat.getColor(context, i2));
                        View view2 = pdMCFRecommendLayerView.f4693m;
                        if (pdMCFRecommendLayerView.s.getMainImageParams().isDark) {
                            context2 = pdMCFRecommendLayerView.f4687g;
                            i3 = R.color.lib_pd_image_color_222222;
                        } else {
                            context2 = pdMCFRecommendLayerView.f4687g;
                            i3 = R.color.lib_pd_image_color_E5E5E5;
                        }
                        view2.setBackgroundColor(ContextCompat.getColor(context2, i3));
                        SkinRecommendInfoEntity.Data data2 = pdMCFRecommendLayerView.r;
                        if (data2 != null) {
                            if (pdMCFRecommendLayerView.f4690j != null) {
                                ArrayList arrayList = new ArrayList();
                                if (data2.completeSkus == null || TextUtils.isEmpty(data2.completeSkusLabel)) {
                                    z = false;
                                } else {
                                    PreferentialRecommendTabItemEntity preferentialRecommendTabItemEntity = new PreferentialRecommendTabItemEntity();
                                    preferentialRecommendTabItemEntity.title = data2.completeSkusLabel;
                                    preferentialRecommendTabItemEntity.tabId = String.valueOf(0);
                                    arrayList.add(preferentialRecommendTabItemEntity);
                                    z = true;
                                }
                                if (data2.diagnosis != null) {
                                    for (int i4 = 0; i4 < data2.diagnosis.size(); i4++) {
                                        PreferentialRecommendTabItemEntity preferentialRecommendTabItemEntity2 = new PreferentialRecommendTabItemEntity();
                                        preferentialRecommendTabItemEntity2.title = data2.diagnosis.get(i4).label;
                                        if (z) {
                                            preferentialRecommendTabItemEntity2.tabId = String.valueOf(i4 + 1);
                                        } else {
                                            preferentialRecommendTabItemEntity2.tabId = String.valueOf(i4);
                                        }
                                        arrayList.add(preferentialRecommendTabItemEntity2);
                                    }
                                    if (arrayList.size() > 1) {
                                        pdMCFRecommendLayerView.f4690j.setVisibility(0);
                                        pdMCFRecommendLayerView.f4690j.f(arrayList);
                                        pdMCFRecommendLayerView.f4690j.scrollToPosition(0);
                                        pdMCFRecommendLayerView.f4690j.b(0);
                                    } else {
                                        pdMCFRecommendLayerView.f4690j.setVisibility(8);
                                    }
                                }
                            }
                            PdMCFRecommendLayerAdapter pdMCFRecommendLayerAdapter = pdMCFRecommendLayerView.p;
                            pdMCFRecommendLayerAdapter.f4677e = pdMainImagePresenter2;
                            List<SkinRecommendInfoEntity.CompleteSkus> a2 = pdMCFRecommendLayerView.a(pdMCFRecommendLayerView.r, 0);
                            String str = pdMCFRecommendLayerView.r.completeSkusDesc;
                            pdMCFRecommendLayerAdapter.b = a2;
                            pdMCFRecommendLayerAdapter.f4676c = str;
                            pdMCFRecommendLayerAdapter.notifyDataSetChanged();
                        }
                        pdMCFRecommendLayerView.d(new e(pdMCfViewHolder));
                    }
                    if (pdMCFRecommendLayerView != null) {
                        PdMCfViewHolder.this.e(pdMCFRecommendLayerView, null, false, true);
                        PdMCfViewHolder.this.f4654n.mtaExposure("Productdetail_ScanSkinExpo");
                    }
                }
                PdMainImagePresenter pdMainImagePresenter3 = PdMCfViewHolder.this.f4654n;
                if (pdMainImagePresenter3 != null) {
                    pdMainImagePresenter3.mtaClick("Productdetail_ScanSkinToast");
                    return;
                }
                return;
            }
            return;
        }
        a aVar2 = this.f4703l;
        if (aVar2 != null) {
            PdMCfViewHolder.b bVar2 = (PdMCfViewHolder.b) aVar2;
            bVar2.getClass();
            if (PDUtils.repeatClick()) {
                if (!TextUtils.isEmpty(PdMCfViewHolder.this.O.jumpUrl)) {
                    if (PdMCfViewHolder.this.O.jumpUrl.startsWith("http")) {
                        PdMCfViewHolder pdMCfViewHolder2 = PdMCfViewHolder.this;
                        PDBaseDeepLinkHelper.gotoMWithUrl(pdMCfViewHolder2.f4649i, pdMCfViewHolder2.O.jumpUrl);
                    } else {
                        PdMCfViewHolder pdMCfViewHolder3 = PdMCfViewHolder.this;
                        OpenAppUtils.openAppForInner(pdMCfViewHolder3.f4649i, pdMCfViewHolder3.O.jumpUrl);
                    }
                }
                PdMainImagePresenter pdMainImagePresenter4 = PdMCfViewHolder.this.f4654n;
                if (pdMainImagePresenter4 != null) {
                    pdMainImagePresenter4.jumpToPage.setValue(Boolean.TRUE);
                }
                PdMainImagePresenter pdMainImagePresenter5 = PdMCfViewHolder.this.f4654n;
                if (pdMainImagePresenter5 != null) {
                    pdMainImagePresenter5.mtaClick("Productdetail_ScanSkinView");
                }
            }
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4698g = (TextView) findViewById(R.id.topimage_cf_rec_title);
        this.f4699h = (TextView) findViewById(R.id.topimage_cf_rec_title_right);
        this.f4700i = (PdMCfRecommendItemView) findViewById(R.id.topimage_cf_rec_image_left);
        this.f4701j = (PdMCfRecommendItemView) findViewById(R.id.topimage_cf_rec_image_mid);
        this.f4702k = (PdMCfRecommendItemView) findViewById(R.id.topimage_cf_rec_image_right);
        this.f4698g.setOnClickListener(this);
        this.f4699h.setOnClickListener(this);
        this.f4700i.setOnClickListener(this);
        this.f4701j.setOnClickListener(this);
        this.f4702k.setOnClickListener(this);
    }
}
