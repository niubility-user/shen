package com.tencent.tencentmap.mapsdk.vector.utils.a;

import android.animation.ValueAnimator;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.vector.utils.animation.MarkerTranslateAnimator;

/* loaded from: classes9.dex */
public class c implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ MarkerTranslateAnimator a;

    public c(MarkerTranslateAnimator markerTranslateAnimator) {
        this.a = markerTranslateAnimator;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        double parseDouble = Double.parseDouble(String.valueOf(valueAnimator.getAnimatedValue()));
        if (this.a.getObject() == null) {
            return;
        }
        ((Marker) this.a.getObject()).setRotation((float) parseDouble);
    }
}
