package com.unionpay;

/* loaded from: classes11.dex */
final class u implements aa {
    final /* synthetic */ UPPayWapActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public u(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.aa
    public final void a(String str, ab abVar) {
        String b;
        UPPayWapActivity.a(this.a, Boolean.parseBoolean(str));
        if (abVar != null) {
            b = UPPayWapActivity.b("0", "success", (String) null);
            abVar.a(b);
        }
    }
}
