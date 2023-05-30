package com.jingdong.common.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: classes6.dex */
public class AlphaTextView extends TextView {
    public AlphaTextView(Context context) {
        super(context);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public AlphaTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AlphaTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
