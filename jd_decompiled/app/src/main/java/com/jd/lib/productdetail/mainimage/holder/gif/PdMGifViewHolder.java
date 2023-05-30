package com.jd.lib.productdetail.mainimage.holder.gif;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicPicItems;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.utils.OpenAppUtils;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jd.lib.productdetail.mainimage.holder.gif.PdMGifViewHolder;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImageParams;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.common.deeplinkhelper.DeepLink3DProductHelper;
import com.jingdong.common.utils.JDImageUtils;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMGifViewHolder extends PdMainImageBaseHolder {
    public ImageView B;
    public ImageView C;
    public RelativeLayout D;
    public boolean E;
    public FrameLayout F;
    public String G;
    public boolean H;
    public Handler I;

    /* loaded from: classes15.dex */
    public class a implements Observer<Boolean> {
        public a() {
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(Boolean bool) {
            if (bool.booleanValue()) {
                PdMGifViewHolder pdMGifViewHolder = PdMGifViewHolder.this;
                pdMGifViewHolder.H = true;
                pdMGifViewHolder.J();
                return;
            }
            PdMGifViewHolder pdMGifViewHolder2 = PdMGifViewHolder.this;
            pdMGifViewHolder2.H = false;
            pdMGifViewHolder2.I();
        }
    }

    /* loaded from: classes15.dex */
    public class b implements Observer<WareBusinessUnitMainImageEntity> {
        public b() {
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity) {
            PdMGifViewHolder pdMGifViewHolder = PdMGifViewHolder.this;
            FrameLayout frameLayout = pdMGifViewHolder.F;
            if (frameLayout != null) {
                DeepLink3DProductHelper.cancel_preload(frameLayout);
            }
            RelativeLayout relativeLayout = pdMGifViewHolder.D;
            if (relativeLayout != null) {
                relativeLayout.setVisibility(8);
            }
            pdMGifViewHolder.G = "";
            pdMGifViewHolder.F = null;
        }
    }

    /* loaded from: classes15.dex */
    public class c implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ WareBusinessMagicPicItems f4816g;

        public c(WareBusinessMagicPicItems wareBusinessMagicPicItems) {
            this.f4816g = wareBusinessMagicPicItems;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PDUtils.repeatClick()) {
                WareBusinessMagicPicItems wareBusinessMagicPicItems = this.f4816g;
                if (wareBusinessMagicPicItems != null) {
                    PdMGifViewHolder.this.l(wareBusinessMagicPicItems.anchorType, -100);
                }
                WareBusinessMagicPicItems wareBusinessMagicPicItems2 = this.f4816g;
                if (wareBusinessMagicPicItems2 == null || TextUtils.isEmpty(wareBusinessMagicPicItems2.jumpUrl)) {
                    return;
                }
                PdMGifViewHolder pdMGifViewHolder = PdMGifViewHolder.this;
                if (pdMGifViewHolder.E) {
                    String str = this.f4816g.jumpUrl;
                    pdMGifViewHolder.G = str;
                    pdMGifViewHolder.G(str);
                    return;
                }
                if (this.f4816g.jumpUrl.startsWith("http")) {
                    PDBaseDeepLinkHelper.gotoMWithUrl(view.getContext(), this.f4816g.jumpUrl);
                } else {
                    OpenAppUtils.openAppForInner(view.getContext(), this.f4816g.jumpUrl);
                }
                PdMainImagePresenter pdMainImagePresenter = PdMGifViewHolder.this.f4654n;
                if (pdMainImagePresenter != null) {
                    pdMainImagePresenter.jumpToPage.setValue(Boolean.TRUE);
                }
            }
        }
    }

    /* loaded from: classes15.dex */
    public class d implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        public final /* synthetic */ WareBusinessMagicPicItems f4818g;

        public d(WareBusinessMagicPicItems wareBusinessMagicPicItems) {
            this.f4818g = wareBusinessMagicPicItems;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (PDUtils.repeatClick()) {
                WareBusinessMagicPicItems wareBusinessMagicPicItems = this.f4818g;
                if (wareBusinessMagicPicItems != null) {
                    PdMGifViewHolder.this.l(wareBusinessMagicPicItems.anchorType, -100);
                }
                WareBusinessMagicPicItems wareBusinessMagicPicItems2 = this.f4818g;
                if (wareBusinessMagicPicItems2 == null || TextUtils.isEmpty(wareBusinessMagicPicItems2.jumpUrl)) {
                    return;
                }
                if (this.f4818g.jumpUrl.startsWith("http")) {
                    PDBaseDeepLinkHelper.gotoMWithUrl(view.getContext(), this.f4818g.jumpUrl);
                } else {
                    OpenAppUtils.openAppForInner(view.getContext(), this.f4818g.jumpUrl);
                }
                PdMainImagePresenter pdMainImagePresenter = PdMGifViewHolder.this.f4654n;
                if (pdMainImagePresenter != null) {
                    pdMainImagePresenter.jumpToPage.setValue(Boolean.TRUE);
                }
            }
        }
    }

    /* loaded from: classes15.dex */
    public class e implements DeepLink3DProductHelper.PreloadingListener {
        public final /* synthetic */ String a;

        /* loaded from: classes15.dex */
        public class a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            public final /* synthetic */ long f4820g;

            public a(long j2) {
                this.f4820g = j2;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void a(String str) {
                PdMGifViewHolder pdMGifViewHolder = PdMGifViewHolder.this;
                pdMGifViewHolder.G = "";
                OpenAppUtils.openAppForInner(pdMGifViewHolder.f4649i, str);
                PdMainImagePresenter pdMainImagePresenter = PdMGifViewHolder.this.f4654n;
                if (pdMainImagePresenter != null) {
                    pdMainImagePresenter.jumpToPage.setValue(Boolean.TRUE);
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                long j2 = this.f4820g;
                if (j2 >= 0 && j2 < 100) {
                    PdMGifViewHolder.this.D.setVisibility(0);
                } else {
                    PdMGifViewHolder.this.D.setVisibility(8);
                }
                long j3 = this.f4820g;
                if (j3 >= 100) {
                    e eVar = e.this;
                    SimpleDraweeView simpleDraweeView = PdMGifViewHolder.this.f4652l;
                    final String str = eVar.a;
                    simpleDraweeView.post(new Runnable() { // from class: com.jd.lib.productdetail.mainimage.holder.gif.a
                        @Override // java.lang.Runnable
                        public final void run() {
                            PdMGifViewHolder.e.a.this.a(str);
                        }
                    });
                } else if (j3 == -1) {
                    e eVar2 = e.this;
                    PdMGifViewHolder.this.G = "";
                    if (TextUtils.isEmpty(eVar2.a)) {
                        return;
                    }
                    if (e.this.a.startsWith("http")) {
                        e eVar3 = e.this;
                        PDBaseDeepLinkHelper.gotoMWithUrl(PdMGifViewHolder.this.f4649i, eVar3.a);
                        PdMainImagePresenter pdMainImagePresenter = PdMGifViewHolder.this.f4654n;
                        if (pdMainImagePresenter != null) {
                            pdMainImagePresenter.jumpToPage.setValue(Boolean.TRUE);
                            return;
                        }
                        return;
                    }
                    e eVar4 = e.this;
                    OpenAppUtils.openAppForInner(PdMGifViewHolder.this.f4649i, eVar4.a);
                    PdMainImagePresenter pdMainImagePresenter2 = PdMGifViewHolder.this.f4654n;
                    if (pdMainImagePresenter2 != null) {
                        pdMainImagePresenter2.jumpToPage.setValue(Boolean.TRUE);
                    }
                }
            }
        }

        public e(String str) {
            this.a = str;
        }

        @Override // com.jingdong.common.deeplinkhelper.DeepLink3DProductHelper.PreloadingListener
        public void onProgress(long j2) {
            PdMGifViewHolder.this.I.post(new a(j2));
        }
    }

    public PdMGifViewHolder(@NonNull View view, View view2) {
        super(view, view2);
        this.E = false;
        this.H = false;
        this.I = new Handler(Looper.getMainLooper());
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public boolean A() {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdArVrBizData pdArVrBizData;
        List<WareBusinessMagicPicItems> list;
        ImageView imageView;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
        if (wareBusinessMagicHeadPicInfoEntity != null && (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) != null && (pdArVrBizData = wareBuinessUnitMainImageBizDataEntity.arVrBizData) != null && (list = pdArVrBizData.items) != null && list.size() == 1 && (imageView = this.B) != null && imageView.getVisibility() == 0 && this.C.getVisibility() == 8) {
            WareBusinessMagicPicItems wareBusinessMagicPicItems = this.r.bizData.arVrBizData.items.get(0);
            if (wareBusinessMagicPicItems != null) {
                l(wareBusinessMagicPicItems.anchorType, -100);
            }
            if (wareBusinessMagicPicItems != null && !TextUtils.isEmpty(wareBusinessMagicPicItems.jumpUrl)) {
                if (this.E) {
                    G(wareBusinessMagicPicItems.jumpUrl);
                    return true;
                } else if (wareBusinessMagicPicItems.jumpUrl.startsWith("http")) {
                    PdMainImagePresenter pdMainImagePresenter = this.f4654n;
                    if (pdMainImagePresenter != null) {
                        pdMainImagePresenter.jumpToPage.setValue(Boolean.TRUE);
                    }
                    PDBaseDeepLinkHelper.gotoMWithUrl(this.B.getContext(), wareBusinessMagicPicItems.jumpUrl);
                    return true;
                } else {
                    PdMainImagePresenter pdMainImagePresenter2 = this.f4654n;
                    if (pdMainImagePresenter2 != null) {
                        pdMainImagePresenter2.jumpToPage.setValue(Boolean.TRUE);
                    }
                    OpenAppUtils.openAppForInner(this.B.getContext(), wareBusinessMagicPicItems.jumpUrl);
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void E() {
        I();
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void F() {
        J();
    }

    public final void G(String str) {
        WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity;
        ViewGroup H = H();
        if (this.E && H != null && this.F != null && !TextUtils.isEmpty(str) && (wareBusinessUnitMainImageEntity = this.v) != null && wareBusinessUnitMainImageEntity.extMap != null) {
            if (H() == null) {
                if (str.startsWith("http")) {
                    PdMainImagePresenter pdMainImagePresenter = this.f4654n;
                    if (pdMainImagePresenter != null) {
                        pdMainImagePresenter.jumpToPage.setValue(Boolean.TRUE);
                    }
                    PDBaseDeepLinkHelper.gotoMWithUrl(this.f4649i, str);
                    return;
                }
                PdMainImagePresenter pdMainImagePresenter2 = this.f4654n;
                if (pdMainImagePresenter2 != null) {
                    pdMainImagePresenter2.jumpToPage.setValue(Boolean.TRUE);
                }
                OpenAppUtils.openAppForInner(this.f4649i, str);
                return;
            }
            DeepLink3DProductHelper.preloading(this.F, H, this.v.extMap.skuId, str, true, new e(str));
        } else if (str.startsWith("http")) {
            PDBaseDeepLinkHelper.gotoMWithUrl(this.f4649i, str);
            PdMainImagePresenter pdMainImagePresenter3 = this.f4654n;
            if (pdMainImagePresenter3 != null) {
                pdMainImagePresenter3.jumpToPage.setValue(Boolean.TRUE);
            }
        } else {
            OpenAppUtils.openAppForInner(this.f4649i, str);
            PdMainImagePresenter pdMainImagePresenter4 = this.f4654n;
            if (pdMainImagePresenter4 != null) {
                pdMainImagePresenter4.jumpToPage.setValue(Boolean.TRUE);
            }
        }
    }

    public final ViewGroup H() {
        PdMainImageParams pdMainImageParams;
        Integer num;
        View decorView;
        PdMainImagePresenter pdMainImagePresenter = this.f4654n;
        if (pdMainImagePresenter == null || (pdMainImageParams = pdMainImagePresenter.mainImageParams) == null || (num = pdMainImageParams.topViewIdFor3D) == null || num.intValue() <= 0 || t() == null || (decorView = t().getWindow().getDecorView()) == null || !(decorView.findViewById(this.f4654n.mainImageParams.topViewIdFor3D.intValue()) instanceof ViewGroup)) {
            return null;
        }
        return (ViewGroup) decorView.findViewById(this.f4654n.mainImageParams.topViewIdFor3D.intValue());
    }

    public void I() {
        RelativeLayout relativeLayout;
        int preloading_get_status;
        FrameLayout frameLayout = this.F;
        boolean z = true;
        if (frameLayout == null || ((preloading_get_status = DeepLink3DProductHelper.preloading_get_status(frameLayout)) != 0 && preloading_get_status != 1)) {
            z = false;
        }
        if (z && this.E && this.F != null && (relativeLayout = this.D) != null && relativeLayout.getVisibility() == 0) {
            DeepLink3DProductHelper.preloading_action(this.F, 0);
        }
    }

    public void J() {
        RelativeLayout relativeLayout;
        int preloading_get_status;
        if (this.H && this.f4650j) {
            FrameLayout frameLayout = this.F;
            if ((frameLayout != null && ((preloading_get_status = DeepLink3DProductHelper.preloading_get_status(frameLayout)) == 0 || preloading_get_status == 1)) && this.E && this.F != null && (relativeLayout = this.D) != null && relativeLayout.getVisibility() == 0) {
                DeepLink3DProductHelper.preloading_action(this.F, 1);
            }
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void a(boolean z) {
        this.f4650j = z;
        if (z) {
            J();
        } else {
            I();
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void d(View view) {
        this.B = (ImageView) view.findViewById(R.id.pd_topimage_gif_btn_1);
        this.C = (ImageView) view.findViewById(R.id.pd_topimage_gif_btn_2);
        this.D = (RelativeLayout) view.findViewById(R.id.pd_topimage_rootView_3d);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void j(PdMainImagePresenter pdMainImagePresenter) {
        LifecycleOwner lifecycleOwner;
        super.j(pdMainImagePresenter);
        PdMainImagePresenter pdMainImagePresenter2 = this.f4654n;
        if (pdMainImagePresenter2 == null || (lifecycleOwner = this.o) == null) {
            return;
        }
        pdMainImagePresenter2.windowAttachState.observe(lifecycleOwner, new a());
        this.f4654n.mainImageData.observe(this.o, new b());
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void n() {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdArVrBizData pdArVrBizData;
        List<WareBusinessMagicPicItems> list;
        if (this.r != null) {
            this.E = false;
            this.B.setVisibility(8);
            this.C.setVisibility(8);
            WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
            if (wareBusinessMagicHeadPicInfoEntity == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) == null || (pdArVrBizData = wareBuinessUnitMainImageBizDataEntity.arVrBizData) == null || (list = pdArVrBizData.items) == null) {
                return;
            }
            if (list.size() > 0) {
                this.B.setVisibility(0);
                WareBusinessMagicPicItems wareBusinessMagicPicItems = this.r.bizData.arVrBizData.items.get(0);
                this.E = wareBusinessMagicPicItems.hasNew3d && TextUtils.equals(this.r.anchorType, WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_3D_NEW);
                JDImageUtils.displayImage(wareBusinessMagicPicItems.icon, this.B);
                this.B.setOnClickListener(new c(wareBusinessMagicPicItems));
            }
            if (this.r.bizData.arVrBizData.items.size() > 1) {
                this.C.setVisibility(0);
                WareBusinessMagicPicItems wareBusinessMagicPicItems2 = this.r.bizData.arVrBizData.items.get(1);
                JDImageUtils.displayImage(wareBusinessMagicPicItems2.icon, this.C);
                this.C.setOnClickListener(new d(wareBusinessMagicPicItems2));
            }
            if (this.E) {
                if (this.F == null) {
                    FrameLayout preloadingWidget = DeepLink3DProductHelper.getPreloadingWidget(this.f4649i);
                    this.F = preloadingWidget;
                    if (preloadingWidget != null) {
                        preloadingWidget.setBackgroundResource(R.drawable.lib_pd_mainimage_holder_preload_bg);
                        this.D.setVisibility(8);
                        this.D.removeAllViews();
                        this.D.addView(this.F, new ViewGroup.LayoutParams(-1, -1));
                        return;
                    }
                    this.D.setVisibility(8);
                    return;
                }
                return;
            }
            this.D.setVisibility(8);
        }
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void r() {
        this.f4651k = true;
        FrameLayout frameLayout = this.F;
        if (frameLayout != null) {
            DeepLink3DProductHelper.cancel_preload(frameLayout);
        }
        RelativeLayout relativeLayout = this.D;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
        this.F = null;
    }
}
