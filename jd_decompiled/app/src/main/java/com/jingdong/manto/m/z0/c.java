package com.jingdong.manto.m.z0;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.e0;
import com.jingdong.manto.m.z0.g;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.input.j;
import com.jingdong.manto.widget.input.u;
import com.jingdong.manto.widget.input.v;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class c extends g {

    /* loaded from: classes15.dex */
    class a implements j.p {
        final /* synthetic */ j a;

        a(c cVar, j jVar) {
            this.a = jVar;
        }

        @Override // com.jingdong.manto.widget.input.j.p
        public void a(int i2, int i3) {
            int m2 = this.a.m();
            n a = com.jingdong.manto.m.z0.b.a(m2);
            if (a == null || !a.f()) {
                return;
            }
            MantoLog.e("JsApiInsertTextArea", "onLineHeightChanged: height=" + i3 + ",  lineCount = " + i2);
            C0639c c0639c = new C0639c(null);
            HashMap hashMap = new HashMap();
            hashMap.put("height", Integer.valueOf(MantoDensityUtils.convertToWebSize(i3)));
            hashMap.put("lineCount", Integer.valueOf(i2));
            hashMap.put("inputId", Integer.valueOf(m2));
            c0639c.a(a.h(), 0).a(hashMap).a(new int[]{a.hashCode()});
        }
    }

    /* loaded from: classes15.dex */
    class b extends v {
        final /* synthetic */ WeakReference w;
        final /* synthetic */ int x;
        final /* synthetic */ String y;
        final /* synthetic */ String z;

        b(WeakReference weakReference, int i2, String str, String str2) {
            c.this = r1;
            this.w = weakReference;
            this.x = i2;
            this.y = str;
            this.z = str2;
        }

        private void E() {
            n nVar = (n) this.w.get();
            if (nVar == null || nVar.s() == null) {
                return;
            }
            u.a().c(nVar.s());
        }

        @Override // com.jingdong.manto.widget.input.j
        public void a(String str, int i2, boolean z, boolean z2) {
            MantoLog.e("JsApiInsertTextArea", "onInputDone: value=" + str + ", cursor=" + i2 + ", sendConfirm=" + z + ", confirmHold=" + z2);
            if (this.w.get() != null) {
                try {
                    String jSONObject = new JSONObject().put("value", MantoUtils.replaceChangeLineCharacter(str)).put("inputId", m()).put("cursor", i2).toString();
                    if (z) {
                        ((n) this.w.get()).a("onKeyboardConfirm", jSONObject, 0);
                    }
                    if (!z2) {
                        ((n) this.w.get()).a("onKeyboardComplete", jSONObject, 0);
                    }
                    g.C0640g c0640g = new g.C0640g();
                    HashMap hashMap = new HashMap();
                    hashMap.put("inputId", Integer.valueOf(m()));
                    hashMap.put("height", 0);
                    c0640g.a((e0) this.w.get()).a(hashMap).a();
                } catch (Throwable th) {
                    MantoLog.e("JsApiInsertTextArea", "dispatch input done, exp = %s", th);
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
                n nVar = (n) this.w.get();
                if (nVar != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("inputId", m());
                    jSONObject.put("height", MantoDensityUtils.pixel2dip(i2));
                    nVar.a("onKeyboardShow", jSONObject.toString(), 0);
                    new g.C0640g().a(nVar).a(jSONObject.toString()).a();
                }
            } catch (Exception e2) {
                MantoLog.e("JsApiInsertTextArea", "sendInputHeightEvent: exp ", e2);
            }
        }

        @Override // com.jingdong.manto.widget.input.j
        public void v() {
            n nVar = (n) this.w.get();
            if (nVar != null) {
                try {
                    int m2 = m();
                    g.h hVar = new g.h();
                    JSONObject put = new JSONObject().put("value", "").put("data", com.jingdong.manto.m.z0.b.b(m2)).put("cursor", 0).put("inputId", m2).put("keyCode", 8);
                    hVar.a(nVar);
                    hVar.f13315c = put.toString();
                    hVar.a();
                } catch (Exception e2) {
                    MantoLog.e("JsApiInsertTextArea", "onBackspaceWhenValueEmpty, e = %s", e2);
                }
            }
        }

        @Override // com.jingdong.manto.widget.input.j
        public void x() {
            if (this.w.get() != null) {
                int m2 = m();
                HashMap hashMap = new HashMap(1);
                hashMap.put("inputId", Integer.valueOf(m2));
                ((n) this.w.get()).a(this.x, c.this.putErrMsg(IMantoBaseModule.SUCCESS, hashMap, this.y));
                com.jingdong.manto.m.z0.b.a(m2, this.z);
                com.jingdong.manto.m.z0.b.a(m2, (n) this.w.get());
            }
        }

        @Override // com.jingdong.manto.widget.input.j
        public void y() {
            if (this.w.get() != null) {
                ((n) this.w.get()).a(this.x, c.this.putErrMsg("fail", null, this.y));
                E();
            }
        }
    }

    /* renamed from: com.jingdong.manto.m.z0.c$c */
    /* loaded from: classes15.dex */
    private static final class C0639c extends com.jingdong.manto.m.d {
        private C0639c() {
        }

        /* synthetic */ C0639c(a aVar) {
            this();
        }

        @Override // com.jingdong.manto.m.a
        public String getJsApiName() {
            return "onTextAreaHeightChange";
        }
    }

    @Override // com.jingdong.manto.m.z0.g
    protected final j a(WeakReference<n> weakReference, String str, int i2, String str2) {
        return new b(weakReference, i2, str2, str);
    }

    @Override // com.jingdong.manto.m.z0.g
    public final void a(j jVar) {
        super.a(jVar);
        jVar.a(new a(this, jVar));
    }

    @Override // com.jingdong.manto.m.z0.g
    public final boolean a(com.jingdong.manto.widget.input.a0.f fVar, JSONObject jSONObject, n nVar, int i2, String str) {
        if (super.a(fVar, jSONObject, nVar, i2, str)) {
            fVar.u = Boolean.TRUE;
            fVar.M = "text";
            fVar.I = false;
            fVar.J = false;
            fVar.A = Boolean.FALSE;
            fVar.v = Boolean.valueOf(jSONObject.optBoolean("confirm", true));
            fVar.E = false;
            return true;
        }
        return false;
    }

    @Override // com.jingdong.manto.m.z0.g
    protected final boolean b() {
        return false;
    }

    @Override // com.jingdong.manto.m.z0.g
    public final boolean c() {
        return false;
    }

    @Override // com.jingdong.manto.m.z0.g, com.jingdong.manto.m.d0
    public final void exec(n nVar, JSONObject jSONObject, int i2, String str) {
        super.exec(nVar, jSONObject, i2, str);
    }

    @Override // com.jingdong.manto.m.z0.g, com.jingdong.manto.m.a
    public String getJsApiName() {
        return "insertTextArea";
    }
}
