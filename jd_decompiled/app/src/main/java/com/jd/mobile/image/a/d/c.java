package com.jd.mobile.image.a.d;

import android.view.View;
import com.facebook.drawee.view.DraweeHolder;

/* loaded from: classes17.dex */
public class c implements View.OnAttachStateChangeListener {

    /* renamed from: g  reason: collision with root package name */
    private DraweeHolder f6843g;

    public c(DraweeHolder draweeHolder) {
        this.f6843g = draweeHolder;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f6843g.onAttach();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.f6843g.onDetach();
    }
}
