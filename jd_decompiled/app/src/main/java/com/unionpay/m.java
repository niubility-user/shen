package com.unionpay;

import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class m implements View.OnClickListener {
    final /* synthetic */ UPPayWapActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        UPPayWapActivity.a(this.a);
    }
}
