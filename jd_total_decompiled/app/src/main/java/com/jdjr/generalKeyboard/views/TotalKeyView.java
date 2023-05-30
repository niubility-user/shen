package com.jdjr.generalKeyboard.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.Nullable;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;

/* loaded from: classes18.dex */
public class TotalKeyView extends View {
    public static final String TYPE_LEFT_BIG = "left_big";
    public static final String TYPE_LEFT_GIANT = "left_giant";
    public static final String TYPE_LEFT_NORMAL = "left_normal";
    public static final String TYPE_MIDDLE_BIG = "middle_big";
    public static final String TYPE_MIDDLE_GIANT = "middle_giant";
    public static final String TYPE_MIDDLE_NORMAL = "middle_normal";
    public static final String TYPE_RIGHT_GIANT = "right_giant";
    public static final String TYPE_RIGHT_NORMAL = "right_normal";
    private final int TYPE_LETTER;
    private final int TYPE_NUMBER;
    private final int TYPE_SYMBOL;
    private String enlargePopType;
    private int keyboardType;
    private int mBaseline;
    private int mLetterPaintTextSize;
    private int mNumberPaintTextSize;
    private Paint mPaint;
    private int mSymbolPaintTextSize;
    private String mValueText;
    private int mWidth;

    public TotalKeyView(Context context) {
        this(context, null);
    }

    private void init() {
        this.mNumberPaintTextSize = getResources().getDimensionPixelSize(R.dimen.security_total_number_paint_text_size);
        this.mLetterPaintTextSize = getResources().getDimensionPixelSize(R.dimen.security_total_letter_paint_text_size);
        this.mSymbolPaintTextSize = getResources().getDimensionPixelSize(R.dimen.security_total_symbol_paint_text_size);
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setTextAlign(Paint.Align.CENTER);
        setOnClickListener(new View.OnClickListener() { // from class: com.jdjr.generalKeyboard.views.TotalKeyView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TotalKeyView.this.sendAccessibilityEvent(4);
                TotalKeyView.this.invalidate();
            }
        });
    }

    public String getEnlargePopType() {
        return this.enlargePopType;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint.setColor(getResources().getColor(KeyboardUiMode.isDark() ? R.color.keyboard_color_total_key_text_dark : R.color.keyboard_color_total_key_text));
        String str = this.mValueText;
        if (str == null || str.length() <= 0) {
            return;
        }
        canvas.drawText(this.mValueText, this.mWidth / 2, this.mBaseline, this.mPaint);
    }

    @Override // android.view.View
    @TargetApi(14)
    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        int eventType = accessibilityEvent.getEventType();
        if (eventType == 4 || eventType == 32768) {
            if (this.keyboardType == 2) {
                if (Character.isUpperCase(this.mValueText.charAt(0))) {
                    accessibilityEvent.getText().add("\u5927\u5199\u7684" + this.mValueText);
                    return;
                }
                accessibilityEvent.getText().add("\u5c0f\u5199\u7684" + this.mValueText);
                return;
            }
            accessibilityEvent.getText().add("" + this.mValueText);
        }
    }

    public void setBaseline(int i2) {
        this.mBaseline = i2;
        invalidate();
    }

    public void setKeyValue(String str) {
        this.mValueText = str;
        setTag(str);
    }

    public void setKeyboardType(int i2) {
        if (i2 == 1) {
            this.mPaint.setTextSize(this.mNumberPaintTextSize);
        } else if (i2 == 2) {
            this.mPaint.setTextSize(this.mLetterPaintTextSize);
        } else if (i2 == 3) {
            this.mPaint.setTextSize(this.mSymbolPaintTextSize);
        }
        this.keyboardType = i2;
        invalidate();
    }

    public void setTextColor(int i2) {
        this.mPaint.setColor(i2);
        invalidate();
    }

    public void setWidth(int i2) {
        this.mWidth = i2;
    }

    public TotalKeyView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TotalKeyView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.TYPE_NUMBER = 1;
        this.TYPE_LETTER = 2;
        this.TYPE_SYMBOL = 3;
        this.keyboardType = -1;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SecureTotalKeyView, i2, 0);
        int i3 = R.styleable.SecureTotalKeyView_security_enlargePopType;
        this.enlargePopType = obtainStyledAttributes.getString(i3) == null ? TYPE_MIDDLE_NORMAL : obtainStyledAttributes.getString(i3);
        init();
        obtainStyledAttributes.recycle();
    }
}
