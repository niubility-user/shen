package com.jingdong.app.mall.bundle.order_center_isv_core.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/* loaded from: classes3.dex */
public class OrderISVWidgetUtil {
    public static void addRule(View view, int i2, int i3) {
        if (view != null && (view.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.addRule(i2, i3);
            view.setLayoutParams(layoutParams);
        }
    }

    public static boolean isVisible(View view) {
        return view != null && view.getVisibility() == 0;
    }

    public static void setGone(View view) {
        if (view != null && isVisible(view)) {
            view.setVisibility(8);
        }
    }

    public static void setInVisible(View view) {
        if (view != null && isVisible(view)) {
            view.setVisibility(4);
        }
    }

    public static void setMargins(View view, int i2, int i3, int i4, int i5) {
        if (view != null && (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            marginLayoutParams.setMargins(i2, i3, i4, i5);
            view.setLayoutParams(marginLayoutParams);
        }
    }

    public static void setVisible(View view) {
        if (view == null || isVisible(view)) {
            return;
        }
        view.setVisibility(0);
    }

    public static void setWidthAndHeight(View view, int i2, int i3) {
        if (view != null && (view.getLayoutParams() instanceof ViewGroup.LayoutParams)) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = i2;
            layoutParams.height = i3;
            view.setLayoutParams(layoutParams);
        }
    }

    public static void addRule(View view, int i2) {
        if (view != null && (view.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            layoutParams.addRule(i2);
            view.setLayoutParams(layoutParams);
        }
    }
}
