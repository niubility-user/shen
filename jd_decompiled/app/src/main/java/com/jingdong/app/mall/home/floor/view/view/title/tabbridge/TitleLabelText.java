package com.jingdong.app.mall.home.floor.view.view.title.tabbridge;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.ctrl.e;
import com.jingdong.app.mall.home.floor.view.view.title.tabnotice.TitleTabNoticeInfo;
import com.jingdong.app.mall.home.o.a.o;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes4.dex */
public class TitleLabelText extends RelativeLayout {
    private static final int TEXT_SIZE = 18;
    private static final int labelHeight = 24;
    private static final Handler sHandler = new Handler(Looper.getMainLooper());
    private boolean isRelease;
    private ValueAnimator mAnimator;
    private final Context mContext;
    private final HourlyGoBridge mHourlyGoBridge;
    private final f mLabelSize;
    private NinePatch mNinePatch;
    private final Paint mPaint;
    private long mShowTime;
    private TextView mText;

    public TitleLabelText(Context context, HourlyGoBridge hourlyGoBridge) {
        super(context);
        this.mPaint = new Paint(1);
        this.mHourlyGoBridge = hourlyGoBridge;
        this.mContext = context;
        f fVar = new f(-2, 24);
        this.mLabelSize = fVar;
        fVar.E(-22, 4, 0, 0);
        setGravity(16);
        setAlpha(0.0f);
        setPivotX(0.0f);
        checkSize();
    }

    private void bindLabel(Bitmap bitmap, String str) {
        if (o.a(bitmap) && !this.isRelease) {
            if (getAlpha() > 0.0f) {
                return;
            }
            Bitmap x = e.x(bitmap, d.d(24));
            byte[] y = e.y(x, 0.5f);
            NinePatch ninePatch = y != null ? new NinePatch(x, y, null) : null;
            this.mNinePatch = ninePatch;
            if (ninePatch == null) {
                setVisibility(8);
                return;
            }
            setVisibility(0);
            this.mShowTime = SystemClock.elapsedRealtime();
            f fVar = new f(-2, -1);
            fVar.J(6, -10, 6, -10);
            TextView textView = this.mText;
            if (textView == null) {
                g gVar = new g(this.mContext, false);
                gVar.f(1);
                gVar.d(16);
                TextView a = gVar.a();
                this.mText = a;
                addView(this.mText, fVar.u(a));
            } else {
                f.d(textView, fVar, true);
            }
            f.c(this, this.mLabelSize);
            g.o(this.mText, 18);
            this.mText.setTypeface(FontsUtil.getTypeFace(getContext()));
            this.mText.setTextColor(-1);
            this.mText.setText(str);
            setAlpha(0.0f);
            postInvalidate();
            startLabelAnimation();
            return;
        }
        setVisibility(8);
    }

    private void drawBg(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        NinePatch ninePatch = this.mNinePatch;
        if (ninePatch != null) {
            ninePatch.draw(canvas, new Rect(0, 0, width, height), this.mPaint);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetAnimationState(float f2) {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        setAlpha(f2);
        setScaleX(1.0f);
        setScaleY(1.0f);
    }

    private void startLabelAnimation() {
        if (getAlpha() > 0.0f || this.isRelease) {
            return;
        }
        ValueAnimator buildLabelAnimator = HourlyGoBridge.buildLabelAnimator();
        this.mAnimator = buildLabelAnimator;
        buildLabelAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabbridge.TitleLabelText.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                TitleLabelText.this.setScaleX(floatValue);
                TitleLabelText.this.setScaleY(floatValue);
                TitleLabelText.this.setAlpha(Math.max(floatValue, 1.0f));
            }
        });
        this.mAnimator.addListener(new com.jingdong.app.mall.home.r.a.d() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabbridge.TitleLabelText.3
            @Override // com.jingdong.app.mall.home.r.a.d
            protected void onEnd(Animator animator, boolean z) {
                if (z) {
                    return;
                }
                TitleLabelText.this.resetAnimationState(1.0f);
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

    public void bindNoticeLabel(TitleTabNoticeInfo titleTabNoticeInfo) {
        if (titleTabNoticeInfo != null && !this.isRelease && titleTabNoticeInfo.isValidLabel()) {
            bindLabel(titleTabNoticeInfo.getLabelBitmap(), titleTabNoticeInfo.getLabelText());
        } else {
            setVisibility(8);
        }
    }

    public void bindPopLabel(TitleTabPop titleTabPop) {
        if (titleTabPop != null && !this.isRelease && titleTabPop.isValidLabel()) {
            bindLabel(titleTabPop.getLabelBitmap(), titleTabPop.getLabelText());
        } else {
            setVisibility(8);
        }
    }

    public void checkSize() {
        setPivotY(this.mLabelSize.h());
        g.o(this.mText, 18);
        f.d(this, this.mLabelSize, true);
    }

    public void delayDismiss(long j2) {
        if (this.mShowTime <= 0) {
            return;
        }
        Handler handler = sHandler;
        handler.removeCallbacksAndMessages(null);
        long elapsedRealtime = j2 - (SystemClock.elapsedRealtime() - this.mShowTime);
        if (elapsedRealtime <= 0) {
            dismiss();
        } else {
            handler.postDelayed(new Runnable() { // from class: com.jingdong.app.mall.home.floor.view.view.title.tabbridge.TitleLabelText.1
                @Override // java.lang.Runnable
                public void run() {
                    TitleLabelText.this.dismiss();
                }
            }, elapsedRealtime);
        }
    }

    public void dismiss() {
        setVisibility(8);
        resetAnimationState(0.0f);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        drawBg(canvas);
        super.dispatchDraw(canvas);
    }

    public void release() {
        if (this.isRelease) {
            return;
        }
        this.isRelease = true;
        m.K(this);
    }
}
