package com.jingdong.app.mall.mylib.CouponUnit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import com.jingdong.sdk.lib.couponunit.R;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

/* loaded from: classes4.dex */
public class CouponValueView extends AppCompatTextView {
    private Integer amountMinSize;
    private Integer amountSpace;
    private int amountWidth;
    private String content;
    private Integer currencySymbolMinSize;
    private int currencySymbolWidth;
    private boolean isNormal;
    private List<Paint> paints;
    private int symbolSize;
    private List<Integer> textMaxSizes;
    private List<String> texts;

    public CouponValueView(Context context, AttributeSet attributeSet) throws DataFormatException {
        super(context, attributeSet);
        this.isNormal = false;
        this.symbolSize = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CouponValueView);
        ArrayList arrayList = new ArrayList();
        this.textMaxSizes = arrayList;
        arrayList.add(Integer.valueOf(obtainStyledAttributes.getDimensionPixelSize(R.styleable.CouponValueView_currencySymbolMaxSize, 0)));
        this.textMaxSizes.add(Integer.valueOf(obtainStyledAttributes.getDimensionPixelSize(R.styleable.CouponValueView_amountMaxSize, 0)));
        this.currencySymbolMinSize = Integer.valueOf(obtainStyledAttributes.getDimensionPixelSize(R.styleable.CouponValueView_currencySymbolMinSize, 0));
        this.amountMinSize = Integer.valueOf(obtainStyledAttributes.getDimensionPixelSize(R.styleable.CouponValueView_amountMinSize, 0));
        this.amountSpace = Integer.valueOf(obtainStyledAttributes.getDimensionPixelSize(R.styleable.CouponValueView_amountSpace, 0));
        this.paints = new ArrayList();
        int size = this.textMaxSizes.size();
        for (int i2 = 0; i2 < size; i2++) {
            TextPaint textPaint = new TextPaint();
            textPaint.setAntiAlias(true);
            textPaint.setTextSize(this.textMaxSizes.get(i2).intValue());
            textPaint.setColor(getCurrentTextColor());
            textPaint.setFakeBoldText(true);
            this.paints.add(textPaint);
        }
    }

    private void resetPaintSize() {
        for (int i2 = 0; i2 < this.textMaxSizes.size(); i2++) {
            if (i2 == 0 && this.symbolSize != 0) {
                this.paints.get(i2).setTextSize(this.symbolSize);
            } else {
                this.paints.get(i2).setTextSize(this.textMaxSizes.get(i2).intValue());
            }
        }
    }

    private void shrinkTextSize(int i2) {
        this.currencySymbolWidth = (int) this.paints.get(0).measureText(this.texts.get(0));
        if (1 < this.texts.size()) {
            this.amountWidth = (int) this.paints.get(1).measureText(this.texts.get(1));
        }
        if (this.currencySymbolWidth + (this.amountWidth / 2) + this.amountSpace.intValue() > i2) {
            for (Paint paint : this.paints) {
                float textSize = paint.getTextSize();
                double textSize2 = paint.getTextSize();
                Double.isNaN(textSize2);
                paint.setTextSize(textSize - ((float) (textSize2 * 0.1d)));
            }
            shrinkTextSize(i2);
        }
    }

    private void splitText() {
        List<String> list = this.texts;
        if (list == null) {
            this.texts = new ArrayList();
        } else {
            list.clear();
        }
        if (!TextUtils.isEmpty(this.content)) {
            this.texts.add(this.content.substring(0, 1));
        }
        if (1 <= this.content.length()) {
            List<String> list2 = this.texts;
            String str = this.content;
            list2.add(str.substring(1, str.length()));
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.isNormal) {
            super.onDraw(canvas);
            return;
        }
        resetPaintSize();
        this.content = getText().toString();
        splitText();
        if (this.texts.size() < 2) {
            return;
        }
        if (!TextUtils.isEmpty(this.content)) {
            this.currencySymbolWidth = (int) this.paints.get(0).measureText(this.texts.get(0));
        }
        if (1 < this.texts.size()) {
            this.amountWidth = (int) this.paints.get(1).measureText(this.texts.get(1));
        }
        if (this.currencySymbolWidth + (this.amountWidth / 2) + this.amountSpace.intValue() > getMeasuredWidth() / 2) {
            shrinkTextSize(getMeasuredWidth() / 2);
        }
        if (this.paints.get(0).getTextSize() < this.currencySymbolMinSize.intValue()) {
            this.paints.get(0).setTextSize(this.currencySymbolMinSize.intValue());
        }
        if (this.paints.get(1).getTextSize() < this.amountMinSize.intValue()) {
            this.paints.get(1).setTextSize(this.amountMinSize.intValue());
        }
        float width = (((getWidth() - this.amountWidth) / 2.0f) - this.currencySymbolWidth) - this.amountSpace.intValue();
        for (int i2 = 0; i2 < this.textMaxSizes.size(); i2++) {
            canvas.drawText(this.texts.get(i2), width, (getHeight() / 2) + ((this.textMaxSizes.get(1).intValue() * 2) / 5), this.paints.get(i2));
            width += this.paints.get(i2).measureText(this.texts.get(i2));
            if (i2 == 0) {
                width += this.amountSpace.intValue();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.isNormal) {
            super.onMeasure(i2, i3);
        } else {
            setMeasuredDimension(View.MeasureSpec.getSize(i2), View.MeasureSpec.getSize(i3));
        }
    }

    public void setNormal(boolean z) {
        this.isNormal = z;
    }

    public void setSymbolSize(int i2) {
        this.symbolSize = i2;
    }
}
