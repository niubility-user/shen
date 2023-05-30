package com.unionpay;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: classes11.dex */
final class l implements aa {
    final /* synthetic */ UPPayWapActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.aa
    public final void a(String str, ab abVar) {
        String b;
        String b2;
        String b3;
        String b4;
        String str2;
        String str3;
        try {
            this.a.f18141i = abVar;
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("packageName");
            String string2 = jSONObject.getString("type");
            String optString = jSONObject.optString("openParams");
            String optString2 = jSONObject.optString("tn");
            if ("0".equals(string2)) {
                if (TextUtils.isEmpty(string) || TextUtils.isEmpty(optString2)) {
                    if (abVar != null) {
                        b4 = UPPayWapActivity.b("1", "Parameter error", (String) null);
                        abVar.a(b4);
                        return;
                    }
                    return;
                }
                Intent intent = new Intent();
                intent.setClassName(string, "com.unionpay.uppay.PayActivity");
                intent.putExtra("paydata", optString2);
                str2 = UPPayWapActivity.a;
                str3 = this.a.f18138f;
                intent.putExtra(str2, str3);
                this.a.startActivityForResult(intent, 1);
            } else if (!"2".equals(string2)) {
                if (abVar != null) {
                    b2 = UPPayWapActivity.b("1", "Parameter error", (String) null);
                    abVar.a(b2);
                }
            } else if (TextUtils.isEmpty(optString)) {
                if (abVar != null) {
                    b3 = UPPayWapActivity.b("1", "Parameter error", (String) null);
                    abVar.a(b3);
                }
            } else {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse(optString));
                if (!TextUtils.isEmpty(string)) {
                    intent2.setPackage(string);
                }
                this.a.startActivityForResult(intent2, 1);
            }
        } catch (Exception e2) {
            if (abVar != null) {
                b = UPPayWapActivity.b("1", e2.getMessage(), (String) null);
                abVar.a(b);
            }
        }
    }
}
