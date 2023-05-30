package com.jingdong.manto.ui.auth;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;

/* loaded from: classes16.dex */
public abstract class MantoAuthDialog extends Dialog implements View.OnClickListener {
    static final String TEMPLATE = "%s\u4e3a\u4e86\u7ed9\u60a8\u63d0\u4f9b\u5b8c\u6574\u670d\u52a1\uff0c\u60f3%s\uff0c\u662f\u5426\u5141\u8bb8\uff1f";

    /* loaded from: classes16.dex */
    public interface Callback {
        void onAccept();

        void onCancel();

        void onReject();
    }

    public MantoAuthDialog(@NonNull Context context) {
        super(context);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        try {
            super.cancel();
        } catch (Throwable unused) {
        }
    }

    @Override // android.view.View.OnClickListener
    public abstract void onClick(View view);

    @Override // android.app.Dialog
    public void show() {
        try {
            super.show();
        } catch (Throwable unused) {
        }
    }
}
