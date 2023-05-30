package com.unionpay;

import com.unionpay.utils.UPUtils;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes11.dex */
final class r implements aa {
    final /* synthetic */ UPPayWapActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    @Override // com.unionpay.aa
    public final void a(String str, ab abVar) {
        String b;
        String b2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                UPUtils.a(this.a, jSONObject.getString(next), next);
            }
            if (abVar != null) {
                b2 = UPPayWapActivity.b("0", "success", (String) null);
                abVar.a(b2);
            }
        } catch (Exception e2) {
            if (abVar != null) {
                b = UPPayWapActivity.b("1", e2.getMessage(), (String) null);
                abVar.a(b);
            }
        }
    }
}
