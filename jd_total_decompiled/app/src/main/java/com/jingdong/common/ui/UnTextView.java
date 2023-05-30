package com.jingdong.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes6.dex */
public class UnTextView extends TextView {
    private static final String TAG = "MyTextView";
    private String content;
    private Context mContext;
    private int width;

    public UnTextView(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        this.width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        this.content = getText().toString();
        TextPaint paint = getPaint();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTextSize(getTextSize());
        paint.setColor(getTextColors().getDefaultColor());
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float f2 = fontMetrics.descent - fontMetrics.ascent;
        float paddingLeft = getPaddingLeft();
        float paddingTop = (getPaddingTop() + f2) - fontMetrics.descent;
        String str = "height:" + getHeight() + "   fontHeight:" + paddingTop + "  paddingTop:" + getPaddingTop() + "   paddingBottom:" + getPaddingBottom();
        int length = this.content.length();
        float[] fArr = new float[length];
        paint.getTextWidths(this.content, fArr);
        if (paint.measureText(this.content) <= this.width) {
            canvas.drawText(this.content, paddingLeft, paddingTop, paint);
            return;
        }
        float paddingLeft2 = getPaddingLeft();
        boolean z = true;
        int i3 = 0;
        while (z) {
            float f3 = 0.0f;
            int i4 = i2;
            int i5 = i4;
            while (i4 < length && f3 + fArr[i4] <= this.width) {
                f3 += fArr[i4];
                i5 = i4;
                i4++;
            }
            int i6 = i5 + 1;
            String substring = this.content.substring(i2, i6);
            if (i3 == 1 && i6 < this.content.length() && substring.length() > 3) {
                substring = substring.substring(0, substring.length() - 3) + "\u2026";
            }
            canvas.drawText(substring, paddingLeft2, (i3 * f2) + paddingTop, paint);
            i3++;
            i2 = (i6 <= this.content.length() && i3 < 2) ? i6 : 0;
            z = false;
        }
    }

    public UnTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        setMaxLines(2);
        setEllipsize(TextUtils.TruncateAt.END);
    }

    public UnTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        setMaxLines(2);
        setEllipsize(TextUtils.TruncateAt.END);
    }
}
