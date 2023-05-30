package com.jingdong.common.sample.jshop.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes6.dex */
public class CenterTextView extends TextView {
    private StaticLayout myStaticLayout;
    private TextPaint tp;

    public CenterTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void initView() {
        TextPaint textPaint = new TextPaint(1);
        this.tp = textPaint;
        textPaint.setTextSize(getTextSize());
        this.tp.setColor(getCurrentTextColor());
        this.myStaticLayout = new StaticLayout(getText(), this.tp, getWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        this.myStaticLayout.draw(canvas);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        initView();
    }
}
