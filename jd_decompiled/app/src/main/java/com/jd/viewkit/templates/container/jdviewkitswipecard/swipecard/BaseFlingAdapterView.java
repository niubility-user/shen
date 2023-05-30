package com.jd.viewkit.templates.container.jdviewkitswipecard.swipecard;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView;

/* loaded from: classes18.dex */
abstract class BaseFlingAdapterView extends AdapterView {
    private int heightMeasureSpec;
    private int widthMeasureSpec;

    public BaseFlingAdapterView(Context context) {
        super(context);
    }

    public int getHeightMeasureSpec() {
        return this.heightMeasureSpec;
    }

    public int getWidthMeasureSpec() {
        return this.widthMeasureSpec;
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        this.widthMeasureSpec = i2;
        this.heightMeasureSpec = i3;
    }

    @Override // android.widget.AdapterView
    public void setSelection(int i2) {
        throw new UnsupportedOperationException("Not supported");
    }

    public BaseFlingAdapterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BaseFlingAdapterView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
