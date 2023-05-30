package com.jingdong.manto.m;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.q.l;
import com.jingdong.manto.utils.MantoLog;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class i0 extends d0 {
    private static final String a = "i0";

    /* loaded from: classes15.dex */
    class a implements l.u {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13368c;

        a(com.jingdong.manto.h hVar, int i2, String str) {
            this.a = hVar;
            this.b = i2;
            this.f13368c = str;
        }

        @Override // com.jingdong.manto.q.l.u
        public void onFail() {
            this.a.a(this.b, i0.this.putErrMsg("fail", null, this.f13368c));
        }

        @Override // com.jingdong.manto.q.l.u
        public void onSuccess() {
            this.a.a(this.b, i0.this.putErrMsg(IMantoBaseModule.SUCCESS, null, this.f13368c));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String str2;
        int i3;
        if (hVar.h() == null || hVar.h().f13014f == null || hVar.h().s == null || hVar.h().f13014f.getPageCount() < (i3 = hVar.h().s.d)) {
            String optString = jSONObject.optString("url");
            if (hVar.h() == null || hVar.h().t == null || hVar.h().t.b == null || !hVar.h().t.b.a(optString)) {
                if (hVar.h() == null || hVar.h().f13014f == null) {
                    hVar.a(i2, putErrMsg("fail", null, str));
                    return;
                }
                com.jingdong.manto.q.l lVar = hVar.h().f13014f;
                lVar.a(new l.v(lVar, optString, new a(hVar, i2, str)));
                return;
            }
            str2 = "fail:can not navigate to a tab bar page";
        } else {
            str2 = String.format("fail:page limit exceeded: %d", Integer.valueOf(i3));
        }
        hVar.a(i2, putErrMsg(str2, null, str));
        MantoLog.e(a, str2);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "navigateTo";
    }
}
