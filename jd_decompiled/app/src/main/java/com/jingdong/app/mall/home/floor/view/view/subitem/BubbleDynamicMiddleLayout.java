package com.jingdong.app.mall.home.floor.view.view.subitem;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.l;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.model.entity.BubbleDynamicEntity;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.floor.view.widget.TimeFormatView;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.lang.reflect.Array;

/* loaded from: classes4.dex */
public class BubbleDynamicMiddleLayout extends RelativeLayout {
    private SimpleDraweeView bgIv;
    private final f bgSize;
    private int[] bottomTextSize;
    private GradientTextView bottomTv;
    private LinearLayout downSkuContainer;
    private boolean downSkuNeedReset;
    private com.jingdong.app.mall.home.r.e.f element;
    private RelativeLayout flipper;
    private final f flipperSize;
    private BubbleDynamicEntity mEntity;
    private final Handler mHandler;
    private ValueAnimator mSkuTranslateAnim;
    private final b mTranslateRunnable;
    private int offset;
    private final SimpleDraweeView[][] skuIvs;
    private int[] skuSize;
    private RelativeLayout topContainer;
    private int[] topTextSize;
    private TimeFormatView topTime;
    private GradientTextView topTv;
    private boolean tranAnimStop;
    private LinearLayout upSkuContainer;

