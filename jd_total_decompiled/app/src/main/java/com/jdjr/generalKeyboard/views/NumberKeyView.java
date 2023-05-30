package com.jdjr.generalKeyboard.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;
import com.jdjr.generalKeyboard.common.TextFontUtils;

/* loaded from: classes18.dex */
public class NumberKeyView extends View {
    private int mHeight;
    private Paint mPaint;
    private String mValueStr;
    private int mWidth;

    public NumberKeyView(Context context) {
        this(context, null);
    }

    private void init(Context context) {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setTextSize(context.getResources().getDimensionPixelSize(R.dimen.security_paint_general_number_text_size));
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        Typeface uDC1BoldFont = TextFontUtils.getUDC1BoldFont(context);
        if (uDC1BoldFont != null) {
            this.mPaint.setTypeface(uDC1BoldFont);
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.jdjr.generalKeyboard.views.NumberKeyView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NumberKeyView.this.sendAccessibilityEvent(4);
                NumberKeyView.this.invalidate();
            }
        });
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setColor(ContextCompat.getColor(getContext(), KeyboardUiMode.isDark() ? R.color.keyboard_color_key_text_dark : R.color.keyboard_color_key_text));
        Paint.FontMetrics fontMetrics = this.mPaint.getFontMetrics();
        float f2 = fontMetrics.top;
        float f3 = fontMetrics.bottom;
        canvas.drawText(this.mValueStr, this.mWidth / 2, (float) (((this.mHeight * 7) / 10) - 5), this.mPaint);
    }

    @Override // android.view.View
    @TargetApi(14)
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        int eventType = accessibilityEvent.getEventType();
        if (eventType == 4 || eventType == 32768) {
            accessibilityEvent.getText().add("" + this.mValueStr);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.mWidth == 0 || this.mHeight == 0) {
            this.mWidth = getWidth();
            this.mHeight = getHeight();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setParams(int i2, int i3) {
        this.mWidth = i2;
        this.mHeight = i3;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTextStr(String str) {
        this.mValueStr = str;
        setTag(str);
        invalidate();
    }

    public NumberKeyView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NumberKeyView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(context);
    }
}
