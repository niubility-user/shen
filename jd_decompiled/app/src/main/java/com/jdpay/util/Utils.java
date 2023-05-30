package com.jdpay.util;

import android.app.Dialog;
import androidx.annotation.Nullable;
import com.jdpay.lib.util.JDPayLog;

/* loaded from: classes18.dex */
public class Utils {
    public static void dismissDialog(Dialog dialog) {
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        try {
            dialog.dismiss();
        } catch (Throwable th) {
            th.printStackTrace();
            JDPayLog.e(th.getLocalizedMessage());
        }
    }

    public static String getThrowableMessage(@Nullable Throwable th) {
        if (th == null) {
            return null;
        }
        return th.getLocalizedMessage();
    }

    public static void showDialog(Dialog dialog) {
        if (dialog.isShowing()) {
            return;
        }
        try {
            dialog.show();
        } catch (Throwable th) {
            th.printStackTrace();
            JDPayLog.e(th.getLocalizedMessage());
        }
    }
}
