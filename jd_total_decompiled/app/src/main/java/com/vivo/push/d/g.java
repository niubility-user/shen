package com.vivo.push.d;

/* loaded from: classes11.dex */
final class g extends z {
    /* JADX INFO: Access modifiers changed from: package-private */
    public g(com.vivo.push.o oVar) {
        super(oVar);
    }

    @Override // com.vivo.push.l
    protected final void a(com.vivo.push.o oVar) {
        com.vivo.push.util.p.d("OnClearCacheTask", "delete push info " + this.a.getPackageName());
        com.vivo.push.util.y.b(this.a).a();
    }
}
