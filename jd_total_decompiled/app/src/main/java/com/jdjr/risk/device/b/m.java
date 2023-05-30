package com.jdjr.risk.device.b;

import android.content.Context;
import android.content.Intent;
import com.jdjr.risk.device.c.ab;
import com.jdjr.risk.device.c.ai;
import com.jdjr.risk.device.c.r;
import com.jdjr.risk.device.c.s;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class m extends a {

    /* renamed from: c  reason: collision with root package name */
    private Intent f7328c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m() {
        this.a = new com.jdjr.risk.device.entity.l();
    }

    private void a(Context context) {
        if (this.f7328c == null) {
            this.f7328c = com.jdjr.risk.device.c.f.a(context);
        }
    }

    @Override // com.jdjr.risk.device.b.a
    public String a() {
        return "device_status_info";
    }

    @Override // com.jdjr.risk.device.b.a
    protected void b(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            com.jdjr.risk.device.entity.l lVar = (com.jdjr.risk.device.entity.l) this.a;
            if (jSONObject.optInt("wifiable") == 1) {
                lVar.a(ab.a(context));
            }
            if (jSONObject.optInt("debug") == 1) {
                lVar.b(String.valueOf(com.jdjr.risk.device.c.m.b(context)));
            }
            if (jSONObject.optInt("adb") == 1) {
                lVar.c(String.valueOf(com.jdjr.risk.device.c.m.a(context)));
            }
            if (jSONObject.optInt("charge") == 1) {
                a(context);
                Intent intent = this.f7328c;
                if (intent != null) {
                    lVar.d(com.jdjr.risk.device.c.f.a(intent));
                }
            }
            if (jSONObject.optInt("btLvl") == 1) {
                a(context);
                Intent intent2 = this.f7328c;
                if (intent2 != null) {
                    lVar.e(com.jdjr.risk.device.c.f.b(intent2));
                }
            }
            if (jSONObject.optInt("hdpAtt") == 1) {
                lVar.f(String.valueOf(ai.b()));
                lVar.a(ai.b() ? 1 : 0);
            }
            if (jSONObject.optInt("touch") == 1) {
                lVar.g(ai.c());
            }
            if (jSONObject.optInt("nfc") == 1) {
                lVar.h(r.m(context) + "");
            }
            if (jSONObject.optInt("btLvl_c") == 1) {
                lVar.f(s.d(context).a());
            }
            if (jSONObject.optInt("btSt") == 1) {
                lVar.c(s.d(context).b());
            }
            if (jSONObject.optInt("btHel") == 1) {
                lVar.b(s.d(context).c());
            }
            if (jSONObject.optInt("btVla") == 1) {
                lVar.d(s.d(context).d());
            }
            if (jSONObject.optInt("muT") == 1) {
                lVar.a(s.b(context));
            }
            if (jSONObject.optInt("nfcE") == 1) {
                lVar.e(s.c(context) ? 1 : 0);
            }
        }
    }
}
