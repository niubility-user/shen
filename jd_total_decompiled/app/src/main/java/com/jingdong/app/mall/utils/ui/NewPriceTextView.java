package com.jingdong.app.mall.utils.ui;

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
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class NewPriceTextView extends TextView {

    /* renamed from: g  reason: collision with root package name */
    private List<Paint> f11872g;

    /* renamed from: h  reason: collision with root package name */
    private Paint f11873h;

    /* renamed from: i  reason: collision with root package name */
    private DisplayMetrics f11874i;

    /* renamed from: j  reason: collision with root package name */
    private List<String> f11875j;

    /* renamed from: k  reason: collision with root package name */
    private List<Float> f11876k;

    /* renamed from: l  reason: collision with root package name */
    private List<Integer> f11877l;

    /* renamed from: m  reason: collision with root package name */
    private List<Integer> f11878m;

    /* renamed from: n  reason: collision with root package name */
    private int f11879n;
    private Bitmap o;
    private int p;
    private boolean q;
    private String r;
    private boolean s;

    public NewPriceTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11872g = new ArrayList();
        this.f11874i = null;
        this.f11875j = new ArrayList();
        this.f11876k = new ArrayList();
        this.f11877l = new ArrayList();
        this.f11878m = new ArrayList();
        this.f11879n = 3;
        this.q = false;
        this.r = "\\.";
        this.f11874i = context.getResources().getDisplayMetrics();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.newpricetext);
        this.f11878m.add(Integer.valueOf(obtainStyledAttributes.getDimensionPixelSize(0, 0)));
        this.f11878m.add(Integer.valueOf(obtainStyledAttributes.getDimensionPixelSize(1, 0)));
        this.f11878m.add(Integer.valueOf(obtainStyledAttributes.getDimensionPixelSize(2, 0)));
        this.f11877l.addAll(this.f11878m);
        if (Log.D) {
            Log.d("TEST", " NewPriceTextView ---> size : " + this.f11877l.size());
        }
        for (int i2 = 0; i2 < this.f11879n; i2++) {
            TextPaint textPaint = new TextPaint();
            textPaint.setAntiAlias(true);
            this.f11872g.add(textPaint);
        }
        BitmapDrawable bitmapDrawable = (BitmapDrawable) obtainStyledAttributes.getDrawable(3);
        this.q = obtainStyledAttributes.getBoolean(4, false);
        if (bitmapDrawable != null) {
            Bitmap bitmap = bitmapDrawable.getBitmap();
            this.o = bitmap;
            this.p = bitmap.getWidth();
            TextPaint textPaint2 = new TextPaint();
            this.f11873h = textPaint2;
            textPaint2.setAntiAlias(true);
        }
        obtainStyledAttributes.recycle();
    }

    private void a() {
        Paint paint = new Paint();
        paint.setTextSize(TypedValue.applyDimension(2, DPIUtil.px2sp(getContext(), Math.max(Math.max(this.f11877l.get(0).intValue(), this.f11877l.get(1).intValue()), this.f11877l.get(2).intValue())), this.f11874i));
        float measureText = paint.measureText(getText().toString());
        int width = getWidth();
        if (this.q) {
            width -= this.p + 3;
        }
        if (measureText >= width) {
            for (int i2 = 0; i2 < this.f11879n; i2++) {
                List<Integer> list = this.f11877l;
                list.set(i2, Integer.valueOf(list.get(i2).intValue() - 1));
            }
            if (this.f11877l.get(0).intValue() < 10) {
                return;
            }
            a();
        }
    }

    private void b() {
        String[] split = getText().toString().split(this.r);
        if (TextUtils.equals(this.r, "\\.")) {
            if (split.length > 0 && !TextUtils.isEmpty(split[0])) {
                this.f11875j.add(split[0].substring(0, 1));
                this.f11875j.add(split[0].substring(1, split[0].length()));
            } else {
                this.f11875j.add("");
                this.f11875j.add("");
            }
            if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                this.f11875j.add(OrderISVUtil.MONEY_DECIMAL + split[1]);
                return;
            }
            this.f11875j.add("");
        } else if (TextUtils.equals(this.r, "\\*")) {
            if (split.length > 1) {
                this.f11875j.add("");
                this.f11875j.add(split[0]);
                this.f11875j.add(split[1]);
            } else if (split.length == 1) {
                this.f11875j.add("");
                this.f11875j.add(split[0]);
                this.f11875j.add("");
            } else {
                this.f11875j.add("");
                this.f11875j.add(getText().toString());
                this.f11875j.add("");
                if (Log.D) {
                    Log.d("NewPriceTextView", " splitText else--->  getText : " + getText().toString());
                }
            }
        } else {
            this.f11875j.add("");
            this.f11875j.add(getText().toString());
            this.f11875j.add("");
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        this.f11875j.clear();
        this.f11876k.clear();
        this.f11877l.clear();
        this.f11877l.addAll(this.f11878m);
        b();
        for (int i2 = 0; i2 < this.f11879n; i2++) {
            this.f11872g.get(i2).setColor(getCurrentTextColor());
        }
        a();
        float f2 = 0.0f;
        for (int i3 = 0; i3 < this.f11879n; i3++) {
            this.f11872g.get(i3).setTextSize(TypedValue.applyDimension(2, DPIUtil.px2sp(getContext(), this.f11877l.get(i3).intValue()), this.f11874i));
            this.f11876k.add(Float.valueOf(this.f11872g.get(i3).measureText(this.f11875j.get(i3))));
            canvas.drawText(this.f11875j.get(i3), f2, (getHeight() >> 1) + ((this.f11877l.get(1).intValue() * 2) / 5), this.f11872g.get(i3));
            f2 += this.f11876k.get(i3).floatValue();
        }
        if (!this.q || this.o == null) {
            return;
        }
        if (this.s) {
            this.f11873h.setAlpha(128);
        } else {
            this.f11873h.setAlpha(255);
        }
        canvas.drawBitmap(this.o, f2 + 3.0f, (getHeight() >> 1) - (this.f11877l.get(1).intValue() / 5), this.f11873h);
    }
}
