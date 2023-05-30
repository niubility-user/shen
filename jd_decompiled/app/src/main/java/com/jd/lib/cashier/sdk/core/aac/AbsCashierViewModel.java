package com.jd.lib.cashier.sdk.core.aac;

import androidx.lifecycle.ViewModel;
import com.jd.lib.cashier.sdk.core.aac.b;

/* loaded from: classes14.dex */
public abstract class AbsCashierViewModel<State extends b> extends ViewModel {
    private State a = a();

    public abstract State a();

    public State b() {
        if (this.a == null) {
            this.a = a();
        }
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        State state = this.a;
        if (state != null) {
            state.b();
            this.a = null;
        }
    }
}
