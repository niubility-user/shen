package com.jd.lib.un.basewidget.widget.simple.c;

import android.animation.ValueAnimator;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;

/* loaded from: classes16.dex */
public interface a {
    void a(MotionEvent motionEvent);

    void b(boolean z);

    ValueAnimator.AnimatorUpdateListener c(int i2);

    @NonNull
    View d();

    boolean e();

    void f(e eVar, View view, View view2);

    void g(int i2, int i3, int i4);

    @NonNull
    View getView();

    void h(g gVar);

    boolean i();
}
