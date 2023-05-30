package com.unionpay;

import android.view.View;

/* loaded from: classes11.dex */
final class f implements View.OnClickListener {
    final /* synthetic */ UPPayWapActivity a;

    public f(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.a.finish();
    }
}
