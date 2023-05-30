package de.greenrobot.event.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

/* loaded from: classes11.dex */
public class ErrorDialogFragments {
    public static int ERROR_DIALOG_ICON;
    public static Class<?> EVENT_TYPE_ON_CLICK;

    @TargetApi(11)
    /* loaded from: classes11.dex */
    public static class Honeycomb extends DialogFragment implements DialogInterface.OnClickListener {
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            ErrorDialogFragments.handleOnClick(dialogInterface, i2, getActivity(), getArguments());
        }

        @Override // android.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            return ErrorDialogFragments.createDialog(getActivity(), getArguments(), this);
        }
    }

    /* loaded from: classes11.dex */
    public static class Support extends androidx.fragment.app.DialogFragment implements DialogInterface.OnClickListener {
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i2) {
            ErrorDialogFragments.handleOnClick(dialogInterface, i2, getActivity(), getArguments());
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            return ErrorDialogFragments.createDialog(getActivity(), getArguments(), this);
        }
    }

    public static Dialog createDialog(Context context, Bundle bundle, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(bundle.getString(ErrorDialogManager.KEY_TITLE));
        builder.setMessage(bundle.getString(ErrorDialogManager.KEY_MESSAGE));
        int i2 = ERROR_DIALOG_ICON;
        if (i2 != 0) {
            builder.setIcon(i2);
        }
        builder.setPositiveButton(17039370, onClickListener);
        return builder.create();
    }

    public static void handleOnClick(DialogInterface dialogInterface, int i2, Activity activity, Bundle bundle) {
        Class<?> cls = EVENT_TYPE_ON_CLICK;
        if (cls != null) {
            try {
                ErrorDialogManager.factory.config.getEventBus().post(cls.newInstance());
            } catch (Exception e2) {
                throw new RuntimeException("Event cannot be constructed", e2);
            }
        }
        if (!bundle.getBoolean(ErrorDialogManager.KEY_FINISH_AFTER_DIALOG, false) || activity == null) {
            return;
        }
        activity.finish();
    }
}
