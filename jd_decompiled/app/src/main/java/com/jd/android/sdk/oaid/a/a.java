package com.jd.android.sdk.oaid.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import com.asus.msa.SupplementaryDID.IDidAidlInterface;
import com.jd.android.sdk.oaid.OaidInfo;
import com.jd.android.sdk.oaid.OaidInfoRequestListener;
import com.jd.android.sdk.oaid.a.j;

/* loaded from: classes12.dex */
public class a implements com.jd.android.sdk.oaid.a {
    private static final String b = "a";
    Context a;

    public a(Context context) {
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
        Intent intent = new Intent("com.asus.msa.action.ACCESS_DID");
        intent.setComponent(new ComponentName("com.asus.msa.SupplementaryDID", "com.asus.msa.SupplementaryDID.SupplementaryDIDService"));
        j.a(this.a, intent, oaidInfoRequestListener, new j.a() { // from class: com.jd.android.sdk.oaid.a.a.1
            @Override // com.jd.android.sdk.oaid.a.j.a
            public final String a(IBinder iBinder) {
                IDidAidlInterface asInterface = IDidAidlInterface.Stub.asInterface(iBinder);
                return (asInterface != null && asInterface.isSupport()) ? asInterface.getOAID() : "";
            }
        });
    }

    @Override // com.jd.android.sdk.oaid.a
    public final boolean a() {
        Context context = this.a;
        if (context != null && Build.VERSION.SDK_INT >= 29) {
            try {
                return context.getPackageManager().getPackageInfo("com.asus.msa.SupplementaryDID", 0) != null;
            } catch (Exception e2) {
                com.jd.android.sdk.oaid.b.a(b, "isSupport : ", e2);
                return false;
            }
        }
        return false;
    }
}
