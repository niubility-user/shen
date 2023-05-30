package com.unionpay;

import android.content.Intent;
import android.os.Bundle;
import org.json.JSONObject;

/* loaded from: classes11.dex */
final class j implements aa {
    final /* synthetic */ UPPayWapActivity a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(UPPayWapActivity uPPayWapActivity) {
        this.a = uPPayWapActivity;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x006b A[Catch: Exception -> 0x0077, TRY_LEAVE, TryCatch #2 {Exception -> 0x0077, blocks: (B:3:0x0005, B:13:0x002d, B:15:0x006b, B:12:0x0022), top: B:25:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    @Override // com.unionpay.aa
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void a(String str, ab abVar) {
        String b;
        String str2;
        String b2;
        String str3;
        String b3;
        String str4 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            try {
                str2 = (String) jSONObject.get("url");
                try {
                    str4 = (String) jSONObject.get("title");
                } catch (Exception e2) {
                    e = e2;
                    if (abVar != null) {
                        b2 = UPPayWapActivity.b("1", e.getMessage(), (String) null);
                        abVar.a(b2);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("waptype", "new_page");
                    bundle.putString("magic_data", "949A1CC");
                    bundle.putString("wapurl", str2);
                    bundle.putString("waptitle", str4);
                    str3 = this.a.f18139g;
                    bundle.putString("actionType", str3);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    intent.setClass(this.a, UPPayWapActivity.class);
                    this.a.startActivity(intent);
                    if (abVar == null) {
                    }
                }
            } catch (Exception e3) {
                e = e3;
                str2 = "";
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString("waptype", "new_page");
            bundle2.putString("magic_data", "949A1CC");
            bundle2.putString("wapurl", str2);
            bundle2.putString("waptitle", str4);
            str3 = this.a.f18139g;
            bundle2.putString("actionType", str3);
            Intent intent2 = new Intent();
            intent2.putExtras(bundle2);
            intent2.setClass(this.a, UPPayWapActivity.class);
            this.a.startActivity(intent2);
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
