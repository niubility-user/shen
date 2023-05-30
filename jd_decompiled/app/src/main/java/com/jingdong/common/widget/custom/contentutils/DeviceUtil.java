package com.jingdong.common.widget.custom.contentutils;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.jingdong.jdsdk.JdSdk;

/* loaded from: classes12.dex */
public class DeviceUtil {
    public static void hideSoftInput(View view) {
        if (view == null) {
            return;
        }
        try {
            ((InputMethodManager) JdSdk.getInstance().getApplication().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception unused) {
        }
    }

    public static void showSoftInput(View view) {
        if (view == null) {
            return;
        }
        try {
            ((InputMethodManager) JdSdk.getInstance().getApplication().getSystemService("input_method")).showSoftInput(view, 0);
        } catch (Exception unused) {
        }
    }
}
