package com.vivo.push.b;

import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes11.dex */
public final class w extends com.vivo.push.o {
    private int a;

    public w() {
        super(R2.attr.textAppearanceLineHeightEnabled);
        this.a = 0;
    }

    @Override // com.vivo.push.o
    protected final void c(com.vivo.push.a aVar) {
        aVar.a("com.bbk.push.ikey.MODE_TYPE", this.a);
    }

    @Override // com.vivo.push.o
    public final boolean c() {
        return true;
    }

    public final int d() {
        return this.a;
    }

    @Override // com.vivo.push.o
    public final String toString() {
        return "PushModeCommand";
    }

    @Override // com.vivo.push.o
    protected final void d(com.vivo.push.a aVar) {
        this.a = aVar.b("com.bbk.push.ikey.MODE_TYPE", 0);
    }
}