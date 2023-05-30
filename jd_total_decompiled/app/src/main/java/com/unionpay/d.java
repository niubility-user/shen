package com.unionpay;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.unionpay.utils.UPUtils;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class d implements Runnable {
    final /* synthetic */ com.unionpay.a.d a;
    final /* synthetic */ Context b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f18161c;
    final /* synthetic */ String d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(com.unionpay.a.d dVar, Context context, String str, String str2) {
        this.a = dVar;
        this.b = context;
        this.f18161c = str;
        this.d = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context q;
        try {
            com.unionpay.a.d dVar = this.a;
            q = UPPayAssistEx.q();
            com.unionpay.a.c cVar = new com.unionpay.a.c(dVar, com.unionpay.utils.b.a(q));
            if (cVar.a() == 0) {
                String b = cVar.b();
                if (this.b == null || TextUtils.isEmpty(b)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject(b);
                String a = com.unionpay.utils.i.a(jSONObject, "sign");
                String a2 = com.unionpay.utils.i.a(jSONObject, "configs");
                if (TextUtils.isEmpty(a) || TextUtils.isEmpty(a2)) {
                    return;
                }
                int i2 = 0;
                try {
                    i2 = Integer.parseInt(this.f18161c);
                } catch (Exception unused) {
                }
                String b2 = com.unionpay.utils.b.b(UPUtils.a(new String(Base64.decode(a2, 2)) + this.d));
                String forConfig = UPUtils.forConfig(i2, a);
                if (TextUtils.isEmpty(forConfig) || !forConfig.equals(b2)) {
                    return;
                }
                UPUtils.a(this.b, b, "scan_configs");
                UPUtils.a(this.b, this.f18161c, "scan_mode");
                UPUtils.a(this.b, this.d, "scan_random");
            }
        } catch (Exception unused2) {
        }
    }
}
