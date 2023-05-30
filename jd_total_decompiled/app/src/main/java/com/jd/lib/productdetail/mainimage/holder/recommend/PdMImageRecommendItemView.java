package com.jd.lib.productdetail.mainimage.holder.recommend;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.AbBuriedExpLabelsEntity;
import com.jd.lib.productdetail.core.entitys.PDTopReocommendEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.AppStaticInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.PreferentialRecommendItemEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMainPictureDpgPops;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageRecommendEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.PdMImagePartsDpgIntegrateView;
import com.jd.lib.productdetail.mainimage.old.PdMPartsDpgViewAdapter;
import com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendView;
import com.jd.lib.productdetail.mainimage.old.j0;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.unification.dialog.UnBottomDialog;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes15.dex */
public class PdMImageRecommendItemView extends RelativeLayout {
    public static final /* synthetic */ int s = 0;

    /* renamed from: g */
    public Context f4895g;

    /* renamed from: h */
    public PdMainImagePresenter f4896h;

    /* renamed from: i */
    public WareBusinessMagicHeadPicInfoEntity f4897i;

    /* renamed from: j */
    public int f4898j;

    /* renamed from: k */
    public TextView f4899k;

    /* renamed from: l */
    public LinearLayout f4900l;

    /* renamed from: m */
    public com.jd.lib.productdetail.mainimage.dialog.a f4901m;

    /* renamed from: n */
    public String f4902n;
    public List<PreferentialRecommendItemEntity> o;
    public PdMImagePartsDpgIntegrateView p;
    public WareBusinessUnitMainImageEntity q;
    public PdMPartsRecommendView r;

    /* loaded from: classes15.dex */
    public class a implements View.OnClickListener {

        /* renamed from: com.jd.lib.productdetail.mainimage.holder.recommend.PdMImageRecommendItemView$a$a */
        /* loaded from: classes15.dex */
        public class C0158a implements PdMImagePartsDpgIntegrateView.a {
            public C0158a() {
                a.this = r1;
            }

            @Override // com.jd.lib.productdetail.mainimage.old.PdMImagePartsDpgIntegrateView.a
            public void a() {
                UnBottomDialog unBottomDialog;
                PdMainImagePresenter pdMainImagePresenter = PdMImageRecommendItemView.this.f4896h;
                if (pdMainImagePresenter == null || (unBottomDialog = pdMainImagePresenter.mLayerDialog) == null) {
                    return;
                }
                unBottomDialog.dismiss();
            }
        }

        /* loaded from: classes15.dex */
        public class b implements PdMPartsRecommendView.a {
            public b() {
                a.this = r1;
            }

            @Override // com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendView.a
            public void a() {
                UnBottomDialog unBottomDialog;
                PdMainImagePresenter pdMainImagePresenter = PdMImageRecommendItemView.this.f4896h;
                if (pdMainImagePresenter == null || (unBottomDialog = pdMainImagePresenter.mLayerDialog) == null) {
                    return;
                }
                unBottomDialog.dismiss();
            }
        }

