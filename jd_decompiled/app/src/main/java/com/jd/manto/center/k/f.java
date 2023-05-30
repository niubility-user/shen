package com.jd.manto.center.k;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes17.dex */
public class f {
    public static void a(View view) {
        if (view != null) {
            try {
                InputMethodManager inputMethodManager = (InputMethodManager) JdSdk.getInstance().getApplication().getSystemService("input_method");
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void b(View view) {
        if (view != null) {
            try {
                view.requestFocus();
                InputMethodManager inputMethodManager = (InputMethodManager) JdSdk.getInstance().getApplication().getSystemService("input_method");
                if (inputMethodManager != null) {
                    inputMethodManager.showSoftInput(view, 0);
                }
            } catch (Exception unused) {
            }
        }
    }

    public static boolean c() {
        InputMethodManager inputMethodManager = (InputMethodManager) JdSdk.getInstance().getApplication().getSystemService("input_method");
        return inputMethodManager != null && inputMethodManager.isActive();
    }
}
