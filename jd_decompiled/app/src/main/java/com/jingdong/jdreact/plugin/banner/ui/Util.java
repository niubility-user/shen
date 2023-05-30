package com.jingdong.jdreact.plugin.banner.ui;

import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.FrameLayout;
import com.jingdong.common.jdreactFramework.JDReactHelper;

/* loaded from: classes13.dex */
public abstract class Util {
    public static final FrameLayout.LayoutParams createLayoutParams(int i2, int i3) {
        return new FrameLayout.LayoutParams(i2, i3);
    }

    public static final FrameLayout.LayoutParams createMatchParams() {
        return createLayoutParams(-1, -1);
    }

    public static final FrameLayout.LayoutParams createMatchWrapParams() {
        return createLayoutParams(-1, -2);
    }

    public static final FrameLayout.LayoutParams createWrapMatchParams() {
        return createLayoutParams(-2, -1);
    }

    public static final FrameLayout.LayoutParams createWrapParams() {
        return createLayoutParams(-2, -2);
    }

    public static final int dpToPx(float f2, Resources resources) {
        try {
            return (int) TypedValue.applyDimension(1, f2, JDReactHelper.newInstance().getDisplayMetricsObject(JDReactHelper.newInstance().getApplication()));
        } catch (Exception unused) {
            return 0;
        }
    }
}
