package com.jdjr.generalKeyboard.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.jdjr.dns.R;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;
import com.jdjr.generalKeyboard.common.TextFontUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class SixInputItemView extends View {
    protected Context mContext;
    public int mHeight;
    private boolean mIsDrawn;
    private boolean mIsPlainText;
    private Paint mPaint;
    private String mPlainText;
    public int mWidth;

    public SixInputItemView(Context context) {
        this(context, null);
    }

    private void init() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.security_six_input_item_text_size));
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        Typeface uDC1BoldFont = TextFontUtils.getUDC1BoldFont(this.mContext);
        if (uDC1BoldFont != null) {
            this.mPaint.setTypeface(uDC1BoldFont);
        }
    }

    protected int dp2px(Context context, float f2) {
        return (int) ((f2 * BaseInfo.getDensity()) + 0.5f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mIsDrawn) {
            this.mPaint.setColor(ContextCompat.getColor(getContext(), KeyboardUiMode.isDark() ? R.color.keyboard_color_input_text_dark : R.color.keyboard_color_input_text));
            if (this.mIsPlainText) {
                canvas.drawText(this.mPlainText, this.mWidth / 2, (this.mHeight * 2) / 3, this.mPaint);
            } else {
                canvas.drawCircle(this.mWidth / 2, this.mHeight / 2, this.mContext.getResources().getDimension(R.dimen.security_six_square_input_item_dot_radius), this.mPaint);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        setMeasuredDimension((BaseInfo.getScreenWidth() - dp2px(getContext(), 54.0f)) / 6, dp2px(getContext(), 54.0f));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.mWidth = i2;
        this.mHeight = i3;
    }

    public void setIsCipher(boolean z) {
        this.mIsPlainText = !z;
        invalidate();
    }

    public void setIsDrawn(boolean z) {
        this.mIsDrawn = z;
        if (this instanceof SixUnderlineInputItemView) {
            if (z) {
                setBackgroundResource(KeyboardUiMode.isDark() ? R.drawable.security_general_six_underline_item_bg2_dark : R.drawable.security_general_six_underline_item_bg2);
            } else {
                setBackgroundResource(KeyboardUiMode.isDark() ? R.drawable.security_general_six_underline_item_bg_dark : R.drawable.security_general_six_underline_item_bg);
            }
        }
    }

    public void setPlainText(String str) {
        this.mPlainText = str;
        this.mIsPlainText = true;
        invalidate();
    }

    public SixInputItemView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SixInputItemView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        init();
    }
}
