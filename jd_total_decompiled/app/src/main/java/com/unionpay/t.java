package com.unionpay;

import com.unionpay.utils.UPUtils;

/* loaded from: classes11.dex */
final class t implements aa {
    final /* synthetic */ UPPayWapActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.aa
    public final void a(String str, ab abVar) {
        String b;
        UPUtils.b(this.a, str);
        if (abVar != null) {
            b = UPPayWapActivity.b("0", "success", (String) null);
            abVar.a(b);
        }
    }
}
