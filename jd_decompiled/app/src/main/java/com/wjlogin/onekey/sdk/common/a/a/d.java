package com.wjlogin.onekey.sdk.common.a.a;

import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class d implements Runnable {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ g b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(g gVar, JSONObject jSONObject) {
        this.b = gVar;
        this.a = jSONObject;
    }

    @Override // java.lang.Runnable
    public void run() {
        b bVar;
        this.b.f18334k = true;
        bVar = this.b.f18331h;
        bVar.a(this.a);
    }
}
