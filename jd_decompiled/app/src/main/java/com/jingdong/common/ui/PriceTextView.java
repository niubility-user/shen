package com.jingdong.common.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import com.jd.base.history.widget.R;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.UnLog;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes6.dex */
public class PriceTextView extends TextView {
    public static final String TAG = PriceTextView.class.getSimpleName();
    private float defaultTextSize;
    private DisplayMetrics dm;
    private boolean isPanicTip;
    private boolean isPromotion;
    private boolean isShowNumTypeText;
    private int maxWidth;
    private Paint textP;
    private float textSizeResult;

    public PriceTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textP = null;
        this.dm = null;
        this.isShowNumTypeText = false;
        this.isPanicTip = false;
        this.isPromotion = false;
        this.textP = new TextPaint();
        this.dm = context.getResources().getDisplayMetrics();
        this.defaultTextSize = getTextSize();
        if (UnLog.D) {
            UnLog.d(TAG, " PriceTextView -->>defaultTextSize \uff1a  " + this.defaultTextSize);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.pricetext);
        this.isShowNumTypeText = obtainStyledAttributes.getBoolean(R.styleable.pricetext_isNum, false);
        this.isPanicTip = obtainStyledAttributes.getBoolean(R.styleable.pricetext_isPanicTip, false);
        this.isPromotion = obtainStyledAttributes.getBoolean(R.styleable.pricetext_isPromotionTip, false);
        this.maxWidth = context.getResources().getDimensionPixelSize(R.dimen.price_limit_max_width);
    }

    private float caleTextSize(float f2, String str, float f3) {
        this.textP.setTextSize(f3);
        if (this.textP.measureText(str) >= (f2 - (str.length() * 1.2f)) - 5.0f) {
            if (UnLog.D) {
                UnLog.d(TAG, " caleTextSize ---> in : ");
            }
            return caleTextSize(f2, str, f3 - 1.0f);
        }
        if (UnLog.D) {
            UnLog.d(TAG, " caleTextSize ---> out : ");
        }
        return f3;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        String charSequence = getText().toString();
        float measureText = getPaint().measureText(charSequence);
        if (this.isPromotion && getWidth() < this.maxWidth) {
            getPaint().setColor(getCurrentTextColor());
            canvas.drawText(charSequence, (getWidth() - measureText) / 2.0f, (getHeight() >> 1) + (getTextSize() / 3.0f), getPaint());
            return;
        }
        getPaint().setTextSize(TypedValue.applyDimension(2, DpiUtil.px2sp(getContext(), this.defaultTextSize), this.dm));
        this.textSizeResult = caleTextSize(getWidth(), charSequence, this.defaultTextSize);
        float f2 = this.textSizeResult;
        float height = (getHeight() >> 1) + (f2 / 3.0f);
        if (f2 != this.defaultTextSize) {
            getPaint().setTextSize(TypedValue.applyDimension(2, DpiUtil.px2sp(getContext(), this.textSizeResult), this.dm));
        }
        getPaint().setColor(getCurrentTextColor());
        if (this.isShowNumTypeText) {
            canvas.drawText(charSequence, 0.0f, height, getPaint());
        } else if (this.isPanicTip) {
            canvas.drawText(charSequence, (getWidth() - measureText) / 2.0f, height, getPaint());
        } else {
            float width = getWidth() - measureText;
            if (UnLog.D) {
                UnLog.d(TAG, "text -->> :" + charSequence);
            }
            canvas.drawText(charSequence, width, height, getPaint());
        }
    }

    public void setPanicTip(boolean z) {
        this.isPanicTip = z;
    }
}
