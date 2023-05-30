package com.jingdong.app.mall.utils.ui;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public class JDResizeRelativeLayout extends RelativeLayout {

    /* renamed from: g  reason: collision with root package name */
    private int f11853g;

    /* renamed from: h  reason: collision with root package name */
    private Handler f11854h;

    /* renamed from: i  reason: collision with root package name */
    private OnInputSoftListener f11855i;

    /* loaded from: classes4.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ int f11856g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ int f11857h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f11858i;

        a(int i2, int i3, int i4) {
            this.f11856g = i2;
            this.f11857h = i3;
            this.f11858i = i4;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f11856g - this.f11857h > this.f11858i && (JDResizeRelativeLayout.this.f11853g == 0 || (JDResizeRelativeLayout.this.f11853g > 0 && Math.abs(this.f11856g - JDResizeRelativeLayout.this.f11853g) < 10))) {
                int i2 = JDResizeRelativeLayout.this.f11853g;
                int i3 = this.f11856g;
                if (i2 < i3) {
                    JDResizeRelativeLayout.this.f11853g = i3;
                }
                if (JDResizeRelativeLayout.this.f11855i != null) {
                    try {
                        JDResizeRelativeLayout.this.f11855i.onShow();
                        return;
                    } catch (Exception e2) {
                        if (Log.E) {
                            e2.printStackTrace();
                            return;
                        }
                        return;
                    }
                }
                return;
            }
            int i4 = this.f11857h;
            if (i4 - this.f11856g <= this.f11858i || Math.abs(i4 - JDResizeRelativeLayout.this.f11853g) >= 10 || JDResizeRelativeLayout.this.f11855i == null) {
                return;
            }
            try {
                JDResizeRelativeLayout.this.f11855i.onHide();
            } catch (Exception e3) {
                if (Log.E) {
                    e3.printStackTrace();
                }
            }
        }
    }

    public JDResizeRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11853g = 0;
        this.f11854h = new Handler();
    }

    private int d() {
        int percentHeight = DPIUtil.percentHeight(0.3f);
        if (percentHeight < 200) {
            return 200;
        }
        return percentHeight;
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.f11854h.post(new a(i5, i3, d()));
    }
}
