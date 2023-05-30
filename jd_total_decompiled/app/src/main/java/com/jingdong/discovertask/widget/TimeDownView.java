package com.jingdong.discovertask.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.R;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.discovertask.model.inter.OnTimeDownFinishListener;
import com.jingdong.discovertask.model.inter.OnTimeStatusChangedListener;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes12.dex */
public class TimeDownView extends FrameLayout {
    public static final String TAG = "TimeDownViewLive";
    private boolean isHandleClick;
    private boolean isPauseAnimationAboveSdk19;
    private boolean isPauseAnimationBlowSdk19;
    private boolean isPortrait;
    private boolean isSwitchScreen;
    private boolean isTimeDownFinish;
    private Bitmap mBgBitmap;
    private Bitmap mBitmapBottom;
    private Bitmap mBitmapTop;
    private int mBottomPaintInitOffsetY;
    private int mCircleBorder;
    private int mCircleCenterX;
    private int mCircleCenterY;
    private int mCircleRadius;
    private RectF mCircleRectF;
    private TextView mContentTV;
    private Context mContext;
    private int mCurrentAngle;
    private int mDiameter;
    private int mExtraHorizontalSpace;
    private int mExtraVerticalSpace;
    private OnTimeDownFinishListener mFinishListener;
    private Rect mFlowLine;
    private String mIconUrl;
    private float mInitX;
    private float mInitY;
    private float mLastTX;
    private float mLastTY;
    private int mMarginBottom;
    private int mMarginRight;
    private int mMaxOffsetX;
    private int mMaxOffsetY;
    private int mMinOffsetX;
    private int mMinOffsetY;
    private int mOffsetY;
    private View.OnClickListener mOnFloatViewClickListener;
    private Paint mPaint;
    private Rect mParentRect;
    private long mRemainTime;
    private int mSandMaxHeight;
    private Bitmap mSrcBitmap;
    private TapRunnable mTapRunnable;
    private long mTime;
    private OnTimeStatusChangedListener mTimeStatusChangedListener;
    private int mTopPaintInitOffsetY;
    private ValueAnimator mValueAnimator;
    private float xR;
    private float yR;

