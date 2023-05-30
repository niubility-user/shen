package com.jingdong.manto.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

/* loaded from: classes16.dex */
public class c extends FrameLayout {
    private ScrollView a;
    private FrameLayout b;

    /* renamed from: c  reason: collision with root package name */
    private b f14342c;

    /* loaded from: classes16.dex */
    class a extends ScrollView {
        a(Context context) {
            super(context);
        }

        @Override // android.view.View
        protected void onScrollChanged(int i2, int i3, int i4, int i5) {
            super.onScrollChanged(i2, i3, i4, i5);
            if (c.this.f14342c != null) {
                c.this.f14342c.a(c.this, i2, i3);
            }
        }

        @Override // android.widget.ScrollView, android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return super.onTouchEvent(motionEvent);
        }
    }

    /* loaded from: classes16.dex */
    public interface b {
        void a(c cVar, int i2, int i3);
    }

    public c(Context context) {
        super(context);
        this.a = new a(context);
        FrameLayout frameLayout = new FrameLayout(context);
        this.b = frameLayout;
        this.a.addView(frameLayout);
        addView(this.a);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (view == this.a) {
            super.addView(view, i2, layoutParams);
        } else {
            this.b.addView(view, i2 - 1, layoutParams);
        }
    }

    public ViewGroup getTargetView() {
        return this.b;
    }

    @Override // android.view.View
    public void scrollTo(int i2, int i3) {
        this.a.scrollTo(i2, i3);
    }

    @Override // android.view.View
    public void setHorizontalScrollBarEnabled(boolean z) {
        this.a.setHorizontalScrollBarEnabled(z);
    }

    public void setOnScrollChangedListener(b bVar) {
        this.f14342c = bVar;
    }

    @Override // android.view.View
    public void setVerticalScrollBarEnabled(boolean z) {
        this.a.setVerticalScrollBarEnabled(z);
    }
}
