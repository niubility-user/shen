package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;
import com.jingdong.app.mall.home.o.a.f;

/* loaded from: classes4.dex */
public class JDViewFlipper extends ViewFlipper {
    public JDViewFlipper(Context context) {
        super(context);
    }

    @Override // android.widget.ViewFlipper, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        try {
            super.onAttachedToWindow();
        } catch (Throwable th) {
            f.s0(this, th);
        }
    }

    @Override // android.widget.ViewFlipper, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        } catch (IllegalArgumentException unused) {
            stopFlipping();
        } catch (Throwable th) {
            f.s0(this, th);
        }
    }

    public JDViewFlipper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
