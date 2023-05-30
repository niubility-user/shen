package com.jingdong.manto.m.n1;

import android.text.TextUtils;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.m.d0;
import com.jingdong.manto.utils.MantoStringUtils;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class d extends d0 {

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ i a;
        final /* synthetic */ com.jingdong.manto.h b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ int f13457c;
        final /* synthetic */ String d;

        a(i iVar, com.jingdong.manto.h hVar, int i2, String str, long j2) {
            this.a = iVar;
            this.b = hVar;
            this.f13457c = i2;
            this.d = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            String str = this.a.f13473k == null ? "fail" : IMantoBaseModule.SUCCESS;
            HashMap hashMap = new HashMap();
            String str2 = this.a.f13473k;
            if (str2 == null) {
                str2 = "";
            }
            hashMap.put("data", str2);
            String str3 = this.a.f13472j;
            hashMap.put("dataType", str3 != null ? str3 : "");
            this.b.a(this.f13457c, d.this.putErrMsg(str, hashMap, this.d));
        }
    }

    @Override // com.jingdong.manto.m.d0
    public void exec(com.jingdong.manto.h hVar, JSONObject jSONObject, int i2, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        String optString = jSONObject.optString("key");
        if (TextUtils.isEmpty(optString)) {
            hVar.a(i2, putErrMsg("fail", null, str));
            return;
        }
        i iVar = new i();
        iVar.f13467e = MantoStringUtils.optional(hVar.h().f13016h == null ? "" : hVar.h().f13016h.type, "");
        iVar.d = hVar.a();
        iVar.f13466c = optString;
        iVar.f13471i = new a(iVar, hVar, i2, str, currentTimeMillis);
        iVar.d();
    }

    @Override // com.jingdong.manto.m.a
    public String getJsApiName() {
        return "getStorage";
    }
}
