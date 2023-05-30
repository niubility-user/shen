package com.jingdong.app.mall.home.p.b.d;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.jingdong.app.mall.home.o.a.f;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class a extends Animation {

    /* renamed from: g  reason: collision with root package name */
    private final AtomicBoolean f10517g = new AtomicBoolean(false);

    /* renamed from: h  reason: collision with root package name */
    private final View f10518h;

    /* renamed from: i  reason: collision with root package name */
    private final View f10519i;

    public a(@NotNull View view, @NotNull View view2) {
        this.f10518h = view;
        this.f10519i = view2;
        view2.setAlpha(0.0f);
        view.setPivotX(view.getWidth() >> 1);
        setDuration(600L);
        setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public void a() {
        this.f10518h.setScaleX(1.0f);
        this.f10519i.setAlpha(1.0f);
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f2, Transformation transformation) {
        if ((f2 > 0.5f) && !this.f10517g.getAndSet(true)) {
            this.f10519i.setAlpha(1.0f);
        }
        float abs = Math.abs(f2 - 0.5f);
        f.r0(this, "time+=" + abs);
        this.f10518h.setScaleX(abs / 0.5f);
    }
}
