package com.jingdong.manto.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import com.jingdong.manto.sdk.api.IMantoDialog;

/* loaded from: classes16.dex */
public class a {
    public static Dialog a(Activity activity, String str, String str2, String str3, String str4, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2, DialogInterface.OnCancelListener onCancelListener, DialogInterface.OnDismissListener onDismissListener, DialogInterface.OnKeyListener onKeyListener) {
        IMantoDialog iMantoDialog = (IMantoDialog) com.jingdong.a.n(IMantoDialog.class);
        if (iMantoDialog != null) {
            return iMantoDialog.getMantoDialog(activity, str, str2, str3, str4, onClickListener, onClickListener2, onCancelListener, onDismissListener, onKeyListener);
        }
        MantoDialog mantoDialog = new MantoDialog(activity);
        mantoDialog.setTitle(str);
        mantoDialog.setMessage(str2);
        if (str4 != null) {
            mantoDialog.setNegativeBtn(str4, onClickListener2);
        }
        if (str3 != null) {
            mantoDialog.setPositiveBtn(str3, onClickListener);
        }
        if (onCancelListener != null) {
            mantoDialog.setOnCancelListener(onCancelListener);
        }
        if (onDismissListener != null) {
            mantoDialog.setOnDismissListener(onDismissListener);
        }
        if (onKeyListener != null) {
            mantoDialog.setOnKeyListener(onKeyListener);
        }
        return mantoDialog;
    }
}
