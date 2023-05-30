package com.jingdong.manto.m;

import com.jingdong.common.jdmiaosha.view.WebContainerUtil;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.q.l;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class a0 extends d0 {

    /* loaded from: classes15.dex */
    class a implements l.u {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13274c;

        a(com.jingdong.manto.h hVar, int i2, String str) {
            this.a = hVar;
            this.b = i2;
            this.f13274c = str;
        }

        @Override // com.jingdong.manto.q.l.u
        public void onFail() {
            this.a.a(this.b, a0.this.putErrMsg("fail", null, this.f13274c));
        }

        @Override // com.jingdong.manto.q.l.u
        public void onSuccess() {
            this.a.a(this.b, a0.this.putErrMsg(IMantoBaseModule.SUCCESS, null, this.f13274c));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String putErrMsg;
        String optString = jSONObject.optString("url");
        if (hVar.h() == null || hVar.h().t == null || hVar.h().t.b == null) {
            putErrMsg = putErrMsg("fail", null, str);
        } else if (hVar.h().t.b.a(optString)) {
            if (hVar.h() == null || hVar.h().f13014f == null) {
                hVar.a(i2, putErrMsg("fail", null, str));
                return;
            } else {
                hVar.h().f13014f.c(optString, new a(hVar, i2, str));
                return;
            }
        } else {
            putErrMsg = putErrMsg("fail:can not switch to non-TabBar page", null, str);
        }
        hVar.a(i2, putErrMsg);
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return WebContainerUtil.EVENT_SWITCH_TAB;
    }
}
