package com.vivo.push.b;

import com.tencent.connect.common.Constants;

/* loaded from: classes11.dex */
public final class i extends s {
    private String a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private String f18249c;

    public i(int i2) {
        super(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        aVar.a("app_id", this.a);
        aVar.a(Constants.PARAM_CLIENT_ID, this.b);
        aVar.a("client_token", this.f18249c);
    }

    public final String d() {
        return this.a;
    }

    public final String e() {
        return this.f18249c;
    }

    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final String toString() {
        return "OnBindCommand";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.o
    public final void d(com.vivo.push.a aVar) {
        super.d(aVar);
        this.a = aVar.a("app_id");
        this.b = aVar.a(Constants.PARAM_CLIENT_ID);
        this.f18249c = aVar.a("client_token");
    }
}
