package com.jdjr.risk.device.c;

import android.content.Context;
import com.jdjr.risk.util.constant.RiskType;
import com.jdjr.risk.util.httputil.LorasHttpCallback;

/* loaded from: classes18.dex */
public class n {
    private String a = "";

    public String a(Context context) {
        com.jdjr.risk.biometric.core.c.a(context, "JDSC-QD-GXPM", RiskType.SCREEN_MIRRORING, "", new LorasHttpCallback() { // from class: com.jdjr.risk.device.c.n.1
            @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
            public void onFailInCurentThread(int i2, String str) {
                n.this.a = "-1";
            }

            @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
            public void onFailInNetThread(int i2, String str) {
                n.this.a = "-1";
            }

            @Override // com.jdjr.risk.util.httputil.LorasHttpCallback
            public void onSuccess(String str) {
                n.this.a = str;
            }
        });
        return this.a;
    }
}
