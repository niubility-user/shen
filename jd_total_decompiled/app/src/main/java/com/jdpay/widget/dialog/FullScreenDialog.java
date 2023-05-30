package com.jdpay.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes18.dex */
public abstract class FullScreenDialog extends Dialog {
    public FullScreenDialog(@NonNull Context context) {
        super(context);
    }

    public FullScreenDialog(@NonNull Context context, int i2) {
        super(context, i2);
    }

    protected FullScreenDialog(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    protected abstract int getLayoutId();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(getLayoutId());
        getWindow().setLayout(-1, -1);
        getWindow().setGravity(17);
        setCancelable(false);
        setCanceledOnTouchOutside(true);
    }
}
