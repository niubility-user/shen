package com.jd.lib.productdetail.mainimage.holder.cf;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicPicItems;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMainPictureCfInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.protocol.SkinRecommendInfoProtocol;
import com.jd.lib.productdetail.core.utils.OpenAppUtils;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jd.lib.productdetail.mainimage.holder.cf.PdMCfRecommendView;
import com.jd.lib.productdetail.mainimage.holder.cf.d;
import com.jd.lib.productdetail.mainimage.presenter.PdHolderDataContainer;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.JDImageUtils;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMCfViewHolder extends PdMainImageBaseHolder {
    public TextView B;
    public RelativeLayout C;
    public TextView D;
    public TextView E;
    public TextView F;
    public TextView G;
    public TextView H;
    public TextView I;
    public ImageView J;
    public TextView K;
    public SimpleDraweeView L;
    public LinearLayout M;
    public WareBusinessMainPictureCfInfo N;
    public WareBusinessMagicPicItems O;
    public d P;
    public PdMCfRecommendView Q;
    public boolean R;
    public TranslateAnimation S;

    /* loaded from: classes15.dex */
    public class a implements View.OnClickListener {
        public a() {
            PdMCfViewHolder.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PDUtils.repeatClick() && !TextUtils.isEmpty(PdMCfViewHolder.this.O.jumpUrl)) {
                if (PdMCfViewHolder.this.O.jumpUrl.startsWith("http")) {
                    PDBaseDeepLinkHelper.gotoMWithUrl(view.getContext(), PdMCfViewHolder.this.O.jumpUrl);
                } else {
                    OpenAppUtils.openAppForInner(view.getContext(), PdMCfViewHolder.this.O.jumpUrl);
                }
                PdMainImagePresenter pdMainImagePresenter = PdMCfViewHolder.this.f4654n;
                if (pdMainImagePresenter != null) {
                    pdMainImagePresenter.jumpToPage.setValue(Boolean.TRUE);
                }
            }
        }
    }

    /* loaded from: classes15.dex */
    public class b implements PdMCfRecommendView.a {
        public b() {
            PdMCfViewHolder.this = r1;
        }
    }

    /* loaded from: classes15.dex */
    public class c implements d.a {
        public c() {
            PdMCfViewHolder.this = r1;
        }
    }

    public PdMCfViewHolder(@NonNull View view, View view2) {
        super(view, view2);
        this.R = true;
        this.S = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
    }

    public final void G() {
        this.B.setVisibility(4);
        this.D.setVisibility(8);
        this.E.setVisibility(8);
        this.F.setVisibility(8);
        this.G.setVisibility(8);
        this.H.setVisibility(8);
        this.I.setVisibility(8);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void a(boolean z) {
        PdMainImagePresenter pdMainImagePresenter;
        PdHolderDataContainer pdHolderDataContainer;
        String str;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        WareBusinessMainPictureCfInfo wareBusinessMainPictureCfInfo;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdArVrBizData pdArVrBizData;
        this.f4650j = z;
        if (!z || (pdMainImagePresenter = this.f4654n) == null || (pdHolderDataContainer = pdMainImagePresenter.pdHolderDataContainer) == null || pdHolderDataContainer.skinRecommendInfoEntity != null) {
            return;
        }
        AddressGlobal addressGlobal = AddressUtil.getAddressGlobal();
        String str2 = "";
        if (addressGlobal != null) {
            str2 = String.valueOf(addressGlobal.getLongitude());
            str = String.valueOf(addressGlobal.getLatitude());
        } else {
            str = "";
        }
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            str2 = "116.40";
            str = "39.90";
        }
        if (this.N == null && (wareBusinessMagicHeadPicInfoEntity = this.r) != null && (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) != null && (pdArVrBizData = wareBuinessUnitMainImageBizDataEntity.arVrBizData) != null) {
            this.N = pdArVrBizData.cfInfo;
        }
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.v;
        if (wareBusinessUnitMainImageEntity == null || (extMapEntity = wareBusinessUnitMainImageEntity.extMap) == null || (wareBusinessMainPictureCfInfo = this.N) == null) {
            return;
        }
        d dVar = this.P;
        BaseActivity baseActivity = (BaseActivity) this.f4649i;
        String str3 = wareBusinessMainPictureCfInfo.caseNo;
        Long valueOf = Long.valueOf(extMapEntity.skuId);
        c cVar = new c();
        dVar.getClass();
        SkinRecommendInfoProtocol skinRecommendInfoProtocol = new SkinRecommendInfoProtocol();
        SkinRecommendInfoProtocol.SkinRecommendInfoRequestParams skinRecommendInfoRequestParams = new SkinRecommendInfoProtocol.SkinRecommendInfoRequestParams();
        skinRecommendInfoRequestParams.caseNo = str3;
        skinRecommendInfoRequestParams.skuId = Long.valueOf(valueOf.longValue());
        skinRecommendInfoRequestParams.lon = str2;
        skinRecommendInfoRequestParams.lat = str;
        skinRecommendInfoProtocol.mRequestParams = skinRecommendInfoRequestParams;
        skinRecommendInfoProtocol.request(baseActivity);
        skinRecommendInfoProtocol.mResult.observe(baseActivity, new com.jd.lib.productdetail.mainimage.holder.cf.b(dVar, cVar, skinRecommendInfoProtocol));
    }

    public final void b(boolean z) {
        int i2 = R.drawable.lib_pd_mainimage_topimage_newcf_switch_open;
        if (!z) {
            i2 = R.drawable.lib_pd_mainimage_topimage_newcf_switch_close;
            this.C.setVisibility(8);
        } else {
            this.C.setVisibility(0);
        }
        Drawable drawable = ContextCompat.getDrawable(this.f4649i, i2);
        if (drawable != null) {
            drawable.setBounds(0, 0, PDUtils.dip2px(12.0f), PDUtils.dip2px(12.0f));
            this.B.setCompoundDrawables(null, null, drawable, null);
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void d(View view) {
        this.B = (TextView) view.findViewById(R.id.pd_topimage_newcf_switch);
        this.C = (RelativeLayout) view.findViewById(R.id.pd_topimage_newcf_taglayout);
        this.D = (TextView) view.findViewById(R.id.pd_topimage_newcf_left_tag1);
        this.E = (TextView) view.findViewById(R.id.pd_topimage_newcf_left_tag2);
        this.F = (TextView) view.findViewById(R.id.pd_topimage_newcf_left_tag3);
        this.G = (TextView) view.findViewById(R.id.pd_topimage_newcf_right_tag1);
        this.H = (TextView) view.findViewById(R.id.pd_topimage_newcf_right_tag2);
        this.I = (TextView) view.findViewById(R.id.pd_topimage_newcf_right_tag3);
        this.J = (ImageView) view.findViewById(R.id.pd_topimage_newcf_btn);
        this.K = (TextView) view.findViewById(R.id.pd_topimage_newcf_message);
        this.L = (SimpleDraweeView) view.findViewById(R.id.pd_topimage_newcf_message_icon);
        this.M = (LinearLayout) view.findViewById(R.id.pd_topimage_newcf_message_layout);
        this.Q = (PdMCfRecommendView) view.findViewById(R.id.pd_topimage_newcf_recommend);
        this.P = new d();
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void n() {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        List<String> list;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
        if (wareBusinessMagicHeadPicInfoEntity != null && (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) != null && wareBuinessUnitMainImageBizDataEntity.arVrBizData != null) {
            G();
            WareBuinessUnitMainImageBizDataEntity.PdArVrBizData pdArVrBizData = this.r.bizData.arVrBizData;
            this.N = pdArVrBizData.cfInfo;
            List<WareBusinessMagicPicItems> list2 = pdArVrBizData.items;
            if (list2 != null && list2.size() > 0) {
                this.O = this.r.bizData.arVrBizData.items.get(0);
            }
            WareBusinessMainPictureCfInfo wareBusinessMainPictureCfInfo = this.N;
            if (wareBusinessMainPictureCfInfo != null && (list = wareBusinessMainPictureCfInfo.diagnosis) != null && list.size() > 1) {
                this.C.setVisibility(0);
                if (!TextUtils.isEmpty(this.N.diagnosisTitle)) {
                    this.B.setText(this.N.diagnosisTitle);
                } else {
                    this.B.setText(this.f4649i.getString(R.string.lib_pd_image_topimage_cf_tag));
                }
                if (TextUtils.equals(DYConstants.DY_TRUE, JDMobileConfig.getInstance().getConfig("JDProductdetail", "isCFHolderChangeStyle", "enable", DYConstants.DY_TRUE))) {
                    WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.v;
                    boolean z = (wareBusinessUnitMainImageEntity == null || (extMapEntity = wareBusinessUnitMainImageEntity.extMap) == null || !extMapEntity.mainPicV12) ? false : true;
                    if (this.B.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.B.getLayoutParams();
                        if (z) {
                            layoutParams.setMargins(PDUtils.dip2px(this.f4649i, 10.0f), PDUtils.dip2px(this.f4649i, 74.0f), PDUtils.dip2px(this.f4649i, 10.0f), 0);
                        } else {
                            layoutParams.setMargins(PDUtils.dip2px(this.f4649i, 10.0f), PDUtils.dip2px(this.f4649i, 67.0f), PDUtils.dip2px(this.f4649i, 10.0f), 0);
                        }
                        this.B.setLayoutParams(layoutParams);
                    }
                    if (this.Q.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.Q.getLayoutParams();
                        if (z) {
                            layoutParams2.setMargins(PDUtils.dip2px(this.f4649i, 8.0f), 0, 0, PDUtils.dip2px(this.f4649i, 8.0f));
                        } else {
                            layoutParams2.setMargins(PDUtils.dip2px(this.f4649i, 10.0f), 0, 0, PDUtils.dip2px(this.f4649i, 10.0f));
                        }
                        this.Q.setLayoutParams(layoutParams2);
                    }
                }
                this.B.setVisibility(0);
                this.B.setOnClickListener(this);
                b(this.f4654n.pdHolderDataContainer.isSHowCfTag);
                if (this.N.diagnosis.size() == 2) {
                    this.D.setVisibility(0);
                    this.G.setVisibility(0);
                    this.D.setText(this.N.diagnosis.get(0));
                    this.G.setText(this.N.diagnosis.get(1));
                } else if (this.N.diagnosis.size() == 3) {
                    this.D.setVisibility(0);
                    this.G.setVisibility(0);
                    this.E.setVisibility(0);
                    this.D.setText(this.N.diagnosis.get(0));
                    this.G.setText(this.N.diagnosis.get(1));
                    this.E.setText(this.N.diagnosis.get(2));
                } else if (this.N.diagnosis.size() == 4) {
                    this.D.setVisibility(0);
                    this.E.setVisibility(0);
                    this.G.setVisibility(0);
                    this.H.setVisibility(0);
                    this.D.setText(this.N.diagnosis.get(0));
                    this.G.setText(this.N.diagnosis.get(1));
                    this.E.setText(this.N.diagnosis.get(2));
                    this.H.setText(this.N.diagnosis.get(3));
                } else if (this.N.diagnosis.size() == 5) {
                    this.D.setVisibility(0);
                    this.E.setVisibility(0);
                    this.G.setVisibility(0);
                    this.H.setVisibility(0);
                    this.F.setVisibility(0);
                    this.D.setText(this.N.diagnosis.get(0));
                    this.G.setText(this.N.diagnosis.get(1));
                    this.E.setText(this.N.diagnosis.get(2));
                    this.H.setText(this.N.diagnosis.get(3));
                    this.F.setText(this.N.diagnosis.get(4));
                } else if (this.N.diagnosis.size() >= 6) {
                    this.D.setVisibility(0);
                    this.E.setVisibility(0);
                    this.G.setVisibility(0);
                    this.H.setVisibility(0);
                    this.F.setVisibility(0);
                    this.I.setVisibility(0);
                    this.D.setText(this.N.diagnosis.get(0));
                    this.G.setText(this.N.diagnosis.get(1));
                    this.E.setText(this.N.diagnosis.get(2));
                    this.H.setText(this.N.diagnosis.get(3));
                    this.F.setText(this.N.diagnosis.get(4));
                    this.I.setText(this.N.diagnosis.get(5));
                }
            } else {
                this.B.setVisibility(8);
                this.C.setVisibility(8);
            }
            if (this.O != null) {
                this.J.setVisibility(0);
                JDImageUtils.displayImage(this.O.icon, this.J);
                this.J.setOnClickListener(new a());
            }
            this.Q.a(new b());
            return;
        }
        G();
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.pd_topimage_newcf_switch) {
            PdHolderDataContainer pdHolderDataContainer = this.f4654n.pdHolderDataContainer;
            boolean z = !pdHolderDataContainer.isSHowCfTag;
            pdHolderDataContainer.isSHowCfTag = z;
            b(z);
            PdMainImagePresenter pdMainImagePresenter = this.f4654n;
            if (pdMainImagePresenter.pdHolderDataContainer.isSHowCfTag) {
                return;
            }
            pdMainImagePresenter.mtaClick("Productdetail_ScanSkinShield");
            return;
        }
        super.onClick(view);
    }
}
