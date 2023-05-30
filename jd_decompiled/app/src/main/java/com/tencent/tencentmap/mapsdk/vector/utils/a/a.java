package com.tencent.tencentmap.mapsdk.vector.utils.a;

import android.animation.ValueAnimator;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.vector.utils.animation.MarkerTranslateAnimator;

/* loaded from: classes9.dex */
public class a implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ f a;
    public final /* synthetic */ f b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ int f18004c;
    public final /* synthetic */ MarkerTranslateAnimator d;

    public a(MarkerTranslateAnimator markerTranslateAnimator, f fVar, f fVar2, int i2) {
        this.d = markerTranslateAnimator;
        this.a = fVar;
        this.b = fVar2;
        this.f18004c = i2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        double[] dArr;
        double[] dArr2;
        g gVar;
        if (this.a.equals(this.b)) {
            return;
        }
        double parseDouble = Double.parseDouble(String.valueOf(valueAnimator.getAnimatedValue()));
        double d = this.a.a;
        dArr = this.d.f18009e;
        double d2 = d + (((this.b.a - d) * parseDouble) / dArr[this.f18004c]);
        double d3 = this.a.b;
        dArr2 = this.d.f18009e;
        double d4 = d3 + (((this.b.b - d3) * parseDouble) / dArr2[this.f18004c]);
        if (this.d.getObject() == null) {
            return;
        }
        gVar = this.d.f18013i;
        ((Marker) this.d.getObject()).setPosition(gVar.a(new f(d2, d4)));
    }
}
