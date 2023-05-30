package com.jingdong.common.widget.shadow.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.CompoundButton;
import androidx.core.app.NotificationCompat;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.utils.config.UnDeviceInfo;
import com.jingdong.common.widget.shadow.utils.ColorUtils;

/* loaded from: classes12.dex */
public class JDShadowSwitch extends CompoundButton {
    public static final int DEFAULT_ANIMATION_DURATION = 250;
    public static final int DEFAULT_THUMB_MARGIN_DP = 2;
    public static final float DEFAULT_THUMB_RANGE_RATIO = 2.0f;
    public static final int DEFAULT_THUMB_SIZE_DP = 20;
    public static final int DEFAULT_TINT_COLOR = 3309506;
    private long mAnimationDuration;
    private ColorStateList mBackColor;
    private Drawable mBackDrawable;
    private int mBackHeight;
    private float mBackRadius;
    private RectF mBackRectF;
    private int mBackWidth;
    private boolean mCatch;
    private CompoundButton.OnCheckedChangeListener mChildOnCheckedChangeListener;
    private int mClickTimeout;
    private int mCurrBackColor;
    private int mCurrThumbColor;
    private Drawable mCurrentBackDrawable;
    private boolean mDrawDebugRect;
    private boolean mFadeBack;
    private boolean mIsBackUseDrawable;
    private boolean mIsThumbUseDrawable;
    private float mLastX;
    private int mNextBackColor;
    private Drawable mNextBackDrawable;
    private Layout mOffLayout;
    private int mOffTextColor;
    private Layout mOnLayout;
    private int mOnTextColor;
    private Paint mPaint;
    private RectF mPresentThumbRectF;
    private float mProgress;
    private ObjectAnimator mProgressAnimator;
    private boolean mReady;
    private Paint mRectPaint;
    private boolean mRestoring;
    private RectF mSafeRectF;
    private float mShadowAlpha;
    private int mShadowColor;
    private boolean mShadowEnable;
    private int mShadowOffsetDx;
    private int mShadowOffsetDy;
    private Paint mShadowPaint;
    private int mShadowRadius;
    private float mStartX;
    private float mStartY;
    private Drawable mTempThumbDrawable;
    private int mTextAdjust;
    private int mTextExtra;
    private float mTextHeight;
    private CharSequence mTextOff;
    private RectF mTextOffRectF;
    private CharSequence mTextOn;
    private RectF mTextOnRectF;
    private TextPaint mTextPaint;
    private int mTextThumbInset;
    private float mTextWidth;
    private ColorStateList mThumbColor;
    private Drawable mThumbDrawabeDisable;
    private Drawable mThumbDrawable;
    private int mThumbHeight;
    private RectF mThumbMargin;
    private float mThumbRadius;
    private float mThumbRangeRatio;
    private RectF mThumbRectF;
    private int mThumbWidth;
    private int mTintColor;
    private int mTouchSlop;
    private static int[] CHECKED_PRESSED_STATE = {16842912, 16842910, 16842919};
    private static int[] UNCHECKED_PRESSED_STATE = {-16842912, 16842910, 16842919};

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.jingdong.common.widget.shadow.widget.JDShadowSwitch.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        CharSequence offText;
        CharSequence onText;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            TextUtils.writeToParcel(this.onText, parcel, i2);
            TextUtils.writeToParcel(this.offText, parcel, i2);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.onText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.offText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        }
    }

    public JDShadowSwitch(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mDrawDebugRect = false;
        this.mRestoring = false;
        this.mReady = false;
        this.mCatch = false;
        init(attributeSet);
    }

    private int adjustColor(float f2, int i2) {
        return Color.argb((int) (f2 * 255.0f), Color.red(i2), Color.green(i2), Color.blue(i2));
    }

    private void catchView() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        this.mCatch = true;
    }

    private int ceil(double d) {
        return (int) Math.ceil(d);
    }

    private float getProgress() {
        return this.mProgress;
    }

    private boolean getStatusBasedOnPos() {
        return getProgress() > 0.5f;
    }

    private void init(AttributeSet attributeSet) {
        String str;
        Drawable drawable;
        float f2;
        ColorStateList colorStateList;
        String str2;
        int i2;
        int i3;
        int i4;
        Drawable drawable2;
        int i5;
        ColorStateList colorStateList2;
        float f3;
        Drawable drawable3;
        float f4;
        boolean z;
        int i6;
        int i7;
        boolean z2;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        int i8;
        int i9;
        float f10;
        int i10;
        float f11;
        ColorStateList colorStateList3;
        TypedArray obtainStyledAttributes;
        Drawable drawable4;
        setLayerType(1, null);
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mClickTimeout = ViewConfiguration.getPressedStateDuration() + ViewConfiguration.getTapTimeout();
        this.mPaint = new Paint(1);
        Paint paint = new Paint(1);
        this.mRectPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.mRectPaint.setStrokeWidth(UnDeviceInfo.getDensity());
        this.mShadowPaint = new Paint(1);
        this.mTextPaint = getPaint();
        this.mThumbRectF = new RectF();
        this.mBackRectF = new RectF();
        this.mSafeRectF = new RectF();
        this.mThumbMargin = new RectF();
        this.mTextOnRectF = new RectF();
        this.mTextOffRectF = new RectF();
        ObjectAnimator duration = ObjectAnimator.ofFloat(this, NotificationCompat.CATEGORY_PROGRESS, 0.0f, 0.0f).setDuration(250L);
        this.mProgressAnimator = duration;
        duration.setInterpolator(new AccelerateDecelerateInterpolator());
        this.mPresentThumbRectF = new RectF();
        getResources();
        float density = UnDeviceInfo.getDensity() * 2.0f;
        TypedArray obtainStyledAttributes2 = attributeSet == null ? null : getContext().obtainStyledAttributes(attributeSet, R.styleable.JDShadowSwitch);
        if (obtainStyledAttributes2 != null) {
            Drawable drawable5 = obtainStyledAttributes2.getDrawable(R.styleable.JDShadowSwitch_switchThumbDrawable);
            Drawable drawable6 = obtainStyledAttributes2.getDrawable(R.styleable.JDShadowSwitch_switchThumbDrawableDisable);
            ColorStateList colorStateList4 = obtainStyledAttributes2.getColorStateList(R.styleable.JDShadowSwitch_switchThumbColor);
            float dimension = obtainStyledAttributes2.getDimension(R.styleable.JDShadowSwitch_switchThumbMargin, density);
            float dimension2 = obtainStyledAttributes2.getDimension(R.styleable.JDShadowSwitch_switchThumbMarginLeft, dimension);
            float dimension3 = obtainStyledAttributes2.getDimension(R.styleable.JDShadowSwitch_switchThumbMarginRight, dimension);
            float dimension4 = obtainStyledAttributes2.getDimension(R.styleable.JDShadowSwitch_switchThumbMarginTop, dimension);
            float dimension5 = obtainStyledAttributes2.getDimension(R.styleable.JDShadowSwitch_switchThumbMarginBottom, dimension);
            float dimension6 = obtainStyledAttributes2.getDimension(R.styleable.JDShadowSwitch_switchThumbWidth, 0.0f);
            float dimension7 = obtainStyledAttributes2.getDimension(R.styleable.JDShadowSwitch_switchThumbHeight, 0.0f);
            float dimension8 = obtainStyledAttributes2.getDimension(R.styleable.JDShadowSwitch_switchThumbRadius, -1.0f);
            float dimension9 = obtainStyledAttributes2.getDimension(R.styleable.JDShadowSwitch_switchBackRadius, -1.0f);
            Drawable drawable7 = obtainStyledAttributes2.getDrawable(R.styleable.JDShadowSwitch_switchBackDrawable);
            ColorStateList colorStateList5 = obtainStyledAttributes2.getColorStateList(R.styleable.JDShadowSwitch_switchBackColor);
            int integer = obtainStyledAttributes2.getInteger(R.styleable.JDShadowSwitch_switchAnimationDuration, 250);
            boolean z3 = obtainStyledAttributes2.getBoolean(R.styleable.JDShadowSwitch_switchFadeBack, true);
            int color = obtainStyledAttributes2.getColor(R.styleable.JDShadowSwitch_switchTintColor, 0);
            String string = obtainStyledAttributes2.getString(R.styleable.JDShadowSwitch_switchTextOn);
            String string2 = obtainStyledAttributes2.getString(R.styleable.JDShadowSwitch_switchTextOff);
            int dimensionPixelSize = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.JDShadowSwitch_switchTextThumbInset, 0);
            int dimensionPixelSize2 = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.JDShadowSwitch_switchTextExtra, 0);
            int dimensionPixelSize3 = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.JDShadowSwitch_switchTextAdjust, 0);
            int color2 = obtainStyledAttributes2.getColor(R.styleable.JDShadowSwitch_switchShadowColor, -12303292);
            float f12 = obtainStyledAttributes2.getFloat(R.styleable.JDShadowSwitch_switchShadowColorAlpha, 0.48f);
            int dimensionPixelSize4 = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.JDShadowSwitch_switchShadowRadius, 5);
            int dimensionPixelSize5 = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.JDShadowSwitch_switchShadowOffsetDx, 0);
            int dimensionPixelSize6 = obtainStyledAttributes2.getDimensionPixelSize(R.styleable.JDShadowSwitch_switchShadowOffsetDy, 0);
            boolean z4 = obtainStyledAttributes2.getBoolean(R.styleable.JDShadowSwitch_switchShadowEnable, true);
            obtainStyledAttributes2.recycle();
            i10 = dimensionPixelSize4;
            f10 = f12;
            i9 = color2;
            i8 = integer;
            drawable = drawable7;
            z2 = z3;
            i2 = dimensionPixelSize;
            i7 = dimensionPixelSize5;
            i6 = dimensionPixelSize6;
            z = z4;
            f7 = dimension5;
            f5 = dimension9;
            f2 = dimension7;
            drawable2 = drawable6;
            str = string2;
            i3 = dimensionPixelSize2;
            f6 = dimension8;
            f9 = dimension3;
            drawable3 = drawable5;
            i4 = dimensionPixelSize3;
            f4 = dimension2;
            colorStateList = colorStateList4;
            str2 = string;
            colorStateList2 = colorStateList5;
            i5 = color;
            f8 = dimension4;
            f3 = dimension6;
        } else {
            str = null;
            drawable = null;
            f2 = 0.0f;
            colorStateList = null;
            str2 = null;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            drawable2 = null;
            i5 = 0;
            colorStateList2 = null;
            f3 = 0.0f;
            drawable3 = null;
            f4 = 0.0f;
            z = true;
            i6 = 0;
            i7 = 0;
            z2 = true;
            f5 = -1.0f;
            f6 = -1.0f;
            f7 = 0.0f;
            f8 = 0.0f;
            f9 = 0.0f;
            i8 = 250;
            i9 = -12303292;
            f10 = 0.48f;
            i10 = 5;
        }
        if (attributeSet == null) {
            colorStateList3 = colorStateList2;
            f11 = f4;
            obtainStyledAttributes = null;
        } else {
            f11 = f4;
            colorStateList3 = colorStateList2;
            obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, new int[]{16842970, 16842981});
        }
        if (obtainStyledAttributes != null) {
            drawable4 = drawable;
            boolean z5 = obtainStyledAttributes.getBoolean(0, true);
            boolean z6 = obtainStyledAttributes.getBoolean(1, z5);
            setFocusable(z5);
            setClickable(z6);
            obtainStyledAttributes.recycle();
        } else {
            drawable4 = drawable;
            setFocusable(true);
            setClickable(true);
        }
        this.mTextOn = str2;
        this.mTextOff = str;
        this.mTextThumbInset = i2;
        this.mTextExtra = i3;
        this.mTextAdjust = i4;
        this.mTempThumbDrawable = drawable3;
        this.mThumbDrawable = drawable3;
        this.mThumbDrawabeDisable = drawable2;
        this.mThumbColor = colorStateList;
        boolean z7 = drawable3 != null;
        this.mIsThumbUseDrawable = z7;
        this.mTintColor = i5;
        if (i5 == 0) {
            this.mTintColor = DEFAULT_TINT_COLOR;
        }
        if (!z7 && colorStateList == null) {
            ColorStateList generateThumbColorWithTintColor = ColorUtils.generateThumbColorWithTintColor(this.mTintColor);
            this.mThumbColor = generateThumbColorWithTintColor;
            this.mCurrThumbColor = generateThumbColorWithTintColor.getDefaultColor();
        }
        this.mThumbWidth = ceil(f3);
        this.mThumbHeight = ceil(f2);
        Drawable drawable8 = drawable4;
        this.mBackDrawable = drawable8;
        ColorStateList colorStateList6 = colorStateList3;
        this.mBackColor = colorStateList6;
        boolean z8 = drawable8 != null;
        this.mIsBackUseDrawable = z8;
        if (!z8 && colorStateList6 == null) {
            ColorStateList generateBackColorWithTintColor = ColorUtils.generateBackColorWithTintColor(this.mTintColor);
            this.mBackColor = generateBackColorWithTintColor;
            int defaultColor = generateBackColorWithTintColor.getDefaultColor();
            this.mCurrBackColor = defaultColor;
            this.mNextBackColor = this.mBackColor.getColorForState(CHECKED_PRESSED_STATE, defaultColor);
        }
        this.mThumbMargin.set(f11, f8, f9, f7);
        this.mThumbRangeRatio = this.mThumbMargin.width() >= 0.0f ? Math.max(2.0f, 1.0f) : 2.0f;
        this.mThumbRadius = f6;
        this.mBackRadius = f5;
        long j2 = i8;
        this.mAnimationDuration = j2;
        this.mFadeBack = z2;
        this.mProgressAnimator.setDuration(j2);
        float f13 = f10;
        this.mShadowAlpha = f13;
        int adjustColor = adjustColor(f13, i9);
        this.mShadowColor = adjustColor;
        this.mShadowRadius = i10;
        this.mShadowOffsetDx = i7;
        this.mShadowOffsetDy = i6;
        this.mShadowEnable = z;
        this.mShadowPaint.setColor(adjustColor);
        if (this.mShadowRadius > 0) {
            this.mShadowPaint.setMaskFilter(new BlurMaskFilter(this.mShadowRadius, BlurMaskFilter.Blur.NORMAL));
        } else {
            this.mShadowPaint.setMaskFilter(null);
        }
        if (isChecked()) {
            setProgress(1.0f);
        }
    }

    private Layout makeLayout(CharSequence charSequence) {
        return new StaticLayout(charSequence, this.mTextPaint, (int) Math.ceil(Layout.getDesiredWidth(charSequence, r2)), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    }

    private int measureHeight(int i2) {
        int size = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (this.mThumbHeight == 0 && this.mIsThumbUseDrawable) {
            this.mThumbHeight = this.mThumbDrawable.getIntrinsicHeight();
        }
        if (mode == 1073741824) {
            if (this.mThumbHeight != 0) {
                RectF rectF = this.mThumbMargin;
                this.mBackHeight = ceil(r6 + rectF.top + rectF.bottom);
                this.mBackHeight = ceil(Math.max(r6, this.mTextHeight));
                if ((((r6 + getPaddingTop()) + getPaddingBottom()) - Math.min(0.0f, this.mThumbMargin.top)) - Math.min(0.0f, this.mThumbMargin.bottom) > size) {
                    this.mThumbHeight = 0;
                }
            }
            if (this.mThumbHeight == 0) {
                int ceil = ceil(((size - getPaddingTop()) - getPaddingBottom()) + Math.min(0.0f, this.mThumbMargin.top) + Math.min(0.0f, this.mThumbMargin.bottom));
                this.mBackHeight = ceil;
                if (ceil < 0) {
                    this.mBackHeight = 0;
                    this.mThumbHeight = 0;
                    return size;
                }
                RectF rectF2 = this.mThumbMargin;
                this.mThumbHeight = ceil((ceil - rectF2.top) - rectF2.bottom);
            }
            if (this.mThumbHeight < 0) {
                this.mBackHeight = 0;
                this.mThumbHeight = 0;
                return size;
            }
            return size;
        }
        if (this.mThumbHeight == 0) {
            this.mThumbHeight = ceil(UnDeviceInfo.getDensity() * 20.0f);
        }
        RectF rectF3 = this.mThumbMargin;
        int ceil2 = ceil(this.mThumbHeight + rectF3.top + rectF3.bottom);
        this.mBackHeight = ceil2;
        if (ceil2 < 0) {
            this.mBackHeight = 0;
            this.mThumbHeight = 0;
            return size;
        }
        int ceil3 = ceil(this.mTextHeight - ceil2);
        if (ceil3 > 0) {
            this.mBackHeight += ceil3;
            this.mThumbHeight += ceil3;
        }
        int max = Math.max(this.mThumbHeight, this.mBackHeight);
        return Math.max(Math.max(max, getPaddingTop() + max + getPaddingBottom()), getSuggestedMinimumHeight());
    }

    private int measureWidth(int i2) {
        int size = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (this.mThumbWidth == 0 && this.mIsThumbUseDrawable) {
            this.mThumbWidth = this.mThumbDrawable.getIntrinsicWidth();
        }
        int ceil = ceil(this.mTextWidth);
        if (this.mThumbRangeRatio == 0.0f) {
            this.mThumbRangeRatio = 2.0f;
        }
        if (mode == 1073741824) {
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            if (this.mThumbWidth == 0) {
                this.mThumbWidth = ceil(UnDeviceInfo.getDensity() * 20.0f);
            }
            RectF rectF = this.mThumbMargin;
            float f2 = rectF.left;
            if (f2 > 0.0f) {
                f2 = 0.0f;
            }
            this.mBackWidth = ceil(paddingLeft + f2 + (rectF.right <= 0.0f ? r1 : 0.0f));
            return size;
        }
        if (this.mThumbWidth == 0) {
            this.mThumbWidth = ceil(UnDeviceInfo.getDensity() * 20.0f);
        }
        if (this.mThumbRangeRatio == 0.0f) {
            this.mThumbRangeRatio = 2.0f;
        }
        int ceil2 = ceil(this.mThumbWidth * this.mThumbRangeRatio);
        RectF rectF2 = this.mThumbMargin;
        int ceil3 = ceil((ceil + this.mTextExtra) - (((ceil2 - this.mThumbWidth) + Math.max(rectF2.left, rectF2.right)) + this.mTextThumbInset));
        float f3 = ceil2;
        RectF rectF3 = this.mThumbMargin;
        int ceil4 = ceil(rectF3.left + f3 + rectF3.right + Math.max(0, ceil3));
        this.mBackWidth = ceil4;
        if (ceil4 < 0) {
            this.mThumbWidth = 0;
            this.mBackWidth = 0;
            return size;
        }
        int ceil5 = ceil(f3 + Math.max(0.0f, this.mThumbMargin.left) + Math.max(0.0f, this.mThumbMargin.right) + Math.max(0, ceil3));
        return Math.max(ceil5, getPaddingLeft() + ceil5 + getPaddingRight());
    }

    private void setDrawableState(Drawable drawable) {
        if (drawable != null) {
            drawable.setState(getDrawableState());
            invalidate();
        }
    }

    private void setProgress(float f2) {
        if (f2 > 1.0f) {
            f2 = 1.0f;
        } else if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.mProgress = f2;
        invalidate();
    }

    private void setup() {
        int i2;
        float paddingTop;
        float paddingLeft;
        if (this.mThumbWidth == 0 || (i2 = this.mThumbHeight) == 0 || this.mBackWidth == 0 || this.mBackHeight == 0) {
            return;
        }
        if (this.mThumbRadius == -1.0f) {
            this.mThumbRadius = Math.min(r0, i2) / 2;
        }
        if (this.mBackRadius == -1.0f) {
            this.mBackRadius = Math.min(this.mBackWidth, this.mBackHeight) / 2;
        }
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int ceil = ceil((this.mBackWidth - Math.min(0.0f, this.mThumbMargin.left)) - Math.min(0.0f, this.mThumbMargin.right));
        if (measuredHeight <= ceil((this.mBackHeight - Math.min(0.0f, this.mThumbMargin.top)) - Math.min(0.0f, this.mThumbMargin.bottom))) {
            paddingTop = getPaddingTop() + Math.max(0.0f, this.mThumbMargin.top);
        } else {
            paddingTop = (((measuredHeight - r3) + 1) / 2) + getPaddingTop() + Math.max(0.0f, this.mThumbMargin.top);
        }
        if (measuredWidth <= this.mBackWidth) {
            paddingLeft = getPaddingLeft() + Math.max(0.0f, this.mThumbMargin.left);
        } else {
            paddingLeft = (((measuredWidth - ceil) + 1) / 2) + getPaddingLeft() + Math.max(0.0f, this.mThumbMargin.left);
        }
        this.mThumbRectF.set(paddingLeft, paddingTop, this.mThumbWidth + paddingLeft, this.mThumbHeight + paddingTop);
        RectF rectF = this.mThumbRectF;
        float f2 = rectF.left;
        RectF rectF2 = this.mThumbMargin;
        float f3 = f2 - rectF2.left;
        RectF rectF3 = this.mBackRectF;
        float f4 = rectF.top;
        float f5 = rectF2.top;
        rectF3.set(f3, f4 - f5, this.mBackWidth + f3, (f4 - f5) + this.mBackHeight);
        RectF rectF4 = this.mSafeRectF;
        RectF rectF5 = this.mThumbRectF;
        rectF4.set(rectF5.left, 0.0f, (this.mBackRectF.right - this.mThumbMargin.right) - rectF5.width(), 0.0f);
        this.mBackRadius = Math.min(Math.min(this.mBackRectF.width(), this.mBackRectF.height()) / 2.0f, this.mBackRadius);
        Drawable drawable = this.mBackDrawable;
        if (drawable != null) {
            RectF rectF6 = this.mBackRectF;
            drawable.setBounds((int) rectF6.left, (int) rectF6.top, ceil(rectF6.right), ceil(this.mBackRectF.bottom));
        }
        if (this.mOnLayout != null) {
            RectF rectF7 = this.mBackRectF;
            float width = (rectF7.left + (((((rectF7.width() + this.mTextThumbInset) - this.mThumbWidth) - this.mThumbMargin.right) - this.mOnLayout.getWidth()) / 2.0f)) - this.mTextAdjust;
            RectF rectF8 = this.mBackRectF;
            float height = rectF8.top + ((rectF8.height() - this.mOnLayout.getHeight()) / 2.0f);
            this.mTextOnRectF.set(width, height, this.mOnLayout.getWidth() + width, this.mOnLayout.getHeight() + height);
        }
        if (this.mOffLayout != null) {
            RectF rectF9 = this.mBackRectF;
            float width2 = ((rectF9.right - (((((rectF9.width() + this.mTextThumbInset) - this.mThumbWidth) - this.mThumbMargin.left) - this.mOffLayout.getWidth()) / 2.0f)) - this.mOffLayout.getWidth()) + this.mTextAdjust;
            RectF rectF10 = this.mBackRectF;
            float height2 = rectF10.top + ((rectF10.height() - this.mOffLayout.getHeight()) / 2.0f);
            this.mTextOffRectF.set(width2, height2, this.mOffLayout.getWidth() + width2, this.mOffLayout.getHeight() + height2);
        }
        this.mReady = true;
    }

    protected void animateToState(boolean z) {
        ObjectAnimator objectAnimator = this.mProgressAnimator;
        if (objectAnimator == null) {
            return;
        }
        if (objectAnimator.isRunning()) {
            this.mProgressAnimator.cancel();
        }
        this.mProgressAnimator.setDuration(this.mAnimationDuration);
        if (z) {
            this.mProgressAnimator.setFloatValues(this.mProgress, 1.0f);
        } else {
            this.mProgressAnimator.setFloatValues(this.mProgress, 0.0f);
        }
        this.mProgressAnimator.start();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        super.drawableStateChanged();
        if (!this.mIsThumbUseDrawable && (colorStateList2 = this.mThumbColor) != null) {
            this.mCurrThumbColor = colorStateList2.getColorForState(getDrawableState(), this.mCurrThumbColor);
        } else {
            setDrawableState(this.mThumbDrawable);
        }
        int[] iArr = isChecked() ? UNCHECKED_PRESSED_STATE : CHECKED_PRESSED_STATE;
        ColorStateList textColors = getTextColors();
        if (textColors != null) {
            int defaultColor = textColors.getDefaultColor();
            this.mOnTextColor = textColors.getColorForState(CHECKED_PRESSED_STATE, defaultColor);
            this.mOffTextColor = textColors.getColorForState(UNCHECKED_PRESSED_STATE, defaultColor);
        }
        if (!this.mIsBackUseDrawable && (colorStateList = this.mBackColor) != null) {
            int colorForState = colorStateList.getColorForState(getDrawableState(), this.mCurrBackColor);
            this.mCurrBackColor = colorForState;
            this.mNextBackColor = this.mBackColor.getColorForState(iArr, colorForState);
            return;
        }
        Drawable drawable = this.mBackDrawable;
        if ((drawable instanceof StateListDrawable) && this.mFadeBack) {
            drawable.setState(iArr);
            this.mNextBackDrawable = this.mBackDrawable.getCurrent().mutate();
        } else {
            this.mNextBackDrawable = null;
        }
        setDrawableState(this.mBackDrawable);
        Drawable drawable2 = this.mBackDrawable;
        if (drawable2 != null) {
            this.mCurrentBackDrawable = drawable2.getCurrent().mutate();
        }
    }

    public long getAnimationDuration() {
        return this.mAnimationDuration;
    }

    public ColorStateList getBackColor() {
        return this.mBackColor;
    }

    public Drawable getBackDrawable() {
        return this.mBackDrawable;
    }

    public float getBackRadius() {
        return this.mBackRadius;
    }

    public PointF getBackSizeF() {
        return new PointF(this.mBackRectF.width(), this.mBackRectF.height());
    }

    public CharSequence getTextOff() {
        return this.mTextOff;
    }

    public CharSequence getTextOn() {
        return this.mTextOn;
    }

    public ColorStateList getThumbColor() {
        return this.mThumbColor;
    }

    public Drawable getThumbDrawable() {
        return this.mThumbDrawable;
    }

    public float getThumbHeight() {
        return this.mThumbHeight;
    }

    public RectF getThumbMargin() {
        return this.mThumbMargin;
    }

    public float getThumbRadius() {
        return this.mThumbRadius;
    }

    public float getThumbRangeRatio() {
        return this.mThumbRangeRatio;
    }

    public float getThumbWidth() {
        return this.mThumbWidth;
    }

    public int getTintColor() {
        return this.mTintColor;
    }

    public boolean isDrawDebugRect() {
        return this.mDrawDebugRect;
    }

    public boolean isFadeBack() {
        return this.mFadeBack;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0131  */
    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void onDraw(Canvas canvas) {
        float f2;
        float f3;
        float progress;
        super.onDraw(canvas);
        if (!this.mReady) {
            setup();
        }
        if (this.mReady) {
            if (this.mIsBackUseDrawable) {
                if (this.mFadeBack && this.mCurrentBackDrawable != null && this.mNextBackDrawable != null) {
                    Drawable drawable = isChecked() ? this.mCurrentBackDrawable : this.mNextBackDrawable;
                    Drawable drawable2 = isChecked() ? this.mNextBackDrawable : this.mCurrentBackDrawable;
                    int progress2 = (int) (getProgress() * 255.0f);
                    drawable.setAlpha(progress2);
                    drawable.draw(canvas);
                    drawable2.setAlpha(255 - progress2);
                    drawable2.draw(canvas);
                } else {
                    this.mBackDrawable.setAlpha(255);
                    this.mBackDrawable.draw(canvas);
                }
            } else if (this.mFadeBack) {
                int i2 = isChecked() ? this.mCurrBackColor : this.mNextBackColor;
                int i3 = isChecked() ? this.mNextBackColor : this.mCurrBackColor;
                int progress3 = (int) (getProgress() * 255.0f);
                this.mPaint.setARGB((Color.alpha(i2) * progress3) / 255, Color.red(i2), Color.green(i2), Color.blue(i2));
                RectF rectF = this.mBackRectF;
                float f4 = this.mBackRadius;
                canvas.drawRoundRect(rectF, f4, f4, this.mPaint);
                this.mPaint.setARGB((Color.alpha(i3) * (255 - progress3)) / 255, Color.red(i3), Color.green(i3), Color.blue(i3));
                RectF rectF2 = this.mBackRectF;
                float f5 = this.mBackRadius;
                canvas.drawRoundRect(rectF2, f5, f5, this.mPaint);
                this.mPaint.setAlpha(255);
            } else {
                this.mPaint.setColor(this.mCurrBackColor);
                RectF rectF3 = this.mBackRectF;
                float f6 = this.mBackRadius;
                canvas.drawRoundRect(rectF3, f6, f6, this.mPaint);
            }
            Layout layout = ((double) getProgress()) > 0.5d ? this.mOnLayout : this.mOffLayout;
            RectF rectF4 = ((double) getProgress()) > 0.5d ? this.mTextOnRectF : this.mTextOffRectF;
            if (layout != null && rectF4 != null) {
                int i4 = (getProgress() > 0.75d ? 1 : (getProgress() == 0.75d ? 0 : -1));
                float progress4 = getProgress();
                if (i4 >= 0) {
                    f3 = progress4 * 4.0f;
                    progress = 3.0f;
                } else if (progress4 < 0.25d) {
                    f3 = 1.0f;
                    progress = getProgress() * 4.0f;
                } else {
                    f2 = 0.0f;
                    int i5 = (int) (f2 * 255.0f);
                    int i6 = ((double) getProgress()) <= 0.5d ? this.mOnTextColor : this.mOffTextColor;
                    layout.getPaint().setARGB((Color.alpha(i6) * i5) / 255, Color.red(i6), Color.green(i6), Color.blue(i6));
                    canvas.save();
                    canvas.translate(rectF4.left, rectF4.top);
                    layout.draw(canvas);
                    canvas.restore();
                }
                f2 = f3 - progress;
                int i52 = (int) (f2 * 255.0f);
                if (((double) getProgress()) <= 0.5d) {
                }
                layout.getPaint().setARGB((Color.alpha(i6) * i52) / 255, Color.red(i6), Color.green(i6), Color.blue(i6));
                canvas.save();
                canvas.translate(rectF4.left, rectF4.top);
                layout.draw(canvas);
                canvas.restore();
            }
            this.mPresentThumbRectF.set(this.mThumbRectF);
            this.mPresentThumbRectF.offset(this.mProgress * this.mSafeRectF.width(), 0.0f);
            if (this.mIsThumbUseDrawable) {
                RectF rectF5 = this.mPresentThumbRectF;
                float f7 = rectF5.left;
                int i7 = this.mShadowOffsetDx;
                float f8 = rectF5.top;
                int i8 = this.mShadowOffsetDy;
                canvas.drawOval(new RectF(f7 + i7, f8 + i8, rectF5.right + i7, rectF5.bottom + i8), this.mShadowPaint);
                if (isChecked()) {
                    Drawable drawable3 = this.mTempThumbDrawable;
                    if (drawable3 != null) {
                        this.mThumbDrawable = drawable3;
                    }
                } else {
                    Drawable drawable4 = this.mThumbDrawabeDisable;
                    if (drawable4 != null) {
                        this.mThumbDrawable = drawable4;
                    }
                }
                Drawable drawable5 = this.mThumbDrawable;
                RectF rectF6 = this.mPresentThumbRectF;
                drawable5.setBounds((int) rectF6.left, (int) rectF6.top, ceil(rectF6.right), ceil(this.mPresentThumbRectF.bottom));
                this.mThumbDrawable.draw(canvas);
            } else {
                RectF rectF7 = this.mPresentThumbRectF;
                float f9 = rectF7.left;
                int i9 = this.mShadowOffsetDx;
                float f10 = rectF7.top;
                int i10 = this.mShadowOffsetDy;
                RectF rectF8 = new RectF(f9 + i9, f10 + i10, rectF7.right + i9, rectF7.bottom + i10);
                float f11 = this.mThumbRadius;
                canvas.drawRoundRect(rectF8, f11, f11, this.mShadowPaint);
                this.mPaint.setColor(this.mCurrThumbColor);
                RectF rectF9 = this.mPresentThumbRectF;
                float f12 = this.mThumbRadius;
                canvas.drawRoundRect(rectF9, f12, f12, this.mPaint);
            }
            if (this.mDrawDebugRect) {
                this.mRectPaint.setColor(Color.parseColor("#AA0000"));
                canvas.drawRect(this.mBackRectF, this.mRectPaint);
                this.mRectPaint.setColor(Color.parseColor("#0000FF"));
                canvas.drawRect(this.mPresentThumbRectF, this.mRectPaint);
                this.mRectPaint.setColor(Color.parseColor(JDDarkUtil.COLOR_0000000));
                RectF rectF10 = this.mSafeRectF;
                float f13 = rectF10.left;
                float f14 = this.mThumbRectF.top;
                canvas.drawLine(f13, f14, rectF10.right, f14, this.mRectPaint);
                this.mRectPaint.setColor(Color.parseColor("#00CC00"));
                canvas.drawRect(((double) getProgress()) > 0.5d ? this.mTextOnRectF : this.mTextOffRectF, this.mRectPaint);
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i2, int i3) {
        if (this.mOnLayout == null && !TextUtils.isEmpty(this.mTextOn)) {
            this.mOnLayout = makeLayout(this.mTextOn);
        }
        if (this.mOffLayout == null && !TextUtils.isEmpty(this.mTextOff)) {
            this.mOffLayout = makeLayout(this.mTextOff);
        }
        float width = this.mOnLayout != null ? r0.getWidth() : 0.0f;
        float width2 = this.mOffLayout != null ? r2.getWidth() : 0.0f;
        if (width == 0.0f && width2 == 0.0f) {
            this.mTextWidth = 0.0f;
        } else {
            this.mTextWidth = Math.max(width, width2);
        }
        float height = this.mOnLayout != null ? r0.getHeight() : 0.0f;
        float height2 = this.mOffLayout != null ? r2.getHeight() : 0.0f;
        if (height == 0.0f && height2 == 0.0f) {
            this.mTextHeight = 0.0f;
        } else {
            this.mTextHeight = Math.max(height, height2);
        }
        setMeasuredDimension(measureWidth(i2), measureHeight(i3));
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        setText(savedState.onText, savedState.offText);
        this.mRestoring = true;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mRestoring = false;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.onText = this.mTextOn;
        savedState.offText = this.mTextOff;
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 == i4 && i3 == i5) {
            return;
        }
        setup();
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0034, code lost:
        if (r0 != 3) goto L47;
     */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isEnabled() && isClickable() && isFocusable() && this.mReady) {
            int action = motionEvent.getAction();
            float x = motionEvent.getX() - this.mStartX;
            float y = motionEvent.getY() - this.mStartY;
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        float x2 = motionEvent.getX();
                        setProgress(getProgress() + ((x2 - this.mLastX) / this.mSafeRectF.width()));
                        if (!this.mCatch && (Math.abs(x) > this.mTouchSlop / 2 || Math.abs(y) > this.mTouchSlop / 2)) {
                            if (y != 0.0f && Math.abs(x) <= Math.abs(y)) {
                                if (Math.abs(y) > Math.abs(x)) {
                                    return false;
                                }
                            } else {
                                catchView();
                            }
                        }
                        this.mLastX = x2;
                    }
                }
                this.mCatch = false;
                setPressed(false);
                float eventTime = (float) (motionEvent.getEventTime() - motionEvent.getDownTime());
                if (Math.abs(x) < this.mTouchSlop && Math.abs(y) < this.mTouchSlop && eventTime < this.mClickTimeout) {
                    performClick();
                } else {
                    boolean statusBasedOnPos = getStatusBasedOnPos();
                    if (statusBasedOnPos != isChecked()) {
                        playSoundEffect(0);
                        setChecked(statusBasedOnPos);
                    } else {
                        animateToState(statusBasedOnPos);
                    }
                }
            } else {
                this.mStartX = motionEvent.getX();
                this.mStartY = motionEvent.getY();
                this.mLastX = this.mStartX;
                setPressed(true);
            }
            return true;
        }
        return false;
    }

    @Override // android.widget.CompoundButton, android.view.View
    public boolean performClick() {
        return super.performClick();
    }

    public void setAnimationDuration(long j2) {
        this.mAnimationDuration = j2;
    }

    public void setBackColor(ColorStateList colorStateList) {
        this.mBackColor = colorStateList;
        if (colorStateList != null) {
            setBackDrawable(null);
        }
        invalidate();
    }

    public void setBackColorRes(int i2) {
        setBackColor(getContext().getResources().getColorStateList(i2));
    }

    public void setBackDrawable(Drawable drawable) {
        this.mBackDrawable = drawable;
        this.mIsBackUseDrawable = drawable != null;
        refreshDrawableState();
        this.mReady = false;
        requestLayout();
        invalidate();
    }

    public void setBackDrawableRes(int i2) {
        setBackDrawable(getContext().getResources().getDrawable(i2));
    }

    public void setBackRadius(float f2) {
        this.mBackRadius = f2;
        if (this.mIsBackUseDrawable) {
            return;
        }
        invalidate();
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        if (isChecked() != z) {
            animateToState(z);
        }
        if (this.mRestoring) {
            setCheckedImmediatelyNoEvent(z);
        } else {
            super.setChecked(z);
        }
    }

    public void setCheckedImmediately(boolean z) {
        super.setChecked(z);
        ObjectAnimator objectAnimator = this.mProgressAnimator;
        if (objectAnimator != null && objectAnimator.isRunning()) {
            this.mProgressAnimator.cancel();
        }
        setProgress(z ? 1.0f : 0.0f);
        invalidate();
    }

    public void setCheckedImmediatelyNoEvent(boolean z) {
        if (this.mChildOnCheckedChangeListener == null) {
            setCheckedImmediately(z);
            return;
        }
        super.setOnCheckedChangeListener(null);
        setCheckedImmediately(z);
        super.setOnCheckedChangeListener(this.mChildOnCheckedChangeListener);
    }

    public void setCheckedNoEvent(boolean z) {
        if (this.mChildOnCheckedChangeListener == null) {
            setChecked(z);
            return;
        }
        super.setOnCheckedChangeListener(null);
        setChecked(z);
        super.setOnCheckedChangeListener(this.mChildOnCheckedChangeListener);
    }

    public void setDrawDebugRect(boolean z) {
        this.mDrawDebugRect = z;
        invalidate();
    }

    public void setFadeBack(boolean z) {
        this.mFadeBack = z;
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        super.setOnCheckedChangeListener(onCheckedChangeListener);
        this.mChildOnCheckedChangeListener = onCheckedChangeListener;
    }

    public void setText(CharSequence charSequence, CharSequence charSequence2) {
        this.mTextOn = charSequence;
        this.mTextOff = charSequence2;
        this.mOnLayout = null;
        this.mOffLayout = null;
        this.mReady = false;
        requestLayout();
        invalidate();
    }

    public void setTextAdjust(int i2) {
        this.mTextAdjust = i2;
        this.mReady = false;
        requestLayout();
        invalidate();
    }

    public void setTextExtra(int i2) {
        this.mTextExtra = i2;
        this.mReady = false;
        requestLayout();
        invalidate();
    }

    public void setTextThumbInset(int i2) {
        this.mTextThumbInset = i2;
        this.mReady = false;
        requestLayout();
        invalidate();
    }

    public void setThumbColor(ColorStateList colorStateList) {
        this.mThumbColor = colorStateList;
        if (colorStateList != null) {
            setThumbDrawable(null);
        }
        invalidate();
    }

    public void setThumbColorRes(int i2) {
        setThumbColor(getContext().getResources().getColorStateList(i2));
    }

    public void setThumbDrawable(Drawable drawable) {
        this.mThumbDrawable = drawable;
        this.mIsThumbUseDrawable = drawable != null;
        refreshDrawableState();
        this.mReady = false;
        requestLayout();
        invalidate();
    }

    public void setThumbDrawableRes(int i2) {
        setThumbDrawable(getContext().getResources().getDrawable(i2));
    }

    public void setThumbMargin(RectF rectF) {
        if (rectF == null) {
            setThumbMargin(0.0f, 0.0f, 0.0f, 0.0f);
        } else {
            setThumbMargin(rectF.left, rectF.top, rectF.right, rectF.bottom);
        }
    }

    public void setThumbRadius(float f2) {
        this.mThumbRadius = f2;
        if (this.mIsThumbUseDrawable) {
            return;
        }
        invalidate();
    }

    public void setThumbRangeRatio(float f2) {
        this.mThumbRangeRatio = f2;
        this.mReady = false;
        requestLayout();
    }

    public void setThumbSize(int i2, int i3) {
        this.mThumbWidth = i2;
        this.mThumbHeight = i3;
        this.mReady = false;
        requestLayout();
    }

    public void setTintColor(int i2) {
        this.mTintColor = i2;
        this.mThumbColor = ColorUtils.generateThumbColorWithTintColor(i2);
        this.mBackColor = ColorUtils.generateBackColorWithTintColor(this.mTintColor);
        this.mIsBackUseDrawable = false;
        this.mIsThumbUseDrawable = false;
        refreshDrawableState();
        invalidate();
    }

    public void toggleImmediately() {
        setCheckedImmediately(!isChecked());
    }

    public void toggleImmediatelyNoEvent() {
        if (this.mChildOnCheckedChangeListener == null) {
            toggleImmediately();
            return;
        }
        super.setOnCheckedChangeListener(null);
        toggleImmediately();
        super.setOnCheckedChangeListener(this.mChildOnCheckedChangeListener);
    }

    public void toggleNoEvent() {
        if (this.mChildOnCheckedChangeListener == null) {
            toggle();
            return;
        }
        super.setOnCheckedChangeListener(null);
        toggle();
        super.setOnCheckedChangeListener(this.mChildOnCheckedChangeListener);
    }

    public void setThumbMargin(float f2, float f3, float f4, float f5) {
        this.mThumbMargin.set(f2, f3, f4, f5);
        this.mReady = false;
        requestLayout();
    }

    public JDShadowSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDrawDebugRect = false;
        this.mRestoring = false;
        this.mReady = false;
        this.mCatch = false;
        init(attributeSet);
    }

    public JDShadowSwitch(Context context) {
        super(context);
        this.mDrawDebugRect = false;
        this.mRestoring = false;
        this.mReady = false;
        this.mCatch = false;
        init(null);
    }
}
