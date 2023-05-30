package com.unionpay;

import android.view.View;

/* loaded from: classes11.dex */
final class n implements View.OnClickListener {
    final /* synthetic */ UPPayWapActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        UPPayWapActivity.a(this.a);
    }
}
