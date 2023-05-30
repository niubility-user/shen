package com.jingdong.manto.widget.input;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.z0.g;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class d extends j {
    final com.jingdong.manto.m.z0.g A;
    final int w;
    final WeakReference<com.jingdong.manto.q.n> x;
    final String y;
    final String z;

    public d(com.jingdong.manto.m.z0.g gVar, WeakReference<com.jingdong.manto.q.n> weakReference, int i2, String str, String str2) {
        this.A = gVar;
        this.x = weakReference;
        this.w = i2;
        this.y = str;
        this.z = str2;
    }

    private void E() {
        com.jingdong.manto.q.n nVar = this.x.get();
        if (nVar == null || nVar.s() == null) {
            return;
        }
        u.a().c(nVar.s());
    }

    @Override // com.jingdong.manto.widget.input.j
    public final void a(String str, int i2, boolean z, boolean z2) {
        this.A.c();
        if (this.x.get() != null) {
            try {
                String jSONObject = new JSONObject().put("value", MantoUtils.replaceChangeLineCharacter(str)).put("inputId", m()).put("cursor", i2).toString();
                if (z) {
                    this.x.get().a("onKeyboardConfirm", jSONObject, 0);
                }
                if (!z2) {
                    this.x.get().a("onKeyboardComplete", jSONObject, 0);
                }
                g.C0640g c0640g = new g.C0640g();
                HashMap hashMap = new HashMap();
                hashMap.put("inputId", Integer.valueOf(m()));
                hashMap.put("height", 0);
                c0640g.a(this.x.get()).a(hashMap).a();
            } catch (Throwable th) {
                MantoLog.e("CustomInvokeHandler", "dispatch input done, exp = %s", th);
            }
            if (z2) {
                return;
            }
            E();
        }
    }

    @Override // com.jingdong.manto.widget.input.z.b
    public void b(int i2) {
        try {
            com.jingdong.manto.q.n nVar = this.x.get();
            if (nVar != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("inputId", m());
                jSONObject.put("height", MantoDensityUtils.pixel2dip(i2));
                nVar.a("onKeyboardShow", jSONObject.toString(), 0);
                new g.C0640g().a(nVar).a(jSONObject.toString()).a();
            }
        } catch (Exception e2) {
            MantoLog.e("CustomInvokeHandler", "sendInputHeightEvent: ====>", e2);
        }
    }

    @Override // com.jingdong.manto.widget.input.j
    public void v() {
        String b;
        com.jingdong.manto.q.n nVar = this.x.get();
        if (nVar != null) {
            try {
                int m2 = m();
                g.h hVar = new g.h();
                JSONObject put = new JSONObject().put("value", "");
                b = com.jingdong.manto.m.z0.b.b(m2);
                JSONObject put2 = put.put("data", b).put("cursor", 0).put("inputId", m2).put("keyCode", 8);
                hVar.a(nVar);
                hVar.f13315c = put2.toString();
                hVar.a();
            } catch (Exception e2) {
                MantoLog.e("CustomInvokeHandler", "onBackspaceWhenValueEmpty, e = %s", e2);
            }
        }
    }

    @Override // com.jingdong.manto.widget.input.j
    public final void x() {
        if (this.x.get() != null) {
            int m2 = m();
            HashMap hashMap = new HashMap(1);
            hashMap.put("inputId", Integer.valueOf(m2));
            this.x.get().a(this.w, this.A.putErrMsg(IMantoBaseModule.SUCCESS, hashMap, this.z));
            com.jingdong.manto.m.z0.b.a(m2, this.y);
            com.jingdong.manto.m.z0.b.a(m2, this.x.get());
        }
    }

    @Override // com.jingdong.manto.widget.input.j
    public final void y() {
        if (this.x.get() != null) {
            this.x.get().a(this.w, this.A.putErrMsg("fail", null, this.z));
            E();
        }
    }
}
