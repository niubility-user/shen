package com.jingdong.app.mall.home.o.a;

import android.content.Context;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.reflect.Field;

/* loaded from: classes4.dex */
public class e {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements Interpolator {
        a() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends OverScroller {
        b(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override // android.widget.OverScroller
        public boolean computeScrollOffset() {
            try {
                return super.computeScrollOffset();
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
    }

    public static void a(RecyclerView recyclerView) {
        try {
            if (l.o()) {
                return;
            }
            Field declaredField = RecyclerView.class.getDeclaredField("mViewFlinger");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(recyclerView);
            Field declaredField2 = obj.getClass().getDeclaredField("mOverScroller");
            declaredField2.setAccessible(true);
            declaredField2.set(obj, new b(recyclerView.getContext(), new a()));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(RecyclerView recyclerView) {
        try {
            Field declaredField = RecyclerView.class.getDeclaredField("sQuinticInterpolator");
            declaredField.setAccessible(true);
            AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
            declaredField.setAccessible(true);
            declaredField.set(recyclerView, accelerateDecelerateInterpolator);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
