package com.unionpay;

import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes11.dex */
final class k implements aa {
    final /* synthetic */ UPPayWapActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.aa
    public final void a(String str, ab abVar) {
        String b;
        String b2;
        String b3;
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() <= 0) {
                if (abVar != null) {
                    b2 = UPPayWapActivity.b("1", "Parameter error", (String) null);
                    abVar.a(b2);
                    return;
                }
                return;
            }
            JSONObject jSONObject = new JSONObject();
            int i2 = 0;
            while (true) {
                String str2 = "0";
                if (i2 >= jSONArray.length()) {
                    break;
                }
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                String string = jSONObject2.getString("packageName");
                if (com.unionpay.utils.b.b(this.a, string, jSONObject2.getString("packageSign"), jSONObject2.getString("supportVersion"))) {
                    str2 = "1";
                }
                jSONObject.put(string, str2);
                i2++;
            }
            if (abVar != null) {
                b3 = UPPayWapActivity.b("0", "success", jSONObject);
                abVar.a(b3);
            }
        } catch (Exception e2) {
            if (abVar != null) {
                b = UPPayWapActivity.b("1", e2.getMessage(), (String) null);
                abVar.a(b);
            }
        }
    }
}
