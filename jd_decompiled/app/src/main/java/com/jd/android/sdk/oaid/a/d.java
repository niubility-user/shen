package com.jd.android.sdk.oaid.a;

import android.content.Context;
import android.content.pm.PackageManager;
import com.huawei.hms.ads.identifier.AdvertisingIdClient;
import com.huawei.hms.common.PackageConstants;
import com.jd.android.sdk.oaid.OaidInfo;
import com.jd.android.sdk.oaid.OaidInfoRequestListener;
import java.io.IOException;

/* loaded from: classes12.dex */
public class d implements com.jd.android.sdk.oaid.a {
    private static final String a = "d";
    private Context b;

    /* renamed from: c  reason: collision with root package name */
    private String f1694c;

    public d(Context context) {
        this.b = context;
    }

    @Override // com.jd.android.sdk.oaid.a
    public final void a(final OaidInfoRequestListener oaidInfoRequestListener) {
        new Thread(new Runnable() { // from class: com.jd.android.sdk.oaid.a.d.1
            @Override // java.lang.Runnable
            public final void run() {
                String str;
                String str2;
                String str3 = "";
                try {
                    AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(d.this.b);
                    if (advertisingIdInfo != null) {
                        str3 = advertisingIdInfo.getId();
                    }
                } catch (IOException e2) {
                    e = e2;
                    str = d.a;
                    str2 = "Catched !! getAdvertisingIdInfo Exception: ";
                    com.jd.android.sdk.oaid.b.a(str, str2, e);
                    oaidInfoRequestListener.onResult(new OaidInfo(str3));
                } catch (Throwable th) {
                    e = th;
                    str = d.a;
                    str2 = "Catched !! getAdvertisingIdInfo Throwable: ";
                    com.jd.android.sdk.oaid.b.a(str, str2, e);
                    oaidInfoRequestListener.onResult(new OaidInfo(str3));
                }
                oaidInfoRequestListener.onResult(new OaidInfo(str3));
            }
        }).start();
    }

    @Override // com.jd.android.sdk.oaid.a
    public final boolean a() {
        Context context = this.b;
        if (context == null) {
            return false;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager.getPackageInfo("com.huawei.hwid", 0) != null) {
                this.f1694c = "com.huawei.hwid";
            } else if (packageManager.getPackageInfo("com.huawei.hwid.tv", 0) != null) {
                this.f1694c = "com.huawei.hwid.tv";
            } else {
                this.f1694c = PackageConstants.SERVICES_PACKAGE_ALL_SCENE;
                if (packageManager.getPackageInfo(PackageConstants.SERVICES_PACKAGE_ALL_SCENE, 0) == null) {
                    return false;
                }
            }
            return true;
        } catch (Exception e2) {
            com.jd.android.sdk.oaid.b.a(a, "isSupport", e2);
            return false;
        }
    }
}
