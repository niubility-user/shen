package com.jingdong.manto.ui;

import android.view.View;

/* loaded from: classes16.dex */
public class c extends b {
    public c(MantoBaseActivity mantoBaseActivity) {
        this.f14287f = mantoBaseActivity;
    }

    @Override // com.jingdong.manto.ui.b
    protected final int a() {
        return this.f14287f.getLayoutId();
    }

    @Override // com.jingdong.manto.ui.b
    protected final void a(View view) {
        this.f14287f.dealContentView(view);
    }

    @Override // com.jingdong.manto.ui.b
    protected final View b() {
        return MantoBaseActivity.getLayoutView();
    }

    @Override // com.jingdong.manto.ui.b
    protected final void d() {
        this.f14287f.onCreateBeforeSetContentView();
    }
}