        public a() {
            PdMImageRecommendItemView.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
            HashMap<String, String> hashMap;
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity;
            WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
            ArrayList<WareBusinessMainPictureDpgPops> arrayList;
            PdMPartsRecommendView pdMPartsRecommendView;
            WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity2;
            WareBuinessUnitMainImageBizDataEntity.PdTjBizData pdTjBizData;
            WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity2;
            AppStaticInfo appStaticInfo;
            WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity3;
            PDTopReocommendEntity pDTopReocommendEntity = new PDTopReocommendEntity();
            PdMImageRecommendItemView pdMImageRecommendItemView = PdMImageRecommendItemView.this;
            pDTopReocommendEntity.tabId = pdMImageRecommendItemView.f4902n;
            pDTopReocommendEntity.pid = "12201";
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = pdMImageRecommendItemView.q;
            if (wareBusinessUnitMainImageEntity != null && (extMapEntity3 = wareBusinessUnitMainImageEntity.extMap) != null && !TextUtils.isEmpty(extMapEntity3.recommendPid)) {
                pDTopReocommendEntity.pid = PdMImageRecommendItemView.this.q.extMap.recommendPid;
            }
            pDTopReocommendEntity.formType = WareBusinessMagicHeadPicInfoEntity.FB_TOP_IMAGE_RECOMMEND;
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity2 = PdMImageRecommendItemView.this.q;
            if (wareBusinessUnitMainImageEntity2 != null && (extMapEntity2 = wareBusinessUnitMainImageEntity2.extMap) != null && (appStaticInfo = extMapEntity2.appStaticInfo) != null && !TextUtils.isEmpty(appStaticInfo.recommendPopTiltle)) {
                pDTopReocommendEntity.layerTitle = PdMImageRecommendItemView.this.q.extMap.appStaticInfo.recommendPopTiltle;
            } else {
                pDTopReocommendEntity.layerTitle = PdMImageRecommendItemView.this.f4895g.getString(R.string.lib_pd_image_recommend_match_product);
            }
            PdMImageRecommendItemView pdMImageRecommendItemView2 = PdMImageRecommendItemView.this;
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity3 = pdMImageRecommendItemView2.q;
            if (wareBusinessUnitMainImageEntity3 != null && (wareBusinessMagicHeadPicInfoEntity = pdMImageRecommendItemView2.f4897i) != null && (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) != null && wareBusinessUnitMainImageEntity3.extMap != null) {
                WareBuinessUnitMainImageBizDataEntity.PdTjBizData pdTjBizData2 = wareBuinessUnitMainImageBizDataEntity.tjBizData;
                ArrayList<WareBusinessMainPictureDpgPops> arrayList2 = null;
                if (pdTjBizData2 != null && (arrayList = pdTjBizData2.mainPictureDpgPops) != null && arrayList.size() > 0) {
                    PdMImageRecommendItemView pdMImageRecommendItemView3 = PdMImageRecommendItemView.this;
                    if (pdMImageRecommendItemView3.p == null) {
                        PdMImagePartsDpgIntegrateView pdMImagePartsDpgIntegrateView = (PdMImagePartsDpgIntegrateView) LayoutInflater.from(pdMImageRecommendItemView3.f4895g).inflate(R.layout.lib_pd_mainimage_parts_dpg_dialog_layout, (ViewGroup) null);
                        pdMImageRecommendItemView3.p = pdMImagePartsDpgIntegrateView;
                        PdMainImagePresenter pdMainImagePresenter = pdMImageRecommendItemView3.f4896h;
                        if (pdMainImagePresenter != null) {
                            pdMImagePartsDpgIntegrateView.c(pdMainImagePresenter);
                        }
                    }
                    PdMImageRecommendItemView pdMImageRecommendItemView4 = PdMImageRecommendItemView.this;
                    WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity2 = pdMImageRecommendItemView4.f4897i;
                    if (wareBusinessMagicHeadPicInfoEntity2 != null && (wareBuinessUnitMainImageBizDataEntity2 = wareBusinessMagicHeadPicInfoEntity2.bizData) != null && (pdTjBizData = wareBuinessUnitMainImageBizDataEntity2.tjBizData) != null) {
                        arrayList2 = pdTjBizData.mainPictureDpgPops;
                    }
                    PdMPartsDpgViewAdapter pdMPartsDpgViewAdapter = pdMImageRecommendItemView4.p.f5048n;
                    if (pdMPartsDpgViewAdapter != null && (pdMPartsRecommendView = pdMPartsDpgViewAdapter.f5071c) != null) {
                        pdMPartsRecommendView.a();
                    }
                    PdMImageRecommendItemView pdMImageRecommendItemView5 = PdMImageRecommendItemView.this;
                    pdMImageRecommendItemView5.p.a(pdMImageRecommendItemView5.q, pDTopReocommendEntity, arrayList2);
                    PdMImageRecommendItemView.this.p.d(new C0158a());
                    PdMImageRecommendItemView pdMImageRecommendItemView6 = PdMImageRecommendItemView.this;
                    com.jd.lib.productdetail.mainimage.dialog.a aVar = pdMImageRecommendItemView6.f4901m;
                    if (aVar != null) {
                        aVar.a(pdMImageRecommendItemView6.p);
                    }
                } else {
                    PdMImageRecommendItemView pdMImageRecommendItemView7 = PdMImageRecommendItemView.this;
                    if (pdMImageRecommendItemView7.r == null) {
                        PdMPartsRecommendView pdMPartsRecommendView2 = (PdMPartsRecommendView) LayoutInflater.from(pdMImageRecommendItemView7.f4895g).inflate(R.layout.lib_pd_mainimage_parts_recommend_dialog_layout, (ViewGroup) null);
                        pdMImageRecommendItemView7.r = pdMPartsRecommendView2;
                        PdMainImagePresenter pdMainImagePresenter2 = pdMImageRecommendItemView7.f4896h;
                        if (pdMainImagePresenter2 != null) {
                            pdMPartsRecommendView2.l(pdMainImagePresenter2);
                        }
                    }
                    PdMImageRecommendItemView.this.r.a();
                    PdMImageRecommendItemView pdMImageRecommendItemView8 = PdMImageRecommendItemView.this;
                    pdMImageRecommendItemView8.r.d(pdMImageRecommendItemView8.q, pDTopReocommendEntity.pid, pDTopReocommendEntity.tabId, pDTopReocommendEntity.layerTitle, false);
                    PdMImageRecommendItemView.this.r.n(true);
                    PdMImageRecommendItemView.this.r.m(new b());
                    PdMImageRecommendItemView pdMImageRecommendItemView9 = PdMImageRecommendItemView.this;
                    com.jd.lib.productdetail.mainimage.dialog.a aVar2 = pdMImageRecommendItemView9.f4901m;
                    if (aVar2 != null) {
                        aVar2.a(pdMImageRecommendItemView9.r);
                    }
                }
            }
            PdMImageRecommendItemView pdMImageRecommendItemView10 = PdMImageRecommendItemView.this;
            String str = pdMImageRecommendItemView10.f4902n;
            try {
                JDJSONArray jDJSONArray = new JDJSONArray();
                List<PreferentialRecommendItemEntity> list = pdMImageRecommendItemView10.o;
                if (list == null || list.size() <= 0) {
                    return;
                }
                for (int i2 = 0; i2 < pdMImageRecommendItemView10.o.size(); i2++) {
                    PreferentialRecommendItemEntity preferentialRecommendItemEntity = pdMImageRecommendItemView10.o.get(i2);
                    if (preferentialRecommendItemEntity != null) {
                        JDJSONObject jDJSONObject = new JDJSONObject();
                        jDJSONObject.put("categoryid", (Object) str);
                        jDJSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) preferentialRecommendItemEntity.skuId);
                        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity4 = pdMImageRecommendItemView10.q;
                        if (wareBusinessUnitMainImageEntity4 != null && (extMapEntity = wareBusinessUnitMainImageEntity4.extMap) != null) {
                            AbBuriedExpLabelsEntity abBuriedExpLabelsEntity = extMapEntity.abBuriedExpLabels;
                            if (abBuriedExpLabelsEntity != null) {
                                hashMap = j0.a(abBuriedExpLabelsEntity.recommendAbtest);
                            } else {
                                hashMap = new HashMap<>();
                            }
                            for (String str2 : hashMap.keySet()) {
                                jDJSONObject.put(str2, (Object) hashMap.get(str2));
                            }
                        }
                        jDJSONArray.add(jDJSONObject);
                    }
                }
                pdMImageRecommendItemView10.f4896h.mtaClick("Productdetail_MainPhotoCategory", jDJSONArray.toJSONString());
            } catch (Exception unused) {
            }
        }
    }

    /* loaded from: classes15.dex */
    public class b implements View.OnClickListener {

        /* renamed from: g */
        public final /* synthetic */ WareBusinessUnitMainImageEntity f4904g;

        /* renamed from: h */
        public final /* synthetic */ PreferentialRecommendItemEntity f4905h;

        public b(WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity, PreferentialRecommendItemEntity preferentialRecommendItemEntity) {
            PdMImageRecommendItemView.this = r1;
            this.f4904g = wareBusinessUnitMainImageEntity;
            this.f4905h = preferentialRecommendItemEntity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ArrayList arrayList;
            try {
                if (this.f4904g != null) {
                    JDJSONObject jDJSONObject = new JDJSONObject();
                    AbBuriedExpLabelsEntity abBuriedExpLabelsEntity = this.f4904g.extMap.abBuriedExpLabels;
                    if (abBuriedExpLabelsEntity != null) {
                        String str = abBuriedExpLabelsEntity.recommendAbtest;
                        arrayList = new ArrayList();
                        try {
                            if (!TextUtils.isEmpty(str)) {
                                Iterator<Map.Entry<String, Object>> it = JDJSON.parseObject(str).entrySet().iterator();
                                while (it.hasNext()) {
                                    arrayList.add(it.next().getValue().toString());
                                }
                            }
                        } catch (Exception e2) {
                            ExceptionReporter.reportExceptionToBugly(e2);
                        }
                    } else {
                        arrayList = new ArrayList();
                    }
                    jDJSONObject.put("touchstone_expids", (Object) arrayList);
                    jDJSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) this.f4905h.skuId);
                    PdMImageRecommendItemView.this.f4896h.mtaClick("Productdetail_MainPhotoRecomenProduct", jDJSONObject.toJSONString());
                }
                j0.b(PdMImageRecommendItemView.this.getContext(), Long.valueOf(Long.parseLong(this.f4905h.skuId)), null, null);
            } catch (Exception unused) {
            }
        }
    }

    public PdMImageRecommendItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4895g = context;
    }

    public CharSequence a(String str, float f2) {
        if (TextUtils.isEmpty(str)) {
            return StringUtil.no_price;
        }
        if (TextUtils.equals(StringUtil.no_price, str)) {
            return str;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.equals(StringUtil.no_price, str) && !str.startsWith("\u00a5")) {
            str = "\u00a5" + str;
        }
        String str2 = getResources().getString(R.string.lib_pd_image_rightbutton_tohands_yugu) + str;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str2);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(PDUtils.sp2px(getContext(), 12.0f)), 0, 2, 33);
        spannableStringBuilder.setSpan(new RelativeSizeSpan(f2), 2, 3, 33);
        try {
            int lastIndexOf = str2.lastIndexOf(OrderISVUtil.MONEY_DECIMAL);
            if (lastIndexOf != -1) {
                spannableStringBuilder.setSpan(new RelativeSizeSpan(f2), lastIndexOf, str2.length(), 33);
            }
        } catch (Exception unused) {
        }
        return spannableStringBuilder;
    }

    public void b(List<PreferentialRecommendItemEntity> list, String str, String str2, WareImageRecommendEntity wareImageRecommendEntity, WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity) {
        int i2;
        this.q = wareBusinessUnitMainImageEntity;
        if (list != null && list.size() != 0 && !TextUtils.isEmpty(str2)) {
            this.f4902n = str;
            this.o = list;
            this.f4900l.removeAllViews();
            this.f4899k.setText(String.format(this.f4895g.getString(R.string.lib_pd_image_tab_add_recommend), str2));
            int i3 = this.f4896h.appImageWidth;
            int dip2px = i3 - PDUtils.dip2px(68.0f);
            int dip2px2 = (i3 - PDUtils.dip2px(71.0f)) / 2;
            int i4 = (dip2px * 9) / 31;
            int i5 = (dip2px * 4) / 31;
            if (list.size() > 0) {
                int size = list.size() < 3 ? list.size() : 3;
                for (int i6 = 0; i6 < size; i6++) {
                    PreferentialRecommendItemEntity preferentialRecommendItemEntity = list.get(i6);
                    View inflate = LayoutInflater.from(this.f4895g).inflate(R.layout.lib_pd_mainimage_top_recommend_product_item, (ViewGroup) null);
                    inflate.setOnClickListener(new b(wareBusinessUnitMainImageEntity, preferentialRecommendItemEntity));
                    SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.lib_pd_top_recommend_product_image);
                    AppCompatTextView appCompatTextView = (AppCompatTextView) inflate.findViewById(R.id.pd_top_recommend_product_price);
                    if (wareImageRecommendEntity == null || preferentialRecommendItemEntity.goodsField == null) {
                        i2 = 0;
                    } else {
                        i2 = 0;
                        JDImageUtils.displayImage(wareImageRecommendEntity.url_before + preferentialRecommendItemEntity.goodsField.img_dfs_url, (ImageView) simpleDraweeView, (JDDisplayImageOptions) null, false);
                    }
                    appCompatTextView.setVisibility(i2);
                    if (TextUtils.equals(preferentialRecommendItemEntity.realPriceNum, "1") && !TextUtils.isEmpty(preferentialRecommendItemEntity.realPrice)) {
                        if (appCompatTextView.getPaint().measureText(a(preferentialRecommendItemEntity.realPrice, 0.6f).toString()) < i4) {
                            appCompatTextView.setBackgroundResource(R.drawable.lib_pd_mainimage_recommend_product_tohand_price_bg);
                            appCompatTextView.getLayoutParams().width = -1;
                            ((RelativeLayout.LayoutParams) appCompatTextView.getLayoutParams()).leftMargin = 0;
                            appCompatTextView.setText(a(preferentialRecommendItemEntity.realPrice, 0.6f));
                        } else {
                            appCompatTextView.setBackgroundResource(R.drawable.lib_pd_mainimage_recommend_product_price_bg);
                            appCompatTextView.getLayoutParams().width = -2;
                            ((RelativeLayout.LayoutParams) appCompatTextView.getLayoutParams()).leftMargin = PDUtils.dip2px(10.0f);
                            appCompatTextView.setText(PDUtils.getJPriceText(preferentialRecommendItemEntity.realPrice, 0.6f));
                        }
                    } else if (!TextUtils.isEmpty(preferentialRecommendItemEntity.jdPrice)) {
                        appCompatTextView.setBackgroundResource(R.drawable.lib_pd_mainimage_recommend_product_price_bg);
                        appCompatTextView.getLayoutParams().width = -2;
                        ((RelativeLayout.LayoutParams) appCompatTextView.getLayoutParams()).leftMargin = PDUtils.dip2px(10.0f);
                        appCompatTextView.setText(PDUtils.getJPriceText(preferentialRecommendItemEntity.jdPrice, 0.6f));
                    } else {
                        appCompatTextView.setVisibility(8);
                        appCompatTextView.setText("");
                    }
                    FontsUtil.changeTextFont(appCompatTextView, 4099);
                    this.f4900l.addView(inflate);
                    if (inflate.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) inflate.getLayoutParams();
                        layoutParams.height = i4;
                        layoutParams.width = i4;
                        layoutParams.rightMargin = PDUtils.dip2px(8.0f);
                        inflate.setLayoutParams(layoutParams);
                    }
                }
                if (this.f4898j == 2) {
                    View inflate2 = LayoutInflater.from(this.f4895g).inflate(R.layout.lib_pd_mainimage_holder_topimage_item_recommend_more_layout, (ViewGroup) null);
                    this.f4900l.addView(inflate2);
                    ViewGroup.LayoutParams layoutParams2 = inflate2.getLayoutParams();
                    layoutParams2.height = i4;
                    layoutParams2.width = i5;
                    inflate2.setLayoutParams(layoutParams2);
                }
                ViewGroup.LayoutParams layoutParams3 = getLayoutParams();
                layoutParams3.height = dip2px2;
                layoutParams3.width = -1;
                setLayoutParams(layoutParams3);
                return;
            }
            return;
        }
        setVisibility(8);
    }

    public void c(WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity) {
        this.f4897i = wareBusinessMagicHeadPicInfoEntity;
    }

    public void d(PdMainImagePresenter pdMainImagePresenter) {
        this.f4896h = pdMainImagePresenter;
        PdMImagePartsDpgIntegrateView pdMImagePartsDpgIntegrateView = this.p;
        if (pdMImagePartsDpgIntegrateView != null) {
            pdMImagePartsDpgIntegrateView.c(pdMainImagePresenter);
        }
    }

    public void e(int i2) {
        this.f4898j = i2;
    }

    public void f(com.jd.lib.productdetail.mainimage.dialog.a aVar) {
        this.f4901m = aVar;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4899k = (TextView) findViewById(R.id.lib_pd_top_img_recommend_title_tv);
        this.f4900l = (LinearLayout) findViewById(R.id.lib_pd_top_img_recommend_layout);
        setOnClickListener(new a());
    }
}
