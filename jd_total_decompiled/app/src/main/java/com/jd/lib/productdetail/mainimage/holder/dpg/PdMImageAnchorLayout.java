package com.jd.lib.productdetail.mainimage.holder.dpg;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.entitys.wozhe.MatchItemsBean;
import com.jd.lib.productdetail.core.entitys.wozhe.MatchSkusBean;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.views.PdAutoChangeTextSize;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.view.PdImageFromType;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.utils.JDImageUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMImageAnchorLayout extends FrameLayout implements View.OnClickListener {
    public static final /* synthetic */ int v = 0;

    /* renamed from: g  reason: collision with root package name */
    public List<MatchSkusBean> f4796g;

    /* renamed from: h  reason: collision with root package name */
    public FrameLayout f4797h;

    /* renamed from: i  reason: collision with root package name */
    public Context f4798i;

    /* renamed from: j  reason: collision with root package name */
    public ArrayList<Rect> f4799j;

    /* renamed from: k  reason: collision with root package name */
    public SimpleDraweeView f4800k;

    /* renamed from: l  reason: collision with root package name */
    public PdAutoChangeTextSize f4801l;

    /* renamed from: m  reason: collision with root package name */
    public PdAutoChangeTextSize f4802m;

    /* renamed from: n  reason: collision with root package name */
    public MatchItemsBean f4803n;
    public com.jd.lib.productdetail.mainimage.dialog.a o;
    public View p;
    public ViewGroup q;
    public ImageView r;
    public String s;
    public PdMainImagePresenter t;
    public WareBusinessUnitMainImageEntity u;

    public PdMImageAnchorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static Point a(PdMImageAnchorLayout pdMImageAnchorLayout, float f2, float f3, View view) {
        pdMImageAnchorLayout.getClass();
        Point point2 = new Point(0, 0);
        point2.x = (int) (f2 - (view.getMeasuredWidth() - PDUtils.dip2px(5.0f)));
        point2.y = (int) (f3 - (view.getMeasuredHeight() / 2));
        return point2;
    }

    public static void c(PdMImageAnchorLayout pdMImageAnchorLayout, View view, ConstraintLayout constraintLayout, SimpleDraweeView simpleDraweeView) {
        pdMImageAnchorLayout.getClass();
        ConstraintSet constraintSet = new ConstraintSet();
        ConstraintLayout constraintLayout2 = (ConstraintLayout) view;
        constraintSet.clone(constraintLayout2);
        constraintSet.connect(constraintLayout.getId(), 2, simpleDraweeView.getId(), 1);
        constraintSet.connect(simpleDraweeView.getId(), 1, constraintLayout.getId(), 2, PDUtils.dip2px(6.0f));
        constraintSet.applyTo(constraintLayout2);
    }

    public static Point d(PdMImageAnchorLayout pdMImageAnchorLayout, float f2, float f3, View view) {
        pdMImageAnchorLayout.getClass();
        Point point2 = new Point(0, 0);
        point2.x = (int) (f2 - PDUtils.dip2px(5.0f));
        point2.y = (int) (f3 - (view.getMeasuredHeight() / 2));
        return point2;
    }

    public final void b(Context context) {
        this.f4798i = context;
        View inflate = FrameLayout.inflate(context, R.layout.lib_pd_mainimage_wozhe_imgview_point_topimage, this);
        this.f4797h = (FrameLayout) inflate.findViewById(R.id.lib_pd_topimage_layouPoints);
        this.f4800k = (SimpleDraweeView) inflate.findViewById(R.id.lib_pd_wozhe_imageview_point_icon);
        this.f4801l = (PdAutoChangeTextSize) inflate.findViewById(R.id.lib_pd_wozhe_imageview_point_title);
        this.f4802m = (PdAutoChangeTextSize) inflate.findViewById(R.id.lib_pd_wozhe_imageview_point_subtitle);
        this.p = inflate.findViewById(R.id.lib_pd_topimage_gdpbuy);
        this.q = (ViewGroup) inflate.findViewById(R.id.lib_pd_wozhe_left_bottom_bg);
        this.r = (ImageView) inflate.findViewById(R.id.lib_pd_wozhe_imageview_point_title_arrow);
        this.p.setOnClickListener(new a(this));
    }

    public void e(WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity) {
        this.u = wareBusinessUnitMainImageEntity;
    }

    public void f(WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity) {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        MatchItemsBean matchItemsBean;
        if (wareBusinessMagicHeadPicInfoEntity == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) == null || (matchItemsBean = wareBuinessUnitMainImageBizDataEntity.dpgHeadPicInfo) == null) {
            return;
        }
        this.f4803n = matchItemsBean;
        String str = matchItemsBean.text;
        if (TextUtils.isEmpty(str)) {
            str = getContext().getString(R.string.lib_pd_image_top_image_dpg_title);
        }
        this.f4801l.setText(str);
        this.f4802m.setText(this.f4803n.subText);
        this.f4802m.setVisibility(TextUtils.isEmpty(this.f4803n.subText) ? 8 : 0);
        this.f4800k.setVisibility(TextUtils.isEmpty(this.f4803n.iconImg) ? 8 : 0);
        JDImageUtils.displayImage(this.f4803n.iconImg, this.f4800k);
    }

    public void g(PdMainImagePresenter pdMainImagePresenter) {
        this.t = pdMainImagePresenter;
    }

    public void h(com.jd.lib.productdetail.mainimage.dialog.a aVar) {
        this.o = aVar;
    }

    public void i(List<MatchSkusBean> list) {
        this.f4796g = list;
    }

    public void j(String str) {
        this.s = str;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view != null && PDUtils.repeatClick()) {
            Object tag = view.getTag();
            if (tag instanceof MatchSkusBean) {
                MatchSkusBean matchSkusBean = (MatchSkusBean) tag;
                if (TextUtils.isEmpty(matchSkusBean.skuId + "") || this.f4798i == null) {
                    return;
                }
                Bundle build = DeeplinkProductDetailHelper.BundleBuilder.from(String.valueOf(matchSkusBean.skuId)).imageTitlePrice(matchSkusBean.img, matchSkusBean.name, matchSkusBean.price).build();
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("matchsku", (Object) Long.valueOf(matchSkusBean.skuId));
                if (!TextUtils.isEmpty(this.s)) {
                    jDJSONObject.put("source", (Object) this.s);
                }
                MatchItemsBean matchItemsBean = this.f4803n;
                if (matchItemsBean != null) {
                    jDJSONObject.put("matchid", (Object) Integer.valueOf(matchItemsBean.matchId));
                }
                PdMainImagePresenter pdMainImagePresenter = this.t;
                if (pdMainImagePresenter.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
                    pdMainImagePresenter.jumpToPage.setValue(Boolean.FALSE);
                    return;
                }
                pdMainImagePresenter.mtaClick("Productdetail_MainPhotoMatch", jDJSONObject.toJSONString());
                Context context = this.f4798i;
                if (build == null) {
                    return;
                }
                DeeplinkProductDetailHelper.startProductDetail(context, build);
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
    }

    public PdMImageAnchorLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        b(context);
    }
}
