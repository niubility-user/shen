package com.facebook.react.uimanager;

import android.view.View;

/* loaded from: classes12.dex */
public class MeasureSpecAssertions {
    public static final void assertExplicitMeasureSpec(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        if (mode == 0 || mode2 == 0) {
            throw new IllegalStateException("A catalyst view must have an explicit width and height given to it. This should normally happen as part of the standard catalyst UI framework.");
        }
    }
}
