package com.jingdong.app.mall.home.floor.view.view.subitem;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.model.entity.BubbleBannerEntity;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.floor.view.widget.TimeFormatView;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.r.e.f;
import com.jingdong.app.mall.home.r.e.k.a;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.lang.reflect.Array;

/* loaded from: classes4.dex */
public class BubbleBannerMiddleLayout extends RelativeLayout {
    private f currentElement;
    private boolean downSkuNeedReset;
    private SimpleDraweeView mBgView;
    private GradientTextView mBottomText;
    private Context mContext;
    private LinearLayout mDownSkuParent;
    private Handler mHandler;
    private com.jingdong.app.mall.home.floor.common.f mLeftSize;
    private a mMiddleItem;
    private com.jingdong.app.mall.home.floor.common.f mRightSize;
    private RelativeLayout mSkuFlipper;
    private com.jingdong.app.mall.home.floor.common.f mSkuFlipperSize;
    private SimpleDraweeView[][] mSkuLists;
    private ValueAnimator mSkuTranslateAnim;
    private RelativeLayout mTimeLayout;
    private com.jingdong.app.mall.home.floor.common.f mTimeSize;
    private TimeFormatView mTimeView;
    private com.jingdong.app.mall.home.floor.common.f mTimeViewSize;
    private com.jingdong.app.mall.home.floor.common.f mTitleSize;
    private GradientTextView mTopText;
    private b mTranslateRunnable;
    private LinearLayout mUpSkuParent;
    private View mWhiteBg;
    private GradientDrawable mWhiteDrawable;
    private com.jingdong.app.mall.home.floor.common.f mWhiteSize;
    private int offset;
    private com.jingdong.app.mall.home.floor.common.f skuParentSize;
    private boolean tranAnimStop;

