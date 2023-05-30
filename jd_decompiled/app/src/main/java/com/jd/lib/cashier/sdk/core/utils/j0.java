package com.jd.lib.cashier.sdk.core.utils;

import android.view.View;

/* loaded from: classes14.dex */
public class j0 {
    public static boolean a(View view) {
        return view != null && view.getVisibility() == 0;
    }

    public static void b(View view) {
        if (view != null && a(view)) {
            view.setVisibility(8);
        }
    }

    public static void c(View view) {
        if (view != null && a(view)) {
            view.setVisibility(4);
        }
    }

    public static void d(View view) {
        if (view == null || a(view)) {
            return;
        }
        view.setVisibility(0);
    }
}
