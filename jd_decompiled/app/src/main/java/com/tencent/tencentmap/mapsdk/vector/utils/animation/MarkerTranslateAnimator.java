package com.tencent.tencentmap.mapsdk.vector.utils.animation;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.vector.utils.a.a;
import com.tencent.tencentmap.mapsdk.vector.utils.a.b;
import com.tencent.tencentmap.mapsdk.vector.utils.a.c;
import com.tencent.tencentmap.mapsdk.vector.utils.a.f;
import com.tencent.tencentmap.mapsdk.vector.utils.a.g;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class MarkerTranslateAnimator extends OverlayAnimator {
    public LatLng[] d;

    /* renamed from: e  reason: collision with root package name */
    public double[] f18009e;

    /* renamed from: f  reason: collision with root package name */
    public double f18010f;

    /* renamed from: g  reason: collision with root package name */
    public boolean f18011g;

    /* renamed from: h  reason: collision with root package name */
    public AnimatorSet f18012h;

    /* renamed from: i  reason: collision with root package name */
    public g f18013i;

    /* renamed from: j  reason: collision with root package name */
    public volatile boolean f18014j;

    public MarkerTranslateAnimator(Marker marker, long j2, LatLng[] latLngArr) {
        this(marker, j2, latLngArr, false);
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.animation.OverlayAnimator
    public void cancelAnimation() {
        AnimatorSet animatorSet;
        super.cancelAnimation();
        synchronized (this) {
            if (this.f18011g && (animatorSet = this.f18012h) != null) {
                animatorSet.cancel();
            }
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.animation.OverlayAnimator
    public ValueAnimator createSegmentAnimator(int i2) {
        f a = this.f18013i.a(this.d[i2]);
        f a2 = this.f18013i.a(this.d[i2 + 1]);
        ValueAnimator valueAnimator = new ValueAnimator();
        double duration = getDuration();
        double d = this.f18009e[i2];
        Double.isNaN(duration);
        valueAnimator.setDuration((long) ((duration * d) / this.f18010f));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setFloatValues((float) this.f18009e[i2]);
        valueAnimator.addUpdateListener(new a(this, a, a2, i2));
        return valueAnimator;
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.animation.OverlayAnimator
    public void endAnimation() {
        AnimatorSet animatorSet;
        super.endAnimation();
        synchronized (this) {
            if (this.f18011g && (animatorSet = this.f18012h) != null) {
                animatorSet.end();
            }
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.vector.utils.animation.OverlayAnimator
    public void startAnimation() {
        super.startAnimation();
        synchronized (this) {
            if (this.f18011g && this.f18012h != null && !this.f18014j) {
                this.f18014j = true;
                this.f18012h.start();
            }
        }
    }

    public MarkerTranslateAnimator(Marker marker, long j2, LatLng[] latLngArr, boolean z) {
        super(marker, j2);
        this.f18014j = false;
        if (latLngArr == null) {
            return;
        }
        this.d = latLngArr;
        this.f18009e = new double[latLngArr.length - 1];
        this.f18013i = new g();
        int i2 = 0;
        while (i2 < latLngArr.length - 1) {
            int i3 = i2 + 1;
            this.f18009e[i2] = this.f18013i.a(latLngArr[i2], latLngArr[i3]);
            this.f18010f += this.f18009e[i2];
            i2 = i3;
        }
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < latLngArr.length - 1; i4++) {
            arrayList.add(createSegmentAnimator(i4));
        }
        getAnimatorSet().playSequentially(arrayList);
        this.f18011g = z;
        if (z) {
            a();
        }
    }

    public final void a() {
        int i2;
        int i3;
        long j2;
        float f2;
        AnimatorSet animatorSet = new AnimatorSet();
        this.f18012h = animatorSet;
        animatorSet.addListener(new b(this));
        ArrayList arrayList = new ArrayList();
        int i4 = 1;
        int i5 = 0;
        int i6 = 0;
        float f3 = 0.0f;
        long j3 = 0;
        while (true) {
            LatLng[] latLngArr = this.d;
            if (i4 < latLngArr.length) {
                if (latLngArr[i5].equals(latLngArr[i4])) {
                    i2 = i4;
                } else {
                    f a = this.f18013i.a(this.d[i6]);
                    f a2 = this.f18013i.a(this.d[i5]);
                    f a3 = this.f18013i.a(this.d[i4]);
                    double d = a2.a;
                    double d2 = a.b;
                    double d3 = a2.b;
                    i2 = i4;
                    int i7 = i5;
                    float a4 = (float) a(d - a.a, d2 - d3, a3.a - d, d3 - a3.b);
                    if (arrayList.size() == 0) {
                        if (getObject() == null) {
                            return;
                        }
                        float rotation = ((Marker) getObject()).getRotation();
                        a4 = ((float) a(0.0d, 1.0d, a3.a - a2.a, a2.b - a3.b)) - rotation;
                        f2 = rotation;
                        i3 = i7;
                        j2 = 0;
                    } else {
                        double abs = Math.abs(a4);
                        Double.isNaN(abs);
                        double duration = getDuration();
                        Double.isNaN(duration);
                        long j4 = (long) ((duration * (((abs * 3.141592653589793d) * 6.0d) / 180.0d)) / this.f18010f);
                        i3 = i7;
                        j3 = a(i6, i3) - (j4 / 2);
                        j2 = j4;
                        f2 = f3;
                    }
                    float f4 = f2 + a4;
                    arrayList.add(a(f2, f4, j2, j3));
                    i6 = i3;
                    f3 = f4;
                    i5 = i2;
                }
                i4 = i2 + 1;
            } else {
                this.f18012h.playSequentially(arrayList);
                return;
            }
        }
    }

    public final double a(double d, double d2, double d3, double d4) {
        double sqrt = ((d * d3) + (d2 * d4)) / (Math.sqrt((d * d) + (d2 * d2)) * Math.sqrt((d3 * d3) + (d4 * d4)));
        if (Double.isNaN(sqrt)) {
            return 0.0d;
        }
        if (sqrt < -1.0d) {
            sqrt = -1.0d;
        }
        if (sqrt > 1.0d) {
            sqrt = 1.0d;
        }
        double acos = (Math.acos(sqrt) * 180.0d) / 3.141592653589793d;
        if ((d * d4) - (d2 * d3) > 0.0d) {
            acos = -acos;
        }
        return (float) acos;
    }

    public final long a(int i2, int i3) {
        double d = 0.0d;
        while (i2 < i3) {
            d += this.f18009e[i2];
            i2++;
        }
        double duration = getDuration();
        Double.isNaN(duration);
        return (long) ((duration * d) / this.f18010f);
    }

    public final ValueAnimator a(float f2, float f3, long j2, long j3) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f2, f3);
        ofFloat.setDuration(j2);
        ofFloat.setStartDelay(j3);
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.addUpdateListener(new c(this));
        return ofFloat;
    }
}
