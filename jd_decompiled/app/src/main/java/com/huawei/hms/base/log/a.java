package com.huawei.hms.base.log;

import android.content.Context;
import com.huawei.hms.support.log.HMSExtLogger;

/* loaded from: classes12.dex */
public class a implements d {
    private final HMSExtLogger a;
    private d b;

    public a(HMSExtLogger hMSExtLogger) {
        this.a = hMSExtLogger;
    }

    @Override // com.huawei.hms.base.log.d
    public void a(Context context, String str) {
        d dVar = this.b;
        if (dVar != null) {
            dVar.a(context, str);
        }
    }

    @Override // com.huawei.hms.base.log.d
    public void a(d dVar) {
        this.b = dVar;
    }

    @Override // com.huawei.hms.base.log.d
    public void a(String str, int i2, String str2, String str3) {
        HMSExtLogger hMSExtLogger = this.a;
        if (hMSExtLogger != null) {
            if (i2 == 3) {
                hMSExtLogger.d(str2, str3);
            } else if (i2 == 4) {
                hMSExtLogger.i(str2, str3);
            } else if (i2 != 5) {
                hMSExtLogger.e(str2, str3);
            } else {
                hMSExtLogger.w(str2, str3);
            }
        }
        d dVar = this.b;
        if (dVar != null) {
            dVar.a(str, i2, str2, str3);
        }
    }
}
