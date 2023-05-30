package com.jingdong.common.ui;

import android.content.Context;
import android.util.AttributeSet;
import com.facebook.drawee.view.SimpleDraweeView;

/* loaded from: classes6.dex */
public class AlphaSimpleDraweeView extends SimpleDraweeView {
    public AlphaSimpleDraweeView(Context context) {
        super(context);
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public AlphaSimpleDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AlphaSimpleDraweeView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
