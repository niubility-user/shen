package com.jdpay.membercode.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import com.jdpay.lib.util.JDPayLog;

/* loaded from: classes18.dex */
public abstract class b implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: g */
    final CodeView f7566g;

    /* renamed from: h */
    View f7567h;

    public b(@NonNull CodeView codeView) {
        this.f7566g = codeView;
    }

    public int a(int i2) {
        Context context = this.f7566g.getContext();
        return ResourcesCompat.getColor(context.getResources(), i2, context.getTheme());
    }

    protected abstract View b(@NonNull Context context);

    public void c() {
        if (this.f7567h == null) {
            View b = b(this.f7566g.getContext());
            this.f7567h = b;
            b.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
        if (this.f7567h.getParent() == null) {
            int width = this.f7566g.getWidth();
            int height = this.f7566g.getHeight();
            if (width <= 0 || height <= 0) {
                this.f7566g.addView(this.f7567h, -1, -2);
            } else {
                this.f7566g.addView(this.f7567h, width, height);
                onGlobalLayout();
            }
            JDPayLog.i("Size:" + width + "/" + height);
        }
    }

    public void d() {
    }

    public void e() {
        if (f()) {
            this.f7566g.removeView(this.f7567h);
        }
    }

    public boolean f() {
        View view = this.f7567h;
        return (view == null || view.getParent() == null) ? false : true;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
    }
}
