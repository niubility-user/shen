package com.jd.aips.verify.face.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.jd.aips.verify.identity.R;

/* loaded from: classes12.dex */
public class CircleProgress extends View {
    private static final String TAG = CircleProgress.class.getSimpleName();
    private boolean antiAlias;
    private long mAnimTime;
    private ValueAnimator mAnimator;
    private Paint mArcPaint;
    private float mArcWidth;
    private int mBgArcColor;
    private Paint mBgArcPaint;
    private float mBgArcWidth;
    private Point mCenterPoint;
    private Context mContext;
    private int mDefaultSize;
    private int[] mGradientColors;
    private CharSequence mHint;
    private int mHintColor;
    private float mHintOffset;
    private TextPaint mHintPaint;
    private float mHintSize;
    private float mMaxValue;
    private float mPercent;
    private int mPrecision;
    private String mPrecisionFormat;
    private float mRadius;
    private RectF mRectF;
    private float mStartAngle;
    private float mSweepAngle;
    private SweepGradient mSweepGradient;
    private float mTextOffsetPercentInRadius;
    private CharSequence mUnit;
    private int mUnitColor;
    private float mUnitOffset;
    private TextPaint mUnitPaint;
    private float mUnitSize;
    private float mValue;
    private int mValueColor;
    private float mValueOffset;
    private TextPaint mValuePaint;
    private float mValueSize;

