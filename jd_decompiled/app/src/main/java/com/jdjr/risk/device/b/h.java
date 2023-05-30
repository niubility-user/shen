package com.jdjr.risk.device.b;

import android.content.Context;
import android.telephony.CellLocation;
import com.jdjr.risk.device.c.ab;
import com.jdjr.risk.device.c.ag;
import com.jdjr.risk.device.c.al;
import com.jdjr.risk.device.c.u;
import com.jdjr.risk.device.c.y;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class h extends a {
    private static String d = "-1";

    /* renamed from: e  reason: collision with root package name */
    private static String f7326e = "-1";

    /* renamed from: c  reason: collision with root package name */
    private CellLocation f7327c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h() {
        this.a = new com.jdjr.risk.device.entity.g();
    }

    private void a(Context context) {
        try {
            if (this.f7327c == null) {
                this.f7327c = ag.a(context);
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.jdjr.risk.device.b.a
    public String a() {
        return "device_network_info";
    }

    @Override // com.jdjr.risk.device.b.a
    protected void b(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            com.jdjr.risk.device.entity.g gVar = (com.jdjr.risk.device.entity.g) this.a;
            if (jSONObject.optInt("netTp") == 1) {
                gVar.a(ab.a());
            }
            if (jSONObject.optInt("crName") == 1) {
                gVar.b(ab.b());
            }
            if (jSONObject.optInt("crName2") == 1) {
                gVar.r("");
            }
            if (jSONObject.optInt("localIp") == 1) {
                gVar.c(u.a(context));
            }
            if (jSONObject.optInt("macA") == 1) {
                gVar.d(y.a(context));
            }
            if (jSONObject.optInt("bssid") == 1) {
                gVar.e(al.a(context));
            }
            if (jSONObject.optInt("ssid") == 1) {
                gVar.f(al.b(context));
            }
            if (jSONObject.optInt("rssi") == 1) {
                gVar.g(String.valueOf(al.c(context)));
            }
            if (jSONObject.optInt("gateWay") == 1) {
                gVar.h(al.a());
            }
            if (jSONObject.optInt("nm") == 1) {
                gVar.i(al.b());
            }
            if (jSONObject.optInt("cS") == 1) {
                a(context);
                CellLocation cellLocation = this.f7327c;
                gVar.j(cellLocation != null ? ag.a(cellLocation) : "");
            }
            if (jSONObject.optInt("cmnc") == 1) {
                gVar.k(ag.a(this.f7327c, context));
            }
            if (jSONObject.optInt("cmnc2") == 1) {
                gVar.s("");
            }
            if (jSONObject.optInt("cicc") == 1) {
                gVar.l(ag.a());
            }
            if (jSONObject.optInt("cicc2") == 1) {
                gVar.t("");
            }
            if (jSONObject.optInt("cmcc") == 1) {
                gVar.m(ag.a(context, 0, 3));
            }
            if (jSONObject.optInt("clac") == 1) {
                a(context);
                CellLocation cellLocation2 = this.f7327c;
                gVar.n(cellLocation2 != null ? String.valueOf(ag.b(cellLocation2)) : "");
            }
            if (jSONObject.optInt("ccId") == 1) {
                a(context);
                CellLocation cellLocation3 = this.f7327c;
                gVar.o(cellLocation3 != null ? String.valueOf(ag.c(cellLocation3)) : "");
            }
            if (jSONObject.optInt("wfLst") == 1) {
                gVar.p(al.d(context));
            }
            if (jSONObject.optInt("lnkSpd") == 1) {
                gVar.q(String.valueOf(al.e(context)));
            }
            if (jSONObject.optInt("netTp_c") == 1) {
                gVar.u(al.c());
            }
            if (jSONObject.optInt("opN") == 1) {
                gVar.v(al.f(context));
            }
        }
    }
}
