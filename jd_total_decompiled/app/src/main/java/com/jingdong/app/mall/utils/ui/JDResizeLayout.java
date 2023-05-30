package com.jingdong.app.mall.utils.ui;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* loaded from: classes4.dex */
public class JDResizeLayout extends LinearLayout {
    private static final int SOFTKEYPAD_MIN_HEIGHT = 80;
    private OnInputSoftListener mListener;
    private int mMaxHeight;
    private Handler uiHandler;

    /* loaded from: classes4.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f11850g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f11851h;

        a(int i2, int i3) {
            this.f11850g = i2;
            this.f11851h = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f11850g - this.f11851h > 80 && (JDResizeLayout.this.mMaxHeight == 0 || (JDResizeLayout.this.mMaxHeight > 0 && Math.abs(this.f11850g - JDResizeLayout.this.mMaxHeight) < 10))) {
                int i2 = JDResizeLayout.this.mMaxHeight;
                int i3 = this.f11850g;
                if (i2 < i3) {
                    JDResizeLayout.this.mMaxHeight = i3;
                }
                if (JDResizeLayout.this.mListener != null) {
                    JDResizeLayout.this.mListener.onShow();
                    return;
                }
                return;
            }
            int i4 = this.f11851h;
            if (i4 - this.f11850g <= 80 || Math.abs(i4 - JDResizeLayout.this.mMaxHeight) >= 10 || JDResizeLayout.this.mListener == null) {
                return;
            }
            JDResizeLayout.this.mListener.onHide();
        }
    }

    public JDResizeLayout(Context context) {
        super(context);
        this.mMaxHeight = 0;
        this.uiHandler = new Handler();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.uiHandler.post(new a(i5, i3));
    }

    public void setInputSoftListener(OnInputSoftListener onInputSoftListener) {
        this.mListener = onInputSoftListener;
    }

    public JDResizeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMaxHeight = 0;
        this.uiHandler = new Handler();
    }
}
