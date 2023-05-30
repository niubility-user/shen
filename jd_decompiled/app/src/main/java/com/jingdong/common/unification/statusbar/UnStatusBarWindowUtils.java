package com.jingdong.common.unification.statusbar;

import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

/* loaded from: classes6.dex */
public class UnStatusBarWindowUtils {
    private static final int WINDOW_LIGHT_MODE_FLAG = 8192;
    private static final int WINDOW_NOT_FOCUSABLE_FLAG = 8;

    public static void changeWindowMode(Dialog dialog, boolean z) {
        View decorView;
        if (dialog == null || (decorView = dialog.getWindow().getDecorView()) == null) {
            return;
        }
        changeWindowMode(decorView, z);
    }

    private static void changeWindowToDarkMode(View view) {
        if (view == null || isDarkMode(view)) {
            return;
        }
        view.setSystemUiVisibility(view.getSystemUiVisibility() & (-8193));
    }

    private static void changeWindowToLightMode(View view) {
        if (view == null || isLightMode(view)) {
            return;
        }
        view.setSystemUiVisibility(view.getSystemUiVisibility() | 8192);
    }

    public static boolean isDarkMode(View view) {
        return !isLightMode(view);
    }

    public static boolean isFocusable(Dialog dialog) {
        Window window;
        WindowManager.LayoutParams attributes;
        return (dialog == null || (window = dialog.getWindow()) == null || (attributes = window.getAttributes()) == null || (attributes.flags & 8) == 8) ? false : true;
    }

    public static boolean isLightMode(View view) {
        return view != null && (view.getSystemUiVisibility() & 8192) == 8192;
    }

    public static void setFocusable(Dialog dialog, boolean z) {
        Window window;
        if (dialog == null || isFocusable(dialog) == z || (window = dialog.getWindow()) == null) {
            return;
        }
        if (z) {
            window.clearFlags(8);
        } else {
            window.addFlags(8);
        }
    }

    public static void changeWindowMode(PopupWindow popupWindow, boolean z) {
        View contentView;
        if (popupWindow == null || (contentView = popupWindow.getContentView()) == null) {
            return;
        }
        changeWindowMode(contentView, z);
    }

    public static boolean isFocusable(PopupWindow popupWindow) {
        if (popupWindow == null) {
            return false;
        }
        return popupWindow.isFocusable();
    }

    private static void changeWindowMode(View view, boolean z) {
        if (z) {
            changeWindowToLightMode(view);
        } else {
            changeWindowToDarkMode(view);
        }
    }

    public static void setFocusable(PopupWindow popupWindow, boolean z) {
        if (popupWindow == null || isFocusable(popupWindow) == z) {
            return;
        }
        popupWindow.setFocusable(z);
    }
}
