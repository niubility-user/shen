package com.jdpay.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.lib.helper.ContextHelper;
import com.jdpay.widget.dialog.DialogManager;

/* loaded from: classes18.dex */
public class BaseDialog extends Dialog {
    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        unregisterHost();
    }

    public void dismissImmediate() {
        super.dismiss();
    }

    public DialogManager getHostDialogManager() {
        Activity activity = ContextHelper.getActivity(getContext());
        if (activity instanceof DialogManager.Manageable) {
            return ((DialogManager.Manageable) activity).getDialogManager();
        }
        return null;
    }

    public void registerHost() {
        DialogManager hostDialogManager = getHostDialogManager();
        if (hostDialogManager != null) {
            hostDialogManager.add(this);
        }
    }

    @Override // android.app.Dialog
    public void show() {
        Activity activity = ContextHelper.getActivity(getContext());
        if (activity == null || activity.isFinishing() || isShowing()) {
            return;
        }
        registerHost();
        super.show();
    }

    public void unregisterHost() {
        DialogManager hostDialogManager = getHostDialogManager();
        if (hostDialogManager != null) {
            hostDialogManager.remove(this);
        }
    }

    public BaseDialog(@NonNull Context context, int i2) {
        super(context, i2);
    }

    public BaseDialog(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }
}
