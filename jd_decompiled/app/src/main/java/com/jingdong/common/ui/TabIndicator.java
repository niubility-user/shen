package com.jingdong.common.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.jd.lib.un.business.R;
import com.jingdong.common.UnLog;

/* loaded from: classes6.dex */
public class TabIndicator extends View implements PageIndicator {
    private static final Interpolator SINTERPOLATOR = new Interpolator() { // from class: com.jingdong.common.ui.TabIndicator.1
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    };
    private int[] gradientColors;
    private boolean isGradient;
    private float mCurrX;
    private int mHeight;
    View mNewTab;
    private float mOffsetX;
    View mOldTab;
    private final Paint mPaint;
    private float mPercent;
    private int mRadius;
    private Scroller mScroller;
    private int mWidth;
    private boolean needChangeWidth;
    private float[] positions;

    public TabIndicator(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mNewTab == null) {
            return;
        }
        float paddingLeft = getPaddingLeft() + this.mCurrX + this.mOffsetX;
        float f2 = this.mWidth + paddingLeft;
        float paddingTop = getPaddingTop();
        float paddingBottom = this.mHeight - getPaddingBottom();
        if (this.isGradient && this.gradientColors != null && this.positions != null) {
            this.mPaint.setShader(new LinearGradient(paddingLeft, paddingTop, f2, paddingBottom, this.gradientColors, this.positions, Shader.TileMode.CLAMP));
        }
        if (this.mRadius == 0) {
            canvas.drawRect(paddingLeft, paddingTop, f2, paddingBottom, this.mPaint);
        } else {
            RectF rectF = new RectF();
            rectF.left = paddingLeft;
            rectF.top = paddingTop;
            rectF.right = f2;
            rectF.bottom = paddingBottom;
            int i2 = this.mRadius;
            canvas.drawRoundRect(rectF, i2, i2, this.mPaint);
        }
        if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
            this.mCurrX = this.mScroller.getCurrX();
            invalidate();
            return;
        }
        this.mScroller.abortAnimation();
    }

    @Override // com.jingdong.common.ui.PageIndicator
    public void onTabSelected(View view, View view2) {
        if (view2 != null) {
            if (this.needChangeWidth || this.mWidth <= 0) {
                this.mWidth = (int) (view2.getWidth() * this.mPercent);
            }
            this.mOldTab = view;
            this.mNewTab = view2;
            if (view != null && view != view2) {
                int left = view.getLeft();
                int left2 = view2.getLeft() - left;
                this.mScroller.startScroll(left, 0, left2, 0, 400);
                if (UnLog.D) {
                    UnLog.d("TabIndicator ", "startScroll  startX " + left + " dx " + left2);
                }
            } else {
                this.mCurrX = view2.getLeft();
            }
            this.mOffsetX = ((view2.getRight() - view2.getLeft()) - this.mWidth) / 2;
            invalidate();
        }
    }

    public void setColor(int i2) {
        Paint paint = this.mPaint;
        if (paint != null) {
            paint.setColor(i2);
            requestLayout();
        }
    }

    public void setGradient(int[] iArr, float[] fArr) {
        this.isGradient = true;
        this.gradientColors = iArr;
        this.positions = fArr;
        invalidate();
    }

    public void setNeedChangeWidth(boolean z) {
        this.needChangeWidth = z;
    }

    public void setShader(Shader shader) {
        Paint paint = this.mPaint;
        if (paint != null) {
            paint.setShader(shader);
            requestLayout();
        }
    }

    public void setTabSelectColor(int i2) {
        this.mPaint.setColor(i2);
    }

    public TabIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint(1);
        this.mPaint = paint;
        this.mHeight = 3;
        this.mWidth = 84;
        this.mPercent = 0.7f;
        this.mRadius = 0;
        this.needChangeWidth = false;
        this.isGradient = false;
        if (isInEditMode()) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.tabIndicator);
        this.mWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.tabIndicator_mwidth, -1);
        this.mHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.tabIndicator_mheight, 3);
        this.mRadius = obtainStyledAttributes.getDimensionPixelSize(R.styleable.tabIndicator_radius, 0);
        this.mPercent = obtainStyledAttributes.getFloat(R.styleable.tabIndicator_percent, 0.7f);
        paint.setColor(obtainStyledAttributes.getColor(R.styleable.tabIndicator_selectColor, -14474458));
        this.isGradient = obtainStyledAttributes.getBoolean(R.styleable.tabIndicator_isGradient, false);
        obtainStyledAttributes.recycle();
        this.mScroller = new Scroller(context, SINTERPOLATOR);
    }
}
