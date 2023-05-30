package com.jd.lib.cashier.sdk.core.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity;
import com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel;
import com.jd.lib.cashier.sdk.core.aac.a;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.core.utils.r;

/* loaded from: classes14.dex */
public abstract class AbsCashierActivity<VM extends AbsCashierViewModel, N extends a> extends CashierCompactActivity {

    /* renamed from: g  reason: collision with root package name */
    private VM f2938g;

    /* renamed from: h  reason: collision with root package name */
    private N f2939h;

    private void initDependency() {
        if (this.f2938g == null) {
            this.f2938g = v();
        }
        if (this.f2939h == null) {
            this.f2939h = u();
        }
    }

    public abstract int createLayout();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        initDependency();
        if (createLayout() == 0) {
            r.b("AbsCashierActivity", "createLayout() returned 0, If you don't want to use createLayout(), but implement your own view,you have to override setContentView();");
        } else {
            super.setContentView(createLayout());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.cashier.app.jdlibcutter.protocol.activity.CashierCompactActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.f2939h != null) {
            this.f2939h = null;
        }
        if (this.f2938g != null) {
            this.f2938g = null;
        }
        o0.c();
    }

    public abstract N u();

    public abstract VM v();

    public N w() {
        if (this.f2939h == null) {
            this.f2939h = u();
        }
        return this.f2939h;
    }

    public VM x() {
        if (this.f2938g == null) {
            this.f2938g = v();
        }
        return this.f2938g;
    }
}
