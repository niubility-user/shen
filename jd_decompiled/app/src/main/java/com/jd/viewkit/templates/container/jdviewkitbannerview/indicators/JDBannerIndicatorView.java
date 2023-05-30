package com.jd.viewkit.templates.container.jdviewkitbannerview.indicators;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

/* loaded from: classes18.dex */
public abstract class JDBannerIndicatorView<T> extends LinearLayout implements IndicatorListener<T> {
    protected int total;

    public JDBannerIndicatorView(Context context) {
        super(context);
    }

    @Override // com.jd.viewkit.templates.container.jdviewkitbannerview.indicators.IndicatorListener
    public int getRealCount() {
        return this.total;
    }

    public JDBannerIndicatorView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public JDBannerIndicatorView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        setOrientation(0);
        setGravity(80);
    }
}
