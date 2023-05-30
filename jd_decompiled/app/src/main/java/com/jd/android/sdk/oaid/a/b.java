package com.jd.android.sdk.oaid.a;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import com.coolpad.deviceidsupport.IDeviceIdManager;
import com.jd.android.sdk.oaid.OaidInfo;
import com.jd.android.sdk.oaid.OaidInfoRequestListener;
import com.jd.android.sdk.oaid.a.j;

/* loaded from: classes12.dex */
public class b implements com.jd.android.sdk.oaid.a {
    private static final String a = "b";
    private final Context b;

    public b(Context context) {
        this.b = context instanceof Application ? context : context.getApplicationContext();
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
        intent.setComponent(new ComponentName("com.coolpad.deviceidsupport", "com.coolpad.deviceidsupport.DeviceIdService"));
        j.a(this.b, intent, oaidInfoRequestListener, new j.a() { // from class: com.jd.android.sdk.oaid.a.b.1
            @Override // com.jd.android.sdk.oaid.a.j.a
            public final String a(IBinder iBinder) {
                IDeviceIdManager asInterface = IDeviceIdManager.Stub.asInterface(iBinder);
                return asInterface == null ? "" : asInterface.getOAID(b.this.b.getPackageName());
            }
        });
    }

    @Override // com.jd.android.sdk.oaid.a
    public final boolean a() {
        Context context = this.b;
        if (context != null && Build.VERSION.SDK_INT >= 29) {
            try {
                return context.getPackageManager().getPackageInfo("com.coolpad.deviceidsupport", 0) != null;
            } catch (Exception e2) {
                com.jd.android.sdk.oaid.b.a(a, "isSupported", e2);
                return false;
            }
        }
        return false;
    }
}
