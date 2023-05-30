package com.jingdong.manto.m.z0;

import android.graphics.Rect;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.q.n;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoUtils;
import com.jingdong.manto.widget.input.k;
import com.jingdong.manto.widget.input.o;
import com.jingdong.manto.widget.input.y;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class h extends b<com.jingdong.manto.widget.input.a0.a> {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ com.jingdong.manto.widget.input.a0.g b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ n f13848c;
        final /* synthetic */ int d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f13849e;

        a(int i2, com.jingdong.manto.widget.input.a0.g gVar, n nVar, int i3, String str) {
            this.a = i2;
            this.b = gVar;
            this.f13848c = nVar;
            this.d = i3;
            this.f13849e = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            Integer num;
            n nVar;
            int i2;
            h hVar;
            String str;
            String str2;
            n nVar2;
            com.jingdong.manto.widget.input.i iVar;
            com.jingdong.manto.widget.input.c cVar = k.b.a.get(Integer.valueOf(this.a));
            if (cVar != null) {
                String str3 = this.b.a;
                if (str3 != null) {
                    cVar.a(str3);
                }
                cVar.b(this.b);
                y yVar = (y) cVar.g();
                if (yVar != null && (nVar2 = cVar.f14446c.get()) != null && nVar2.s() != null && (iVar = nVar2.v) != null) {
                    Rect i3 = cVar.i();
                    iVar.a(nVar2.s(), yVar, i3.width(), i3.height(), i3.left, i3.top);
                }
                num = 1;
            } else {
                num = null;
            }
            if (num == null && !o.a(this.b, this.a)) {
                nVar = this.f13848c;
                i2 = this.d;
                hVar = h.this;
                str = this.f13849e;
                str2 = "fail";
            } else {
                nVar = this.f13848c;
                i2 = this.d;
                hVar = h.this;
                str = this.f13849e;
                str2 = IMantoBaseModule.SUCCESS;
            }
            nVar.a(i2, hVar.putErrMsg(str2, null, str));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(n nVar, JSONObject jSONObject, int i2, String str) {
        com.jingdong.manto.widget.input.a0.g gVar = new com.jingdong.manto.widget.input.a0.g();
        if (a((h) gVar, jSONObject, nVar, i2)) {
            try {
                int i3 = jSONObject.getInt("inputId");
                Integer num = gVar.f14402c;
                if (num != null && num.intValue() < 0) {
                    gVar.f14402c = 0;
                }
                Integer num2 = gVar.d;
                if (num2 != null && num2.intValue() < 0) {
                    gVar.d = 0;
                }
                String optString = jSONObject.optString("data", null);
                if (optString != null) {
                    b.a(i3, optString);
                }
                MantoUtils.runOnUiThread(new a(i3, gVar, nVar, i2, str));
            } catch (Throwable th) {
                MantoLog.e("JsApiUpdateInput", "exec: error!", th);
                nVar.a(i2, putErrMsg("fail:invalid data", null, str));
            }
        }
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "updateInput";
    }
}
