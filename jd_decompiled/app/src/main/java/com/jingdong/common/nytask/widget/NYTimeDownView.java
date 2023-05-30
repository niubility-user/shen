package com.jingdong.common.nytask.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import androidx.annotation.Nullable;
import com.jingdong.common.R;
import com.jingdong.common.nytask.inter.ITimeDown;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes5.dex */
public class NYTimeDownView extends View implements ITimeDown {
    public static final String TAG = "NYTimeDownView";
    private boolean isPauseAnimationAboveSdk19;
    private boolean isPauseAnimationBlowSdk19;
    private boolean isTimeDownFinish;
    private int mCircleBorder;
    private int mCircleRadius;
    private RectF mCircleRectF;
    private int mCircleWidth;
    private Context mContext;
    private int mCurrentAngle;
    private int mHeight;
    private Paint mPaint;
    private long mRemainTime;
    private long mTime;
    private TimeDownListener mTimeDownListener;
    private boolean mUserHandlePause;
    private ValueAnimator mValueAnimator;
    private int mWidth;

    /* loaded from: classes5.dex */
    public interface TimeDownListener {
        void onFinish();
    }

    public NYTimeDownView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animationFinish() {
        this.isTimeDownFinish = true;
        TimeDownListener timeDownListener = this.mTimeDownListener;
        if (timeDownListener != null) {
            timeDownListener.onFinish();
        }
    }

    private void init() {
        setBackgroundResource(R.drawable.ny_bg_time_down);
        initSize();
        this.isTimeDownFinish = false;
        int i2 = this.mCircleRadius;
        int i3 = this.mCircleBorder;
        int i4 = (i2 + i3) * 2;
        int i5 = this.mWidth - i3;
        int i6 = this.mHeight - i3;
        if (Log.D) {
            Log.e(TAG, "init: left = " + i3 + ", right = " + i5 + ", top = " + i3 + ", bottom = " + i6 + ", diameter = " + i4 + ", " + this.mCircleRadius);
        }
        this.mCircleRectF = new RectF(i3, i3, i5, i6);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setStrokeWidth(this.mCircleWidth);
        this.mPaint.setColor(Color.parseColor("#FB1547"));
    }

    private void initAnimator() {
        if (this.isTimeDownFinish) {
            return;
        }
        initCircleAnimator();
    }

    private void initCircleAnimator() {
        ValueAnimator ofInt = ValueAnimator.ofInt(0, R2.attr.additionBottom);
        this.mValueAnimator = ofInt;
        ofInt.setDuration(this.mRemainTime);
        this.mValueAnimator.setInterpolator(new LinearInterpolator());
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.common.nytask.widget.NYTimeDownView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (NYTimeDownView.this.isPauseAnimationBlowSdk19) {
                    return;
                }
                NYTimeDownView.this.mRemainTime = (int) (((float) r0.mTime) - (valueAnimator.getAnimatedFraction() * ((float) valueAnimator.getDuration())));
                NYTimeDownView.this.mCurrentAngle = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (Log.D) {
                    Log.e(NYTimeDownView.TAG, "initCircleAnimator: onAnimationUpdate: mRemainTime = " + NYTimeDownView.this.mRemainTime + ", mTime = " + NYTimeDownView.this.mTime);
                }
                NYTimeDownView.this.postInvalidate();
            }
        });
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.jingdong.common.nytask.widget.NYTimeDownView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (Log.D) {
                    Log.e(NYTimeDownView.TAG, "initCircleAnimator: onAnimationEnd: start");
                    Log.e(NYTimeDownView.TAG, "initCircleAnimator: onAnimationEnd: mRemainTime = " + NYTimeDownView.this.mRemainTime);
                }
                if (NYTimeDownView.this.mRemainTime == 0) {
                    NYTimeDownView.this.animationFinish();
                }
                if (Log.D) {
                    Log.e(NYTimeDownView.TAG, "initCircleAnimator: onAnimationEnd: end");
                }
            }
        });
    }

    private void initSize() {
        this.mWidth = DPIUtil.dip2px(40.0f);
        this.mHeight = DPIUtil.dip2px(40.0f);
        this.mCircleBorder = DPIUtil.dip2px(1.5f);
        this.mCircleRadius = DPIUtil.dip2px(1.5f);
        this.mCircleWidth = DPIUtil.dip2px(3.0f);
    }

    private void setTime(long j2) {
        this.mTime = j2;
        this.mRemainTime = j2;
        initAnimator();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawArc(this.mCircleRectF, 0.0f, this.mCurrentAngle, false, this.mPaint);
    }

    public void pauseTimeDown(boolean z) {
        if (z) {
            this.mUserHandlePause = true;
        }
        pauseTimeDown();
    }

    public void releaseResource() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
            this.mValueAnimator.cancel();
            this.mValueAnimator.end();
            this.mValueAnimator = null;
        }
    }

    public void resumeTimeDown(boolean z) {
        if (!this.mUserHandlePause) {
            resumeTimeDown();
        } else if (z) {
            this.mUserHandlePause = false;
            resumeTimeDown();
        }
    }

    public void setTimeDownListener(TimeDownListener timeDownListener) {
        this.mTimeDownListener = timeDownListener;
    }

    public void showTimeDown(boolean z) {
        setVisibility(z ? 0 : 8);
    }

    @Override // com.jingdong.common.nytask.inter.ITimeDown
    public void startTimeDown(long j2) {
        if (this.isTimeDownFinish) {
            return;
        }
        setTime(j2);
        showTimeDown(true);
        this.mValueAnimator.start();
    }

    public NYTimeDownView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NYTimeDownView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        init();
    }

    @Override // com.jingdong.common.nytask.inter.ITimeDown
    public void pauseTimeDown() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator == null || this.isTimeDownFinish) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            this.isPauseAnimationAboveSdk19 = true;
            valueAnimator.pause();
            return;
        }
        this.isPauseAnimationBlowSdk19 = true;
        valueAnimator.cancel();
    }

    @Override // com.jingdong.common.nytask.inter.ITimeDown
    public void resumeTimeDown() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator == null || this.isTimeDownFinish) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            if (this.isPauseAnimationAboveSdk19) {
                if (Log.D) {
                    Log.e(TAG, "startTimeDown: isPauseAnimationAboveSdk19");
                }
                this.mValueAnimator.resume();
                return;
            }
            valueAnimator.start();
        } else if (this.isPauseAnimationBlowSdk19) {
            initAnimator();
            this.isPauseAnimationBlowSdk19 = false;
        } else {
            valueAnimator.start();
        }
    }
}