    public CircleProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mValue = 0.0f;
        this.mGradientColors = new int[]{Color.parseColor("#FF4EE4A4"), Color.parseColor("#FF1DB270")};
        this.mPercent = 0.0f;
        init(context, attributeSet);
    }

    private void drawArc(Canvas canvas) {
        canvas.save();
        float f2 = this.mSweepAngle * this.mPercent;
        float f3 = this.mStartAngle;
        Point point2 = this.mCenterPoint;
        canvas.rotate(f3, point2.x, point2.y);
        canvas.drawArc(this.mRectF, f2, (this.mSweepAngle - f2) + 2.0f, false, this.mBgArcPaint);
        if (f2 > 0.0f) {
            canvas.drawArc(this.mRectF, 2.0f, f2, false, this.mArcPaint);
        }
        canvas.restore();
    }

    private void drawText(Canvas canvas) {
        canvas.drawText(String.format(this.mPrecisionFormat, Float.valueOf(this.mValue)), this.mCenterPoint.x, this.mValueOffset, this.mValuePaint);
        CharSequence charSequence = this.mHint;
        if (charSequence != null) {
            canvas.drawText(charSequence.toString(), this.mCenterPoint.x, this.mHintOffset, this.mHintPaint);
        }
        CharSequence charSequence2 = this.mUnit;
        if (charSequence2 != null) {
            canvas.drawText(charSequence2.toString(), this.mCenterPoint.x, this.mUnitOffset, this.mUnitPaint);
        }
    }

    private float getBaselineOffsetFromY(Paint paint) {
        return MiscUtil.measureTextHeight(paint) / 2.0f;
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.mContext = context;
        this.mDefaultSize = MiscUtil.dipToPx(context, 150.0f);
        this.mAnimator = new ValueAnimator();
        this.mRectF = new RectF();
        this.mCenterPoint = new Point();
        initAttrs(attributeSet);
        initPaint();
        reset();
    }

    private void initAttrs(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.vf_CircleProgressBar);
        this.antiAlias = obtainStyledAttributes.getBoolean(R.styleable.vf_CircleProgressBar_vf_antiAlias, true);
        this.mHint = obtainStyledAttributes.getString(R.styleable.vf_CircleProgressBar_vf_hint);
        this.mHintColor = obtainStyledAttributes.getColor(R.styleable.vf_CircleProgressBar_vf_hintColor, -16777216);
        this.mHintSize = obtainStyledAttributes.getDimension(R.styleable.vf_CircleProgressBar_vf_hintSize, 15.0f);
        this.mValue = obtainStyledAttributes.getFloat(R.styleable.vf_CircleProgressBar_vf_value, 0.0f);
        this.mMaxValue = obtainStyledAttributes.getFloat(R.styleable.vf_CircleProgressBar_vf_maxValue, 100.0f);
        int i2 = obtainStyledAttributes.getInt(R.styleable.vf_CircleProgressBar_vf_precision, 0);
        this.mPrecision = i2;
        this.mPrecisionFormat = MiscUtil.getPrecisionFormat(i2);
        this.mValueColor = obtainStyledAttributes.getColor(R.styleable.vf_CircleProgressBar_vf_valueColor, -16777216);
        this.mValueSize = obtainStyledAttributes.getDimension(R.styleable.vf_CircleProgressBar_vf_valueSize, 15.0f);
        this.mUnit = obtainStyledAttributes.getString(R.styleable.vf_CircleProgressBar_vf_unit);
        this.mUnitColor = obtainStyledAttributes.getColor(R.styleable.vf_CircleProgressBar_vf_unitColor, -16777216);
        this.mUnitSize = obtainStyledAttributes.getDimension(R.styleable.vf_CircleProgressBar_vf_unitSize, 30.0f);
        this.mArcWidth = obtainStyledAttributes.getDimension(R.styleable.vf_CircleProgressBar_vf_arcWidth, 15.0f);
        this.mStartAngle = obtainStyledAttributes.getFloat(R.styleable.vf_CircleProgressBar_vf_startAngle, 102.0f);
        this.mSweepAngle = obtainStyledAttributes.getFloat(R.styleable.vf_CircleProgressBar_vf_sweepAngle, 332.0f);
        this.mBgArcColor = obtainStyledAttributes.getColor(R.styleable.vf_CircleProgressBar_vf_bgArcColor, -1);
        this.mBgArcWidth = obtainStyledAttributes.getDimension(R.styleable.vf_CircleProgressBar_vf_bgArcWidth, 15.0f);
        this.mTextOffsetPercentInRadius = obtainStyledAttributes.getFloat(R.styleable.vf_CircleProgressBar_vf_textOffsetPercentInRadius, 0.33f);
        this.mAnimTime = obtainStyledAttributes.getInt(R.styleable.vf_CircleProgressBar_vf_animTime, 200);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.vf_CircleProgressBar_vf_arcColors, 0);
        if (resourceId != 0) {
            try {
                int[] intArray = getResources().getIntArray(resourceId);
                if (intArray.length == 0) {
                    int color = getResources().getColor(resourceId);
                    this.mGradientColors = r2;
                    int[] iArr = {color, color};
                } else if (intArray.length == 1) {
                    this.mGradientColors = r0;
                    int[] iArr2 = {intArray[0], intArray[0]};
                } else {
                    this.mGradientColors = intArray;
                }
            } catch (Resources.NotFoundException unused) {
                throw new Resources.NotFoundException("the give resource not found.");
            }
        }
        obtainStyledAttributes.recycle();
    }

    private void initPaint() {
        TextPaint textPaint = new TextPaint();
        this.mHintPaint = textPaint;
        textPaint.setAntiAlias(this.antiAlias);
        this.mHintPaint.setTextSize(this.mHintSize);
        this.mHintPaint.setColor(this.mHintColor);
        this.mHintPaint.setTextAlign(Paint.Align.CENTER);
        TextPaint textPaint2 = new TextPaint();
        this.mValuePaint = textPaint2;
        textPaint2.setAntiAlias(this.antiAlias);
        this.mValuePaint.setTextSize(this.mValueSize);
        this.mValuePaint.setColor(this.mValueColor);
        this.mValuePaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.mValuePaint.setTextAlign(Paint.Align.CENTER);
        TextPaint textPaint3 = new TextPaint();
        this.mUnitPaint = textPaint3;
        textPaint3.setAntiAlias(this.antiAlias);
        this.mUnitPaint.setTextSize(this.mUnitSize);
        this.mUnitPaint.setColor(this.mUnitColor);
        this.mUnitPaint.setTextAlign(Paint.Align.CENTER);
        Paint paint = new Paint();
        this.mArcPaint = paint;
        paint.setAntiAlias(this.antiAlias);
        this.mArcPaint.setStyle(Paint.Style.STROKE);
        this.mArcPaint.setStrokeWidth(this.mArcWidth);
        this.mArcPaint.setStrokeCap(Paint.Cap.ROUND);
        Paint paint2 = new Paint();
        this.mBgArcPaint = paint2;
        paint2.setAntiAlias(this.antiAlias);
        this.mBgArcPaint.setColor(this.mBgArcColor);
        this.mBgArcPaint.setStyle(Paint.Style.STROKE);
        this.mBgArcPaint.setStrokeWidth(this.mBgArcWidth);
        this.mBgArcPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void startAnimator(float f2, float f3, long j2) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f2, f3);
        this.mAnimator = ofFloat;
        ofFloat.setDuration(j2);
        this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jd.aips.verify.face.view.CircleProgress.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CircleProgress.this.mPercent = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                CircleProgress circleProgress = CircleProgress.this;
                circleProgress.mValue = circleProgress.mPercent * CircleProgress.this.mMaxValue;
                CircleProgress.this.postInvalidate();
            }
        });
        this.mAnimator.start();
    }

    private void updateArcPaint() {
        Point point2 = this.mCenterPoint;
        SweepGradient sweepGradient = new SweepGradient(point2.x, point2.y, this.mGradientColors, (float[]) null);
        this.mSweepGradient = sweepGradient;
        this.mArcPaint.setShader(sweepGradient);
    }

    public long getAnimTime() {
        return this.mAnimTime;
    }

    public int[] getGradientColors() {
        return this.mGradientColors;
    }

    public CharSequence getHint() {
        return this.mHint;
    }

    public float getMaxValue() {
        return this.mMaxValue;
    }

    public int getPrecision() {
        return this.mPrecision;
    }

    public CharSequence getUnit() {
        return this.mUnit;
    }

    public float getValue() {
        return this.mValue;
    }

    public boolean isAntiAlias() {
        return this.antiAlias;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
        drawArc(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        setMeasuredDimension(MiscUtil.measure(i2, this.mDefaultSize), MiscUtil.measure(i3, this.mDefaultSize));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        float max = Math.max(this.mArcWidth, this.mBgArcWidth);
        int i6 = ((int) max) * 2;
        float min = Math.min(((i2 - getPaddingLeft()) - getPaddingRight()) - i6, ((i3 - getPaddingTop()) - getPaddingBottom()) - i6) / 2;
        this.mRadius = min;
        Point point2 = this.mCenterPoint;
        int i7 = i2 / 2;
        point2.x = i7;
        int i8 = i3 / 2;
        point2.y = i8;
        RectF rectF = this.mRectF;
        float f2 = i7;
        float f3 = max / 2.0f;
        rectF.left = (f2 - min) - f3;
        float f4 = i8;
        rectF.top = (f4 - min) - f3;
        rectF.right = f2 + min + f3;
        rectF.bottom = min + f4 + f3;
        this.mValueOffset = f4 + getBaselineOffsetFromY(this.mValuePaint);
        this.mHintOffset = (this.mCenterPoint.y - (this.mRadius * this.mTextOffsetPercentInRadius)) + getBaselineOffsetFromY(this.mHintPaint);
        this.mUnitOffset = this.mCenterPoint.y + (this.mRadius * this.mTextOffsetPercentInRadius) + getBaselineOffsetFromY(this.mUnitPaint);
        updateArcPaint();
    }

    public void reset() {
        this.mValue = 0.0f;
        this.mPercent = 0.0f;
        postInvalidate();
    }

    public void setAnimTime(long j2) {
        this.mAnimTime = j2;
    }

    public void setGradientColors(int[] iArr) {
        this.mGradientColors = iArr;
        updateArcPaint();
    }

    public void setHint(CharSequence charSequence) {
        this.mHint = charSequence;
    }

    public void setMaxValue(float f2) {
        this.mMaxValue = f2;
    }

    public void setPrecision(int i2) {
        this.mPrecision = i2;
        this.mPrecisionFormat = MiscUtil.getPrecisionFormat(i2);
    }

    public void setUnit(CharSequence charSequence) {
        this.mUnit = charSequence;
    }

    public void setValue(float f2) {
        String str = "set value=" + f2;
        float f3 = this.mMaxValue;
        if (f2 > f3) {
            f2 = f3;
        }
        startAnimator(this.mPercent, f2 / f3, this.mAnimTime);
    }
}
