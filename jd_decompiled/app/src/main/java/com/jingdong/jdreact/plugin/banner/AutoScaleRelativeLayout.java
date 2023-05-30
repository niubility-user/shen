package com.jingdong.jdreact.plugin.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/* loaded from: classes13.dex */
public class AutoScaleRelativeLayout extends RelativeLayout {
    private float widthHeightRate;

    public AutoScaleRelativeLayout(Context context) {
        this(context, null);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    public AutoScaleRelativeLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AutoScaleRelativeLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.widthHeightRate = 1.0f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.card, 0, 0);
        this.widthHeightRate = obtainStyledAttributes.getFloat(R.styleable.card_widthHeightRate, this.widthHeightRate);
        obtainStyledAttributes.recycle();
    }
}
