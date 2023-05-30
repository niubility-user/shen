package com.jd.lib.productdetail.mainimage.holder.suit;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PDTopReocommendEntity;
import com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdDpgSmallInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdMainSku;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.utils.JDImageUtils;
import java.util.List;

/* loaded from: classes15.dex */
public class PdTopImageSuitViewFloor extends ConstraintLayout {

    /* renamed from: g  reason: collision with root package name */
    public com.jd.lib.productdetail.mainimage.dialog.a f4945g;

    /* renamed from: h  reason: collision with root package name */
    public WareBuinessUnitMainImageBizDataEntity.PdRecommendRankShowMap f4946h;

    /* renamed from: i  reason: collision with root package name */
    public List<PdDpgListLayerInfo.DetailBean> f4947i;

    /* renamed from: j  reason: collision with root package name */
    public TextView f4948j;

    /* renamed from: k  reason: collision with root package name */
    public TextView f4949k;

    /* renamed from: l  reason: collision with root package name */
    public PdTopImageSuitViewFloorSkuItem f4950l;

    /* renamed from: m  reason: collision with root package name */
    public PdTopImageSuitViewFloorSkuItem f4951m;

    /* renamed from: n  reason: collision with root package name */
    public PdTopImageSuitViewFloorSkuItem f4952n;
    public ImageView o;
    public ImageView p;
    public PdMainSku q;
    public PdDpgSmallInfo r;
    public boolean s;
    public String t;
    public PdMainImagePresenter u;

