package com.jingdong.common.nytask.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes5.dex */
public abstract class DraggableView extends FrameLayout {
    public static final String TAG = "DraggableView";
    private boolean isHandleClick;
    private boolean isPortrait;
    private boolean isSwitchScreen;
    private View mAreaParent;
    private Context mContext;
    private int mExtraHorizontalSpace;
    private int mExtraVerticalSpace;
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
    private Rect mParentRect;
    private TapRunnable mTapRunnable;
    private float xR;
    private float yR;

    /* loaded from: classes5.dex */
    final class TapRunnable implements Runnable {
        TapRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DraggableView.this.isHandleClick = false;
        }
    }

    public DraggableView(@NonNull Context context) {
        this(context, null);
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
        if (Log.D) {
            Log.e(TAG, "resetOffsetAndCorrect: mParentRect.width() = " + this.mParentRect.width());
            Log.e(TAG, "resetOffsetAndCorrect: mParentRect.height() = " + this.mParentRect.height());
            Log.e(TAG, "resetOffsetAndCorrect: mParentRect.top = " + this.mParentRect.top);
            Log.e(TAG, "resetOffsetAndCorrect: mMarginRight = " + this.mMarginRight);
            Log.e(TAG, "resetOffsetAndCorrect: mMarginBottom = " + this.mMarginBottom);
            Log.e(TAG, "resetOffsetAndCorrect: getWidth = " + getWidth());
            Log.e(TAG, "resetOffsetAndCorrect: getHeight = " + getHeight());
        }
        float translationX = getTranslationX() - this.mMarginRight;
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

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawContent(canvas);
    }

    protected abstract void drawContent(Canvas canvas);

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (this.mAreaParent == null) {
            this.mAreaParent = (ViewGroup) getParent();
        }
        int[] iArr = new int[2];
        this.mAreaParent.getLocationOnScreen(iArr);
        this.mParentRect = new Rect(iArr[0] + this.mAreaParent.getPaddingLeft(), iArr[1] + this.mAreaParent.getPaddingTop(), iArr[0] + this.mAreaParent.getMeasuredWidth(), iArr[1] + this.mAreaParent.getMeasuredHeight());
        if (this.isSwitchScreen) {
            setX((this.xR * ((r7.width() - (this.mMarginRight * 2)) - getWidth())) + this.mMarginRight);
            setY((this.yR * (this.mParentRect.height() - ((this.mMarginBottom * 2) + getHeight()))) + this.mMarginBottom + this.mParentRect.top);
            this.mLastTX = getTranslationX();
            this.mLastTY = getTranslationY();
            this.isSwitchScreen = false;
        }
        resetOffsetAndCorrect();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        float rawX = (int) motionEvent.getRawX();
        float rawY = (int) motionEvent.getRawY();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
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
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
            } else if (actionMasked == 2) {
                float f2 = rawX - this.mInitX;
                float f3 = rawY - this.mInitY;
                float f4 = this.mLastTX + f2;
                float f5 = this.mLastTY + f3;
                if (Log.D) {
                    Log.e(TAG, "onTouchEvent: tx = " + f4);
                    Log.e(TAG, "onTouchEvent: ty = " + f5);
                    Log.e(TAG, "onTouchEvent: mMinOffsetX = " + this.mMinOffsetX);
                    Log.e(TAG, "onTouchEvent: mMaxOffsetX = " + this.mMaxOffsetX);
                    Log.e(TAG, "onTouchEvent: mMinOffsetY = " + this.mMinOffsetY);
                    Log.e(TAG, "onTouchEvent: mMaxOffsetY = " + this.mMaxOffsetY);
                }
                if (f4 >= this.mMinOffsetX && f4 <= this.mMaxOffsetX) {
                    setTranslationX(f4);
                }
                if (f5 >= this.mMinOffsetY && f5 <= this.mMaxOffsetY) {
                    setTranslationY(f5);
                }
                this.isHandleClick = Math.max(Math.abs(f2), Math.abs(f3)) < ((float) ViewConfiguration.get(this.mContext).getScaledTouchSlop());
            }
        } else {
            this.mInitX = rawX;
            this.mInitY = rawY;
            this.mLastTX = getTranslationX();
            this.mLastTY = getTranslationY();
            if (this.mTapRunnable == null) {
                this.mTapRunnable = new TapRunnable();
            }
            this.isHandleClick = true;
            postDelayed(this.mTapRunnable, ViewConfiguration.getLongPressTimeout());
            if (getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
        }
        return true;
    }

    public void setAreaParent(View view) {
        this.mAreaParent = view;
    }

    public void setExtraHorizontalSpace(int i2) {
        this.mExtraHorizontalSpace = i2;
    }

    public void setExtraVerticalSpace(int i2) {
        this.mExtraVerticalSpace = i2;
    }

    public void setInitPoint(float f2, float f3) {
        setTranslationX(f2);
        setTranslationY(f3);
    }

    public void setWH(int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i3;
        setLayoutParams(layoutParams);
    }

    public DraggableView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DraggableView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMinOffsetX = 0;
        this.mMaxOffsetX = 0;
        this.mMinOffsetY = 0;
        this.mMaxOffsetY = 0;
        this.xR = 1.0f;
        this.yR = 1.0f;
        this.isSwitchScreen = false;
        this.isPortrait = true;
        this.isHandleClick = true;
        this.mMarginRight = 0;
        this.mMarginBottom = 0;
        this.mExtraVerticalSpace = 0;
        this.mExtraHorizontalSpace = 0;
        this.mContext = context;
    }
}
