package com.jingdong.common.jdreactFramework.floatingview;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;

/* loaded from: classes5.dex */
public interface IFloatingView {
    FloatingView add();

    FloatingView attach(Activity activity);

    FloatingView attach(FrameLayout frameLayout);

    FloatingView customView(@LayoutRes int i2);

    FloatingView customView(FloatingMagnetView floatingMagnetView);

    FloatingView detach(Activity activity);

    FloatingView detach(FrameLayout frameLayout);

    FloatingMagnetView getView();

    FloatingView icon(@DrawableRes int i2);

    FloatingView layoutParams(ViewGroup.LayoutParams layoutParams);

    FloatingView listener(MagnetViewListener magnetViewListener);

    FloatingView remove();

    FloatingView text(String str);
}
