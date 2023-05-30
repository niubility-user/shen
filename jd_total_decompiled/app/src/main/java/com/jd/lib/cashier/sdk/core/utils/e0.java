package com.jd.lib.cashier.sdk.core.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.ui.title.IThemeTitle;

/* loaded from: classes14.dex */
public class e0 {
    public static TextView a(View view) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (themeTitle == null || view == null) {
            return null;
        }
        return themeTitle.getRightTv1TextView(view);
    }

    public static TextView b(View view) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (themeTitle == null || view == null) {
            return null;
        }
        return themeTitle.getTitleView(view);
    }

    public static View c(Context context) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (themeTitle == null || context == null) {
            return null;
        }
        return themeTitle.instanceTitleView(context);
    }

    public static boolean d(View view) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        return themeTitle != null && themeTitle.isThemeLightStyleColor(view);
    }

    public static void e(View view) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (themeTitle != null) {
            themeTitle.loadTheme(view);
        }
    }

    public static void f(View view) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (themeTitle != null) {
            themeTitle.notifyChange(view);
        }
    }

    public static void g(View view) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (themeTitle == null || view == null) {
            return;
        }
        themeTitle.onHandDarkMode(view);
    }

    public static void h(View view, boolean z) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (view == null || themeTitle == null) {
            return;
        }
        themeTitle.setCustomOpen(view, z);
    }

    public static void i(View view) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (view == null || themeTitle == null) {
            return;
        }
        themeTitle.setLeft1BackDrawableId(view);
    }

    public static void j(View view, int i2) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (themeTitle == null || view == null) {
            return;
        }
        themeTitle.setLeft1ImageViewVisibility(view, i2);
    }

    public static void k(View view, View.OnClickListener onClickListener) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (themeTitle == null || onClickListener == null) {
            return;
        }
        themeTitle.setLeftIv1ClickListener(view, onClickListener);
    }

    public static void l(View view, View.OnClickListener onClickListener) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (themeTitle == null || onClickListener == null) {
            return;
        }
        themeTitle.setRightTv1ClickListener(view, onClickListener);
    }

    public static void m(View view, String str) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (themeTitle != null) {
            themeTitle.setRightTv1Text(view, str);
        }
    }

    public static void n(View view, int i2) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (themeTitle == null || view == null) {
            return;
        }
        themeTitle.setRightTv1Visibility(view, i2);
    }

    public static void o(View view, Activity activity) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (view == null || activity == null) {
            return;
        }
        themeTitle.setStatusBarColorStyle(view, activity);
    }

    public static void p(View view, boolean z) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (view == null || themeTitle == null) {
            return;
        }
        themeTitle.setStatusBarHint(view, z);
    }

    public static void q(View view, float f2) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (view == null || themeTitle == null) {
            return;
        }
        themeTitle.setTitleBgImageViewAlpha(view, f2);
    }

    public static void r(View view, String str) {
        IThemeTitle themeTitle = DependInitializer.getThemeTitle();
        if (themeTitle == null || view == null) {
            return;
        }
        themeTitle.setTitleText(view, str);
    }
}
