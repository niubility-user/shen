package com.jd.lib.un.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import com.jd.lib.un.utils.config.UnDeviceInfo;

/* loaded from: classes16.dex */
public class UnKeyboardUtils {
    private static ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    private static OnSoftInputChangedListener onSoftInputChangedListener;
    private static int sContentViewInvisibleHeightPre;

    /* loaded from: classes16.dex */
    public interface OnSoftInputChangedListener {
        void onSoftInputChanged(int i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getContentViewInvisibleHeight(Activity activity) {
        View findViewById = activity.findViewById(16908290);
        Rect rect = new Rect();
        findViewById.getWindowVisibleDisplayFrame(rect);
        return rect.bottom - findViewById.getBottom();
    }

    public static void hideSoftInput(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    public static boolean isSoftInputVisible(Activity activity) {
        return isSoftInputVisible(activity, 200);
    }

    public static void registerSoftInputChangedListener(final Activity activity, OnSoftInputChangedListener onSoftInputChangedListener2) {
        if ((activity.getWindow().getAttributes().flags & 512) != 0) {
            activity.getWindow().clearFlags(512);
        }
        View findViewById = activity.findViewById(16908290);
        sContentViewInvisibleHeightPre = getContentViewInvisibleHeight(activity);
        onSoftInputChangedListener = onSoftInputChangedListener2;
        onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.jd.lib.un.utils.UnKeyboardUtils.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                int contentViewInvisibleHeight;
                if (UnKeyboardUtils.onSoftInputChangedListener == null || UnKeyboardUtils.sContentViewInvisibleHeightPre == (contentViewInvisibleHeight = UnKeyboardUtils.getContentViewInvisibleHeight(activity))) {
                    return;
                }
                UnKeyboardUtils.onSoftInputChangedListener.onSoftInputChanged(contentViewInvisibleHeight);
                int unused = UnKeyboardUtils.sContentViewInvisibleHeightPre = contentViewInvisibleHeight;
            }
        };
        findViewById.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    public static void showSoftInput(Activity activity) {
        InputMethodManager inputMethodManager = activity.getSystemService("input_method") instanceof InputMethodManager ? (InputMethodManager) activity.getSystemService("input_method") : null;
        if (inputMethodManager == null) {
            return;
        }
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = new View(activity);
            currentFocus.setFocusable(true);
            currentFocus.setFocusableInTouchMode(true);
            currentFocus.requestFocus();
        }
        inputMethodManager.showSoftInput(currentFocus, 2);
    }

    public static void toggleSoftInput(Context context) {
        InputMethodManager inputMethodManager = context.getApplicationContext().getSystemService("input_method") instanceof InputMethodManager ? (InputMethodManager) context.getApplicationContext().getSystemService("input_method") : null;
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(2, 0);
    }

    public static void unregisterSoftInputChangedListener(Activity activity) {
        View findViewById = activity.findViewById(16908290);
        if (findViewById.getViewTreeObserver() != null && onGlobalLayoutListener != null) {
            if (UnDeviceInfo.getSdkVersion() >= 16) {
                findViewById.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
            } else {
                findViewById.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
            }
        }
        onSoftInputChangedListener = null;
        onGlobalLayoutListener = null;
    }

    public static boolean isSoftInputVisible(Activity activity, int i2) {
        return getContentViewInvisibleHeight(activity) >= i2;
    }

    public static void hideSoftInput(View view, Context context) {
        InputMethodManager inputMethodManager = context.getApplicationContext().getSystemService("input_method") instanceof InputMethodManager ? (InputMethodManager) context.getApplicationContext().getSystemService("input_method") : null;
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showSoftInput(View view, Context context) {
        InputMethodManager inputMethodManager = context.getApplicationContext().getSystemService("input_method") instanceof InputMethodManager ? (InputMethodManager) context.getApplicationContext().getSystemService("input_method") : null;
        if (inputMethodManager == null) {
            return;
        }
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 2);
    }
}
