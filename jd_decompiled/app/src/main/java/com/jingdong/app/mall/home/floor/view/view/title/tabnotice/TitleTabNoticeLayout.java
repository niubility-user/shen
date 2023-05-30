package com.jingdong.app.mall.home.floor.view.view.title.tabnotice;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.base.HomeDraweeView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.view.title.HomeTitleNew;
import com.jingdong.app.mall.home.floor.view.view.title.TitleLogo;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabItemContent;
import com.jingdong.app.mall.home.floor.view.view.title.tab.TitleTabLayout;
import com.jingdong.app.mall.home.floor.view.view.title.tabbridge.TitleLabelText;
import com.jingdong.app.mall.home.n.h.e;
import com.jingdong.app.mall.home.n.h.g;
import com.jingdong.app.mall.home.o.a.b;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.pdj.libcore.watcher.HourlyGoObserverManager;

/* loaded from: classes4.dex */
public class TitleTabNoticeLayout extends RelativeLayout {
    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    public static final JDDisplayImageOptions sIconOption;
    private int bgEndCenterX;
    private int bgHeightEnd;
    private float bgHeightStart;
    private float bgStartCenterX;
    private int bgWidthEnd;
    private float bgWidthStart;
    private int iconLeftX;
    private int iconToLeftX;
    private int infoState;
    private boolean isRelease;
    private boolean isShowing;
    private boolean isStart;
    private ValueAnimator mAnimator;
    private final Paint mBgPaint;
    private final Path mBgPath;
    private f mIconSize;
    private HomeDraweeView mIconView;
    private NoticeOrderContent mInfoLayout;
    private f mInfoSize;
    private f mNoticeSize;
    private final OvershootInterpolator mOvershootInterpolator;
    private final TitleLogo mTitleLogo;
    private TitleTabLayout mTitleTabLayout;
    private final DecelerateInterpolator mTwoInterpolator;
    private final float scaleStart;
    private int tryCount;
    private boolean tryShow;

    static {
        JDDisplayImageOptions a = com.jingdong.app.mall.home.floor.ctrl.f.a();
        int i2 = R.drawable.home_tab_notice_icon;
        sIconOption = a.showImageOnFail(i2).showImageOnLoading(i2).showImageForEmptyUri(i2);
    }

    public TitleTabNoticeLayout(HomeTitleNew homeTitleNew) {
        super(homeTitleNew.getContext());
        this.isStart = true;
        this.scaleStart = 0.3f;
        this.mBgPaint = new Paint(1);
        this.mBgPath = new Path();
        this.mOvershootInterpolator = new OvershootInterpolator(1.1f);
        this.mTwoInterpolator = new DecelerateInterpolator(2.0f);
        Context context = homeTitleNew.getContext();
        this.mTitleLogo = homeTitleNew.getTitleLogo();
        f fVar = new f(-1, 64);
        this.mNoticeSize = fVar;
        fVar.E(0, 12, 0, 0);
        this.mInfoSize = new f(-2, 64);
        this.mIconView = new HomeDraweeView(context);
        f fVar2 = new f(94, 48);
        this.mIconSize = fVar2;
        RelativeLayout.LayoutParams u = fVar2.u(this.mIconView);
        u.addRule(15);
        addView(this.mIconView, u);
    }

    private void addTabNotice() {
        f.c(this.mIconView, this.mIconSize);
        RelativeLayout.LayoutParams u = this.mNoticeSize.u(this);
        u.addRule(15);
        setLayoutParams(u);
        m.a(this.mTitleTabLayout, this);
        if (this.mInfoLayout == null) {
            NoticeOrderContent noticeOrderContent = new NoticeOrderContent(getContext());
            this.mInfoLayout = noticeOrderContent;
            RelativeLayout.LayoutParams u2 = this.mInfoSize.u(noticeOrderContent);
            u2.addRule(13);
            this.mInfoLayout.setLayoutParams(u2);
            m.a(this, this.mInfoLayout);
        }
        this.mInfoLayout.bindData();
    }

