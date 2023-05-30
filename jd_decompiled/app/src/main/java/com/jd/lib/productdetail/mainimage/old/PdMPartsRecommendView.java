package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.AbBuriedExpLabelsEntity;
import com.jd.lib.productdetail.core.entitys.PdPreferentialRecommendProductExtInfo;
import com.jd.lib.productdetail.core.entitys.PdPreferentialRecommendProductListInfo;
import com.jd.lib.productdetail.core.entitys.PreferentialRecommendTabItemEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.AppStaticInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.PreferentialRecommendItemEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageRecommendRankEntity;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.protocol.PdRankRecommendProtecal;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jd.lib.productdetail.core.views.PdAutoChangeTextSize;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendSkuRecyclerView;
import com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendTabRecyclerView;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.abmta.ABMtaUtils;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.jdsdk.constant.CartConstant;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMPartsRecommendView extends ConstraintLayout implements View.OnClickListener {
    public static final /* synthetic */ int E = 0;
    public a A;
    public boolean B;
    public boolean C;
    public WareBusinessUnitMainImageEntity D;

    /* renamed from: g */
    public Context f5118g;

    /* renamed from: h */
    public AppCompatTextView f5119h;

    /* renamed from: i */
    public LinearLayout f5120i;

    /* renamed from: j */
    public SimpleDraweeView f5121j;

    /* renamed from: k */
    public PdMPartsRecommendTabRecyclerView f5122k;

    /* renamed from: l */
    public PdMPartsRecommendSkuRecyclerView f5123l;

    /* renamed from: m */
    public PdMainImagePresenter f5124m;

    /* renamed from: n */
    public PdMNoDataView f5125n;
    public ConstraintLayout o;
    public View p;
    public PdAutoChangeTextSize q;
    public PdAutoChangeTextSize r;
    public String s;
    public String t;
    public String u;
    public String v;
    public String w;
    public boolean x;
    public int y;
    public String z;

    /* loaded from: classes15.dex */
    public interface a {
        void a();
    }

    public PdMPartsRecommendView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        new HashMap();
        this.y = 0;
        this.B = false;
        this.C = false;
        this.f5118g = context;
    }

    public void b(int i2, PreferentialRecommendTabItemEntity preferentialRecommendTabItemEntity) {
        String str;
        this.y = i2;
        if (preferentialRecommendTabItemEntity != null) {
            str = preferentialRecommendTabItemEntity.tabId;
            this.f5122k.b(i2);
            PdMPartsRecommendSkuRecyclerView pdMPartsRecommendSkuRecyclerView = this.f5123l;
            pdMPartsRecommendSkuRecyclerView.f5079k = false;
            pdMPartsRecommendSkuRecyclerView.f5076h.clear();
            pdMPartsRecommendSkuRecyclerView.f5077i.clear();
            c(preferentialRecommendTabItemEntity);
        } else {
            str = null;
        }
        g(true, "Productdetail_AccessoryToastTab", null, null, str, false, false);
    }

    public void a() {
        PdMPartsRecommendTabRecyclerView pdMPartsRecommendTabRecyclerView = this.f5122k;
        if (pdMPartsRecommendTabRecyclerView != null) {
            pdMPartsRecommendTabRecyclerView.f5111k = false;
            pdMPartsRecommendTabRecyclerView.f5109i.clear();
            pdMPartsRecommendTabRecyclerView.f5110j.clear();
        }
        PdMPartsRecommendSkuRecyclerView pdMPartsRecommendSkuRecyclerView = this.f5123l;
        if (pdMPartsRecommendSkuRecyclerView != null) {
            pdMPartsRecommendSkuRecyclerView.f5079k = false;
            pdMPartsRecommendSkuRecyclerView.f5076h.clear();
            pdMPartsRecommendSkuRecyclerView.f5077i.clear();
        }
        this.B = false;
    }

    public final void c(PreferentialRecommendTabItemEntity preferentialRecommendTabItemEntity) {
        MutableLiveData<WareBusinessUnitMainImageEntity> mutableLiveData;
        if (preferentialRecommendTabItemEntity == null) {
            e(null);
            f(null, null);
            return;
        }
        String str = preferentialRecommendTabItemEntity.tabId;
        this.z = str;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity = this.D.extMap;
        if (extMapEntity != null) {
            PdMainImagePresenter pdMainImagePresenter = this.f5124m;
            g0 g0Var = pdMainImagePresenter.mRecommendContainer;
            BaseActivity baseActivity = (BaseActivity) this.f5118g;
            String str2 = extMapEntity.skuId;
            String str3 = this.v;
            boolean z = this.x;
            g0Var.getClass();
            String str4 = (pdMainImagePresenter == null || (mutableLiveData = pdMainImagePresenter.mainImageData) == null || mutableLiveData.getValue() == null || pdMainImagePresenter.mainImageData.getValue().extMap == null || TextUtils.isEmpty(pdMainImagePresenter.mainImageData.getValue().extMap.recommendPid)) ? "12201" : pdMainImagePresenter.mainImageData.getValue().extMap.recommendPid;
            if (z) {
                PdRankRecommendProtecal pdRankRecommendProtecal = new PdRankRecommendProtecal();
                PdRankRecommendProtecal.RecommendProductListRequestParams recommendProductListRequestParams = new PdRankRecommendProtecal.RecommendProductListRequestParams();
                recommendProductListRequestParams.skuId = pdMainImagePresenter.getMainImageParams().skuId;
                recommendProductListRequestParams.storeId = pdMainImagePresenter.getShopId();
                recommendProductListRequestParams.cid1 = pdMainImagePresenter.getCategoryId(0);
                recommendProductListRequestParams.cid2 = pdMainImagePresenter.getCategoryId(1);
                recommendProductListRequestParams.cid3 = pdMainImagePresenter.getCategoryId(2);
                recommendProductListRequestParams.queryType = 1;
                recommendProductListRequestParams.rankIdAndType = str;
                recommendProductListRequestParams.fromName = "MainPhotoRecomendRank";
                pdRankRecommendProtecal.mRequestParams = recommendProductListRequestParams;
                pdRankRecommendProtecal.request(baseActivity);
                pdRankRecommendProtecal.mResult.observe(baseActivity, new s(g0Var, "3", str, true, pdRankRecommendProtecal));
            } else if (TextUtils.equals(str3, str4)) {
                g0Var.a(baseActivity, str2, str, "3", pdMainImagePresenter);
            }
        }
    }

    public void d(WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity, String str, String str2, String str3, boolean z) {
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
        AbBuriedExpLabelsEntity abBuriedExpLabelsEntity;
        WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity2;
        this.D = wareBusinessUnitMainImageEntity;
        this.v = str;
        this.s = str3;
        this.x = z;
        if (wareBusinessUnitMainImageEntity != null && (extMapEntity2 = wareBusinessUnitMainImageEntity.extMap) != null && extMapEntity2.appStaticInfo != null) {
            if (TextUtils.isEmpty(str3)) {
                this.s = wareBusinessUnitMainImageEntity.extMap.appStaticInfo.recommendPopTiltle;
            }
            AppStaticInfo appStaticInfo = wareBusinessUnitMainImageEntity.extMap.appStaticInfo;
            this.t = appStaticInfo.selfSupportIcon;
            this.u = appStaticInfo.url_before;
        }
        if (wareBusinessUnitMainImageEntity != null && (extMapEntity = wareBusinessUnitMainImageEntity.extMap) != null && (abBuriedExpLabelsEntity = extMapEntity.abBuriedExpLabels) != null) {
            this.w = abBuriedExpLabelsEntity.recommendAbtest;
        }
        if (TextUtils.isEmpty(this.s)) {
            this.s = z ? getContext().getString(R.string.lib_pd_image_topimage_rank_recommend_title) : "\u70ed\u95e8\u914d\u4ef6";
        }
        this.f5119h.setText(this.s);
        g(false, "Productdetail_AccessoryExpo", this.w, null, str2, true, false);
        PreferentialRecommendTabItemEntity preferentialRecommendTabItemEntity = new PreferentialRecommendTabItemEntity();
        preferentialRecommendTabItemEntity.tabId = str2;
        c(preferentialRecommendTabItemEntity);
    }

    public final void e(List<PreferentialRecommendTabItemEntity> list) {
        if (this.f5122k == null || this.C || this.B) {
            return;
        }
        if (list != null && list.size() > 1) {
            this.f5122k.f(list);
            this.f5122k.setVisibility(0);
            this.f5122k.scrollToPosition(this.y);
            this.f5122k.b(this.y);
            this.B = true;
            return;
        }
        this.f5122k.setVisibility(8);
    }

    public final void f(List<PreferentialRecommendItemEntity> list, PdPreferentialRecommendProductExtInfo pdPreferentialRecommendProductExtInfo) {
        if (this.f5123l == null || this.C) {
            return;
        }
        if (list != null && list.size() > 0) {
            PdMPartsRecommendSkuRecyclerView pdMPartsRecommendSkuRecyclerView = this.f5123l;
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = this.D;
            String str = this.u;
            String str2 = this.t;
            String str3 = this.w;
            pdMPartsRecommendSkuRecyclerView.o = wareBusinessUnitMainImageEntity;
            pdMPartsRecommendSkuRecyclerView.f5075g = list;
            pdMPartsRecommendSkuRecyclerView.f5080l = str3;
            PdMPartsRecommendSkuRecyclerView.PartsRecommendSkuAdapter partsRecommendSkuAdapter = pdMPartsRecommendSkuRecyclerView.f5078j;
            if (partsRecommendSkuAdapter != null) {
                partsRecommendSkuAdapter.f5083g = pdMPartsRecommendSkuRecyclerView.f5082n;
                partsRecommendSkuAdapter.f5084h = str;
                partsRecommendSkuAdapter.f5086j = str2;
                List<PreferentialRecommendItemEntity> list2 = partsRecommendSkuAdapter.f5085i;
                if (list2 != null) {
                    list2.clear();
                }
                partsRecommendSkuAdapter.f5085i = list;
                partsRecommendSkuAdapter.f5089m = pdPreferentialRecommendProductExtInfo;
                partsRecommendSkuAdapter.notifyDataSetChanged();
            }
            this.f5123l.scrollToPosition(0);
            this.f5123l.setVisibility(0);
            this.f5125n.setVisibility(8);
            return;
        }
        this.f5123l.setVisibility(8);
        this.f5125n.setVisibility(0);
    }

    public final void g(boolean z, String str, String str2, String str3, String str4, boolean z2, boolean z3) {
        PdMainImagePresenter pdMainImagePresenter;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareImageRecommendRankEntity wareImageRecommendRankEntity;
        List<String> list;
        JDJSONObject jDJSONObject = new JDJSONObject();
        if (!TextUtils.isEmpty(str3)) {
            jDJSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) str3);
        }
        boolean equals = TextUtils.equals("Productdetail_AccessoryExpo", str);
        if (!TextUtils.isEmpty(str4)) {
            if (!equals) {
                jDJSONObject.put("categoryid", (Object) str4);
                if (!this.x) {
                    jDJSONObject.put("tabid", (Object) str4);
                }
            }
            if (z3) {
                jDJSONObject.put("categoryid", (Object) null);
                jDJSONObject.put("tabid", (Object) str4);
            }
            if (this.x) {
                String[] split = str4.split(CartConstant.KEY_YB_INFO_LINK);
                if (split.length > 0) {
                    jDJSONObject.put("rankid", (Object) split[0]);
                }
            }
        }
        if (this.x && (pdMainImagePresenter = this.f5124m) != null) {
            WareBusinessMagicHeadPicInfoEntity topImageBanDanData = pdMainImagePresenter.getTopImageBanDanData();
            if (topImageBanDanData != null && (wareBuinessUnitMainImageBizDataEntity = topImageBanDanData.bizData) != null && (wareImageRecommendRankEntity = wareBuinessUnitMainImageBizDataEntity.bangDanInfo) != null && (list = wareImageRecommendRankEntity.bangDanExperiment) != null) {
                jDJSONObject.put("touchstone_expids", (Object) list);
            }
            if (z2) {
                jDJSONObject.put("abtestid", (Object) "");
            }
        }
        if (z) {
            if (!TextUtils.isEmpty(str2)) {
                HashMap<String, String> a2 = j0.a(str2);
                ABMtaUtils.sendClickDataWithExt(this.f5118g, str + this.f5124m.getMainImageParams().eventParams, null, "onClick", PDHelper.getPageId("com.jd.lib.productdetail.ProductDetailActivity"), "com.jd.lib.productdetail.ProductDetailActivity", this.D.extMap.skuId, null, jDJSONObject.toJSONString(), "", "", "", this.f5124m.getMainImageParams().mSkuTag, null, a2);
                return;
            }
            this.f5124m.mtaClick(str, jDJSONObject.toJSONString());
        } else if (!TextUtils.isEmpty(str2)) {
            ABMtaUtils.sendExposureDataWithExt(this.f5118g, str, null, PDHelper.getPageId("com.jd.lib.productdetail.ProductDetailActivity"), "com.jd.lib.productdetail.ProductDetailActivity", this.D.extMap.skuId, jDJSONObject.toJSONString(), "", "", this.f5124m.getMainImageParams().mSkuTag, null, j0.a(str2));
        } else {
            this.f5124m.mtaExposure(str, jDJSONObject.toJSONString());
        }
    }

    public final void h() {
        this.f5122k.g(new PdMPartsRecommendTabRecyclerView.c() { // from class: com.jd.lib.productdetail.mainimage.old.v
            {
                PdMPartsRecommendView.this = this;
            }

            @Override // com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendTabRecyclerView.c
            public final void a(int i2, PreferentialRecommendTabItemEntity preferentialRecommendTabItemEntity) {
                PdMPartsRecommendView.this.b(i2, preferentialRecommendTabItemEntity);
            }
        });
        this.f5122k.setVisibility(8);
    }

    public final boolean i() {
        PdMainImagePresenter pdMainImagePresenter = this.f5124m;
        return pdMainImagePresenter != null && pdMainImagePresenter.getMainImageParams().isDark;
    }

    public final void j() {
        Context context;
        int i2;
        Context context2;
        int i3;
        if (this.C) {
            return;
        }
        setBackgroundResource(i() ? R.drawable.lib_pd_mainimage_parts_recommend_dark_bg : R.drawable.lib_pd_mainimage_parts_recommend_bg);
        AppCompatTextView appCompatTextView = this.f5119h;
        Context context3 = this.f5118g;
        int i4 = R.color.lib_pd_image_color_1A1A1A;
        appCompatTextView.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(context3, i4, i()));
        this.q.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(this.f5118g, i4, i()));
        this.q.setBackgroundResource(i() ? R.drawable.lib_pd_mainimage_parts_recommend_goon_btn_dark_bg : R.drawable.lib_pd_mainimage_parts_recommend_goon_btn_bg);
        ConstraintLayout constraintLayout = this.o;
        if (i()) {
            context = this.f5118g;
            i2 = R.color.lib_pd_image_color_302E2E;
        } else {
            context = this.f5118g;
            i2 = R.color.lib_pd_image_color_FFFFFF;
        }
        constraintLayout.setBackgroundColor(ContextCompat.getColor(context, i2));
        View view = this.p;
        if (i()) {
            context2 = this.f5118g;
            i3 = R.color.lib_pd_image_color_222222;
        } else {
            context2 = this.f5118g;
            i3 = R.color.lib_pd_image_color_E5E5E5;
        }
        view.setBackgroundColor(ContextCompat.getColor(context2, i3));
        PdMNoDataView pdMNoDataView = this.f5125n;
        int i5 = com.jingdong.common.R.drawable.y_12;
        int i6 = com.jingdong.common.R.drawable.y_12_dark;
        pdMNoDataView.c(i());
        pdMNoDataView.d(null);
        pdMNoDataView.a(null);
        pdMNoDataView.f5063k = i5;
        pdMNoDataView.f5064l = i6;
        SimpleDraweeView simpleDraweeView = pdMNoDataView.f5060h;
        if (simpleDraweeView != null) {
            if (pdMNoDataView.o) {
                i5 = i6;
            }
            simpleDraweeView.setImageResource(i5);
        }
    }

    public void l(PdMainImagePresenter pdMainImagePresenter) {
        g0 g0Var;
        MutableLiveData<PdBaseProtocolLiveData.Result<PdPreferentialRecommendProductListInfo>> mutableLiveData;
        this.f5124m = pdMainImagePresenter;
        PdMPartsRecommendTabRecyclerView pdMPartsRecommendTabRecyclerView = this.f5122k;
        if (pdMPartsRecommendTabRecyclerView != null) {
            pdMPartsRecommendTabRecyclerView.h(pdMainImagePresenter);
        }
        PdMPartsRecommendSkuRecyclerView pdMPartsRecommendSkuRecyclerView = this.f5123l;
        if (pdMPartsRecommendSkuRecyclerView != null) {
            pdMPartsRecommendSkuRecyclerView.f(pdMainImagePresenter);
        }
        PdMainImagePresenter pdMainImagePresenter2 = this.f5124m;
        if (pdMainImagePresenter2 == null || (g0Var = pdMainImagePresenter2.mRecommendContainer) == null || (mutableLiveData = g0Var.a) == null) {
            return;
        }
        mutableLiveData.observe((BaseActivity) this.f5118g, new o(this));
    }

    public void m(a aVar) {
        this.A = aVar;
    }

    public void n(boolean z) {
        if (z) {
            this.f5119h.setVisibility(0);
            this.f5121j.setVisibility(0);
            if (this.f5120i.getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
                ((ViewGroup.MarginLayoutParams) ((ConstraintLayout.LayoutParams) this.f5120i.getLayoutParams())).topMargin = PDUtils.dip2px(this.f5118g, 20.0f);
            }
            this.o.setVisibility(0);
            return;
        }
        this.f5119h.setVisibility(8);
        this.f5121j.setVisibility(8);
        this.o.setVisibility(8);
        if (this.f5120i.getLayoutParams() instanceof ConstraintLayout.LayoutParams) {
            ((ViewGroup.MarginLayoutParams) ((ConstraintLayout.LayoutParams) this.f5120i.getLayoutParams())).topMargin = PDUtils.dip2px(this.f5118g, 0.0f);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareImageRecommendRankEntity wareImageRecommendRankEntity;
        List<String> list;
        int id = view.getId();
        int i2 = R.id.lib_pd_parts_recommend_dialog_close_btn;
        if (id != i2 && id != R.id.lib_pd_parts_recommend_dialog_goon_btn) {
            if (id == R.id.lib_pd_parts_recommend_dialog_cart_btn) {
                if (this.x) {
                    JDJSONObject jDJSONObject = new JDJSONObject();
                    if (TextUtils.isEmpty(this.z)) {
                        String[] split = this.z.split(CartConstant.KEY_YB_INFO_LINK);
                        if (split.length > 0) {
                            jDJSONObject.put("rankid", (Object) split[0]);
                        }
                    }
                    PdMainImagePresenter pdMainImagePresenter = this.f5124m;
                    if (pdMainImagePresenter != null) {
                        WareBusinessMagicHeadPicInfoEntity topImageBanDanData = pdMainImagePresenter.getTopImageBanDanData();
                        if (topImageBanDanData != null && (wareBuinessUnitMainImageBizDataEntity = topImageBanDanData.bizData) != null && (wareImageRecommendRankEntity = wareBuinessUnitMainImageBizDataEntity.bangDanInfo) != null && (list = wareImageRecommendRankEntity.bangDanExperiment) != null) {
                            jDJSONObject.put("touchstone_expids", (Object) list);
                        }
                        this.f5124m.mtaClick("Productdetail_AccessoryShoppingCart", jDJSONObject.toJSONString());
                    }
                } else {
                    this.f5124m.mtaClick("Productdetail_AccessoryShoppingCart");
                }
                DeepLinkCommonHelper.startShoppingCartActivity(this.f5118g, null, true);
                return;
            }
            return;
        }
        a aVar = this.A;
        if (aVar != null) {
            aVar.a();
        }
        if (id == i2) {
            g(true, "Productdetail_AccessoryClose", this.w, null, null, true, false);
        } else if (id == R.id.lib_pd_parts_recommend_dialog_goon_btn) {
            this.f5124m.mtaClick("Productdetail_AccessoryGoHanging");
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f5119h = (AppCompatTextView) findViewById(R.id.lib_pd_parts_recommend_dialog_title);
        this.f5121j = (SimpleDraweeView) findViewById(R.id.lib_pd_parts_recommend_dialog_close_btn);
        this.f5122k = (PdMPartsRecommendTabRecyclerView) findViewById(R.id.lib_pd_parts_recommend_dialog_tab_list);
        this.f5123l = (PdMPartsRecommendSkuRecyclerView) findViewById(R.id.lib_pd_parts_recommend_dialog_sku_list);
        this.f5125n = (PdMNoDataView) findViewById(R.id.lib_pd_parts_recommend_dialog_no_data);
        this.o = (ConstraintLayout) findViewById(R.id.lib_pd_parts_recommend_dialog_bottom_layout);
        this.p = findViewById(R.id.lib_pd_parts_recommend_dialog_bottom_line);
        this.q = (PdAutoChangeTextSize) findViewById(R.id.lib_pd_parts_recommend_dialog_goon_btn);
        this.r = (PdAutoChangeTextSize) findViewById(R.id.lib_pd_parts_recommend_dialog_cart_btn);
        this.f5120i = (LinearLayout) findViewById(R.id.lib_pd_parts_recommend_dialog_list_layout);
        this.f5121j.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.r.setOnClickListener(this);
        h();
        this.f5123l.e(new e0(this));
        this.f5123l.setVisibility(8);
        this.f5125n.b(new f0(this));
    }
}
