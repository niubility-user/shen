package com.jingdong.common.recommend;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes6.dex */
public class RecommendAutoFitTextView extends TextView {
    private Paint mPaint;
    private float mTextSize;

    public RecommendAutoFitTextView(Context context) {
        super(context);
    }

    private float getTextLength(float f2, String str) {
        this.mPaint.setTextSize(f2);
        return this.mPaint.measureText(str);
    }

    private void refitText(String str, int i2) {
        if (i2 > 0) {
            this.mTextSize = getTextSize();
            Paint paint = new Paint();
            this.mPaint = paint;
            paint.set(getPaint());
            Drawable[] compoundDrawables = getCompoundDrawables();
            int i3 = 0;
            for (int i4 = 0; i4 < compoundDrawables.length; i4++) {
                if (compoundDrawables[i4] != null) {
                    i3 += compoundDrawables[i4].getBounds().width();
                }
            }
            int paddingLeft = (((i2 - getPaddingLeft()) - getPaddingRight()) - (i3 > 0 ? getCompoundDrawablePadding() : 0)) - i3;
            float textLength = getTextLength(this.mTextSize, str);
            while (textLength > paddingLeft) {
                Paint paint2 = this.mPaint;
                float f2 = this.mTextSize - 1.0f;
                this.mTextSize = f2;
                paint2.setTextSize(f2);
                textLength = getTextLength(this.mTextSize, str);
            }
            setTextSize(0, this.mTextSize);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        refitText(getText().toString(), getWidth());
    }

    public RecommendAutoFitTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RecommendAutoFitTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
