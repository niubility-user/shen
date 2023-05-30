package com.jingdong.common.recommend.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class LinerPagerCursor extends View {
    private static final String TAG = "LinerPagerCursor";
    protected boolean colorDifferent;
    protected Paint cursorPaint;
    protected RectF dRectF;
    protected int indicatorH;
    protected int indicatorPadding;
    protected int indicatorW;
    protected int layoutWidth;
    protected int mCount;
    protected int progressWidth;
    protected Paint selectPaint;
    protected int selectedIndex;
    protected int selectedWidth;
    protected Paint spacePaint;

    public LinerPagerCursor(Context context) {
        super(context);
        this.indicatorW = RecommendViewUtil.getRightSize(10);
        this.indicatorH = RecommendViewUtil.getRightSize(10);
        this.selectedWidth = RecommendViewUtil.getRightSize(16);
        this.indicatorPadding = RecommendViewUtil.getRightSize(8);
        this.layoutWidth = -1;
        this.progressWidth = 0;
        this.mCount = 1;
        this.selectedIndex = 0;
        init();
    }

    private void init() {
        setIndicatorColor(-1, -1, ViewCompat.MEASURED_SIZE_MASK);
        setIndicatorSize(this.indicatorW, this.indicatorH, this.indicatorPadding);
    }

    private void resetSize() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        int i2 = ((this.indicatorW + this.indicatorPadding) * (this.mCount - 1)) + this.selectedWidth;
        this.layoutWidth = i2;
        layoutParams.width = i2;
        layoutParams.height = this.indicatorH;
        requestLayout();
    }

    public void drawPoint(Canvas canvas) {
        RectF rectF = this.dRectF;
        rectF.left = 0.0f;
        rectF.right = 0.0f;
        int i2 = 0;
        while (i2 < this.mCount) {
            RectF rectF2 = this.dRectF;
            float f2 = rectF2.right + (i2 == 0 ? 0 : this.indicatorPadding);
            rectF2.left = f2;
            int i3 = this.selectedIndex;
            rectF2.right = f2 + (i2 == i3 ? this.selectedWidth : this.indicatorW);
            int i4 = this.indicatorH;
            canvas.drawRoundRect(rectF2, i4 / 2.0f, i4 / 2.0f, i2 == i3 ? this.selectPaint : this.cursorPaint);
            i2++;
        }
        RectF rectF3 = this.dRectF;
        rectF3.left = 0.0f;
        rectF3.right = this.progressWidth;
        int i5 = this.indicatorH;
        canvas.drawRoundRect(rectF3, i5 / 2.0f, i5 / 2.0f, this.spacePaint);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPoint(canvas);
    }

    public void onPageScrolled(int i2, float f2, int i3) {
        if (OKLog.D) {
            OKLog.d(TAG, "onPageScrolled p:" + i2 + " offset:" + f2 + " px:" + i3);
        }
        int i4 = this.colorDifferent ? this.indicatorW : 0;
        int i5 = this.indicatorW;
        int i6 = this.indicatorPadding;
        int i7 = (int) (((i5 + i6) * i2) + i5 + ((i4 + i6) * f2));
        this.progressWidth = i7;
        int i8 = this.layoutWidth;
        if (i8 > 0 && i7 > i8) {
            this.progressWidth = i8;
        }
        postInvalidate();
    }

    public void onPageSelected(int i2) {
        if (OKLog.D) {
            OKLog.d(TAG, "onPageSelected " + i2);
        }
        this.selectedIndex = i2;
        postInvalidate();
    }

    public void setIndicatorColor(int i2, int i3, int i4) {
        if (this.cursorPaint == null) {
            Paint paint = new Paint();
            this.cursorPaint = paint;
            paint.setAntiAlias(true);
            Paint paint2 = new Paint();
            this.spacePaint = paint2;
            paint2.setAntiAlias(true);
            Paint paint3 = new Paint();
            this.selectPaint = paint3;
            paint3.setAntiAlias(true);
        }
        this.colorDifferent = i3 != i4;
        this.cursorPaint.setColor(i2);
        this.spacePaint.setColor(i3);
        this.selectPaint.setColor(i4);
    }

    public void setIndicatorSize(int i2, int i3, int i4) {
        setIndicatorSize(i2, i2, i3, i4);
    }

    public void setPageCount(int i2) {
        this.mCount = i2;
        if (i2 <= 0) {
            this.mCount = 1;
        }
        resetSize();
    }

    public void setIndicatorSize(int i2, int i3, int i4, int i5) {
        if (i4 % 2 == 1) {
            i4++;
        }
        this.indicatorW = i2;
        this.indicatorH = i4;
        this.selectedWidth = i3;
        this.indicatorPadding = i5;
        RectF rectF = new RectF();
        this.dRectF = rectF;
        rectF.top = 0.0f;
        rectF.bottom = i4;
        postInvalidate();
    }

    public LinerPagerCursor(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.indicatorW = RecommendViewUtil.getRightSize(10);
        this.indicatorH = RecommendViewUtil.getRightSize(10);
        this.selectedWidth = RecommendViewUtil.getRightSize(16);
        this.indicatorPadding = RecommendViewUtil.getRightSize(8);
        this.layoutWidth = -1;
        this.progressWidth = 0;
        this.mCount = 1;
        this.selectedIndex = 0;
        init();
    }

    public LinerPagerCursor(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.indicatorW = RecommendViewUtil.getRightSize(10);
        this.indicatorH = RecommendViewUtil.getRightSize(10);
        this.selectedWidth = RecommendViewUtil.getRightSize(16);
        this.indicatorPadding = RecommendViewUtil.getRightSize(8);
        this.layoutWidth = -1;
        this.progressWidth = 0;
        this.mCount = 1;
        this.selectedIndex = 0;
        init();
    }
}