    private void cancelCallbacks(boolean z) {
        sHandler.removeCallbacksAndMessages(null);
        TitleTabLayout titleTabLayout = this.mTitleTabLayout;
        if (titleTabLayout == null || !z) {
            return;
        }
        titleTabLayout.getLabelText().delayDismiss(TitleTabNoticeInfo.getInstance().getDismissTime());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAnimationEnd() {
        cancelCallbacks(!this.isStart);
        if (this.isStart) {
            this.mInfoLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabnotice.TitleTabNoticeLayout.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    TitleTabNoticeLayout.this.onClickNotice();
                }
            });
            sHandler.postDelayed(new b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabnotice.TitleTabNoticeLayout.3
                @Override // com.jingdong.app.mall.home.o.a.b
                protected void safeRun() {
                    TitleTabNoticeLayout.this.mInfoLayout.setOnClickListener(null);
                    TitleTabNoticeLayout.this.startAnimation(false);
                }
            }, TitleTabNoticeInfo.getInstance().getShowTime());
            return;
        }
        release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doAnimation(float f2) {
        float interpolation = this.mTwoInterpolator.getInterpolation(f2);
        TitleLogo titleLogo = this.mTitleLogo;
        if (titleLogo != null) {
            titleLogo.doAnimation(interpolation);
        }
        setAlpha(f2 < 0.002f ? 0.0f : Math.min(1.0f, (5.0f * f2) + 0.2f));
        float interpolation2 = this.mOvershootInterpolator.getInterpolation(f2);
        float min = this.iconLeftX - ((r1 - this.iconToLeftX) * Math.min(1.0f, interpolation2));
        float min2 = (Math.min(1.0f, interpolation2) * 0.7f) + 0.3f;
        this.mIconView.setTranslationX(min);
        this.mIconView.setScaleX(min2);
        this.mIconView.setScaleY(min2);
        doInfoAnimation(interpolation2);
        this.mBgPaint.setColor(-1);
        float f3 = this.bgStartCenterX;
        float f4 = f3 - ((f3 - this.bgEndCenterX) * interpolation);
        float f5 = this.bgHeightStart;
        float f6 = f5 + ((this.bgHeightEnd - f5) * interpolation);
        float f7 = this.bgWidthStart;
        this.mBgPath.reset();
        float f8 = (f7 + ((this.bgWidthEnd - f7) * interpolation2)) / 2.0f;
        float f9 = f6 / 2.0f;
        float h2 = this.mNoticeSize.h() / 2.0f;
        g.g(f4 - f8, h2 - f9, f4 + f8, h2 + f9, f9, this.mBgPath);
        postInvalidate();
    }

    private void doInfoAnimation(float f2) {
        boolean z = this.isStart;
        int i2 = (!z || f2 <= 0.6f) ? (z || f2 >= 0.9f) ? 0 : 2 : 1;
        if (this.infoState == i2 || i2 == 0) {
            return;
        }
        this.infoState = i2;
        this.mInfoLayout.animate().cancel();
        this.mInfoLayout.setAlpha(this.isStart ? 0.0f : 1.0f);
        this.mInfoLayout.animate().translationX(0.0f).alpha(this.isStart ? 1.0f : 0.0f).setDuration(100L).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickNotice() {
        this.mTitleTabLayout.onTabClick(-1, HourlyGoObserverManager.HOURLY_GO_SOURCE_VALUE_ORDER_BUBBLE);
        TitleTabNoticeInfo.getInstance().onPopClick();
    }

    private void resetState() {
        this.isRelease = false;
        this.isShowing = true;
        this.isStart = true;
        this.infoState = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void safeShowNotice(TitleTabItemContent titleTabItemContent) {
        this.tryCount++;
        int left = titleTabItemContent.getLeft();
        int width = this.mInfoLayout.getWidth();
        int i2 = left + width;
        boolean z = false;
        boolean z2 = i2 < d.f9279g && i2 > (d.f9279g >> 1);
        if ((left <= 0 || width <= 0 || !z2) && this.tryCount < 5) {
            z = true;
        }
        if (z) {
            tryShowNotice(titleTabItemContent);
            return;
        }
        int left2 = this.mInfoLayout.getLeft();
        int width2 = titleTabItemContent.getWidth();
        if (z2 && left > 0 && width2 > 0 && width > 0 && left2 > 0) {
            if (!TitleTabNoticeInfo.getInstance().canShow() || this.isShowing) {
                return;
            }
            resetState();
            this.mTitleTabLayout.getLabelText().dismiss();
            int tabWidth = titleTabItemContent.getTabWidth();
            e.d(this.mIconView, this.mIconSize.h());
            float v = this.mIconSize.v() >> 1;
            this.iconLeftX = (left + width2) - ((tabWidth + this.mIconSize.v()) >> 1);
            this.iconToLeftX = d.d(8) + left2;
            this.bgStartCenterX = this.iconLeftX + v;
            this.bgEndCenterX = left2 + (width >> 1);
            this.bgWidthStart = this.mIconSize.v() * 0.3f;
            this.bgWidthEnd = width;
            this.bgHeightStart = this.mIconSize.h() * 0.3f;
            this.bgHeightEnd = this.mNoticeSize.h();
            this.mIconView.setPivotX(v);
            this.mIconView.setPivotY(this.mIconSize.h() >> 1);
            this.mIconView.setScaleX(0.3f);
            this.mIconView.setScaleY(0.3f);
            this.mIconView.setTranslationX(this.iconLeftX);
            this.mInfoLayout.setAlpha(0.0f);
            setAlpha(0.0f);
            startAnimation(true);
            TitleTabNoticeInfo.getInstance().onPopShow();
            return;
        }
        m.K(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryShowNotice(final TitleTabItemContent titleTabItemContent) {
        f.c(this.mIconView, this.mIconSize);
        f.c(this, this.mNoticeSize);
        this.mInfoLayout.bindData();
        Handler handler = sHandler;
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new b() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabnotice.TitleTabNoticeLayout.4
            @Override // com.jingdong.app.mall.home.o.a.b
            protected void safeRun() {
                TitleTabNoticeLayout.this.safeShowNotice(titleTabItemContent);
            }
        }, 80L);
    }

    public boolean bindModel(TitleTabLayout titleTabLayout) {
        this.mTitleTabLayout = titleTabLayout;
        if (titleTabLayout.getVisibility() != 0) {
            return false;
        }
        cancelCallbacks(true);
        m.K(this);
        if (this.isShowing) {
            release();
            return true;
        }
        TitleTabNoticeInfo.getInstance().setShowed(true);
        titleTabLayout.onNoticeShow();
        setAlpha(0.0f);
        addTabNotice();
        this.mTitleTabLayout.getLabelImg().dismiss();
        this.mTitleTabLayout.getLabelText().dismiss();
        this.tryShow = false;
        this.tryCount = 0;
        String skuImg = TitleTabNoticeInfo.getInstance().getSkuImg();
        com.jingdong.app.mall.home.floor.ctrl.f.f(skuImg, this.mIconView, sIconOption, new JDSimpleImageLoadingListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabnotice.TitleTabNoticeLayout.1
            @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                super.onLoadingComplete(str, view, bitmap);
                if (TitleTabNoticeLayout.this.tryShow) {
                    return;
                }
                TitleTabNoticeLayout.this.tryShow = true;
                TitleTabNoticeLayout titleTabNoticeLayout = TitleTabNoticeLayout.this;
                titleTabNoticeLayout.tryShowNotice(titleTabNoticeLayout.mTitleTabLayout.getTabContent());
            }

            @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
            public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                super.onLoadingFailed(str, view, jDFailReason);
                if (TitleTabNoticeLayout.this.tryShow) {
                    return;
                }
                TitleTabNoticeLayout.this.tryShow = true;
                TitleTabNoticeLayout titleTabNoticeLayout = TitleTabNoticeLayout.this;
                titleTabNoticeLayout.tryShowNotice(titleTabNoticeLayout.mTitleTabLayout.getTabContent());
            }
        });
        if (TextUtils.isEmpty(skuImg)) {
            this.tryShow = true;
            tryShowNotice(this.mTitleTabLayout.getTabContent());
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        canvas.drawPath(this.mBgPath, this.mBgPaint);
        super.dispatchDraw(canvas);
    }

    public boolean isRelease() {
        return this.isRelease;
    }

    public void onPause() {
        if (!this.isShowing || this.isRelease) {
            return;
        }
        release();
    }

    public void release() {
        release(false);
    }

    public void showNoticeLabel(TitleLabelText titleLabelText) {
        if (!TitleTabNoticeInfo.getInstance().canLabelShow()) {
            titleLabelText.dismiss();
            return;
        }
        titleLabelText.bindNoticeLabel(TitleTabNoticeInfo.getInstance());
        cancelCallbacks(true);
    }

    public void startAnimation(boolean z) {
        if (!this.isStart || this.isRelease) {
            return;
        }
        this.isStart = z;
        this.infoState = 0;
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (this.isStart) {
            this.mAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        } else {
            this.mAnimator = ValueAnimator.ofFloat(1.0f, 0.0f);
        }
        this.mAnimator.setDuration(600L);
        this.mAnimator.setInterpolator(new LinearInterpolator());
        this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabnotice.TitleTabNoticeLayout.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                TitleTabNoticeLayout.this.doAnimation(((Float) valueAnimator2.getAnimatedValue()).floatValue());
            }
        });
        this.mAnimator.addListener(new com.jingdong.app.mall.home.r.a.d() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabnotice.TitleTabNoticeLayout.6
            @Override // com.jingdong.app.mall.home.r.a.d
            protected void onEnd(Animator animator, boolean z2) {
                if (z2) {
                    return;
                }
                TitleTabNoticeLayout.this.checkAnimationEnd();
            }
        });
        this.mAnimator.start();
    }

    public void release(boolean z) {
        if (!this.isRelease || z) {
            this.isRelease = true;
            this.isShowing = false;
            setAlpha(0.0f);
            cancelCallbacks(true);
            TitleTabLayout titleTabLayout = this.mTitleTabLayout;
            if (titleTabLayout != null) {
                showNoticeLabel(titleTabLayout.getLabelText());
            }
            ValueAnimator valueAnimator = this.mAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            TitleLogo titleLogo = this.mTitleLogo;
            if (titleLogo != null) {
                titleLogo.doAnimation(0.0f);
            }
            TitleTabNoticeInfo.getInstance().onPopClose();
            m.K(this);
        }
    }
}
