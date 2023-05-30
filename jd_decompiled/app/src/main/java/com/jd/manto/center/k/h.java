package com.jd.manto.center.k;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes17.dex */
public class h {
    private static long a;

    public static void a(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "rotation", 0.0f, 180.0f);
        ofFloat.setDuration(0L);
        ofFloat.start();
    }

    public static void b(View view) {
        if (view instanceof ViewStub) {
            view.setVisibility(8);
        } else if (view == null || view.getVisibility() == 8) {
        } else {
            view.setVisibility(8);
        }
    }

    public static void c(View view) {
        if (view instanceof ViewStub) {
            view.setVisibility(4);
        } else if (view == null || view.getVisibility() == 4) {
        } else {
            view.setVisibility(4);
        }
    }

    public static synchronized boolean d() {
        boolean e2;
        synchronized (h.class) {
            e2 = e(500L);
        }
        return e2;
    }

    public static synchronized boolean e(long j2) {
        synchronized (h.class) {
            long currentTimeMillis = System.currentTimeMillis();
            long j3 = a;
            if (currentTimeMillis - j3 >= j2 || currentTimeMillis - j3 <= 0) {
                a = currentTimeMillis;
                return false;
            }
            return true;
        }
    }

    public static void f(ImageView imageView, int i2) {
        if (imageView != null) {
            imageView.setImageResource(i2);
        }
    }

    public static void g(Context context, SimpleDraweeView simpleDraweeView, float f2) {
        if (simpleDraweeView == null || context == null) {
            return;
        }
        RoundingParams fromCornersRadius = RoundingParams.fromCornersRadius(f2);
        GenericDraweeHierarchyBuilder newInstance = GenericDraweeHierarchyBuilder.newInstance(context.getResources());
        newInstance.setRoundingParams(fromCornersRadius);
        simpleDraweeView.setHierarchy(newInstance.build());
    }

    public static void h(TextView textView, int i2) {
        if (textView != null) {
            textView.setText(i2);
        }
    }

    public static void i(TextView textView, String str) {
        if (textView != null) {
            textView.setText(str);
        }
    }

    public static void j(TextView textView, float f2) {
        if (textView != null) {
            textView.setTextSize(TextScaleModeHelper.INSTANCE.getInstance().getScaleSize(JdSdk.getInstance().getApplicationContext(), f2));
        }
    }

    public static void k(View view, int i2) {
        if (view != null) {
            ViewCompat.setBackground(view, g.a(i2));
        }
    }

    public static void l(View view) {
        if (view instanceof ViewStub) {
            view.setVisibility(0);
        } else if (view == null || view.getVisibility() == 0) {
        } else {
            view.setVisibility(0);
        }
    }
}
