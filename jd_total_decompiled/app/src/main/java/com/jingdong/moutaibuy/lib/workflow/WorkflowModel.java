package com.jingdong.moutaibuy.lib.workflow;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/* loaded from: classes16.dex */
public class WorkflowModel extends ViewModel {
    private final MutableLiveData<c> a;

    public WorkflowModel() {
        c.a();
        this.a = new MutableLiveData<>(new c());
    }

    public MutableLiveData<c> a() {
        return this.a;
    }

    public void b(int i2) {
        c value = this.a.getValue();
        value.b(i2);
        this.a.setValue(value);
    }
}