    /* loaded from: classes15.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PdDpgSmallInfo.PdTitleInfo pdTitleInfo;
            PdTopImageSuitViewFloor pdTopImageSuitViewFloor = PdTopImageSuitViewFloor.this;
            if (pdTopImageSuitViewFloor.s) {
                if (pdTopImageSuitViewFloor.q != null && pdTopImageSuitViewFloor.u != null) {
                    JDJSONObject jDJSONObject = new JDJSONObject();
                    jDJSONObject.put("type", (Object) PdTopImageSuitViewFloor.this.t);
                    jDJSONObject.put("matchid", (Object) "-100");
                    PdDpgSmallInfo pdDpgSmallInfo = PdTopImageSuitViewFloor.this.r;
                    if (pdDpgSmallInfo != null && (pdTitleInfo = pdDpgSmallInfo.info) != null && !TextUtils.isEmpty(pdTitleInfo.matchId)) {
                        jDJSONObject.put("matchid", (Object) PdTopImageSuitViewFloor.this.r.info.matchId);
                    }
                    PdTopImageSuitViewFloor.this.u.mtaClick("Productdetail_MainPhotoViewAll", jDJSONObject.toJSONString());
                }
                PDTopReocommendEntity pDTopReocommendEntity = new PDTopReocommendEntity();
                PdTopImageSuitViewFloor pdTopImageSuitViewFloor2 = PdTopImageSuitViewFloor.this;
                WareBuinessUnitMainImageBizDataEntity.PdRecommendRankShowMap pdRecommendRankShowMap = pdTopImageSuitViewFloor2.f4946h;
                if (pdRecommendRankShowMap != null) {
                    pDTopReocommendEntity.rankId = pdRecommendRankShowMap.rankId;
                    pDTopReocommendEntity.typeId = pdRecommendRankShowMap.rankType;
                }
                pDTopReocommendEntity.dpgIntegration = pdTopImageSuitViewFloor2.f4947i;
                pDTopReocommendEntity.formType = pdTopImageSuitViewFloor2.t;
                com.jd.lib.productdetail.mainimage.dialog.a aVar = pdTopImageSuitViewFloor2.f4945g;
                if (aVar != null) {
                    aVar.a(pDTopReocommendEntity);
                }
            }
        }
    }

    public PdTopImageSuitViewFloor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = true;
    }

    public void a(PdMainSku pdMainSku, PdDpgSmallInfo pdDpgSmallInfo, String str, String str2, WareBuinessUnitMainImageBizDataEntity.PdRecommendRankShowMap pdRecommendRankShowMap, List<PdDpgListLayerInfo.DetailBean> list) {
        PdDpgSmallInfo.PdTitleInfo pdTitleInfo;
        this.q = pdMainSku;
        this.r = pdDpgSmallInfo;
        this.t = str2;
        this.f4947i = list;
        this.f4946h = pdRecommendRankShowMap;
        this.f4948j.setText("");
        this.f4950l.setVisibility(4);
        this.f4951m.setVisibility(4);
        this.f4952n.setVisibility(4);
        this.f4951m.setClickable(false);
        this.f4952n.setClickable(false);
        this.o.setVisibility(8);
        this.p.setVisibility(8);
        PdDpgSmallInfo pdDpgSmallInfo2 = this.r;
        if (pdDpgSmallInfo2 == null || pdDpgSmallInfo2.info == null) {
            return;
        }
        this.f4950l.setVisibility(0);
        PdTopImageSuitViewFloorSkuItem pdTopImageSuitViewFloorSkuItem = this.f4950l;
        pdTopImageSuitViewFloorSkuItem.f4958k = this.u;
        pdTopImageSuitViewFloorSkuItem.a(pdMainSku, null, str2, this.r.info.matchId);
        PdDpgSmallInfo pdDpgSmallInfo3 = this.r;
        if (pdDpgSmallInfo3.items == null || (pdTitleInfo = pdDpgSmallInfo3.info) == null) {
            return;
        }
        this.f4948j.setText(pdTitleInfo.matchTitle);
        if (this.r.items.size() > 0 && this.r.items.get(0) != null) {
            this.f4949k.setText(R.string.lib_pd_mainimage_suit_btn_all);
            if (!TextUtils.isEmpty(this.r.info.joint)) {
                this.o.setVisibility(0);
                JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
                createSimple.showImageOnLoading(17170445);
                createSimple.showImageOnFail(17170445);
                JDImageUtils.displayImage(this.r.info.joint, this.o, createSimple);
            }
            this.f4951m.setVisibility(0);
            PdTopImageSuitViewFloorSkuItem pdTopImageSuitViewFloorSkuItem2 = this.f4951m;
            pdTopImageSuitViewFloorSkuItem2.f4958k = this.u;
            pdTopImageSuitViewFloorSkuItem2.a(null, this.r.items.get(0), str2, this.r.info.matchId);
        }
        if (this.r.items.size() <= 1 || this.r.items.get(1) == null) {
            return;
        }
        this.f4949k.setText(R.string.lib_pd_mainimage_suit_seemore);
        if (!TextUtils.isEmpty(this.r.info.joint)) {
            this.p.setVisibility(0);
            JDDisplayImageOptions createSimple2 = JDDisplayImageOptions.createSimple();
            createSimple2.showImageOnLoading(17170445);
            createSimple2.showImageOnFail(17170445);
            JDImageUtils.displayImage(this.r.info.joint, this.p, createSimple2);
        }
        this.f4952n.setVisibility(0);
        PdTopImageSuitViewFloorSkuItem pdTopImageSuitViewFloorSkuItem3 = this.f4952n;
        pdTopImageSuitViewFloorSkuItem3.f4958k = this.u;
        pdTopImageSuitViewFloorSkuItem3.a(null, this.r.items.get(1), str2, this.r.info.matchId);
    }

    public void b(boolean z) {
        this.s = z;
        PdTopImageSuitViewFloorSkuItem pdTopImageSuitViewFloorSkuItem = this.f4950l;
        if (pdTopImageSuitViewFloorSkuItem != null) {
            pdTopImageSuitViewFloorSkuItem.f(z);
        }
        PdTopImageSuitViewFloorSkuItem pdTopImageSuitViewFloorSkuItem2 = this.f4951m;
        if (pdTopImageSuitViewFloorSkuItem2 != null) {
            pdTopImageSuitViewFloorSkuItem2.f(z);
        }
        PdTopImageSuitViewFloorSkuItem pdTopImageSuitViewFloorSkuItem3 = this.f4952n;
        if (pdTopImageSuitViewFloorSkuItem3 != null) {
            pdTopImageSuitViewFloorSkuItem3.f(z);
        }
        TextView textView = this.f4949k;
        if (textView != null) {
            textView.setVisibility(z ? 0 : 8);
        }
    }

    public void c(com.jd.lib.productdetail.mainimage.dialog.a aVar) {
        this.f4945g = aVar;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        DisplayMetrics displayMetrics;
        super.onFinishInflate();
        this.f4948j = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_suit_floor_title);
        this.f4949k = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_suit_floor_btn_all);
        this.f4950l = (PdTopImageSuitViewFloorSkuItem) findViewById(R.id.lib_pd_holder_topimage_item_suit_floor_main_sku);
        this.f4951m = (PdTopImageSuitViewFloorSkuItem) findViewById(R.id.lib_pd_holder_topimage_item_suit_floor_suit_sku_1);
        this.f4952n = (PdTopImageSuitViewFloorSkuItem) findViewById(R.id.lib_pd_holder_topimage_item_suit_floor_suit_sku_2);
        this.o = (ImageView) findViewById(R.id.lib_pd_holder_topimage_item_suit_floor_item_divider_1);
        this.p = (ImageView) findViewById(R.id.lib_pd_holder_topimage_item_suit_floor_item_divider_2);
        this.f4949k.setOnClickListener(new a());
        if (!(getContext() instanceof Activity) || (displayMetrics = PDUtils.getDisplayMetrics(getContext())) == null) {
            return;
        }
        float f2 = displayMetrics.density;
        if (f2 <= 0.0f) {
            f2 = 1.0f;
        }
        float appWidth = (PDUtils.getAppWidth((Activity) getContext()) / f2) / 375.0f;
        this.f4948j.setTextSize(0, getResources().getDimension(R.dimen.lib_pd_mainimage_item_suit_floor_title_textsize) * appWidth);
        this.f4949k.setTextSize(0, getResources().getDimension(R.dimen.lib_pd_mainimage_item_suit_floor_btn_all_textsize) * appWidth);
        ViewGroup.LayoutParams layoutParams = this.f4948j.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ViewGroup.MarginLayoutParams) ((ConstraintLayout.LayoutParams) layoutParams)).topMargin = (int) (PDUtils.dip2px(10.0f) * appWidth);
        }
        ViewGroup.LayoutParams layoutParams2 = this.f4950l.getLayoutParams();
        if (layoutParams2 instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) layoutParams2;
            Resources resources = getResources();
            int i2 = R.dimen.lib_pd_mainimage_item_suit_floor_item_pic_size;
            ((ViewGroup.MarginLayoutParams) layoutParams3).width = (int) (resources.getDimension(i2) * appWidth);
            ((ViewGroup.MarginLayoutParams) layoutParams3).height = (int) (getResources().getDimension(i2) * appWidth);
            ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin = (int) (PDUtils.dip2px(10.0f) * appWidth);
            ((ViewGroup.MarginLayoutParams) layoutParams3).bottomMargin = (int) (PDUtils.dip2px(10.0f) * appWidth);
        }
        ViewGroup.LayoutParams layoutParams4 = this.f4951m.getLayoutParams();
        if (layoutParams4 instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams5 = (ConstraintLayout.LayoutParams) layoutParams4;
            Resources resources2 = getResources();
            int i3 = R.dimen.lib_pd_mainimage_item_suit_floor_item_pic_size;
            ((ViewGroup.MarginLayoutParams) layoutParams5).width = (int) (resources2.getDimension(i3) * appWidth);
            ((ViewGroup.MarginLayoutParams) layoutParams5).height = (int) (getResources().getDimension(i3) * appWidth);
        }
        ViewGroup.LayoutParams layoutParams6 = this.f4952n.getLayoutParams();
        if (layoutParams6 instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams7 = (ConstraintLayout.LayoutParams) layoutParams6;
            Resources resources3 = getResources();
            int i4 = R.dimen.lib_pd_mainimage_item_suit_floor_item_pic_size;
            ((ViewGroup.MarginLayoutParams) layoutParams7).width = (int) (resources3.getDimension(i4) * appWidth);
            ((ViewGroup.MarginLayoutParams) layoutParams7).height = (int) (getResources().getDimension(i4) * appWidth);
        }
        ViewGroup.LayoutParams layoutParams8 = this.o.getLayoutParams();
        if (layoutParams8 instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams9 = (ConstraintLayout.LayoutParams) layoutParams8;
            Resources resources4 = getResources();
            int i5 = R.dimen.lib_pd_mainimage_item_suit_floor_item_divider;
            ((ViewGroup.MarginLayoutParams) layoutParams9).width = (int) (resources4.getDimension(i5) * appWidth);
            ((ViewGroup.MarginLayoutParams) layoutParams9).height = (int) (getResources().getDimension(i5) * appWidth);
        }
        ViewGroup.LayoutParams layoutParams10 = this.p.getLayoutParams();
        if (layoutParams10 instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams11 = (ConstraintLayout.LayoutParams) layoutParams10;
            Resources resources5 = getResources();
            int i6 = R.dimen.lib_pd_mainimage_item_suit_floor_item_divider;
            ((ViewGroup.MarginLayoutParams) layoutParams11).width = (int) (resources5.getDimension(i6) * appWidth);
            ((ViewGroup.MarginLayoutParams) layoutParams11).height = (int) (getResources().getDimension(i6) * appWidth);
        }
    }
}
