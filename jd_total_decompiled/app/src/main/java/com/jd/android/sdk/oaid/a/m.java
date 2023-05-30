package com.jd.android.sdk.oaid.a;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import com.jd.android.sdk.oaid.OaidInfo;
import com.jd.android.sdk.oaid.OaidInfoRequestListener;
import com.jd.android.sdk.oaid.a.j;
import com.samsung.android.deviceidservice.IDeviceIdService;

/* loaded from: classes12.dex */
public class m implements com.jd.android.sdk.oaid.a {
    private static final String a = "m";
    private final Context b;

    public m(Context context) {
        this.b = context;
    }

    @Override // com.jd.android.sdk.oaid.a
    public final void a(OaidInfoRequestListener oaidInfoRequestListener) {
        if (this.b == null) {
            return;
        }
        if (!a()) {
            oaidInfoRequestListener.onResult(new OaidInfo());
            return;
        }
        Intent intent = new Intent();
        intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
        j.a(this.b, intent, oaidInfoRequestListener, new j.a() { // from class: com.jd.android.sdk.oaid.a.m.1
            @Override // com.jd.android.sdk.oaid.a.j.a
            public final String a(IBinder iBinder) {
                IDeviceIdService asInterface = IDeviceIdService.Stub.asInterface(iBinder);
                if (asInterface != null) {
                    return asInterface.getOAID();
                }
                throw new h("IDeviceIdService is null");
            }
        });
    }

    @Override // com.jd.android.sdk.oaid.a
    public final boolean a() {
        Context context = this.b;
        if (context != null && Build.VERSION.SDK_INT >= 29) {
            try {
                return context.getPackageManager().getPackageInfo("com.samsung.android.deviceidservice", 0) != null;
            } catch (Exception e2) {
                com.jd.android.sdk.oaid.b.a(a, "isSupport : ", e2);
                return false;
            }
        }
        return false;
    }
}
