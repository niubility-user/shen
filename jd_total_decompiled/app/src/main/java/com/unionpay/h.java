package com.unionpay;

import org.json.JSONObject;

/* loaded from: classes11.dex */
final class h implements aa {
    final /* synthetic */ UPPayWapActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0034 A[Catch: Exception -> 0x0040, TRY_LEAVE, TryCatch #0 {Exception -> 0x0040, blocks: (B:3:0x0005, B:13:0x002d, B:15:0x0034, B:12:0x0022), top: B:21:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    @Override // com.unionpay.aa
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void a(String str, ab abVar) {
        String b;
        String str2;
        String b2;
        String b3;
        String str3 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            try {
                str2 = (String) jSONObject.get("resultCode");
                try {
                    str3 = (String) jSONObject.get("resultData");
                } catch (Exception e2) {
                    e = e2;
                    if (abVar != null) {
                        b2 = UPPayWapActivity.b("1", e.getMessage(), (String) null);
                        abVar.a(b2);
                    }
                    this.a.a(str2, str3);
                    if (abVar == null) {
                    }
                }
            } catch (Exception e3) {
                e = e3;
                str2 = "";
            }
            this.a.a(str2, str3);
            if (abVar == null) {
                b3 = UPPayWapActivity.b("0", "success", (String) null);
                abVar.a(b3);
            }
        } catch (Exception e4) {
            if (abVar != null) {
                b = UPPayWapActivity.b("1", e4.getMessage(), (String) null);
                abVar.a(b);
            }
        }
    }
}
