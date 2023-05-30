package com.facebook.react.modules.datepicker;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.fragment.app.DialogFragment;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.model.datetime.JDDateTimePickerDialog;
import java.util.Calendar;
import java.util.Locale;
import javax.annotation.Nullable;

@SuppressLint({"ValidFragment"})
/* loaded from: classes12.dex */
public class DatePickerDialogFragment extends DialogFragment {
    private static final long DEFAULT_MIN_DATE = -2208988800001L;
    @Nullable
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    @Nullable
    private DialogInterface.OnDismissListener mOnDismissListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.facebook.react.modules.datepicker.DatePickerDialogFragment$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$modules$datepicker$DatePickerMode;

        static {
            int[] iArr = new int[DatePickerMode.values().length];
            $SwitchMap$com$facebook$react$modules$datepicker$DatePickerMode = iArr;
            try {
                iArr[DatePickerMode.CALENDAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$modules$datepicker$DatePickerMode[DatePickerMode.SPINNER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$modules$datepicker$DatePickerMode[DatePickerMode.DEFAULT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static Dialog createDialog(Bundle bundle, Context context, @Nullable DatePickerDialog.OnDateSetListener onDateSetListener) {
        DismissableDatePickerDialog dismissableDatePickerDialog;
        Calendar calendar = Calendar.getInstance();
        if (bundle != null && bundle.containsKey(JDDateTimePickerDialog.SELECT_DATE_MODE)) {
            calendar.setTimeInMillis(bundle.getLong(JDDateTimePickerDialog.SELECT_DATE_MODE));
        }
        int i2 = calendar.get(1);
        int i3 = calendar.get(2);
        int i4 = calendar.get(5);
        DatePickerMode datePickerMode = DatePickerMode.DEFAULT;
        DismissableDatePickerDialog dismissableDatePickerDialog2 = null;
        if (bundle != null && bundle.getString("mode", null) != null) {
            datePickerMode = DatePickerMode.valueOf(bundle.getString("mode").toUpperCase(Locale.US));
        }
        DatePickerMode datePickerMode2 = datePickerMode;
        if (Build.VERSION.SDK_INT >= 21) {
            int i5 = AnonymousClass1.$SwitchMap$com$facebook$react$modules$datepicker$DatePickerMode[datePickerMode2.ordinal()];
            if (i5 == 1) {
                dismissableDatePickerDialog = new DismissableDatePickerDialog(context, context.getResources().getIdentifier("CalendarDatePickerDialog", DeeplinkProductDetailHelper.LAYER_STYLE, context.getPackageName()), onDateSetListener, i2, i3, i4);
            } else if (i5 == 2) {
                dismissableDatePickerDialog = new DismissableDatePickerDialog(context, context.getResources().getIdentifier("SpinnerDatePickerDialog", DeeplinkProductDetailHelper.LAYER_STYLE, context.getPackageName()), onDateSetListener, i2, i3, i4);
            } else if (i5 == 3) {
                dismissableDatePickerDialog = new DismissableDatePickerDialog(context, onDateSetListener, i2, i3, i4);
            }
            dismissableDatePickerDialog2 = dismissableDatePickerDialog;
        } else {
            DismissableDatePickerDialog dismissableDatePickerDialog3 = new DismissableDatePickerDialog(context, onDateSetListener, i2, i3, i4);
            int i6 = AnonymousClass1.$SwitchMap$com$facebook$react$modules$datepicker$DatePickerMode[datePickerMode2.ordinal()];
            if (i6 == 1) {
                dismissableDatePickerDialog3.getDatePicker().setCalendarViewShown(true);
                dismissableDatePickerDialog3.getDatePicker().setSpinnersShown(false);
            } else if (i6 == 2) {
                dismissableDatePickerDialog3.getDatePicker().setCalendarViewShown(false);
            }
            dismissableDatePickerDialog2 = dismissableDatePickerDialog3;
        }
        DatePicker datePicker = dismissableDatePickerDialog2.getDatePicker();
        if (bundle != null && bundle.containsKey("minDate")) {
            calendar.setTimeInMillis(bundle.getLong("minDate"));
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            datePicker.setMinDate(calendar.getTimeInMillis());
        } else {
            datePicker.setMinDate(DEFAULT_MIN_DATE);
        }
        if (bundle != null && bundle.containsKey("maxDate")) {
            calendar.setTimeInMillis(bundle.getLong("maxDate"));
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            calendar.set(14, 999);
            datePicker.setMaxDate(calendar.getTimeInMillis());
        }
        return dismissableDatePickerDialog2;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return createDialog(getArguments(), getActivity(), this.mOnDateSetListener);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        DialogInterface.OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialogInterface);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnDateSetListener(@Nullable DatePickerDialog.OnDateSetListener onDateSetListener) {
        this.mOnDateSetListener = onDateSetListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnDismissListener(@Nullable DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }
}
