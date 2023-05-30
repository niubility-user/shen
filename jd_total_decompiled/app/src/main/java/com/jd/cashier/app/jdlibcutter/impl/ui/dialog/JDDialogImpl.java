package com.jd.cashier.app.jdlibcutter.impl.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog;
import com.jingdong.common.ui.JDBottomDialog;
import com.jingdong.common.ui.JDCheckDialog;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;

/* loaded from: classes13.dex */
public class JDDialogImpl implements IDialog {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog
    public void addContentWithHeight(Dialog dialog, View view, float f2) {
        if (view == null || !(dialog instanceof JDBottomDialog)) {
            return;
        }
        if (f2 > 0.0f) {
            ((JDBottomDialog) dialog).addContentWithHeight(view, "", f2);
        } else {
            ((JDBottomDialog) dialog).addContent(view, "");
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog
    public Dialog createBottomDialog(Context context) {
        JDBottomDialog jDBottomDialog = new JDBottomDialog(context);
        jDBottomDialog.setAutoDarkMode(true);
        Window window = jDBottomDialog.getWindow();
        if (window != null) {
            window.setSoftInputMode(48);
        }
        return jDBottomDialog;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog
    public Dialog createJdDialogWithStyle1(Context context, String str, String str2) {
        JDDialog createJdDialogWithStyle1 = JDDialogFactory.getInstance().createJdDialogWithStyle1(context, str, str2);
        if (createJdDialogWithStyle1 != null) {
            createJdDialogWithStyle1.setAutoDarkMode(true);
        }
        return createJdDialogWithStyle1;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog
    public Dialog createJdDialogWithStyle10(Context context, String str, String str2, View view, String str3, String str4) {
        JDDialog createJdDialogWithStyle10 = JDDialogFactory.getInstance().createJdDialogWithStyle10(context, str, str2, view, str3, str4);
        if (createJdDialogWithStyle10 != null) {
            createJdDialogWithStyle10.setAutoDarkMode(true);
        }
        return createJdDialogWithStyle10;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog
    public Dialog createJdDialogWithStyle2(Context context, String str, String str2, String str3) {
        JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(context, str, str2, str3);
        if (createJdDialogWithStyle2 != null) {
            createJdDialogWithStyle2.setAutoDarkMode(true);
        }
        return createJdDialogWithStyle2;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog
    public Dialog createJdDialogWithStyle5(Context context, String str, String str2, String str3) {
        JDDialog createJdDialogWithStyle5 = JDDialogFactory.getInstance().createJdDialogWithStyle5(context, str, str2, str3);
        if (createJdDialogWithStyle5 != null) {
            createJdDialogWithStyle5.setAutoDarkMode(true);
        }
        return createJdDialogWithStyle5;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog
    public Dialog createJdDialogWithStyle6(Context context, String str, String str2, String str3, String str4) {
        JDCheckDialog createJdDialogWithStyle6 = JDDialogFactory.getInstance().createJdDialogWithStyle6(context, str, str2, str3, str4);
        if (createJdDialogWithStyle6 != null) {
            createJdDialogWithStyle6.setAutoDarkMode(true);
        }
        return createJdDialogWithStyle6;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog
    public Dialog createJdDialogWithStyle9(Context context, String str, String str2, View view, String str3, String str4) {
        JDDialog createJdDialogWithStyle9 = JDDialogFactory.getInstance().createJdDialogWithStyle9(context, str, str2, view, str3, str4);
        if (createJdDialogWithStyle9 != null) {
            createJdDialogWithStyle9.setAutoDarkMode(true);
        }
        return createJdDialogWithStyle9;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog
    public Dialog createJdDialogWithStyle9WithGravity(Context context, String str, String str2, View view, int i2, String str3, String str4) {
        JDDialog createJdDialogWithStyle9 = JDDialogFactory.getInstance().createJdDialogWithStyle9(context, str, str2, view, i2, str3, str4);
        if (createJdDialogWithStyle9 != null) {
            createJdDialogWithStyle9.setAutoDarkMode(true);
        }
        return createJdDialogWithStyle9;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog
    public Dialog createJdDialogWithStyleX(Context context, View view, View.OnClickListener onClickListener) {
        JDDialog createJdDialogWithStyleX = JDDialogFactory.getInstance().createJdDialogWithStyleX(context, view, onClickListener);
        if (createJdDialogWithStyleX != null) {
            createJdDialogWithStyleX.setAutoDarkMode(true);
        }
        return createJdDialogWithStyleX;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog
    public void setOnLeftButtonClickListener(Dialog dialog, View.OnClickListener onClickListener) {
        if (dialog instanceof JDDialog) {
            ((JDDialog) dialog).setOnLeftButtonClickListener(onClickListener);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog
    public void setOnRightButtonClickListener(Dialog dialog, View.OnClickListener onClickListener) {
        if (dialog instanceof JDDialog) {
            ((JDDialog) dialog).setOnRightButtonClickListener(onClickListener);
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.dialog.IDialog
    public void setTitle(Dialog dialog, CharSequence charSequence) {
        if (!(dialog instanceof JDDialog) || TextUtils.isEmpty(charSequence)) {
            return;
        }
        try {
            ((JDDialog) dialog).titleView.setText(charSequence);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
