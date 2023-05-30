package com.jd.verify.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;

/* loaded from: classes18.dex */
public class d extends Dialog {
    private Context a;

    public d(Context context, int i2) {
        super(context, i2);
        this.a = null;
        this.a = context;
    }

    private boolean a() {
        try {
            Context context = this.a;
            if (context instanceof Activity) {
                return Build.VERSION.SDK_INT >= 17 ? (context == null || ((Activity) context).isFinishing() || ((Activity) this.a).isDestroyed()) ? false : true : (context == null || ((Activity) context).isFinishing()) ? false : true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        if (a() && isShowing()) {
            super.cancel();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (a() && isShowing()) {
            super.dismiss();
        }
    }

    @Override // android.app.Dialog
    public void hide() {
        if (a() && isShowing()) {
            super.hide();
        }
    }

    @Override // android.app.Dialog
    public void show() {
        if (a()) {
            super.show();
        }
    }
}
