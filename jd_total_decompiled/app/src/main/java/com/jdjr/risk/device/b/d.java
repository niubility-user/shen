package com.jdjr.risk.device.b;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONObject;
import tv.danmaku.ijk.media.ext.config.PlayerConfigKey;

/* loaded from: classes18.dex */
public class d extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public d() {
        this.a = new com.jdjr.risk.device.entity.d();
    }

    @Override // com.jdjr.risk.device.b.a
    public String a() {
        return "device_cpu_info";
    }

    @Override // com.jdjr.risk.device.b.a
    protected void b(Context context, JSONObject jSONObject) {
        if (jSONObject != null) {
            com.jdjr.risk.device.entity.d dVar = (com.jdjr.risk.device.entity.d) this.a;
            if (jSONObject.optInt("abi") == 1) {
                dVar.a(com.jdjr.risk.device.c.h.g());
            }
            if (jSONObject.optInt("usg") == 1) {
                dVar.b(com.jdjr.risk.device.c.h.f());
            }
            if (jSONObject.optInt("num") == 1) {
                dVar.c(com.jdjr.risk.device.c.h.a());
            }
            if (jSONObject.optInt("freq") == 1) {
                dVar.d(com.jdjr.risk.device.c.h.d());
            }
            if (jSONObject.optInt("abi_c") == 1) {
                dVar.f(com.jdjr.risk.device.c.h.e());
            }
            if (jSONObject.optInt(PlayerConfigKey.AudioConfigKey.KEY_MAX_FREQ) == 1) {
                dVar.g(TextUtils.equals("-1", com.jdjr.risk.device.c.h.b()) ? "" : com.jdjr.risk.device.c.h.b());
            }
            if (jSONObject.optInt("cpuN") == 1) {
                dVar.e(com.jdjr.risk.device.c.h.c());
            }
        }
    }
}
