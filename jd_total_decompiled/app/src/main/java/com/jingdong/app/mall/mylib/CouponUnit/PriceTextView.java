package com.jingdong.app.mall.mylib.CouponUnit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import com.jingdong.sdk.lib.couponunit.R;
import com.jingdong.sdk.log.Log;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public class PriceTextView extends TextView {
    public static final String TAG = PriceTextView.class.getSimpleName();
    private float defaultTextSize;
    private DisplayMetrics dm;
    private boolean isNormal;
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
        this.isNormal = false;
        this.textP = new TextPaint();
        this.dm = context.getResources().getDisplayMetrics();
        this.defaultTextSize = getTextSize();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.pricetext);
        this.isShowNumTypeText = obtainStyledAttributes.getBoolean(R.styleable.pricetext_isNum, false);
        this.isPanicTip = obtainStyledAttributes.getBoolean(R.styleable.pricetext_isPanicTip, false);
        this.isPromotion = obtainStyledAttributes.getBoolean(R.styleable.pricetext_isPromotionTip, false);
        this.maxWidth = context.getResources().getDimensionPixelSize(R.dimen.my_coupon_limit_max_width);
    }

    private float caleTextSize(float f2, String str, float f3) {
        this.textP.setTextSize(f3);
        if (this.textP.measureText(str) >= (f2 - (str.length() * 1.2f)) - 5.0f) {
            if (Log.D) {
                Log.d(TAG, " caleTextSize ---> in : ");
            }
            return caleTextSize(f2, str, f3 - 1.0f);
        }
        if (Log.D) {
            Log.d(TAG, " caleTextSize ---> out : ");
        }
        return f3;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.isNormal) {
            super.onDraw(canvas);
            return;
        }
        String str = "" + getText().toString();
        if (this.isPromotion && getWidth() < this.maxWidth) {
            getPaint().setColor(getCurrentTextColor());
            canvas.drawText(str, (getWidth() - getPaint().measureText(str)) / 2.0f, (getHeight() / 2.0f) + (getTextSize() / 3.0f), getPaint());
            return;
        }
        getPaint().setTextSize(TypedValue.applyDimension(2, DPIUtil.px2sp(getContext(), this.defaultTextSize), this.dm));
        this.textSizeResult = caleTextSize(getWidth(), str, this.defaultTextSize);
        if (r1 - this.defaultTextSize != 0.0d) {
            getPaint().setTextSize(TypedValue.applyDimension(2, DPIUtil.px2sp(getContext(), this.textSizeResult), this.dm));
        }
        getPaint().setColor(getCurrentTextColor());
        if (this.isShowNumTypeText) {
            canvas.drawText(str, 0.0f, (getHeight() / 2) + (this.textSizeResult / 3.0f), getPaint());
        } else if (this.isPanicTip) {
            canvas.drawText(str, (getWidth() - getPaint().measureText(str)) / 2.0f, (getHeight() / 2) + (this.textSizeResult / 3.0f), getPaint());
        } else {
            float width = getWidth() - getPaint().measureText(str);
            float height = (getHeight() / 2) + (this.textSizeResult / 3.0f);
            if (Log.D) {
                Log.d(TAG, "text -->> :" + str);
            }
            canvas.drawText(str, width, height, getPaint());
        }
    }

    public void setNormal(boolean z) {
        this.isNormal = z;
    }

    public void setPanicTip(boolean z) {
        this.isPanicTip = z;
    }
}
