package com.jd.android.sdk.oaid.a;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import com.jd.android.sdk.oaid.OaidInfo;
import com.jd.android.sdk.oaid.OaidInfoRequestListener;
import com.jd.android.sdk.oaid.a.j;
import com.zui.deviceidservice.IDeviceidInterface;

/* loaded from: classes12.dex */
public class e implements com.jd.android.sdk.oaid.a {
    private static final String b = "e";
    Context a;

    public e(Context context) {
        this.a = context;
    }

    @Override // com.jd.android.sdk.oaid.a
    public final void a(OaidInfoRequestListener oaidInfoRequestListener) {
        if (this.a == null) {
            return;
        }
        if (!a()) {
            oaidInfoRequestListener.onResult(new OaidInfo());
            return;
        }
        Intent intent = new Intent();
        intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
        j.a(this.a, intent, oaidInfoRequestListener, new j.a() { // from class: com.jd.android.sdk.oaid.a.e.1
            @Override // com.jd.android.sdk.oaid.a.j.a
            public final String a(IBinder iBinder) {
                IDeviceidInterface asInterface = IDeviceidInterface.Stub.asInterface(iBinder);
                return (asInterface != null && asInterface.isSupport()) ? asInterface.getOAID() : "";
            }
        });
    }

    @Override // com.jd.android.sdk.oaid.a
    public final boolean a() {
        Context context = this.a;
        if (context != null && Build.VERSION.SDK_INT >= 29) {
            try {
                return context.getPackageManager().getPackageInfo("com.zui.deviceidservice", 0) != null;
            } catch (Exception e2) {
                com.jd.android.sdk.oaid.b.a(b, "isSupport : ", e2);
                return false;
            }
        }
        return false;
    }
}
