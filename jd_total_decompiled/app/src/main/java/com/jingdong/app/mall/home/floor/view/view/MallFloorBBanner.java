package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.floor.animation.lottie.LottieAnimationViewCatchDraw;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.f.a.n;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class MallFloorBBanner extends BaseMallFloor<n> {
    public static final int TYPE_9_9 = 3;
    public static final int TYPE_LIVE = 2;
    public static final int TYPE_MIL_ALL = 4;
    public static final int TYPE_SEC_KILL = 1;
    private final f bgSize;
    private String bgUrl;
    private HomeDraweeView bgView;
    private String lastLoadSuccessBgUrl;
    private String lastLoadSuccessMaskUrl;
    private GradientDrawable mDefDrawable;
    private final f maskSize;
    private String maskUrl;
    private HomeDraweeView maskView;
    private TextView showNameTv;
    private final f showNameTvSize;
    private ISkuItem[] skuItemList;
    private boolean useMask;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class BLiveLottieView extends LottieAnimationViewCatchDraw {
        private static final AtomicBoolean isInit = new AtomicBoolean(false);
        private static String sIconString;

        public BLiveLottieView(Context context) {
            super(context);
            initLiveLottie();
        }

        private void initLiveLottie() {
            try {
                setImageAssetsFolder("assets/");
                if (TextUtils.isEmpty(sIconString)) {
                    sIconString = k.o("local/homeLiveLottie_b.json");
                }
                if (isValid(sIconString)) {
                    setLottieJson(sIconString, "HOME_LIVE_LOTTIE_B");
                    isInit.set(true);
                } else {
                    setVisibility(8);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            setRepeatCount(-1);
        }

        public void start() {
            try {
                if (isInit.get()) {
                    setVisibility(0);
                    setFrame(0);
                    playAnimation();
                }
            } catch (Exception e2) {
                com.jingdong.app.mall.home.o.a.f.o(e2.getMessage());
            }
        }

        public void stop() {
            pauseAnimation();
            setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public interface ISkuItem {
        void bind(n nVar, int i2);

        View toView();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class LiveItemView extends RelativeLayout implements ISkuItem {
        private SkuLabel label;
        private final f labelSize;
        private final f lottieSize;
        private BLiveLottieView lottieView;
        private int mIndex;
        private HomeDraweeView sku;

        public LiveItemView(Context context) {
            super(context);
            this.lottieSize = new f(120, 120);
            this.labelSize = new f(-2, 34);
        }

        private void bindLottie(n nVar) {
            BLiveLottieView bLiveLottieView = this.lottieView;
            if (bLiveLottieView != null) {
                bLiveLottieView.stop();
            }
            this.lottieSize.E(0, 26, 0, 0);
            BLiveLottieView bLiveLottieView2 = this.lottieView;
            if (bLiveLottieView2 == null) {
                BLiveLottieView bLiveLottieView3 = new BLiveLottieView(getContext());
                this.lottieView = bLiveLottieView3;
                RelativeLayout.LayoutParams u = this.lottieSize.u(bLiveLottieView3);
                u.addRule(14);
                addView(this.lottieView, u);
            } else {
                f.c(bLiveLottieView2, this.lottieSize);
            }
            if (nVar.i0(this.mIndex)) {
                this.lottieView.start();
            } else {
                this.lottieView.stop();
            }
        }

        private void bindSku(n nVar) {
            boolean i0 = nVar.i0(this.mIndex);
            int i2 = i0 ? 100 : 108;
            f fVar = new f(i2, i2);
            fVar.E(0, i0 ? 36 : 32, 0, 0);
            HomeDraweeView homeDraweeView = this.sku;
            if (homeDraweeView == null) {
                HomeDraweeView homeDraweeView2 = new HomeDraweeView(getContext());
                this.sku = homeDraweeView2;
                homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
                RelativeLayout.LayoutParams u = fVar.u(this.sku);
                u.addRule(14);
                addView(this.sku, u);
            } else {
                f.c(homeDraweeView, fVar);
            }
            e.d(this.sku, d.d(i0 ? 50 : 20));
            com.jingdong.app.mall.home.floor.ctrl.e.u(this.sku, nVar.b0(this.mIndex));
        }

        private void bindSkuLabel(n nVar) {
            this.labelSize.E(0, 132, 0, 0);
            SkuLabel skuLabel = this.label;
            if (skuLabel == null) {
                SkuLabel skuLabel2 = new SkuLabel(getContext());
                this.label = skuLabel2;
                RelativeLayout.LayoutParams u = this.labelSize.u(skuLabel2);
                u.addRule(14);
                addView(this.label, u);
            } else {
                f.c(skuLabel, this.labelSize);
            }
            this.label.f(nVar.c0(this.mIndex));
        }

        @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorBBanner.ISkuItem
        public void bind(final n nVar, int i2) {
            this.mIndex = i2;
            setVisibility(nVar.g0(i2) ? 0 : 8);
            bindSku(nVar);
            bindLottie(nVar);
            bindSkuLabel(nVar);
            setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBBanner.LiveItemView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    n nVar2 = nVar;
                    LiveItemView liveItemView = LiveItemView.this;
                    nVar2.m0(liveItemView, liveItemView.mIndex, LiveItemView.this.mIndex + 1);
                }
            });
        }

        @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorBBanner.ISkuItem
        public View toView() {
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class MilAllItemView extends RelativeLayout implements ISkuItem {
        private static final int ALL_LABEL_HEIGHT = 28;
        private SkuLabel allowanceLabel;
        private final f allowanceLabelSize;
        private int mIndex;
        private SkuLabel priceLabel;
        private final f priceLabelSize;
        private HomeDraweeView sku;
        private final f skuSize;

        public MilAllItemView(Context context) {
            super(context);
            this.skuSize = new f(126, 126);
            this.allowanceLabelSize = new f(-2, 28);
            this.priceLabelSize = new f(-2, 36);
        }

        private void binPriceLabel(n nVar) {
            this.priceLabelSize.E(0, R2.anim.out_to_bottom_slow, 0, 0);
            SkuLabel skuLabel = this.priceLabel;
            if (skuLabel == null) {
                SkuLabel skuLabel2 = new SkuLabel(getContext());
                this.priceLabel = skuLabel2;
                RelativeLayout.LayoutParams u = this.priceLabelSize.u(skuLabel2);
                u.addRule(14);
                addView(this.priceLabel, u);
            } else {
                f.c(skuLabel, this.priceLabelSize);
            }
            this.priceLabel.f(nVar.c0(this.mIndex));
        }

        private void bindAllowanceLabel(n nVar) {
            this.allowanceLabelSize.E(0, 124, 0, 0);
            SkuLabel skuLabel = this.allowanceLabel;
            if (skuLabel == null) {
                SkuLabel skuLabel2 = new SkuLabel(getContext());
                this.allowanceLabel = skuLabel2;
                RelativeLayout.LayoutParams u = this.allowanceLabelSize.u(skuLabel2);
                u.addRule(14);
                addView(this.allowanceLabel, u);
            } else {
                f.c(skuLabel, this.allowanceLabelSize);
            }
            SkuLabel.Info R = nVar.R(this.mIndex);
            if (R != null) {
                R.j(28);
            }
            this.allowanceLabel.f(R);
        }

        private void bindSku(n nVar) {
            this.skuSize.E(0, 16, 0, 0);
            HomeDraweeView homeDraweeView = this.sku;
            if (homeDraweeView == null) {
                HomeDraweeView homeDraweeView2 = new HomeDraweeView(getContext());
                this.sku = homeDraweeView2;
                homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
                RelativeLayout.LayoutParams u = this.skuSize.u(this.sku);
                u.addRule(14);
                addView(this.sku, u);
            } else {
                f.c(homeDraweeView, this.skuSize);
            }
            e.d(this.sku, d.d(8));
            com.jingdong.app.mall.home.floor.ctrl.e.u(this.sku, nVar.b0(this.mIndex));
        }

        @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorBBanner.ISkuItem
        public void bind(final n nVar, int i2) {
            this.mIndex = i2;
            setVisibility(nVar.g0(i2) ? 0 : 8);
            bindSku(nVar);
            bindAllowanceLabel(nVar);
            binPriceLabel(nVar);
            setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBBanner.MilAllItemView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    n nVar2 = nVar;
                    MilAllItemView milAllItemView = MilAllItemView.this;
                    nVar2.m0(milAllItemView, milAllItemView.mIndex, MilAllItemView.this.mIndex + 1);
                }
            });
        }

        @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorBBanner.ISkuItem
        public View toView() {
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class SecKillItemView extends RelativeLayout implements ISkuItem {
        private SkuLabel label;
        private final f labelSize;
        private int mIndex;
        private HomeDraweeView sku;
        private final f skuSize;

        public SecKillItemView(Context context) {
            super(context);
            this.skuSize = new f(128, 128);
            this.labelSize = new f(-2, 30);
        }

        private void bindSku(n nVar) {
            this.skuSize.E(0, 22, 0, 0);
            HomeDraweeView homeDraweeView = this.sku;
            if (homeDraweeView == null) {
                HomeDraweeView homeDraweeView2 = new HomeDraweeView(getContext());
                this.sku = homeDraweeView2;
                homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
                RelativeLayout.LayoutParams u = this.skuSize.u(this.sku);
                u.addRule(14);
                addView(this.sku, u);
            } else {
                f.c(homeDraweeView, this.skuSize);
            }
            e.d(this.sku, d.d(8));
            com.jingdong.app.mall.home.floor.ctrl.e.u(this.sku, nVar.b0(this.mIndex));
        }

        private void bindSkuLabel(n nVar) {
            this.labelSize.E(0, R2.anim.pickerview_dialog_scale_in, 0, 0);
            SkuLabel skuLabel = this.label;
            if (skuLabel == null) {
                SkuLabel skuLabel2 = new SkuLabel(getContext());
                this.label = skuLabel2;
                RelativeLayout.LayoutParams u = this.labelSize.u(skuLabel2);
                u.addRule(14);
                addView(this.label, u);
            } else {
                f.c(skuLabel, this.labelSize);
            }
            this.label.f(nVar.c0(this.mIndex));
        }

        @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorBBanner.ISkuItem
        public void bind(final n nVar, int i2) {
            this.mIndex = i2;
            setVisibility(nVar.g0(i2) ? 0 : 8);
            bindSku(nVar);
            bindSkuLabel(nVar);
            setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBBanner.SecKillItemView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    n nVar2 = nVar;
                    SecKillItemView secKillItemView = SecKillItemView.this;
                    nVar2.m0(secKillItemView, secKillItemView.mIndex, SecKillItemView.this.mIndex + 1);
                }
            });
        }

        @Override // com.jingdong.app.mall.home.floor.view.view.MallFloorBBanner.ISkuItem
        public View toView() {
            return this;
        }
    }

    public MallFloorBBanner(Context context, int i2) {
        super(context);
        this.bgSize = new f(-1, -1);
        this.maskSize = new f(-1, -1);
        this.showNameTvSize = new f(160, 38);
        this.maskUrl = "";
        this.bgUrl = "";
        this.useMask = false;
        this.lastLoadSuccessBgUrl = "";
        this.lastLoadSuccessMaskUrl = "";
        ((n) this.mPresenter).o0(i2);
    }

    private void bindBg() {
        HomeDraweeView homeDraweeView = this.bgView;
        if (homeDraweeView == null) {
            HomeDraweeView homeDraweeView2 = new HomeDraweeView(this.mContext);
            this.bgView = homeDraweeView2;
            homeDraweeView2.setScaleType(ImageView.ScaleType.FIT_XY);
            m.b(this, this.bgView, 0);
            HomeDraweeView homeDraweeView3 = this.bgView;
            homeDraweeView3.setLayoutParams(this.bgSize.u(homeDraweeView3));
        } else {
            f.c(homeDraweeView, this.bgSize);
        }
        if (this.mDefDrawable == null) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            this.mDefDrawable = gradientDrawable;
            gradientDrawable.setColor(-1);
            this.mDefDrawable.setShape(0);
        }
        this.mDefDrawable.setCornerRadius(d.d(((n) this.mPresenter).U()));
        com.jingdong.app.mall.home.floor.ctrl.e.p(this.bgView, this.bgUrl, this.mDefDrawable, new e.b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBBanner.2
            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onFailed(String str, View view) {
                MallFloorBBanner.this.lastLoadSuccessBgUrl = "";
                if (com.jingdong.app.mall.home.floor.ctrl.e.A(MallFloorBBanner.this.bgView, MallFloorBBanner.this.bgUrl) || MallFloorBBanner.this.useMask || MallFloorBBanner.this.showNameTv == null) {
                    return;
                }
                MallFloorBBanner.this.showNameTv.setVisibility(0);
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onStart(String str, View view) {
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onSuccess(String str, View view) {
                if (com.jingdong.app.mall.home.floor.ctrl.e.A(MallFloorBBanner.this.bgView, MallFloorBBanner.this.bgUrl)) {
                    return;
                }
                MallFloorBBanner.this.lastLoadSuccessBgUrl = str;
                if (!MallFloorBBanner.this.useMask && MallFloorBBanner.this.showNameTv != null) {
                    MallFloorBBanner.this.showNameTv.setVisibility(8);
                }
                MallFloorBBanner.this.setBackgroundColor(0);
            }
        });
    }

    private void bindMask() {
        if (TextUtils.isEmpty(this.maskUrl)) {
            this.lastLoadSuccessMaskUrl = "";
            HomeDraweeView homeDraweeView = this.maskView;
            if (homeDraweeView != null) {
                homeDraweeView.setVisibility(8);
                return;
            }
            return;
        }
        HomeDraweeView homeDraweeView2 = this.maskView;
        if (homeDraweeView2 == null) {
            HomeDraweeView homeDraweeView3 = new HomeDraweeView(this.mContext);
            this.maskView = homeDraweeView3;
            homeDraweeView3.setScaleType(ImageView.ScaleType.FIT_XY);
            HomeDraweeView homeDraweeView4 = this.maskView;
            addView(homeDraweeView4, this.maskSize.u(homeDraweeView4));
        } else {
            f.c(homeDraweeView2, this.maskSize);
        }
        this.maskView.setVisibility(0);
        com.jingdong.app.mall.home.floor.ctrl.e.p(this.maskView, this.maskUrl, com.jingdong.app.mall.home.floor.ctrl.e.b, new e.b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBBanner.3
            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onFailed(String str, View view) {
                if (com.jingdong.app.mall.home.floor.ctrl.e.A(MallFloorBBanner.this.maskView, MallFloorBBanner.this.maskUrl)) {
                    return;
                }
                MallFloorBBanner.this.lastLoadSuccessMaskUrl = "";
                if (MallFloorBBanner.this.showNameTv != null) {
                    MallFloorBBanner.this.showNameTv.setVisibility(0);
                }
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onStart(String str, View view) {
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onSuccess(String str, View view) {
                if (com.jingdong.app.mall.home.floor.ctrl.e.A(MallFloorBBanner.this.maskView, MallFloorBBanner.this.maskUrl)) {
                    return;
                }
                MallFloorBBanner.this.lastLoadSuccessMaskUrl = str;
                if (MallFloorBBanner.this.showNameTv != null) {
                    MallFloorBBanner.this.showNameTv.setVisibility(8);
                }
            }
        });
    }

    private void bindShowName() {
        boolean z;
        this.showNameTvSize.E(0, 42, 0, 0);
        TextView textView = this.showNameTv;
        boolean z2 = true;
        if (textView == null) {
            g gVar = new g(this.mContext, false);
            gVar.d(17);
            gVar.f(1);
            gVar.g(0, -3, 0, -3);
            gVar.l(-16777216);
            gVar.m(28);
            gVar.j(true);
            TextView a = gVar.a();
            this.showNameTv = a;
            addView(a, this.showNameTvSize.u(a));
        } else {
            f.c(textView, this.showNameTvSize);
            g.o(this.showNameTv, 28);
        }
        this.showNameTv.setText(((n) this.mPresenter).a0());
        if (this.useMask) {
            z = !TextUtils.equals(this.maskUrl, this.lastLoadSuccessMaskUrl);
        } else {
            if (!TextUtils.isEmpty(this.bgUrl) && TextUtils.equals(this.bgUrl, this.lastLoadSuccessBgUrl)) {
                z2 = false;
            }
            z = z2;
        }
        this.showNameTv.setVisibility(z ? 0 : 8);
    }

    private void bindSku() {
        ISkuItem secKillItemView;
        if (this.skuItemList == null) {
            this.skuItemList = new ISkuItem[4];
        }
        for (int i2 = 0; i2 < 4; i2++) {
            f d0 = ((n) this.mPresenter).d0(i2);
            ISkuItem[] iSkuItemArr = this.skuItemList;
            if (iSkuItemArr[i2] == null) {
                if (!((n) this.mPresenter).k0() && !((n) this.mPresenter).f0()) {
                    if (((n) this.mPresenter).j0()) {
                        secKillItemView = new MilAllItemView(this.mContext);
                    } else {
                        secKillItemView = new LiveItemView(this.mContext);
                    }
                } else {
                    secKillItemView = new SecKillItemView(this.mContext);
                }
                ISkuItem[] iSkuItemArr2 = this.skuItemList;
                iSkuItemArr2[i2] = secKillItemView;
                addView(iSkuItemArr2[i2].toView(), d0.u(this.skuItemList[i2].toView()));
            } else {
                f.c(iSkuItemArr[i2].toView(), d0);
            }
            this.skuItemList[i2].bind((n) this.mPresenter, i2);
        }
    }

    private void initData() {
        this.maskUrl = ((n) this.mPresenter).X();
        this.bgUrl = ((n) this.mPresenter).S();
        this.useMask = !TextUtils.isEmpty(this.maskUrl);
    }

    private void sendExpo() {
        if (((n) this.mPresenter).k0()) {
            a.A("Home_SeckillExpo", "", ((n) this.mPresenter).Z().toString(), RecommendMtaUtils.Home_PageId, l.f());
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomePause() {
        super.onHomePause();
        if (m.I(this, com.jingdong.app.mall.home.a.f8560i, com.jingdong.app.mall.home.a.f8562k, false)) {
            sendExpo();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        initData();
        setContentDescription(((n) this.mPresenter).V());
        bindShowName();
        bindBg();
        bindSku();
        bindMask();
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBBanner.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ((n) ((BaseMallColorFloor) MallFloorBBanner.this).mPresenter).m0(MallFloorBBanner.this, 0, 0);
            }
        });
        sendExpo();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    protected boolean useBgMarginColor() {
        return this.mFloorBindElement.s != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public n createPresenter() {
        return new n();
    }
}
