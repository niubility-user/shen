package com.unionpay;

import android.app.AlertDialog;
import android.content.DialogInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class p implements DialogInterface.OnClickListener {
    final /* synthetic */ UPPayWapActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i2) {
        AlertDialog alertDialog;
        alertDialog = this.a.d;
        alertDialog.dismiss();
    }
}
