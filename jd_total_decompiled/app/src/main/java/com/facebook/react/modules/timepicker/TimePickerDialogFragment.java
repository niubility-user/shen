package com.facebook.react.modules.timepicker;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import androidx.fragment.app.DialogFragment;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import java.util.Calendar;
import java.util.Locale;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class TimePickerDialogFragment extends DialogFragment {
    @Nullable
    private DialogInterface.OnDismissListener mOnDismissListener;
    @Nullable
    private TimePickerDialog.OnTimeSetListener mOnTimeSetListener;

    static Dialog createDialog(Bundle bundle, Context context, @Nullable TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        Calendar calendar = Calendar.getInstance();
        int i2 = calendar.get(11);
        int i3 = calendar.get(12);
        boolean is24HourFormat = DateFormat.is24HourFormat(context);
        TimePickerMode timePickerMode = TimePickerMode.DEFAULT;
        if (bundle != null && bundle.getString("mode", null) != null) {
            timePickerMode = TimePickerMode.valueOf(bundle.getString("mode").toUpperCase(Locale.US));
        }
        if (bundle != null) {
            i2 = bundle.getInt("hour", calendar.get(11));
            i3 = bundle.getInt("minute", calendar.get(12));
            is24HourFormat = bundle.getBoolean("is24Hour", DateFormat.is24HourFormat(context));
        }
        if (Build.VERSION.SDK_INT >= 21) {
            if (timePickerMode == TimePickerMode.CLOCK) {
                return new DismissableTimePickerDialog(context, context.getResources().getIdentifier("ClockTimePickerDialog", DeeplinkProductDetailHelper.LAYER_STYLE, context.getPackageName()), onTimeSetListener, i2, i3, is24HourFormat);
            }
            if (timePickerMode == TimePickerMode.SPINNER) {
                return new DismissableTimePickerDialog(context, context.getResources().getIdentifier("SpinnerTimePickerDialog", DeeplinkProductDetailHelper.LAYER_STYLE, context.getPackageName()), onTimeSetListener, i2, i3, is24HourFormat);
            }
        }
        return new DismissableTimePickerDialog(context, onTimeSetListener, i2, i3, is24HourFormat);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return createDialog(getArguments(), getActivity(), this.mOnTimeSetListener);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogInterface.OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialogInterface);
        }
    }

    public void setOnDismissListener(@Nullable DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void setOnTimeSetListener(@Nullable TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        this.mOnTimeSetListener = onTimeSetListener;
    }
}
