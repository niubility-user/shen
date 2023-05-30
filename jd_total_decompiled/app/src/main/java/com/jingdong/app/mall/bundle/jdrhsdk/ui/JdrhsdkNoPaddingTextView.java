package com.jingdong.app.mall.bundle.jdrhsdk.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;
import com.jingdong.app.mall.bundle.jdrhsdk.R;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes2.dex */
public class JdrhsdkNoPaddingTextView extends TextView {

    /* renamed from: g  reason: collision with root package name */
    private Paint f8179g;

    /* renamed from: h  reason: collision with root package name */
    private Rect f8180h;

    /* renamed from: i  reason: collision with root package name */
    private Boolean f8181i;

    public JdrhsdkNoPaddingTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8179g = getPaint();
        this.f8180h = new Rect();
        this.f8181i = Boolean.FALSE;
        b(context, attributeSet);
    }

    public JdrhsdkNoPaddingTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f8179g = getPaint();
        this.f8180h = new Rect();
        this.f8181i = Boolean.FALSE;
        b(context, attributeSet);
    }

    private String a() {
        String charSequence = getText().toString();
        int length = charSequence.length();
        this.f8179g.getTextBounds(charSequence, 0, length, this.f8180h);
        if (length == 0) {
            Rect rect = this.f8180h;
            rect.right = rect.left;
        }
        return charSequence;
    }

    private void b(Context context, AttributeSet attributeSet) {
        try {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.JdrhsdkNoPaddingTextView);
            this.f8181i = Boolean.valueOf(obtainStyledAttributes.getBoolean(R.styleable.JdrhsdkNoPaddingTextView_jdrhsdk_removeDefaultPadding, true));
            obtainStyledAttributes.recycle();
            if (obtainStyledAttributes != null) {
                obtainStyledAttributes.close();
            }
        } catch (Throwable unused) {
        }
    }

    private void c(Canvas canvas) {
        String a = a();
        Rect rect = this.f8180h;
        int i2 = rect.left;
        int i3 = rect.bottom;
        int i4 = -i2;
        rect.offset(i4, -rect.top);
        this.f8179g.setAntiAlias(true);
        this.f8179g.setColor(getCurrentTextColor());
        canvas.drawText(a, i4, this.f8180h.bottom - i3, this.f8179g);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        c(canvas);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.f8181i.booleanValue()) {
            a();
            Rect rect = this.f8180h;
            setMeasuredDimension(rect.right - rect.left, (-rect.top) + rect.bottom);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
    }
}
