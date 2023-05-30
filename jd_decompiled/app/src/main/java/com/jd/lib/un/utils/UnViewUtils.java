package com.jd.lib.un.utils;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/* loaded from: classes16.dex */
public class UnViewUtils {
    public static int getMeasuredHeight(View view) {
        if (view == null) {
            return -1;
        }
        return measureView(view)[1];
    }

    public static int getMeasuredWidth(View view) {
        if (view == null) {
            return -1;
        }
        return measureView(view)[0];
    }

    public static boolean isViewVisible(View view) {
        return view != null && view.getVisibility() == 0;
    }

    public static int[] measureView(View view) {
        int makeMeasureSpec;
        if (view == null) {
            return null;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i2 = layoutParams.height;
        if (i2 > 0) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, makeMeasureSpec);
        return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
    }

    public static void setEnabled(View view, boolean z) {
        if (view != null) {
            view.setEnabled(z);
        }
    }

    public static void setImageResource(ImageView imageView, int i2) {
        if (imageView != null) {
            imageView.setImageResource(i2);
        }
    }

    public static void setText(TextView textView, CharSequence charSequence) {
        if (textView == null || TextUtils.isEmpty(charSequence)) {
            return;
        }
        textView.setText(charSequence);
    }

    public static void setViewsGone(View... viewArr) {
        for (View view : viewArr) {
            viewGone(view);
        }
    }

    public static void setViewsInvisible(View... viewArr) {
        for (View view : viewArr) {
            viewInvisible(view);
        }
    }

    public static void setViewsVisible(View... viewArr) {
        for (View view : viewArr) {
            viewVisible(view);
        }
    }

    public static void viewGone(View view) {
        if (view == null || view.getVisibility() == 8) {
            return;
        }
        view.setVisibility(8);
    }

    public static void viewInvisible(View view) {
        if (view == null || view.getVisibility() == 4) {
            return;
        }
        view.setVisibility(4);
    }

    public static void viewVisible(View view) {
        if (view == null || view.getVisibility() == 0) {
            return;
        }
        view.setVisibility(0);
    }
}
