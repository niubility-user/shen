package com.jingdong.common.sample.jshop;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.jingdong.app.mall.R;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes6.dex */
public class TabIndicator extends View implements PageIndicator {
    private float mCurrX;
    View mNewTab;
    private float mOffsetX;
    private final Paint mPaint;
    private Scroller mScroller;
    private int mWidth;
    private final Interpolator sInterpolator;

    public TabIndicator(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mNewTab == null) {
            Log.d("jaygao", "mNewTab == null return");
            return;
        }
        float paddingLeft = getPaddingLeft() + this.mCurrX + this.mOffsetX;
        float f2 = this.mWidth + paddingLeft;
        float paddingTop = getPaddingTop();
        float height = getHeight() - getPaddingBottom();
        canvas.drawRect(paddingLeft, paddingTop, f2, height, this.mPaint);
        Log.d("jaygao", "left=" + paddingLeft);
        Log.d("jaygao", "right=" + f2);
        Log.d("jaygao", "top=" + paddingTop);
        Log.d("jaygao", "bottom=" + height);
        if (!this.mScroller.isFinished() && this.mScroller.computeScrollOffset()) {
            Log.d("jaygao", "!mScroller.isFinished() && mScroller.computeScrollOffset()");
            this.mCurrX = this.mScroller.getCurrX();
            invalidate();
            return;
        }
        Log.d("jaygao", "!!!!!!!mScroller.isFinished() && mScroller.computeScrollOffset()");
        this.mScroller.abortAnimation();
    }

    @Override // com.jingdong.common.sample.jshop.PageIndicator
    public void onTabSelected(View view, View view2) {
        Log.d("jaygao", "enter onTabSelected");
        if (view2 != null) {
            Log.d("jaygao", "newTab != null");
            if (this.mWidth <= 0) {
                Log.d("jaygao", "mWidth<=0");
                double width = view2.getWidth();
                Double.isNaN(width);
                this.mWidth = (int) (width * 0.7d);
                Log.d("jaygao", "mWidth=" + this.mWidth);
            }
            this.mNewTab = view2;
            if (view != null && view != view2) {
                Log.d("jaygao", "oldTab != null && oldTab != newTab");
                int left = view.getLeft();
                int left2 = view2.getLeft() - left;
                Log.d("jaygao", "startX=" + left);
                Log.d("jaygao", "dx=" + left2);
                this.mScroller.startScroll(left, 0, left2, 0, 400);
            } else {
                Log.d("jaygao", "!!!!!!oldTab != null && oldTab != newTab");
                this.mCurrX = view2.getLeft();
                Log.d("jaygao", "mCurrX=" + this.mCurrX);
            }
            this.mOffsetX = ((view2.getRight() - view2.getLeft()) - this.mWidth) / 2.0f;
            Log.d("jaygao", "mOffsetX=" + this.mOffsetX);
            Log.d("jaygao", "onTabSelected-->invalidate()");
            invalidate();
        }
    }

    public TabIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint(1);
        this.mPaint = paint;
        Interpolator interpolator = new Interpolator() { // from class: com.jingdong.common.sample.jshop.TabIndicator.1
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                float f3 = f2 - 1.0f;
                return (f3 * f3 * f3 * f3 * f3) + 1.0f;
            }
        };
        this.sInterpolator = interpolator;
        this.mWidth = 90;
        if (isInEditMode()) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.tabIndicator);
        this.mWidth = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        paint.setColor(obtainStyledAttributes.getColor(2, -13388315));
        obtainStyledAttributes.recycle();
        this.mScroller = new Scroller(context, interpolator);
    }
}
