package com.jd.lib.productdetail.mainimage.holder.recommend;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.entitys.topimagerecommend.PdRankEntity;
import com.jd.lib.productdetail.core.entitys.topimagerecommend.PdSkuInfoListEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageRecommendRankEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.PdMImagePartsDpgIntegrateView;
import com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendView;
import com.jd.lib.productdetail.mainimage.old.h0;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.utils.JDImageUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMImageRecommendNewView extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    public String f4907g;

    /* renamed from: h  reason: collision with root package name */
    public Context f4908h;

    /* renamed from: i  reason: collision with root package name */
    public int f4909i;

    /* renamed from: j  reason: collision with root package name */
    public TextView f4910j;

    /* renamed from: k  reason: collision with root package name */
    public PdMImageRecommendProductRecycleView f4911k;

    /* renamed from: l  reason: collision with root package name */
    public LinearLayout f4912l;

    /* renamed from: m  reason: collision with root package name */
    public SimpleDraweeView f4913m;

    /* renamed from: n  reason: collision with root package name */
    public TextView f4914n;
    public String o;
    public PdMainImagePresenter p;
    public WareImageRecommendRankEntity q;
    public PdRankEntity r;
    public PdMPartsRecommendView s;
    public PdMImagePartsDpgIntegrateView t;
    public com.jd.lib.productdetail.mainimage.dialog.a u;
    public String v;
    public String w;
    public String x;
    public String y;
    public String z;

    public PdMImageRecommendNewView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4908h = context;
    }

    public static void a(PdMImageRecommendNewView pdMImageRecommendNewView, PdRankEntity pdRankEntity) {
        pdMImageRecommendNewView.r = pdRankEntity;
        if (pdRankEntity != null) {
            ArrayList arrayList = new ArrayList();
            List<PdSkuInfoListEntity> list = pdRankEntity.skuInfoList;
            if (list != null && list.size() > 0) {
                if (pdRankEntity.skuInfoList.size() > 0 && pdRankEntity.skuInfoList.size() < 7) {
                    arrayList.addAll(pdRankEntity.skuInfoList);
                } else {
                    arrayList.addAll(pdRankEntity.skuInfoList.subList(0, 6));
                }
                pdMImageRecommendNewView.c(false);
                pdMImageRecommendNewView.f4910j.setText(pdRankEntity.channelEntryTitle);
                pdMImageRecommendNewView.f4911k.a(arrayList, pdMImageRecommendNewView.f4909i, pdMImageRecommendNewView.f4907g, pdMImageRecommendNewView.p, pdMImageRecommendNewView.q);
                return;
            }
            pdMImageRecommendNewView.c(true);
            return;
        }
        pdMImageRecommendNewView.f4910j.setText("");
        pdMImageRecommendNewView.f4911k.a(null, pdMImageRecommendNewView.f4909i, pdMImageRecommendNewView.f4907g, pdMImageRecommendNewView.p, pdMImageRecommendNewView.q);
        pdMImageRecommendNewView.c(true);
    }

    public void b(String str, String str2, String str3, String str4, String str5, WareImageRecommendRankEntity wareImageRecommendRankEntity) {
        h0 h0Var;
        if (wareImageRecommendRankEntity != null) {
            this.o = wareImageRecommendRankEntity.default_image;
        }
        this.v = str;
        this.w = str2;
        this.x = str3;
        this.y = str4;
        this.z = str5;
        this.q = wareImageRecommendRankEntity;
        this.f4907g = str5;
        PdMainImagePresenter pdMainImagePresenter = this.p;
        if (pdMainImagePresenter == null || (h0Var = pdMainImagePresenter.mTopImageRecommendContainer) == null) {
            return;
        }
        h0Var.b((BaseActivity) this.f4908h, str, str2, str3, str4, str5);
    }

    public final void c(boolean z) {
        if (z) {
            JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
            createSimple.displayer(new JDRoundedBitmapDisplayer(PDUtils.dip2px(6.0f)));
            createSimple.resetViewBeforeLoading(false);
            createSimple.setPlaceholder(19);
            JDImageUtils.displayImage(this.o, this.f4913m, createSimple);
            this.f4913m.setVisibility(0);
            this.f4910j.setVisibility(8);
            this.f4911k.setVisibility(8);
            this.f4914n.setVisibility(0);
            PdMainImagePresenter pdMainImagePresenter = this.p;
            if (pdMainImagePresenter != null) {
                pdMainImagePresenter.mtaExposure("Productdetail_MainRankDDExpo");
                return;
            }
            return;
        }
        this.f4913m.setVisibility(8);
        this.f4910j.setVisibility(0);
        this.f4911k.setVisibility(0);
        this.f4914n.setVisibility(8);
    }

    public void d(PdMainImagePresenter pdMainImagePresenter) {
        h0 h0Var;
        this.p = pdMainImagePresenter;
        if (pdMainImagePresenter == null || (h0Var = pdMainImagePresenter.mTopImageRecommendContainer) == null) {
            return;
        }
        h0Var.a((BaseActivity) this.f4908h);
        this.p.mTopImageRecommendContainer.a.observe((BaseActivity) this.f4908h, new e(this));
    }

    public void e(int i2) {
        this.f4909i = i2;
        if (i2 == 1) {
            this.f4912l.setVisibility(4);
            this.f4912l.setClickable(false);
            this.f4911k.setClickable(false);
            return;
        }
        this.f4912l.setVisibility(0);
        this.f4912l.setClickable(true);
        this.f4911k.setClickable(true);
    }

    public void f(com.jd.lib.productdetail.mainimage.dialog.a aVar) {
        this.u = aVar;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f4910j = (TextView) findViewById(R.id.lib_pd_holder_topimage_item_recommend_title);
        this.f4911k = (PdMImageRecommendProductRecycleView) findViewById(R.id.lib_pd_holder_topimage_item_recommend_recycle);
        this.f4912l = (LinearLayout) findViewById(R.id.lib_pd_holder_topimage_item_recommend_content_btn_ok);
        this.f4913m = (SimpleDraweeView) findViewById(R.id.pd_top_default_image);
        this.f4914n = (TextView) findViewById(R.id.lib_pd_holder_topimage_recommend_reload);
        this.f4912l.setOnClickListener(new a(this));
        this.f4914n.setOnClickListener(new b(this));
    }
}
