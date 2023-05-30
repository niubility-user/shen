package com.unionpay;

/* loaded from: classes11.dex */
final class q implements aa {
    final /* synthetic */ UPPayWapActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.aa
    public final void a(String str, ab abVar) {
        String b;
        String a = UPPayAssistEx.a(this.a);
        if (abVar != null) {
            b = UPPayWapActivity.b("0", "success", a);
            abVar.a(b);
        }
    }
}
