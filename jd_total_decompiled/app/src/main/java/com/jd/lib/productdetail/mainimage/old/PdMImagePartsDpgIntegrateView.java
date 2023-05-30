package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.entitys.PDTopReocommendEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMainPictureDpgPops;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.views.PdAutoChangeTextSize;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class PdMImagePartsDpgIntegrateView extends RelativeLayout implements View.OnClickListener, LifecycleObserver {

    /* renamed from: g  reason: collision with root package name */
    public RecyclerView f5041g;

    /* renamed from: h  reason: collision with root package name */
    public SimpleDraweeView f5042h;

    /* renamed from: i  reason: collision with root package name */
    public a f5043i;

    /* renamed from: j  reason: collision with root package name */
    public ViewPager2 f5044j;

    /* renamed from: k  reason: collision with root package name */
    public ConstraintLayout f5045k;

    /* renamed from: l  reason: collision with root package name */
    public View f5046l;

    /* renamed from: m  reason: collision with root package name */
    public PdAutoChangeTextSize f5047m;

    /* renamed from: n  reason: collision with root package name */
    public PdMPartsDpgViewAdapter f5048n;
    public LinearLayoutManager o;
    public WareBusinessUnitMainImageEntity p;
    public PdMainImagePresenter q;

    /* loaded from: classes15.dex */
    public interface a {
        void a();
    }

    public PdMImagePartsDpgIntegrateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void b(boolean z) {
        Context context;
        int i2;
        this.f5042h.setImageResource(z ? R.drawable.lib_pd_mainimage_topimage_gift_layer_close_dark : R.drawable.lib_pd_mainimage_big_plus_layer_btn_close);
        setBackgroundResource(z ? R.drawable.lib_pd_mainimage_parts_recommend_dark_bg : R.drawable.lib_pd_mainimage_parts_recommend_bg);
        this.f5047m.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(getContext(), R.color.lib_pd_image_color_1A1A1A, z));
        this.f5047m.setBackgroundResource(z ? R.drawable.lib_pd_mainimage_parts_recommend_goon_btn_dark_bg : R.drawable.lib_pd_mainimage_parts_recommend_goon_btn_bg);
        this.f5045k.setBackgroundColor(ContextCompat.getColor(getContext(), z ? R.color.lib_pd_image_color_302E2E : R.color.lib_pd_image_color_FFFFFF));
        View view = this.f5046l;
        if (z) {
            context = getContext();
            i2 = R.color.lib_pd_image_color_222222;
        } else {
            context = getContext();
            i2 = R.color.lib_pd_image_color_E5E5E5;
        }
        view.setBackgroundColor(ContextCompat.getColor(context, i2));
    }

    public void a(WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity, PDTopReocommendEntity pDTopReocommendEntity, ArrayList<WareBusinessMainPictureDpgPops> arrayList) {
        b(this.q.getMainImageParams().isDark);
        this.p = wareBusinessUnitMainImageEntity;
        if (arrayList == null || pDTopReocommendEntity == null) {
            return;
        }
        if (TextUtils.equals(pDTopReocommendEntity.formType, "vrDPG")) {
            int i2 = -1;
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                if (TextUtils.equals(arrayList.get(i3).type, WareBusinessMagicHeadPicInfoEntity.FB_TOP_IMAGE_RECOMMEND)) {
                    i2 = i3;
                }
            }
            if (i2 >= 0) {
                arrayList.remove(i2);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        int i4 = 0;
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            WareBusinessMainPictureDpgPops wareBusinessMainPictureDpgPops = arrayList.get(i5);
            if (wareBusinessMainPictureDpgPops != null) {
                if (TextUtils.equals(pDTopReocommendEntity.formType, wareBusinessMainPictureDpgPops.type)) {
                    i4 = i5;
                }
                arrayList2.add(wareBusinessMainPictureDpgPops.title);
            }
        }
        PdMPartsDpgLayerTitleAdapter pdMPartsDpgLayerTitleAdapter = new PdMPartsDpgLayerTitleAdapter(this.f5041g, getContext());
        boolean z = this.q.getMainImageParams().isDark;
        pdMPartsDpgLayerTitleAdapter.a = arrayList2;
        pdMPartsDpgLayerTitleAdapter.b = i4;
        pdMPartsDpgLayerTitleAdapter.d = z;
        if (arrayList2.size() == 1) {
            pdMPartsDpgLayerTitleAdapter.f5067c = true;
        }
        pdMPartsDpgLayerTitleAdapter.f5069f = new a0(this, arrayList);
        this.f5041g.setAdapter(pdMPartsDpgLayerTitleAdapter);
        if (this.f5048n == null) {
            this.f5048n = new PdMPartsDpgViewAdapter(getContext());
        }
        PdMPartsDpgViewAdapter pdMPartsDpgViewAdapter = this.f5048n;
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity2 = this.p;
        pdMPartsDpgViewAdapter.a = arrayList;
        pdMPartsDpgViewAdapter.f5072e = pDTopReocommendEntity;
        pdMPartsDpgViewAdapter.f5073f = wareBusinessUnitMainImageEntity2;
        this.f5044j.setAdapter(pdMPartsDpgViewAdapter);
        PdMainImagePresenter pdMainImagePresenter = this.q;
        if (pdMainImagePresenter != null) {
            this.f5048n.f5074g = pdMainImagePresenter;
        }
        ViewPager2 viewPager2 = this.f5044j;
        if (viewPager2 != null) {
            viewPager2.setCurrentItem(i4, false);
        }
        for (int i6 = 0; i6 < arrayList.size(); i6++) {
            if (TextUtils.equals(arrayList.get(i6).type, "vrDPG")) {
                this.q.mtaExposure("Productdetail_DapeiBuyToastTabExpo");
            }
        }
    }

    public void c(PdMainImagePresenter pdMainImagePresenter) {
        this.q = pdMainImagePresenter;
        PdMPartsDpgViewAdapter pdMPartsDpgViewAdapter = this.f5048n;
        if (pdMPartsDpgViewAdapter != null) {
            pdMPartsDpgViewAdapter.f5074g = pdMainImagePresenter;
        }
    }

    public void d(a aVar) {
        this.f5043i = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.lib_pd_parts_dpg_dialog_goon_btn) {
            this.q.mtaClick("Productdetail_DapeiBuyGoHangingapei");
            a aVar = this.f5043i;
            if (aVar != null) {
                aVar.a();
            }
        } else if (id == R.id.lib_pd_parts_dpg_dialog_cart_btn) {
            this.q.mtaClick("Productdetail_DapeiBuyShoppingCart");
            DeepLinkCommonHelper.startShoppingCartActivity(getContext(), null, true);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestoryView() {
        PdMPartsRecommendView pdMPartsRecommendView;
        PdMPartsDpgViewAdapter pdMPartsDpgViewAdapter = this.f5048n;
        if (pdMPartsDpgViewAdapter == null || (pdMPartsRecommendView = pdMPartsDpgViewAdapter.f5071c) == null) {
            return;
        }
        pdMPartsRecommendView.C = true;
        pdMPartsRecommendView.f5119h = null;
        pdMPartsRecommendView.f5121j = null;
        pdMPartsRecommendView.f5122k = null;
        pdMPartsRecommendView.f5123l = null;
        pdMPartsRecommendView.f5125n = null;
        pdMPartsRecommendView.o = null;
        pdMPartsRecommendView.p = null;
        pdMPartsRecommendView.q = null;
        pdMPartsRecommendView.r = null;
        pdMPartsDpgViewAdapter.f5071c = null;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.f5041g = (RecyclerView) findViewById(R.id.pd_parts_dpg_layer_titlerecycle);
        if (this.o == null) {
            this.o = new LinearLayoutManager(getContext(), 0, false);
        }
        this.f5041g.setLayoutManager(this.o);
        this.f5041g.setItemAnimator(null);
        this.f5041g.setHasFixedSize(true);
        this.f5042h = (SimpleDraweeView) findViewById(R.id.pd_parts_dpg_layer_close_img);
        ViewPager2 viewPager2 = (ViewPager2) findViewById(R.id.pd_parts_dpg_layer_viewpager);
        this.f5044j = viewPager2;
        viewPager2.setUserInputEnabled(false);
        this.f5045k = (ConstraintLayout) findViewById(R.id.lib_pd_parts_dpg_dialog_bottom_layout);
        this.f5046l = findViewById(R.id.lib_pd_parts_dpg_dialog_bottom_line);
        PdAutoChangeTextSize pdAutoChangeTextSize = (PdAutoChangeTextSize) findViewById(R.id.lib_pd_parts_dpg_dialog_goon_btn);
        this.f5047m = pdAutoChangeTextSize;
        pdAutoChangeTextSize.setOnClickListener(this);
        ((PdAutoChangeTextSize) findViewById(R.id.lib_pd_parts_dpg_dialog_cart_btn)).setOnClickListener(this);
        LifecycleOwner lifecycleOwner = ViewTreeLifecycleOwner.get(((BaseActivity) getContext()).getWindow().getDecorView());
        if (lifecycleOwner != null) {
            lifecycleOwner.getLifecycle().addObserver(this);
        }
        findViewById(R.id.pd_parts_dpg_layer_close_layout).setOnClickListener(new z(this));
    }
}
