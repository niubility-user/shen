package com.jingdong.app.mall.utils.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;
import com.jingdong.app.mall.R;

/* loaded from: classes4.dex */
public class TwinkledTextView extends TextView {

    /* renamed from: g  reason: collision with root package name */
    private int f11903g;

    /* renamed from: h  reason: collision with root package name */
    private int[] f11904h;

    /* renamed from: i  reason: collision with root package name */
    private Handler f11905i;

    /* renamed from: j  reason: collision with root package name */
    private int f11906j;

    /* renamed from: k  reason: collision with root package name */
    private Runnable f11907k;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (TwinkledTextView.this.f11904h != null && TwinkledTextView.this.f11907k != null && TwinkledTextView.this.f11905i != null) {
                TwinkledTextView twinkledTextView = TwinkledTextView.this;
                twinkledTextView.f11906j = (twinkledTextView.f11906j + 1) % TwinkledTextView.this.f11904h.length;
                TwinkledTextView twinkledTextView2 = TwinkledTextView.this;
                twinkledTextView2.setTextColor(twinkledTextView2.f11904h[TwinkledTextView.this.f11906j]);
                TwinkledTextView.this.f11905i.postDelayed(TwinkledTextView.this.f11907k, TwinkledTextView.this.f11903g);
                return;
            }
            TwinkledTextView.this.i();
        }
    }

    public TwinkledTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        g(context, attributeSet);
    }

    private void g(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TwinkledTextView);
        int color = obtainStyledAttributes.getColor(2, getCurrentTextColor());
        int color2 = obtainStyledAttributes.getColor(1, getCurrentTextColor());
        this.f11904h = r0;
        int[] iArr = {color, color2};
        this.f11903g = obtainStyledAttributes.getInt(0, 1000);
        obtainStyledAttributes.recycle();
        h();
    }

    public void h() {
        if (this.f11905i == null) {
            Handler handler = getHandler();
            this.f11905i = handler;
            if (handler == null) {
                this.f11905i = new Handler();
            }
        }
        if (this.f11907k == null) {
            this.f11907k = new a();
        }
        this.f11905i.post(this.f11907k);
    }

    public void i() {
        Handler handler = this.f11905i;
        if (handler != null) {
            handler.removeCallbacks(this.f11907k);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        i();
        this.f11907k = null;
        this.f11905i = null;
        this.f11904h = null;
    }

    @Override // android.view.View
    public void setVisibility(int i2) {
        if (i2 == 0) {
            h();
        } else {
            i();
        }
        super.setVisibility(i2);
    }
}
