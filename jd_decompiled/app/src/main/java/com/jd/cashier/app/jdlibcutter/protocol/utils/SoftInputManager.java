package com.jd.cashier.app.jdlibcutter.protocol.utils;

import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.FragmentActivity;

/* loaded from: classes13.dex */
public class SoftInputManager {
    public static void hideSoftInput(FragmentActivity fragmentActivity) {
        if (fragmentActivity != null) {
            try {
                ((InputMethodManager) fragmentActivity.getSystemService("input_method")).hideSoftInputFromWindow(fragmentActivity.getWindow().getDecorView().getWindowToken(), 0);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
