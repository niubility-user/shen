package com.jdjr.risk.device.b;

import android.content.Context;
import com.jdjr.risk.assist.info.BuildConfig;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class k extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public k() {
        this.a = new com.jdjr.risk.device.entity.j();
    }

    @Override // com.jdjr.risk.device.b.a
    public String a() {
        return "device_sdk_info";
    }

    @Override // com.jdjr.risk.device.b.a
    protected void b(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            com.jdjr.risk.device.entity.j jVar = (com.jdjr.risk.device.entity.j) this.a;
            if (jSONObject.optInt("ver") == 1) {
                jVar.a(BuildConfig.BIOVersionName);
            }
        }
    }
}
