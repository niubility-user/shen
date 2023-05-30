package com.jd.lib.cashier.sdk.core.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog;

/* loaded from: classes14.dex */
public class j {
    public static void a(Dialog dialog, View view, float f2) {
        IDialog dialog2 = DependInitializer.getDialog();
        if (dialog == null || dialog2 == null || view == null) {
            return;
        }
        dialog2.addContentWithHeight(dialog, view, f2);
    }

    public static Dialog b(Context context) {
        IDialog dialog = DependInitializer.getDialog();
        if (dialog != null) {
            return dialog.createBottomDialog(context);
        }
        return null;
    }

    public static Dialog c(Context context, String str, String str2) {
        IDialog dialog = DependInitializer.getDialog();
        if (dialog != null) {
            return dialog.createJdDialogWithStyle1(context, str, str2);
        }
        return null;
    }

    public static Dialog d(Context context, String str, String str2, View view, String str3, String str4) {
        IDialog dialog = DependInitializer.getDialog();
        if (dialog != null) {
            return dialog.createJdDialogWithStyle10(context, str, str2, view, str3, str4);
        }
        return null;
    }

    public static Dialog e(Context context, String str, String str2, String str3) {
        IDialog dialog = DependInitializer.getDialog();
        if (dialog != null) {
            return dialog.createJdDialogWithStyle2(context, str, str2, str3);
        }
        return null;
    }

    public static Dialog f(Context context, String str, String str2, String str3) {
        IDialog dialog = DependInitializer.getDialog();
        if (dialog != null) {
            return dialog.createJdDialogWithStyle5(context, str, str2, str3);
        }
        return null;
    }

    public static Dialog g(Context context, String str, String str2, String str3, String str4) {
        IDialog dialog = DependInitializer.getDialog();
        if (dialog != null) {
            return dialog.createJdDialogWithStyle6(context, str, str2, str3, str4);
        }
        return null;
    }

    public static Dialog h(Context context, String str, String str2, View view, String str3, String str4) {
        IDialog dialog = DependInitializer.getDialog();
        if (dialog != null) {
            return dialog.createJdDialogWithStyle9(context, str, str2, view, str3, str4);
        }
        return null;
    }

    public static Dialog i(Context context, String str, String str2, View view, int i2, String str3, String str4) {
        IDialog dialog = DependInitializer.getDialog();
        if (dialog != null) {
            return dialog.createJdDialogWithStyle9WithGravity(context, str, str2, view, i2, str3, str4);
        }
        return null;
    }

    public static Dialog j(Context context, View view, View.OnClickListener onClickListener) {
        IDialog dialog = DependInitializer.getDialog();
        if (dialog != null) {
            return dialog.createJdDialogWithStyleX(context, view, onClickListener);
        }
        return null;
    }

    public static void k(Dialog dialog, View.OnClickListener onClickListener) {
        IDialog dialog2 = DependInitializer.getDialog();
        if (dialog2 == null || dialog == null) {
            return;
        }
        dialog2.setOnLeftButtonClickListener(dialog, onClickListener);
    }

    public static void l(Dialog dialog, View.OnClickListener onClickListener) {
        IDialog dialog2 = DependInitializer.getDialog();
        if (dialog2 == null || dialog == null) {
            return;
        }
        dialog2.setOnRightButtonClickListener(dialog, onClickListener);
    }

    public static void m(Dialog dialog, CharSequence charSequence) {
        IDialog dialog2 = DependInitializer.getDialog();
        if (dialog == null || dialog2 == null || TextUtils.isEmpty(charSequence)) {
            return;
        }
        dialog2.setTitle(dialog, charSequence);
    }
}
