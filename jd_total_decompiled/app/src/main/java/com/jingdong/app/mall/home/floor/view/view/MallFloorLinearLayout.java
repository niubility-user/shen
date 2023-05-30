package com.jingdong.app.mall.home.floor.view.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.common.i.p;
import com.jingdong.app.mall.home.floor.common.i.r;
import com.jingdong.app.mall.home.floor.model.entity.LinearFloorEntity;
import com.jingdong.app.mall.home.floor.presenter.engine.LinearFloorEngine;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleCommon;
import com.jingdong.app.mall.home.floor.view.view.module.MallFloorModuleMixed;
import com.jingdong.app.mall.home.i;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.r.c.a;
import com.jingdong.app.mall.home.r.e.d;
import com.jingdong.app.mall.home.r.e.h;
import com.jingdong.app.mall.home.r.f.a.q;
import com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView;
import com.jingdong.common.recommend.RecommendMtaUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class MallFloorLinearLayout extends BaseMallFloor<q> {
    private int itemCount;
    private int itemWeightCount;
    private ArrayList<Integer> itemsWidths;
    private AnimatorListenerAdapter layerAdapter;
    private b layerTask;
    private ValueAnimator.AnimatorUpdateListener layerUpdateListener;
    private Handler mHandler;
    private ValueAnimator mLayerAnimator;
    private SimpleDraweeView mLayerBg;
    private static ConcurrentHashMap<String, Boolean> mExpoSalUrlMap = new ConcurrentHashMap<>();
    private static boolean hasShowLayer = false;

    public MallFloorLinearLayout(Context context) {
        super(context);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.layerTask = new b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLinearLayout.3
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                MallFloorLinearLayout.this.mLayerAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
                MallFloorLinearLayout.this.mLayerAnimator.setDuration(1000L);
                MallFloorLinearLayout.this.mLayerAnimator.setInterpolator(new LinearInterpolator());
                MallFloorLinearLayout.this.mLayerAnimator.addUpdateListener(MallFloorLinearLayout.this.layerUpdateListener);
                MallFloorLinearLayout.this.mLayerAnimator.addListener(MallFloorLinearLayout.this.layerAdapter);
                MallFloorLinearLayout.this.mLayerAnimator.start();
            }
        };
        this.layerUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLinearLayout.4
            int secondStartWidth = 0;

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (floatValue < 0.5f) {
                    int width = (int) (MallFloorLinearLayout.this.getWidth() - ((MallFloorLinearLayout.this.getWidth() * 0.37f) * (floatValue / 0.5f)));
                    this.secondStartWidth = width;
                    MallFloorLinearLayout.this.mLayerBg.getLayoutParams().width = width;
                } else {
                    float f2 = (floatValue - 0.5f) / 0.5f;
                    int i2 = this.secondStartWidth;
                    int height = (int) (i2 - ((i2 - (MallFloorLinearLayout.this.getHeight() * 0.8f)) * f2));
                    MallFloorLinearLayout.this.mLayerBg.getLayoutParams().width = height;
                    MallFloorLinearLayout.this.mLayerBg.getLayoutParams().height = (int) (MallFloorLinearLayout.this.getHeight() - ((MallFloorLinearLayout.this.getHeight() * 0.2f) * f2));
                    MallFloorLinearLayout.this.mLayerBg.setAlpha(((1.0f - f2) * 0.7f) + 0.3f);
                    e.d(MallFloorLinearLayout.this.mLayerBg, (int) ((MallFloorLinearLayout.this.getHeight() / 2) * f2));
                }
                MallFloorLinearLayout.this.mLayerBg.requestLayout();
            }
        };
        this.layerAdapter = new AnimatorListenerAdapter() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLinearLayout.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                MallFloorLinearLayout.this.removeLayer();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addLayerBgInMainThread() {
        removeAnimator();
        HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
        this.mLayerBg = homeDraweeView;
        homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.mLayerBg.setLayoutParams(layoutParams);
        l.w(this, this.mLayerBg, ((q) this.mPresenter).T(1), 2, "Home_FloorAnimation");
        m.b(this, this.mLayerBg, -1);
        ((q) this.mPresenter).f0();
        com.jingdong.app.mall.home.floor.ctrl.e.m(this.mLayerBg, ((q) this.mPresenter).U(), com.jingdong.app.mall.home.floor.ctrl.e.w());
        a.z("Home_FloorAnimationExpo", ((q) this.mPresenter).T(1).j(), "", RecommendMtaUtils.Home_PageId);
        hasShowLayer = true;
        startLayerTask();
    }

    private void clearExpoSalUrl() {
        if (mExpoSalUrlMap.size() > 0) {
            mExpoSalUrlMap.clear();
        }
    }

    private void removeAnimator() {
        removeLayer();
        this.mHandler.removeCallbacks(this.layerTask);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeLayer() {
        ValueAnimator valueAnimator = this.mLayerAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        SimpleDraweeView simpleDraweeView = this.mLayerBg;
        if (simpleDraweeView == null) {
            return;
        }
        ViewParent parent = simpleDraweeView.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.mLayerBg);
            this.mLayerAnimator = null;
        }
    }

    public void addLayerBg() {
        if (hasShowLayer) {
            return;
        }
        if (unUsePostWaitThread()) {
            addLayerBgInMainThread();
        } else {
            f.u0(new b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLinearLayout.2
                @Override // com.jingdong.app.mall.home.o.a.b
                protected void safeRun() {
                    MallFloorLinearLayout.this.addLayerBgInMainThread();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public void beforeInitData(h hVar, d dVar, boolean z) {
        super.beforeInitData(hVar, dVar, z);
        if (z) {
            return;
        }
        a.i().c(((q) this.mPresenter).i(), ((q) this.mPresenter).Y());
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public void doOtherWithSeparationItemLayout(p.a aVar, p.a.C0288a c0288a, MallFloorModuleCommon mallFloorModuleCommon, int i2) {
        if (i2 != 0) {
            ViewGroup.LayoutParams layoutParams = mallFloorModuleCommon.getLayoutParams();
            f.n(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.setMargins(((q) this.mPresenter).m(), 0, 0, 0);
            mallFloorModuleCommon.setLayoutParams(layoutParams2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public void floorDisplayInScreen(boolean z) {
        List<com.jingdong.app.mall.home.r.e.f> R;
        super.floorDisplayInScreen(z);
        if (z && (R = ((q) this.mPresenter).R()) != null && R.size() > 0) {
            for (com.jingdong.app.mall.home.r.e.f fVar : R) {
                if (fVar != null) {
                    String n2 = fVar.n();
                    if (mExpoSalUrlMap.get(n2) == null) {
                        postUrl(n2);
                        mExpoSalUrlMap.put(n2, Boolean.TRUE);
                    }
                }
            }
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public Object initFloorViewItemOnMainThread(com.jingdong.app.mall.home.r.e.f fVar, int i2, int i3, int i4, Object obj) {
        super.initFloorViewItemOnMainThread(fVar, i2, i3, i4, obj);
        DarkWhiteBgImageView imgViewByCache = getImgViewByCache(this, r.MODULE_BIG_IMG.getSaveKey(i4), i2, i3);
        imgViewByCache.setContentDescription(this.mContext.getString(R.string.home_obstacle_free));
        imgViewByCache.setScaleType(((q) this.mPresenter).X());
        Object itemPosAndAddItem = setItemPosAndAddItem(imgViewByCache, fVar, i4, i2, obj);
        if (itemPosAndAddItem == null) {
            return null;
        }
        com.jingdong.app.mall.home.floor.ctrl.e.e(imgViewByCache, fVar.u(), true);
        return itemPosAndAddItem;
    }

    public Object initMixedFloorViewItem(d dVar, com.jingdong.app.mall.home.r.e.f fVar, int i2, int i3, int i4, Object obj) {
        if (unUsePostWaitThread()) {
            initMixedFloorViewItemOnMainThread(dVar, fVar, i2, i3, i4, obj);
            return null;
        }
        Class cls = Integer.TYPE;
        postToWaitMainThread("initMixedFloorViewItemOnMainThread", new Class[]{d.class, com.jingdong.app.mall.home.r.e.f.class, cls, cls, cls, Object.class}, dVar, fVar, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), obj);
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3 */
    public Object initMixedFloorViewItemOnMainThread(d dVar, com.jingdong.app.mall.home.r.e.f fVar, int i2, int i3, int i4, Object obj) {
        int saveKey = r.MODULE_MIX.getSaveKey(i4);
        MallFloorModuleCommon mallFloorModuleCommon = this.mModelViewCache.get(saveKey);
        MallFloorModuleMixed mallFloorModuleMixed = mallFloorModuleCommon instanceof MallFloorModuleMixed ? (MallFloorModuleMixed) mallFloorModuleCommon : 0;
        if (mallFloorModuleMixed == null) {
            mallFloorModuleMixed = new MallFloorModuleMixed(getContext(), this);
            this.mModelViewCache.put(saveKey, mallFloorModuleMixed);
        }
        ViewGroup.LayoutParams layoutParams = mallFloorModuleMixed.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
            layoutParams2.width = i2;
            layoutParams2.height = i3;
        } else {
            mallFloorModuleMixed.setLayoutParams(new RelativeLayout.LayoutParams(i2, i3));
        }
        mallFloorModuleMixed.removeAllViews();
        mallFloorModuleMixed.addItemBackgroundImg(dVar, fVar.u(), mallFloorModuleMixed, true, false);
        mallFloorModuleMixed.addItemMixedShowName(fVar, mallFloorModuleMixed);
        return setItemPosAndAddItem(mallFloorModuleMixed, fVar, i4, i2, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        startLayerTask();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mHandler.removeCallbacks(this.layerTask);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomePause() {
        super.onHomePause();
        removeLayer();
        this.mHandler.removeCallbacks(this.layerTask);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeRefresh() {
        super.onHomeRefresh();
        clearExpoSalUrl();
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeScrollStop(int i2, int i3) {
        super.onHomeScrollStop(i2, i3);
        if (isFloorDisplay()) {
            a.i().c(((q) this.mPresenter).i(), ((q) this.mPresenter).Y());
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeSplashClosed(int i2, int i3) {
        super.onHomeSplashClosed(i2, i3);
        startLayerTask();
    }

    public void onInitViewData() {
        f.u0(new b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLinearLayout.1
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                MallFloorLinearLayout.this.floorDisplayInScreen(MallFloorLinearLayout.this.isFloorDisplay());
            }
        });
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onParseEnd() {
        super.onParseEnd();
        ((q) this.mPresenter).Z(this.itemCount);
        if (this.itemsWidths == null) {
            return;
        }
        ((q) this.mPresenter).c0(this.itemWeightCount);
        ((q) this.mPresenter).d0(this.itemsWidths);
    }

    public void setItemPadding(int i2) {
        ((q) this.mPresenter).b0(i2);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor
    public Object setItemPosAndAddItem(View view, com.jingdong.app.mall.home.r.e.f fVar, int i2, int i3, Object obj) {
        if (obj instanceof Integer) {
            int m2 = ((q) this.mPresenter).m();
            boolean z = i2 == 0;
            int i4 = R.id.mallfloor_item1 + i2;
            setOnClickJsonListener(view, fVar, 0);
            if (!z) {
                int i5 = i4 - 1;
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                    layoutParams2.addRule(1, i5);
                    View findViewById = findViewById(i5);
                    layoutParams2.setMargins(m2, 0, 0, 0);
                    view.setLayoutParams(layoutParams2);
                    checkCircularDependencies(findViewById, i4);
                }
            }
            view.setId(i4);
            m.L(this, view, i2);
            return Integer.valueOf(i4);
        }
        return null;
    }

    public void startLayerTask() {
        SimpleDraweeView simpleDraweeView;
        if (i.i() || (simpleDraweeView = this.mLayerBg) == null || simpleDraweeView.getParent() == null) {
            return;
        }
        this.mHandler.removeCallbacks(this.layerTask);
        this.mHandler.postDelayed(this.layerTask, ((q) this.mPresenter).V() * 1000);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public boolean useBgMarginColor() {
        return ((q) this.mPresenter).g0();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public q createPresenter() {
        return new q(LinearFloorEntity.class, LinearFloorEngine.class);
    }

    public MallFloorLinearLayout(Context context, int i2) {
        this(context, i2, null);
    }

    public MallFloorLinearLayout(Context context, int i2, ArrayList<Integer> arrayList) {
        this(context, i2, arrayList, 0);
    }

    public MallFloorLinearLayout(Context context, int i2, ArrayList<Integer> arrayList, int i3) {
        super(context);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.layerTask = new b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLinearLayout.3
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                MallFloorLinearLayout.this.mLayerAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
                MallFloorLinearLayout.this.mLayerAnimator.setDuration(1000L);
                MallFloorLinearLayout.this.mLayerAnimator.setInterpolator(new LinearInterpolator());
                MallFloorLinearLayout.this.mLayerAnimator.addUpdateListener(MallFloorLinearLayout.this.layerUpdateListener);
                MallFloorLinearLayout.this.mLayerAnimator.addListener(MallFloorLinearLayout.this.layerAdapter);
                MallFloorLinearLayout.this.mLayerAnimator.start();
            }
        };
        this.layerUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLinearLayout.4
            int secondStartWidth = 0;

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (floatValue < 0.5f) {
                    int width = (int) (MallFloorLinearLayout.this.getWidth() - ((MallFloorLinearLayout.this.getWidth() * 0.37f) * (floatValue / 0.5f)));
                    this.secondStartWidth = width;
                    MallFloorLinearLayout.this.mLayerBg.getLayoutParams().width = width;
                } else {
                    float f2 = (floatValue - 0.5f) / 0.5f;
                    int i22 = this.secondStartWidth;
                    int height = (int) (i22 - ((i22 - (MallFloorLinearLayout.this.getHeight() * 0.8f)) * f2));
                    MallFloorLinearLayout.this.mLayerBg.getLayoutParams().width = height;
                    MallFloorLinearLayout.this.mLayerBg.getLayoutParams().height = (int) (MallFloorLinearLayout.this.getHeight() - ((MallFloorLinearLayout.this.getHeight() * 0.2f) * f2));
                    MallFloorLinearLayout.this.mLayerBg.setAlpha(((1.0f - f2) * 0.7f) + 0.3f);
                    e.d(MallFloorLinearLayout.this.mLayerBg, (int) ((MallFloorLinearLayout.this.getHeight() / 2) * f2));
                }
                MallFloorLinearLayout.this.mLayerBg.requestLayout();
            }
        };
        this.layerAdapter = new AnimatorListenerAdapter() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorLinearLayout.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                MallFloorLinearLayout.this.removeLayer();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
            }
        };
        this.itemCount = i2;
        this.itemsWidths = arrayList;
        this.itemWeightCount = i3;
    }
}
