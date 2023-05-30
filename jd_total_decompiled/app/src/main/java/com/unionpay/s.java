package com.unionpay;

import com.unionpay.utils.UPUtils;

/* loaded from: classes11.dex */
final class s implements aa {
    final /* synthetic */ UPPayWapActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.aa
    public final void a(String str, ab abVar) {
        String b;
        String a = UPUtils.a(this.a, str);
        if (abVar != null) {
            b = UPPayWapActivity.b("0", "success", a);
            abVar.a(b);
        }
    }
}
