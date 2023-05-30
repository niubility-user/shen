package com.facebook.react.views.scroll;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.reflect.Field;

/* loaded from: classes12.dex */
public class OverScrollDelegateUtils {
    public static void delegateOverScrollerForRsv(ReactScrollView reactScrollView) {
        try {
            OverScroller overScroller = new OverScroller(reactScrollView.getContext(), new Interpolator() { // from class: com.facebook.react.views.scroll.OverScrollDelegateUtils.1
                @Override // android.animation.TimeInterpolator
                public float getInterpolation(float f2) {
                    float f3 = f2 - 1.0f;
                    return (f3 * f3 * f3 * f3 * f3) + 1.0f;
                }
            }) { // from class: com.facebook.react.views.scroll.OverScrollDelegateUtils.2
                @Override // android.widget.OverScroller
                public boolean computeScrollOffset() {
                    try {
                        return super.computeScrollOffset();
                    } catch (Throwable th) {
                        th.printStackTrace();
                        return false;
                    }
                }
            };
            Field declaredField = ReactScrollView.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(reactScrollView, overScroller);
            Field declaredField2 = ScrollView.class.getDeclaredField("mScroller");
            declaredField2.setAccessible(true);
            declaredField2.set(reactScrollView, overScroller);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void delegateOverScrollerForRv(RecyclerView recyclerView) {
        try {
            Field declaredField = RecyclerView.class.getDeclaredField("mViewFlinger");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(recyclerView);
            Field declaredField2 = obj.getClass().getDeclaredField("mOverScroller");
            declaredField2.setAccessible(true);
            declaredField2.set(obj, new OverScroller(recyclerView.getContext(), new Interpolator() { // from class: com.facebook.react.views.scroll.OverScrollDelegateUtils.3
                @Override // android.animation.TimeInterpolator
                public float getInterpolation(float f2) {
                    float f3 = f2 - 1.0f;
                    return (f3 * f3 * f3 * f3 * f3) + 1.0f;
                }
            }) { // from class: com.facebook.react.views.scroll.OverScrollDelegateUtils.4
                @Override // android.widget.OverScroller
                public boolean computeScrollOffset() {
                    try {
                        return super.computeScrollOffset();
                    } catch (Throwable th) {
                        th.printStackTrace();
                        return false;
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void delegateQuinticInterpolatorForRv(RecyclerView recyclerView) {
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
