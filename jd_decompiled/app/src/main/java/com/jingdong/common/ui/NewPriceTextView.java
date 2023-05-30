package com.jingdong.common.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.R;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class NewPriceTextView extends TextView {
    private static final String TAG = "NewPriceTextView";
    private int bimapWidth;
    private List<Integer> defaultSizes;
    private DisplayMetrics dm;
    private boolean isOutStock;
    private Bitmap labelBitmap;
    private Paint labelPaint;
    private List<Paint> paints;
    private boolean showLabel;
    private List<Integer> sizes;
    private String splitChar;
    private List<Float> textWidths;
    private List<String> texts;
    private int typeDefinedSize;

    public NewPriceTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paints = new ArrayList();
        this.dm = null;
        this.texts = new ArrayList();
        this.textWidths = new ArrayList();
        this.sizes = new ArrayList();
        this.defaultSizes = new ArrayList();
        this.typeDefinedSize = 3;
        this.showLabel = false;
        this.splitChar = "\\.";
        this.dm = context.getResources().getDisplayMetrics();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.newpricetext);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.newpricetext_useJDZhengHT, false);
        this.defaultSizes.add(Integer.valueOf(obtainStyledAttributes.getDimensionPixelSize(R.styleable.newpricetext_textSize1, 0)));
        this.defaultSizes.add(Integer.valueOf(obtainStyledAttributes.getDimensionPixelSize(R.styleable.newpricetext_textSize2, 0)));
        this.defaultSizes.add(Integer.valueOf(obtainStyledAttributes.getDimensionPixelSize(R.styleable.newpricetext_textSize3, 0)));
        this.sizes.addAll(this.defaultSizes);
        if (OKLog.D) {
            OKLog.d("TEST", " NewPriceTextView ---> size : " + this.sizes.size());
        }
        for (int i2 = 0; i2 < this.typeDefinedSize; i2++) {
            TextPaint textPaint = new TextPaint();
            textPaint.setAntiAlias(true);
            if (z) {
                textPaint.setTypeface(FontsUtil.getTypeFace(context));
            }
            this.paints.add(textPaint);
        }
        BitmapDrawable bitmapDrawable = (BitmapDrawable) obtainStyledAttributes.getDrawable(R.styleable.newpricetext_labelDrawable);
        this.showLabel = obtainStyledAttributes.getBoolean(R.styleable.newpricetext_showLabel, false);
        if (bitmapDrawable != null) {
            Bitmap bitmap = bitmapDrawable.getBitmap();
            this.labelBitmap = bitmap;
            this.bimapWidth = bitmap.getWidth();
            TextPaint textPaint2 = new TextPaint();
            this.labelPaint = textPaint2;
            textPaint2.setAntiAlias(true);
        }
        obtainStyledAttributes.recycle();
    }

    private void caleTextSize() {
        Paint paint = new Paint();
        paint.setTextSize(TypedValue.applyDimension(2, DPIUtil.px2sp(getContext(), Math.max(Math.max(this.sizes.get(0).intValue(), this.sizes.get(1).intValue()), this.sizes.get(2).intValue())), this.dm));
        float measureText = paint.measureText(getText().toString());
        int width = getWidth();
        if (this.showLabel) {
            width -= this.bimapWidth + 3;
        }
        if (measureText >= width) {
            for (int i2 = 0; i2 < this.typeDefinedSize; i2++) {
                List<Integer> list = this.sizes;
                list.set(i2, Integer.valueOf(list.get(i2).intValue() - 1));
            }
            if (this.sizes.get(0).intValue() < 10) {
                return;
            }
            caleTextSize();
        }
    }

    private void splitText() {
        String[] split = getText().toString().split(this.splitChar);
        if (TextUtils.equals(this.splitChar, "\\.")) {
            if (split.length > 0 && !TextUtils.isEmpty(split[0])) {
                this.texts.add(split[0].substring(0, 1));
                this.texts.add(split[0].substring(1, split[0].length()));
            } else {
                this.texts.add("");
                this.texts.add("");
            }
            if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                this.texts.add(OrderISVUtil.MONEY_DECIMAL + split[1]);
                return;
            }
            this.texts.add("");
        } else if (TextUtils.equals(this.splitChar, "\\*")) {
            if (split.length > 1) {
                this.texts.add("");
                this.texts.add(split[0]);
                this.texts.add(split[1]);
            } else if (split.length == 1) {
                this.texts.add("");
                this.texts.add(split[0]);
                this.texts.add("");
            } else {
                this.texts.add("");
                this.texts.add(getText().toString());
                this.texts.add("");
                if (OKLog.D) {
                    OKLog.d(TAG, " splitText else--->  getText : " + getText().toString());
                }
            }
        } else if (split.length > 0 && split[0].startsWith("\u00a5")) {
            this.texts.add("\u00a5");
            this.texts.add(split[0].substring(1));
            this.texts.add("");
        } else {
            this.texts.add("");
            this.texts.add(getText().toString());
            this.texts.add("");
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        this.texts.clear();
        this.textWidths.clear();
        this.sizes.clear();
        this.sizes.addAll(this.defaultSizes);
        splitText();
        for (int i2 = 0; i2 < this.typeDefinedSize; i2++) {
            this.paints.get(i2).setColor(getCurrentTextColor());
        }
        caleTextSize();
        float f2 = 0.0f;
        for (int i3 = 0; i3 < this.typeDefinedSize; i3++) {
            this.paints.get(i3).setTextSize(TypedValue.applyDimension(2, DPIUtil.px2sp(getContext(), this.sizes.get(i3).intValue()), this.dm));
            this.textWidths.add(Float.valueOf(this.paints.get(i3).measureText(this.texts.get(i3))));
            canvas.drawText(this.texts.get(i3), f2, (getHeight() >> 1) + ((this.sizes.get(1).intValue() * 2) / 5), this.paints.get(i3));
            f2 += this.textWidths.get(i3).floatValue();
        }
        if (!this.showLabel || this.labelBitmap == null) {
            return;
        }
        if (this.isOutStock) {
            this.labelPaint.setAlpha(128);
        } else {
            this.labelPaint.setAlpha(255);
        }
        canvas.drawBitmap(this.labelBitmap, f2 + 3.0f, (getHeight() >> 1) - (this.sizes.get(1).intValue() / 5), this.labelPaint);
    }

    public void setLabelBitmap(Bitmap bitmap) {
        this.labelBitmap = bitmap;
    }

    public void setOutStock(boolean z) {
        this.isOutStock = z;
    }

    public void setShowLabel(boolean z) {
        this.showLabel = z;
    }

    public void setSplitChar(String str) {
        this.splitChar = str;
    }
}
