package com.jd.lib.cashier.sdk.core.utils;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.ui.toast.IToast;

/* loaded from: classes14.dex */
public class f0 {
    public static void a(Context context, int i2) {
        IToast toast = DependInitializer.getToast();
        if (toast != null) {
            toast.showToast(context, i2);
        }
    }

    public static void b(FragmentActivity fragmentActivity, String str) {
        IToast toast = DependInitializer.getToast();
        if (toast != null) {
            toast.showToast(fragmentActivity, str);
        }
    }

    public static void c(String str) {
        IToast toast = DependInitializer.getToast();
        if (toast != null) {
            toast.showToast(str);
        }
    }
}