    /* loaded from: classes12.dex */
    final class TapRunnable implements Runnable {
        TapRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TimeDownView.this.isHandleClick = false;
        }
    }

    public TimeDownView(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animationFinish() {
        this.isTimeDownFinish = true;
        OnTimeDownFinishListener onTimeDownFinishListener = this.mFinishListener;
        if (onTimeDownFinishListener != null) {
            onTimeDownFinishListener.onFinish();
        }
        OnTimeStatusChangedListener onTimeStatusChangedListener = this.mTimeStatusChangedListener;
        if (onTimeStatusChangedListener != null) {
            long j2 = this.mTime;
            onTimeStatusChangedListener.onTimeOver(j2, j2 - this.mRemainTime);
        }
    }

    private void drawCircle(Canvas canvas) {
        this.mPaint.setStrokeWidth(this.mCircleBorder);
        this.mPaint.setColor(Color.parseColor("#EED241"));
        canvas.drawArc(this.mCircleRectF, -54.0f, this.mCurrentAngle, false, this.mPaint);
    }

    private void drawSand(Canvas canvas) {
        Rect rect = new Rect(0, 0, getWidth(), this.mSandMaxHeight - this.mOffsetY);
        Rect rect2 = new Rect(0, 0, getWidth(), this.mOffsetY);
        this.mBitmapTop = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        new Canvas(this.mBitmapTop).drawRect(0.0f, 0.0f, rect.width(), rect.height(), this.mPaint);
        this.mBitmapBottom = Bitmap.createBitmap(rect2.width(), rect2.height(), Bitmap.Config.ARGB_8888);
        new Canvas(this.mBitmapBottom).drawRect(0.0f, 0.0f, rect2.width(), rect2.height(), this.mPaint);
        canvas.drawBitmap(this.mBgBitmap, 0.0f, 0.0f, this.mPaint);
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null, 31);
        canvas.drawBitmap(this.mBitmapTop, 0.0f, this.mTopPaintInitOffsetY + this.mOffsetY, this.mPaint);
        canvas.drawBitmap(this.mBitmapBottom, 0.0f, this.mBottomPaintInitOffsetY - this.mOffsetY, this.mPaint);
        this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(this.mSrcBitmap, 0.0f, 0.0f, this.mPaint);
        this.mPaint.setXfermode(null);
        if (this.mOffsetY <= this.mSandMaxHeight - 3) {
            canvas.drawRect(this.mFlowLine, this.mPaint);
        }
        canvas.restoreToCount(saveLayer);
    }

    private void init(Context context) {
        this.mContext = context;
        this.mExtraVerticalSpace = DPIUtil.dip2px(233.0f);
        this.mExtraHorizontalSpace = DPIUtil.dip2px(15.0f);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.discovertask.widget.TimeDownView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TimeDownView.this.isTimeDownFinish && TimeDownView.this.mOnFloatViewClickListener != null) {
                    TimeDownView.this.mOnFloatViewClickListener.onClick(TimeDownView.this);
                }
            }
        });
    }

    private void initAnimator() {
        if (this.isTimeDownFinish) {
            return;
        }
        initCircleAnimator();
    }

    private void initCircleAnimator() {
        ValueAnimator ofInt = ValueAnimator.ofInt(0, 278);
        this.mValueAnimator = ofInt;
        ofInt.setDuration(this.mRemainTime);
        this.mValueAnimator.setInterpolator(new LinearInterpolator());
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.discovertask.widget.TimeDownView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (TimeDownView.this.isPauseAnimationBlowSdk19) {
                    return;
                }
                TimeDownView.this.mRemainTime = (int) (((float) r0.mTime) - (valueAnimator.getAnimatedFraction() * ((float) valueAnimator.getDuration())));
                TimeDownView.this.mCurrentAngle = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (Log.D) {
                    Log.e(TimeDownView.TAG, "initCircleAnimator: onAnimationUpdate: mRemainTime = " + TimeDownView.this.mRemainTime + ", mTime = " + TimeDownView.this.mTime);
                }
                TimeDownView.this.postInvalidate();
            }
        });
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.jingdong.discovertask.widget.TimeDownView.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (Log.D) {
                    Log.e(TimeDownView.TAG, "initCircleAnimator: onAnimationEnd: start");
                    Log.e(TimeDownView.TAG, "initCircleAnimator: onAnimationEnd: mRemainTime = " + TimeDownView.this.mRemainTime);
                }
                if (TimeDownView.this.mRemainTime == 0) {
                    TimeDownView.this.animationFinish();
                }
                if (Log.D) {
                    Log.e(TimeDownView.TAG, "initCircleAnimator: onAnimationEnd: end");
                }
            }
        });
    }

    private void initCircleTimeDown() {
        if (Log.D) {
            Log.e(TAG, "initCircleTimeDown: start");
        }
        setBackgroundResource(R.drawable.time_down_circle);
        if (this.mContentTV == null) {
            this.mContentTV = new TextView(this.mContext);
        }
        this.isTimeDownFinish = false;
        setWH(DPIUtil.dip2px(46.5f), DPIUtil.dip2px(56.0f));
        if (this.mContentTV.getParent() instanceof ViewGroup) {
            ((ViewGroup) this.mContentTV.getParent()).removeView(this.mContentTV);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, DPIUtil.dip2px(42.0f));
        layoutParams.gravity = 80;
        this.mContentTV.setText("\u770b\u5185\u5bb9\n\u9886\u4eac\u8c46");
        this.mContentTV.setTextSize(2, 7.0f);
        this.mContentTV.setTextColor(-1);
        this.mContentTV.setGravity(17);
        addView(this.mContentTV, layoutParams);
        this.mCircleRectF = new RectF(this.mCircleBorder, (r0 - this.mDiameter) - r2, r2 + this.mDiameter, r0 - this.mCircleBorder);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setColor(-1);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mCircleBorder);
        if (Log.D) {
            Log.e(TAG, "initCircleTimeDown: end");
        }
    }

    private void initDragFloat() {
        this.isTimeDownFinish = true;
        setWH(DPIUtil.dip2px(118.0f), DPIUtil.dip2px(126.0f));
    }

    private void initSandAnimator() {
        ValueAnimator ofInt = ValueAnimator.ofInt(1, this.mSandMaxHeight - 1);
        this.mValueAnimator = ofInt;
        ofInt.setDuration(this.mRemainTime);
        this.mValueAnimator.setInterpolator(new LinearInterpolator());
        this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.discovertask.widget.TimeDownView.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (TimeDownView.this.isPauseAnimationBlowSdk19) {
                    return;
                }
                TimeDownView.this.mRemainTime = (int) (((float) r0.mTime) - (valueAnimator.getAnimatedFraction() * ((float) valueAnimator.getDuration())));
                TimeDownView.this.mOffsetY = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                TimeDownView.this.postInvalidate();
            }
        });
        this.mValueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.jingdong.discovertask.widget.TimeDownView.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                TimeDownView.this.mRemainTime = 0L;
                TimeDownView.this.animationFinish();
            }
        });
    }

    private void initSandTimeDown() {
        this.isTimeDownFinish = false;
        setWH(DPIUtil.dip2px(39.0f), DPIUtil.dip2px(43.0f));
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setColor(-1);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mSandMaxHeight = DPIUtil.dip2px(19.0f);
        this.mTopPaintInitOffsetY = DPIUtil.dip2px(3.0f);
        this.mBottomPaintInitOffsetY = DPIUtil.dip2px(40.0f);
        int dip2px = DPIUtil.dip2px(17.0f);
        int dip2px2 = DPIUtil.dip2px(20.0f);
        this.mFlowLine = new Rect(dip2px, dip2px2, DPIUtil.dip2px(1.0f) + dip2px, DPIUtil.dip2px(19.0f) + dip2px2);
        Resources resources = getResources();
        this.mSrcBitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_sand_glass_cover, null);
        this.mBgBitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_sand_glass, null);
    }

    private void oldChangeStyle() {
        if (getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
            marginLayoutParams.width = DPIUtil.dip2px(82.0f);
            marginLayoutParams.height = DPIUtil.dip2px(77.0f);
            this.mMarginRight = DPIUtil.dip2px(36.0f) / 2;
            this.mMarginBottom = DPIUtil.dip2px(50.0f) / 2;
            setLayoutParams(marginLayoutParams);
            if (TextUtils.isEmpty(this.mIconUrl)) {
                setVisibility(8);
                return;
            }
            final ViewGroup viewGroup = (ViewGroup) getParent();
            final SimpleDraweeView simpleDraweeView = new SimpleDraweeView(this.mContext);
            viewGroup.addView(simpleDraweeView, new FrameLayout.LayoutParams(-2, -2));
            JDImageUtils.displayImage(this.mIconUrl, simpleDraweeView, new JDImageLoadingListener() { // from class: com.jingdong.discovertask.widget.TimeDownView.6
                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingCancelled(String str, View view) {
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                    if (Log.D) {
                        Log.e(TimeDownView.TAG, "onLoadingComplete: \u52a0\u8f7d\u6210\u529f");
                    }
                    simpleDraweeView.setVisibility(8);
                    viewGroup.removeView(simpleDraweeView);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingFailed(String str, View view, JDFailReason jDFailReason) {
                    if (Log.D) {
                        Log.e(TimeDownView.TAG, "onLoadingFailed: \u52a0\u8f7d\u5931\u8d25");
                    }
                    TimeDownView.this.setVisibility(8);
                    simpleDraweeView.setVisibility(8);
                    viewGroup.removeView(simpleDraweeView);
                }

                @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                public void onLoadingStarted(String str, View view) {
                }
            });
        }
    }

    private void resetOffsetAndCorrect() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        if ((layoutParams.gravity & 48) == 48) {
            int height = this.mParentRect.height();
            int height2 = this.mMarginBottom + getHeight();
            int i2 = this.mExtraVerticalSpace;
            this.mMinOffsetY = height - (height2 + i2);
            this.mMaxOffsetY = this.mMarginBottom + i2;
        } else {
            int height3 = this.mParentRect.height();
            int height4 = this.mMarginBottom + getHeight();
            int i3 = this.mExtraVerticalSpace;
            this.mMinOffsetY = -(height3 - (height4 + i3));
            this.mMaxOffsetY = -(this.mMarginBottom + i3);
        }
        int i4 = this.mMinOffsetY;
        int i5 = this.mMaxOffsetY;
        if (i4 > i5) {
            this.mMaxOffsetY = i4;
            this.mMinOffsetY = i5;
        }
        if ((layoutParams.gravity & 3) == 3) {
            int width = this.mParentRect.width();
            int width2 = this.mMarginRight + getWidth();
            int i6 = this.mExtraHorizontalSpace;
            this.mMinOffsetX = width - (width2 + i6);
            this.mMaxOffsetX = this.mMarginRight + i6;
        } else {
            int width3 = this.mParentRect.width();
            int width4 = this.mMarginRight + getWidth();
            int i7 = this.mExtraHorizontalSpace;
            this.mMinOffsetX = -(width3 - (width4 + i7));
            this.mMaxOffsetX = -(this.mMarginRight + i7);
        }
        int i8 = this.mMinOffsetX;
        int i9 = this.mMaxOffsetX;
        if (i8 > i9) {
            this.mMaxOffsetX = i8;
            this.mMinOffsetX = i9;
        }
        Log.e(TAG, "resetOffsetAndCorrect: mParentRect.width() = " + this.mParentRect.width());
        Log.e(TAG, "resetOffsetAndCorrect: mParentRect.height() = " + this.mParentRect.height());
        Log.e(TAG, "resetOffsetAndCorrect: mParentRect.top = " + this.mParentRect.top);
        Log.e(TAG, "resetOffsetAndCorrect: mMarginRight = " + this.mMarginRight);
        Log.e(TAG, "resetOffsetAndCorrect: mMarginBottom = " + this.mMarginBottom);
        Log.e(TAG, "resetOffsetAndCorrect: getWidth = " + getWidth());
        Log.e(TAG, "resetOffsetAndCorrect: getHeight = " + getHeight());
        float translationX = getTranslationX() - ((float) this.mMarginRight);
        int i10 = this.mMinOffsetX;
        if (translationX < i10) {
            setTranslationX(i10);
        } else {
            int i11 = this.mMaxOffsetX;
            if (translationX > i11) {
                setTranslationX(i11);
            } else {
                setTranslationX(translationX);
            }
        }
        float translationY = getTranslationY() - this.mMarginBottom;
        int i12 = this.mMinOffsetY;
        if (translationY < i12) {
            setTranslationY(i12);
            return;
        }
        int i13 = this.mMaxOffsetY;
        if (translationY > i13) {
            setTranslationY(i13);
        } else {
            setTranslationY(translationY);
        }
    }

    private void setWH(int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i3;
        setLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawCircle(canvas);
    }

    public TimeDownView finishListener(OnTimeDownFinishListener onTimeDownFinishListener) {
        this.mFinishListener = onTimeDownFinishListener;
        return this;
    }

    public TimeDownView gravity(int i2) {
        if (getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
            layoutParams.gravity = i2;
            setLayoutParams(layoutParams);
        }
        return this;
    }

    public TimeDownView iconUrl(String str) {
        this.mIconUrl = str;
        return this;
    }

    public TimeDownView initWithStyle(int i2) {
        if (i2 == 1) {
            initCircleTimeDown();
        } else if (i2 == 3) {
            initDragFloat();
        }
        return this;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        ViewGroup viewGroup = (ViewGroup) getParent();
        int[] iArr = new int[2];
        viewGroup.getLocationOnScreen(iArr);
        this.mParentRect = new Rect(iArr[0] + viewGroup.getPaddingLeft(), iArr[1] + viewGroup.getPaddingTop(), iArr[0] + viewGroup.getMeasuredWidth(), iArr[1] + viewGroup.getMeasuredHeight());
        if (this.isSwitchScreen) {
            setX((this.xR * ((r9.width() - (this.mMarginRight * 2)) - getWidth())) + this.mMarginRight);
            setY((this.yR * (this.mParentRect.height() - ((this.mMarginBottom * 2) + getHeight()))) + this.mMarginBottom + this.mParentRect.top);
            this.mLastTX = getTranslationX();
            this.mLastTY = getTranslationY();
            this.isSwitchScreen = false;
        }
        resetOffsetAndCorrect();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        Bitmap bitmap;
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.isTimeDownFinish || (bitmap = this.mBgBitmap) == null || this.mSrcBitmap == null) {
            return;
        }
        if (bitmap.getWidth() != i2 || this.mBgBitmap.getHeight() != i3) {
            this.mBgBitmap = translateBm(this.mBgBitmap, i2, i3);
        }
        if (this.mSrcBitmap.getWidth() == i2 && this.mSrcBitmap.getHeight() == i3) {
            return;
        }
        this.mSrcBitmap = translateBm(this.mSrcBitmap, i2, i3);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        float rawX = (int) motionEvent.getRawX();
        float rawY = (int) motionEvent.getRawY();
        if (actionMasked == 0) {
            this.mInitX = rawX;
            this.mInitY = rawY;
            this.mLastTX = getTranslationX();
            this.mLastTY = getTranslationY();
            if (this.mTapRunnable == null) {
                this.mTapRunnable = new TapRunnable();
            }
            this.isHandleClick = true;
            postDelayed(this.mTapRunnable, ViewConfiguration.getLongPressTimeout());
        } else if (actionMasked == 1) {
            if (rawX < this.mParentRect.right / 2.0f) {
                setTranslationX(this.mMinOffsetX);
                this.xR = 0.0f;
            } else {
                setTranslationX(this.mMaxOffsetX);
                this.xR = 1.0f;
            }
            this.mLastTX = getTranslationX();
            this.mLastTY = getTranslationY();
            float y = getY();
            Rect rect = this.mParentRect;
            float height = ((y - rect.top) - this.mMarginBottom) / (rect.height() - ((this.mMarginBottom * 2) + getHeight()));
            this.yR = height;
            this.yR = height <= 1.0f ? height : 1.0f;
            if (this.isHandleClick) {
                performClick();
            }
        } else if (actionMasked == 2) {
            float f2 = rawX - this.mInitX;
            float f3 = rawY - this.mInitY;
            float f4 = this.mLastTX + f2;
            float f5 = this.mLastTY + f3;
            Log.e(TAG, "onTouchEvent: tx = " + f4);
            Log.e(TAG, "onTouchEvent: ty = " + f5);
            Log.e(TAG, "onTouchEvent: mMinOffsetX = " + this.mMinOffsetX);
            Log.e(TAG, "onTouchEvent: mMaxOffsetX = " + this.mMaxOffsetX);
            Log.e(TAG, "onTouchEvent: mMinOffsetY = " + this.mMinOffsetY);
            Log.e(TAG, "onTouchEvent: mMaxOffsetY = " + this.mMaxOffsetY);
            if (f4 >= this.mMinOffsetX && f4 <= this.mMaxOffsetX) {
                setTranslationX(f4);
            }
            if (f5 >= this.mMinOffsetY && f5 <= this.mMaxOffsetY) {
                setTranslationY(f5);
            }
            this.isHandleClick = Math.max(Math.abs(f2), Math.abs(f3)) < ((float) ViewConfiguration.get(this.mContext).getScaledTouchSlop());
        }
        return true;
    }

    public void pauseTimeDown() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator == null || this.isTimeDownFinish) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            this.isPauseAnimationAboveSdk19 = true;
            valueAnimator.pause();
        } else {
            this.isPauseAnimationBlowSdk19 = true;
            valueAnimator.cancel();
        }
        OnTimeStatusChangedListener onTimeStatusChangedListener = this.mTimeStatusChangedListener;
        if (onTimeStatusChangedListener != null) {
            long j2 = this.mTime;
            onTimeStatusChangedListener.onTimePause(j2, j2 - this.mRemainTime);
        }
    }

    public void releaseResource() {
        Bitmap bitmap = this.mSrcBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.mSrcBitmap = null;
        }
        Bitmap bitmap2 = this.mBgBitmap;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.mBgBitmap = null;
        }
        Bitmap bitmap3 = this.mBitmapTop;
        if (bitmap3 != null) {
            bitmap3.recycle();
            this.mBitmapTop = null;
        }
        Bitmap bitmap4 = this.mBitmapBottom;
        if (bitmap4 != null) {
            bitmap4.recycle();
            this.mBitmapBottom = null;
        }
        TapRunnable tapRunnable = this.mTapRunnable;
        if (tapRunnable != null) {
            removeCallbacks(tapRunnable);
            this.mTapRunnable = null;
        }
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.mValueAnimator = null;
        }
        if (this.mFinishListener != null) {
            this.mFinishListener = null;
        }
        if (this.mTimeStatusChangedListener != null) {
            this.mTimeStatusChangedListener = null;
        }
        if (!this.isTimeDownFinish) {
            this.isTimeDownFinish = false;
        }
        Animation animation = getAnimation();
        if (animation != null) {
            animation.cancel();
        }
    }

    public void resumeTimeDown() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator == null || this.isTimeDownFinish) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            if (this.isPauseAnimationAboveSdk19) {
                Log.e(TAG, "startTimeDown: isPauseAnimationAboveSdk19");
                this.mValueAnimator.resume();
            } else {
                valueAnimator.start();
            }
        } else if (this.isPauseAnimationBlowSdk19) {
            initAnimator();
            this.isPauseAnimationBlowSdk19 = false;
        } else {
            valueAnimator.start();
        }
        OnTimeStatusChangedListener onTimeStatusChangedListener = this.mTimeStatusChangedListener;
        if (onTimeStatusChangedListener != null) {
            long j2 = this.mTime;
            onTimeStatusChangedListener.onTimeResume(j2, j2 - this.mRemainTime);
        }
    }

    public TimeDownView setOnFloatViewClickListener(View.OnClickListener onClickListener) {
        this.mOnFloatViewClickListener = onClickListener;
        return this;
    }

    public void setTime(long j2) {
        if (this.isTimeDownFinish) {
            return;
        }
        long j3 = j2 * 1000;
        this.mTime = j3;
        this.mRemainTime = j3;
        initAnimator();
    }

    public TimeDownView setTimeStatusChangedListener(OnTimeStatusChangedListener onTimeStatusChangedListener) {
        this.mTimeStatusChangedListener = onTimeStatusChangedListener;
        return this;
    }

    public void showTimeDown(boolean z) {
        setVisibility(z ? 0 : 8);
    }

    public void startTimeDown() {
        if (this.mValueAnimator == null || this.isTimeDownFinish) {
            return;
        }
        showTimeDown(true);
        this.mValueAnimator.start();
        OnTimeStatusChangedListener onTimeStatusChangedListener = this.mTimeStatusChangedListener;
        if (onTimeStatusChangedListener != null) {
            long j2 = this.mTime;
            onTimeStatusChangedListener.onTimeStart(j2, j2 - this.mRemainTime);
        }
    }

    public void stopTimeDown() {
        ValueAnimator valueAnimator = this.mValueAnimator;
        if (valueAnimator == null || this.isTimeDownFinish) {
            return;
        }
        valueAnimator.cancel();
    }

    public void switchScreen(boolean z) {
        this.isSwitchScreen = true;
        this.isPortrait = z;
    }

    public void taskFinishCircleStyle(String str) {
        TextView textView = this.mContentTV;
        if (textView != null) {
            textView.setTextSize(2, 13.0f);
            this.mContentTV.setText(str);
        }
    }

    public Bitmap translateBm(Bitmap bitmap, float f2, float f3) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(f2 / width, f3 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public TimeDownView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimeDownView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mTime = 0L;
        this.mOffsetY = 1;
        this.mMarginRight = 0;
        this.mMarginBottom = 0;
        this.mExtraVerticalSpace = 0;
        this.mExtraHorizontalSpace = 0;
        this.mMinOffsetX = 0;
        this.mMaxOffsetX = 0;
        this.mMinOffsetY = 0;
        this.mMaxOffsetY = 0;
        this.xR = 1.0f;
        this.yR = 1.0f;
        this.isSwitchScreen = false;
        this.isPortrait = true;
        this.isHandleClick = true;
        this.mCircleRadius = DPIUtil.dip2px(21.25f);
        this.mDiameter = DPIUtil.dip2px(42.5f);
        this.mCircleBorder = DPIUtil.dip2px(2.0f);
        this.mCircleCenterX = DPIUtil.dip2px(23.0f);
        this.mCircleCenterY = DPIUtil.dip2px(30.0f);
        init(context);
    }
}
