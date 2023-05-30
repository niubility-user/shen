package com.jingdong.jdsdk.widget.a;

import android.app.Application;

/* loaded from: classes14.dex */
public final class e extends a {
    private final i b;

    public e(Application application) {
        super(application);
        this.b = new i(this, application);
    }

    @Override // android.widget.Toast
    public void cancel() {
        this.b.a();
    }

    @Override // android.widget.Toast
    public void show() {
        this.b.b();
    }
}
