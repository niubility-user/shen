package com.jdpay.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.jdpay.lib.util.JDPayLog;
import com.jdpay.widget.R;

/* loaded from: classes18.dex */
public class ToastDialog extends BaseDialog implements Handler.Callback {
    private final Handler handler;
    private CharSequence text;

    private ToastDialog(@NonNull Context context, @NonNull CharSequence charSequence) {
        super(context, R.style.Theme_AppCompat_JDPay_Dialog_Toast);
        this.handler = new Handler(this);
        setCancelable(true);
        this.text = charSequence;
    }

    private void close() {
        try {
            if (isShowing()) {
                dismiss();
            }
        } catch (Throwable th) {
            JDPayLog.e(th);
        }
    }

    public static ToastDialog makeText(@NonNull Context context, @NonNull CharSequence charSequence) {
        return new ToastDialog(context, charSequence);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what == 0) {
            close();
            return true;
        }
        return false;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View inflate = View.inflate(getContext(), R.layout.jdpay_widget_dialog_toast, null);
        ((TextView) inflate.findViewById(R.id.text)).setText(this.text);
        setContentView(inflate);
    }

    @Override // com.jdpay.widget.dialog.BaseDialog, android.app.Dialog
    public void show() {
        super.show();
        this.handler.sendEmptyMessageDelayed(0, 4000L);
    }
}
