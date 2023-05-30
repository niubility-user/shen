package com.jingdong.manto.widget.input;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.os.IBinder;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.widget.MantoStatusBarUtil;
import java.lang.reflect.Method;

/* loaded from: classes16.dex */
public class InputUtil {
    static Class ClsComposingText = null;
    private static final String TAG = "InputUtil";

    /* loaded from: classes16.dex */
    public static class a {
        public static void a(EditText editText) {
            if (editText != null) {
                InputUtil.getInputManager(editText).hideSoftInputFromInputMethod(editText.getWindowToken(), 2);
            }
        }

        public static void b(EditText editText) {
            if (editText != null) {
                if (Build.VERSION.SDK_INT >= 21) {
                    editText.setShowSoftInputOnFocus(false);
                    return;
                }
                try {
                    Method method = EditText.class.getMethod("setShowSoftInputOnFocus", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(editText, Boolean.FALSE);
                    method.setAccessible(false);
                } catch (NoSuchMethodException unused) {
                    MantoLog.i("EditTextUtil", String.format("setNoSystemInputOnEditText, setShowSoftInputOnFocus no such method, api level = %d", Integer.valueOf(Build.VERSION.SDK_INT)));
                    try {
                        Method method2 = EditText.class.getMethod("setSoftInputShownOnFocus", Boolean.TYPE);
                        method2.setAccessible(true);
                        method2.invoke(editText, Boolean.FALSE);
                        method2.setAccessible(false);
                    } catch (Throwable th) {
                        MantoLog.e("EditTextUtil", "setNoSystemInputOnEditText, reflect method [setSoftInputShownOnFocus], exp = %s", th);
                        com.jingdong.manto.utils.e.a(editText);
                    }
                } catch (Throwable th2) {
                    MantoLog.e("EditTextUtil", "setNoSystemInputOnEditText, reflect method [setShowSoftInputOnFocus], exp = %s", th2);
                }
            }
        }
    }

    static {
        Class<?> cls;
        try {
            cls = Class.forName("android.view.inputmethod.ComposingText");
        } catch (ClassNotFoundException e2) {
            MantoLog.e(TAG, "class for ComposingText e = %s", e2);
            cls = null;
        }
        ClsComposingText = cls;
    }

    public static InputMethodManager getInputManager(View view) {
        return (InputMethodManager) (view == null ? com.jingdong.manto.c.a() : view.getContext()).getSystemService("input_method");
    }

    public static int getTopHeight(Activity activity) {
        int statusBarHeight = MantoStatusBarUtil.getStatusBarHeight(activity);
        if (statusBarHeight > 0) {
            Rect rect = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            if (rect.top > statusBarHeight) {
                return 0;
            }
            return statusBarHeight;
        } else if (activity instanceof Activity) {
            Rect rect2 = new Rect();
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect2);
            int height = activity.getWindow().getDecorView().getHeight();
            int[] iArr = new int[2];
            activity.getWindow().getDecorView().getLocationOnScreen(iArr);
            return (height - rect2.height() < 0 || iArr[1] <= 200) ? rect2.top : height - rect2.height();
        } else {
            return MantoDensityUtils.dip2pixel(activity, 20);
        }
    }

    public static boolean hasSmartBar() {
        return false;
    }

    public static void hideVKB(Activity activity) {
        InputMethodManager inputMethodManager;
        View currentFocus;
        IBinder windowToken;
        if (activity == null || activity.isFinishing() || (inputMethodManager = (InputMethodManager) activity.getSystemService("input_method")) == null || (currentFocus = activity.getCurrentFocus()) == null || (windowToken = currentFocus.getWindowToken()) == null) {
            return;
        }
        try {
            inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
        } catch (IllegalArgumentException e2) {
            MantoLog.e(TAG, "hide VKB exception %s", e2);
        }
    }

    public static boolean isComposingText(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            Object[] spans = spanned.getSpans(0, spanned.length(), ClsComposingText);
            return spans != null && spans.length > 0;
        }
        return false;
    }

    public static boolean isInstanceOfComposingText(Object obj) {
        Class cls = ClsComposingText;
        if (cls == null) {
            return false;
        }
        return cls.isInstance(obj);
    }

    public static boolean isTrue(Boolean bool) {
        return bool != null && bool.booleanValue();
    }

    public static void resetPadding(Activity activity, View view) {
    }

    public static Spannable s(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        return charSequence instanceof Spannable ? (Spannable) charSequence : new SpannableStringBuilder(charSequence);
    }
}
