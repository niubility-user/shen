package com.jd.cashier.app.jdlibcutter.protocol.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

/* loaded from: classes13.dex */
public interface IDialog {
    void addContentWithHeight(Dialog dialog, View view, float f2);

    Dialog createBottomDialog(Context context);

    Dialog createJdDialogWithStyle1(Context context, String str, String str2);

    Dialog createJdDialogWithStyle10(Context context, String str, String str2, View view, String str3, String str4);

    Dialog createJdDialogWithStyle2(Context context, String str, String str2, String str3);

    Dialog createJdDialogWithStyle5(Context context, String str, String str2, String str3);

    Dialog createJdDialogWithStyle6(Context context, String str, String str2, String str3, String str4);

    Dialog createJdDialogWithStyle9(Context context, String str, String str2, View view, String str3, String str4);

    Dialog createJdDialogWithStyle9WithGravity(Context context, String str, String str2, View view, int i2, String str3, String str4);

    Dialog createJdDialogWithStyleX(Context context, View view, View.OnClickListener onClickListener);

    void setOnLeftButtonClickListener(Dialog dialog, View.OnClickListener onClickListener);

    void setOnRightButtonClickListener(Dialog dialog, View.OnClickListener onClickListener);

    void setTitle(Dialog dialog, CharSequence charSequence);
}
