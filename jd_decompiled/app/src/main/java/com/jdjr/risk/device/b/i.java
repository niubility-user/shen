package com.jdjr.risk.device.b;

import android.content.Context;
import android.os.Build;
import com.jdcn.risk.cpp.LoadDoor;
import com.jdjr.risk.device.c.ah;
import com.jdjr.risk.device.c.r;
import com.jdjr.risk.device.c.v;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class i extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public i() {
        this.a = new com.jdjr.risk.device.entity.h();
    }

    @Override // com.jdjr.risk.device.b.a
    public String a() {
        return "device_other_info";
    }

    @Override // com.jdjr.risk.device.b.a
    protected void b(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            com.jdjr.risk.device.entity.h hVar = (com.jdjr.risk.device.entity.h) this.a;
            if (jSONObject.optInt("ext") == 1) {
                hVar.b("");
            }
            if (jSONObject.optInt("drawHs") == 1) {
                hVar.d(r.b(context));
            }
            if (jSONObject.optInt("canvasHs") == 1) {
                hVar.e(r.c(context));
            }
            if (jSONObject.optInt("buildIct") == 1) {
                hVar.f(Build.VERSION.INCREMENTAL);
            }
            if (jSONObject.optInt("buildCn") == 1) {
                hVar.g(Build.VERSION.CODENAME);
            }
            if (jSONObject.optInt("laupckN") == 1) {
                hVar.h(r.g(context));
            }
            if (jSONObject.optInt("mediaDpp") == 1) {
                hVar.i(r.h(context));
            }
            if (jSONObject.optInt("screenMir") == 1) {
                hVar.j(r.i(context) + "");
            }
            if (jSONObject.optInt("screenDis") == 1) {
                hVar.k(r.j(context) + "");
            }
            if (jSONObject.optInt("audioM") == 1) {
                hVar.l(r.k(context) + "");
            }
            if (jSONObject.optInt("calls") == 1) {
                hVar.m(r.l(context));
            }
            if (jSONObject.optInt("hOS") == 1) {
                hVar.n(r.b() + "");
            }
            if (jSONObject.optInt("jscvh") == 1) {
                hVar.c(r.a());
            }
            if (jSONObject.optInt("gsfId") == 1) {
                hVar.o(r.n(context));
            }
            if (jSONObject.optInt("accessE") == 1) {
                hVar.p(r.o(context) + "");
            }
            if (jSONObject.optInt("accessS") == 1) {
                hVar.q(r.p(context));
            }
            if (jSONObject.optInt("screenTot") == 1) {
                hVar.r(r.r(context));
            }
            if (jSONObject.optInt("romN") == 1) {
                hVar.u(r.u(context));
            }
            if (jSONObject.optInt("simS") == 1) {
                hVar.t(r.t(context) + "");
            }
            if (jSONObject.optInt("deviceN") == 1) {
                hVar.s(r.s(context));
            }
            if (jSONObject.optInt("dnStat") == 1) {
                hVar.B(r.d());
            }
            if (jSONObject.optInt("accessD") == 1) {
                hVar.E(com.jdjr.risk.device.c.d.c(context).toString());
            }
            if (jSONObject.optInt("appType") == 1) {
                hVar.G(r.v(context));
            }
            if (jSONObject.optInt("l1") == 1) {
                hVar.H(r.c());
            }
            if (jSONObject.optInt("bll") == 1) {
                hVar.I(r.e());
            }
            r.a(context);
            if (jSONObject.optInt("modelJni") == 1) {
                hVar.v(LoadDoor.e().g(context));
            }
            if (jSONObject.optInt("fingerJni") == 1) {
                hVar.w(LoadDoor.e().d(context));
            }
            if (jSONObject.optInt("insId") == 1) {
                hVar.y(v.a(context));
            }
            if (jSONObject.optInt("uuid") == 1) {
                hVar.z(ah.b(context));
            }
            if (jSONObject.optInt("uid") == 1) {
                hVar.A(ah.c(context));
            }
            if (jSONObject.optInt("devUID") == 1) {
                hVar.x(ah.a(context));
            }
            if (jSONObject.optInt("dtS") == 1) {
                hVar.a(new com.jdjr.risk.device.c.n().a(context));
            }
            if (jSONObject.optInt("dEnv") == 1) {
                hVar.C(ah.d(context));
            }
            if (jSONObject.optInt("isDas") == 1) {
                hVar.D(String.valueOf(com.jdjr.risk.device.c.d.b(context)));
            }
            if (jSONObject.optInt("isFore") == 1) {
                hVar.F(com.jdjr.risk.device.c.c.b(context));
            }
        }
    }
}
