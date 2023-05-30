package com.jingdong.manto.m.z0;

import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class i extends h {
    @Override // com.jingdong.manto.m.z0.b
    public final boolean a(com.jingdong.manto.widget.input.a0.a aVar, JSONObject jSONObject, n nVar, int i2) {
        boolean a = super.a((i) aVar, jSONObject, nVar, i2);
        MantoLog.e("JsApiUpdateTextArea", "parseParam: return super : " + a + ", param:" + jSONObject);
        aVar.u = Boolean.TRUE;
        aVar.A = Boolean.FALSE;
        aVar.v = MantoUtils.isTrue(jSONObject.opt("confirm"));
        MantoLog.e("JsApiUpdateTextArea", "inputParam=" + aVar);
        return a;
    }

    @Override // com.jingdong.manto.m.z0.h, com.jingdong.manto.m.a
    public String getJsApiName() {
        return "updateTextArea";
    }
}
