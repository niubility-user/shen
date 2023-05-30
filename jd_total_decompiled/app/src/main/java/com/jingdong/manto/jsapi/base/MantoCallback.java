package com.jingdong.manto.jsapi.base;

import com.jingdong.manto.m.e0;

/* loaded from: classes15.dex */
public class MantoCallback {
    int callBackIndex;
    e0 mantoComponent;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MantoCallback(e0 e0Var, int i2) {
        this.mantoComponent = e0Var;
        this.callBackIndex = i2;
    }

    public final void invokeCallback(String str) {
        this.mantoComponent.a(this.callBackIndex, str);
    }
}
