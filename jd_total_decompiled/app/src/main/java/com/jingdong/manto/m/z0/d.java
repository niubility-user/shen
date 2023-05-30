package com.jingdong.manto.m.z0;

import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.input.o;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d extends d0 {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ n a;
        final /* synthetic */ JSONObject b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13834c;
        final /* synthetic */ String d;

        a(n nVar, JSONObject jSONObject, int i2, String str) {
            this.a = nVar;
            this.b = jSONObject;
            this.f13834c = i2;
            this.d = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.manto.utils.e.b(d.this.getCore(this.a).getActivity());
            this.a.a(this.f13834c, d.this.putErrMsg(o.b(this.b.optInt("inputId")) ? IMantoBaseModule.SUCCESS : "fail", null, this.d));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public final void exec(n nVar, JSONObject jSONObject, int i2, String str) {
        try {
            MantoUtils.runOnUiThread(new a(nVar, jSONObject, i2, str));
        } catch (Throwable unused) {
            nVar.a(i2, putErrMsg("fail:invalid data", null, str));
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "removeTextArea";
    }
}
