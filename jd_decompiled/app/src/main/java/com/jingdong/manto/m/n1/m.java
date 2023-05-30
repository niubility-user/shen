package com.jingdong.manto.m.n1;

import android.text.TextUtils;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoStringUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class m extends d0 {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ com.jingdong.manto.h a;
        final /* synthetic */ int b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ o f13476c;
        final /* synthetic */ String d;

        a(com.jingdong.manto.h hVar, int i2, o oVar, String str) {
            this.a = hVar;
            this.b = i2;
            this.f13476c = oVar;
            this.d = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a(this.b, m.this.putErrMsg(this.f13476c.f13485k, null, this.d));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        String optString = jSONObject.optString("key");
        String optString2 = jSONObject.optString("data");
        String optString3 = jSONObject.optString("dataType");
        if (TextUtils.isEmpty(optString)) {
            hVar.a(i2, putErrMsg("fail", null, str));
            return;
        }
        if ((optString2 == null ? 0 : optString2.length()) + optString.length() > hVar.h().s.p.b) {
            hVar.a(i2, putErrMsg("fail:entry size limit reached", null, str));
            return;
        }
        o oVar = new o();
        String optional = MantoStringUtils.optional(hVar.h().f13016h == null ? "" : hVar.h().f13016h.type, "");
        oVar.d = hVar.a();
        oVar.f13479e = optional;
        oVar.a(optString, optString2, optString3);
        oVar.f13484j = new a(hVar, i2, oVar, str);
        oVar.d();
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "setStorage";
    }
}