    public BubbleBannerMiddleLayout(Context context) {
        super(context);
        this.mSkuLists = (HomeDraweeView[][]) Array.newInstance(HomeDraweeView.class, 2, 2);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mTranslateRunnable = new b() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleBannerMiddleLayout.7
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                BubbleBannerMiddleLayout.this.mSkuTranslateAnim.start();
            }
        };
        this.mContext = context;
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleBannerMiddleLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BubbleBannerMiddleLayout.this.onItemClick(0);
            }
        });
    }

    private void beforeSkuTranslate() {
        createTransAnim(d.d(this.offset));
        this.downSkuNeedReset = false;
        this.mDownSkuParent.setTranslationY(d.d(this.offset));
        this.mUpSkuParent.setTranslationY(0.0f);
    }

    private void bindImage(f fVar, a aVar) {
        boolean z = fVar.t() == 0;
        String u = z ? fVar.u() : fVar.G();
        setBackgroundColor(e.a);
        e.p(this.mBgView, u, e.b, new e.b() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleBannerMiddleLayout.2
            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onFailed(String str, View view) {
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onStart(String str, View view) {
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onSuccess(String str, View view) {
                BubbleBannerMiddleLayout.this.setBackgroundColor(0);
            }
        });
        if (z) {
            this.mWhiteBg.setVisibility(8);
            this.mSkuFlipper.setVisibility(8);
            return;
        }
        this.mWhiteBg.setVisibility(0);
        this.mSkuFlipper.setVisibility(0);
        for (int i2 = 0; i2 < 2; i2++) {
            e.l(this.mMiddleItem.g(i2, 0), this.mSkuLists[i2][0]);
            e.l(this.mMiddleItem.g(i2, 1), this.mSkuLists[i2][1]);
        }
    }

    private void bindSku(int i2, LinearLayout linearLayout, final int i3) {
        Rect rect;
        SimpleDraweeView[][] simpleDraweeViewArr = this.mSkuLists;
        if (simpleDraweeViewArr[i3][0] == null) {
            simpleDraweeViewArr[i3][0] = new HomeDraweeView(this.mContext);
            this.mSkuLists[i3][0].setContentDescription(this.mContext.getString(R.string.home_obstacle_free));
            this.mSkuLists[i3][0].setScaleType(ImageView.ScaleType.FIT_XY);
            this.mSkuLists[i3][0].setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleBannerMiddleLayout.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BubbleBannerMiddleLayout.this.onItemClick(i3 == 0 ? 1 : 3);
                }
            });
            if (i2 == BubbleBannerEntity.TYPE_08008) {
                this.mLeftSize = new com.jingdong.app.mall.home.floor.common.f(R2.anim.pop_in, R2.anim.pop_in);
            } else if (i2 == BubbleBannerEntity.TYPE_08005) {
                this.mLeftSize = new com.jingdong.app.mall.home.floor.common.f(140, 140);
            } else if (i2 == BubbleBannerEntity.TYPE_08009) {
                this.mLeftSize = new com.jingdong.app.mall.home.floor.common.f(126, 126);
            } else if (i2 == BubbleBannerEntity.TYPE_08008_V936) {
                this.mLeftSize = new com.jingdong.app.mall.home.floor.common.f(R2.anim.out_to_bottom_slow, R2.anim.out_to_bottom_slow);
            } else {
                this.mLeftSize = new com.jingdong.app.mall.home.floor.common.f(R2.anim.lib_cashier_sdk_fragment_right_out, R2.anim.lib_cashier_sdk_fragment_right_out);
            }
            linearLayout.addView(this.mSkuLists[i3][0], this.mLeftSize.i(this.mSkuLists[i3][0]));
        } else {
            com.jingdong.app.mall.home.floor.common.f.d(simpleDraweeViewArr[i3][0], this.mLeftSize, true);
        }
        SimpleDraweeView[][] simpleDraweeViewArr2 = this.mSkuLists;
        if (simpleDraweeViewArr2[i3][1] == null) {
            simpleDraweeViewArr2[i3][1] = new HomeDraweeView(this.mContext);
            this.mSkuLists[i3][1].setContentDescription(this.mContext.getString(R.string.home_obstacle_free));
            this.mSkuLists[i3][1].setScaleType(ImageView.ScaleType.FIT_XY);
            this.mSkuLists[i3][1].setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleBannerMiddleLayout.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BubbleBannerMiddleLayout.this.onItemClick(i3 == 0 ? 2 : 4);
                }
            });
            if (i2 == BubbleBannerEntity.TYPE_08008) {
                this.mRightSize = new com.jingdong.app.mall.home.floor.common.f(R2.anim.pop_in, R2.anim.pop_in);
            } else if (i2 == BubbleBannerEntity.TYPE_08005) {
                this.mRightSize = new com.jingdong.app.mall.home.floor.common.f(140, 140);
            } else if (i2 == BubbleBannerEntity.TYPE_08009) {
                this.mRightSize = new com.jingdong.app.mall.home.floor.common.f(126, 126);
            } else if (i2 == BubbleBannerEntity.TYPE_08008_V936) {
                this.mRightSize = new com.jingdong.app.mall.home.floor.common.f(R2.anim.out_to_bottom_slow, R2.anim.out_to_bottom_slow);
            } else {
                this.mRightSize = new com.jingdong.app.mall.home.floor.common.f(R2.anim.lib_cashier_sdk_fragment_right_out, R2.anim.lib_cashier_sdk_fragment_right_out);
            }
            com.jingdong.app.mall.home.floor.common.f fVar = this.mRightSize;
            if (i2 == BubbleBannerEntity.TYPE_08008) {
                rect = new Rect(22, 0, 0, 0);
            } else if (i2 == BubbleBannerEntity.TYPE_08005) {
                rect = new Rect(38, 0, 0, 0);
            } else if (i2 == BubbleBannerEntity.TYPE_08009) {
                rect = new Rect(51, 0, 0, 0);
            } else if (i2 == BubbleBannerEntity.TYPE_08008_V936) {
                rect = new Rect(12, 0, 0, 0);
            } else {
                rect = new Rect(40, 0, 0, 0);
            }
            fVar.F(rect);
            linearLayout.addView(this.mSkuLists[i3][1], this.mRightSize.i(this.mSkuLists[i3][1]));
            return;
        }
        com.jingdong.app.mall.home.floor.common.f.d(simpleDraweeViewArr2[i3][1], this.mRightSize, true);
    }

    private void createTransAnim(final int i2) {
        if (this.mSkuTranslateAnim == null) {
            com.jingdong.app.mall.home.n.h.f fVar = new com.jingdong.app.mall.home.n.h.f();
            fVar.b(0.0f, i2);
            fVar.d(400L);
            fVar.e(new AccelerateDecelerateInterpolator());
            fVar.g(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleBannerMiddleLayout.6
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    if (!BubbleBannerMiddleLayout.this.downSkuNeedReset) {
                        float f2 = -floatValue;
                        BubbleBannerMiddleLayout.this.mUpSkuParent.setTranslationY(f2);
                        BubbleBannerMiddleLayout.this.mDownSkuParent.setTranslationY(f2 + i2);
                        return;
                    }
                    float f3 = -floatValue;
                    BubbleBannerMiddleLayout.this.mUpSkuParent.setTranslationY(i2 + f3);
                    BubbleBannerMiddleLayout.this.mDownSkuParent.setTranslationY(f3);
                }
            });
            fVar.c(new AnimatorListenerAdapter() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleBannerMiddleLayout.5
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    super.onAnimationCancel(animator);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    BubbleBannerMiddleLayout.this.downSkuNeedReset = !r2.downSkuNeedReset;
                    BubbleBannerMiddleLayout.this.doTransAnim();
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    super.onAnimationStart(animator);
                    String str = "onAnimationStart" + BubbleBannerMiddleLayout.this.downSkuNeedReset;
                    if (BubbleBannerMiddleLayout.this.downSkuNeedReset) {
                        BubbleBannerMiddleLayout.this.mUpSkuParent.setTranslationY(i2);
                        BubbleBannerMiddleLayout.this.mDownSkuParent.setTranslationY(0.0f);
                        return;
                    }
                    BubbleBannerMiddleLayout.this.mDownSkuParent.setTranslationY(i2);
                    BubbleBannerMiddleLayout.this.mUpSkuParent.setTranslationY(0.0f);
                }
            });
            this.mSkuTranslateAnim = fVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doTransAnim() {
        startFlip(this.mMiddleItem.f());
    }

    private void endFlip() {
        b bVar = this.mTranslateRunnable;
        if (bVar != null) {
            this.mHandler.removeCallbacks(bVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onItemClick(int i2) {
        f fVar;
        JumpEntity jump;
        if (l.k() || (fVar = this.currentElement) == null || (jump = fVar.getJump()) == null) {
            return;
        }
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(jump.srvJson);
        c2.a("skuposition", i2 + "");
        l.onClickJsonEvent(getContext(), jump, "", jump.getSrv(), c2.toString(), i2);
    }

    private void startFlip(long j2) {
        endFlip();
        if (j2 <= 0 || this.mTranslateRunnable == null || this.tranAnimStop || !this.mMiddleItem.m()) {
            return;
        }
        this.mHandler.postDelayed(this.mTranslateRunnable, j2);
    }

    public void beforeTimeStart() {
        this.mTopText.setVisibility(0);
        this.mTimeView.setVisibility(8);
        this.mTopText.setText(this.mMiddleItem.j());
    }

    public void onHomeResume() {
        this.tranAnimStop = false;
        doTransAnim();
    }

    public void onHomeStop() {
        this.tranAnimStop = true;
        endFlip();
    }

    public void onViewBind(f fVar, a aVar, int i2) {
        int i3;
        int i4;
        boolean z;
        int i5;
        this.currentElement = fVar;
        if (fVar == null || aVar == null) {
            return;
        }
        this.mMiddleItem = aVar;
        int l2 = aVar.l();
        if (l2 == BubbleBannerEntity.TYPE_08008) {
            i3 = R2.anim.pop_in;
        } else if (l2 == BubbleBannerEntity.TYPE_08005) {
            i3 = 140;
        } else if (l2 == BubbleBannerEntity.TYPE_08009) {
            i3 = 126;
        } else {
            i3 = l2 == BubbleBannerEntity.TYPE_08008_V936 ? R2.anim.out_to_bottom_slow : R2.anim.lib_cashier_sdk_fragment_right_out;
        }
        this.offset = i3;
        View view = this.mWhiteBg;
        if (view == null) {
            this.mWhiteBg = new View(this.mContext);
            if (l2 == BubbleBannerEntity.TYPE_08005) {
                com.jingdong.app.mall.home.floor.common.f fVar2 = new com.jingdong.app.mall.home.floor.common.f(326, R2.anim.mtrl_bottom_sheet_slide_in);
                this.mWhiteSize = fVar2;
                fVar2.F(new Rect(0, 33, 0, 0));
            } else if (l2 == BubbleBannerEntity.TYPE_08008) {
                com.jingdong.app.mall.home.floor.common.f fVar3 = new com.jingdong.app.mall.home.floor.common.f(R2.attr.alertDialogStyle, 178);
                this.mWhiteSize = fVar3;
                fVar3.F(new Rect(0, 56, 0, 0));
            } else if (l2 == BubbleBannerEntity.TYPE_08009) {
                com.jingdong.app.mall.home.floor.common.f fVar4 = new com.jingdong.app.mall.home.floor.common.f(311, 134);
                this.mWhiteSize = fVar4;
                fVar4.F(new Rect(0, 28, 0, 0));
            } else if (l2 == BubbleBannerEntity.TYPE_08008_V936) {
                com.jingdong.app.mall.home.floor.common.f fVar5 = new com.jingdong.app.mall.home.floor.common.f(R2.attr.actionModeWebSearchDrawable, R2.anim.pop_in);
                this.mWhiteSize = fVar5;
                fVar5.F(new Rect(7, 56, 0, 0));
            } else {
                com.jingdong.app.mall.home.floor.common.f fVar6 = new com.jingdong.app.mall.home.floor.common.f(336, R2.anim.manto_translate_dialog_in);
                this.mWhiteSize = fVar6;
                fVar6.F(new Rect(9, 96, 0, 0));
            }
            RelativeLayout.LayoutParams u = this.mWhiteSize.u(this.mWhiteBg);
            u.addRule(14);
            addView(this.mWhiteBg, u);
            GradientDrawable gradientDrawable = new GradientDrawable();
            this.mWhiteDrawable = gradientDrawable;
            gradientDrawable.setColor(-1);
            this.mWhiteBg.setBackgroundDrawable(this.mWhiteDrawable);
        } else {
            com.jingdong.app.mall.home.floor.common.f.d(view, this.mWhiteSize, true);
        }
        this.mWhiteDrawable.setCornerRadius(d.d(l2 == BubbleBannerEntity.TYPE_08008 ? 24 : 4));
        RelativeLayout relativeLayout = new RelativeLayout(this.mContext);
        addView(relativeLayout, new RelativeLayout.LayoutParams(-1, -1));
        RelativeLayout relativeLayout2 = this.mSkuFlipper;
        if (relativeLayout2 == null) {
            this.mSkuFlipper = new RelativeLayout(this.mContext);
            if (l2 == BubbleBannerEntity.TYPE_08008) {
                com.jingdong.app.mall.home.floor.common.f fVar7 = new com.jingdong.app.mall.home.floor.common.f(R2.attr.actionbar_icon_dark_back, R2.anim.pop_in);
                this.mSkuFlipperSize = fVar7;
                fVar7.F(new Rect(24, 64, 0, 0));
            } else if (l2 == BubbleBannerEntity.TYPE_08005) {
                com.jingdong.app.mall.home.floor.common.f fVar8 = new com.jingdong.app.mall.home.floor.common.f(318, 140);
                this.mSkuFlipperSize = fVar8;
                fVar8.F(new Rect(36, 37, 0, 0));
            } else if (l2 == BubbleBannerEntity.TYPE_08009) {
                com.jingdong.app.mall.home.floor.common.f fVar9 = new com.jingdong.app.mall.home.floor.common.f(303, 126);
                this.mSkuFlipperSize = fVar9;
                fVar9.F(new Rect(43, 32, 0, 0));
            } else if (l2 == BubbleBannerEntity.TYPE_08008_V936) {
                com.jingdong.app.mall.home.floor.common.f fVar10 = new com.jingdong.app.mall.home.floor.common.f(324, R2.anim.out_to_bottom_slow);
                this.mSkuFlipperSize = fVar10;
                fVar10.F(new Rect(17, 60, 0, 0));
            } else {
                com.jingdong.app.mall.home.floor.common.f fVar11 = new com.jingdong.app.mall.home.floor.common.f(312, R2.anim.lib_cashier_sdk_fragment_right_out);
                this.mSkuFlipperSize = fVar11;
                fVar11.F(new Rect(21, 99, 0, 0));
            }
            RelativeLayout relativeLayout3 = this.mSkuFlipper;
            relativeLayout.addView(relativeLayout3, this.mSkuFlipperSize.u(relativeLayout3));
        } else {
            com.jingdong.app.mall.home.floor.common.f.c(relativeLayout2, this.mSkuFlipperSize);
        }
        LinearLayout linearLayout = this.mUpSkuParent;
        if (linearLayout == null) {
            LinearLayout linearLayout2 = new LinearLayout(this.mContext);
            this.mUpSkuParent = linearLayout2;
            linearLayout2.setOrientation(0);
            com.jingdong.app.mall.home.floor.common.f fVar12 = new com.jingdong.app.mall.home.floor.common.f(-1, -1);
            this.skuParentSize = fVar12;
            LinearLayout.LayoutParams i6 = fVar12.i(this.mUpSkuParent);
            i6.gravity = 1;
            this.mSkuFlipper.addView(this.mUpSkuParent, i6);
        } else {
            com.jingdong.app.mall.home.floor.common.f.d(linearLayout, this.skuParentSize, true);
        }
        bindSku(l2, this.mUpSkuParent, 0);
        LinearLayout linearLayout3 = this.mDownSkuParent;
        if (linearLayout3 == null) {
            LinearLayout linearLayout4 = new LinearLayout(this.mContext);
            this.mDownSkuParent = linearLayout4;
            linearLayout4.setOrientation(0);
            com.jingdong.app.mall.home.floor.common.f fVar13 = new com.jingdong.app.mall.home.floor.common.f(-1, -1);
            this.skuParentSize = fVar13;
            LinearLayout.LayoutParams i7 = fVar13.i(this.mDownSkuParent);
            i4 = 1;
            i7.gravity = 1;
            this.mSkuFlipper.addView(this.mDownSkuParent, i7);
        } else {
            i4 = 1;
            com.jingdong.app.mall.home.floor.common.f.d(linearLayout3, this.skuParentSize, true);
        }
        bindSku(l2, this.mDownSkuParent, i4);
        beforeSkuTranslate();
        doTransAnim();
        if (this.mBgView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(this.mContext);
            this.mBgView = homeDraweeView;
            homeDraweeView.setContentDescription(this.mContext.getString(R.string.home_obstacle_free));
            this.mBgView.setScaleType(ImageView.ScaleType.FIT_XY);
            relativeLayout.addView(this.mBgView, new RelativeLayout.LayoutParams(-1, -1));
        }
        RelativeLayout relativeLayout4 = this.mTimeLayout;
        if (relativeLayout4 == null) {
            this.mTimeLayout = new RelativeLayout(this.mContext);
            if (l2 == BubbleBannerEntity.TYPE_08008) {
                this.mTimeSize = new com.jingdong.app.mall.home.floor.common.f(300, 64);
            } else if (l2 == BubbleBannerEntity.TYPE_08005) {
                this.mTimeSize = new com.jingdong.app.mall.home.floor.common.f(100, 42);
            } else if (l2 == BubbleBannerEntity.TYPE_08009) {
                this.mTimeSize = new com.jingdong.app.mall.home.floor.common.f(100, 36);
            } else if (l2 == BubbleBannerEntity.TYPE_08008_V936) {
                this.mTimeSize = new com.jingdong.app.mall.home.floor.common.f(R2.anim.settlement_dialog_right_exit, 60);
            } else {
                this.mTimeSize = new com.jingdong.app.mall.home.floor.common.f(R2.anim.settlement_dialog_right_exit, 50);
            }
            RelativeLayout.LayoutParams u2 = this.mTimeSize.u(this.mTimeLayout);
            u2.addRule(14);
            addView(this.mTimeLayout, u2);
            z = true;
        } else {
            z = true;
            com.jingdong.app.mall.home.floor.common.f.d(relativeLayout4, this.mTimeSize, true);
        }
        if (this.mTopText == null) {
            GradientTextView gradientTextView = new GradientTextView(this.mContext);
            this.mTopText = gradientTextView;
            com.jingdong.app.mall.home.floor.common.f.y(gradientTextView, z);
            this.mTopText.setGravity(15);
            this.mTopText.setTextColor(-1);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            this.mTimeLayout.addView(this.mTopText, layoutParams);
        }
        com.jingdong.app.mall.home.floor.common.f.O(this.mTopText, l2 == BubbleBannerEntity.TYPE_08008 ? 28 : l2 == BubbleBannerEntity.TYPE_08008_V936 ? 30 : 24);
        this.mTopText.setMaxWidth(this.mTimeSize.v());
        TimeFormatView timeFormatView = this.mTimeView;
        if (timeFormatView == null) {
            TimeFormatView timeFormatView2 = new TimeFormatView(getContext());
            this.mTimeView = timeFormatView2;
            timeFormatView2.n(FontsUtil.getTypeFace(this.mContext));
            com.jingdong.app.mall.home.floor.common.f fVar14 = new com.jingdong.app.mall.home.floor.common.f((l2 == BubbleBannerEntity.TYPE_08008_V936 || l2 == BubbleBannerEntity.TYPE_08008) ? 120 : 100, -1);
            this.mTimeViewSize = fVar14;
            RelativeLayout.LayoutParams u3 = fVar14.u(this.mTimeView);
            u3.addRule(13);
            this.mTimeLayout.addView(this.mTimeView, u3);
        } else {
            com.jingdong.app.mall.home.floor.common.f.d(timeFormatView, this.mTimeViewSize, true);
        }
        if (l2 == BubbleBannerEntity.TYPE_08008) {
            this.mTimeView.j(4);
            this.mTimeView.n(FontsUtil.getTypeFace(this.mContext, 4097));
        }
        GradientTextView gradientTextView2 = this.mBottomText;
        if (gradientTextView2 == null) {
            GradientTextView gradientTextView3 = new GradientTextView(this.mContext);
            this.mBottomText = gradientTextView3;
            com.jingdong.app.mall.home.floor.common.f.y(gradientTextView3, true);
            this.mBottomText.setGravity(l2 != BubbleBannerEntity.TYPE_08008_V936 ? 16 : 17);
            if (l2 == BubbleBannerEntity.TYPE_08008) {
                com.jingdong.app.mall.home.floor.common.f fVar15 = new com.jingdong.app.mall.home.floor.common.f(-2, 54);
                this.mTitleSize = fVar15;
                fVar15.J(0, 0, 0, 0);
            } else if (l2 == BubbleBannerEntity.TYPE_08005) {
                com.jingdong.app.mall.home.floor.common.f fVar16 = new com.jingdong.app.mall.home.floor.common.f(-2, 62);
                this.mTitleSize = fVar16;
                fVar16.J(0, 0, 0, 0);
            } else if (l2 == BubbleBannerEntity.TYPE_08009) {
                com.jingdong.app.mall.home.floor.common.f fVar17 = new com.jingdong.app.mall.home.floor.common.f(-2, 50);
                this.mTitleSize = fVar17;
                fVar17.J(0, 12, 0, 12);
            } else if (l2 == BubbleBannerEntity.TYPE_08008_V936) {
                com.jingdong.app.mall.home.floor.common.f fVar18 = new com.jingdong.app.mall.home.floor.common.f(265, 68);
                this.mTitleSize = fVar18;
                fVar18.J(21, 0, 0, 0);
            } else {
                com.jingdong.app.mall.home.floor.common.f fVar19 = new com.jingdong.app.mall.home.floor.common.f(-2, 130);
                this.mTitleSize = fVar19;
                fVar19.J(0, 0, 0, 0);
            }
            RelativeLayout.LayoutParams u4 = this.mTitleSize.u(this.mBottomText);
            u4.addRule(l2 == BubbleBannerEntity.TYPE_08008_V936 ? 9 : 14);
            u4.addRule(12);
            addView(this.mBottomText, u4);
            i5 = 1;
        } else {
            i5 = 1;
            com.jingdong.app.mall.home.floor.common.f.d(gradientTextView2, this.mTitleSize, true);
        }
        com.jingdong.app.mall.home.floor.common.f.O(this.mBottomText, (l2 == 0 || l2 == i5) ? 28 : l2 == 2 ? 26 : l2 == 3 ? 34 : 42);
        this.mBottomText.setMaxWidth(d.d(l2 == BubbleBannerEntity.TYPE_08008_V936 ? 265 : 300));
        this.mBottomText.setText(this.mMiddleItem.b(true));
        GradientTextView gradientTextView4 = this.mBottomText;
        GradientTextView.GradientType gradientType = GradientTextView.GradientType.LeftToRight;
        gradientTextView4.setTextGradient(gradientType, this.mMiddleItem.a());
        if (this.mMiddleItem.n()) {
            int[] k2 = this.mMiddleItem.k();
            if (l2 == BubbleBannerEntity.TYPE_08008_V936) {
                this.mTopText.setTextGradient(GradientTextView.GradientType.TopToBottom, k2);
                this.mTimeLayout.setVisibility(0);
                this.mTimeView.k(this.mMiddleItem.c());
                this.mTimeView.l(d.d(24));
                this.mTimeView.h(d.d(32));
                this.mTimeView.g(d.d(30));
                if (this.mMiddleItem.o()) {
                    this.mTimeView.e(-1);
                    this.mTimeView.i(-1);
                } else {
                    this.mTimeView.f(k2);
                    this.mTimeView.i(k2[0]);
                }
            } else {
                this.mTopText.setTextGradient(gradientType, k2);
                this.mTimeLayout.setVisibility(0);
                this.mTimeView.i(k2[0]);
                this.mTimeView.k(k2[0]);
                this.mTimeView.e(0);
                this.mTimeView.l(d.d(24));
            }
        } else {
            this.mTimeLayout.setVisibility(8);
        }
        bindImage(fVar, aVar);
    }

    public void setTimeEnd() {
        this.mTopText.setVisibility(0);
        this.mTimeView.setVisibility(8);
        this.mTopText.setText(this.mMiddleItem.i());
        this.mTimeView.m("00", "00", "00");
    }

    public void setTimeText(com.jingdong.app.mall.home.x.e eVar) {
        if (eVar == null) {
            setTimeEnd();
            return;
        }
        this.mTopText.setVisibility(8);
        this.mTimeView.setVisibility(0);
        String a = eVar.a();
        String b = eVar.b();
        String c2 = eVar.c();
        TimeFormatView timeFormatView = this.mTimeView;
        if (a.length() <= 1) {
            a = "0" + a;
        }
        if (b.length() <= 1) {
            b = "0" + b;
        }
        if (c2.length() <= 1) {
            c2 = "0" + c2;
        }
        timeFormatView.m(a, b, c2);
    }
}