    public BubbleDynamicMiddleLayout(Context context) {
        super(context);
        this.bgSize = new f(-1, -1);
        this.flipperSize = new f(-2, -2);
        this.skuIvs = (HomeDraweeView[][]) Array.newInstance(HomeDraweeView.class, 2, 2);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mTranslateRunnable = new b() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleDynamicMiddleLayout.1
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                if (BubbleDynamicMiddleLayout.this.mSkuTranslateAnim != null) {
                    BubbleDynamicMiddleLayout.this.mSkuTranslateAnim.start();
                }
            }
        };
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleDynamicMiddleLayout.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BubbleDynamicMiddleLayout.this.onItemClick(0);
            }
        });
    }

    private void beforeSkuTranslate() {
        stopFlip();
        createTransAnim(d.d(this.offset));
        this.downSkuNeedReset = false;
        this.downSkuContainer.setTranslationY(d.d(this.offset));
        this.upSkuContainer.setTranslationY(0.0f);
    }

    private void bindBg(String str) {
        SimpleDraweeView simpleDraweeView = this.bgIv;
        if (simpleDraweeView == null) {
            HomeDraweeView homeDraweeView = new HomeDraweeView(getContext());
            this.bgIv = homeDraweeView;
            homeDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            addView(this.bgIv, this.bgSize.u(this.bgIv));
        } else {
            f.c(simpleDraweeView, this.bgSize);
        }
        this.bgIv.setVisibility(0);
        setBackgroundColor(e.a);
        e.p(this.bgIv, str, e.b, new e.b() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleDynamicMiddleLayout.5
            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onFailed(String str2, View view) {
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onStart(String str2, View view) {
            }

            @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
            public void onSuccess(String str2, View view) {
                BubbleDynamicMiddleLayout.this.setBackgroundColor(0);
            }
        });
    }

    private void bindBottom(boolean z) {
        if (z) {
            GradientTextView gradientTextView = this.bottomTv;
            if (gradientTextView != null) {
                gradientTextView.setVisibility(8);
                return;
            }
            return;
        }
        int[] iArr = this.bottomTextSize;
        int min = Math.min(iArr[1], this.mEntity.floorHeight - iArr[1]);
        int i2 = this.bottomTextSize[1] - min;
        f fVar = new f(-2, min * 2);
        fVar.E(0, 0, 0, i2);
        GradientTextView gradientTextView2 = this.bottomTv;
        if (gradientTextView2 == null) {
            GradientTextView gradientTextView3 = new GradientTextView(getContext());
            this.bottomTv = gradientTextView3;
            gradientTextView3.setFocusable(false);
            this.bottomTv.getPaint().setFakeBoldText(true);
            this.bottomTv.setBackgroundColor(0);
            this.bottomTv.setSingleLine(true);
            this.bottomTv.setEllipsize(TextUtils.TruncateAt.END);
            this.bottomTv.setPadding(0, -3, 0, -3);
            this.bottomTv.setGravity(16);
            RelativeLayout.LayoutParams u = fVar.u(this.bottomTv);
            u.addRule(14);
            u.addRule(12);
            addView(this.bottomTv, u);
            this.bottomTv.setVisibility(4);
        } else {
            f.d(gradientTextView2, fVar, true);
        }
        f.O(this.bottomTv, this.bottomTextSize[0]);
        double measureText = this.bottomTv.getPaint().measureText("\u5bbd");
        Double.isNaN(measureText);
        this.bottomTv.setMaxWidth(Math.min((int) (measureText * 10.5d), d.d(this.mEntity.middleWidth)));
        this.bottomTv.setText(this.element.getJsonString("interestPoint"));
        this.bottomTv.setTextGradient(GradientTextView.GradientType.LeftToRight, m.o(this.element.getJsonString("interestPointColor"), -16777216));
        this.bottomTv.setVisibility(0);
    }

    private void bindFlipper(boolean z) {
        int[] iArr = this.skuSize;
        if (iArr != null && !z) {
            this.offset = iArr[1] + 4;
            this.flipperSize.F(new Rect(0, this.skuSize[2] - 4, 0, 0));
            RelativeLayout relativeLayout = this.flipper;
            if (relativeLayout == null) {
                RelativeLayout relativeLayout2 = new RelativeLayout(getContext());
                this.flipper = relativeLayout2;
                relativeLayout2.setBackgroundColor(-1);
                RelativeLayout.LayoutParams u = this.flipperSize.u(this.flipper);
                u.addRule(14);
                this.flipper.setLayoutParams(u);
                m.b(this, this.flipper, 0);
            } else {
                relativeLayout.setVisibility(0);
                f.c(this.flipper, this.flipperSize);
            }
            f fVar = new f(-2, -2);
            fVar.K(new Rect(4, 4, 4, 4));
            LinearLayout linearLayout = this.upSkuContainer;
            if (linearLayout == null) {
                LinearLayout linearLayout2 = new LinearLayout(getContext());
                this.upSkuContainer = linearLayout2;
                linearLayout2.setOrientation(0);
                this.flipper.addView(this.upSkuContainer, fVar.u(this.upSkuContainer));
            } else {
                f.d(linearLayout, fVar, true);
            }
            bindSku(this.upSkuContainer, 0);
            LinearLayout linearLayout3 = this.downSkuContainer;
            if (linearLayout3 == null) {
                LinearLayout linearLayout4 = new LinearLayout(getContext());
                this.downSkuContainer = linearLayout4;
                linearLayout4.setOrientation(0);
                this.flipper.addView(this.downSkuContainer, fVar.u(this.downSkuContainer));
            } else {
                f.d(linearLayout3, fVar, true);
            }
            bindSku(this.downSkuContainer, 1);
            com.jingdong.app.mall.home.n.h.e.d(this.flipper, d.d(20));
            beforeSkuTranslate();
            e.l(this.element.u(), this.skuIvs[0][0]);
            e.l(this.element.v(), this.skuIvs[0][1]);
            e.l(this.element.w(), this.skuIvs[1][0]);
            e.l(this.element.x(), this.skuIvs[1][1]);
            doTransAnim();
            return;
        }
        RelativeLayout relativeLayout3 = this.flipper;
        if (relativeLayout3 != null) {
            relativeLayout3.setVisibility(8);
        }
    }

    private void bindSku(LinearLayout linearLayout, final int i2) {
        int[] iArr = this.skuSize;
        f fVar = new f(iArr[0], iArr[1]);
        SimpleDraweeView[][] simpleDraweeViewArr = this.skuIvs;
        if (simpleDraweeViewArr[i2][0] == null) {
            simpleDraweeViewArr[i2][0] = new HomeDraweeView(getContext());
            this.skuIvs[i2][0].setContentDescription(getContext().getString(R.string.home_obstacle_free));
            this.skuIvs[i2][0].setScaleType(ImageView.ScaleType.FIT_XY);
            this.skuIvs[i2][0].setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleDynamicMiddleLayout.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BubbleDynamicMiddleLayout.this.onItemClick(i2 == 0 ? 1 : 3);
                }
            });
            linearLayout.addView(this.skuIvs[i2][0], fVar.i(this.skuIvs[i2][0]));
        } else {
            f.d(simpleDraweeViewArr[i2][0], fVar, true);
        }
        com.jingdong.app.mall.home.n.h.e.d(this.skuIvs[i2][0], d.d(this.mEntity.getMidSkuRadius()));
        int[] iArr2 = this.skuSize;
        f fVar2 = new f(iArr2[0], iArr2[1]);
        fVar2.F(new Rect(this.skuSize[3], 0, 0, 0));
        SimpleDraweeView[][] simpleDraweeViewArr2 = this.skuIvs;
        if (simpleDraweeViewArr2[i2][1] == null) {
            simpleDraweeViewArr2[i2][1] = new HomeDraweeView(getContext());
            this.skuIvs[i2][1].setContentDescription(getContext().getString(R.string.home_obstacle_free));
            this.skuIvs[i2][1].setScaleType(ImageView.ScaleType.FIT_XY);
            this.skuIvs[i2][1].setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleDynamicMiddleLayout.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BubbleDynamicMiddleLayout.this.onItemClick(i2 == 0 ? 2 : 4);
                }
            });
            linearLayout.addView(this.skuIvs[i2][1], fVar2.i(this.skuIvs[i2][1]));
        } else {
            f.d(simpleDraweeViewArr2[i2][1], fVar2, true);
        }
        com.jingdong.app.mall.home.n.h.e.d(this.skuIvs[i2][1], d.d(this.mEntity.getMidSkuRadius()));
    }

    private void bindTop(boolean z) {
        if (z) {
            RelativeLayout relativeLayout = this.topContainer;
            if (relativeLayout != null) {
                relativeLayout.setVisibility(8);
                return;
            }
            return;
        }
        int[] iArr = this.topTextSize;
        int min = Math.min(iArr[1], this.mEntity.floorHeight - iArr[1]);
        int i2 = this.topTextSize[1] - min;
        f fVar = new f(-2, min * 2);
        fVar.F(new Rect(0, i2, 0, 0));
        RelativeLayout relativeLayout2 = this.topContainer;
        if (relativeLayout2 == null) {
            RelativeLayout relativeLayout3 = new RelativeLayout(getContext());
            this.topContainer = relativeLayout3;
            RelativeLayout.LayoutParams u = fVar.u(relativeLayout3);
            u.addRule(14);
            addView(this.topContainer, u);
        } else {
            f.d(relativeLayout2, fVar, true);
        }
        f fVar2 = new f(-2, -1);
        GradientTextView gradientTextView = this.topTv;
        if (gradientTextView == null) {
            GradientTextView gradientTextView2 = new GradientTextView(getContext());
            this.topTv = gradientTextView2;
            gradientTextView2.setFocusable(false);
            this.topTv.getPaint().setFakeBoldText(true);
            this.topTv.setBackgroundColor(0);
            this.topTv.setSingleLine(true);
            this.topTv.setEllipsize(TextUtils.TruncateAt.END);
            this.topTv.setPadding(0, -3, 0, -3);
            this.topTv.setGravity(16);
            this.topTv.setTextColor(-16777216);
            RelativeLayout.LayoutParams u2 = fVar2.u(this.topTv);
            u2.addRule(13);
            this.topContainer.addView(this.topTv, u2);
        } else {
            f.d(gradientTextView, fVar2, true);
        }
        this.topTv.setVisibility(0);
        f.O(this.topTv, this.topTextSize[0]);
        this.topTv.setMaxWidth(d.d(this.mEntity.middleWidth));
        int timerWidth = getTimerWidth();
        double d = this.topTextSize[0];
        Double.isNaN(d);
        f fVar3 = new f(timerWidth, (int) (d * 1.1d));
        TimeFormatView timeFormatView = this.topTime;
        if (timeFormatView == null) {
            TimeFormatView timeFormatView2 = new TimeFormatView(getContext());
            this.topTime = timeFormatView2;
            timeFormatView2.n(FontsUtil.getTypeFace(getContext()));
            this.topTime.j(4);
            RelativeLayout.LayoutParams u3 = fVar3.u(this.topTime);
            u3.addRule(13);
            this.topContainer.addView(this.topTime, u3);
        } else {
            f.d(timeFormatView, fVar3, true);
        }
        this.topTime.setVisibility(0);
        int midTopMode = this.mEntity.getMidTopMode();
        if (midTopMode != 1 && midTopMode != 2) {
            this.topContainer.setVisibility(8);
            return;
        }
        this.topTv.setTextGradient(GradientTextView.GradientType.LeftToRight, this.mEntity.getMidTopTextColors());
        this.topContainer.setVisibility(0);
        this.topTime.k(this.mEntity.getMidTopTimeTextColor());
        this.topTime.l(d.d(this.topTextSize[0]));
        this.topTime.h(d.d(this.topTextSize[0]));
        this.topTime.g(d.d(this.topTextSize[0]));
        this.topTime.i(this.mEntity.getMidTopTimePointColor());
        this.topTime.e(this.mEntity.getMidTopTimeOvalBgColor());
    }

    private void createTransAnim(final int i2) {
        com.jingdong.app.mall.home.n.h.f fVar = new com.jingdong.app.mall.home.n.h.f();
        fVar.b(0.0f, i2);
        fVar.d(400L);
        fVar.e(new AccelerateDecelerateInterpolator());
        fVar.g(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleDynamicMiddleLayout.7
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (!BubbleDynamicMiddleLayout.this.downSkuNeedReset) {
                    float f2 = -floatValue;
                    BubbleDynamicMiddleLayout.this.upSkuContainer.setTranslationY(f2);
                    BubbleDynamicMiddleLayout.this.downSkuContainer.setTranslationY(f2 + i2);
                    return;
                }
                float f3 = -floatValue;
                BubbleDynamicMiddleLayout.this.upSkuContainer.setTranslationY(i2 + f3);
                BubbleDynamicMiddleLayout.this.downSkuContainer.setTranslationY(f3);
            }
        });
        fVar.c(new AnimatorListenerAdapter() { // from class: com.jingdong.app.mall.home.floor.view.view.subitem.BubbleDynamicMiddleLayout.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                BubbleDynamicMiddleLayout.this.downSkuNeedReset = !r2.downSkuNeedReset;
                BubbleDynamicMiddleLayout.this.doTransAnim();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                if (BubbleDynamicMiddleLayout.this.downSkuNeedReset) {
                    BubbleDynamicMiddleLayout.this.upSkuContainer.setTranslationY(i2);
                    BubbleDynamicMiddleLayout.this.downSkuContainer.setTranslationY(0.0f);
                    return;
                }
                BubbleDynamicMiddleLayout.this.downSkuContainer.setTranslationY(i2);
                BubbleDynamicMiddleLayout.this.upSkuContainer.setTranslationY(0.0f);
            }
        });
        this.mSkuTranslateAnim = fVar.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doTransAnim() {
        startFlip(this.mEntity.getFlipInterval());
    }

    private int getTimerWidth() {
        TextPaint textPaint = new TextPaint(1);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(this.topTextSize[0]);
        textPaint.setTypeface(Typeface.create(Typeface.MONOSPACE, 1));
        textPaint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect();
        textPaint.getTextBounds("88", 0, 2, rect);
        return (rect.width() + 6) * 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onItemClick(int i2) {
        com.jingdong.app.mall.home.r.e.f fVar;
        JumpEntity jump;
        if (l.k() || (fVar = this.element) == null || (jump = fVar.getJump()) == null) {
            return;
        }
        com.jingdong.app.mall.home.r.c.b c2 = com.jingdong.app.mall.home.r.c.b.c(jump.srvJson);
        c2.a("skuposition", i2 + "");
        l.onClickJsonEvent(getContext(), jump, "", jump.getSrv(), c2.toString(), i2);
    }

    private void startFlip(long j2) {
        stopFlip();
        if ((true ^ TextUtils.isEmpty(this.element.x())) && (((j2 <= 0 || this.mTranslateRunnable == null || this.tranAnimStop) ? false : true) & (!TextUtils.isEmpty(this.element.w())))) {
            this.mHandler.postDelayed(this.mTranslateRunnable, j2);
        }
    }

    private void stopFlip() {
        b bVar = this.mTranslateRunnable;
        if (bVar != null) {
            this.mHandler.removeCallbacks(bVar);
        }
    }

    public void beforeTimeStart() {
        GradientTextView gradientTextView = this.topTv;
        if (gradientTextView == null || this.topTime == null) {
            return;
        }
        gradientTextView.setVisibility(0);
        this.topTv.setText(this.mEntity.getTextBefore());
        this.topTime.setVisibility(8);
    }

    public void bindData(int i2, BubbleDynamicEntity bubbleDynamicEntity) {
        this.mEntity = bubbleDynamicEntity;
        this.element = bubbleDynamicEntity.midElement;
        this.skuSize = bubbleDynamicEntity.midSkuSize;
        this.topTextSize = bubbleDynamicEntity.midTopTextSize;
        this.bottomTextSize = bubbleDynamicEntity.midBottomTextSize;
        stopFlip();
        boolean z = this.element.t() == 0;
        bindFlipper(z);
        com.jingdong.app.mall.home.r.e.f fVar = this.element;
        bindBg(z ? fVar.u() : fVar.G());
        bindTop(z);
        bindBottom(z);
    }

    public void onHomeResume() {
        this.tranAnimStop = false;
        doTransAnim();
    }

    public void onHomeStop() {
        this.tranAnimStop = true;
        stopFlip();
    }

    public void setTimeEnd() {
        GradientTextView gradientTextView = this.topTv;
        if (gradientTextView == null || this.topTime == null) {
            return;
        }
        gradientTextView.setVisibility(0);
        this.topTv.setText(this.mEntity.getTextAfter());
        this.topTime.setVisibility(8);
        this.topTime.m("00", "00", "00");
    }

    public void setTimeText(com.jingdong.app.mall.home.x.e eVar) {
        GradientTextView gradientTextView = this.topTv;
        if (gradientTextView == null || this.topTime == null) {
            return;
        }
        if (eVar == null) {
            setTimeEnd();
            return;
        }
        gradientTextView.setVisibility(8);
        this.topTime.setVisibility(0);
        String a = eVar.a();
        String b = eVar.b();
        String c2 = eVar.c();
        TimeFormatView timeFormatView = this.topTime;
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
