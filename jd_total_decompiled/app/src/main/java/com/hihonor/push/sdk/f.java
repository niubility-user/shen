package com.hihonor.push.sdk;

import org.json.JSONException;

/* loaded from: classes12.dex */
public class f implements e0<c> {
    public final /* synthetic */ HonorMessageService a;

    public f(HonorMessageService honorMessageService) {
        this.a = honorMessageService;
    }

    @Override // com.hihonor.push.sdk.e0
    public void a(e<c> eVar) {
        if (eVar.f()) {
            c d = eVar.d();
            if (d != null) {
                String str = "onMessageReceived. msgId is " + d.b();
                this.a.onMessageReceived(d);
                return;
            }
            return;
        }
        boolean z = eVar.c() instanceof JSONException;
    }
}
