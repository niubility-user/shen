package com.jd.lib.productdetail.mainimage.holder.suit;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdDpgSmallInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdMainSku;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdTopImageSuitView extends ConstraintLayout implements com.jd.lib.productdetail.mainimage.dialog.a {

    /* renamed from: g  reason: collision with root package name */
    public com.jd.lib.productdetail.mainimage.dialog.a f4938g;

    /* renamed from: h  reason: collision with root package name */
    public PdMainImagePresenter f4939h;

    /* renamed from: i  reason: collision with root package name */
    public PdTopImageSuitViewFloor f4940i;

    /* renamed from: j  reason: collision with root package name */
    public PdTopImageSuitViewFloor f4941j;

    /* renamed from: k  reason: collision with root package name */
    public PdMainSku f4942k;

    /* renamed from: l  reason: collision with root package name */
    public ArrayList<PdDpgSmallInfo> f4943l;

    /* renamed from: m  reason: collision with root package name */
    public String f4944m;

    public PdTopImageSuitView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.jd.lib.productdetail.mainimage.dialog.a
    public void a() {
    }

    @Override // com.jd.lib.productdetail.mainimage.dialog.a
    public void a(Object obj) {
        com.jd.lib.productdetail.mainimage.dialog.a aVar = this.f4938g;
        if (aVar != null) {
            aVar.a(obj);
        }
    }

    public void b(PdMainSku pdMainSku, ArrayList<PdDpgSmallInfo> arrayList, String str, String str2, WareBuinessUnitMainImageBizDataEntity.PdRecommendRankShowMap pdRecommendRankShowMap, List<PdDpgListLayerInfo.DetailBean> list) {
        this.f4942k = pdMainSku;
        this.f4943l = arrayList;
        this.f4944m = str2;
        PdTopImageSuitViewFloor pdTopImageSuitViewFloor = this.f4940i;
        if (pdTopImageSuitViewFloor != null) {
            pdTopImageSuitViewFloor.setVisibility(8);
        }
        PdTopImageSuitViewFloor pdTopImageSuitViewFloor2 = this.f4941j;
        if (pdTopImageSuitViewFloor2 != null) {
            pdTopImageSuitViewFloor2.setVisibility(8);
        }
        this.f4940i.c(this);
        this.f4941j.c(this);
        if (pdMainSku == null || arrayList == null) {
            return;
        }
        if (arrayList.size() > 0 && arrayList.get(0) != null) {
            this.f4940i.setVisibility(0);
            PdTopImageSuitViewFloor pdTopImageSuitViewFloor3 = this.f4940i;
            pdTopImageSuitViewFloor3.u = this.f4939h;
            pdTopImageSuitViewFloor3.a(pdMainSku, arrayList.get(0), str, str2, pdRecommendRankShowMap, list);
        }
        if (arrayList.size() <= 1 || arrayList.get(1) == null) {
            return;
        }
        PdTopImageSuitViewFloor pdTopImageSuitViewFloor4 = this.f4941j;
        pdTopImageSuitViewFloor4.u = this.f4939h;
        pdTopImageSuitViewFloor4.setVisibility(0);
        this.f4941j.a(pdMainSku, arrayList.get(1), str, str2, pdRecommendRankShowMap, list);
    }

    public void c(boolean z) {
        PdTopImageSuitViewFloor pdTopImageSuitViewFloor = this.f4940i;
        if (pdTopImageSuitViewFloor != null) {
            pdTopImageSuitViewFloor.b(z);
        }
        PdTopImageSuitViewFloor pdTopImageSuitViewFloor2 = this.f4941j;
        if (pdTopImageSuitViewFloor2 != null) {
            pdTopImageSuitViewFloor2.b(z);
        }
    }

    public void d(com.jd.lib.productdetail.mainimage.dialog.a aVar) {
        this.f4938g = aVar;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        DisplayMetrics displayMetrics;
        super.onFinishInflate();
        this.f4940i = (PdTopImageSuitViewFloor) findViewById(R.id.lib_pd_holder_topimage_item_suit_floor_1);
        this.f4941j = (PdTopImageSuitViewFloor) findViewById(R.id.lib_pd_holder_topimage_item_suit_floor_2);
        if (!(getContext() instanceof Activity) || (displayMetrics = PDUtils.getDisplayMetrics(getContext())) == null) {
            return;
        }
        float f2 = displayMetrics.density;
        if (f2 <= 0.0f) {
            ExceptionReporter.reportExceptionToBugly(new RuntimeException());
            f2 = 1.0f;
        }
        float appWidth = (PDUtils.getAppWidth((Activity) getContext()) / f2) / 375.0f;
        ViewGroup.LayoutParams layoutParams = this.f4941j.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ((ViewGroup.MarginLayoutParams) ((ConstraintLayout.LayoutParams) layoutParams)).topMargin = (int) (PDUtils.dip2px(14.0f) * appWidth);
        }
        requestLayout();
    }
}
