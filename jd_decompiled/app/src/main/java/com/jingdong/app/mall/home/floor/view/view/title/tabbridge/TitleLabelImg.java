package com.jingdong.app.mall.home.floor.view.view.title.tabbridge;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Looper;
import android.os.MessageQueue;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabExpoUtil;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabManager;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.mall.home.r.a.d;

/* loaded from: classes4.dex */
public class TitleLabelImg extends HomeDraweeView {
    private String iconAnimationUrl;
    private boolean isGoneIcon;
    private boolean isRelease;
    private ValueAnimator mAnimator;
    private String mCurrentIcon;
    private final HourlyGoBridge mHourlyGoBridge;
    private final f mLabelSize;

    public TitleLabelImg(Context context, HourlyGoBridge hourlyGoBridge) {
        super(context);
        this.mHourlyGoBridge = hourlyGoBridge;
        f fVar = new f(90, 30);
        this.mLabelSize = fVar;
        fVar.E(-28, 11, 0, 0);
        setScaleType(ImageView.ScaleType.FIT_XY);
        setAlpha(0.0f);
        setPivotX(0.0f);
        setPivotY(fVar.h());
    }

    public void resetAnimationState() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        setAlpha(1.0f);
        setScaleX(1.0f);
        setScaleY(1.0f);
    }

    public void startIconAnimationIdle() {
        if (getAlpha() > 0.0f || this.mAnimator != null || this.isRelease) {
            return;
        }
        TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo().addPlayTimes();
        ValueAnimator buildLabelAnimator = HourlyGoBridge.buildLabelAnimator();
        this.mAnimator = buildLabelAnimator;
        buildLabelAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabbridge.TitleLabelImg.4
            {
                TitleLabelImg.this = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                TitleLabelImg.this.setScaleX(floatValue);
                TitleLabelImg.this.setScaleY(floatValue);
                TitleLabelImg.this.setAlpha(Math.max(floatValue, 1.0f));
            }
        });
        this.mAnimator.addListener(new d() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabbridge.TitleLabelImg.5
            {
                TitleLabelImg.this = this;
            }

            @Override // com.jingdong.app.mall.home.r.a.d
            protected void onEnd(Animator animator, boolean z) {
                if (z) {
                    return;
                }
                TitleLabelImg.this.resetAnimationState();
            }
        });
        this.mAnimator.start();
    }

    public void addLabel(ViewGroup viewGroup, View view) {
        RelativeLayout.LayoutParams u = this.mLabelSize.u(this);
        u.addRule(1, view.getId());
        setLayoutParams(u);
        m.a(viewGroup, this);
    }

    public void checkSize() {
        setPivotY(this.mLabelSize.h());
        f.d(this, this.mLabelSize, true);
    }

    public void dismiss() {
        setVisibility(8);
        resetAnimationState();
    }

    public boolean isShowing() {
        return !this.isRelease && getAlpha() > 0.0f;
    }

    public void loadIcon(final String str) {
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabbridge.TitleLabelImg.1
                {
                    TitleLabelImg.this = this;
                }

                @Override // com.jingdong.app.mall.home.o.a.b
                protected void safeRun() {
                    TitleLabelImg.this.loadIcon(str);
                }
            });
        } else if (!TextUtils.isEmpty(str) && !this.isRelease) {
            if (TextUtils.equals(this.mCurrentIcon, str)) {
                return;
            }
            setAlpha(0.0f);
            setVisibility(0);
            this.mCurrentIcon = str;
            this.isGoneIcon = false;
            e.p(this, str, e.b, new e.b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabbridge.TitleLabelImg.2
                {
                    TitleLabelImg.this = this;
                }

                @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
                public void onFailed(String str2, View view) {
                }

                @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
                public void onStart(String str2, View view) {
                }

                @Override // com.jingdong.app.mall.home.floor.ctrl.e.b
                public void onSuccess(String str2, View view) {
                    if (TitleLabelImg.this.isGoneIcon || TitleLabelImg.this.isRelease || !TitleTabManager.getInstance().canShowHourlyGoTab()) {
                        return;
                    }
                    if (!TextUtils.equals(TitleLabelImg.this.iconAnimationUrl, str2)) {
                        TitleLabelImg.this.iconAnimationUrl = str2;
                        TitleLabelImg.this.startLabelAnimation();
                        return;
                    }
                    TitleLabelImg.this.resetAnimationState();
                }
            });
        } else {
            setAlpha(0.0f);
            this.mCurrentIcon = str;
            this.isGoneIcon = true;
        }
    }

    public void release() {
        if (this.isRelease) {
            return;
        }
        this.isRelease = true;
        m.K(this);
    }

    void startLabelAnimation() {
        TitleTabExpoUtil.postRightTagExpo();
        TitleTabExpoUtil.postLabelExpo();
        if (!TitleTabManager.getInstance().getTitleTabInfo().getTabHourlyGo().isUseLabelAnimation()) {
            resetAnimationState();
        } else if (getAlpha() > 0.0f) {
        } else {
            Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabbridge.TitleLabelImg.3
                {
                    TitleLabelImg.this = this;
                }

                @Override // android.os.MessageQueue.IdleHandler
                public boolean queueIdle() {
                    TitleLabelImg.this.startIconAnimationIdle();
                    return false;
                }
            });
        }
    }
}
