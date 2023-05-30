package com.jingdong.manto.m;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.q.l;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class m extends d0 {

    /* loaded from: classes15.dex */
    class a implements l.u {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13449c;

        a(com.jingdong.manto.h hVar, int i2, String str) {
            this.a = hVar;
            this.b = i2;
            this.f13449c = str;
        }

        @Override // com.jingdong.manto.q.l.u
        public void onFail() {
            this.a.a(this.b, m.this.putErrMsg("fail", null, this.f13449c));
        }

        @Override // com.jingdong.manto.q.l.u
        public void onSuccess() {
            this.a.a(this.b, m.this.putErrMsg(IMantoBaseModule.SUCCESS, null, this.f13449c));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        if (hVar.h() == null || hVar.h().f13014f == null) {
            hVar.a(i2, putErrMsg("fail", null, str));
        } else {
            hVar.h().f13014f.a(jSONObject.optInt("delta", 1), true, (l.u) new a(hVar, i2, str));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "navigateBack";
    }
}
