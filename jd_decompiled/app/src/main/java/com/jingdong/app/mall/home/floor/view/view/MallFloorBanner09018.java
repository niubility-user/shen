package com.jingdong.app.mall.home.floor.view.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.deploy.view.layout.widget.SkuLabel;
import com.jingdong.app.mall.home.floor.common.MallFloorEvent;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.r.f.a.m;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class MallFloorBanner09018 extends BaseMallFloor<m> implements IMallFloorUI {
    private final f leftSize;
    private LRView leftView;
    private final f midSize;
    private MidView midView;
    private final f rightSize;
    private LRView rightView;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class AnimSku extends RelativeLayout {
        private int index;
        private final f labelSize;
        private SimpleDraweeView skuIv;
        private SkuLabel skuLabel;
        private final f skuSize;

        public AnimSku(Context context) {
            super(context);
            this.skuSize = new f(110, 110);
            this.labelSize = new f(-1, 30);
        }

        private void doScale(float f2) {
            setScaleX(f2);
            setScaleY(f2);
        }

        public void bind(SkuInfo skuInfo, int i2) {
            this.index = i2;
            if (skuInfo != null && !TextUtils.isEmpty(skuInfo.skuImgUrl)) {
                setVisibility(0);
                SimpleDraweeView simpleDraweeView = this.skuIv;
                if (simpleDraweeView == null) {
                    HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                    this.skuIv = homeDraweeView;
                    homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                    this.skuIv.setBackgroundColor(-1);
                    RelativeLayout.LayoutParams u = this.skuSize.u(this.skuIv);
                    u.addRule(14);
                    addView(this.skuIv, u);
                } else {
                    f.c(simpleDraweeView, this.skuSize);
                }
                e.u(this.skuIv, skuInfo.skuImgUrl);
                SkuLabel skuLabel = this.skuLabel;
                if (skuLabel == null) {
                    SkuLabel skuLabel2 = new SkuLabel(getContext());
                    this.skuLabel = skuLabel2;
                    RelativeLayout.LayoutParams u2 = this.labelSize.u(skuLabel2);
                    u2.addRule(12);
                    u2.addRule(14);
                    addView(this.skuLabel, u2);
                } else {
                    f.c(skuLabel, this.labelSize);
                }
                this.skuLabel.f(skuInfo.labelInfo);
                return;
            }
            setVisibility(8);
        }

        public void doFadingIn(int i2) {
            if (i2 >= 100) {
                float f2 = i2 - 100;
                setAlpha(f2 / 400.0f);
                doScale(((f2 * 0.3f) / 400.0f) + 0.7f);
            }
        }

        public void doFadingOut(int i2) {
            if (i2 < 400) {
                setAlpha((400 - i2) / 400.0f);
                doScale(1.0f - ((i2 * 0.3f) / 400));
            }
            if (i2 < 400 || i2 >= 450) {
                return;
            }
            setAlpha(0.0f);
            doScale(0.7f);
        }

        public int getIndex() {
            return this.index;
        }

        public void reset(float f2, float f3) {
            setAlpha(f2);
            doScale(f3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class LRView extends RelativeLayout {
        private SimpleDraweeView bgIv;
        private final f bgSize;
        private final f containerSize;
        private boolean isLeft;
        private GradientTextView label;
        private final f labelSize;
        private GradientTextView mainTitle;
        private final f mainTitleSize;
        private m presenter;
        private SimpleDraweeView skuIv;
        private final f skuSize;
        private RelativeLayout textContainer;

        public LRView(Context context) {
            super(context);
            this.bgSize = new f(-1, -1);
            this.skuSize = new f(104, 104);
            this.containerSize = new f(150, -1);
            this.mainTitleSize = new f(-2, 80);
            this.labelSize = new f(-2, 44);
        }

        private void bindBg() {
            SimpleDraweeView simpleDraweeView = this.bgIv;
            if (simpleDraweeView == null) {
                HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                this.bgIv = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                addView(this.bgIv, this.bgSize.u(this.bgIv));
            } else {
                f.c(simpleDraweeView, this.bgSize);
            }
            setBackgroundColor(e.a);
            e.p(this.bgIv, this.presenter.S(this.isLeft), e.b, new e.b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner09018.LRView.2
                @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
                public void onFailed(String str, View view) {
                }

                @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
                public void onStart(String str, View view) {
                }

                @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
                public void onSuccess(String str, View view) {
                    LRView.this.setBackgroundColor(0);
                }
            });
        }

        private void bindLabel() {
            f fVar = this.containerSize;
            boolean z = this.isLeft;
            fVar.E(z ? 24 : 0, 0, z ? 0 : 24, 0);
            RelativeLayout relativeLayout = this.textContainer;
            if (relativeLayout == null) {
                RelativeLayout relativeLayout2 = new RelativeLayout(getContext());
                this.textContainer = relativeLayout2;
                RelativeLayout.LayoutParams u = this.containerSize.u(relativeLayout2);
                u.addRule(this.isLeft ? 9 : 11);
                addView(this.textContainer, u);
            } else {
                f.c(relativeLayout, this.containerSize);
            }
            GradientTextView gradientTextView = this.label;
            if (gradientTextView == null) {
                GradientTextView gradientTextView2 = new GradientTextView(getContext());
                this.label = gradientTextView2;
                gradientTextView2.getPaint().setFakeBoldText(true);
                this.label.setSingleLine();
                this.label.setGravity(16);
                this.label.setBackgroundColor(0);
                this.label.setTypeface(FontsUtil.getTypeFace(getContext()));
                this.label.setPadding(0, -3, 0, -3);
                RelativeLayout.LayoutParams u2 = this.labelSize.u(this.label);
                u2.addRule(14);
                u2.addRule(12);
                this.textContainer.addView(this.label, u2);
            } else {
                f.c(gradientTextView, this.labelSize);
            }
            g.o(this.label, 24);
            this.label.setTextGradient(GradientTextView.GradientType.LeftToRight, com.jingdong.app.mall.home.floor.common.i.m.p(this.presenter.W(this.isLeft), -1, true));
            this.label.setText(this.presenter.X(this.isLeft));
        }

        private void bindMainTitle() {
            f fVar = this.containerSize;
            boolean z = this.isLeft;
            fVar.E(z ? 24 : 0, 0, z ? 0 : 24, 0);
            RelativeLayout relativeLayout = this.textContainer;
            if (relativeLayout == null) {
                RelativeLayout relativeLayout2 = new RelativeLayout(getContext());
                this.textContainer = relativeLayout2;
                RelativeLayout.LayoutParams u = this.containerSize.u(relativeLayout2);
                u.addRule(this.isLeft ? 9 : 11);
                addView(this.textContainer, u);
            } else {
                f.c(relativeLayout, this.containerSize);
            }
            GradientTextView gradientTextView = this.mainTitle;
            if (gradientTextView == null) {
                GradientTextView gradientTextView2 = new GradientTextView(getContext());
                this.mainTitle = gradientTextView2;
                gradientTextView2.getPaint().setFakeBoldText(true);
                this.mainTitle.setSingleLine();
                this.mainTitle.setGravity(16);
                this.mainTitle.setBackgroundColor(0);
                this.mainTitle.setPadding(0, -3, 0, -3);
                this.mainTitle.setTypeface(FontsUtil.getTypeFace(getContext()));
                RelativeLayout.LayoutParams u2 = this.mainTitleSize.u(this.mainTitle);
                u2.addRule(14);
                this.textContainer.addView(this.mainTitle, u2);
            } else {
                f.c(gradientTextView, this.mainTitleSize);
            }
            g.o(this.mainTitle, 22);
            this.mainTitle.setTextGradient(GradientTextView.GradientType.LeftToRight, com.jingdong.app.mall.home.floor.common.i.m.p(this.presenter.U(this.isLeft), -1, true));
            this.mainTitle.setText(this.presenter.T(this.isLeft));
        }

        private void bindSku() {
            f fVar = this.skuSize;
            boolean z = this.isLeft;
            fVar.E(z ? 47 : 0, 55, z ? 0 : 47, 0);
            SimpleDraweeView simpleDraweeView = this.skuIv;
            if (simpleDraweeView == null) {
                HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                this.skuIv = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                RelativeLayout.LayoutParams u = this.skuSize.u(this.skuIv);
                u.addRule(this.isLeft ? 9 : 11);
                addView(this.skuIv, u);
            } else {
                f.c(simpleDraweeView, this.skuSize);
            }
            this.skuIv.setBackgroundColor(-1);
            com.jingdong.app.mall.home.n.h.e.d(this.skuIv, d.d(6));
            e.l(this.presenter.V(this.isLeft), this.skuIv);
        }

        public void bind(final boolean z, final m mVar) {
            this.isLeft = z;
            this.presenter = mVar;
            setContentDescription(getContext().getString(R.string.home_obstacle_free));
            bindSku();
            bindBg();
            bindMainTitle();
            bindLabel();
            setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner09018.LRView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    mVar.d0(LRView.this, z ? 0 : 2, 0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class MidView extends RelativeLayout {
        private final Runnable animationRunnable;
        private ValueAnimator animator;
        private SimpleDraweeView bgIv;
        private final f bgSize;
        private final Handler handler;
        private final AtomicBoolean isPause;
        private SkuAnimContainer leftSkuContainer;
        private m presenter;
        private SkuAnimContainer rightSkuContainer;
        private final f skuContainerSize;
        private View whiteBg;
        private final f whiteBgSize;

        public MidView(Context context) {
            super(context);
            this.whiteBgSize = new f(R2.attr.actionbar_icon_dark_back, R2.anim.mtrl_bottom_sheet_slide_in);
            this.skuContainerSize = new f(-2, -2);
            this.bgSize = new f(-1, -1);
            this.isPause = new AtomicBoolean(false);
            this.handler = new Handler(Looper.getMainLooper());
            this.animationRunnable = new Runnable() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner09018.MidView.5
                @Override // java.lang.Runnable
                public void run() {
                    MidView.this.checkAndStartAnim();
                }
            };
        }

        private void bindAnimSku() {
            this.skuContainerSize.E(46, 56, 0, 0);
            SkuAnimContainer skuAnimContainer = this.leftSkuContainer;
            if (skuAnimContainer == null) {
                SkuAnimContainer skuAnimContainer2 = new SkuAnimContainer(getContext());
                this.leftSkuContainer = skuAnimContainer2;
                addView(this.leftSkuContainer, this.skuContainerSize.u(skuAnimContainer2));
            } else {
                f.c(skuAnimContainer, this.skuContainerSize);
            }
            this.leftSkuContainer.bindSku(this.presenter, true);
            this.skuContainerSize.E(0, 56, 46, 0);
            SkuAnimContainer skuAnimContainer3 = this.rightSkuContainer;
            if (skuAnimContainer3 == null) {
                SkuAnimContainer skuAnimContainer4 = new SkuAnimContainer(getContext());
                this.rightSkuContainer = skuAnimContainer4;
                RelativeLayout.LayoutParams u = this.skuContainerSize.u(skuAnimContainer4);
                u.addRule(11);
                addView(this.rightSkuContainer, u);
            } else {
                f.c(skuAnimContainer3, this.skuContainerSize);
            }
            this.rightSkuContainer.bindSku(this.presenter, false);
        }

        private void bindBg() {
            SimpleDraweeView simpleDraweeView = this.bgIv;
            if (simpleDraweeView == null) {
                HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
                this.bgIv = homeDraweeView;
                homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                addView(this.bgIv, this.bgSize.u(this.bgIv));
            } else {
                f.c(simpleDraweeView, this.bgSize);
            }
            setBackgroundColor(e.a);
            e.p(this.bgIv, this.presenter.Y(), e.b, new e.b() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner09018.MidView.2
                @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
                public void onFailed(String str, View view) {
                }

                @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
                public void onStart(String str, View view) {
                }

                @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
                public void onSuccess(String str, View view) {
                    MidView.this.setBackgroundColor(0);
                }
            });
        }

        private void bindWhiteBg() {
            this.whiteBgSize.E(0, 48, 0, 0);
            if (this.whiteBg == null) {
                View view = new View(getContext());
                this.whiteBg = view;
                RelativeLayout.LayoutParams u = this.whiteBgSize.u(view);
                u.addRule(14);
                addView(this.whiteBg, u);
            }
            this.whiteBg.setBackgroundColor(-1);
        }

        private void cancelAnimation() {
            this.handler.removeCallbacksAndMessages(null);
            ValueAnimator valueAnimator = this.animator;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                return;
            }
            this.animator.cancel();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void checkAndStartAnim() {
            this.handler.removeCallbacksAndMessages(null);
            if (this.presenter.P()) {
                this.animator.start();
            }
        }

        private void startAnimation() {
            if (this.animator == null) {
                ValueAnimator ofInt = ValueAnimator.ofInt(0, 500);
                this.animator = ofInt;
                ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner09018.MidView.3
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                        if (MidView.this.leftSkuContainer != null) {
                            MidView.this.leftSkuContainer.onAnimate(intValue);
                        }
                        if (MidView.this.rightSkuContainer != null) {
                            MidView.this.rightSkuContainer.onAnimate(intValue);
                        }
                    }
                });
                this.animator.addListener(new com.jingdong.app.mall.home.r.a.d() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner09018.MidView.4
                    long startTime;

                    @Override // com.jingdong.app.mall.home.r.a.d, android.animation.Animator.AnimatorListener
                    public void onAnimationStart(Animator animator) {
                        this.startTime = SystemClock.elapsedRealtime();
                        super.onAnimationStart(animator);
                        if (MidView.this.leftSkuContainer != null) {
                            MidView.this.leftSkuContainer.onSkuAnimationStart();
                        }
                        if (MidView.this.rightSkuContainer != null) {
                            MidView.this.rightSkuContainer.onSkuAnimationStart();
                        }
                    }

                    @Override // com.jingdong.app.mall.home.r.a.d
                    protected void onEnd(Animator animator, boolean z) {
                        if (MidView.this.leftSkuContainer != null) {
                            MidView.this.leftSkuContainer.onSkuAnimationEnd(z);
                        }
                        if (MidView.this.rightSkuContainer != null) {
                            MidView.this.rightSkuContainer.onSkuAnimationEnd(z);
                        }
                        if (z) {
                            return;
                        }
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        if (MidView.this.isPause.get() || elapsedRealtime - this.startTime < 400) {
                            return;
                        }
                        MidView.this.handler.postDelayed(MidView.this.animationRunnable, MidView.this.presenter.R());
                    }
                });
                this.animator.setDuration(500L);
                this.animator.setInterpolator(new LinearInterpolator());
            }
            this.handler.postDelayed(this.animationRunnable, this.presenter.R());
        }

        public void bind(final m mVar) {
            this.presenter = mVar;
            cancelAnimation();
            bindWhiteBg();
            bindAnimSku();
            bindBg();
            startAnimation();
            setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner09018.MidView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    mVar.d0(MidView.this, 1, 0);
                }
            });
        }

        public void onHomePause() {
            this.isPause.set(true);
            cancelAnimation();
        }

        public void onHomeResume() {
            this.isPause.set(false);
            if (this.animator.isRunning()) {
                return;
            }
            this.handler.removeCallbacksAndMessages(null);
            this.handler.postDelayed(this.animationRunnable, this.presenter.R());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class SkuAnimContainer extends RelativeLayout {
        private int currentAnimTime;
        private AnimSku skuBackground;
        private final AnimSku skuBottom;
        private AnimSku skuFront;
        final f skuSize;
        private final AnimSku skuTop;

        public SkuAnimContainer(Context context) {
            super(context);
            f fVar = new f(R2.anim.lib_cashier_sdk_pop_out_animation_bottom, R2.anim.lib_cashier_sdk_fragment_right_out);
            this.skuSize = fVar;
            AnimSku animSku = new AnimSku(context);
            this.skuBottom = animSku;
            addView(animSku, fVar.u(animSku));
            animSku.reset(0.0f, 1.0f);
            this.skuBackground = animSku;
            AnimSku animSku2 = new AnimSku(context);
            this.skuTop = animSku2;
            addView(animSku2, fVar.u(animSku2));
            this.skuFront = animSku2;
        }

        private void setInitAndFinalAnimState() {
            AnimSku animSku = this.skuFront;
            if (animSku != null) {
                animSku.reset(1.0f, 1.0f);
            }
            AnimSku animSku2 = this.skuBackground;
            if (animSku2 != null) {
                animSku2.reset(0.0f, 0.7f);
            }
            this.currentAnimTime = 0;
        }

        private void swapSku() {
            AnimSku animSku = this.skuFront;
            this.skuFront = this.skuBackground;
            this.skuBackground = animSku;
        }

        public void bindSku(final m mVar, boolean z) {
            f.c(this.skuTop, this.skuSize);
            f.c(this.skuBackground, this.skuSize);
            AnimSku animSku = this.skuTop;
            this.skuFront = animSku;
            this.skuBackground = this.skuBottom;
            animSku.reset(1.0f, 1.0f);
            this.skuBackground.reset(0.0f, 1.0f);
            this.skuFront.bind(z ? mVar.Z() : mVar.a0(), z ? 1 : 2);
            this.skuBackground.bind(z ? mVar.b0() : mVar.c0(), z ? 3 : 4);
            setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.MallFloorBanner09018.SkuAnimContainer.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    m mVar2 = mVar;
                    SkuAnimContainer skuAnimContainer = SkuAnimContainer.this;
                    mVar2.d0(skuAnimContainer, 1, skuAnimContainer.getItemIndex());
                }
            });
        }

        public int getItemIndex() {
            return (this.currentAnimTime < 250 ? this.skuFront : this.skuBackground).getIndex();
        }

        public void onAnimate(int i2) {
            this.currentAnimTime = i2;
            AnimSku animSku = this.skuFront;
            if (animSku != null) {
                animSku.doFadingOut(i2);
            }
            AnimSku animSku2 = this.skuBackground;
            if (animSku2 != null) {
                animSku2.doFadingIn(i2);
            }
        }

        public void onSkuAnimationEnd(boolean z) {
            if (!z) {
                swapSku();
            }
            setInitAndFinalAnimState();
        }

        public void onSkuAnimationStart() {
            setInitAndFinalAnimState();
        }
    }

    /* loaded from: classes4.dex */
    public static class SkuInfo {
        public SkuLabel.Info labelInfo;
        public String skuImgUrl;
    }

    public MallFloorBanner09018(Context context) {
        super(context);
        this.leftSize = new f(-1, -1);
        this.midSize = new f(R2.attr.animateRelativeTo, -1);
        this.rightSize = new f(-1, -1);
    }

    private void bindLeft() {
        LRView lRView = this.leftView;
        if (lRView == null) {
            LRView lRView2 = new LRView(getContext());
            this.leftView = lRView2;
            RelativeLayout.LayoutParams u = this.leftSize.u(lRView2);
            u.addRule(0, this.midView.getId());
            addView(this.leftView, u);
        } else {
            f.c(lRView, this.leftSize);
        }
        this.leftView.bind(true, (m) this.mPresenter);
    }

    private void bindMiddle() {
        MidView midView = this.midView;
        if (midView == null) {
            MidView midView2 = new MidView(getContext());
            this.midView = midView2;
            midView2.setId(R.id.mallfloor_item1);
            RelativeLayout.LayoutParams u = this.midSize.u(this.midView);
            u.addRule(14);
            addView(this.midView, u);
        } else {
            f.c(midView, this.midSize);
        }
        this.midView.bind((m) this.mPresenter);
    }

    private void bindRight() {
        LRView lRView = this.rightView;
        if (lRView == null) {
            LRView lRView2 = new LRView(getContext());
            this.rightView = lRView2;
            RelativeLayout.LayoutParams u = this.rightSize.u(lRView2);
            u.addRule(1, this.midView.getId());
            addView(this.rightView, u);
        } else {
            f.c(lRView, this.rightSize);
        }
        this.rightView.bind(false, (m) this.mPresenter);
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomePause() {
        super.onHomePause();
        MidView midView = this.midView;
        if (midView != null) {
            midView.onHomePause();
        }
    }

    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor, com.jingdong.app.mall.home.floor.view.baseui.IMallFloorUI
    public void onHomeResume(MallFloorEvent mallFloorEvent) {
        super.onHomeResume(mallFloorEvent);
        MidView midView = this.midView;
        if (midView != null) {
            midView.onHomeResume();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public void onRefreshViewOnMainThread() {
        super.onRefreshViewOnMainThread();
        bindMiddle();
        bindLeft();
        bindRight();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.baseui.BaseMallColorFloor
    public m createPresenter() {
        return new m();
    }
}
