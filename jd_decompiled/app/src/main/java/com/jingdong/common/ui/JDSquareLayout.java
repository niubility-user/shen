package com.jingdong.common.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/* loaded from: classes6.dex */
public class JDSquareLayout extends RelativeLayout {
    public JDSquareLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i2);
    }

    public JDSquareLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public JDSquareLayout(Context context) {
        super(context);
    }
}
