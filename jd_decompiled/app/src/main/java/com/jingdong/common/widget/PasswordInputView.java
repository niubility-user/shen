package com.jingdong.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.EditText;
import com.jd.lib.un.basewidget.R;

/* loaded from: classes12.dex */
public class PasswordInputView extends EditText {
    private int borderColor;
    private Paint borderPaint;
    private float borderRadius;
    private float borderWidth;
    private int contentColor;
    private float defaultContMargin;
    private float defaultSplitLineWidth;
    private int dividerColor;
    private OnPasswordInputFinishListener finishListener;
    private int passwordColor;
    private int passwordLength;
    private Paint passwordPaint;
    private float passwordRadius;
    private float passwordWidth;
    private int textLength;

    /* loaded from: classes12.dex */
    public interface OnPasswordInputFinishListener {
        void onFinish(String str);
    }

    public PasswordInputView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.defaultContMargin = 4.0f;
        this.defaultSplitLineWidth = 3.0f;
        this.borderColor = -3355444;
        this.dividerColor = -3355444;
        this.borderWidth = 5.0f;
        this.borderRadius = 3.0f;
        this.passwordLength = 6;
        this.passwordColor = -3355444;
        this.passwordWidth = 8.0f;
        this.passwordRadius = 3.0f;
        this.contentColor = -1;
        this.passwordPaint = new Paint(1);
        this.borderPaint = new Paint(1);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.borderWidth = (int) TypedValue.applyDimension(2, this.borderWidth, displayMetrics);
        this.borderRadius = (int) TypedValue.applyDimension(2, this.borderRadius, displayMetrics);
        this.passwordLength = (int) TypedValue.applyDimension(2, this.passwordLength, displayMetrics);
        this.passwordWidth = (int) TypedValue.applyDimension(2, this.passwordWidth, displayMetrics);
        this.passwordRadius = (int) TypedValue.applyDimension(2, this.passwordRadius, displayMetrics);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.PasswordInputView, 0, 0);
        this.borderColor = obtainStyledAttributes.getColor(R.styleable.PasswordInputView_pivBorderColor, this.borderColor);
        this.dividerColor = obtainStyledAttributes.getColor(R.styleable.PasswordInputView_pivDividerColor, this.dividerColor);
        float dimension = obtainStyledAttributes.getDimension(R.styleable.PasswordInputView_pivBorderWidth, this.borderWidth);
        this.borderWidth = dimension;
        this.defaultContMargin = dimension;
        this.defaultSplitLineWidth = dimension;
        this.borderRadius = obtainStyledAttributes.getDimension(R.styleable.PasswordInputView_pivBorderRadius, this.borderRadius);
        this.passwordLength = obtainStyledAttributes.getInt(R.styleable.PasswordInputView_pivPasswordLength, this.passwordLength);
        this.passwordColor = obtainStyledAttributes.getColor(R.styleable.PasswordInputView_pivPasswordColor, this.passwordColor);
        this.passwordWidth = obtainStyledAttributes.getDimension(R.styleable.PasswordInputView_pivPasswordWidth, this.passwordWidth);
        this.passwordRadius = obtainStyledAttributes.getDimension(R.styleable.PasswordInputView_pivPasswordRadius, this.passwordRadius);
        obtainStyledAttributes.recycle();
        this.borderPaint.setStrokeWidth(this.borderWidth);
        this.borderPaint.setColor(this.borderColor);
        this.borderPaint.setStyle(Paint.Style.FILL);
        this.passwordPaint.setStrokeWidth(this.passwordWidth);
        this.passwordPaint.setColor(this.passwordColor);
    }

    public int getBorderColor() {
        return this.borderColor;
    }

    public float getBorderRadius() {
        return this.borderRadius;
    }

    public float getBorderWidth() {
        return this.borderWidth;
    }

    public int getPasswordColor() {
        return this.passwordColor;
    }

    public int getPasswordLength() {
        return this.passwordLength;
    }

    public float getPasswordRadius() {
        return this.passwordRadius;
    }

    public float getPasswordWidth() {
        return this.passwordWidth;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        int i2;
        int width = getWidth();
        int height = getHeight();
        float f2 = height;
        RectF rectF = new RectF(0.0f, 0.0f, width, f2);
        this.borderPaint.setColor(this.borderColor);
        float f3 = this.borderRadius;
        canvas.drawRoundRect(rectF, f3, f3, this.borderPaint);
        float f4 = rectF.left;
        float f5 = this.defaultContMargin;
        RectF rectF2 = new RectF(f4 + f5, rectF.top + f5, rectF.right - f5, rectF.bottom - f5);
        this.borderPaint.setColor(this.contentColor);
        float f6 = this.borderRadius;
        canvas.drawRoundRect(rectF2, f6, f6, this.borderPaint);
        this.borderPaint.setColor(this.dividerColor);
        this.borderPaint.setStrokeWidth(this.defaultSplitLineWidth);
        int i3 = 1;
        while (true) {
            i2 = this.passwordLength;
            if (i3 >= i2) {
                break;
            }
            float f7 = (width * i3) / i2;
            float f8 = this.borderWidth;
            canvas.drawLine(f7, f8 + 0.0f, f7, f2 - f8, this.borderPaint);
            i3++;
        }
        float f9 = height / 2;
        float f10 = (width / i2) / 2;
        for (int i4 = 0; i4 < this.textLength; i4++) {
            canvas.drawCircle(((width * i4) / this.passwordLength) + f10, f9, this.passwordWidth, this.passwordPaint);
        }
    }

    @Override // android.widget.TextView
    protected void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
        OnPasswordInputFinishListener onPasswordInputFinishListener;
        super.onTextChanged(charSequence, i2, i3, i4);
        this.textLength = charSequence.toString().length();
        invalidate();
        if (this.textLength != this.passwordLength || (onPasswordInputFinishListener = this.finishListener) == null) {
            return;
        }
        onPasswordInputFinishListener.onFinish(getText().toString());
    }

    public void setBorderColor(int i2) {
        this.borderColor = i2;
        this.borderPaint.setColor(i2);
        invalidate();
    }

    public void setBorderRadius(float f2) {
        this.borderRadius = f2;
        invalidate();
    }

    public void setBorderWidth(float f2) {
        this.borderWidth = f2;
        this.borderPaint.setStrokeWidth(f2);
        invalidate();
    }

    public void setContentColor(int i2) {
        this.contentColor = i2;
        invalidate();
    }

    public void setDividerColor(int i2) {
        this.dividerColor = i2;
        invalidate();
    }

    public void setFinishListener(OnPasswordInputFinishListener onPasswordInputFinishListener) {
        this.finishListener = onPasswordInputFinishListener;
    }

    public void setPasswordColor(int i2) {
        this.passwordColor = i2;
        this.passwordPaint.setColor(i2);
        invalidate();
    }

    public void setPasswordLength(int i2) {
        this.passwordLength = i2;
        invalidate();
    }

    public void setPasswordRadius(float f2) {
        this.passwordRadius = f2;
        invalidate();
    }

    public void setPasswordWidth(float f2) {
        this.passwordWidth = f2;
        this.passwordPaint.setStrokeWidth(f2);
        invalidate();
    }
}
