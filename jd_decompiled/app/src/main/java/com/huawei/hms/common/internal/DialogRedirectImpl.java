package com.huawei.hms.common.internal;

import android.app.Activity;
import android.content.Intent;

/* loaded from: classes12.dex */
public class DialogRedirectImpl extends DialogRedirect {
    private final Activity a;
    private final int b;

    /* renamed from: c */
    private final Intent f1271c;

    public DialogRedirectImpl(Intent intent, Activity activity, int i2) {
        this.f1271c = intent;
        this.a = activity;
        this.b = i2;
    }

    @Override // com.huawei.hms.common.internal.DialogRedirect
    public final void redirect() {
        Intent intent = this.f1271c;
        if (intent != null) {
            this.a.startActivityForResult(intent, this.b);
        }
    }
}
