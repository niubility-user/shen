package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;
import com.tencent.mapsdk.internal.z7;
import com.tencent.tencentmap.mapsdk.maps.model.Animation;
import com.tencent.tencentmap.mapsdk.maps.model.AnimationListener;

/* loaded from: classes9.dex */
public abstract class i7 implements Animation {
    public z7 a = null;
    public a b;

    /* loaded from: classes9.dex */
    public static class a implements z7.a {
        private AnimationListener a;

        /* renamed from: com.tencent.mapsdk.internal.i7$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public class RunnableC0803a implements Runnable {
            public RunnableC0803a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.a.onAnimationStart();
            }
        }

        /* loaded from: classes9.dex */
        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.a.onAnimationEnd();
            }
        }

        public a(AnimationListener animationListener) {
            this.a = animationListener;
        }

        @Override // com.tencent.mapsdk.internal.z7.a
        public void a() {
            if (this.a != null) {
                ba.b(new b());
            }
        }

        @Override // com.tencent.mapsdk.internal.z7.a
        public void onAnimationStart() {
            if (this.a != null) {
                ba.b(new RunnableC0803a());
            }
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Animation
    public AnimationListener getAnimationListener() {
        a aVar = this.b;
        if (aVar != null) {
            return aVar.a;
        }
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Animation
    public long getDuration() {
        z7 z7Var = this.a;
        if (z7Var != null) {
            return z7Var.c();
        }
        return 0L;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Animation
    public Interpolator getInterpolator() {
        z7 z7Var = this.a;
        if (z7Var != null) {
            return z7Var.d();
        }
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Animation
    public void setAnimationListener(AnimationListener animationListener) {
        a aVar = new a(animationListener);
        this.b = aVar;
        z7 z7Var = this.a;
        if (z7Var != null) {
            z7Var.a(aVar);
        }
    }
}
